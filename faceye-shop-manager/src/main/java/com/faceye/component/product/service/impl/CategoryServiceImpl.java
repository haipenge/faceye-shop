package com.faceye.component.product.service.impl;

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

import com.faceye.component.product.entity.Category;
import com.faceye.component.product.repository.mongo.CategoryRepository;
import com.faceye.component.product.service.CategoryService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;


/**
 * 模块:产品->com.faceye.compoent.product.service.impl<br>
 * 说明:<br>
 * 实体:产品分类->com.faceye.component.product.entity.entity.Category 服务实现类<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:35<br>
 */
@Service
public class CategoryServiceImpl extends BaseMongoServiceImpl<Category, Long, CategoryRepository> implements CategoryService {

	@Autowired
	public CategoryServiceImpl(CategoryRepository dao) {
		super(dao);
	}
	
	/**
	 *数据分页查询
	 * @author haipenge <br>
     * 联系:haipenge@gmail.com<br>
     * 创建日期:2015-6-13 11:31:35<br>
	*/
	@Override
	public Page<Category> getPage(Map<String, Object> searchParams, int page, int size)  {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Category> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Category> builder = new PathBuilder<Category>(entityPath.getType(), entityPath.getMetadata());
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
		Page<Category> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Category>("id") {
			// })
			List<Category> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Category>(items);

		}
		return res;
	}
	
	
}/**@generate-service-source@**/
