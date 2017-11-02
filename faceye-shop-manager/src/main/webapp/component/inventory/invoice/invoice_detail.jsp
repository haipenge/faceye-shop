<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/inventory/invoice/invoice.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/inventory/invoice/invoice.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="inventory.invoice.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key="inventory.invoice.shop"></fmt:message></td>
					<td>${invoiceInfo.invoice.shop.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="inventory.invoice.type"></fmt:message></td>
					<td>${invoiceInfo.invoice.typeName}</td>
				</tr>
				<tr>
					<td><fmt:message key="inventory.invoice.remark"></fmt:message></td>
					<td>${invoiceInfo.invoice.remark}</td>
				</tr>
				<tr>
					<td><fmt:message key="inventory.invoice.code"></fmt:message></td>
					<td>${invoiceInfo.invoice.code}</td>
				</tr>
				<tr>
					<td><fmt:message key="inventory.invoice.order"></fmt:message></td>
					<td>${invoiceInfo.invoice.order.code}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
		<div class="content">
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
							<td><fmt:message key="product.product" /></td>
							<td><fmt:message key="inventory.item.quantity" /></td>
							<td><fmt:message key="product.product.price" /></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${invoiceInfo.invoiceItemInfos}" var="invoiceItemInfo">
							<td>${invoiceItemInfo.productSku.product.name}&nbsp;&nbsp;<c:forEach
									items="${invoiceItemInfo.skuProperties}" var="skuProperty" varStatus="status">
								                ${skuProperty.dynamicProperty.name}:${skuProperty.dynamicPropertyValue.value}<c:if
										test="${!status.last}">,</c:if>
								</c:forEach>
							</td>
							<td>${invoiceItemInfo.invoiceItem.quantity }</td>
							<td>${invoiceItemInfo.productSku.price}</td>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>