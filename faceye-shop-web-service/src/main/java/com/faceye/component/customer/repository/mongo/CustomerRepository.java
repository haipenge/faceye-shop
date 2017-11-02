package com.faceye.component.customer.repository.mongo;

import com.faceye.component.customer.entity.Customer;
import com.faceye.component.security.web.entity.User;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:客户->com.faceye.compoent.customer.repository.mongo<br>
 * 说明:<br>
 * 实体:客户->com.faceye.component.customer.entity.entity.Customer 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2015-6-16 18:57:43<br>
 */
public interface CustomerRepository extends BaseMongoRepository<Customer,Long> {
	
	public Customer getCustomerByUserId(Long userId);
	
	public Customer getCustomerByUid(String uid);
}/**@generate-repository-source@**/
