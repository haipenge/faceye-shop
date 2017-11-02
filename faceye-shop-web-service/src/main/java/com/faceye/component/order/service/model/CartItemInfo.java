package com.faceye.component.order.service.model;

import java.util.ArrayList;
import java.util.List;

import com.faceye.component.order.entity.CartItem;
import com.faceye.component.product.entity.Image;
import com.faceye.component.product.entity.SkuProperty;

/**
 * 购物车明细信息
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月17日
 */
public class CartItemInfo {
	private CartItem cartItem = null;
	private List<Image> images=new ArrayList<Image>(0);
	private List<SkuProperty> skuProperties = null;

	public List<SkuProperty> getSkuProperties() {
		return skuProperties;
	}

	public void setSkuProperties(List<SkuProperty> skuProperties) {
		this.skuProperties = skuProperties;
	}

	public CartItem getCartItem() {
		return cartItem;
	}

	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	

}
