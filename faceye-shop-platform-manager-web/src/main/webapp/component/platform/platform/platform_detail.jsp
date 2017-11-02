<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/platform/platform/platform.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/platform/platform/platform.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="platform.platform.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="platform.platform.name"></fmt:message></td>
	<td>${platform.name}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->

			</table>
		</div>
	</div>
	<div class="content">
	  <a href="<c:url value="/platform/platform/edit/${platform}.id"/>" class="btn btn-sm btn-primary"><fmt:message key="global.edit"/></a>
	  <a href="<c:url value="/platform/platform/remove/${platform}.id"/>" class="btn btn-sm btn-danger"><fmt:message key="global.remove"/></a>
	  <a href="<c:url value="/platform/platform/home"/>" class="btn btn-sm btn-info"><fmt:message key="global.back"/></a>
	</div>
</div>