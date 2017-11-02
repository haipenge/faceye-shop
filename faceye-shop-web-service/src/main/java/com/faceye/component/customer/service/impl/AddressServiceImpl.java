package com.faceye.component.customer.service.impl;

import java.util.ArrayList;
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

import com.faceye.component.customer.entity.Address;
import com.faceye.component.customer.entity.Customer;
import com.faceye.component.customer.repository.mongo.AddressRepository;
import com.faceye.component.customer.service.AddressService;
import com.faceye.component.lbs.service.GeoLibraryService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;


/**
 * 模块:客户->com.faceye.compoent.customer.service.impl<br>
 * 说明:<br>
 * 实体:送货地址->com.faceye.component.customer.entity.entity.Address 服务实现类<br>
 * 
 * @author haipenge <br>
 *         联系:haipenge@gmail.com<br>
 *         创建日期:2015-6-16 18:57:43<br>
 */
@Service
public class AddressServiceImpl extends BaseMongoServiceImpl<Address, Long, AddressRepository> implements AddressService {
	@Autowired
	private GeoLibraryService geoLibraryService = null;

	@Autowired
	public AddressServiceImpl(AddressRepository dao) {
		super(dao);
	}

	/**
	 * 数据分页查询
	 * 
	 * @author haipenge <br>
	 *         联系:haipenge@gmail.com<br>
	 *         创建日期:2015-6-16 18:57:43<br>
	 */
	@Override
	public Page<Address> getPage(Map<String, Object> searchParams, int page, int size) {
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
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new
			// NumberExpression<Address>("id") {
			// })
			List<Address> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Address>(items);
		}
		/**
		 * 对结果集进行重新构建
		 */
		if (res != null && CollectionUtils.isNotEmpty(res.getContent())) {
			List<Address> rebuildAddress = new ArrayList<Address>();
			for (Address address : res.getContent()) {
				rebuildAddress.add(this.rebuildAddress(address));
			}
			if (size == 0) {
				res = new PageImpl(rebuildAddress);
			} else {
				res = new PageImpl(rebuildAddress, new PageRequest(page, size, sort), res.getTotalElements());
			}
		}

		return res;
	}

	public Address get(Long id) {
		Address address = super.get(id);
		return this.rebuildAddress(address);
	}

	@Override
	public List<Address> getAddressesByCustomer(Customer customer) {
		List<Address> addresses = null;
		List<Address> result = new ArrayList<Address>(0);
		if (customer != null) {
			addresses = this.dao.getAddressesByCustomerId(customer.getId());
			if (CollectionUtils.isNotEmpty(addresses)) {
				for (Address address : addresses) {
					result.add(this.rebuildAddress(address));
				}
			}
		}
		return result;
	}

	private Address rebuildAddress(Address address) {
		Address res = null;
		if (address != null) {
			if (address.getGeoLibrary() != null) {
				String showAddressGeoLibrary = this.geoLibraryService.getGeoLibrariesShowDetail(address.getGeoLibrary().getId());
				showAddressGeoLibrary+=" ";
				showAddressGeoLibrary+=address.getDetail();
				res = address;
				res.setShow(showAddressGeoLibrary);
			}else{
				res=address;
			}
		}
		return res;
	}

}
/** @generate-service-source@ **/
