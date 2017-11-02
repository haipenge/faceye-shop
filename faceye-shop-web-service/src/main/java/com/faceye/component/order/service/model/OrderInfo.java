package com.faceye.component.order.service.model;

import java.util.ArrayList;
import java.util.List;

import com.faceye.component.order.entity.Item;
import com.faceye.component.order.entity.Order;

/**
 * 订单明细数据组装
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月18日
 */
public class OrderInfo {
	private Order order;
	private List<ItemInfo> itemInfos = new ArrayList<ItemInfo>(0);

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<ItemInfo> getItemInfos() {
		return itemInfos;
	}

	public void setItemInfos(List<ItemInfo> itemInfos) {
		this.itemInfos = itemInfos;
	}

}
