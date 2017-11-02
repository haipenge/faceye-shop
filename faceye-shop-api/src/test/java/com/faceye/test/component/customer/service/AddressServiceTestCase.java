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

import com.faceye.component.customer.entity.Address;
import com.faceye.component.customer.service.AddressService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Address  服务层测试用例
 * 
 * @author haipenge 
 * 联系人:haipenge@gmail.com 
 * 创建日期:2015-6-13 11:31:37
 */
public class AddressServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private AddressService addressService = null;
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
		Assert.isTrue(addressService != null);
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
		//this.addressService.removeAllInBatch();
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
		Address address = new Address();
		this.addressService.save(address);
		List<Address> addresss = this.addressService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(addresss));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Address address = new Address();
		this.addressService.save(address);
		List<Address> addresss = this.addressService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(addresss));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Address address = new Address();
			this.addressService.save(address);
		}
		List<Address> addresss = this.addressService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(addresss) && addresss.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Address address = new Address();
		this.addressService.save(address);
		logger.debug(">>Entity id is:" + address.getId());
		Address e = this.addressService.get(address.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Address address = new Address();
		this.addressService.save(address);
		this.addressService.remove(address);
		List<Address> addresss = this.addressService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(addresss));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Address address = new Address();
			this.addressService.save(address);
		}
		List<Address> addresss = this.addressService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(addresss) && addresss.size() == 5);
		this.addressService.removeAllInBatch();
		addresss = this.addressService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(addresss));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Address address = new Address();
			this.addressService.save(address);
		}
		this.addressService.removeAll();
		List<Address> addresss = this.addressService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(addresss));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Address> addresss = new ArrayList<Address>();
		for (int i = 0; i < 5; i++) {
			Address address = new Address();
			
			this.addressService.save(address);
			addresss.add(address);
		}
		this.addressService.removeInBatch(addresss);
		addresss = this.addressService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(addresss));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Address address = new Address();
			this.addressService.save(address);
		}
		List<Address> addresss = this.addressService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(addresss) && addresss.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Address address = new Address();
			this.addressService.save(address);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Address> page = this.addressService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.addressService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.addressService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);
	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Address address = new Address();
			this.addressService.save(address);
			id = address.getId();
		}
		Address e = this.addressService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Address address = new Address();
			this.addressService.save(address);
			if (i < 5) {
				ids.add(address.getId());
			}
		}
		List<Address> addresss = this.addressService.getAll(ids);
		Assert.isTrue(addresss != null && addresss.size() == 5);
	}
}
