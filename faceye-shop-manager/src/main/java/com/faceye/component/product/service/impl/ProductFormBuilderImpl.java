package com.faceye.component.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.product.entity.DynamicProperty;
import com.faceye.component.product.entity.DynamicPropertyValue;
import com.faceye.component.product.entity.Product;
import com.faceye.component.product.entity.ProductProperty;
import com.faceye.component.product.service.DynamicPropertyService;
import com.faceye.component.product.service.DynamicPropertyValueService;
import com.faceye.component.product.service.FormBuilder;
import com.faceye.component.product.service.ProductPropertyService;

/**
 * 构建动态商品输入表单
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月13日
 */
@Service
public class ProductFormBuilderImpl implements FormBuilder {
	private Logger logger = LoggerFactory.getLogger(getClass());
	// Html 动态属性前缀
	private String dynamicPropertyPrefix = "dynamic";
	@Autowired
	private DynamicPropertyService dynamicPropertyService = null;
	@Autowired
	private DynamicPropertyValueService dynamicPropertyValueService = null;
	@Autowired
	private ProductPropertyService productPropertyService = null;

	@Override
	public String build(Product product, List<DynamicProperty> dynamicProperties, Map params) {
		StringBuilder sb = new StringBuilder();
		if (CollectionUtils.isNotEmpty(dynamicProperties)) {
			for (DynamicProperty dynamicProperty : dynamicProperties) {
				sb.append(this.buildDynamicProperty(product, dynamicProperty, params));
			}
		}
		return sb.toString();
	}

	/**
	 * 构建一个属性的动态Html
	 * @todo
	 * @param dynamicProperty
	 * @param params
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月13日
	 */
	private String buildDynamicProperty(Product product, DynamicProperty dynamicProperty, Map params) {
		StringBuilder sb = new StringBuilder();
		String propertyName = this.buildPropertyName(dynamicProperty);
		sb.append("<div class=\"form-group\">");
		sb.append("<label class=\"col-md-2 control-label\" for=\"").append(propertyName).append("\">");
		sb.append(dynamicProperty.getName());
		sb.append("</label>");
		sb.append("<div class=\"col-md-6\">");
		String dataTypeCode = dynamicProperty.getDataType().getCode();
		if (StringUtils.isNotEmpty(dataTypeCode)) {
			if (StringUtils.equalsIgnoreCase(dataTypeCode, "string") || StringUtils.equalsIgnoreCase(dataTypeCode, "integer")
					|| StringUtils.equalsIgnoreCase(dataTypeCode, "float")) {
				sb.append(this.buildInputText(product, dynamicProperty, params));
			} else if (StringUtils.equalsIgnoreCase(dataTypeCode, "boolean")) {
				sb.append(this.buildInputBoolean(product, dynamicProperty, params));
			} else if (StringUtils.equalsIgnoreCase(dataTypeCode, "enum") && !dynamicProperty.getIsSku()) {
				sb.append(this.buildEnumSelect(product, dynamicProperty, params));
			} else if (StringUtils.equalsIgnoreCase(dataTypeCode, "enum") && dynamicProperty.getIsSku()) {
				sb.append(this.buildEnumCheckbox(product, dynamicProperty, params));
			}
		}
		sb.append("</div>");
		sb.append("</div>");
		return sb.toString();
	}

	/**
	 * 构建input type = text  -> dataType = integer,float,string
	 * @todo
	 * @param dynamicProperty
	 * @param params
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月13日
	 */
	private String buildInputText(Product product, DynamicProperty dynamicProperty, Map params) {
		StringBuffer sb = new StringBuffer();
		String propertyName = this.buildPropertyName(dynamicProperty);
		String value = MapUtils.getString(params, propertyName);
		// 如果params中没有值，从数据库回填
		if (StringUtils.isEmpty(value)) {
			Map searchParams = new HashMap();
			if (product != null && product.getId() != null) {
				searchParams.put("EQ|product.$id", product.getId());
				searchParams.put("EQ|dynamicProperty.$id", dynamicProperty.getId());
				List<ProductProperty> productProperties = this.productPropertyService.getPage(searchParams, 1, 0).getContent();
				if (CollectionUtils.isNotEmpty(productProperties)) {
					logger.debug(">>FaceYe product properties size is :" + productProperties.size());
					ProductProperty productProperty = productProperties.get(0);
					value = productProperty.getValue();
				}
			}
		}
		sb.append("<input type=\"text\" name=\"" + propertyName + "\" class=\"form-control\"");
		if (StringUtils.isNotBlank(value)) {
			sb.append(" value=\"" + value + "\"");
		}
		sb.append(">");
		return sb.toString();
	}

	/**
	 * 构建布尔型
	 * @todo
	 * @param product
	 * @param dynamicProperty
	 * @param params
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月13日
	 */
	private String buildInputBoolean(Product product, DynamicProperty dynamicProperty, Map params) {
		StringBuffer sb = new StringBuffer();
		String propertyName = this.buildPropertyName(dynamicProperty);
		String value = MapUtils.getString(params, propertyName);
		// 如果params中没有值，从数据库回填
		if (StringUtils.isEmpty(value)) {
			Map searchParams = new HashMap();
			if (product != null && product.getId() != null) {
				searchParams.put("EQ|product.$id", product.getId());
				searchParams.put("EQ|dynamicProperty.$id", dynamicProperty.getId());
				List<ProductProperty> productProperties = this.productPropertyService.getPage(searchParams, 1, 0).getContent();
				if (CollectionUtils.isNotEmpty(productProperties)) {
					logger.debug(">>FaceYe product properties size is :" + productProperties.size());
					ProductProperty productProperty = productProperties.get(0);
					value = productProperty.getValue();
				}
			}
		}
		sb.append("<span class=\"label label-primary\">是</span>&nbsp;&nbsp;");
		sb.append("<input id=\"" + propertyName + "1\" name=\"" + propertyName + "\" type=\"radio\" value=\"true\"");
		if (StringUtils.equals(value, "true")) {
			sb.append("checked=\"checked\"");
		}
		sb.append("/>&nbsp;&nbsp;&nbsp;&nbsp;");
		sb.append("<span class=\"label label-danger\">否</span>&nbsp;&nbsp;");
		sb.append("<input id=\"" + propertyName + "2\" name=\"" + propertyName + "\" type=\"radio\" value=\"false\"");
		if (StringUtils.equals(value, "false")) {
			sb.append("checked=\"checked\"");
		}
		sb.append("/>");
		return sb.toString();
	}

	/**
	 * 构建枚举型下拉选择框
	 * @todo
	 * @param product
	 * @param dynamicProperty
	 * @param params
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月13日
	 */
	private String buildEnumSelect(Product product, DynamicProperty dynamicProperty, Map params) {
		StringBuffer sb = new StringBuffer();
		String propertyName = this.buildPropertyName(dynamicProperty);
		// DynamicPropertyValue -> id
		String value = MapUtils.getString(params, propertyName);
		// 如果params中没有值，从数据库回填
		if (StringUtils.isEmpty(value)) {
			Map searchParams = new HashMap();
			if (product != null && product.getId() != null) {
				searchParams.put("EQ|product.$id", product.getId());
				searchParams.put("EQ|dynamicProperty.$id", dynamicProperty.getId());
				List<ProductProperty> productProperties = this.productPropertyService.getPage(searchParams, 1, 0).getContent();
				if (CollectionUtils.isNotEmpty(productProperties)) {
					logger.debug(">>FaceYe product properties size is :" + productProperties.size());
					ProductProperty productProperty = productProperties.get(0);
					if (productProperty.getDynamicPropertyValue() != null) {
						value = "" + productProperty.getDynamicPropertyValue().getId();
					}
				}
			}
		}
		sb.append("<select name=\"" + propertyName + "\" class=\"form-control\">");
		sb.append("<option value=\"0\">");
		sb.append("请选择");
		sb.append(dynamicProperty.getName());
		sb.append("</option>");
		Map searchParams = new HashMap();
		searchParams.put("EQ|dynamicProperty.$id", dynamicProperty.getId());
		List<DynamicPropertyValue> dynamicPropertyValues = this.dynamicPropertyValueService.getPage(searchParams, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(dynamicPropertyValues)) {
			for (DynamicPropertyValue dynamicPropertyValue : dynamicPropertyValues) {
				sb.append("<option value=\"");
				sb.append(dynamicPropertyValue.getId());
				sb.append("\"");
				if (StringUtils.equals(value, dynamicPropertyValue.getId().toString())) {
					sb.append(" selected");
				}
				sb.append(">");
				sb.append(dynamicPropertyValue.getValue());
				sb.append("</option>");
			}
		}
		sb.append("</select>");
		return sb.toString();
	}

	/**
	 * 构建checkbox选择框,当前为sku 动态属性
	 * @todo
	 * @param product
	 * @param dynamicProperty
	 * @param params
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月13日
	 */
	private String buildEnumCheckbox(Product product, DynamicProperty dynamicProperty, Map params) {
		StringBuffer sb = new StringBuffer();
		// String propertyName = this.buildPropertyName(dynamicProperty);
		// DynamicPropertyValue -> id
		String value = "";
		List<ProductProperty> productProperties = null;
		// 如果params中没有值，从数据库回填
		if (StringUtils.isEmpty(value)) {
			Map searchParams = new HashMap();
			if (product != null && product.getId() != null) {
				searchParams.put("EQ|product.$id", product.getId());
				searchParams.put("EQ|dynamicProperty.$id", dynamicProperty.getId());
				productProperties = this.productPropertyService.getPage(searchParams, 1, 0).getContent();
				if (CollectionUtils.isNotEmpty(productProperties)) {
					logger.debug(">>FaceYe product properties size is :" + productProperties.size());
				}
			}
		}
		List<DynamicPropertyValue> dynamicPropertyValues = null;
		Map searchParams = new HashMap();
		searchParams.put("EQ|dynamicProperty.$id", dynamicProperty.getId());
		dynamicPropertyValues = this.dynamicPropertyValueService.getPage(searchParams, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(dynamicPropertyValues)) {
			for (DynamicPropertyValue dynamicPropertyValue : dynamicPropertyValues) {
				String propertyName = this.buildPropertyEnumSkuName(dynamicProperty, dynamicPropertyValue);
				value = "";
				value = MapUtils.getString(params, propertyName);
				if (StringUtils.isEmpty(value) && CollectionUtils.isNotEmpty(productProperties)) {
					for (ProductProperty productProperty : productProperties) {
						if (productProperty.getDynamicPropertyValue().getId().compareTo(dynamicPropertyValue.getId()) == 0) {
							value = "" + dynamicPropertyValue.getId();
							break;
						}
					}
				}
				sb.append("<label class=\"checkbox-inline\">");
				sb.append("<input type=\"checkbox\" name=\"" + propertyName + "\" value = \"" + dynamicPropertyValue.getId() + "\"");
				if (StringUtils.isNotEmpty(value)) {
					sb.append(" checked=\"checked\"");
				}
				sb.append(">");
				sb.append(dynamicPropertyValue.getValue());
				sb.append("</label>");
			}
		}
		return sb.toString();
	}

	/**
	 * 构建输入框 name
	 * @todo
	 * @param dynamicProperty
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月13日
	 */
	private String buildPropertyName(DynamicProperty dynamicProperty) {
		String propertyName = this.dynamicPropertyPrefix + "_" + dynamicProperty.getId();
		return propertyName;
	}

	/**
	 * 用于sku 属性的checkbox name
	 * @todo
	 * @param dynamicProperty
	 * @param dynamicPropertyValue
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月13日
	 */
	private String buildPropertyEnumSkuName(DynamicProperty dynamicProperty, DynamicPropertyValue dynamicPropertyValue) {
		String propertyName = this.dynamicPropertyPrefix + "_" + dynamicProperty.getId() + "_" + dynamicPropertyValue.getId();
		return propertyName;
	}

}
