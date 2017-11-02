package com.faceye.component.product.controller;

import java.util.HashMap;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.faceye.component.product.entity.Category;
import com.faceye.component.product.entity.DataType;
import com.faceye.component.product.entity.DynamicProperty;
import com.faceye.component.product.entity.ProductProperty;
import com.faceye.component.product.service.CategoryService;
import com.faceye.component.product.service.DataTypeService;
import com.faceye.component.product.service.DynamicPropertyService;
import com.faceye.component.product.service.ProductPropertyService;
import com.faceye.component.setting.controller.BaseShopController;
import com.faceye.component.setting.entity.Shop;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

/**
 * 模块:产品->com.faceye.compoent.product.controller<br>
 * 说明:<br>
 * 实体:产品属性:com.faceye.component.product.entity.entity.DynamicProperty<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2015-6-13 11:31:35<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/product/dynamicProperty")
public class DynamicPropertyController extends BaseShopController<DynamicProperty, Long, DynamicPropertyService> {
	@Autowired
	private CategoryService categoryService = null;
	@Autowired
	private DataTypeService dataTypeService = null;
	@Autowired
	private ProductPropertyService productPropertyService = null;

	@Autowired
	public DynamicPropertyController(DynamicPropertyService service) {
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
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		Shop shop = this.shopService.getCurrentUserShop();
		if (shop != null) {
			String categoryId=MapUtils.getString(searchParams, "categoryId");
			String dataTypeId=MapUtils.getString(searchParams, "dataTypeId");
			if(StringUtils.isNotEmpty(categoryId)){
				searchParams.put("EQ|category.$id", Long.parseLong(categoryId));
			}
			if(StringUtils.isNotEmpty(dataTypeId)){
				searchParams.put("EQ|dataType.$id", Long.parseLong(dataTypeId));
			}
			searchParams.put("EQ|shop.$id", shop.getId());
			beforeInput(model, request);
			Page<DynamicProperty> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
			model.addAttribute("page", page);
		}
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "product.dynamicProperty.manager";
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
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			beforeInput(model, request);
			DynamicProperty entity = this.service.get(id);
			model.addAttribute("dynamicProperty", entity);
		}
		return "product.dynamicProperty.update";
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
	@RequestMapping(value = "/input")
	public String input(DynamicProperty dynamicProperty, Model model, HttpServletRequest request) {
		beforeInput(model, request);
		return "product.dynamicProperty.update";
	}

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:35<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid DynamicProperty dynamicProperty, BindingResult bindingResult, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request);
			return "product.dynamicProperty.update";
		} else {
			this.beforeSave(dynamicProperty, request);
			this.service.save(dynamicProperty);
			this.afterSave(dynamicProperty, request);
			return "redirect:/product/dynamicProperty/home";
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
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, RedirectAttributesModelMap model) {
		if (id != null) {
			DynamicProperty dynamicProperty = this.service.get(id);
			if (this.beforeRemove(dynamicProperty, model)) {
				this.service.remove(dynamicProperty);
				MessageBuilder.getInstance().setMessage(model, dynamicProperty.getName() + " " + this.getI18N("global.remove.success"));
			}
		}
		return "redirect:/product/dynamicProperty/home";
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
	public String remove(@RequestParam(required = true) String ids, RedirectAttributes redirectAttributes, Model model) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				DynamicProperty dynamicProperty = this.service.get(Long.parseLong(id));
				if (this.beforeRemove(dynamicProperty, model)) {
					this.service.remove(dynamicProperty);
					MessageBuilder.getInstance().setMessage(model, dynamicProperty.getName() + " " + this.getI18N("global.remove.success"));
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
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:35<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			DynamicProperty entity = this.service.get(id);
			model.addAttribute("dynamicProperty", entity);
		}
		return "product.dynamicProperty.detail";
	}

	// /////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:35<br>
	 */
	protected void beforeInput(Model model, HttpServletRequest request) {
		Map searchParams = new HashMap();
		searchParams.put("EQ|shop.$id", this.shopService.getCurrentUserShop().getId());
		List<Category> categorys = this.categoryService.getPage(searchParams, 1, 0).getContent();
		model.addAttribute("categorys", categorys);
		List<DataType> dataTypes = this.dataTypeService.getAll();
		model.addAttribute("dataTypes", dataTypes);
	}

	/**
	 * 保存前的数据回调
	 * @todo
	 * @param dynamicProperty
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-13 11:31:35
	 */
	protected void beforeSave(DynamicProperty dynamicProperty, HttpServletRequest request) {
		if (dynamicProperty.getCategory() == null || dynamicProperty.getCategory().getId().compareTo(0L) == 0) {
			dynamicProperty.setIsShare(true);
		} else {
			dynamicProperty.setIsShare(false);
		}
		dynamicProperty.setShop(this.shopService.getCurrentUserShop());
	}

	/**
	 * 删除前回调
	 * @todo
	 * @param dynamicProperty
	 * @param model
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月19日
	 */

	protected boolean beforeRemove(DynamicProperty dynamicProperty, Model model) {
		boolean res = true;
		List<ProductProperty> produtcProperties = this.productPropertyService.getProductPropertyByDynamicProperty(dynamicProperty);
		if (CollectionUtils.isNotEmpty(produtcProperties)) {
			res = false;
			MessageBuilder.getInstance().setMessage(model, this.getI18N("product.dynamicProperty.remove.error.tip"));
		}
		return res;
	}

	/**
	 * 保存后的数据回调
	 * @todo
	 * @param dynamicProperty
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-13 11:31:35
	 */
	protected void afterSave(DynamicProperty dynamicProperty, HttpServletRequest request) {

	}

}
