package com.faceye.component.product.service;

import java.util.List;

import com.faceye.component.product.entity.Category;
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
 * 实体:产品分类->com.faceye.component.product.entity.entity.Category 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-16 18:57:41<br>
 */
public interface CategoryService extends BaseService<Category,Long>{
	public List<Category> getCategoriesByShopId(Long shopId);
	
}/**@generate-service-source@**/
