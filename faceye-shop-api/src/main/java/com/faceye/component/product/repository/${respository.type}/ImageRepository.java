package com.faceye.component.product.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.product.entity.Image;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Image 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface ImageRepository extends BaseMongoRepository<Image,Long> {
	
	
}/**@generate-repository-source@**/
