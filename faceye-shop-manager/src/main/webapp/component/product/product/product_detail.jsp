<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" href="<c:url value="/js/lib/jQuery-File-Upload/css/jquery.fileupload.css"/>">
<link rel="stylesheet" href="<c:url value="/js/lib/jQuery-File-Upload/css/jquery.fileupload-ui.css"/>">
<noscript>
	<link rel="stylesheet" href="<c:url value="/js/lib/jQuery-File-Upload/css/jquery.fileupload-noscript.css"/>">
</noscript>
<noscript>
	<link rel="stylesheet" href="<c:url value="/js/lib/jQuery-File-Upload/css/jquery.fileupload-ui-noscript.css"/>">
</noscript>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/product/product/product.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/product/product/product.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/component/product/image/image.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3>
			<fmt:message key="product.product.detail"></fmt:message>
		</h3>
	</div>
	<div class="content">
		<div classs="table-responsive">
		  <input type="hidden" name="productId" value="${product.id }">
			<table class="table table-bordered table-hover">
				<tr>
					<td><fmt:message key="product.product.name"></fmt:message></td>
					<td>${product.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.product.code"></fmt:message></td>
					<td>${product.code}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.product.remark"></fmt:message></td>
					<td>${product.remark}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.product.category"></fmt:message></td>
					<td>${product.category.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.product.shop"></fmt:message></td>
					<td>${product.shop.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="product.product.price"></fmt:message></td>
					<td><f:currency value="${product.priceYuan}"/></td>
				</tr>
				<tr>
					<td><fmt:message key="product.product.isOnSale"></fmt:message></td>
					<td><f:boolean value="${product.isOnSale}"/></td>
				</tr>
				<!--@generate-entity-jsp-property-detail@-->
			</table>
		</div>
	</div>
	<h3><fmt:message key="product.image"/>:</h3>
	<div class="content">
		<!-- The file upload form used as target for the file upload widget -->
		<form id="fileupload" action="//jquery-file-upload.appspot.com/" method="POST" enctype="multipart/form-data">
			<!-- Redirect browsers with JavaScript disabled to the origin page -->
			<noscript>
				<input type="hidden" name="redirect" value="https://blueimp.github.io/jQuery-File-Upload/">
			</noscript>
			<!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
			<div class="row fileupload-buttonbar">
				<div class="col-lg-4">
					<!-- The fileinput-button span is used to style the file input field as button -->
					<span class="btn btn-success fileinput-button"> <i class="glyphicon glyphicon-plus"></i> <span><fmt:message key="product.image.upload"/>
					</span> <input type="file" name="files[]" multiple>
					</span>
					<!-- 
					<button type="submit" class="btn btn-primary start">
						<i class="glyphicon glyphicon-upload"></i> <span>Start upload</span>
					</button>
					<button type="reset" class="btn btn-warning cancel">
						<i class="glyphicon glyphicon-ban-circle"></i> <span>Cancel upload</span>
					</button>
					-->
					<button type="button" class="btn btn-danger delete">
						<i class="glyphicon glyphicon-trash"></i> <span><fmt:message key="global.remove"/></span>
					</button>
					 
					<input type="checkbox" class="toggle">
					<!-- The global file processing state -->
					<span class="fileupload-process"></span>
				</div>
				<!-- The global progress state -->
				<div class="col-lg-4 fileupload-progress fade">
					<!-- The global progress bar -->
					<div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
						<div class="progress-bar progress-bar-success" style="width: 0%;"></div>
					</div>
					<!-- The extended global progress state -->
					<div class="progress-extended">&nbsp;</div>
				</div>
				<div class="col-lg-4" id="upload-msg">
				  
				</div>
			</div>
			<!-- The table listing the files available for upload/download -->
			<table role="presentation" class="table table-striped">
				<tbody class="files"></tbody>
			</table>
		</form>
		<!-- The blueimp Gallery widget -->
		<!-- 
		<div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls" data-filter=":even">
			<div class="slides"></div>
			<h3 class="title"></h3>
			<a class="prev">&lt;</a> <a class="next">&gt;</a> <a class="close">×</a> <a class="play-pause"></a>
			<ol class="indicator"></ol>
		</div>
		 -->
	</div>
	<h3>
		<fmt:message key="inventory.inventory" /> &nbsp;&nbsp;<small><a href="<c:url value="/product/product/toSetInventory/${product.id}"/>"><fmt:message
											key="product.product.set.inventory" /></a></small>
	</h3>
	<div class="content">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<td><fmt:message key="product.product" /></td>
					<td><fmt:message key="product.productSku.price" /></td>
					<td><fmt:message key="inventory.inventory" /></td>
					<td><fmt:message key="inventory.inventory.lockedCount" /></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${skuInfos}" var="skuInfo">
					<tr>
						<td>${product.name}&nbsp;&nbsp;&nbsp;&nbsp;<c:forEach items="${skuInfo.skuProperties}" var="skuProperty"
								varStatus="status">
						    ${skuProperty.dynamicProperty.name }:${skuProperty.dynamicPropertyValue.value}
						    <c:if test="${not status.last }">
						    ,
						    </c:if>
							</c:forEach></td>
						<td><fmt:message key="global.currency.rmb"/>&nbsp;&nbsp;${skuInfo.productSku.priceYuan }&nbsp;&nbsp;<fmt:message key="global.currency.unit"/></td>
						<td>${skuInfo.inventory.amount }</td>
						<td>${skuInfo.inventory.lockedCount }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<!-- The template to display files available for upload -->
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td>
            <span class="preview"></span>
        </td>
       
        <td>
            <p class="size">Processing...</p>
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
        </td>
<td>
<button type="button" class="btn btn-success" onclick="Image.setDefault('{%=file.filename%}');return false;">Set As Default</button>
</td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="btn btn-primary start" disabled>
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>Start</span>
                </button>
            {% } %}
            {% if (!i) { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        <td>
            <span class="preview">
                {% if (file.thumbnailUrl) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                {% } %}
            </span>
        </td>
       
<td>
<button type="button" class="btn btn-success" onclick="Image.setDefault('{%=file.filename%}');return false;">Set As Default</button>
</td>
        
        <td>
            {% if (file.deleteUrl) { %}
                <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                    <i class="glyphicon glyphicon-trash"></i>
                    <span><fmt:message key="global.remove"/></span>
                </button>
                <input type="checkbox" name="delete" value="1" class="toggle">
            {% } else { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"/>"></script>
<!-- The Templates plugin is included to render the upload/download listings -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/tmpl.min.js"/>"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/load-image.min.js"/>"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/canvas-to-blob.min.js"/>"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/jquery.iframe-transport.js"/>"></script>
<!-- The basic File Upload plugin -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/jquery.fileupload.js"/>"></script>
<!-- The File Upload processing plugin -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/jquery.fileupload-process.js"/>"></script>
<!-- The File Upload image preview & resize plugin -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/jquery.fileupload-image.js"/>"></script>
<!-- The File Upload audio preview plugin -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/jquery.fileupload-audio.js"/>"></script>
<!-- The File Upload video preview plugin -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/jquery.fileupload-video.js"/>"></script>
<!-- The File Upload validation plugin -->
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/jquery.fileupload-validate.js"/>"></script>
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/jquery.fileupload-ui.js"/>"></script>
<!-- The main application script -->
<!-- 
<script src="<c:url value="/js/lib/jQuery-File-Upload/js/upload.js"/>"></script>
 -->
 <script src="<c:url value="/js/component/product/product/upload.js"/>"></script>