<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/order/order/order.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/order/order/order.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="order.order"></fmt:message>
		Pay Success!
	</h2>
</div>
<div class="cl-mcont">
	<div class="block-flat">
		<!-- 
			<div class="content">
				<form action="<c:url value="/order/order/home"/>" method="post"
					role="form" class="form-horizontal">
					<fieldset>
						<div class="form-group">
							<div class="col-md-2">
								<input type="text" name="EQ|code" value="${searchParams.code}"
									placeholder="<fmt:message key="order.order.code"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|status" value="${searchParams.status}"
									placeholder="<fmt:message key="order.order.status"></fmt:message>"
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
							     <select name="EQ|customer.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="customer.customer"/></option>
							       <c:forEach items="${customers}" var ="customer">
							         <option value="${customer.id}"<c:if test="${customer.id eq entity.customer.id}"> selected</c:if>>${customer.name}</option>
							       </c:forEach>
							     </select>
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|isPaid" value="${searchParams.isPaid}"
									placeholder="<fmt:message key="order.order.isPaid"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|payWay" value="${searchParams.payWay}"
									placeholder="<fmt:message key="order.order.payWay"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|payDate" value="${searchParams.payDate}"
									placeholder="<fmt:message key="order.order.payDate"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|totalFee" value="${searchParams.totalFee}"
									placeholder="<fmt:message key="order.order.totalFee"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-2">
								<input type="text" name="EQ|remark" value="${searchParams.remark}"
									placeholder="<fmt:message key="order.order.remark"></fmt:message>"
									class="form-control input-sm">
							</div>
							<div class="col-md-1">
								<button type="submit" class="btn btn-sm btn-primary">
									<fmt:message key="global.search"></fmt:message>
								</button>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
			 -->
	</div>
</div>