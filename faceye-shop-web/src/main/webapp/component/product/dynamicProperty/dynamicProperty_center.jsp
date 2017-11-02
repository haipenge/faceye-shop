<%@ include file="/component/core/taglib/taglib.jsp"%>
	<link rel="stylesheet" type="text/css"
		href="<c:url value="/css/component/product/dynamicProperty/dynamicProperty.css"/>" />
	<script type="text/javascript"
		src="<c:url value="/js/component/product/dynamicProperty/dynamicProperty.js"/>"></script>
	<div class="page-head">
		<h2>
			<fmt:message key="product.dynamicProperty.manager"></fmt:message>
			<a class="btn btn-primary" href="<c:url value="/product/dynamicProperty/input"/>">
				<fmt:message key="product.dynamicProperty.add"></fmt:message>
			</a>
		</h2>
	</div>
	<div class="cl-mcont">
		<div class="block-flat">
			<div class="content">
				<form action="<c:url value="/product/dynamicProperty/home"/>" method="post"
					role="form" class="form-horizontal">
					<fieldset>
						<div class="form-group">
							<div class="col-md-2">
							     <select name="EQ|category.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="product.category"/></option>
							       <c:forEach items="${categorys}" var ="category">
							         <option value="${category.id}"<c:if test="${category.id eq entity.category.id}"> selected</c:if>>${category.name}</option>
							       </c:forEach>
							     </select>
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|dataType" value="${searchParams.dataType}"
									placeholder="<fmt:message key="product.dynamicProperty.dataType"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|unit" value="${searchParams.unit}"
									placeholder="<fmt:message key="product.dynamicProperty.unit"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|isSku" value="${searchParams.isSku}"
									placeholder="<fmt:message key="product.dynamicProperty.isSku"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|isRequired" value="${searchParams.isRequired}"
									placeholder="<fmt:message key="product.dynamicProperty.isRequired"></fmt:message>"
									class="form-control input-sm">
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
								<input type="text" name="EQ|isMulti" value="${searchParams.isMulti}"
									placeholder="<fmt:message key="product.dynamicProperty.isMulti"></fmt:message>"
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
								  <th><fmt:message key='product.dynamicProperty.name'></fmt:message></th>
								  <th><fmt:message key='product.dynamicProperty.dataType'></fmt:message></th>
								  <th><fmt:message key='product.dynamicProperty.unit'></fmt:message></th>
								  <th><fmt:message key='product.dynamicProperty.isSku'></fmt:message></th>
								  <th><fmt:message key='product.dynamicProperty.isRequired'></fmt:message></th>
								  <th><fmt:message key='product.dynamicProperty.shop'></fmt:message></th>
								  <th><fmt:message key='product.dynamicProperty.isMulti'></fmt:message></th>
								<!--@generate-entity-jsp-property-desc@-->
								<th><fmt:message key="global.view"/></th>
								<th><fmt:message key="global.edit"></fmt:message></th>
								<th><fmt:message key="global.remove"></fmt:message></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.content}" var="dynamicProperty">
								<tr id="${dynamicProperty.id}">
									<td><input type="checkbox" name="check-single" value="${dynamicProperty.id}"></td>
									  <td>${dynamicProperty.category.name}</td>
									  <td>${dynamicProperty.dataType}</td>
									  <td>${dynamicProperty.unit}</td>
									  <td>${dynamicProperty.isSku}</td>
									  <td>${dynamicProperty.isRequired}</td>
									  <td>${dynamicProperty.shop.name}</td>
									  <td>${dynamicProperty.isMulti}</td>
									
									<!--@generate-entity-jsp-property-value@-->
									<td><a href="<c:url value="/product/dynamicProperty/detail/${dynamicProperty.id}.html"/>"><fmt:message key="global.view"/></a></td>
									<td><a
										href="<c:url value="/product/dynamicProperty/edit/${dynamicProperty.id}"/>"> <fmt:message
												key="global.edit"></fmt:message>
									</a></td>
									<td><a
										href="<c:url value="/product/dynamicProperty/remove/${dynamicProperty.id}"/>"> <fmt:message
												key="global.remove"></fmt:message>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<f:page page="${page}" url="/product/dynamicProperty/home"  params="<%=request.getParameterMap()%>" />
			</div>
		</div>
	</div>