package com.faceye.component.setting.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.faceye.component.security.entity.User;
import com.faceye.component.weixin.entity.Account;
/**
 * 模块:配置->setting->Shop<br>
 * 说明:<br>
 * 实体:店铺->com.faceye.component.setting.entity.Shop Mongo 对像<br>
 * mongo数据集:setting_shop<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:35<br>
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
 
@Document(collection="setting_shop")
public class Shop implements Serializable {
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
    * 说明:名称<br>
    * 属性名: name<br>
    * 类型: java.lang.String<br>
    * 数据库字段:<br>
    * @author haipenge<br>
    */
	private  String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		
   /**
    * 说明:创建日期<br>
    * 属性名: createDate<br>
    * 类型: Date<br>
    * 数据库字段:createDate<br>
    * @author haipenge<br>
    */
    
	private  Date createDate=new Date();
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * Logo存储 文件名
	 */
	private String logoFilename="";
	
	public String getLogoFilename() {
		return logoFilename;
	}
	public void setLogoFilename(String logoFilename) {
		this.logoFilename = logoFilename;
		if(StringUtils.isNotEmpty(logoFilename)&&logoFilename.length()>8){
			String path=logoFilename.subSequence(0, 8)+"/"+logoFilename;
			this.setLogoPath(path);
		}
	}
	/**
	 * 店铺Logo地址
	 */
	private String logoPath="";
	
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	@DBRef
	private User user=null;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

	
   /**
    * 说明:备注<br>
    * 属性名: remark<br>
    * 类型: String<br>
    * 数据库字段:remark<br>
    * @author haipenge<br>
    */
    
	private  String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@DBRef
	private Account account=null;
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
}/**@generate-entity-source@**/
	
