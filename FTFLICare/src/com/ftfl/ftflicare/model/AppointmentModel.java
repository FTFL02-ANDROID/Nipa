package com.ftfl.ftflicare.model;

public class AppointmentModel {
	String mAppointmentId;
	byte[] mPhotoPath;
	String mDoctorName;
	String mAppointmentReason;
	String mAppointmentDate;
	String mChemberAddress;
	
	
	public AppointmentModel() {
		super();
	}
	
	public AppointmentModel(String eAppointmentId, byte[] ePhotoPath,
			String eDoctorName, String eAppointmentReason, String eAppointmentDate,
			String eChemberAddress) {
		super();
		this.mAppointmentId = eAppointmentId;
		this.mPhotoPath = ePhotoPath;
		this.mDoctorName = eDoctorName;
		this.mAppointmentReason = eAppointmentReason;
		this.mAppointmentDate = eAppointmentDate;
		this.mChemberAddress = eChemberAddress;
	}
	
	public AppointmentModel(byte[] ePhotoPath, String eDoctorName, String eAppointmentReason,
			String eAppointmentDate, String eChemberAddress) {
		super();
		this.mPhotoPath = ePhotoPath;
		this.mDoctorName = eDoctorName;
		this.mAppointmentReason = eAppointmentReason;
		this.mAppointmentDate = eAppointmentDate;
		this.mChemberAddress = eChemberAddress;
	}

	public String getAppointmentId() {
		return mAppointmentId;
	}

	public void setAppointmentId(String eAppointmentId) {
		this.mAppointmentId = eAppointmentId;
	}

	public byte[] getPhotoPath() {
		return mPhotoPath;
	}

	public void setPhotoPath(byte[] ePhotoPath) {
		this.mPhotoPath = ePhotoPath;
	}
	
	public String getDoctorName() {
		return mDoctorName;
	}

	public void setDoctorName(String eDoctorName) {
		this.mDoctorName = eDoctorName;
	}

	public String getAppointmentReason() {
		return mAppointmentReason;
	}

	public void setAppointmentReason(String eAppointmentReason) {
		this.mAppointmentReason = eAppointmentReason;
	}

	public String getAppointmentDate() {
		return mAppointmentDate;
	}

	public void setAppointmentDate(String eAppointmentDate) {
		this.mAppointmentDate = eAppointmentDate;
	}

	public String getChemberAddress() {
		return mChemberAddress;
	}

	public void setChemberAddress(String eChemberAddress) {
		this.mChemberAddress = eChemberAddress;
	}
	
	

}
