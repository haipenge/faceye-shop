package com.faceye.component.inventory.model;

import java.util.ArrayList;
import java.util.List;

import com.faceye.component.inventory.entity.Invoice;
import com.faceye.component.inventory.entity.InvoiceItem;

/**
 * 构造出(入)库单明细
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月18日
 */
public class InvoiceInfo {
	private Invoice invoice = null;

	private List<InvoiceItemInfo> invoiceItemInfos = new ArrayList<InvoiceItemInfo>(0);

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public List<InvoiceItemInfo> getInvoiceItemInfos() {
		return invoiceItemInfos;
	}

	public void setInvoiceItemInfos(List<InvoiceItemInfo> invoiceItemInfos) {
		this.invoiceItemInfos = invoiceItemInfos;
	}

	

}
