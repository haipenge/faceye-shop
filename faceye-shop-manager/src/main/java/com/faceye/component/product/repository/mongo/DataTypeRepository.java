package com.faceye.component.product.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.product.entity.DataType;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * DataType 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface DataTypeRepository extends BaseMongoRepository<DataType,Long> {
	
	
}/**@generate-repository-source@**/
