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
				    <td>${invoice.shop.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="inventory.invoice.type"></fmt:message></td>
					<td>${invoice.type}</td>
				</tr>
				<tr>
					<td><fmt:message key="inventory.invoice.remark"></fmt:message></td>
					<td>${invoice.remark}</td>
				</tr>
				<tr>
					<td><fmt:message key="inventory.invoice.code"></fmt:message></td>
					<td>${invoice.code}</td>
				</tr>
				<tr>
					<td><fmt:message key="inventory.invoice.order"></fmt:message></td>
				    <td>${invoice.order.name}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
</div>