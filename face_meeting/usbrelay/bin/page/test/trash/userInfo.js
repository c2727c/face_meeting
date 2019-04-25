var form, $;

layui.use(['form','layer','jquery'],function(){
    form = layui.form;
    $ = layui.jquery;


    //提交个人资料
    form.on("submit(changeUser)",function(data){
        var index = layer.msg('提交中，请稍候',{icon: 16,time:2000,shade:0.8});
        var id=getCookie("userId");
        var username=data.field.username;
        var sex=data.field.sex;
        var userPhone=data.field.phone;
        var email=data.field.email;
        $.ajax({
        	url:basePath+"/user/userInfoUpdate.do",
        	type:"post",
        	traditional:true,
        	data:JSON.stringify({"pId":id,"pName":username,"pGender":sex,"pPhone":userPhone,"pEmail":email}),
        	contentType:"application/json;charset=UTF-8",
        	dataType:"json",
        	beforeSend:function(){
        		$("#changeUser").attr({disabled:"disabled"});
        	},
        	success:function(data){
        		if(data.status==1){
        			layer.msg(data.msg,{icon:5});
        		}else if(data.status==0){
        			layer.msg(data.msg,{icon:6,time:1000});
        			//将用户信息存到session以便下次调取
        			var key,userInfoHtml="";
        			userInfoHtml={
							 "userId":data.data.pId,
		        			 "userName":data.data.pName,
		        			 "userSex":data.data.pGender,
		        			 "userRole":data.data.pRole,
		        			 "userDept":data.data.pDept,
		        			 "userPhone":data.data.pPhone,
		        			 "userPrivilege":data.data.pPrivilege,
		        			 "userPictPath":data.data.pPictPath,
		        			 "userEmail":$(".userEmail").val(),
        			};
        			for(key in data.field){
        				if(key.indexOf("like") != -1){
        	                userInfoHtml[key] = "on";
        	            }
        			}
        	        window.sessionStorage.setItem("userInfo",JSON.stringify(userInfoHtml));
        		}
        	},
        	error:function(){
        		alert("ajax请求失败");
        	},
        	complete:function(){
        		$("#changeUser").removeAttr("disabled");
        	},
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
    
    //修改密码
    form.on("submit(changePwd)",function(data){
        var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
            layer.msg("密码修改成功！");
            $(".pwd").val('');
        },2000);
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
})