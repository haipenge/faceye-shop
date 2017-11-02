package com.faceye.component.setting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.faceye.component.product.entity.Category;
import com.faceye.component.product.entity.Product;
import com.faceye.component.product.model.ProductInfo;
import com.faceye.component.product.service.CategoryService;
import com.faceye.component.product.service.ProductService;
import com.faceye.component.setting.entity.Shop;
import com.faceye.component.setting.service.ShopService;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;

/**
 * 模块:配置->com.faceye.compoent.setting.controller<br>
 * 说明:<br>
 * 实体:店铺:com.faceye.component.setting.entity.entity.Shop<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2015-6-16 18:57:41<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/setting/shop")
public class ShopController extends BaseShopController<Shop, Long, ShopService> {
	@Autowired
	private CategoryService categoryService = null;
	@Autowired
	private ProductService productService = null;

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
	 * 创建日期2015-6-16 18:57:41<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		beforeInput(model, request);
		Page<Shop> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		Map searchProductParams = new HashMap();
//		searchProductParams.put("EQ|shop.$id", id);
		searchProductParams.put("boolean|isOnSale", true);
		Long categoryId = MapUtils.getLong(searchParams, "categoryId");
		if (categoryId != null) {
			searchProductParams.put("EQ|category.$id", categoryId);
		}
		Page<Product> products = this.productService.getPage(searchParams, this.getPage(searchParams), this.getSize(searchParams));
		List<ProductInfo> productInfos = this.productService.getProductInfos(products.getContent());
		model.addAttribute("productInfos", productInfos);
		model.addAttribute("products", products);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "setting.shop.manager";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-16 18:57:41<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			beforeInput(model, request);
			Shop shop = this.service.get(id);
			model.addAttribute("shop", shop);
		}
		return "setting.shop.update";
	}

	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-16 18:57:41<br>
	 */
	@RequestMapping(value = "/input")
	public String input(Shop shop, Model model, HttpServletRequest request) {
		beforeInput(model, request);
		return "setting.shop.update";
	}

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-16 18:57:41<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Shop shop, BindingResult bindingResult, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request);
			return "setting.shop.update";
		} else {
			this.beforeSave(shop, request);
			this.service.save(shop);
			this.afterSave(shop, request);
			return "redirect:/setting/shop/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-16 18:57:41<br>
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if (id != null) {
			this.service.remove(id);
		}
		return "redirect:/setting/shop/home";
	}

	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-16 18:57:41<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required = true) String ids, RedirectAttributes redirectAttributes) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
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
	 * 创建日期:2015-6-16 18:57:41<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model, HttpServletRequest request) {
		Map searchParams = HttpUtil.getRequestParams(request);
		if (id != null) {
			Shop shop = this.service.get(id);
			model.addAttribute("shop", shop);
			List<Category> categories = this.categoryService.getCategoriesByShopId(id);
			model.addAttribute("categories", categories);
			Map searchProductParams = new HashMap();
			searchProductParams.put("EQ|shop.$id", id);
			searchProductParams.put("boolean|isOnSale", true);
			Long categoryId = MapUtils.getLong(searchParams, "categoryId");
			if (categoryId != null) {
				searchProductParams.put("EQ|category.$id", categoryId);
			}
			Page<Product> products = this.productService.getPage(searchParams, this.getPage(searchParams), this.getSize(searchParams));
			List<ProductInfo> productInfos = this.productService.getProductInfos(products.getContent());
			model.addAttribute("productInfos", productInfos);
			model.addAttribute("products", products);
		}
		return "setting.shop.detail";
	}

	// /////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-16 18:57:41<br>
	 */
	protected void beforeInput(Model model, HttpServletRequest request) {
	}

	/**
	 * 保存前的数据回调
	 * @todo
	 * @param shop
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-16 18:57:41
	 */
	protected void beforeSave(Shop shop, HttpServletRequest request) {
	}

	/**
	 * 保存后的数据回调
	 * @todo
	 * @param shop
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-16 18:57:41
	 */
	protected void afterSave(Shop shop, HttpServletRequest request) {

	}

}
