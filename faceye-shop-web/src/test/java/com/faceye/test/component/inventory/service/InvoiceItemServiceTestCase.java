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

import com.faceye.component.inventory.entity.InvoiceItem;
import com.faceye.component.inventory.service.InvoiceItemService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * InvoiceItem  服务层测试用例
 * 
 * @author haipenge 
 * 联系人:haipenge@gmail.com 
 * 创建日期:2015-6-16 18:57:43
 */
public class InvoiceItemServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private InvoiceItemService invoiceItemService = null;
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
		Assert.isTrue(invoiceItemService != null);
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
		//this.invoiceItemService.removeAllInBatch();
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
		InvoiceItem invoiceItem = new InvoiceItem();
		this.invoiceItemService.save(invoiceItem);
		List<InvoiceItem> invoiceItems = this.invoiceItemService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(invoiceItems));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		InvoiceItem invoiceItem = new InvoiceItem();
		this.invoiceItemService.save(invoiceItem);
		List<InvoiceItem> invoiceItems = this.invoiceItemService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(invoiceItems));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			InvoiceItem invoiceItem = new InvoiceItem();
			this.invoiceItemService.save(invoiceItem);
		}
		List<InvoiceItem> invoiceItems = this.invoiceItemService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(invoiceItems) && invoiceItems.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		InvoiceItem invoiceItem = new InvoiceItem();
		this.invoiceItemService.save(invoiceItem);
		logger.debug(">>Entity id is:" + invoiceItem.getId());
		InvoiceItem e = this.invoiceItemService.get(invoiceItem.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		InvoiceItem invoiceItem = new InvoiceItem();
		this.invoiceItemService.save(invoiceItem);
		this.invoiceItemService.remove(invoiceItem);
		List<InvoiceItem> invoiceItems = this.invoiceItemService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(invoiceItems));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			InvoiceItem invoiceItem = new InvoiceItem();
			this.invoiceItemService.save(invoiceItem);
		}
		List<InvoiceItem> invoiceItems = this.invoiceItemService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(invoiceItems) && invoiceItems.size() == 5);
		this.invoiceItemService.removeAllInBatch();
		invoiceItems = this.invoiceItemService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(invoiceItems));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			InvoiceItem invoiceItem = new InvoiceItem();
			this.invoiceItemService.save(invoiceItem);
		}
		this.invoiceItemService.removeAll();
		List<InvoiceItem> invoiceItems = this.invoiceItemService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(invoiceItems));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<InvoiceItem> invoiceItems = new ArrayList<InvoiceItem>();
		for (int i = 0; i < 5; i++) {
			InvoiceItem invoiceItem = new InvoiceItem();
			
			this.invoiceItemService.save(invoiceItem);
			invoiceItems.add(invoiceItem);
		}
		this.invoiceItemService.removeInBatch(invoiceItems);
		invoiceItems = this.invoiceItemService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(invoiceItems));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			InvoiceItem invoiceItem = new InvoiceItem();
			this.invoiceItemService.save(invoiceItem);
		}
		List<InvoiceItem> invoiceItems = this.invoiceItemService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(invoiceItems) && invoiceItems.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			InvoiceItem invoiceItem = new InvoiceItem();
			this.invoiceItemService.save(invoiceItem);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<InvoiceItem> page = this.invoiceItemService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.invoiceItemService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.invoiceItemService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);
	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			InvoiceItem invoiceItem = new InvoiceItem();
			this.invoiceItemService.save(invoiceItem);
			id = invoiceItem.getId();
		}
		InvoiceItem e = this.invoiceItemService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			InvoiceItem invoiceItem = new InvoiceItem();
			this.invoiceItemService.save(invoiceItem);
			if (i < 5) {
				ids.add(invoiceItem.getId());
			}
		}
		List<InvoiceItem> invoiceItems = this.invoiceItemService.getAll(ids);
		Assert.isTrue(invoiceItems != null && invoiceItems.size() == 5);
	}
}
