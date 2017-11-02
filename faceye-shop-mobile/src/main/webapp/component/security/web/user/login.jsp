<%@ include file="/component/core/taglib/taglib.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('input[name="j_username"]').focus();
	});
</script>
<div class="content">
	<form class="form-horizontal" action="<c:url value="/j_spring_security_check"/>" method="POST" role="form">
	<!-- 
		<input type="text" name="j_username" class="form-control"
			value="<c:if test="${not empty param.loginFailure}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>"
			class="security" placeholder="<fmt:message key="security.web.user.name"></fmt:message>"> <input
			type="password" name="j_password" class="form-control"
			placeholder="<fmt:message  key="security.web.user.password"></fmt:message>"> <label> <input
			type="checkbox" class="icheck" name="remember-me" value="true" checked /> <fmt:message
				key="security.web.user.remember.me"></fmt:message>
		</label>
		<button type="submit" class="btn btn-primary pull-right">
			<fmt:message key="security.web.user.login"></fmt:message>
		</button>
		 -->
		
		<div class="form-group">
				<input type="text" name="j_username" class="form-control"
					value="<c:if test="${not empty param.loginFailure}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>"
					class="security" placeholder="<fmt:message key="security.web.user.name"></fmt:message>">
		</div>
		<div class="form-group">
				<input type="password" name="j_password" class="form-control"
					placeholder="<fmt:message  key="security.web.user.password"></fmt:message>">
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="checkbox">
					<label> <input type="checkbox" class="icheck" name="remember-me" value="true" checked /> <fmt:message
							key="security.web.user.remember.me"></fmt:message>
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-block btn-primary pull-right">
					<fmt:message key="security.web.user.login"></fmt:message>
				</button>
				
			</div>
		</div>
	</form>
</div>


