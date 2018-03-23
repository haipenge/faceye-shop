package com.faceye.test.component.order.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.order.entity.Order;
import com.faceye.component.order.repository.mongo.OrderRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Order Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2015-6-16 18:57:42<br>
 */
public class OrderRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private OrderRepository orderRepository = null;

	@Before
	public void before() throws Exception {
		//this.orderRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Order order = new Order();
		this.orderRepository.save(order);
		Iterable<Order> orders = this.orderRepository.findAll();
		Assert.isTrue(orders.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Order order = new Order();
		this.orderRepository.save(order);
        this.orderRepository.deleteById(order.getId());
        Iterable<Order> orders = this.orderRepository.findAll();
		Assert.isTrue(!orders.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Order order = new Order();
		this.orderRepository.save(order);
		order=this.orderRepository.findById(order.getId()).get();
		Assert.isTrue(order!=null);
	}

	
}
