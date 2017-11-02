package com.faceye.component.order.repository.mongo;

import com.faceye.component.customer.entity.Customer;
import com.faceye.component.order.entity.Cart;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Cart 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface CartRepository extends BaseMongoRepository<Cart,Long> {
	
	public Cart getCartByCustomer(Customer customer);
	
	public Cart getCartByUid(String uid);
}/**@generate-repository-source@**/
