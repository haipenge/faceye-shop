<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/lbs/geoLibrary/geoLibrary.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/lbs/geoLibrary/geoLibrary.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty geoLibrary.id}">
					<fmt:message key="lbs.geoLibrary.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="lbs.geoLibrary.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
	<form:form action="/lbs/geoLibrary/save" method="post" role="form" cssClass="form-horizontal"
			commandName="geoLibrary">
			<form:hidden path="id"/>
			<fieldset>
				<div class="form-group">
	<label class="col-md-2 control-label" for="name"> <spring:message
			code="lbs.geoLibrary.name"/>
	</label>
	<div class="col-md-6">
		<form:input path="name" cssClass="form-control"/>
		<form:errors path="name" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="orderIndex"> <spring:message
			code="lbs.geoLibrary.orderIndex"/>
	</label>
	<div class="col-md-6">
		<form:input path="orderIndex" cssClass="form-control"/>
		<form:errors path="orderIndex" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="code"> <spring:message
			code="lbs.geoLibrary.code"/>
	</label>
	<div class="col-md-6">
		<form:input path="code" cssClass="form-control"/>
		<form:errors path="code" cssClass="error"/>
	</div>
</div>
<div class="form-group">
	<label class="col-md-2 control-label" for="level"> <spring:message
			code="lbs.geoLibrary.level"/>
	</label>
	<div class="col-md-6">
		<form:input path="level" cssClass="form-control"/>
		<form:errors path="level" cssClass="error"/>
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
