layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    $(".loginBody .seraph").click(function(){
        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧",{
            time:5000
        });
    })

    //登录按钮
    form.on("submit(login)",function(data){
    	//先进行验证看是否为空
        console.log("data.field.name"+data.field.name); 
        //当前容器的全部表单字段，名值对形式：{name: value}
    	//javascript如果前面的语句发生错误，后面的语句将不会执行
        //var userInfo = data.field无法使用
        console.log("data.field.userType="+data.field.userType);
        var userType=data.field.userType;
        var userId = data.field.name;
        var userPass = data.field.password;
        var path="http://localhost:8080/face_meeting"
        var url = path + "/user/login.do";
        $.ajax({
            url : url,
            type : "post",
            traditional:true,//这使json格式的字符不会被转码
            data :JSON.stringify({"pId":userId,"pPass":userPass}),                      
            contentType:'application/json;charset=UTF-8',  //这里的这行是关键
			dataType : "json",
            beforeSend: function () {
                // 禁用按钮防止重复提交
                $("#loginButton").attr({ disabled: "disabled" });
            },
            success:function(data){
                 if(data.status == 1){
                     layer.msg(data.msg,{icon: 5});//失败的表情
                 }else if(data.status == 0){
                	 document.cookie="userId="+data.data.pId;
                	 document.cookie="userName="+data.data.pName;
                	 var key,userInfoHtml="";
                	 userInfoHtml={
                			 "userId":data.data.pId,
                			 "userName":data.data.pName,
                			 "userSex":data.data.pGender,
                			 "userRole":data.data.pRole,
                			 "userDept":data.data.pDept,
                			 "userPrivilege":data.data.pPrivilege,
                			 "userPictPath":data.data.pPictPath,
                	 };
         	        window.sessionStorage.setItem("userInfo",JSON.stringify(userInfoHtml));
                     layer.msg(data.msg, {
                         icon: 6,//成功的表情
                         time: 1000 //1秒关闭（如果不配置，默认是3秒）
                     }, function(){
                    	 if(userType==="0"){
                    		 window.location.href = "../face_meeting/page/user/index.html";
                    	 }else if(userType==="1"){
                    		 window.location.href = "../face_meeting/page/admin/index.html";
                    	 }
                     });
                 }
            },
            complete: function () {
                $("#loginButton").removeAttr("disabled");
            },
            error: function(){
            	alert("ajax请求失败")
            }
        });
        return false;
    })

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})
