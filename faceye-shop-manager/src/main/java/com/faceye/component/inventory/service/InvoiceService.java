package com.faceye.component.inventory.service;

import java.util.List;

import com.faceye.component.inventory.entity.Invoice;
import com.faceye.component.inventory.model.InvoiceInfo;
import com.faceye.feature.service.BaseService;
/**
 * 模块:库存->com.faceye.compoent.inventory.service<br>
 * 说明:<br>
 * 实体:库存单据->com.faceye.component.inventory.entity.entity.Invoice 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:37<br>
 */
public interface InvoiceService extends BaseService<Invoice,Long>{

	/**
	 * 构造 出(入)库单明细
	 * @todo
	 * @param invoices
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月18日
	 */
	public List<InvoiceInfo> getInvoiceInfos(List<Invoice> invoices);
	
	/**
	 * 构造单条 出(入)库单明细
	 * @todo
	 * @param invoice
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月18日
	 */
	public InvoiceInfo getInvoiceInfo(Invoice invoice);
}/**@generate-service-source@**/
