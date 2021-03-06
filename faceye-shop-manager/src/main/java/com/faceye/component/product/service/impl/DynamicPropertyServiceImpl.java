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

import com.faceye.component.product.entity.Category;
import com.faceye.component.product.entity.DynamicProperty;
import com.faceye.component.product.repository.mongo.DynamicPropertyRepository;
import com.faceye.component.product.service.DynamicPropertyService;
import com.faceye.component.setting.entity.Shop;
import com.faceye.component.setting.service.ShopService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;

/**
 * 模块:产品->com.faceye.compoent.product.service.impl<br>
 * 说明:<br>
 * 实体:产品属性->com.faceye.component.product.entity.entity.DynamicProperty 服务实现类<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>，
 * 创建日期:2015-6-13 11:31:35<br>
 */
@Service
public class DynamicPropertyServiceImpl extends BaseMongoServiceImpl<DynamicProperty, Long, DynamicPropertyRepository> implements
		DynamicPropertyService {
	@Autowired
	private ShopService shopService = null;

	@Autowired
	public DynamicPropertyServiceImpl(DynamicPropertyRepository dao) {
		super(dao);
	}

	/**
	 *数据分页查询
	 * @author haipenge <br>
	 * 联系:haipenge@gmail.com<br>
	 * 创建日期:2015-6-13 11:31:35<br>
	*/
	@Override
	public Page<DynamicProperty> getPage(Map<String, Object> searchParams, int page, int size)  {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<DynamicProperty> entityPath = resolver.createPath(entityClass);
		// PathBuilder<DynamicProperty> builder = new PathBuilder<DynamicProperty>(entityPath.getType(), entityPath.getMetadata());
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
		Page<DynamicProperty> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<DynamicProperty>("id") {
			// })
			List<DynamicProperty> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<DynamicProperty>(items);

		}
		return res;
	}

	@Override
	public List<DynamicProperty> getDynamicProperties(Shop shop, Category category) {
		List<DynamicProperty> dynamicProperties = new ArrayList<DynamicProperty>(0);
		if (shop == null) {
			shop = this.shopService.getCurrentUserShop();
		}
		Map searchParams = new HashMap();
		// 获取公共属性
		if (shop != null) {
			searchParams.put("EQ|shop.$id", shop.getId());
			searchParams.put("ISTRUE|isShare", true);
			List<DynamicProperty> sharedDynamicProperties = this.getPage(searchParams, 1, 0).getContent();
			if (CollectionUtils.isNotEmpty(sharedDynamicProperties)) {
				dynamicProperties.addAll(sharedDynamicProperties);
			}
		}
		// 获取私有属性（分类专用属性)
		if (category != null) {
			searchParams = new HashMap();
			searchParams.put("EQ|shop.$id", category.getShop().getId());
			searchParams.put("ISFALSE|isShare", false);
			searchParams.put("EQ|category.$id", category.getId());
			List<DynamicProperty> unSharedDynamicProperties = this.getPage(searchParams, 1, 0).getContent();
			if (CollectionUtils.isNotEmpty(unSharedDynamicProperties)) {
				dynamicProperties.addAll(unSharedDynamicProperties);
			}
		}
		return dynamicProperties;
	}

}
/**@generate-service-source@**/
