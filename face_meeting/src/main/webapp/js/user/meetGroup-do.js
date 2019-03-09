layui.use(["element", "layer", "jquery", "form"], function () {
    var element = layui.element;
    var layer = layui.layer;
    var $ = layui.jquery;
    var form = layui.form;

    getAllGroup();

    function getAllGroup() {
        // 取得所有组
        var url = path + "/user/group/findAllGroup.do";
        console.log("请求controller的url是:" + url)
        $.ajax({
            url: url,
            type: "get",
            dataType: "json",
            success: function (data) {
                if (data.status == 0) {
                    console.log(data)
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

                    //tip加入工作组
                    var tip_index = 0;
                    $(".btnDelGroup").mouseenter(function () {
                        var index = $(".btnDelGroup").index(this);
                        var str = '.btnDelGroup:eq(' + index + ')';
                        tip_index = layer.tips('加入该工作组', str, {
                            time: 1000
                        });
                    });
                    $(".btnDelGroup").mouseleave(function () {
                        layer.close(tip_index);
                    });

                    //询问框是否加入
                    $(".btnDelGroup").click(function () {
                        var userId = localStorage.getItem('userId')
                        var gId = $(this).data('gid');
                        // console.log(gId)
                        // console.log(userId)
                        var url = path + "/user/group/join.do";
                        // console.log("请求controller的url是:" + url)

                        layer.confirm('确定加入当前工作组？', {
                            btn: ['确定', '取消'] //按钮
                        }, function () {
                            $.ajax({
                                url: url,
                                type: "post",
                                data: {
                                    'gId': gId,
                                    'pId': userId,
                                },
                                dataType: "json",
                                success: function (data) {
                                    console.log(data)
                                    layer.msg('加入成功', {
                                        icon: 1,
                                        time: 1000,
                                    }, function () {
                                        // window.location.reload() //刷新当前页面
                                        getAllGroup();
                                    });
                                },
                                error: function () {
                                    console.log("ajax请求失败");
                                }
                            });

                        }, function () {

                        });
                    });


                }
            },
            error: function () {
                console.log("ajax请求失败");
            }
        });
    }

});