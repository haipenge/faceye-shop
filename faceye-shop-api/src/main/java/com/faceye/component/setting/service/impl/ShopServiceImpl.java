package com.faceye.component.setting.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
 

import com.faceye.component.setting.entity.Shop;
import com.faceye.component.setting.repository.mongo.ShopRepository;
import com.faceye.component.setting.service.ShopService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;

/**
 * 模块:配置->com.faceye.compoent.setting.service.impl<br>
 * 说明:<br>
 * 实体:店铺->com.faceye.component.setting.entity.entity.Shop 服务实现类<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:35<br>
 */
@Service
public class ShopServiceImpl extends BaseMongoServiceImpl<Shop, Long, ShopRepository> implements ShopService {

	@Autowired
	public ShopServiceImpl(ShopRepository dao) {
		super(dao);
	}
	
	/**
	 *数据分页查询
	 * @author haipenge <br>
     * 联系:haipenge@gmail.com<br>
     * 创建日期:2015-6-13 11:31:35<br>
	*/
	@Override
	public Page<Shop> getPage(Map<String, Object> searchParams, int page, int size)   {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Shop> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Shop> builder = new PathBuilder<Shop>(entityPath.getType(), entityPath.getMetadata());
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
		Page<Shop> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Shop>("id") {
			// })
			List<Shop> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Shop>(items);

		}
		return res;
	}
	
	
}/**@generate-service-source@**/
