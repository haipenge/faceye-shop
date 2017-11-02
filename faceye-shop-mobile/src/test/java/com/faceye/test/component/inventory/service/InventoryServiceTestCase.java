package com.faceye.test.component.inventory.service;

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

import com.faceye.component.inventory.entity.Inventory;
import com.faceye.component.inventory.service.InventoryService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Inventory  服务层测试用例
 * 
 * @author haipenge 
 * 联系人:haipenge@gmail.com 
 * 创建日期:2015-6-16 18:57:43
 */
public class InventoryServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private InventoryService inventoryService = null;
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
		Assert.isTrue(inventoryService != null);
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
		//this.inventoryService.removeAllInBatch();
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
		Inventory inventory = new Inventory();
		this.inventoryService.save(inventory);
		List<Inventory> inventorys = this.inventoryService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(inventorys));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Inventory inventory = new Inventory();
		this.inventoryService.save(inventory);
		List<Inventory> inventorys = this.inventoryService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(inventorys));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Inventory inventory = new Inventory();
			this.inventoryService.save(inventory);
		}
		List<Inventory> inventorys = this.inventoryService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(inventorys) && inventorys.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Inventory inventory = new Inventory();
		this.inventoryService.save(inventory);
		logger.debug(">>Entity id is:" + inventory.getId());
		Inventory e = this.inventoryService.get(inventory.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Inventory inventory = new Inventory();
		this.inventoryService.save(inventory);
		this.inventoryService.remove(inventory);
		List<Inventory> inventorys = this.inventoryService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(inventorys));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Inventory inventory = new Inventory();
			this.inventoryService.save(inventory);
		}
		List<Inventory> inventorys = this.inventoryService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(inventorys) && inventorys.size() == 5);
		this.inventoryService.removeAllInBatch();
		inventorys = this.inventoryService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(inventorys));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Inventory inventory = new Inventory();
			this.inventoryService.save(inventory);
		}
		this.inventoryService.removeAll();
		List<Inventory> inventorys = this.inventoryService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(inventorys));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Inventory> inventorys = new ArrayList<Inventory>();
		for (int i = 0; i < 5; i++) {
			Inventory inventory = new Inventory();
			
			this.inventoryService.save(inventory);
			inventorys.add(inventory);
		}
		this.inventoryService.removeInBatch(inventorys);
		inventorys = this.inventoryService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(inventorys));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Inventory inventory = new Inventory();
			this.inventoryService.save(inventory);
		}
		List<Inventory> inventorys = this.inventoryService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(inventorys) && inventorys.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Inventory inventory = new Inventory();
			this.inventoryService.save(inventory);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Inventory> page = this.inventoryService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.inventoryService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.inventoryService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);
	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Inventory inventory = new Inventory();
			this.inventoryService.save(inventory);
			id = inventory.getId();
		}
		Inventory e = this.inventoryService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Inventory inventory = new Inventory();
			this.inventoryService.save(inventory);
			if (i < 5) {
				ids.add(inventory.getId());
			}
		}
		List<Inventory> inventorys = this.inventoryService.getAll(ids);
		Assert.isTrue(inventorys != null && inventorys.size() == 5);
	}
}
