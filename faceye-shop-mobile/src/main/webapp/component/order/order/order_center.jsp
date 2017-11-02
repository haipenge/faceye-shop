<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/order/order/order.css"/>" />
<script type="text/javascript" type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="<c:url value="/js/component/weixin/js-sdk/weixin.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/order/order/order.js"/>"></script>
<c:forEach items="${orderInfos}" var="orderInfo">
	<div class="card card-block bg-white m-t-0">
		<div class="row">
			<div class="col-xs-6 col-sm-6 col-md-4 col-lg-4">
				<fmt:message key='order.order.status' />
				:${orderInfo.order.statusName}&nbsp;
				<f:boolean value="${orderInfo.order.isPaid}" show="已支付|未支付" />
				<br>
				<fmt:message key="order.order.totalFee" />
				:
				<f:currency value="${orderInfo.order.totalFeeYuan}" />
			</div>
			<div class="col-xs-6 col-sm-6 col-md-8 col-lg-8">
				<a href="<c:url value="/order/order/detail?id=${orderInfo.order.id }"/>"><fmt:message key="global.view" /></a>
				<c:if test="${not orderInfo.order.isPaid and orderInfo.order.status != '4'}">
					<a href="<c:url value="/order/order/remove/${orderInfo.order.id}"/>"> <fmt:message key="order.order.destroy"></fmt:message>
					</a>
				</c:if>
				<c:if test="${not orderInfo.order.isPaid and orderInfo.order.status=='0'}">
					<button type="button" class="btn btn-sm btn-success-outline pull-right list-pay" id="${orderInfo.order.id }">
						<fmt:message key="order.order.pay"></fmt:message>
					</button>
				</c:if>
			</div>
		</div>
		<hr>
		<p>
			<b>${orderInfo.order.shop.name}</b>
		</p>
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
					<div class="content">
						<a href="<c:url value="/product/product/detail/${itemInfo.item.productSku.product.id}"/>">${itemInfo.item.productSku.product.name}</a>
						<br>
						<c:forEach items="${itemInfo.skuProperties}" var="skuProperty" varStatus="status">
                                                       ${skuProperty.dynamicProperty.name}:${skuProperty.dynamicPropertyValue.value}
                                                       <c:if test="${ !status.last}">
                                                         ,
                                                       </c:if>
						</c:forEach>
						<br> ${itemInfo.item.quantity} *
						<f:currency value="${itemInfo.item.priceYuan}" />
						=
						<f:currency value="${itemInfo.item.priceYuan * itemInfo.item.quantity}" />
					</div>
				</div>
			</div>
		</c:forEach>
		<hr>
		<fmt:message key="customer.address" />
		: ${orderInfo.order.address.receiveUserName},${orderInfo.order.address.mobile},${orderInfo.order.address.detail}
	</div>
</c:forEach>
<f:page page="${page}" url="/order/order/home" params="<%=request.getParameterMap()%>" />
<c:if test="${empty orderInfos}">
	<div class="card card-block bg-white">
		<div class="card-text m-a">
			<fmt:message key="order.order.empty" />
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
</c:if>