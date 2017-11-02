/*
 * jQuery File Upload Plugin JS Example 8.9.1
 * https://github.com/blueimp/jQuery-File-Upload
 *
 * Copyright 2010, Sebastian Tschan
 * https://blueimp.net
 *
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/MIT
 */

/* global $, window */

$(function() {
	'use strict';
	// Initialize the jQuery File Upload widget:
	$('#fileupload').fileupload({
		// Uncomment the following to send cross-domain cookies:
		// xhrFields: {withCredentials: true},
		url : '/UploadServlet',
		autoUpload : true
	});

	// Enable iframe cross-domain access via redirect option:
	$('#fileupload')
			.fileupload('option', 'redirect', window.location.href.replace(/\/[^\/]*$/, '/cors/result.html?%s'));
	$('#fileupload').bind('fileuploaddestroy', function(e, data) {
		var filename = data.url.substring(data.url.indexOf('=') + 1);
//		var filename = data.filename;
		// alert(data.url);
//		alert(filename);
		if (filename) {
			$.ajax({
				url : '/product/image/remove/0',
				type : 'post',
				dataType : 'json',
				data : {
					filename : filename
				}
			});
		}
		$('#' + data.key).remove();
	});

	if (window.location.hostname === 'localhost' || window.location.hostname === 'm.faceye.com') {
		// Demo settings:
		$('#fileupload').fileupload('option', {
			url : '/UploadServlet',
			autoUpload : true,
			disableImageResize : /Android(?!.*Chrome)|Opera/.test(window.navigator.userAgent),
			maxFileSize : 5000000,
			acceptFileTypes : /(\.|\/)(gif|jpe?g|png|ppt|doc|docx)$/i,
			success : function(data, textStatus, xhr) {
				var length = data.files.length;
				var image = data.files[length - 1];
				var shopId = $('input[name="shopId"]').val();
				var filename = image.filename;
				$('#upload-msg').empty().append('图片"'+image.name+'"上传成功.');
				// alert(filename);
				$.ajax({
					url : '/setting/shop/saveLogo',
					type : 'post',
					dataType : 'json',
					data : {
						filename : filename,
						shopId : shopId
					},
					success : function(data, textStatus, xhr) {
						 $('.shop-logo').remove();
						// window.location.Reload(true);
					}
				});

			}
		});
		// $('#fileupload').bind('fileuploaddone', function(e, data) {
		//			
		//			
		// });

		var shopId = "";
		// var reg = new RegExp("(^|&)id=([^&]*)(&|$)");
		// var r = window.location.search.substr(1).match(reg);
		var url = window.location.href;
		if (url) {
			var uArray = url.split("\/");
			shopId = uArray[uArray.length - 1];
		}
		// 验证
		var regex = /^[1-9]\d*$/;
		var isNumber = false;
		isNumber = regex.test(shopId);

		if (isNumber) {
			$.ajax({
				url : '/setting/shop/getLogo',
				type : 'post',
				dataType : 'json',
				data : {
					shopId : shopId
				},
				context : $('#fileupload')[0]
			}).always(function() {
				$(this).removeClass('fileupload-processing');
			}).done(function(result) {
				
				$(this).fileupload('option', 'done').call(this, $.Event('done'), {
					result : result
				});
			});
		}
		// Upload server status check for browsers with CORS support:
		// if ($.support.cors) {
		// $.ajax({
		// url: '/UploadServlet',
		// type: 'HEAD'
		// }).fail(function () {
		// $('<div class="alert alert-danger"/>')
		// .text('Upload server currently unavailable - ' +
		// new Date())
		// .appendTo('#fileupload');
		// });
		// }
	} else {
		// Load existing files:
		$('#fileupload').addClass('fileupload-processing');
		$.ajax({
			// Uncomment the following to send cross-domain cookies:
			// xhrFields: {withCredentials: true},
			url : $('#fileupload').fileupload('option', 'url'),
			dataType : 'json',
			context : $('#fileupload')[0]
		}).always(function() {
			$(this).removeClass('fileupload-processing');
		}).done(function(result) {
			$(this).fileupload('option', 'done').call(this, $.Event('done'), {
				result : result
			});
		});
	}

});

/**
 * json对象转字符串形式
 */
function json2str(o) {
	var arr = [];
	var fmt = function(s) {
		if (typeof s == 'object' && s != null)
			return json2str(s);
		return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s;
	}
	for ( var i in o)
		arr.push("'" + i + "':" + fmt(o[i]));
	return '{' + arr.join(',') + '}';
}
