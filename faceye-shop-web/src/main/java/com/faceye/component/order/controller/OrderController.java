package com.faceye.component.order.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import com.faceye.component.order.service.model.ItemInfo;
import com.faceye.component.order.service.model.OrderInfo;
import com.faceye.component.product.entity.Product;
import com.faceye.component.product.model.SkuInfo;
import com.faceye.component.product.service.ProductService;
import com.faceye.component.product.service.ProductSkuService;
import com.faceye.component.security.entity.User;
import com.faceye.component.setting.controller.BaseShopController;
import com.faceye.component.setting.entity.Shop;
import com.faceye.component.setting.service.ShopService;
import com.faceye.component.weixin.entity.Account;
import com.faceye.component.weixin.service.AccountService;
import com.faceye.component.weixin.service.pay.WeixinPayService;
import com.faceye.component.weixin.service.pay.model.PayProductInfo;
import com.faceye.component.weixin.service.pay.request.NotifyRequest;
import com.faceye.component.weixin.service.pay.response.NotifyResponse;
import com.faceye.component.weixin.service.pay.response.UnifiedOrderResponse;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.DateUtil;
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
	private CustomerService customerService = null;
	@Autowired
	private AccountService accountService = null;
	@Autowired
	private WeixinPayService weixinPayService = null;
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
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
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
		Product product = this.productService.get(productId);
		Map addressSearchParams = new HashMap();
		addressSearchParams.put("EQ|customer.$id", customer.getId());
		addressSearchParams.put("boolean|isRemoved", false);
		List<Address> addresses = this.addressService.getPage(addressSearchParams, 1, 0).getContent();
		model.addAttribute("addresses", addresses);
		SkuInfo skuInfo = this.productSkuService.getSkuInfo(productId, dpvids);
		model.addAttribute("skuInfo", skuInfo);
		model.addAttribute("quantity", quantity);
		return "order.order.direct.buy";
	}

	/**
	 * 直接购买：从商品详情页直接购买
	 * @todo
	 * @param request
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年9月29日
	 */
	@RequestMapping("/buy")
	public String buy(HttpServletRequest request, @RequestParam(required = true) Long productSkuId,
			@RequestParam(required = true) Integer quantity, @RequestParam(required = true) Long addressId,Model model) {
        Order order=this.service.buy(productSkuId, quantity, addressId);
        UnifiedOrderResponse unifiedOrderResponse =this.service.callWeixinUnifiedOrder(order,"","");
        model.addAttribute("unifiedOrderResponse", unifiedOrderResponse);
//        List<Order> orders=new ArrayList<Order>(0);
//        orders.add(order);
//        List<OrderInfo> orderInfos = this.service.getOrderInfos(orders);
//		model.addAttribute("orderInfos", orderInfos);
		return "order.order.to.pay";
	}

	/**
	 * 去支付,微信支付,扫码支付
	 * @todo
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年7月9日
	 */
	@RequestMapping("/toPay")
	public String toPay(@RequestParam(required = true) Long orderId, Model model) {
		logger.debug(">>FaceYe --> User 2 Pay now.");
		Order order = this.service.get(orderId);
		model.addAttribute("order", order);
		logger.debug(">>FaceYe ->to pay order id is:"+order.getId()+",code :"+order.getCode());
		OrderInfo orderInfo = this.service.getOrderInfo(order);
		model.addAttribute("orderInfo", orderInfo);
		Shop shop = order.getShop();
		logger.debug(">>FaceYe to pay shop is:"+shop.getName());
//		User user = shop.getUser();
		Account account = shop.getAccount();
		UnifiedOrderResponse unifiedOrderResponse = this.service.callWeixinUnifiedOrder(order,"","");
		model.addAttribute("unifiedOrderResponse", unifiedOrderResponse);
		model.addAttribute("account", account);
		return "order.order.to.pay";
	}

	private String resetOrderBody(Order order) {
		StringBuilder body = new StringBuilder();
		String res = "";
		if (order != null) {
			OrderInfo orderInfo = this.service.getOrderInfo(order);
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
//		try {
//			res=URLEncoder.encode(res, "UTF-8");
//			logger.debug(">>FaceYe after encoder is:"+res);
////			res=new String(res.getBytes("UTF-8"),"gbk");
//		} catch (UnsupportedEncodingException e) {
//			logger.error(">>FaceYe throws Exception: --->",e);
//		}
//		res=chineseToUnicode(res);
//		try {
//			try {
//				MessageDigest md = MessageDigest.getInstance("MD5");
//				res=new String (md.digest(res.getBytes("UTF-8")),"UTF-8");
//			} catch (NoSuchAlgorithmException e) {
//				logger.error(">>FaceYe throws Exception: --->",e);
//			}
////			res=new String(res.getBytes(),"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			logger.error(">>FaceYe throws Exception: --->",e);
//		}
		return res;
	}
	/**
	 * 汉字 转unicode
	 * @todo
	 * @param source
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年10月1日
	 */
	private String chineseToUnicode(String str){  
        String result="";  
        for (int i = 0; i < str.length(); i++){  
            int chr1 = (char) str.charAt(i);  
            if(chr1>=19968&&chr1<=171941){//汉字范围 \u4e00-\u9fa5 (中文)  
                result+="\\u" + Integer.toHexString(chr1);  
            }else{  
                result+=str.charAt(i);  
            }  
        }  
        return result;  
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
