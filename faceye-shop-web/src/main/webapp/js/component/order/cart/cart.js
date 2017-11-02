/**
 * 说明:Cart js 脚本 作者:@haipenge
 */
var Cart = {
	init : function() {
		/**
		 * 全选，全不选
		 */
		$('input[name="check-all"]').click(function() {
			Check.onCheck($('input[name="check-all"]'), $('input[name="check-single"]'));
		});
		$('.multi-remove').click(function() {
			Cart.multiRemove();
		});

	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="check-single"]'));
		if (checkedIds != '') {
			$.ajax({
				url : '/order/cart/multiRemove',
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
						$('#' + idArray[i]).remove();
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
	add2Cart : function() {
		var msg = Product.checkSkuSelect();
		// alert(msg);
		if (msg == '') {
			var dynamicPropertyIdAndDynamicPropertyValueIds = Product.getCheckSkuPropertyAndValus();
			var productId = $('input[name="productId"]').val();
			var quantity = $('input[name="quantity"]').val();
			$.ajax({
				url : '/order/cartItem/addProductSku2Cart',
				type : 'post',
				dataType : 'json',
				data : {
					productId : productId,
					quantity : quantity,
					dpvids : dynamicPropertyIdAndDynamicPropertyValueIds
				},
				success : function(data, textStatus, xhr) {
					//返回购物车中产品数量
//					alert(data.totalQuantity);
					$('#order-cart-item-count').empty().append(data.totalQuantity);
				}
			});
		} else {
			var m = new Msg({
				msg : msg,
				type : 'warning'
			});
			m.show();
		}
	},
	/**
	 * 直接购买
	 */
	direct2Buy:function(){
		var msg = Product.checkSkuSelect();
		// alert(msg);
		if (msg == '') {
			var dynamicPropertyIdAndDynamicPropertyValueIds = Product.getCheckSkuPropertyAndValus();
			// alert(dynamicPropertyIdAndDynamicPropertyValueIds);
			var productId = $('input[name="productId"]').val();
			var quantity = $('input[name="quantity"]').val();
			var url="/order/order/direct2Buy?productId="+productId+"&quantity="+quantity+"&dpvids="+dynamicPropertyIdAndDynamicPropertyValueIds;
			location.href=url;
		} else {
			var m = new Msg({
				msg : msg,
				type : 'warning'
			});
			m.show();
		}
	}
};

$(document).ready(function() {
	Cart.init();
});
