package com.faceye.component.lbs.service.model;

/**
 * 地区
 * 
 * @author haipenge
 *
 */
public class Area {
	private Integer id = null;
	private Integer cityId = null;
	private String disName = "";
	private Integer disSort = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}



	public String getDisName() {
		return disName;
	}

	public void setDisName(String disName) {
		this.disName = disName;
	}

	public Integer getDisSort() {
		return disSort;
	}

	public void setDisSort(Integer disSort) {
		this.disSort = disSort;
	}

}
