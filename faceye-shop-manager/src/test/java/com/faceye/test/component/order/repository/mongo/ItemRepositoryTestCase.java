package com.faceye.test.component.order.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.order.entity.Item;
import com.faceye.component.order.repository.mongo.ItemRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Item Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2015-6-13 11:31:36<br>
 */
public class ItemRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private ItemRepository itemRepository = null;

	@Before
	public void before() throws Exception {
		//this.itemRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Item item = new Item();
		this.itemRepository.save(item);
		Iterable<Item> items = this.itemRepository.findAll();
		Assert.isTrue(items.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Item item = new Item();
		this.itemRepository.save(item);
        this.itemRepository.delete(item.getId());
        Iterable<Item> items = this.itemRepository.findAll();
		Assert.isTrue(!items.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Item item = new Item();
		this.itemRepository.save(item);
		item=this.itemRepository.findOne(item.getId());
		Assert.isTrue(item!=null);
	}

	
}
