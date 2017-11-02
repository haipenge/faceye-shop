package com.faceye.component.order.service.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单确认对像
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年10月4日
 */
public class OrderConfirmInfo {
	private List<SkuConfirmInfo> skuConfirmInfos=new ArrayList<SkuConfirmInfo>(0);

	public List<SkuConfirmInfo> getSkuConfirmInfos() {
		return skuConfirmInfos;
	}

	public void setSkuConfirmInfos(List<SkuConfirmInfo> skuConfirmInfos) {
		this.skuConfirmInfos = skuConfirmInfos;
	}
	
}
