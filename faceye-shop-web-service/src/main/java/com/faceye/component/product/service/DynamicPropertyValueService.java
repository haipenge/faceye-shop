package com.faceye.component.product.service;

import com.faceye.component.product.entity.DynamicPropertyValue;
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
import com.faceye.feature.util.ServiceException;
/**
 * 模块:产品->com.faceye.compoent.product.service<br>
 * 说明:<br>
 * 实体:动态属性值->com.faceye.component.product.entity.entity.DynamicPropertyValue 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-16 18:57:42<br>
 */
public interface DynamicPropertyValueService extends BaseService<DynamicPropertyValue,Long>{

	
}/**@generate-service-source@**/
