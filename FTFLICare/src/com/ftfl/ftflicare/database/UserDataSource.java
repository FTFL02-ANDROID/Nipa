package com.ftfl.ftflicare.database;

import java.util.ArrayList;
import java.util.List;

import com.ftfl.ftflicare.model.UserProfileModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class UserDataSource {
	// Database fields
			private SQLiteDatabase mDatabase;
			private DataBaseHelper mDbHelper;
			Cursor mCursor;

			UserProfileModel mUserProfileModel;
			List<UserProfileModel> mUserProfile = new ArrayList<UserProfileModel>();

			public UserDataSource(Context context) {
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

			public boolean insertUserInfo(UserProfileModel eProfile) {

				this.open();

				ContentValues cv = new ContentValues();

				cv.put(DataBaseHelper.COLUMN_USER_NAME, eProfile.getName());
				cv.put(DataBaseHelper.COLUMN_GENDER, eProfile.getGender());
				cv.put(DataBaseHelper.COLUMN_HEIGHT, eProfile.getHeight());
				cv.put(DataBaseHelper.COLUMN_WEIGHT, eProfile.getWeight());
				cv.put(DataBaseHelper.COLUMN_DOB, eProfile.getDOB());

				Long check = mDatabase.insert(DataBaseHelper.USER_TABLE_NAME, null, cv);
				mDatabase.close();

				this.close();

				if (check < 0)
					return false;
				else
					return true;
			}
			
			// Updating database by id
			public boolean updateData(UserProfileModel eProfile) {
				this.open();
				ContentValues cvUpdate = new ContentValues();

				cvUpdate.put(DataBaseHelper.COLUMN_USER_NAME, eProfile.getName());
				cvUpdate.put(DataBaseHelper.COLUMN_GENDER, eProfile.getGender());
				cvUpdate.put(DataBaseHelper.COLUMN_HEIGHT, eProfile.getHeight());
				cvUpdate.put(DataBaseHelper.COLUMN_WEIGHT, eProfile.getWeight());
				cvUpdate.put(DataBaseHelper.COLUMN_DOB, eProfile.getDOB());

				int check = mDatabase.update(
						DataBaseHelper.USER_TABLE_NAME, cvUpdate,null,null);
				mDatabase.close();

				this.close();
				if (check == 0)
					return false;
				else
					return true;
			}

			
		public List<UserProfileModel> getPlaceList() {
			this.open();

			mCursor = mDatabase.query(
					DataBaseHelper.USER_TABLE_NAME, new String[] {
							DataBaseHelper.COLUMN_USER_ID,
							DataBaseHelper.COLUMN_USER_NAME,
							DataBaseHelper.COLUMN_GENDER,
							DataBaseHelper.COLUMN_HEIGHT,
							DataBaseHelper.COLUMN_WEIGHT,
							DataBaseHelper.COLUMN_DOB},
							null, null, null, null, null);

			if (mCursor != null) {
				if (mCursor.moveToFirst()) {

					do {

						int userId = mCursor.getInt(mCursor.getColumnIndex(DataBaseHelper.COLUMN_USER_ID));
						String userName = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.COLUMN_USER_NAME));
						
						String gender = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.COLUMN_GENDER));
						
						String height = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.COLUMN_HEIGHT));
						
						String weight = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.COLUMN_WEIGHT));
						
						String dateOfBirth = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.COLUMN_DOB));
						

						mUserProfile.add(new UserProfileModel(userId, userName,gender, height, weight, dateOfBirth));

					} while (mCursor.moveToNext());
				}
				mCursor.close();
			}
			this.close();
			return mUserProfile;
		}
		
		
		public UserProfileModel getProfile() {
			this.open();
			UserProfileModel tempProfile = null;
			mCursor = mDatabase.query(
					DataBaseHelper.USER_TABLE_NAME, new String[] {
							DataBaseHelper.COLUMN_USER_ID,
							DataBaseHelper.COLUMN_USER_NAME,
							DataBaseHelper.COLUMN_GENDER,
							DataBaseHelper.COLUMN_HEIGHT,
							DataBaseHelper.COLUMN_WEIGHT,
							DataBaseHelper.COLUMN_DOB},
							null, null, null, null, null);

			if (mCursor != null) {
				if (mCursor.moveToFirst()) {

					do {

						int userId = mCursor.getInt(mCursor.getColumnIndex(DataBaseHelper.COLUMN_USER_ID));
						String userName = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.COLUMN_USER_NAME));
						
						String gender = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.COLUMN_GENDER));
						
						String height = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.COLUMN_HEIGHT));
						
						String weight = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.COLUMN_WEIGHT));
						
						String dateOfBirth = mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.COLUMN_DOB));
						

						tempProfile = new UserProfileModel(userId, userName,gender, height, weight, dateOfBirth);

					} while (mCursor.moveToNext());
				}
				mCursor.close();
			}
			this.close();
			return tempProfile;
		}
		

		public boolean isEmpty() {
			
			this.open();

			mCursor = mDatabase.query(
					DataBaseHelper.USER_TABLE_NAME, new String[] {
							DataBaseHelper.COLUMN_USER_ID,
							DataBaseHelper.COLUMN_USER_NAME,
							DataBaseHelper.COLUMN_GENDER,
							DataBaseHelper.COLUMN_HEIGHT,
							DataBaseHelper.COLUMN_WEIGHT,
							DataBaseHelper.COLUMN_DOB},
							null, null, null, null, null);
			   int count = -1;
			   if (mCursor != null && mCursor.moveToFirst()) {
			      count = mCursor.getInt(0);
			      if (count > 0) {
			         return false;
			      }
			      else {
			         return true;
			      }
			   }
			   return true;
			}

}
