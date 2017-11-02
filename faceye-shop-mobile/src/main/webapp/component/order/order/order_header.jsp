<%@ include file="/component/core/taglib/taglib.jsp"%>
<ul class="nav nav-tabs" role="tablist">
	<li class="nav-item"><a class="nav-link <c:if test="${param.type eq 'unpaid' }"> active</c:if>"
		href="<c:url value="/order/order/home?EQ|status=0&type=unpaid"/>"><fmt:message key="order.order.status.0" /></a></li>
	<li class="nav-item"><a class="nav-link<c:if test="${param.type eq 'paid' }"> active</c:if>"
		href="<c:url value="/order/order/home?EQ|status=1&type=paid"/>"><fmt:message key="order.order.status.1" /></a></li>
	
	<li class="nav-item"><a class="nav-link<c:if test="${empty param.type }"> active</c:if>"
		href="<c:url value="/order/order/home"/>"><fmt:message key="order.order.all" /></a></li>
</ul>
