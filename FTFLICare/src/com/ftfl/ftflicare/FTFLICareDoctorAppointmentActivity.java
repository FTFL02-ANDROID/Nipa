package com.ftfl.ftflicare;

import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.ftfl.ftflicare.adapter.AppointmentItemAdapter;
import com.ftfl.ftflicare.database.AppointmentDataSource;
import com.ftfl.ftflicare.database.DataBaseHelper;
import com.ftfl.ftflicare.model.AppointmentModel;
import com.ftfl.ftflicare.util.Constants;

public class FTFLICareDoctorAppointmentActivity extends Activity {

	DataBaseHelper mDataBaseHelper;
	AppointmentModel mAppointmentModel;
	AppointmentDataSource mAppointmentDataSource;
	AppointmentItemAdapter mAppointmentItemAdapter;
	List<AppointmentModel> mAppointment;
	ListView mAppointmentListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_appointment);

		// initialize();

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayUseLogoEnabled(false);

		actionBar.setCustomView(R.layout.custom_action_bar2);

		TextView actionBarTextView = (TextView) actionBar.getCustomView()
				.findViewById(R.id.tvActionBar);
		ImageButton addnewButton = (ImageButton) actionBar.getCustomView()
				.findViewById(R.id.btnAddNew);

		// set the action bar title
		actionBarTextView.setText("Doctor's Appointment");

		// go to add new appointment activity
		addnewButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(FTFLICareDoctorAppointmentActivity.this,
						FTFLICareAddNewDoctorAppointment.class);
				startActivity(i);

			}
		});

		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
				| ActionBar.DISPLAY_SHOW_HOME);

		// initialization

		mDataBaseHelper = new DataBaseHelper(this);
		mAppointmentDataSource = new AppointmentDataSource(this);
		mAppointment = mAppointmentDataSource.getAppointmentList();
		mAppointmentItemAdapter = new AppointmentItemAdapter(this, mAppointment);
		mAppointmentListView = (ListView) findViewById(R.id.listAppointment);
		mAppointmentListView.setAdapter(mAppointmentItemAdapter);

		mAppointmentListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int ePosition, long eId) {

						Intent intent = new Intent(
								FTFLICareDoctorAppointmentActivity.this,
								FTFLICareAppointmentDetailActivity.class);
						String id = String.valueOf(mAppointment.get(ePosition)
								.getAppointmentId());
						intent.putExtra(Constants.ID, id);

						startActivity(intent);

					}

				});

	}

}
