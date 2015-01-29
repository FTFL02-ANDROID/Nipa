package com.ftfl.ftflhistoricalplaces.DataBase;

import java.util.ArrayList;

import com.ftfl.ftflhistoricalplacesModel.PlaceModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	   

	   public static final String DATABASE_NAME = "FTFLHistoricalPlaces";
	   public static final int DATABASE_VERSION = 1;
	   public static final String PLACE_TABLE_NAME = "historicalPlace";
	   public static final String PLACE_COLUMN_ID = "_id";
	   public static final String PLACE_COLUMN_NAME = "placeName";
	   public static final String PLACE_COLUMN_HISTORICAL_DESCRIPTION = "historicalDescription";
	   public static final String PLACE_COLUMN_ADDRESS = "address";
	   public static final String PLACE_COLUMN_DISTRICT = "district";
	   public static final String PLACE_COLUMN_WEEKLY_CLOSE_DAY = "weeklyCloseDay";
	   public static final String PLACE_COLUMN_DAILY_VISIT_TIME = "dailyVisitTime";
	   public static final String PLACE_COLUMN_LATITUDE = "latitude";
	   public static final String PLACE_COLUMN_LONGITUDE = "longitude";
	  
	   
	   private static final String CREATE_PLACE_TABLE_SQL = "CREATE TABLE "
				+ PLACE_TABLE_NAME + "(" + PLACE_COLUMN_ID
				+ " integer primary key, "
				+ PLACE_COLUMN_NAME + " text not null, "
				+ PLACE_COLUMN_HISTORICAL_DESCRIPTION + " text not null, "
				+ PLACE_COLUMN_ADDRESS + " text not null, "
				+ PLACE_COLUMN_DISTRICT + " text not null, "
				+ PLACE_COLUMN_WEEKLY_CLOSE_DAY + " text not null, "
				+ PLACE_COLUMN_DAILY_VISIT_TIME + " text not null, "
			    + PLACE_COLUMN_LATITUDE  + " integer, "
			    + PLACE_COLUMN_LONGITUDE  + " integer)";
	   
	   public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, 1);
		}
	 
	@Override
	public void onCreate(SQLiteDatabase sqlDbObj) {
		// TODO table create
		sqlDbObj.execSQL(CREATE_PLACE_TABLE_SQL);
		Log.e("TABLE CREATE", CREATE_PLACE_TABLE_SQL);
		 
		 
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqlDbObj, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		sqlDbObj.execSQL("DROP TABLE IF EXISTS PLACE_TABLE_NAME");
	      onCreate(sqlDbObj);
	}
	
	//insert data
	public long insertPlace(PlaceModel place){
		SQLiteDatabase sqlDbObj=this.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put(PLACE_COLUMN_NAME, place.getPlaceName());
		values.put(PLACE_COLUMN_HISTORICAL_DESCRIPTION, place.getHistoricalDescription());
		values.put(PLACE_COLUMN_ADDRESS, place.getAddress());
		values.put(PLACE_COLUMN_DISTRICT, place.getDistrict());
		values.put(PLACE_COLUMN_WEEKLY_CLOSE_DAY, place.getWeeklyCloseDay());
		values.put(PLACE_COLUMN_DAILY_VISIT_TIME, place.getDailyVisitTime());
		values.put(PLACE_COLUMN_LATITUDE, place.getLatitude());
		values.put(PLACE_COLUMN_LONGITUDE, place.getLangitude());
		
		long inserted=sqlDbObj.insert(PLACE_TABLE_NAME, null, values);
		
		sqlDbObj.close();
		return inserted;
	}
	
	//query
	public ArrayList<PlaceModel>getAllPlaces(){
		ArrayList<PlaceModel> placeArrayList=new ArrayList<PlaceModel>();
		SQLiteDatabase sqlDbObj=this.getReadableDatabase();
		//Select*from PLACE_TABLE_NAME
		Cursor cursor=sqlDbObj.query(PLACE_TABLE_NAME, null, null, null, null, null, null);
		
		if(cursor!=null && cursor.getCount()>0){
			cursor.moveToFirst();
			for(int i=0;i<cursor.getCount();i++){
				int placeId=cursor.getInt(cursor.getColumnIndex(PLACE_COLUMN_ID));
				String placeName=cursor.getString(cursor.getColumnIndex(PLACE_COLUMN_NAME));
				String historicalDescription=cursor.getString(cursor.getColumnIndex(PLACE_COLUMN_HISTORICAL_DESCRIPTION));
				String address=cursor.getString(cursor.getColumnIndex(PLACE_COLUMN_ADDRESS));
				String district=cursor.getString(cursor.getColumnIndex(PLACE_COLUMN_DISTRICT));
				String weeklyCloseDay=cursor.getString(cursor.getColumnIndex(PLACE_COLUMN_WEEKLY_CLOSE_DAY));
				String dailyVisitTime=cursor.getString(cursor.getColumnIndex(PLACE_COLUMN_DAILY_VISIT_TIME));
				String latitude=cursor.getString(cursor.getColumnIndex(PLACE_COLUMN_LATITUDE));
				String longitude=cursor.getString(cursor.getColumnIndex(PLACE_COLUMN_LONGITUDE));
				
				PlaceModel placeModel=new PlaceModel(placeId, placeName, historicalDescription, address, district, weeklyCloseDay, dailyVisitTime, latitude, longitude);
				placeArrayList.add(placeModel);
				
				cursor.moveToNext();	
			}
		}
		cursor.close();
		sqlDbObj.close();
		return placeArrayList;
		
	}

}

