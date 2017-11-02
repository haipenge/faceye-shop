/**
 * 说明:CartItem js 脚本 作者:@haipenge
 */
var CartItem = {
	cartItemEdit:0,
	init : function() {
		/**
		 * 全选，全不选
		 */
		$('input[name="check-all"]').click(function() {
			Check.onCheck($('input[name="check-all"]'), $('input[name="cartItemId"]'));
		});
		/**
		 * 重新选择购物车条目时，重新计算价格
		 */
		$('input[name="cartItemId"]').click(function() {
			var isChecked = $(this).is(':checked');

			// 不管选中，还是弃选，都需要重新计算总价。
			CartItem.computeSelectedCartItemsTotalFee();
		});
		$('.multi-remove').click(function() {
			CartItem.multiRemove();
		});

		$('#submit-cart-items').click(function() {
			CartItem.submitCartItems2Order();
		});
		/**
		 * 点击编辑购物车时
		 */
		$('#edit-cart-item').click(function(){
			if(CartItem.cartItemEdit===0){
				$('#edit-cart-item').html('完成');
				$('#edit-cart-item').show();
				$('#submit-cartItem-container').hide();
				$('#edit-cartItem-container').show();
				CartItem.cartItemEdit=1;
			}else{
				$('#edit-cart-item').html('编辑');
				$('#edit-cart-item').show();
				$('#submit-cartItem-container').show();
				$('#edit-cartItem-container').hide();
				
				CartItem.cartItemEdit=0;
				CartItem.computeSelectedCartItemsTotalFee();
			}
		});
		$('#edit-cartItem-container').hide();
		
	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="cartItemId"]'));
		if (checkedIds != '') {
			$.ajax({
				url : '/order/cartItem/multiRemove',
				type : 'post',
				dataType : 'json',
				data : {
					ids : checkedIds
				},
				success : function(data, textStatux, xhr) {
					var type = data.result ? '' : 'waring'
					var msg = new Msg({
						msg : data.msg,
						type : type
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
	/**
	 * 将购物车中选中的条目，转化为订单,调用 :cartItem_to_pay.jsp
	 */
	submitCartItems2Order : function() {
		var addressId = '';
		$('input[name="addressId"]').each(function(i, r) {
			var isChecked = $(this).is(':checked');
			if (isChecked) {
				addressId = $(this).val();
			}
		});
		if (addressId == '') {
			var msg = new Msg({
				msg : '请设置收货地址.',
				type : 'warning'
			});
			msg.show();
		} else {
			$('#submit-cart-item-2-order-form').trigger('submit');
		}
	},
	/**
	 * 改变购物车中待支付商品的选中情况时触发
	 */
	computeSelectedCartItemsTotalFee : function() {
		var cartItemIds = Check.getCheckedIds($('input[name="cartItemId"]'));
		if (cartItemIds) {
			$.ajax({
				url : '/order/cartItem/computeSelectedCartItemsTotalFee',
				type : 'post',
				dataType : 'json',
				data : {
					cartItemIds : cartItemIds
				},
				success : function(data, textStatus, xhr) {
					if (data && data.totalFeeYuan > 0.0) {
						var html = '<h4>';
						html += '总价:';
						html += '&nbsp;&nbsp;';
						html += data.totalFeeYuan;
						html += '&nbsp;&nbsp;';
						html += '<button type="submit" class="btn  btn-success-outline pull-right">结算</button>';
						$('#submit-cartItem-container').empty().append(html);
					} else {
						$('#submit-cartItem-container').empty().append('<h4>系统繁忙，请稍候...</h4>');
					}
				},
				beforeSend : function() {
					$('#submit-cartItem-container').empty().append('<h4>正在计算价格...</h4>');
				}
			});
		} else {
			$('#submit-cartItem-container').empty().append('<h4>您还没有选择要结算的商品</h4>');
		}
	}
};

$(document).ready(function() {
	CartItem.init();
});
