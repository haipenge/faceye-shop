<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/customer/customer/customer.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/customer/customer/customer.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="customer.customer.manager"></fmt:message>
		<a class="btn btn-primary"
			href="<c:url value="/customer/customer/input"/>"> <fmt:message
				key="customer.customer.add"></fmt:message>
		</a>
	</h2>
</div>
<div class="cl-mcont">
	<div class="block-flat">
		<div class="content">
			<form action="<c:url value="/customer/customer/home"/>" method="post"
				role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<div class="col-md-2">
							<input type="text" name="EQ|name" value="${searchParams.name}"
								placeholder="<fmt:message key="customer.customer.name"></fmt:message>"
								class="form-control input-sm">
						</div>
						<div class="col-md-2">
							<input type="text" name="EQ|mobile"
								value="${searchParams.mobile}"
								placeholder="<fmt:message key="customer.customer.mobile"></fmt:message>"
								class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|uid" value="${searchParams.uid}"
								placeholder="<fmt:message key="customer.customer.uid"></fmt:message>"
								class="form-control input-sm">
						</div>
						<!--@generate-entity-jsp-query-detail@-->

						<div class="col-md-1">
							<button type="submit" class="btn btn-sm btn-primary">
								<fmt:message key="global.search"></fmt:message>
							</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="content">
			<div id="msg"></div>
			<button class="btn btn-primary btn-sm multi-remove">
				<fmt:message key="global.remove"></fmt:message>
			</button>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='customer.customer.name'></fmt:message></th>
							<th><fmt:message key='customer.customer.mobile'></fmt:message></th>
							<th><fmt:message key='customer.customer.uid'></fmt:message></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.view" /></th>
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="customer">
							<tr id="${customer.id}">
								<td><input type="checkbox" name="check-single"
									value="${customer.id}"></td>
								<td>${customer.name}</td>
								<td>${customer.mobile}</td>

								<td>${customer.uid}</td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a
									href="<c:url value="/customer/customer/detail/${customer.id}.html"/>"><fmt:message
											key="global.view" /></a></td>
								<td><a
									href="<c:url value="/customer/customer/edit/${customer.id}"/>">
										<fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a
									href="<c:url value="/customer/customer/remove/${customer.id}"/>">
										<fmt:message key="global.remove"></fmt:message>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/customer/customer/home"
				params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>