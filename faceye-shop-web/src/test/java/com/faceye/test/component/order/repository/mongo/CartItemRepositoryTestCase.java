package com.faceye.test.component.order.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.order.entity.CartItem;
import com.faceye.component.order.repository.mongo.CartItemRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * CartItem DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class CartItemRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private CartItemRepository cartItemRepository = null;

	@Before
	public void before() throws Exception {
		//this.cartItemRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		CartItem entity = new CartItem();
		this.cartItemRepository.save(entity);
		Iterable<CartItem> entities = this.cartItemRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		CartItem entity = new CartItem();
		this.cartItemRepository.save(entity);
        this.cartItemRepository.deleteById(entity.getId());
        Iterable<CartItem> entities = this.cartItemRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		CartItem entity = new CartItem();
		this.cartItemRepository.save(entity);
		CartItem cartItem=this.cartItemRepository.findById(entity.getId()).get();
		Assert.isTrue(cartItem!=null);
	}

	
}
