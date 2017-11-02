package com.faceye.component.product.controller;

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

import com.faceye.component.product.entity.DynamicProperty;
import com.faceye.component.product.entity.DynamicPropertyValue;
import com.faceye.component.product.entity.ProductProperty;
import com.faceye.component.product.service.DynamicPropertyService;
import com.faceye.component.product.service.DynamicPropertyValueService;
import com.faceye.component.product.service.ProductPropertyService;
import com.faceye.component.setting.controller.BaseShopController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

/**
 * 模块:产品->com.faceye.compoent.product.controller<br>
 * 说明:<br>
 * 实体:动态属性值:com.faceye.component.product.entity.entity.DynamicPropertyValue<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2015-6-13 11:31:35<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/product/dynamicPropertyValue")
public class DynamicPropertyValueController extends BaseShopController<DynamicPropertyValue, Long, DynamicPropertyValueService> {
	@Autowired
	private DynamicPropertyService dynamicPropertyService = null;
	@Autowired
	private ProductPropertyService productPropertyService = null;

	@Autowired
	public DynamicPropertyValueController(DynamicPropertyValueService service) {
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
		Long dynamicPropertyId = MapUtils.getLong(searchParams, "EQ|dynamicProperty.$id");
		if (null != dynamicPropertyId) {
			DynamicProperty dynamicProperty = this.dynamicPropertyService.get(dynamicPropertyId);
			model.addAttribute("dynamicProperty", dynamicProperty);
		}
		beforeInput(model, request);
		Page<DynamicPropertyValue> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "product.dynamicPropertyValue.manager";
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
			DynamicPropertyValue entity = this.service.get(id);
			model.addAttribute("dynamicPropertyValue", entity);
		}
		return "product.dynamicPropertyValue.update";
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
	public String input(DynamicPropertyValue dynamicPropertyValue, Model model, HttpServletRequest request) {
		beforeInput(model, request, dynamicPropertyValue);
		return "product.dynamicPropertyValue.update";
	}

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:35<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid DynamicPropertyValue dynamicPropertyValue, BindingResult bindingResult, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request, dynamicPropertyValue);
			return "product.dynamicPropertyValue.update";
		} else {
			this.beforeSave(dynamicPropertyValue, request);
			this.service.save(dynamicPropertyValue);
			this.afterSave(dynamicPropertyValue, request);
			return "redirect:/product/dynamicPropertyValue/home?EQ|dynamicProperty.$id="
					+ dynamicPropertyValue.getDynamicProperty().getId();
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
		Long dynamicPropertyId = null;
		if (id != null) {
			DynamicPropertyValue dynamicPropertyValue = this.service.get(id);
			dynamicPropertyId = dynamicPropertyValue.getDynamicProperty().getId();
			if(this.beforeRemove(dynamicPropertyValue,model)) {
				this.service.remove(dynamicPropertyValue);
				MessageBuilder.getInstance().setMessage(model, dynamicPropertyValue.getValue()+" "+this.getI18N("global.remove.success"));
			} 

		}
		return "redirect:/product/dynamicPropertyValue/home?EQ|dynamicProperty.$id=" + dynamicPropertyId;
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
				DynamicPropertyValue dynamicPropertyValue = this.service.get(Long.parseLong(id));
				if(this.beforeRemove(dynamicPropertyValue, model)){
					this.service.remove(dynamicPropertyValue);
					MessageBuilder.getInstance().setMessage(model, dynamicPropertyValue.getValue()+" "+this.getI18N("global.remove.success"));
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
			DynamicPropertyValue entity = this.service.get(id);
			model.addAttribute("dynamicPropertyValue", entity);
		}
		return "product.dynamicPropertyValue.detail";
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
	protected void beforeInput(Model model, HttpServletRequest request, DynamicPropertyValue dynamicPropertyValue) {
		// List<DynamicProperty> dynamicPropertys=this.dynamicPropertyService.getAll();
		// model.addAttribute("dynamicPropertys", dynamicPropertys);
		Map params = HttpUtil.getRequestParams(request);
		Long dynamicePropertyId = MapUtils.getLong(params, "dynamicPropertyId");
		if (dynamicePropertyId != null) {
			DynamicProperty dynamicProperty = this.dynamicPropertyService.get(dynamicePropertyId);
			dynamicPropertyValue.setDynamicProperty(dynamicProperty);
		}

	}

	/**
	 * 保存前的数据回调
	 * @todo
	 * @param dynamicPropertyValue
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-13 11:31:35
	 */
	protected void beforeSave(DynamicPropertyValue dynamicPropertyValue, HttpServletRequest request) {
		dynamicPropertyValue.setShop(this.shopService.getCurrentUserShop());
	}
	
	/**
	 * 删除前回调
	 * @todo
	 * @param dynamicPropertyValue
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月19日
	 */
	protected boolean beforeRemove(DynamicPropertyValue dynamicPropertyValue,Model model){
		boolean res=true;
		List<ProductProperty> productProperties = this.productPropertyService
				.getProductPropertyByDynamicPropertyValue(dynamicPropertyValue);
		if (CollectionUtils.isNotEmpty(productProperties)) {
			res=false;
			MessageBuilder.getInstance().setMessage(model,
					dynamicPropertyValue.getValue() + "," + this.getI18N("product.dynamicPropertyValue.remove.error.msg"));
		}
		return res;
	}

	/**
	 * 保存后的数据回调
	 * @todo
	 * @param dynamicPropertyValue
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2015-6-13 11:31:35
	 */
	protected void afterSave(DynamicPropertyValue dynamicPropertyValue, HttpServletRequest request) {

	}

}
