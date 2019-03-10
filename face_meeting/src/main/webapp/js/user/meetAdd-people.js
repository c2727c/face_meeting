var proposals = [];
var attend = '';

layui.use(["element", "layer", "jquery", "form"], function () {
    var element = layui.element;
    var layer = layui.layer;
    var $ = layui.jquery;
    var form = layui.form;
    var userId = localStorage.getItem('userId')

    getMyGroup();
    getDept();


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
                console.log(data)
                if (data.status == 0) {
                    // console.log("读入")
                    var html = template('meetGroup', {
                        data: data.data
                    });
                    document.getElementById('content').innerHTML = html;
                    element.render('collapse');
                    form.render('checkbox')

                    //阻止事件冒泡
                    $(function () {
                        $(".noshow").click(function (event) {
                            event.stopPropagation();
                        });
                    });

                    // //tip提示全选人员
                    // var tip_index = 0;
                    // $(".btnDelGroup").mouseenter(function () {
                    //     var index = $(".btnDelGroup").index(this);
                    //     var str = '.btnDelGroup:eq(' + index + ')';
                    //     tip_index = layer.tips('全选当前组', str, {
                    //         time: 1000
                    //     });
                    // });
                    // $(".btnDelGroup").mouseleave(function () {
                    //     layer.close(tip_index);
                    // });

                    //全选
                    form.on('checkbox(allChoose)', function (data) {
                        var gg = data.elem.dataset.gid;
                        var child = $(':checkbox[data-pgid=' + gg + ']');
                        child.each(function (index, item) {
                            item.checked = data.elem.checked;
                        });
                        form.render('checkbox');
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

    function getDept() {
        // 取得公司部门
        var url = path + "/user/group/findAllDept2.do";
        console.log("请求controller的url是:" + url)
        $.ajax({
            url: url,
            type: "get",
            dataType: "json",
            success: function (data) {
                console.log(data)
                if (data.status == 0) {
                    // console.log("读入")
                    var html = template('meetDept', {
                        data: data.data
                    });
                    document.getElementById('content2').innerHTML = html;
                    element.render('collapse');
                    form.render('checkbox')

                    //阻止事件冒泡
                    $(function () {
                        $(".noshow").click(function (event) {
                            event.stopPropagation();
                        });
                    });

                    //全选
                    form.on('checkbox(allChoose)', function (data) {
                        var gg = data.elem.dataset.gid;
                        var child = $(':checkbox[data-pgid=' + gg + ']');
                        child.each(function (index, item) {
                            item.checked = data.elem.checked;
                        });
                        form.render('checkbox');
                    });

                    for (var i = 0; i < data.data.length; i++) {
                        for (var j = 0; j < data.data[i].memberList.length; j++) {
                            proposals.push(data.data[i].memberList[j].pName);

                        }
                    }
                    // console.log('现在:' + proposals)

                    $("#search-form").autocomplete({
                        hints: proposals,
                        width: 300,
                        height: 30,
                        onSubmit: function (text) {
                            // $('#message').html('Selected: <b>' + text + '</b>');
                            go(text)
                        }
                    });
                    $(".autocomplete-input").keypress(function (e) {
                        if (e.which == 13) {
                            var value = $(".autocomplete-input").val()
                            go(value);
                        }
                    });
                    go = function (index) {
                        $('[data-pname=' + index + ']').addClass("active-name")
                        // console.log('改颜色')
                        var top = $('[data-pname=' + index + ']').offset().top;
                        $('html, body').animate({
                            scrollTop: top
                        }, 500)
                    }

                    $(".chosepeople").click( function() {
                        $(this).removeClass("active-name")
                    })


                } else {
                    layer.msg("请求部门失败", {
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

function getAttend() {
    attend = ''
    $("input:checkbox[cname!='all']:checked").each(function(i){
        if (attend == '') {
            attend = $(this).data('pid')
        } else {
            attend = attend +','+ $(this).data('pid');
        }
        
    });
    // console.log(attend)
    return attend;
}