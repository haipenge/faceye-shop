package com.faceye.component.order.entity;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.faceye.component.customer.entity.Customer;
/**
 * Cart ORM 实体<br>
 * 数据库表:order_cart<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="order_cart")
public class Cart implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	@Id
	private  Long id=null;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

   /**
    * 说明:用户标识<br>
    * 属性名: uid<br>
    * 类型: String<br>
    * 数据库字段:uid<br>
    * @author haipenge<br>
    */
    
	private  String uid;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * 客户
	 */
	@DBRef
	private Customer customer=null;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}/**@generate-entity-source@**/
	
