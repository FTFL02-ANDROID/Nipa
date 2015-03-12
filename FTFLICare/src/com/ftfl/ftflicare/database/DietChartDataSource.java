package com.ftfl.ftflicare.database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ftfl.ftflicare.model.DietChartModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DietChartDataSource {
	
	// Database fields
		private SQLiteDatabase iCareDacDatabase;
		private DataBaseHelper iCareDacDbHelper;
		List<DietChartModel> dailyDietChart = new ArrayList<DietChartModel>();
		List<DietChartModel> todayDietChart = new ArrayList<DietChartModel>();
		List<String> upcomingDates = new ArrayList<String>();
		List<String> allDates = new ArrayList<String>();
		public String mCurrentDate = "";

		public DietChartDataSource(Context context) {
			iCareDacDbHelper = new DataBaseHelper(context);
		}

		/*
		 * open a method for writable database
		 */
		public void open() throws SQLException {
			iCareDacDatabase = iCareDacDbHelper.getWritableDatabase();
		}

		/*
		 * close database connection
		 */
		public void close() {
			iCareDacDbHelper.close();
		}

		public void cDate() {
			DateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy");
			Date date = new Date();
			mCurrentDate = dateFormat.format(date);
		}

		/*
		 * insert data into the database.
		 */

		public boolean insert(DietChartModel eActivityChart) {

			this.open();

			ContentValues cv = new ContentValues();

			cv.put(DataBaseHelper.COL_ICARE_DIET_DATE,
					eActivityChart.getDate());
			cv.put(DataBaseHelper.COL_ICARE_DIET_TIME,
					eActivityChart.getTime());
			cv.put(DataBaseHelper.COL_ICARE_DIET_FOOD_MENU,
					eActivityChart.getFoodMenu());
			cv.put(DataBaseHelper.COL_ICARE_DIET_EVENT_NAME,
					eActivityChart.getEventName());
			cv.put(DataBaseHelper.COL_ICARE_DIET_ALARM,
					eActivityChart.getAlarm());

			
				Long check = iCareDacDatabase.insert(
						DataBaseHelper.ICARE_DIET_CHART, null, cv);
				iCareDacDatabase.close();
		
			this.close();
			
			if(check <0)
				return false;
			else
				return true;
		}

		// Updating database by id
		public boolean updateData(long eActivityId,
				DietChartModel eActivityChart) {
			this.open();
			ContentValues cvUpdate = new ContentValues();

			cvUpdate.put(DataBaseHelper.COL_ICARE_DIET_DATE,
					eActivityChart.getDate());
			cvUpdate.put(DataBaseHelper.COL_ICARE_DIET_TIME,
					eActivityChart.getTime());
			cvUpdate.put(DataBaseHelper.COL_ICARE_DIET_FOOD_MENU,
					eActivityChart.getFoodMenu());
			cvUpdate.put(DataBaseHelper.COL_ICARE_DIET_EVENT_NAME,
					eActivityChart.getEventName());
			cvUpdate.put(DataBaseHelper.COL_ICARE_DIET_ALARM,
					eActivityChart.getAlarm());
			
			int check = iCareDacDatabase.update(
					DataBaseHelper.ICARE_DIET_CHART, cvUpdate,
					DataBaseHelper.COL_ICARE_DIET_ID + "="
								+ eActivityId, null);
				iCareDacDatabase.close();
		
			this.close();
			if(check ==0)
				return false;
			else
				return true;
		}

		// delete data form database.
		public boolean deleteData(long eActivityId) {
			this.open();
			try {
				iCareDacDatabase.delete(
						DataBaseHelper.ICARE_DIET_CHART,
						DataBaseHelper.COL_ICARE_DIET_ID + "="
								+ eActivityId, null);
			} catch (Exception ex) {
				Log.e("ERROR", "data insertion problem");
				return false;
			}
			this.close();
			return true;
		}

		/*
		 * using cursor for display data from database.
		 */
		public List<DietChartModel> dailyDietChart(String eDate) {
			this.open();
			this.cDate();

			Cursor mCursor = iCareDacDatabase
					.query(DataBaseHelper.ICARE_DIET_CHART,
							new String[] {
							DataBaseHelper.COL_ICARE_DIET_ID,
							DataBaseHelper.COL_ICARE_DIET_DATE,
							DataBaseHelper.COL_ICARE_DIET_TIME,
							DataBaseHelper.COL_ICARE_DIET_FOOD_MENU,
							DataBaseHelper.COL_ICARE_DIET_EVENT_NAME,
							DataBaseHelper.COL_ICARE_DIET_ALARM,

							}, DataBaseHelper.COL_ICARE_DIET_DATE
									+ "= '" + eDate + "' ", null, null,
							null, null);

			if (mCursor != null) {
				if (mCursor.moveToFirst()) {

					do {

						String mActivityId = mCursor.getString(mCursor
								.getColumnIndex("diet_id"));
						String mActivityDate = mCursor.getString(mCursor
								.getColumnIndex("date"));
						String mActivityTime = mCursor.getString(mCursor
								.getColumnIndex("time"));
						String mDietFoodMenu = mCursor.getString(mCursor
								.getColumnIndex("food_menu"));
						String mDietEventName = mCursor.getString(mCursor
								.getColumnIndex("event_name"));
						String mSetAlarm = mCursor.getString(mCursor
								.getColumnIndex("set_alarm"));

						dailyDietChart.add(new DietChartModel(mActivityId,
								mActivityDate, mActivityTime, mDietFoodMenu,
								mDietEventName,  mSetAlarm));

					} while (mCursor.moveToNext());
				}
				mCursor.close();
			}
			this.close();
			return dailyDietChart;
		}
		
		
		

		/*
		 * using cursor for display data from database.
		 */
		public List<DietChartModel> todayDietChart( ) {
			this.open();
			this.cDate();

			Cursor mCursor = iCareDacDatabase
					.query(DataBaseHelper.ICARE_DIET_CHART,
							new String[] {
							DataBaseHelper.COL_ICARE_DIET_ID,
							DataBaseHelper.COL_ICARE_DIET_DATE,
							DataBaseHelper.COL_ICARE_DIET_TIME,
							DataBaseHelper.COL_ICARE_DIET_FOOD_MENU,
							DataBaseHelper.COL_ICARE_DIET_EVENT_NAME,
							DataBaseHelper.COL_ICARE_DIET_ALARM,

							}, DataBaseHelper.COL_ICARE_DIET_DATE
									+ "= '" + mCurrentDate + "' ", null, null,
							null, null);

			if (mCursor != null) {
				if (mCursor.moveToFirst()) {

					do {

						String mActivityId = mCursor.getString(mCursor
								.getColumnIndex("diet_id"));
						String mActivityDate = mCursor.getString(mCursor
								.getColumnIndex("date"));
						String mActivityTime = mCursor.getString(mCursor
								.getColumnIndex("time"));
						String mDietFoodMenu = mCursor.getString(mCursor
								.getColumnIndex("food_menu"));
						String mDietEventName = mCursor.getString(mCursor
								.getColumnIndex("event_name"));
						String mSetAlarm = mCursor.getString(mCursor
								.getColumnIndex("set_alarm"));

						todayDietChart.add(new DietChartModel(mActivityId,
								mActivityDate, mActivityTime, mDietFoodMenu,
								mDietEventName,  mSetAlarm));

					} while (mCursor.moveToNext());
				}
				mCursor.close();
			}
			this.close();
			return todayDietChart;
		}
		
		

		/*
		 * using cursor for display data from database.
		 */
		public List<String> upcomingDates( ) {
			this.open();
			this.cDate();

			Cursor mCursor = iCareDacDatabase.rawQuery("SELECT DISTINCT date FROM icaredietchart  WHERE date > '" +mCurrentDate+ "'", null);     

			if (mCursor != null) {
				if (mCursor.moveToFirst()) {

					do {
						String mActivityDate = mCursor.getString(mCursor
								.getColumnIndex("date"));
						upcomingDates.add(mActivityDate);


					} while (mCursor.moveToNext());
				}
				mCursor.close();
			}
			this.close();
			return upcomingDates;
		}
		
		
		/*
		 * using cursor for display data from database.
		 */
		public List<String> allDates( ) {
			this.open();
			this.cDate();

			Cursor mCursor = iCareDacDatabase.rawQuery("SELECT DISTINCT date FROM icaredietchart", null);     

			if (mCursor != null) {
				if (mCursor.moveToFirst()) {

					do {
						String mActivityDate = mCursor.getString(mCursor
								.getColumnIndex("date"));
						allDates.add(mActivityDate);


					} while (mCursor.moveToNext());
				}
				mCursor.close();
			}
			this.close();
			return allDates;
		}

		/*
		 * create a profile of ICareProfile. Here the data of the database according
		 * to the given id is set to the profile and return a profile.
		 */

		public DietChartModel updateActivityData(long eActivityId) {
			this.open();
			DietChartModel iCareUpdateActivity;

			Cursor mUpdateCursor = iCareDacDatabase
					.query(DataBaseHelper.ICARE_DIET_CHART,
							new String[] {
							DataBaseHelper.COL_ICARE_DIET_ID,
							DataBaseHelper.COL_ICARE_DIET_DATE,
							DataBaseHelper.COL_ICARE_DIET_TIME,
							DataBaseHelper.COL_ICARE_DIET_FOOD_MENU,
							DataBaseHelper.COL_ICARE_DIET_EVENT_NAME,
							DataBaseHelper.COL_ICARE_DIET_ALARM,

							}, DataBaseHelper.COL_ICARE_DIET_ID + "="
									+ eActivityId, null, null, null, null);

			mUpdateCursor.moveToFirst();

			String mActivityId = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex("diet_id"));
			String mActivityDate = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex("date"));
			String mActivityTime = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex("time"));
			String mDietFoodMenu = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex("food_menu"));
			String mDietEventName = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex("event_name"));
			String mSetAlarm = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex("set_alarm"));

			mUpdateCursor.close();
			iCareUpdateActivity = new DietChartModel(mActivityId,
					mActivityDate, mActivityTime, mDietFoodMenu,
					mDietEventName,  mSetAlarm);

			this.close();
			return iCareUpdateActivity;
		}
		public boolean isEmpty(){
			this.open();
			Cursor mCursor = iCareDacDatabase.query(DataBaseHelper.ICARE_DIET_CHART,
					new String[] { DataBaseHelper.COL_ICARE_DIET_ID,
					DataBaseHelper.COL_ICARE_DIET_DATE,
					DataBaseHelper.COL_ICARE_DIET_TIME,
					DataBaseHelper.COL_ICARE_DIET_FOOD_MENU,
					DataBaseHelper.COL_ICARE_DIET_EVENT_NAME,
					DataBaseHelper.COL_ICARE_DIET_ALARM}, null,
					null, null, null, null);
	        if(mCursor.getCount() == 0) {
	        	this.close();
	        	return true;
	        }
	        
	        else
	        {
	        	this.close();
	        	return false;
	        }
	    }
	}
