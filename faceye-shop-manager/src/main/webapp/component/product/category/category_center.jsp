<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/product/category/category.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/category/category.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="product.category.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/product/category/input"/>"> <fmt:message
				key="product.category.add"></fmt:message>
		</a>
	</h2>
</div>
<div class="cl-mcont">
	<div class="block-flat">
	<c:import url="/component/core/template/msg/msg.jsp"/>
		<div class="content">
			<form action="<c:url value="/product/category/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<div class="col-md-2">
							<input type="text" name="LIKE|name" value="${searchParams.name}"
								placeholder="<fmt:message key="product.category.name"></fmt:message>" class="form-control input-sm">
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
							<th><fmt:message key='product.category.name'></fmt:message></th>
							<th><fmt:message key="setting.shop"/></th>
							<th><fmt:message key='product.category.remark'></fmt:message></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="category">
							<tr id="${category.id}">
								<td><input type="checkbox" name="check-single" value="${category.id}"></td>
								<td><a href="<c:url value="/product/category/detail/${category.id}.html"/>">${category.name}</a></td>
								<td>${category.shop.name }</td>
								<td>${category.remark}</td>

								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/product/category/edit/${category.id}"/>"> <fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/product/category/remove/${category.id}"/>"> <fmt:message
											key="global.remove"></fmt:message>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/product/category/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>