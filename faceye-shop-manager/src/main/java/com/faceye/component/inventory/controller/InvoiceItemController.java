package com.faceye.component.inventory.controller;

import java.util.List;
import java.util.ArrayList;
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

import com.faceye.component.inventory.entity.InvoiceItem;
import com.faceye.component.inventory.service.InvoiceItemService;
import com.faceye.component.setting.entity.Shop;
import com.faceye.component.setting.service.ShopService;
import com.faceye.component.product.entity.Product;
import com.faceye.component.product.service.ProductService;
import com.faceye.component.product.entity.ProductSku;
import com.faceye.component.product.service.ProductSkuService;
import com.faceye.component.inventory.entity.Invoice;
import com.faceye.component.inventory.service.InvoiceService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.AjaxResult;

/**
 * 模块:库存->com.faceye.compoent.inventory.controller<br>
 * 说明:<br>
 * 实体:库存单据明细:com.faceye.component.inventory.entity.entity.Item<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2015-6-13 11:31:37<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/inventory/invoiceItem")
public class InvoiceItemController extends BaseController<InvoiceItem, Long, InvoiceItemService> {
	@Autowired
	private ShopService shopService=null; 
	@Autowired
	private ProductService productService=null; 
	@Autowired
	private ProductSkuService productSkuService=null; 
	@Autowired
	private InvoiceService invoiceService=null; 
	
	@Autowired
	public InvoiceItemController(InvoiceItemService service) {
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
		Map searchParams=HttpUtil.getRequestParams(request);
		beforeInput(model,request);
		Page<InvoiceItem> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "inventory.item.manager";
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
	public String edit(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		if(id!=null){
			beforeInput(model,request);
			InvoiceItem entity=this.service.get(id);
			model.addAttribute("item", entity);
		}
		return "inventory.item.update";
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
	@RequestMapping(value="/input")
	public String input(InvoiceItem invoiceItem, Model model,HttpServletRequest request){
		beforeInput(model,request);
		return "inventory.item.update";
	}
	

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:37<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid InvoiceItem invoiceItem,BindingResult bindingResult,Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
		  beforeInput(model,request);
		  return "inventory.item.update";
		}else{
		  this.beforeSave(invoiceItem,request);
	      this.service.save(invoiceItem);
	      this.afterSave(invoiceItem,request);
		  return "redirect:/inventory/invoiceItem/home";
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
		if(id!=null){
			this.service.remove(id);
		}
		return "redirect:/inventory/invoiceItem/home";
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
	public String remove(@RequestParam(required=true) String  ids, RedirectAttributes redirectAttributes) {
		if(StringUtils.isNotEmpty(ids)){
			String [] idArray=ids.split(",");
			for(String id:idArray){
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
	public String detail(@PathVariable Long id,Model model){
		if(id!=null){
			InvoiceItem entity=this.service.get(id);
			model.addAttribute("item", entity);
		}
		return "inventory.item.detail";
	}
	
	///////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:37<br>
	 */
	protected void beforeInput(Model model,HttpServletRequest request){
		    List<Shop> shops=this.shopService.getAll();
		    model.addAttribute("shops", shops);
		    List<Product> products=this.productService.getAll();
		    model.addAttribute("products", products);
		    List<ProductSku> productSkus=this.productSkuService.getAll();
		    model.addAttribute("productSkus", productSkus);
		    List<Invoice> invoices=this.invoiceService.getAll();
		    model.addAttribute("invoices", invoices);
	}
	
	/**
	 * 保存前的数据回调
	 * @todo
	 * @param invoiceItem
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-13 11:31:37
	 */
	protected void beforeSave(InvoiceItem invoiceItem,HttpServletRequest request){
	}
	
	
	
	/**
	 * 保存后的数据回调
	 * @todo
	 * @param invoiceItem
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-13 11:31:37
	 */
	protected void afterSave(InvoiceItem invoiceItem,HttpServletRequest request){
	   
	}
	

}
