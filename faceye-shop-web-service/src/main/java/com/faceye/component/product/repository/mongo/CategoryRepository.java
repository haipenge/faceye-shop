package com.faceye.component.product.repository.mongo;

import java.util.List;

import com.faceye.component.product.entity.Category;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:产品->com.faceye.compoent.product.repository.mongo<br>
 * 说明:<br>
 * 实体:产品分类->com.faceye.component.product.entity.entity.Category 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2015-6-16 18:57:41<br>
 */
public interface CategoryRepository extends BaseMongoRepository<Category,Long> {
	
	public List<Category> getCategoriesByShopId(Long shopId);
}/**@generate-repository-source@**/
