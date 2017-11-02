package com.faceye.component.product.service;

import java.util.List;

import com.faceye.component.product.entity.Category;
import com.faceye.component.product.entity.DynamicProperty;
import com.faceye.component.setting.entity.Shop;
import com.faceye.feature.service.BaseService;
/**
 * 模块:产品->com.faceye.compoent.product.service<br>
 * 说明:<br>
 * 实体:产品属性->com.faceye.component.product.entity.entity.DynamicProperty 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:35<br>
 */
public interface DynamicPropertyService extends BaseService<DynamicProperty,Long>{
    /**
     * 取得一个分类的所有属性，含公共属性和分类下的专用属性
     * @todo
     * @param category
     * @return
     * @author:@haipenge
     * 联系:haipenge@gmail.com
     * 创建时间:2015年6月13日
     */
	public List<DynamicProperty> getDynamicProperties(Shop shop,Category category);
}/**@generate-service-source@**/
