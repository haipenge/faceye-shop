<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/order/order/order.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/order/order/order.js"/>"></script>

<div class="page-head">
	<h2>
		<fmt:message key="order.order"></fmt:message>

	</h2>
</div>
<div class="cl-mcont">
	<div class="block-flat">

		<div class="content">
			<div id="msg"></div>

			<a href="<c:url value="/order/order/home?EQ|status=1"/>" class="btn btn-success"><fmt:message
					key="order.order.status.1" /></a> <a href="<c:url value="/order/order/home?EQ|status=0"/>" class="btn btn-danger"><fmt:message
					key="order.order.status.0" /></a>
			<div classs="table-responsive">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th><fmt:message key='order.order.shop'></fmt:message></th>
							<th><fmt:message key='order.order.code'></fmt:message></th>
							<th><fmt:message key='order.order.status'></fmt:message></th>
							<th><fmt:message key="order.order.createDate" /></th>
							<th><fmt:message key='order.order.isPaid'></fmt:message></th>
							<th><fmt:message key="order.order.status"/></th>
							<!-- 
							<th><fmt:message key='order.order.payWay'></fmt:message></th>
							 -->
							<th><fmt:message key='order.order.payDate'></fmt:message></th>
							<th><fmt:message key='order.order.totalFee'></fmt:message></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.view" /> <c:if test="${orderInfo.order.status eq 0 }">
									<fmt:message key="order.order.pay"></fmt:message>
									<fmt:message key="order.order.destroy"></fmt:message>
								</c:if></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orderInfos}" var="orderInfo">
							<tr id="${orderInfo.order.id}">
								<td>${orderInfo.order.shop.name}</td>
								<td>${orderInfo.order.code}</td>
								<td>${orderInfo.order.statusName}</td>
								<td><fmt:formatDate value="${orderInfo.order.createDate}" pattern="yyyy-MM-dd HH:MM" /></td>
								<td><f:boolean value="${orderInfo.order.isPaid}" show="已支付|未支付" />&nbsp;</td>
								<td>${orderInfo.order.statusName}</td>
								<!-- 
								<td>${orderInfo.order.payWayName}</td>
								 -->
								<td><fmt:formatDate value="${orderInfo.order.payDate}" pattern="yyyy-MM-dd HH:MM" /></td>
								<td><f:currency value="${orderInfo.order.totalFeeYuan}"/></td>

								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/order/order/detail/${orderInfo.order.id}.html"/>"><fmt:message
											key="global.view" /></a> <c:if test="${orderInfo.order.status eq 0 }">
										
										|
										<a href="<c:url value="/order/order/remove/${orderInfo.order.id}"/>"> <fmt:message
												key="order.order.destroy"></fmt:message>
										</a>
									</c:if>
							</tr>
							<!-- Order Item -->
							<tr>
								<td><fmt:message key="order.item" /></td>
								<td colspan="12">
									<table class="table table-bordered table-striped">
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
													<td><a href="<c:url value="/product/product/detail/${itemInfo.item.productSku.product.id}"/>">${itemInfo.item.productSku.product.name}</a></td>
													
													<td><c:forEach items="${itemInfo.skuProperties}" var="skuProperty" varStatus="status">
                                                       ${skuProperty.dynamicProperty.name}:${skuProperty.dynamicPropertyValue.value}
                                                       <c:if test="${ !status.last}">
                                                         ,
                                                       </c:if>
														</c:forEach></td>
													<td><f:currency value="${itemInfo.item.priceYuan}"/></td>
													<td>${itemInfo.item.quantity}</td>
													<td><f:currency value="${itemInfo.item.priceYuan * itemInfo.item.quantity}"/></td>
												</tr>

											</c:forEach>
										</tbody>
									</table>
									<p>
										<fmt:message key="customer.address" />
										:
										${orderInfo.order.address.receiveUserName},${orderInfo.order.address.mobile},${orderInfo.order.address.detail}
										<c:if test="${not orderInfo.order.isPaid}">
										<a  class ="btn btn-success pull-right" href="<c:url value="/order/order/toPay?orderId=${orderInfo.order.id }"/>"> <fmt:message
												key="order.order.pay"></fmt:message></a>
										</c:if>
									</p>
								</td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/order/order/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>