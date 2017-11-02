<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/product/product/product.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/product/product.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/order/cart/cart.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<ol class="breadcrumb">
			<li><a href="<c:url value="/"/>"><fmt:message key="global.home" /></a></li>
			<li><a href="<c:url value="/setting/shop/detail/${product.shop.id}"/>">${product.shop.name}</a></li>
			<li class="active">${product.name }</li>
		</ol>
		<h3>
			<fmt:message key="product.product.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div class="row">
			<div class="col-md-5">
				<div class="row">
					<c:if test="${not empty productInfo.images}">
						<img class="img-responsive img-rounded" id="main-product-pic" alt=""
							src="<c:url value="/UploadServlet?getfile=${productInfo.images[0].filename}"/>">
					</c:if>
				</div>
				<div class="row">
					<div class="content">
						<c:set var="imagesCount" value="${fn:length(productInfo.images) gt 4 ? 4:fn:length(productInfo.images)}" />
						<c:forEach items="${productInfo.images}" var="image" varStatus="status">
							<c:if test="${status.index lt 4 }">
								<div class="col-md-3">
									<img class="img-responsive img-rounded small-product-pic" data-filename="${image.filename}" alt=""
										src="<c:url value="/UploadServlet?getthumb=${image.filename}&size=500X300"/>">

								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-md-7">
				<c:import url="/component/core/template/msg/msg.jsp" />
				<h4>${productInfo.product.name }</h4>
				<p>
					<fmt:message key="product.product.shop" />
					:${productInfo.product.shop.name}
				</p>
				<p>${productInfo.product.remark }</p>
				<p>
					<fmt:message key="product.product.price" />
					:
					<fmt:message key="product.product.price.rmb" />
					<span class="product-sku-price">${productInfo.product.priceYuan }</span>
					<fmt:message key="product.product.price.unit" />
				</p>
				<c:forEach items="${skuSelects}" var="skuSelect">
					<div class="form-group sku-container-key">
						<label class="col-md-2 control-label key">${skuSelect.dynamicProperty.name}:</label>
						<div class="col-md-10">
							<c:forEach items="${skuSelect.dynamicPropertyValues}" var="dynamicPropertyValue" varStatus="status">
								<label class="btn btn-default"> <input type="radio" name="${skuSelect.dynamicProperty.id}"
									id="option${dynamicProperty.id}-${dynamicPropertyValue.id}-${status.index}" value="${dynamicPropertyValue.id}"
									autocomplete="off"> ${dynamicPropertyValue.value}
								</label>
							</c:forEach>
						</div>
					</div>
				</c:forEach>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <fmt:message key="product.product.buy.count" />:
					</label>
					<div class="col-md-10">
						<div class="input-group">
							<span class="input-group-addon" id="increace-product-count"><a href="#" style="cursor: pointer;">+</a></span> <input
								type="text" class="form-control" name="quantity" value="1"> <span class="input-group-addon"
								id="reduce-product-count"><a href="#" style="cursor: pointer;">-</a></span>
						</div>
					</div>
				</div>
				<input type="hidden" name="productId" value="${product.id}">
				<div class="form-group text-center">
					<button type="button" class="btn btn-sm btn-primary" onclick="Cart.direct2Buy();return false;">
						<fmt:message key="product.product.buy" />
					</button>
					<button type="button" class="btn btn-sm btn-success" onclick="Cart.add2Cart();return false;">
						<fmt:message key="order.cart.add.product" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="content">
	  <h3><fmt:message key="product.product.dynamic.info"/></h3>
	  <table class="table">
	     <c:forEach items="${productInfo.productPropertyInfos}" var="productPropertyInfo" varStatus="status">
	       <tr>
	         <td>${productPropertyInfo.productProperty.dynamicProperty.name}</td>
	         <td>
	         <c:choose>
	           <c:when test="${not empty productPropertyInfo.dynamicPropertyValues}">
	            <c:forEach items="${productPropertyInfo.dynamicPropertyValues}" var = "dynamicPropertyValue" varStatus="status">
	               ${dynamicPropertyValue.value}
	               <c:if test="${! status.last }">
	               ,
	               </c:if>
	            </c:forEach>
	           </c:when>
	           <c:otherwise>
	            <c:choose>
	              <c:when test="${productPropertyInfo.productProperty.dynamicProperty.dataType.code eq 'boolean' }">
	               <f:boolean value="${productPropertyInfo.productProperty.value}"/>
	              </c:when>
	              <c:otherwise>
	               ${productPropertyInfo.productProperty.value}
	              </c:otherwise>
	            </c:choose>
	           </c:otherwise>
	         </c:choose>
	         </td>
	       </tr>
	     </c:forEach>
	  </table>
	</div>
</div>