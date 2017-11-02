package com.faceye.component.product.service.impl;

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
import com.faceye.feature.util.ServiceException;

import com.faceye.component.product.entity.DynamicPropertyValue;
import com.faceye.component.product.repository.mongo.DynamicPropertyValueRepository;
import com.faceye.component.product.service.DynamicPropertyValueService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.mysema.query.types.Predicate;

/**
 * 模块:产品->com.faceye.compoent.product.service.impl<br>
 * 说明:<br>
 * 实体:动态属性值->com.faceye.component.product.entity.entity.DynamicPropertyValue 服务实现类<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:35<br>
 */
@Service
public class DynamicPropertyValueServiceImpl extends BaseMongoServiceImpl<DynamicPropertyValue, Long, DynamicPropertyValueRepository> implements DynamicPropertyValueService {

	@Autowired
	public DynamicPropertyValueServiceImpl(DynamicPropertyValueRepository dao) {
		super(dao);
	}
	
	/**
	 *数据分页查询
	 * @author haipenge <br>
     * 联系:haipenge@gmail.com<br>
     * 创建日期:2015-6-13 11:31:35<br>
	*/
	@Override
	public Page<DynamicPropertyValue> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<DynamicPropertyValue> entityPath = resolver.createPath(entityClass);
		// PathBuilder<DynamicPropertyValue> builder = new PathBuilder<DynamicPropertyValue>(entityPath.getType(), entityPath.getMetadata());
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
		Page<DynamicPropertyValue> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<DynamicPropertyValue>("id") {
			// })
			List<DynamicPropertyValue> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<DynamicPropertyValue>(items);

		}
		return res;
	}
	
	
}/**@generate-service-source@**/
