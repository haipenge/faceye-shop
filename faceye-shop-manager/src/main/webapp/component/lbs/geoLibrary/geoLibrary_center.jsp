<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/lbs/geoLibrary/geoLibrary.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/lbs/geoLibrary/geoLibrary.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="lbs.geoLibrary.manager"></fmt:message>
		<a class="btn btn-primary"
			href="<c:url value="/lbs/geoLibrary/input"/>"> <fmt:message
				key="lbs.geoLibrary.add"></fmt:message>
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
			<form action="<c:url value="/lbs/geoLibrary/home"/>" method="post"
				role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">

						<div class="col-md-1">
							<input type="text" name="like|name" value="${searchParams.name}"
								placeholder="<fmt:message key="lbs.geoLibrary.name"></fmt:message>"
								class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|orderIndex"
								value="${searchParams.orderIndex}"
								placeholder="<fmt:message key="lbs.geoLibrary.orderIndex"></fmt:message>"
								class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|code" value="${searchParams.code}"
								placeholder="<fmt:message key="lbs.geoLibrary.code"></fmt:message>"
								class="form-control input-sm">
						</div>

						<div class="col-md-1">
							<input type="text" name="EQ|level" value="${searchParams.level}"
								placeholder="<fmt:message key="lbs.geoLibrary.level"></fmt:message>"
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
		<div class="content geoSelect">
			<div class="row">
				<div class="col-md-2">
					<select name="provinceId" class="form-control">
						<option value="">Province</option>
					</select>
				</div>
				<div class="col-md-2">
					<select name="cityId" class="form-control">
						<option value="">City</option>
					</select>
				</div>
				<div class="col-md-2">
					<select name="areaId" class="form-control">
						<option value="">Area</option>

					</select>
				</div>
			</div>


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
							<th><fmt:message key='lbs.geoLibrary.name'></fmt:message></th>
							<th><fmt:message key='lbs.geoLibrary.orderIndex'></fmt:message></th>
							<th><fmt:message key='lbs.geoLibrary.code'></fmt:message></th>
							<th><fmt:message key='lbs.geoLibrary.level'></fmt:message></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.view" /></th>
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="geoLibrary">
							<tr>
								<td><input type="checkbox" name="check-single"
									value="${geoLibrary.id}"></td>
								<td>${geoLibrary.name}</td>
								<td>${geoLibrary.orderIndex}</td>
								<td>${geoLibrary.code}</td>
								<td>${geoLibrary.level}</td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a
									href="<c:url value="/lbs/geoLibrary/detail/${geoLibrary.id}"/>">
										<fmt:message key="global.view"></fmt:message>
								</a></td>
								<td><a
									href="<c:url value="/lbs/geoLibrary/edit/${geoLibrary.id}"/>">
										<fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a
									href="<c:url value="/lbs/geoLibrary/remove/${geoLibrary.id}"/>">
										<fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/lbs/geoLibrary/home"
				params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
