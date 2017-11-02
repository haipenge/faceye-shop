package com.faceye.test.component.product.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import com.faceye.component.product.entity.ProductProperty;
import com.faceye.component.product.service.ProductPropertyService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * ProductProperty  服务层测试用例
 * 
 * @author haipenge 
 * 联系人:haipenge@gmail.com 
 * 创建日期:2015-6-16 18:57:42
 */
public class ProductPropertyServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private ProductPropertyService productPropertyService = null;
	/**
	 * 初始化
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Before
	public void set() throws Exception {
		Assert.isTrue(productPropertyService != null);
	}

	/**
	 * 清理
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@After
	public void after() throws Exception {
		//this.productPropertyService.removeAllInBatch();
	}

	/**
	 *  保存测试
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Test
	public void testSave() throws Exception {
		ProductProperty productProperty = new ProductProperty();
		this.productPropertyService.save(productProperty);
		List<ProductProperty> productPropertys = this.productPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(productPropertys));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		ProductProperty productProperty = new ProductProperty();
		this.productPropertyService.save(productProperty);
		List<ProductProperty> productPropertys = this.productPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(productPropertys));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			ProductProperty productProperty = new ProductProperty();
			this.productPropertyService.save(productProperty);
		}
		List<ProductProperty> productPropertys = this.productPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(productPropertys) && productPropertys.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		ProductProperty productProperty = new ProductProperty();
		this.productPropertyService.save(productProperty);
		logger.debug(">>Entity id is:" + productProperty.getId());
		ProductProperty e = this.productPropertyService.get(productProperty.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		ProductProperty productProperty = new ProductProperty();
		this.productPropertyService.save(productProperty);
		this.productPropertyService.remove(productProperty);
		List<ProductProperty> productPropertys = this.productPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(productPropertys));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			ProductProperty productProperty = new ProductProperty();
			this.productPropertyService.save(productProperty);
		}
		List<ProductProperty> productPropertys = this.productPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(productPropertys) && productPropertys.size() == 5);
		this.productPropertyService.removeAllInBatch();
		productPropertys = this.productPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(productPropertys));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			ProductProperty productProperty = new ProductProperty();
			this.productPropertyService.save(productProperty);
		}
		this.productPropertyService.removeAll();
		List<ProductProperty> productPropertys = this.productPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(productPropertys));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<ProductProperty> productPropertys = new ArrayList<ProductProperty>();
		for (int i = 0; i < 5; i++) {
			ProductProperty productProperty = new ProductProperty();
			
			this.productPropertyService.save(productProperty);
			productPropertys.add(productProperty);
		}
		this.productPropertyService.removeInBatch(productPropertys);
		productPropertys = this.productPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(productPropertys));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			ProductProperty productProperty = new ProductProperty();
			this.productPropertyService.save(productProperty);
		}
		List<ProductProperty> productPropertys = this.productPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(productPropertys) && productPropertys.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			ProductProperty productProperty = new ProductProperty();
			this.productPropertyService.save(productProperty);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<ProductProperty> page = this.productPropertyService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.productPropertyService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.productPropertyService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);
	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			ProductProperty productProperty = new ProductProperty();
			this.productPropertyService.save(productProperty);
			id = productProperty.getId();
		}
		ProductProperty e = this.productPropertyService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			ProductProperty productProperty = new ProductProperty();
			this.productPropertyService.save(productProperty);
			if (i < 5) {
				ids.add(productProperty.getId());
			}
		}
		List<ProductProperty> productPropertys = this.productPropertyService.getAll(ids);
		Assert.isTrue(productPropertys != null && productPropertys.size() == 5);
	}
}
