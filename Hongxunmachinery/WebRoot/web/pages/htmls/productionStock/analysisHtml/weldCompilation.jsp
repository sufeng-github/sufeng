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
    <title>焊接盘点</title> 
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
 		<div id="tb" style="padding:0 10px;height:40px">	
						<table>
							<tr>
								<td>部件号:</td>
								<td><input class="easyui-combobox" id="num" style="width:200px;height:25px;"></input></td>						
								<td>型号/规格:</td>
								<td><input class="easyui-textbox" id="spec"  style="width:200px;height:25px;"></input></td>		
<!-- 								<td>状态:</td>
								<td><input class="easyui-textbox" id="alarm"  style="width:100px;height:25px;"></input></td> -->												
								<td><a href="javascript:;" class="easyui-linkbutton"  onclick="find()" >查找</a></td>
		        				<td><a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('toExcel','dg.xls')">导出</a></td>
		        				<td><a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('print','DataGrid')">打印</a></td>
		        				<td><a href="javascript:;" id="sync" class="easyui-linkbutton"   onclick="synchronization()">同步</a></td>	
		        			</tr>
		        		</table>					
		</div> 

		<table id="tt" class="easyui-datagrid" style="width: 100%; height: '100%'" 
			data-options="collapsible:true,				
				url:'${pageContext.request.contextPath}/productionWeldStock.htm/getData',
				toolbar:'#tb',
				singleSelect:false,
				fitColumns:false,
				autoRowHeight:false,
				striped:true,				
				pagination:true,
				remoteSort:false,
				pageList: [10,50,100,500,1000],
				rownumbers:true"
			>
			<thead frozen="true">
				<tr>					
					<th data-options="field:'ck',checkbox:true"></th>
					<th field="idc" align="center" width="20" hidden="true">ID</th>
					<th field="materialNum" align="center" width="120">物料编码</th>
					<th field="specification" align="center" width="280">型号/规格</th>
					<th data-options="field:'action',width:100,align:'center',formatter:fmaterAction"></th>
				</tr>
			</thead>
			<thead>
			 		
				<tr>
					<th colspan="6">一月</th>
					<th colspan="6">二月</th>
					<th colspan="6">三月</th>
					<th colspan="6">四月</th>
					<th colspan="6">五月</th>
					<th colspan="6">六月</th>
					<th colspan="6">七月</th>
					<th colspan="6">八月</th>
					<th colspan="6">九月</th>
					<th colspan="6">十月</th>
					<th colspan="6">十一月</th>
					<th colspan="6">十二月</th>
			
				</tr>
				<tr>
					<th data-options="field:'quantity1',width:60, align:'center',editor:'numberbox'">库存量</th>
					<th data-options="field:'inQuantity1', width:60, align:'center', editor:'numberbox' ">入库量</th> 
					<th data-options="field:'outQuantity1', width:60, align:'center', editor:'numberbox'">出库量</th>
					<th data-options="field:'checkQuantity1', width:60, align:'center', editor:'numberbox'">盘点量</th>
					<th data-options="field:'diffQuantity1', width:60, align:'center', editor:'numberbox'">差异量</th>
					<th data-options="field:'remark1', width:60, align:'center', editor:'textbox'">备注</th>
					<th data-options="field:'quantity2', width:60, align:'center', editor:'numberbox'">库存量</th>
					<th data-options="field:'inQuantity2', width:60, align:'center', editor:'numberbox'">入库量</th>
					<th data-options="field:'outQuantity2', width:60, align:'center', editor:'numberbox'">出库量</th>
					<th data-options="field:'checkQuantity2', width:60, align:'center', editor:'numberbox'">盘点量</th>
					<th data-options="field:'diffQuantity2', width:60, align:'center', editor:'numberbox'">差异量</th>
					<th data-options="field:'remark2', width:60, align:'center', editor:'textbox'">备注</th>
					<th data-options="field:'quantity3', width:60, align:'center', editor:'numberbox'">库存量</th>
					<th data-options="field:'inQuantity3', width:60, align:'center', editor:'numberbox'">入库量</th>
					<th data-options="field:'outQuantity3', width:60, align:'center', editor:'numberbox'">出库量</th>
					<th data-options="field:'checkQuantity3', width:60, align:'center', editor:'numberbox'">盘点量</th>
					<th data-options="field:'diffQuantity3', width:60, align:'center', editor:'numberbox'">差异量</th>
					<th data-options="field:'remark3', width:60, align:'center', editor:'textbox'">备注</th>
					<th data-options="field:'quantity4', width:60, align:'center', editor:'numberbox'">库存量</th>
					<th data-options="field:'inQuantity4', align:'center', width:60, editor:'numberbox'">入库量</th>
					<th data-options="field:'outQuantity4', align:'center', width:60, editor:'numberbox'">出库量</th>
					<th data-options="field:'checkQuantity4', align:'center', width:60, editor:'numberbox'">盘点量</th>
					<th data-options="field:'diffQuantity4', align:'center', width:60, editor:'numberbox'">差异量</th>
					<th data-options="field:'remark4', align:'center', width:60, editor:'textbox'">备注</th> 
					<th data-options="field:'quantity5', 		align:'center', width:60, editor:'numberbox'">库存量</th>
					<th data-options="field:'inQuantity5', 		align:'center', width:60, editor:'numberbox'">入库量</th>
					<th data-options="field:'outQuantity5', 	align:'center', width:60, editor:'numberbox'">出库量</th>
					<th data-options="field:'checkQuantity5', 	align:'center', width:60, editor:'numberbox'">盘点量</th>
					<th data-options="field:'diffQuantity5', 	align:'center', width:60, editor:'numberbox'">差异量</th>
					<th data-options="field:'remark5', 			align:'center', width:60, editor:'textbox'">备注</th>
					<th data-options="field:'quantity6', 		align:'center', width:60, editor:'numberbox'">库存量</th>
					<th data-options="field:'inQuantity6', 		align:'center', width:60, editor:'numberbox'">入库量</th>
					<th data-options="field:'outQuantity6', 	align:'center', width:60, editor:'numberbox'">出库量</th>
					<th data-options="field:'checkQuantity6', 	align:'center', width:60, editor:'numberbox'">盘点量</th>
					<th data-options="field:'diffQuantity6', 	align:'center', width:60, editor:'numberbox'">差异量</th>
					<th data-options="field:'remark6', 			align:'center', width:60, editor:'textbox'">备注</th>
					<th data-options="field:'quantity7', 		align:'center', width:60, editor:'numberbox'">库存量</th>
					<th data-options="field:'inQuantity7', 		align:'center', width:60, editor:'numberbox'">入库量</th>
					<th data-options="field:'outQuantity7', 	align:'center', width:60, editor:'numberbox'">出库量</th>
					<th data-options="field:'checkQuantity7', 	align:'center', width:60, editor:'numberbox'">盘点量</th>
					<th data-options="field:'diffQuantity7', 	align:'center', width:60, editor:'numberbox'">差异量</th>
					<th data-options="field:'remark7', 			align:'center', width:60, editor:'textbox'">备注</th>
					<th data-options="field:'quantity8', 		align:'center', width:60, editor:'numberbox'">库存量</th>
					<th data-options="field:'inQuantity8', 		align:'center', width:60, editor:'numberbox'">入库量</th>
					<th data-options="field:'outQuantity8', 	align:'center', width:60, editor:'numberbox'">出库量</th>
					<th data-options="field:'checkQuantity8', 	align:'center', width:60, editor:'numberbox'">盘点量</th>
					<th data-options="field:'diffQuantity8', 	align:'center', width:60, editor:'numberbox'">差异量</th>
					<th data-options="field:'remark8', 			align:'center', width:60, editor:'textbox'">备注</th>					
					<th data-options="field:'quantity9', 		align:'center', width:60, editor:'numberbox'">库存量</th>
					<th data-options="field:'inQuantity9', 		align:'center', width:60, editor:'numberbox'">入库量</th>
					<th data-options="field:'outQuantity9', 	align:'center', width:60, editor:'numberbox'">出库量</th>
					<th data-options="field:'checkQuantity9', 	align:'center', width:60, editor:'numberbox'">盘点量</th>
					<th data-options="field:'diffQuantity9', 	align:'center', width:60, editor:'numberbox'">差异量</th>
					<th data-options="field:'remark9', 			align:'center', width:60, editor:'textbox'">备注</th>
					<th data-options="field:'quantity10', 		align:'center', width:60, editor:'numberbox'">库存量</th>
					<th data-options="field:'inQuantity10', 	align:'center', width:60, editor:'numberbox'">入库量</th>
					<th data-options="field:'outQuantity10', 	align:'center', width:60, editor:'numberbox'">出库量</th>
					<th data-options="field:'checkQuantity10',	align:'center', width:60, editor:'numberbox'">盘点量</th>
					<th data-options="field:'diffQuantity10', 	align:'center', width:60, editor:'numberbox'">差异量</th>
					<th data-options="field:'remark10', 		align:'center', width:60, editor:'textbox'">备注</th>
					<th data-options="field:'quantity11', 		align:'center', width:60, editor:'numberbox'">库存量</th>
					<th data-options="field:'inQuantity11', 	align:'center', width:60, editor:'numberbox'">入库量</th>
					<th data-options="field:'outQuantity11', 	align:'center', width:60, editor:'numberbox'">出库量</th>
					<th data-options="field:'checkQuantity11', 	align:'center', width:60, editor:'numberbox'">盘点量</th>
					<th data-options="field:'diffQuantity11', 	align:'center', width:60, editor:'numberbox'">差异量</th>
					<th data-options="field:'remark11', 		align:'center', width:60, editor:'textbox'">备注</th>
					<th data-options="field:'quantity12', 		align:'center', width:60, editor:'numberbox'">库存量</th>
					<th data-options="field:'inQuantity12', 	align:'center', width:60, editor:'numberbox'">入库量</th>
					<th data-options="field:'outQuantity12', 	align:'center', width:60, editor:'numberbox'">出库量</th>
					<th data-options="field:'checkQuantity12', 	align:'center', width:60, editor:'numberbox'">盘点量</th>
					<th data-options="field:'diffQuantity12', 	align:'center', width:60, editor:'numberbox'">差异量</th>
					<th data-options="field:'remark12', 		align:'center', width:60, editor:'textbox'">备注</th>
				</tr>
			</thead>
		</table>
	</div>		

	<script>
/* 		function fmaterAction1 (value,row,index){
		 			//if(isOnlyRead()==false){				
						if (row.editing){				
							var s = '<a href="#" onclick="saveRow(this)">保存</a> ';
							var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
							return s+c;
						}else {				
							var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
							var d = '<a href="#" onclick="deleterow(this)">删除</a>';
							return e+d;
						}
					//}	 
		} */
		function find(){		
			var queryParams = $('#tt').datagrid('options').queryParams;
			queryParams.materialNum = $('#num').combobox('getText');
			queryParams.specification = $('#spec').textbox('getText');
			$('#tt').datagrid('reload');
		}
	
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
		
			
			$('#tt').datagrid({	
				loadFilter: function(data){									
 					for(var i=0; i<data.rows.length;i++){
						if(data.rows[i].hongXunWeldCompilation != null){
							data.rows[i].quantity1		=	data.rows[i].hongXunWeldCompilation.quantity1;
							data.rows[i].inQuantity1	=	data.rows[i].hongXunWeldCompilation.inQuantity1;
							data.rows[i].outQuantity1	=	data.rows[i].hongXunWeldCompilation.outQuantity1;
							data.rows[i].checkQuantity1	=	data.rows[i].hongXunWeldCompilation.checkQuantity1;
							data.rows[i].diffQuantity1	=	data.rows[i].hongXunWeldCompilation.diffQuantity1;
							data.rows[i].remark1		=	data.rows[i].hongXunWeldCompilation.remark1;
							data.rows[i].quantity2		=	data.rows[i].hongXunWeldCompilation.quantity2;
							data.rows[i].inQuantity2	=	data.rows[i].hongXunWeldCompilation.inQuantity2;
							data.rows[i].outQuantity2	=	data.rows[i].hongXunWeldCompilation.outQuantity2;
							data.rows[i].checkQuantity2	=	data.rows[i].hongXunWeldCompilation.checkQuantity2;
							data.rows[i].diffQuantity2	=	data.rows[i].hongXunWeldCompilation.diffQuantity2;
							data.rows[i].remark2		=	data.rows[i].hongXunWeldCompilation.remark2;
							data.rows[i].quantity3		=	data.rows[i].hongXunWeldCompilation.quantity3;
							data.rows[i].inQuantity3	=	data.rows[i].hongXunWeldCompilation.inQuantity3;
							data.rows[i].outQuantity3	=	data.rows[i].hongXunWeldCompilation.outQuantity3;
							data.rows[i].checkQuantity3	=	data.rows[i].hongXunWeldCompilation.checkQuantity3;
							data.rows[i].diffQuantity3	=	data.rows[i].hongXunWeldCompilation.diffQuantity3;
							data.rows[i].remark3		=	data.rows[i].hongXunWeldCompilation.remark3;
							data.rows[i].quantity4		=	data.rows[i].hongXunWeldCompilation.quantity4;
							data.rows[i].inQuantity4	=	data.rows[i].hongXunWeldCompilation.inQuantity4;
							data.rows[i].outQuantity4	=	data.rows[i].hongXunWeldCompilation.outQuantity4;
							data.rows[i].checkQuantity4	=	data.rows[i].hongXunWeldCompilation.checkQuantity4;
							data.rows[i].diffQuantity4	=	data.rows[i].hongXunWeldCompilation.diffQuantity4;
							data.rows[i].remark4		=	data.rows[i].hongXunWeldCompilation.remark4;
							data.rows[i].quantity5		=	data.rows[i].hongXunWeldCompilation.quantity5;
							data.rows[i].inQuantity5	=	data.rows[i].hongXunWeldCompilation.inQuantity5;
							data.rows[i].outQuantity5	=	data.rows[i].hongXunWeldCompilation.outQuantity5;
							data.rows[i].checkQuantity5	=	data.rows[i].hongXunWeldCompilation.checkQuantity5;
							data.rows[i].diffQuantity5	=	data.rows[i].hongXunWeldCompilation.diffQuantity5;
							data.rows[i].remark5		=	data.rows[i].hongXunWeldCompilation.remark5;
							data.rows[i].quantity6		=	data.rows[i].hongXunWeldCompilation.quantity6;
							data.rows[i].inQuantity6	=	data.rows[i].hongXunWeldCompilation.inQuantity6;
							data.rows[i].outQuantity6	=	data.rows[i].hongXunWeldCompilation.outQuantity6;
							data.rows[i].checkQuantity6	=	data.rows[i].hongXunWeldCompilation.checkQuantity6;
							data.rows[i].diffQuantity6	=	data.rows[i].hongXunWeldCompilation.diffQuantity6;
							data.rows[i].remark6		=	data.rows[i].hongXunWeldCompilation.remark6;
							data.rows[i].quantity7		=	data.rows[i].hongXunWeldCompilation.quantity7;
							data.rows[i].inQuantity7	=	data.rows[i].hongXunWeldCompilation.inQuantity7;
							data.rows[i].outQuantity7	=	data.rows[i].hongXunWeldCompilation.outQuantity7;
							data.rows[i].checkQuantity7	=	data.rows[i].hongXunWeldCompilation.checkQuantity7;
							data.rows[i].diffQuantity7	=	data.rows[i].hongXunWeldCompilation.diffQuantity7;
							data.rows[i].remark7		=	data.rows[i].hongXunWeldCompilation.remark7;
							data.rows[i].quantity8		=	data.rows[i].hongXunWeldCompilation.quantity8;
							data.rows[i].inQuantity8	=	data.rows[i].hongXunWeldCompilation.inQuantity8;
							data.rows[i].outQuantity8	=	data.rows[i].hongXunWeldCompilation.outQuantity8;
							data.rows[i].checkQuantity8	=	data.rows[i].hongXunWeldCompilation.checkQuantity8;
							data.rows[i].diffQuantity8	=	data.rows[i].hongXunWeldCompilation.diffQuantity8;
							data.rows[i].remark8		=	data.rows[i].hongXunWeldCompilation.remark8;
							data.rows[i].quantity9		=	data.rows[i].hongXunWeldCompilation.quantity9;
							data.rows[i].inQuantity9	=	data.rows[i].hongXunWeldCompilation.inQuantity9;
							data.rows[i].outQuantity9	=	data.rows[i].hongXunWeldCompilation.outQuantity9;
							data.rows[i].checkQuantity9	=	data.rows[i].hongXunWeldCompilation.checkQuantity9;
							data.rows[i].diffQuantity9	=	data.rows[i].hongXunWeldCompilation.diffQuantity9;
							data.rows[i].remark9		=	data.rows[i].hongXunWeldCompilation.remark9;
							data.rows[i].quantity10		=	data.rows[i].hongXunWeldCompilation.quantity10;
							data.rows[i].inQuantity10	=	data.rows[i].hongXunWeldCompilation.inQuantity10;
							data.rows[i].outQuantity10	=	data.rows[i].hongXunWeldCompilation.outQuantity10;
							data.rows[i].checkQuantity10	=	data.rows[i].hongXunWeldCompilation.checkQuantity10;
							data.rows[i].diffQuantity10	=	data.rows[i].hongXunWeldCompilation.diffQuantity10;
							data.rows[i].remark10		=	data.rows[i].hongXunWeldCompilation.remark10;
							data.rows[i].quantity11		=	data.rows[i].hongXunWeldCompilation.quantity11;
							data.rows[i].inQuantity11	=	data.rows[i].hongXunWeldCompilation.inQuantity11;
							data.rows[i].outQuantity11	=	data.rows[i].hongXunWeldCompilation.outQuantity11;
							data.rows[i].checkQuantity11	=	data.rows[i].hongXunWeldCompilation.checkQuantity11;
							data.rows[i].diffQuantity11	=	data.rows[i].hongXunWeldCompilation.diffQuantity11;
							data.rows[i].remark11		=	data.rows[i].hongXunWeldCompilation.remark11;
							data.rows[i].quantity12		=	data.rows[i].hongXunWeldCompilation.quantity12;
							data.rows[i].inQuantity12	=	data.rows[i].hongXunWeldCompilation.inQuantity12;
							data.rows[i].outQuantity12	=	data.rows[i].hongXunWeldCompilation.outQuantity12;
							data.rows[i].checkQuantity12	=	data.rows[i].hongXunWeldCompilation.checkQuantity12;
							data.rows[i].diffQuantity12	=	data.rows[i].hongXunWeldCompilation.diffQuantity12;
							data.rows[i].remark12		=	data.rows[i].hongXunWeldCompilation.remark12;
						}					
					} 
					return data;
					
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
         
		function isOnlyRead(){
/* 			var str = localStorage.getItem('authority2'); 			
	        str = str.substr(0, 1)
			if(str.charAt(0)==1){	
	        	return true;					        			
	        }else{
	        	return false;
	        } */    
	        return false;		        	    	         		
		}
		
		$('#num').combobox({
			prompt : '请输入部件号', //提示信息
			//required:true, 	//是否必填
			mode : 'remote', //动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
			url : '${pageContext.request.contextPath}/productionWeldStock.htm/autotimp', //请求数据路径				
			editable : true, //可编辑
			hasDownArrow : false, //下拉面板不关闭
			valueField : "id", //数组的键索引
			textField : "name", //数组的值索引
			queryParams : {
				//orderNumID : window.parent.window.getOrderNumID()
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
						//param["outStoreNumID"] = window.parent.window.getOutStoreNumID();
						return true;
					}
					return false;
				}
			}
		});

		function saveRow(target){			
			return saveOrDelete(target,'${pageContext.request.contextPath}/weldCompilation.htm/getEntity','${pageContext.request.contextPath}/weldCompilation.htm/saveRow','是否保存？');				
		}
		
		function synchronization(){
			$.messager.confirm('Confirm', '是否同步库存?', function(r) {
				if (r) {

					$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/weldCompilation.htm/synchronization',
						//data:JSON.stringify(userList), 
						traditional: true,
						dataType:"json", 
						//contentType : 'application/json;charset=utf-8', //设置请求头信息  
						beforeSend: function () {
							load();
						},
						complete: function () {
							disLoad();
						},
						success: function(result) {		
							/*$('#tt').datagrid('updateRow',{
							
							});	*/	
							alert("同步完成")									
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});
				}
			});		
		}
	
	</script> 
</body>
</html>


