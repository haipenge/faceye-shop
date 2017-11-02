<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/product/product/product.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/product/product.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="product.product.manager"></fmt:message>
		<a class="btn btn-primary" href="<c:url value="/product/product/input"/>"> <fmt:message key="product.product.add"></fmt:message>
		</a>
	</h2>
</div>
<div class="cl-mcont">
	<div class="block-flat">
		<c:import url="/component/core/template/msg/msg.jsp" />
		<div class="content">
			<form action="<c:url value="/product/product/home"/>" method="post" role="form" class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<div class="col-md-2">
							<select name="categoryId" class="form-control">
								<option value=""><fmt:message key="global.select" /><fmt:message key="product.category" /></option>
								<c:forEach items="${categories}" var="category">
									<option value="${category.id}" <c:if test="${category.id eq searchParams.categoryId}"> selected</c:if>>${category.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-2">
							<label class="radio-inline"> <input type="radio" name="boolean|isOnSale" id="inlineRadio1"
								value="true" <c:if test="${searchParams.isOnSale eq 'true'}">checked</c:if>> <fmt:message key="product.product.on.sale"/>
							</label>
							<label class="radio-inline"> <input type="radio" name="boolean|isOnSale" id="inlineRadio2"
								value="false" <c:if test="${searchParams.isOnSale eq 'false' || empty searchParams.isOnSale}">checked</c:if>> <fmt:message key="product.product.close.sale"/>
							</label>
						</div>
						<div class="col-md-2">
							<input type="text" name="EQ|name" value="${searchParams.name}"
								placeholder="<fmt:message key="product.product.name"></fmt:message>" class="form-control input-sm">
						</div>
						<div class="col-md-2">
							<input type="text" name="EQ|code" value="${searchParams.code}"
								placeholder="<fmt:message key="product.product.code"></fmt:message>" class="form-control input-sm">
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
			<button class="btn btn-primary btn-sm multi-remove">
				<fmt:message key="global.remove"></fmt:message>
			</button>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='product.product.name'></fmt:message></th>
							<th><fmt:message key='product.product.code'></fmt:message></th>
							<th><fmt:message key='product.product.remark'></fmt:message></th>
							<th><fmt:message key='product.product.category'></fmt:message></th>
							<th><fmt:message key='product.product.price'></fmt:message></th>
							<th><fmt:message key='product.product.isOnSale'></fmt:message></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.view"></fmt:message></th>
							<th><fmt:message key="product.product.set.inventory" /></th>
							<th><fmt:message key="product.product.isOnSale" /></th>
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="product">
							<tr id="${product.id}">
								<td><input type="checkbox" name="check-single" value="${product.id}"></td>
								<td>${product.name}</td>
								<td>${product.code}</td>
								<td>${product.remark}</td>
								<td>${product.category.name}</td>

								<td><f:currency value="${product.priceYuan}"/></td>
								<td><f:boolean value="${product.isOnSale}" /></td>
								<!--@generate-entity-jsp-property-value@-->

								<td><a href="<c:url value="/product/product/detail/${product.id}"/>"><fmt:message
											key="global.view" /></a></td>
								<td><a href="<c:url value="/product/product/toSetInventory/${product.id}"/>"><fmt:message
											key="product.product.set.inventory" /></a></td>
								<td><a
									href="<c:url value="/product/product/setOnSaleOrNot/${product.id}?isOnSale=${product.isOnSale? 'false':'true' }"/>">
										<c:if test="${!product.isOnSale}">
											<fmt:message key="product.product.on.sale" />
										</c:if> <c:if test="${product.isOnSale}">
											<fmt:message key="product.product.close.sale" />
										</c:if>
								</a></td>
								<td><a href="<c:url value="/product/product/edit/${product.id}"/>"> <fmt:message key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/product/product/remove/${product.id}"/>"> <fmt:message key="global.remove"></fmt:message>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/product/product/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>