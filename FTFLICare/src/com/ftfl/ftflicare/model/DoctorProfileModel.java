package com.ftfl.ftflicare.model;

public class DoctorProfileModel {
	
	int mDoctorId;
	String mDoctorName;
	String mSpecialistOf;
	String mDoctorEmail;
	String mDoctorMobile;
	
	public DoctorProfileModel() {
		super();
	}
	
	public DoctorProfileModel(int eDoctorId, String eDoctorName,
			String eSpecialistOf, String eDoctorEmail, String eDoctorMobile) {
		super();
		this.mDoctorId = eDoctorId;
		this.mDoctorName = eDoctorName;
		this.mSpecialistOf = eSpecialistOf;
		this.mDoctorEmail = eDoctorEmail;
		this.mDoctorMobile = eDoctorMobile;
	}
	
	public DoctorProfileModel(String eDoctorName, String eSpecialistOf,
			String eDoctorEmail, String eDoctorMobile) {
		super();
		this.mDoctorName = eDoctorName;
		this.mSpecialistOf = eSpecialistOf;
		this.mDoctorEmail = eDoctorEmail;
		this.mDoctorMobile = eDoctorMobile;
	}

	public int getDoctorId() {
		return mDoctorId;
	}

	public void setDoctorId(int eDoctorId) {
		this.mDoctorId = eDoctorId;
	}

	public String getDoctorName() {
		return mDoctorName;
	}

	public void setDoctorName(String eDoctorName) {
		this.mDoctorName = eDoctorName;
	}

	public String getSpecialistOf() {
		return mSpecialistOf;
	}

	public void setSpecialistOf(String eSpecialistOf) {
		this.mSpecialistOf = eSpecialistOf;
	}

	public String getDoctorEmail() {
		return mDoctorEmail;
	}

	public void setDoctorEmail(String eDoctorEmail) {
		this.mDoctorEmail = eDoctorEmail;
	}

	public String getDoctorMobile() {
		return mDoctorMobile;
	}

	public void setDoctorMobile(String eDoctorMobile) {
		this.mDoctorMobile = eDoctorMobile;
	}


}
