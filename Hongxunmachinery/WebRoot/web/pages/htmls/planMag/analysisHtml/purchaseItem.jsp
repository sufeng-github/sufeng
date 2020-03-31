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
								<td>部件号:</td>
								<td><input class="easyui-combobox" id="num" style="width:200px;height:25px;"></input></td>						
								<td>型号/规格:</td>
								<td><input class="easyui-textbox" id="spec"  style="width:200px;height:25px;"></input></td>										
								<td>供应商:</td>
								<td><input class="easyui-textbox" id="supplier"  style="width:100px;height:25px;"></input></td>					
								<td><a href="javascript:;" class="easyui-linkbutton"  onclick="find()" >查找</a></td>
<!-- 								<td><a href="javascript:;" id="importBtn" class="easyui-linkbutton"  onclick="$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格')">导入</a> </td> -->
								<td><a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('toExcel','采购单.xls')">导出</a></td>
								<td><a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('print','DataGrid')">打印</a>			</td>
							</tr>
						</table>						
			 </div> 			
		</div>
 		
		<table id="tt" class="easyui-datagrid" style="width: 100%; height: '100%'" 
			data-options="collapsible:true,				
				url:'${pageContext.request.contextPath}/purchaseItem.htm/getData',
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
					<th data-options="field:'materialNum',title:'物料编码',align:'center',width:40"></th>									
					<th data-options="field:'specification',title:'型号/规格',align:'center',width:100"></th>	
					<th data-options="field:'quantity',align:'center',width:20">库存量</th>
					<th data-options="field:'inRoadQuantity',align:'center',width:20,editor:'numberbox'">在途数</th>	
					<th data-options="field:'inQuantity',align:'center',width:20">入库量</th>		
					<th data-options="field:'materialPurchaseQuantity',title:'采购量',align:'center',width:30,formatter:fmaterColor"></th>					
					<th data-options="field:'materialRealQuantity',title:'到货总数量',align:'center',width:30,formatter:fmaterCompare"></th>  
					<th data-options="field:'arrivalQuantity',title:'最新入库量',align:'center',width:30"></th>   	
			      	<th data-options="field:'unit',title:'单位',align:'center',width:20,editor:'text'"></th>    
					<th data-options="field:'price',title:'单价',align:'center',width:20,editor:'numberbox'"></th>  
					<th data-options="field:'materialMoney',title:'金额',align:'center',width:20"></th>  
					<th data-options="field:'supplier',title:'供应商',align:'center',width:60,editor:'text'"></th>  	
					<th data-options="field:'deliveryDate',title:'交货日期',align:'center',width:40,editor:'datebox',formatter:fmaterDate"></th>   
					<th data-options="field:'materialRemark',title:'备注',align:'center',width:20,editor:'text'"></th>  
					<th data-options="field:'action',title:'',width:30,align:'center',formatter:fmaterLaction"></th> 
					<th data-options="field:'idc',title:'ID',width:20,hidden:'true'"></th> 
					<th data-options="field:'purchaseNumID',title:'purchaseNumID',width:20,hidden:'true'"></th> 
				</tr>
			</thead>
	 	</table> 			
	</div>		
	<div id="dlg" class="easyui-dialog" title="入库对话框" style="width:400px;height:200px;padding:10px"
			data-options="	
			closed:true,modal:true,			
				buttons: [{
					text:'确定',					
					handler:confirmStockIn
				},{
					text:'取消',
					handler:function(){						
						$('#dlg').dialog('close');
					}
				}]
			">
			<table>
							<tr>
								<td>部件号:</td>
								<td><input class="easyui-textbox" id="materialNum" editable=false style="width:280px;height:25px;"></input></td>	
							</tr>
							<tr>						
								<td>型号/规格:</td>
								<td><input class="easyui-textbox" id="specification" editable=false style="width:280px;height:25px;"></input></td>	
							</tr>
							<tr>										
								<td>数量:</td>
								<td><input class="easyui-numberbox" id="quantity"  style="width:100px;height:25px;"></input></td>									
							</tr>
							<!-- 保存行的index -->
							<tr>																	
								<td style="display:none;"><input class="easyui-numberbox" id="index"  style="width:100px;height:25px;" ></input></td>									
							</tr>
			</table>	
	</div>	
	<script>	
	
		function find(){				
 			var queryParams = $('#tt').datagrid('options').queryParams;
			queryParams.materialNum = $('#num').combobox('getText');
			queryParams.specification = $('#spec').textbox('getText');
			queryParams.supplier = $('#supplier').textbox('getText');			
			$('#tt').datagrid('reload');	
		}
		function fmaterCompare(value,row,index){ 							
			if(value != undefined){		
				if(row.materialPurchaseQuantity <= row.materialRealQuantity){
					return  '<span style="color:green">'+value+'</span>';			
				}else{									
					return  '<span style="color:red">'+value+'</span>';	
				}
			}					    	 	
		}
		function fmaterLaction (value,row,index){			
		 	if(parent.getFatherDeItemStr()=="purchase"){		
				if (row.editing){				
					var s = '<a href="#" onclick="saveRow(this)">保存</a> ';
					var c = '<a href="#" onclick="cancelrow(this)">取消</a>';		
					return s+c;
				}else {				
					var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
					var d = '<a href="#" onclick="deleterow(this)">删除</a>';		
					return e+d;
				}
			}else if(parent.getFatherDeItemStr()=="material"){
				var f = '<a href="#" onclick="stockinrow(this)">入库</a>';
				return f;
			}				
		} 
		
		function stockinrow(target){	
			$('#tt').datagrid('unselectAll');			
			$('#tt').datagrid('selectRow',getRowIndex(target));
			var row = $("#tt").datagrid('getSelected');
			$('#dlg').dialog('open');
			$('#dlg').window('center');
			$('#materialNum').textbox('setText', row.materialNum);
			$('#specification').textbox('setText', row.specification);
			$('#quantity').textbox('setText',row.materialPurchaseQuantity);
			$('#index').numberbox('setText',getRowIndex(target));	
		}
		
		function confirmStockIn(){
			$('#dlg').dialog('close');
			var indx = $('#index').numberbox('getText');
			$('#tt').datagrid('updateRow',{
				index: parseInt(indx),
				row: {
					arrivalQuantity : $('#quantity').textbox('getText')
				}
			});
			purchaseItemStockIn(indx);
		}
		
	 	$('#tt').datagrid({	
	 	
	 		loadFilter: function(data){										
 				for(var i=0; i<data.rows.length;i++){
					if(data.rows[i].hongXunPurchaseDeItem.hongXunMaterialStock != null){						
						data.rows[i].materialNum 				= data.rows[i].hongXunPurchaseDeItem.hongXunMaterialStock.materialNum;
						data.rows[i].specification 				= data.rows[i].hongXunPurchaseDeItem.hongXunMaterialStock.specification;
						data.rows[i].materialPurchaseQuantity 	= data.rows[i].hongXunPurchaseDeItem.quantity;	
						data.rows[i].deliveryDate 				= data.rows[i].hongXunPurchaseDeItem.hongXunMaterialStock.deliveryDate;
						data.rows[i].unit 						= data.rows[i].hongXunPurchaseDeItem.hongXunMaterialStock.unit;		
						data.rows[i].quantity 					= data.rows[i].hongXunPurchaseDeItem.hongXunMaterialStock.quantity;							
						data.rows[i].inQuantity 				= data.rows[i].hongXunPurchaseDeItem.hongXunMaterialStock.inQuantity;												
						data.rows[i].inRoadQuantity				= data.rows[i].hongXunPurchaseDeItem.hongXunMaterialStock.inRoadQuantity;												
					}					
				} 
				return data;		
			},
	 		queryParams: {
				purchaseNumId:window.parent.window.getPurchaseNumID('#dialog')
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
		      
        $('#num').combobox({
			prompt : '请输入要查询的供应商', //提示信息
			//required:true, 	//是否必填
			mode : 'remote', //动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
			url : '${pageContext.request.contextPath}/purchaseItem.htm/autotimp', //请求数据路径				
			editable : true, //可编辑
			hasDownArrow : false, //下拉面板不关闭
			valueField : "id", //数组的键索引
			textField : "name", //数组的值索引
			queryParams: {purchaseNumID:window.parent.window.getPurchaseNumID("#dialog")},
			icons : [ {
				iconCls : 'icon-search'
			} ],
			onBeforeLoad : function(param) { //onBeforeLoad：在请求加载数据之前触发，返回 false 则取消加载动作，为true的话则重新加载数据。
				console.log("------ " + param.q + " ------"); //param.q ：combobox框输入的参数，请求方式POST
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

	
		function moveRow(target){
			$.messager.confirm('Confirm','是否移出采购单?',function(r){
				if (r){
					var rowIndex = getRowIndex(target);
					$('#tt').datagrid('endEdit', rowIndex);	
					$('#tt').datagrid('selectRow',rowIndex);
					var row = $("#tt").datagrid('getSelected');  
					if(row.materialRealQuantity == undefined){      
								$.ajax({  
									type: "post",  
									url: '${pageContext.request.contextPath}/purchaseItem.htm/movePurchaseItem',
									//data:JSON.stringify(userList), 
									data:{idc:row.idc},
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
										if(result){
											$('#tt').datagrid('deleteRow', rowIndex);	
										}						
									}, 
									error : function() {
								       	alert("异常！");
								  	}
								});	
					}else{
						alert("已存在入库信息，不能移出。");
					}							
				}
			});
		} 
		
		function deleterow(target){
			return saveOrDelete(target,'${pageContext.request.contextPath}/purchaseItem.htm/getEntity','${pageContext.request.contextPath}/purchaseItem.htm/deleteRow','是否删除？');							
		} 
		
		function saveRow(target){			
			return saveOrDelete(target,'${pageContext.request.contextPath}/purchaseItem.htm/getEntity','${pageContext.request.contextPath}/purchaseItem.htm/saveRow','是否保存？');				
		}
	 
	 	function purchaseItemStockIn(index){			
			return indexSave(index,'${pageContext.request.contextPath}/purchaseItem.htm/getEntity','${pageContext.request.contextPath}/purchaseItem.htm/instock','采购入库');				
		}
	</script>
 	
</body>
</html>


