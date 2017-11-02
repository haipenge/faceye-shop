<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/order/order/order.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/order/order/order.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="order.order.pay"></fmt:message>
	</h2>
</div>
<div class="cl-mcont">
	<div class="block-flat">
		<div class="row">

			<p style="padding-left:15px;">
				<fmt:message key="order.order.code"></fmt:message>
				:${order.code} &nbsp;&nbsp;
				<fmt:message key="order.order.totalFee"></fmt:message>
				:
				<f:currency value="${order.totalFeeYuan}" />
			</p>
		</div>
		<div class="row">
			<div class="col-md-4">
				<h4><fmt:message key="shop.web.pay.weixin.pay"/></h4>
				<p><fmt:message key="shop.web.pay.weixin.tips"/></p>
				<div class="content">
					<c:choose>
						<c:when test="${empty unifiedOrderResponse.codeUrl}">
							<fmt:message key="shop.web.pay.weixin.unuse" />
						</c:when>
						<c:otherwise>
							<img src="<c:url value="/weixin/api/generateQRCode?source=${unifiedOrderResponse.codeUrl}"/>">
						</c:otherwise>
					</c:choose>
				</div>
				<p>Debug :${unifiedOrderResponse.codeUrl}</p>
			</div>
			<div class="col-md-4">
				<h4>Ali Pay</h4>
				<p>User zhi fu bao</p>
				<div class="content"></div>
			</div>
		</div>
	</div>
</div>