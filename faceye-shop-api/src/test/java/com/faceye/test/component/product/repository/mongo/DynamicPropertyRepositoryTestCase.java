package com.faceye.test.component.product.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.product.entity.DynamicProperty;
import com.faceye.component.product.repository.mongo.DynamicPropertyRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * DynamicProperty Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2015-6-13 11:31:35<br>
 */
public class DynamicPropertyRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private DynamicPropertyRepository dynamicPropertyRepository = null;

	@Before
	public void before() throws Exception {
		//this.dynamicPropertyRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		DynamicProperty dynamicProperty = new DynamicProperty();
		this.dynamicPropertyRepository.save(dynamicProperty);
		Iterable<DynamicProperty> dynamicPropertys = this.dynamicPropertyRepository.findAll();
		Assert.isTrue(dynamicPropertys.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		DynamicProperty dynamicProperty = new DynamicProperty();
		this.dynamicPropertyRepository.save(dynamicProperty);
        this.dynamicPropertyRepository.delete(dynamicProperty.getId());
        Iterable<DynamicProperty> dynamicPropertys = this.dynamicPropertyRepository.findAll();
		Assert.isTrue(!dynamicPropertys.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		DynamicProperty dynamicProperty = new DynamicProperty();
		this.dynamicPropertyRepository.save(dynamicProperty);
		dynamicProperty=this.dynamicPropertyRepository.findOne(dynamicProperty.getId());
		Assert.isTrue(dynamicProperty!=null);
	}

	
}
