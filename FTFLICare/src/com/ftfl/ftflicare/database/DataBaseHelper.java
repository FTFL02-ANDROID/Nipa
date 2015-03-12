package com.ftfl.ftflicare.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{
	
	public static final String DATABASE_NAME = "ftflicare.db";
	private static final int DATABASE_VERSION = 2;
	
	//user profile table constant
	public static final String USER_TABLE_NAME = "userProfile";
	public static final String COLUMN_USER_ID = "userId";
	public static final String COLUMN_USER_NAME = "userName";
	public static final String COLUMN_GENDER = "gender";
	public static final String COLUMN_HEIGHT = "height";
	public static final String COLUMN_WEIGHT = "weight";
	public static final String COLUMN_DOB = "dateOfBirth";
	
	

	//diet chart table constant
	public static final String ICARE_DIET_CHART = "icaredietchart";
	public static final String COL_ICARE_DIET_ID = "diet_id";
	public static final String COL_ICARE_DIET_DATE = "date";
	public static final String COL_ICARE_DIET_TIME = "time";
	public static final String COL_ICARE_DIET_FOOD_MENU = "food_menu";
	public static final String COL_ICARE_DIET_EVENT_NAME = "event_name";
	public static final String COL_ICARE_DIET_ALARM = "set_alarm";
	
	//vaccine table constant
	public static final String VACCINE_TABLE_NAME = "vaccine";
	public static final String COLUMN_VACCINE_ID = "vaccineId";
	public static final String COLUMN_VACCINE_NAME = "vaccineName";
	public static final String COLUMN_VACCINE_DATE = "vaccineDate";
	public static final String COLUMN_VACCINE_TIME = "vaccineTime";
	public static final String COLUMN_ALARM_CHECKED = "alarmChecked";
	
	
	//doctor profile table constant
	public static final String DOCTOR_TABLE_NAME = "doctorProfile";
	public static final String COLUMN_DOCTOR_ID = "doctorId";
	public static final String COLUMN_DOCTOR_NAME = "doctorName";
	public static final String COLUMN_SPECIALIST_OF = "specialistOf";
	public static final String COLUMN_EMAIL = "doctorEmail";
	public static final String COLUMN_CONTACT_NO= "doctorContact";
	
	
	//appointment table constant
	public static final String APPOINTMENT_TABLE_NAME = "appointment";
	public static final String COLUMN_APPOINTMENT_ID = "appointmentId";
	public static final String COLUMN_APPOINTMENT_REASON = "appointmentReason";
	public static final String COLUMN_APPOINTMENT_DATE = "appointmentDate";
	public static final String COLUMN_CHEMBER_ADDRESS = "chemberAddress";
	public static final String COLUMN_PRESCRIPTION_PHOTO_PATH = "prescriptionPhotoPath";
	
	
	// create user profile table
	private static final String USER_CREATE_TABLE = "create table "
			+ USER_TABLE_NAME + "(" 
			+ COLUMN_USER_ID + " integer primary key, "
			+ COLUMN_USER_NAME + " text not null," 
			+ COLUMN_GENDER + " text not null," 
			+ COLUMN_HEIGHT + " text not null,"
			+ COLUMN_WEIGHT + " text not null," 
			+ COLUMN_DOB + " text not null);";

	
	// create vaccine table
	private static final String VACCINE_CREATE_TABLE = "create table "
			+ VACCINE_TABLE_NAME + "(" 
			+ COLUMN_VACCINE_ID + " integer primary key, " 
			+ COLUMN_USER_ID + " integer, "
			+ COLUMN_VACCINE_NAME + " text not null, " 
			+ COLUMN_VACCINE_DATE + " text not null, " 
			+ COLUMN_VACCINE_TIME + " text not null, "
			+ COLUMN_ALARM_CHECKED + " integer);";
	
	// create diet chart table
	private	static final String DIET_CREATE_TABLE =  "create table "
			+ ICARE_DIET_CHART + "(" + COL_ICARE_DIET_ID
			+ " integer primary key autoincrement, "
			+ COL_ICARE_DIET_DATE + " text not null,"
			+ COL_ICARE_DIET_TIME + " text not null,"
			+ COL_ICARE_DIET_FOOD_MENU + " text not null,"
			+ COL_ICARE_DIET_EVENT_NAME + " text not null,"
			+ COL_ICARE_DIET_ALARM + " text not null);";

	
	// create doctor profile table
	private static final String DOCTOR_CREATE_TABLE = "create table "
			+ DOCTOR_TABLE_NAME + "(" 
			+ COLUMN_DOCTOR_ID + " integer primary key, " 
			+ COLUMN_USER_ID + " integer,"
			+ COLUMN_DOCTOR_NAME + " text not null," 
			+ COLUMN_SPECIALIST_OF + " text not null," 
			+ COLUMN_EMAIL + " text not null,"
			+ COLUMN_CONTACT_NO + " text not null);";

	
	// create appointment table
	private static final String APPOINTMENT_CREATE_TABLE = "create table "
			+ APPOINTMENT_TABLE_NAME + "(" 
			+ COLUMN_APPOINTMENT_ID + " integer primary key, " 
			+ COLUMN_USER_ID + " integer,"
			+ COLUMN_DOCTOR_NAME + " text not null," 
			+ COLUMN_APPOINTMENT_REASON + " text not null," 
			+ COLUMN_APPOINTMENT_DATE + " text not null,"
			+ COLUMN_CHEMBER_ADDRESS + " text not null,"
			+ COLUMN_PRESCRIPTION_PHOTO_PATH + " text not null);";

	
	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(USER_CREATE_TABLE);
		db.execSQL(VACCINE_CREATE_TABLE);
		db.execSQL(DOCTOR_CREATE_TABLE);
		db.execSQL(APPOINTMENT_CREATE_TABLE);
		db.execSQL(DIET_CREATE_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


		db.execSQL("DROP TABLE IF EXISTS userProfile");
		db.execSQL("DROP TABLE IF EXISTS vaccine");
		db.execSQL("DROP TABLE IF EXISTS doctorProfile");
		db.execSQL("DROP TABLE IF EXISTS appointment");
		db.execSQL("DROP TABLE IF EXISTS icaredietchart");
		onCreate(db);
		
	}

}
