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
	
	/**
	 * 根据GeoId和level获取Geo对像
	 * @param geoId
	 * @param level
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2015年12月24日 上午10:36:20
	 */
	public GeoLibrary getGeoLibraryByGeoIdAndLevel(Long geoId,Integer level);
	
}/**@generate-repository-source@**/
