layui.use(['element', 'layer', 'jquery', 'form', 'upload'], function () {
    var element = layui.element;
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.$;
    var upload = layui.upload;

    //取得会议详情
    var url = path + "/meeting/detail.do";
    // console.log("请求controller的url是:" + url)
    var mNo = parent.mNo;
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

            if (parent.urladd == parent.url1) {
                // console.log("隐藏")
                $(".hidediv").css("display", "none");
            }
            tipAll();

            //编辑会议,负责关闭页面并跳转
            $("#editMeet").on('click', function () {
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                // console.log(index);
                parent.layer.close(index); //再执行关闭
                parent.gotoEditMeet(index, data.data)
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


            //添加人员
            $(".btnAddPeople").on('click', function () {
                layer.open({
                    type: 2,
                    title: '添加人员',
                    content: 'meetAdd-people.html',
                    scrollbar: false,
                    area: ["411px", "98%"],
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


    }


});