package com.faceye.test.component.setting.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.setting.entity.Shop;
import com.faceye.component.setting.repository.mongo.ShopRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Shop Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2015-6-13 11:31:35<br>
 */
public class ShopRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private ShopRepository shopRepository = null;

	@Before
	public void before() throws Exception {
		//this.shopRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Shop shop = new Shop();
		this.shopRepository.save(shop);
		Iterable<Shop> shops = this.shopRepository.findAll();
		Assert.isTrue(shops.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Shop shop = new Shop();
		this.shopRepository.save(shop);
        this.shopRepository.deleteById(shop.getId());
        Iterable<Shop> shops = this.shopRepository.findAll();
		Assert.isTrue(!shops.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Shop shop = new Shop();
		this.shopRepository.save(shop);
		shop=this.shopRepository.findById(shop.getId());
		Assert.isTrue(shop!=null);
	}

	
}
