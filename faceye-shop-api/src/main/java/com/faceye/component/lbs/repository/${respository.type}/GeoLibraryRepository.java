package com.faceye.component.lbs.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.lbs.entity.GeoLibrary;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * GeoLibrary 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface GeoLibraryRepository extends BaseMongoRepository<GeoLibrary,Long> {
	
	
}/**@generate-repository-source@**/
