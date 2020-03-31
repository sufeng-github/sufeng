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
								<td>部件号:</td>
								<td><input class="easyui-combobox" id="num" style="width:200px;height:25px;"></input></td>						
								<td>型号/规格:</td>
								<td><input class="easyui-textbox" id="spec"  style="width:200px;height:25px;"></input></td>														
								<td><a href="javascript:;" class="easyui-linkbutton"  onclick="find()" >查找</a></td>
								<td><a href="javascript:;" id="importBtn" class="easyui-linkbutton"  onclick="$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格')">导入</a> </td>
								<td><a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('toExcel','送货单详细.xls')">导出</a></td>
								<td><a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('print','DataGrid')">打印</a></td>
		
							</tr>
						</table>						
			 </div> 			
		</div> 
		<table id="tt" class="easyui-datagrid" style="width: 100%; height: 100%" 
			data-options="collapsible:true,				
				url:'${pageContext.request.contextPath}/deliveryItem.htm/getData',
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
					<th data-options="field:'pon',title:'PO号',align:'center',width:40,editor:'textbox'"></th>								
					<th data-options="field:'line',title:'行号',align:'center',width:30,editor:'textbox'"></th>								
					<th data-options="field:'materialNo',title:'物料号',width:40,align:'center',editor:'textbox'"></th>   					
					<th data-options="field:'materialDesc',title:'物料描述',align:'center',width:150,editor:'textbox'"></th>		
					<th data-options="field:'exemption',title:'免检',align:'center',width:20,editor:'textbox'"></th>	
					<th data-options="field:'code',title:'仓储代码',align:'center',width:20,editor:'textbox'"></th>
					<th data-options="field:'unit',title:'单位',align:'center',width:20,editor:'textbox'"></th>
					<th data-options="field:'projectNum',title:'项目号',align:'center',width:20,editor:'textbox'"></th>
					<th data-options="field:'sendQuantity',title:'送货数量',align:'center',width:20,editor:'textbox'"></th>
					<th data-options="field:'status',title:'状态',align:'center',width:20,formatter:fmaterLColors"></th>
					<th data-options="field:'remark',title:'备注',align:'center',width:20,editor:'textbox'"></th>				
					<th data-options="field:'idc',title:'ID',width:20,hidden:'true'"></th>
					<th data-options="field:'action',width:40,align:'center',formatter:fmaterLAction"></th>
				</tr>
			</thead>
	 	</table> 			
	</div>
   	<div id="importExcelDlg" class="easyui-dialog" style="width:280px;height:150px;padding:10px 0px" data-options="top:10,closed:true">
		<form action='' enctype="multipart/form-data" method="post" name="fileForm"> 
		  	<label >
		  		<input type="text" id="nodeID" name="nodeID" style="display:none" />
				<input type="file" name="xlfile" id="xlf" />	  	
			</label> 			
		</form> 	
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
		function fmaterLColors(value,row,index){ 	
	    	if(value == '完成'){
				return  '<span style="color:green">'+value+'</span>';
			}else {
				return  '<span style="color:red">'+value+'</span>';								
			}
	    }
	    function fmaterLAction(value,row,index){ 
	    	if (row.editing){				
				var s = '<a href="#" onclick="saveRow(this)">保存</a> ';
				var c = '<a href="#" onclick="cancelrow(this)">取消</a>';						
				return s+c;
			}else {				
				var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
				var t = '<a href="#" onclick="itemInStock(this)">出库</a> ';
				if(row.status=='完成'){
		    		return e;
		    	}else{		    		
		    		return e+t;
		    	}										
			} 	
	    }
		function find(){		
			var queryParams = $('#tt').datagrid('options').queryParams;
			queryParams.specification = $('#spec').combobox('getText');
			$('#tt').datagrid('reload');
		}
		$('#tt').datagrid({
			queryParams: {				
				deliveryNumID:window.parent.window.getDeliveryNumID('#dialog')
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
		
/*         function deleterow(target){
			return saveOrDelete(target,'${pageContext.request.contextPath}/deliveryItem.htm/getEntity','${pageContext.request.contextPath}/deliveryItem.htm/deleteRow','是否删除？');							
		}  */
		
		function saveRow(target){
			return saveOrDelete(target,'${pageContext.request.contextPath}/deliveryItem.htm/getEntity','${pageContext.request.contextPath}/deliveryItem.htm/saveRow','是否保存？');				
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
	
		function itemInStock(target){
        	var rowIndex = getRowIndex(target);
        	$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');
	        $("#dialog").dialog({
			     title: '成品仓库',
			     queryParams: {idc:row.idc, materialNum: row.materialNo, sendQuantity: row.sendQuantity},		
			     content:"<iframe scrolling='auto' frameborder='0' src='./itemOutStock.jsp' style='width:100%; height:100%; display:block;'></iframe>"
			});	
			$("#dialog").dialog("open"); // 打开dialog     	     	
		}
		
		$('#num').combobox({
			prompt : '请输入部件号', //提示信息
			//required:true, 	//是否必填
			mode : 'remote', //动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
			url : '${pageContext.request.contextPath}/deliveryItem.htm/autotimp', //请求数据路径				
			editable : true, //可编辑
			hasDownArrow : false, //下拉面板不关闭
			valueField : "id", //数组的键索引
			textField : "name", //数组的值索引
 			queryParams : {
				deliveryNumID:window.parent.window.getDeliveryNumID('#dialog')
			},
			icons : [ {
				iconCls : 'icon-search'
			} ],
			onBeforeLoad : function(param) { //onBeforeLoad：在请求加载数据之前触发，返回 false 则取消加载动作，为true的话则重新加载数据。
				//console.log("------ "+param.q+" ------"); 	//param.q ：combobox框输入的参数，请求方式POST
				if (param == null || param.q == null || param.q.replace(/ /g, '') == '') {
					var value = $(this).combobox('getValue');
					if (value) { //不为空的时候才传关键字到后台，模糊查询数据到前台
						param.q = value;
						//param.deliveryNumID=window.parent.window.getDeliveryNumID('#dialog');
						return true;
					}
					return false;
				}
			}
		});

        function getDeliveryItemMaterialNum(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["materialNum"];
		}
		function getDeliveryItemID(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["idc"];
		}
		function getDeliveryItemSendQuantity(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["sendQuantity"];
		}
		(function() {
			var xlf = document.getElementById('xlf');
			if(!xlf.addEventListener) return;
			function handleFile(e) {
				//$('#tt').datagrid('importexcl','DataGrid');	
				do_file(e.target.files);				
			}
			xlf.addEventListener('change', handleFile, false);
		})();
		
		var process_wb = (function() {
			var to_json = function to_json(workbook) {
				var result = {};
				workbook.SheetNames.forEach(function(sheetName) {
					var roa = XLSX.utils.sheet_to_json(workbook.Sheets[sheetName], {header:1});
					if(roa.length) result[sheetName] = roa;
				});
				return (result[Object.keys(result)[0]]);
			};
		
			return function process_wb(wb) {	
				return to_json(wb)		
			};
		})();
	
		var do_file = (function() {
			return function do_file(files) {
				var f = files[0];
				var reader = new FileReader();
				reader.onload = function(e) {
					var data = e.target.result;
					var result =  process_wb(XLSX.read(data, {type: 'binary'}));
					var fields = $('#tt').datagrid('getColumnFields',true).concat($('#tt').datagrid('getColumnFields',false));		
					var jsonHead = {};
					for(var i=0; i<fields.length; i++){
					 	var option = $("#tt").datagrid('getColumnOption', fields[i]);
						if (option.field != "checkItem") { //过滤勾选框和隐藏列
		                   jsonHead[fields[i]]= option.title;
		                }
					}
					//alert(JSON.stringify(jsonHead));
					var data = new Array(); 					
					for(var i=1; i< result.length; i++){
						var jsonRow = {};
						for(var j=0; j< result[i].length; j++){	
							for(var p in jsonHead){  
								if(result[0][j]=== jsonHead[p]){
							  　　　　　	jsonRow[p]= result[i][j];
							　　　　　	break;  
							　　　}  						
							}
						}
						jsonRow.deliveryNumID=window.parent.window.getDeliveryNumID('#dialog');
						data.push(jsonRow);
  					}
  					//alert(JSON.stringify(data));
 	  				$.ajax({
	  					type : "post",
  						url : '${pageContext.request.contextPath}/deliveryItem.htm/importData',
	  					data : JSON.stringify(data),
	  					//traditional: true,
	  					dataType : "json",
	  					contentType : 'application/json;charset=utf-8', //设置请求头信息  
	  					beforeSend : function() {
	  						load();
	  					},
	  					complete : function() {
	  						disLoad();
	  					},
	  					success : function(result) {
	  						$('#tt').datagrid('reload');
	  						$('#importExcelDlg').dialog('close');
	  					},
	  					error : function() {
	  						alert("异常！");
	  					}
	  				}); 
  				};
				reader.readAsBinaryString(f);
			};
			
		})();
	</script> 
</body>
</html>


