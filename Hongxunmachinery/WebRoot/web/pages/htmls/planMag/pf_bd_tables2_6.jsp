<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html> 
<html lang="en"> 
<head> 
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>基本信息</title> 

    <link href="../../css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="../../../custom/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../../custom/uimaker/icon.css">   
    <link href="../../css/basic_info.css" rel="stylesheet">
 <!--   	<link href="../../css/process.css" rel="stylesheet"> -->
    <script type="text/javascript" src="../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../custom/jquery.easyui.min.js"></script>
	<!--     
	<script type="text/javascript" src="../../../../jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    -->
 	<script type="text/javascript" src="../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../js/myjs/ajaxfileupload.js"></script> 
    <script type="text/javascript" src="../../js/myjs/pdfobject.min.js"></script> 
    

<!--     <script type="text/javascript" charset="utf-8" src="../../js/umeditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../../js/umeditor/umeditor.min.js"></script> -->

</head>

<body>

		<div class="easyui-layout"  data-options="fit:true">	
		    <div data-options="region:'center'" style="padding:0px;background:#eee;">
		    	<!-- <a href="javascript:void(0)" onclick="analysis()" class="easyui-linkbutton" iconCls="icon-search" style="width:100%; height:30px;">分解定单</a>					 -->
 				<div id="tt" class="easyui-tabs1" style="width:100%;height:100%;">
	          	</div>	 				
			</div> 
		</div>	
 	 
		
	<script type="text/javascript">
		
        $('.easyui-tabs1').tabs({
          	tabHeight: 26,
           /*	onSelect:function(title,index){
	           	if(title.indexOf('A01焊接下料')>-1){
	          		var currentTab = $('.easyui-tabs1').tabs("getSelected");
	               	if(currentTab.find("iframe") && currentTab.find("iframe").size()){
	                  	currentTab.find("iframe").attr("src",currentTab.find("iframe").attr("src"));
	               	}
	               	if(title.indexOf('筛选清单')>-1){
	               		analysis();
	               	}
	           	}
	      	} */    	
        });
      
      
      	var timeStamp;
       	function setTimeStamp(t){
			timeStamp = t;
        }
        function getTimeStamp(){  	
	         return timeStamp;
        }
        function distribution(){
        	//var tabs = $('#tt').tabs('tabs');
        	for(var i=1;i<$('#tt').tabs('tabs').length;i++) {
				var tab = $('#tt').tabs('getTab', i);
				var iframeWindow=tab.find('iframe')[0].contentWindow;    
				var url = iframeWindow.$('#tt').datagrid("options").url;
				var title = tab.panel('options').title;
				alert(title);
				if(title.indexOf('外购')>-1){
					$.ajax({  
						type: "post",  
						url: url, 
						traditional: true,
						dataType:"json", 						
						success: function(result) {							
							newPurchaseNum(result, title);												
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});    	
				}else if(title.indexOf('外协')>-1){
					$.ajax({  
						type: "post",  
						url: url, 
						traditional: true,
						dataType:"json", 						
						success: function(result) {	
							newWeiwaiNum(result, title);													
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});    		
				
				}else{
					$.ajax({  
						type: "post",  
						url: url, 
						traditional: true,
						dataType:"json", 						
						success: function(result) {	
							newWorkNum(result, title)													
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});    		
				}
			}
        }
        
        function newWorkNum(rows, title){
			//var rows = $('#tt').datagrid('getSelections');  
			var userList = new Array(); 
			//alert(window.parent.window.getTimeStamp())
			userList.push({remark:title,status:timeStamp});
		    for(var i=0; i<rows.length; i++){  
        		userList.push(rows[i]);
			}
			//var userList = $('#tt').datagrid('getData').rows;
			//alert(JSON.stringify(userList));
			$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/workNum.htm/newWorkNum',
					data:JSON.stringify(userList), 
					//traditional: true,
					dataType:"json", 
					contentType : 'application/json;charset=utf-8', //设置请求头信息  
					success: function(result) {	
						//updateStatus(userList,'工单已生成!');		
						//alert("分配成功");														
					}, 
					error : function() {
				       	alert("异常！");
				  	}
			});    		
		}
        
        function newWeiwaiNum(rows, title){
				
			var userList = new Array();      		
			//alert('attribute:' + attribute);
			userList.push({remark:title,status:timeStamp});
			for(var i=0; i<rows.length; i++){
				userList.push(rows[i]);	
			}
				//alert(JSON.stringify(userList));
				$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/weiwaiNum.htm/newWeiwaiNum',
					data:JSON.stringify(userList), 
					//traditional: true,
					dataType:"json", 
					contentType : 'application/json;charset=utf-8', //设置请求头信息  

					success: function(result) {	
						//updateStatus(userList,'外协单已生成！');
						//alert("分配成功");																								
					}, 
					error : function() {
				       	alert("异常！");
				  	}
				});
		} 
		function newPurchaseNum(rows, title){
			//var rows = $('#tt').datagrid('getSelections');  	
			var flag = true;
			var userList = new Array();      		
			userList.push({remark:title,status:timeStamp});
			//alert('attribute:' + attribute);
			for(var i=0; i<rows.length; i++){
				userList.push(rows[i]);			
			}
					//alert(JSON.stringify(userList));
			$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/purchaseNum.htm/newPurchaseNum',
					data:JSON.stringify(userList), 
					//traditional: true,
					dataType:"json", 
					contentType : 'application/json;charset=utf-8', //设置请求头信息  
					success: function(result) {
						//updateStatus(userList,'外购单已生成！');
						//alert("分配完成")																																	
					}, 
					error : function() {
				       	alert("异常！");
				  	}
			});		
		}
		function addTab(title, url){
                if ($('#tt').tabs('exists', title)){
                    $('#tt').tabs('select', title);
                } else {
                    var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
                    $('#tt').tabs('add',{
                        title:title,
                        content:content,
                        closable:false
                    });                     
                }
     	}
      	
      	$(function(){					
            	setTimeout(function(){
					addTab('元数据','./pf_bd_tables2_0.jsp');
					
				},300);
	   	});
	   	
	    $(window).resize(function(){
	    	$('.easyui-tabs1').tabs("resize");
	    }).resize(); 
	    
	</script>

</body> 
</html>


