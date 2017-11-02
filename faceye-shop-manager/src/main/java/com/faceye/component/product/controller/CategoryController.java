package com.faceye.component.product.controller;

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

import com.faceye.component.product.entity.Category;
import com.faceye.component.product.entity.Product;
import com.faceye.component.product.service.CategoryService;
import com.faceye.component.product.service.ProductService;
import com.faceye.component.setting.controller.BaseShopController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

/**
 * 模块:产品->com.faceye.compoent.product.controller<br>
 * 说明:<br>
 * 实体:产品分类:com.faceye.component.product.entity.entity.Category<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2015-6-13 11:31:35<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/product/category")
public class CategoryController extends BaseShopController<Category, Long, CategoryService> {
	@Autowired
	private ProductService productService = null;

	@Autowired
	public CategoryController(CategoryService service) {
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
		searchParams.put("EQ|shop.$id", this.shopService.getCurrentUserShop().getId());
		beforeInput(model, request);
		Page<Category> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "product.category.manager";
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
			Category entity = this.service.get(id);
			model.addAttribute("category", entity);
		}
		return "product.category.update";
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
	public String input(Category category, Model model, HttpServletRequest request) {
		beforeInput(model, request);
		return "product.category.update";
	}

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:35<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Category category, BindingResult bindingResult, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request);
			return "product.category.update";
		} else {
			this.beforeSave(category, request);
			this.service.save(category);
			this.afterSave(category, request);
			return "redirect:/product/category/home";
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
			Category category = this.service.get(id);
			if (category != null) {
				if (this.beforeRemove(category, model)) {
					this.service.remove(category);
					MessageBuilder.getInstance().setMessage(model,category.getName()+" "+ this.getI18N("global.remove.success"));
				}
			}
		}
		return "redirect:/product/category/home";
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
				Category category = this.service.get(Long.parseLong(id));
				if (category != null) {
					if (this.beforeRemove(category, model)) {
						this.service.remove(category);
						MessageBuilder.getInstance().setMessage(model,category.getName()+" "+ this.getI18N("global.remove.success"));
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
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:35<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			Category entity = this.service.get(id);
			model.addAttribute("category", entity);
		}
		return "product.category.detail";
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
	}

	/**
	 * 保存前的数据回调
	 * @todo
	 * @param category
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-13 11:31:35
	 */
	protected void beforeSave(Category category, HttpServletRequest request) {
		category.setShop(this.shopService.getCurrentUserShop());
	}

	/**
	 * 删除 前回调
	 * @todo
	 * @param category
	 * @param model
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月19日
	 */
	protected boolean beforeRemove(Category category, Model model) {
		boolean res = true;
		Map searchParams = new HashMap();
		searchParams.put("EQ|category.$id", category.getId());
		Page<Product> produtcs = this.productService.getPage(searchParams, 1, 1);
		long count = produtcs.getTotalElements();
		if (count > 0) {
			MessageBuilder.getInstance().setMessage(model, category.getName() + " 下还有" + count + "条产品，不可删除.");
			res = false;
		}
		return res;
	}

	/**
	 * 保存后的数据回调
	 * @todo
	 * @param category
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-13 11:31:35
	 */
	protected void afterSave(Category category, HttpServletRequest request) {

	}

}
