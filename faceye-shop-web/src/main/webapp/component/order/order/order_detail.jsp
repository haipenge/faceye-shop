<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/order/order/order.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/order/order/order.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="order.order.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
				<tr>
					<td class="table-title-col width-p-15"><fmt:message key="order.order.code"></fmt:message></td>
					<td class="width-p-35">${order.code}</td>

					<td class="table-title-col width-p-15"><fmt:message key="order.order.status"></fmt:message></td>
					<td>${order.statusName}&nbsp;&nbsp;<f:boolean value="${order.isPaid}" show="已支付|未支付" /></td>
				</tr>
				<tr>
					<td class="table-title-col"><fmt:message key="order.order.shop"></fmt:message></td>
					<td >${order.shop.name}</td>
					<td class="table-title-col"><fmt:message key="order.order.totalFee"></fmt:message></td>
					<td><f:currency value="${order.totalFeeYuan}" /></td>
				</tr>
				<!-- 
				<tr>
					<td class="table-title-col"><fmt:message key="order.order.payDate"></fmt:message></td>
					<td><fmt:formatDate value="${order.payDate}" pattern="yyyy-MM-dd HH:MM" /></td>
				</tr>
				 -->
				<tr>
					<td class="table-title-col"><fmt:message key="order.order.remark"></fmt:message></td>
					<td colspan="3">${order.remark}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
		<h3>
			<fmt:message key="product.product" />
		</h3>
		<div class="content table-responsive">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th><fmt:message key="product.product" /></th>
						<th><fmt:message key="product.productSku" /></th>
						<th><fmt:message key="product.product.price" /></th>
						<th><fmt:message key="product.productSku.count" /></th>
						<th><fmt:message key="order.order.line.total.fee" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${orderInfo.itemInfos}" var="itemInfo">
						<tr>
							<td>${itemInfo.item.productSku.product.name}</td>
							<td><c:forEach items="${itemInfo.skuProperties}" var="skuProperty" varStatus="status">
                                                       ${skuProperty.dynamicProperty.name}:${skuProperty.dynamicPropertyValue.value}
                                                       <c:if test="${ !status.last}">
                                                         ,
                                                       </c:if>
								</c:forEach></td>
							<td><f:currency value="${itemInfo.item.priceYuan}" /></td>
							<td>${itemInfo.item.quantity}</td>
							<td><f:currency value="${itemInfo.item.quantity * itemInfo.item.priceYuan}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<h3>
			<fmt:message key="customer.address" />
		</h3>
		<div class="content">
			${orderInfo.order.address.receiveUserName},${orderInfo.order.address.mobile},${orderInfo.order.address.detail}</div>
		<c:if test="${!orderInfo.order.isPaid }">
			<div class="row">
				<div class="col-sm-offset-2">
					<h3>
						<fmt:message key="order.order.total" />
						:&nbsp;&nbsp;
						<f:currency value="${orderInfo.order.totalFeeYuan}" />
						&nbsp;&nbsp; <a href="<c:url value="/order/order/toPay?orderId=${orderInfo.order.id }"/>"
							class="btn btn-lg btn-success"><fmt:message key="shop.web.pay.weixin.pay" /></a> &nbsp;&nbsp; <a
							href="<c:url value="/order/order/toPay?orderId=${orderInfo.order.id }"/>" class="btn btn-lg btn-danger"><fmt:message
								key="shop.web.pay.ali.pay" /></a>
					</h3>
				</div>
			</div>
		</c:if>
	</div>
</div>