package com.faceye.component.order.model;

import java.util.ArrayList;
import java.util.List;

import com.faceye.component.order.entity.Item;
import com.faceye.component.product.entity.SkuProperty;

/**
 * 订单明细数据组装
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月18日
 */
public class ItemInfo {
	private Item item = null;
	private List<SkuProperty> skuProperties = new ArrayList<SkuProperty>(0);

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<SkuProperty> getSkuProperties() {
		return skuProperties;
	}

	public void setSkuProperties(List<SkuProperty> skuProperties) {
		this.skuProperties = skuProperties;
	}

}
