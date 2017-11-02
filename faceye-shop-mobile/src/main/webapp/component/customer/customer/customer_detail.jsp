<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/customer/customer/customer.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/customer/customer/customer.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="customer.customer.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key="customer.customer.name"></fmt:message></td>
					<td>${customer.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="customer.customer.mobile"></fmt:message></td>
					<td>${customer.mobile}</td>
				</tr>
				<tr>
	<td><fmt:message key="customer.customer.uid"></fmt:message></td>
	<td>${customer.uid}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->

			</table>
		</div>
	</div>
</div>