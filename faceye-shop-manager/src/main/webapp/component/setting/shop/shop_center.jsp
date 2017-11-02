<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/setting/shop/shop.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/setting/shop/shop.js"/>"></script>
<c:if test="${not empty shop}">
	<div class="page-head" style="height: 200px;">
		<h2>
			<div class="col-md-2">
				<img src="<c:url value="/UploadServlet?getfile=${shop.logoFilename}"/>" class="img-responsive img-thumbnail"><br>
				<p class="text-center">
					<a href="<c:url value="/setting/shop/setLogo/${shop.id}"/>"><fmt:message key="setting.shop.set.log" /></a>
				</p>
			</div>
			<div class="col-md-10">
				<!-- 
			<fmt:message key="setting.shop.manager"></fmt:message>
			-->
				Welcome to ${shop.name}&nbsp;&nbsp;<small>${shop.remark}</small><small><a
					href="<c:url value="/setting/shop/edit/${shop.id }"/>" class="pull-right"><fmt:message key="global.edit" /></a></small>
			</div>
		</h2>
	</div>
</c:if>
<c:choose>
	<c:when test="${empty shop}">
		<div class="cl-mcont">
			<div class="block-flat">
				<div class="content">
					<a class="btn btn-primary" href="<c:url value="/setting/shop/input"/>"> <fmt:message key="setting.shop.add"></fmt:message>
					</a>
				</div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="cl-mcount">
			<div class="block-flat">
				<div class="row">
					<div class="col-md-2">
						<button type="button" class="btn btn-primary" id="show-weixin-url-btn">
							<fmt:message key="shop.manager.weixin.push.url" />
						</button>
					</div>
					<div class="col-md-10">
						<div class="content" id="show-weixin-url-container">
							<textarea class="form-control">${oauth2Url}</textarea>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>
