package com.faceye.component.inventory.service.impl;

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
import com.faceye.component.inventory.repository.mongo.InventoryRepository;
import com.faceye.component.inventory.service.InventoryService;
import com.faceye.component.product.entity.SkuProperty;
import com.faceye.component.product.model.SkuInfo;
import com.faceye.component.product.service.SkuPropertyService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
 
import com.querydsl.core.types.Predicate;

/**
 * 模块:库存->com.faceye.compoent.inventory.service.impl<br>
 * 说明:<br>
 * 实体:库存明细->com.faceye.component.inventory.entity.entity.Inventory 服务实现类<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:37<br>
 */
@Service
public class InventoryServiceImpl extends BaseMongoServiceImpl<Inventory, Long, InventoryRepository> implements InventoryService {
	@Autowired
	private SkuPropertyService skuPropertyService = null;

	@Autowired
	public InventoryServiceImpl(InventoryRepository dao) {
		super(dao);
	}

	/**
	 *数据分页查询
	 * @author haipenge <br>
	 * 联系:haipenge@gmail.com<br>
	 * 创建日期:2015-6-13 11:31:37<br>
	*/
	@Override
	public Page<Inventory> getPage(Map<String, Object> searchParams, int page, int size)  {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Inventory> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Inventory> builder = new PathBuilder<Inventory>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = new Sort(Direction.DESC, "product.$id");
		sort.and(new Sort(Direction.ASC, "productSku.$id"));
		Page<Inventory> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Inventory>("id") {
			// })
			List<Inventory> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Inventory>(items);

		}
		return res;
	}

	@Override
	public Inventory getInventoryByProductSkuId(Long productSkuId) {
		return this.dao.getInventoryByProductSkuId(productSkuId);
	}

	@Override
	public List<SkuInfo> getSkuInfos(List<Inventory> inventories) {
		List<SkuInfo> skuInfos = new ArrayList<SkuInfo>(0);
		if (CollectionUtils.isNotEmpty(inventories)) {
			for (Inventory inventory : inventories) {
				Map searchParams = new HashMap();
				searchParams.put("EQ|productSku.$id", inventory.getProductSku().getId());
				List<SkuProperty> skuProperties = this.skuPropertyService.getPage(searchParams, 1, 0).getContent();
				SkuInfo skuInfo = new SkuInfo();
				skuInfo.setProduct(inventory.getProduct());
				skuInfo.setInventory(inventory);
				skuInfo.setProductSku(inventory.getProductSku());
				skuInfo.setSkuProperties(skuProperties);
				skuInfos.add(skuInfo);
			}
		}
		return skuInfos;
	}

}
/**@generate-service-source@**/
