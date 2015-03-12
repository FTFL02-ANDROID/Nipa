package com.ftfl.ftflicare;


import com.ftfl.ftflicare.database.UserDataSource;
import com.ftfl.ftflicare.model.UserProfileModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class FTFLICareSplashActivity extends Activity {
	
	UserProfileModel mUserProfileModel;
	UserDataSource mUserDataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		//Remove title bar
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	    //Remove notification bar
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		
		setContentView(R.layout.activity_splash);
		
		mUserDataSource = new UserDataSource(this);
		
Thread mSplashTime=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					
					if (mUserDataSource.isEmpty() == true){
					Intent i = new Intent(FTFLICareSplashActivity.this,
							FTFLICareCreateProfileActivity.class);
				
              startActivity(i);
              finish();
					}else{
						Intent i = new Intent(FTFLICareSplashActivity.this,
								FTFLICareHomeActivity.class);
					
	              startActivity(i);
	              finish();
						
					}
				}
				
			}
		});
		mSplashTime.start();
	}

}
