package com.faceye.test.component.inventory.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.inventory.entity.InvoiceItem;
import com.faceye.component.inventory.repository.mongo.InvoiceItemRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Item Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2015-6-13 11:31:37<br>
 */
public class ItemRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private InvoiceItemRepository invoiceItemRepository = null;

	@Before
	public void before() throws Exception {
		//this.itemRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		InvoiceItem invoiceItem = new InvoiceItem();
		this.invoiceItemRepository.save(invoiceItem);
		Iterable<InvoiceItem> invoiceItems = this.invoiceItemRepository.findAll();
		Assert.isTrue(invoiceItems.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		InvoiceItem invoiceItem = new InvoiceItem();
		this.invoiceItemRepository.save(invoiceItem);
        this.invoiceItemRepository.delete(invoiceItem.getId());
        Iterable<InvoiceItem> invoiceItems = this.invoiceItemRepository.findAll();
		Assert.isTrue(!invoiceItems.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		InvoiceItem invoiceItem = new InvoiceItem();
		this.invoiceItemRepository.save(invoiceItem);
		invoiceItem=this.invoiceItemRepository.findOne(invoiceItem.getId());
		Assert.isTrue(invoiceItem!=null);
	}

	
}
