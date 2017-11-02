<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/order/cartItem/cartItem.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/order/cartItem/cartItem.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty cartItem.id}">
					<fmt:message key="order.cartItem.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="order.cartItem.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/order/cartItem/save" method="post" role="form" cssClass="form-horizontal"
			commandName="cartItem">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="quantity"> <spring:message
			code="order.cartItem.quantity"/>
	</label>
	<div class="col-md-6">
		<form:input path="quantity" cssClass="form-control"/>
		<form:errors path="quantity" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="productSku"> <spring:message
			code="order.cartItem.productSku"/>
	</label>
	<div class="col-md-6">
		<form:input path="productSku" cssClass="form-control"/>
		<form:errors path="productSku" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="status"> <spring:message
			code="order.cartItem.status"/>
	</label>
	<div class="col-md-6">
		<form:input path="status" cssClass="form-control"/>
		<form:errors path="status" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="statusName"> <spring:message
			code="order.cartItem.statusName"/>
	</label>
	<div class="col-md-6">
		<form:input path="statusName" cssClass="form-control"/>
		<form:errors path="statusName" cssClass="error"/>
	</div>
</div>
<!--@generate-entity-jsp-property-update@-->




				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="global.submit.save"></fmt:message>
						</button>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>
