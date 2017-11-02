package com.faceye.component.product.model;

import java.util.ArrayList;
import java.util.List;

import com.faceye.component.product.entity.DynamicProperty;
import com.faceye.component.product.entity.DynamicPropertyValue;
import com.faceye.component.product.entity.Product;

/**
 * 产品详情页选择SKU的对像
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月17日
 */
public class SkuSelect {
	private Product product = null;
	private DynamicProperty dynamicProperty = null;
	private List<DynamicPropertyValue> dynamicPropertyValues = new ArrayList<DynamicPropertyValue>(0);

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public DynamicProperty getDynamicProperty() {
		return dynamicProperty;
	}

	public void setDynamicProperty(DynamicProperty dynamicProperty) {
		this.dynamicProperty = dynamicProperty;
	}

	public List<DynamicPropertyValue> getDynamicPropertyValues() {
		return dynamicPropertyValues;
	}

	public void setDynamicPropertyValues(List<DynamicPropertyValue> dynamicPropertyValues) {
		this.dynamicPropertyValues = dynamicPropertyValues;
	}

}
