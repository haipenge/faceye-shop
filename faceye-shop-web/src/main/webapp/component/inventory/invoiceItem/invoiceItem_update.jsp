<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/inventory/invoiceItem/invoiceItem.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/inventory/invoiceItem/invoiceItem.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty invoiceItem.id}">
					<fmt:message key="inventory.invoiceItem.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="inventory.invoiceItem.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/inventory/invoiceItem/save" method="post" role="form" cssClass="form-horizontal" commandName="invoiceItem">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="inventory.invoiceItem.shop"/>
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
					<label class="col-md-2 control-label" for="name"> <spring:message code="inventory.invoiceItem.product"/>
					</label>
					<div class="col-md-6">
					      <form:select path="product.id" cssClass="form-control">
					      <form:option value="0" label="--Please Select" />
							<form:options items="${products}" itemValue="id" itemLabel="name" />
						  </form:select>
						  <!--
					      <select name="product.id" class="form-control">
					        <option value="0"><fmt:message key="global.select"></fmt:message><fmt:message key="product.product"></fmt:message></option>
					        <c:forEach items ="${products}" var="product">
					          <option value="${product.id}">${product.name}</option>
					        </c:forEach>
					      </select>
					      -->
					   <form:errors path="product" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="inventory.invoiceItem.productSku"/>
					</label>
					<div class="col-md-6">
					      <form:select path="productSku.id" cssClass="form-control">
					      <form:option value="0" label="--Please Select" />
							<form:options items="${productSkus}" itemValue="id" itemLabel="name" />
						  </form:select>
						  <!--
					      <select name="productSku.id" class="form-control">
					        <option value="0"><fmt:message key="global.select"></fmt:message><fmt:message key="product.productSku"></fmt:message></option>
					        <c:forEach items ="${productSkus}" var="productSku">
					          <option value="${productSku.id}">${productSku.name}</option>
					        </c:forEach>
					      </select>
					      -->
					   <form:errors path="productSku" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="inventory.invoiceItem.quantity"/>
					</label>
					<div class="col-md-6">
					     <form:input path="quantity" cssClass="form-control"/>
					   <form:errors path="quantity" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="inventory.invoiceItem.invoice"/>
					</label>
					<div class="col-md-6">
					      <form:select path="invoice.id" cssClass="form-control">
					      <form:option value="0" label="--Please Select" />
							<form:options items="${invoices}" itemValue="id" itemLabel="name" />
						  </form:select>
						  <!--
					      <select name="invoice.id" class="form-control">
					        <option value="0"><fmt:message key="global.select"></fmt:message><fmt:message key="inventory.invoice"></fmt:message></option>
					        <c:forEach items ="${invoices}" var="invoice">
					          <option value="${invoice.id}">${invoice.name}</option>
					        </c:forEach>
					      </select>
					      -->
					   <form:errors path="invoice" cssClass="error"/>
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