package com.faceye.component.inventory.service.impl;

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

import com.faceye.component.inventory.entity.InvoiceItem;
import com.faceye.component.inventory.model.InvoiceItemInfo;
import com.faceye.component.inventory.repository.mongo.InvoiceItemRepository;
import com.faceye.component.inventory.service.InvoiceItemService;
import com.faceye.component.product.entity.SkuProperty;
import com.faceye.component.product.service.SkuPropertyService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;

/**
 * 模块:库存->com.faceye.compoent.inventory.service.impl<br>
 * 说明:<br>
 * 实体:库存单据明细->com.faceye.component.inventory.entity.entity.Item 服务实现类<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:37<br>
 */
@Service
public class InvoiceItemServiceImpl extends BaseMongoServiceImpl<InvoiceItem, Long, InvoiceItemRepository> implements InvoiceItemService {
	@Autowired
	private SkuPropertyService skuPropertyService;

	@Autowired
	public InvoiceItemServiceImpl(InvoiceItemRepository dao) {
		super(dao);
	}

	/**
	 *数据分页查询
	 * @author haipenge <br>
	 * 联系:haipenge@gmail.com<br>
	 * 创建日期:2015-6-13 11:31:37<br>
	*/
	@Override
	public Page<InvoiceItem> getPage(Map<String, Object> searchParams, int page, int size) {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Item> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Item> builder = new PathBuilder<Item>(entityPath.getType(), entityPath.getMetadata());
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
		Page<InvoiceItem> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Item>("id") {
			// })
			List<InvoiceItem> invoiceItems = (List) this.dao.findAll(predicate);
			res = new PageImpl<InvoiceItem>(invoiceItems);

		}
		return res;
	}

	@Override
	public InvoiceItemInfo getInvoiceItemInfo(InvoiceItem invoiceItem) {
		InvoiceItemInfo invoiceItemInfo = null;
		Map searchParams = new HashMap();
		if (invoiceItem != null && invoiceItem.getProductSku() != null) {
			invoiceItemInfo = new InvoiceItemInfo();
			searchParams.put("EQ|productSku.$id", invoiceItem.getProductSku().getId());
			List<SkuProperty> skuProperties = this.skuPropertyService.getPage(searchParams, 1, 0).getContent();
			invoiceItemInfo.setSkuProperties(skuProperties);
			invoiceItemInfo.setInvoiceItem(invoiceItem);
			invoiceItemInfo.setProductSku(invoiceItem.getProductSku());
		}
		return invoiceItemInfo;
	}

	@Override
	public List<InvoiceItemInfo> getInvoiceItemInfos(List<InvoiceItem> invoiceItems) {
		List<InvoiceItemInfo> invoiceItemInfos = new ArrayList<InvoiceItemInfo>(0);
		if (CollectionUtils.isNotEmpty(invoiceItems)) {
			for (InvoiceItem invoiceItem : invoiceItems) {
				InvoiceItemInfo invoiceItemInfo = this.getInvoiceItemInfo(invoiceItem);
				if (invoiceItemInfo != null) {
					invoiceItemInfos.add(invoiceItemInfo);
				}
			}
		}
		return invoiceItemInfos;
	}

}
/**@generate-service-source@**/
