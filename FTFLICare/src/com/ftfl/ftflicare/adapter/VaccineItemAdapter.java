package com.ftfl.ftflicare.adapter;

import java.util.List;

import com.ftfl.ftflicare.R;
import com.ftfl.ftflicare.model.VaccineModel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VaccineItemAdapter extends ArrayAdapter<VaccineModel> {

	private static LayoutInflater mInflater = null;
	List<VaccineModel> mVaccine;
	VaccineModel mVaccineModel;

	public VaccineItemAdapter(Activity context, List<VaccineModel> eVaccine) {
		super(context, R.layout.each_vaccine_item, eVaccine);
		this.mVaccine = eVaccine;

		/*********** Layout inflator to call external xml layout () ***********/
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/********* Create a holder Class to contain inflated xml file elements *********/
	public static class ViewHolder {

		public TextView vaccineName;
		public TextView vaccineDate;
		public TextView vaccineTime;
		public ImageView ivAlarmImage;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		ViewHolder holder;
		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			view = mInflater.inflate(R.layout.each_vaccine_item, null);

			
			
			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			holder.vaccineName = (TextView) view
					.findViewById(R.id.tvVaccinineName);
			holder.vaccineDate = (TextView) view
					.findViewById(R.id.tvVaccineDate);
			holder.vaccineTime = (TextView) view
					.findViewById(R.id.tvVaccineTime);
			holder.ivAlarmImage = (ImageView) view.findViewById(R.id.ivAlarm);

			/************ Set holder with LayoutInflater ************/
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		
		/*getting info from vaccine model to holder*/
		mVaccineModel = mVaccine.get(position);

		holder.vaccineName.setText(mVaccineModel.getVaccineName().toString());
		holder.vaccineDate.setText("Date: "
				+ mVaccineModel.getVaccineDate().toString());
		holder.vaccineTime.setText("Time: "
				+ mVaccineModel.getVaccineTime().toString());
		
		

		/*check alarm set or not*/
		if (mVaccineModel.getAlarmCecked() == 1) {

			holder.ivAlarmImage.setImageResource(R.drawable.alarm_on);
		} else {
			holder.ivAlarmImage.setImageResource(R.drawable.alarm_off);
		}

		return view;
	}

}
