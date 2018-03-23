package com.faceye.test.component.order.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.order.entity.Cart;
import com.faceye.component.order.repository.mongo.CartRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Cart DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class CartRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private CartRepository cartRepository = null;

	@Before
	public void before() throws Exception {
		//this.cartRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Cart entity = new Cart();
		this.cartRepository.save(entity);
		Iterable<Cart> entities = this.cartRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Cart entity = new Cart();
		this.cartRepository.save(entity);
        this.cartRepository.deleteById(entity.getId());
        Iterable<Cart> entities = this.cartRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Cart entity = new Cart();
		this.cartRepository.save(entity);
		Cart cart=this.cartRepository.findById(entity.getId()).get();
		Assert.isTrue(cart!=null);
	}

	
}
