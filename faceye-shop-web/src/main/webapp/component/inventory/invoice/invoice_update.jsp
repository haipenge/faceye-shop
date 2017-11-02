<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/inventory/invoice/invoice.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/inventory/invoice/invoice.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty invoice.id}">
					<fmt:message key="inventory.invoice.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="inventory.invoice.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/inventory/invoice/save" method="post" role="form" cssClass="form-horizontal" commandName="invoice">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="inventory.invoice.shop"/>
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
					<label class="col-md-2 control-label" for="name"> <spring:message code="inventory.invoice.type"/>
					</label>
					<div class="col-md-6">
					     <form:input path="type" cssClass="form-control"/>
					   <form:errors path="type" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="inventory.invoice.remark"/>
					</label>
					<div class="col-md-6">
					     <form:input path="remark" cssClass="form-control"/>
					   <form:errors path="remark" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="inventory.invoice.code"/>
					</label>
					<div class="col-md-6">
					     <form:input path="code" cssClass="form-control"/>
					   <form:errors path="code" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="inventory.invoice.order"/>
					</label>
					<div class="col-md-6">
					      <form:select path="order.id" cssClass="form-control">
					      <form:option value="0" label="--Please Select" />
							<form:options items="${orders}" itemValue="id" itemLabel="name" />
						  </form:select>
						  <!--
					      <select name="order.id" class="form-control">
					        <option value="0"><fmt:message key="global.select"></fmt:message><fmt:message key="order.order"></fmt:message></option>
					        <c:forEach items ="${orders}" var="order">
					          <option value="${order.id}">${order.name}</option>
					        </c:forEach>
					      </select>
					      -->
					   <form:errors path="order" cssClass="error"/>
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