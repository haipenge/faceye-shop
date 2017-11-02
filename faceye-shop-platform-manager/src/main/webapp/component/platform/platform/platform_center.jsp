<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/platform/platform/platform.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/platform/platform/platform.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="platform.platform.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/platform/platform/input"/>"> <fmt:message
				key="platform.platform.add"></fmt:message>
		</a>
	</h2>
</div>
<div class="cl-mcont">
	<!-- 
	<div class="header">
		<h2>
			<fmt:message key="security.role.manager"></fmt:message>
		</h2>
		<a class="btn btn-default" href="<c:url value="/security/role/input"/>"> <fmt:message key="security.role.add"></fmt:message>
		</a>
	</div>
	 -->
	<div class="block-flat">
		<c:import url="/component/core/template/msg/msg.jsp" />
		<div class="content">
			<form action="<c:url value="/platform/platform/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">

						<div class="col-md-1">
							<input type="text" name="EQ|name" value="${searchParams.name}"
								placeholder="<fmt:message key="platform.platform.name"></fmt:message>" class="form-control input-sm">
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
			<button class="btn btn-primary btn-sm multi-remove">
				<fmt:message key="global.remove"></fmt:message>
			</button>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='platform.platform.name'></fmt:message></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.view"></fmt:message></th>
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="platform">
							<tr id="platform.id">
								<td><input type="checkbox" name="check-single" value="${platform.id}"></td>
								<td>${platform.name}</td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/platform/platform/detail/${platform.id}"/>"> <fmt:message key="global.view"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/platform/platform/edit/${platform.id}"/>"> <fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/platform/platform/remove/${platform.id}"/>"> <fmt:message
											key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/platform/platform/home" params="<%=request.getParameterMap()%>" />
		</div>
		<div class="content">
			<div classs="table-responsive">
				<table class="table table-bordered">
					<tr>
						<th><fmt:message key="setting.shop.name"/></th>
						<th>Generate</th>
					</tr>
					<c:forEach items="${shops.content}" var="shop">
					  <tr>
					    <td>${shop.name}</td>
					    <td><button type="button" class="btn btn-primary generate-weixin-shop-url" id="${shop.id}">Generte</button></td>
					  </tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</div>