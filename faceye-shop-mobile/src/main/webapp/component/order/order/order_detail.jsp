<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/order/order/order.css"/>" />
<script type="text/javascript" type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="<c:url value="/js/component/weixin/js-sdk/weixin.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/order/order/order.js"/>"></script>
<div class="card card-block bg-white">
	<fmt:message key="order.order.code" />
	:${order.code}<br>
	<fmt:message key="order.order.status"></fmt:message>
	:${order.statusName} &nbsp;
	<f:boolean value="${order.isPaid}" show="已支付|未支付" />
	<br>
	<fmt:message key="order.order.totalFee" />
	:
	<f:currency value="${order.totalFeeYuan}" />
	<br>
	<hr>
	<b>${order.shop.name}</b><br>
	<c:forEach items="${orderInfo.itemInfos}" var="itemInfo">
		<div class="row">
			<div class="col-xs-3 col-md-3 col-lg-2">
				<c:forEach items="${itemInfo.images}" var="image">
					<c:if test="${image.isDefault}">
						<a href="<c:url value="/product/product/detail/${itemInfo.item.productSku.product.id}"/>"></a>
						<img data-src="<c:url value="/UploadServlet?getfile=${image.filename}"/>"
							src="<c:url value="/UploadServlet?getfile=${image.filename}"/>" alt="Order Image" class="img-responsive">
						</a>
					</c:if>
				</c:forEach>
			</div>
			<div class="col-xs-9 col-md-9 col-lg-10">

				${itemInfo.item.productSku.product.name}<br>
				<c:forEach items="${itemInfo.skuProperties}" var="skuProperty" varStatus="status">
                                                       ${skuProperty.dynamicProperty.name}:${skuProperty.dynamicPropertyValue.value}
                                                       <c:if test="${ !status.last}">
                                                         ,
                                                       </c:if>
				</c:forEach>
				<br>
				<fmt:message key="order.order.line.total.fee" />
				:
				<f:unit value="${itemInfo.item.quantity}" />
				*
				<f:currency value="${itemInfo.item.priceYuan}" />
				=
				<f:currency value="${itemInfo.item.quantity * itemInfo.item.priceYuan}" />
			</div>
		</div>
	</c:forEach>
	<hr>
	<div class="content">
		<fmt:message key="customer.address" />
		:${orderInfo.order.address.receiveUserName},${orderInfo.order.address.mobile},${orderInfo.order.address.detail}<br>
		<c:if test="${!orderInfo.order.isPaid }">
			<p>
				<fmt:message key="order.order.total" />
				:&nbsp;&nbsp;
				<f:currency value="${orderInfo.order.totalFeeYuan}" />
				&nbsp;&nbsp;
				<button type="button" id="${orderInfo.order.id }" class="btn btn-sm btn-success-outline detail-pay">
					<fmt:message key="shop.web.pay.weixin.pay" />
				</button>
			</p>
		</c:if>
	</div>
</div>