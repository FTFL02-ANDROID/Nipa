package com.ftfl.ftflicare.model;

public class VaccineModel {
	int mVaccineId;
	String mVaccineName;
	String mVaccineDate;
	String mVaccineTime;
	int mAlarmCecked;
	
	
	
	public VaccineModel() {
		super();
	}


	public VaccineModel(int eVaccineId, String eVaccineName,
			String eVaccineDate, String eVaccineTime, int eAlarmCecked) {
		super();
		this.mVaccineId = eVaccineId;
		this.mVaccineName = eVaccineName;
		this.mVaccineDate = eVaccineDate;
		this.mVaccineTime = eVaccineTime;
		this.mAlarmCecked = eAlarmCecked;
	}


	public VaccineModel(String eVaccineName, String eVaccineDate,
			String eVaccineTime, int eAlarmCecked) {
		super();
		this.mVaccineName = eVaccineName;
		this.mVaccineDate = eVaccineDate;
		this.mVaccineTime = eVaccineTime;
		this.mAlarmCecked = eAlarmCecked;
	}


	public int getVaccineId() {
		return mVaccineId;
	}


	public void setVaccineId(int eVaccineId) {
		this.mVaccineId = eVaccineId;
	}


	public String getVaccineName() {
		return mVaccineName;
	}


	public void setVaccineName(String eVaccineName) {
		this.mVaccineName = eVaccineName;
	}


	public String getVaccineDate() {
		return mVaccineDate;
	}


	public void setVaccineDate(String eVaccineDate) {
		this.mVaccineDate = eVaccineDate;
	}


	public String getVaccineTime() {
		return mVaccineTime;
	}


	public void setVaccineTime(String eVaccineTime) {
		this.mVaccineTime = eVaccineTime;
	}
	
	public int getAlarmCecked() {
		return mAlarmCecked;
	}


	public void setAlarmCecked(int eAlarmCecked) {
		this.mAlarmCecked = eAlarmCecked;
	}
	

}
