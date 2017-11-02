/**
 * 说明:订单->order -> 订单 Order js 脚本 作者:haipenge
 */
var Order = {
	init : function() {
		/**
		 * 全选、全不选
		 */
		$('input[name="check-all"]').click(function() {
			Check.onCheck($('input[name="check-all"]'), $('input[name="check-single"]'));
		});
		// Order.checkWeixinBridge();
		/**
		 * 执行删除
		 */
		$('.multi-remove').click(function() {
			Order.multiRemove();
		});
		$('#btn-weixin-pay').click(function() {
			Order.weixinPay();
		});
		$('.list-pay').click(function(){
			var orderId=$(this).attr('id');
			Order.pay(orderId);
		});
		$('.detail-pay').click(function(){
			var orderId=$(this).attr('id');
			Order.pay(orderId);
		});
	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="check-single"]'));
		if (checkedIds != '') {
			$.ajax({
				url : '/order/order/multiRemove',
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
	payRouter : {

	},
	/**
	 * 购物车-》结算-》支付 或：商品-》购买->支付
	 */
	weixinPay : function() {
		if (addressId == '') {
			var msg = new Msg({
				msg : '请设置收货地址.',
				type : 'warning'
			});
			msg.show();
		} else {
			// $('#submit-cart-item-2-order-form').trigger('submit');
		}
		var addressId = $('input[name="addressId"]:checked').val();
		var productSkuIds = "";
		var url = location.href.split('#')[0];
		//alert(url);
		// url=encodeURIComponent(url);
		$('input[name="productSkuId"]').each(function(i, el) {
			productSkuIds += $(el).val();
			productSkuIds += ',';
		});// -> arry
		var quantities = "";
		$('input[name="quantity"]').each(function(i, el) {
			quantities += $(el).val();
			quantities += ",";
		});// -> array
		$.ajax({
			url : '/order/order/orderConfirm',
			type : 'post',
			dataType : 'json',
			data : {
				addressId : addressId,
				'productSkuIds' : productSkuIds,
				'quantities' : quantities,
				url : url
			},
			success : function(data, textStatus, xhr) {
				Weixin.pay(data);
			}
		});
	},
	/**
	 * 在列表页/详情页点击支付按钮
	 */
	pay : function(orderId) {
		var url = location.href;
		url = url.split('#')[0];
		$.ajax({
			url : '/order/order/toPay',
			type : 'post',
			dataType : 'json',
			data : {
				orderId : orderId,
				url : url
			},
			success : function(data, textStatus, xhr) {
				Weixin.pay(data);
			}
		});
	}
};

$(document).ready(function() {
	Order.init();
});