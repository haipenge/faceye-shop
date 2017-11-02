<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/product/product/product.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/product/product.js"/>"></script>

<c:forEach items="${productInfos}" var="productInfo" varStatus="status">
	<div class="card card-block">
		<div class="row">
			<div class="col-sm-3 col-xs-4">
				<c:forEach items="${productInfo.images}" var="image">
					<c:if test="${image.isDefault}">
						<a href="<c:url value="/product/product/detail/${productInfo.product.id}"/>"> <img
							class="img-rounded img-responsive" src="<c:url value="/UploadServlet?getfile=${image.filename}"/>"
							data-src="<c:url value="/UploadServlet?getfile=${image.filename}"/>" alt="Card image cap">
						</a>
					</c:if>
				</c:forEach>
			</div>
			<div class="col-sm-9 col-xs-8 m-t-md">
				<p>${productInfo.product.name}</p>
				<f:currency value="${productInfo.product.priceYuan}" />
				<a href="<c:url value="/product/product/detail/${productInfo.product.id}"/>"
					class="btn btn-sm pull-right m-r-md btn-success"><fmt:message key="product.product.buy" /></a>
			</div>
		</div>
	</div>
</c:forEach>
