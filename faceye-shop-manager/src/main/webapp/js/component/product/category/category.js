/**
 * 说明:产品->product -> 产品分类 Category js 脚本 作者:haipenge
 */
var Category = {
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
			Category.multiRemove();
		});
	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="check-single"]'));
		if (checkedIds != '') {
			$.ajax({
				url : '/product/category/multiRemove',
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
							var id = idArray[i];
							$('#' + id).remove();
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
	}
};

$(document).ready(function() {
	Category.init();
});