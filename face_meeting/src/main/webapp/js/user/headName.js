var userName = localStorage.getItem('userName')
var userId = localStorage.getItem('userId')
var pIcon = ''
pIcon = localStorage.getItem('pIcon')
// console.log(pIcon)

layui.use(['element', 'layer', 'jquery'], function () {
    var element = layui.element;
    var layer = layui.layer;
    var $ = layui.$;
});

function getNameAndIcon() {
    userName = localStorage.getItem('userName')
    pIcon = localStorage.getItem('pIcon')
    $("#pName").html(userName);
    if (pIcon != null && pIcon != 'null') {
        $('#pIcon').attr('src', "data:image/jpeg;base64," + pIcon);
    } else {
        $('#pIcon').attr('src', "../../images/default.jpg");
    }
    // console.log("重写成功")
}

getNameAndIcon();