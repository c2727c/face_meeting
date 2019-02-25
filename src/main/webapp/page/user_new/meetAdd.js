layui.use(['element','layer','jquery','form'],function(){
	var element = layui.element;
	var layer = parent.layer === undefined ? layui.layer : top.layer;
	var $ = layui.jquery;
	var form = layui.form;
	
	var username=getCookie("userName");
	$("#username").html(username);
	
	
})