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

import com.faceye.component.inventory.entity.Invoice;
import com.faceye.component.inventory.entity.InvoiceItem;
import com.faceye.component.inventory.model.InvoiceInfo;
import com.faceye.component.inventory.model.InvoiceItemInfo;
import com.faceye.component.inventory.repository.mongo.InvoiceRepository;
import com.faceye.component.inventory.service.InvoiceItemService;
import com.faceye.component.inventory.service.InvoiceService;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;

/**
 * 模块:库存->com.faceye.compoent.inventory.service.impl<br>
 * 说明:<br>
 * 实体:库存单据->com.faceye.component.inventory.entity.entity.Invoice 服务实现类<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2015-6-13 11:31:37<br>
 */
@Service
public class InvoiceServiceImpl extends BaseMongoServiceImpl<Invoice, Long, InvoiceRepository> implements InvoiceService {

	@Autowired
	private InvoiceItemService invoiceItemService;

	@Autowired
	public InvoiceServiceImpl(InvoiceRepository dao) {
		super(dao);
	}

	/**
	 *数据分页查询
	 * @author haipenge <br>
	 * 联系:haipenge@gmail.com<br>
	 * 创建日期:2015-6-13 11:31:37<br>
	*/
	@Override
	public Page<Invoice> getPage(Map<String, Object> searchParams, int page, int size)  {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Invoice> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Invoice> builder = new PathBuilder<Invoice>(entityPath.getType(), entityPath.getMetadata());
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
		Page<Invoice> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Invoice>("id") {
			// })
			List<Invoice> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Invoice>(items);

		}
		return res;
	}

	@Override
	public List<InvoiceInfo> getInvoiceInfos(List<Invoice> invoices) {
		List<InvoiceInfo> invoiceInfos = new ArrayList<InvoiceInfo>(0);
		if (CollectionUtils.isNotEmpty(invoices)) {
			for (Invoice invoice : invoices) {
				InvoiceInfo invoiceInfo = this.getInvoiceInfo(invoice);
				invoiceInfos.add(invoiceInfo);
			}
		}
		return invoiceInfos;
	}

	@Override
	public InvoiceInfo getInvoiceInfo(Invoice invoice) {
		InvoiceInfo invoiceInfo = new InvoiceInfo();
		invoiceInfo.setInvoice(invoice);
		Map searchParams = new HashMap();
		searchParams.put("EQ|invoice.$id", invoice.getId());
		List<InvoiceItem> invoiceItems = this.invoiceItemService.getPage(searchParams, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(invoiceItems)) {
			List<InvoiceItemInfo> invoiceItemInfos = this.invoiceItemService.getInvoiceItemInfos(invoiceItems);
			invoiceInfo.setInvoiceItemInfos(invoiceItemInfos);
		}
		return invoiceInfo;
	}

}
/**@generate-service-source@**/
