package raisewave.a30daysabsworkout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 8/10/2016.
 */
public class SitupExercise extends ActionBarActivity{
    Button btn_next;
    ImageView img_instruction;
    Button btn_instruction;
    ProgressBar pg_bar;
    String dayvalue;
    List<ExercisesDetailBean> exercise_bean=new ArrayList<ExercisesDetailBean>();
    TextView text_instruction;
    ActionBar actionBar;
   // MediaPlayer mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exericse);
        btn_next=(Button) findViewById(R.id.button_ready);
        btn_instruction=(Button)findViewById(R.id.button_exerciseinstruction);
        pg_bar=(ProgressBar)findViewById(R.id.progressBar) ;
        text_instruction=(TextView)findViewById(R.id.text_instructionshortdescription);
        pg_bar.setVisibility(View.INVISIBLE);
        Intent get_intent=getIntent();
        dayvalue=get_intent.getStringExtra("dayvalue");

        actionBar = getSupportActionBar();
        actionBar.setTitle("Day "+dayvalue);
       // mPlayer = MediaPlayer.create(SitupExercise.this, R.raw.sound);
        DataBaseHelper dataBaseHelper=new DataBaseHelper(SitupExercise.this);
        exercise_bean=dataBaseHelper.getDayExercises(dayvalue);
        text_instruction.setText("DO "+exercise_bean.get(0).getExercise_count()+" Sit-Ups");

        btn_instruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SitupExercise.this);
                builder.setMessage(R.string.situp_instructions)
                        .setCancelable(true)
                        .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pg_bar.setVisibility(View.VISIBLE);
                MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000, 1000);
                myCountDownTimer.start();
                btn_instruction.setVisibility(View.INVISIBLE);
                text_instruction.setText(R.string.app_rest);
                btn_next.setText("SKIP");
                btn_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent next_intent=new Intent(SitupExercise.this,CrunchesExercise.class);
                        next_intent.putExtra("dayvalue",dayvalue);
                        startActivity(next_intent);
                        finish();
                    }
                });
            }
        });
     }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            int progress = (int) (millisUntilFinished/1000);
            pg_bar.setProgress(pg_bar.getMax()-progress);
        }

        @Override
        public void onFinish() {
            btn_next.setText("NEXT");
           // mPlayer.start();
        }
    }

   /* @Override
    protected void onPause() {
        if (mPlayer!= null){
            mPlayer.release();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mPlayer!= null){
            mPlayer.release();
        }
        super.onDestroy();
    }*/
}
