package com.faceye.component.order.service.impl;

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

import com.faceye.component.order.entity.Item;
import com.faceye.component.order.repository.mongo.ItemRepository;
import com.faceye.component.order.service.ItemService;
import com.faceye.component.order.service.model.ItemInfo;
import com.faceye.component.product.entity.Image;
import com.faceye.component.product.service.ImageService;
import com.faceye.component.product.service.SkuPropertyService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;

/**
 * 模块:订单->com.faceye.compoent.order.service.impl<br>
 * 说明:<br>
 * 实体:订单条目->com.faceye.component.order.entity.entity.Item 服务实现类<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-16 18:57:43<br>
 */
@Service
public class ItemServiceImpl extends BaseMongoServiceImpl<Item, Long, ItemRepository> implements ItemService {
	@Autowired
	private SkuPropertyService skuPropertyService;
	@Autowired
	private ImageService imageService=null;

	@Autowired
	public ItemServiceImpl(ItemRepository dao) {
		super(dao);
	}

	/**
	 *数据分页查询
	 * @author haipenge <br>
	 * 联系:haipenge@gmail.com<br>
	 * 创建日期:2015-6-16 18:57:43<br>
	*/
	@Override
	public Page<Item> getPage(Map<String, Object> searchParams, int page, int size) {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Item> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Item> builder = new PathBuilder<Item>(entityPath.getType(), entityPath.getMetadata());
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
		Page<Item> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Item>("id") {
			// })
			List<Item> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Item>(items);

		}
		return res;
	}

	@Override
	public ItemInfo getItemInfo(Item item) {
		ItemInfo itemInfo = new ItemInfo();
		itemInfo.setItem(item);
		itemInfo.setSkuProperties(this.skuPropertyService.getSkuPropertiesByProductSku(item.getProductSku()));
		Map imageParams=new HashMap();
		imageParams.put("EQ|product.$id", item.getProduct().getId());
		List<Image> images=this.imageService.getPage(imageParams, 1, 0).getContent();
		itemInfo.setImages(images);
		return itemInfo;
	}

	@Override
	public List<ItemInfo> getItemInfos(List<Item> items) {
		List<ItemInfo> itemInfos = new ArrayList<ItemInfo>(0);
		if (CollectionUtils.isNotEmpty(items)) {
			for (Item item : items) {
				itemInfos.add(this.getItemInfo(item));
			}
		}
		return itemInfos;
	}

}
/**@generate-service-source@**/
