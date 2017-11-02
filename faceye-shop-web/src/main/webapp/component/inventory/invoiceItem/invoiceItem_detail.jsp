<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/inventory/invoiceItem/invoiceItem.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/inventory/invoiceItem/invoiceItem.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="inventory.invoiceItem.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key="inventory.invoiceItem.shop"></fmt:message></td>
				    <td>${invoiceItem.shop.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="inventory.invoiceItem.product"></fmt:message></td>
				    <td>${invoiceItem.product.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="inventory.invoiceItem.productSku"></fmt:message></td>
				    <td>${invoiceItem.productSku.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="inventory.invoiceItem.quantity"></fmt:message></td>
					<td>${invoiceItem.quantity}</td>
				</tr>
				<tr>
					<td><fmt:message key="inventory.invoiceItem.invoice"></fmt:message></td>
				    <td>${invoiceItem.invoice.name}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
</div>