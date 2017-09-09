package raisewave.a30daysabsworkout;

import java.util.ArrayList;
import java.util.List;


import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteAssetHelper {

	public static final String DAY_ID = "day_id";
	public static final String DAY_FLAG = "day_flag";
	public static final String DAY_NAME = "day_name";
	public static final String DAY_STATUS_FLAG = "day_status_flag";
	public static final String DAY_COMMENT = "day_comment";
    public static final String DAY_PROG="day_prog";


	public static final String ROW_ID = "rowid";
	public static final String EXERICSE_DAY_ID = "exercise_day_id";
	private static final String EXERCISE_ID = "exercise_id";
	private static final String EXERCISE_COUNT = "exercise_count";
	private static final String EXERCISE_TIME = "exercise_time";
	private static final String EXERCISE_NAME = "exercise_name";
	private static final String EXERCISE_TYPEFLAG = "exercise_typeflag";
    private static final String EXERCISE_IMAGE_NAME = "exercise_image_name";


	private static final int DATABASE_VERSION = 1;

	public static final String DATABASE_NAME = "abs_db.db";


	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}
	
	/*public void insertData(com.axis.model.ApplicationBean appbean)
	{
		
			SQLiteDatabase db=this.getWritableDatabase();
			
		ContentValues cv = new ContentValues();
		cv.put(BANK_NAME, appbean.getBankname());
		cv.put(BANK_BALENQ, appbean.getBalanceenquiry());
		cv.put(BANK_CONTACT, appbean.getCustomercare());
		cv.put(BANK_IMGCODE, appbean.getImgtag());
		cv.put(BANK_MINISTATEMENT, appbean.getMinistatement());
	    db.insert(TABLE_NAME, null, cv);
		db.close();
	}*/


	public List<DaysBean> getDaysData() {

		List<DaysBean> pblist = new ArrayList<DaysBean>();
		SQLiteDatabase db = this.getWritableDatabase();

		String rawquery = "select day_id,day_flag,day_name,day_status_flag,day_comment,dayprog from days_table";
		Cursor cb = db.rawQuery(rawquery, null);


		if (cb.moveToNext()) {
			do {
				DaysBean pb = new DaysBean();
				pb.setDay_id(cb.getString(0));
				pb.setDay_flag(cb.getString(1));
				pb.setDay_name(cb.getString(2));
				pb.setDay_status_flag(cb.getString(3));
				pb.setDay_comment(cb.getString(4));
				pb.setDay_progress(cb.getString(5));
				pblist.add(pb);

			} while (cb.moveToNext());
		}

		db.close();
		return pblist;

	}

	public List<ExercisesDetailBean> getExercisesDetail() {

		List<ExercisesDetailBean> pblist = new ArrayList<ExercisesDetailBean>();
		SQLiteDatabase db = this.getWritableDatabase();

		String rawquery = "select rowid,exercise_day_id,exercise_id,exercise_count,exercise_time,exercise_name,exercise_typeflag from exercise_detailtable";

		Cursor cb = db.rawQuery(rawquery, null);

		if (cb.moveToNext()) {
			do {
				ExercisesDetailBean pb = new ExercisesDetailBean();
				pb.setRowwid((cb.getString(0)));
				pb.setExercise_day_id(cb.getString(1));
				pb.setExercise_id(cb.getString(2));
				pb.setExercise_count(cb.getString(3));
				pb.setExercise_time(cb.getString(4));
				pb.setExercise_name(cb.getString(5));
				pb.setExercise_typeflag(cb.getString(6));

				pblist.add(pb);

			} while (cb.moveToNext());
		}

		db.close();
		return pblist;

	}
	public List<ExercisesDetailBean> getDayExercises(String day_exercise) {

		List<ExercisesDetailBean> pblist = new ArrayList<ExercisesDetailBean>();
		SQLiteDatabase db = this.getWritableDatabase();

		String rawquery = "select exercise_day_id,exercise_id,exercise_count,exercise_time,exercise_name,exercise_typeflag from exercisedetail_table where exercise_day_id ='"+day_exercise+"'";

		Cursor cb = db.rawQuery(rawquery, null);

		if (cb.moveToNext()) {
			do {
				ExercisesDetailBean pb = new ExercisesDetailBean();
				//pb.setRowwid((cb.getString(0)));
				pb.setExercise_day_id(cb.getString(0));
				pb.setExercise_id(cb.getString(1));
				pb.setExercise_count(cb.getString(2));
				pb.setExercise_time(cb.getString(3));
				pb.setExercise_name(cb.getString(4));
				pb.setExercise_typeflag(cb.getString(5));

				pblist.add(pb);

			} while (cb.moveToNext());
		}

		db.close();
		return pblist;

	}

	public List<ExercisesBean> getExercises() {

		List<ExercisesBean> pblist = new ArrayList<ExercisesBean>();
		SQLiteDatabase db = this.getWritableDatabase();

		String rawquery = "select exercise_id,exercise_name,exercise_image_name from exercises_table";

		Cursor cb = db.rawQuery(rawquery, null);

		if (cb.moveToNext()) {
			do {
				ExercisesBean pb = new ExercisesBean();
				pb.setExercise_id((cb.getString(0)));
				pb.setExercise_name(cb.getString(1));
				pb.setExercise_image_name(cb.getString(2));
				pblist.add(pb);

			} while (cb.moveToNext());
		}

		db.close();
		return pblist;

	}

	public List<ExercisesBean> getSingleExercise(String day_exercise) {

		List<ExercisesBean> pblist = new ArrayList<ExercisesBean>();
		SQLiteDatabase db = this.getWritableDatabase();

		String rawquery = "select exercise_id,exercise_name,exercise_image_name from exercises_table where exercise_id ='"+day_exercise+"'";

		Cursor cb = db.rawQuery(rawquery, null);

		if (cb.moveToNext()) {
			do {
				ExercisesBean pb = new ExercisesBean();
				pb.setExercise_id((cb.getString(0)));
				pb.setExercise_name(cb.getString(1));
				pb.setExercise_image_name(cb.getString(2));
				pblist.add(pb);

			} while (cb.moveToNext());
		}

		db.close();
		return pblist;

	}
	public int getProgress() {


		SQLiteDatabase db = this.getWritableDatabase();

		//String rawquery = "select exercise_id,exercise_name,exercise_image_name from exercises_table where exercise_id ='"+day_exercise+"'";
        String rawquery = "select dayprog from days_table where dayprog ='"+1+"'";
		Cursor cb = db.rawQuery(rawquery, null);
        cb.moveToFirst();
        int total = cb.getCount();
        cb.close();
        db.close();
        return total;

	}

	public void SetProg(int dayid) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues cv = new ContentValues();
			cv.put("dayprog", "1");

			db.update("days_table", cv, "day_id=" + dayid, null);
			db.close();

		} catch (Exception e) {
			// TODO: handle exception
		}


	}

	public void ResetProg() {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues cv = new ContentValues();
			cv.put("dayprog", "0");

			db.update("days_table", cv, "dayprog =1", null);
			db.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

/*
public void UpdateValues(ApplicationBean appbean,String bank_imgtag)
{

	try
	{
		SQLiteDatabase db=this.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put(BANK_BALENQ, appbean.getBalanceenquiry());
		cv.put(BANK_CONTACT, appbean.getCustomercare());
		cv.put(BANK_MINISTATEMENT, appbean.getMinistatement());
		db.update("banktable", cv, "imgtag="+bank_imgtag, null);

		// db.insertWithOnConflict("banktable", null, cv, SQLiteDatabase.CONFLICT_REPLACE);

         db.close();

	} catch (Exception e)
	{
		// TODO: handle exception
	}
    
	
}
public void rmFav(int bankid)
{
	try
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("favorite","false");
		db.update("banktable", cv, "_id="+bankid, null);
		db.close();
		
	} catch (Exception e) 
	{
		// TODO: handle exception
	}
    
}
*/


/*
public boolean CheckBanksData(String bank_imgtag)
{
	boolean flag =false;
	List<ApplicationBean> pblist=new ArrayList<ApplicationBean>();
	SQLiteDatabase db=this.getWritableDatabase();
	
	String rawquery="select _id,bankname,balanceenquiry,customercare,ministatement from banktable where imgtag ='"+bank_imgtag+"'";
	
	
	
    Cursor cb=db.rawQuery(rawquery, null);
	if(cb.moveToFirst())
	{
		flag=true;
	}
	
	db.close();
	return flag;
	
}

}
*/

