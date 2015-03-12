package com.ftfl.ftflicare.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ftfl.ftflicare.model.VaccineModel;

public class VaccinationDataSource {
	
	// Database fields
	private SQLiteDatabase mDatabase;
	private DataBaseHelper mDbHelper;
	Cursor mCursor;

	VaccineModel mVaccineModel;
	List<VaccineModel> mVaccination = new ArrayList<VaccineModel>();

	public VaccinationDataSource(Context context) {
		mDbHelper = new DataBaseHelper(context);
	}

	/*
	 * open a method for writable database
	 */
	public void open() throws SQLException {
		mDatabase = mDbHelper.getWritableDatabase();
	}

	/*
	 * close database connection
	 */
	public void close() {
		mDbHelper.close();
	}
	
	/*
	 * insert data into the database.
	 */

	public boolean insertVaccinationInfo(VaccineModel eVaccineModel) {

		this.open();

		ContentValues cv = new ContentValues();

		cv.put(DataBaseHelper.COLUMN_VACCINE_NAME, eVaccineModel.getVaccineName());
		cv.put(DataBaseHelper.COLUMN_VACCINE_DATE, eVaccineModel.getVaccineDate());
		cv.put(DataBaseHelper.COLUMN_VACCINE_TIME, eVaccineModel.getVaccineTime());
		cv.put(DataBaseHelper.COLUMN_ALARM_CHECKED, eVaccineModel.getAlarmCecked());

		Long check = mDatabase.insert(DataBaseHelper.VACCINE_TABLE_NAME, null, cv);
		mDatabase.close();

		this.close();

		if (check < 0)
			return false;
		else
			return true;
	}
	
	public List<VaccineModel> getVaccineList() {
		this.open();

		Cursor mCursor = mDatabase.query(
				DataBaseHelper.VACCINE_TABLE_NAME, new String[] {
						DataBaseHelper.COLUMN_VACCINE_ID,
						DataBaseHelper.COLUMN_VACCINE_NAME,
						DataBaseHelper.COLUMN_VACCINE_DATE,
						DataBaseHelper.COLUMN_VACCINE_TIME,
						DataBaseHelper.COLUMN_ALARM_CHECKED},
						null, null, null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {

					int vaccineID = mCursor.getInt(mCursor.getColumnIndex(DataBaseHelper.COLUMN_VACCINE_ID));
					String vaccineName = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.COLUMN_VACCINE_NAME));
					
					String vaccineDate = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.COLUMN_VACCINE_DATE));
					
					String vaccineTime = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.COLUMN_VACCINE_TIME));
					
					int alarmChecked = mCursor.getInt(mCursor.getColumnIndex(DataBaseHelper.COLUMN_ALARM_CHECKED));
					
					

					mVaccination.add(new VaccineModel(vaccineID, vaccineName, vaccineDate, vaccineTime, alarmChecked));

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return mVaccination;
	}
	
	
	// delete data form database.
		public boolean deleteData(int eId) {
			this.open();
			try {
				mDatabase.delete(
						DataBaseHelper.VACCINE_TABLE_NAME,
						DataBaseHelper.COLUMN_VACCINE_ID + "=" + eId, null);
			} catch (Exception ex) {
				Log.e("ERROR", "data deletion problem....");
				return false;
			}
			this.close();
			return true;
		}


}
