package com.faceye.component.platform.service.impl;

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

import com.faceye.component.platform.entity.Platform;

import com.faceye.component.platform.repository.mongo.PlatformRepository;
import com.faceye.component.platform.service.PlatformService;

 
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
/**
 * Platform 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
import com.querydsl.core.types.Predicate;
@Service
public class PlatformServiceImpl extends BaseMongoServiceImpl<Platform, Long, PlatformRepository> implements PlatformService {

	@Autowired
	public PlatformServiceImpl(PlatformRepository dao) {
		super(dao);
	}
	
	
	@Override
	public Page<Platform> getPage(Map<String, Object> searchParams, int page, int size)   {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Platform> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Platform> builder = new PathBuilder<Platform>(entityPath.getType(), entityPath.getMetadata());
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
		Page<Platform> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Platform>("id") {
			// })
			List<Platform> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Platform>(items);

		}
		return res;
	}
	
}/**@generate-service-source@**/
