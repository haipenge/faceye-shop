/**
 * 说明:库存->inventory -> 库存明细 Inventory js 脚本 作者:haipenge
 */
var Inventory = {
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
			Inventory.multiRemove();
		});
		/**
		 * 当设置sku价格点击时
		 */
		$('.set-price').click(function() {
			if ($(this).find('input').length == 0) {
				var price = $(this).text();
				$(this).empty().append('<input type="text" name="price" value="' + price + '" class="form-control">');
				Inventory.init();
			}
		});
		$('.set-price input[name="price"]').blur(function() {
			var price = $(this).val();
			var productSkuId = $(this).parent().parent().attr('id');
			Inventory.setInventory("", productSkuId, price, "", $(this));
		});
		/**
		 * 当设置Sku库存量的时候
		 */
		$('.set-amount').click(
				function() {
					if ($(this).find('input').length == 0) {
						var amount = $(this).text();
						$(this).empty().append(
								'<input type="text" name="amount" value="' + amount + '" class="form-control">');
						Inventory.init();
					}
				});

		$('.set-amount input[name="amount"]').blur(function() {
			var amount = $(this).val();
			var productSkuId = $(this).parent().parent().attr('id');
			Inventory.setInventory("", productSkuId, "", amount, $(this));
		});
	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="check-single"]'));
		if (checkedIds != '') {
			$.ajax({
				url : '/inventory/inventory/multiRemove',
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
	/**
	 * 设置库存
	 */
	setInventory : function(productId, productSkuId, price, amount, el) {
		$.ajax({
			url : '/inventory/inventory/setInventory',
			type : 'post',
			dataType : 'json',
			data : {
				productId : productId,
				productSkuId : productSkuId,
				price : price,
				amount : amount
			},
			success : function(data, textStatus, xhr) {
				var info='保存成功.';
				
				if (price != '') {
					$(el).parent().empty().append(price);
					info='价格设置成功.';
				}
				if (amount != '') {
					$(el).parent().empty().append(amount);
					info='库存数量设置成功.';
				}
				var msg = new Msg({
					msg : info
				});
				msg.show();
			}
		});
	}
};

$(document).ready(function() {
	Inventory.init();
});