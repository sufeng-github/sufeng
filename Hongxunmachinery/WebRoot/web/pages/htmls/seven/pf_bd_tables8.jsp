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
	<link rel="stylesheet" type="text/css" href="../../../../jquery-easyui-1.7.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../../../../jquery-easyui-1.7.0/themes/icon.css">
<!-- 	<link rel="stylesheet" type="text/css" href="../../../../jquery-easyui-1.7.0/demo.css"> -->
	<script type="text/javascript" src="../../../../jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/myjs/xlsx.js"></script>
    <script type="text/javascript" src="../../js/myjs/datagrid-export.js"></script> 
    <script type="text/javascript" src="../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../custom/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../../js/myjs/jquery.form.js"></script> 
</head>
<!-- type="hidden" -->
<body>  
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	

		<table id="tt"></table>		
		<!-- <a href="javascript:void(0);" onclick="CreateFormPage('打印测试', $('#tt'));">打印</a>	 -->
	</div>		
	
  	
	<script>
		$(function(){
			$('#tt').datagrid({
				url:'${pageContext.request.contextPath}/materialStock.htm/getData',
						
				width:'100%',
				height:'100%',
				toolbar:'#tb',
				singleSelect:true,

				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				//method:'post',
				pagination:true,
				pageList: [10,50,100,500,1000],
				rownumbers:true,
				remoteSort:false,
				multiSort:false,
				columns:[[
 					{field:'ck', checkbox:true},	
					{field:'materialNum',title:'物料编码1',align:'center',sortable:true},								
					{field:'specification',title:'型号/规格',align:'center',sortable:true},		
					{field:'quantity',title:'库存量',align:'center'},
					{field:'inQuantity',title:'入库量',align:'center'},
					{field:'outQuantity',title:'出库量',align:'center'},
					{field:'safeQuantity',title:'安全库存',align:'center',editor:'numberbox'},
					{field:'itemQuantity',title:'项目数',align:'center',editor:'numberbox'},
					{field:'inRoadQuantity',title:'在途数',align:'center',editor:'numberbox'},	
					{field:'purQuantity',title:'提单量',align:'center',hidden:'true'},		
					{field:'deliveryDate',title:'在途交期',align:'center',sortable:true},					 
					{field:'alarm',title:'报警',align:'center',sortable:true},
					{field:'unit',title:'单位',align:'center'},  
 					{field:'localCode',title:'库位代码',align:'center',editor:'text',sortable:true},  	
					{field:'remark',title:'备注',align:'center',editor:'text'},
					{field:'idc',title:'ID',width:10},	
					{field:'purchaseDeId',title:'purchaseDeId',hidden:'true'},	
					{field:'purchaseId',title:'purchaseId',hidden:'true'},					
					{field:'action',title:'Action',align:'center',
						formatter:function(value,row,index){
							if(isOnlyRead()==false){
								if (row.editing){
									var s = '<a href="#" onclick="saveRow(this)">保存</a> ';
									var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
									return s+c;
								} else {
								
									/* 
									if(row.materialPlanQuantity <= row.materialRealQuantity){
										e= '入库完成'
									}else{
										e = '<a href="#" onclick="editrow1(this)">编辑</a> ';
									} */
							
									var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
									var d = '<a href="#" onclick="deleterow(this)">删除</a>';
									return e+d;
									return e;	
								}
							}
						}
					}

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
		
</body>
</html>


