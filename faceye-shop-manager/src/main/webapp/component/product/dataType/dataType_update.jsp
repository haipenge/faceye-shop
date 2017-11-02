<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/product/dataType/dataType.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/product/dataType/dataType.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty dataType.id}">
					<fmt:message key="product.dataType.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="product.dataType.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/product/dataType/save" method="post" role="form" cssClass="form-horizontal"
			commandName="dataType">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="name"> <spring:message
			code="product.dataType.name"/>
	</label>
	<div class="col-md-6">
		<form:input path="name" cssClass="form-control"/>
		<form:errors path="name" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="code"> <spring:message
			code="product.dataType.code"/>
	</label>
	<div class="col-md-6">
		<form:input path="code" cssClass="form-control"/>
		<form:errors path="code" cssClass="error"/>
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
