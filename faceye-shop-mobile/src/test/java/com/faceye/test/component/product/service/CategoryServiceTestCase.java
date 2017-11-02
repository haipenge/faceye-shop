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

import com.faceye.component.product.entity.Category;
import com.faceye.component.product.service.CategoryService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Category  服务层测试用例
 * 
 * @author haipenge 
 * 联系人:haipenge@gmail.com 
 * 创建日期:2015-6-16 18:57:41
 */
public class CategoryServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private CategoryService categoryService = null;
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
		Assert.isTrue(categoryService != null);
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
		//this.categoryService.removeAllInBatch();
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
		Category category = new Category();
		this.categoryService.save(category);
		List<Category> categorys = this.categoryService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(categorys));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Category category = new Category();
		this.categoryService.save(category);
		List<Category> categorys = this.categoryService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(categorys));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Category category = new Category();
			this.categoryService.save(category);
		}
		List<Category> categorys = this.categoryService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(categorys) && categorys.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Category category = new Category();
		this.categoryService.save(category);
		logger.debug(">>Entity id is:" + category.getId());
		Category e = this.categoryService.get(category.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Category category = new Category();
		this.categoryService.save(category);
		this.categoryService.remove(category);
		List<Category> categorys = this.categoryService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(categorys));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Category category = new Category();
			this.categoryService.save(category);
		}
		List<Category> categorys = this.categoryService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(categorys) && categorys.size() == 5);
		this.categoryService.removeAllInBatch();
		categorys = this.categoryService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(categorys));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Category category = new Category();
			this.categoryService.save(category);
		}
		this.categoryService.removeAll();
		List<Category> categorys = this.categoryService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(categorys));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Category> categorys = new ArrayList<Category>();
		for (int i = 0; i < 5; i++) {
			Category category = new Category();
			
			this.categoryService.save(category);
			categorys.add(category);
		}
		this.categoryService.removeInBatch(categorys);
		categorys = this.categoryService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(categorys));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Category category = new Category();
			this.categoryService.save(category);
		}
		List<Category> categorys = this.categoryService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(categorys) && categorys.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Category category = new Category();
			this.categoryService.save(category);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Category> page = this.categoryService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.categoryService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.categoryService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);
	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Category category = new Category();
			this.categoryService.save(category);
			id = category.getId();
		}
		Category e = this.categoryService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Category category = new Category();
			this.categoryService.save(category);
			if (i < 5) {
				ids.add(category.getId());
			}
		}
		List<Category> categorys = this.categoryService.getAll(ids);
		Assert.isTrue(categorys != null && categorys.size() == 5);
	}
}
