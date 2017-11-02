package com.faceye.test.component.inventory.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.inventory.entity.Invoice;
import com.faceye.component.inventory.repository.mongo.InvoiceRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Invoice Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2015-6-13 11:31:37<br>
 */
public class InvoiceRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private InvoiceRepository invoiceRepository = null;

	@Before
	public void before() throws Exception {
		//this.invoiceRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Invoice invoice = new Invoice();
		this.invoiceRepository.save(invoice);
		Iterable<Invoice> invoices = this.invoiceRepository.findAll();
		Assert.isTrue(invoices.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Invoice invoice = new Invoice();
		this.invoiceRepository.save(invoice);
        this.invoiceRepository.delete(invoice.getId());
        Iterable<Invoice> invoices = this.invoiceRepository.findAll();
		Assert.isTrue(!invoices.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Invoice invoice = new Invoice();
		this.invoiceRepository.save(invoice);
		invoice=this.invoiceRepository.findOne(invoice.getId());
		Assert.isTrue(invoice!=null);
	}

	
}
