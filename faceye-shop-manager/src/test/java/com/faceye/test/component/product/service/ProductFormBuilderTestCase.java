package com.faceye.test.component.product.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.faceye.component.product.entity.Category;
import com.faceye.component.product.entity.DynamicProperty;
import com.faceye.component.product.entity.Product;
import com.faceye.component.product.service.CategoryService;
import com.faceye.component.product.service.DynamicPropertyService;
import com.faceye.component.product.service.FormBuilder;
import com.faceye.component.security.entity.User;
import com.faceye.component.security.service.UserService;
import com.faceye.component.setting.entity.Shop;
import com.faceye.component.setting.service.ShopService;
import com.faceye.test.feature.service.BaseServiceTestCase;

public class ProductFormBuilderTestCase extends BaseServiceTestCase {
	@Autowired
	private DynamicPropertyService dynamicPropertyService = null;
	@Autowired
	private ShopService shopService = null;
	@Autowired
	private UserService userService = null;
	@Autowired
	private CategoryService categoryService = null;
	@Autowired
	private FormBuilder formBuilder = null;

	@Test
	public void testBuilder() throws Exception {
		User user = this.userService.getUserByUsername("admin");
		Shop shop = this.shopService.getShopByUser(user);
		Category category = this.categoryService.get(1L);
		List<DynamicProperty> dynamicProperties = this.dynamicPropertyService.getDynamicProperties(shop, category);
		Assert.assertTrue(CollectionUtils.isNotEmpty(dynamicProperties));
		Product product = null;
		String html = formBuilder.build(product, dynamicProperties, null);
		logger.debug(">>FaceYe Html is:" + html);
		Assert.assertTrue(StringUtils.isNotEmpty(html));
	}
}
