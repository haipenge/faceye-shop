package com.faceye.component.product.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.product.entity.ProductSku;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:产品->com.faceye.compoent.product.repository.mongo<br>
 * 说明:<br>
 * 实体:产品SKU->com.faceye.component.product.entity.entity.ProductSku 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2015-6-16 18:57:42<br>
 */
public interface ProductSkuRepository extends BaseMongoRepository<ProductSku,Long> {
	
	
}/**@generate-repository-source@**/
