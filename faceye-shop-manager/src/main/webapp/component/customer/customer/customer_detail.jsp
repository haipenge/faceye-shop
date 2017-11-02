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
	<h3>
		<fmt:message key="customer.address" />
	</h3>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th><input type="checkbox" name="check-all"></th>
						<th><fmt:message key='customer.address.customer'></fmt:message></th>
						<!--@generate-entity-jsp-property-desc@-->
						<th><fmt:message key="global.edit"></fmt:message></th>
						<th><fmt:message key="global.remove"></fmt:message></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${addresses}" var="address">
						<tr id="${address.id}">
							<td><input type="checkbox" name="check-single" value="${address.id}"></td>
							<td>${address.customer.name}</td>

							<!--@generate-entity-jsp-property-value@-->
							<td><a href="<c:url value="/customer/address/edit/${address.id}"/>"> <fmt:message key="global.edit"></fmt:message>
							</a></td>
							<td><a href="<c:url value="/customer/address/remove/${address.id}"/>"> <fmt:message key="global.remove"></fmt:message>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>