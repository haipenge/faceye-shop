package com.faceye.component.product.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
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
import com.faceye.component.inventory.entity.InvoiceItem;
import com.faceye.component.inventory.service.InventoryService;
import com.faceye.component.inventory.service.InvoiceItemService;
import com.faceye.component.order.entity.Item;
import com.faceye.component.order.service.ItemService;
import com.faceye.component.product.entity.Category;
import com.faceye.component.product.entity.DynamicProperty;
import com.faceye.component.product.entity.DynamicPropertyValue;
import com.faceye.component.product.entity.Image;
import com.faceye.component.product.entity.Product;
import com.faceye.component.product.entity.ProductProperty;
import com.faceye.component.product.entity.ProductSku;
import com.faceye.component.product.entity.SkuProperty;
import com.faceye.component.product.model.ProductInfo;
import com.faceye.component.product.model.ProductPropertyInfo;
import com.faceye.component.product.repository.mongo.ProductRepository;
import com.faceye.component.product.service.CategoryService;
import com.faceye.component.product.service.DynamicPropertyService;
import com.faceye.component.product.service.DynamicPropertyValueService;
import com.faceye.component.product.service.ImageService;
import com.faceye.component.product.service.ProductPropertyService;
import com.faceye.component.product.service.ProductService;
import com.faceye.component.product.service.ProductSkuService;
import com.faceye.component.product.service.SkuPropertyService;
import com.faceye.component.setting.entity.Shop;
import com.faceye.component.setting.service.ShopService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.DateUtil;
import com.faceye.feature.util.RandUtil;
import com.querydsl.core.types.Predicate;

/**
 * 模块:产品->com.faceye.compoent.product.service.impl<br>
 * 说明:<br>
 * 实体:产品->com.faceye.component.product.entity.entity.Product 服务实现类<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:35<br>
 */
@Service
public class ProductServiceImpl extends BaseMongoServiceImpl<Product, Long, ProductRepository> implements ProductService {
	@Autowired
	private CategoryService categoryService = null;
	@Autowired
	private ShopService shopService = null;
	@Autowired
	private ProductPropertyService productPropertyService = null;
	@Autowired
	private DynamicPropertyService dynamicPropertyService = null;
	@Autowired
	private DynamicPropertyValueService dynamicPropertyValueService = null;
	@Autowired
	private ProductSkuService productSkuService = null;
	@Autowired
	private SkuPropertyService skuPropertyService = null;
	@Autowired
	private InventoryService inventoryService = null;

	@Autowired
	private ItemService itemService = null;
	@Autowired
	private InvoiceItemService invoiceItemService = null;
	@Autowired
	private ImageService imageService = null;

	@Autowired
	public ProductServiceImpl(ProductRepository dao) {
		super(dao);
	}

	/**
	 *数据分页查询
	 * @author haipenge <br>
	 * 联系:haipenge@gmail.com<br>
	 * 创建日期:2015-6-13 11:31:35<br>
	*/
	@Override
	public Page<Product> getPage(Map<String, Object> searchParams, int page, int size) {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Product> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Product> builder = new PathBuilder<Product>(entityPath.getType(), entityPath.getMetadata());
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
		Page<Product> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Product>("id") {
			// })
			List<Product> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Product>(items);

		}
		return res;
	}

	@Override
	public void remove(Product product) {
		// 删除产品前，先删除产品自定义属性
		Map searchParams = new HashMap();
		searchParams.put("EQ|product.$id", product.getId());
		List<ProductProperty> productProperties = this.productPropertyService.getPage(searchParams, 1, 0).getContent();
		this.productPropertyService.removeInBatch(productProperties);
		List<Image> images = this.imageService.getImageByProductId(product.getId());
		this.imageService.removeInBatch(images);
		super.remove(product);
	}

	@Override
	public void remove(Long id) {
		Product product = this.get(id);
		this.remove(product);
	}

	/**
	 * 保存商品
	 */
	@Override
	public Product save(Map params) {
		Product product = null;
		Shop shop = this.shopService.getCurrentUserShop();
		Long id = MapUtils.getLong(params, "id");
		String name = MapUtils.getString(params, "name");
		String remark = MapUtils.getString(params, "remark");
		Long categoryId = MapUtils.getLong(params, "categoryId");
		Float priceYuan = MapUtils.getFloat(params, "priceYuan");
		String code = MapUtils.getString(params, "code");
		Boolean isOnSale = MapUtils.getBoolean(params, "isOnSale", false);
		if (null != id) {
			product = this.get(id);
		}
		if (product == null) {
			product = new Product();
			// 生成产品编码
			// @TODO,根据分类ID生成产品编码
			product.setCode(this.generateProductCode());
		}
		Category category = this.categoryService.get(categoryId);
		product.setCategory(category);
		product.setName(name);
		product.setRemark(remark);
		product.setShop(shop);
		// product.setPrice(price);
		product.setPriceYuan(priceYuan);
		product.setIsOnSale(isOnSale);
		this.save(product);
		this.cleanProductProperty(product);
		this.saveDynamicProperties(product, params);
		this.saveProductSkus(product, params);
		return product;
	}

	/**
	 * 清理产品的自定义属性
	 * @todo
	 * @param product
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月13日
	 */
	private void cleanProductProperty(Product product) {
		Map searchParams = new HashMap();
		searchParams.put("EQ|product.$id", product.getId());
		List<ProductProperty> productProperties = this.productPropertyService.getPage(searchParams, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(productProperties)) {
			this.productPropertyService.removeInBatch(productProperties);
		}
	}

	/**
	 * 保存产品动态属性
	 * @todo
	 * @param product
	 * @param params
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月13日
	 */
	private void saveDynamicProperties(Product product, Map params) {
		if (MapUtils.isNotEmpty(params)) {
			Iterator it = params.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next().toString();
				String value = MapUtils.getString(params, key);
				// 如果是动态属性
				if (StringUtils.startsWith(key, "dynamic")) {
					String[] keyArray = key.split("_");
					String dynamicPropertyId = "";
					String dynamicPropertyValueId = "";
					ProductProperty productProperty = new ProductProperty();
					productProperty.setProduct(product);
					productProperty.setShop(product.getShop());
					dynamicPropertyId = keyArray[1];
					DynamicProperty dynamicProperty = this.dynamicPropertyService.get(Long.parseLong(dynamicPropertyId));
					productProperty.setDynamicProperty(dynamicProperty);
					if (StringUtils.equalsIgnoreCase(dynamicProperty.getDataType().getCode(), "enum")) {
						dynamicPropertyValueId = value;
						DynamicPropertyValue dynamicPropertyValue = this.dynamicPropertyValueService.get(Long
								.parseLong(dynamicPropertyValueId));
						productProperty.setDynamicPropertyValue(dynamicPropertyValue);
					} else {
						productProperty.setValue(value);
					}
					this.productPropertyService.save(productProperty);
				}
			}
		}
	}

	/**
	 * 创建产品SKU
	 * @todo
	 * @param product
	 * @param params
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月13日
	 */
	private void saveProductSkus(Product product, Map params) {
		// 存储 sku的数据结构Key:DynamicProperty.id,V:<DynamicPropertyValue>
		Map<Long, List<DynamicPropertyValue>> map = new TreeMap();
		// List<List<DynamicPropertyValue>> dynamicPropertyValues=new ArrayList<List<DynamicPropertyValue>>(0);
		Iterator it = params.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next().toString();
			String value = params.get(key).toString();
			if (StringUtils.startsWith(key, "dynamic")) {
				String[] keyArray = key.split("_");
				// Sku 的 params key由三段组成
				if (keyArray != null && keyArray.length == 3) {
					Long dynamicPropertyId = Long.parseLong(keyArray[1]);
					Long dynamicPropertyValueId = Long.parseLong(value);
					List<DynamicPropertyValue> dynamicPropertyValues = null;
					if (map.containsKey(dynamicPropertyId)) {
						dynamicPropertyValues = map.get(dynamicPropertyId);
					} else {
						dynamicPropertyValues = new ArrayList<DynamicPropertyValue>(0);
						map.put(dynamicPropertyId, dynamicPropertyValues);
					}
					DynamicPropertyValue dynamicPropertyValue = this.dynamicPropertyValueService.get(dynamicPropertyValueId);
					dynamicPropertyValues.add(dynamicPropertyValue);
				}
			}
		}
		Shop shop = this.shopService.getCurrentUserShop();
		if (MapUtils.isNotEmpty(map)) {
			// 计算SKU的数量
			int skuCount = 1;
			it = map.keySet().iterator();
			while (it.hasNext()) {
				Long key = (Long) it.next();
				List<DynamicPropertyValue> items = map.get(key);
				skuCount *= items.size();
			}
			DynamicPropertyValue[][] skuDynamicPropertyValues = new DynamicPropertyValue[skuCount][map.size()];
			// 计步数组,初始化计步数组为[0,0,0,0,0,0],length = map.size
			int[] step = new int[map.size()];
			for (int j = 0; j < map.size(); j++) {
				step[j] = 0;
			}
			for (int k = 0; k < skuCount; k++) {
				for (int j = 0; j < map.size(); j++) {
					List<DynamicPropertyValue> dynamicPropertyValues = this.getDynamicPropertyValusByIndex(map, j);
					skuDynamicPropertyValues[k][j] = dynamicPropertyValues.get(step[j]);
				}
				// 计步数组平移计算
				int length = step.length;
				for (int i = length - 1; i >= 0; i--) {
					int index = step[i];
					List<DynamicPropertyValue> dynamicPropertyValues = this.getDynamicPropertyValusByIndex(map, i);
					if (index < dynamicPropertyValues.size() - 1) {
						step[i] = index + 1;
						break;
					} else {
						step[i] = 0;
						--length;
					}
				}
			}
			// 清理前后不一致的Sku(被删除的sku)
			this.cleanSku(product, skuDynamicPropertyValues);
			// 保存存Sku DynamicPropertyValue
			for (int i = 0; i < skuDynamicPropertyValues.length; i++) {
				DynamicPropertyValue[] lineDynamicPropertyValues = skuDynamicPropertyValues[i];
				boolean isExist = this.isSkuExist(product, lineDynamicPropertyValues);
				if (!isExist) {
					ProductSku productSku = new ProductSku();
					productSku.setPrice(product.getPrice());
					productSku.setProduct(product);
					productSku.setShop(shop);
					this.productSkuService.save(productSku);
					for (DynamicPropertyValue dynamicPropertyValue : lineDynamicPropertyValues) {
						SkuProperty skuProperty = new SkuProperty();
						skuProperty.setDynamicProperty(dynamicPropertyValue.getDynamicProperty());
						skuProperty.setDynamicPropertyValue(dynamicPropertyValue);
						skuProperty.setProductSku(productSku);
						skuProperty.setShop(shop);
						this.skuPropertyService.save(skuProperty);
					}
				}
			}
		} else {
			// 如果没有sku属性，创建默认sku
			Map searchParams = new HashMap();
			searchParams.put("EQ|product.$id", product.getId());
			List<ProductSku> productSkus = this.productSkuService.getPage(searchParams, 1, 0).getContent();
			ProductSku productSku = null;
			if (CollectionUtils.isNotEmpty(productSkus)) {
				productSku = productSkus.get(0);
			} else {
				productSku = new ProductSku();
				productSku.setProduct(product);
				productSku.setShop(shop);
			}
			productSku.setPrice(product.getPrice());
			this.productSkuService.save(productSku);
		}
	}

	/**
	 * 根据索引取得sku 属性值集合
	 * @todo
	 * @param map
	 * @param index
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月14日
	 */
	private List<DynamicPropertyValue> getDynamicPropertyValusByIndex(Map<Long, List<DynamicPropertyValue>> map, int index) {
		List<DynamicPropertyValue> dynamicPropertyValues = null;
		int count = 0;
		Iterator<Long> it = map.keySet().iterator();
		while (it.hasNext()) {
			Long key = it.next();
			if (count == index) {
				dynamicPropertyValues = map.get(key);
				break;
			}
			count++;
		}
		return dynamicPropertyValues;
	}

	/**
	 * 清理不再需要的sku
	 * @todo
	 * @param product
	 * @param dynamicPropertyValues
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月14日
	 */
	private void cleanSku(Product product, DynamicPropertyValue[][] dynamicPropertyValues) {
		Map searchParams = new HashMap();
		searchParams.put("EQ|product.$id", product.getId());
		List<ProductSku> existSkus = this.productSkuService.getPage(searchParams, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(existSkus)) {
			for (ProductSku productSku : existSkus) {
				searchParams = new HashMap();
				searchParams.put("EQ|productSku.$id", productSku.getId());
				List<SkuProperty> skuProperties = this.skuPropertyService.getPage(searchParams, 1, 0).getContent();
				// 是否在[][]中存在
				boolean isFind = false;
				for (DynamicPropertyValue[] lineDynamicPropertyValues : dynamicPropertyValues) {
					if (skuProperties.size() != lineDynamicPropertyValues.length) {
						break;
					}
					for (SkuProperty skuProperty : skuProperties) {
						isFind = false;
						for (DynamicPropertyValue dynamicPropertyValue : lineDynamicPropertyValues) {
							if (dynamicPropertyValue.getId().compareTo(skuProperty.getDynamicPropertyValue().getId()) == 0) {
								isFind = true;
								break;
							}
						}
						if (!isFind) {
							break;
						}
					}
					if (isFind) {
						break;
					}
				}
				// 如果原来存储的sku，在新的[][]中不存在，则清楚已保存的SKU
				if (!isFind && isSkuCanClean(productSku)) {
					this.skuPropertyService.removeInBatch(skuProperties);
					this.productSkuService.remove(productSku);
				}
			}
		}
	}

	/**
	 * SKU是否可被删除
	 * @todo
	 * @param productSku
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月14日
	 */
	private boolean isSkuCanClean(ProductSku productSku) {
		// @TODO :有库存，不可删除，有历史订单，不可删除
		// 检查库存
		boolean res = true;
		Map searchParams = new HashMap();
		searchParams.put("EQ|productSku.$id", productSku.getId());
		Page<Inventory> inventories = this.inventoryService.getPage(searchParams, 1, 1);
		if (inventories.getTotalElements() > 0) {
			res = false;
			return res;
		}
		// 检查出库单
		searchParams = new HashMap();
		searchParams.put("EQ|productSku.$id", productSku.getId());
		Page<InvoiceItem> invoiceItems = this.invoiceItemService.getPage(searchParams, 1, 1);
		if (invoiceItems.getTotalElements() > 0) {
			res = false;
			return res;
		}
		// 检查订单条目
		searchParams = new HashMap();
		searchParams.put("EQ|productSku.$id", productSku.getId());
		Page<Item> items = this.itemService.getPage(searchParams, 1, 1);
		if (items.getTotalElements() > 0) {
			res = false;
			return res;
		}
		return res;
	}

	/**
	 * 检查 sku 是否已存在
	 * @todo
	 * @param product
	 * @param lineDynamicPropertyValues
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月14日
	 */
	private boolean isSkuExist(Product product, DynamicPropertyValue[] lineDynamicPropertyValues) {
		boolean isExist = false;
		Map searchParams = new HashMap();
		searchParams.put("EQ|product.$id", product.getId());
		List<ProductSku> existSkus = this.productSkuService.getPage(searchParams, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(existSkus)) {
			for (ProductSku productSku : existSkus) {
				searchParams = new HashMap();
				searchParams.put("EQ|productSku.$id", productSku.getId());
				List<SkuProperty> skuProperties = this.skuPropertyService.getPage(searchParams, 1, 0).getContent();
				boolean isFind = false;
				if (CollectionUtils.isNotEmpty(skuProperties)) {
					for (DynamicPropertyValue dynamicPropertyValue : lineDynamicPropertyValues) {
						isFind = false;
						for (SkuProperty skuProperty : skuProperties) {
							if (skuProperty.getDynamicPropertyValue().getId().compareTo(dynamicPropertyValue.getId()) == 0) {
								isFind = true;
								break;
							}
						}
						if (!isFind) {
							break;
						}
					}
				}
				if (isFind) {
					isExist = true;
					break;
				}
			}
		}
		return isExist;
	}

	@Override
	public ProductInfo getProductInfo(Long productId) {
		ProductInfo productInfo = new ProductInfo();
		Product product = this.get(productId);
		List<Image> images = this.imageService.getImageByProductId(productId);
		List<ProductPropertyInfo> productPropertyInfos = this.productPropertyService.getProdutPropertyInfosByProductId(productId);
		productInfo.setProduct(product);
		productInfo.setImages(images);
		productInfo.setProductPropertyInfos(productPropertyInfos);
		return productInfo;
	}

	@Override
	public String generateProductCode() {
		String code = "";
		String date = DateUtil.formatDate(new Date(), "yyyyMMddHHmmss");
		code = date + RandUtil.getRandInt(1000, 9990);
		return code;
	}

}
/**@generate-service-source@**/
