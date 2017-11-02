package com.faceye.component.product.model;

import java.util.ArrayList;
import java.util.List;

import com.faceye.component.product.entity.DynamicPropertyValue;
import com.faceye.component.product.entity.ProductProperty;

/**
 * 产品属性信息
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月20日
 */
public class ProductPropertyInfo {
	private ProductProperty productProperty = null;
	// 只有SKU属性，具有属性值列表，其它类型属性，无此列表
	private List<DynamicPropertyValue> dynamicPropertyValues = new ArrayList<DynamicPropertyValue>(0);
	public ProductProperty getProductProperty() {
		return productProperty;
	}
	public void setProductProperty(ProductProperty productProperty) {
		this.productProperty = productProperty;
	}
	public List<DynamicPropertyValue> getDynamicPropertyValues() {
		return dynamicPropertyValues;
	}
	public void setDynamicPropertyValues(List<DynamicPropertyValue> dynamicPropertyValues) {
		this.dynamicPropertyValues = dynamicPropertyValues;
	}
	
	public void addDynamicPropertyValue(DynamicPropertyValue dynamicPropertyValue){
		if(!this.isExistDynamicePropertyValue(dynamicPropertyValue)){
			this.getDynamicPropertyValues().add(dynamicPropertyValue);
		}
	}
	
	private boolean isExistDynamicePropertyValue(DynamicPropertyValue dynamicPropertyValue){
		boolean isExist=false;
		for(DynamicPropertyValue dv:this.getDynamicPropertyValues()){
			if(dv.getId().compareTo(dynamicPropertyValue.getId())==0){
				isExist=true;
				break;
			}
		}
		return isExist;
	}
	
}
