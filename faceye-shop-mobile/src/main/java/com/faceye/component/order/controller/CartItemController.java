package com.faceye.component.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceye.component.customer.entity.Customer;
import com.faceye.component.customer.service.AddressService;
import com.faceye.component.order.entity.CartItem;
import com.faceye.component.order.service.CartItemService;
import com.faceye.component.order.service.OrderService;
import com.faceye.component.order.service.model.CartInfo;
import com.faceye.component.order.service.model.CartItemInfo;
import com.faceye.component.order.service.model.ShopCartBuilder;
import com.faceye.component.order.service.model.ShopCartItemInfo;
import com.faceye.component.setting.controller.BaseShopController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.GlobalEntity;
import com.faceye.feature.util.http.HttpUtil;

/**
 * 模块:order<br>
 * 实体:CartItem<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/order/cartItem")
public class CartItemController extends BaseShopController<CartItem, Long, CartItemService> {
	@Autowired
	private AddressService addressService = null;
	@Autowired
	private OrderService orderService=null;

	@Autowired
	public CartItemController(CartItemService service) {
		super(service);
	}

	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		Customer customer=this.customerService.getCurrentLoginCustomer();
		String uid = customer.getUid();
		if (StringUtils.isNotEmpty(uid)) {
			List<CartItemInfo> cartItemInfos = this.service.getCartItemInfos(uid);
			List<ShopCartItemInfo> shopCartItemInfos = ShopCartBuilder.builder(cartItemInfos);
			model.addAttribute("shopCartItemInfos", shopCartItemInfos);
		}
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("order.cart"));
		model.addAttribute("global", global);
		return "order.cartItem.manager";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			beforeInput(model, request);
			CartItem entity = this.service.get(id);
			model.addAttribute("cartItem", entity);
		}
		return "order.cartItem.update";
	}

	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * @param model<br>
	 * @return<br>
	 * @author:@haipenge<br>
	 * haipenge@gmail.com<br>
	 * 2014年5月27日<br>
	 */
	@RequestMapping(value = "/input")
	public String input(CartItem cartItem, Model model, HttpServletRequest request) {
		beforeInput(model, request);
		return "order.cartItem.update";
	}

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid CartItem cartItem, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request);
			return "order.cartItem.update";
		} else {
			this.beforeSave(cartItem, request);
			this.service.save(cartItem);
			return "redirect:/order/cartItem/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if (id != null) {
			CartItem cartItem = this.service.get(id);
			if (null != cartItem) {
				// 如果删除，变更购物车条目状态:status = 2(删除）
				cartItem.setStatus(2);
				this.service.save(cartItem);
			}
			// this.service.remove(id);
		}
		return "redirect:/order/cartItem/home";
	}

	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required = true) String ids, RedirectAttributes redirectAttributes) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				CartItem cartItem = this.service.get(Long.parseLong(id));
				if (cartItem != null) {
					// 如果删除，变更购物车条目状态:status = 2(删除）
					cartItem.setStatus(2);
					this.service.save(cartItem);
				}
				// this.service.remove(Long.parseLong(id));
			}
		}
		return AjaxResult.getInstance().buildDefaultResult(true, "选择商品已移除");
	}

	/**
	 * 取得数据明细<br>
	 * @todo<br>
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:@haipenge<br>
	 * haipenge@gmail.com<br>
	 * 2014年5月26日<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			CartItem entity = this.service.get(id);
			model.addAttribute("cartItem", entity);
		}
		return "order.cartItem.detail";
	}

	@RequestMapping("/addProductSku2Cart")
	@ResponseBody
	public CartInfo addProductSku2Cart(HttpServletRequest request, HttpServletResponse response) {
		Map params = HttpUtil.getRequestParams(request);
		Long productId = MapUtils.getLong(params, "productId");
		String dpvids = MapUtils.getString(params, "dpvids");
		Integer quantity = MapUtils.getInteger(params, "quantity");
		String uid = this.customerService.getCurrentLoginCustomer().getUid();
		this.service.addProductSku2Cart(uid, productId, dpvids, quantity);
		CartInfo cartInfo = this.cartService.getCartInfo(uid);
		return cartInfo;
	}
	
	/**
	 * 计算购物车中选中商品的总金额
	 * @todo
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年12月5日
	 */
	@RequestMapping("/computeSelectedCartItemsTotalFee")
	@ResponseBody
	public String computeSelectedCartItemsTotalFee(@RequestParam(required=true)String cartItemIds){
		String res="";
		String [] idArray=StringUtils.split(cartItemIds,",");
		List<Long> ids=new ArrayList<Long>(0);
		for(String id:idArray){
			if(StringUtils.isNotEmpty(id)){
				ids.add(Long.parseLong(id));
			}
		}
		List<CartItemInfo> cartItemInfos=this.service.getCartItemInfos(ids.toArray(new Long[ids.size()]));
		Integer totalFee=0;
		Float totalFeeYuan=0F;
		if(CollectionUtils.isNotEmpty(cartItemInfos)){
			for(CartItemInfo cartItemInfo:cartItemInfos){
				
				totalFee+=cartItemInfo.getCartItem().getProductSku().getPrice()*cartItemInfo.getCartItem().getQuantity();
			}
		}
		if(totalFee!=0){
			totalFeeYuan=new Float(totalFee/100);
		}
		res="{\"totalFeeYuan\":\""+totalFeeYuan+"\"}";
		return res;
	}
	

	

	// /////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:@haipenge<br>
	 * haipenge@gmail.com<br>
	 * 2015年4月5日<br>
	 */
	protected void beforeInput(Model model, HttpServletRequest request) {

	}

	/**
	 *
	 *保存数据前的回调函数
	 */
	protected void beforeSave(CartItem cartItem, HttpServletRequest request) {

	}

}
