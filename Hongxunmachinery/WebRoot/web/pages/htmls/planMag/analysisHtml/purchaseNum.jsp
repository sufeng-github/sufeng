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
    <title>采购信息</title> 
  	<link rel="stylesheet" type="text/css" href="../../../../../jquery-easyui-1.7.0/themes/icon.css">
 	<link rel="stylesheet" href="../../../../../jquery-easyui-1.7.0/themes/default/easyui.css"> 
    <script type="text/javascript" src="../../../../../jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../../jquery-easyui-1.7.0/jquery.easyui.min.js"></script> 
   	<script type="text/javascript" src="../../../js/myjs/xlsx.js"></script>
    <script type="text/javascript" src="../../../js/myjs/datagrid-export.js"></script>
	<script type="text/javascript" src="../../../js/myjs/purchaseReadOnly.js"></script>
    <script type="text/javascript" src="../../../js/myjs/myjs.js"></script>   
    <script type="text/javascript" src="../../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../../custom/easyui-lang-zh_CN.js"></script>
</head>

<body>  
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
 		<div id="tb" style="padding:0 10px;height:40px">	
			<div style="margin-top:5px; margin-left:10px; float:left">	
						<table>
							<tr>
								<td>采购单号:</td>
								<td><input class="easyui-combobox" id="num" style="width:200px;height:25px;"></input></td>	
								<!-- <td>状态:</td> -->
								<!-- <td><input class="easyui-textbox" id="status"  style="width:100px;height:25px;"></input></td>	 -->					
								<td>开始时间:</td>
								<td><input class="easyui-datebox" id="startTime"  style="height:25px;width:150px;line-height:25px;"></input></td>						
								<td>结束时间:</td>
								<td><input class="easyui-datebox" id="endTime"  style="height:25px;width:150px;line-height:25px;"></input></td>
								<td><a href="javascript:;" class="easyui-linkbutton"  onclick="find()" >查找</a></td>
								<td><a href="javascript:;" id="importBtn" class="easyui-linkbutton"  onclick="$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格')">导入</a> </td>
								<td><a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('toExcel','实时库存.xls')">导出</a></td>
								<td><a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('print','DataGrid')">打印</a>			</td>
							</tr>
						</table>						
			 </div> 			
		</div>
		 
		<table id="tt" class="easyui-datagrid" style="width: 100%; height: '100%'" 
			data-options="collapsible:true,				
				url:'${pageContext.request.contextPath}/purchaseNum.htm/getData',
				toolbar:'#tb',
				singleSelect:false,
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
					<th data-options="field:'ck',checkbox:true"></th>	
					<th data-options="field:'purchaseNum',title:'采购单号',width:100,align:'center'"></th>
					<th data-options="field:'productionDes',title:'采购项目',width:150,align:'center',editor:'text'"></th>
					<th data-options="field:'purchaseDate',title:'采购日期',width:130,align:'center',sortable: true,formatter:fmaterDate"></th>				
					<th data-options="field:'purchasingAgent',title:'采购员',width:100,align:'center',editor:'text'"></th>
					<th data-options="field:'purchaseAmount',title:'采购金额',width:80,align:'center',formatter:fmaterColor"></th>	
					<th data-options="field:'purchaseSupplier',title:'供应商',width:80,align:'center',editor:'text'"></th> 					
 					<th data-options="field:'purchaseStatus',title:'采购入库率',width:50,align:'center',formatter:fmaterState"></th>							
					<th data-options="field:'purchaseRemark',title:'备注',width:100,align:'center',editor:'text'"></th>	
					<th data-options="field:'action',title:'',width:60,formatter:fmaterLoaDetail"></th>				
				</tr>
			</thead>
	 	</table> 				
		<!-- <a href="javascript:void(0);" onclick="CreateFormPage('打印测试', $('#tt'));">打印</a>	 -->
	</div>		
	<div id="dialog"  class="easyui-dialog" style="width:90%;height:90%;padding:0px" data-options="
			closed:true,
			resizable:true,	
			modal:true,		
			onResize:function(){
				$(this).dialog('center');
			}">
	</div> 
 	
	<script>
		$('#tt').datagrid({			
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
	
 		function fmaterLoaDetail(value, row, index) {
				if (row.editing) {
					var s = '<a href="#" onclick="saveRow(this)">保存</a> ';
					var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
					return s + c;
				} else {
					var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
					var d = '<a href="#" onclick="deleterow(this)">删除</a>';
					var t = '<a href="#" onclick="spreadrow(this)"> 详细</a>';
					if(parent.getFatherParameter()=='../../materialStock/instoreHtml/purchaseInStock.html'){
						return t;
					}else{
						return e + d + t;
					}
					
				}
		} 
	
		function find(){	
 			var queryParams = $('#tt').datagrid('options').queryParams;
			queryParams.purchaseNum = $('#num').combobox('getText');
			queryParams.startTime = $('#startTime').datebox('getText');
			queryParams.endTime = $('#endTime').datebox('getText');
			//queryParams.purchaseStatus = $('#status').textbox('getText');
			$('#tt').datagrid('reload');	
		}
      
        function spreadrow(target){
        	var rowIndex = getRowIndex(target);
        	$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');	 	             
	       	var url = parent.getFatherParameter();
	       //	alert(url);
	        $("#dialog").dialog({
			     title: '采购单号 :' + row.purchaseNum,
			     queryParams: {purchaseNumID: row.idc},		
			     content:"<iframe scrolling='auto' frameborder='0' src='" + url +"' style='width:100%; height:100%; display:block;'></iframe>"
			});		
			$("#dialog").dialog("open"); //打开dialog			 	
        }
        
		function getFatherDeItemStr() {
        	return	parent.getFatherDeItemStr();
		}
		
       	$('#num').combobox({
		        prompt:'请输入要查询的采购单', 	//提示信息
				//required:true, 	//是否必填
				mode:'remote', 		//动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
				url:'${pageContext.request.contextPath}/purchaseNum.htm/autotimp', 	//请求数据路径
				editable:true, 		//可编辑
				hasDownArrow:false, 	//下拉面板不关闭
				valueField: "id", 	//数组的键索引
				textField: "name", 	//数组的值索引
				icons:[{
						//iconCls:'icon-search'
					}],
				onBeforeLoad: function(param){ 	//onBeforeLoad：在请求加载数据之前触发，返回 false 则取消加载动作，为true的话则重新加载数据。
				    console.log("------ "+param.q+" ------"); 	//param.q ：combobox框输入的参数，请求方式POST
				    if(param == null || param.q == null || param.q.replace(/ /g, '') == ''){ 
				        var value = $(this).combobox('getValue'); 
				        if(value){	//不为空的时候才传关键字到后台，模糊查询数据到前台
					    param.q = value; 
					    return true; 
				        } 
				        return false; 
				    } 
		        }				
		});		 
		
		function getPurchaseNumID(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["purchaseNumID"];
		}
		function deleterow(target){
			return saveOrDelete(target,'${pageContext.request.contextPath}/purchaseNum.htm/getEntity','${pageContext.request.contextPath}/purchaseNum.htm/deleteRow','是否删除？');							
		} 
		
		function saveRow(target){			
			return saveOrDelete(target,'${pageContext.request.contextPath}/purchaseNum.htm/getEntity','${pageContext.request.contextPath}/purchaseNum.htm/saveRow','是否保存？');				
		}           
	</script>
 	
</body>
</html>


