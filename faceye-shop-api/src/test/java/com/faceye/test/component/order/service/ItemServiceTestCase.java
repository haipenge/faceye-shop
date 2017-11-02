package com.faceye.test.component.order.service;

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

import com.faceye.component.order.entity.Item;
import com.faceye.component.order.service.ItemService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Item  服务层测试用例
 * 
 * @author haipenge 
 * 联系人:haipenge@gmail.com 
 * 创建日期:2015-6-13 11:31:36
 */
public class ItemServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private ItemService itemService = null;
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
		Assert.isTrue(itemService != null);
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
		//this.itemService.removeAllInBatch();
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
		Item item = new Item();
		this.itemService.save(item);
		List<Item> items = this.itemService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(items));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Item item = new Item();
		this.itemService.save(item);
		List<Item> items = this.itemService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(items));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Item item = new Item();
			this.itemService.save(item);
		}
		List<Item> items = this.itemService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(items) && items.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Item item = new Item();
		this.itemService.save(item);
		logger.debug(">>Entity id is:" + item.getId());
		Item e = this.itemService.get(item.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Item item = new Item();
		this.itemService.save(item);
		this.itemService.remove(item);
		List<Item> items = this.itemService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(items));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Item item = new Item();
			this.itemService.save(item);
		}
		List<Item> items = this.itemService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(items) && items.size() == 5);
		this.itemService.removeAllInBatch();
		items = this.itemService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(items));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Item item = new Item();
			this.itemService.save(item);
		}
		this.itemService.removeAll();
		List<Item> items = this.itemService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(items));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Item> items = new ArrayList<Item>();
		for (int i = 0; i < 5; i++) {
			Item item = new Item();
			
			this.itemService.save(item);
			items.add(item);
		}
		this.itemService.removeInBatch(items);
		items = this.itemService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(items));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Item item = new Item();
			this.itemService.save(item);
		}
		List<Item> items = this.itemService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(items) && items.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Item item = new Item();
			this.itemService.save(item);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Item> page = this.itemService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.itemService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.itemService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);
	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Item item = new Item();
			this.itemService.save(item);
			id = item.getId();
		}
		Item e = this.itemService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Item item = new Item();
			this.itemService.save(item);
			if (i < 5) {
				ids.add(item.getId());
			}
		}
		List<Item> items = this.itemService.getAll(ids);
		Assert.isTrue(items != null && items.size() == 5);
	}
}
