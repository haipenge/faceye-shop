package com.faceye.component.setting.service;

import com.faceye.component.security.entity.User;
import com.faceye.component.setting.entity.Shop;
import com.faceye.feature.service.BaseService;
/**
 * 模块:配置->com.faceye.compoent.setting.service<br>
 * 说明:<br>
 * 实体:店铺->com.faceye.component.setting.entity.entity.Shop 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:35<br>
 */
public interface ShopService extends BaseService<Shop,Long>{

	public Shop getShopByUser(User user);
	
	/**
	 * 取得当前登陆用户的店铺
	 * @todo
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月13日
	 */
	public Shop getCurrentUserShop();
	
}/**@generate-service-source@**/
