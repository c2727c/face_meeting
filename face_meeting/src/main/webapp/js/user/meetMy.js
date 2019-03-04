var mNo = 'number';

layui.use(['element', 'layer', 'jquery', 'laydate'], function () {
    var element = layui.element;
    var layer = layui.layer;
    var $ = layui.$;

    var d1 = new Date();
    var d = d1.format('yyyy-MM-dd');
    // console.log("new Date:" + d);
    getListMeeting(d);

    var laydate = layui.laydate;
    //根据日期选择显示的会议
    laydate.render({
        elem: '#test1',
        // theme: '#1E9FFF',
        isInitValue: true,
        value: new Date(),
        done: function (value, date) {
            // console.log("done:")
            // console.log(value); //得到日期生成的值，如：2017-08-18
            // console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
            getListMeeting(value);
        }
    });

    function getListMeeting(value) {
        var userId = $.cookie("userId");
        var datetime = value;
        var url = path + "/meeting/getMyAttends.do";
        $.ajax({
            url: url,
            type: "post",
            data: {
                "pId": userId,
                "date": datetime
            },
            dataType: "json",
            success: function (data) {
                console.log(data)
                if (JSON.stringify(data.data) != "[]") {
                    var html = template('meetList', {
                        data: data.data
                    });
                    document.getElementById('content').innerHTML = html;
                } else {
                    //无会议返回时处理
                    var html = template('noMeet', data);
                    document.getElementById('content').innerHTML = html;
                }
                //跳转
                var index = ''
                $(".btnInfoMeet").on('click', function () {
                    mNo = $(this).data("mno");
                    console.log('mNo:' + mNo)
                    index = layer.open({
                        type: 2,
                        title: '会议详情',
                        content: 'meetMy-Info.html',
                        scrollbar: false,
                        area: ["411px", "96%"],
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
            },
            error: function () {
                console.log("ajax请求失败");
            }
        });
    }


});

//格式化时间为yyyy-mm-dd
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
        (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1,
                RegExp.$1.length == 1 ? o[k] :
                ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}

function gotoEditMeet(index, data) {
    layer.close(index);
    window.location.href = "meetAdd.html"
}