package raisewave.a30daysabsworkout;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/20/2016.
 */
public class DayPlan extends ActionBarActivity {

    TextView textview_situps,textview_crunches,textview_legraises,textview_plank;
    Button  button_startexercise;
    ActionBar actionBar;
    List<ExercisesDetailBean>  exercise_bean=new ArrayList<ExercisesDetailBean>();
    String dayvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dayplan);
        textview_situps=(TextView)findViewById(R.id.text_situpsvalue);
        textview_crunches=(TextView)findViewById(R.id.text_crunchesvalue);
        textview_legraises=(TextView)findViewById(R.id.text_legraisesvalue);
        textview_plank=(TextView)findViewById(R.id.text_plankvalue);
        button_startexercise=(Button)findViewById(R.id.button_start);

        Intent get_intent=getIntent();
        dayvalue=get_intent.getStringExtra("dayvalue");

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Day "+dayvalue);
      /*  actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#303F9F")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#303F9F")));*/


        DataBaseHelper dataBaseHelper=new DataBaseHelper(DayPlan.this);
        exercise_bean=dataBaseHelper.getDayExercises(dayvalue);

        textview_situps.setText(exercise_bean.get(0).getExercise_count()+" Repetitions");
        textview_crunches.setText(exercise_bean.get(1).getExercise_count()+" Repetitions");
        textview_legraises.setText(exercise_bean.get(2).getExercise_count()+" Repetitions");
        textview_plank.setText(exercise_bean.get(3).getExercise_time()+" Seconds");

        button_startexercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent day_exercise=new Intent(DayPlan.this,SitupExercise.class);
                day_exercise.putExtra("dayvalue",dayvalue);
                startActivity(day_exercise);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
       /* Intent day_exercise=new Intent(DayPlan.this,DaysList.class);

        startActivity(day_exercise);
        finish();*/

    }
}
