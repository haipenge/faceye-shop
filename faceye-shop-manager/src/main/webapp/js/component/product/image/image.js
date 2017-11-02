/**
 * 说明:Image js 脚本 作者:@haipenge
 */
var Image={
  init:function(){
	  /**
		 * 全选，全不选
		 */
	  $('input[name="check-all"]').click(function(){
	    Check.onCheck($('input[name="check-all"]'),$('input[name="check-single"]'));
	  });
	  $('.multi-remove').click(function(){
		  Image.multiRemove();
	  });
	  
  },
  /**
	 * 批量删除
	 */
  multiRemove:function(){
	  var checkedIds=Check.getCheckedIds($('input[name="check-single"]'));
	  if(checkedIds!=''){
		  $.ajax({
			  url:'/product/image/multiRemove',
			  type:'post',
			  dataType:'json',
			  data:{
				  ids:checkedIds
			  },
			  success:function(data,textStatux,xhr){
				  var type = data.result? '':'warning';
				  var msg=new Msg({msg:data.msg,type:type});
				  if(data.result){
				  var idArray=checkedIds.split(',');
				  for(var i=0;i<idArray.length;i++){
					  $('#'+idArray[i]).remove();
				  }
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
   * 设为默认图片
   */
  setDefault:function(filename){
	  if(filename){
		  $.ajax({
			  url:'/product/image/setDefaultImage',
			  type:'post',
			  dataType:'json',
			  data:{
				  filename:filename
			  },
			  success:function(data,textStatus,xhr){
				  var msg=new Msg({msg:'设置成功'});
				  msg.show();
			  }
		  });
	  }
  }
};

$(document).ready(function(){
	Image.init();
});
