<%@ include file="/component/core/taglib/taglib.jsp"%>
	<link rel="stylesheet" type="text/css"
		href="<c:url value="/css/component/product/dynamicPropertyValue/dynamicPropertyValue.css"/>" />
	<script type="text/javascript"
		src="<c:url value="/js/component/product/dynamicPropertyValue/dynamicPropertyValue.js"/>"></script>
	<div class="page-head">
		<h2>
			<fmt:message key="product.dynamicPropertyValue.manager"></fmt:message>
			<a class="btn btn-primary" href="<c:url value="/product/dynamicPropertyValue/input"/>">
				<fmt:message key="product.dynamicPropertyValue.add"></fmt:message>
			</a>
		</h2>
	</div>
	<div class="cl-mcont">
		<div class="block-flat">
			<div class="content">
				<form action="<c:url value="/product/dynamicPropertyValue/home"/>" method="post"
					role="form" class="form-horizontal">
					<fieldset>
						<div class="form-group">
							<div class="col-md-2">
								<input type="text" name="EQ|value" value="${searchParams.value}"
									placeholder="<fmt:message key="product.dynamicPropertyValue.value"></fmt:message>"
									class="form-control input-sm">
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
							     <select name="EQ|shop.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="setting.shop"/></option>
							       <c:forEach items="${shops}" var ="shop">
							         <option value="${shop.id}"<c:if test="${shop.id eq entity.shop.id}"> selected</c:if>>${shop.name}</option>
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
								  <th><fmt:message key='product.dynamicPropertyValue.value'></fmt:message></th>
								  <th><fmt:message key='product.dynamicPropertyValue.dynamicProperty'></fmt:message></th>
								  <th><fmt:message key='product.dynamicPropertyValue.shop'></fmt:message></th>
								<!--@generate-entity-jsp-property-desc@-->
								<th><fmt:message key="global.view"/></th>
								<th><fmt:message key="global.edit"></fmt:message></th>
								<th><fmt:message key="global.remove"></fmt:message></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.content}" var="dynamicPropertyValue">
								<tr id="${dynamicPropertyValue.id}">
									<td><input type="checkbox" name="check-single" value="${dynamicPropertyValue.id}"></td>
									  <td>${dynamicPropertyValue.value}</td>
									  <td>${dynamicPropertyValue.dynamicProperty.name}</td>
									  <td>${dynamicPropertyValue.shop.name}</td>
									
									<!--@generate-entity-jsp-property-value@-->
									<td><a href="<c:url value="/product/dynamicPropertyValue/detail/${dynamicPropertyValue.id}.html"/>"><fmt:message key="global.view"/></a></td>
									<td><a
										href="<c:url value="/product/dynamicPropertyValue/edit/${dynamicPropertyValue.id}"/>"> <fmt:message
												key="global.edit"></fmt:message>
									</a></td>
									<td><a
										href="<c:url value="/product/dynamicPropertyValue/remove/${dynamicPropertyValue.id}"/>"> <fmt:message
												key="global.remove"></fmt:message>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<f:page page="${page}" url="/product/dynamicPropertyValue/home"  params="<%=request.getParameterMap()%>" />
			</div>
		</div>
	</div>