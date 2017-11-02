package com.faceye.component.product.service;

import java.util.List;
import java.util.Map;

import com.faceye.component.product.entity.DynamicProperty;
import com.faceye.component.product.entity.Product;

/**
 * 构造动态Form
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月13日
 */
public interface FormBuilder {
  /**
   * 根据动态属性构建动态Form
   * @todo
   * @param dynamicProperties
   * @param params ->回填参数
   * @return
   * @author:@haipenge
   * 联系:haipenge@gmail.com
   * 创建时间:2015年6月13日
   */
	public String build(Product product,List<DynamicProperty> dynamicProperties,Map params);
}
