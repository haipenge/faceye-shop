package com.faceye.component.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.order.entity.Item;
import com.faceye.component.order.entity.Order;
import com.faceye.component.order.model.ItemInfo;
import com.faceye.component.order.model.OrderInfo;
import com.faceye.component.order.repository.mongo.OrderRepository;
import com.faceye.component.order.service.ItemService;
import com.faceye.component.order.service.OrderService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;


/**
 * 模块:订单->com.faceye.compoent.order.service.impl<br>
 * 说明:<br>
 * 实体:订单->com.faceye.component.order.entity.entity.Order 服务实现类<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:36<br>
 */
@Service
public class OrderServiceImpl extends BaseMongoServiceImpl<Order, Long, OrderRepository> implements OrderService {
	@Autowired
	private ItemService itemService = null;

	@Autowired
	public OrderServiceImpl(OrderRepository dao) {
		super(dao);
	}

	/**
	 *数据分页查询
	 * @author haipenge <br>
	 * 联系:haipenge@gmail.com<br>
	 * 创建日期:2015-6-13 11:31:36<br>
	*/
	@Override
	public Page<Order> getPage(Map<String, Object> searchParams, int page, int size) {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Order> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Order> builder = new PathBuilder<Order>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = new Sort(Direction.DESC, "id");
		Page<Order> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Order>("id") {
			// })
			List<Order> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Order>(items);

		}
		return res;
	}

	@Override
	public OrderInfo getOrderInfo(Order order, List<OrderInfo> orderInfos) {
		OrderInfo orderInfo = null;
		if (CollectionUtils.isNotEmpty(orderInfos)) {
			for (OrderInfo oi : orderInfos) {
				if (oi.getOrder().getId().compareTo(order.getId()) == 0) {
					orderInfo = oi;
					break;
				}
			}
		}
		if (orderInfo == null) {
			orderInfo = new OrderInfo();
			if (orderInfos!=null) {
				orderInfos.add(orderInfo);
			}
		}
		Map params = new HashMap();
		params.put("EQ|order.$id", order.getId());
		List<Item> items = this.itemService.getPage(params, 1, 0).getContent();
		List<ItemInfo> itemInfos = this.itemService.getItemInfos(items);
		orderInfo.setOrder(order);
		orderInfo.setItemInfos(itemInfos);
		return orderInfo;
	}

	@Override
	public List<OrderInfo> getOrderInfos(List<Order> orders) {
		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>(0);
		if (CollectionUtils.isNotEmpty(orders)) {
			for (Order order : orders) {
				this.getOrderInfo(order, orderInfos);
			}
		}
		return orderInfos;
	}

}
/**@generate-service-source@**/
