package com.faceye.component.setting.interceptor;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.faceye.component.customer.entity.Customer;
import com.faceye.component.customer.service.CustomerService;
import com.faceye.component.order.entity.Cart;
import com.faceye.component.order.service.CartItemService;
import com.faceye.component.order.service.CartService;
import com.faceye.component.order.service.model.CartInfo;
import com.faceye.component.security.web.entity.User;
import com.faceye.component.security.web.service.UserService;
import com.faceye.component.util.UIDGenerator;

/**
 * Session过滤
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月17日
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserService userService = null;
	@Autowired
	private CustomerService customerService = null;
	@Autowired
	private CartService cartService = null;
	@Autowired
	private CartItemService cartItemService = null;

	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		String uid = UIDGenerator.getInstance().getUid(request);
		logger.debug(">>FaceYe Got Uid is:" + uid);
		User user = this.userService.getCurrentLoginUser();
		Customer customer = null;
		if (user != null) {
			logger.debug(">>FaceYe current login user is :" + user.getUsername());
			customer = this.customerService.getCustomerByUser(user);
			if (customer == null) {
				uid = UIDGenerator.getInstance().generate();
				customer = new Customer();
				customer.setCreateDate(new Date());
				customer.setUser(user);
				customer.setUid(uid);
				this.customerService.save(customer);
			}
			Cart cart = this.cartService.getCartByUid(uid);
			if (cart == null) {
				cart = new Cart();
				cart.setUid(uid);
				if (null != customer) {
					cart.setCustomer(customer);
				}
				this.cartService.save(cart);
			}
		} else {
			logger.debug(">>FaceYe user have not login now");
		}

		// 取得购物车中商品条目数量
		if (customer != null) {
			CartInfo cartInfo = this.cartService.getCartInfo(customer.getUid());
			request.getSession().setAttribute("cartInfo", cartInfo);
			// 设置session
			request.getSession().setAttribute("uid", uid);
			// 设置header
			response.setHeader("uid", uid);
			// 设置cookie
			Cookie cookie = new Cookie("uid", uid);
			cookie.setPath("/");
			// 一年有效期
			cookie.setMaxAge(365 * 24 * 60 * 60);
			response.addCookie(cookie);
		}
		// cookie.setDomain("com.faceye");
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	}
}
