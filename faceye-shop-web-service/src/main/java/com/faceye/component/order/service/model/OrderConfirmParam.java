package com.faceye.component.order.service.model;

/**
 * 订单确认参数
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年10月4日
 */
public class OrderConfirmParam {
	/**
	 * SKU ID
	 */
	private Long productSkuId = null;
	/**
	 * 数量
	 */
	private Integer quantity = 0;
	public Long getProductSkuId() {
		return productSkuId;
	}
	public void setProductSkuId(Long productSkuId) {
		this.productSkuId = productSkuId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
