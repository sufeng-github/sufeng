<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
       	<title>DataGrid Complex Toolbar - jQuery EasyUI Demo</title>  
       	  	

<!--    <link rel="stylesheet" type="text/css" href="web/custom/uimaker/easyui.css">
		<link rel="stylesheet" type="text/css" href="web/custom/uimaker/icons/icon.css">
	
		<script type="text/javascript" src="web/custom/uimaker/jquery.min.js"></script>
		<script type="text/javascript" src="web/custom/uimaker/jquery.easyui.min.js"></script> -->
<!--   		<script type="text/javascript" src="js/login.js"></script> -->
  		<link rel="stylesheet" type="text/css" href="CSS/login.css">
  	</head>
		<script type="text/javascript">		    
		    document.onkeydown = function(e){
		        var event = e || window.event;  
		        var code = event.keyCode || event.which || event.charCode;
		        if (code == 13) {
		            doLogin();
		        }
		    }
		    
/* 			jQuery(document).ready(function() {
				$("#userName").focus();
			}); */
					
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
		</script>  
    </head>

    <body> 
			<div class="box">
			    <div id="header">
			        <h1>LOGIN</h1>
			    </div>
			    <form id="loginForm" action="login.do" method="post">
			        <div class="group">
			            <input class="inputMaterial" id="userName" name="userName" type="text" required>
			            <span class="highlight"></span>
			            <span class="bar"></span>
			            <label>用户名</label>
			        </div>
			        <div class="group">
			            <input class="inputMaterial" id="password" name="password" type="password" required>
			            <span class="highlight"></span>
			            <span class="bar"></span>
			            <label>密码</label>
			        </div>
			        <button  type="submit" >登陆</button>
			    </form>
			    <div id="footer-box"><p class="footer-text">Not a member?<span class="sign-up"> Sign up now</span></p></div>
			</div>
 	</body> 
</html> 