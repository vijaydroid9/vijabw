package raisewave.a30daysabsworkout;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class Settings extends ActionBarActivity {

    Button btn_settings,btn_reminder,btn_disclaimer,btn_cancel;
    DataBaseHelper dataBaseHelper;
    TimePickerDialog mTimePicker;
    Calendar mcurrentTime;
    long time;
    ActionBar actionBar;
    PendingIntent pendingIntent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        btn_settings = (Button) findViewById(R.id.button_exercisesreset);
        btn_reminder=(Button)findViewById(R.id.button_setreminder);
        btn_cancel=(Button)findViewById(R.id.button_cancelreminder);
        btn_disclaimer=(Button)findViewById(R.id.button_disclaimer);
        actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.app_name));
        dataBaseHelper=new DataBaseHelper(Settings.this);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              dataBaseHelper.ResetProg();
            }
        });
        btn_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                mTimePicker = new TimePickerDialog(Settings.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timePicker.setIs24HourView(true);
                        mcurrentTime.set(Calendar.HOUR_OF_DAY,selectedHour);
                        mcurrentTime.set(Calendar.MINUTE,selectedMinute);
                        mcurrentTime.set(Calendar.SECOND,0);
                        time= mcurrentTime.getTimeInMillis();
                        Intent myIntent = new Intent(Settings.this, NotifyReceiver.class);
                        pendingIntent = PendingIntent.getBroadcast(Settings.this,1, myIntent,pendingIntent.FLAG_UPDATE_CURRENT);
                        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                        alarmManager.set(AlarmManager.RTC, time , pendingIntent);
                    }
                }, hour, minute, true);//Yes 24 hour tim

                mTimePicker.show();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(Settings.this, NotifyReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(Settings.this, 1, myIntent, pendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);

            }
                    });
        btn_disclaimer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                builder.setMessage(R.string.situp_instructions)
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                  dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}
