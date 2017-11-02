package com.faceye.test.component.product.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.product.entity.DynamicPropertyValue;
import com.faceye.component.product.repository.mongo.DynamicPropertyValueRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * DynamicPropertyValue Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2015-6-16 18:57:42<br>
 */
public class DynamicPropertyValueRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private DynamicPropertyValueRepository dynamicPropertyValueRepository = null;

	@Before
	public void before() throws Exception {
		//this.dynamicPropertyValueRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		DynamicPropertyValue dynamicPropertyValue = new DynamicPropertyValue();
		this.dynamicPropertyValueRepository.save(dynamicPropertyValue);
		Iterable<DynamicPropertyValue> dynamicPropertyValues = this.dynamicPropertyValueRepository.findAll();
		Assert.isTrue(dynamicPropertyValues.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		DynamicPropertyValue dynamicPropertyValue = new DynamicPropertyValue();
		this.dynamicPropertyValueRepository.save(dynamicPropertyValue);
        this.dynamicPropertyValueRepository.delete(dynamicPropertyValue.getId());
        Iterable<DynamicPropertyValue> dynamicPropertyValues = this.dynamicPropertyValueRepository.findAll();
		Assert.isTrue(!dynamicPropertyValues.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		DynamicPropertyValue dynamicPropertyValue = new DynamicPropertyValue();
		this.dynamicPropertyValueRepository.save(dynamicPropertyValue);
		dynamicPropertyValue=this.dynamicPropertyValueRepository.findOne(dynamicPropertyValue.getId());
		Assert.isTrue(dynamicPropertyValue!=null);
	}

	
}
