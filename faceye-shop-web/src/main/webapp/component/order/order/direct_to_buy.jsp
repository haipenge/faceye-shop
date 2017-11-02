<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/order/cartItem/cartItem.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/order/cartItem/cartItem.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/customer/address/address.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="order.order.confirm"></fmt:message>
	</h2>
</div>
<div class="cl-mcont">
	<c:import url="/component/core/template/msg/msg.jsp" />
	<!-- 
	<div class="header">
		<h2>
			<fmt:message key="security.role.manager"></fmt:message>
		</h2>
		<a class="btn btn-default" href="<c:url value="/security/role/input"/>"> <fmt:message key="security.role.add"></fmt:message>
		</a>
	</div>
	 -->
	<c:set var="totalFee" value="0.0" />
	<div class="block-flat">
		<h3>
			<fmt:message key="customer.address" />
			&nbsp;&nbsp;<small><a href="#" id="to-add-address"><fmt:message key="customer.address.add" /></a></small>
		</h3>

		<div class="content" id="address-add-container">
			<div class="header">
				<h3>
					<fmt:message key="customer.address.add" />
				</h3>
			</div>
			<div class="content">
			    <form action="#" method="post" role="form" class="form-horizontal">
					<input type="hidden" name="id">
					<fieldset>
						<div class="form-group">
							<label class="col-md-1 control-label" for="receiveUserName"> <spring:message
									code="customer.address.receiveUserName" />
							</label>
							<div class="col-md-5">
							   <input type="text" name="receiveUserName" class="form-control">
							</div>

						</div>
						<div class="form-group">
							<label class="col-md-1 control-label" for="mobile"> <spring:message code="customer.address.mobile" />
							</label>
							<div class="col-md-5">
							    <input type="text" name="mobile" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-1 control-label" for="detail"> <spring:message code="customer.address.detail" />
							</label>
							<div class="col-md-10">
							    <input type="text" name="detail" class="form-control">
							</div>
						</div>

						<!--@generate-entity-jsp-property-update@-->
						<div class="form-group">
							<div class="col-md-offset-1 col-md-10">
								<button type="button" id="address-save" class="btn btn-primary">
									<fmt:message key="global.submit.save"></fmt:message>
								</button>
								<button type="button" id="address-save-cancel" class="btn btn-info">
									<fmt:message key="global.cancel" />
								</button>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
		<!-- end add address -->
				<form action="<c:url value="/order/order/buy"/>" method="post" role="form" class="form-horizontal"
					id="submit-cart-item-2-order-form">
					<div class="content">
						<div class="table-responsive">
							<table class="table table-bordered" id="address-table">
								<thead>
									<tr>
										<th></th>
										<th><fmt:message key="customer.address.receiveUserName" /></th>
										<th><fmt:message key="customer.address.mobile" /></th>
										<th><fmt:message key="customer.address.detail" /></th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${empty addresses }">
										<tr>
											<td colspan="5" class="text-center"><fmt:message key="customer.address.empty.tip" /></td>
										</tr>
									</c:if>
									<c:forEach items="${addresses}" var="address">
										<tr id="${address.id}">
											<td><input type="radio" name="addressId" value="${address.id}"
												<c:if test="${address.isDefault}">checked</c:if>></td>
											<td>${address.receiveUserName}</td>
											<td>${address.mobile }</td>
											<td>${address.detail}</td>
											<td><a href="#" class="set-default-address"><fmt:message key="customer.address.set.default" /></a>|<a
												href="#" class="remove-address"><fmt:message key="global.remove" /></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="content">
						<div classs="table-responsive">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th><fmt:message key='product.product'></fmt:message></th>
										<th><fmt:message key='order.cartItem.quantity'></fmt:message></th>
										<th><fmt:message key="product.productSku.price" />
										<th><fmt:message key="order.order.line.total.fee" /> <!--@generate-entity-jsp-property-desc@-->
									</tr>
								</thead>
								<tbody>
									<tr id="${skuInfo.productSku.id}">
										<input type="hidden" name="productSkuId" value="${skuInfo.productSku.id}">
										<td>${skuInfo.productSku.product.name}&nbsp;&nbsp;&nbsp;<c:forEach items="${skuInfo.skuProperties}"
												var="skuProperty" varStatus="status">
								  ${skuProperty.dynamicProperty.name}:${skuProperty.dynamicPropertyValue.value}
								  <c:if test="${ !status.last}">,</c:if>
											</c:forEach></td>
										<td><input type="text" name="quantity" class="form-control" value="${quantity}"></td>
										<td><f:currency value="${skuInfo.productSku.priceYuan }" /></td>
										<td><f:currency value="${quantity * skuInfo.productSku.priceYuan}" /></td>
										<!--@generate-entity-jsp-property-value@-->
									<tr>
								</tbody>
							</table>
						</div>
						<div class="row">
							<div class="col-sm-offset-8">
								<h3>
									<fmt:message key="order.order.total" />
									:&nbsp;&nbsp;
							        <f:currency value="${quantity * skuInfo.productSku.priceYuan}" />
									&nbsp;&nbsp;
									<button type="button" class="btn btn-lg btn-info" id="submit-cart-items">
										<fmt:message key="global.submit" />
									</button>
								</h3>
							</div>
						</div>

					</div>
				</form>
	</div>
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
