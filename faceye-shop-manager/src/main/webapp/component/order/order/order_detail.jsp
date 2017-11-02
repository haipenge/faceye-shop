<%@ include file="/component/core/taglib/taglib.jsp"%>
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
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key="order.order.code"></fmt:message></td>
					<td>${orderInfo.order.code}</td>
				</tr>
				<tr>
					<td><fmt:message key="order.order.status"></fmt:message></td>
					<td>${orderInfo.order.statusName}</td>
				</tr>
				<tr>
					<td><fmt:message key="order.order.shop"></fmt:message></td>
					<td>${orderInfo.order.shop.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="order.order.customer"></fmt:message></td>
					<td>${orderInfo.order.customer.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="order.order.isPaid"></fmt:message></td>
					<td><f:boolean value="${orderInfo.order.isPaid}"/></td>
				</tr>
				<tr>
					<td><fmt:message key="order.order.payWay"></fmt:message></td>
					<td>${orderInfo.order.payWay}</td>
				</tr>
				<tr>
					<td><fmt:message key="order.order.payDate"></fmt:message></td>
					<td><fmt:formatDate value="${orderInfo.order.payDate}" type="date" pattern="yyyy-MM-dd HH:mm"/></td>
				</tr>
				<tr>
					<td><fmt:message key="order.order.totalFee"></fmt:message></td>
					<td><fmt:message key="product.product.price.rmb"/>&nbsp;&nbsp;${orderInfo.order.totalFeeYuan}&nbsp;&nbsp;<fmt:message key="product.product.price.unit"/></td>
				</tr>
				<tr>
					<td><fmt:message key="order.order.remark"></fmt:message></td>
					<td>${orderInfo.order.remark}</td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
	<h3>
		<fmt:message key="order.item" />
	</h3>
	<div class="content">
		<div classs="table-responsive">
			<table class="table table-bordered">
				<thead>
					<th><fmt:message key="product.product" /></th>
					<th><fmt:message key="product.productSku" /></th>
					<th><fmt:message key="product.product.price" /></th>
					<th><fmt:message key="order.item.quantity" /></th>
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
							<td><fmt:message key="product.product.price.rmb"/>&nbsp;&nbsp;${itemInfo.item.priceYuan}&nbsp;&nbsp;<fmt:message key="product.product.price.unit"/></td>
							<td>${itemInfo.item.quantity}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
