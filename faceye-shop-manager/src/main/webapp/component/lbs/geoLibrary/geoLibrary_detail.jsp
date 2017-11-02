<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/lbs/geoLibrary/geoLibrary.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/lbs/geoLibrary/geoLibrary.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="lbs.geoLibrary.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
			 <tr>
	<td><fmt:message key="lbs.geoLibrary.name"></fmt:message></td>
	<td>${geoLibrary.name}</td>
</tr>
<tr>
	<td><fmt:message key="lbs.geoLibrary.orderIndex"></fmt:message></td>
	<td>${geoLibrary.orderIndex}</td>
</tr>
<tr>
	<td><fmt:message key="lbs.geoLibrary.code"></fmt:message></td>
	<td>${geoLibrary.code}</td>
</tr>
<tr>
	<td><fmt:message key="lbs.geoLibrary.level"></fmt:message></td>
	<td>${geoLibrary.level}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->




			</table>
		</div>
	</div>
	<div class="content">
	  <a href="<c:url value="/lbs/geoLibrary/edit/${geoLibrary}.id"/>" class="btn btn-sm btn-primary"><fmt:message key="global.edit"/></a>
	  <a href="<c:url value="/lbs/geoLibrary/remove/${geoLibrary}.id"/>" class="btn btn-sm btn-danger"><fmt:message key="global.remove"/></a>
	  <a href="<c:url value="/lbs/geoLibrary/home"/>" class="btn btn-sm btn-info"><fmt:message key="global.back"/></a>
	</div>
</div>
