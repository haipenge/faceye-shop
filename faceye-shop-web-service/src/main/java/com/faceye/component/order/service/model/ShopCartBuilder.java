package com.faceye.component.order.service.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.faceye.component.setting.entity.Shop;

/**
 * 构造购物车数据结构
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月18日
 */
public class ShopCartBuilder {
	public static List<ShopCartItemInfo> builder(List<CartItemInfo> cartItemInfos) {
		List<ShopCartItemInfo> shopCartItemInfos = new ArrayList<ShopCartItemInfo>(0);
		if (CollectionUtils.isNotEmpty(cartItemInfos)) {
			for (CartItemInfo cartItemInfo : cartItemInfos) {
				ShopCartItemInfo shopCartItemInfo = getShopCartItemInfo(shopCartItemInfos, cartItemInfo.getCartItem().getProductSku()
						.getShop());
				if (shopCartItemInfo == null) {
					shopCartItemInfo = new ShopCartItemInfo();
					shopCartItemInfo.setShop(cartItemInfo.getCartItem().getProductSku().getShop());
					shopCartItemInfos.add(shopCartItemInfo);
				}
				//计算总价
				Float totalFee = shopCartItemInfo.getTotalFee() + cartItemInfo.getCartItem().getQuantity()
						* cartItemInfo.getCartItem().getProductSku().getPrice();
				shopCartItemInfo.setTotalFee(totalFee);
				shopCartItemInfo.getCartItemInfos().add(cartItemInfo);
			}
		}
		return shopCartItemInfos;
	}

	private static ShopCartItemInfo getShopCartItemInfo(List<ShopCartItemInfo> shopCartItemInfos, Shop shop) {
		ShopCartItemInfo shopCartItemInfo = null;
		for (ShopCartItemInfo sInfo : shopCartItemInfos) {
			if (sInfo.getShop().getId().compareTo(shop.getId()) == 0) {
				shopCartItemInfo = sInfo;
				break;
			}
		}
		return shopCartItemInfo;
	}
}
