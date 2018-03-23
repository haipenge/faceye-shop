package com.faceye.test.component.customer.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.customer.entity.Address;
import com.faceye.component.customer.repository.mongo.AddressRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Address Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2015-6-16 18:57:43<br>
 */
public class AddressRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private AddressRepository addressRepository = null;

	@Before
	public void before() throws Exception {
		//this.addressRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Address address = new Address();
		this.addressRepository.save(address);
		Iterable<Address> addresss = this.addressRepository.findAll();
		Assert.isTrue(addresss.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Address address = new Address();
		this.addressRepository.save(address);
		this.addressRepository.deleteById(address.getId());
        Iterable<Address> addresss = this.addressRepository.findAll();
		Assert.isTrue(!addresss.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Address address = new Address();
		this.addressRepository.save(address);
		address=this.addressRepository.findById(address.getId()).get();
		Assert.isTrue(address!=null);
	}

	
}
