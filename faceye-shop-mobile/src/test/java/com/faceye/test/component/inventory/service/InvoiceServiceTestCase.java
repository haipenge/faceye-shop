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

import com.faceye.component.inventory.entity.Invoice;
import com.faceye.component.inventory.service.InvoiceService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Invoice  服务层测试用例
 * 
 * @author haipenge 
 * 联系人:haipenge@gmail.com 
 * 创建日期:2015-6-16 18:57:43
 */
public class InvoiceServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private InvoiceService invoiceService = null;
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
		Assert.isTrue(invoiceService != null);
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
		//this.invoiceService.removeAllInBatch();
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
		Invoice invoice = new Invoice();
		this.invoiceService.save(invoice);
		List<Invoice> invoices = this.invoiceService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(invoices));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Invoice invoice = new Invoice();
		this.invoiceService.save(invoice);
		List<Invoice> invoices = this.invoiceService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(invoices));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Invoice invoice = new Invoice();
			this.invoiceService.save(invoice);
		}
		List<Invoice> invoices = this.invoiceService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(invoices) && invoices.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Invoice invoice = new Invoice();
		this.invoiceService.save(invoice);
		logger.debug(">>Entity id is:" + invoice.getId());
		Invoice e = this.invoiceService.get(invoice.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Invoice invoice = new Invoice();
		this.invoiceService.save(invoice);
		this.invoiceService.remove(invoice);
		List<Invoice> invoices = this.invoiceService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(invoices));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Invoice invoice = new Invoice();
			this.invoiceService.save(invoice);
		}
		List<Invoice> invoices = this.invoiceService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(invoices) && invoices.size() == 5);
		this.invoiceService.removeAllInBatch();
		invoices = this.invoiceService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(invoices));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Invoice invoice = new Invoice();
			this.invoiceService.save(invoice);
		}
		this.invoiceService.removeAll();
		List<Invoice> invoices = this.invoiceService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(invoices));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Invoice> invoices = new ArrayList<Invoice>();
		for (int i = 0; i < 5; i++) {
			Invoice invoice = new Invoice();
			
			this.invoiceService.save(invoice);
			invoices.add(invoice);
		}
		this.invoiceService.removeInBatch(invoices);
		invoices = this.invoiceService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(invoices));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Invoice invoice = new Invoice();
			this.invoiceService.save(invoice);
		}
		List<Invoice> invoices = this.invoiceService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(invoices) && invoices.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Invoice invoice = new Invoice();
			this.invoiceService.save(invoice);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Invoice> page = this.invoiceService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.invoiceService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.invoiceService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);
	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Invoice invoice = new Invoice();
			this.invoiceService.save(invoice);
			id = invoice.getId();
		}
		Invoice e = this.invoiceService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Invoice invoice = new Invoice();
			this.invoiceService.save(invoice);
			if (i < 5) {
				ids.add(invoice.getId());
			}
		}
		List<Invoice> invoices = this.invoiceService.getAll(ids);
		Assert.isTrue(invoices != null && invoices.size() == 5);
	}
}
