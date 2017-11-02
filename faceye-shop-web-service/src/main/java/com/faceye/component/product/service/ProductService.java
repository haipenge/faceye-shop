package com.faceye.component.product.service;

import java.util.List;

import com.faceye.component.product.entity.Product;
import com.faceye.component.product.model.ProductInfo;
import com.faceye.feature.service.BaseService;
/**
 * 模块:产品->com.faceye.compoent.product.service<br>
 * 说明:<br>
 * 实体:产品->com.faceye.component.product.entity.entity.Product 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-16 18:57:42<br>
 */
public interface ProductService extends BaseService<Product,Long>{
	/**
	 * 构建产品信息
	 * @todo
	 * @param productId
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月21日
	 */
	public ProductInfo getProductInfo(Long productId);
	
	/**
	 * 构建产品信息列表
	 * @todo
	 * @param products
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月21日
	 */
	public List<ProductInfo> getProductInfos(List<Product> products);
	
}/**@generate-service-source@**/
