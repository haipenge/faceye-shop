<%@ include file="/component/core/taglib/taglib.jsp"%>
<script type="text/javascript" src="<c:url value="/js/component/security/web/user/register.js"/>"></script>
<div class="content">
	<form action="<c:url value="/security/web/user/doRegister"/>" method="post" class="form-horizontal" role="form"
		id="user-register-form">
		<fieldset>
			<div class="form-group">
				
					<input type="text" name="username" class="form-control" placeholder="<fmt:message key="security.web.user.name"></fmt:message>">
			</div>
			<div class="form-group">
					<input type="password" name="password" class="form-control" placeholder="<fmt:message key="security.web.user.password"></fmt:message>" />
			</div>

			<div class="form-group">
					<input type="password" name="repassword" class="form-control" placeholder="<fmt:message key="security.web.user.repassword"></fmt:message>"/>
			</div>
			<div class="form-group">
					<input type="text" name="email" class="form-control" placeholder="<fmt:message key="security.web.user.email"></fmt:message>">
			</div>
			<div class="form-group">
					<button type="button" id="register-button" class="btn btn-block btn-primary">
						<fmt:message key="global.submit"></fmt:message>
					</button>
					<!-- 
					<button type="button" class="btn btn-warning">
						<fmt:message key="global.cancel"></fmt:message>
					</button>
					 -->
			</div>
		</fieldset>
	</form>
</div>


