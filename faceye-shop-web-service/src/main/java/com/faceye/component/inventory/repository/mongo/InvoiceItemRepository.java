package com.faceye.component.inventory.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.inventory.entity.InvoiceItem;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:库存->com.faceye.compoent.inventory.repository.mongo<br>
 * 说明:<br>
 * 实体:库存单据明细->com.faceye.component.inventory.entity.entity.InvoiceItem 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2015-6-16 18:57:43<br>
 */
public interface InvoiceItemRepository extends BaseMongoRepository<InvoiceItem,Long> {
	
	
}/**@generate-repository-source@**/
