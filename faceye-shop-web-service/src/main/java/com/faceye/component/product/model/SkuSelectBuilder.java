package com.faceye.component.product.model;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.faceye.component.product.entity.DynamicProperty;
import com.faceye.component.product.entity.DynamicPropertyValue;
import com.faceye.component.product.entity.Product;
import com.faceye.component.product.entity.ProductSku;
import com.faceye.component.product.entity.SkuProperty;

/**
 * 构建选择Sku 所属的数据结构
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月18日
 */
public class SkuSelectBuilder {

	public static List<SkuSelect> build(Product product, List<SkuSelect> skuSelects, List<SkuProperty> skuProperties) {
		if (CollectionUtils.isNotEmpty(skuProperties)) {
			for (SkuProperty skuProperty : skuProperties) {
				ProductSku productSku = skuProperty.getProductSku();
				DynamicProperty dynamicProperty = skuProperty.getDynamicProperty();
				DynamicPropertyValue dynamicPropertyValue = skuProperty.getDynamicPropertyValue();
				SkuSelect skuSelect = getSkuSelect(skuSelects, product, dynamicProperty, dynamicPropertyValue);
				if (skuSelect == null) {
					skuSelect = new SkuSelect();
					skuSelect.setDynamicProperty(dynamicProperty);
					skuSelect.getDynamicPropertyValues().add(dynamicPropertyValue);
					skuSelect.setProduct(product);
					skuSelects.add(skuSelect);
				}
			}
		}
		return skuSelects;
	}

	private static SkuSelect getSkuSelect(List<SkuSelect> skuSelects, Product product, DynamicProperty dynamicProperty,
			DynamicPropertyValue dynamicPropertyValue) {
		SkuSelect result = null;
		if (CollectionUtils.isNotEmpty(skuSelects)) {
			boolean isFind = false;
			for (SkuSelect skuSelect : skuSelects) {
				isFind = false;
				boolean isKeyFind = false;
				if (skuSelect.getProduct().getId().compareTo(product.getId()) == 0
						&& skuSelect.getDynamicProperty().getId().compareTo(dynamicProperty.getId()) == 0) {
					isKeyFind = true;
				}
				if (isKeyFind) {
					result = skuSelect;
					break;
				}
			}
			if (result != null) {
				boolean isListFind = false;
				for (DynamicPropertyValue v : result.getDynamicPropertyValues()) {
					if (v.getId().compareTo(dynamicPropertyValue.getId()) == 0) {
						isListFind = true;
						break;
					}
				}
				if (!isListFind) {
					result.getDynamicPropertyValues().add(dynamicPropertyValue);
				}
			}
		}
		return result;
	}

}
