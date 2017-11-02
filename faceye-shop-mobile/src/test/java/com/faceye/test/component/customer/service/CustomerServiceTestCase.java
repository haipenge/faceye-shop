package com.faceye.test.component.customer.service;

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

import com.faceye.component.customer.entity.Customer;
import com.faceye.component.customer.service.CustomerService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Customer  服务层测试用例
 * 
 * @author haipenge 
 * 联系人:haipenge@gmail.com 
 * 创建日期:2015-6-16 18:57:43
 */
public class CustomerServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private CustomerService customerService = null;
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
		Assert.isTrue(customerService != null);
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
		//this.customerService.removeAllInBatch();
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
		Customer customer = new Customer();
		this.customerService.save(customer);
		List<Customer> customers = this.customerService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(customers));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Customer customer = new Customer();
		this.customerService.save(customer);
		List<Customer> customers = this.customerService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(customers));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Customer customer = new Customer();
			this.customerService.save(customer);
		}
		List<Customer> customers = this.customerService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(customers) && customers.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Customer customer = new Customer();
		this.customerService.save(customer);
		logger.debug(">>Entity id is:" + customer.getId());
		Customer e = this.customerService.get(customer.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Customer customer = new Customer();
		this.customerService.save(customer);
		this.customerService.remove(customer);
		List<Customer> customers = this.customerService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(customers));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Customer customer = new Customer();
			this.customerService.save(customer);
		}
		List<Customer> customers = this.customerService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(customers) && customers.size() == 5);
		this.customerService.removeAllInBatch();
		customers = this.customerService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(customers));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Customer customer = new Customer();
			this.customerService.save(customer);
		}
		this.customerService.removeAll();
		List<Customer> customers = this.customerService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(customers));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Customer> customers = new ArrayList<Customer>();
		for (int i = 0; i < 5; i++) {
			Customer customer = new Customer();
			
			this.customerService.save(customer);
			customers.add(customer);
		}
		this.customerService.removeInBatch(customers);
		customers = this.customerService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(customers));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Customer customer = new Customer();
			this.customerService.save(customer);
		}
		List<Customer> customers = this.customerService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(customers) && customers.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Customer customer = new Customer();
			this.customerService.save(customer);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Customer> page = this.customerService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.customerService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.customerService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);
	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Customer customer = new Customer();
			this.customerService.save(customer);
			id = customer.getId();
		}
		Customer e = this.customerService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Customer customer = new Customer();
			this.customerService.save(customer);
			if (i < 5) {
				ids.add(customer.getId());
			}
		}
		List<Customer> customers = this.customerService.getAll(ids);
		Assert.isTrue(customers != null && customers.size() == 5);
	}
}
