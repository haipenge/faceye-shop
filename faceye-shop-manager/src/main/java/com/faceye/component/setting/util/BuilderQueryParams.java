package com.faceye.component.setting.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

/**
 * 构造mongo查询条件
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月19日
 */
public class BuilderQueryParams {
	private Map params = null;

	public BuilderQueryParams() {
		if (MapUtils.isEmpty(params)) {
			params = new HashMap();
		}
	}

	public static BuilderQueryParams getBuilderQueryParams() {
		return new BuilderQueryParams();
	}

	public BuilderQueryParams add(Object key, Object value) {
		params.put(key, value);
		return this;
	}

	public Map getParams() {
		return params;
	}
}
