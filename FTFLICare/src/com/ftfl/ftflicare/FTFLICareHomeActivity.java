package com.ftfl.ftflicare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class FTFLICareHomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

	}
	
	public void goToProfile(View v) {
		
		Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_LONG).show();
		Intent i = new Intent(FTFLICareHomeActivity.this,
				FTFLICareProfileActivity.class);
		startActivity(i);

	}

	public void goToGallery(View v) {

		Toast.makeText(getApplicationContext(), "Gallery", Toast.LENGTH_LONG).show();
		Intent i = new Intent(FTFLICareHomeActivity.this,
				FTFLICareGalleryActivity.class);
		startActivity(i);

	}

	public void goToAppointment(View v) {

		Toast.makeText(getApplicationContext(), "Doctor's Appointment", Toast.LENGTH_LONG).show();
		Intent i = new Intent(FTFLICareHomeActivity.this,
				FTFLICareDoctorAppointmentActivity.class);
		startActivity(i);

	}

	public void goToVaccine(View v) {

		Toast.makeText(getApplicationContext(), "Vaccination", Toast.LENGTH_LONG).show();
		Intent i = new Intent(FTFLICareHomeActivity.this,
				FTFLICareVaccineListActivity.class);
		startActivity(i);

	}

	public void goToSettings(View v) {

		Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_LONG).show();
		Intent i = new Intent(FTFLICareHomeActivity.this,
				FTFLICareMedicalCenterActivity.class);
		startActivity(i);

	}

	public void goToDietChart(View v) {

		Toast.makeText(getApplicationContext(), "Diet Chart", Toast.LENGTH_LONG).show();
		Intent i = new Intent(FTFLICareHomeActivity.this,
				FTFLICareDietChartActivity.class);
		startActivity(i);

	}

	public void goToEmergencyContact(View v) {

		Toast.makeText(getApplicationContext(), "Help Line", Toast.LENGTH_LONG).show();
		Intent i = new Intent(FTFLICareHomeActivity.this,
				FTFLICareEmergencyContactActivity.class);
		startActivity(i);

	}

}
