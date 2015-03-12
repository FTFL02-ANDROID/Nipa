package com.ftfl.ftflicare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ftfl.ftflicare.adapter.MedicalCenterItemAdapter;

public class FTFLICareMedicalCenterActivity extends Activity {

	 ListView listCare;
	  String[] mCareName = {
	    "Apollo Hospital",
	    "Square Hospital",
	    "National Heart Foundation Hospital & Research ",
	    "Al Helal Specialized Hospital",
	    "Bangabandhu Sheikh Mujib Medical University",
	    "Bangladesh Medical College",
	    "Lion Foundation Eye Hospital"
	  } ;

	  
	  double[] mLatitude = {
			    23.8108949,
			    23.752997,
			    23.803745,
			    23.802677,
			    23.738914,
			    23.750306,
			    23.779461
			  } ;
	  
	  double[] mLongitude = {
			    90.4305911,
			    90.381461,
			    90.361955,
			    90.370751,
			    90.394778,
			    90.369881,
			    90.379176			    
			  } ;
	
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_medical_center);
	    MedicalCenterItemAdapter adapter = new
	    		MedicalCenterItemAdapter(FTFLICareMedicalCenterActivity.this, mCareName, mLatitude, mLongitude);
	    listCare=(ListView)findViewById(R.id.listCare);
	    listCare.setAdapter(adapter);
	    listCare.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	                @Override
	                public void onItemClick(AdapterView<?> parent, View view,
	                                        int position, long id) {
	                	
	                	Bundle b = new Bundle();
						b.putStringArray("care", mCareName);
						b.putDoubleArray("lat", mLatitude);
						b.putDoubleArray("long", mLongitude);
					
						Intent in = new Intent(FTFLICareMedicalCenterActivity.this, FTFLICareGoogleMapActivity.class);

						// Storing bundle object into intent
						in.putExtras(b);
						startActivity(in);
//	                	startActivity(new Intent(CareCenterListActivity.this,
//	                			CareCenterGoogleMapActivity.class));
	                }
	            });
	  }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ftflicare_settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
