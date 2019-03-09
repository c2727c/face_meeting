layui.use(["element", "layer", "jquery", "form"], function () {
    var element = layui.element;
    var layer = layui.layer;
    var $ = layui.jquery;
    var form = layui.form;

    getMyGroup();

    //弹出工作组
    $(".btnAddPeople").on("click", function () {
        layer.open({
            type: 2,
            title: ["加入新工作组"],
            content: "meetGroup-do.html",
            area: ["411px", "98%"],
            skin: 'layui-layer-molv',
            cancel: function (index, layero) {
                //先关闭弹窗后刷新我的工作组
                layer.close(index);
                getMyGroup();
            }
        });
    });

    function getMyGroup() {
        // 取得我加入的组
        var url = path + "/user/group/findMyGroup.do";
        console.log("请求controller的url是:" + url)
        $.ajax({
            url: url,
            type: "post",
            data: {
                'pId': userId,
            },
            dataType: "json",
            success: function (data) {
                // console.log(data)
                if (data.status == 0) {
                    // console.log("读入")
                    var html = template('meetGroup', {
                        data: data.data
                    });
                    document.getElementById('content').innerHTML = html;
                    element.render('collapse');


                    //阻止事件冒泡
                    $(function () {
                        $(".noshow").click(function (event) {
                            event.stopPropagation();
                        });
                    });

                    //询问框是否删除
                    $(".btnDelGroup").click(function () {
                        var gId = $(this).data('gid');
                        // console.log(gId)
                        var url = path + "/user/group/quit.do";
                        // console.log("请求controller的url是:" + url)

                        layer.confirm('确定退出当前工作组？', {
                            btn: ['确定', '取消'] //按钮
                        }, function () {
                            $.ajax({
                                url: url,
                                type: "post",
                                data: {
                                    'pId': userId,
                                    'gId': gId,
                                },
                                dataType: "json",
                                success: function (data) {
                                    // console.log(data)
                                    layer.msg('退出完成', {
                                        icon: 1,
                                        time: 1000,
                                    }, function () {
                                        // window.location.reload() //刷新当前页面
                                        getMyGroup();
                                    });
                                },
                                error: function () {
                                    console.log("ajax请求失败");
                                }
                            });

                        }, function () {

                        });
                    });


                    //tip删除工作组
                    var tip_index = 0;
                    $(".btnDelGroup").mouseenter(function () {
                        var index = $(".btnDelGroup").index(this);
                        var str = '.btnDelGroup:eq(' + index + ')';
                        tip_index = layer.tips('退出工作组', str, {
                            time: 1000
                        });
                    });
                    $(".btnDelGroup").mouseleave(function () {
                        layer.close(tip_index);
                    });

                } else {
                    layer.msg("请求我的工作组失败", {
                        time: '1000',
                        icon: 5,
                    })
                }
            },
            error: function () {
                console.log("ajax请求失败");
            }
        });

    }

});