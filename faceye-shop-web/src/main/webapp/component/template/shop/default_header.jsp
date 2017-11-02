<%@ include file="/component/core/taglib/taglib.jsp"%>
<div class="navbar-header">
	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		<span class="fa fa-gear"></span>
	</button>
	<a class="navbar-brand" href="<c:url value="/"/>"><fmt:message key="global.name"></fmt:message></a>
</div>
<div class="navbar-collapse collapse">
	<ul class="nav navbar-nav">
		<li class="active"><a href="<c:url value="/setting/shop/home"/>"><fmt:message key="global.home"></fmt:message></a></li>
		<!--  
		<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Contact <b class="caret"></b></a>
			<ul class="dropdown-menu">
				<li><a href="#">Action</a></li>
				<li><a href="#">Another action</a></li>
				<li><a href="#">Something else here</a></li>
				<li class="dropdown-submenu"><a href="#">Sub menu</a>
					<ul class="dropdown-menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
					</ul></li>
			</ul></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Large menu <b class="caret"></b></a>
			<ul class="dropdown-menu col-menu-2">
				<li class="col-sm-6 no-padding">
					<ul>
						<li class="dropdown-header"><i class="fa fa-group"></i>Users</li>
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li class="dropdown-header"><i class="fa fa-gear"></i>Config</li>
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
					</ul>
				</li>
				<li class="col-sm-6 no-padding">
					<ul>
						<li class="dropdown-header"><i class="fa fa-legal"></i>Sales</li>
						<li><a href="#">New sale</a></li>
						<li><a href="#">Register a product</a></li>
						<li><a href="#">Register a client</a></li>
						<li><a href="#">Month sales</a></li>
						<li><a href="#">Delivered orders</a></li>
					</ul>
				</li>
			</ul></li>
			-->
		<li><a href="<c:url value="/default/forward?forward=default.about"/>"><fmt:message key="global.about"></fmt:message></a></li>
	</ul>
	<ul class="nav navbar-nav navbar-right user-nav">
		<li><a href="<c:url value="/order/order/home"/>"><fmt:message key="order.order" /></a></li>
		<li><a href="<c:url value="/order/cartItem/home"/>"><fmt:message key="order.cart" /><span class="badge"
				id="order-cart-item-count">${cartInfo.totalQuantity}</span></a></li>
		<c:if test="${not empty pageContext.request.userPrincipal}">
			<li class="dropdown profile_menu"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <img
					alt="Avatar" src="<c:url value="/js/li/b/clean-zone/images/avatar2.jpg"/>" /> <span> <sec:authentication
							property="principal.username" />
				</span> <b class="caret"></b>
			</a>
				<ul class="dropdown-menu">
					<li><a href="#"><fmt:message key="security.web.user.account"></fmt:message></a></li>
					<li><a href="<c:url value="/security/web/user/profile/${pageContext.request.userPrincipal.name}"/>"><fmt:message
								key="security.web.user.profile"></fmt:message></a></li>
					<li class="divider"></li>
					<li><a href="<c:url value="/j_spring_security_logout"/>"><fmt:message key="security.web.user.logout"></fmt:message></a></li>
				</ul></li>
		</c:if>
		<c:if test="${empty pageContext.request.userPrincipal}">
			<li><a href="<c:url value="/login"/>"><fmt:message key="security.web.user.login" /></a></li>
			<li><a href="<c:url value="/register"/>"><fmt:message key="security.web.user.register" /></a></li>
		</c:if>

	</ul>
	<!-- 
	<ul class="nav navbar-nav navbar-right not-nav">
		<li class="button dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><i
				class=" fa fa-comments"></i></a>
			<ul class="dropdown-menu messages">
				<li>
					<div class="nano nscroller">
						<div class="content">
							<ul>
								<li><a href="#"> <img src="<c:url value="/js/lib/clean-zone/images/avatar2.jpg"/>" alt="avatar" /><span
										class="date pull-right">13 Sept.</span> <span class="name">Daniel</span> I'm following you, and I want your
										money!
								</a></li>
								<li><a href="#"> <img src="<c:url value="/js/lib/clean-zone/images/avatar_50.jpg"/>" alt="avatar" /><span
										class="date pull-right">20 Oct.</span><span class="name">Adam</span> is now following you
								</a></li>
								<li><a href="#"> <img src="<c:url value="/js/lib/clean-zone/images/avatar4_50.jpg"/>" alt="avatar" /><span
										class="date pull-right">2 Nov.</span><span class="name">Michael</span> is now following you
								</a></li>
								<li><a href="#"> <img src="<c:url value="/js/lib/clean-zone/images/avatar3_50.jpg"/>" alt="avatar" /><span
										class="date pull-right">2 Nov.</span><span class="name">Lucy</span> is now following you
								</a></li>
							</ul>
						</div>
					</div>
					<ul class="foot">
						<li><a href="#">View all messages </a></li>
					</ul>
				</li>
			</ul></li>
		<li class="button dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><i
				class="fa fa-globe"></i><span class="bubble">2</span></a>
			<ul class="dropdown-menu">
				<li>
					<div class="nano nscroller">
						<div class="content">
							<ul>
								<li><a href="#"><i class="fa fa-cloud-upload info"></i><b>Daniel</b> is now following you <span
										class="date">2 minutes ago.</span></a></li>
								<li><a href="#"><i class="fa fa-male success"></i> <b>Michael</b> is now following you <span
										class="date">15 minutes ago.</span></a></li>
								<li><a href="#"><i class="fa fa-bug warning"></i> <b>Mia</b> commented on post <span class="date">30
											minutes ago.</span></a></li>
								<li><a href="#"><i class="fa fa-credit-card danger"></i> <b>Andrew</b> killed someone <span
										class="date">1 hour ago.</span></a></li>
							</ul>
						</div>
					</div>
					<ul class="foot">
						<li><a href="#">View all activity </a></li>
					</ul>
				</li>
			</ul></li>
		<li class="button"><a href="javascript:;" class="speech-button"><i class="fa fa-microphone"></i></a></li>
	</ul>
	 -->
</div>