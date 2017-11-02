package com.faceye.component.order.service;

import com.faceye.component.order.entity.Cart;
import com.faceye.component.order.service.model.CartInfo;
import com.faceye.feature.service.BaseService;

/**
 * Cart 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface CartService extends BaseService<Cart, Long> {

	/**
	 * 取得当前登陆用户的购物车
	 * @todo
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月17日
	 */
	public Cart getCurrentUserCart();

	public Cart getCartByUid(String uid);

	/**
	 * 统计购物车中的商品(sku)情况
	 * @todo
	 * @param uid
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月17日
	 */
	public CartInfo getCartInfo(String uid);

//	/**
//	 * 按店铺组织购物车数据
//	 * @todo
//	 * @param cartItemInfos
//	 * @return
//	 * @author:@haipenge
//	 * 联系:haipenge@gmail.com
//	 * 创建时间:2015年6月22日
//	 */
//	public List<CartShopInfo> getCartShopInfos(List<CartItemInfo> cartItemInfos);
//
//	/**
//	 * 按店铺组织购物车
//	 * @todo
//	 * @param uid
//	 * @return
//	 * @author:@haipenge
//	 * 联系:haipenge@gmail.com
//	 * 创建时间:2015年6月22日
//	 */
//	public List<CartShopInfo> getCartShopInfos(String uid);

}
/**@generate-service-source@**/
