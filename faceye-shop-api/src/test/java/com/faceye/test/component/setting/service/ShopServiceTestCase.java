package com.faceye.test.component.setting.service;

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

import com.faceye.component.setting.entity.Shop;
import com.faceye.component.setting.service.ShopService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Shop  服务层测试用例
 * 
 * @author haipenge 
 * 联系人:haipenge@gmail.com 
 * 创建日期:2015-6-13 11:31:35
 */
public class ShopServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private ShopService shopService = null;
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
		Assert.isTrue(shopService != null);
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
		//this.shopService.removeAllInBatch();
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
		Shop shop = new Shop();
		this.shopService.save(shop);
		List<Shop> shops = this.shopService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(shops));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Shop shop = new Shop();
		this.shopService.save(shop);
		List<Shop> shops = this.shopService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(shops));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Shop shop = new Shop();
			this.shopService.save(shop);
		}
		List<Shop> shops = this.shopService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(shops) && shops.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Shop shop = new Shop();
		this.shopService.save(shop);
		logger.debug(">>Entity id is:" + shop.getId());
		Shop e = this.shopService.get(shop.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Shop shop = new Shop();
		this.shopService.save(shop);
		this.shopService.remove(shop);
		List<Shop> shops = this.shopService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(shops));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Shop shop = new Shop();
			this.shopService.save(shop);
		}
		List<Shop> shops = this.shopService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(shops) && shops.size() == 5);
		this.shopService.removeAllInBatch();
		shops = this.shopService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(shops));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Shop shop = new Shop();
			this.shopService.save(shop);
		}
		this.shopService.removeAll();
		List<Shop> shops = this.shopService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(shops));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Shop> shops = new ArrayList<Shop>();
		for (int i = 0; i < 5; i++) {
			Shop shop = new Shop();
			
			this.shopService.save(shop);
			shops.add(shop);
		}
		this.shopService.removeInBatch(shops);
		shops = this.shopService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(shops));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Shop shop = new Shop();
			this.shopService.save(shop);
		}
		List<Shop> shops = this.shopService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(shops) && shops.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Shop shop = new Shop();
			this.shopService.save(shop);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Shop> page = this.shopService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.shopService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.shopService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);
	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Shop shop = new Shop();
			this.shopService.save(shop);
			id = shop.getId();
		}
		Shop e = this.shopService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Shop shop = new Shop();
			this.shopService.save(shop);
			if (i < 5) {
				ids.add(shop.getId());
			}
		}
		List<Shop> shops = this.shopService.getAll(ids);
		Assert.isTrue(shops != null && shops.size() == 5);
	}
}
