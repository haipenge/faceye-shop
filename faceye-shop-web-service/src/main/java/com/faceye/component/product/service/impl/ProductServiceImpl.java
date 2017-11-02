package com.faceye.component.product.service.impl;

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

import com.faceye.component.product.entity.Image;
import com.faceye.component.product.entity.Product;
import com.faceye.component.product.model.ProductInfo;
import com.faceye.component.product.model.ProductPropertyInfo;
import com.faceye.component.product.repository.mongo.ProductRepository;
import com.faceye.component.product.service.ImageService;
import com.faceye.component.product.service.ProductPropertyService;
import com.faceye.component.product.service.ProductService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;

/**
 * 模块:产品->com.faceye.compoent.product.service.impl<br>
 * 说明:<br>
 * 实体:产品->com.faceye.component.product.entity.entity.Product 服务实现类<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-16 18:57:42<br>
 */
@Service
public class ProductServiceImpl extends BaseMongoServiceImpl<Product, Long, ProductRepository> implements ProductService {
	@Autowired
    private ImageService imageService=null;
	@Autowired
	private ProductPropertyService productPropertyService;
	@Autowired
	public ProductServiceImpl(ProductRepository dao) {
		super(dao);
	}
	
	/**
	 *数据分页查询
	 * @author haipenge <br>
     * 联系:haipenge@gmail.com<br>
     * 创建日期:2015-6-16 18:57:42<br>
	*/
	@Override
	public Page<Product> getPage(Map<String, Object> searchParams, int page, int size)  {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Product> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Product> builder = new PathBuilder<Product>(entityPath.getType(), entityPath.getMetadata());
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
		Page<Product> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Product>("id") {
			// })
			List<Product> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Product>(items);

		}
		return res;
	}
	
	@Override
	public ProductInfo getProductInfo(Long productId) {
		ProductInfo productInfo = new ProductInfo();
		Product product = this.get(productId);
		List<Image> images = this.imageService.getImagesByProductId(productId);
		List<ProductPropertyInfo> productPropertyInfos = this.productPropertyService.getProdutPropertyInfosByProductId(productId);
		productInfo.setProduct(product);
		productInfo.setImages(images);
		productInfo.setProductPropertyInfos(productPropertyInfos);
		return productInfo;
	}

	@Override
	public List<ProductInfo> getProductInfos(List<Product> products) {
		List<ProductInfo> productInfos=new ArrayList<ProductInfo>(0);
		if(CollectionUtils.isNotEmpty(products)){
			for(Product product:products){
				productInfos.add(this.getProductInfo(product.getId()));
			}
		}
		return productInfos;
	}
}/**@generate-service-source@**/
