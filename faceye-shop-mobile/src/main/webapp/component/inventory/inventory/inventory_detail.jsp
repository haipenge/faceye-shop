<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/inventory/inventory/inventory.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/inventory/inventory/inventory.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="inventory.inventory.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key="inventory.inventory.shop"></fmt:message></td>
				    <td>${inventory.shop.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="inventory.inventory.productSku"></fmt:message></td>
				    <td>${inventory.productSku.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="inventory.inventory.amount"></fmt:message></td>
					<td>${inventory.amount}</td>
				</tr>
				<tr>
					<td><fmt:message key="inventory.inventory.product"></fmt:message></td>
				    <td>${inventory.product.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="inventory.inventory.lockedCount"></fmt:message></td>
					<td>${inventory.lockedCount}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
</div>