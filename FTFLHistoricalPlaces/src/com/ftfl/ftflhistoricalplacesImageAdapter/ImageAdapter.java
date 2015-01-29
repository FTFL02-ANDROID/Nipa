package com.ftfl.ftflhistoricalplacesImageAdapter;

import com.ftfl.ftflhistoricalplaces.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ImageAdapter extends BaseAdapter{
	 private Context mContext;
	 
	    // Keep all Images in array
	    public Integer[] mImageArray = {
	            R.drawable.maynamati, R.drawable.shahid_minar,
	            R.drawable.paribibi, R.drawable.shat_gombuj,
	            R.drawable.mohostan_gor, R.drawable.sonargoan,
	            R.drawable.songsod, R.drawable.smriti_saudho,
	            R.drawable.war_symmetry, R.drawable.lalbagh_fort,
	           
	    };
	    public String[] mName = {
	          "Maynamati",
	          "Shahid Minar",
	          "Paribibir Mazar",
	          "Shat Gombuj Masjid",
	          "Mohostan Gar",
	          "Sonargoan",
	          "Shangshad Bhaban",
	          "Smriti Saudho",
	          "War Symmetry",
	          "Lal Bagh fort"
	    };
	    
	 // Constructor
	    public ImageAdapter(Context context) {
			this.mContext=context;
		}
	 

	    @Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mImageArray.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mImageArray[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
	   

	    @SuppressLint("InflateParams")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View gridView;
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			if (convertView == null) {

				gridView = new View(mContext);

				gridView = inflater.inflate(R.layout.activity_grid_row, null);

				ImageView flag = (ImageView) gridView .findViewById(R.id.image_view);
				
				flag.setImageResource(mImageArray[position]);
				
				TextView textView = (TextView) gridView.findViewById(R.id.grid_text);

				textView.setText(mName[position]);

				
			} else {
				gridView = (View) convertView;
			}

			return gridView;
		}
}

