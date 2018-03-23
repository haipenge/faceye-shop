package com.faceye.component.setting.service;

import com.faceye.component.setting.entity.Shop;
import com.faceye.feature.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
 
/**
 * 模块:配置->com.faceye.compoent.setting.service<br>
 * 说明:<br>
 * 实体:店铺->com.faceye.component.setting.entity.entity.Shop 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-16 18:57:41<br>
 */
public interface ShopService extends BaseService<Shop,Long>{

	
}/**@generate-service-source@**/
