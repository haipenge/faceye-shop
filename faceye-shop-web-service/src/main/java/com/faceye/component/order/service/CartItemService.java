package com.faceye.component.order.service;

import java.util.List;

import com.faceye.component.order.entity.CartItem;
import com.faceye.component.order.service.model.CartInfo;
import com.faceye.component.order.service.model.CartItemInfo;
import com.faceye.feature.service.BaseService;

/**
 * CartItem 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface CartItemService extends BaseService<CartItem, Long> {

	/**
	 * 向购物车中添加商品
	 * @todo
	 * @param uid
	 * @param productSkuId
	 * @param quantity
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月17日
	 */
	public boolean addProductSku2Cart(String uid, Long productId, String dynamicPropertyAndValueIds, Integer quantity);

	/**
	 * 取得购物车中商品的明细信息
	 * @todo
	 * @param uid
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月17日
	 */
	public List<CartItemInfo> getCartItemInfos(String uid);

	/**
	 * 转化CartItem -> CartItemInfo
	 * @todo
	 * @param cartItemId
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月22日
	 */
	public CartItemInfo getCartItemInfo(Long cartItemId);

	/**
	 * 取得一组CartItemInfo
	 * @todo
	 * @param cartItemId
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月22日
	 */
	public List<CartItemInfo> getCartItemInfos(Long[] cartItemIds);

}
/**@generate-service-source@**/
