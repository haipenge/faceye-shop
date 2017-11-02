package com.faceye.component.inventory.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.faceye.component.order.entity.Order;
import com.faceye.component.setting.entity.Shop;

/**
 * 模块:库存->inventory->Invoice<br>
 * 说明:<br>
 * 实体:库存单据->com.faceye.component.inventory.entity.Invoice Mongo 对像<br>
 * mongo数据集:inventory_invoice<br>
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

@Document(collection = "inventory_invoice")
public class Invoice implements Serializable {
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
	* 说明:类型(出入库）<br>
	* 属性名: type,0:出库，1：入库<br>
	* 类型: java.lang.Integer<br>
	* 数据库字段:<br>
	* @author haipenge<br>
	*/
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Transient
	private String typeName = "";

	public String getTypeName() {
		if (this.getType() != null) {
			if (this.getType() == 0) {
				typeName = "出库";
			} else if (this.getType() == 1) {
				typeName = "入库";
			}
		} else {
			typeName = "未知";
		}
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	/**
	* 说明:编码<br>
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
	* 说明:订单<br>
	* 属性名: order<br>
	* 类型: com.faceye.component.order.entity.Order<br>
	* 数据库字段:order<br>
	* @author haipenge<br>
	*/
	@DBRef
	private Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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

