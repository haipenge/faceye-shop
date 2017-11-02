<%@ include file="/component/core/taglib/taglib.jsp"%>
<script type="text/javascript" src="<c:url value="/js/component/setting/shop/shop.js"/>"></script>
<div class="card card-block bg-white m-t-0">
	<c:forEach items="${shops.content}" var="shop">
		<a href="<c:url value="/setting/shop/detail/${shop.id}"/>">${shop.name}</a>
	</c:forEach>
</div>

<div class="card">
	<div class="list-group">
		<c:forEach items="${productInfos}" var="productInfo" varStatus="status">
			<a href="<c:url value="/product/product/detail/${productInfo.product.id}"/>" class="list-group-item p-l-0 p-r-0">
				<div class="row">
					<div class="col-sm-5 col-xs-5 p-a-0">
						<c:forEach items="${productInfo.images}" var="image">
							<c:if test="${image.isDefault}">
								<img class="img-rounded img-responsive center-block product-list-img"
									src="<c:url value="/UploadServlet?getthumb=${image.filename}&size=0X80"/>"
									data-src="<c:url value="/UploadServlet?getthumb=${image.filename}&size=0X80"/>" alt="...">
							</c:if>
						</c:forEach>
					</div>
					<div class="col-sm-7 col-xs-7 p-t-0">
						<p>${productInfo.product.name}</p>
						<f:currency value="${productInfo.product.priceYuan}" />
						<!-- 
				<a href="<c:url value="/product/product/detail/${productInfo.product.id}"/>"
					class="btn btn-sm pull-right m-r-md btn-success"><fmt:message key="product.product.buy" /></a>
					 -->
					</div>
				</div>
			</a>
		</c:forEach>
	</div>
</div>
<c:forEach items="${productInfos}" var="productInfo" varStatus="status">
	<c:if test="${status.index mod 2 eq 0 }">
		<c:if test="${!status.first}">
</div>
</c:if>
<div class="row m-t-0 bg-white">
	</c:if>
	<div class="col-xs-6 col-sm-6 col-md-6">
		<div class="card m-t-0">
			<a href="<c:url value="/product/product/detail/${productInfo.product.id}"/>" class="list-group-item border-0"> <c:forEach
					items="${productInfo.images}" var="image">
					<c:if test="${image.isDefault}">
						<img class="img-rounded img-responsive card-img-top center-block product-list-img"
							src="<c:url value="/UploadServlet?getthumb=${image.filename}&size=0X80"/>"
							data-src="<c:url value="/UploadServlet?getthumb=${image.filename}&size=0X80"/>" alt="Card image cap">
					</c:if>
				</c:forEach>
				<p class="card-text show-more m-a-0">
					${productInfo.product.name}
				</p>
				<p class="card-text m-a-0 price">
					<f:currency value="${productInfo.product.priceYuan}" />
				</p>
			</a>
		</div>
	</div>
	<c:if test="${ status.count mod 2 ne 0  && status.last }">
</div>
</c:if>
</c:forEach>
