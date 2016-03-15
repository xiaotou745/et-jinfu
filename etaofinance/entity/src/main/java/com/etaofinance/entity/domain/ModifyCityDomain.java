package com.etaofinance.entity.domain;

import java.util.List;

public class ModifyCityDomain {
	private Integer cityCode;
	private Integer provenceCode;
	private List<String> openPublicCityCodeList;
	private List<String> closePublicCityCodeList;
	private List<String> openHotCityCodeList;
	private List<String> closeHotCityCodeList;
	public List<String> getOpenPublicCityCodeList() {
		return openPublicCityCodeList;
	}
	public void setOpenPublicCityCodeList(List<String> openPublicCityCodeList) {
		this.openPublicCityCodeList = openPublicCityCodeList;
	}
	public List<String> getClosePublicCityCodeList() {
		return closePublicCityCodeList;
	}
	public void setClosePublicCityCodeList(List<String> closePublicCityCodeList) {
		this.closePublicCityCodeList = closePublicCityCodeList;
	}
	public List<String> getOpenHotCityCodeList() {
		return openHotCityCodeList;
	}
	public void setOpenHotCityCodeList(List<String> openHotCityCodeList) {
		this.openHotCityCodeList = openHotCityCodeList;
	}
	public List<String> getCloseHotCityCodeList() {
		return closeHotCityCodeList;
	}
	public void setCloseHotCityCodeList(List<String> closeHotCityCodeList) {
		this.closeHotCityCodeList = closeHotCityCodeList;
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
