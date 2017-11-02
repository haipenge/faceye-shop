package com.faceye.test.component.product.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.product.entity.Category;
import com.faceye.component.product.repository.mongo.CategoryRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Category Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2015-6-16 18:57:41<br>
 */
public class CategoryRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private CategoryRepository categoryRepository = null;

	@Before
	public void before() throws Exception {
		//this.categoryRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Category category = new Category();
		this.categoryRepository.save(category);
		Iterable<Category> categorys = this.categoryRepository.findAll();
		Assert.isTrue(categorys.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Category category = new Category();
		this.categoryRepository.save(category);
        this.categoryRepository.delete(category.getId());
        Iterable<Category> categorys = this.categoryRepository.findAll();
		Assert.isTrue(!categorys.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Category category = new Category();
		this.categoryRepository.save(category);
		category=this.categoryRepository.findOne(category.getId());
		Assert.isTrue(category!=null);
	}

	
}
