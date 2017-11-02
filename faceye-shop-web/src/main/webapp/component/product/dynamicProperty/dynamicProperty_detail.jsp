<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/product/dynamicProperty/dynamicProperty.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/dynamicProperty/dynamicProperty.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="product.dynamicProperty.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key="product.dynamicProperty.name"></fmt:message></td>
				    <td>${dynamicProperty.category.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.dynamicProperty.dataType"></fmt:message></td>
					<td>${dynamicProperty.dataType}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.dynamicProperty.unit"></fmt:message></td>
					<td>${dynamicProperty.unit}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.dynamicProperty.isSku"></fmt:message></td>
					<td>${dynamicProperty.isSku}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.dynamicProperty.isRequired"></fmt:message></td>
					<td>${dynamicProperty.isRequired}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.dynamicProperty.shop"></fmt:message></td>
				    <td>${dynamicProperty.shop.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.dynamicProperty.isMulti"></fmt:message></td>
					<td>${dynamicProperty.isMulti}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
</div>