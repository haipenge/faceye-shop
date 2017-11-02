<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/order/cart/cart.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/order/cart/cart.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="order.cart.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="order.cart.uid"></fmt:message></td>
	<td>${cart.uid}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->

			</table>
		</div>
	</div>
</div>
