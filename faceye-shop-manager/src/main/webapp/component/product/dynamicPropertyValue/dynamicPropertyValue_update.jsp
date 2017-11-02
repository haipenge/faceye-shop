<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/product/dynamicPropertyValue/dynamicPropertyValue.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/dynamicPropertyValue/dynamicPropertyValue.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty dynamicPropertyValue.id}">
					<fmt:message key="product.dynamicPropertyValue.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="product.dynamicPropertyValue.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form:form action="/product/dynamicPropertyValue/save" method="post" role="form" cssClass="form-horizontal"
			commandName="dynamicPropertyValue">
			<form:hidden path="id" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message
							code="product.dynamicPropertyValue.dynamicProperty" />
					</label>
					<div class="col-md-6">
						<form:hidden path="dynamicProperty.id" />
						${dynamicPropertyValue.dynamicProperty.name}
						<form:errors path="dynamicProperty" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="product.dynamicPropertyValue.value" />
					</label>
					<div class="col-md-6">
						<form:input path="value" cssClass="form-control" />
						<form:errors path="value" cssClass="error" />
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