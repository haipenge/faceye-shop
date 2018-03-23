package com.faceye.component.order.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.order.entity.CartItem;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * CartItem 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface CartItemRepository extends BaseMongoRepository<CartItem,Long> {
	
	
}/**@generate-repository-source@**/
