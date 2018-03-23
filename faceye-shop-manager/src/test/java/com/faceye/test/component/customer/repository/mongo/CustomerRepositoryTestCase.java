package com.faceye.test.component.customer.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.customer.entity.Customer;
import com.faceye.component.customer.repository.mongo.CustomerRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Customer Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2015-6-13 11:31:36<br>
 */
public class CustomerRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private CustomerRepository customerRepository = null;

	@Before
	public void before() throws Exception {
		//this.customerRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Customer customer = new Customer();
		this.customerRepository.save(customer);
		Iterable<Customer> customers = this.customerRepository.findAll();
		Assert.isTrue(customers.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Customer customer = new Customer();
		this.customerRepository.save(customer);
        this.customerRepository.deleteById(customer.getId());
        Iterable<Customer> customers = this.customerRepository.findAll();
		Assert.isTrue(!customers.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Customer customer = new Customer();
		this.customerRepository.save(customer);
		customer=this.customerRepository.findById(customer.getId());
		Assert.isTrue(customer!=null);
	}

	
}
