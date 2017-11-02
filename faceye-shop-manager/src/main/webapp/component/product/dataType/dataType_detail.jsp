<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/product/dataType/dataType.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/product/dataType/dataType.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="product.dataType.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="product.dataType.name"></fmt:message></td>
	<td>${dataType.name}</td>
</tr>
<tr>
	<td><fmt:message key="product.dataType.code"></fmt:message></td>
	<td>${dataType.code}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->


			</table>
		</div>
	</div>
</div>
