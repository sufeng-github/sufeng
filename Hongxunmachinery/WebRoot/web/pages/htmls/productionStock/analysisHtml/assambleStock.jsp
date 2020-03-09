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
   	<script type="text/javascript" src="../../../js/myjs/common.js"></script>  
    <script type="text/javascript" src="../../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../../custom/easyui-lang-zh_CN.js"></script>

</head>


<body>
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
 		<div id="tb" style="padding:0 10px;height:auto">	
			<div id="ttdiv" style="width:100%;  height:50px;">
				<div style="margin-top:5px; margin-left:10px; float:left">		
					<input class="easyui-combobox" id="userId" style="width:200px;height:35px;"></input>	
					<a href="javascript:;" id="importBtn" class="easyui-linkbutton"  onclick="$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格')">导入</a>  	  
		        	<a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('toExcel','装配件仓库.xls')">导出</a>
		        	<a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('print','DataGrid')">打印</a>			
				</div>
			</div>		   
		</div> 
		<table id="tt" class="easyui-datagrid" style="width: 100%; height: 100%" 
			data-options="collapsible:true,				
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
					<th data-options="field:'materialNum',align:'center',width:40">物料编码</th>
					<th data-options="field:'specification',align:'center',width:50,editor:'text'">型号/规格</th>
					<th data-options="field:'quantity',align:'center',width:40">库存量</th>
					<th data-options="field:'inQuantity',align:'center',width:40">入库量</th>
					<th data-options="field:'outQuantity',align:'center',width:40">出库量</th>
					<th data-options="field:'safeQuantity',align:'center',width:20,editor:'numberbox'">安全库存</th>
					<th data-options="field:'itemQuantity',align:'center',width:20,editor:'numberbox'">发货量</th>
					<th data-options="field:'inRoadQuantity',align:'center',width:20,editor:'numberbox'">在产量</th>				
					<th data-options="field:'unit',align:'center',width:20">单位</th>
 					<th data-options="field:'localCode',align:'center',width:40,editor:'text'">库位代码</th>
					<th data-options="field:'remark',align:'center',width:20,editor:'text'">备注</th>
					<th data-options="field:'idc',width:10">ID</th>
					<th data-options="field:'action',width:40,align:'center',formatter:fmaterAction"></th>
				</tr>
			</thead>
	 	</table> 		
	</div>	
	<div id="importExcelDlg" class="easyui-dialog" style="width:280px;height:150px;padding:10px 0px" data-options="top:10,closed:true">
		<form action='' enctype="multipart/form-data" method="post" name="fileForm"> 
		  	<label >
				<input type="file" name="xlfile" id="xlf" />	  	
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
			//if(isOnlyRead()==true){
				$('#importBtn').linkbutton('disable');
			//} 

        function isOnlyRead(){
			var str = localStorage.getItem('authority1'); 			
	        str = str.substr(6, 1);
			if(str.charAt(0)==1){	
	        	return true;					        			
	        }else{
	        	return false;
	        }    		        	    	         		
		} 
		
        $('#userId').combobox({
		        prompt:'请输入要查询的部件号', 	//提示信息
				//required:true, 	//是否必填
				mode:'remote', 		//动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
				url:'${pageContext.request.contextPath}/productionStock.htm/autotimp', 	//请求数据路径
				editable:true, 		//可编辑
				hasDownArrow:false, //下拉面板不关闭
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
		        },
		        onSelect: function(record){
		        	//alert(JSON.stringify(record));
		        	$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/productionStock.htm/searchMaterialNum',
						data:record, 
						traditional: true,
						dataType:"json", 
						beforeSend: function () {
							load();
						},
						complete: function () {
							disLoad();
						},
						success: function(result) {	
							//alert(JSON.stringify(result));	
							$('#tt').datagrid('loadData', result);										
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});
		        } 
		});		             	
		function deleterow(target){
			return saveOrDelete(target,'${pageContext.request.contextPath}/productionStock.htm/getEntity','${pageContext.request.contextPath}/productionStock.htm/deleteRow','是否删除？');							
		} 
		
		function saveRow(target){
			return saveOrDelete(target,'${pageContext.request.contextPath}/productionStock.htm/getEntity','${pageContext.request.contextPath}/productionStock.htm/saveRow','是否保存？');				
		}

	</script>

</body>
</html>


