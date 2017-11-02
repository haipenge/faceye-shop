package com.faceye.component.customer.service;

import com.faceye.component.customer.entity.Customer;
import com.faceye.component.security.web.entity.User;
import com.faceye.feature.service.BaseService;
/**
 * 模块:客户->com.faceye.compoent.customer.service<br>
 * 说明:<br>
 * 实体:客户->com.faceye.component.customer.entity.entity.Customer 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-16 18:57:43<br>
 */
public interface CustomerService extends BaseService<Customer,Long>{

	public Customer getCustomerByUser(User user);
	
	public Customer getCustomerByUid(String uid);
	
	public Customer getCurrentLoginCustomer();
}/**@generate-service-source@**/
