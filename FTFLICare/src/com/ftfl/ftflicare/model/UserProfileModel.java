package com.ftfl.ftflicare.model;

public class UserProfileModel {
	int mUserId;
	String mName;
	String mGender;
	String mHeight;
	String mWeight;
	String mDOB;
	
	
	public UserProfileModel() {
		super();
	}


	public UserProfileModel(int eUserId, String eName, String eGender,
			String eHeight, String eWeight, String eDOB) {
		super();
		this.mUserId = eUserId;
		this.mName = eName;
		this.mGender = eGender;
		this.mHeight = eHeight;
		this.mWeight = eWeight;
		this.mDOB = eDOB;
	}
	
	
	public UserProfileModel(String eName, String eGender, String eHeight,
			String eWeight, String eDOB) {
		super();
		this.mName = eName;
		this.mGender = eGender;
		this.mHeight = eHeight;
		this.mWeight = eWeight;
		this.mDOB = eDOB;
	}


	public int getUserId() {
		return mUserId;
	}


	public void setId(int eUserId) {
		this.mUserId = eUserId;
	}


	public String getName() {
		return mName;
	}


	public void setName(String eName) {
		this.mName = eName;
	}


	public String getGender() {
		return mGender;
	}


	public void setGender(String eGender) {
		this.mGender = eGender;
	}


	public String getHeight() {
		return mHeight;
	}


	public void setHeight(String eHeight) {
		this.mHeight = eHeight;
	}


	public String getWeight() {
		return mWeight;
	}


	public void setWeight(String eWeight) {
		this.mWeight = eWeight;
	}


	public String getDOB() {
		return mDOB;
	}


	public void setDOB(String eDOB) {
		this.mDOB = eDOB;
	}
	


}
