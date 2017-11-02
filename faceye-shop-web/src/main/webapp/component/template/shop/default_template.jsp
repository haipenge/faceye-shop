<%@ include file="/component/core/taglib/taglib.jsp"%>
<html>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="">
<meta http-equiv="description" content="">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/clean-zone/js/bootstrap/dist/css/bootstrap.css"/>" />
<!--
<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/bootstrap/css/bootstrap.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/bootstrap/css/bootstrap-theme.min.css"/>" />
-->
<!-- comme form clean zone -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/js/lib/clean-zone/fonts/font-awesome-4/css/font-awesome.min.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/js/lib/clean-zone/js/jquery.nanoscroller/nanoscroller.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/js/lib/clean-zone/js/jquery.easypiechart/jquery.easy-pie-chart.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/js/lib/clean-zone/js/bootstrap.switch/bootstrap-switch.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/js/lib/clean-zone/js/bootstrap.datetimepicker/css/bootstrap-datetimepicker.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/clean-zone/js/jquery.select2/select2.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/clean-zone/js/bootstrap.slider/css/slider.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/js/lib/clean-zone/js/intro.js/introjs.css"/>" />
<!-- Custom styles for this template -->
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/core/Core.css"/>" />
<script type="text/javascript" src="<c:url value="/js/lib/jquery/jquery-min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/lib/bootstrap/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/core/Window.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/security/web/user/login.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/core/Core.js"/>"></script>
<link href="<c:url value="/js/lib/clean-zone/css/style.css"/>" rel="stylesheet" />
</head>
<body>
	<div id="head-nav" class="navbar navbar-default navbar-fixed-top">
		<!--  
	<div id="head-nav" class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	-->
		<div class="container-fluid">
			<tiles:insertAttribute name="default-header"></tiles:insertAttribute>
		</div>
	</div>
	<div class="container-fluid" style="margin-top:50px;">
		<tiles:insertAttribute name="default-center"></tiles:insertAttribute>
	</div>
	<div class="container-fluid">
		<tiles:insertAttribute name="default-footer"></tiles:insertAttribute>
	</div>
</body>



<script type="text/javascript" src="<c:url value="/js/lib/clean-zone/js/jquery.gritter/js/jquery.gritter.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/lib/clean-zone/js/jquery.nanoscroller/jquery.nanoscroller.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/lib/clean-zone/js/behaviour/general.js"/>"></script>
<script src="<c:url value="/js/lib/clean-zone/js/jquery.ui/jquery-ui.js"/>" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/js/lib/clean-zone/js/jquery.sparkline/jquery.sparkline.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/lib/clean-zone/js/jquery.easypiechart/jquery.easy-pie-chart.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/lib/clean-zone/js/jquery.nestable/jquery.nestable.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/lib/clean-zone/js/bootstrap.switch/bootstrap-switch.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/lib/clean-zone/js/bootstrap.datetimepicker/js/bootstrap-datetimepicker.min.js"/>"></script>
<script src="<c:url value="/js/lib/clean-zone/js/jquery.select2/select2.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/lib/clean-zone/js/skycons/skycons.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/lib/clean-zone/js/bootstrap.slider/js/bootstrap-slider.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/lib/clean-zone/js/intro.js/intro.js"/>" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//initialize the javascript
		App.init();
		App.dashBoard();

		introJs().setOption('showBullets', false).start();

	});
</script>
<script type="text/javascript" src="<c:url value="/js/lib/clean-zone/js/behaviour/voice-commands.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/lib/clean-zone/js/jquery.flot/jquery.flot.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/lib/clean-zone/js/jquery.flot/jquery.flot.pie.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/lib/clean-zone/js/jquery.flot/jquery.flot.resize.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/lib/clean-zone/js/jquery.flot/jquery.flot.labels.js"/>"></script>

<script type="text/javascript" src="<c:url value="/js/lib/clean-zone/js/jquery.datatables/jquery.datatables.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/lib/clean-zone/js/jquery.datatables/bootstrap-adapter/js/datatables.js"/>"></script>
</html>
