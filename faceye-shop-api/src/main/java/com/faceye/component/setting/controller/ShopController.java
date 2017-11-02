package com.faceye.component.setting.controller;

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

import com.faceye.component.setting.entity.Shop;
import com.faceye.component.setting.service.ShopService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.AjaxResult;

/**
 * 模块:配置->com.faceye.compoent.setting.controller<br>
 * 说明:<br>
 * 实体:店铺:com.faceye.component.setting.entity.entity.Shop<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2015-6-13 11:31:35<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/setting/shop")
public class ShopController extends BaseController<Shop, Long, ShopService> {
	
	@Autowired
	public ShopController(ShopService service) {
		super(service);
	}

	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br> 
	 * haipenge@gmail.com <br>
	 * 创建日期2015-6-13 11:31:35<br>
	 */
	@RequestMapping("/home")
	@ResponseBody
	public Page<Shop> home(HttpServletRequest request, Model model) {
		Map searchParams=HttpUtil.getRequestParams(request);
		beforeInput(model,request);
		Page<Shop> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
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
	 * 创建日期:2015-6-13 11:31:35<br>
	 */
	@RequestMapping("/edit/{id}")
	@ResponseBody
	public Shop edit(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		beforeInput(model,request);
		Shop shop=this.service.get(id);
		return shop;
	}
	
	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:35<br>
	 */
	@RequestMapping(value="/input")
	public String input(Shop shop, Model model,HttpServletRequest request){
		beforeInput(model,request);
		return "setting.shop.update";
	}
	

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:35<br>
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(@Valid Shop shop,BindingResult bindingResult,Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
		  beforeInput(model,request);
		  return AjaxResult.getInstance().buildDefaultResult(false);
		}else{
		  this.beforeSave(shop,request);
	      this.service.save(shop);
	      this.afterSave(shop,request);
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
	 * 创建日期:2015-6-13 11:31:35<br>
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
	 * 创建日期:2015-6-13 11:31:35<br>
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
	 * 创建日期:2015-6-13 11:31:35<br>
	 */
	@RequestMapping("/detail/{id}")
	@ResponseBody
	public Shop detail(@PathVariable Long id,Model model){
		Shop shop=this.service.get(id);
		return shop;
	}
	
	///////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:35<br>
	 */
	protected void beforeInput(Model model,HttpServletRequest request){
	}
	
	/**
	 * 保存前的数据回调
	 * @todo
	 * @param shop
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-13 11:31:35
	 */
	protected void beforeSave(Shop shop,HttpServletRequest request){
	}
	
	
	
	/**
	 * 保存后的数据回调
	 * @todo
	 * @param shop
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-13 11:31:35
	 */
	protected void afterSave(Shop shop,HttpServletRequest request){
	   
	}
	

}
