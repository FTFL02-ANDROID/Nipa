package com.ftfl.ftflicare;

import com.ftfl.ftflicare.util.ShakeDetector;
import com.ftfl.ftflicare.util.ShakeDetector.OnShakeListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FTFLICareEmergencyContactActivity extends Activity {
	
	
	private ShakeDetector mShakeDetector;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    String mPhoneNumber = null, 
    	   mName = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);                               
        
        mName = "ICare"; 
        mPhoneNumber = "+8801717206505";
        
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new OnShakeListener() {
           
        	@Override
            public void onShake() {
                // Do stuff!
        		
        		Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mPhoneNumber));
				startActivity(callIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }   
}
