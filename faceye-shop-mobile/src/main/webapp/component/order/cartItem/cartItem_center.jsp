<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/order/cartItem/cartItem.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/order/cartItem/cartItem.js"/>"></script>


<c:choose>
	<c:when test="${not empty shopCartItemInfos}">
		<div class="row">
			<div class="col-xs-10 col-md-10 cl-lg-10"></div>
			<div class="col-xs-2 col-md-2 col-lg-2">
				<button type="button" class="btn btn-sm btn-primary-outline pull-right" id="edit-cart-item">
					<fmt:message key="global.edit" />
				</button>
			</div>
		</div>
		<c:set var="totalFee" value="0.00" />
		<form action="<c:url value="/order/order/confirmCart?showwxpaytitle=1"/>" method="post" role="form"
			class="form-horizontal">
			<c:forEach items="${shopCartItemInfos}" var="shopCartItemInfo">
				<c:set var="totalFee" value="${totalFee + shopCartItemInfo.totalFeeYuan}" />

				<div class="list-group">
					<c:forEach items="${shopCartItemInfo.cartItemInfos}" var="cartItemInfo">
						<div class="list-group-item" id="${cartItemInfo.cartItem.id}">
							<div class="row">
								<div class="col-xs-2 col-sm-1 col-lg-1">
									<input type="checkbox" name="cartItemId" value="${cartItemInfo.cartItem.id}" checked>
								</div>
								<div class="col-xs-3 col-sm-3 col-lg-2">
									<c:forEach items="${cartItemInfo.images}" var="image">
										<c:if test="${image.isDefault}">
											<img data-src="<c:url value="/UploadServlet?getfile=${image.filename}"/>"
												src="<c:url value="/UploadServlet?getfile=${image.filename}"/>" alt="Order Image" class="img-responsive">
										</c:if>
									</c:forEach>
								</div>
								<div class="col-xs-7 col-sm-8 col-lg-9">
									<p>
										<a href="<c:url value="/product/product/detail/${cartItemInfo.cartItem.productSku.product.id}"/>">${cartItemInfo.cartItem.productSku.product.name}</a>
									</p>
									<p>
										<c:forEach items="${cartItemInfo.skuProperties}" var="skuProperty" varStatus="status">
								  ${skuProperty.dynamicProperty.name}:${skuProperty.dynamicPropertyValue.value}
								  <c:if test="${ !status.last}">,</c:if>
										</c:forEach>
									</p>
									<p>
										${cartItemInfo.cartItem.quantity}&nbsp;&nbsp; *
										<f:currency value="${cartItemInfo.cartItem.productSku.priceYuan }" />
										=
										<f:currency value="${cartItemInfo.cartItem.quantity * cartItemInfo.cartItem.productSku.priceYuan}" />
									</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:forEach>
			
			<div class="card card-block m-t bg-white" id="submit-cartItem-container">
				<h4>
					<fmt:message key="order.order.total" />
					:&nbsp;&nbsp;<span id="cart-item-total-fee">${totalFee}</span>&nbsp;&nbsp;
					<fmt:message key="product.product.price.unit" />
					&nbsp;&nbsp;

					<button type="submit" class="btn btn-success-outline pull-right">
						<fmt:message key="order.cart.balance" />
					</button>
				</h4>
			</div>
			<div class="card m-t card-block bg-white" id="edit-cartItem-container">
				<div id="msg"></div>
				<div class="row">
					<div class="col-xs-6 col-sm-6">
						<div class="checkbox">
							<label> <input type="checkbox" name="check-all"> <fmt:message key="global.checkall" />
							</label>
						</div>
					</div>
					<div class="col-xs-6 col-sm-6">
						<button type="button" class="btn btn-danger-outline pull-right multi-remove">
							<fmt:message key="global.remove" />
						</button>
					</div>
				</div>

			</div>
		</form>
	</c:when>
	<c:otherwise>
		<div class="card m-t card-block bg-white">
			<div class="card-text m-a">
				<fmt:message key="order.cart.empty.msg" />
			</div>
			<div class="text-center m-a">
				<div class="row">
					<div class="col-xs-6 col-md-6">
						<a href="<c:url value="/customer/customer/me"/>" class="btn btn-block btn-success-outline"><fmt:message
								key="customer.customer.me" /></a>
					</div>
					<div class="col-xs-6 col-md-6">
						<a href="<c:url value="/setting/shop/home"/>" class="btn btn-block btn-warning-outline"><fmt:message
								key="shop.mobile.go" /></a>
					</div>
				</div>

			</div>
		</div>
	</c:otherwise>
</c:choose>