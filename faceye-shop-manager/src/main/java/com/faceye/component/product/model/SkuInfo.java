package com.faceye.component.product.model;

import java.util.List;

import com.faceye.component.inventory.entity.Inventory;
import com.faceye.component.product.entity.Product;
import com.faceye.component.product.entity.ProductSku;
import com.faceye.component.product.entity.SkuProperty;

/**
 * SKU组装对像
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月14日
 */
public class SkuInfo {
	private Product product = null;
	private ProductSku productSku = null;
	private Inventory inventory = null;
	private List<SkuProperty> skuProperties = null;

	public List<SkuProperty> getSkuProperties() {
		return skuProperties;
	}

	public void setSkuProperties(List<SkuProperty> skuProperties) {
		this.skuProperties = skuProperties;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductSku getProductSku() {
		return productSku;
	}

	public void setProductSku(ProductSku productSku) {
		this.productSku = productSku;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}
