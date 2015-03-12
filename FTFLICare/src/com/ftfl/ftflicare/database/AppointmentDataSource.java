package com.ftfl.ftflicare.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ftfl.ftflicare.model.AppointmentModel;

public class AppointmentDataSource {

	
				// Database fields
				private SQLiteDatabase mDatabase = null;
				private DataBaseHelper mDbHelper = null;
				Cursor mCursor;

				AppointmentModel mAppointmentModel = null;
				List<AppointmentModel> mAppointment = new ArrayList<AppointmentModel>();

				public AppointmentDataSource(Context context) {
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

				public boolean insertAppointmentInfo(AppointmentModel eAppointment) {

					this.open();

					ContentValues cv = new ContentValues();

					cv.put(DataBaseHelper.COLUMN_PRESCRIPTION_PHOTO_PATH, eAppointment.getPhotoPath());
					cv.put(DataBaseHelper.COLUMN_DOCTOR_NAME, eAppointment.getDoctorName());
					cv.put(DataBaseHelper.COLUMN_APPOINTMENT_REASON, eAppointment.getAppointmentReason());
					cv.put(DataBaseHelper.COLUMN_APPOINTMENT_DATE, eAppointment.getAppointmentDate());
					cv.put(DataBaseHelper.COLUMN_CHEMBER_ADDRESS, eAppointment.getChemberAddress());

					Long check = mDatabase.insert(DataBaseHelper.APPOINTMENT_TABLE_NAME, null, cv);
					mDatabase.close();

					this.close();

					if (check < 0)
						return false;
					else
						return true;
				}
				
				
				public List<AppointmentModel> getAppointmentList() {
					this.open();

					Cursor mCursor = mDatabase.query(
							DataBaseHelper.APPOINTMENT_TABLE_NAME, new String[] {
									DataBaseHelper.COLUMN_APPOINTMENT_ID,
									DataBaseHelper.COLUMN_PRESCRIPTION_PHOTO_PATH,
									DataBaseHelper.COLUMN_DOCTOR_NAME,
									DataBaseHelper.COLUMN_APPOINTMENT_REASON,
									DataBaseHelper.COLUMN_APPOINTMENT_DATE,
									DataBaseHelper.COLUMN_CHEMBER_ADDRESS},
									null, null, null, null, null);

					if (mCursor != null) {
						if (mCursor.moveToFirst()) {

							do {

								String appointmentID = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.COLUMN_APPOINTMENT_ID));
								byte[] imagePath = mCursor.getBlob(mCursor.getColumnIndex(DataBaseHelper.COLUMN_PRESCRIPTION_PHOTO_PATH));
								
								String doctorName = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.COLUMN_DOCTOR_NAME));
								
								String appointmentReason = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.COLUMN_APPOINTMENT_REASON));
								
								String appointmentDate = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.COLUMN_APPOINTMENT_DATE));
								
								String chamberAddress = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.COLUMN_CHEMBER_ADDRESS));
								
								

								mAppointment.add(new AppointmentModel(appointmentID, imagePath, doctorName, appointmentReason, appointmentDate, chamberAddress));

							} while (mCursor.moveToNext());
						}
						mCursor.close();
					}
					this.close();
					return mAppointment;
				}
				
				public AppointmentModel getDetailAppointmentById(String eId){
					AppointmentModel appointmentModel = null;
					SQLiteDatabase db = mDbHelper. getReadableDatabase();
					String query = "SELECT * FROM "+DataBaseHelper.APPOINTMENT_TABLE_NAME+" WHERE "+DataBaseHelper.COLUMN_APPOINTMENT_ID+" ="+eId;
					Cursor cursor = db.rawQuery(query, null);
					
					if (cursor.moveToFirst()){
						
						do {

							String appointmentId = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_APPOINTMENT_ID));
							
							String doctorName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_DOCTOR_NAME));
							
							String appointmentReason = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_APPOINTMENT_REASON));
							
							String appointmentDate = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_APPOINTMENT_DATE));
							
							String chamberAddress = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_CHEMBER_ADDRESS));
							
							byte[] prescriptionImagePath = cursor.getBlob(cursor.getColumnIndex(DataBaseHelper.COLUMN_PRESCRIPTION_PHOTO_PATH));
							

							appointmentModel = new AppointmentModel(appointmentId, prescriptionImagePath,doctorName, appointmentReason, appointmentDate, chamberAddress);

						} while (cursor.moveToNext());
						
				}
					
				this.close();
				return appointmentModel;
			}
				
				// delete data form database.
				public boolean deleteData(int eId) {
					this.open();
					try {
						mDatabase.delete(
								DataBaseHelper.APPOINTMENT_TABLE_NAME,
								DataBaseHelper.COLUMN_APPOINTMENT_ID + "=" + eId, null);
					} catch (Exception ex) {
						Log.e("ERROR", "data deletion problem....");
						return false;
					}
					this.close();
					return true;
				}
}
