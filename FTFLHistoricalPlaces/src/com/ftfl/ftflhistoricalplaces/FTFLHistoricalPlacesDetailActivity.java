package com.ftfl.ftflhistoricalplaces;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ftfl.ftflhistoricalplaces.DataBase.DatabaseHelper;
import com.ftfl.ftflhistoricalplaces.constant.Constant;
import com.ftfl.ftflhistoricalplacesImageAdapter.ImageAdapter;
import com.ftfl.ftflhistoricalplacesModel.PlaceModel;

public class FTFLHistoricalPlacesDetailActivity extends Activity {

	private int mPosition = 0;
	ImageView mPlaceImageView;
	TextView mPlaceNameTextView;
	TextView mHistoricalDescriptionTextView;
	TextView mAddressTextView;
	TextView mDistrictTextView;
	TextView mWeeklyCloseDayTextView;
	TextView mDailyVisitTimeTextView;
	TextView mLatitudeTextView;
	TextView mLongitudeTextView;
	int mPlaceID;
	String mPlaceName;
	String mHistoricalDescription;
	String mAddress;
	String mDistrict;
	String mWeeklyCloseDay;
	String mDailyVisitTime;
	String mLatitude;
	String mLongitude;
	ImageAdapter mImageAdapter;
	DatabaseHelper mDbHelper;
	PlaceModel mPlace;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place_detail);

		Intent i = getIntent();
		mPosition = i.getExtras().getInt("id");
		mImageAdapter = new ImageAdapter(this);

		mPlaceImageView = (ImageView) findViewById(R.id.place_image_view);
		mPlaceImageView.setImageResource(mImageAdapter.mImageArray[mPosition]);

		mPlaceNameTextView = (TextView) findViewById(R.id.place_name_text_view);
		mPlaceNameTextView.setText(mImageAdapter.mName[mPosition]);

		mHistoricalDescriptionTextView = (TextView) findViewById(R.id.historicalDescriptionView);
		mAddressTextView = (TextView) findViewById(R.id.addressView);
		mDistrictTextView = (TextView) findViewById(R.id.districtView);
		mWeeklyCloseDayTextView = (TextView) findViewById(R.id.weeklyCloseDayView);
		mDailyVisitTimeTextView = (TextView) findViewById(R.id.dailyVisitTimeView);
		mLatitudeTextView = (TextView) findViewById(R.id.latitudeView);
		mLongitudeTextView = (TextView) findViewById(R.id.langitudeView);

		mDbHelper = new DatabaseHelper(this);
		ArrayList<PlaceModel> places = mDbHelper.getAllPlaces();
		
		//get data
		if (places != null && places.size() > 0) {
			for(PlaceModel mPlace : places){
				mPlaceID=mPlace.getPlacId();
				mPlaceName = mPlace.getPlaceName();
				mHistoricalDescription = mPlace.getHistoricalDescription();
				mAddress = mPlace.getAddress();
				mDistrict = mPlace.getDistrict();
				mWeeklyCloseDay = mPlace.getWeeklyCloseDay();
				mDailyVisitTime = mPlace.getDailyVisitTime();
				mLatitude = mPlace.getLatitude();
				mLongitude = mPlace.getLangitude();
				
				for(mPlaceID=0;mPlaceID<=places.size();mPlaceID++){
				
				//set data
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

	public void viewMap(View view) {
		Intent intent = new Intent(FTFLHistoricalPlacesDetailActivity.this,
				FTFLHistoricalPlacesShowMapActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString(Constant.LATITUDE, mPlace.getLatitude());
		bundle.putString(Constant.LONGITUDE, mPlace.getLangitude());
		bundle.putString(Constant.PLACENAME, mPlace.getPlaceName());
		intent.putExtras(bundle);
		startActivity(intent);
		
	}

}
