package com.faceye.component.product.service;

import java.util.Map;

import com.faceye.component.product.entity.Product;
import com.faceye.component.product.model.ProductInfo;
import com.faceye.feature.service.BaseService;

/**
 * 模块:产品->com.faceye.compoent.product.service<br>
 * 说明:<br>
 * 实体:产品->com.faceye.component.product.entity.entity.Product 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:35<br>
 */
public interface ProductService extends BaseService<Product, Long> {

	public Product save(Map params);
	
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
	 * 生成产品编码
	 * @todo
	 * @param product
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年12月9日
	 */
	public String generateProductCode();
}
/**@generate-service-source@**/
