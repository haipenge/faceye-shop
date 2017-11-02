<%@ include file="/component/core/taglib/taglib.jsp"%>
	<link rel="stylesheet" type="text/css"
		href="<c:url value="/css/component/inventory/inventory/inventory.css"/>" />
	<script type="text/javascript"
		src="<c:url value="/js/component/inventory/inventory/inventory.js"/>"></script>
	<div class="page-head">
		<h2>
			<fmt:message key="inventory.inventory.manager"></fmt:message>
			<a class="btn btn-primary" href="<c:url value="/inventory/inventory/input"/>">
				<fmt:message key="inventory.inventory.add"></fmt:message>
			</a>
		</h2>
	</div>
	<div class="cl-mcont">
		<div class="block-flat">
			<div class="content">
				<form action="<c:url value="/inventory/inventory/home"/>" method="post"
					role="form" class="form-horizontal">
					<fieldset>
						<div class="form-group">
							<div class="col-md-2">
							     <select name="EQ|shop.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="setting.shop"/></option>
							       <c:forEach items="${shops}" var ="shop">
							         <option value="${shop.id}"<c:if test="${shop.id eq entity.shop.id}"> selected</c:if>>${shop.name}</option>
							       </c:forEach>
							     </select>
							</div>
							<div class="col-md-2">
							     <select name="EQ|productSku.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="product.productSku"/></option>
							       <c:forEach items="${productSkus}" var ="productSku">
							         <option value="${productSku.id}"<c:if test="${productSku.id eq entity.productSku.id}"> selected</c:if>>${productSku.product.name}</option>
							       </c:forEach>
							     </select>
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|amount" value="${searchParams.amount}"
									placeholder="<fmt:message key="inventory.inventory.amount"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
							     <select name="EQ|product.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="product.product"/></option>
							       <c:forEach items="${products}" var ="product">
							         <option value="${product.id}"<c:if test="${product.id eq entity.product.id}"> selected</c:if>>${product.name}</option>
							       </c:forEach>
							     </select>
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|lockedCount" value="${searchParams.lockedCount}"
									placeholder="<fmt:message key="inventory.inventory.lockedCount"></fmt:message>"
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
								  <th><fmt:message key='inventory.inventory.shop'></fmt:message></th>
								  <th><fmt:message key='inventory.inventory.productSku'></fmt:message></th>
								  <th><fmt:message key='inventory.inventory.amount'></fmt:message></th>
								  <th><fmt:message key='inventory.inventory.product'></fmt:message></th>
								  <th><fmt:message key='inventory.inventory.lockedCount'></fmt:message></th>
								<!--@generate-entity-jsp-property-desc@-->
								<th><fmt:message key="global.edit"></fmt:message></th>
								<th><fmt:message key="global.remove"></fmt:message></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${skuInfos}" var="skuInfo">
								<tr id="${skuInfo.inventory.id}">
									<td><input type="checkbox" name="check-single" value="${skuInfo.inventory.id}"></td>
									  <td>${skuInfo.inventory.shop.name}</td>
									  <td>${skuInfo.product.name} -
									    &nbsp;&nbsp;
									    <c:forEach items="${skuInfo.skuProperties}" var="skuProperty" varStatus="status">
									     ${skuProperty.dynamicProperty.name}:${skuProperty.dynamicPropertyValue.value}
									     <c:if test="${! status.last }">
									     ,
									     </c:if>
									    </c:forEach>
									  </td>
									  <td>${skuInfo.inventory.amount}</td>
									  <td>${skuInfo.product.name}</td>
									  <td>${skuInfo.inventory.lockedCount}</td>
									
									<!--@generate-entity-jsp-property-value@-->
									<td><a
										href="<c:url value="/inventory/inventory/edit/${skuInfo.inventory.id}"/>"> <fmt:message
												key="global.edit"></fmt:message>
									</a></td>
									<td><a
										href="<c:url value="/inventory/inventory/remove/${skuInfo.inventory.id}"/>"> <fmt:message
												key="global.remove"></fmt:message>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<f:page page="${page}" url="/inventory/inventory/home"  params="<%=request.getParameterMap()%>" />
			</div>
		</div>
	</div>