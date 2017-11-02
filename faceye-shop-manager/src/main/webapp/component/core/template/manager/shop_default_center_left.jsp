
<%@ include file="/component/core/taglib/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<li><a href="#"><i class="fa fa-file"></i><span>配置</span></a>
	<ul class="sub-menu">
		<li class="<%=JspUtil.isActive(request, "/setting/shop")%>"><a
			href="/setting/shop/home"><fmt:message key="setting.shop.manager" /></a>
		</li>
		
		<li class="<%=JspUtil.isActive(request, "/product/dataType")%>"><a
			href="/product/dataType/home"><fmt:message
					key="product.dataType.manager"></fmt:message></a></li>
	</ul></li>
<li><a href="#"><i class="fa fa-file"></i><span>Geo</span></a>
	<ul class="sub-menu">
	<li class="<%=JspUtil.isActive(request, "/lbs/geoLibrary")%>"><a
			href="/lbs/geoLibrary/home"><fmt:message key="lbs.geoLibrary.manager" /></a>
		</li>
	</ul>
</li>
<li><a href="#"><i class="fa fa-file"></i><span>产品</span></a>
	<ul class="sub-menu">
		<li class="<%=JspUtil.isActive(request, "/product/category")%>">
			<a href="/product/category/home"><fmt:message
					key="product.category.manager" /></a>
		</li>
		<li class="<%=JspUtil.isActive(request, "/product/product")%>"><a
			href="/product/product/home"><fmt:message
					key="product.product.manager" /></a></li>
		<li class="<%=JspUtil.isActive(request, "/product/dynamicProperty")%>">
			<a href="/product/dynamicProperty/home"><fmt:message
					key="product.dynamicProperty.manager" /></a>
		</li>
		<li class="<%=JspUtil.isActive(request, "/product/image")%>"><a
			href="/product/image/home"><fmt:message
					key="product.image.manager"></fmt:message></a></li>
		<!-- 
						   <li class="<%=JspUtil.isActive(request, "dynamicPropertyValue")%>">
						     <a href="/product/dynamicPropertyValue/home"><fmt:message key="product.dynamicPropertyValue.manager"/></a>
						   </li>
						   
						   <li class="<%=JspUtil.isActive(request, "productProperty")%>">
						     <a href="/product/productProperty/home"><fmt:message key="product.productProperty.manager"/></a>
						   </li>
						   <li class="<%=JspUtil.isActive(request, "productSku")%>">
						     <a href="/product/productSku/home"><fmt:message key="product.productSku.manager"/></a>
						   </li>
						   <li class="<%=JspUtil.isActive(request, "skuProperty")%>">
						     <a href="/product/skuProperty/home"><fmt:message key="product.skuProperty.manager"/></a>
						   </li>
						    -->
	</ul></li>
<li><a href="#"><i class="fa fa-file"></i><span>订单</span></a>
	<ul class="sub-menu">
		<li class="<%=JspUtil.isActive(request, "/order/order")%>"><a
			href="/order/order/home"><fmt:message key="order.order.manager" /></a>
		</li>

		<li class="<%=JspUtil.isActive(request, "/order/cart")%>"><a
			href="/order/cart/home"><fmt:message key="order.cart.manager"></fmt:message></a></li>
		<li class="<%=JspUtil.isActive(request, "/order/cartItem")%>"><a
			href="/order/cartItem/home"><fmt:message
					key="order.cartItem.manager"></fmt:message></a></li>
	</ul></li>
<li><a href="#"><i class="fa fa-file"></i><span>客户</span></a>
	<ul class="sub-menu">
		<li class="<%=JspUtil.isActive(request, "/customer/customer")%>">
			<a href="/customer/customer/home"><fmt:message
					key="customer.customer.manager" /></a>
		</li>
		<li class="<%=JspUtil.isActive(request, "/customer/address")%>">
			<a href="/customer/address/home"><fmt:message
					key="customer.address.manager" /></a>
		</li>
	</ul></li>
<li><a href="#"><i class="fa fa-file"></i><span>库存</span></a>
	<ul class="sub-menu">
		<li class="<%=JspUtil.isActive(request, "/inventory/inventory")%>">
			<a href="/inventory/inventory/home"><fmt:message
					key="inventory.inventory.manager" /></a>
		</li>
		<li class="<%=JspUtil.isActive(request, "/inventory/invoice")%>">
			<a href="/inventory/invoice/home"><fmt:message
					key="inventory.invoice.manager" /></a>
		</li>
		<!-- 
						   <li class="<%=JspUtil.isActive(request, "item")%>">
						     <a href="/inventory/invoiceItem/home"><fmt:message key="inventory.item.manager"/></a>
						   </li>
						    -->
	</ul></li>