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

import com.faceye.component.product.entity.DynamicProperty;
import com.faceye.component.product.service.DynamicPropertyService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * DynamicProperty  服务层测试用例
 * 
 * @author haipenge 
 * 联系人:haipenge@gmail.com 
 * 创建日期:2015-6-13 11:31:35
 */
public class DynamicPropertyServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private DynamicPropertyService dynamicPropertyService = null;
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
		Assert.isTrue(dynamicPropertyService != null);
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
		//this.dynamicPropertyService.removeAllInBatch();
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
		DynamicProperty dynamicProperty = new DynamicProperty();
		this.dynamicPropertyService.save(dynamicProperty);
		List<DynamicProperty> dynamicPropertys = this.dynamicPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(dynamicPropertys));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		DynamicProperty dynamicProperty = new DynamicProperty();
		this.dynamicPropertyService.save(dynamicProperty);
		List<DynamicProperty> dynamicPropertys = this.dynamicPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(dynamicPropertys));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			DynamicProperty dynamicProperty = new DynamicProperty();
			this.dynamicPropertyService.save(dynamicProperty);
		}
		List<DynamicProperty> dynamicPropertys = this.dynamicPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(dynamicPropertys) && dynamicPropertys.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		DynamicProperty dynamicProperty = new DynamicProperty();
		this.dynamicPropertyService.save(dynamicProperty);
		logger.debug(">>Entity id is:" + dynamicProperty.getId());
		DynamicProperty e = this.dynamicPropertyService.get(dynamicProperty.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		DynamicProperty dynamicProperty = new DynamicProperty();
		this.dynamicPropertyService.save(dynamicProperty);
		this.dynamicPropertyService.remove(dynamicProperty);
		List<DynamicProperty> dynamicPropertys = this.dynamicPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(dynamicPropertys));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			DynamicProperty dynamicProperty = new DynamicProperty();
			this.dynamicPropertyService.save(dynamicProperty);
		}
		List<DynamicProperty> dynamicPropertys = this.dynamicPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(dynamicPropertys) && dynamicPropertys.size() == 5);
		this.dynamicPropertyService.removeAllInBatch();
		dynamicPropertys = this.dynamicPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(dynamicPropertys));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			DynamicProperty dynamicProperty = new DynamicProperty();
			this.dynamicPropertyService.save(dynamicProperty);
		}
		this.dynamicPropertyService.removeAll();
		List<DynamicProperty> dynamicPropertys = this.dynamicPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(dynamicPropertys));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<DynamicProperty> dynamicPropertys = new ArrayList<DynamicProperty>();
		for (int i = 0; i < 5; i++) {
			DynamicProperty dynamicProperty = new DynamicProperty();
			
			this.dynamicPropertyService.save(dynamicProperty);
			dynamicPropertys.add(dynamicProperty);
		}
		this.dynamicPropertyService.removeInBatch(dynamicPropertys);
		dynamicPropertys = this.dynamicPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(dynamicPropertys));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			DynamicProperty dynamicProperty = new DynamicProperty();
			this.dynamicPropertyService.save(dynamicProperty);
		}
		List<DynamicProperty> dynamicPropertys = this.dynamicPropertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(dynamicPropertys) && dynamicPropertys.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			DynamicProperty dynamicProperty = new DynamicProperty();
			this.dynamicPropertyService.save(dynamicProperty);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<DynamicProperty> page = this.dynamicPropertyService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.dynamicPropertyService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.dynamicPropertyService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);
	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			DynamicProperty dynamicProperty = new DynamicProperty();
			this.dynamicPropertyService.save(dynamicProperty);
			id = dynamicProperty.getId();
		}
		DynamicProperty e = this.dynamicPropertyService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			DynamicProperty dynamicProperty = new DynamicProperty();
			this.dynamicPropertyService.save(dynamicProperty);
			if (i < 5) {
				ids.add(dynamicProperty.getId());
			}
		}
		List<DynamicProperty> dynamicPropertys = this.dynamicPropertyService.getAll(ids);
		Assert.isTrue(dynamicPropertys != null && dynamicPropertys.size() == 5);
	}
}
