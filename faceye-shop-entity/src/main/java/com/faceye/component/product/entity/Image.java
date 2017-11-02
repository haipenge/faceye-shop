package com.faceye.component.product.entity;

import java.io.Serializable;

import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.faceye.component.setting.entity.Shop;
import com.faceye.feature.service.PropertyService;
import com.faceye.feature.util.bean.BeanContextUtil;

/**
 * Image ORM 实体<br>
 * 数据库表:product_image<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection = "product_image")
public class Image implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	@Id
	private Long id = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String filename = "";

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
		if (StringUtils.isNotEmpty(filename) && filename.length() > 8) {
			String path = filename.substring(0, 8) + "/" + filename;
			this.setPath(path);
		}
	}

	/**
	    * 说明:路径<br>
	    * 属性名: path<br>
	    * 类型: String<br>
	    * 数据库字段:path<br>
	    * @author haipenge<br>
	    */

	private String path;

	public String getPath() {

		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 说明:链接<br>
	 * 属性名: url<br>
	 * 类型: String<br>
	 * 数据库字段:url<br>
	 * @author haipenge<br>
	 */
	@Transient
	private String url;

	public String getUrl() {
		String imageServer = BeanContextUtil.getBean(PropertyService.class).get("img.server");
		if (StringUtils.isNotEmpty(imageServer)) {
			url = imageServer + "/" + this.getPath();
		}
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 说明:默认<br>
	 * 属性名: isDefault<br>
	 * 类型: Boolean<br>
	 * 数据库字段:is_default<br>
	 * @author haipenge<br>
	 */

	private Boolean isDefault = Boolean.FALSE;

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	@DBRef
	private Shop shop = null;

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	@DBRef
	private Product product = null;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
/**@generate-entity-source@**/

