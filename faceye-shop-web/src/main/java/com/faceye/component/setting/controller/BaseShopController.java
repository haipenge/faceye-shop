package com.faceye.component.setting.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.faceye.component.customer.service.CustomerService;
import com.faceye.component.order.service.CartService;
import com.faceye.component.setting.service.ShopService;
import com.faceye.component.util.UIDGenerator;
import com.faceye.feature.service.BaseService;
import com.faceye.feature.util.bean.BeanContextUtil;

public abstract class BaseShopController<T, ID extends Serializable, S extends BaseService<T, ID>> {
	protected S service = null;
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	protected ShopService shopService = null;
	@Autowired
	protected CustomerService customerService = null;
	@Autowired
	protected CartService cartService=null;

	public BaseShopController(S service) {
		this.service = service;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	/**
	 * 取得页码
	 * @todo
	 * @param params
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年6月26日
	 */
	public int getPage(Map params) {
		int page = MapUtils.getIntValue(params, "page");
		if (page == 0) {
			page = 1;
		}
		return page;
	}

	/**
	 * 取得每页数据大小
	 * @todo
	 * @param params
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年6月26日
	 */
	public int getSize(Map params) {
		int size = MapUtils.getIntValue(params, "size");
		if (size == 0) {
			size = 20;
		}
		return size;
	}

	/**
	 * 新增、编辑页面的前置逻辑处理
	 * @todo
	 * @param model
	 * @param request
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年4月5日
	 */
	protected void beforeInput(Model model, HttpServletRequest request) {

	}

	/**
	 * 取得Spring application context
	 * @todo
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年4月5日
	 */
	protected ApplicationContext getApplicationContext() {
		ApplicationContext ctx = null;
		ctx = BeanContextUtil.getApplicationContext();
		return ctx;
	}

	/**
	 * 取得i18n数据
	 * @todo
	 * @param key
	 * @param args
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年4月5日
	 */
	protected String getI18N(String key, Object[] args) {
		ApplicationContext ctx = this.getApplicationContext();
		String res = "";
		if (StringUtils.isNotEmpty(key)) {
			res = ctx.getMessage(key, args, Locale.CHINA);
		}
		return res;
	}

	protected String getI18N(String key) {
		return this.getI18N(key, null);
	}

	/**
	 * 重置查询参数
	 * @todo
	 * @param searchParams
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年5月5日
	 */
	protected void resetSearchParams(Map searchParams) {
		if (MapUtils.isNotEmpty(searchParams)) {
			Iterator<String> keyIterator = searchParams.keySet().iterator();
			while (keyIterator.hasNext()) {
				String key = keyIterator.next();
				Object value = searchParams.get(key);
				logger.debug(">>FaceYe Key is >>:" + key);
				if (StringUtils.indexOf(key, "|") != -1) {
					String subKey = StringUtils.split(key, "|")[1];
					logger.debug(">>FaceYe -->Sub key is:" + subKey);
					searchParams.put(subKey, value);
					searchParams.remove(key);
					keyIterator = searchParams.keySet().iterator();
				}
			}
		}
		// searchParams=res;
	}

	/**
	 * 取得UID
	 * @todo
	 * @param request
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月17日
	 */
	protected String getUid(HttpServletRequest request) {
		String uid = "";
		uid = UIDGenerator.getInstance().getUid(request);
		return uid;
	}
}
