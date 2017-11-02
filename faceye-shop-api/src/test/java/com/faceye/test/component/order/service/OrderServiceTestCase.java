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

import com.faceye.component.order.entity.Order;
import com.faceye.component.order.service.OrderService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Order  服务层测试用例
 * 
 * @author haipenge 
 * 联系人:haipenge@gmail.com 
 * 创建日期:2015-6-13 11:31:36
 */
public class OrderServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private OrderService orderService = null;
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
		Assert.isTrue(orderService != null);
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
		//this.orderService.removeAllInBatch();
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
		Order order = new Order();
		this.orderService.save(order);
		List<Order> orders = this.orderService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(orders));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Order order = new Order();
		this.orderService.save(order);
		List<Order> orders = this.orderService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(orders));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Order order = new Order();
			this.orderService.save(order);
		}
		List<Order> orders = this.orderService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(orders) && orders.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Order order = new Order();
		this.orderService.save(order);
		logger.debug(">>Entity id is:" + order.getId());
		Order e = this.orderService.get(order.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Order order = new Order();
		this.orderService.save(order);
		this.orderService.remove(order);
		List<Order> orders = this.orderService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(orders));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Order order = new Order();
			this.orderService.save(order);
		}
		List<Order> orders = this.orderService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(orders) && orders.size() == 5);
		this.orderService.removeAllInBatch();
		orders = this.orderService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(orders));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Order order = new Order();
			this.orderService.save(order);
		}
		this.orderService.removeAll();
		List<Order> orders = this.orderService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(orders));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Order> orders = new ArrayList<Order>();
		for (int i = 0; i < 5; i++) {
			Order order = new Order();
			
			this.orderService.save(order);
			orders.add(order);
		}
		this.orderService.removeInBatch(orders);
		orders = this.orderService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(orders));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Order order = new Order();
			this.orderService.save(order);
		}
		List<Order> orders = this.orderService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(orders) && orders.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Order order = new Order();
			this.orderService.save(order);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Order> page = this.orderService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.orderService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.orderService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);
	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Order order = new Order();
			this.orderService.save(order);
			id = order.getId();
		}
		Order e = this.orderService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Order order = new Order();
			this.orderService.save(order);
			if (i < 5) {
				ids.add(order.getId());
			}
		}
		List<Order> orders = this.orderService.getAll(ids);
		Assert.isTrue(orders != null && orders.size() == 5);
	}
}
