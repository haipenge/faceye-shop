package com.faceye.component.setting.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceye.component.security.entity.User;
import com.faceye.component.security.service.UserService;
import com.faceye.component.setting.entity.Shop;
import com.faceye.component.setting.service.ShopService;
import com.faceye.component.weixin.entity.Account;
import com.faceye.component.weixin.service.AccountService;
import com.faceye.component.weixin.service.oauth2.OAuth2Service;
import com.faceye.feature.upload.Upload;
import com.faceye.feature.upload.UploadResult;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;

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
public class ShopController extends BaseShopController<Shop, Long, ShopService> {
	@Autowired
	private UserService userService = null;
	@Autowired
	private AccountService accountService = null;
	@Autowired
	private OAuth2Service oauth2Service=null;
	@Value("#{property['shop.default.host']}")
	private String host="";
	FormHttpMessageConverter f=null;

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
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		Shop shop = this.service.getCurrentUserShop();
		model.addAttribute("shop", shop);
		beforeInput(model, request);
		//生成微信授权URL
		Account account=shop.getAccount();
		String oauth2Url=oauth2Service.getOAuth2Url(account.getAppId(), host+"/setting/shop/detail/"+shop.getId()+"?appId="+account.getAppId(), "", true);
		model.addAttribute("oauth2Url", oauth2Url);
		return "setting.shop.manager";
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
			Shop entity = this.service.get(id);
			model.addAttribute("shop", entity);
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
	 * 创建日期:2015-6-13 11:31:35<br>
	 */
	@RequestMapping(value = "/input")
	public String input(Shop shop, Model model, HttpServletRequest request) {
		Shop userShop = this.service.getCurrentUserShop();
		if (userShop != null) {
			return "redirect:/setting/shop/home";
		} else {
			beforeInput(model, request);
			return "setting.shop.update";
		}
	}

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2015-6-13 11:31:35<br>
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
	 * 创建日期:2015-6-13 11:31:35<br>
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
	 * 创建日期:2015-6-13 11:31:35<br>
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
	 * 创建日期:2015-6-13 11:31:35<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			Shop entity = this.service.get(id);
			model.addAttribute("shop", entity);
		}
		return "setting.shop.detail";
	}

	/**
	 * 设置Logo
	 * @todo
	 * @param id
	 * @param model
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年11月24日
	 */
	@RequestMapping("/setLogo/{id}")
	public String setLogo(@PathVariable Long id, Model model) {
		if (id != null) {
			Shop entity = this.service.get(id);
			model.addAttribute("shop", entity);
		}
		return "setting.shop.set.logo";
	}

	/**
	 * 保存店铺Logo
	 * @todo
	 * @param shopId
	 * @param filename
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年11月24日
	 */
	@RequestMapping("/saveLogo")
	@ResponseBody
	public String saveLogo(@RequestParam(required = true) Long shopId, @RequestParam(required = true) String filename) {
		Shop shop = this.service.get(shopId);
		shop.setLogoFilename(filename);
		this.service.save(shop);
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

	/**
	 * 取得店铺的Logo图片
	 * @todo
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年11月24日
	 */
	@RequestMapping("/getLogo")
	@ResponseBody
	public String getLogo(@RequestParam(required = true) Long shopId) {
		Shop shop = this.service.get(shopId);
		List<UploadResult> uploadResults = new ArrayList<UploadResult>(0);
		if (StringUtils.isNotEmpty(shop.getLogoPath())) {
			UploadResult uploadResult = new UploadResult();
			uploadResult.setGenerateFileName(shop.getLogoFilename());
			uploadResult.setPath(shop.getLogoPath());
			uploadResult.setName("a");
			uploadResult.setSize("5");
			uploadResults.add(uploadResult);
		}
		return Upload.uploadResults2Json(uploadResults);
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
		User user = this.userService.getCurrentLoginUser();
		List<Account> accounts = this.accountService.getAccountsByUser(user);
		model.addAttribute("accounts", accounts);
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
	protected void beforeSave(Shop shop, HttpServletRequest request) {
		User user = this.userService.getCurrentLoginUser();
		shop.setUser(user);
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
	protected void afterSave(Shop shop, HttpServletRequest request) {

	}

}
