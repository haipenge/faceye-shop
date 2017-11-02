<%@ include file="/component/core/taglib/taglib.jsp"%>
	<link rel="stylesheet" type="text/css"
		href="<c:url value="/css/component/customer/address/address.css"/>" />
	<script type="text/javascript"
		src="<c:url value="/js/component/customer/address/address.js"/>"></script>
	<div class="page-head">
		<h2>
			<fmt:message key="customer.address.manager"></fmt:message>
			<a class="btn btn-primary" href="<c:url value="/customer/address/input"/>">
				<fmt:message key="customer.address.add"></fmt:message>
			</a>
		</h2>
	</div>
	<div class="cl-mcont">
		<div class="block-flat">
			<div class="content">
				<form action="<c:url value="/customer/address/home"/>" method="post"
					role="form" class="form-horizontal">
					<fieldset>
						<div class="form-group">
							<div class="col-md-2">
							     <select name="EQ|customer.$id" class="form-control">
							       <option value="0"><fmt:message key="global.select"/><fmt:message key="customer.customer"/></option>
							       <c:forEach items="${customers}" var ="customer">
							         <option value="${customer.id}"<c:if test="${customer.id eq entity.customer.id}"> selected</c:if>>${customer.name}</option>
							       </c:forEach>
							     </select>
							</div>
							
<div class="col-md-1">
	<input type="text" name="EQ|mobile" value="${searchParams.mobile}"
		placeholder="<fmt:message key="customer.address.mobile"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|receiveUserName" value="${searchParams.receiveUserName}"
		placeholder="<fmt:message key="customer.address.receiveUserName"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|province" value="${searchParams.province}"
		placeholder="<fmt:message key="customer.address.province"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|city" value="${searchParams.city}"
		placeholder="<fmt:message key="customer.address.city"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|area" value="${searchParams.area}"
		placeholder="<fmt:message key="customer.address.area"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|street" value="${searchParams.street}"
		placeholder="<fmt:message key="customer.address.street"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|detail" value="${searchParams.detail}"
		placeholder="<fmt:message key="customer.address.detail"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|isDefault" value="${searchParams.isDefault}"
		placeholder="<fmt:message key="customer.address.isDefault"></fmt:message>"
		class="form-control input-sm">
</div>

<div class="col-md-1">
	<input type="text" name="EQ|isRemoved" value="${searchParams.isRemoved}"
		placeholder="<fmt:message key="customer.address.isRemoved"></fmt:message>"
		class="form-control input-sm">
</div>
<!--@generate-entity-jsp-query-detail@-->









							<div class="col-md-1">
								<button type="submit" class="btn btn-sm btn-primary">
									<fmt:message key="global.search"></fmt:message>
								</button>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
			<div class="content">
				<div id="msg"></div>
				<button class="btn btn-primary btn-sm multi-remove">
					<fmt:message key="global.remove"></fmt:message>
				</button>
				<div classs="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th><input type="checkbox" name="check-all"></th>
								  <th><fmt:message key='customer.address.customer'></fmt:message></th>
								<th><fmt:message key='customer.address.mobile'></fmt:message></th>   
 <th><fmt:message key='customer.address.receiveUserName'></fmt:message></th>   
 <th><fmt:message key='customer.address.province'></fmt:message></th>   
 <th><fmt:message key='customer.address.city'></fmt:message></th>   
 <th><fmt:message key='customer.address.area'></fmt:message></th>   
 <th><fmt:message key='customer.address.street'></fmt:message></th>   
 <th><fmt:message key='customer.address.detail'></fmt:message></th>   
 <th><fmt:message key='customer.address.isDefault'></fmt:message></th>   
 <th><fmt:message key='customer.address.isRemoved'></fmt:message></th>   
 <!--@generate-entity-jsp-property-desc@-->
								<th><fmt:message key="global.view"/></th>
								<th><fmt:message key="global.edit"></fmt:message></th>
								<th><fmt:message key="global.remove"></fmt:message></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.content}" var="address">
								<tr id="${address.id}">
									<td><input type="checkbox" name="check-single" value="${address.id}"></td>
									  <td>${address.customer.name}</td>
									
									<td>${address.mobile}</td>   
 <td>${address.receiveUserName}</td>   
 <td>${address.province}</td>   
 <td>${address.city}</td>   
 <td>${address.area}</td>   
 <td>${address.street}</td>   
 <td>${address.detail}</td>   
 <td>${address.isDefault}</td>   
 <td>${address.isRemoved}</td>   
 <!--@generate-entity-jsp-property-value@-->
									<td><a href="<c:url value="/customer/address/detail/${address.id}.html"/>"><fmt:message key="global.view"/></a></td>
									<td><a
										href="<c:url value="/customer/address/edit/${address.id}"/>"> <fmt:message
												key="global.edit"></fmt:message>
									</a></td>
									<td><a
										href="<c:url value="/customer/address/remove/${address.id}"/>"> <fmt:message
												key="global.remove"></fmt:message>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<f:page page="${page}" url="/customer/address/home"  params="<%=request.getParameterMap()%>" />
			</div>
		</div>
	</div>