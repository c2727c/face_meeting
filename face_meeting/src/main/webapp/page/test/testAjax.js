layui.use([ 'element', 'layer', 'jquery' ], function() {
	var element = layui.element;
	var layer = layui.layer;
	var $ = layui.$;

	// 根据cookie中得userId查询用户信息
	// var userId = $.cookie("userId")
	var userId = 'user01'
	console.log(userId)
	// var userId = 'user01'
	var datatime = '2019-03-02'
	console.log(datatime)

	var url = path + "/meeting/getMyAttends.do";
	console.log("请求controller的url是:" + url)
	$.ajax({
		url : url,
		type : "post",
		data:{
			"pId": userId,
			"date": datatime
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

});