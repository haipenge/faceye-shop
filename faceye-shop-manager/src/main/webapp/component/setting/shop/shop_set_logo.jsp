<%@ include file="/component/core/taglib/taglib.jsp"%>
<link rel="stylesheet" href="<c:url value="/js/lib/jQuery-File-Upload/css/jquery.fileupload.css"/>">
<link rel="stylesheet" href="<c:url value="/js/lib/jQuery-File-Upload/css/jquery.fileupload-ui.css"/>">
<noscript>
	<link rel="stylesheet" href="<c:url value="/js/lib/jQuery-File-Upload/css/jquery.fileupload-noscript.css"/>">
</noscript>
<noscript>
	<link rel="stylesheet" href="<c:url value="/js/lib/jQuery-File-Upload/css/jquery.fileupload-ui-noscript.css"/>">
</noscript>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/component/setting/shop/shop.css"/>" />
<script type="text/javascript" src="<c:url value="/js/component/setting/shop/shop.js"/>"></script>
<div class="block-flat">
	<div class="header">
		<h3><fmt:message key="setting.shop.set.log"/>:${shop.name }</h3>
	</div>
	<div class="content">
		<!-- The template to display files available for upload -->
		<script id="template-upload" type="text/x-tmpl">

{% for (var i=0, file; file=o.files[i]; i++) { %}
<div class="card template-upload fade">
    
            <span class="preview"></span>
       
            <p class="name">{%=file.name%}</p>
            <strong class="error text-danger"></strong>
        
            <p class="size">Processing...</p>
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
    </div>
{% } %}
</script>
		<!-- The template to display files available for download -->
		<script id="template-download" type="text/x-tmpl">
<div class="card">
{% for (var i=0, file; file=o.files[i]; i++) { %}
   <img src="{%=file.url%}" alt="." class="card-img-top img-responsive img-thumbnail shop-logo">
{% } %}
  <div class="card-block">
    <!--
    <h4 class="card-title">Upload Shop Logo:</h4>
    <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
    <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
-->
  </div>
</div>

</script>
	</div>
	
	<input type="hidden" name="shopId" value="${shop.id }">
	<div class="content">
		<form id="fileupload" action="#" method="POST" enctype="multipart/form-data">
			<div class="row fileupload-buttonbar">
				<div class="col-lg-4">
					<span class="btn btn-success fileinput-button"> <i class="glyphicon glyphicon-plus"></i> <span><fmt:message
								key="product.image.upload" /> </span> <input type="file" name="files[]" multiple>
					</span> <span class="fileupload-process"></span>
				</div>
				<div class="col-lg-4 fileupload-progress fade">
					<div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
						<div class="progress-bar progress-bar-success" style="width: 0%;"></div>
					</div>
					<div class="progress-extended">&nbsp;</div>
				</div>
				<div class="col-lg-4" id="upload-msg"></div>
			</div>
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
</div>
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
<script src="<c:url value="/js/component/setting/shop/upload.js"/>"></script>