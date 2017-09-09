package raisewave.a30daysabsworkout;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Exercise_CustomTotal extends BaseAdapter {


    Context my_Context;
    List<ExercisesBean> pbn_bean = new ArrayList<ExercisesBean>();

    public Exercise_CustomTotal(Context ct, List<ExercisesBean> pbs_list) {
        this.my_Context = ct;
        this.pbn_bean = pbs_list;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return pbn_bean.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        LayoutInflater lInflater = (LayoutInflater) my_Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = lInflater.inflate(R.layout.totalexercises_customlist, null);
            holder = new ViewHolder();

            holder.img_icon = (ImageView) convertView.findViewById(R.id.exercise_totalimage);
            holder.txt_likes = (TextView) convertView.findViewById(R.id.exercise_totaltext);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String image_icon = pbn_bean.get(position).getExercise_image_name();
        int id = my_Context.getResources().getIdentifier(image_icon, "drawable", my_Context.getPackageName());
        Drawable drawable = my_Context.getResources().getDrawable(id);
        holder.img_icon.setImageDrawable(drawable);
        holder.txt_likes.setText(pbn_bean.get(position).getExercise_name());
        return convertView;
    }

    static class ViewHolder {
        ImageView img_icon;
        TextView txt_likes;

    }
}