layui.use(['element', 'layer', 'jquery', 'laydate'], function () {
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

    console.log("data:")
    var laydate = layui.laydate;
    //根据日期选择显示的会议
    laydate.render({
        elem: '#test1',
        isInitValue: true,
        value: new Date(),
        done: function (value, date, endDate) {
            console.log(value); //得到日期生成的值，如：2017-08-18
            console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
            console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
        }
    });

    // //获取会议列表
    // var userId = $.cookie("userId")
    // var url = path + "/meeting/getMyAttends.do";
    // console.log("请求controller的url是:" + url)
    // $.ajax({
    //     url: url,
    //     type: "post",
    //     traditional: true, // 这使json格式的字符不会被转码
    //     data: JSON.stringify({
    //         "pId": userId
    //     }),
    //     contentType: 'application/json;charset=UTF-8', // 这里的这行是关键
    //     dataType: "json",
    //     success: function (data) {
    //         console.log("data.data是：" + JSON.stringify(data.data))
    //     },
    //     error: function () {
    //         alert("ajax请求失败");
    //     }
    // });
});