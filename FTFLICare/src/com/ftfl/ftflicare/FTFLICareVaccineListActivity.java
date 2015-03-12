package com.ftfl.ftflicare;

import java.util.List;

import com.ftfl.ftflicare.adapter.VaccineItemAdapter;
import com.ftfl.ftflicare.database.DataBaseHelper;
import com.ftfl.ftflicare.database.VaccinationDataSource;
import com.ftfl.ftflicare.model.VaccineModel;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FTFLICareVaccineListActivity extends Activity {
	
	private int selectedId;
	DataBaseHelper mDataBaseHelper;
	VaccineModel mVaccineModel;
	VaccinationDataSource mVaccinationDataSource;
	VaccineItemAdapter mVaccineItemAdapter;
	List<VaccineModel> mVaccine;
	ListView mVaccineListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vaccine_list);
		
		//initialize();
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayUseLogoEnabled(false);
		
		actionBar.setCustomView(R.layout.custom_action_bar2);
		
		TextView actionBarTextView = (TextView) actionBar.getCustomView().findViewById(R.id.tvActionBar);
		ImageButton addnewButton = (ImageButton) actionBar.getCustomView().findViewById(R.id.btnAddNew);
		
		//set the action bar title 
		actionBarTextView.setText("Vaccination");
		
		//go to add new vaccination activity
		addnewButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(FTFLICareVaccineListActivity.this,FTFLICareAddNewVaccineActivity.class);
				startActivity(i);
			
			}
		});
		
		
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
		        | ActionBar.DISPLAY_SHOW_HOME);
		
		//initialization
		
		mDataBaseHelper = new DataBaseHelper(this);
		mVaccinationDataSource = new VaccinationDataSource(this);
		mVaccine = mVaccinationDataSource.getVaccineList();
		mVaccineItemAdapter = new VaccineItemAdapter(this, mVaccine);
		mVaccineListView = (ListView) findViewById(R.id.listVaccine);
		mVaccineListView.setAdapter(mVaccineItemAdapter);
		
		mVaccineListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            
        	@Override
 			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
	          	AlertDialog.Builder alertDialog = new AlertDialog.Builder(FTFLICareVaccineListActivity.this);
	          	selectedId=position;
	          	// Setting Dialog Title
	            alertDialog.setTitle("Item Actions...");
	            // Setting Dialog Message
	            alertDialog.setMessage("What do you want to do?");
	            // Setting Icon to Dialog
	            alertDialog.setIcon(R.drawable.ic_launcher);
	       
	              // Setting Positive "Edit" Button
	          	alertDialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
      		public void onClick(DialogInterface dialog,int which) {
   
      			Intent intent=new Intent(FTFLICareVaccineListActivity.this,FTFLICareEditVaccineActivity.class);
                startActivity(intent);
              
              }
          });
   
          // Setting Negative "Delete" Button
	          alertDialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
              // Write your code here to invoke NO event
              //Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
            	  int sId=mVaccine.get(selectedId).getVaccineId();
            	   mVaccinationDataSource.deleteData(sId);
              dialog.cancel();
              finish();
              startActivity(getIntent());
              
              }
          });
   
          // Showing Alert Message
          alertDialog.show();
      }
      
       
      });
		
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ftflicare_vaccine_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
	    switch (item.getItemId()) {
	    
	   
	    case R.id.action_edit_vaccination:
            Intent intent=new Intent(FTFLICareVaccineListActivity.this,FTFLICareEditVaccineActivity.class);
            startActivity(intent);   
            
	    default:
            return super.onOptionsItemSelected(item);
	    }
	}
}
