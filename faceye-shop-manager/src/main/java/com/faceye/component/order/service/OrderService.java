package com.faceye.component.order.service;

import java.util.List;

import com.faceye.component.order.entity.Order;
import com.faceye.component.order.model.OrderInfo;
import com.faceye.feature.service.BaseService;
/**
 * 模块:订单->com.faceye.compoent.order.service<br>
 * 说明:<br>
 * 实体:订单->com.faceye.component.order.entity.entity.Order 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:36<br>
 */
public interface OrderService extends BaseService<Order,Long>{
	/**
	 * 取得订单明细数据
	 * @todo
	 * @param order
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月18日
	 */
	public OrderInfo getOrderInfo(Order order,List<OrderInfo> orderInfos);

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
	
}/**@generate-service-source@**/
