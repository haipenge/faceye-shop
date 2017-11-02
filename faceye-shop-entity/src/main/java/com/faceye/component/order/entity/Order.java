package com.faceye.component.order.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.faceye.component.customer.entity.Address;
import com.faceye.component.customer.entity.Customer;
import com.faceye.component.setting.entity.Shop;

/**
 * 模块:订单->order->Order<br>
 * 说明:<br>
 * 实体:订单->com.faceye.component.order.entity.Order Mongo 对像<br>
 * mongo数据集:order_order<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:36<br>
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

@Document(collection = "order_order")
public class Order implements Serializable {
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
	* 说明:订单编号<br>
	* 属性名: code<br>
	* 类型: java.lang.String<br>
	* 数据库字段:<br>
	* @author haipenge<br>
	*/
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	* 说明:订单状态<br>
	* 属性名: status ->0:已下单,1:已支付,11:支付失败,2:已发货,3:已签收,4:已作废<br> 
	* 类型: java.lang.String<br>
	* 数据库字段:<br>
	* @author haipenge<br>
	*/
	private Integer status = 0;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Transient
	private String statusName = "已下单";

	public String getStatusName() {
		if (this.getStatus() == 1) {
			statusName = "已支付";
		} else if (this.getStatus() == 11) {
			statusName = "支付失败";
		} else if (this.getStatus() == 2) {
			statusName = "已发货";
		} else if (this.getStatus() == 3) {
			statusName = "已签收";
		} else if (this.status == 4) {
			statusName = "已作废";
		}
		return statusName;
	}

	public void setStatusName(String statusName) {

		this.statusName = statusName;
	}

	/**
	* 说明:店铺<br>
	* 属性名: shop<br>
	* 类型: com.faceye.component.setting.entity.Shop<br>
	* 数据库字段:shop<br>
	* @author haipenge<br>
	*/
	@DBRef
	private Shop shop;

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
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
	* 说明:是否支付<br>
	* 属性名: isPaid<br>
	* 类型: java.lang.Boolean<br>
	* 数据库字段:<br>
	* @author haipenge<br>
	*/
	private Boolean isPaid;

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	/**
	* 说明:支付方式<br>
	* 属性名: payWay<br>
	* 类型: java.lang.String<br>
	* 数据库字段:<br>
	* @author haipenge<br>
	*/
	private String payWay="";

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	
	@Transient
	private String payWayName="";
	

	public String getPayWayName() {
		if(StringUtils.equalsIgnoreCase(this.getPayWay(),"WEIXIN")){
			payWayName="微信支付";
		}else if(StringUtils.equalsIgnoreCase(this.getPayWay(),"ALIPAY")){
			payWayName="支付宝";
		}else{
			payWayName = "货到付款";
		}
		return payWayName;
	}

	public void setPayWayName(String payWayName) {
		this.payWayName = payWayName;
	}

	/**
	* 说明:支付日期<br>
	* 属性名: payDate<br>
	* 类型: java.util.Date<br>
	* 数据库字段:<br>
	* @author haipenge<br>
	*/
	private Date payDate;

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	/**
	* 说明:总价<br>
	* 属性名: totalFee<br>
	* 类型: java.lang.Float<br>
	* 数据库字段:<br>
	* @author haipenge<br>
	* 总价->分
	*/
	private Integer totalFee = 0;

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}
	@Transient
	private Float totalFeeYuan=0F;
	

	public Float getTotalFeeYuan() {
		totalFeeYuan=new Float(this.getTotalFee())/100;
		return totalFeeYuan;
	}

	public void setTotalFeeYuan(Float totalFeeYuan) {
		this.setTotalFee(new Float(totalFeeYuan*100).intValue());
		this.totalFeeYuan = totalFeeYuan;
	}

	/**
	* 说明:备注<br>
	* 属性名: remark<br>
	* 类型: java.lang.String<br>
	* 数据库字段:<br>
	* @author haipenge<br>
	*/
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@DBRef
	private Address address=null;
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
	

}
/**@generate-entity-source@**/

