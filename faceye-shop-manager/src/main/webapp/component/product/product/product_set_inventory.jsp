<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/product/product/product.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/product/product.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/inventory/inventory/inventory.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="product.product.set.inventory"></fmt:message>
			&nbsp;&nbsp; <small>${product.name}</small>
		</h3>
	</div>
	<div id="msg"></div>
	<div class="content">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th><fmt:message key="product.productSku" /></th>
					<th><fmt:message key="product.productSku.price" /></th>
					<th><fmt:message key="inventory.inventory" /></th>
					<th><fmt:message key="inventory.inventory.lockedCount" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${skuInfos}" var="skuInfo">
					<tr id="${skuInfo.productSku.id}">
						<td>${skuInfo.productSku.product.name}: &nbsp;&nbsp; <c:forEach items="${skuInfo.skuProperties}"
								var="skuProperty" varStatus="status">
						    ${skuProperty.dynamicProperty.name }:${skuProperty.dynamicPropertyValue.value}
						    <c:if test="${not status.last }">
						    ,
						    </c:if>
							</c:forEach></td>
						<td class="set-price">${skuInfo.productSku.priceYuan }</td>
						<td class="set-amount">${skuInfo.inventory.amount}</td>
						<td>${skuInfo.inventory.lockedCount }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>