package com.faceye.test.component.lbs.service;

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

import com.faceye.component.lbs.entity.GeoLibrary;
import com.faceye.component.lbs.service.GeoLibraryService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * GeoLibrary  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class GeoLibraryServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private GeoLibraryService geoLibraryService = null;
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
		Assert.isTrue(geoLibraryService != null);
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
		//this.geoLibraryService.removeAllInBatch();
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
		GeoLibrary entity = new GeoLibrary();
		this.geoLibraryService.save(entity);
		List<GeoLibrary> entites = this.geoLibraryService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		GeoLibrary entity = new GeoLibrary();
		this.geoLibraryService.save(entity);
		List<GeoLibrary> entites = this.geoLibraryService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			GeoLibrary entity = new GeoLibrary();
			this.geoLibraryService.save(entity);
		}
		List<GeoLibrary> entities = this.geoLibraryService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		GeoLibrary entity = new GeoLibrary();
		this.geoLibraryService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		GeoLibrary e = this.geoLibraryService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		GeoLibrary entity = new GeoLibrary();
		this.geoLibraryService.save(entity);
		this.geoLibraryService.remove(entity);
		List<GeoLibrary> entities = this.geoLibraryService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			GeoLibrary entity = new GeoLibrary();
			this.geoLibraryService.save(entity);
		}
		List<GeoLibrary> entities = this.geoLibraryService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.geoLibraryService.removeAllInBatch();
		entities = this.geoLibraryService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			GeoLibrary entity = new GeoLibrary();
			this.geoLibraryService.save(entity);
		}
		this.geoLibraryService.removeAll();
		List<GeoLibrary> entities = this.geoLibraryService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<GeoLibrary> entities = new ArrayList<GeoLibrary>();
		for (int i = 0; i < 5; i++) {
			GeoLibrary entity = new GeoLibrary();
			
			this.geoLibraryService.save(entity);
			entities.add(entity);
		}
		this.geoLibraryService.removeInBatch(entities);
		entities = this.geoLibraryService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			GeoLibrary entity = new GeoLibrary();
			this.geoLibraryService.save(entity);
		}
		List<GeoLibrary> entities = this.geoLibraryService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			GeoLibrary entity = new GeoLibrary();
			this.geoLibraryService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<GeoLibrary> page = this.geoLibraryService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.geoLibraryService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.geoLibraryService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			GeoLibrary entity = new GeoLibrary();
			this.geoLibraryService.save(entity);
			id = entity.getId();
		}
		GeoLibrary e = this.geoLibraryService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			GeoLibrary entity = new GeoLibrary();
			this.geoLibraryService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<GeoLibrary> entities = this.geoLibraryService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
	
	@Test
	public void testInitGeoLibrary() throws Exception{
		this.geoLibraryService.initGeoLibrary();
		Page<GeoLibrary> geoLibraries=this.geoLibraryService.getPage(null, 1, 20);
		Assert.isTrue(geoLibraries!=null && CollectionUtils.isNotEmpty(geoLibraries.getContent()));
	}
}
