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

import com.faceye.component.product.entity.Product;
import com.faceye.component.product.service.ProductService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Product  服务层测试用例
 * 
 * @author haipenge 
 * 联系人:haipenge@gmail.com 
 * 创建日期:2015-6-16 18:57:42
 */
public class ProductServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private ProductService productService = null;
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
		Assert.isTrue(productService != null);
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
		//this.productService.removeAllInBatch();
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
		Product product = new Product();
		this.productService.save(product);
		List<Product> products = this.productService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(products));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Product product = new Product();
		this.productService.save(product);
		List<Product> products = this.productService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(products));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Product product = new Product();
			this.productService.save(product);
		}
		List<Product> products = this.productService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(products) && products.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Product product = new Product();
		this.productService.save(product);
		logger.debug(">>Entity id is:" + product.getId());
		Product e = this.productService.get(product.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Product product = new Product();
		this.productService.save(product);
		this.productService.remove(product);
		List<Product> products = this.productService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(products));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Product product = new Product();
			this.productService.save(product);
		}
		List<Product> products = this.productService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(products) && products.size() == 5);
		this.productService.removeAllInBatch();
		products = this.productService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(products));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Product product = new Product();
			this.productService.save(product);
		}
		this.productService.removeAll();
		List<Product> products = this.productService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(products));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Product> products = new ArrayList<Product>();
		for (int i = 0; i < 5; i++) {
			Product product = new Product();
			
			this.productService.save(product);
			products.add(product);
		}
		this.productService.removeInBatch(products);
		products = this.productService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(products));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Product product = new Product();
			this.productService.save(product);
		}
		List<Product> products = this.productService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(products) && products.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Product product = new Product();
			this.productService.save(product);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Product> page = this.productService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.productService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.productService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);
	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Product product = new Product();
			this.productService.save(product);
			id = product.getId();
		}
		Product e = this.productService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Product product = new Product();
			this.productService.save(product);
			if (i < 5) {
				ids.add(product.getId());
			}
		}
		List<Product> products = this.productService.getAll(ids);
		Assert.isTrue(products != null && products.size() == 5);
	}
}
