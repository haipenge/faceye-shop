<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/order/cartItem/cartItem.css"/>" />
<script type="text/javascript" type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="<c:url value="/js/component/order/cartItem/cartItem.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/customer/address/address.js"/>"></script>
<script type="text/javascript" type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="<c:url value="/js/component/weixin/js-sdk/weixin.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/order/order/order.js"/>"></script>
<c:import url="/component/core/template/msg/msg.jsp" />
<div class="content bg-white" id="address-add-container">
	<h6>
		<fmt:message key="customer.address.add" />
	</h6>
	<div class="content">
		<form action="#" class="form-horizontal" role="form" method="post">
			<input type="hidden" name="id" value="${address.id}">
			<fieldset>
				<div class="form-group">
					<input type="text" name="receiveUserName" class="form-control"
						placeholder="<spring:message
							code="customer.address.receiveUserName" />">

				</div>
				<div class="form-group">
					<input type="text" class="form-control" name="mobile" placeholder="<fmt:message key="customer.address.mobile"/>">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" name="detail"
						placeholder="<spring:message code="customer.address.detail" />">
				</div>
				<!--@generate-entity-jsp-property-update@-->
				<div class="form-group">
					<button type="button" id="address-save" class="btn btn-primary">
						<fmt:message key="global.submit.save"></fmt:message>
					</button>
					<button type="button" id="address-save-cancel" class="btn btn-info">
						<fmt:message key="global.cancel" />
					</button>
				</div>
			</fieldset>
		</form>
	</div>
</div>
<form action="<c:url value="/order/order/orderConfirm"/>" method="post" role="form" class="form-horizontal"
	id="submit-cart-item-2-order-form">
	<div class="card card-block bg-white">
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
			<c:if test="${not empty addresses}">
				<div class="content text-right">
					<span id="show-toggle"><fmt:message key="global.show" /></span>
				</div>
			</c:if>
		</div>
		<hr>
		<c:set var="tatalFee" value="0.00" />
		<c:forEach items="${orderConfirmInfo.skuConfirmInfos}" var="skuConfirmInfo" varStatus="status">
			<c:set var="totalFee" value="${totalFee + skuConfirmInfo.skuInfo.productSku.priceYuan * skuConfirmInfo.quantity}" />
			<h6>${skuConfirmInfo.skuInfo.productSku.product.shop.name}</h6>

			<div class="row">
				<input type="hidden" name="productSkuId" value="${skuConfirmInfo.skuInfo.productSku.id}"> <input
					type="hidden" name="quantity" value="${skuConfirmInfo.skuInfo.productSku.id}_${skuConfirmInfo.quantity}">
				<div class="col-xs-3 col-md-3 col-lg-3">
					<c:forEach items="${skuConfirmInfo.images}" var="image">
						<c:if test="${image.isDefault}">
							<img data-src="<c:url value="/UploadServlet?getfile=${image.filename}"/>"
								src="<c:url value="/UploadServlet?getfile=${image.filename}"/>" alt="Order Image" class="img-responsive">
						</c:if>
					</c:forEach>
				</div>
				<div class="col-xs-9 col-md-9 col-lg-9">
					<a href="<c:url value="/product/product/detail/${skuConfirmInfo.skuInfo.productSku.product.id }"/>">${skuConfirmInfo.skuInfo.productSku.product.name}</a><br>
					<c:forEach items="${skuConfirmInfo.skuInfo.skuProperties}" var="skuProperty" varStatus="status">
								  ${skuProperty.dynamicProperty.name}:${skuProperty.dynamicPropertyValue.value}
								  <c:if test="${ !status.last}">,</c:if>
					</c:forEach>
					<br>
					<f:unit value="${skuConfirmInfo.quantity}" />
					*
					<f:currency value="${skuConfirmInfo.skuInfo.productSku.priceYuan }" />
					=
					<f:currency value="${skuConfirmInfo.quantity * skuConfirmInfo.skuInfo.productSku.priceYuan}" />
					<br>
				</div>
			</div>
			<c:if test="${!status.last}">
				<hr>
			</c:if>
		</c:forEach>

		<div class="row">
			<h4>
				<fmt:message key="order.order.total" />
				:&nbsp;&nbsp;
				<f:currency value="${totalFee}" />
				&nbsp;&nbsp;
				<button type="button" class="btn btn-sm btn-success-outline pull-right" id="btn-weixin-pay">
					<fmt:message key="shop.web.pay.weixin.pay" />
				</button>
			</h4>
		</div>
	</div>
</form>

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
	/* wx.config({
		debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		appId : 'wxa278a6279989a438', // 必填，公众号的唯一标识
		timestamp : ${weixinConfigRequest.timestamp}, // 必填，生成签名的时间戳
		nonceStr :  ${weixinConfigRequest.noncestr}, // 必填，生成签名的随机串
		signature : ${weixinConfigRequest.signature},// 必填，签名，见附录1
		jsApiList : [ 'chooseWXPay' ]
	// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	}); */
	<!--
//-->
</script>