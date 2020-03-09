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
    <link href="../../css/base.css" rel="stylesheet">
    <link href="../../css/providers1.css" rel="stylesheet">
    <link rel="stylesheet" href="../../../custom/uimaker/easyui.css"> 
 	<link rel="stylesheet" type="text/css" href="../../../custom/uimaker/icon.css">
    <link href="basic_info.css" rel="stylesheet">
 	<script type="text/javascript" src="../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../../custom/easyui-lang-zh_CN.js"></script>
    <script>
    	/*function getProdStatus(para,para1){
    		//var t1 = window.setInterval(hello(para,para1),300); 
			setInterval(function(){
			    hello(para, para1);
			},100000);

		}*/
		var  referenceMsg = null;
		function getProdStatus(para, para1){
			//alert(123);
					var BASE_URL = 'http://192.168.8.100:11005' 
			//var BASE_URL = 'http://192.168.8.100:13005'
			$.ajax({
					url: BASE_URL + para,			
					type: 'GET',
					dataType: 'json',
					//contentType:'application/json',
					success: function(res) {
						if(para1 == 'prodStatus'){
							if(referenceMsg == null){
								referenceMsg = res.data;
								alert(JSON.stringify(referenceMsg));
								console.log(JSON.stringify(referenceMsg));
							}
						}else{
    						alert(JSON.stringify(res));
    						
    						var data = res.data;
    						for(var i=0; i<data.length; i++){
    							//if(i==7){
    							var eqpID = referenceMsg[i].equipmentId ;
    							eqpID =	eqpID.substring(eqpID.length-3,eqpID.length);
    								data[i].equipmentId = referenceMsg[i].equipmentName + '_' + eqpID;
    							//}else{
    							//	data[i].equipmentId = referenceMsg[i].equipmentName;
    							//}
    						}
    						console.log(JSON.stringify(data));
    						sendData('${pageContext.request.contextPath}/numTwo.htm/' + para1, JSON.stringify(data));
    					}
					},
					error: function(err) {
						alert('false');
					}
			});
		}
		
		
		function getProdStatus1(para,para1){
			var BASE_URL = 'http://192.168.8.100:11005' 
			//var BASE_URL = 'http://192.168.8.100:13005'
			$.ajax({
					url: BASE_URL + para,			
					type: 'GET',
					dataType: 'json',
					//contentType:'application/json',
					success: function(res) {
    					alert(JSON.stringify(res));
    					console.log(JSON.stringify(res));
    					var data = res.data;
    					var userList = new Array(); 
    					userList.push(data); 	
    					sendData('${pageContext.request.contextPath}/numTwo.htm/' + para1, JSON.stringify(userList));
					},
					error: function(err) {
						alert('false');
					}
			});
		}
		
		/*function postRunStatus(){
			var BASE_URL = 'http://192.168.8.100:11005' //领先
			//var BASE_URL = 'http://192.168.8.100:13005' //伊莱特
			var runStatusCondition = {"time": "2019-03-26"};
 			$.ajax({
					url: BASE_URL + '/runStatus/getRunStatus',			
					type: 'POST',
					data: JSON.stringify(runStatusCondition),
					dataType: 'json',
					contentType:'application/json',
					success: function(res) {
    					var data = res.data;
					},
					error: function(err) {
						alert('false');
					}
			}); 
		}*/
		
		function getRunStatus(){
			var BASE_URL = 'http://192.168.8.100:11005' //领先
			//var BASE_URL = 'http://192.168.8.100:13005' //伊莱特
			var time="2019-03-26";
			
 			$.ajax({
					url: BASE_URL + '/runStatus/getRunStatus/'+time,			
					type: 'GET',
					dataType: 'json',
					contentType:'application/json',
					success: function(res) {
    					var data = res.data;
    					alert(JSON.stringify(res));
					},
					error: function(err) {
						alert('false');
					}
			}); 
		}
		
		function sendData(url, data){
           	$.ajax({ 
				type: "post",  		
				url:url,
				data:data,
				contentType : 'application/json;charset=utf-8', //设置请求头信息  
				traditional: true,
				dataType:"json",  
				success: function(result) {
					//alert('上访成功');
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	
        }
	</script>	
</head>

	<body>
		<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">		
			<a href="#" class="easyui-linkbutton" data-options="selected:false,plain:true" style="height:25px;" onclick="getProdStatus('/prodStatus/getProdStatus','prodStatus' )">状态获取</a>	
			<a href="#" class="easyui-linkbutton" data-options="selected:false,plain:true" style="height:25px;" onclick="getProdStatus1('/prodStatus/getProdStatusCountAndData','prodStatusCountAndData')">状态和数据</a>	
			<a href="#" class="easyui-linkbutton" data-options="selected:false,plain:true" style="height:25px;" onclick="getProdStatus('/prodStatus/getProdStatusReal', 'prodStatusReal')">实时状态</a>	
			<a href="#" class="easyui-linkbutton" data-options="selected:false,plain:true" style="height:25px;" onclick="getRunStatus('/runStatus/getRunStatus')">机台运行状态</a>	
			<a href="#" class="easyui-linkbutton" data-options="selected:false,plain:true" style="height:25px;" onclick="postRunStatus('/runStatus/getRunStatusList')">机台运行状态列表</a>	
		</div>		
	</body>
</html>


