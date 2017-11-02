package com.faceye.component.customer.service;

import java.util.List;

import com.faceye.component.customer.entity.Address;
import com.faceye.component.customer.entity.Customer;
import com.faceye.feature.service.BaseService;
/**
 * 模块:客户->com.faceye.compoent.customer.service<br>
 * 说明:<br>
 * 实体:送货地址->com.faceye.component.customer.entity.entity.Address 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:37<br>
 */
public interface AddressService extends BaseService<Address,Long>{

	public List<Address> getAddressByCustomer(Customer customer);
}/**@generate-service-source@**/
