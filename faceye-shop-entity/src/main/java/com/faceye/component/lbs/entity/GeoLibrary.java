package com.faceye.component.lbs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * GeoLibrary ORM 实体<br>
 * 数据库表:lbs_geoLibrary<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月21日<br>
 */
@Document(collection = "lbs_geo_library")
public class GeoLibrary implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	@Id
	private Long id = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 地址位置标识ID，冗余字段，用于标识导入的城市数据
	 */
	private Long geoId=null;
	
	public Long getGeoId() {
		return geoId;
	}

	public void setGeoId(Long geoId) {
		this.geoId = geoId;
	}

	/**
	 * 说明:名称<br>
	 * 
	 * 属性名: name<br>
	 * 类型: String<br>
	 * 数据库字段:name<br>
	 * 
	 * @author haipenge<br>
	 */

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 说明:排序值<br>
	 * 属性名: orderIndex<br>
	 * 类型: Integer<br>
	 * 数据库字段:order_index<br>
	 * 
	 * @author haipenge<br>
	 */

	private Integer orderIndex;

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	/**
	 * 说明:编码<br>
	 * 属性名: code<br>
	 * 类型: String<br>
	 * 数据库字段:code<br>
	 * 
	 * @author haipenge<br>
	 */

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 上级行政单位ID
	 */
	private Long parentId = null;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 说明:级别，0->省，1->市,2->区县乡,3->街道,4->社区，村<br>
	 * 属性名: level<br>
	 * 类型: Integer<br>
	 * 数据库字段:level<br>
	 * 
	 * @author haipenge<br>
	 */

	private Integer level;

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}/** @generate-entity-source@ **/
