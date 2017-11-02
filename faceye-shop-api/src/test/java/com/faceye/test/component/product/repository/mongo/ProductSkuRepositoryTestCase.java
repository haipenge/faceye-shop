package com.faceye.test.component.product.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.product.entity.ProductSku;
import com.faceye.component.product.repository.mongo.ProductSkuRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * ProductSku Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2015-6-13 11:31:36<br>
 */
public class ProductSkuRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private ProductSkuRepository productSkuRepository = null;

	@Before
	public void before() throws Exception {
		//this.productSkuRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		ProductSku productSku = new ProductSku();
		this.productSkuRepository.save(productSku);
		Iterable<ProductSku> productSkus = this.productSkuRepository.findAll();
		Assert.isTrue(productSkus.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		ProductSku productSku = new ProductSku();
		this.productSkuRepository.save(productSku);
        this.productSkuRepository.delete(productSku.getId());
        Iterable<ProductSku> productSkus = this.productSkuRepository.findAll();
		Assert.isTrue(!productSkus.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		ProductSku productSku = new ProductSku();
		this.productSkuRepository.save(productSku);
		productSku=this.productSkuRepository.findOne(productSku.getId());
		Assert.isTrue(productSku!=null);
	}

	
}
