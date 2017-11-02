package com.faceye.component.order.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.faceye.component.customer.entity.Customer;
import com.faceye.component.customer.service.CustomerService;
import com.faceye.component.order.entity.Order;
import com.faceye.component.order.model.OrderInfo;
import com.faceye.component.order.service.OrderService;
import com.faceye.component.setting.entity.Shop;
import com.faceye.component.setting.service.ShopService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

/**
 * 模块:订单->com.faceye.compoent.order.controller<br>
 * 说明:<br>
 * 实体:订单:com.faceye.component.order.entity.entity.Order<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2015-6-13 11:31:36<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/order/order")
public class OrderController extends BaseController<Order, Long, OrderService> {
	@Autowired
	private ShopService shopService = null;
	@Autowired
	private CustomerService customerService = null;

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
	 * 创建日期2015-6-13 11:31:36<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		beforeInput(model, request);
		Shop shop = this.shopService.getCurrentUserShop();
		if (shop != null) {
			searchParams.put("EQ|shop.$id", shop.getId());
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
	 * 发货<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:36<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, RedirectAttributesModelMap model, HttpServletRequest request) {
		if (id != null) {
			// beforeInput(model, request);
			Order entity = this.service.get(id);
			if (entity != null) {
				// 设置为发货
				if (entity.getStatus() == 1) {
					entity.setStatus(2);
					this.service.save(entity);
					MessageBuilder.getInstance().setMessage(model, "订单 "+entity.getCode()+" 已发货");
				}
			}
//			model.addAttribute("order", entity);
		}
		return "redirect:/order/order/home";
	}

	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:36<br>
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
	 * 创建日期:2015-6-13 11:31:36<br>
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
	 * 创建日期:2015-6-13 11:31:36<br>
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if (id != null) {
			Order order = this.service.get(id);
			// 状态设置为作废:4
			if (order.getStatus() == 0) {
				order.setStatus(4);
				this.service.save(order);
			}
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
	 * 创建日期:2015-6-13 11:31:36<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required = true) String ids, RedirectAttributes redirectAttributes) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				Order order = this.service.get(Long.parseLong(id));
				// 状态设置为作废:4
				if (order.getStatus() == 0) {
					order.setStatus(4);
					this.service.save(order);
				}
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
	 * 创建日期:2015-6-13 11:31:36<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			Order entity = this.service.get(id);
			OrderInfo orderInfo = this.service.getOrderInfo(entity,null);
			model.addAttribute("orderInfo", orderInfo);
		}
		return "order.order.detail";
	}

	// /////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:36<br>
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
	 * 创建日期:2015-6-13 11:31:36
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
	 * 创建日期:2015-6-13 11:31:36
	 */
	protected void afterSave(Order order, HttpServletRequest request) {

	}

}
