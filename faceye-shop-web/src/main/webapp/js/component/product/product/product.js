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
		// $('.btn').button();
		// $().button("toggle")
		// alert($('label').find('input').length);
		// $('label').find('input').click(function(){alert(1);});
		// $('label .btn').click(function(){
		// alert('label click.');
		// });
		// $('input[type="radio"]').click(function(){
		// var name=$(this).attr('name');
		// var value=$(this).val();
		// alert('name:'+name+",value:"+value);
		// });
		/**
		 * 商品详情页图片切换
		 */
		$('.small-product-pic').parent().mouseenter(function() {
			var filename = $(this).find('img').attr('data-filename');
			$('#main-product-pic').attr('src', '/UploadServlet?getfile=' + filename);
		});
		/**
		 * 点击加号，增加产品数量
		 */
		$('#increace-product-count').click(function() {
			var count = $(this).parent().find('input').val();
			$(this).parent().find('input').val(parseInt(count) + 1);
			return false
		});
		/**
		 * 点击减号，减少产品数量
		 */
		$('#reduce-product-count').click(function() {
			var count = $(this).parent().find('input').val();
			if (count > 1) {
				$(this).parent().find('input').val(parseInt(count) - 1);
			}
			return false;
		});
		
		$('.sku-container-key input[type="radio"]').change(function(){
//			alert($(this).val()+":"+$(this).is(':checked'));
			Product.afterAllSkuPropertyChecked();
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
	 * 检查SKU是否选择
	 */
	checkSkuSelect:function(){
		var msg='';
//		alert('sku check start.')
		$('.sku-container-key').each(function(i,data){
			var skuPropertyName=$(this).find('.key').text();
			skuPropertyName = skuPropertyName.replace(":","");
//			alert(skuPropertyName);
//			alert('check sku property is'+skuPropertyName);
			var isChecked=false;
			$(this).find('input[type="radio"]').each(function(j,r){
				 var innerSsChecked=$(this).is(':checked');
				if(innerSsChecked){
					isChecked=true;
				}
			});
			if(!isChecked){
				msg+=skuPropertyName;
			}
		});
		if(msg !=''){
			msg='请选择 '+msg;
		}
		return msg;
	},
	/**
	 * 所有SKU属性选择后，查询SKU对像，加载对应价格
	 */
	afterAllSkuPropertyChecked:function(){
		var totalSkuPropertyCount=$('.sku-container-key').length;
//		alert(totalSkuPropertyCount);
		var msg=Product.checkSkuSelect();
//		alert(msg);
		if(totalSkuPropertyCount>0 && msg ==''){
			var productId=$('input[name="productId"]').val();
			var dynamicPropertyAndValues=Product.getCheckSkuPropertyAndValus();
			$.ajax({
				url:'/product/productSku/getProductSkuByDynamicPropertyAndValues',
				type:"post",
				dataType:'json',
				data:{
					productId:productId,
					dynamicPropertyAndValues:dynamicPropertyAndValues
				},
				success:function(data,textStatx,xhr){
					$('.product-sku-price').empty().append(data.priceYuan);
				}
				
			});
		}
	},
	/**
	 * 取得被选中的sku属性及值
	 * 数据格式:dynamicProperty.id-dynamicPropertyValue.id|dynamicProperty.id-dynamicPropertyValue.id
	 */
	getCheckSkuPropertyAndValus:function(){
		var dynamicPropertyIdAndDynamicPropertyValueIds = '';
		$('label input[type="radio"]').each(function(i, index) {
			var isChecked = $(this).is(':checked');
			if (isChecked) {
				var name = $(this).attr('name');
				var value = $(this).val();
				dynamicPropertyIdAndDynamicPropertyValueIds += name + '-' + value;
				dynamicPropertyIdAndDynamicPropertyValueIds += '|';
			}
		});
		return dynamicPropertyIdAndDynamicPropertyValueIds;
	}
};

$(document).ready(function() {
	Product.init();
});