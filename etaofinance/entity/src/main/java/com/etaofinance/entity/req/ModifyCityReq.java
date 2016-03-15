package com.etaofinance.entity.req;

public class ModifyCityReq {
	private Integer cityCode;
	private Integer provenceCode;
	private String oldOpenPublicCityCodeList;
	private String oldOpenHotCityCodeList;
	private String oldClosePublicCityCodeList;
	private String oldCloseHotCityCodeList;
	
	private String openPublicCityCodeList;
	
	private String closePublicCityCodeList;

	private String openHotCityCodeList;
	
	private String closeHotCityCodeList;

	public String getClosePublicCityCodeList() {
		return closePublicCityCodeList;
	}

	public void setClosePublicCityCodeList(String closePublicCityCodeList) {
		this.closePublicCityCodeList = closePublicCityCodeList;
	}

	public String getOpenHotCityCodeList() {
		return openHotCityCodeList;
	}

	public void setOpenHotCityCodeList(String openHotCityCodeList) {
		this.openHotCityCodeList = openHotCityCodeList;
	}

	public String getCloseHotCityCodeList() {
		return closeHotCityCodeList;
	}

	public void setCloseHotCityCodeList(String closeHotCityCodeList) {
		this.closeHotCityCodeList = closeHotCityCodeList;
	}

	public String getOpenPublicCityCodeList() {
		return openPublicCityCodeList;
	}

	public void setOpenPublicCityCodeList(String openPublicCityCodeList) {
		this.openPublicCityCodeList = openPublicCityCodeList;
	}

	public String getOldClosePublicCityCodeList() {
		return oldClosePublicCityCodeList;
	}

	public void setOldClosePublicCityCodeList(String oldClosePublicCityCodeList) {
		this.oldClosePublicCityCodeList = oldClosePublicCityCodeList;
	}

	public String getOldCloseHotCityCodeList() {
		return oldCloseHotCityCodeList;
	}

	public void setOldCloseHotCityCodeList(String oldCloseHotCityCodeList) {
		this.oldCloseHotCityCodeList = oldCloseHotCityCodeList;
	}

	public String getOldOpenPublicCityCodeList() {
		return oldOpenPublicCityCodeList;
	}

	public void setOldOpenPublicCityCodeList(String oldOpenPublicCityCodeList) {
		this.oldOpenPublicCityCodeList = oldOpenPublicCityCodeList;
	}

	public String getOldOpenHotCityCodeList() {
		return oldOpenHotCityCodeList;
	}

	public void setOldOpenHotCityCodeList(String oldOpenHotCityCodeList) {
		this.oldOpenHotCityCodeList = oldOpenHotCityCodeList;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	public Integer getProvenceCode() {
		return provenceCode;
	}

	public void setProvenceCode(Integer provenceCode) {
		this.provenceCode = provenceCode;
	}
}
