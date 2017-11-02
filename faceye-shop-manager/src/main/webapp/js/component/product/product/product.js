/**
 * 说明:产品->product -> 产品 Product js 脚本 作者:haipenge
 */
var Product = {
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
			Product.multiRemove();
		});
		$('select[name="categoryId"]').change(function() {
			Product.onCategoryChange();
		});
	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="check-single"]'));
		if (checkedIds != '') {
			$.ajax({
				url : '/product/product/multiRemove',
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
	},
	onCategoryChange : function() {
		var productId = $('input[name="id"]').val();
		var categoryId = $('select[name="categoryId"]').val();
		$.ajax({
			url : '/product/product/onCategoryChange',
			type : 'post',
			dataType : 'json',
			data : {
				categoryId : categoryId,
				productId : productId
			},
			success : function(data, status, xhr) {
				if (data.html && data.html != '') {
					$('#dynamic-html').empty().append(data.html);
				}else{
					$('#dynamic-html').empty();
				}
			}
		});
	}
};

$(document).ready(function() {
	Product.init();
});