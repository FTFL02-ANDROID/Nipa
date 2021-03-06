package com.ftfl.ftflicare;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.GridView;

import com.ftfl.ftflicare.adapter.ImageAdapter;
import com.ftfl.ftflicare.util.Constants;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class FTFLICareGalleryActivity extends Activity {
	GridView grid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
		.cacheInMemory(true).cacheOnDisk(true).build();
ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
		getApplicationContext()).defaultDisplayImageOptions(
		defaultOptions).build();
ImageLoader.getInstance().init(config);

grid = (GridView) findViewById(R.id.gridview);
 List<String> mImagePathList = new ArrayList<String>();

 File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
 Constants.IMAGE_DIRECTORY_NAME);
 for (File imageFile : mediaStorageDir.listFiles()) {
 if (imageFile.isFile()) {
 mImagePathList.add(imageFile.getAbsolutePath());
 }
 }
 grid.setAdapter(new ImageAdapter(FTFLICareGalleryActivity.this, mImagePathList));

}

}
