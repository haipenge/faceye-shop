package com.faceye.test.component.platform.service;

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

import com.faceye.component.platform.entity.Platform;
import com.faceye.component.platform.service.PlatformService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Platform  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class PlatformServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private PlatformService platformService = null;
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
		Assert.isTrue(platformService != null);
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
		//this.platformService.removeAllInBatch();
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
		Platform entity = new Platform();
		this.platformService.save(entity);
		List<Platform> entites = this.platformService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Platform entity = new Platform();
		this.platformService.save(entity);
		List<Platform> entites = this.platformService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Platform entity = new Platform();
			this.platformService.save(entity);
		}
		List<Platform> entities = this.platformService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Platform entity = new Platform();
		this.platformService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Platform e = this.platformService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Platform entity = new Platform();
		this.platformService.save(entity);
		this.platformService.remove(entity);
		List<Platform> entities = this.platformService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Platform entity = new Platform();
			this.platformService.save(entity);
		}
		List<Platform> entities = this.platformService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.platformService.removeAllInBatch();
		entities = this.platformService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Platform entity = new Platform();
			this.platformService.save(entity);
		}
		this.platformService.removeAll();
		List<Platform> entities = this.platformService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Platform> entities = new ArrayList<Platform>();
		for (int i = 0; i < 5; i++) {
			Platform entity = new Platform();
			
			this.platformService.save(entity);
			entities.add(entity);
		}
		this.platformService.removeInBatch(entities);
		entities = this.platformService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Platform entity = new Platform();
			this.platformService.save(entity);
		}
		List<Platform> entities = this.platformService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Platform entity = new Platform();
			this.platformService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Platform> page = this.platformService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.platformService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.platformService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Platform entity = new Platform();
			this.platformService.save(entity);
			id = entity.getId();
		}
		Platform e = this.platformService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Platform entity = new Platform();
			this.platformService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Platform> entities = this.platformService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}
