<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/order/order/order.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/order/order/order.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty order.id}">
					<fmt:message key="order.order.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="order.order.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/order/order/save" method="post" role="form" cssClass="form-horizontal" commandName="order">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="order.order.code"/>
					</label>
					<div class="col-md-6">
					     <form:input path="code" cssClass="form-control"/>
					   <form:errors path="code" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="order.order.status"/>
					</label>
					<div class="col-md-6">
					     <form:input path="status" cssClass="form-control"/>
					   <form:errors path="status" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="order.order.shop"/>
					</label>
					<div class="col-md-6">
					      <form:select path="shop.id" cssClass="form-control">
					      <form:option value="0" label="--Please Select" />
							<form:options items="${shops}" itemValue="id" itemLabel="name" />
						  </form:select>
						  <!--
					      <select name="shop.id" class="form-control">
					        <option value="0"><fmt:message key="global.select"></fmt:message><fmt:message key="setting.shop"></fmt:message></option>
					        <c:forEach items ="${shops}" var="shop">
					          <option value="${shop.id}">${shop.name}</option>
					        </c:forEach>
					      </select>
					      -->
					   <form:errors path="shop" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="order.order.customer"/>
					</label>
					<div class="col-md-6">
					      <form:select path="customer.id" cssClass="form-control">
					      <form:option value="0" label="--Please Select" />
							<form:options items="${customers}" itemValue="id" itemLabel="name" />
						  </form:select>
						  <!--
					      <select name="customer.id" class="form-control">
					        <option value="0"><fmt:message key="global.select"></fmt:message><fmt:message key="customer.customer"></fmt:message></option>
					        <c:forEach items ="${customers}" var="customer">
					          <option value="${customer.id}">${customer.name}</option>
					        </c:forEach>
					      </select>
					      -->
					   <form:errors path="customer" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="order.order.isPaid"/>
					</label>
					<div class="col-md-6">
					     <form:input path="isPaid" cssClass="form-control"/>
					   <form:errors path="isPaid" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="order.order.payWay"/>
					</label>
					<div class="col-md-6">
					     <form:input path="payWay" cssClass="form-control"/>
					   <form:errors path="payWay" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="order.order.payDate"/>
					</label>
					<div class="col-md-6">
					     <form:input path="payDate" cssClass="form-control"/>
					   <form:errors path="payDate" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="order.order.totalFee"/>
					</label>
					<div class="col-md-6">
					     <form:input path="totalFee" cssClass="form-control"/>
					   <form:errors path="totalFee" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="order.order.remark"/>
					</label>
					<div class="col-md-6">
					     <form:input path="remark" cssClass="form-control"/>
					   <form:errors path="remark" cssClass="error"/>
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