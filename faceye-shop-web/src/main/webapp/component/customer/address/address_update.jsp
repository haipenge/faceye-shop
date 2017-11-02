<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/customer/address/address.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/customer/address/address.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty address.id}">
					<fmt:message key="customer.address.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="customer.address.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form:form action="/customer/address/save" method="post" role="form" cssClass="form-horizontal" commandName="address">
			<form:hidden path="id" />
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="customer.address.customer" />
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
						<form:errors path="customer" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="mobile"> <spring:message code="customer.address.mobile" />
					</label>
					<div class="col-md-6">
						<form:input path="mobile" cssClass="form-control" />
						<form:errors path="mobile" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="receiveUserName"> <spring:message
							code="customer.address.receiveUserName" />
					</label>
					<div class="col-md-6">
						<form:input path="receiveUserName" cssClass="form-control" />
						<form:errors path="receiveUserName" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="province"> <spring:message code="customer.address.province" />
					</label>
					<div class="col-md-6">
						<form:input path="province" cssClass="form-control" />
						<form:errors path="province" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="city"> <spring:message code="customer.address.city" />
					</label>
					<div class="col-md-6">
						<form:input path="city" cssClass="form-control" />
						<form:errors path="city" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="area"> <spring:message code="customer.address.area" />
					</label>
					<div class="col-md-6">
						<form:input path="area" cssClass="form-control" />
						<form:errors path="area" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="street"> <spring:message code="customer.address.street" />
					</label>
					<div class="col-md-6">
						<form:input path="street" cssClass="form-control" />
						<form:errors path="street" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="detail"> <spring:message code="customer.address.detail" />
					</label>
					<div class="col-md-6">
						<form:input path="detail" cssClass="form-control" />
						<form:errors path="detail" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="isDefault"> <spring:message code="customer.address.isDefault" />
					</label>
					<div class="col-md-6">
						<form:input path="isDefault" cssClass="form-control" />
						<form:errors path="isDefault" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
	<label class="col-md-2 control-label" for="isRemoved"> <spring:message
			code="customer.address.isRemoved"/>
	</label>
	<div class="col-md-6">
		<form:input path="isRemoved" cssClass="form-control"/>
		<form:errors path="isRemoved" cssClass="error"/>
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