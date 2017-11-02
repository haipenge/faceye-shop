package com.faceye.component.product.controller;

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

import com.faceye.component.product.entity.DynamicPropertyValue;
import com.faceye.component.product.entity.ProductProperty;
import com.faceye.component.product.entity.ProductSku;
import com.faceye.component.product.entity.SkuProperty;
import com.faceye.component.product.service.DynamicPropertyValueService;
import com.faceye.component.product.service.ProductPropertyService;
import com.faceye.component.product.service.ProductSkuService;
import com.faceye.component.product.service.SkuPropertyService;
import com.faceye.component.setting.controller.BaseShopController;
import com.faceye.component.setting.entity.Shop;
import com.faceye.component.setting.service.ShopService;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;

/**
 * 模块:产品->com.faceye.compoent.product.controller<br>
 * 说明:<br>
 * 实体:SKU属性:com.faceye.component.product.entity.entity.SkuProperty<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2015-6-16 18:57:42<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/product/skuProperty")
public class SkuPropertyController extends BaseShopController<SkuProperty, Long, SkuPropertyService> {
	@Autowired
	private ProductSkuService productSkuService=null; 
	@Autowired
	private ShopService shopService=null; 
	@Autowired
	private DynamicPropertyValueService dynamicPropertyValueService=null; 
	@Autowired
	private ProductPropertyService productPropertyService=null; 
	
	@Autowired
	public SkuPropertyController(SkuPropertyService service) {
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
		Map searchParams=HttpUtil.getRequestParams(request);
		beforeInput(model,request);
		Page<SkuProperty> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "product.skuProperty.manager";
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
	public String edit(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		if(id!=null){
			beforeInput(model,request);
			SkuProperty skuProperty=this.service.get(id);
			model.addAttribute("skuProperty", skuProperty);
		}
		return "product.skuProperty.update";
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
	@RequestMapping(value="/input")
	public String input(SkuProperty skuProperty, Model model,HttpServletRequest request){
		beforeInput(model,request);
		return "product.skuProperty.update";
	}
	

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-16 18:57:42<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid SkuProperty skuProperty,BindingResult bindingResult,Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
		  beforeInput(model,request);
		  return "product.skuProperty.update";
		}else{
		  this.beforeSave(skuProperty,request);
	      this.service.save(skuProperty);
	      this.afterSave(skuProperty,request);
		  return "redirect:/product/skuProperty/home";
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
		if(id!=null){
			this.service.remove(id);
		}
		return "redirect:/product/skuProperty/home";
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
	 * 创建日期:2015-6-16 18:57:42<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id,Model model){
		if(id!=null){
			SkuProperty skuProperty=this.service.get(id);
			model.addAttribute("skuProperty", skuProperty);
		}
		return "product.skuProperty.detail";
	}
	
	///////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-16 18:57:42<br>
	 */
	protected void beforeInput(Model model,HttpServletRequest request){
		    List<ProductSku> productSkus=this.productSkuService.getAll();
		    model.addAttribute("productSkus", productSkus);
		    List<Shop> shops=this.shopService.getAll();
		    model.addAttribute("shops", shops);
		    List<DynamicPropertyValue> dynamicPropertyValues=this.dynamicPropertyValueService.getAll();
		    model.addAttribute("dynamicPropertyValues", dynamicPropertyValues);
		    List<ProductProperty> productPropertys=this.productPropertyService.getAll();
		    model.addAttribute("productPropertys", productPropertys);
	}
	
	/**
	 * 保存前的数据回调
	 * @todo
	 * @param skuProperty
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-16 18:57:42
	 */
	protected void beforeSave(SkuProperty skuProperty,HttpServletRequest request){
	}
	
	
	
	/**
	 * 保存后的数据回调
	 * @todo
	 * @param skuProperty
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-16 18:57:42
	 */
	protected void afterSave(SkuProperty skuProperty,HttpServletRequest request){
	   
	}
	

}
