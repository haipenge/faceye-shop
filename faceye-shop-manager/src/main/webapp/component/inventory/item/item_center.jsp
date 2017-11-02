<%@ include file="/component/core/taglib/taglib.jsp"%>
	<link rel="stylesheet" type="text/css"
		href="<c:url value="/css/component/inventory/item/item.css"/>" />
	<script type="text/javascript"
		src="<c:url value="/js/component/inventory/item/item.js"/>"></script>
	<div class="page-head">
		<h2>
			<fmt:message key="inventory.item.manager"></fmt:message>
			<a class="btn btn-primary" href="<c:url value="/inventory/item/input"/>">
				<fmt:message key="inventory.item.add"></fmt:message>
			</a>
		</h2>
	</div>
	<div class="cl-mcont">
		<div class="block-flat">
			<div class="content">
				<form action="<c:url value="/inventory/invoiceItem/home"/>" method="post"
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
							     <select name="EQ|product.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="product.product"/></option>
							       <c:forEach items="${products}" var ="product">
							         <option value="${product.id}"<c:if test="${product.id eq entity.product.id}"> selected</c:if>>${product.name}</option>
							       </c:forEach>
							     </select>
							</div>
							<div class="col-md-2">
							     <select name="EQ|productSku.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="product.productSku"/></option>
							       <c:forEach items="${productSkus}" var ="productSku">
							         <option value="${productSku.id}"<c:if test="${productSku.id eq entity.productSku.id}"> selected</c:if>>${productSku.name}</option>
							       </c:forEach>
							     </select>
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|quantity" value="${searchParams.quantity}"
									placeholder="<fmt:message key="inventory.item.quantity"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
							     <select name="EQ|invoice.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="inventory.invoice"/></option>
							       <c:forEach items="${invoices}" var ="invoice">
							         <option value="${invoice.id}"<c:if test="${invoice.id eq entity.invoice.id}"> selected</c:if>>${invoice.name}</option>
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
								  <th><fmt:message key='inventory.item.shop'></fmt:message></th>
								  <th><fmt:message key='inventory.item.product'></fmt:message></th>
								  <th><fmt:message key='inventory.item.productSku'></fmt:message></th>
								  <th><fmt:message key='inventory.item.quantity'></fmt:message></th>
								  <th><fmt:message key='inventory.item.invoice'></fmt:message></th>
								<!--@generate-entity-jsp-property-desc@-->
								<th><fmt:message key="global.edit"></fmt:message></th>
								<th><fmt:message key="global.remove"></fmt:message></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.content}" var="item">
								<tr id="${item.id}">
									<td><input type="checkbox" name="check-single" value="${item.id}"></td>
									  <td>${item.shop.name}</td>
									  <td>${item.product.name}</td>
									  <td>${item.productSku.name}</td>
									  <td>${item.quantity}</td>
									  <td>${item.invoice.name}</td>
									
									<!--@generate-entity-jsp-property-value@-->
									<td><a
										href="<c:url value="/inventory/invoiceItem/edit/${item.id}"/>"> <fmt:message
												key="global.edit"></fmt:message>
									</a></td>
									<td><a
										href="<c:url value="/inventory/invoiceItem/remove/${item.id}"/>"> <fmt:message
												key="global.remove"></fmt:message>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<f:page page="${page}" url="/inventory/invoiceItem/home"  params="<%=request.getParameterMap()%>" />
			</div>
		</div>
	</div>