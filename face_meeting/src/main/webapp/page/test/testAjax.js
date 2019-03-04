layui.use(['element', 'layer', 'jquery'], function () {
	var element = layui.element;
	var layer = layui.layer;
	var $ = layui.$;

	// var userId = 'user01'
	// console.log(userId)
	// var datatime = '2019-03-02'
	// console.log(datatime)

	var url = path + "/meeting/detail.do";
	console.log("请求controller的url是:" + url)
	$.ajax({
		url : url,
		type : "post",
		data:{
			'mNo': 1,
		},
		dataType : "json",
		success : function(data) {
			console.log("data.data是：" + JSON.stringify(data.data))
			$("#test1").html(JSON.stringify(data.data));
		},
		error : function() {
			alert("ajax请求失败");
		}
	});

	// //测试图片
	// var userId = 'user01';
	// var userPass = '123456';
	// var url = path + "/user/user-login.do";
	// console.log("请求controller的url是:" + url)
	// $.ajax({
	// 	url: url,
	// 	type: "post",
	// 	traditional: true, //这使json格式的字符不会被转码
	// 	data: JSON.stringify({
	// 		"pId": userId,
	// 		"pPass": userPass
	// 	}),
	// 	contentType: 'application/json;charset=UTF-8', //这里的这行是关键
	// 	dataType: "json",
	// 	// dataType: "text",
	// 	success: function (data) {
	// 		console.log("传过来的是：")
	// 		console.log(data)
	// 		console.log("图片信息")
	// 		console.log(data.data.pIcon)

	// 		function blobToDataURI(blob, callback) {
	// 			var reader = new FileReader();
	// 			reader.onload = function (e) {
	// 				callback(e.target.result);
	// 			}
	// 			reader.readAsDataURL(blob);
	// 		}
	// 		var blob = data.data.pIcon;
	// 		blobToDataURI(blob, function (data) {
	// 			console.log(data);
	// 			$('#userFace').attr('src', data); //图片链接（base64）
	// 		});
	// 	},
	// 	error: function (XMLHttpRequest, textStatus, errorThrown) {
	// 		console.log("ajax请求失败");
	// 	}
	// });

});