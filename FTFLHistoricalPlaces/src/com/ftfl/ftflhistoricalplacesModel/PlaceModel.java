package com.ftfl.ftflhistoricalplacesModel;

public class PlaceModel {
	
	int mPlacId;
	String mPlaceName;
	String mHistoricalDescription;
	String mAddress;
	String mDistrict;
	String mWeeklyCloseDay;
	String mDailyVisitTime;
	String mLatitude;
	String mLangitude;
	
	
	
	public PlaceModel() {
	}
	
	

	public PlaceModel(int ePlacId, String ePlaceName,
			String eHistoricalDescription, String eAddress, String eDistrict,
			String eWeeklyCloseDay, String eDailyVisitTime, String eLatitude,
			String eLangitude) {
		this.mPlacId = ePlacId;
		this.mPlaceName = ePlaceName;
		this.mHistoricalDescription = eHistoricalDescription;
		this.mAddress = eAddress;
		this.mDistrict = eDistrict;
		this.mWeeklyCloseDay = eWeeklyCloseDay;
		this.mDailyVisitTime = eDailyVisitTime;
		this.mLatitude = eLatitude;
		this.mLangitude = eLangitude;
	}



	public PlaceModel(String ePlaceName,String eHistoricalDescription, 
			String eAddress, String eDistrict, String eWeeklyCloseDay,
			String eDailyVisitTime, String eLatitude, String eLangitude) {
		this.mPlaceName = ePlaceName;
		this.mHistoricalDescription = eHistoricalDescription;
		this.mAddress = eAddress;
		this.mDistrict = eDistrict;
		this.mWeeklyCloseDay = eWeeklyCloseDay;
		this.mDailyVisitTime = eDailyVisitTime;
		this.mLatitude = eLatitude;
		this.mLangitude = eLangitude;
	}

	public int getPlacId() {
		return mPlacId;
	}
	
	public void setPlacId(int ePlacId) {
		this.mPlacId = ePlacId;
	}
	
	public String getPlaceName() {
		return mPlaceName;
	}

	public String getHistoricalDescription() {
		return mHistoricalDescription;
	}

	public String getAddress() {
		return mAddress;
	}

	public String getDistrict() {
		return mDistrict;
	}

	public String getWeeklyCloseDay() {
		return mWeeklyCloseDay;
	}

	public String getDailyVisitTime() {
		return mDailyVisitTime;
	}

	public String getLatitude() {
		return mLatitude;
	}

	public String getLangitude() {
		return mLangitude;
	}
	

}
