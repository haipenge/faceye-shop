package com.faceye.component.lbs.repository.mongo;

import com.faceye.component.lbs.entity.GeoLibrary;
import com.faceye.feature.repository.mongo.BaseMongoRepository;

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
}
