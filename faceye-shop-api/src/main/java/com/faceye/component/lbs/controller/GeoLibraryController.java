package com.faceye.component.lbs.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;

import com.faceye.component.lbs.entity.GeoLibrary;
import com.faceye.component.lbs.service.GeoLibraryService;

import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.MathUtil;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.regexp.RegexpUtil;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.controller.BaseController;

/**
 * 模块:lbs<br>
 * 实体:GeoLibrary<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/lbs/geoLibrary")
public class GeoLibraryController extends BaseController<GeoLibrary, Long, GeoLibraryService> {

	@Autowired
	public GeoLibraryController(GeoLibraryService service) {
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
	@ResponseBody
	public Page<GeoLibrary> home(HttpServletRequest request, Model model) {
		Map searchParams=HttpUtil.getRequestParams(request);
		Page<GeoLibrary> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		return page
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/edit/{id}")
	@ResponseBody
	public String edit(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		if(id!=null){
			beforeInput(model,request);
			GeoLibrary entity=this.service.get(id);
			model.addAttribute("geoLibrary", entity);
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
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
	@RequestMapping(value="/input")
	@ResponseBody
	public String input(GeoLibrary geoLibrary,Model model,HttpServletRequest request){
		beforeInput(model,request);
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(@Valid GeoLibrary geoLibrary,BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model,HttpServletRequest request) {
		if(bindingResult.hasErrors()){
			beforeInput(model,request);
			return AjaxResult.getInstance().buildDefaultResult(false);
		}else{
		   this.beforeSave(geoLibrary,request);
		   this.service.save(geoLibrary);
		   return AjaxResult.getInstance().buildDefaultResult(true);
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
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
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
	 * @author:@haipenge<br>
	 * haipenge@gmail.com<br>
	 * 2014年5月26日<br>
	 */
	@RequestMapping("/detail/{id}")
	@ResponseBody
	public GeoLibrary detail(@PathVariable Long id,Model model){
		if(id!=null){
			GeoLibrary geoLibrary=this.service.get(id);
		}
		return geoLibrary;
	}
	
	///////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:@haipenge<br>
	 * haipenge@gmail.com<br>
	 * 2015年4月5日<br>
	 */
	protected void beforeInput(Model model,HttpServletRequest request){
		
	}
	/**
	 *
	 *保存数据前的回调函数
	 */
	protected void beforeSave(GeoLibrary geoLibrary,HttpServletRequest request){
		
	}

}
