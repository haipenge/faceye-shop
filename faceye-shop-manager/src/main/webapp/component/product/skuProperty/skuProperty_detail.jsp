<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/product/skuProperty/skuProperty.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/skuProperty/skuProperty.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="product.skuProperty.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key="product.skuProperty.productSku"></fmt:message></td>
				    <td>${skuProperty.productSku.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.skuProperty.shop"></fmt:message></td>
				    <td>${skuProperty.shop.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.skuProperty.dynamicPropertyValue"></fmt:message></td>
				    <td>${skuProperty.dynamicPropertyValue.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.skuProperty.dynamicProperty"></fmt:message></td>
				    <td>${skuProperty.productProperty.name}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
</div>