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

import com.faceye.component.inventory.entity.Inventory;
import com.faceye.component.inventory.service.InventoryService;
import com.faceye.component.setting.entity.Shop;
import com.faceye.component.setting.service.ShopService;
import com.faceye.component.product.entity.ProductSku;
import com.faceye.component.product.service.ProductSkuService;
import com.faceye.component.product.entity.Product;
import com.faceye.component.product.service.ProductService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.AjaxResult;

/**
 * 模块:库存->com.faceye.compoent.inventory.controller<br>
 * 说明:<br>
 * 实体:库存明细:com.faceye.component.inventory.entity.entity.Inventory<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2015-6-13 11:31:37<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/inventory/inventory")
public class InventoryController extends BaseController<Inventory, Long, InventoryService> {
	@Autowired
	private ShopService shopService=null; 
	@Autowired
	private ProductSkuService productSkuService=null; 
	@Autowired
	private ProductService productService=null; 
	
	@Autowired
	public InventoryController(InventoryService service) {
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
	@ResponseBody
	public Page<Inventory> home(HttpServletRequest request, Model model) {
		Map searchParams=HttpUtil.getRequestParams(request);
		beforeInput(model,request);
		Page<Inventory> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return page;
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
	@ResponseBody
	public Inventory edit(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		beforeInput(model,request);
		Inventory inventory=this.service.get(id);
		return inventory;
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
	public String input(Inventory inventory, Model model,HttpServletRequest request){
		beforeInput(model,request);
		return "inventory.inventory.update";
	}
	

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:37<br>
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(@Valid Inventory inventory,BindingResult bindingResult,Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
		  beforeInput(model,request);
		  return AjaxResult.getInstance().buildDefaultResult(false);
		}else{
		  this.beforeSave(inventory,request);
	      this.service.save(inventory);
	      this.afterSave(inventory,request);
	      return  AjaxResult.getInstance().buildDefaultResult(true);
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
	@ResponseBody
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if(id!=null){
			this.service.remove(id);
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
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
	@ResponseBody
	public Inventory detail(@PathVariable Long id,Model model){
		Inventory inventory=this.service.get(id);
		return inventory;
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
		    List<ProductSku> productSkus=this.productSkuService.getAll();
		    model.addAttribute("productSkus", productSkus);
		    List<Product> products=this.productService.getAll();
		    model.addAttribute("products", products);
	}
	
	/**
	 * 保存前的数据回调
	 * @todo
	 * @param inventory
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-13 11:31:37
	 */
	protected void beforeSave(Inventory inventory,HttpServletRequest request){
	}
	
	
	
	/**
	 * 保存后的数据回调
	 * @todo
	 * @param inventory
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-13 11:31:37
	 */
	protected void afterSave(Inventory inventory,HttpServletRequest request){
	   
	}
	

}
