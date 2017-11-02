<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/product/category/category.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/category/category.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="product.category.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key="product.category.name"></fmt:message></td>
					<td>${category.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.category.remark"></fmt:message></td>
					<td>${category.remark}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
</div>