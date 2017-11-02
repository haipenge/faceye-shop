package com.faceye.test.component.product.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.product.entity.Product;
import com.faceye.component.product.repository.mongo.ProductRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Product Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2015-6-13 11:31:35<br>
 */
public class ProductRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private ProductRepository productRepository = null;

	@Before
	public void before() throws Exception {
		//this.productRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Product product = new Product();
		this.productRepository.save(product);
		Iterable<Product> products = this.productRepository.findAll();
		Assert.isTrue(products.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Product product = new Product();
		this.productRepository.save(product);
        this.productRepository.delete(product.getId());
        Iterable<Product> products = this.productRepository.findAll();
		Assert.isTrue(!products.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Product product = new Product();
		this.productRepository.save(product);
		product=this.productRepository.findOne(product.getId());
		Assert.isTrue(product!=null);
	}

	
}
