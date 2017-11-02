<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/product/image/image.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/product/image/image.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="product.image.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="product.image.path"></fmt:message></td>
	<td>${image.path}</td>
</tr>
<tr>
	<td><fmt:message key="product.image.url"></fmt:message></td>
	<td>${image.url}</td>
</tr>
<tr>
	<td><fmt:message key="product.image.isDefault"></fmt:message></td>
	<td>${image.isDefault}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->



			</table>
		</div>
	</div>
	<div class="content">
	  <a href="<c:url value="/product/image/edit/${image}.id"/>" class="btn btn-sm btn-primary"><fmt:message key="global.edit"/></a>
	  <a href="<c:url value="/product/image/remove/${image}.id"/>" class="btn btn-sm btn-danger"><fmt:message key="global.remove"/></a>
	  <a href="<c:url value="/product/image/home"/>" class="btn btn-sm btn-info"><fmt:message key="global.back"/></a>
	</div>
</div>
