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

    <link rel="stylesheet" type="text/css" href="../../../../../jquery-easyui-1.7.0/themes/icon.css">
 	<link rel="stylesheet" href="../../../../../jquery-easyui-1.7.0/themes/default/easyui.css"> 
    <script type="text/javascript" src="../../../../../jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../../jquery-easyui-1.7.0/jquery.easyui.min.js"></script> 
    <script type="text/javascript" src="../../../js/myjs/xlsx.js"></script>
    <script type="text/javascript" src="../../../js/myjs/datagrid-export.js"></script>
    <script type="text/javascript" src="../../../js/myjs/myjs.js"></script>  
<!--    	<script type="text/javascript" src="../../../js/myjs/production.js"></script>  --> 
   	<script type="text/javascript" src="../../../js/myjs/common.js"></script>  
    <script type="text/javascript" src="../../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../../custom/easyui-lang-zh_CN.js"></script>

</head>


<body>
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
		<div id="tb" style="padding:0 10px;height:50px">	
			<div style="margin-top:5px; margin-left:10px; float:left">	
		<!-- style="display:none; style="display:none; -->
						<table>	
							<tr>
								<td>部件号:</td>
								<td><input class="easyui-textbox" id="num" readonly="true" style="width:200px;height:25px;"></input></td>
								<td>型号/规格:</td>
								<td><input class="easyui-textbox" id="spec" readonly="true" style="width:200px;height:25px;"></input></td>									
								<td><input class="easyui-textbox" id="quantity" readonly="true" style="width:50px;height:25px;"></input></td>	
								<td><input class="easyui-textbox" id="sendQuantity" readonly="true" style="width:50px;height:25px;"></input></td>																				
								<td "><input class="easyui-numberbox"  id="proIdc" readonly="true" style="width:100px;height:25px;" ></input></td>	
								<td "><input class="easyui-numberbox"  id="itemOutIdc" readonly="true" style="width:100px;height:25px;" ></input></td>																															
								<td><a href="javascript:;" class="easyui-linkbutton" style="height:25px;" onclick="outStock()" >确定</a></td>															
							</tr>
						</table>						
			 </div> 			
		</div>
		<table id="tt" class="easyui-datagrid" style="width:100%; height:100%;" data-options="collapsible:true,				
				url:'${pageContext.request.contextPath}/productionStock.htm/getData',
				toolbar:'#tb',
				singleSelect:true,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,				
				pagination:true,
				remoteSort:false,
				pageList: [10,50,100,500,1000],
				rownumbers:true"
			>
			<thead>
				<tr>
					<th data-options="field:'ck', checkbox:true"></th>
					<th data-options="field:'materialNum',align:'center',width:40,editor:'text'">物料编码</th>
					<th data-options="field:'specification',align:'center',width:70,editor:'text'">型号/规格</th>
					<th data-options="field:'quantity',align:'center',width:20">库存量</th>
					<th data-options="field:'unit',align:'center',width:20">单位</th>
					<th data-options="field:'idc',width:10,hidden:'true'">ID</th>

				</tr>
			</thead>
	 	</table> 		
	</div>	
	

	<script>
			
		$('#tt').datagrid({

			onDblClickRow:function(index, row){
				$('#num').textbox('setText', row.materialNum);
				$('#spec').textbox('setText', row.specification);
				$('#quantity').textbox('setText', row.quantity);
				$('#sendQuantity').textbox('setText', window.parent.window.getDeliveryItemSendQuantity('#dialog'));
				$('#itemOutIdc').textbox('setText', window.parent.window.getDeliveryItemID('#dialog'));
				$('#proIdc').textbox('setText', row.idc);	
			},
			queryParams : {				
				materialNum:window.parent.window.getDeliveryItemMaterialNum('#dialog')
			},
			onBeforeEdit:function(index,row){
				$(this).datagrid('updateRow', {index:index,row:{editing:true}})
			},
			onAfterEdit:function(index,row){
				$(this).datagrid('updateRow', {index:index,row:{editing:false}})
			},
			onCancelEdit:function(index,row){
				$(this).datagrid('updateRow', {index:index,row:{editing:false}})
			}
		});
			
		$.extend($.fn.datagrid.defaults.editors, {
			numberspinner: {
				init: function(container, options){
					var input = $('<input type="text">').appendTo(container);
					return input.numberspinner(options);
				},
				destroy: function(target){
					$(target).numberspinner('destroy');
				},
				getValue: function(target){
					return $(target).numberspinner('getValue');
				},
				setValue: function(target, value){
					$(target).numberspinner('setValue',value);
				},
				resize: function(target, width){
					$(target).numberspinner('resize',width);
				}
			}
		});
		
		function outStock(){	
			if($('#proIdc').textbox('getText')==""){
				alert('请双击下列一项');
				return 
			}	
			if($('#quantity').textbox('getText')==""){
				alert('请检查送货数量是否为空');
				return 
			}
			if($('#quantity').textbox('getText') < $('#sendQuantity').textbox('getText')){
				alert('库存不足');
			}						
			var list = new Array();
			var obj = {};
			obj.proIdc = $('#proIdc').textbox('getText');			
			obj.itemOutIdc = $('#itemOutIdc').textbox('getText');
			obj.sendQuantity = $('#sendQuantity').textbox('getText');
			obj.quantity = $('#quantity').textbox('getText');			
			//list.push(obj);
			//alert(JSON.stringify(list));
			if(ajaxObj("${pageContext.request.contextPath}/productionStock.htm/outStock",obj,"成品出库")==true){
				
			
			}
			
		} 
	</script>

</body>
</html>


