package com.faceye.component.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.order.entity.Cart;

import com.faceye.component.order.repository.mongo.CartRepository;
import com.faceye.component.order.service.CartService;

 
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
/**
 * Cart 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
import com.querydsl.core.types.Predicate;
@Service
public class CartServiceImpl extends BaseMongoServiceImpl<Cart, Long, CartRepository> implements CartService {

	@Autowired
	public CartServiceImpl(CartRepository dao) {
		super(dao);
	}
	
	
	@Override
	public Page<Cart> getPage(Map<String, Object> searchParams, int page, int size)   {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Cart> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Cart> builder = new PathBuilder<Cart>(entityPath.getType(), entityPath.getMetadata());
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
		Page<Cart> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Cart>("id") {
			// })
			List<Cart> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Cart>(items);

		}
		return res;
	}
	
}/**@generate-service-source@**/
