<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/product/product/product.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/product/product.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<c:choose>
				<c:when test="${empty product.id}">
					<fmt:message key="product.product.add"></fmt:message>
				</c:when>
				<c:otherwise>
					<fmt:message key="product.product.edit"></fmt:message>
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<div class="content">
		<form action="<c:url value="/product/product/save"/>" role="form" method="post" class="form-horizontal">
			<input type="hidden" name="id" value="${product.id}">
			<input type="hidden" name="code" class="form-control" value="${product.code }">
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="product.product.name" />
					</label>
					<div class="col-md-6">
						<input type="text" name="name" class="form-control" value="${product.name}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="product.product.remark" />
					</label>
					<div class="col-md-6">
						<textarea rows="3" cols="" name="remark" value="${product.remark}" class="form-control"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="name"> <spring:message code="product.product.category" />
					</label>
					<div class="col-md-6">
						<select name="categoryId" class="form-control">
							<option value="0"><fmt:message key="global.select"></fmt:message><fmt:message key="product.category"></fmt:message></option>
							<c:forEach items="${categorys}" var="category">
								<option value="${category.id}" <c:if test="${product.category.id eq category.id }">selected</c:if>>${category.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="priceYuan"> <spring:message code="product.product.price" />
					</label>
					<div class="col-md-6">
						<input type="text" name="priceYuan" class="form-control" value="${product.priceYuan }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" for="isOnSale"> <spring:message code="product.product.isOnSale" />
					</label>
					<div class="col-md-6">
						<label class="radio-inline"> <input type="radio" name="isOnSale" id="inlineRadio1" value="true" <c:if test="${product.isOnSale}">checked</c:if>> <fmt:message
								key="product.product.on.sale" />
						</label> <label class="radio-inline"> <input type="radio" name="isOnSale" id="inlineRadio2" value="false" <c:if test="${!product.isOnSale}">checked</c:if>>
							<fmt:message key="product.product.close.sale" />
						</label>

					</div>
				</div>

				<!--@generate-entity-jsp-property-update@-->
				<div class="content" id="dynamic-html">${html}</div>
				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="global.submit.save"></fmt:message>
						</button>
					</div>
				</div>
			</fieldset>
		</form>

	</div>
</div>