package com.faceye.component.order.service;

import com.faceye.component.order.entity.Item;
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
 * 模块:订单->com.faceye.compoent.order.service<br>
 * 说明:<br>
 * 实体:订单条目->com.faceye.component.order.entity.entity.Item 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:36<br>
 */
public interface ItemService extends BaseService<Item,Long>{

	
}/**@generate-service-source@**/
