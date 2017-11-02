package com.faceye.component.lbs.controller;

import java.util.HashMap;
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

import com.faceye.component.lbs.entity.GeoLibrary;
import com.faceye.component.lbs.service.GeoLibraryService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.GlobalEntity;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

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
	public String home(HttpServletRequest request, Model model) {
		Map searchParams=HttpUtil.getRequestParams(request);
		Page<GeoLibrary> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("lbs.geoLibrary"));
		model.addAttribute("global",global);
		return "lbs.geoLibrary.manager";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		if(id!=null){
			beforeInput(model,request);
			GeoLibrary entity=this.service.get(id);
			model.addAttribute("geoLibrary", entity);
		}
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("lbs.geoLibrary.edit"));
		model.addAttribute("global",global);
		return "lbs.geoLibrary.update";
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
	public String input(GeoLibrary geoLibrary,Model model,HttpServletRequest request){
		beforeInput(model,request);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("lbs.geoLibrary.add"));
		model.addAttribute("global",global);
		return "lbs.geoLibrary.update";
	}
	
	
    

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid GeoLibrary geoLibrary,BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model,HttpServletRequest request) {
		if(bindingResult.hasErrors()){
			beforeInput(model,request);
			return "lbs.geoLibrary.update";
		}else{
		   this.beforeSave(geoLibrary,request);
		   this.service.save(geoLibrary);
		   return "redirect:/lbs/geoLibrary/home";
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
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,RedirectAttributesModelMap model) {
		if(id!=null){
			GeoLibrary geoLibrary=this.service.get(id);
			if(geoLibrary!=null){
				if(beforeRemove(geoLibrary,model)){
					this.service.remove(geoLibrary);	
					//MessageBuilder.getInstance().setMessage(model,geoLibrary+" "+ this.getI18N("global.remove.success"));
				}
			}
		}
		return "redirect:/lbs/geoLibrary/home";
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
	public String remove(@RequestParam(required=true) String  ids, RedirectAttributes redirectAttributes,Model model) {
		if(StringUtils.isNotEmpty(ids)){
			String [] idArray=ids.split(",");
			for(String id:idArray){
				GeoLibrary geoLibrary=this.service.get(Long.parseLong(id));
				if(geoLibrary!=null){
					if(beforeRemove(geoLibrary,model)){
						this.service.remove(geoLibrary);	
						//MessageBuilder.getInstance().setMessage(model,geoLibrary+" "+ this.getI18N("global.remove.success"));
					}
				}
			}
		}
		String messages = MessageBuilder.getInstance().getMessages(model);
		return AjaxResult.getInstance().buildDefaultResult(StringUtils.isEmpty(messages), messages);
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
	public String detail(@PathVariable Long id,Model model){
		if(id!=null){
			GeoLibrary geoLibrary=this.service.get(id);
			model.addAttribute("geoLibrary", geoLibrary);
		}
		return "lbs.geoLibrary.detail";
	}
	/**
	 * 位置选择
	 * @param level
	 * @param parentId
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2015年12月24日 上午11:19:12
	 */
	@RequestMapping("/geoLibraryQuery")
	@ResponseBody
	public Page<GeoLibrary> geoLibraryQuery(@RequestParam(required=false,defaultValue="0")Integer level,@RequestParam(required=false)Long parentId){
		Page<GeoLibrary> page=null;
		Map searchParams=new HashMap();
		searchParams.put("EQ|level", level);
		if(parentId!=null && parentId.compareTo(0L)>0){
		searchParams.put("EQ|parentId", parentId);
		}
		page=this.service.getPage(searchParams, 1, 0);
		return page;
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
	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(GeoLibrary geoLibrary,Model model){
		boolean res=true;
		
		return res;
	}

}
