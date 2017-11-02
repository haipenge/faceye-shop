<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/order/cart/cart.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/order/cart/cart.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty cart.id}">
					<fmt:message key="order.cart.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="order.cart.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/order/cart/save" method="post" role="form" cssClass="form-horizontal"
			commandName="cart">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="uid"> <spring:message
			code="order.cart.uid"/>
	</label>
	<div class="col-md-6">
		<form:input path="uid" cssClass="form-control"/>
		<form:errors path="uid" cssClass="error"/>
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
