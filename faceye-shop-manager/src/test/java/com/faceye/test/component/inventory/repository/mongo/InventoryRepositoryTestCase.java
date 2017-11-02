package com.faceye.test.component.inventory.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.inventory.entity.Inventory;
import com.faceye.component.inventory.repository.mongo.InventoryRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Inventory Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2015-6-13 11:31:37<br>
 */
public class InventoryRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private InventoryRepository inventoryRepository = null;

	@Before
	public void before() throws Exception {
		//this.inventoryRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Inventory inventory = new Inventory();
		this.inventoryRepository.save(inventory);
		Iterable<Inventory> inventorys = this.inventoryRepository.findAll();
		Assert.isTrue(inventorys.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Inventory inventory = new Inventory();
		this.inventoryRepository.save(inventory);
        this.inventoryRepository.delete(inventory.getId());
        Iterable<Inventory> inventorys = this.inventoryRepository.findAll();
		Assert.isTrue(!inventorys.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Inventory inventory = new Inventory();
		this.inventoryRepository.save(inventory);
		inventory=this.inventoryRepository.findOne(inventory.getId());
		Assert.isTrue(inventory!=null);
	}

	
}
