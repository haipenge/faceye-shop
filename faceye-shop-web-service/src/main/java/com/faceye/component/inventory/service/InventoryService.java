package com.faceye.component.inventory.service;

import com.faceye.component.inventory.entity.Inventory;
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
 * 模块:库存->com.faceye.compoent.inventory.service<br>
 * 说明:<br>
 * 实体:库存明细->com.faceye.component.inventory.entity.entity.Inventory 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-16 18:57:43<br>
 */
public interface InventoryService extends BaseService<Inventory,Long>{
	public Inventory getInventoryByProductSkuId(Long productSkuId);
	
}/**@generate-service-source@**/
