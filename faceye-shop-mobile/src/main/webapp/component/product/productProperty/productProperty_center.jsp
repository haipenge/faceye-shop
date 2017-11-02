<%@ include file="/component/core/taglib/taglib.jsp"%>
	<link rel="stylesheet" type="text/css"
		href="<c:url value="/css/component/product/productProperty/productProperty.css"/>" />
	<script type="text/javascript"
		src="<c:url value="/js/component/product/productProperty/productProperty.js"/>"></script>
	<div class="page-head">
		<h2>
			<fmt:message key="product.productProperty.manager"></fmt:message>
			<a class="btn btn-primary" href="<c:url value="/product/productProperty/input"/>">
				<fmt:message key="product.productProperty.add"></fmt:message>
			</a>
		</h2>
	</div>
	<div class="cl-mcont">
		<div class="block-flat">
			<div class="content">
				<form action="<c:url value="/product/productProperty/home"/>" method="post"
					role="form" class="form-horizontal">
					<fieldset>
						<div class="form-group">
							<div class="col-md-2">
							     <select name="EQ|product.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="product.product"/></option>
							       <c:forEach items="${products}" var ="product">
							         <option value="${product.id}"<c:if test="${product.id eq entity.product.id}"> selected</c:if>>${product.name}</option>
							       </c:forEach>
							     </select>
							</div>
							<div class="col-md-2">
							     <select name="EQ|dynamicProperty.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="product.dynamicProperty"/></option>
							       <c:forEach items="${dynamicPropertys}" var ="dynamicProperty">
							         <option value="${dynamicProperty.id}"<c:if test="${dynamicProperty.id eq entity.dynamicProperty.id}"> selected</c:if>>${dynamicProperty.name}</option>
							       </c:forEach>
							     </select>
							</div>
							<div class="col-md-2">
							     <select name="EQ|dynamicPropertyValue.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="product.dynamicPropertyValue"/></option>
							       <c:forEach items="${dynamicPropertyValues}" var ="dynamicPropertyValue">
							         <option value="${dynamicPropertyValue.id}"<c:if test="${dynamicPropertyValue.id eq entity.dynamicPropertyValue.id}"> selected</c:if>>${dynamicPropertyValue.name}</option>
							       </c:forEach>
							     </select>
							</div>
							<div class="col-md-2">
							     <select name="EQ|shop.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="setting.shop"/></option>
							       <c:forEach items="${shops}" var ="shop">
							         <option value="${shop.id}"<c:if test="${shop.id eq entity.shop.id}"> selected</c:if>>${shop.name}</option>
							       </c:forEach>
							     </select>
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|value" value="${searchParams.value}"
									placeholder="<fmt:message key="product.productProperty.value"></fmt:message>"
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
								  <th><fmt:message key='product.productProperty.product'></fmt:message></th>
								  <th><fmt:message key='product.productProperty.dynamicProperty'></fmt:message></th>
								  <th><fmt:message key='product.productProperty.dynamicPropertyValue'></fmt:message></th>
								  <th><fmt:message key='product.productProperty.shop'></fmt:message></th>
								  <th><fmt:message key='product.productProperty.value'></fmt:message></th>
								<!--@generate-entity-jsp-property-desc@-->
								<th><fmt:message key="global.view"/></th>
								<th><fmt:message key="global.edit"></fmt:message></th>
								<th><fmt:message key="global.remove"></fmt:message></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.content}" var="productProperty">
								<tr id="${productProperty.id}">
									<td><input type="checkbox" name="check-single" value="${productProperty.id}"></td>
									  <td>${productProperty.product.name}</td>
									  <td>${productProperty.dynamicProperty.name}</td>
									  <td>${productProperty.dynamicPropertyValue.name}</td>
									  <td>${productProperty.shop.name}</td>
									  <td>${productProperty.value}</td>
									
									<!--@generate-entity-jsp-property-value@-->
									<td><a href="<c:url value="/product/productProperty/detail/${productProperty.id}.html"/>"><fmt:message key="global.view"/></a></td>
									<td><a
										href="<c:url value="/product/productProperty/edit/${productProperty.id}"/>"> <fmt:message
												key="global.edit"></fmt:message>
									</a></td>
									<td><a
										href="<c:url value="/product/productProperty/remove/${productProperty.id}"/>"> <fmt:message
												key="global.remove"></fmt:message>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<f:page page="${page}" url="/product/productProperty/home"  params="<%=request.getParameterMap()%>" />
			</div>
		</div>
	</div>