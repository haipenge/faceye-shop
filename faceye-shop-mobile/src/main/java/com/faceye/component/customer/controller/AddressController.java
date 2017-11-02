package com.faceye.component.customer.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
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
import com.faceye.component.lbs.entity.GeoLibrary;
import com.faceye.component.lbs.service.GeoLibraryService;
import com.faceye.component.security.web.util.AjaxSecurity;
import com.faceye.component.setting.controller.BaseShopController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.GlobalEntity;
import com.faceye.feature.util.Json;
import com.faceye.feature.util.http.HttpUtil;

/**
 * 模块:客户->com.faceye.compoent.customer.controller<br>
 * 说明:<br>
 * 实体:送货地址:com.faceye.component.customer.entity.entity.Address<br>
 * 
 * @author haipenge <br>
 *         haipenge@gmail.com<br>
 *         创建日期:2015-6-16 18:57:43<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/customer/address")
public class AddressController extends BaseShopController<Address, Long, AddressService> {
	@Autowired
	private CustomerService customerService = null;
	@Autowired
	private GeoLibraryService geoLibraryService = null;

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
	 *                      创建日期2015-6-16 18:57:43<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		Customer customer = this.customerService.getCurrentLoginCustomer();
		beforeInput(model, request);
		searchParams.put("EQ|customer.$id", customer.getId());
		searchParams.put("boolean|isRemoved", false);
		Page<Address> page = this.service.getPage(searchParams, getPage(searchParams), 0);
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		GlobalEntity globalEntity=new GlobalEntity();
		globalEntity.setTitle(this.getI18N("customer.address"));
		model.addAttribute("global", globalEntity);
		return "customer.address.manager";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2015-6-16 18:57:43<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			Address address = this.service.get(id);
			beforeInput(model, request, address);
			model.addAttribute("address", address);
		}
		GlobalEntity globalEntity=new GlobalEntity();
		globalEntity.setTitle(this.getI18N("customer.address"));
		model.addAttribute("global", globalEntity);
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
	 *                      创建日期:2015-6-16 18:57:43<br>
	 */
	@RequestMapping(value = "/input")
	public String input(Address address, Model model, HttpServletRequest request) {
		beforeInput(model, request);
		GlobalEntity globalEntity=new GlobalEntity();
		globalEntity.setTitle(this.getI18N("customer.address"));
		model.addAttribute("global", globalEntity);
		return "customer.address.update";
	}

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2015-6-16 18:57:43<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Address address, BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request, address);
			return "customer.address.update";
		} else {
			this.beforeSave(address, request);
			//如果设置为默认地址
			Map params=HttpUtil.getRequestParams(request);
			
			if(address.getIsDefault()){
				List<Address> addresses = this.service.getAddressesByCustomer(address.getCustomer());
				if (CollectionUtils.isNotEmpty(addresses)) {
					for (Address a : addresses) {
						a.setIsDefault(false);
						this.service.save(a);
					}
				}
			}
			address.setIsRemoved(false);
			this.service.save(address);
			this.afterSave(address, request);
			// 如果是ajax ,返回json对像
			boolean isAjax = AjaxSecurity.isAjaxRequest(request);
			if (isAjax) {
				return Json.toJson(address);
			} else {
				return "redirect:/customer/address/home";
			}
		}
	}

	@RequestMapping("/ajaxSave")
	@ResponseBody
	public Address ajaxSave(@Valid Address address, BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		this.beforeSave(address, request);
		this.service.save(address);
		List<Address> addresses = this.service.getAddressesByCustomer(address.getCustomer());
		if (CollectionUtils.isNotEmpty(addresses)) {
			for (Address a : addresses) {
				a.setIsDefault(false);
				this.service.save(a);
			}
		}
		address.setIsDefault(true);
		address.setIsRemoved(false);
		this.service.save(address);
		this.afterSave(address, request);
		return address;
	}

	/**
	 * 设为默认地址
	 * 
	 * @todo
	 * @param addressId
	 * @return
	 * @author:@haipenge 联系:haipenge@gmail.com 创建时间:2015年6月22日
	 */
	@RequestMapping("/setDefault")
	@ResponseBody
	public String setDefault(@RequestParam(required = true) Long addressId) {
		List<Address> addresses = this.service.getAddressesByCustomer(this.customerService.getCurrentLoginCustomer());
		if (CollectionUtils.isNotEmpty(addresses)) {
			for (Address a : addresses) {
				a.setIsDefault(false);
				this.service.save(a);
			}
		}
		Address address = this.service.get(addressId);
		address.setIsDefault(true);
		this.service.save(address);
		return AjaxResult.getInstance().buildDefaultResult(true);

	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2015-6-16 18:57:43<br>
	 *                      
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if (id != null) {
			Address address = this.service.get(id);
			address.setIsRemoved(true);
			this.service.save(address);
		}
		return "redirect:/customer/address/home";
	}

	@RequestMapping("/ajaxRemove")
	@ResponseBody
	public String ajaxRemove(@RequestParam(required = true) Long addressId) {
		if (addressId != null) {
			Address address = this.service.get(addressId);
			address.setIsRemoved(true);
			this.service.save(address);
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2015-6-16 18:57:43<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required = true) String ids, RedirectAttributes redirectAttributes) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				Address address = this.service.get(Long.parseLong(id));
				address.setIsRemoved(true);
				this.service.save(address);
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
	 *                      创建日期:2015-6-16 18:57:43<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			Address address = this.service.get(id);
			model.addAttribute("address", address);
		}
		GlobalEntity globalEntity=new GlobalEntity();
		globalEntity.setTitle(this.getI18N("customer.address"));
		model.addAttribute("global", globalEntity);
		return "customer.address.detail";
	}

	// /////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2015-6-16 18:57:43<br>
	 */
	protected void beforeInput(Model model, HttpServletRequest request, Address address) {
		// List<Customer> customers = this.customerService.getAll();
		Customer customer = this.customerService.getCurrentLoginCustomer();
		address.setCustomer(customer);
		GeoLibrary geoLibrary = address.getGeoLibrary();
		if (null != geoLibrary) {
			List<GeoLibrary> geoLibraries = this.geoLibraryService.getGeoLibrariesDetail(geoLibrary.getId());
			model.addAttribute("geoLibraries", geoLibraries);
		}
		// model.addAttribute("customers", customers);
	}

	/**
	 * 保存前的数据回调
	 * 
	 * @todo
	 * @param address
	 * @param request
	 * @author:haipenge 联系:haipenge@gmail.com 创建日期:2015-6-16 18:57:43
	 */
	protected void beforeSave(Address address, HttpServletRequest request) {
		Customer customer = this.customerService.getCurrentLoginCustomer();
		if (customer != null) {
			address.setCustomer(customer);
		}
		// 设置GEO信息
		Map params = HttpUtil.getRequestParams(request);
		Long areaId = MapUtils.getLong(params, "areaId");
		if (areaId != null && areaId.compareTo(0L) > 0) {
			GeoLibrary geoLibrary = this.geoLibraryService.get(areaId);
			address.setGeoLibrary(geoLibrary);
		}
	}

	/**
	 * 保存后的数据回调
	 * 
	 * @todo
	 * @param address
	 * @param request
	 * @author:haipenge 联系:haipenge@gmail.com 创建日期:2015-6-16 18:57:43
	 */
	protected void afterSave(Address address, HttpServletRequest request) {

	}

}
