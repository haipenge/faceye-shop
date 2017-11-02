/**
 * 说明:GeoLibrary js 脚本 作者:@haipenge
 */
var GeoLibrary = {
	init : function() {
		/**
		 * 全选，全不选
		 */
		$('input[name="check-all"]').click(
				function() {
					Check.onCheck($('input[name="check-all"]'),
							$('input[name="check-single"]'));
				});
		$('.multi-remove').click(function() {
			GeoLibrary.multiRemove();
		});
		$('select[name="provinceId"]').change(function(){
			var html='<option value="">区</option>';
			$('select[name="areaId"]').empty().append(html);
			GeoLibrary.loadCity();
		});
		$('select[name="cityId"]').change(function(){
			GeoLibrary.loadArea();
		});

	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="check-single"]'));
		if (checkedIds != '') {
			$.ajax({
				url : '/lbs/geoLibrary/multiRemove',
				type : 'post',
				dataType : 'json',
				data : {
					ids : checkedIds
				},
				success : function(data, textStatux, xhr) {
					var type = data.result ? '' : 'warning';
					var msg = new Msg({
						msg : data.msg,
						type : type
					});
					if (data.result) {
						var idArray = checkedIds.split(',');
						for (var i = 0; i < idArray.length; i++) {
							$('#' + idArray[i]).remove();
						}
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
	 * 当Geo library 选择时
	 */
	loadProvince:function(el){
		GeoLibrary.load(0,null,el,'省份');
	  
	},
	loadCity:function(){
		var provinceId=$('select[name="provinceId"]').val();
	    GeoLibrary.load(1,provinceId,$('select[name="cityId"]'),'城市');
	},
	loadArea:function(){
		var cityId=$('select[name="cityId"]').val();
		GeoLibrary.load(2,cityId,$('select[name="areaId"]'),'区');
	},
	/**
	 * 加载地址数据
	 */
	load:function(level,parentId,el,title){
		if(!parentId || parentId ===undefined || parentId===null){
			parentId=0;
		}
		$.ajax({
			url:'/lbs/geoLibrary/geoLibraryQuery',
			type:'post',
			dataType:'json',
			data:{
				level:level,
				parentId:parentId
			},
			success:function(data,textStatus,xhr){
				if(data){
					var html='<option value="">';
					html+=title;
					html+='</option>';
					for(var i=0;i<data.content.length;i++){
						var record=data.content[i];
						html+='<option value="';
						html+=record.id;
						html+='">';
						html+=record.name;
						html+='</option>';
					}
					$(el).empty().append(html);
				}
			}
		});
	}
};

$(document).ready(function() {
	GeoLibrary.init();
    GeoLibrary.loadProvince($('select[name="provinceId"]'));
});
