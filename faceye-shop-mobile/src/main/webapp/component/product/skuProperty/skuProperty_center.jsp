<%@ include file="/component/core/taglib/taglib.jsp"%>
	<link rel="stylesheet" type="text/css"
		href="<c:url value="/css/component/product/skuProperty/skuProperty.css"/>" />
	<script type="text/javascript"
		src="<c:url value="/js/component/product/skuProperty/skuProperty.js"/>"></script>
	<div class="page-head">
		<h2>
			<fmt:message key="product.skuProperty.manager"></fmt:message>
			<a class="btn btn-primary" href="<c:url value="/product/skuProperty/input"/>">
				<fmt:message key="product.skuProperty.add"></fmt:message>
			</a>
		</h2>
	</div>
	<div class="cl-mcont">
		<div class="block-flat">
			<div class="content">
				<form action="<c:url value="/product/skuProperty/home"/>" method="post"
					role="form" class="form-horizontal">
					<fieldset>
						<div class="form-group">
							<div class="col-md-2">
							     <select name="EQ|productSku.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="product.productSku"/></option>
							       <c:forEach items="${productSkus}" var ="productSku">
							         <option value="${productSku.id}"<c:if test="${productSku.id eq entity.productSku.id}"> selected</c:if>>${productSku.name}</option>
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
							     <select name="EQ|dynamicPropertyValue.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="product.dynamicPropertyValue"/></option>
							       <c:forEach items="${dynamicPropertyValues}" var ="dynamicPropertyValue">
							         <option value="${dynamicPropertyValue.id}"<c:if test="${dynamicPropertyValue.id eq entity.dynamicPropertyValue.id}"> selected</c:if>>${dynamicPropertyValue.name}</option>
							       </c:forEach>
							     </select>
							</div>
							<div class="col-md-2">
							     <select name="EQ|productProperty.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="product.productProperty"/></option>
							       <c:forEach items="${productPropertys}" var ="productProperty">
							         <option value="${productProperty.id}"<c:if test="${productProperty.id eq entity.productProperty.id}"> selected</c:if>>${productProperty.name}</option>
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
								  <th><fmt:message key='product.skuProperty.productSku'></fmt:message></th>
								  <th><fmt:message key='product.skuProperty.shop'></fmt:message></th>
								  <th><fmt:message key='product.skuProperty.dynamicPropertyValue'></fmt:message></th>
								  <th><fmt:message key='product.skuProperty.dynamicProperty'></fmt:message></th>
								<!--@generate-entity-jsp-property-desc@-->
								<th><fmt:message key="global.view"/></th>
								<th><fmt:message key="global.edit"></fmt:message></th>
								<th><fmt:message key="global.remove"></fmt:message></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.content}" var="skuProperty">
								<tr id="${skuProperty.id}">
									<td><input type="checkbox" name="check-single" value="${skuProperty.id}"></td>
									  <td>${skuProperty.productSku.name}</td>
									  <td>${skuProperty.shop.name}</td>
									  <td>${skuProperty.dynamicPropertyValue.name}</td>
									  <td>${skuProperty.productProperty.name}</td>
									
									<!--@generate-entity-jsp-property-value@-->
									<td><a href="<c:url value="/product/skuProperty/detail/${skuProperty.id}.html"/>"><fmt:message key="global.view"/></a></td>
									<td><a
										href="<c:url value="/product/skuProperty/edit/${skuProperty.id}"/>"> <fmt:message
												key="global.edit"></fmt:message>
									</a></td>
									<td><a
										href="<c:url value="/product/skuProperty/remove/${skuProperty.id}"/>"> <fmt:message
												key="global.remove"></fmt:message>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<f:page page="${page}" url="/product/skuProperty/home"  params="<%=request.getParameterMap()%>" />
			</div>
		</div>
	</div>