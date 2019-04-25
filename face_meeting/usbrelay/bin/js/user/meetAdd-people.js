var proposals = [];
var attend = '';
var attendList = parent.attendList;

layui.use(["element", "layer", "jquery", "form"], function () {
    var element = layui.element;
    var layer = layui.layer;
    var $ = layui.jquery;
    var form = layui.form;
    var userId = localStorage.getItem('userId')

    getMyGroup();
    getDept();



    // 取得我加入的组
    function getMyGroup() {
        var url = path + "/user/group/findMyGroup.do";
        // console.log("请求controller的url是:" + url)
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
                    checkedPerson();


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

    // 取得公司部门
    function getDept() {
        var url = path + "/user/group/findAllDept2.do";
        // console.log("请求controller的url是:" + url)
        $.ajax({
            url: url,
            type: "get",
            dataType: "json",
            success: function (data) {
                // console.log(data)
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

                    //获取提示名字列表
                    for (var i = 0; i < data.data.length; i++) {
                        for (var j = 0; j < data.data[i].memberList.length; j++) {
                            proposals.push(data.data[i].memberList[j].pName);
                        }
                    }
                    // console.log('现在:' + proposals)
                    tipName();
                    checkedPerson();


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

    //已经邀请的人，再次打开时显示已勾选
    function checkedPerson() {
        // console.log("checkPerson:")
        var attendList = parent.attendList;
        if (attendList != '') {
            var attendArry = [];
            attendArry = attendList.split(',');
            // console.log('attendArry:')
            // console.log(attendArry)
            $("#depart input:checkbox[data-pid]").each(function (i) {
                var test = $(this).data('pid');
                // console.log(test)
                if (attendArry.indexOf(test) != -1) {
                    //选中
                    // $(this).attr("checked", 'true');
                    $(this).prop("checked", true)
                }
            });
            form.render('checkbox');
        }
    }

    //获得参与人员
    window.getAttend = function () {
        var attendArry = [];

        $("input:checkbox[cname!='all']:checked").each(function (i) {
            attendArry.push($(this).data('pid'))
        });
        // console.log('2.attendArry:')
        // console.log(attendArry)

        //数组去重
        var ass = [];
        for (var i = 0; i < attendArry.length; i++) {
            if (ass.indexOf(attendArry[i]) == -1) {
                ass.push(attendArry[i])
            }
        }
        // console.log('3.ass:')
        // console.log(ass)

        var result = ass.join(',')
        // console.log('4.result:')
        // console.log(result)
        attendList = result;
        // console.log('attendList:', attendList)

        return result;
    }

    //提示已在depart中
    window.tipDepart = function () {
        layer.msg('已选人员已经勾选在部门中，不在我的小组显示', {
                time: 2500
            }
        )
    }

    //提示名字
    function tipName() {
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
            // console.log(top)
            top = top - 60;
            $('html, body').animate({
                scrollTop: top
            }, 500)
        }

        $(".chosepeople").click(function () {
            $(this).removeClass("active-name")
            // console.log('移除')
        })
    }


});