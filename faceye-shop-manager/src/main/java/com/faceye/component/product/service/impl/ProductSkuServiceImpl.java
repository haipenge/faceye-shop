package com.faceye.component.product.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.inventory.entity.Inventory;
import com.faceye.component.inventory.service.InventoryService;
import com.faceye.component.product.entity.Product;
import com.faceye.component.product.entity.ProductSku;
import com.faceye.component.product.entity.SkuProperty;
import com.faceye.component.product.model.SkuInfo;
import com.faceye.component.product.repository.mongo.ProductSkuRepository;
import com.faceye.component.product.service.ProductSkuService;
import com.faceye.component.product.service.SkuPropertyService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
 
import com.querydsl.core.types.Predicate;

/**
 * 模块:产品->com.faceye.compoent.product.service.impl<br>
 * 说明:<br>
 * 实体:产品SKU->com.faceye.component.product.entity.entity.ProductSku 服务实现类<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:36<br>
 */
@Service
public class ProductSkuServiceImpl extends BaseMongoServiceImpl<ProductSku, Long, ProductSkuRepository> implements ProductSkuService {
	@Autowired
	private SkuPropertyService skuPropertyService = null;
	@Autowired
	private InventoryService inventoryService = null;

	@Autowired
	public ProductSkuServiceImpl(ProductSkuRepository dao) {
		super(dao);
	}

	/**
	 *数据分页查询
	 * @author haipenge <br>
	 * 联系:haipenge@gmail.com<br>
	 * 创建日期:2015-6-13 11:31:36<br>
	*/
	@Override
	public Page<ProductSku> getPage(Map<String, Object> searchParams, int page, int size)  {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<ProductSku> entityPath = resolver.createPath(entityClass);
		// PathBuilder<ProductSku> builder = new PathBuilder<ProductSku>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = new Sort(Direction.DESC, "id");
		Page<ProductSku> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<ProductSku>("id") {
			// })
			List<ProductSku> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<ProductSku>(items);

		}
		return res;
	}

	@Override
	public List<SkuInfo> getProductSkuInfos(Product product) {
		List<SkuInfo> skuInfos = new ArrayList<SkuInfo>(0);
		Map searchParams = new HashMap();
		searchParams.put("EQ|product.$id", product.getId());
		List<ProductSku> productSkus = this.getPage(searchParams, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(productSkus)) {
			for (ProductSku productSku : productSkus) {
				SkuInfo skuInfo = new SkuInfo();
				searchParams = new HashMap();
				searchParams.put("EQ|productSku.$id", productSku.getId());
				List<SkuProperty> skuProperties = this.skuPropertyService.getPage(searchParams, 1, 0).getContent();
				Inventory inventory=this.inventoryService.getInventoryByProductSkuId(productSku.getId());
				skuInfo.setProduct(product);
				skuInfo.setProductSku(productSku);
				skuInfo.setSkuProperties(skuProperties);
				skuInfo.setInventory(inventory);
				skuInfos.add(skuInfo);
			}
		}
		return skuInfos;
	}

}
/**@generate-service-source@**/
