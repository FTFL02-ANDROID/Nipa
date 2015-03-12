package com.ftfl.ftflicare;

import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ftfl.ftflicare.database.VaccinationDataSource;
import com.ftfl.ftflicare.model.VaccineModel;
import com.ftfl.ftflicare.util.SetDateOnClickDialog;
import com.ftfl.ftflicare.util.SetTimeOnClickDialog;
import com.ftfl.ftflicare.util.Validation;

public class FTFLICareAddNewVaccineActivity extends Activity implements
		OnTimeSetListener {

	EditText mVaccineNameEditText;
	EditText mVaccineDateEditText;
	EditText mVaccineTimeEditText;
	CheckBox mSetAlarmCheckBox;

	String mVaccineName;
	String mVaccineDate;
	String mVaccineTime;
	String mDay;
	public int mAlarmCecked = 0;

	Integer mSetHour = 0;
	Integer mSetMinute = 0;
	int mHour = 0;
	int mMinute = 0;
	final Calendar mCalendar = Calendar.getInstance();

	VaccineModel mVaccineModel;
	VaccinationDataSource mVaccinationDataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_vaccine);

		initialize();

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayUseLogoEnabled(false);

		actionBar.setCustomView(R.layout.custom_action_bar4);

		TextView actionBarTextView = (TextView) actionBar.getCustomView()
				.findViewById(R.id.tvActionBar);
		Button saveButton = (Button) actionBar.getCustomView().findViewById(
				R.id.btnSave);

		actionBarTextView.setText("Add Vaccination");
		saveButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				saveVaccinationInfo();

			}
		});

		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
				| ActionBar.DISPLAY_SHOW_HOME);
	}

	public void saveVaccinationInfo() {

		if (checkValidation()) {
			// getting values from input fields.....
			mVaccineName = mVaccineNameEditText.getText().toString();
			mVaccineDate = mVaccineDateEditText.getText().toString();
			mVaccineTime = mVaccineTimeEditText.getText().toString();

			if (mSetAlarmCheckBox.isChecked()) {
				mAlarmCecked = 1;

				Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
				alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, mSetHour);
				alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, mSetMinute);
				alarmIntent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
				alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(alarmIntent);
			} else {
				mAlarmCecked = 0;
			}

			// ................................................

			mVaccineModel = new VaccineModel(mVaccineName, mVaccineDate,
					mVaccineTime, mAlarmCecked);

			mVaccinationDataSource = new VaccinationDataSource(this);

			if (mVaccinationDataSource.insertVaccinationInfo(mVaccineModel) == true) {
				Toast toast = Toast.makeText(this, "Data Saved.",
						Toast.LENGTH_LONG);
				toast.show();

				startActivity(new Intent(getApplicationContext(),
						FTFLICareVaccineListActivity.class));

			} else {
				Toast toast = Toast.makeText(this,
						"Unable to save, Please insert above information.",
						Toast.LENGTH_LONG);
				toast.show();

			}
		}

	}

	private boolean checkValidation() {
		boolean isValid = true;

		if (!Validation.hasText(mVaccineNameEditText))
			isValid = false;
		if (!Validation.hasText(mVaccineDateEditText))
			isValid = false;
		if (!Validation.hasText(mVaccineTimeEditText))
			isValid = false;

		return isValid;
	}

	public void initialize() {

		// Process to get Current Time
		mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
		mMinute = mCalendar.get(Calendar.MINUTE);

		/*
		 * // Launch Time Picker Dialog TimePickerDialog tpd = new
		 * TimePickerDialog(FTFLICareAddNewVaccineActivity.this, this,
		 * mHour,mMinute,false); tpd.show();
		 */

		mVaccineNameEditText = (EditText) findViewById(R.id.etVaccineName);
		mVaccineNameEditText.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {

				Validation.hasText(mVaccineNameEditText);
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

		mVaccineDateEditText = (EditText) findViewById(R.id.etVaccineDate);

		SetDateOnClickDialog datePickerObj = new SetDateOnClickDialog(
				mVaccineDateEditText, this);
		int dayOfWeek = datePickerObj.mDayOfWeek;
		mDay = datePickerObj.getDayOfMonth(dayOfWeek).toString();

		mVaccineDateEditText.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {

				Validation.hasText(mVaccineDateEditText);
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

		mVaccineTimeEditText = (EditText) findViewById(R.id.etVaccineTime);

		SetTimeOnClickDialog timePickerObj = new SetTimeOnClickDialog(
				mVaccineTimeEditText, this);

		mVaccineTimeEditText.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {

				Validation.hasText(mVaccineTimeEditText);
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

		mSetAlarmCheckBox = (CheckBox) findViewById(R.id.cbSetAlarm);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ftflicare_add_new_vaccine, menu);
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

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// TODO Auto-generated method stub

		mSetHour = hourOfDay;
		mSetMinute = minute;
		int hour = 0;
		String st = "";
		if (hourOfDay > 12) {
			hour = hourOfDay - 12;
			st = "PM";
		}

		else if (hourOfDay == 12) {
			hour = hourOfDay;
			st = "PM";
		}

		else if (hourOfDay == 0) {
			hour = hourOfDay + 12;
			st = "AM";
		} else {
			hour = hourOfDay;
			st = "AM";
		}
		mVaccineTimeEditText.setText(new StringBuilder().append(hour)
				.append(" : ").append(minute).append(" ").append(st));
	}
}
