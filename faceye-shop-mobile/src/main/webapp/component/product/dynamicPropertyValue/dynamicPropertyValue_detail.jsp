<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/product/dynamicPropertyValue/dynamicPropertyValue.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/dynamicPropertyValue/dynamicPropertyValue.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="product.dynamicPropertyValue.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key="product.dynamicPropertyValue.value"></fmt:message></td>
					<td>${dynamicPropertyValue.value}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.dynamicPropertyValue.dynamicProperty"></fmt:message></td>
				    <td>${dynamicPropertyValue.dynamicProperty.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.dynamicPropertyValue.shop"></fmt:message></td>
				    <td>${dynamicPropertyValue.shop.name}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
</div>