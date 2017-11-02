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

import com.faceye.component.order.entity.Cart;
import com.faceye.component.order.service.CartService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Cart  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class CartServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private CartService cartService = null;
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
		Assert.isTrue(cartService != null);
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
		//this.cartService.removeAllInBatch();
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
		Cart entity = new Cart();
		this.cartService.save(entity);
		List<Cart> entites = this.cartService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Cart entity = new Cart();
		this.cartService.save(entity);
		List<Cart> entites = this.cartService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Cart entity = new Cart();
			this.cartService.save(entity);
		}
		List<Cart> entities = this.cartService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Cart entity = new Cart();
		this.cartService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Cart e = this.cartService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Cart entity = new Cart();
		this.cartService.save(entity);
		this.cartService.remove(entity);
		List<Cart> entities = this.cartService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Cart entity = new Cart();
			this.cartService.save(entity);
		}
		List<Cart> entities = this.cartService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.cartService.removeAllInBatch();
		entities = this.cartService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Cart entity = new Cart();
			this.cartService.save(entity);
		}
		this.cartService.removeAll();
		List<Cart> entities = this.cartService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Cart> entities = new ArrayList<Cart>();
		for (int i = 0; i < 5; i++) {
			Cart entity = new Cart();
			
			this.cartService.save(entity);
			entities.add(entity);
		}
		this.cartService.removeInBatch(entities);
		entities = this.cartService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Cart entity = new Cart();
			this.cartService.save(entity);
		}
		List<Cart> entities = this.cartService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Cart entity = new Cart();
			this.cartService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Cart> page = this.cartService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.cartService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.cartService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Cart entity = new Cart();
			this.cartService.save(entity);
			id = entity.getId();
		}
		Cart e = this.cartService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Cart entity = new Cart();
			this.cartService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Cart> entities = this.cartService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}
