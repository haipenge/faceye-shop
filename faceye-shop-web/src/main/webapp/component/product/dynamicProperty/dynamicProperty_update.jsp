<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/product/dynamicProperty/dynamicProperty.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/dynamicProperty/dynamicProperty.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty dynamicProperty.id}">
					<fmt:message key="product.dynamicProperty.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="product.dynamicProperty.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/product/dynamicProperty/save" method="post" role="form" cssClass="form-horizontal" commandName="dynamicProperty">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="product.dynamicProperty.name"/>
					</label>
					<div class="col-md-6">
					      <form:select path="category.id" cssClass="form-control">
					      <form:option value="0" label="--Please Select" />
							<form:options items="${categorys}" itemValue="id" itemLabel="name" />
						  </form:select>
						  <!--
					      <select name="category.id" class="form-control">
					        <option value="0"><fmt:message key="global.select"></fmt:message><fmt:message key="product.category"></fmt:message></option>
					        <c:forEach items ="${categorys}" var="category">
					          <option value="${category.id}">${category.name}</option>
					        </c:forEach>
					      </select>
					      -->
					   <form:errors path="name" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="product.dynamicProperty.dataType"/>
					</label>
					<div class="col-md-6">
					     <form:input path="dataType" cssClass="form-control"/>
					   <form:errors path="dataType" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="product.dynamicProperty.unit"/>
					</label>
					<div class="col-md-6">
					     <form:input path="unit" cssClass="form-control"/>
					   <form:errors path="unit" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="product.dynamicProperty.isSku"/>
					</label>
					<div class="col-md-6">
					     <form:input path="isSku" cssClass="form-control"/>
					   <form:errors path="isSku" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="product.dynamicProperty.isRequired"/>
					</label>
					<div class="col-md-6">
					     <form:input path="isRequired" cssClass="form-control"/>
					   <form:errors path="isRequired" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="product.dynamicProperty.shop"/>
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
					<label class="col-md-2 control-label" for="name"> <spring:message code="product.dynamicProperty.isMulti"/>
					</label>
					<div class="col-md-6">
					     <form:input path="isMulti" cssClass="form-control"/>
					   <form:errors path="isMulti" cssClass="error"/>
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