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

import com.faceye.component.product.entity.DynamicProperty;
import com.faceye.component.product.entity.DynamicPropertyValue;
import com.faceye.component.product.entity.ProductProperty;
import com.faceye.component.product.model.ProductPropertyInfo;
import com.faceye.component.product.repository.mongo.ProductPropertyRepository;
import com.faceye.component.product.service.ProductPropertyService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;

/**
 * 模块:产品->com.faceye.compoent.product.service.impl<br>
 * 说明:<br>
 * 实体:产品属性->com.faceye.component.product.entity.entity.ProductProperty 服务实现类<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:36<br>
 */

@Service
public class ProductPropertyServiceImpl extends BaseMongoServiceImpl<ProductProperty, Long, ProductPropertyRepository> implements
		ProductPropertyService {

	@Autowired
	public ProductPropertyServiceImpl(ProductPropertyRepository dao) {
		super(dao);
	}

	/**
	 *数据分页查询
	 * @author haipenge <br>
	 * 联系:haipenge@gmail.com<br>
	 * 创建日期:2015-6-13 11:31:36<br>
	*/
	@Override
	public Page<ProductProperty> getPage(Map<String,Object> searchParams, int page, int size) {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<ProductProperty> entityPath = resolver.createPath(entityClass);
		// PathBuilder<ProductProperty> builder = new PathBuilder<ProductProperty>(entityPath.getType(), entityPath.getMetadata());
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
		Page<ProductProperty> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<ProductProperty>("id") {
			// })
			List<ProductProperty> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<ProductProperty>(items);

		}
		return res;
	}

	@Override
	public List<ProductProperty> getProductPropertyByDynamicProperty(DynamicProperty dynamicProperty) {
		Map<String,Object> searchParams = new HashMap<String,Object>();
		List<ProductProperty> productProperties = null;
		if (dynamicProperty != null) {
			searchParams.put("EQ|dynamicProperty.$id", dynamicProperty.getId());
			productProperties = this.getPage(searchParams, 1, 0).getContent();
		}
		return productProperties;
	}

	@Override
	public List<ProductProperty> getProductPropertyByDynamicPropertyValue(DynamicPropertyValue dynamicPropertyValue) {
		List<ProductProperty> productProperties = null;
		if (dynamicPropertyValue != null) {
			Map<String,Object> searchParams = new HashMap<String,Object>();
			searchParams.put("EQ|dynamicPropertyValue.$id", dynamicPropertyValue.getId());
			productProperties = this.getPage(searchParams, 1, 0).getContent();
		}
		return productProperties;
	}

	@Override
	public List<ProductProperty> getProductPropertyByProductId(Long productId) {
		Map<String,Object> searchParams = new HashMap<String,Object>();
		List<ProductProperty> productProperties = null;
		searchParams.put("EQ|product.$id", productId);
		productProperties = this.getPage(searchParams, 1, 0).getContent();
		return productProperties;
	}

	private List<ProductPropertyInfo> getProductPropertyInfos(List<ProductProperty> productProperties) {
		List<ProductPropertyInfo> productPropertyInfos = new ArrayList<ProductPropertyInfo>(0);
		if (CollectionUtils.isNotEmpty(productProperties)) {
			for (ProductProperty productProperty : productProperties) {
				if (productProperty.getDynamicProperty().getIsSku()) {
					ProductPropertyInfo productPropertyInfo = this.getProductProperty(productPropertyInfos, productProperty);
					if (productPropertyInfo == null) {
						productPropertyInfo = new ProductPropertyInfo();
						productPropertyInfos.add(productPropertyInfo);
					}
					productPropertyInfo.setProductProperty(productProperty);
					productPropertyInfo.addDynamicPropertyValue(productProperty.getDynamicPropertyValue());
				} else {
					ProductPropertyInfo productPropertyInfo = new ProductPropertyInfo();
					productPropertyInfo.setProductProperty(productProperty);
					productPropertyInfos.add(productPropertyInfo);
				}

			}
		}
		return productPropertyInfos;
	}

	private ProductPropertyInfo getProductProperty(List<ProductPropertyInfo> productPropertyInfos, ProductProperty productProperty) {
		ProductPropertyInfo productPropertyInfo = null;
		if (CollectionUtils.isNotEmpty(productPropertyInfos)) {
			for (ProductPropertyInfo info : productPropertyInfos) {
				if (info.getProductProperty().getDynamicProperty().getId().compareTo(productProperty.getDynamicProperty().getId()) == 0) {
					productPropertyInfo = info;
					break;
				}
			}
		}
		return productPropertyInfo;
	}

	@Override
	public List<ProductPropertyInfo> getProdutPropertyInfosByProductId(Long productId) {
		return this.getProductPropertyInfos(this.getProductPropertyByProductId(productId));
	}

}
/**@generate-service-source@**/
