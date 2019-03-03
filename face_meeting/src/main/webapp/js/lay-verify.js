layui.use(['form','layer','jquery'],function(){
	var form=layui.form;
	var $=layui.jquery;
	var layer=parent.layer===undefined ? layui.layer:top.layer;
	//自定义表单验证，https://www.layui.com/doc/modules/form.html#verify
	//layui自带的验证有required（必填项）phone（手机号）email（邮箱）url（网址）number（数字）date（日期）identity（身份证）
	form.verify({
		userName: function(value, item){ //value：表单的值、item：表单的DOM对象
		    if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
		      return '用户名不能有特殊字符';
		    }
		    if(/(^\_)|(\__)|(\_+$)/.test(value)){
		      return '用户名首尾不能出现下划线\'_\'';
		    }
		    if(/^\d+\d+\d$/.test(value)){
		      return '用户名不能全为数字';
		    }
		  }
		  ,passwordConfirm:function(value){
			  var password=$("#password").val();
			  var passwordConfirm=$("#passwordConfirm").val();
			  //参考https://blog.csdn.net/zzz___bj/article/details/80323889
			  if(password!=passwordConfirm){
				  return '密码不一致';
			  }
		  }
		  //我们既支持上述函数式的方式，也支持下述数组的形式
		  //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
		  //同时使用多个layui-veritify中间是用的是"|"而非"/"
		  ,password: [
		    /^[\S]{6,12}$/
		    ,'密码必须6到12位，且不能出现空格'
		  ] 
	});
})