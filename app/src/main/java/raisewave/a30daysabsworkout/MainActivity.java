package raisewave.a30daysabsworkout;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton img_calender,img_exercises,img_progress,img_rateus,img_moreapps,img_share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainfin);
        img_calender=(ImageButton)findViewById(R.id.image_calendar);
        img_exercises=(ImageButton)findViewById(R.id.image_exercise);
        img_progress=(ImageButton)findViewById(R.id.image_progress);
        img_rateus=(ImageButton)findViewById(R.id.image_rateus);
        img_moreapps=(ImageButton)findViewById(R.id.image_moreappps);
        img_share=(ImageButton)findViewById(R.id.image_share);

        img_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent days_intent=new Intent(MainActivity.this,DaysList.class);
                startActivity(days_intent);
            }
        });

        img_exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exercises_intent=new Intent(MainActivity.this,TotalExerciseList.class);
                startActivity(exercises_intent);
            }
        });
        img_rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.axismobile.allbankbalancechecker")));
            }
        });
        img_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent next_intent=new Intent(MainActivity.this,Progress.class);

                startActivity(next_intent);
            }
        });
        img_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
                    String sAux = "\nHey Checkout this application\n\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=com.axismobile.allbankbalancechecker \n\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });
      }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        MenuInflater mv=getMenuInflater();
        mv.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId())
        {

            case R.id.action_settings:
                startActivity(new Intent(MainActivity.this,Settings.class));
                 return  true;

            case R.id.action_moreapps:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.axismobile.allbankbalancechecker")));
                break;

            default:
                break;
        }
        return true;
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        finish();
    }
}
