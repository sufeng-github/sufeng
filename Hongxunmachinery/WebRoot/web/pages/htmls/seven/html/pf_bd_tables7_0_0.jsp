<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>基本信息</title> 
<!-- 	<link rel="stylesheet" type="text/css" href="../../../../../jquery-easyui-1.7.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../../../../../jquery-easyui-1.7.0/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../../../../../jquery-easyui-1.7.0/demo.css">
	<script type="text/javascript" src="../../../../../jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../../jquery-easyui-1.7.0/jquery.easyui.min.js"></script> -->
</head>

<body>
	<div style="width:100%; margin-top:20px;"> 
		<div>
			<table > 
			    <tr> 
			      	<td style="width:80px;text-align:right;"> 用户名 </td> 
			      	<td> <input  id="userName" class="easyui-textbox" style="width:150px;" data-options="validType:'text'"> </td>                     
			       	<td style="width:70px;text-align:right;"> 密码 </td> 
			        <td> <input  id="code" class="easyui-passwordbox" style="width:150px;"  data-options="showEye: false"> </td>    	                    
			      	<td style="width:70px;text-align:right;"> 职位 </td> 
			      	<td> <input  id="position" class="easyui-textbox" style="width:150px;" data-options="validType:'text'"> </td> 							
			  	</tr> 
			</table>     
		</div> 
	</div>	
	<div style="width:100%; height:100%; margin-top:20px;">
		<div style="width:16.333333%; margin-left:10px; float:left">
			<h2>基础信息</h2>
			<div style="margin-bottom:20px">
				<input class="easyui-checkbox dynamic" name="fruit" id="dynamic0" value="1" label="Bom:">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-checkbox dynamic" name="fruit" id="dynamic1" value="2" label="产品图纸:">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-checkbox dynamic" name="fruit" id="dynamic2" value="4" label="二维码:">
			</div>
		</div>
		<div style="width:16.333333%; margin-left:10px; float:left">
			<h2>计划管理</h2>
			<div style="margin-bottom:20px">
				<input class="easyui-checkbox dynamic" name="fruit" id="dynamic3" value="8" label="定单计划:">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-checkbox dynamic" name="fruit" id="dynamic4" value="16" label="采购计划:">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-checkbox dynamic" name="fruit" id="dynamic5" value="32" label="排产计划:">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-checkbox dynamic" name="fruit" id="dynamic6" value="64" label="委外计划:">
			</div>
		</div>
		<div style="width:16.333333%; margin-left:10px; float:left">
			<h2>原料仓库</h2>
			<div style="margin-bottom:20px">
				<input class="easyui-checkbox dynamic" name="fruit" id="dynamic7" value="128" label="采购入库:">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-checkbox dynamic" name="fruit" id="dynamic8" value="256" label="工单领料:">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-checkbox dynamic" name="fruit" id="dynamic9" value="512" label="实时库存:">
			</div>
		</div>
		<div style="width:16.333333%; margin-left:10px; float:left">
			<h2>成品仓库</h2>
			<div style="margin-bottom:20px">
				<input class="easyui-checkbox dynamic" name="fruit" id="dynamic10" value="1024" label="成品出入:">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-checkbox dynamic" name="fruit" id="dynamic11" value="2048" label="实时库存:">
			</div>		
		</div>
 	</div>
 
 	<div style="width:100%; margin-bottom:10px;">
 		<div style="width:100%;margin-right:10px">
				<input class="easyui-checkbox dynamic" name="fruit"  value="128" label="保存">
				<input class="easyui-checkbox dynamic" name="fruit" value="128" label="取消">
		</div>
	
 	</div>
 	<script>
 		var stateValue = 0;
	 	$('.dynamic').checkbox({
	        onChange:function(){
	        	var ch = $(this).checkbox('options')['checked'];
				var value = parseInt($(this).checkbox('options').value, 10); //returns 2					
	           	if(ch == false){
					stateValue = stateValue - value;
	           	}else {
					stateValue = value + stateValue;
	          	}
	            alert(stateValue);
	        }
	    });
	</script>
</body>
</html>


