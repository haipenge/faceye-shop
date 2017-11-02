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

import com.faceye.component.product.entity.SkuProperty;
import com.faceye.component.product.service.SkuPropertyService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * SkuProperty  服务层测试用例
 * 
 * @author haipenge 
 * 联系人:haipenge@gmail.com 
 * 创建日期:2015-6-13 11:31:36
 */
public class SkuPropertyServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private SkuPropertyService skuPropertyService = null;
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
		Assert.isTrue(skuPropertyService != null);
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
		//this.skuPropertyService.removeAllInBatch();
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
		SkuProperty skuProperty = new SkuProperty();
		this.skuPropertyService.save(skuProperty);
		List<SkuProperty> skuPropertys = this.skuPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(skuPropertys));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		SkuProperty skuProperty = new SkuProperty();
		this.skuPropertyService.save(skuProperty);
		List<SkuProperty> skuPropertys = this.skuPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(skuPropertys));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			SkuProperty skuProperty = new SkuProperty();
			this.skuPropertyService.save(skuProperty);
		}
		List<SkuProperty> skuPropertys = this.skuPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(skuPropertys) && skuPropertys.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		SkuProperty skuProperty = new SkuProperty();
		this.skuPropertyService.save(skuProperty);
		logger.debug(">>Entity id is:" + skuProperty.getId());
		SkuProperty e = this.skuPropertyService.get(skuProperty.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		SkuProperty skuProperty = new SkuProperty();
		this.skuPropertyService.save(skuProperty);
		this.skuPropertyService.remove(skuProperty);
		List<SkuProperty> skuPropertys = this.skuPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(skuPropertys));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			SkuProperty skuProperty = new SkuProperty();
			this.skuPropertyService.save(skuProperty);
		}
		List<SkuProperty> skuPropertys = this.skuPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(skuPropertys) && skuPropertys.size() == 5);
		this.skuPropertyService.removeAllInBatch();
		skuPropertys = this.skuPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(skuPropertys));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			SkuProperty skuProperty = new SkuProperty();
			this.skuPropertyService.save(skuProperty);
		}
		this.skuPropertyService.removeAll();
		List<SkuProperty> skuPropertys = this.skuPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(skuPropertys));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<SkuProperty> skuPropertys = new ArrayList<SkuProperty>();
		for (int i = 0; i < 5; i++) {
			SkuProperty skuProperty = new SkuProperty();
			
			this.skuPropertyService.save(skuProperty);
			skuPropertys.add(skuProperty);
		}
		this.skuPropertyService.removeInBatch(skuPropertys);
		skuPropertys = this.skuPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(skuPropertys));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			SkuProperty skuProperty = new SkuProperty();
			this.skuPropertyService.save(skuProperty);
		}
		List<SkuProperty> skuPropertys = this.skuPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(skuPropertys) && skuPropertys.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			SkuProperty skuProperty = new SkuProperty();
			this.skuPropertyService.save(skuProperty);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<SkuProperty> page = this.skuPropertyService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.skuPropertyService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.skuPropertyService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);
	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			SkuProperty skuProperty = new SkuProperty();
			this.skuPropertyService.save(skuProperty);
			id = skuProperty.getId();
		}
		SkuProperty e = this.skuPropertyService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			SkuProperty skuProperty = new SkuProperty();
			this.skuPropertyService.save(skuProperty);
			if (i < 5) {
				ids.add(skuProperty.getId());
			}
		}
		List<SkuProperty> skuPropertys = this.skuPropertyService.getAll(ids);
		Assert.isTrue(skuPropertys != null && skuPropertys.size() == 5);
	}
}
