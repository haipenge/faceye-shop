<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/product/productProperty/productProperty.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/productProperty/productProperty.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="product.productProperty.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key="product.productProperty.product"></fmt:message></td>
				    <td>${productProperty.product.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.productProperty.dynamicProperty"></fmt:message></td>
				    <td>${productProperty.dynamicProperty.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.productProperty.dynamicPropertyValue"></fmt:message></td>
				    <td>${productProperty.dynamicPropertyValue.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.productProperty.shop"></fmt:message></td>
				    <td>${productProperty.shop.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.productProperty.value"></fmt:message></td>
					<td>${productProperty.value}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
</div>