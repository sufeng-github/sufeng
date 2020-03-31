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
    <link href="../../../css/base.css" rel="stylesheet">
    <link href="../../../css/providers1.css" rel="stylesheet">
    <link rel="stylesheet" href="../../../../custom/uimaker/easyui.css"> 
 	<link rel="stylesheet" type="text/css" href="../../../../custom/uimaker/icon.css">
  <!--   <link href="../basic_info.css" rel="stylesheet"> -->
    <link rel="stylesheet" href="../../../font-awesome-4.7.0/css/font-awesome.min.css" media="screen" type="text/css"/>
 	<script type="text/javascript" src="../../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../../js/myjs/xlsx.js"></script>
    <script type="text/javascript" src="../../../js/myjs/datagrid-export.js"></script> 
    <script type="text/javascript" src="../../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../../custom/easyui-lang-zh_CN.js"></script>
</head>

<body>  
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	

		<table id="tt"></table>		
		<!-- <a href="javascript:void(0);" onclick="CreateFormPage('打印测试', $('#tt'));">打印</a>	 -->
	</div>		

	<div id="menu" class="easyui-menu" style="width: 100px; display: none;">
	    <!--放置一个隐藏的菜单Div-->
		<div  onclick="moveToNewPurchaseNum()"><i class="fa fa-file-powerpoint-o "></i>&nbsp; 提交采购单</div>
		<!-- <div data-options="iconCls:'icon-edit'" onclick="moveToOldPurchaseNum()">移动到已有采购单</div> -->
	    <!-- <div data-options="iconCls:'icon-remove'" onclick="deleteSelected()">删除选中行</div>  -->
	</div>
	<script>
	
		$(function(){
			
			$('#tt').datagrid({
				width:'100%',
				height:'100%',
				//toolbar:'#tb',
				singleSelect:false,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				//pagination:true,
				//pageList: [10,50,100,500,1000],
				rownumbers:true,
				columns:[[
 					{field:'ck', checkbox:true},	
					{field:'materialNum',title:'物料编码',align:'center',width:30},								
					{field:'specification',title:'型号/规格',align:'center',width:80},		
					{field:'quantity',title:'库存量',align:'center',width:20},
					{field:'safeQuantity',title:'安全库存',align:'center',width:20},
					{field:'itemQuantity',title:'项目数',align:'center',width:20},
					{field:'inRoadQuantity',title:'在途数',align:'center',width:20},
					{field:'purQuantity',title:'提单量',align:'center',width:20,editor:'numberbox'},		
					{field:'deliveryDate',title:'交货日期',align:'center',width:20}, 
					{field:'alarm',title:'报警',align:'center',width:20,
						formatter:function(value,row,index){ 				    	 	
							return  '<span style="color:red">'+value+'</span>';						
			      		} 
					},
					{field:'unit',title:'单位',align:'center',width:20},  
 					{field:'localCode',title:'库位代码',align:'center',width:20},  	
					{field:'remark',title:'备注',align:'center',width:20,editor:'text'},					
					{field:'idc',title:'ID',width:20,hidden:'true'}
				]],
				onBeforeEdit:function(index,row){
					row.editing = true;
					$(this).datagrid('refreshRow', index);
				},
				onAfterEdit:function(index,row){
					row.editing = false;
					$(this).datagrid('refreshRow', index);
				},
				onCancelEdit:function(index,row){
					row.editing = false;
					$(this).datagrid('refreshRow', index);
				}
			});
			
		});
		
	</script>	
	<script>	

        $(function(){				  
           $.ajax({ 
				type: "post",  				
				url:'${pageContext.request.contextPath}/materialStock.htm/getMaterialAlarms',
				traditional: true,
				data:{alarm:"报警"},
				dataType:"json",  
				beforeSend: function () {
					load();
				},
				complete: function () {
					disLoad();
				},
				success: function(result) {
					$('#tt').datagrid({data:result});
					
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	
                         
        });  
            
	</script>
	
</body>
</html>


