package com.faceye.component.product.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.product.entity.DynamicProperty;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:产品->com.faceye.compoent.product.repository.mongo<br>
 * 说明:<br>
 * 实体:产品属性->com.faceye.component.product.entity.entity.DynamicProperty 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2015-6-13 11:31:35<br>
 */
public interface DynamicPropertyRepository extends BaseMongoRepository<DynamicProperty,Long> {
	
	
}/**@generate-repository-source@**/
