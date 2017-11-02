<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/product/dynamicProperty/dynamicProperty.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/dynamicProperty/dynamicProperty.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="product.dynamicProperty.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/product/dynamicProperty/input"/>"> <fmt:message
				key="product.dynamicProperty.add"></fmt:message>
		</a>
	</h2>
</div>
<div class="cl-mcont">
	<div class="block-flat">
		<c:import url="/component/core/template/msg/msg.jsp" />
		<div class="content">
			<form action="<c:url value="/product/dynamicProperty/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<div class="col-md-2">
							<select name="EQ|categoryId" class="form-control">
								<option value="0"><fmt:message key="global.select" /><fmt:message key="product.category" /></option>
								<c:forEach items="${categorys}" var="category">
									<option value="${category.id}" <c:if test="${category.id eq entity.categoryId}"> selected</c:if>>${category.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-2">
							<select name="EQ|dataTypeId" class="form-control">
								<option value="0"><fmt:message key="global.select" /><fmt:message key="product.dataType" /></option>
								<c:forEach items="${dataTypes}" var="dataType">
									<option value="${dataType.id}" <c:if test="${dataType.id eq entity.dataTypeId}"> selected</c:if>>${dataType.name}</option>
								</c:forEach>
							</select>
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
							<th><fmt:message key='product.dynamicProperty.name'></fmt:message></th>
							<th><fmt:message key='product.dynamicProperty.dataType'></fmt:message></th>
							<th><fmt:message key='product.dynamicProperty.unit'></fmt:message></th>
							<th><fmt:message key='product.dynamicProperty.shop'></fmt:message></th>
							<th><fmt:message key="product.category" /></th>
							<th><fmt:message key='product.dynamicProperty.isSku'></fmt:message></th>
							<th><fmt:message key='product.dynamicProperty.isRequired'></fmt:message></th>
							<th><fmt:message key='product.dynamicProperty.isShare'></fmt:message></th>
							<th><fmt:message key="product.dynamicPropertyValue" /></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="dynamicProperty">
							<tr id="${dynamicProperty.id}">
								<td><input type="checkbox" name="check-single" value="${dynamicProperty.id}"></td>
								<td>${dynamicProperty.name}</td>
								<td>${dynamicProperty.dataType.name}</td>
								<td>${dynamicProperty.unit}</td>
								<td>${dynamicProperty.shop.name}</td>
								<td>${dynamicProperty.category.name}</td>
								<td><f:boolean value="${dynamicProperty.isSku}" /></td>
								<td><f:boolean value="${dynamicProperty.isRequired}" /></td>

								<td><f:boolean value="${dynamicProperty.isShare}" /></td>
								<td><c:if test="${dynamicProperty.dataType.code eq 'enum' }">
										<a href="<c:url value="/product/dynamicPropertyValue/home?EQ|dynamicProperty.$id=${dynamicProperty.id}"/>"><fmt:message
												key="product.dynamicPropertyValue" /></a>
									</c:if></td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/product/dynamicProperty/edit/${dynamicProperty.id}"/>"> <fmt:message
											key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/product/dynamicProperty/remove/${dynamicProperty.id}"/>"> <fmt:message
											key="global.remove"></fmt:message>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/product/dynamicProperty/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>