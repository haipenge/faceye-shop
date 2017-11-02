package com.faceye.component.util;

import java.util.UUID;

import com.faceye.feature.util.RandUtil;

import freemarker.template.utility.StringUtil;

/**
 * 订单编号生成算法
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月18日
 */
public class OrderCodeGenerator {
	private static class OrderCodeGeneratorHolder {
		static OrderCodeGenerator INSTANCE = new OrderCodeGenerator();
	}

	synchronized public static OrderCodeGenerator getInstance() {
		return OrderCodeGeneratorHolder.INSTANCE;
	}

	/**
	 * 生成订单编号,由两段组成:[yyyyMMddHHMMSS{rand - 3 } - UUID]
	 * @todo
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月18日
	 */
	synchronized public String genearte() {
//		UUID uuid = UUID.randomUUID();
		String dateRandId = RandUtil.randId("");
		//TODO 订单编号唯一性,微信订单编号最多32位
		String code=dateRandId;
//		String code = dateRandId  + uuid.getLeastSignificantBits();
		return code;
	}
}
