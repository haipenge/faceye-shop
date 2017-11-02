package com.faceye.component.customer.repository.mongo;

import java.util.List;

import com.faceye.component.customer.entity.Address;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:客户->com.faceye.compoent.customer.repository.mongo<br>
 * 说明:<br>
 * 实体:送货地址->com.faceye.component.customer.entity.entity.Address 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2015-6-16 18:57:43<br>
 */
public interface AddressRepository extends BaseMongoRepository<Address,Long> {
	
	/**
	 * 取得用户的配货地址
	 * @todo
	 * @param customerId
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月22日
	 */
	public List<Address> getAddressesByCustomerId(Long customerId);
	
}/**@generate-repository-source@**/
