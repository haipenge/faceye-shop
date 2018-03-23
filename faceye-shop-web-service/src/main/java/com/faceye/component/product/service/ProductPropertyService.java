package com.faceye.component.product.service;

import java.util.List;

import com.faceye.component.product.entity.ProductProperty;
import com.faceye.component.product.model.ProductPropertyInfo;
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
 * 模块:产品->com.faceye.compoent.product.service<br>
 * 说明:<br>
 * 实体:产品属性->com.faceye.component.product.entity.entity.ProductProperty 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-16 18:57:42<br>
 */
public interface ProductPropertyService extends BaseService<ProductProperty,Long>{

	
	/**
	 * 根据产品ID取得产品属性列表
	 * @todo
	 * @param productId
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月20日
	 */
	public List<ProductProperty> getProductPropertyByProductId(Long productId);
	/**
	 * 构建产品信息对像
	 * @todo
	 * @param productProperties
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月20日
	 */
	//public List<ProductPropertyInfo> getProductPropertyInfos(List<ProductProperty> productProperties);
	
	
	public List<ProductPropertyInfo> getProdutPropertyInfosByProductId(Long productId);
}/**@generate-service-source@**/
