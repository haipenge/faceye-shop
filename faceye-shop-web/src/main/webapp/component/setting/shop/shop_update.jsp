<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/setting/shop/shop.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/setting/shop/shop.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty shop.id}">
					<fmt:message key="setting.shop.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="setting.shop.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form:form action="/setting/shop/save" method="post" role="form" cssClass="form-horizontal" commandName="shop">
			<form:hidden path="id" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="setting.shop.name" />
					</label>
					<div class="col-md-6">
						<form:input path="name" cssClass="form-control" />
						<form:errors path="name" cssClass="error" />
					</div>
					<div class="cod-md-6">
						<form:select path="account.id" cssClass="form-control">
							<form:option value="0" label="--Please Select Compane" />
							<form:options items="${accounts}" itemValue="id" itemLabel="weixinName" />
						</form:select>
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