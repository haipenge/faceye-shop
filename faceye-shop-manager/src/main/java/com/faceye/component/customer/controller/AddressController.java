package com.faceye.component.customer.controller;

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

import com.faceye.component.customer.entity.Address;
import com.faceye.component.customer.entity.Customer;
import com.faceye.component.customer.service.AddressService;
import com.faceye.component.customer.service.CustomerService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;

/**
 * 模块:客户->com.faceye.compoent.customer.controller<br>
 * 说明:<br>
 * 实体:送货地址:com.faceye.component.customer.entity.entity.Address<br>
 * 
 * @author haipenge <br>
 *         haipenge@gmail.com<br>
 *         创建日期:2015-6-13 11:31:37<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/customer/address")
public class AddressController extends BaseController<Address, Long, AddressService> {
	@Autowired
	private CustomerService customerService = null;

	@Autowired
	public AddressController(AddressService service) {
		super(service);
	}

	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge@gmail.com <br>
	 *                      创建日期2015-6-13 11:31:37<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		beforeInput(model, request);
		Page<Address> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "customer.address.manager";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2015-6-13 11:31:37<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			beforeInput(model, request);
			Address entity = this.service.get(id);
			model.addAttribute("address", entity);
		}
		return "customer.address.update";
	}

	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2015-6-13 11:31:37<br>
	 */
	@RequestMapping(value = "/input")
	public String input(Address address, Model model, HttpServletRequest request) {
		beforeInput(model, request);
		return "customer.address.update";
	}

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2015-6-13 11:31:37<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Address address, BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request);
			return "customer.address.update";
		} else {
			this.beforeSave(address, request);
			this.service.save(address);
			this.afterSave(address, request);
			return "redirect:/customer/address/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * @Connect:haipenge @gmail.com <br>
	 * @Date: 创建日期:2015-6-13 11:31:37<br>
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if (id != null) {
			this.service.remove(id);
		}
		return "redirect:/customer/address/home";
	}

	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2015-6-13 11:31:37<br>
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
	 * 
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2015-6-13 11:31:37<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			Address entity = this.service.get(id);
			model.addAttribute("address", entity);
		}
		return "customer.address.detail";
	}

	/////////////////////////////////////////////// 以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2015-6-13 11:31:37<br>
	 */
	protected void beforeInput(Model model, HttpServletRequest request) {
		List<Customer> customers = this.customerService.getAll();
		model.addAttribute("customers", customers);
	}

	/**
	 * 保存前的数据回调
	 * 
	 * @todo
	 * @param address
	 * @param request
	 * @author:haipenge 联系:haipenge@gmail.com 创建日期:2015-6-13 11:31:37
	 */
	protected void beforeSave(Address address, HttpServletRequest request) {
	}

	/**
	 * 保存后的数据回调
	 * 
	 * @todo
	 * @param address
	 * @param request
	 * @author:haipenge 联系:haipenge@gmail.com 创建日期:2015-6-13 11:31:37
	 */
	protected void afterSave(Address address, HttpServletRequest request) {

	}

}
