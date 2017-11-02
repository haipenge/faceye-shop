<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/product/productSku/productSku.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/productSku/productSku.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="product.productSku.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key="product.productSku.product"></fmt:message></td>
				    <td>${productSku.product.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.productSku.shop"></fmt:message></td>
				    <td>${productSku.shop.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.productSku.price"></fmt:message></td>
					<td>${productSku.price}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
</div>