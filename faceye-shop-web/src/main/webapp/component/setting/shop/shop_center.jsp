<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/setting/shop/shop.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/setting/shop/shop.js"/>"></script>
<div class="block-flat">
<div class="page-header">
	<h2>
		<fmt:message key="setting.shop"></fmt:message>
	</h2>
</div>
	<c:forEach items="${productInfos}" var="productInfo" varStatus="status">
		<c:if test="${status.index mod 4 eq 0 }">
			<c:if test="${!status.first}">
</div>
</c:if>
<div class="row">
</c:if>
<div class="col-md-3">
	<div class="thumbnail">
		<c:forEach items="${productInfo.images}" var="image">
			<c:if test="${image.isDefault}">
				<a href="<c:url value="/product/product/detail/${productInfo.product.id}"/>"><img
					src="<c:url value="/UploadServlet?getfile=${image.filename}"/>" alt="..."></a>
			</c:if>
		</c:forEach>
		<div class="caption">
			<h4>${productInfo.product.name}</h4>
			<p>
				<a href="<c:url value="/product/product/detail/${productInfo.product.id}"/>" class="btn btn-success"><fmt:message
						key="product.product.buy" /></a>
			</p>
		</div>
	</div>
</div>
<c:if test="${ status.index mod 4 ne 0  && status.last }">
	</div>
</c:if>
</c:forEach>
</div>

