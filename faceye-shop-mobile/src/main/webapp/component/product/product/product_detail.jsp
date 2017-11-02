<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/product/product/product.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/product/product.js"/>"></script>
<script type="text/javascript">
$(document).ready(function(){
	
});
</script>
<div id="carousel-example-generic" class="carousel slide bg-white" data-ride="carousel">
	<ol class="carousel-indicators">
		<c:forEach items="${productInfo.images}" var="image" varStatus="status">
			<li data-target="#carousel-example-generic" data-slide-to="${status.index }"
				class="<c:if test="${status.first }">active</c:if>"></li>
		</c:forEach>
	</ol>
	<div class="carousel-inner" role="listbox">
		<c:forEach items="${productInfo.images}" var="image" varStatus="status">
			<div class="carousel-item <c:if test="${status.first}">active</c:if>">
				<img data-src="<c:url value="/UploadServlet?getfile=${image.filename}"/>" style="height:120px;" class="center-block"
					src="<c:url value="/UploadServlet?getfile=${image.filename}"/>" alt="First slide">
			</div>
		</c:forEach>
	</div>
	<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev"> <span
		class="icon-prev" aria-hidden="true"></span> <span class="sr-only">Previous</span>
	</a> <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next"> <span
		class="icon-next" aria-hidden="true"></span> <span class="sr-only">Next</span>
	</a>
</div>
<div class="card card-block m-t bg-white">
	<div class="content">
		<h5>${productInfo.product.name }</h5>
		<p>
			<fmt:message key="product.product.price" />
			:
			<f:currency value="${productInfo.product.priceYuan}" />
		</p>
	</div>
	<div class="row">
		<c:import url="/component/core/template/msg/msg.jsp" />
	</div>
	<div class="row">
		<c:forEach items="${skuSelects}" var="skuSelect">
			<div class="form-group sku-container-key">
				<label class="col-xs-2 col-sm-2 col-md-2 control-label key">${skuSelect.dynamicProperty.name}:</label>
				<div class="col-xs-10 col-sm-2 col-md-10">
				  <div class="" data-toggle="buttons">
					<c:forEach items="${skuSelect.dynamicPropertyValues}" var="dynamicPropertyValue" varStatus="status">
						<label class="btn btn-warning-outline" style="margin-right:10px;"> <input type="radio" name="${skuSelect.dynamicProperty.id}"
							id="option${dynamicProperty.id}-${dynamicPropertyValue.id}-${status.index}" value="${dynamicPropertyValue.id}"
							autocomplete="off"> ${dynamicPropertyValue.value}
						</label>
					</c:forEach>
				   </div>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="col-xs-2 col-sm-2 col-md-2 control-label"><fmt:message key="order.order.count"/>:</label>
			<div class="input-group col-xs-5 col-sm-5 col-md-5">
				<span class="input-group-addon" id="reduce-product-count"><a href="#" style="cursor: pointer;"><b>-</b></a></span> <input
					type="text" class="form-control form-control-sm" name="quantity" value="1"> <span class="input-group-addon"
					id="increace-product-count"><a href="#" style="cursor:pointer;"><b>+</b></a></span>
			</div>
		</div>
	</div>
	<c:forEach items="${productInfo.productPropertyInfos}" var="productPropertyInfo" varStatus="status">
		<c:if test="${not productPropertyInfo.productProperty.dynamicProperty.isSku }">
			<div class="row">
				<div class="col-xs-2 col-sm-2 col-md-2 col-lg-1">${productPropertyInfo.productProperty.dynamicProperty.name}:</div>
				<div class="col-xs-10 col-sm-10 col-md-10 col-lg-11">
					<c:choose>
						<c:when test="${not empty productPropertyInfo.dynamicPropertyValues}">
							<c:forEach items="${productPropertyInfo.dynamicPropertyValues}" var="dynamicPropertyValue" varStatus="status">
	               ${dynamicPropertyValue.value}
	               <c:if test="${! status.last }">
	               ,
	               </c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${productPropertyInfo.productProperty.dynamicProperty.dataType.code eq 'boolean' }">
									<f:boolean value="${productPropertyInfo.productProperty.value}" />
								</c:when>
								<c:otherwise>
	               ${productPropertyInfo.productProperty.value}
	              </c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</c:if>
	</c:forEach>
	<input type="hidden" name="productId" value="${product.id}">
</div>
