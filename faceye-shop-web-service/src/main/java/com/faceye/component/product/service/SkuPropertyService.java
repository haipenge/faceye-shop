package com.faceye.component.product.service;

import java.util.List;

import com.faceye.component.product.entity.ProductSku;
import com.faceye.component.product.entity.SkuProperty;
import com.faceye.feature.service.BaseService;
/**
 * 模块:产品->com.faceye.compoent.product.service<br>
 * 说明:<br>
 * 实体:SKU属性->com.faceye.component.product.entity.entity.SkuProperty 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-16 18:57:42<br>
 */
public interface SkuPropertyService extends BaseService<SkuProperty,Long>{

	/**
	 * 根据 SKU取得SKU属性列表
	 * @todo
	 * @param produtcSku
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月18日
	 */
	public List<SkuProperty> getSkuPropertiesByProductSku(ProductSku productSku);
	
}/**@generate-service-source@**/
