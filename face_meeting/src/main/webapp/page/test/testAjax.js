layui.use(['element', 'layer', 'jquery'], function () {
	var element = layui.element;
	var layer = layui.layer;
	var $ = layui.$;

	// var userId = 'user01'
	// console.log(userId)
	// var datatime = '2019-03-02'
	// console.log(datatime)

	// var url = path + "/meeting/detail.do";
	// console.log("请求controller的url是:" + url)
	// $.ajax({
	// 	url : url,
	// 	type : "post",
	// 	data:{
	// 		'mNo': 1,
	// 	},
	// 	dataType : "json",
	// 	success : function(data) {
	// 		console.log("data.data是：" + JSON.stringify(data.data))
	// 		$("#test1").html(JSON.stringify(data.data));
	// 	},
	// 	error : function() {
	// 		alert("ajax请求失败");
	// 	}
	// });

	var url = path + '/user/getPerson.do';
	var userId = 'user01';
	$.ajax({
		url: url,
		type: "post",
		traditional: true, //这使json格式的字符不会被转码
		data: JSON.stringify({
			"pId": userId,
		}),
		contentType: 'application/json;charset=UTF-8', //这里的这行是关键
		dataType: "json",
		// dataType: "text",
		success: function (data) {
			console.log(data)
			console.log("data.data是：" + JSON.stringify(data.data))
			$("#test1").html(JSON.stringify(data));
		},
		error: function () {
			console.log("ajax请求失败");
		}
	});

	//获取房间
	// var mSize = '20';
	// var startDate = '2019-03-05'
	// var startTime = '17:00'
	// var endTime = '17:30'
	// var url = path + "/meeting/recommendRoom.do";
	// console.log("请求controller的url是:" + url)
	// $.ajax({
	// 	url: url,
	// 	type: "post",
	// 	data: {
	// 		'mSize': mSize,
	// 		'startDate': startDate,
	// 		'startTime': startTime,
	// 		'endTime': endTime, 
	// 	},
	// 	dataType: "json",
	// 	success: function (data) {
	// 		console.log("传过来的是：")
	// 		console.log(data)
	// 		console.log("data.data是：" + JSON.stringify(data.data))
	// 		$("#test1").html(JSON.stringify(data));
	// 	},
	// 	error: function (XMLHttpRequest, textStatus, errorThrown) {
	// 		console.log("ajax请求失败");
	// 	}
	// });

});