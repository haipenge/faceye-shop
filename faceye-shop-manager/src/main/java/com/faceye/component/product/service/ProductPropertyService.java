package com.faceye.component.product.service;

import java.util.List;

import com.faceye.component.product.entity.DynamicProperty;
import com.faceye.component.product.entity.DynamicPropertyValue;
import com.faceye.component.product.entity.ProductProperty;
import com.faceye.component.product.model.ProductPropertyInfo;
import com.faceye.feature.service.BaseService;
/**
 * 模块:产品->com.faceye.compoent.product.service<br>
 * 说明:<br>
 * 实体:产品属性->com.faceye.component.product.entity.entity.ProductProperty 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:36<br>
 */
public interface ProductPropertyService extends BaseService<ProductProperty,Long>{

	/**
	 * 获取使用动态属性的产品属性列表
	 * @todo
	 * @param dynamicProperty
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月19日
	 */
	public List<ProductProperty> getProductPropertyByDynamicProperty(DynamicProperty dynamicProperty);
	
	/**
	 * 获取使用动态属性值的产品属性列表
	 * @todo
	 * @param dynamicPropertyValue
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月19日
	 */
	public List<ProductProperty> getProductPropertyByDynamicPropertyValue(DynamicPropertyValue dynamicPropertyValue);
	
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
