package com.ftfl.ftflicare.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ftfl.ftflicare.fragment.EditProfileFragment;
import com.ftfl.ftflicare.fragment.ViewProfileFragment;

public class ProfileTabsPagerAdapter extends FragmentPagerAdapter{

	public ProfileTabsPagerAdapter(FragmentManager fm) {
		super(fm);

	}

	@Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            return new ViewProfileFragment();
        case 1:
            return new EditProfileFragment();
	    }
 
        return null;
    }
	


	@Override
	public int getCount() {
		return 2;
	}
	

}