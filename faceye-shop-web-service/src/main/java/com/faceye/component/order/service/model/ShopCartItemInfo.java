package com.faceye.component.order.service.model;

import java.util.ArrayList;
import java.util.List;

import com.faceye.component.setting.entity.Shop;
/**
 * 按店铺（shop)组织购物车
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月18日
 */
public class ShopCartItemInfo {
	private Shop shop = null;
	private List<CartItemInfo> cartItemInfos = new ArrayList<CartItemInfo>(0);
	/**
	 * 购物车的商品价格总合（每个店铺)
	 * 单位:分
	 */
	private Float totalFee=0.0F;
	/**
	 * /**
	 * 购物车的商品价格总合（每个店铺)
	 * 单位:元
	 */
	private Float totalFeeYuan=0.0F;

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<CartItemInfo> getCartItemInfos() {
		return cartItemInfos;
	}

	public void setCartItemInfos(List<CartItemInfo> cartItemInfos) {
		this.cartItemInfos = cartItemInfos;
	}

	public Float getTotalFee() {
		totalFee=new Float(totalFee);
		return totalFee;
	}

	public void setTotalFee(Float totalFee) {
		this.totalFee = totalFee;
	}

	public Float getTotalFeeYuan() {
		totalFeeYuan=this.getTotalFee()/100;
		return totalFeeYuan;
	}

	public void setTotalFeeYuan(Float totalFeeYuan) {
		this.totalFeeYuan = totalFeeYuan;
	}

	
	
}
