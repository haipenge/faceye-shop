<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/product/skuProperty/skuProperty.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/skuProperty/skuProperty.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty skuProperty.id}">
					<fmt:message key="product.skuProperty.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="product.skuProperty.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/product/skuProperty/save" method="post" role="form" cssClass="form-horizontal" commandName="skuProperty">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="product.skuProperty.productSku"/>
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
					<label class="col-md-2 control-label" for="name"> <spring:message code="product.skuProperty.shop"/>
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
					<label class="col-md-2 control-label" for="name"> <spring:message code="product.skuProperty.dynamicPropertyValue"/>
					</label>
					<div class="col-md-6">
					      <form:select path="dynamicPropertyValue.id" cssClass="form-control">
					      <form:option value="0" label="--Please Select" />
							<form:options items="${dynamicPropertyValues}" itemValue="id" itemLabel="name" />
						  </form:select>
						  <!--
					      <select name="dynamicPropertyValue.id" class="form-control">
					        <option value="0"><fmt:message key="global.select"></fmt:message><fmt:message key="product.dynamicPropertyValue"></fmt:message></option>
					        <c:forEach items ="${dynamicPropertyValues}" var="dynamicPropertyValue">
					          <option value="${dynamicPropertyValue.id}">${dynamicPropertyValue.name}</option>
					        </c:forEach>
					      </select>
					      -->
					   <form:errors path="dynamicPropertyValue" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="product.skuProperty.dynamicProperty"/>
					</label>
					<div class="col-md-6">
					      <form:select path="productProperty.id" cssClass="form-control">
					      <form:option value="0" label="--Please Select" />
							<form:options items="${productPropertys}" itemValue="id" itemLabel="name" />
						  </form:select>
						  <!--
					      <select name="productProperty.id" class="form-control">
					        <option value="0"><fmt:message key="global.select"></fmt:message><fmt:message key="product.productProperty"></fmt:message></option>
					        <c:forEach items ="${productPropertys}" var="productProperty">
					          <option value="${productProperty.id}">${productProperty.name}</option>
					        </c:forEach>
					      </select>
					      -->
					   <form:errors path="dynamicProperty" cssClass="error"/>
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