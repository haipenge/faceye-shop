package com.faceye.component.order.service;

import java.util.List;

import com.faceye.component.order.entity.Order;
import com.faceye.component.order.service.model.OrderConfirmInfo;
import com.faceye.component.order.service.model.OrderConfirmParam;
import com.faceye.component.order.service.model.OrderInfo;
import com.faceye.component.order.service.model.SkuConfirmInfo;
import com.faceye.component.weixin.service.pay.response.UnifiedOrderResponse;
import com.faceye.feature.service.BaseService;

/**
 * 模块:订单->com.faceye.compoent.order.service<br>
 * 说明:<br>
 * 实体:订单->com.faceye.component.order.entity.entity.Order 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-16 18:57:42<br>
 */
public interface OrderService extends BaseService<Order, Long> {

	/**
	 * 从购物车提交订单
	 * @todo
	 * @param cartItemIds
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月18日
	 */
	@Deprecated
	public List<Order> submitOrderFromCart(Long[] cartItemIds,Long addressId);
	
	/**
	 * 直接购买:从商品明细页
	 * @todo
	 * @param porductId
	 * @param quantity
	 * @param dpvids
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年9月29日
	 */
	@Deprecated
    public Order buy(Long productSkuId, Integer quantity,Long addressId) ;
	/**
	 * 取得订单明细数据
	 * @todo
	 * @param order
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月18日
	 */
	public OrderInfo getOrderInfo(Order order);

	/**
	 * 取得订单明细集合
	 * @todo
	 * @param order
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月18日
	 */
	public List<OrderInfo> getOrderInfos(List<Order> orders);
	
	
	/**
	 * 支付
	 * @todo
	 * @param orderId
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月18日
	 */
	public boolean pay(Long orderId);
	
	/**
	 * 根据订单号查询订单
	 * @todo
	 * @param code
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年9月26日
	 */
	public Order getOrderByCode(String code);
	
	/**
	 * 调用微信统一下单接口,准备进行支付
	 * @todo
	 * @param order
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年10月4日
	 */
	public UnifiedOrderResponse callWeixinUnifiedOrder(Order order,String tradeType,String openid);
	
	/**
	 * 根据提交的购物车条目结算ID，取得 确认订单对像
	 * @todo
	 * @param cartItemId
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年10月4日
	 */
	public OrderConfirmInfo getOrderConfirmInfoByCartItemIds(Long ...cartItemId);
	/**
	 * 取得直接购买商品的OrderConfirmInfo
	 * @todo
	 * @param productId
	 * @param dvps,动态属性ID(SKU)
	 * @param quantity
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年10月4日
	 */
	public OrderConfirmInfo getOrderConfirmInfo(Long productId,String dpvids,Integer quantity);
	
	/**
	 * 确认订单
	 * @todo
	 * @param sukConfirmInfos
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年10月4日
	 */
	public Order confirmOrder(List<OrderConfirmParam> orderConfirmParams,Long addressId);
}
/**@generate-service-source@**/
