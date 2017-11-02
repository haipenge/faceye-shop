package com.faceye.component.customer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.faceye.component.lbs.entity.GeoLibrary;

/**
 * 模块:客户->customer->Address<br>
 * 说明:<br>
 * 实体:送货地址->com.faceye.component.customer.entity.Address Mongo 对像<br>
 * mongo数据集:customer_address<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:37<br>
 *spring-data-mongo支持的注释类型<br>
 *@Id - 文档的唯一标识，在mongodb中为ObjectId，它是唯一的，通过时间戳+机器标识+进程ID+自增计数器（确保同一秒内产生的Id不会冲突）构成。<br>
 *@Document - 把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。<br>
 *@DBRef - 声明类似于关系数据库的关联关系。ps：暂不支持级联的保存功能，当你在本实例中修改了DERef对象里面的值时，单独保存本实例并不能保存DERef引用的对象，它要另外保存<br>
 *@Indexed - 声明该字段需要索引，建索引可以大大的提高查询效率。<br>
 *@CompoundIndex - 复合索引的声明，建复合索引可以有效地提高多字段的查询效率。<br>
 *@GeoSpatialIndexed - 声明该字段为地理信息的索引。<br>
 *@Transient - 映射忽略的字段，该字段不会保存到<br>
 *@PersistenceConstructor - 声明构造函数，作用是把从数据库取出的数据实例化为对象。该构造函数传入的值为从DBObject中取出的数据。<br>
 *@CompoundIndexes({
 *    @CompoundIndex(name = "age_idx", def = "{'lastName': 1, 'age': -1}")
 *})
 *@Indexed(unique = true)
 */

@Document(collection = "customer_address")
public class Address implements Serializable {
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
	* 说明:客户<br>
	* 属性名: customer<br>
	* 类型: com.faceye.component.customer.entity.Customer<br>
	* 数据库字段:customer<br>
	* @author haipenge<br>
	*/
	@DBRef
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	/**
	    * 说明:创建日期<br>
	    * 属性名: createDate<br>
	    * 类型: Date<br>
	    * 数据库字段:createDate<br>
	    * @author haipenge<br>
	    */

	private Date createDate = new Date();

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



   /**
    * 说明:手机<br>
    * 属性名: mobile<br>
    * 类型: String<br>
    * 数据库字段:mobile<br>
    * @author haipenge<br>
    */
    
	private  String mobile;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	

	
   /**
    * 说明:收件人<br>
    * 属性名: receiveUserName<br>
    * 类型: String<br>
    * 数据库字段:receive_user_name<br>
    * @author haipenge<br>
    */
    
	private  String receiveUserName;
	public String getReceiveUserName() {
		return receiveUserName;
	}
	public void setReceiveUserName(String receiveUserName) {
		this.receiveUserName = receiveUserName;
	}
	

	
   /**
    * 说明:省<br>
    * 属性名: province<br>
    * 类型: String<br>
    * 数据库字段:province<br>
    * @author haipenge<br>
    */
    
	private  String province=null;
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	

	
   /**
    * 说明:市<br>
    * 属性名: city<br>
    * 类型: String<br>
    * 数据库字段:city<br>
    * @author haipenge<br>
    */
    
	private  String city;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	

	
   /**
    * 说明:区<br>
    * 属性名: area<br>
    * 类型: String<br>
    * 数据库字段:area<br>
    * @author haipenge<br>
    */
    
	private  String area;
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	

	
   /**
    * 说明:街道<br>
    * 属性名: street<br>
    * 类型: String<br>
    * 数据库字段:street<br>
    * @author haipenge<br>
    */
    
	private  String street;
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	

	
   /**
    * 说明:详细地址<br>
    * 属性名: detail<br>
    * 类型: String<br>
    * 数据库字段:detail<br>
    * @author haipenge<br>
    */
    
	private  String detail;
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	

	
   /**
    * 说明:默认<br>
    * 属性名: isDefault<br>
    * 类型: Boolean<br>
    * 数据库字段:is_default<br>
    * @author haipenge<br>
    */
    
	private  Boolean isDefault=false;
	public Boolean getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	

	
   /**
    * 说明:是否删除<br>
    * 属性名: isRemoved<br>
    * 类型: Boolean<br>
    * 数据库字段:is_removed<br>
    * @author haipenge<br>
    */
    
	private  Boolean isRemoved=false;
	public Boolean getIsRemoved() {
		return isRemoved;
	}
	public void setIsRemoved(Boolean isRemoved) {
		this.isRemoved = isRemoved;
	}
	
	/**
	 * GEO地址
	 */
	
	private GeoLibrary geoLibrary=null;

	public GeoLibrary getGeoLibrary() {
		return geoLibrary;
	}

	public void setGeoLibrary(GeoLibrary geoLibrary) {
		this.geoLibrary = geoLibrary;
	}
	
	/**
	 * 显示地址详情
	 */
	private String show="";

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}
	
	
	
	
}/**@generate-entity-source@**/
	
