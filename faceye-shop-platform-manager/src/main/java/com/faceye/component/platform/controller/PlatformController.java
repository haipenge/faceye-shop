package com.faceye.component.platform.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.faceye.component.platform.entity.Platform;
import com.faceye.component.platform.service.PlatformService;
import com.faceye.component.setting.entity.Shop;
import com.faceye.component.setting.service.ShopService;
import com.faceye.component.weixin.entity.Account;
import com.faceye.component.weixin.service.oauth2.OAuth2Service;
import com.faceye.component.weixin.util.WeixinConstants;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.GlobalEntity;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

/**
 * 模块:platform<br>
 * 实体:Platform<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/platform/platform")
public class PlatformController extends BaseController<Platform, Long, PlatformService> {
	@Autowired
    private ShopService shopService=null;
	@Autowired
    private OAuth2Service oAuth2Service=null;
	@Value("#{property['shop.host']}")
	private String  shopHost="";
	@Autowired
	public PlatformController(PlatformService service) {
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
		Page<Platform> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		//Got Shop
		
		Page<Shop> shops=this.shopService.getPage(null, 1, 15);
		model.addAttribute("shops", shops);
 		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("platform.platform"));
		model.addAttribute("global",global);
		return "platform.platform.manager";
	}
	/**
	 * 生成微信店铺的URL
	 * @todo
	 * @param shopId
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年10月7日
	 */
	@RequestMapping("/generateWeixinShopUrl")
	@ResponseBody
	public Map generateWeixinShopUrl(@RequestParam(required=true)Long shopId){
	  Map res=new HashMap();
	  Shop shop=this.shopService.get(shopId);
	  Account account=shop.getAccount();
	  String appid=account.getAppId();
	  String redirectUri=this.shopHost+"/setting/shop/detail/"+shop.getId()+"?appId="+appid;
	  String url=this.oAuth2Service.getOAuth2Url(appid, redirectUri, WeixinConstants.OAUTH2_SCOPE_BASE, true);
	  res.put("url", url);
	  return res;
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
			Platform entity=this.service.get(id);
			model.addAttribute("platform", entity);
		}
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("platform.platform.edit"));
		model.addAttribute("global",global);
		return "platform.platform.update";
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
	public String input(Platform platform,Model model,HttpServletRequest request){
		beforeInput(model,request);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("platform.platform.add"));
		model.addAttribute("global",global);
		return "platform.platform.update";
	}
	
	
    

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Platform platform,BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model,HttpServletRequest request) {
		if(bindingResult.hasErrors()){
			beforeInput(model,request);
			return "platform.platform.update";
		}else{
		   this.beforeSave(platform,request);
		   this.service.save(platform);
		   return "redirect:/platform/platform/home";
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
			Platform platform=this.service.get(id);
			if(platform!=null){
				if(beforeRemove(platform,model)){
					this.service.remove(platform);	
					//MessageBuilder.getInstance().setMessage(model,platform+" "+ this.getI18N("global.remove.success"));
				}
			}
		}
		return "redirect:/platform/platform/home";
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
	public String remove(@RequestParam(required=true) String  ids, RedirectAttributes redirectAttributes,RedirectAttributesModelMap model) {
		if(StringUtils.isNotEmpty(ids)){
			String [] idArray=ids.split(",");
			for(String id:idArray){
				Platform platform=this.service.get(Long.parseLong(id));
				if(platform!=null){
					if(beforeRemove(platform,model)){
						this.service.remove(platform);	
						//MessageBuilder.getInstance().setMessage(model,platform+" "+ this.getI18N("global.remove.success"));
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
			Platform platform=this.service.get(id);
			model.addAttribute("platform", platform);
		}
		return "platform.platform.detail";
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
	protected void beforeSave(Platform platform,HttpServletRequest request){
		
	}
	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(Platform platform,Model model){
		boolean res=true;
		
		return res;
	}

}
