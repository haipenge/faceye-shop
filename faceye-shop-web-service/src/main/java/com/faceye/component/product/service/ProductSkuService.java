package com.faceye.component.product.service;

import java.util.List;

import com.faceye.component.product.entity.Product;
import com.faceye.component.product.entity.ProductSku;
import com.faceye.component.product.model.SkuInfo;
import com.faceye.component.product.model.SkuSelect;
import com.faceye.feature.service.BaseService;
/**
 * 模块:产品->com.faceye.compoent.product.service<br>
 * 说明:<br>
 * 实体:产品SKU->com.faceye.component.product.entity.entity.ProductSku 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-16 18:57:42<br>
 */
public interface ProductSkuService extends BaseService<ProductSku,Long>{
	
	/**
	 * 取得一个商品的SKU信息
	 * @todo
	 * @param productId
	 * @param dynamicPropertyAndValueIds
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年9月29日
	 */
	public SkuInfo getSkuInfo(Long productId,String dynamicPropertyAndValueIds);

	public List<SkuInfo> getProductSkuInfos(Product product) ;
	
	/**
	 * 根据productSku 对像取得SkuInfo
	 * @todo
	 * @param productSku
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年10月4日
	 */
	public SkuInfo getSkuInfo(ProductSku productSku);
	
	/**
	 * 产品详情页选择SKU
	 * @todo
	 * @param product
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月17日
	 */
	public List<SkuSelect> getSkuSelects(Product product);
	
	/**
	 * 
	 * @todo
	 * @param product
	 * @param dynamicPropertyAndValueIds ->[dynamicProperty.id-dynamicPropertyValue.id|dynamicProperty.id-dynamicPropertyValue.id]
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月17日
	 */
	public ProductSku getProductSkuByDynamicPropertyAndValueIds(Product product,String dynamicPropertyAndValueIds);
}/**@generate-service-source@**/
