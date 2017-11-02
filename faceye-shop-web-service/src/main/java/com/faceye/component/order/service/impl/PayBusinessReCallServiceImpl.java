package com.faceye.component.order.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.order.entity.Order;
import com.faceye.component.order.service.OrderService;
import com.faceye.component.order.service.model.OrderInfo;
import com.faceye.component.weixin.service.pay.PayBusinessReCallService;
import com.faceye.component.weixin.service.pay.model.PayProductInfo;
import com.faceye.feature.util.DateUtil;

@Service
public class PayBusinessReCallServiceImpl implements PayBusinessReCallService {
	@Autowired
	private OrderService  orderService=null;
	@Override
	public PayProductInfo buildPayProductInfo(String productOrOrderId) {
		PayProductInfo payProductInfo=null;
		Order order=this.orderService.get(Long.parseLong(productOrOrderId));
		if(null!=order){
			OrderInfo orderInfo=this.orderService.getOrderInfo(order);
			if(orderInfo!=null){
				payProductInfo =new PayProductInfo();
				payProductInfo.setTimeStart(DateUtil.formatDate(order.getCreateDate(), "yyyyMMddHHmmss"));
				payProductInfo.setTimeExpire(DateUtil.formatDate(new Date(order.getCreateDate().getTime()+7*24*60*60*1000), "yyyyMMddHHmmss"));
				payProductInfo.setProductId(""+order.getId());
				payProductInfo.setOutTradeNo(order.getCode());
				payProductInfo.setAttach("attach");
				payProductInfo.setBody("body");
				payProductInfo.setGoodsTag("GoodsTag");
				payProductInfo.setNotifyUrl("NotifyUrl");
				payProductInfo.setSpbillCreateIp("IP");
				payProductInfo.setTotalFee(order.getTotalFee());
				
			}
		}
		return payProductInfo;
	}
	/**
	 * 微信支付成功后的业务回调
	 */
	@Override
	public boolean pay(String productorOrderId) {
		boolean result=false;
		result=this.orderService.pay(Long.parseLong(productorOrderId));
		return result;
	}

}
