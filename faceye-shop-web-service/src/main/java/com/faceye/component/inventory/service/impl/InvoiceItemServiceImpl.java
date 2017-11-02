package com.faceye.component.inventory.service.impl;

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

import com.faceye.component.inventory.entity.InvoiceItem;
import com.faceye.component.inventory.repository.mongo.InvoiceItemRepository;
import com.faceye.component.inventory.service.InvoiceItemService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;


/**
 * 模块:库存->com.faceye.compoent.inventory.service.impl<br>
 * 说明:<br>
 * 实体:库存单据明细->com.faceye.component.inventory.entity.entity.InvoiceItem 服务实现类<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-16 18:57:43<br>
 */
@Service
public class InvoiceItemServiceImpl extends BaseMongoServiceImpl<InvoiceItem, Long, InvoiceItemRepository> implements InvoiceItemService {

	@Autowired
	public InvoiceItemServiceImpl(InvoiceItemRepository dao) {
		super(dao);
	}
	
	/**
	 *数据分页查询
	 * @author haipenge <br>
     * 联系:haipenge@gmail.com<br>
     * 创建日期:2015-6-16 18:57:43<br>
	*/
	@Override
	public Page<InvoiceItem> getPage(Map<String, Object> searchParams, int page, int size) {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<InvoiceItem> entityPath = resolver.createPath(entityClass);
		// PathBuilder<InvoiceItem> builder = new PathBuilder<InvoiceItem>(entityPath.getType(), entityPath.getMetadata());
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
		Page<InvoiceItem> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<InvoiceItem>("id") {
			// })
			List<InvoiceItem> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<InvoiceItem>(items);

		}
		return res;
	}
	
	
}/**@generate-service-source@**/
