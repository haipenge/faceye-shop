<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/customer/address/address.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/component/customer/address/address.js"/>"></script>
<div class="list-group">
	<c:forEach items="${page.content}" var="address">
		<li class="list-group-item">
			<div class="row">
				<div class="col-md-10 col-xs-10 col-sm-10">
					<c:if test="${address.isDefault}">
						<span class="text-success">[默认]</span>
					</c:if>${address.show}<br>
					${address.receiveUserName}&nbsp;&nbsp;${address.mobile }
				</div>
				<div class="col-md-2 col-xs-2 col-sm-2">
					<a href="<c:url value="/customer/address/edit/${address.id}"/>"
						class="btn btn-sm btn-primary-outline"> <fmt:message
							key="global.edit"></fmt:message>
					</a>
				</div>
			</div>
		</li>
	</c:forEach>
	<a href="<c:url value="/customer/address/input"/>">
		<li class="list-group-item"><div class="row">
				<div class="col-xs-10 col-md-10">
					<fmt:message key="customer.address.add" />
				</div>
				<div class="col-xs-2 col-md-2">+</div>
			</div></li>
	</a>
</div>