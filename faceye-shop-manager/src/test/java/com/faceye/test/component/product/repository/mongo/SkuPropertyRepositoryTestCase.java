package com.faceye.test.component.product.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.product.entity.SkuProperty;
import com.faceye.component.product.repository.mongo.SkuPropertyRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * SkuProperty Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2015-6-13 11:31:36<br>
 */
public class SkuPropertyRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private SkuPropertyRepository skuPropertyRepository = null;

	@Before
	public void before() throws Exception {
		//this.skuPropertyRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		SkuProperty skuProperty = new SkuProperty();
		this.skuPropertyRepository.save(skuProperty);
		Iterable<SkuProperty> skuPropertys = this.skuPropertyRepository.findAll();
		Assert.isTrue(skuPropertys.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		SkuProperty skuProperty = new SkuProperty();
		this.skuPropertyRepository.save(skuProperty);
        this.skuPropertyRepository.deleteById(skuProperty.getId());
        Iterable<SkuProperty> skuPropertys = this.skuPropertyRepository.findAll();
		Assert.isTrue(!skuPropertys.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		SkuProperty skuProperty = new SkuProperty();
		this.skuPropertyRepository.save(skuProperty);
		skuProperty=this.skuPropertyRepository.findById(skuProperty.getId());
		Assert.isTrue(skuProperty!=null);
	}

	
}
