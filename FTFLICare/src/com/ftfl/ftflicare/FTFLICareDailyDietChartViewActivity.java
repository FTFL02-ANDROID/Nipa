package com.ftfl.ftflicare;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.ftfl.ftflicare.adapter.DietItemUpcomingAdapter;
import com.ftfl.ftflicare.database.DataBaseHelper;
import com.ftfl.ftflicare.database.DietChartDataSource;
import com.ftfl.ftflicare.model.DietChartModel;

public class FTFLICareDailyDietChartViewActivity extends Activity {
		ListView lv;
		DietChartDataSource mDietDataSource;
		DataBaseHelper mDBHelper;
		DietChartModel dailyDietChart;
		int id_To_Update = 0;
		String mDate;
		TextView textDate;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_icare_daily_diet_chart_view);

			mDBHelper = new DataBaseHelper(this);	
			mDietDataSource = new DietChartDataSource(this);
			//textDate = (TextView) findViewById(R.id.tvDailyDietChartDate);
			//mDate = textDate.getText().toString();
			
			Intent mIntent = getIntent();
			mDate = mIntent.getStringExtra("cDate");
			//mDate = "18/1/2015";
			TextView mTitel=(TextView) findViewById(R.id.tvDailyDietChartDate);
			mTitel.getText().toString();
			mTitel.setText("Date is :"+ mDate);
			List<DietChartModel> allDailyDietChart = mDietDataSource.dailyDietChart(mDate);

			DietItemUpcomingAdapter arrayAdapter = new DietItemUpcomingAdapter(this, allDailyDietChart);

			// adding it to the list view.
			lv = (ListView) findViewById(R.id.lvDietChaetList);
			lv.setAdapter(arrayAdapter);
		}

		
	}
