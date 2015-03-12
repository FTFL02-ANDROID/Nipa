package com.ftfl.ftflicare.fragment;

import com.ftfl.ftflicare.R;
import com.ftfl.ftflicare.database.UserDataSource;
import com.ftfl.ftflicare.model.UserProfileModel;
import com.ftfl.ftflicare.util.Validation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EditProfileFragment extends Fragment {

	EditText mNameEditText;
	EditText mHeightEditText;
	EditText mWeightEditText;
	EditText mDOBEditText;

	String mName;
	String mGender;
	String mHeight;
	String mWeight;
	String mDOB;

	UserProfileModel mUserProfileModel;
	UserDataSource mUserDataSource;
	private RadioButton mFemaleRadioButton;
	private RadioButton mMaleRadioButton;

	RadioGroup mGenderRadioGroup;
	RadioButton mGenderRadioButton;
	Button mSaveButton;
	
	View mRootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mUserProfileModel = new UserProfileModel();
		mUserDataSource = new UserDataSource(getActivity());

		mUserProfileModel = mUserDataSource.getProfile();

		mRootView = inflater.inflate(R.layout.fragment_edit_profile,
				container, false);

		mNameEditText = (EditText) mRootView.findViewById(R.id.etName);
		mNameEditText.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {

				Validation.hasText(mNameEditText);
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});
		
		
		mGenderRadioGroup = (RadioGroup) mRootView
				.findViewById(R.id.radioGroupGender);
		int selectedId = mGenderRadioGroup.getCheckedRadioButtonId();
		mGenderRadioButton = (RadioButton) mRootView.findViewById(selectedId);
		mMaleRadioButton = (RadioButton) mRootView
				.findViewById(R.id.radioButtonMale);
		mFemaleRadioButton = (RadioButton) mRootView
				.findViewById(R.id.radioButtonfemale);
		
		mHeightEditText = (EditText) mRootView.findViewById(R.id.etHeight);
		mHeightEditText.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				Validation.hasText(mHeightEditText);
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

		mWeightEditText = (EditText) mRootView.findViewById(R.id.etWeight);
		mWeightEditText.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				Validation.hasText(mWeightEditText);
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

		mDOBEditText = (EditText) mRootView.findViewById(R.id.etDateOfBirth);
		mDOBEditText.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				Validation.hasText(mDOBEditText);
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

		mSaveButton = (Button) mRootView.findViewById(R.id.btnSave);

		setData();

		mSaveButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				saveData();

			}
		});

		return mRootView;
	}

	public void setData() {

		mNameEditText.setText(mUserProfileModel.getName().toString());
		mHeightEditText.setText(mUserProfileModel.getHeight().toString());
		mWeightEditText.setText(mUserProfileModel.getWeight().toString());
		mDOBEditText.setText(mUserProfileModel.getDOB().toString());

		if (mUserProfileModel.getGender().equals("Male")) {
			mMaleRadioButton.setChecked(true);
		} else {

			mFemaleRadioButton.setChecked(true);
		}
	}

	public void saveData() {
		if (checkValidation()) {
			// getting values from input fields.....
			mName = mNameEditText.getText().toString();
			mGenderRadioGroup = (RadioGroup) mRootView.findViewById(R.id.radioGroupGender);
			int selectedId = mGenderRadioGroup.getCheckedRadioButtonId();
			mGenderRadioButton = (RadioButton) mRootView.findViewById(selectedId);
			mGender = mGenderRadioButton.getText().toString();
			mHeight = mHeightEditText.getText().toString();
			mWeight = mWeightEditText.getText().toString();
			mDOB = mDOBEditText.getText().toString();
			// ................................................

			mUserProfileModel = new UserProfileModel(mName, mGender, mHeight,
					mWeight, mDOB);

			mUserDataSource = new UserDataSource(getActivity());

			if (mUserDataSource.updateData(mUserProfileModel) == true) {
				Toast toast = Toast.makeText(getActivity(), "Data Saved.",
						Toast.LENGTH_LONG);
				toast.show();

			} else {
				Toast toast = Toast.makeText(getActivity(),
						"Unable to save, Please insert above information.",
						Toast.LENGTH_LONG);
				toast.show();

			}
		}

	}

	private boolean checkValidation() {
		boolean isValid = true;

		if (!Validation.hasText(mNameEditText))
			isValid = false;
		if (!Validation.hasText(mHeightEditText))
			isValid = false;
		if (!Validation.hasText(mWeightEditText))
			isValid = false;
		if (!Validation.hasText(mDOBEditText))
			isValid = false;

		return isValid;
	}

}
