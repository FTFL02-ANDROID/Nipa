package com.ftfl.ftflhistoricalplaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import com.ftfl.ftflhistoricalplacesImageAdapter.ImageAdapter;

public class FTFLHistoricalPlacesHomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		

		GridView gridView = (GridView) findViewById(R.id.grid_view);
		// Instance of ImageAdapter Class
		gridView.setAdapter(new ImageAdapter(this));

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						String.valueOf(position), Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getApplicationContext(),
						FTFLHistoricalPlacesDetailActivity.class);
				// passing array index
				i.putExtra("id", position);
				startActivity(i);
			}
		});

		TextView addMoreTextView = (TextView) findViewById(R.id.addMoreTextView);
		addMoreTextView.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
                // Switching to Add more places screen
                Intent i = new Intent(getApplicationContext(), FTFLHistoricalPlacesAddMoreActivity.class);
                startActivity(i);
            }
        });

	}

}
