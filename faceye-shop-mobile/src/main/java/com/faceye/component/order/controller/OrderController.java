package com.faceye.component.order.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceye.component.customer.entity.Address;
import com.faceye.component.customer.entity.Customer;
import com.faceye.component.customer.service.AddressService;
import com.faceye.component.customer.service.CustomerService;
import com.faceye.component.order.entity.Order;
import com.faceye.component.order.service.OrderService;
import com.faceye.component.order.service.model.OrderConfirmInfo;
import com.faceye.component.order.service.model.OrderConfirmParam;
import com.faceye.component.order.service.model.OrderInfo;
import com.faceye.component.product.service.ProductService;
import com.faceye.component.product.service.ProductSkuService;
import com.faceye.component.security.web.entity.User;
import com.faceye.component.security.web.service.UserService;
import com.faceye.component.setting.controller.BaseShopController;
import com.faceye.component.setting.entity.Shop;
import com.faceye.component.setting.service.ShopService;
import com.faceye.component.weixin.entity.Account;
import com.faceye.component.weixin.entity.JSAPITicket;
import com.faceye.component.weixin.service.AccountService;
import com.faceye.component.weixin.service.api.MsgApi;
import com.faceye.component.weixin.service.message.request.WeixinConfigRequest;
import com.faceye.component.weixin.service.pay.WeixinPayService;
import com.faceye.component.weixin.service.pay.request.JSAPIPayRequest;
import com.faceye.component.weixin.service.pay.request.JSAPIPayRequestObject;
import com.faceye.component.weixin.service.pay.request.NotifyRequest;
import com.faceye.component.weixin.service.pay.response.NotifyResponse;
import com.faceye.component.weixin.service.pay.response.UnifiedOrderResponse;
import com.faceye.component.weixin.util.WeixinConstants;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.GlobalEntity;
import com.faceye.feature.util.JaxbMapper;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.http.ResponseUtil;

/**
 * 模块:订单->com.faceye.compoent.order.controller<br>
 * 说明:<br>
 * 实体:订单:com.faceye.component.order.entity.entity.Order<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
 * 创建日期:2015-6-16 18:57:42<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/order/order")
public class OrderController extends BaseShopController<Order, Long, OrderService> {
	@Autowired
	private ShopService shopService = null;
	@Autowired
	private UserService userService = null;
	@Autowired
	private CustomerService customerService = null;
	@Autowired
	private AccountService accountService = null;
	@Autowired
	private WeixinPayService weixinPayService = null;
	@Autowired
	private MsgApi msgApi = null;
	@Autowired
	private AddressService addressService = null;
	@Autowired
	private ProductSkuService productSkuService = null;
	@Autowired
	private ProductService productService = null;
	@Value("#{property['weixin.pay.notify.url']}")
	private String weixinNotifyUrl = "";

	@Autowired
	public OrderController(OrderService service) {
		super(service);
	}

	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br> 
	 * haipenge@gmail.com <br>
	 * 创建日期2015-6-16 18:57:42<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		beforeInput(model, request);
		Customer customer = this.customerService.getCurrentLoginCustomer();
		if (customer != null) {
			searchParams.put("EQ|customer.$id", customer.getId());
			Page<Order> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
			List<OrderInfo> orderInfos = this.service.getOrderInfos(page.getContent());
			model.addAttribute("page", page);
			model.addAttribute("orderInfos", orderInfos);
		}
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "order.order.manager";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-16 18:57:42<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			beforeInput(model, request);
			Order order = this.service.get(id);
			model.addAttribute("order", order);
		}
		return "order.order.update";
	}

	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-16 18:57:42<br>
	 */
	@RequestMapping(value = "/input")
	public String input(Order order, Model model, HttpServletRequest request) {
		beforeInput(model, request);
		return "order.order.update";
	}

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-16 18:57:42<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Order order, BindingResult bindingResult, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request);
			return "order.order.update";
		} else {
			this.beforeSave(order, request);
			this.service.save(order);
			this.afterSave(order, request);
			return "redirect:/order/order/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-16 18:57:42<br>
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if (id != null) {
			Order order = this.service.get(id);
			// 设置为作废
			order.setStatus(4);
			this.service.save(order);
		}
		return "redirect:/order/order/home";
	}

	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-16 18:57:42<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required = true) String ids, RedirectAttributes redirectAttributes) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				this.service.remove(Long.parseLong(id));
			}
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

	/**
	 * 取得数据明细<br>
	 * @todo<br>
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-16 18:57:42<br>
	 */
	@RequestMapping("/detail")
	public String detail(@RequestParam(required=true) Long id, Model model) {
		if (id != null) {
			Order order = this.service.get(id);
			model.addAttribute("order", order);
			OrderInfo orderInfo = this.service.getOrderInfo(order);
			model.addAttribute("orderInfo", orderInfo);
		}
		return "order.order.detail";
	}

	/**
	 * 从购物车提交订单并转向支付
	 * @todo
	 * @param id->cartItem.id
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月17日
	 */
	@Deprecated
	@RequestMapping("/submitOrderFromCart")
	public String submitOrderFromCart(HttpServletRequest request, Long[] cartItemId, @RequestParam(required = true) Long addressId,
			Model model) {
		List<Order> orders = this.service.submitOrderFromCart(cartItemId, addressId);
		List<OrderInfo> orderInfos = this.service.getOrderInfos(orders);
		model.addAttribute("orderInfos", orderInfos);
		return "order.order.submit.cartItem.2.order.result";
	}

	/**
	 * 点击直接购买后转向的页面
	 * @todo
	 * @param request
	 * @param productId
	 * @param quantity
	 * @param dpvids
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年9月29日
	 */
	@RequestMapping("/direct2Buy")
	public String direct2Buy(HttpServletRequest request, @RequestParam(required = true) Long productId,
			@RequestParam(required = true) Integer quantity, @RequestParam(required = false) String dpvids, Model model) {
		Customer customer = this.customerService.getCurrentLoginCustomer();
		// Product product = this.productService.get(productId);
		Map addressSearchParams = new HashMap();
		addressSearchParams.put("EQ|customer.$id", customer.getId());
		addressSearchParams.put("boolean|isRemoved", false);
		List<Address> addresses = this.addressService.getPage(addressSearchParams, 1, 0).getContent();
		model.addAttribute("addresses", addresses);
		// SkuInfo skuInfo = this.productSkuService.getSkuInfo(productId, dpvids);
		// model.addAttribute("skuInfo", skuInfo);
		// model.addAttribute("quantity", quantity);
		OrderConfirmInfo orderConfirmInfo = this.service.getOrderConfirmInfo(productId, dpvids, quantity);
		// SkuConfirmInfo skuConfirmInfo=new SkuConfirmInfo();
		// skuConfirmInfo.setSkuInfo(skuInfo);
		// skuConfirmInfo.setQuantity(quantity);
		// orderConfirmInfo.getSkuConfirmInfos().add(skuConfirmInfo);
		model.addAttribute("orderConfirmInfo", orderConfirmInfo);
		// return "order.order.direct.buy";
		GlobalEntity globalEntity = new GlobalEntity();
		globalEntity.setTitle(this.getI18N("order.order.pay"));
		model.addAttribute("global", globalEntity);
		return "order.order.confirm";
	}

	/**
	 * 从购物车购买（去结算)
	 * @todo
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月22日
	 */
	@RequestMapping("/confirmCart")
	public String toPay(Address address, @RequestParam(required = true) Long[] cartItemId, Model model, HttpServletRequest request) {
		// List<CartItemInfo> cartItemInfos = this.service.getCartItemInfos(cartItemId);
		// model.addAttribute("cartItemInfos", cartItemInfos);
		// List<ShopCartItemInfo> shopCartItemInfos = ShopCartBuilder.builder(cartItemInfos);
		// model.addAttribute("shopCartItemInfos", shopCartItemInfos);
		Customer customer = this.customerService.getCurrentLoginCustomer();
		Map addressSearchParams = new HashMap();
		addressSearchParams.put("EQ|customer.$id", customer.getId());
		addressSearchParams.put("boolean|isRemoved", false);
		List<Address> addresses = this.addressService.getPage(addressSearchParams, 1, 0).getContent();
		model.addAttribute("addresses", addresses);
		OrderConfirmInfo orderConfirmInfo = this.service.getOrderConfirmInfoByCartItemIds(cartItemId);
		model.addAttribute("orderConfirmInfo", orderConfirmInfo);
		GlobalEntity globalEntity = new GlobalEntity();
		globalEntity.setTitle(this.getI18N("order.order.pay"));
		model.addAttribute("global", globalEntity);
		return "order.order.confirm";
	}

	/**
	 * 支付：
	 * 入口路径：1.商品详情->直接购买->微信支付
	 *          2.购物车->去结算->微信支付
	 * 
	 * @todo
	 * @param request
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * quantity:格式-> productSkuID_quantity
	 * 创建时间:2015年9月29日
	 */
	@RequestMapping("/orderConfirm")
	@ResponseBody
	public JSAPIPayRequestObject orderConfirm(HttpServletRequest request, @RequestParam(required = true) String productSkuIds,
			@RequestParam(required = true) String quantities, @RequestParam(required = true) Long addressId, Model model) {
		Map params = HttpUtil.getRequestParams(request);
		JSAPIPayRequestObject jsapiPayRequestObject = null;
		List<OrderConfirmParam> orderConfirmParams = new ArrayList<OrderConfirmParam>(0);
		if (StringUtils.isNotEmpty(productSkuIds)) {
			String[] productSkuId = productSkuIds.split(",");
			for (String id : productSkuId) {
				if (StringUtils.isEmpty(id)) {
					continue;
				}
				String[] quantity = StringUtils.split(quantities, ",");
				for (String quantityStr : quantity) {
					if (StringUtils.isEmpty(quantityStr)) {
						continue;
					}
					if (StringUtils.isNotEmpty(quantityStr) && StringUtils.contains(quantityStr, "_")) {
						String[] strArray = StringUtils.split(quantityStr, "_");
						String quantityStrSkuId = strArray[0];
						Integer trueQuantity = Integer.parseInt(strArray[1]);
						if (StringUtils.equals(quantityStrSkuId, id.toString())) {
							if (trueQuantity > 0) {
								OrderConfirmParam orderConfirmParam = new OrderConfirmParam();
								orderConfirmParam.setProductSkuId(Long.parseLong(id));
								orderConfirmParam.setQuantity(trueQuantity);
								orderConfirmParams.add(orderConfirmParam);
							}
						}
					}
				}
			}
		}
		if (CollectionUtils.isNotEmpty(orderConfirmParams)) {
			Order order = this.service.confirmOrder(orderConfirmParams, addressId);
			jsapiPayRequestObject = this.buildJSAPIRequestObject(order, request);
		}
		return jsapiPayRequestObject;
	}

	/**
	 * 去支付,微信支付,js-sdk
	 * @todo
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年7月9日
	 */
	@RequestMapping("/toPay")
	@ResponseBody
	public JSAPIPayRequestObject toPay(@RequestParam(required = true) Long orderId, HttpServletRequest request, Model model) {
		logger.debug(">>FaceYe --> User 2 Pay now.");
		Order order = this.service.get(orderId);
		JSAPIPayRequestObject jsapiPayRequestObject = this.buildJSAPIRequestObject(order, request);
		// model.addAttribute("account", account);
		return jsapiPayRequestObject;
	}

	private JSAPIPayRequestObject buildJSAPIRequestObject(Order order, HttpServletRequest request) {
		JSAPIPayRequestObject jsapiPayRequestObject = null;
		Map params = HttpUtil.getRequestParams(request);
		String url = MapUtils.getString(params, "url");
		User user = this.userService.getCurrentLoginUser();
		UnifiedOrderResponse unifiedOrderResponse = this.service.callWeixinUnifiedOrder(order, WeixinConstants.PAY_TRADE_TYPE_JSAPI,
				user.getWeixinOpenId());
		if (unifiedOrderResponse != null) {
			Account account = this.accountService.getAccountByAppId(unifiedOrderResponse.getAppid());
			if (account != null) {
				JSAPIPayRequest jsapiPayRequest = this.weixinPayService.buildJSAPITicketRequest(account, unifiedOrderResponse);
				JSAPITicket jsapiTicket = this.msgApi.getJSAPITicket(account);
				WeixinConfigRequest weixinConfigRequest = this.weixinPayService.buildWeixinConfigRequest(jsapiTicket, url);
				jsapiPayRequestObject = new JSAPIPayRequestObject();
				jsapiPayRequestObject.setJsapiPayRequest(jsapiPayRequest);
				jsapiPayRequestObject.setWeixinConfigRequest(weixinConfigRequest);
				jsapiPayRequestObject.setSuccessRedirectUrl("/order/order/detail?id=" + order.getId());
				jsapiPayRequestObject.setCancelRedirectUrl("/order/order/detail?id=" + order.getId());
				jsapiPayRequestObject.setFailRedirectUrl("/order/order/detail?id=" + order.getId());
			}
		}
		return jsapiPayRequestObject;
	}

	/**
	 * 支付 回调地址
	 * @todo
	 * @param request
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月18日
	 */
	@RequestMapping("/weixinPayNotify")
	public void weixinPayNotify(HttpServletRequest request, HttpServletResponse response) {
		BufferedReader in = null;
		String inputLine = "";
		NotifyResponse notifyResponse = null;
		StringBuilder receiveData = new StringBuilder("");
		try {
			in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			while ((inputLine = in.readLine()) != null) {
				receiveData.append(inputLine);
			}
			NotifyRequest notifyRequest = JaxbMapper.fromXml(receiveData.toString(), NotifyRequest.class);
			if (notifyRequest != null && StringUtils.equals(notifyRequest.getReturn_code(), "SUCCESS")) {
				// 订单编号
				String code = notifyRequest.getOut_trade_no();
				Order order = this.service.getOrderByCode(code);
				this.service.pay(order.getId());
				this.weixinPayService.payNotify(true, "OK");
			} else {
				this.weixinPayService.payNotify(false, "FAIL");
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(">>FaceYe throws Exception: --->", e);
		} catch (IOException e) {
			logger.error(">>FaceYe throws Exception: --->", e);
		} finally {
			if (notifyResponse == null) {
				notifyResponse = this.weixinPayService.payNotify(false, "FAIL");
			}
		}
		String xml = JaxbMapper.toXml(notifyResponse);
		ResponseUtil.xml(response, xml);
		// return "order.order.pay.success";
	}

	// /////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-16 18:57:42<br>
	 */
	protected void beforeInput(Model model, HttpServletRequest request) {
		List<Shop> shops = this.shopService.getAll();
		model.addAttribute("shops", shops);
		List<Customer> customers = this.customerService.getAll();
		model.addAttribute("customers", customers);
	}

	/**
	 * 保存前的数据回调
	 * @todo
	 * @param order
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-16 18:57:42
	 */
	protected void beforeSave(Order order, HttpServletRequest request) {
	}

	/**
	 * 保存后的数据回调
	 * @todo
	 * @param order
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-16 18:57:42
	 */
	protected void afterSave(Order order, HttpServletRequest request) {

	}

}
