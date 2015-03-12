package com.ftfl.ftflicare.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ftfl.ftflicare.R;

public class MedicalCenterItemAdapter extends ArrayAdapter<String> {

	private final Activity context;
	private final String[] mCareName;

	private final double[] mLatitude;
	private final double[] mLongitude;

	public MedicalCenterItemAdapter(Activity context, String[] mCareName,
			double[] mLatitude, double[] mLongitude) {
		super(context, R.layout.each_medical_center_item, mCareName);
		this.context = context;
		this.mCareName = mCareName;
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;

	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.each_medical_center_item, null, true);

		TextView txtNum = (TextView) rowView
				.findViewById(R.id.tvCareCenterName);
		TextView tvLatitude = (TextView) rowView.findViewById(R.id.tvLat);
		TextView tvLongitude = (TextView) rowView.findViewById(R.id.tvLong);

		txtNum.setText(mCareName[position]);
		tvLatitude.setText("Latitude :"+mLatitude[position]);
		tvLongitude.setText("Longitude :"+mLongitude[position]);
		return rowView;
	}

}

