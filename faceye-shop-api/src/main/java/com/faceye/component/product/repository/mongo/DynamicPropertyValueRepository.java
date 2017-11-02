package com.faceye.component.product.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.product.entity.DynamicPropertyValue;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:产品->com.faceye.compoent.product.repository.mongo<br>
 * 说明:<br>
 * 实体:动态属性值->com.faceye.component.product.entity.entity.DynamicPropertyValue 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2015-6-13 11:31:35<br>
 */
public interface DynamicPropertyValueRepository extends BaseMongoRepository<DynamicPropertyValue,Long> {
	
	
}/**@generate-repository-source@**/
