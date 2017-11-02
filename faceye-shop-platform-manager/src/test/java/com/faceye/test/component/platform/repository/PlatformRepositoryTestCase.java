package com.faceye.test.component.platform.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.platform.entity.Platform;
import com.faceye.component.platform.repository.mongo.PlatformRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Platform DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class PlatformRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private PlatformRepository platformRepository = null;

	@Before
	public void before() throws Exception {
		//this.platformRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Platform entity = new Platform();
		this.platformRepository.save(entity);
		Iterable<Platform> entities = this.platformRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Platform entity = new Platform();
		this.platformRepository.save(entity);
        this.platformRepository.delete(entity.getId());
        Iterable<Platform> entities = this.platformRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Platform entity = new Platform();
		this.platformRepository.save(entity);
		Platform platform=this.platformRepository.findOne(entity.getId());
		Assert.isTrue(platform!=null);
	}

	
}
