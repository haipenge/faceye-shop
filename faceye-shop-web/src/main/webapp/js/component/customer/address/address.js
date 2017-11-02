/**
 * 说明:客户->customer -> 送货地址 Address js 脚本 作者:haipenge
 */
var Address = {
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
			Address.multiRemove();
		});
		/**
		 * 保存地址
		 */
		$('#address-save').click(function(){
			Address.doSave();
		});
		
		$('.set-default-address').click(function(){
			Address.setDefault(this);
			return false;
		});
		$('.remove-address').click(function(){
			Address.remove(this);
			return false;
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
                   var html='<tr>';
                   html+='<td>';
                   html+='<input type="radio" name="addressId" value="'+data.id+'" checked>';
                   html+='</td>';
                   html+='<td>';
                   html+=receiveUserName;
                   html+='</td>';
                   html+='<td>';
                   html+=mobile;
                   html+='</td>';
                   html+='<td>';
                   html+=detail;
                   html+='</td>';
                   html+='</tr>';
                   $('input[name="addressId"]').each(function(i,data){
                	   if($(this).is(':checked')){
                		   $(this).prop('checked',false);
                	   }
                   });
                   $('#address-table tbody').append(html);
                   $('#address-add-container').hide();
                   Address.init();
                   CartItem.init();
			}
		});
	},
	/**
	 * 设置为默认地址
	 */
	setDefault:function(dom){
		var addressId=$(dom).parent().parent().attr('id');
		$.ajax({
			url:'/customer/address/setDefault',
			type:'post',
			dataType:'json',
			data:{
				addressId:addressId
			},
			success:function(data,textStatus,xhr){
				 $('input[name="addressId"]').each(function(i,data){
              	   if($(this).is(':checked')){
              		   $(this).prop('checked',false);
              	   }
                 });
				 $('#'+addressId).find('input[name="addressId"]').prop('checked',true);
			}
		});
	},
	/**
	 * 删除
	 */
	remove:function(dom){
		var addressId=$(dom).parent().parent().attr('id');
		alert(addressId);
		$.ajax({
			url:'/customer/address/ajaxRemove',
			type:'post',
			dataType:'json',
			data:{
				addressId:addressId
			},
			success:function(data,textStatus,xhr){
				 $('#'+addressId).remove();
			}
		});
	}
};

$(document).ready(function() {
	Address.init();
});