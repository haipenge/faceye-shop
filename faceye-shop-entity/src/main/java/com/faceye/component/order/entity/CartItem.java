package com.faceye.component.order.entity;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.faceye.component.customer.entity.Customer;
import com.faceye.component.product.entity.ProductSku;

/**
 * CartItem ORM 实体<br>
 * 数据库表:order_cartItem<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection = "order_cart_item")
public class CartItem implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	@Id
	private Long id = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 说明:数量<br>
	 * 属性名: quantity<br>
	 * 类型: Integer<br>
	 * 数据库字段:quantity<br>
	 * @author haipenge<br>
	 */

	private Integer quantity = 0;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * 说明:产品单元<br>
	 * 属性名: productSku<br>
	 * 类型: ProductSku<br>
	 * 数据库字段:product_sku_id<br>
	 * @author haipenge<br>
	 */
	@DBRef
	private ProductSku productSku;

	public ProductSku getProductSku() {
		return productSku;
	}

	public void setProductSku(ProductSku productSku) {
		this.productSku = productSku;
	}

	/**
	 * 客户
	 */
	@DBRef
	private Customer customer = null;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * 所属购物车
	 */
	@DBRef
	private Cart cart = null;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	/**
	 * 说明:状态<br>
	 * 0->创建
	 * 1->已提交订单
	 * 2->已删除
	 * 属性名: status<br>
	 * 类型: Integer<br>
	 * 数据库字段:status<br>
	 * @author haipenge<br>
	 */

	private Integer status = 0;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 说明:状态名<br>
	 * 属性名: statusName<br>
	 * 类型: String<br>
	 * 数据库字段:status_name<br>
	 * @author haipenge<br>
	 */
	@Transient
	private String statusName = "创建";

	public String getStatusName() {
		if (this.getStatus() == 1) {
			// 已形成订单
			statusName = "已成单";
		} else if (this.getStatus() == 2) {
			statusName = "已删除";
		}
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}
/**@generate-entity-source@**/

