<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/order/cartItem/cartItem.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/order/cartItem/cartItem.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="order.cartItem.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="order.cartItem.quantity"></fmt:message></td>
	<td>${cartItem.quantity}</td>
</tr>
<tr>
	<td><fmt:message key="order.cartItem.productSku"></fmt:message></td>
	<td>${cartItem.productSku}</td>
</tr>
<tr>
	<td><fmt:message key="order.cartItem.status"></fmt:message></td>
	<td>${cartItem.status}</td>
</tr>
<tr>
	<td><fmt:message key="order.cartItem.statusName"></fmt:message></td>
	<td>${cartItem.statusName}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->




			</table>
		</div>
	</div>
</div>
