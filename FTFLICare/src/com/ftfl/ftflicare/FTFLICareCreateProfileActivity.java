package com.ftfl.ftflicare;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ftfl.ftflicare.database.UserDataSource;
import com.ftfl.ftflicare.model.UserProfileModel;
import com.ftfl.ftflicare.util.SetDateOnClickDialog;
import com.ftfl.ftflicare.util.Validation;

public class FTFLICareCreateProfileActivity extends Activity {
	
	EditText mNameEditText;
	RadioGroup mGenderRadioGroup;
	RadioButton mGenderRadioButton;
	EditText mHeightEditText;
	EditText mWeightEditText;
	EditText mDOBEditText;
	
	String mName;
	String mGender;
	String mHeight;
	String mWeight;
	String mDOB;
	String mDay;
	
	UserProfileModel mUserProfileModel;
	UserDataSource mUserDataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_profile);
		
		initialize();
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayUseLogoEnabled(false);
		
		actionBar.setCustomView(R.layout.custom_action_bar4);
		
		TextView actionBarTextView = (TextView) actionBar.getCustomView().findViewById(R.id.tvActionBar);
		Button saveButton = (Button) actionBar.getCustomView().findViewById(R.id.btnSave);
		
		actionBarTextView.setText("Create Profile");
		saveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				saveData();
			
			}
		});
		
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
		        | ActionBar.DISPLAY_SHOW_HOME);
	}
	
	public void initialize(){
		
		mNameEditText = (EditText) findViewById(R.id.etName);
		mNameEditText.addTextChangedListener(new TextWatcher() {
			
            public void afterTextChanged(Editable s) {
            	
                Validation.hasText(mNameEditText);
            }
            
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
		
		
		mHeightEditText = (EditText) findViewById(R.id.etHeight);
		mHeightEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(mHeightEditText);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
		mWeightEditText = (EditText) findViewById(R.id.etWeight);
		mWeightEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(mWeightEditText);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
		
		
		mDOBEditText = (EditText) findViewById(R.id.etDateOfBirth);
		
		SetDateOnClickDialog datePickerObj=new SetDateOnClickDialog(mDOBEditText,this);
		int dayOfWeek=datePickerObj.mDayOfWeek;
		mDay=datePickerObj.getDayOfMonth(dayOfWeek).toString();
		mDOBEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(mDOBEditText);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
		
	}
	
	public void saveData(){
		if (checkValidation()) {
			//getting values from input fields.....
			mName = mNameEditText.getText().toString();
			
			mGenderRadioGroup = (RadioGroup) findViewById(R.id.radioGroupGender);
			int selectedId = mGenderRadioGroup.getCheckedRadioButtonId();
			mGenderRadioButton = (RadioButton) findViewById(selectedId);
			mGender = mGenderRadioButton.getText().toString();
			
			mHeight = mHeightEditText.getText().toString();
			mWeight = mWeightEditText.getText().toString();
			mDOB = mDOBEditText.getText().toString();
			//................................................
			
			
			mUserProfileModel = new UserProfileModel(mName, mGender, mHeight, mWeight, mDOB);
			
			mUserDataSource = new UserDataSource(this);
			
			if (mUserDataSource.insertUserInfo(mUserProfileModel) == true) {
				Toast toast = Toast.makeText(this, "Data Saved.",
						Toast.LENGTH_LONG);
				toast.show();

				startActivity(new Intent(getApplicationContext(),
						FTFLICareHomeActivity.class));

			} else {
				Toast toast = Toast.makeText(this,
						"Unable to save, Please insert above information.",
						Toast.LENGTH_LONG);
				toast.show();

			}
		}

	}
	
	private boolean checkValidation(){
		boolean isValid = true;
		 
        if (!Validation.hasText(mNameEditText)) isValid = false;
        if (!Validation.hasText(mHeightEditText)) isValid = false;
        if (!Validation.hasText(mWeightEditText)) isValid = false;
        if (!Validation.hasText(mDOBEditText)) isValid = false;
 
        return isValid;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ftflcreate_profile, menu);
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
