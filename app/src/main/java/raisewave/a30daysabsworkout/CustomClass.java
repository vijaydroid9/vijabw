package raisewave.a30daysabsworkout;



import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CustomClass extends BaseAdapter
{
	Context context;
	List<DaysBean> pbn_bean=new ArrayList<DaysBean>();
	
	public CustomClass(Context ct,List<DaysBean> pbs_list)
	{
		this.context=ct;
		this.pbn_bean=pbs_list;
		
	}

    public CustomClass(Context ct)
    {
        this.context=ct;
    }

	@Override
	public int getCount() 
	{
		// TODO Auto-generated method stub
		return pbn_bean.size();

	}

	@Override
	public boolean areAllItemsEnabled() {
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		if(pbn_bean.get(position).getDay_comment().equalsIgnoreCase("REST")) {
			return false;
		}
		return true;
	}
	@Override
	public Object getItem(int position) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		
		LayoutInflater linf=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView=linf.inflate(R.layout.custom_list, null);
		TextView tt_day=(TextView)convertView.findViewById(R.id.custom_day);
		TextView  tt_level=(TextView)convertView.findViewById(R.id.custom_level);
        CheckBox ch_box=(CheckBox)convertView.findViewById(R.id.checkBox);
		ch_box.setVisibility(View.GONE);
		tt_day.setText(pbn_bean.get(position).getDay_name());
		tt_level.setText(pbn_bean.get(position).getDay_comment());
		if(pbn_bean.get(position).getDay_comment().equalsIgnoreCase("REST")) {
			convertView.setBackgroundColor(Color.parseColor("#DADBE0"));
			tt_day.setVisibility(View.GONE);
			tt_level.setTextColor(Color.parseColor("#0b1633"));
			LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams)tt_level.getLayoutParams();
			ll.gravity = Gravity.CENTER;
			tt_level.setLayoutParams(ll);
           // ch_box.setChecked(true);

		}
		if(pbn_bean.get(position).getDay_progress().equalsIgnoreCase("1"))
        {
            ch_box.setChecked(true);
			ch_box.setVisibility(View.VISIBLE);
        }
		return convertView;
	}

}
