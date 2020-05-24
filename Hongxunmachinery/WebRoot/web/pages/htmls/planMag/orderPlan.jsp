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
  	<link rel="stylesheet" type="text/css" href="../../../../jquery-easyui-1.7.0/themes/icon.css">
 	<link rel="stylesheet" href="../../../../jquery-easyui-1.7.0/themes/default/easyui.css"> 
    <script type="text/javascript" src="../../../../jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../jquery-easyui-1.7.0/jquery.easyui.min.js"></script> 
   	<script type="text/javascript" src="../../js/myjs/xlsx.js"></script>
    <script type="text/javascript" src="../../js/myjs/datagrid-export.js"></script>
   	<script type="text/javascript" src="../../js/myjs/orderReadOnly.js"></script>
    <script type="text/javascript" src="../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../js/myjs/jquery.form.js"></script>  
    <script type="text/javascript" src="../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../custom/easyui-lang-zh_CN.js"></script> 
</head>

<body>  
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
		 <div id="tb" style="padding:0 10px;height:40px">	
			<div style="margin-top:5px; margin-left:10px; float:left">	
						<table>
							<tr>
								<td>采购单号:</td>
								<td><input class="easyui-combobox" id="num" style="width:200px;height:25px;"></input></td>	
								<td>状态:</td>
								<td><input class="easyui-textbox" id="status"  style="width:100px;height:25px;"></input></td>						
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
				url:'${pageContext.request.contextPath}/orderNum.htm/getData',
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
					<th data-options="field:'orderNum',title:'订单号',width:100,align:'center',editor:'text'"></th>
					<th data-options="field:'orderQuantity',title:'订单数量',width:100,align:'center'"></th>	
					<th data-options="field:'poCreateDate',title:'创建日期',width:100,align:'center',formatter:fmaterDate"></th>							
 					<th data-options="field:'status',title:'订单状态',width:100,align:'center',editor:'text',formatter:fmaterStatus"></th>
					<th data-options="field:'remark',title:'备注',width:100,align:'center',editor:'text'"></th>
					<th data-options="field:'action',title:'Action',width:30,align:'center',formatter:fmaterDetail"></th> 
					<th data-options="field:'idc',title:'ID',width:20,hidden:'true'"></th> 	
				</tr>
			</thead>
	 	</table> 			
	</div>		
	<div id="dialog"  class="easyui-dialog" style="width:90%;height:90%;padding:0px" data-options="
			closed:true,
			resizable:true,	
			modal:true,	
			onResize:function(){
				$(this).dialog('center');
			}">
		
	</div> 
	<div id="importExcelDlg" class="easyui-dialog" style="width:280px;height:150px;padding:10px 0px" data-options="top:10,closed:true">
			<form action='' enctype="multipart/form-data" method="post" name="fileForm"> 
		        <label >
				  	<!-- <input type="file" name="xlfile" id="xlf" /> -->
 				  	<input type="file" class="file" name="filename" id="fileId" onchange="upload('${pageContext.request.contextPath}/orderNum.htm/exlImport')" accept=".xls"/>
				</label> 			
			</form> 	
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
		function find(){	
 			var queryParams = $('#tt').datagrid('options').queryParams;
			queryParams.orderNum = $('#num').combobox('getText');
			queryParams.startTime = $('#startTime').datebox('getText');
			queryParams.endTime = $('#endTime').textbox('getText');
			queryParams.status = $('#status').textbox('getText');
			$('#tt').datagrid('reload');	
		}
	
		function fmaterStatus(value,row,index){ 			    	 		
			if(value == '订单结束'){
				return  '<span style="color:green">'+value+'</span>';
			}else if(value.indexOf('部分')>-1){
				return  '<span style="color:red">'+value+'</span>';
			}else{	
				return  '<span style="color:orange">'+value+'</span>';
			}			      		
		}			      		 						 	
		
		
		function deleterow(target){
			return saveOrDelete(target,'${pageContext.request.contextPath}/orderNum.htm/getEntity','${pageContext.request.contextPath}/orderNum.htm/deleteRow','是否删除？');							
		} 
		
		function saveRow(target){			
			return saveOrDelete(target,'${pageContext.request.contextPath}/orderNum.htm/getEntity','${pageContext.request.contextPath}/orderNum.htm/saveRow','是否保存？');				
		}
        function spreadrow(target){
        	var rowIndex = getRowIndex(target);
        	$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');
	        $("#dialog").dialog({
			     title: row.orderNum,
			     queryParams: {orderNumID: row.idc,index:rowIndex,orderNum:row.orderNum,status:row.status},		
			     content:"<iframe scrolling='auto' frameborder='0' src='./analysisHtml/orderNum.html' style='width:100%; height:100%; display:block;'></iframe>"
			});
		
			$("#dialog").dialog("open"); // 打开dialog
      	     	
        }
		$('#num').combobox({
		        prompt:'请输入要查询的订单号', 	//提示信息
				//required:true, 	//是否必填
				mode:'remote', 		//动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
				url:'${pageContext.request.contextPath}/orderNum.htm/autotimp', 	//请求数据路径
				editable:true, 		//可编辑
				hasDownArrow:false, 	//下拉面板不关闭
				valueField: "id", 	//数组的键索引
				textField: "name", 	//数组的值索引
				icons:[{
						iconCls:'icon-search'
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
     	
     

     	function upload(url) { 
			var fileName = $('#fileId').val().split('\\'); //得到文件名数组
			var fileSize =  document.getElementById('fileId').files[0]; //获得文件大小；
			fileName2 = fileName[fileName.length-1]; // 获得文件名
			filePath = $('#fileId').val().toLowerCase().split(".");
			fileType =  filePath[filePath.length - 1]; //获得文件结尾的类型如 zip rar 这种写法确保是最后的
			$('#importExcelDlg').dialog('close');
			if(!(fileType == "xls")){
			 	alert('文件格式不符合要求！');
			}else if(fileSize.size>10485760){
			  	alert('文件超过10485760')		       
			}else{
			   	//alert('asdfsa');
				var form = $("form[name=fileForm]"); 
				var options = {  
					url:url, 
					type:'post',
					clearForm:true,
					success:function(data){
							
						if(data[0].hasOwnProperty('error')){
							alert(data[0].error);						
						}else{
							$('#tt').datagrid('loadData', data);
							$.messager.show({
								title:'提示信息',
								msg:'操作成功！'
							});
						}
						
						
					},
					beforeSubmit: function () {						
						load();			        
					},
					complete: function () {
						disLoad();	
					}
				};  
				form.ajaxSubmit(options); 				
			}
    	} 
	  

		function getDlgGetOrderNum(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["orderNum"];
		}

		function getDlgGetOrderNumID(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["orderNumID"];
		}

		function getDlgGetOrderNumStatus(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["status"];
		}
		function getDlgParaRowIndex(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["index"];
		}

	</script>

</body>
</html>


