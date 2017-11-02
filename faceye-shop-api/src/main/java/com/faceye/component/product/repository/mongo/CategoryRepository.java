package com.faceye.component.product.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.product.entity.Category;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:产品->com.faceye.compoent.product.repository.mongo<br>
 * 说明:<br>
 * 实体:产品分类->com.faceye.component.product.entity.entity.Category 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2015-6-13 11:31:35<br>
 */
public interface CategoryRepository extends BaseMongoRepository<Category,Long> {
	
	
}/**@generate-repository-source@**/
