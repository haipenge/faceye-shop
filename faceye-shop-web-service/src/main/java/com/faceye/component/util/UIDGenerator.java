package com.faceye.component.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * UID生成算法
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月17日
 */
public class UIDGenerator {
	private static class UIDGeneratorHolder {
		private static final UIDGenerator INSTANCE = new UIDGenerator();
	}

	synchronized public static UIDGenerator getInstance() {
		return UIDGeneratorHolder.INSTANCE;
	}

	/**
	 * 生成UID
	 * @todo
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月17日
	 */
	synchronized public String generate() {
		String uid = "";
		uid = "" + System.currentTimeMillis();
		return uid;
	}

	synchronized public String getUid(HttpServletRequest request) {
		String uid = "";
		if (request.getSession().getAttribute("uid") != null) {
			uid = request.getSession().getAttribute("uid").toString();
		}
		// 从cookie中取得uid
		Cookie[] cookies = null;
		if (StringUtils.isEmpty(uid)) {
			cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					String name = cookie.getName();
					String value = cookie.getValue();
					String domain = cookie.getDomain();
					if (StringUtils.equals(name, "uid") && StringUtils.isNotEmpty(value)) {
						uid = value;
						break;
					}
				}
			}
		}
		// 从header中取得UID
		if (StringUtils.isEmpty(uid)) {
			String headerValue = request.getHeader("uid");
			if (StringUtils.isNotEmpty(headerValue)) {
				uid = headerValue;
			}
		}
		return uid;
	}
}
