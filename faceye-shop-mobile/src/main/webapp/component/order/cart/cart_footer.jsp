<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/order/cart/cart.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/order/cart/cart.js"/>"></script>
<nav class="navbar navbar-fixed-bottom navbar-light bg-faded">
	<div class="row">
		<div class="content text-center">
			<button type="button" class="btn btn-sm btn-primary-outline" onclick="Cart.direct2Buy();return false;">
				<fmt:message key="product.product.buy" />
			</button>
			<button type="button" class="btn btn-sm btn-success-outline" onclick="Cart.add2Cart();return false;">
				<fmt:message key="order.cart.add.product" /><span class="label label-danger label-pill" id="order-cart-item-count">${cartInfo.totalQuantity}</span>
			</button>
			<a href="<c:url value="/order/cartItem/home"/>" class="btn btn-sm btn-success-outline"><fmt:message
					key="order.cart.balance" /></a>
		</div>
	</div>
</nav>
