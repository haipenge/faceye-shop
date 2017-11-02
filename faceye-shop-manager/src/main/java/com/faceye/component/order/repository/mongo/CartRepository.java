package com.faceye.component.order.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.order.entity.Cart;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Cart 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface CartRepository extends BaseMongoRepository<Cart,Long> {
	
	
}/**@generate-repository-source@**/
