package com.faceye.component.order.service.model;

import java.util.ArrayList;
import java.util.List;

import com.faceye.component.product.entity.Image;
import com.faceye.component.product.model.SkuInfo;

/**
 * 订单中的SKU确认信息
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年10月4日
 */
public class SkuConfirmInfo {
	private SkuInfo skuInfo = null;
	private Integer quantity = 1;
	private List<Image> images = new ArrayList<Image>(0);

	public SkuInfo getSkuInfo() {
		return skuInfo;
	}

	public void setSkuInfo(SkuInfo skuInfo) {
		this.skuInfo = skuInfo;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

}
