<%@ include file="/component/core/taglib/taglib.jsp"%>
<div class="card card-block bg-white">
	<c:if test="${not empty pageContext.request.userPrincipal}">
		<c:if test="${not empty weixinUser }">
			<div class="row">
				<div class="col-xs-3 col-md-3 col-lg-2">
					<img src="${weixinUser.headimgurl}" alt="Welcome" class="img-responsive img-rounded img-thumbnail">
				</div>
				<div class="col-xs-9 col-md-9 col-lg-10">${weixinUser.nickname}</div>
			</div>
		</c:if>
		<c:if test="${empty weixinUser }">
			<div class="row">
				<p>
					Welcome
					<sec:authentication property="principal.username" />
				</p>
			</div>
		</c:if>
	</c:if>
</div>
<div class="card card-block bg-white">
   <h6>My Orders</h6>
   
</div>
<div class="list-group">
	<a href="<c:url value="/order/cartItem/home"/>" class="list-group-item"><fmt:message key="order.cart" /></a> <a
		href="<c:url value="/order/order/home?type=unpaid"/>" class="list-group-item"><fmt:message key="order.order" /></a> <a
		href="<c:url value="/customer/address/home"/>" class="list-group-item"><fmt:message key="customer.address.my" /></a>
</div>
