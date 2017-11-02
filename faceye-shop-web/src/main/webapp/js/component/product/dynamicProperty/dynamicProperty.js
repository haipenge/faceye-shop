/**
 * 说明:产品->product -> 产品属性 DynamicProperty js 脚本 作者:haipenge DynamiceProperty.id ->
 * 1:文本，2：整数,3:小数,4:布尔,5:枚举
 */
var DynamicProperty = {
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
			DynamicProperty.multiRemove();
		});

		var dataTypeId = $('select[name="dataType.id"]').val();
		if (dataTypeId) {
			DynamicProperty.onDataTypeSelect(dataTypeId);
		}
		$('select[name="dataType.id"]').change(function() {
			var dataTypeId = $(this).val();
			DynamicProperty.onDataTypeSelect(dataTypeId);
		});
		$('input[name="isSku"]').click(function() {
			DynamicProperty.onIsSkuChecked();
		});
		$('input[name="isRequired"]').click(function(){
			DynamicProperty.onIsSkuChecked();
		});
	},
	/**
	 * 批量删除
	 */
	multiRemove : function() {
		var checkedIds = Check.getCheckedIds($('input[name="check-single"]'));
		if (checkedIds != '') {
			$.ajax({
				url : '/product/dynamicProperty/multiRemove',
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
	 * 根据dataType的值，变换显示与选中与否
	 */
	onDataTypeSelect : function(dataTypeId) {
		if (dataTypeId === '2' || dataTypeId === '3') {
			$('input[name="unit"]').parent().parent().show();
		} else {
			$('input[name="unit"]').val('');
			$('input[name="unit"]').parent().parent().hide();
		}
		if (dataTypeId != '5') {
			$('#isSku1').prop('checked', false);
			$('#isSku2').prop('checked', true);
			$('input[name="isSku"]').parent().parent().hide();
		} else {
			$('input[name="isSku"]').parent().parent().show();
			$('#isSku1').prop('checked', false);
			$('#isSku2').prop('checked', true);
			DynamicProperty.onIsSkuChecked();
		}
	},
	/**
	 * 当isSku选中时
	 */
	onIsSkuChecked : function() {
		var isSku1Checked = $('#isSku1').is(":checked");
		if (isSku1Checked) {
			$('#isRequired1').prop('checked', true);
			$('#isRequired2').prop('checked', false);
		}
	}
	
};

$(document).ready(function() {
	DynamicProperty.init();
});