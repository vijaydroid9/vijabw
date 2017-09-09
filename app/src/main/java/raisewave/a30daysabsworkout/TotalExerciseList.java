package raisewave.a30daysabsworkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/20/2016.
 */
public class TotalExerciseList extends Activity {

    ListView days_listview;
    String [] days={"Day 1","Day 2","Day 3"};
    List<ExercisesBean> exercisesBean=new ArrayList<ExercisesBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totalexercises_activity);
        days_listview=(ListView)findViewById(R.id.total_exerciseslist);

        DataBaseHelper dataBaseHelper=new DataBaseHelper(TotalExerciseList.this);
        exercisesBean=dataBaseHelper.getExercises();

        Exercise_CustomTotal popular_customTotal=new Exercise_CustomTotal(TotalExerciseList.this,exercisesBean);
        days_listview.setAdapter(popular_customTotal);

        days_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent dayplan_intent=new Intent(TotalExerciseList.this,ExerciseInstruction.class);
                dayplan_intent.putExtra("dayvalue",String.valueOf(i+1));
                startActivity(dayplan_intent);
            }
        });
    }
}
