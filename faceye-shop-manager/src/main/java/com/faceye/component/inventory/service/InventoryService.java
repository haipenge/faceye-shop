package com.faceye.component.inventory.service;

import java.util.List;

import com.faceye.component.inventory.entity.Inventory;
import com.faceye.component.product.model.SkuInfo;
import com.faceye.feature.service.BaseService;
/**
 * 模块:库存->com.faceye.compoent.inventory.service<br>
 * 说明:<br>
 * 实体:库存明细->com.faceye.component.inventory.entity.entity.Inventory 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:37<br>
 */
public interface InventoryService extends BaseService<Inventory,Long>{

	public Inventory getInventoryByProductSkuId(Long productSkuId);
	
	/**
	 * 将库存转换为sku存储结构
	 * @todo
	 * @param inventories
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月18日
	 */
	public List<SkuInfo> getSkuInfos(List<Inventory> inventories);

}/**@generate-service-source@**/
