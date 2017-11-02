/**
 * 说明:配置->setting -> 店铺 Shop js 脚本 作者:haipenge
 */
var Shop = {
	showWeixinUrlFlag : 0,
	init : function() {
		/**
		 * 全选、全不选
		 */
		$('input[name="check-all"]').click(function() {
			Check.onCheck($('input[name="check-all"]'), $('input[name="check-single"]'));
		});
		/**
		 * 执行删除
		 */
		$('.multi-remove').click(function() {
			Shop.multiRemove();
		});
		/**
		 * 显示，隐藏Weixin url
		 */
		Shop.showWeixinUrl();
		$('#show-weixin-url-btn').click(function() {
			Shop.showWeixinUrl();
		});

	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="check-single"]'));
		if (checkedIds != '') {
			$.ajax({
				url : '/setting/shop/multiRemove',
				type : 'post',
				dataType : 'json',
				data : {
					ids : checkedIds
				},
				success : function(data, textStatux, xhr) {
					var msg = new Msg({
						msg : '数据删除成功'
					});
					var idArray = checkedIds.split(',');
					for (var i = 0; i < idArray.length; i++) {
						var id = idArray[i];
						$('#' + id).remove();
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
	showWeixinUrl : function() {
		if (Shop.showWeixinUrlFlag === 0) {
			$('#show-weixin-url-container').hide();
			$('#show-weixin-url-btn').empty().append('微信店铺链接');
			Shop.showWeixinUrlFlag = 1;
		} else {
			$('#show-weixin-url-container').show();
			$('#show-weixin-url-btn').empty().append('隐藏');
			Shop.showWeixinUrlFlag = 0;
		}
	}
};

$(document).ready(function() {
	Shop.init();
});