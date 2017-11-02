package com.faceye.component.inventory.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
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

import com.faceye.component.inventory.entity.Invoice;
import com.faceye.component.inventory.model.InvoiceInfo;
import com.faceye.component.inventory.service.InvoiceService;
import com.faceye.component.order.entity.Order;
import com.faceye.component.order.service.OrderService;
import com.faceye.component.setting.entity.Shop;
import com.faceye.component.setting.service.ShopService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;

/**
 * 模块:库存->com.faceye.compoent.inventory.controller<br>
 * 说明:<br>
 * 实体:库存单据:com.faceye.component.inventory.entity.entity.Invoice<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2015-6-13 11:31:37<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/inventory/invoice")
public class InvoiceController extends BaseController<Invoice, Long, InvoiceService> {
	@Autowired
	private ShopService shopService = null;
	@Autowired
	private OrderService orderService = null;

	@Autowired
	public InvoiceController(InvoiceService service) {
		super(service);
	}

	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br> 
	 * haipenge@gmail.com <br>
	 * 创建日期2015-6-13 11:31:37<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		beforeInput(model, request);
		Shop shop = this.shopService.getCurrentUserShop();
		if (shop != null) {
			searchParams.put("EQ|shop.$id", shop.getId());
			Page<Invoice> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
			model.addAttribute("page", page);
			List<InvoiceInfo> invoiceInfos = this.service.getInvoiceInfos(page.getContent());
			model.addAttribute("invoiceInfos", invoiceInfos);
		}
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "inventory.invoice.manager";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:37<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			beforeInput(model, request);
			Invoice entity = this.service.get(id);
			model.addAttribute("invoice", entity);
		}
		return "inventory.invoice.update";
	}

	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:37<br>
	 */
	@RequestMapping(value = "/input")
	public String input(Invoice invoice, Model model, HttpServletRequest request) {
		beforeInput(model, request);
		return "inventory.invoice.update";
	}

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:37<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Invoice invoice, BindingResult bindingResult, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request);
			return "inventory.invoice.update";
		} else {
			this.beforeSave(invoice, request);
			this.service.save(invoice);
			this.afterSave(invoice, request);
			return "redirect:/inventory/invoice/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:37<br>
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if (id != null) {
			this.service.remove(id);
		}
		return "redirect:/inventory/invoice/home";
	}

	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:37<br>
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
	 * 创建日期:2015-6-13 11:31:37<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			Invoice entity = this.service.get(id);
			model.addAttribute("invoice", entity);
			InvoiceInfo invoiceInfo = this.service.getInvoiceInfo(entity);
			model.addAttribute("invoiceInfo", invoiceInfo);
		}
		return "inventory.invoice.detail";
	}

	/**
	 * 根据订单编号查询出库单
	 * @todo
	 * @param id
	 * @param model
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月19日
	 */
	@RequestMapping("/order/{id}")
	public String byOrder(@PathVariable Long id, Model model) {
		if (id != null) {
			Map searchParams = new HashMap();
			searchParams.put("EQ|order.$id", id);
			List<Invoice> invoices = this.service.getPage(searchParams, 1, 0).getContent();
			if (CollectionUtils.isNotEmpty(invoices)) {
				Invoice entity = invoices.get(0);
				model.addAttribute("invoice", entity);
				InvoiceInfo invoiceInfo = this.service.getInvoiceInfo(entity);
				model.addAttribute("invoiceInfo", invoiceInfo);
			}
		}
		return "inventory.invoice.detail";
	}

	// /////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:37<br>
	 */
	protected void beforeInput(Model model, HttpServletRequest request) {
		List<Shop> shops = this.shopService.getAll();
		model.addAttribute("shops", shops);
		List<Order> orders = this.orderService.getAll();
		model.addAttribute("orders", orders);
	}

	/**
	 * 保存前的数据回调
	 * @todo
	 * @param invoice
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-13 11:31:37
	 */
	protected void beforeSave(Invoice invoice, HttpServletRequest request) {
	}

	/**
	 * 保存后的数据回调
	 * @todo
	 * @param invoice
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-13 11:31:37
	 */
	protected void afterSave(Invoice invoice, HttpServletRequest request) {

	}

}
