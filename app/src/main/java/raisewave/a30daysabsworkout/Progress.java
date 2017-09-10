package raisewave.a30daysabsworkout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 8/10/2016.
 */
public class Progress extends Activity{
     Button btn_next;
     ImageView img_instruction;
     Button btn_instruction;
     ProgressBar pg_bar;
     int pStatus = 0;
     int progdb;
     float progvalue;
     private Handler handler = new Handler();
    TextView tv,tv_daysprog;

     //Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        tv_daysprog=(TextView)findViewById(R.id.text_dayscompleted);
       /* CircularProgressBar circularProgressBar = (CircularProgressBar)findViewById(R.id.exercise_progress);
        circularProgressBar.setColor(ContextCompat.getColor(this, R.color.progressBarColor));
        circularProgressBar.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundProgressBarColor));
        circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
        circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));
        int animationDuration = 2500; // 2500ms = 2,5s
        circularProgressBar.setProgressWithAnimation(12, animationDuration); // Default duration = 1500ms*/
        DataBaseHelper db_helper=new DataBaseHelper(Progress.this);
        progdb=db_helper.getProgress();
        tv_daysprog.setText(String.valueOf(progdb)+" Days");
        progdb=progdb*100;
        progvalue =(progdb/23.0f);

        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular);
        final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        mProgress.setProgress(0);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);


      /*  ObjectAnimator animation = ObjectAnimator.ofInt(mProgress, "progress", 0, 100);
        animation.setDuration(50000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();*/

        tv = (TextView) findViewById(R.id.tv);
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus < 100) {

                    pStatus=((int)progvalue);
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress.setProgress(pStatus);
                            tv.setText(pStatus + "%");

                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(8); //thread will take approx 1.5 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    }
