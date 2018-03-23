package com.faceye.test.component.lbs.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.lbs.entity.GeoLibrary;
import com.faceye.component.lbs.repository.mongo.GeoLibraryRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * GeoLibrary DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class GeoLibraryRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private GeoLibraryRepository geoLibraryRepository = null;

	@Before
	public void before() throws Exception {
		//this.geoLibraryRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		GeoLibrary entity = new GeoLibrary();
		this.geoLibraryRepository.save(entity);
		Iterable<GeoLibrary> entities = this.geoLibraryRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		GeoLibrary entity = new GeoLibrary();
		this.geoLibraryRepository.save(entity);
        this.geoLibraryRepository.deleteById(entity.getId());
        Iterable<GeoLibrary> entities = this.geoLibraryRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		GeoLibrary entity = new GeoLibrary();
		this.geoLibraryRepository.save(entity);
		GeoLibrary geoLibrary=this.geoLibraryRepository.findById(entity.getId()).get();
		Assert.isTrue(geoLibrary!=null);
	}

	
}
