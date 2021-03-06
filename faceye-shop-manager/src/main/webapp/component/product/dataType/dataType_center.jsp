<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/product/dataType/dataType.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/product/dataType/dataType.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="product.dataType.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/product/dataType/input"/>"> <fmt:message
				key="product.dataType.add"></fmt:message>
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
		<div class="content">
			<form action="<c:url value="/product/dataType/home"/>" method="post" role="form"
				class="form-horizontal">
				<fieldset>
					<div class="form-group">
						
<div class="col-md-1">
	<input type="text" name="EQ|name" value="${searchParams.name}"
		placeholder="<fmt:message key="product.dataType.name"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|code" value="${searchParams.code}"
		placeholder="<fmt:message key="product.dataType.code"></fmt:message>"
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
	       <button class="btn btn-primary btn-sm multi-remove"><fmt:message key="global.remove"></fmt:message></button>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
						   <th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='product.dataType.name'></fmt:message></th>   
 <th><fmt:message key='product.dataType.code'></fmt:message></th>   
 <!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="dataType">
							<tr>
							   <td><input type="checkbox" name="check-single" value="${dataType.id}"></td>
								<td>${dataType.name}</td>   
 <td>${dataType.code}</td>   
 <!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/product/dataType/edit/${dataType.id}"/>">
										<fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/product/dataType/remove/${dataType.id}"/>">
										<fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/product/dataType/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
