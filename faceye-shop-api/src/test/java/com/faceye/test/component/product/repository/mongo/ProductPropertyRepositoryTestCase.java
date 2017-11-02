package com.faceye.test.component.product.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.product.entity.ProductProperty;
import com.faceye.component.product.repository.mongo.ProductPropertyRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * ProductProperty Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2015-6-13 11:31:36<br>
 */
public class ProductPropertyRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private ProductPropertyRepository productPropertyRepository = null;

	@Before
	public void before() throws Exception {
		//this.productPropertyRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		ProductProperty productProperty = new ProductProperty();
		this.productPropertyRepository.save(productProperty);
		Iterable<ProductProperty> productPropertys = this.productPropertyRepository.findAll();
		Assert.isTrue(productPropertys.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		ProductProperty productProperty = new ProductProperty();
		this.productPropertyRepository.save(productProperty);
        this.productPropertyRepository.delete(productProperty.getId());
        Iterable<ProductProperty> productPropertys = this.productPropertyRepository.findAll();
		Assert.isTrue(!productPropertys.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		ProductProperty productProperty = new ProductProperty();
		this.productPropertyRepository.save(productProperty);
		productProperty=this.productPropertyRepository.findOne(productProperty.getId());
		Assert.isTrue(productProperty!=null);
	}

	
}
