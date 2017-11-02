package com.faceye.component.product.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.faceye.component.product.model.SkuSelect;
import com.faceye.component.product.model.SkuSelectBuilder;
import com.faceye.component.product.repository.mongo.ProductSkuRepository;
import com.faceye.component.product.service.DynamicPropertyService;
import com.faceye.component.product.service.DynamicPropertyValueService;
import com.faceye.component.product.service.ProductService;
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
 * 创建日期:2015-6-16 18:57:42<br>
 */
@Service
public class ProductSkuServiceImpl extends BaseMongoServiceImpl<ProductSku, Long, ProductSkuRepository> implements ProductSkuService {

	@Autowired
	private SkuPropertyService skuPropertyService;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private DynamicPropertyService dynamicPropertyService = null;
	@Autowired
	private DynamicPropertyValueService dynamicPropertyValueService = null;
	@Autowired
	private ProductService productService=null;

	@Autowired
	public ProductSkuServiceImpl(ProductSkuRepository dao) {
		super(dao);
	}

	/**
	 *数据分页查询
	 * @author haipenge <br>
	 * 联系:haipenge@gmail.com<br>
	 * 创建日期:2015-6-16 18:57:42<br>
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
				SkuInfo skuInfo = this.getSkuInfo(productSku);
				skuInfos.add(skuInfo);
			}
		}
		return skuInfos;
	}
	
	@Override
	public SkuInfo getSkuInfo(ProductSku productSku) {
		SkuInfo skuInfo = new SkuInfo();
		Map searchParams = new HashMap();
		searchParams.put("EQ|productSku.$id", productSku.getId());
		List<SkuProperty> skuProperties = this.skuPropertyService.getPage(searchParams, 1, 0).getContent();
		Inventory inventory = this.inventoryService.getInventoryByProductSkuId(productSku.getId());
		skuInfo.setProduct(productSku.getProduct());
		skuInfo.setProductSku(productSku);
		skuInfo.setSkuProperties(skuProperties);
		skuInfo.setInventory(inventory);
		return skuInfo;
	}
	
	@Override
	public SkuInfo getSkuInfo(Long productId, String dynamicPropertyAndValueIds) {
		SkuInfo skuInfo=new SkuInfo();
		Product product=this.productService.get(productId);
		ProductSku productSku=this.getProductSkuByDynamicPropertyAndValueIds(product, dynamicPropertyAndValueIds);
		Map searchParams = new HashMap();
		searchParams.put("EQ|productSku.$id", productSku.getId());
		List<SkuProperty> skuProperties = this.skuPropertyService.getPage(searchParams, 1, 0).getContent();
		Inventory inventory = this.inventoryService.getInventoryByProductSkuId(productSku.getId());
		skuInfo.setProduct(product);
		skuInfo.setProductSku(productSku);
		skuInfo.setSkuProperties(skuProperties);
		skuInfo.setInventory(inventory);
		return skuInfo;
	}

	/**
	 * 用于产品明细页Sku的选择
	 */
	@Override
	public List<SkuSelect> getSkuSelects(Product product) {
		List<SkuSelect> skuSelects = new ArrayList<SkuSelect>(0);
		Map searchParams = new HashMap();
		searchParams.put("EQ|product.$id", product.getId());
		List<ProductSku> productSkus = this.getPage(searchParams, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(productSkus)) {
			for (ProductSku productSku : productSkus) {
				searchParams = new HashMap();
				searchParams.put("EQ|productSku.$id", productSku.getId());
				List<SkuProperty> skuProperties = this.skuPropertyService.getPage(searchParams, 1, 0).getContent();
				SkuSelectBuilder.build(product, skuSelects, skuProperties);
			}
		}

		// List<DynamicProperty> skuDynamicProperties = this.dynamicPropertyService.getDynamicPropertiesByProduct(product, true);
		// if (CollectionUtils.isNotEmpty(skuDynamicProperties)) {
		// for (DynamicProperty dynamicProperty : skuDynamicProperties) {
		// SkuSelect skuSelect = new SkuSelect();
		// Map searchParams = new HashMap();
		// searchParams.put("EQ|dynamicProperty.$id", dynamicProperty.getId());
		// List<DynamicPropertyValue> dynamicPropertyValues = this.dynamicPropertyValueService.getPage(searchParams, 1, 0)
		// .getContent();
		// skuSelect.setProduct(product);
		// skuSelect.setDynamicProperty(dynamicProperty);
		// skuSelect.setDynamicPropertyValues(dynamicPropertyValues);
		// skuSelects.add(skuSelect);
		// }
		// }
		return skuSelects;
	}

	@Override
	public ProductSku getProductSkuByDynamicPropertyAndValueIds(Product product, String dynamicPropertyAndValueIds) {
		ProductSku productSku = null;
		Map searchParams = new HashMap();
		searchParams.put("EQ|product.$id", product.getId());
		List<ProductSku> productSkus = this.getPage(searchParams, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(productSkus)) {
			if (StringUtils.isEmpty(dynamicPropertyAndValueIds)) {
				productSku = productSkus.get(0);
			} else {
				String[] dynamicPropertyIds = StringUtils.split(dynamicPropertyAndValueIds, "|");
				for (ProductSku sku : productSkus) {
					boolean isFind = false;
					Map params = new HashMap();
					// params.put("EQ|product.$id", product.getId());
					params.put("EQ|productSku.$id", sku.getId());
					int totalSkuPropertiesCount = this.skuPropertyService.getPage(params, 1, 0).getContent().size();
					int count = 0;
					for (String ids : dynamicPropertyIds) {
						isFind = false;
						String id[] = StringUtils.split(ids, "-");
						params.put("EQ|dynamicProperty.$id", Long.parseLong(id[0]));
						params.put("EQ|dynamicPropertyValue.$id", Long.parseLong(id[1]));
						List<SkuProperty> skuProperties = this.skuPropertyService.getPage(params, 1, 0).getContent();
						if (CollectionUtils.isNotEmpty(skuProperties)) {
							isFind = true;
							count++;
							// break;
						}
						if (!isFind) {
							break;
						}
					}

					if (isFind && count == totalSkuPropertiesCount) {
						productSku = sku;
						break;
					}
				}
			}
		}
		return productSku;
	}

	

	
}
/**@generate-service-source@**/
