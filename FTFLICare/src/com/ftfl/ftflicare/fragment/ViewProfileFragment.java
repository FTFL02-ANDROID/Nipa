package com.ftfl.ftflicare.fragment;


import com.ftfl.ftflicare.R;
import com.ftfl.ftflicare.database.UserDataSource;
import com.ftfl.ftflicare.model.UserProfileModel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewProfileFragment extends Fragment{
	
	UserProfileModel mUserProfileModel;
	UserDataSource mUserDataSource;
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		mUserProfileModel = new UserProfileModel();
		mUserDataSource = new UserDataSource(getActivity());
		
		mUserProfileModel = mUserDataSource.getProfile();
		
		View rootView = inflater.inflate(R.layout.fragment_view_profile, container, false);

		TextView nameTv = (TextView) rootView.findViewById(R.id.viewName);
		TextView genderTv = (TextView) rootView.findViewById(R.id.viewGender);
		TextView heightTv = (TextView) rootView.findViewById(R.id.viewHeight);
		TextView weightTv = (TextView) rootView.findViewById(R.id.viewWeight);
		TextView dateOfBirthTv = (TextView) rootView.findViewById(R.id.viewDateOfBirth);
		
		 nameTv.setText(mUserProfileModel.getName().toString());
		 genderTv.setText(mUserProfileModel.getGender().toString());
		 heightTv.setText(mUserProfileModel.getHeight().toString());
		 weightTv.setText(mUserProfileModel.getWeight().toString());
		 dateOfBirthTv.setText(mUserProfileModel.getDOB().toString());
		
        return rootView;
	}

}
