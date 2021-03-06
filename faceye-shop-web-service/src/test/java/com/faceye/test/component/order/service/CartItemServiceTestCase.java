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

import com.faceye.component.order.entity.CartItem;
import com.faceye.component.order.service.CartItemService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * CartItem  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class CartItemServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private CartItemService cartItemService = null;
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
		Assert.isTrue(cartItemService != null);
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
		//this.cartItemService.removeAllInBatch();
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
		CartItem entity = new CartItem();
		this.cartItemService.save(entity);
		List<CartItem> entites = this.cartItemService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		CartItem entity = new CartItem();
		this.cartItemService.save(entity);
		List<CartItem> entites = this.cartItemService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			CartItem entity = new CartItem();
			this.cartItemService.save(entity);
		}
		List<CartItem> entities = this.cartItemService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		CartItem entity = new CartItem();
		this.cartItemService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		CartItem e = this.cartItemService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		CartItem entity = new CartItem();
		this.cartItemService.save(entity);
		this.cartItemService.remove(entity);
		List<CartItem> entities = this.cartItemService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			CartItem entity = new CartItem();
			this.cartItemService.save(entity);
		}
		List<CartItem> entities = this.cartItemService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.cartItemService.removeAllInBatch();
		entities = this.cartItemService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			CartItem entity = new CartItem();
			this.cartItemService.save(entity);
		}
		this.cartItemService.removeAll();
		List<CartItem> entities = this.cartItemService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<CartItem> entities = new ArrayList<CartItem>();
		for (int i = 0; i < 5; i++) {
			CartItem entity = new CartItem();
			
			this.cartItemService.save(entity);
			entities.add(entity);
		}
		this.cartItemService.removeInBatch(entities);
		entities = this.cartItemService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			CartItem entity = new CartItem();
			this.cartItemService.save(entity);
		}
		List<CartItem> entities = this.cartItemService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			CartItem entity = new CartItem();
			this.cartItemService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<CartItem> page = this.cartItemService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.cartItemService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.cartItemService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			CartItem entity = new CartItem();
			this.cartItemService.save(entity);
			id = entity.getId();
		}
		CartItem e = this.cartItemService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			CartItem entity = new CartItem();
			this.cartItemService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<CartItem> entities = this.cartItemService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}
