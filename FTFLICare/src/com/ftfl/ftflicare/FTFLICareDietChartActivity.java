package com.ftfl.ftflicare;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ftfl.ftflicare.adapter.DietItemTodayAdapter;
import com.ftfl.ftflicare.database.DataBaseHelper;
import com.ftfl.ftflicare.database.DietChartDataSource;
import com.ftfl.ftflicare.model.DietChartModel;

public class FTFLICareDietChartActivity extends Activity {
	
	ListView mListViewOne;
	ListView mListViewTwo;
	DietChartDataSource mDietDataSource;
	DataBaseHelper mDBHelper;
	DietChartModel dailyDietChart;
	int id_To_Update = 0;
	String mDate;
	TextView textDate;
	TextView tvDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diet_chart);
		
		mDBHelper = new DataBaseHelper(this);	
		mDietDataSource = new DietChartDataSource(this);
		//textDate = (TextView) findViewById(R.id.tvDailyDietChartDate);
		//mDate = textDate.getText().toString();
		mDate = "18/1/2015";
		List<DietChartModel> allDailyDietChart = mDietDataSource.todayDietChart();

		DietItemTodayAdapter arrayAdapterOne = new DietItemTodayAdapter(this, allDailyDietChart);

		// adding it to the list view.
		mListViewOne = (ListView) findViewById(R.id.lvTodayDietChart);
		mListViewOne.setAdapter(arrayAdapterOne);
		
		
		List<String> upcomingDates = mDietDataSource.upcomingDates();
		//DailyProfileViewThreeAdapter arrayAdapterTwo = new DailyProfileViewThreeAdapter(this, allDailyDietChart);
		ArrayAdapter<String> mDatesAdapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, upcomingDates);
		// adding it to the list view.
		mListViewTwo = (ListView) findViewById(R.id.lvUpComingDietChart);
		mListViewTwo.setAdapter(mDatesAdapter);
		
		mListViewTwo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent,
					android.view.View view, int position, long id) {

				tvDate = (TextView) view.findViewById(android.R.id.text1);
				String dateValue = tvDate.getText().toString(); // get id
																	// from text
																	// view

				/*
				 * create an intent and send data
				 */
				Intent mPreviewIntent = new Intent(getApplicationContext(),
						FTFLICareDailyDietChartViewActivity.class);
				mPreviewIntent.putExtra("cDate", dateValue);

				startActivity(mPreviewIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ftflicare_diet_chart, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
        case R.id.createDiet:
        	startActivity(new Intent(FTFLICareDietChartActivity.this, FTFLICareCreateDietChartActivity.class));
            return true;

        
        default:
            return super.onOptionsItemSelected(item);
    }
	}
}
