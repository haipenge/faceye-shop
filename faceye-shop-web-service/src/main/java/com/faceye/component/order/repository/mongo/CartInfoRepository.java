package com.faceye.component.order.repository.mongo;

import com.faceye.component.order.service.model.CartInfo;

/**
 * 购物车信息统计
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年10月6日
 */
public interface CartInfoRepository {
	/**
	 * 统计购物车商品信息
	 * @todo
	 * @param cartId
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年10月6日
	 */
	public CartInfo getCartInfo(String cartId);
}
