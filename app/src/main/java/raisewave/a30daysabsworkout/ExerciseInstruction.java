package raisewave.a30daysabsworkout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VK on 06-04-2017.
 */


public class ExerciseInstruction extends Activity {
    ImageView img_view;
    TextView txt_view;
    List<ExercisesBean> exercise_bean = new ArrayList<ExercisesBean>();
    String dayvalue;
    int exerciseid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exericse_instruction);
        txt_view = (TextView) findViewById(R.id.text_singleinstruction);
        img_view = (ImageView) findViewById(R.id.image_singleexerciseinstruction);

        Intent get_intent = getIntent();
        dayvalue = get_intent.getStringExtra("dayvalue");

        DataBaseHelper dataBaseHelper = new DataBaseHelper(ExerciseInstruction.this);
        exercise_bean = dataBaseHelper.getSingleExercise(dayvalue);
        exerciseid=Integer.parseInt(exercise_bean.get(0).getExercise_id());
        String image_icon = exercise_bean.get(0).getExercise_image_name();
        int id = ExerciseInstruction.this.getResources().getIdentifier(image_icon, "drawable", ExerciseInstruction.this.getPackageName());
        Drawable drawable = ExerciseInstruction.this.getResources().getDrawable(id);
         img_view.setImageDrawable(drawable);
        // img_view.setImageResource(R.drawable.b6);

        switch (exerciseid) {
            case 1:
                txt_view.setText(R.string.situp_instructions);
                break;
            case 2:
                txt_view.setText(R.string.crunch_instructions);
                break;
            case 3:
                txt_view.setText(R.string.legraise_instructions);
                break;
            case 4:
                txt_view.setText(R.string.plank_instructions);


        }
    }
}
