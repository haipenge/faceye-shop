package com.faceye.component.product.model;

import java.util.ArrayList;
import java.util.List;

import com.faceye.component.product.entity.Image;
import com.faceye.component.product.entity.Product;

/**
 * 产品信息
 * @author @haipenge 
 * @联系:haipenge@gmail.com
 * 创建时间:2015年6月20日
 */
public class ProductInfo {
	private Product product;
	private List<Image> images = new ArrayList<Image>(0);
	private List<ProductPropertyInfo> productPropertyInfos = new ArrayList<ProductPropertyInfo>(0);

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<ProductPropertyInfo> getProductPropertyInfos() {
		return productPropertyInfos;
	}

	public void setProductPropertyInfos(List<ProductPropertyInfo> productPropertyInfos) {
		this.productPropertyInfos = productPropertyInfos;
	}

}
