layui.use(['element', 'layer', 'jquery'], function () {
    var element = layui.element;
    var layer = layui.layer;
    var $ = layui.$;

    // console.log("userId" + $.cookie("userId"))
    // console.log("userName" + $.cookie("userName"))

    var userId = $.cookie("userId")
    console.log("useId:" + userId);
    var path = "http://localhost:8080/face_meeting"
    var url = path + "/user/getPerson.do";
    $.ajax({
        url: url,
        type: "post",
        traditional: true, //这使json格式的字符不会被转码
        data: JSON.stringify({
            "pId": userId
        }),
        contentType: 'application/json;charset=UTF-8', //这里的这行是关键
        dataType: "json",
        success: function (data) {
            // console.log(data.data.pName)
            $("#pName").html(data.data.pName);
        },
        error: function () {
            alert("ajax请求失败");
        }
    });

});