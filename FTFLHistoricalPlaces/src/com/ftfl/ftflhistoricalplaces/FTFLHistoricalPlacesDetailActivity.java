package com.ftfl.ftflhistoricalplaces;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ftfl.ftflhistoricalplaces.DataBase.DatabaseHelper;
import com.ftfl.ftflhistoricalplacesImageAdapter.ImageAdapter;
import com.ftfl.ftflhistoricalplacesModel.PlaceModel;

public class FTFLHistoricalPlacesDetailActivity extends Activity {

	private int position = 0;
	ImageView mPlaceImageView;
	TextView mPlaceNameTextView, mHistoricalDescriptionTextView,
			mAddressTextView, mDistrictTextView, mWeeklyCloseDayTextView,
			mDailyVisitTimeTextView, mLatitudeTextView, mLongitudeTextView;
	String mPlaceName, mHistoricalDescription, mAddress, mDistrict,
			mWeeklyCloseDay, mDailyVisitTime, mLatitude, mLongitude;
	ImageAdapter mImageAdapter;
	DatabaseHelper mDbHelper;
	PlaceModel place;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place_detail);

		Intent i = getIntent();
		position = i.getExtras().getInt("id");
		mImageAdapter = new ImageAdapter(this);

		mPlaceImageView = (ImageView) findViewById(R.id.place_image_view);
		mPlaceImageView.setImageResource(mImageAdapter.mImageArray[position]);

		mPlaceNameTextView = (TextView) findViewById(R.id.place_name_text_view);
		mPlaceNameTextView.setText(mImageAdapter.mName[position]);

		mHistoricalDescriptionTextView = (TextView) findViewById(R.id.historicalDescriptionView);
		mAddressTextView = (TextView) findViewById(R.id.addressView);
		mDistrictTextView = (TextView) findViewById(R.id.districtView);
		mWeeklyCloseDayTextView = (TextView) findViewById(R.id.weeklyCloseDayView);
		mDailyVisitTimeTextView = (TextView) findViewById(R.id.dailyVisitTimeView);
		mLatitudeTextView = (TextView) findViewById(R.id.latitudeView);
		mLongitudeTextView = (TextView) findViewById(R.id.langitudeView);

		mDbHelper = new DatabaseHelper(this);
		ArrayList<PlaceModel> places = mDbHelper.getAllPlaces();

		if (places != null && places.size() > 0) {
			for (PlaceModel place : places) {
				mPlaceName = place.getPlaceName();
				mHistoricalDescription = place.getHistoricalDescription();
				mAddress = place.getAddress();
				mDistrict = place.getDistrict();
				mWeeklyCloseDay = place.getWeeklyCloseDay();
				mDailyVisitTime = place.getDailyVisitTime();
				mLatitude = place.getLatitude();
				mLongitude = place.getLangitude();

				if (mPlaceName == mPlaceNameTextView.getText()) {
					mHistoricalDescriptionTextView
							.setText(mHistoricalDescription);
					mAddressTextView.setText(mAddress);
					mDistrictTextView.setText(mDistrict);
					mWeeklyCloseDayTextView.setText(mWeeklyCloseDay);
					mDailyVisitTimeTextView.setText(mDailyVisitTime);
					mLatitudeTextView.setText(mLatitude);
					mLongitudeTextView.setText(mLongitude);
				} else {
					mPlaceNameTextView.setText(mPlaceName);
					mHistoricalDescriptionTextView
							.setText(mHistoricalDescription);
					mAddressTextView.setText(mAddress);
					mDistrictTextView.setText(mDistrict);
					mWeeklyCloseDayTextView.setText(mWeeklyCloseDay);
					mDailyVisitTimeTextView.setText(mDailyVisitTime);
					mLatitudeTextView.setText(mLatitude);
					mLongitudeTextView.setText(mLongitude);
				}

			}
		} else {
			Toast.makeText(this, "No data found", Toast.LENGTH_LONG).show();
		}

	}

}
