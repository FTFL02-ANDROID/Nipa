package com.ftfl.ftflicare;

import com.ftfl.ftflicare.adapter.ProfileTabsPagerAdapter;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

@SuppressLint("NewApi")
@SuppressWarnings("deprecation")
public class FTFLICareProfileActivity extends FragmentActivity implements
		TabListener {
	private ViewPager viewPager;
	private ProfileTabsPagerAdapter mAdapter;
	private ActionBar mActionBar;
	// Tab titles
	private String[] tabs = { "View Profile", "Edit Profile" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		// Initilization 
		viewPager = (ViewPager) findViewById(R.id.pager);
		mActionBar = getActionBar();
		/*mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayUseLogoEnabled(false);

		mActionBar.setCustomView(R.layout.custom_action_bar1);*/
		mAdapter = new ProfileTabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		mActionBar.setHomeButtonEnabled(false);
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Adding Tabs
		for (String tab_name : tabs) {
			mActionBar.addTab(mActionBar.newTab().setText(tab_name)
					.setTabListener((android.app.ActionBar.TabListener) this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				mActionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
}
