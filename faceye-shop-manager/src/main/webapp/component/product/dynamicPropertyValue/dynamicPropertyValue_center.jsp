<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/product/dynamicPropertyValue/dynamicPropertyValue.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/dynamicPropertyValue/dynamicPropertyValue.js"/>"></script>
<div class="page-head">
	<h2>
		<fmt:message key="product.dynamicPropertyValue.manager"></fmt:message>
		<a class="btn btn-primary"
			href="<c:url value="/product/dynamicPropertyValue/input?dynamicPropertyId=${dynamicProperty.id}"/>"> <fmt:message
				key="product.dynamicPropertyValue.add"></fmt:message>
		</a>
	</h2>
</div>
<div class="cl-mcont">
	<div class="block-flat">
		<c:import url="/component/core/template/msg/msg.jsp" />
		<div class="content">
			<div id="msg"></div>
			<button class="btn btn-primary btn-sm multi-remove">
				<fmt:message key="global.remove"></fmt:message>
			</button>
			<div classs="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><input type="checkbox" name="check-all"></th>
							<th><fmt:message key='product.dynamicPropertyValue.dynamicProperty'></fmt:message></th>
							<th><fmt:message key='product.dynamicPropertyValue.value'></fmt:message></th>

							<th><fmt:message key='product.dynamicPropertyValue.shop'></fmt:message></th>
							<!--@generate-entity-jsp-property-desc@-->
							<th><fmt:message key="global.edit"></fmt:message></th>
							<th><fmt:message key="global.remove"></fmt:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="dynamicPropertyValue">
							<tr id="${dynamicPropertyValue.id}">
								<td><input type="checkbox" name="check-single" value="${dynamicPropertyValue.id}"></td>
								<td>${dynamicPropertyValue.dynamicProperty.name}</td>
								<td>${dynamicPropertyValue.value}</td>
								<td>${dynamicPropertyValue.shop.name}</td>

								<!--@generate-entity-jsp-property-value@-->
								<td><a href="<c:url value="/product/dynamicPropertyValue/edit/${dynamicPropertyValue.id}"/>"> <fmt:message
											key="global.edit"></fmt:message>
								</a></td>
								<td><a href="<c:url value="/product/dynamicPropertyValue/remove/${dynamicPropertyValue.id}"/>"> <fmt:message
											key="global.remove"></fmt:message>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<f:page page="${page}" url="/product/dynamicPropertyValue/home" params="<%=request.getParameterMap()%>" />
		</div>
	</div>
</div>