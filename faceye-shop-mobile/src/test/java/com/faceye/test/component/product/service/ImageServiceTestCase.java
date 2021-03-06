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

import com.faceye.component.product.entity.Image;
import com.faceye.component.product.service.ImageService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Image  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class ImageServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private ImageService imageService = null;
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
		Assert.isTrue(imageService != null);
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
		//this.imageService.removeAllInBatch();
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
		Image entity = new Image();
		this.imageService.save(entity);
		List<Image> entites = this.imageService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Image entity = new Image();
		this.imageService.save(entity);
		List<Image> entites = this.imageService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Image entity = new Image();
			this.imageService.save(entity);
		}
		List<Image> entities = this.imageService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Image entity = new Image();
		this.imageService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Image e = this.imageService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Image entity = new Image();
		this.imageService.save(entity);
		this.imageService.remove(entity);
		List<Image> entities = this.imageService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Image entity = new Image();
			this.imageService.save(entity);
		}
		List<Image> entities = this.imageService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.imageService.removeAllInBatch();
		entities = this.imageService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Image entity = new Image();
			this.imageService.save(entity);
		}
		this.imageService.removeAll();
		List<Image> entities = this.imageService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Image> entities = new ArrayList<Image>();
		for (int i = 0; i < 5; i++) {
			Image entity = new Image();
			
			this.imageService.save(entity);
			entities.add(entity);
		}
		this.imageService.removeInBatch(entities);
		entities = this.imageService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Image entity = new Image();
			this.imageService.save(entity);
		}
		List<Image> entities = this.imageService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Image entity = new Image();
			this.imageService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Image> page = this.imageService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.imageService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.imageService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Image entity = new Image();
			this.imageService.save(entity);
			id = entity.getId();
		}
		Image e = this.imageService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Image entity = new Image();
			this.imageService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Image> entities = this.imageService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}
