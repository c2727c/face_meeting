//JavaScript代码区域
layui.use(['element', 'layer', 'jquery','form'], function () {
    var element = layui.element;
	var layer=parent.layer===undefined?layui.layer:top.layer;
    var $ = layui.jquery;
	var form=layui.form;
	
    var userJsonInfo=sessionStorage.getItem("userInfo");
    var userInfo=JSON.parse(userJsonInfo);
    var username=userInfo.userName;
    $("#username").html(username);
    form.val("pswChange",{
    	"username":username
    });
    form.on("submit(changePwd)",function(data){
        console.log("aa");
    	var userId=getCookie("userId");
    	var userPass=$("#oldPwd").val();
    	var newPass=$("#newPwd").val();
    	console.log("oldPass:"+userPass+"newPass:"+newPass);
    	console.log(basePath);
    	$.ajax({
    		url:basePath+"/user/login.do",
    		type:"post",
    		traditional:true,
    		data:JSON.stringify({"pId":userId,"pPass":userPass}),
    		contentType:"application/json;charset=UTF-8",
    		dataType:"json",
    		beforeSend:function(){
    			//排查错误的循序基本上可以按照$.ajax前->beforeSend..进行
    			$("#changePwd").attr({disabled:"disabled"});
    		},
    		success:function(data){
    			if(data.status==1){
    				layer.msg(data.msg,{icon:5});
    			}else if(data.status==0){
    				//输入的原密码正确
    				//修改密码的ajax请求
    				$.ajax({
    					url:basePath+"/user/changePass.do",
    					type:"post",
    					traditional:true,
    					data:JSON.stringify({"pId":userId,"pPass":newPass}),
    					contentType:"application/json;charset=UTF-8",
    					dataType:"json",
    					success:function(data){
    						if(data.status==1){
    							layer.msg(data.msg,{icon:5});
    						}
    						else if(data.status==0){
    							layer.msg(data.msg,{icon:6});
    						}
    					},
    					error:function(){
    						layer.msg("修改密码失败",{icon:5});
    					},
    				});
    			}
    		},
    		complete:function(){
    			$("#changePwd").removeAttr("disabled");
    		},
    		error:function(){
    			layer.msg("密码验证失败",{icon:5});
    		},
    	});
    	return false;
    })
});