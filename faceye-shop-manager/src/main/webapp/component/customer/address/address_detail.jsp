<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/customer/address/address.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/customer/address/address.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="customer.address.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key="customer.address.customer"></fmt:message></td>
				    <td>${address.customer.name}</td>
				</tr>
				<tr>
	<td><fmt:message key="customer.address.mobile"></fmt:message></td>
	<td>${address.mobile}</td>
</tr>
<tr>
	<td><fmt:message key="customer.address.receiveUserName"></fmt:message></td>
	<td>${address.receiveUserName}</td>
</tr>
<tr>
	<td><fmt:message key="customer.address.province"></fmt:message></td>
	<td>${address.province}</td>
</tr>
<tr>
	<td><fmt:message key="customer.address.city"></fmt:message></td>
	<td>${address.city}</td>
</tr>
<tr>
	<td><fmt:message key="customer.address.area"></fmt:message></td>
	<td>${address.area}</td>
</tr>
<tr>
	<td><fmt:message key="customer.address.street"></fmt:message></td>
	<td>${address.street}</td>
</tr>
<tr>
	<td><fmt:message key="customer.address.detail"></fmt:message></td>
	<td>${address.detail}</td>
</tr>
<tr>
	<td><fmt:message key="customer.address.isDefault"></fmt:message></td>
	<td>${address.isDefault}</td>
</tr>
<tr>
	<td><fmt:message key="customer.address.isRemoved"></fmt:message></td>
	<td>${address.isRemoved}</td>
</tr>
<!--@generate-entity-jsp-property-detail@-->









			</table>
		</div>
	</div>
</div>