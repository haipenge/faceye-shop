<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/order/cartItem/cartItem.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/order/cartItem/cartItem.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="order.cart"></fmt:message>
	</h2>
</div>
<div class="cl-mcont">
	<!-- 
	<div class="header">
		<h2>
			<fmt:message key="security.role.manager"></fmt:message>
		</h2>
		<a class="btn btn-default" href="<c:url value="/security/role/input"/>"> <fmt:message key="security.role.add"></fmt:message>
		</a>
	</div>
	 -->
	<c:set var="totalFee" value="0.0" />
	<div class="block-flat">
		<c:choose>
			<c:when test="${not empty shopCartItemInfos}">
				<form action="<c:url value="/order/cartItem/toPay"/>" method="post" role="form" class="form-horizontal">
					<div class="content">
						<div id="msg"></div>
						<button type="button" class="btn btn-primary btn-sm multi-remove">
							<fmt:message key="order.cart.clear"></fmt:message>
						</button>
						<div classs="table-responsive">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th><input type="checkbox" name="check-all" checked></th>
										<th><fmt:message key='product.product'></fmt:message></th>
										<th><fmt:message key='order.cartItem.quantity'></fmt:message></th>
										<th><fmt:message key="product.productSku.price" />
										<th><fmt:message key="order.order.line.total.fee" /> <!--@generate-entity-jsp-property-desc@-->
										<th><fmt:message key="order.cartItem.move.out"></fmt:message></th>
									</tr>
								</thead>
								<tbody>
									<c:set var="totalFee" value="0.00" />
									<c:forEach items="${shopCartItemInfos}" var="shopCartItemInfo">
										<c:set var="totalFee" value="${totalFee + shopCartItemInfo.totalFeeYuan}" />
										<tr>
											<td colspan="6"><fmt:message key="setting.shop" />:${shopCartItemInfo.shop.name}</td>
										</tr>
										<c:forEach items="${shopCartItemInfo.cartItemInfos}" var="cartItemInfo">
											<tr id="${cartItemInfo.cartItem.id}">
												<td><input type="checkbox" name="cartItemId" value="${cartItemInfo.cartItem.id}" checked></td>

												<td><a href="<c:url value="/product/product/detail/${cartItemInfo.cartItem.productSku.product.id }"/>">${cartItemInfo.cartItem.productSku.product.name}</a>&nbsp;&nbsp;&nbsp;<c:forEach
														items="${cartItemInfo.skuProperties}" var="skuProperty" varStatus="status">
								  ${skuProperty.dynamicProperty.name}:${skuProperty.dynamicPropertyValue.value}
								  <c:if test="${ !status.last}">,</c:if>
													</c:forEach></td>
												<td>${cartItemInfo.cartItem.quantity}</td>
												<td><f:currency value="${cartItemInfo.cartItem.productSku.priceYuan }" /></td>
												<td><f:currency value="${cartItemInfo.cartItem.quantity * cartItemInfo.cartItem.productSku.priceYuan}" /></td>
												<!--@generate-entity-jsp-property-value@-->
												<td><a href="<c:url value="/order/cartItem/remove/${cartItemInfo.cartItem.id}"/>"> <fmt:message
															key="order.cartItem.move.out"></fmt:message>
												</a></td>
											<tr>
										</c:forEach>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="row">
							<div class="col-sm-offset-8">
								<h3>
									<fmt:message key="order.order.total" />
									:&nbsp;&nbsp;<span id="cart-item-total-fee">${totalFee}</span>&nbsp;&nbsp;
									<fmt:message key="product.product.price.unit" />
									&nbsp;&nbsp;
									<button type="submit" class="btn btn-lg btn-info">
										<fmt:message key="order.cart.balance" />
									</button>
								</h3>
							</div>
						</div>
					</div>
				</form>
			</c:when>
			<c:otherwise>
				<fmt:message key="order.cart.empty.msg" />
			</c:otherwise>
		</c:choose>
	</div>
</div>
