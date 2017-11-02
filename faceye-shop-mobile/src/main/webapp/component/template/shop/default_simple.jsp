<%@ include file="/component/core/taglib/taglib.jsp"%>
<html>
<head>
<title>${global.title}</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0">
<meta name="keywords" content="${global.keywords}" />
<meta name="description" content="${global.desc}" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<!-- 
<meta name="viewport" content="width=device-width, initial-scale=1"> -->
<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/bootstrap-4.0.0-alpha/dist/css/bootstrap.min.css"/>" />
<!-- Custom styles for this template -->
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/core/Core.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/setting/shop/shop.css"/>" />
<script type="text/javascript" src="<c:url value="/js/lib/jquery/jquery-2.1.4.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/lib/bootstrap-4.0.0-alpha/dist/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/core/Window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/security/web/user/login.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/core/Core.js"/>"></script>
</head>

<body>
	<div class="container-fluid" style="margin-top: 5px;margin-bottom:40px;">
		<tiles:insertAttribute name="default-center"></tiles:insertAttribute>
	</div>
	<div class="container-fluid">
		<tiles:insertAttribute name="default-footer"></tiles:insertAttribute>
	</div>
</body>

</html>
