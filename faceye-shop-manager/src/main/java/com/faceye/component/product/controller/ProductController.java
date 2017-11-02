package com.faceye.component.product.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.faceye.component.inventory.entity.Inventory;
import com.faceye.component.inventory.entity.InvoiceItem;
import com.faceye.component.order.entity.Item;
import com.faceye.component.product.entity.Category;
import com.faceye.component.product.entity.Product;
import com.faceye.component.product.model.SkuInfo;
import com.faceye.component.product.service.FormBuilder;
import com.faceye.component.product.service.ProductService;
import com.faceye.component.setting.controller.BaseShopController;
import com.faceye.component.setting.entity.Shop;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.Json;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

/**
 * 模块:产品->com.faceye.compoent.product.controller<br>
 * 说明:<br>
 * 实体:产品:com.faceye.component.product.entity.entity.Product<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2015-6-13 11:31:35<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/product/product")
public class ProductController extends BaseShopController<Product, Long, ProductService> {

	@Autowired
	private FormBuilder formBuilder = null;

	@Autowired
	public ProductController(ProductService service) {
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
			searchParams.put("EQ|shop.$id", shop.getId());
			String categoryId = MapUtils.getString(searchParams, "categoryId");
			if (StringUtils.isNotEmpty(categoryId)) {
				searchParams.put("EQ|category.$id", Long.parseLong(categoryId));
			}
			Page<Product> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
			model.addAttribute("page", page);
			Map categorySearchParams = new HashMap();
			categorySearchParams.put("EQ|shop.$id", shop.getId());
			List<Category> categories = this.categoryService.getPage(categorySearchParams, 1, 0).getContent();
			model.addAttribute("categories", categories);
		}
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "product.product.manager";
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
			Product entity = this.service.get(id);
			beforeInput(model, request, entity);
			model.addAttribute("product", entity);
		}
		return "product.product.update";
	}

	/**
	 * 添加产品第一步，选择分类
	 * @todo
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月19日
	 */
	@RequestMapping("/onCategoryChange")
	@ResponseBody
	public String selectCategory(Model model, HttpServletRequest request) {
		Shop shop = this.shopService.getCurrentUserShop();
		Map params = HttpUtil.getRequestParams(request);
		Long productId = MapUtils.getLong(params, "productId");
		Long categoryId = MapUtils.getLong(params, "categoryId");
		Product product = null;
		Category category = null;
		if (null != productId) {
			product = this.service.get(productId);
		}
		if (categoryId != null) {
			category = this.categoryService.get(categoryId);
		}
		String html = formBuilder.build(product, this.dynamicPropertyService.getDynamicProperties(shop, category), params);
		// return "product.product.select.category";
		Map map = new HashMap();
		map.put("html", html);
		String json = Json.toJson(map);
		return json;
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
	public String input(Product product, Model model, HttpServletRequest request) {
		beforeInput(model, request, product);
		return "product.product.update";
	}

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:35<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Product product, BindingResult bindingResult, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request, product);
			return "product.product.update";
		} else {
			this.beforeSave(product, request);
			Map params = HttpUtil.getRequestParams(request);
			product = this.service.save(params);
			this.afterSave(product, request);
			// return "redirect:/product/product/home";
			return "redirect:/product/product/toSetInventory/" + product.getId();
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
			Product product = this.service.get(id);
			if (product != null && this.beforeRemove(product, model)) {
				this.service.remove(product);
				MessageBuilder.getInstance().setMessage(model, this.getI18N("global.remove.success"));
			}

		}
		return "redirect:/product/product/home";
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
				Product product = this.service.get(Long.parseLong(id));
				if (product != null && this.beforeRemove(product, model)) {
					this.service.remove(product);
					MessageBuilder.getInstance().setMessage(model, product.getName() + " " + this.getI18N("global.remove.success"));
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
			Product product = this.service.get(id);
			model.addAttribute("product", product);
			List<SkuInfo> skuInfos = this.productSkuService.getProductSkuInfos(product);
			model.addAttribute("skuInfos", skuInfos);
		}

		return "product.product.detail";
	}

	/**
	 * 转向设置商品库存
	 * @todo
	 * @param id
	 * @param model
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月14日
	 */
	@RequestMapping("/toSetInventory/{id}")
	public String toSetInventory(@PathVariable Long id, Model model) {
		if (id != null) {
			Product product = this.service.get(id);
			model.addAttribute("product", product);
			List<SkuInfo> skuInfos = this.productSkuService.getProductSkuInfos(product);
			model.addAttribute("skuInfos", skuInfos);
		}
		return "product.product.set.inventory";
	}

	/**
	 * 设置为在售或停售
	 * @todo
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月19日
	 */
	@RequestMapping("/setOnSaleOrNot/{id}")
	public String setOnSaleOrNot(@PathVariable Long id, @RequestParam(required = true) Boolean isOnSale, RedirectAttributesModelMap model) {
		Product product = this.service.get(id);
		if (product != null) {
			product.setIsOnSale(isOnSale);
			this.service.save(product);
			MessageBuilder.getInstance().setMessage(model, product.getName() + " 已设置为 " + (isOnSale ? "在售" : "停售"));
		}
		return "redirect:/product/product/home";
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
	protected void beforeInput(Model model, HttpServletRequest request, Product product) {
		Map searchParams = new HashMap();
		Shop shop = this.shopService.getCurrentUserShop();
		searchParams.put("EQ|shop.$id", shop.getId());
		List<Category> categorys = this.categoryService.getPage(searchParams, 1, 0).getContent();
		model.addAttribute("categorys", categorys);
		String html = formBuilder.build(product, this.dynamicPropertyService.getDynamicProperties(shop, product.getCategory()),
				HttpUtil.getRequestParams(request));
		model.addAttribute("html", html);
	}

	/**
	 * 保存前的数据回调
	 * @todo
	 * @param product
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-13 11:31:35
	 */
	protected void beforeSave(Product product, HttpServletRequest request) {
		product.setShop(this.shopService.getCurrentUserShop());
	}

	protected boolean beforeRemove(Product product, Model model) {
		boolean res = true;
		// 检查是否有订单数据
		Map searchParams = new HashMap();
		searchParams.put("EQ|product.$id", product.getId());
		Page<Item> items = this.itemService.getPage(searchParams, 1, 1);
		if (items.getTotalElements() > 0) {
			res = false;
			MessageBuilder.getInstance().setMessage(model, product.getName() + " 有" + items.getTotalElements() + "条订单,不可删除.");
			return res;
		}
		searchParams = new HashMap();
		// 库存检查
		searchParams.put("EQ|product.$id", product.getId());
		Page<Inventory> inventories = this.inventoryService.getPage(searchParams, 1, 1);
		if (inventories.getTotalElements() > 0) {
			res = false;
			MessageBuilder.getInstance().setMessage(model, product.getName() + " 有" + inventories.getTotalElements() + "条库存数据,不可删除.");
			return res;
		}
		// 出入库单条目检查
		searchParams.put("EQ|product.$id", product.getId());
		Page<InvoiceItem> invoiceItems = this.invoiceItemService.getPage(searchParams, 1, 1);
		if (inventories.getTotalElements() > 0) {
			res = false;
			MessageBuilder.getInstance().setMessage(model, product.getName() + " 有" + invoiceItems.getTotalElements() + "条出库单,不可删除.");
			return res;
		}
		return res;
	}

	/**
	 * 保存后的数据回调
	 * @todo
	 * @param product
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-13 11:31:35
	 */
	protected void afterSave(Product product, HttpServletRequest request) {

	}

}
