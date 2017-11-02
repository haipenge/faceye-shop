package com.faceye.component.setting.repository.mongo;

import com.faceye.component.security.entity.User;
import com.faceye.component.setting.entity.Shop;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:配置->com.faceye.compoent.setting.repository.mongo<br>
 * 说明:<br>
 * 实体:店铺->com.faceye.component.setting.entity.entity.Shop 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2015-6-13 11:31:35<br>
 */
public interface ShopRepository extends BaseMongoRepository<Shop,Long> {
	
	public Shop getShopByUser(User user);
}/**@generate-repository-source@**/
