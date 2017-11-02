package com.faceye.component.lbs.service.model;
/**
 * 城市
 * @author haipenge
 *
 */
public class City {
	private Integer cityId = null;
	private String name = "";
	private Integer proId = null;
	private Integer citySort = null;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public Integer getCitySort() {
		return citySort;
	}

	public void setCitySort(Integer citySort) {
		this.citySort = citySort;
	}

}
