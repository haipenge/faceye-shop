<%@ include file="/component/core/taglib/taglib.jsp"%>
<html>
<title>${global.title}</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0">
<meta name="keywords" content="${global.keywords}" />
<meta name="description" content="${global.desc}" />
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/js/lib/bootstrap-4.0.0-alpha/dist/css/bootstrap.min.css"/>" />
<!-- Custom styles for this template -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/core/Core.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/component/setting/shop/shop.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/lib/jquery/jquery-2.1.4.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/lib/bootstrap-4.0.0-alpha/dist/js/bootstrap.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/component/core/Window.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/component/security/web/user/login.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/component/core/Core.js"/>"></script>
	<style type="text/css">
	body{
	margin-top:36px;
	}
	</style>
</head>

<body>
	<nav class="navbar navbar-light navbar-fixed-top bg-faded m-a-0"
		style="background-color: #e3f2fd; padding: 2px 2px 2px 2px; height: 36px">
		<tiles:insertAttribute name="default-header"></tiles:insertAttribute>
	</nav>
	<div class="container-fluid m-t-0"
		style="margin-bottom: 40px;">
		<tiles:insertAttribute name="default-center"></tiles:insertAttribute>
	</div>
	<div class="container-fluid">
		<tiles:insertAttribute name="default-footer"></tiles:insertAttribute>
	</div>
</body>

</html>
