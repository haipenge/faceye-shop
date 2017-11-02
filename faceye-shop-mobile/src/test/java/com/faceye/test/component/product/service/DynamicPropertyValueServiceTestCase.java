package com.faceye.test.component.product.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import com.faceye.component.product.entity.DynamicPropertyValue;
import com.faceye.component.product.service.DynamicPropertyValueService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * DynamicPropertyValue  服务层测试用例
 * 
 * @author haipenge 
 * 联系人:haipenge@gmail.com 
 * 创建日期:2015-6-16 18:57:42
 */
public class DynamicPropertyValueServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private DynamicPropertyValueService dynamicPropertyValueService = null;
	/**
	 * 初始化
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Before
	public void set() throws Exception {
		Assert.isTrue(dynamicPropertyValueService != null);
	}

	/**
	 * 清理
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@After
	public void after() throws Exception {
		//this.dynamicPropertyValueService.removeAllInBatch();
	}

	/**
	 *  保存测试
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Test
	public void testSave() throws Exception {
		DynamicPropertyValue dynamicPropertyValue = new DynamicPropertyValue();
		this.dynamicPropertyValueService.save(dynamicPropertyValue);
		List<DynamicPropertyValue> dynamicPropertyValues = this.dynamicPropertyValueService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(dynamicPropertyValues));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		DynamicPropertyValue dynamicPropertyValue = new DynamicPropertyValue();
		this.dynamicPropertyValueService.save(dynamicPropertyValue);
		List<DynamicPropertyValue> dynamicPropertyValues = this.dynamicPropertyValueService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(dynamicPropertyValues));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			DynamicPropertyValue dynamicPropertyValue = new DynamicPropertyValue();
			this.dynamicPropertyValueService.save(dynamicPropertyValue);
		}
		List<DynamicPropertyValue> dynamicPropertyValues = this.dynamicPropertyValueService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(dynamicPropertyValues) && dynamicPropertyValues.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		DynamicPropertyValue dynamicPropertyValue = new DynamicPropertyValue();
		this.dynamicPropertyValueService.save(dynamicPropertyValue);
		logger.debug(">>Entity id is:" + dynamicPropertyValue.getId());
		DynamicPropertyValue e = this.dynamicPropertyValueService.get(dynamicPropertyValue.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		DynamicPropertyValue dynamicPropertyValue = new DynamicPropertyValue();
		this.dynamicPropertyValueService.save(dynamicPropertyValue);
		this.dynamicPropertyValueService.remove(dynamicPropertyValue);
		List<DynamicPropertyValue> dynamicPropertyValues = this.dynamicPropertyValueService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(dynamicPropertyValues));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			DynamicPropertyValue dynamicPropertyValue = new DynamicPropertyValue();
			this.dynamicPropertyValueService.save(dynamicPropertyValue);
		}
		List<DynamicPropertyValue> dynamicPropertyValues = this.dynamicPropertyValueService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(dynamicPropertyValues) && dynamicPropertyValues.size() == 5);
		this.dynamicPropertyValueService.removeAllInBatch();
		dynamicPropertyValues = this.dynamicPropertyValueService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(dynamicPropertyValues));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			DynamicPropertyValue dynamicPropertyValue = new DynamicPropertyValue();
			this.dynamicPropertyValueService.save(dynamicPropertyValue);
		}
		this.dynamicPropertyValueService.removeAll();
		List<DynamicPropertyValue> dynamicPropertyValues = this.dynamicPropertyValueService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(dynamicPropertyValues));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<DynamicPropertyValue> dynamicPropertyValues = new ArrayList<DynamicPropertyValue>();
		for (int i = 0; i < 5; i++) {
			DynamicPropertyValue dynamicPropertyValue = new DynamicPropertyValue();
			
			this.dynamicPropertyValueService.save(dynamicPropertyValue);
			dynamicPropertyValues.add(dynamicPropertyValue);
		}
		this.dynamicPropertyValueService.removeInBatch(dynamicPropertyValues);
		dynamicPropertyValues = this.dynamicPropertyValueService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(dynamicPropertyValues));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			DynamicPropertyValue dynamicPropertyValue = new DynamicPropertyValue();
			this.dynamicPropertyValueService.save(dynamicPropertyValue);
		}
		List<DynamicPropertyValue> dynamicPropertyValues = this.dynamicPropertyValueService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(dynamicPropertyValues) && dynamicPropertyValues.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			DynamicPropertyValue dynamicPropertyValue = new DynamicPropertyValue();
			this.dynamicPropertyValueService.save(dynamicPropertyValue);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<DynamicPropertyValue> page = this.dynamicPropertyValueService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.dynamicPropertyValueService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.dynamicPropertyValueService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);
	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			DynamicPropertyValue dynamicPropertyValue = new DynamicPropertyValue();
			this.dynamicPropertyValueService.save(dynamicPropertyValue);
			id = dynamicPropertyValue.getId();
		}
		DynamicPropertyValue e = this.dynamicPropertyValueService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			DynamicPropertyValue dynamicPropertyValue = new DynamicPropertyValue();
			this.dynamicPropertyValueService.save(dynamicPropertyValue);
			if (i < 5) {
				ids.add(dynamicPropertyValue.getId());
			}
		}
		List<DynamicPropertyValue> dynamicPropertyValues = this.dynamicPropertyValueService.getAll(ids);
		Assert.isTrue(dynamicPropertyValues != null && dynamicPropertyValues.size() == 5);
	}
}
