package com.faceye.component.inventory.service;

import java.util.List;

import com.faceye.component.inventory.entity.InvoiceItem;
import com.faceye.component.inventory.model.InvoiceItemInfo;
import com.faceye.feature.service.BaseService;
/**
 * 模块:库存->com.faceye.compoent.inventory.service<br>
 * 说明:<br>
 * 实体:库存单据明细->com.faceye.component.inventory.entity.entity.Item 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:37<br>
 */
public interface InvoiceItemService extends BaseService<InvoiceItem,Long>{

	/**
	 * 构造 出库单条目 明细数据
	 * @todo
	 * @param invoiceItem
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月18日
	 */
	public InvoiceItemInfo getInvoiceItemInfo(InvoiceItem invoiceItem);
	
	/**
	 * 构建出入库单条目明细
	 * @todo
	 * @param invliceItems
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月18日
	 */
	public List<InvoiceItemInfo> getInvoiceItemInfos(List<InvoiceItem> invoiceItems);
}/**@generate-service-source@**/
