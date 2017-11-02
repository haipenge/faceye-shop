package com.faceye.component.customer.service.impl;

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

import com.faceye.component.customer.entity.Address;
import com.faceye.component.customer.repository.mongo.AddressRepository;
import com.faceye.component.customer.service.AddressService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.mysema.query.types.Predicate;

/**
 * 模块:客户->com.faceye.compoent.customer.service.impl<br>
 * 说明:<br>
 * 实体:送货地址->com.faceye.component.customer.entity.entity.Address 服务实现类<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:37<br>
 */
@Service
public class AddressServiceImpl extends BaseMongoServiceImpl<Address, Long, AddressRepository> implements AddressService {

	@Autowired
	public AddressServiceImpl(AddressRepository dao) {
		super(dao);
	}
	
	/**
	 *数据分页查询
	 * @author haipenge <br>
     * 联系:haipenge@gmail.com<br>
     * 创建日期:2015-6-13 11:31:37<br>
	*/
	@Override
	public Page<Address> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Address> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Address> builder = new PathBuilder<Address>(entityPath.getType(), entityPath.getMetadata());
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
		Page<Address> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Address>("id") {
			// })
			List<Address> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Address>(items);

		}
		return res;
	}
	
	
}/**@generate-service-source@**/
