package raisewave.a30daysabsworkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/20/2016.
 */
public class DaysList extends ActionBarActivity {

    ListView days_listview;
    List<DaysBean> daysBean=new ArrayList<DaysBean>();
    int position;
    boolean disable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dayslist);
        days_listview=(ListView)findViewById(R.id.dayslistView);
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,days);
         getSupportActionBar().setDisplayShowHomeEnabled(true);
         DataBaseHelper dataBaseHelper=new DataBaseHelper(DaysList.this);
         daysBean=dataBaseHelper.getDaysData();
        CustomClass customClass=new CustomClass(DaysList.this,daysBean);
        days_listview.setAdapter(customClass);
         for(int i=0;i<daysBean.size();i++)
         {
             disable=customClass.isEnabled(i);

         }


        days_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Intent dayplan_intent=new Intent(DaysList.this,DayPlan.class);
                dayplan_intent.putExtra("dayvalue",String.valueOf(position+1));
                startActivity(dayplan_intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent day_exercise=new Intent(DaysList.this,MainActivity.class);

        startActivity(day_exercise);
        finish();

    }


    }

