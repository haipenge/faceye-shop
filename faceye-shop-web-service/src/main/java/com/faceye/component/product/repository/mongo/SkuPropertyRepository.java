package com.faceye.component.product.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.product.entity.SkuProperty;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:产品->com.faceye.compoent.product.repository.mongo<br>
 * 说明:<br>
 * 实体:SKU属性->com.faceye.component.product.entity.entity.SkuProperty 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2015-6-16 18:57:42<br>
 */
public interface SkuPropertyRepository extends BaseMongoRepository<SkuProperty,Long> {
	
	
}/**@generate-repository-source@**/
