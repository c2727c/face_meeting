var mNo = 'number';
var url1 = "/meeting/getMyAttends.do";
var url2 = "/meeting/getMyCreates.do";
var urladd = url1;
var dataMy = '';

layui.use(['element', 'layer', 'jquery', 'laydate', 'form'], function () {
    var element = layui.element;
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.$;

    var d1 = new Date();
    var d = d1.format('yyyy-MM-dd');
    dataMy = d;

    //跳转
    $(".m-info-peo").on('click', function () {
        layer.open({
            type: 2,
            title: '会议详情',
            content: 'meetMy-Info.html',
            scrollbar: false,
            area: ["411px", "98%"],

        });
    });

    window.getListMeeting = function () {
        // console.log('刷新我的会议')
        $('input:radio').each(function () {
            if (this.checked) {
                is_Middle = $(this).val();
                if (is_Middle == 0) {
                    urladd = url1;
                } else {
                    urladd = url2;
                }
            }
        });
        // console.log('当前url'+urladd)
        var datetime = dataMy;
        var url = path + urladd;
        $.ajax({
            url: url,
            type: "post",
            data: {
                "pId": userId,
                "date": datetime
            },
            dataType: "json",
            success: function (data) {
                // console.log(data)
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
                        skin: 'layui-layer-molv',
                        cancel: function (index, layero) {
                            layer.close(index)
                        }
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

    getListMeeting();

    form.render('radio');
    //看单选框选择url
    form.on('radio(my1)', function (data) {
        // console.log(data.elem); //得到radio原始DOM对象
        // console.log(data.value); //被点击的radio的value值
        urladd = url1;
        // console.log(urladd)
        // console.log(dataMy)
        getListMeeting()
    });
    form.on('radio(my2)', function (data) {
        // console.log(data.elem); //得到radio原始DOM对象
        // console.log(data.value); //被点击的radio的value值
        urladd = url2;
        // console.log(urladd)
        // console.log(dataMy)
        getListMeeting()
    });

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
            dataMy = value;
            getListMeeting();
        }
    });

    window.gotoEditMeet = function (data) {
        // console.log('gotoEdit')
        setTimeout(function () {
            window.location.href = "meetMy-Info-edit.html?mNo=" + data;
        }, 400);
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