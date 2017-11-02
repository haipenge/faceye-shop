package com.faceye.component.order.service.model;

/**
 * 购物车的总体情况(含各个店铺)
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月22日
 */
public class CartInfo {

	/**
	 * SKU的总数量
	 */
	private Integer totalQuantity = 0;

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

}
