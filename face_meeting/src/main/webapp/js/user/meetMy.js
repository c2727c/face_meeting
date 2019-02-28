layui.use(['element', 'layer', 'jquery'], function () {
    var element = layui.element;
    var layer = layui.layer;
    var $ = layui.$;

    //跳转
    var index = '';
    $(".btnInfoMeet").on('click', function () {
        index = layer.open({
            type: 2,
            title: '会议详情',
            content: 'meetMy-Info.html',
            scrollbar: false,
            area: ["411px", "98%"],

        });
    });

    //提示编辑
    var tip_index = 0;
    $(".lookMeet").mouseenter(function () {
        var index = $(".lookMeet").index(this);

        var str = '.lookMeet:eq(' + index + ')';
        tip_index = layer.tips('点击查看详情', str, {
            time: 1000
        });
    });
    $(".lookMeet").mouseleave(function () {
        layer.close(tip_index);
    });

    //获取用户名
    var userId = $.cookie("userId")
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
        success: function (data) {},
        error: function () {
            alert("ajax请求失败");
        }
    });
});