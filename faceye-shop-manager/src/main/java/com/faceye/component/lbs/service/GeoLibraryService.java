package com.faceye.component.lbs.service;

import com.faceye.component.lbs.entity.GeoLibrary;
import com.faceye.feature.service.BaseService;
/**
 * GeoLibrary 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface GeoLibraryService extends BaseService<GeoLibrary,Long>{

	/**
	 * 对Geo Library进行初始化
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2015年12月23日 下午3:09:57
	 */
	public void initGeoLibrary();
}/**@generate-service-source@**/
