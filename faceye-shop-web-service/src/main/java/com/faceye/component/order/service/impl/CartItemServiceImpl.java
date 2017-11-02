package com.faceye.component.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.customer.entity.Customer;
import com.faceye.component.customer.service.CustomerService;
import com.faceye.component.order.entity.Cart;
import com.faceye.component.order.entity.CartItem;
import com.faceye.component.order.repository.mongo.CartItemRepository;
import com.faceye.component.order.service.CartItemService;
import com.faceye.component.order.service.CartService;
import com.faceye.component.order.service.model.CartItemInfo;
import com.faceye.component.product.entity.Image;
import com.faceye.component.product.entity.Product;
import com.faceye.component.product.entity.ProductSku;
import com.faceye.component.product.entity.SkuProperty;
import com.faceye.component.product.service.ImageService;
import com.faceye.component.product.service.ProductService;
import com.faceye.component.product.service.ProductSkuService;
import com.faceye.component.product.service.SkuPropertyService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;
/**
 * CartItem 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */

@Service
public class CartItemServiceImpl extends BaseMongoServiceImpl<CartItem, Long, CartItemRepository> implements CartItemService {
	@Autowired
	private ProductSkuService productSkuService = null;
	@Autowired
	private CartService cartService = null;
	@Autowired
	private CustomerService customerService = null;
	@Autowired
	private ProductService productService = null;
	@Autowired
	private SkuPropertyService skuPropertyService = null;
	@Autowired
	private ImageService imageService=null;

	@Autowired
	public CartItemServiceImpl(CartItemRepository dao) {
		super(dao);
	}

	@Override
	public Page<CartItem> getPage(Map<String, Object> searchParams, int page, int size)  {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<CartItem> entityPath = resolver.createPath(entityClass);
		// PathBuilder<CartItem> builder = new PathBuilder<CartItem>(entityPath.getType(), entityPath.getMetadata());
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
		Page<CartItem> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<CartItem>("id") {
			// })
			List<CartItem> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<CartItem>(items);

		}
		return res;
	}

	@Override
	public boolean addProductSku2Cart(String uid, Long productId, String dynamicPropertyAndValueIds, Integer quantity) {
		Product product = this.productService.get(productId);
		Customer customer=this.customerService.getCurrentLoginCustomer();
		ProductSku productSku = null;
		// 如果产品只有一个SKU，或只有默认SKU
		if (StringUtils.isEmpty(dynamicPropertyAndValueIds)) {
			Map searchProdutSkuParams = new HashMap();
			searchProdutSkuParams.put("EQ|product.$id", productId);
			List<ProductSku> productSkus = this.productSkuService.getPage(searchProdutSkuParams, 1, 0).getContent();
			if (CollectionUtils.isNotEmpty(productSkus) && productSkus.size() == 1) {
				productSku = productSkus.get(0);
			}
		} else {
			productSku = this.productSkuService.getProductSkuByDynamicPropertyAndValueIds(product, dynamicPropertyAndValueIds);
		}
		if (productSku != null) {
			Cart cart = this.cartService.getCartByUid(customer.getUid());
			Map searchCartItemParams = new HashMap();
			searchCartItemParams.put("EQ|productSku.$id", productSku.getId());
			searchCartItemParams.put("EQ|cart.$id", cart.getId());
			searchCartItemParams.put("EQ|status", 0);
			Page<CartItem> cartItems = this.getPage(searchCartItemParams, 1, 0);
			CartItem cartItem = null;
			if (cartItems != null && CollectionUtils.isNotEmpty(cartItems.getContent())) {
				cartItem = cartItems.getContent().get(0);
			}
			if (cartItem == null) {
				cartItem = new CartItem();
				cartItem.setProductSku(productSku);
				cartItem.setCustomer(customer);
				cartItem.setCart(cart);
			}
			cartItem.setQuantity(cartItem.getQuantity() + quantity);
			this.save(cartItem);
		}
		return true;
	}

	@Override
	public List<CartItemInfo> getCartItemInfos(String uid) {
		Map params = new HashMap();
		List<CartItemInfo> cartItemInfos = new ArrayList<CartItemInfo>(0);
		Cart cart = this.cartService.getCartByUid(uid);
		if (cart != null) {
			params.put("EQ|cart.$id", cart.getId());
			// 只查看创建状态的,已提交，已删除状态的不显示
			params.put("EQ|status", 0);
			Page<CartItem> cartItems = this.getPage(params, 1, 0);
			if (cartItems != null && CollectionUtils.isNotEmpty(cartItems.getContent())) {
				for (CartItem cartItem : cartItems.getContent()) {
					CartItemInfo cartItemInfo = this.getCartItemInfo(cartItem.getId());
					cartItemInfos.add(cartItemInfo);
				}
			}
		}
		return cartItemInfos;
	}

	@Override
	public CartItemInfo getCartItemInfo(Long cartItemId) {
		CartItemInfo cartItemInfo = null;
		CartItem cartItem = this.get(cartItemId);
		if (cartItem != null) {
			cartItemInfo = new CartItemInfo();
			Map params = new HashMap();
			params.put("EQ|productSku.$id", cartItem.getProductSku().getId());
			List<SkuProperty> skuProperties = this.skuPropertyService.getPage(params, 1, 0).getContent();
			cartItemInfo.setSkuProperties(skuProperties);
			cartItemInfo.setCartItem(cartItem);
			Map imageParams=new HashMap();
			imageParams.put("EQ|product.$id", cartItem.getProductSku().getProduct().getId());
			List<Image> images=this.imageService.getPage(imageParams, 1, 0).getContent();
			cartItemInfo.setImages(images);
		}
		return cartItemInfo;
	}

	@Override
	public List<CartItemInfo> getCartItemInfos(Long[] cartItemIds) {
		List<CartItemInfo> cartItemInfos = new ArrayList<CartItemInfo>(0);
		if (null != cartItemIds) {
			for (Long cartItemId : cartItemIds) {
				cartItemInfos.add(this.getCartItemInfo(cartItemId));
			}
		}
		return cartItemInfos;
	}

}
/**@generate-service-source@**/
