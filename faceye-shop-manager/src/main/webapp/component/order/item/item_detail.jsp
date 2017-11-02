<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/order/item/item.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/order/item/item.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="order.item.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key="order.item.shop"></fmt:message></td>
				    <td>${item.shop.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="order.item.product"></fmt:message></td>
				    <td>${item.product.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="order.item.productSku"></fmt:message></td>
				    <td>${item.productSku.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="order.item.price"></fmt:message></td>
					<td>${item.price}</td>
				</tr>
				<tr>
					<td><fmt:message key="order.item.quantity"></fmt:message></td>
					<td>${item.quantity}</td>
				</tr>
				<tr>
					<td><fmt:message key="order.item.order"></fmt:message></td>
				    <td>${item.order.name}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
</div>