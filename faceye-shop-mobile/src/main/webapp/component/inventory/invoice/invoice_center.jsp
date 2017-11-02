<%@ include file="/component/core/taglib/taglib.jsp"%>
	<link rel="stylesheet" type="text/css"
		href="<c:url value="/css/component/inventory/invoice/invoice.css"/>" />
	<script type="text/javascript"
		src="<c:url value="/js/component/inventory/invoice/invoice.js"/>"></script>
	<div class="page-head">
		<h2>
			<fmt:message key="inventory.invoice.manager"></fmt:message>
			<a class="btn btn-primary" href="<c:url value="/inventory/invoice/input"/>">
				<fmt:message key="inventory.invoice.add"></fmt:message>
			</a>
		</h2>
	</div>
	<div class="cl-mcont">
		<div class="block-flat">
			<div class="content">
				<form action="<c:url value="/inventory/invoice/home"/>" method="post"
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
								<input type="text" name="EQ|type" value="${searchParams.type}"
									placeholder="<fmt:message key="inventory.invoice.type"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|remark" value="${searchParams.remark}"
									placeholder="<fmt:message key="inventory.invoice.remark"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|code" value="${searchParams.code}"
									placeholder="<fmt:message key="inventory.invoice.code"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
							     <select name="EQ|order.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="order.order"/></option>
							       <c:forEach items="${orders}" var ="order">
							         <option value="${order.id}"<c:if test="${order.id eq entity.order.id}"> selected</c:if>>${order.name}</option>
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
								  <th><fmt:message key='inventory.invoice.shop'></fmt:message></th>
								  <th><fmt:message key='inventory.invoice.type'></fmt:message></th>
								  <th><fmt:message key='inventory.invoice.remark'></fmt:message></th>
								  <th><fmt:message key='inventory.invoice.code'></fmt:message></th>
								  <th><fmt:message key='inventory.invoice.order'></fmt:message></th>
								<!--@generate-entity-jsp-property-desc@-->
								<th><fmt:message key="global.view"/></th>
								<th><fmt:message key="global.edit"></fmt:message></th>
								<th><fmt:message key="global.remove"></fmt:message></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.content}" var="invoice">
								<tr id="${invoice.id}">
									<td><input type="checkbox" name="check-single" value="${invoice.id}"></td>
									  <td>${invoice.shop.name}</td>
									  <td>${invoice.type}</td>
									  <td>${invoice.remark}</td>
									  <td>${invoice.code}</td>
									  <td>${invoice.order.name}</td>
									
									<!--@generate-entity-jsp-property-value@-->
									<td><a href="<c:url value="/inventory/invoice/detail/${invoice.id}.html"/>"><fmt:message key="global.view"/></a></td>
									<td><a
										href="<c:url value="/inventory/invoice/edit/${invoice.id}"/>"> <fmt:message
												key="global.edit"></fmt:message>
									</a></td>
									<td><a
										href="<c:url value="/inventory/invoice/remove/${invoice.id}"/>"> <fmt:message
												key="global.remove"></fmt:message>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<f:page page="${page}" url="/inventory/invoice/home"  params="<%=request.getParameterMap()%>" />
			</div>
		</div>
	</div>