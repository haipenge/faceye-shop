package com.faceye.component.inventory.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.inventory.entity.Inventory;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:库存->com.faceye.compoent.inventory.repository.mongo<br>
 * 说明:<br>
 * 实体:库存明细->com.faceye.component.inventory.entity.entity.Inventory 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2015-6-13 11:31:37<br>
 */
public interface InventoryRepository extends BaseMongoRepository<Inventory,Long> {
	
	
}/**@generate-repository-source@**/
