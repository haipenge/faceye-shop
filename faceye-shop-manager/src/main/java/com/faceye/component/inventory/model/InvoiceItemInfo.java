package com.faceye.component.inventory.model;

import java.util.ArrayList;
import java.util.List;

import com.faceye.component.inventory.entity.InvoiceItem;
import com.faceye.component.product.entity.ProductSku;
import com.faceye.component.product.entity.SkuProperty;

/**
 * 出(入)库单条目明细
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月18日
 */
public class InvoiceItemInfo {
	private InvoiceItem invoiceItem = null;
	private ProductSku productSku = null;
	private List<SkuProperty> skuProperties = new ArrayList<SkuProperty>(0);

	public InvoiceItem getInvoiceItem() {
		return invoiceItem;
	}

	public void setInvoiceItem(InvoiceItem invoiceItem) {
		this.invoiceItem = invoiceItem;
	}

	public ProductSku getProductSku() {
		return productSku;
	}

	public void setProductSku(ProductSku productSku) {
		this.productSku = productSku;
	}

	public List<SkuProperty> getSkuProperties() {
		return skuProperties;
	}

	public void setSkuProperties(List<SkuProperty> skuProperties) {
		this.skuProperties = skuProperties;
	}

}
