/**
 * 说明:Platform js 脚本 作者:@haipenge
 */
var Platform = {
	init : function() {
		/**
		 * 全选，全不选
		 */
		$('input[name="check-all"]').click(function() {
			Check.onCheck($('input[name="check-all"]'), $('input[name="check-single"]'));
		});
		$('.multi-remove').click(function() {
			Platform.multiRemove();
		});
		$('.generate-weixin-shop-url').click(function() {
			var shopId = $(this).attr('id');
			Platform.generateWeixinShopUrl(shopId);
		});

	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="check-single"]'));
		if (checkedIds != '') {
			$.ajax({
				url : '/platform/platform/multiRemove',
				type : 'post',
				dataType : 'json',
				data : {
					ids : checkedIds
				},
				success : function(data, textStatux, xhr) {
					var type = data.result ? '' : 'warning';
					var msg = new Msg({
						msg : data.msg,
						type : type
					});
					if (data.result) {
						var idArray = checkedIds.split(',');
						for (var i = 0; i < idArray.length; i++) {
							$('#' + idArray[i]).remove();
						}
					}
					msg.show();
				}
			});
		} else {
			var msg = new Msg({
				msg : '请选择要删除的数据',
				type : 'warning'
			});
			msg.show();
		}
	},
	generateWeixinShopUrl : function(shopId) {
		$.ajax({
			url : '/platform/platform/generateWeixinShopUrl',
			type : 'post',
			data : {
				shopId : shopId
			},
			success : function(data, textStatus, xhr) {
				var html = '';
				html += '<div class="content">';
				html += '<p>URL IS:</p>';
				html += '<textarea class="form-control" rows="8">';
				html += data.url;
				html += '</textarea>';
				html += '</div>';
				var modal = new Modal({
					title : '微信店铺地址'
				});
				modal.setBody(html);
				modal.show();
			}
		});
	}
};

$(document).ready(function() {
	Platform.init();
});
