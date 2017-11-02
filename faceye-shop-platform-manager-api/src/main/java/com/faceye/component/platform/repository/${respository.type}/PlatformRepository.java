package com.faceye.component.platform.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.platform.entity.Platform;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Platform 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface PlatformRepository extends BaseMongoRepository<Platform,Long> {
	
	
}/**@generate-repository-source@**/
