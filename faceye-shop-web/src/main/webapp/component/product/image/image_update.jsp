<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/product/image/image.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/product/image/image.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty image.id}">
					<fmt:message key="product.image.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="product.image.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/product/image/save" method="post" role="form" cssClass="form-horizontal"
			commandName="image">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="path"> <spring:message
			code="product.image.path"/>
	</label>
	<div class="col-md-6">
		<form:input path="path" cssClass="form-control"/>
		<form:errors path="path" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="url"> <spring:message
			code="product.image.url"/>
	</label>
	<div class="col-md-6">
		<form:input path="url" cssClass="form-control"/>
		<form:errors path="url" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="isDefault"> <spring:message
			code="product.image.isDefault"/>
	</label>
	<div class="col-md-6">
		<form:input path="isDefault" cssClass="form-control"/>
		<form:errors path="isDefault" cssClass="error"/>
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
