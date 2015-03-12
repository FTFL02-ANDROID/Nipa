package com.ftfl.ftflicare.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftfl.ftflicare.R;
import com.ftfl.ftflicare.model.AppointmentModel;

public class AppointmentItemAdapter extends ArrayAdapter<AppointmentModel>{
	
	private static LayoutInflater mInflater = null;
	List<AppointmentModel> mAppointment;
	AppointmentModel mAppointmentModel;
	
	public byte[] mPrescription;

	public AppointmentItemAdapter(Activity context, List<AppointmentModel> eAppointment) {
		super(context, R.layout.each_appointment_item, eAppointment);
		this.mAppointment = eAppointment;

		/*********** Layout inflator to call external xml layout () ***********/
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/********* Create a holder Class to contain inflated xml file elements *********/
	public static class ViewHolder {

		public TextView doctorNameTextView;
		public TextView appointmentDateTextView;
		public ImageView ivPrescription;

	}
	

	// convert from byte array to bitmap
	public static Bitmap getImage(byte[] image) {
		return BitmapFactory.decodeByteArray(image, 0, image.length);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		ViewHolder holder;
		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			view = mInflater.inflate(R.layout.each_appointment_item, null);

			
			
			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			holder.doctorNameTextView = (TextView) view
					.findViewById(R.id.tvDoctorName);
			holder.appointmentDateTextView = (TextView) view
					.findViewById(R.id.tvAppointmentDate);
			holder.ivPrescription = (ImageView) view.findViewById(R.id.ivPrescription);

			/************ Set holder with LayoutInflater ************/
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		
		/*getting info from vaccine model to holder*/
		mAppointmentModel = mAppointment.get(position);

		holder.doctorNameTextView.setText(mAppointmentModel.getDoctorName().toString());
		holder.appointmentDateTextView.setText("Date: "
				+ mAppointmentModel.getAppointmentDate().toString());
		
		mPrescription = mAppointmentModel.getPhotoPath();
		holder.ivPrescription.setImageBitmap(getImage(mPrescription));
		
		

		return view;
	}
}
