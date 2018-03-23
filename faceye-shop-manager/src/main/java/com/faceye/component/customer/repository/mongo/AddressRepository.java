package com.faceye.component.customer.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.customer.entity.Address;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:客户->com.faceye.compoent.customer.repository.mongo<br>
 * 说明:<br>
 * 实体:送货地址->com.faceye.component.customer.entity.entity.Address 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2015-6-13 11:31:37<br>
 */
public interface AddressRepository extends BaseMongoRepository<Address,Long> {
	
	
}/**@generate-repository-source@**/
