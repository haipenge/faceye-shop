<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/order/cartItem/cartItem.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/order/cartItem/cartItem.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="order.cartItem.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/order/cartItem/input"/>"> <fmt:message
				key="order.cartItem.add"></fmt:message>
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
			<form action="<c:url value="/order/cartItem/home"/>" method="post" role="form"
				class="form-horizontal">
				<fieldset>
					<div class="form-group">
						
<div class="col-md-1">
	<input type="text" name="EQ|quantity" value="${searchParams.quantity}"
		placeholder="<fmt:message key="order.cartItem.quantity"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|productSku" value="${searchParams.productSku}"
		placeholder="<fmt:message key="order.cartItem.productSku"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|status" value="${searchParams.status}"
		placeholder="<fmt:message key="order.cartItem.status"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|statusName" value="${searchParams.statusName}"
		placeholder="<fmt:message key="order.cartItem.statusName"></fmt:message>"
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
							<th><fmt:message key='order.cartItem.quantity'></fmt:message></th>   
 <th><fmt:message key='order.cartItem.productSku'></fmt:message></th>   
 <th><fmt:message key='order.cartItem.status'></fmt:message></th>   
 <th><fmt:message key='order.cartItem.statusName'></fmt:message></th>   
 <!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="cartItem">
							<tr>
							   <td><input type="checkbox" name="check-single" value="${cartItem.id}"></td>
								<td>${cartItem.quantity}</td>   
 <td>${cartItem.productSku}</td>   
 <td>${cartItem.status}</td>   
 <td>${cartItem.statusName}</td>   
 <!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/order/cartItem/edit/${cartItem.id}"/>">
										<fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/order/cartItem/remove/${cartItem.id}"/>">
										<fmt:message key="global.remove"></fmt:message>
								</a></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/order/cartItem/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>
