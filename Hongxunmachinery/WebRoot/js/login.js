jQuery(document).ready(function() {
	$("#userName").focus();
});

/*
function doLogin(){
	var userName=$("#userName");
	var password=$("#password");
	if(userName==null||userName.attr("value")==""){
		alert("请输入用户名！");
	}else if(password==null||password.attr("value")==""){
		alert("请输入密码！");
	}
	else{
		//alert("用户名："+userName.attr("value")+"\n密码："+password.attr("value")+"\n正在提交form....");
		//alert($("#loginForm").attr("action"));
		//alert('asfdsadf');
		$("#loginForm").submit();
	}
}
*/
/*
function doLoginByAjax(){
	//jQuery.post(url, [data], [callback], [type])
	var userName=$("#userName");
	var password=$("#password");
	if(userName==null||userName.attr("value")==""){
		alert("请输入用户名！");
	}else if(password==null||password.attr("value")==""){
		alert("请输入密码！");
	}else{
		var url = "http://localhost:8080/springMVC/login/login.do";
		
		//var url = "http://localhost:8080/springMVC/TestServlet";
		var params = {
			"userName" : userName.attr("value"),
			"password" : password.attr("value")
		};
		alert("正在登陆.....");
		$.post(url,params,false);
	}
}*/