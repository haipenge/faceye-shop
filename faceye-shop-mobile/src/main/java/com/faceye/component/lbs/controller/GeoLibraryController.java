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
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年12月10日<br>
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
	 * GEO位置查询
	 * @param level
	 * @param parentId
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2015年12月25日 下午1:49:38
	 */
	@RequestMapping("/geoLibraryQuery")
	@ResponseBody
	public Page<GeoLibrary> geoLibraryQuery(@RequestParam(required = false, defaultValue = "0") Integer level, @RequestParam(required = false) Long parentId) {
		Page<GeoLibrary> page = null;
		Map searchParams = new HashMap();
		searchParams.put("EQ|level", level);
		if (parentId != null && parentId.compareTo(0L) > 0) {
			searchParams.put("EQ|parentId", parentId);
		}
		page = this.service.getPage(searchParams, 1, 0);
		return page;
	}

	/////////////////////////////////////////////// 以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @param request<br>
	 * @author:@haipenge<br>
	 * 						haipenge@gmail.com<br>
	 *                       2015年4月5日<br>
	 */
	protected void beforeInput(Model model, HttpServletRequest request) {

	}

	/**
	 *
	 * 保存数据前的回调函数
	 */
	protected void beforeSave(GeoLibrary geoLibrary, HttpServletRequest request) {

	}

	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(GeoLibrary geoLibrary, Model model) {
		boolean res = true;

		return res;
	}

}
