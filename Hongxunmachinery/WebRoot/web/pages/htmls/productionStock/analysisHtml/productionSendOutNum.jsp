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
 		<div id="tb" style="padding:0 10px;height:40px">	
			<div style="margin-top:5px; margin-left:10px; float:left">	
						<table>
							<tr>
 								<td>发货单号:</td>
								<td><input class="easyui-combobox" id="num" style="width:200px;height:25px;"></input></td>														
								<td>开始时间:</td>
								<td><input class="easyui-datebox" id="startTime"  style="height:25px;width:150px;line-height:25px;"></input></td>						
								<td>结束时间:</td>
								<td><input class="easyui-datebox" id="endTime"  style="height:25px;width:150px;line-height:25px;"></input></td>											
								<td><a href="javascript:;" class="easyui-linkbutton"  onclick="find()" >查找</a></td>
								<td><a href="javascript:;" class="easyui-linkbutton"  onclick="add()" >新增</a></td>
								<td><a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('toExcel','采购单.xls')">导出</a></td>
								<td><a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('print','DataGrid')">打印</a></td>
							</tr>
						</table>						
			 </div> 			
		</div>
		<table id="tt" class="easyui-datagrid" style="width: 100%; height: 100%" 
			data-options="collapsible:true,				
				url:'${pageContext.request.contextPath}/deliveryNum.htm/getData',
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
					<th data-options="field:'customer',title:'客户',align:'center',width:40,editor:'textbox'"></th>							
					<th data-options="field:'supplierCode',title:'供应商代码',align:'center',width:50,editor:'textbox'"></th>								
					<th data-options="field:'date',title:'时间日期',width:40,align:'center', editor:'datebox',formatter:fmaterDate"></th>   					
					<th data-options="field:'deliveryNum',title:'送货单号',align:'center',width:30"></th>
					<th data-options="field:'status',title:'状态',align:'center',width:20,formatter:fmaterColor"></th>
					<th data-options="field:'name',title:'类别',align:'center',width:20"></th>
					<th data-options="field:'remark',title:'备注',align:'center',width:20,editor:'textbox'"></th>
					<th data-options="field:'idc',width:10,hidden:'true'">ID</th>
					<th data-options="field:'action',width:40,align:'center',formatter:fmaterDetail"></th>
				</tr>
			</thead>
	 	</table> 		
		
		<div id="dialog"  class="easyui-dialog" style="width:90%;height:90%;padding:0px" data-options="
			closed:true,
			resizable:true,	
			modal:true,	
			onResize:function(){
				$(this).dialog('center');
			}">		
		</div> 
	</div>

	<script>
	$('#tt').datagrid({
 			onBeforeLoad(param){
				if(parent.getFatherParameter()=='productionStock.htm'){
					param.name='pro'
				}else{
					param.name='weld'
				}
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
		
		function find(){		
			var queryParams = $('#tt').datagrid('options').queryParams;
			queryParams.deliveryNum = $('#num').combobox('getText');
			queryParams.startTime = $('#startTime').datebox('getText');
			queryParams.endTime = $('#endTime').datebox('getText');
			$('#tt').datagrid('reload');
		}
		
		function isOnlyRead(){
			var str = localStorage.getItem('authority1'); 			
	        str = str.substr(6, 1)
			if(str.charAt(0)==1){	
	        	return true;					        			
	        }else{
	        	return false;
	        }
		}
		
		function add(){
			var param = {};
			param.customer='蒂森扶梯公司';
 			param.supplierCode='100096';		
			if(parent.getFatherParameter()=='productionStock.htm'){
				param.name='pro'
			}else{
				param.name='weld'
			}
			insert(param);
			//insert();
		}
	
 		$('#num').combobox({
			prompt : '请输入送货单号', //提示信息
			//required:true, 	//是否必填
			mode : 'remote', //动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
			url : '${pageContext.request.contextPath}/deliveryNum.htm/autotimp', //请求数据路径				
			editable : true, //可编辑
			hasDownArrow : false, //下拉面板不关闭
			valueField : "id", //数组的键索引
			textField : "name", //数组的值索引
			onBeforeLoad : function(param) { 
				if (param == null || param.q == null || param.q.replace(/ /g, '') == '') {
					var value = $(this).combobox('getValue');
					if (value) { //不为空的时候才传关键字到后台，模糊查询数据到前台
						param.q = value;
					
						return true;
					}
					return false;
				}
			}
		});  
	
		//customer:'蒂森扶梯公司',	 						
		function spreadrow(target){
        	var rowIndex = getRowIndex(target);
        	$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');
	        $("#dialog").dialog({
			     title: row.deliveryNum,
			     queryParams: {deliveryNumID: row.idc, deliveryNum: row.deliveryNum, index:rowIndex},		
			     content:"<iframe scrolling='auto' frameborder='0' src='./productionSendOutItem.jsp' style='width:100%; height:100%; display:block;'></iframe>"
			});	
			$("#dialog").dialog("open"); // 打开dialog     	     	
		}
	
 
        function getDeliveryNumID(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["deliveryNumID"];
		}
		function getDeliveryNum(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["deliveryNum"];
		}
		function getDlgParaRowIndex(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["index"];		
		}
		function deleterow(target){
			return saveOrDelete(target,'${pageContext.request.contextPath}/deliveryNum.htm/getEntity','${pageContext.request.contextPath}/deliveryNum.htm/deleteRow','是否删除？');							
		} 
		
		function saveRow(target){
			return saveOrDelete(target,'${pageContext.request.contextPath}/deliveryNum.htm/getEntity','${pageContext.request.contextPath}/deliveryNum.htm/saveRow','是否保存？');				
		}		
 	</script>
 
</body>
</html>


