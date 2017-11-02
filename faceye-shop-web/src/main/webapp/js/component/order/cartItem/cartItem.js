/**
*说明:CartItem js 脚本
*作者:@haipenge
*/
var CartItem={
  init:function(){
	  /**
	   * 全选，全不选 
	   */
	  $('input[name="check-all"]').click(function(){
	    Check.onCheck($('input[name="check-all"]'),$('input[name="cartItemId"]'));
	  });
	  $('.multi-remove').click(function(){
		  CartItem.multiRemove();
	  });
	  
	  $('#submit-cart-items').click(function(){
		  CartItem.submitCartItems2Order();
	  });
  },
  /**
   * 批量删除
   */
  multiRemove:function(){
	  var checkedIds=Check.getCheckedIds($('input[name="cartItemId"]'));
	  if(checkedIds!=''){
		  $.ajax({
			  url:'/order/cartItem/multiRemove',
			  type:'post',
			  dataType:'json',
			  data:{
				  ids:checkedIds
			  },
			  success:function(data,textStatux,xhr){
				  var type=data.result?'':'waring'
				  var msg=new Msg({msg:data.msg,type:type});
				  var idArray=checkedIds.split(',');
				  for(var i=0;i<idArray.length;i++){
					  $('#'+idArray[i]).remove();
				  }
				  msg.show();
			  }
		  });
	  }else{
		  var msg=new Msg({msg:'请选择要删除的数据',type:'warning'});
		  msg.show();
	  }
  },
  /**
   * 将购物车中选中的条目，转化为订单,调用 :cartItem_to_pay.jsp
   */
  submitCartItems2Order:function(){
	  var addressId='';
	  $('input[name="addressId"]').each(function(i,r){
		  var isChecked=$(this).is(':checked');
		  if(isChecked){
			  addressId=$(this).val();
		  }
	  });
	  if(addressId==''){
		  var msg=new Msg({msg:'请设置收货地址.',type:'warning'});
		  msg.show();
	  }else{
		  $('#submit-cart-item-2-order-form').trigger('submit');
	  }
  }
};

$(document).ready(function(){
	CartItem.init();
});
