<%@ include file="/component/core/taglib/taglib.jsp"%>
<!--  
<div class="content">
	<footer class="text-center">
		<hr>
		<fmt:message key="copyright" />
	</footer>
</div>
-->
<nav class="navbar navbar-fixed-bottom navbar-light" style="background-color:white;border-top:thin solid lightgray;">
	<div class="row">
		<div class="col-md-3 col-xs-3 col-lg-3 text-center">
			<a href="<c:url value="/"/>" class="btn btn-sm btn-success-outline"><fmt:message key="shop.mobile.home" /></a>
		</div>
		<div class="col-md-3 col-xs-3 col-lg-3 text-center">
			<a href="#" class="btn btn-sm  btn-success-outline"><fmt:message key="shop.mobile.search"/></a>
		</div>
		<div class="col-md-3 col-xs-3 col-lg-3 text-center">
			<a href="<c:url value="/order/cartItem/home"/>" class="btn btn-sm btn-success-outline"><fmt:message key="order.cart" /><span
				class="label label-danger label-pill" id="order-cart-item-count">${cartInfo.totalQuantity}</span></a>
		</div>
		<div class="col-md-3 col-xs-3 col-lg-3 text-center">
			<a href="<c:url value="/customer/customer/me"/>" class="btn btn-sm btn-success-outline"><fmt:message key="shop.mobile.me" /></a>
		</div>
	</div>
</nav>
