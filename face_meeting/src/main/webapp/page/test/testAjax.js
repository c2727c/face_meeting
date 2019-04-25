layui.use(['element', 'layer', 'jquery'], function () {
	var element = layui.element;
	var layer = layui.layer;
	var $ = layui.$;

	var url = path + "/meeting/conflictTest.do";
	console.log('url:' + url)
	var attendList = '1210051224@qq.com,1659010991@qq.com,2319277867@qq.com,2516485257@qq.com'
	var startDate = '2019-04-14'
	var startTime = '14:00'
	var endTime = '23:00'
	// layer.msg(url);
	$.ajax({
		url: url,
		type: "post",
		data: {
			'attendList': attendList,
			'startDate': startDate,
			'startTime': startTime,
			'endTime': endTime,
		},
		dataType: "json",
		success: function (data) {
			console.log(attendList)
			console.log(data)
			if (data.data != "") {
				var strPeo = data.data[0];
				console.log(strPeo)
			}
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			// layer.alert("ajax请求失败");
			console.log('ajax请求失败')
		}
	});


});