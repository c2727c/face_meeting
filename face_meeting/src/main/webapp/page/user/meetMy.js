layui.use(['element', 'layer', 'jquery'], function () {
    var element = layui.element;
    var layer = layui.layer;
    var $ = layui.jquery;

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

    // //提示编辑
    var tip_index = 0;
    $(".lookMeet").mouseenter(function () {
        var index = $(".lookMeet").index(this);
        
        var str = '.lookMeet:eq('+index+')';
        tip_index = layer.tips('点击查看详情', str, {
            time: 1000
        });
    });
    $(".lookMeet").mouseleave(function () {
        layer.close(tip_index);
    });

});