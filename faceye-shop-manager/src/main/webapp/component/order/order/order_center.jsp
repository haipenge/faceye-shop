<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/order/order/order.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/order/order/order.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="order.order.manager"></fmt:message>
		<!-- 
		<a class="btn btn-primary" href="<c:url value="/order/order/input"/>"> <fmt:message key="order.order.add"></fmt:message>
		</a>
		 -->
	</h2>
</div>
<div class="cl-mcont">
	<div class="block-flat">
	<c:import url="/component/core/template/msg/msg.jsp"/>
		<div class="content">
			<form action="<c:url value="/order/order/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<div class="col-md-2">
							<input type="text" name="LIKE|code" value="${searchParams.code}"
								placeholder="<fmt:message key="order.order.code"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-2">
						  <select name="EQ|status" class="form-control">
						    <option value=""><fmt:message key="order.order.status"/></option>
						    <option value="0" <c:if test="${searchParams.status eq '0' }">selected</c:if>><fmt:message key="order.order.status.0"/></option>
						    <option value="1" <c:if test="${searchParams.status eq '1' }">selected</c:if>><fmt:message key="order.order.status.1"/></option>
						    <option value="11" <c:if test="${searchParams.status eq '11' }">selected</c:if>><fmt:message key="order.order.status.11"/></option>
						    <option value="2"  <c:if test="${searchParams.status eq '2' }">selected</c:if>><fmt:message key="order.order.status.2"/></option>
						    <option value="3"  <c:if test="${searchParams.status eq '3' }">selected</c:if>><fmt:message key="order.order.status.3"/></option>
						    <option value="4"  <c:if test="${searchParams.status eq '4' }">selected</c:if>><fmt:message key="order.order.status.4"/></option>
						  </select>
							
						</div>
						<!--@generate-entity-jsp-query-detail@-->
						<div class="col-md-1">
							<button type="submit" class="btn btn-sm btn-primary">
								<fmt:message key="global.search"></fmt:message>
							</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="content">
			<!-- 
			<button class="btn btn-primary btn-sm multi-remove">
				<fmt:message key="order.order.destroy"></fmt:message>
			</button>
			 -->
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
						<!-- 
							<th><input type="checkbox" name="check-all"></th>
							 -->
							<th><fmt:message key='order.order.code'></fmt:message></th>
							<th><fmt:message key='order.order.status'></fmt:message></th>
							<th><fmt:message key='order.order.customer'></fmt:message></th>
							<th><fmt:message key="order.order.createDate"/></th>
							<th><fmt:message key='order.order.isPaid'></fmt:message></th>
							<th><fmt:message key='order.order.payWay'></fmt:message></th>
							<th><fmt:message key='order.order.payDate'></fmt:message></th>
							<th><fmt:message key='order.order.totalFee'></fmt:message></th>
							<th><fmt:message key='order.order.remark'></fmt:message></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.view" /></th>
							<th><fmt:message key="inventory.invoice"/></th>
							<th><fmt:message key="order.order.send"></fmt:message></th>
							<th><fmt:message key="order.order.destroy"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orderInfos}" var="orderInfo">
							<tr id="${orderInfo.order.id}">
							<!-- 
								<td><input type="checkbox" name="check-single" value="${orderInfo.order.id}"></td>
								 -->
								<td>${orderInfo.order.code }</td>
								<td>${orderInfo.order.statusName}</td>
								<td>${orderInfo.order.customer.name}</td>
								<td><fmt:formatDate value="${orderInfo.order.createDate}" type="date" pattern="yyyy-MM-dd HH:mm"/></td>
								<td><f:boolean value="${orderInfo.order.isPaid}" />&nbsp;${orderInfo.order.statusName}</td>
								<td>${orderInfo.order.payWay}</td>
								<td><fmt:formatDate value="${orderInfo.order.payDate}" pattern="yyyy-MM-dd HH:mm" /></td>
								<td><f:currency value="${orderInfo.order.totalFeeYuan }"/></td>
								<td>${orderInfo.order.remark}</td>
								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/order/order/detail/${orderInfo.order.id}"/>"><fmt:message key="global.view" /></a></td>
								<td>
								   <c:if test="${orderInfo.order.isPaid }">
								    <a href="<c:url value="/inventory/invoice/order/${orderInfo.order.id }"/>"><fmt:message key="inventory.invoice"/></a>
								   </c:if>
								</td>
								<td>
								<c:if test="${orderInfo.order.status eq 1 }">
								<a href="<c:url value="/order/order/edit/${orderInfo.order.id}"/>"> <fmt:message key="order.order.send"></fmt:message>
								</a>
								</c:if>
								</td>
								<td><c:if test="${orderInfo.order.status eq 0 }">
										<a href="<c:url value="/order/order/remove/${orderInfo.order.id}"/>"> <fmt:message
												key="order.order.destroy"></fmt:message>
										</a>
									</c:if></td>
							</tr>
							<!-- Order Item -->
							<tr>
								<td><fmt:message key="order.item" /></td>
								<td colspan="12">
									<table class="table table-bordered">
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
													<td><f:currency value="${itemInfo.item.priceYuan}"/></td>
													<td>${itemInfo.item.quantity}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
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