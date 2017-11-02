package com.faceye.component.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.customer.entity.Address;
import com.faceye.component.customer.entity.Customer;
import com.faceye.component.customer.service.AddressService;
import com.faceye.component.customer.service.CustomerService;
import com.faceye.component.inventory.entity.Inventory;
import com.faceye.component.inventory.entity.Invoice;
import com.faceye.component.inventory.entity.InvoiceItem;
import com.faceye.component.inventory.service.InventoryService;
import com.faceye.component.inventory.service.InvoiceItemService;
import com.faceye.component.inventory.service.InvoiceService;
import com.faceye.component.order.entity.CartItem;
import com.faceye.component.order.entity.Item;
import com.faceye.component.order.entity.Order;
import com.faceye.component.order.repository.mongo.OrderRepository;
import com.faceye.component.order.service.CartItemService;
import com.faceye.component.order.service.ItemService;
import com.faceye.component.order.service.OrderService;
import com.faceye.component.order.service.model.ItemInfo;
import com.faceye.component.order.service.model.OrderConfirmInfo;
import com.faceye.component.order.service.model.OrderConfirmParam;
import com.faceye.component.order.service.model.OrderInfo;
import com.faceye.component.order.service.model.SkuConfirmInfo;
import com.faceye.component.product.entity.Image;
import com.faceye.component.product.entity.Product;
import com.faceye.component.product.entity.ProductSku;
import com.faceye.component.product.model.SkuInfo;
import com.faceye.component.product.service.ImageService;
import com.faceye.component.product.service.ProductSkuService;
import com.faceye.component.product.service.SkuPropertyService;
import com.faceye.component.security.web.entity.User;
import com.faceye.component.security.web.service.UserService;
import com.faceye.component.security.web.util.SecurityUtil;
import com.faceye.component.setting.entity.Shop;
import com.faceye.component.util.OrderCodeGenerator;
import com.faceye.component.weixin.entity.Account;
import com.faceye.component.weixin.service.pay.WeixinPayService;
import com.faceye.component.weixin.service.pay.model.PayProductInfo;
import com.faceye.component.weixin.service.pay.response.UnifiedOrderResponse;
import com.faceye.component.weixin.util.WeixinConstants;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.DateUtil;
import com.querydsl.core.types.Predicate;

/**
 * 模块:订单->com.faceye.compoent.order.service.impl<br>
 * 说明:<br>
 * 实体:订单->com.faceye.component.order.entity.entity.Order 服务实现类<br>
 * 
 * @author haipenge <br>
 *         联系:haipenge@gmail.com<br>
 *         创建日期:2015-6-16 18:57:42<br>
 */
@Service
public class OrderServiceImpl extends BaseMongoServiceImpl<Order, Long, OrderRepository> implements OrderService {

	@Autowired
	private UserService userService = null;
	@Autowired
	private CustomerService customerService = null;
	@Autowired
	private CartItemService cartItemService = null;
	@Autowired
	private ItemService itemService = null;
	@Autowired
	private SkuPropertyService skuPropertyService = null;
	@Autowired
	private InvoiceService invoiceService = null;
	@Autowired
	private InvoiceItemService invoiceItemService = null;
	@Autowired
	private InventoryService inventoryService = null;
	@Autowired
	private AddressService addressService = null;
	@Autowired
	private ProductSkuService productSkuService = null;
	@Autowired
	private WeixinPayService weixinPayService = null;
	@Autowired
	private ImageService imageService = null;
	/**
	 * 微信支付回调URL
	 */
	@Value("#{property['weixin.pay.notify.url']}")
	private String weixinNotifyUrl = "";

	/**
	 * 微信支付服务器IP
	 * 
	 * @param dao
	 */
	@Value("#{property['weixin.pay.server.ip']}")
	private String weixinPayServerIp = "";

	@Autowired
	public OrderServiceImpl(OrderRepository dao) {
		super(dao);
	}

	/**
	 * 数据分页查询
	 * 
	 * @author haipenge <br>
	 *         联系:haipenge@gmail.com<br>
	 *         创建日期:2015-6-16 18:57:42<br>
	 */
	@Override
	public Page<Order> getPage(Map<String, Object> searchParams, int page, int size) {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Order> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Order> builder = new PathBuilder<Order>(entityPath.getType(), entityPath.getMetadata());
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
		Page<Order> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Order>("id") {
			// })
			List<Order> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Order>(items);
		}
		return res;
	}

	/**
	 * 从购物车提交订单 注：可以同时提交多个店铺的购物车
	 */
	@Deprecated
	@Override
	public List<Order> submitOrderFromCart(Long[] cartItemIds, Long addressId) {
		List<Order> orders = new ArrayList<Order>(0);
		if (cartItemIds != null) {
			User user = this.userService.getCurrentLoginUser();
			Customer customer = this.customerService.getCustomerByUser(user);
			for (Long cartItemId : cartItemIds) {
				CartItem cartItem = this.cartItemService.get(cartItemId);
				// 如果购物车条目不是创建状态，不能提交
				if (cartItem.getStatus() != 0) {
					continue;
				}
				ProductSku productSku = cartItem.getProductSku();
				Product product = productSku.getProduct();
				Integer quantity = cartItem.getQuantity();
				Shop shop = productSku.getShop();
				Order order = this.getOrderFromOrders(orders, shop);
				if (order == null) {
					Address address = this.addressService.get(addressId);
					order = new Order();
					order.setCreateDate(new Date());
					order.setCustomer(customer);
					order.setIsPaid(false);
					order.setShop(shop);
					order.setAddress(address);
					// 已下单:下单成功
					order.setStatus(0);
					order.setCode(OrderCodeGenerator.getInstance().genearte());
				}
				order.setTotalFee(order.getTotalFee() + quantity * productSku.getPrice());
				this.save(order);
				orders.add(order);
				// 订单条目
				Item item = new Item();
				item.setCreateDate(new Date());
				item.setOrder(order);
				item.setPrice(productSku.getPrice());
				item.setProductSku(productSku);
				item.setProduct(product);
				item.setQuantity(cartItem.getQuantity());
				item.setShop(shop);
				this.itemService.save(item);
				// 提交后，将购物车条目 状态变更 为已提交:status=1
				cartItem.setStatus(1);
				this.cartItemService.save(cartItem);
			}
		}
		return orders;
	}

	/**
	 * 直接购买
	 */
	@Deprecated
	@Override
	public Order buy(Long productSkuId, Integer quantity, Long addressId) {
		Customer customer = this.customerService.getCurrentLoginCustomer();
		Address address = this.addressService.get(addressId);
		ProductSku productSku = this.productSkuService.get(productSkuId);
		Order order = new Order();
		order.setCreateDate(new Date());
		order.setCustomer(customer);
		order.setAddress(address);
		order.setIsPaid(false);
		order.setShop(productSku.getShop());
		// 已下单:下单成功
		order.setStatus(0);
		order.setCode(OrderCodeGenerator.getInstance().genearte());
		order.setTotalFee(order.getTotalFee() + quantity * productSku.getPrice());
		this.save(order);
		Item item = new Item();
		item.setCreateDate(new Date());
		item.setOrder(order);
		item.setPrice(productSku.getPrice());
		item.setProductSku(productSku);
		item.setProduct(productSku.getProduct());
		item.setQuantity(quantity);
		item.setShop(productSku.getShop());
		this.itemService.save(item);
		return order;
	}

	/**
	 * 确认订单
	 */
	@Override
	public Order confirmOrder(List<OrderConfirmParam> orderConfirmParams, Long addressId) {
		Customer customer = this.customerService.getCurrentLoginCustomer();
		Address address = this.addressService.get(addressId);
		Order order = new Order();
		order.setCreateDate(new Date());
		order.setCustomer(customer);
		order.setAddress(address);
		order.setIsPaid(false);
		// 已下单:下单成功
		order.setStatus(0);
		order.setCode(OrderCodeGenerator.getInstance().genearte());
		this.save(order);
		for (OrderConfirmParam orderConfirmParam : orderConfirmParams) {

			ProductSku productSku = this.productSkuService.get(orderConfirmParam.getProductSkuId());

			order.setShop(productSku.getShop());
			order.setTotalFee(order.getTotalFee() + orderConfirmParam.getQuantity() * productSku.getPrice());
			Item item = new Item();
			item.setCreateDate(new Date());
			item.setOrder(order);
			item.setPrice(productSku.getPrice());
			item.setProductSku(productSku);
			item.setProduct(productSku.getProduct());
			item.setQuantity(orderConfirmParam.getQuantity());
			item.setShop(productSku.getShop());
			this.itemService.save(item);
			// 修改购物车条目状态
			Map cartItemParams = new HashMap();
			cartItemParams.put("EQ|customer.$id", customer.getId());
			cartItemParams.put("EQ|productSku.$id", productSku.getId());
			cartItemParams.put("EQ|quantity", orderConfirmParam.getQuantity());
			List<CartItem> cartItems = this.cartItemService.getPage(cartItemParams, 1, 0).getContent();
			if (CollectionUtils.isNotEmpty(cartItems)) {
				logger.debug(">>FaceYe got cart items size is:" + cartItems.size());
				for (CartItem cartItem : cartItems) {
					cartItem.setStatus(1);
					this.cartItemService.save(cartItem);
				}
			}
		}
		this.save(order);
		return order;
	}

	private Order getOrderFromOrders(List<Order> orders, Shop shop) {
		Order order = null;
		if (CollectionUtils.isNotEmpty(orders)) {
			for (Order o : orders) {
				if (o.getShop().getId().compareTo(shop.getId()) == 0) {
					order = o;
					break;
				}
			}
		}
		return order;
	}

	@Override
	public OrderInfo getOrderInfo(Order order) {
		OrderInfo orderInfo = new OrderInfo();
		// if (CollectionUtils.isNotEmpty(orderInfos)) {
		// for (OrderInfo oi : orderInfos) {
		// if (oi.getOrder().getId().compareTo(order.getId()) == 0) {
		// orderInfo = oi;
		// break;
		// }
		// }
		// }
		// if (orderInfo == null) {
		// orderInfo = new OrderInfo();
		// if (orderInfos!=null) {
		// orderInfos.add(orderInfo);
		// }
		// }
		Map params = new HashMap();
		params.put("EQ|order.$id", order.getId());
		List<Item> items = this.itemService.getPage(params, 1, 0).getContent();
		List<ItemInfo> itemInfos = this.itemService.getItemInfos(items);
		orderInfo.setOrder(order);
		orderInfo.setItemInfos(itemInfos);
		return orderInfo;
	}

	@Override
	public List<OrderInfo> getOrderInfos(List<Order> orders) {
		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>(0);
		if (CollectionUtils.isNotEmpty(orders)) {
			for (Order order : orders) {
				OrderInfo orderInfo = this.getOrderInfo(order, orderInfos);
				if (orderInfo == null) {
					orderInfo = this.getOrderInfo(order);
					orderInfos.add(orderInfo);
				}

			}
		}
		return orderInfos;
	}

	/**
	 * 根据订单，从已有的OrderInfos中取得OrderInfo
	 * 
	 * @todo
	 * @param order
	 * @param orderInfos
	 * @return
	 * @author:@haipenge 联系:haipenge@gmail.com 创建时间:2015年6月21日
	 */
	private OrderInfo getOrderInfo(Order order, List<OrderInfo> orderInfos) {
		OrderInfo orderInfo = null;
		if (CollectionUtils.isNotEmpty(orderInfos)) {
			for (OrderInfo oi : orderInfos) {
				if (oi.getOrder().getId().compareTo(order.getId()) == 0) {
					orderInfo = oi;
					break;
				}
			}
		}
		return orderInfo;
	}

	/**
	 * 支付
	 */
	@Override
	public boolean pay(Long orderId) {
		boolean res = false;
		Order order = this.get(orderId);
		// 是有已创建的订单可被支付
		if (order.getStatus() == 0) {
			res = this.afterPaySuccess(order);
		}
		return res;
	}

	/**
	 * 支付成功后
	 * 
	 * @todo
	 * @param orderId
	 * @author:@haipenge 联系:haipenge@gmail.com 创建时间:2015年6月18日
	 */
	private boolean afterPaySuccess(Order order) {
		// 支付成功，变更订单状态
		boolean res = true;
		order.setIsPaid(true);
		order.setPayDate(new Date());
		order.setPayWay("");
		// 支付成功
		order.setStatus(1);
		this.save(order);
		// 生成出库单
		OrderInfo orderInfo = this.getOrderInfo(order);
		Shop shop = order.getShop();
		List<ItemInfo> itemInfos = orderInfo.getItemInfos();
		Invoice invoice = new Invoice();
		invoice.setShop(shop);
		// 出库
		invoice.setType(0);
		invoice.setOrder(order);
		invoice.setCreateDate(new Date());
		this.invoiceService.save(invoice);
		for (ItemInfo itemInfo : itemInfos) {
			ProductSku productSku = itemInfo.getItem().getProductSku();
			Integer quantity = itemInfo.getItem().getQuantity();
			Map searchParams = new HashMap();
			searchParams.put("EQ|productSku.$id", productSku.getId());
			// 减库存，增加锁定数量
			List inventories = this.inventoryService.getPage(searchParams, 1, 0).getContent();
			if (CollectionUtils.isEmpty(inventories)) {
				logger.error(">>FaceYe product sku id :" + productSku.getId() + ",product:" + productSku.getProduct().getName() + " 无库存记录.");
			} else {
				List<Inventory> inventoriess = this.inventoryService.getPage(searchParams, 1, 0).getContent();
				if (CollectionUtils.isNotEmpty(inventoriess)) {
					Inventory inventory = inventoriess.get(0);
					inventory.setAmount(inventory.getAmount() - quantity);
					inventory.setLockedCount(inventory.getLockedCount() + quantity);
					this.inventoryService.save(inventory);
					// 生成出库配送条目
					InvoiceItem invoiceItem = new InvoiceItem();
					invoiceItem.setInvoice(invoice);
					invoiceItem.setProduct(productSku.getProduct());
					invoiceItem.setProductSku(productSku);
					invoiceItem.setQuantity(quantity);
					invoiceItem.setShop(shop);
					invoiceItem.setCreateDate(new Date());
					invoiceItem.setItem(itemInfo.getItem());
					this.invoiceItemService.save(invoiceItem);
				}
			}
		}
		return res;
	}

	/**
	 * 支付失败后
	 * 
	 * @todo
	 * @param orderId
	 * @author:@haipenge 联系:haipenge@gmail.com 创建时间:2015年6月18日
	 */
	private boolean afterPayFailure(Long orderId) {
		boolean res = false;
		Order order = this.get(orderId);
		// 支付失败
		order.setStatus(11);
		this.save(order);
		return res;
	}

	@Override
	public Order getOrderByCode(String code) {
		return this.dao.getOrderByCode(code);
	}

	@Override
	public UnifiedOrderResponse callWeixinUnifiedOrder(Order order, String tradeType, String openid) {
		logger.debug(">>FaceYe ->to pay order id is:" + order.getId() + ",code :" + order.getCode());
		OrderInfo orderInfo = this.getOrderInfo(order);
		Shop shop = order.getShop();
		logger.debug(">>FaceYe to pay shop is:" + shop.getName());
		// User user = shop.getUser();
		Account account = shop.getAccount();
		PayProductInfo payProductInfo = new PayProductInfo();
		payProductInfo.setNotifyUrl(weixinNotifyUrl);
		payProductInfo.setAttach("Order ID:" + order.getId() + ",Order Num:" + order.getCode() + ",shop ID:" + shop.getId());
		// 商品或支付单简要描述 32个字
		String body = this.resetOrderBody(orderInfo);
		payProductInfo.setBody(body);
		// 商品名称明细列表 8192字
		payProductInfo.setDetail(body);
		// 商品标记，代金券或立减优惠功能的参数
		payProductInfo.setGoodsTag("");
		payProductInfo.setNotifyUrl(this.weixinNotifyUrl);
		// 扫码支付,不需要填写openid
		payProductInfo.setOpenid(openid);
		// 32位
		// 商户支付的订单号由商户自定义生成，微信支付要求商户订单号保持唯一性（建议根据当前系统时间加随机序列来生成订单号）。重新发起一笔支付要使用原订单号，避免重复支付；已支付过或已调用关单、撤销（请见后文的API列表）的订单号不能重新发起支付
		payProductInfo.setOutTradeNo(order.getCode());
		// trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
		payProductInfo.setProductId("" + order.getId());
		// APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
		if (StringUtils.equals(tradeType, WeixinConstants.PAY_TRADE_TYPE_NATIVE)) {
			payProductInfo.setSpbillCreateIp(this.weixinPayServerIp);
		} else {
			payProductInfo.setSpbillCreateIp(SecurityUtil.getCurrentUserIp());
		}
		// 交易结束时间
		payProductInfo.setTimeExpire(DateUtil.formatDate(new Date(order.getCreateDate().getTime() + 24 * 60 * 60 * 1000L), "yyyyMMddHHmmss"));
		// 交易开始时间
		payProductInfo.setTimeStart(DateUtil.formatDate(order.getCreateDate(), "yyyyMMddHHmmss"));
		payProductInfo.setTotalFee(order.getTotalFee());
		UnifiedOrderResponse unifiedOrderResponse = this.weixinPayService.unifiedOrder(account.getAppId(), payProductInfo, tradeType);
		return unifiedOrderResponse;
	}

	private String resetOrderBody(OrderInfo orderInfo) {
		StringBuilder body = new StringBuilder();
		String res = "";
		if (orderInfo != null) {
			List<ItemInfo> itemInfos = orderInfo.getItemInfos();
			for (ItemInfo itemInfo : itemInfos) {
				body.append(itemInfo.getItem().getProduct().getName());
				body.append(",");
			}
			body.deleteCharAt(body.lastIndexOf(","));
		}
		if (StringUtils.length(body) > 32) {
			res = StringUtils.substring(body.toString(), 0, 29);
			res += "...";
		} else {
			res = body.toString();
		}
		//
		return res;
	}

	@Override
	public OrderConfirmInfo getOrderConfirmInfoByCartItemIds(Long... cartItemId) {
		OrderConfirmInfo orderConfirmInfo = new OrderConfirmInfo();
		for (Long id : cartItemId) {
			CartItem cartItem = this.cartItemService.get(id);
			ProductSku productSku = cartItem.getProductSku();
			Integer quantity = cartItem.getQuantity();
			SkuInfo skuInfo = this.productSkuService.getSkuInfo(productSku);
			SkuConfirmInfo skuConfirmInfo = new SkuConfirmInfo();
			skuConfirmInfo.setQuantity(quantity);
			skuConfirmInfo.setSkuInfo(skuInfo);
			Map params = new HashMap();
			params.put("EQ|product.$id", productSku.getProduct().getId());
			params.put("boolean|isDefault", true);
			List<Image> images = this.imageService.getPage(params, 1, 0).getContent();
			skuConfirmInfo.setImages(images);
			orderConfirmInfo.getSkuConfirmInfos().add(skuConfirmInfo);
		}
		return orderConfirmInfo;
	}

	@Override
	public OrderConfirmInfo getOrderConfirmInfo(Long productId, String dpvids, Integer quantity) {
		SkuInfo skuInfo = this.productSkuService.getSkuInfo(productId, dpvids);
		OrderConfirmInfo orderConfirmInfo = new OrderConfirmInfo();
		SkuConfirmInfo skuConfirmInfo = new SkuConfirmInfo();
		skuConfirmInfo.setSkuInfo(skuInfo);
		skuConfirmInfo.setQuantity(quantity);
		Map params = new HashMap();
		params.put("EQ|product.$id", productId);
		params.put("boolean|isDefault", true);
		List<Image> images = this.imageService.getPage(params, 1, 0).getContent();
		skuConfirmInfo.setImages(images);
		orderConfirmInfo.getSkuConfirmInfos().add(skuConfirmInfo);
		return orderConfirmInfo;
	}

}
/** @generate-service-source@ **/
