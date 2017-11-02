package com.faceye.component.order.service;

import java.util.List;

import com.faceye.component.order.entity.Item;
import com.faceye.component.order.model.ItemInfo;
import com.faceye.feature.service.BaseService;
/**
 * 模块:订单->com.faceye.compoent.order.service<br>
 * 说明:<br>
 * 实体:订单条目->com.faceye.component.order.entity.entity.Item 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:36<br>
 */
public interface ItemService extends BaseService<Item,Long>{

	/**
	 * 构建订单条目 明细
	 * @todo
	 * @param item
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月18日
	 */
	public ItemInfo getItemInfo(Item item);
	/**
	 * 取得订单条目 明细 列表
	 * @todo
	 * @param items
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月18日
	 */
	public List<ItemInfo> getItemInfos(List<Item> items);
}/**@generate-service-source@**/
