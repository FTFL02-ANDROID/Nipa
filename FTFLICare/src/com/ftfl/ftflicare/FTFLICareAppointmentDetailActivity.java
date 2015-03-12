package com.ftfl.ftflicare;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ftfl.ftflicare.database.AppointmentDataSource;
import com.ftfl.ftflicare.model.AppointmentModel;
import com.ftfl.ftflicare.util.Constants;

public class FTFLICareAppointmentDetailActivity extends Activity {
	
	TextView mDoctorNameTextView;
	TextView mAppointmentReasonTextView;
	TextView mAppointmentDateTextView;
	TextView mChamberAddressTextView;
	
	ImageView mPrescriptionImageView;
	
	AppointmentDataSource mAppointmentDataSource;
	AppointmentModel mAppointmentModel;
	
	String mSelectedId;
	public byte[] mPrescriptionImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appointment_detail);
		
		initialize();
		/* get Id from Intent */
		mSelectedId = getIntent().getExtras().getString(Constants.ID);
		
		/* create instance of Data Source class */
		mAppointmentDataSource = new AppointmentDataSource(this);
		
		/*
		 * receive appointment information object by calling a method from Data
		 * Source class and sent selected id as parameter
		 */
		mAppointmentModel = mAppointmentDataSource.getDetailAppointmentById(mSelectedId);
		
		/* SHow existing data */
		setAppointmentInfo();

		
	}
	

	// convert from byte array to bitmap
	public static Bitmap getImage(byte[] image) {
		return BitmapFactory.decodeByteArray(image, 0, image.length);
	}
	
	public void initialize() {
		
		mDoctorNameTextView = (TextView) findViewById(R.id.viewDoctorName);
		
		mAppointmentReasonTextView = (TextView) findViewById(R.id.viewAppointmentReason);
		
		mAppointmentDateTextView = (TextView) findViewById(R.id.viewAppointmentDate);
		
		mChamberAddressTextView = (TextView) findViewById(R.id.viewChamberAddress);
		 
		mPrescriptionImageView = (ImageView)findViewById(R.id.ivPrescription);
	}

	public void setAppointmentInfo() {
		
		mDoctorNameTextView.setText(mAppointmentModel.getDoctorName());
		
		mAppointmentReasonTextView.setText(mAppointmentModel.getAppointmentReason());
		
		mAppointmentDateTextView.setText(mAppointmentModel.getAppointmentDate());
		
		mChamberAddressTextView.setText(mAppointmentModel.getChemberAddress());
		
		mPrescriptionImage = mAppointmentModel.getPhotoPath();
		
		mPrescriptionImageView.setImageBitmap(getImage(mPrescriptionImage));
		
		
		
		mDoctorNameTextView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent (FTFLICareAppointmentDetailActivity.this, FTFLICareDoctorProfileActivity.class);
				startActivity(i);
				
			}
		});
		
	}
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ftflicare_appointment_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
