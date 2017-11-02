/**
 * 说明:GeoLibrary js 脚本 作者:@haipenge
 */
var GeoLibrary = {
	init : function() {
		$('select[name="provinceId"]').change(function() {
			var html = '<option value="">选择县/区</option>';
			$('select[name="areaId"]').empty().append(html);
			GeoLibrary.loadCity();
		});
		$('select[name="cityId"]').change(function() {
			GeoLibrary.loadArea();
		});
		// 对地址编辑进行预加载
		var provinceId = $('select[name="provinceId"]').val();
		var cityId = $('select[name="cityId"]').val();
		var cityOptionLength = $('select[name="cityId"]').find('option').length;
		if (provinceId != '' && cityOptionLength == 1) {
			GeoLibrary.load(1, provinceId, $('select[name="cityId"]'), '选择城市');
		}
		var areaOptionLength = $('select[name="areaId"]').find('option').length;
		if (cityId != '' && areaOptionLength == 1) {
			GeoLibrary.load(2, cityId, $('select[name="areaId"]'), '选择县/区');
		}

	},
	/**
	 * 当Geo library 选择时
	 */
	loadProvince : function(el) {
		GeoLibrary.load(0, null, el, '选择省份');

	},
	loadCity : function() {
		var provinceId = $('select[name="provinceId"]').val();
		GeoLibrary.load(1, provinceId, $('select[name="cityId"]'), '选择城市');
	},
	loadArea : function() {
		var cityId = $('select[name="cityId"]').val();
		GeoLibrary.load(2, cityId, $('select[name="areaId"]'), '选择县区');
	},
	/**
	 * 加载地址数据
	 */
	load : function(level, parentId, el, title) {
		if (!parentId || parentId === undefined || parentId === null) {
			parentId = 0;
		}
		$.ajax({
			url : '/lbs/geoLibrary/geoLibraryQuery',
			type : 'post',
			dataType : 'json',
			data : {
				level : level,
				parentId : parentId
			},
			success : function(data, textStatus, xhr) {
				if (data) {
					var currentGeoLibraryId = '';
					if (level === 0) {
						currentGeoLibraryId = $('select[name="provinceId"]')
								.val();
					} else if (level === 1) {
						currentGeoLibraryId = $('select[name="cityId"]').val();
					} else {
						currentGeoLibraryId = $('select[name="areaId"]').val();
					}
					var html = '<option value="">';
					html += title;
					html += '</option>';
					for (var i = 0; i < data.content.length; i++) {
						var record = data.content[i];
						html += '<option value="';
						html += record.id;
						html += '"';
						if (record.id == currentGeoLibraryId) {
							html += 'selected="selected"';
						}
						html += '>';
						html += record.name;
						html += '</option>';
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
