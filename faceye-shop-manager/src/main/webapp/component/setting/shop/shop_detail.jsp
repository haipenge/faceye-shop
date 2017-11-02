<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/setting/shop/shop.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/setting/shop/shop.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="setting.shop.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key="setting.shop.name"></fmt:message></td>
					<td>${shop.name}</td>
				</tr>
				<tr>
	<td><fmt:message key="setting.shop.remark"></fmt:message></td>
	<td>${shop.remark}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->

			</table>
		</div>
	</div>
</div>