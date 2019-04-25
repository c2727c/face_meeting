var roomData = ''
var rId = ''
var mNo = ''
layui.use(['element', 'layer', 'jquery', 'form', 'upload'], function () {
    var element = layui.element;
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.$;
    var upload = layui.upload;

    //取得会议详情
    var url = path + "/meeting/detail.do";
    // console.log("请求controller的url是:" + url)
    mNo = parent.mNo;
    // console.log('iframe-mNo:' + mNo)
    $.ajax({
        url: url,
        type: "post",
        data: {
            "mNo": mNo,
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            // console.log("data.data是：" + JSON.stringify(data.data))
            var html = template('meetInfo', data.data);
            document.getElementById('content').innerHTML = html;
            //更新人员列表
            html = template('peopleList', data.data);
            document.getElementById('contentPeople').innerHTML = html;

            rId = data['data']['mEventList'][0]['rId']
            console.log('rId:', rId)
            getRoom(rId)
            // console.log('roomData:')
            // console.log(roomData)

            if (parent.urladd == parent.url1) {
                // console.log("隐藏")
                $(".hidediv").css("display", "none");
            }
            tipAll();

            report();

            //编辑会议,负责关闭页面并跳转
            $("#editMeet").on('click', function () {
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                // console.log(index);
                parent.layer.close(index); //再执行关闭
                parent.gotoEditMeet(data.data.mNo)
            });

            //询问框是否删除
            $(".btnDelGroup").click(function () {
                layer.confirm('确定删除当前会议？', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    var url = path + "/meeting/delete.do";
                    // console.log("请求controller的url是:" + url)
                    $.ajax({
                        url: url,
                        type: "post",
                        data: {
                            'mNo': mNo,
                        },
                        dataType: "json",
                        success: function (data) {
                            // console.log(data)
                            if (data.status == 0) {
                                layer.msg(data.msg, {
                                    icon: 1,
                                    time: 1000,
                                }, function () {
                                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                    parent.layer.close(index);
                                    parent.getListMeeting();
                                });
                            } else {
                                layer.msg(data.msg, {
                                    icon: 1,
                                    time: 1000,
                                });
                            }
                        },
                        error: function () {
                            alert("ajax请求失败");
                        }
                    });

                }, function () {

                });
            });

            //上传文件
            var uploadInst = upload.render({
                elem: '#uploadfile',
                url: '/upload/', //上传接口
                done: function (res) {
                    //上传完毕回调
                },
                error: function () {
                    //请求异常回调
                }
            });

        },
        error: function () {
            console.log("ajax请求失败");
        }
    })

    tipAll();

    //鼠标移动提示
    function tipAll() {
        //tip删除
        var tip_index = 0;
        $(".btnDelGroup").mouseenter(function () {
            var index = $(".btnDelGroup").index(this);
            var str = '.btnDelGroup:eq(' + index + ')';
            tip_index = layer.tips('删除会议', str, {
                time: 1000
            });
        });
        $(".btnDelGroup").mouseleave(function () {
            layer.close(tip_index);
        });

        //提示编辑
        var tip_index = 0;
        $(document).on('mouseenter', '#editMeet', function () {
            tip_index = layer.tips('编辑会议', '#editMeet', {
                time: 1000
            });
        }).on('mouseleave', '#editMeet', function () {
            layer.close(tip_index);
        });

        //提示出勤情况
        var tip_index = 0;
        $(document).on('mouseenter', '.infoAttend', function () {
            tip_index = layer.tips('查看详情', '.infoAttend', {
                time: 1000
            });
        }).on('mouseleave', '.infoAttend', function () {
            layer.close(tip_index);
        });

        //提示我要请假
        var tip_index = 0;
        $(document).on('mouseenter', '.askForLeave', function () {
            tip_index = layer.tips('我要请假', '.askForLeave', {
                time: 1000
            });
        }).on('mouseleave', '.askForLeave', function () {
            layer.close(tip_index);
        });

    }

    //获取会议室详情
    function getRoom(rId) {
        var url = path + "/room/roomInfo.do";
        console.log("请求controller的url是:" + url)
        $.ajax({
            url: url,
            type: "post",
            data: {
                'rId': rId,
            },
            dataType: "json",
            success: function (data) {
                // console.log(data)
                var rName = data['data']['rName']
                var rAddr = data['data']['rAddr']
                var rSize = data['data']['rSize']
                $(".rName").html(rName)
                $(".rAddr").html(rAddr)
                $(".rSize").html(rSize)
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("ajax请求失败");
            }
        });
    }

    $(".infoAttend").click(function () {
        report();
        $(".peoAll").slideToggle();
    });

    $(".askForLeave").click(function () {
        layer.prompt({
            formType: 2,
            value: '',
            title: '请假理由',
            // area: ['100%', '%'] //自定义文本域宽高
        }, function (value, index, elem) {
            // alert(value); //得到value
            askLeave()

            layer.close(index);
        });
    })

    function askLeave() {
        var url = path + "/attend/askForLeave.do";
        console.log("请求controller的url是:" + url)
        $.ajax({
            url: url,
            type: "post",
            data: {
                'mNo': mNo,
                'pId': userId,
            },
            dataType: "json",
            success: function (data) {
                console.log("传过来的是：")
                console.log(data)
                report();
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("ajax请求失败");
            }
        });
    }

    function report() {
        var url = path + "/meeting/report.do";
        console.log("请求controller的url是:" + url)
        $.ajax({
            url: url,
            type: "post",
            data: {
                'mNo': mNo,
            },
            dataType: "json",
            success: function (data) {
                console.log(data)
                var html = template('attendance', data.data);
                document.getElementById('attendanceHtml').innerHTML = html;
                element.render('collapse');

                var canceledNum = data['data']['canceledNum']
                var checkedinNum = data['data']['checkedinNum']
                var noshowNum = data['data']['noshowNum']
                $(".showPeople").html(checkedinNum + '/' + (checkedinNum + noshowNum))
                $(".showCancel").html(canceledNum)

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("ajax请求失败");
            }
        });
    }

});