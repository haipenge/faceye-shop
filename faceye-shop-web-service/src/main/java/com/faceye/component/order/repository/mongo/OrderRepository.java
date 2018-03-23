package com.faceye.component.order.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.order.entity.Order;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:订单->com.faceye.compoent.order.repository.mongo<br>
 * 说明:<br>
 * 实体:订单->com.faceye.component.order.entity.entity.Order 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2015-6-16 18:57:42<br>
 */
public interface OrderRepository extends BaseMongoRepository<Order,Long> {
	
	public Order getOrderByCode(String code);
}/**@generate-repository-source@**/
