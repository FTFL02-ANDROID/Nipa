package com.ftfl.ftflhistoricalplaces;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ftfl.ftflhistoricalplaces.DataBase.DatabaseHelper;
import com.ftfl.ftflhistoricalplacesModel.PlaceModel;

public class FTFLHistoricalPlacesAddMoreActivity extends Activity {

	EditText mPlaceNameEditText;
	EditText mHistoricalDescriptionEditText;
	EditText mAddressEditText;
	EditText mDistrictEditText;
	EditText mWeeklyCloseDayEditText;
	EditText mDailyVisitTimeEditText;
	EditText mLatitudeEditText;
	EditText mLongitudeEditText;

	DatabaseHelper mDbHelper;
	PlaceModel mPlace;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_more);

		mPlaceNameEditText = (EditText) findViewById(R.id.placeNameEditText);
		mHistoricalDescriptionEditText = (EditText) findViewById(R.id.historicalDescriptionEditText);
		mAddressEditText = (EditText) findViewById(R.id.addressEditText);
		mDistrictEditText = (EditText) findViewById(R.id.districtEditText);
		mWeeklyCloseDayEditText = (EditText) findViewById(R.id.weeklyCloseDayEditText);
		mDailyVisitTimeEditText = (EditText) findViewById(R.id.dailyVisitTimeEditText);
		mLatitudeEditText = (EditText) findViewById(R.id.latitudeEditText);
		mLongitudeEditText = (EditText) findViewById(R.id.langitudeEditText);
		mDbHelper = new DatabaseHelper(this);

	}

	public void save(View v) {

		String mPlaceName = mPlaceNameEditText.getText().toString();
		String mHistoricalDescription = mHistoricalDescriptionEditText
				.getText().toString();
		String mAddress = mAddressEditText.getText().toString();
		String mDistrict = mDistrictEditText.getText().toString();
		String mWeeklyCloseDay = mWeeklyCloseDayEditText.getText().toString();
		String mDailyVisitTime = mDailyVisitTimeEditText.getText().toString();
		String mLatitude = mLatitudeEditText.getText().toString();
		String mLongitude = mLongitudeEditText.getText().toString();

		mPlace = new PlaceModel(mPlaceName, mHistoricalDescription, mAddress,
				mDistrict, mWeeklyCloseDay, mDailyVisitTime, mLatitude,
				mLongitude);

		long inserted = mDbHelper.insertPlace(mPlace);

		if (inserted >= 0) {

			Toast.makeText(this, "Data insertion successful", Toast.LENGTH_LONG)
					.show();
		} else {
			Toast.makeText(this, "Data insertion failed", Toast.LENGTH_LONG)
					.show();
		}

	}

}
