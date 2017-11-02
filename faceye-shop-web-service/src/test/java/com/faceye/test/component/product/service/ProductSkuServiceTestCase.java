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

import com.faceye.component.product.entity.ProductSku;
import com.faceye.component.product.service.ProductSkuService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * ProductSku  服务层测试用例
 * 
 * @author haipenge 
 * 联系人:haipenge@gmail.com 
 * 创建日期:2015-6-16 18:57:42
 */
public class ProductSkuServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private ProductSkuService productSkuService = null;
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
		Assert.isTrue(productSkuService != null);
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
		//this.productSkuService.removeAllInBatch();
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
		ProductSku productSku = new ProductSku();
		this.productSkuService.save(productSku);
		List<ProductSku> productSkus = this.productSkuService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(productSkus));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		ProductSku productSku = new ProductSku();
		this.productSkuService.save(productSku);
		List<ProductSku> productSkus = this.productSkuService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(productSkus));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			ProductSku productSku = new ProductSku();
			this.productSkuService.save(productSku);
		}
		List<ProductSku> productSkus = this.productSkuService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(productSkus) && productSkus.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		ProductSku productSku = new ProductSku();
		this.productSkuService.save(productSku);
		logger.debug(">>Entity id is:" + productSku.getId());
		ProductSku e = this.productSkuService.get(productSku.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		ProductSku productSku = new ProductSku();
		this.productSkuService.save(productSku);
		this.productSkuService.remove(productSku);
		List<ProductSku> productSkus = this.productSkuService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(productSkus));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			ProductSku productSku = new ProductSku();
			this.productSkuService.save(productSku);
		}
		List<ProductSku> productSkus = this.productSkuService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(productSkus) && productSkus.size() == 5);
		this.productSkuService.removeAllInBatch();
		productSkus = this.productSkuService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(productSkus));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			ProductSku productSku = new ProductSku();
			this.productSkuService.save(productSku);
		}
		this.productSkuService.removeAll();
		List<ProductSku> productSkus = this.productSkuService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(productSkus));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<ProductSku> productSkus = new ArrayList<ProductSku>();
		for (int i = 0; i < 5; i++) {
			ProductSku productSku = new ProductSku();
			
			this.productSkuService.save(productSku);
			productSkus.add(productSku);
		}
		this.productSkuService.removeInBatch(productSkus);
		productSkus = this.productSkuService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(productSkus));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			ProductSku productSku = new ProductSku();
			this.productSkuService.save(productSku);
		}
		List<ProductSku> productSkus = this.productSkuService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(productSkus) && productSkus.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			ProductSku productSku = new ProductSku();
			this.productSkuService.save(productSku);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<ProductSku> page = this.productSkuService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.productSkuService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.productSkuService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);
	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			ProductSku productSku = new ProductSku();
			this.productSkuService.save(productSku);
			id = productSku.getId();
		}
		ProductSku e = this.productSkuService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			ProductSku productSku = new ProductSku();
			this.productSkuService.save(productSku);
			if (i < 5) {
				ids.add(productSku.getId());
			}
		}
		List<ProductSku> productSkus = this.productSkuService.getAll(ids);
		Assert.isTrue(productSkus != null && productSkus.size() == 5);
	}
}
