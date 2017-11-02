<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/setting/shop/shop.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/setting/shop/shop.js"/>"></script>

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
	<ol class="carousel-indicators">
		<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
		<li data-target="#carousel-example-generic" data-slide-to="1"></li>
		<li data-target="#carousel-example-generic" data-slide-to="2"></li>
	</ol>
	<div class="carousel-inner" role="listbox">
		<div class="carousel-item active">
			<img data-src="<c:url value="/UploadServlet?getfile=${shop.logoFilename}"/>"
				src="<c:url value="/UploadServlet?getfile=${shop.logoFilename}"/>" style="height: 120px;" class="center-block"
				alt="First slide">
		</div>
		<div class="carousel-item">
			<img data-src="<c:url value="/UploadServlet?getfile=${shop.logoFilename}"/>"
				src="<c:url value="/UploadServlet?getfile=${shop.logoFilename}"/>" style="height: 120px;" class="center-block"
				alt="Second slide">
		</div>
		<div class="carousel-item">
			<img data-src="<c:url value="/UploadServlet?getfile=${shop.logoFilename}"/>"
				src="<c:url value="/UploadServlet?getfile=${shop.logoFilename}"/>" style="height: 120px;" class="center-block"
				alt="Third slide">
		</div>
	</div>
	<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev"> <span
		class="icon-prev" aria-hidden="true"></span> <span class="sr-only">Previous</span>
	</a> <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next"> <span
		class="icon-next" aria-hidden="true"></span> <span class="sr-only">Next</span>
	</a>
</div>
<div class="row">
	<div class="content text-center">
		<p><fmt:message key="shop.mobile.tips"/></p>
	</div>
</div>
<!-- 
<div class="row bg-success">
	<div class="col-xs-3">
		<img src="<c:url value="/UploadServlet?getfile=${shop.logoFilename}"/>" class="img-responsive img-thumbnail img-rounded" alt="...">
	</div>
	<div class="col-xs-9">
		<h5>${shop.name }</h5>
	</div>
</div>
 -->

<c:forEach items="${productInfos}" var="productInfo" varStatus="status">
	<c:if test="${status.index mod 2 eq 0 }">
		<c:if test="${!status.first}">
			</div>
		</c:if>
		<div class="row m-t-0 bg-white">
	</c:if>
	<div class="col-xs-6 col-sm-6 col-md-6">
		<div class="card p-a-0">
			<a class="list-group-item border-0" href="<c:url value="/product/product/detail/${productInfo.product.id}"/>">
			 <c:forEach items="${productInfo.images}" var="image">
					<c:if test="${image.isDefault}">
						<img class="img-rounded img-responsive card-img-top product-list-img center-block"
							src="<c:url value="/UploadServlet?getfile=${image.filename}"/>"
							data-src="<c:url value="/UploadServlet?getfile=${image.filename}"/>" alt="Card image cap">

					</c:if>
				</c:forEach>
				<p class="card-text m-a-0 show-more">${productInfo.product.name}</p>
				<p class="card-text m-a-0 price"><f:currency value="${productInfo.product.priceYuan}" /></p>
			</a>
		</div>
	</div>
	<c:if test="${ status.count mod 2 ne 0  && status.last }">
		</div>
	</c:if>
</c:forEach>