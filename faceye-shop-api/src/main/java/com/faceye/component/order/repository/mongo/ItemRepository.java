package com.faceye.component.order.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.order.entity.Item;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:订单->com.faceye.compoent.order.repository.mongo<br>
 * 说明:<br>
 * 实体:订单条目->com.faceye.component.order.entity.entity.Item 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2015-6-13 11:31:36<br>
 */
public interface ItemRepository extends BaseMongoRepository<Item,Long> {
	
	
}/**@generate-repository-source@**/
