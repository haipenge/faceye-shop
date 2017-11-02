package com.faceye.component.product.controller;

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

import com.faceye.component.product.entity.ProductProperty;
import com.faceye.component.product.service.ProductPropertyService;
import com.faceye.component.product.entity.Product;
import com.faceye.component.product.service.ProductService;
import com.faceye.component.product.entity.DynamicProperty;
import com.faceye.component.product.service.DynamicPropertyService;
import com.faceye.component.product.entity.DynamicPropertyValue;
import com.faceye.component.product.service.DynamicPropertyValueService;
import com.faceye.component.setting.entity.Shop;
import com.faceye.component.setting.service.ShopService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.AjaxResult;

/**
 * 模块:产品->com.faceye.compoent.product.controller<br>
 * 说明:<br>
 * 实体:产品属性:com.faceye.component.product.entity.entity.ProductProperty<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2015-6-13 11:31:36<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/product/productProperty")
public class ProductPropertyController extends BaseController<ProductProperty, Long, ProductPropertyService> {
	@Autowired
	private ProductService productService=null; 
	@Autowired
	private DynamicPropertyService dynamicPropertyService=null; 
	@Autowired
	private DynamicPropertyValueService dynamicPropertyValueService=null; 
	@Autowired
	private ShopService shopService=null; 
	
	@Autowired
	public ProductPropertyController(ProductPropertyService service) {
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
	@ResponseBody
	public Page<ProductProperty> home(HttpServletRequest request, Model model) {
		Map searchParams=HttpUtil.getRequestParams(request);
		beforeInput(model,request);
		Page<ProductProperty> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
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
	 * 创建日期:2015-6-13 11:31:36<br>
	 */
	@RequestMapping("/edit/{id}")
	@ResponseBody
	public ProductProperty edit(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		beforeInput(model,request);
		ProductProperty productProperty=this.service.get(id);
		return productProperty;
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
	@RequestMapping(value="/input")
	public String input(ProductProperty productProperty, Model model,HttpServletRequest request){
		beforeInput(model,request);
		return "product.productProperty.update";
	}
	

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:36<br>
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(@Valid ProductProperty productProperty,BindingResult bindingResult,Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
		  beforeInput(model,request);
		  return AjaxResult.getInstance().buildDefaultResult(false);
		}else{
		  this.beforeSave(productProperty,request);
	      this.service.save(productProperty);
	      this.afterSave(productProperty,request);
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
	 * 创建日期:2015-6-13 11:31:36<br>
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
	 * 创建日期:2015-6-13 11:31:36<br>
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
	 * 创建日期:2015-6-13 11:31:36<br>
	 */
	@RequestMapping("/detail/{id}")
	@ResponseBody
	public ProductProperty detail(@PathVariable Long id,Model model){
		ProductProperty productProperty=this.service.get(id);
		return productProperty;
	}
	
	///////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:36<br>
	 */
	protected void beforeInput(Model model,HttpServletRequest request){
		    List<Product> products=this.productService.getAll();
		    model.addAttribute("products", products);
		    List<DynamicProperty> dynamicPropertys=this.dynamicPropertyService.getAll();
		    model.addAttribute("dynamicPropertys", dynamicPropertys);
		    List<DynamicPropertyValue> dynamicPropertyValues=this.dynamicPropertyValueService.getAll();
		    model.addAttribute("dynamicPropertyValues", dynamicPropertyValues);
		    List<Shop> shops=this.shopService.getAll();
		    model.addAttribute("shops", shops);
	}
	
	/**
	 * 保存前的数据回调
	 * @todo
	 * @param productProperty
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-13 11:31:36
	 */
	protected void beforeSave(ProductProperty productProperty,HttpServletRequest request){
	}
	
	
	
	/**
	 * 保存后的数据回调
	 * @todo
	 * @param productProperty
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-13 11:31:36
	 */
	protected void afterSave(ProductProperty productProperty,HttpServletRequest request){
	   
	}
	

}
