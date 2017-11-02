/**
 * 说明:客户->customer -> 送货地址 Address js 脚本 作者:haipenge
 */
var Address = {
	isShowAllAddress : false,
	init : function() {
		/**
		 * 全选、全不选
		 */
		$('input[name="check-all"]').click(
				function() {
					Check.onCheck($('input[name="check-all"]'),
							$('input[name="check-single"]'));
				});
		/**
		 * 执行删除
		 */
		$('.multi-remove').click(function() {
			Address.multiRemove();
		});
		/**
		 * 保存
		 */
		$('.customer-address-save-btn').click(function() {
			Address.saveCustomerAddress();
		});
		/**
		 * 保存地址
		 */
		$('#address-save').click(function() {
			Address.doSave();
		});

		$('.set-default-address').click(function() {
			Address.setDefault(this);
			return false;
		});
		$('.remove-address').click(function() {
			Address.remove(this);
			return false;
		});
		/**
		 * 展开全部地址与隐藏
		 */
		$('#show-toggle').click(function() {

			Address.addressShowOrHideToggle();
		});
		/**
		 * 实始化时只显示默认地址
		 */
		$('#address .row').each(
				function(i, data) {
					var isChecked = $(this).find('input[name="addressId"]').is(
							':checked');
					if (!isChecked) {
						$(this).hide();
					}
				});
	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="check-single"]'));
		if (checkedIds != '') {
			$.ajax({
				url : '/customer/address/multiRemove',
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
	doSave : function() {
		var receiveUserName = $('input[name="receiveUserName"]').val();
		var mobile = $('input[name="mobile"]').val();
		var detail = $('input[name="detail"]').val();
		$.ajax({
			url : '/customer/address/ajaxSave',
			type : 'post',
			dataType : 'json',
			data : {
				receiveUserName : receiveUserName,
				mobile : mobile,
				detail : detail
			},
			success : function(data, textStatus, xhr) {
				var id = 'address';
				if ($('#' + id).find('div').length === 0) {
					$('#' + id).empty();
				}
				$('input[name="addressId"]').each(function(i, data) {
					if ($(this).is(':checked')) {
						$(this).prop('checked', false);
					}
				});
				var html = '';
				html += '<div class="row" id="' + data.id + '">';
				html += '<div class="col-xs-2 col-md-1 col-lg-1">';
				html += '<input type="radio" name="addressId" value="'
						+ data.id + '" checked>';
				html += '</div>';
				html += '<div class="col-xs-10 col-md-10 col-lg-10">'
						+ receiveUserName + ',' + mobile + ',' + detail
						+ '</div>';
				html += '</div>';
				$('#' + id).append(html);
				$('#address-add-container').hide();
				Address.init();
				CartItem.init();
			}
		});
	},
	/**
	 * 设置为默认地址
	 */
	setDefault : function(dom) {
		var addressId = $(dom).parent().parent().attr('id');
		$.ajax({
			url : '/customer/address/setDefault',
			type : 'post',
			dataType : 'json',
			data : {
				addressId : addressId
			},
			success : function(data, textStatus, xhr) {
				$('input[name="addressId"]').each(function(i, data) {
					if ($(this).is(':checked')) {
						$(this).prop('checked', false);
					}
				});
				$('#' + addressId).find('input[name="addressId"]').prop(
						'checked', true);
				$('.default-address').find('.content:nth-child(2)').append('<a href="#" class="set-default-address btn btn-sm btn-success-outline">设为默认</a>');
				$('.default-address').removeClass('default-address');
				$(dom).parent().parent().addClass('default-address');
				$(dom).remove();
				Address.init();
			}
		});
	},
	/**
	 * 删除
	 */
	remove : function(dom) {
		var addressId = $(dom).parent().parent().attr('id');
		$.ajax({
			url : '/customer/address/ajaxRemove',
			type : 'post',
			dataType : 'json',
			data : {
				addressId : addressId
			},
			success : function(data, textStatus, xhr) {
				$('#' + addressId).remove();
			}
		});
	},
	/**
	 * 显示全部或隐藏未选中地址，在确认订单时使用
	 */
	addressShowOrHideToggle : function() {
		if (Address.isShowAllAddress) {
			$('#address .row').each(
					function(i, data) {
						var isChecked = $(this).find('input[name="addressId"]')
								.is(':checked');
						if (isChecked) {
							$(data).show();
						} else {
							$(data).hide();
						}
					});
			$('#show-toggle').empty().append("显示全部");
			Address.isShowAllAddress = false;
		} else {
			$('#address .row').each(
					function(i, data) {
						var isChecked = $(this).find('input[name="addressId"]')
								.is(':checked');
						if (!isChecked) {
							$(data).show();
						}
					});
			$('#show-toggle').empty().append("隐藏");
			Address.isShowAllAddress = true;
		}
	},
	/**
	 * 保存地址，我的-》收货地址-》新增、编辑
	 */
	saveCustomerAddress : function() {
		var provinceId = $('select[name="provinceId"]').val();
		var cityId = $('select[name="cityId"]').val();
		var areaId = $('select[name="areaId"]').val();
		var receiveUserName=$('input[name="receiveUserName"]').val();
		var mobile=$('input[name="mobile"]').val();
		var detail=$('input[name="detail"]').val();
		var msg = '';
		if (provinceId == '') {
			msg = '请选择省份.';
		} else if (cityId == '') {
			msg = '请选择城市';
		} else if (areaId == '') {
			msg = '请选择地区';
		}else if(receiveUserName==''){
			msg='收货人不能为空.';
		}else if(mobile==''){
			msg='手机号不能为空.';
		}else if (detail==''){
			msg='详细地址不能为空.';
		}
		if (msg != '') {
			var tip = new Msg({
				msg : msg,
				type:'warning'
			});
			tip.show();
			return;
		} else {
			$('#customer-address-form').submit();
		}
	}
};

$(document).ready(function() {
	Address.init();
});