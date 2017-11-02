package com.faceye.component.order.service.impl;

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

import com.faceye.component.customer.entity.Customer;
import com.faceye.component.customer.service.CustomerService;
import com.faceye.component.order.entity.Cart;
import com.faceye.component.order.entity.CartItem;
import com.faceye.component.order.repository.mongo.CartItemRepository;
import com.faceye.component.order.repository.mongo.CartRepository;
import com.faceye.component.order.service.CartService;
import com.faceye.component.order.service.model.CartInfo;
import com.faceye.component.security.web.service.UserService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;
/**
 * Cart 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */

@Service
public class CartServiceImpl extends BaseMongoServiceImpl<Cart, Long, CartRepository> implements CartService {
	@Autowired
	private UserService userService = null;
	@Autowired
	private CustomerService customerService = null;
	@Autowired
	private CartItemRepository cartItemRepository = null;
	@Autowired
	private CartItemServiceImpl cartItemService = null;

	@Autowired
	public CartServiceImpl(CartRepository dao) {
		super(dao);
	}

	@Override
	public Page<Cart> getPage(Map<String, Object> searchParams, int page, int size)  {
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

	@Override
	public Cart getCurrentUserCart() {
		Customer customer = this.customerService.getCurrentLoginCustomer();
		if (customer != null) {
			return this.dao.getCartByCustomer(customer);
		}
		return null;
	}

	@Override
	public Cart getCartByUid(String uid) {
		return dao.getCartByUid(uid);
	}

	@Override
	public CartInfo getCartInfo(String uid) {
		CartInfo cartInfo = new CartInfo();
		Cart cart = this.getCartByUid(uid);
		if (cart != null) {
			Map searchParams = new HashMap();
			searchParams.put("EQ|cart.$id", cart.getId());
			// 只查看创建状态的,已提交，已删除状态的不显示
			searchParams.put("EQ|status", 0);
			List<CartItem> cartItems = this.cartItemService.getPage(searchParams, 1, 0).getContent();
			if (CollectionUtils.isNotEmpty(cartItems)) {
				for (CartItem cartItem : cartItems) {
					cartInfo.setTotalQuantity(cartInfo.getTotalQuantity() + cartItem.getQuantity());
				}
			}
		}
		return cartInfo;
	}

	// /**
	// * 将CartItemInfo 按shop进行分组
	// */
	// @Override
	// public List<CartShopInfo> getCartShopInfos(List<CartItemInfo> cartItemInfos) {
	// List<CartShopInfo> cartShopInfos = new ArrayList<CartShopInfo>(0);
	// if (CollectionUtils.isNotEmpty(cartItemInfos)) {
	// for (CartItemInfo cartItemInfo : cartItemInfos) {
	// Shop shop = cartItemInfo.getCartItem().getProductSku().getShop();
	// CartShopInfo cartShopInfo = this.getCartShopInfo(shop, cartShopInfos);
	// if (cartShopInfo == null) {
	// cartShopInfo = new CartShopInfo();
	// cartShopInfo.setShop(shop);
	// cartShopInfos.add(cartShopInfo);
	// }
	// cartShopInfo.addCartItemInfo(cartItemInfo);
	// }
	// }
	// return cartShopInfos;
	// }
	//
	// /**
	// * 从集合中查找CartShopInfo，如果不存在，返回空。
	// * @todo
	// * @param shop
	// * @param cartShopInfos
	// * @return
	// * @author:@haipenge
	// * 联系:haipenge@gmail.com
	// * 创建时间:2015年6月22日
	// */
	// private CartShopInfo getCartShopInfo(Shop shop, List<CartShopInfo> cartShopInfos) {
	// CartShopInfo cartShopInfo = null;
	// for (CartShopInfo info : cartShopInfos) {
	// if (shop.getId().compareTo(info.getShop().getId()) == 0) {
	// cartShopInfo = info;
	// break;
	// }
	// }
	// return cartShopInfo;
	// }
	//
	// /**
	// * 根据UID，按shop对CartItemInfo 进行分组
	// */
	// @Override
	// public List<CartShopInfo> getCartShopInfos(String uid) {
	// List<CartShopInfo> cartShopInfos = null;
	// List<CartItemInfo> cartItemInfos = this.cartItemService.getCartItemInfos(uid);
	// cartShopInfos = this.getCartShopInfos(cartItemInfos);
	// return cartShopInfos;
	// }

}
/**@generate-service-source@**/
