<%@ include file="/component/core/taglib/taglib.jsp"%>
<li><a href="#"><i class="fa fa-smile-o"></i><span>Serach</span></a>
	<ul class="sub-menu">
		<li class="<%=JspUtil.isActive(request, "/platform/platform")%>"><a
			href="/platform/platform/home"><fmt:message
					key="platform.platform.manager"></fmt:message></a></li>
	</ul></li>
<c:import
	url="/component/core/template/manager/platform_security_default.jsp" />