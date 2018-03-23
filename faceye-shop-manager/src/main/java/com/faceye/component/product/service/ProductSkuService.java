package com.faceye.component.product.service;

import java.util.List;

import com.faceye.component.product.entity.Product;
import com.faceye.component.product.entity.ProductSku;
import com.faceye.component.product.model.SkuInfo;
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
 * 实体:产品SKU->com.faceye.component.product.entity.entity.ProductSku 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:36<br>
 */
public interface ProductSkuService extends BaseService<ProductSku,Long>{

	/**
	 * 取得某一商品的SKU信息
	 * @todo
	 * @param product
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月14日
	 */
	public List<SkuInfo> getProductSkuInfos(Product product);
}/**@generate-service-source@**/
