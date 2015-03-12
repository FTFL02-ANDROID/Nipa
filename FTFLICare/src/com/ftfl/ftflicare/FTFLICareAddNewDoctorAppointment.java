package com.ftfl.ftflicare;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ftfl.ftflicare.database.AppointmentDataSource;
import com.ftfl.ftflicare.model.AppointmentModel;
import com.ftfl.ftflicare.util.Constants;
import com.ftfl.ftflicare.util.SetDateOnClickDialog;
import com.ftfl.ftflicare.util.Validation;


public class FTFLICareAddNewDoctorAppointment extends Activity {
	
	static final int REQUEST_IMAGE_CAPTURE = 1;
	
	AppointmentModel mAppointmentModel = null;
	AppointmentDataSource mAppointmentDataSource = null;
	
	EditText mDoctorNameEditText = null;
	EditText mAppointmentReasonEditText = null;
	EditText mAppointmentDateEditText = null;
	EditText mChamberAddressEditText = null;
	ImageView mPrescriptionImageView = null;
	
	String mDoctorName = "";
	String mAppointmentReason = "";
	String mAppointmentDate = "";
	String mChamberAddress = "";
	byte[] mPrescriptionImage = null;
	String mCurrentImagePath = "";
	
	String mDay = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_appointment);
		
		initialize();
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayUseLogoEnabled(false);
		
actionBar.setCustomView(R.layout.custom_action_bar4);
		
		TextView actionBarTextView = (TextView) actionBar.getCustomView().findViewById(R.id.tvActionBar);
		Button saveAppoinmentButton = (Button) actionBar.getCustomView().findViewById(R.id.btnSave);
		
		actionBarTextView.setText("Add Appointment");
		saveAppoinmentButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				saveAppointment();
			
			}
		});
		
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
		        | ActionBar.DISPLAY_SHOW_HOME);
	}
	
	public void saveAppointment(){
		
		if (checkValidation()) {
		
		mDoctorName = mDoctorNameEditText.getText().toString();
		mAppointmentReason = mAppointmentReasonEditText.getText().toString();
		mAppointmentDate = mAppointmentDateEditText.getText().toString();
		mChamberAddress = mChamberAddressEditText.getText().toString();
		
		
		mAppointmentModel = new AppointmentModel(mPrescriptionImage, mDoctorName, mAppointmentReason, mAppointmentDate, mChamberAddress);
		mAppointmentDataSource = new AppointmentDataSource(this);
		if (mAppointmentDataSource.insertAppointmentInfo(mAppointmentModel) == true) {
			Toast toast = Toast.makeText(this, "Data Saved.",
					Toast.LENGTH_LONG);
			toast.show();

			startActivity(new Intent(getApplicationContext(),
					FTFLICareDoctorAppointmentActivity.class));

		} else {
			Toast toast = Toast.makeText(this,
					"Unable to save, Please take photo.",
					Toast.LENGTH_LONG);
			toast.show();

		}
		
		}
	}
	
	private boolean checkValidation(){
		boolean isValid = true;
		 
        if (!Validation.hasText(mDoctorNameEditText)) isValid = false;
        if (!Validation.hasText(mAppointmentReasonEditText)) isValid = false;
        if (!Validation.hasText(mAppointmentDateEditText)) isValid = false;
        if (!Validation.hasText(mChamberAddressEditText)) isValid = false;
 
        return isValid;
	}
	
	public void initialize(){
		
		mPrescriptionImageView = (ImageView) findViewById(R.id.ivPrescription);
		
		
		mDoctorNameEditText = (EditText) findViewById(R.id.etDoctorName);
		mDoctorNameEditText.addTextChangedListener(new TextWatcher() {
			
            public void afterTextChanged(Editable s) {
            	
                Validation.hasText(mDoctorNameEditText);
            }
            
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
		
		
		mAppointmentReasonEditText = (EditText) findViewById(R.id.etAppointmentReason);
		mDoctorNameEditText.addTextChangedListener(new TextWatcher() {
			
            public void afterTextChanged(Editable s) {
            	
                Validation.hasText(mDoctorNameEditText);
            }
            
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
		
		mAppointmentDateEditText = (EditText) findViewById(R.id.etAppointmentDate);
		
		SetDateOnClickDialog datePickerObj=new SetDateOnClickDialog(mAppointmentDateEditText,this);
		int dayOfWeek=datePickerObj.mDayOfWeek;
		mDay=datePickerObj.getDayOfMonth(dayOfWeek).toString();
		
		mAppointmentDateEditText.addTextChangedListener(new TextWatcher() {
			
            public void afterTextChanged(Editable s) {
            	
                Validation.hasText(mAppointmentDateEditText);
            }
            
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
		
		mChamberAddressEditText = (EditText) findViewById(R.id.etChamberAddress);
		mChamberAddressEditText.addTextChangedListener(new TextWatcher() {
			
            public void afterTextChanged(Editable s) {
            	
                Validation.hasText(mChamberAddressEditText);
            }
            
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
		
		
	}
	
	

	public void takePrescriptionPhoto(View v){
		
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// Ensure that there's a camera activity to handle the intent
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			// Create the File where the photo should go
			File photoFile = null;
			try {
				photoFile = createImageFile();
			} catch (IOException ex) {
				Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT)
						.show();
			}
			// Continue only if the File was successfully created
			if (photoFile != null) {
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(photoFile));
				startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			setPic();
			galleryAddPic();
		}
	}

	/**
	 * If user wants to load photo into gallery
	 */
	private void galleryAddPic() {
		Intent mediaScanIntent = new Intent(
				Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		File f = new File(mCurrentImagePath);
		Uri contentUri = Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		this.sendBroadcast(mediaScanIntent);
	}

	@SuppressWarnings("deprecation")
	private void setPic() {
		// Get the dimensions of the View
		int targetW = mPrescriptionImageView.getWidth();
		int targetH = mPrescriptionImageView.getHeight();
		
	

		// Get the dimensions of the bitmap
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(mCurrentImagePath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;

		// Determine how much to scale down the image
		int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
		

		// Decode the image file into a Bitmap sized to fill the View
		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		Bitmap bitmap = BitmapFactory.decodeFile(mCurrentImagePath, bmOptions);// bmOptions
		mPrescriptionImageView.setImageBitmap(bitmap);
		mPrescriptionImage = getBytes(bitmap);
	}

	// convert from bitmap to byte array
	public static byte[] getBytes(Bitmap bitmap) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.PNG, 0, stream);
		return stream.toByteArray();
	}

	// convert from byte array to bitmap
	public static Bitmap getImage(byte[] image) {
		return BitmapFactory.decodeByteArray(image, 0, image.length);
	}

	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = new File(Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),Constants.IMAGE_DIRECTORY_NAME);
		File image = File.createTempFile(imageFileName, /* prefix */
				".jpg", /* suffix */
				storageDir /* directory */
		);

		// Save a file: path for use with ACTION_VIEW intents

		/**************************
		 * |---- You will get the image location from this variable which you
		 * will save into database
		 */
		mCurrentImagePath = image.getAbsolutePath();
		return image;
	}

		
		
	}

	

