/**
*说明:客户->customer -> 客户 Customer  js 脚本
*作者:haipenge
*/
var Customer={
  init:function(){
       /**
       *全选、全不选
       */
       $('input[name="check-all"]').click(function(){
	     Check.onCheck($('input[name="check-all"]'),$('input[name="check-single"]'));
	    });
	    /**
	    *执行删除
	    */
	    $('.multi-remove').click(function(){
	       Customer.multiRemove();
	    });
  },
  /**
   * 批量删除
   */
  multiRemove:function(){
	  var checkedIds=Check.getCheckedIds($('input[name="check-single"]'));
	  if(checkedIds!=''){
		  $.ajax({
			  url:'/customer/customer/multiRemove',
			  type:'post',
			  dataType:'json',
			  data:{
				  ids:checkedIds
			  },
			  success:function(data,textStatux,xhr){
				  var msg=new Msg({msg:'数据删除成功'});
				  var idArray=checkedIds.split(',');
				  for(var i=0;i<idArray.length;i++){
				     var id=idArray[i];
				     $('#'+id).remove();
				  }
				  msg.show();
			  }
		  });
	  }else{
		  var msg=new Msg({msg:'请选择要删除的数据',type:'warning'});
		  msg.show();
	  }
  }
};

$(document).ready(function(){
	Customer.init();
});