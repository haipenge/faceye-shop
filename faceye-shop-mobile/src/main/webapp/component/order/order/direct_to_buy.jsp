<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/order/cartItem/cartItem.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/order/cartItem/cartItem.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/customer/address/address.js"/>"></script>
<!-- Buy Form -->
<div class="card card-block bg-white">
	<form action="<c:url value="/order/order/orderConfirm"/>" method="post" role="form" class="form-horizontal"
		id="submit-cart-item-2-order-form">
		<h6>
			<fmt:message key="customer.address" />
			<small><a href="#" id="to-add-address" class="pull-right"><fmt:message key="customer.address.add" /></a></small>
		</h6>
		<div id="address">
			<c:if test="${not empty addresses}">
				<c:forEach items="${addresses}" var="address">
					<div class="row" id="${address.id}">
						<div class="col-xs-2 col-md-1 col-lg-1">
							<input type="radio" name="addressId" value="${address.id}" <c:if test="${address.isDefault}">checked</c:if>>
						</div>
						<div class="col-xs-10 col-md-10 col-lg-10">${address.receiveUserName},${address.mobile},${address.detail}</div>
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${empty addresses }">
				<fmt:message key="customer.address.empty.tip" />
			</c:if>
			<c:if test="${not empty addresses and fn:length(addresses) gt 1}">
				<div class="content text-right">
					<span id="show-toggle"><fmt:message key="global.show" /></span>
				</div>
			</c:if>
		</div>
		<hr>
		<c:forEach items="${orderConfirmInfo.skuConfirmInfos}" var="skuConfirmInfo">
			<c:set var="tatal" value="0" />
			<div class="row">
				<input type="hidden" name="productSkuId" value="${skuConfirmInfo.skuInfo.productSku.id}">
				<c:set var="total" value="${total + skuConfirmInfo.skuInfo.productSku.priceYuan * skuConfirmInfo.quantity }" />
				<p>${skuConfirmInfo.skuInfo.productSku.product.name}&nbsp;&nbsp;<c:forEach
						items="${skuConfirmInfo.skuInfo.skuProperties}" var="skuProperty" varStatus="status">
								  ${skuProperty.dynamicProperty.name}:${skuProperty.dynamicPropertyValue.value}
								  <c:if test="${ !status.last}">,</c:if>
					</c:forEach>
				</p>
				<p>
					<fmt:message key="product.productSku.price" />
					:
					<f:currency value="${skuConfirmInfo.skuInfo.productSku.priceYuan }" />
				</p>
				<p>
					<fmt:message key="order.order.line.total.fee" />
					:
					<f:currency value="${skuConfirmInfo.quantity * skuConfirmInfo.skuInfo.productSku.priceYuan}" />
				<div class="form-group">
					<input type="text" name="quantity" class="form-control" value="${skuConfirmInfo.quantity}">
				</div>
			</div>

		</c:forEach>


		<div class="row">
			<div class="content">
				<fmt:message key="order.order.total" />
				:&nbsp;&nbsp;
				<f:currency value="${total}" />
			</div>

			<div class="content">

				&nbsp;&nbsp;
				<button type="button" class="btn btn-block btn-success" id="submit-cart-items">
					<fmt:message key="shop.web.pay.weixin.pay" />
				</button>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$('#address-add-container').hide();
		$('#to-add-address').click(function() {
			$('#address-add-container').show();
			return false;
		});

		$('#address-save-cancel').click(function() {
			$('#address-add-container').hide();
		});
	});
	<!--
//-->
</script>
