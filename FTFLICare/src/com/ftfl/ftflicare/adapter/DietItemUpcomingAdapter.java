package com.ftfl.ftflicare.adapter;

import java.util.List;

import com.ftfl.ftflicare.R;
import com.ftfl.ftflicare.model.DietChartModel;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DietItemUpcomingAdapter extends ArrayAdapter<DietChartModel>{
	
	TextView tvEvent, tvTime, tvManu;
	ImageView imageAlarm;
	
	private final Activity context;
	List<DietChartModel> allDailyDietChart;
	
	public DietItemUpcomingAdapter(Activity context, List<DietChartModel> allDailyDietChart) {
		super(context, R.layout.each_today_diet_item,allDailyDietChart);
		this.context = context;
		this.allDailyDietChart = allDailyDietChart;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		DietChartModel mDietChart;
		mDietChart = allDailyDietChart.get(position);
		
		LayoutInflater inflater = context.getLayoutInflater();
		
		View rowView = inflater.inflate(R.layout.each_today_diet_item, null, true);
		
		tvEvent = (TextView) rowView.findViewById(R.id.viewEvent);
		tvEvent.setText(mDietChart.getEventName().toString());
		
		tvTime = (TextView) rowView.findViewById(R.id.viewTime);
		tvTime.setText("Time:"+mDietChart.getTime().toString());
		
		tvManu = (TextView) rowView.findViewById(R.id.viewManu);
		tvManu.setText(mDietChart.getFoodMenu().toString());
		
		String mAlarm = mDietChart.getAlarm();
		if(mAlarm!=""){
			
			imageAlarm = (ImageView) rowView.findViewById(R.id.imageAlarm);
			imageAlarm.setImageResource(R.drawable.alarm_on);
		
			}else{
				imageAlarm.setImageResource(R.drawable.alarm_off);
			}
		
		return rowView;
	}
	
	

}
