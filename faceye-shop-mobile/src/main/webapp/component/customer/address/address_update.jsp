<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/customer/address/address.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/customer/address/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/component/lbs/geoLibrary/geoLibrary.js"/>"></script>
<div class="card card-block bg-white">
	<div class="row">
		<c:import url="/component/core/template/msg/msg.jsp" />
	</div>
	<form:form action="/customer/address/save" method="post" role="form"
		cssClass="form-horizontal" commandName="address"
		id="customer-address-form">
		<form:hidden path="id" />
		<form:hidden path="customer.id" />
		<form:hidden path="isRemoved" />
		<fieldset class="form-group">
			<label class="col-sm-2 col-xs-3 control-label text-right">收货人</label>
			<div class="col-sm-10 col-xs-9">
				<form:input path="receiveUserName" cssClass="form-control"
					placeholder="姓名" />
				<form:errors path="receiveUserName" cssClass="error" />
			</div>
		</fieldset>
		<fieldset class="form-group">
			<label class="col-sm-2 col-xs-3 control-label text-right">手机号</label>
			<div class="col-sm-10 col-xs-9">
				<form:input path="mobile" cssClass="form-control" placeholder="手机号" />
				<form:errors path="mobile" cssClass="error" />
		</fieldset>
		<c:if test="${not empty geoLibraries }">
			<c:forEach items="${geoLibraries}" var="geoLibrary">
				<fieldset class="form-group">
					<c:if test="${geoLibrary.level eq 0 }">
						<label class="col-sm-2 col-xs-3 control-label text-right">省份</label>
						<div class="col-sm-10 col-xs-9">
							<select name="provinceId" class="form-control">
								<option value="${geoLibrary.id}">${geoLibrary.name}</option>
							</select>
						</div>
					</c:if>
					<c:if test="${geoLibrary.level eq 1 }">
						<label class="col-sm-2 col-xs-3 control-label text-right">城市</label>
						<div class="col-sm-10 col-xs-9">
							<select name="cityId" class="form-control">
								<option value="${geoLibrary.id}">${geoLibrary.name}</option>
							</select>
						</div>
					</c:if>
					<c:if test="${geoLibrary.level eq 2 }">
						<label class="col-sm-2 col-xs-3 control-label text-right">县/区</label>
						<div class="col-sm-10 col-xs-9">
							<select name="areaId" class="form-control">
								<option value="${geoLibrary.id}">${geoLibrary.name}</option>
							</select>
						</div>
					</c:if>
				</fieldset>
			</c:forEach>
		</c:if>
		<c:if test="${empty geoLibraries}">
			<fieldset class="form-group">
				<label class="col-sm-2 col-xs-3 control-label text-right">省份</label>
				<div class="col-sm-10 col-xs-9">
					<select name="provinceId" class="form-control">
						<option value="">选择省份</option>
					</select>
				</div>
			</fieldset>
			<fieldset class="form-group">
				<label class="col-sm-2 col-xs-3 control-label text-right">城市</label>
				<div class="col-sm-10 col-xs-9">
					<select name="cityId" class="form-control">
						<option value="">选择城市</option>
					</select>
				</div>
			</fieldset>
			<fieldset class="form-group">
				<label class="col-sm-2 col-xs-3 control-label text-right">县/区</label>
				<div class="col-sm-10 col-xs-9">
					<select name="areaId" class="form-control">
						<option value="">选择县/区</option>
					</select>
				</div>
			</fieldset>
		</c:if>
		<fieldset class="form-group">
			<label class="col-sm-2 col-xs-3 control-label text-right">详细地址</label>
			<div class="col-sm-10 col-xs-9">
				<form:input path="detail" cssClass="form-control"
					placeholder="请输入详细地址" />
				<form:errors path="detail" cssClass="error" />
			</div>
		</fieldset>
		<fieldset class="form-group">
			<label class="c-input c-checkbox"> <form:checkbox
					path="isDefault" /> <span class="c-indicator"></span> 设为默认地址
			</label>
		</fieldset>
		<!--@generate-entity-jsp-property-update@-->
		<fieldset class="form-group">
			<button type="button"
				class="btn btn-primary btn-sm btn-block customer-address-save-btn">
				<fmt:message key="global.submit.save"></fmt:message>
			</button>
		</fieldset>
	</form:form>
</div>

