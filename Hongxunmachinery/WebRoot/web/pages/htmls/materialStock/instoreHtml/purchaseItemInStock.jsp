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
    <title>扫码入库</title> 
   	<link rel="stylesheet" type="text/css" href="../../../../../jquery-easyui-1.7.0/themes/icon.css">
 	<link rel="stylesheet" href="../../../../../jquery-easyui-1.7.0/themes/default/easyui.css"> 
    <script type="text/javascript" src="../../../../../jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../../jquery-easyui-1.7.0/jquery.easyui.min.js"></script> 
   	<script type="text/javascript" src="../../../js/myjs/xlsx.js"></script>
    <script type="text/javascript" src="../../../js/myjs/datagrid-export.js"></script>
 	<script type="text/javascript" src="../../../js/myjs/materialReadOnly.js"></script>
    <script type="text/javascript" src="../../../js/myjs/myjs.js"></script>    
    <script type="text/javascript" src="../../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../../custom/easyui-lang-zh_CN.js"></script>
</head>


<body>
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
 		<div id="tb" style="padding:0 10px;height:30px">	
			<table>
				<tr>
 					<td>部件号:</td>	
 					<td><input class="easyui-combobox" id="num" style="width:150px;height:25px;"></input></td>									
					<td>型号/规格:</td>
					<td><input class="easyui-textbox" id="spec"  style="width:200px;height:25px;"></input></td>					
					<!-- <td>入库数量:</td> -->		
					<!-- <td><input type="text" id="quantity" class="easyui-numberbox" value="" data-options="min:1,prompt:'入库数量'" style="width:90px;height:25px;"></td>	 --> 						 					
<!-- 					<td><a href="javascript:;" class="easyui-linkbutton" style="height:25px;" onclick="find()" >入库</a></td>-->
					<td><a href="javascript:;" class="easyui-linkbutton" style="height:25px;" onclick="find()" >查找</a></td> 
					<!-- <td><input class="easyui-textbox" id="scanQ" data-options="iconCls:'icon-search'" style="width:200px;height:25px;"></input></td> 
		        	<td><a href="javascript:;"  class="easyui-linkbutton" style="height:25px;"  onclick="$('#tt').datagrid('toExcel','入库记录.xls')">导出</a></td> -->
					<!-- <td><input id="sbutton" class="easyui-switchbutton" checked onText="手动" offText="扫描" style="width:100px;height:25px"></td> -->
				</tr>
			</table>					   
		</div>  
		<table id="tt" class="easyui-datagrid" style="width: 100%; height: '100%'" 
			data-options="collapsible:true,				
				url:'${pageContext.request.contextPath}/purchaseItemInStock.htm/getData',
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
					<th data-options="field:'specification',title:'型号/规格',align:'center',width:50"></th>		
					<th data-options="field:'realQuantity',title:'入库数量',align:'center',width:40"></th> 
					<th data-options="field:'unit',title:'单位',align:'center',width:20"></th>   	
					<th data-options="field:'instoreDate',title:'时间日期',width:40,align:'center',sortable: true,formatter:fmaterDate"></th>   					
					<th data-options="field:'lotNum',title:'批号',align:'center',width:30,editor:'text'"></th>		
					<th data-options="field:'remark',title:'备注',align:'center',width:20,editor:'text'"></th>
					<th data-options="field:'action',title:'',width:40,align:'center',formatter:fmaterAction"></th>
					<th data-options="field:'idc',title:'ID',width:20,hidden:'true'"></th>					
					<th data-options="field:'purchaseItemID',title:'purchaseItemID',width:20,hidden:'true'"></th>						
				
				</tr>
			</thead>
	 	</table> 		
	</div>		
    
	<script>
		function find(){		
			var queryParams = $('#tt').datagrid('options').queryParams;
			queryParams.materialNum = $('#num').combobox('getText');
			queryParams.specification = $('#spec').textbox('getText');
			//queryParams.quantity = $('#quantity').textbox('getText');
			$('#tt').datagrid('reload');
		}
		$('#tt').datagrid({	
			 loadFilter: function(data){										
 				for(var i=0; i<data.rows.length;i++){
					if(data.rows[i].hongXunPurchaseItem.hongXunPurchaseDeItem.hongXunMaterialStock != null){						
						data.rows[i].materialNum 				= data.rows[i].hongXunPurchaseItem.hongXunPurchaseDeItem.hongXunMaterialStock.materialNum;
						data.rows[i].specification 				= data.rows[i].hongXunPurchaseItem.hongXunPurchaseDeItem.hongXunMaterialStock.specification;
						//data.rows[i].materialPurchaseQuantity 	= data.rows[i].hongXunPurchaseDeItem.quantity;	
						//data.rows[i].deliveryDate 				= data.rows[i].hongXunPurchaseDeItem.hongXunMaterialStock.deliveryDate;
						data.rows[i].unit 						= data.rows[i].hongXunPurchaseItem.hongXunPurchaseDeItem.hongXunMaterialStock.unit;							
					}					
				} 
				return data;		
			},
			queryParams: {				
				purchaseNumID:window.parent.window.getPurchaseNumID
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
/* 		$(function(){ 
			$('#scanQ').next().hide();
			$('#sbutton').switchbutton({
				//checked: false,
				onChange: function(checked){
					if (checked == true){
						//document.getElementById('authenTypeL').innerHTML = '已启用!';
						
						$('#scanQ').next().hide();
						$('#userId').next().show();
						
						return;
					}
					if (checked == false){
						//document.getElementById('authenTypeL').innerHTML = '未启用!';
						$('#userId').next().hide();
						$('#scanQ').next().show();
						
					}
				}
			});
		}); */


        $(function(){				
       		if(isOnlyRead()==true){
				$('#scanQ').textbox('disable');
				$('#userId').combobox('disable');
			}
		});
	
	
		function isOnlyRead(){
			var str = localStorage.getItem('authority1'); 			
	        str = str.substr(9, 1)
			if(str.charAt(0)==1){	
	        	return true;					        			
	        }else{
	        	return false;
	        }
		}
	
		function inputEnter() {
			var qvalue = $("#userId").textbox("getValue");
			//alert(qvalue);
			console.log("------ " + qvalue + " ------"); 
			if (qvalue.indexOf("shuliang=") > -1) {
				qvalue = qvalue.split("=")[1];
				if ($("#quantityId").numberbox("getValue") != "") {
					//alert(123);
					if (qvalue == 0) {
						qvalue = "";
					} else {
						qvalue = parseInt(qvalue, 10) + parseInt($("#quantityId").numberbox("getValue"), 10);
					} //alert(qvalue)
				}
				$("#quantityId").numberbox("setValue", qvalue);
				$("#userId").textbox("setValue", "");
				return;
			}
			if ($("#quantityId").numberbox("getValue") == "") {
				$.messager.show({
					title : '提示信息',
					msg : '报错，扫描数量为空',
					showType : 'show',
					timeout : 0,
					style : {
						right : '',
						top : document.body.scrollTop + document.documentElement.scrollTop,
						bottom : ''
					}
				});
				return;
			}
			var data = {}
	
			data["name"] = $("#userId").textbox("getValue");
			data["id"] = window.parent.window.getPurchaseNumID();
			if ($("#quantityId").numberbox("getValue") == '') {
				data["quantity"] = 0;
			} else {
				data["quantity"] = $("#quantityId").numberbox("getValue");
			}
			//var data = {name:$('#userId').combobox("getValue"),id:window.parent.window.getPurchaseNumID(),quantity:$("#quantityId").numberbox("getValue")},
	
			//alert(JSON.stringify(data));	
			$.ajax({
				type : "post",
				url : '${pageContext.request.contextPath}/purchaseItemInStock.htm/instoreMaterialNum',
	
				data : data,
				traditional : true,
				dataType : "json",
				beforeSend : function() {
					load();
				},
				complete : function() {
					disLoad();
				},
				success : function(result) {
					//alert(JSON.stringify(result));
					if (result[0].hasOwnProperty('error')) {
						$.messager.show({
							title : '提示信息',
							msg : result[0].error,
							showType : 'show',
							timeout : 0,
							style : {
								right : '',
								top : document.body.scrollTop + document.documentElement.scrollTop,
								bottom : ''
							}
						});
					} else {
						$('#tt').datagrid('insertRow', {
							index : 0,
							row : result[0]
						});
						$("#userId").textbox("setValue", "");
						$("#quantityId").numberbox("setValue", "");
	
					}
				},
				error : function() {
					alert("异常！");
				}
			});	
		}
	
		$("#scanQ").textbox({
		  	inputEvents: $.extend({}, $.fn.textbox.defaults.inputEvents,{
		    	keyup:function(event){
		         	if(event.keyCode == 13){
		         		//alert($("#userId").combobox("getValue"));
		         		var qvalue = $("#scanQ").textbox("getValue");
		         		if(qvalue.indexOf("shuliang=")>-1){		         	
		         			qvalue=qvalue.split("=")[1];
		         			if($("#quantityId").numberbox("getValue")!=""){
		         				//alert(123);
		         				if(qvalue==0){
		         					qvalue="";
		         				}else{
		         			 		qvalue = parseInt(qvalue, 10) + parseInt($("#quantityId").numberbox("getValue"), 10);
		         				}//alert(qvalue)
		         			}
		         			$("#quantityId").numberbox("setValue",qvalue);
		         			$("#scanQ").textbox("setValue","");	
		         			return;
		         		}
		         		if($("#quantityId").numberbox("getValue")==""){
		         			$.messager.show({
								title:'提示信息',
								msg:'报错，扫描数量为空',
								showType:'show',
								timeout:0,
								style:{
									right:'',
									top:document.body.scrollTop+document.documentElement.scrollTop,
									bottom:''
								}
							});
							return;
		         		}
		         		var data={}
		         		
		         		data["name"]=$("#scanQ").textbox("getValue");
		         		data["id"]=window.parent.window.getPurchaseNumID();
		         		if($("#quantityId").numberbox("getValue") == ''){
		         			data["quantity"]=0;
		         		}else{
		         			data["quantity"]=$("#quantityId").numberbox("getValue");
		         		}
		         		//var data = {name:$('#userId').combobox("getValue"),id:window.parent.window.getPurchaseNumID(),quantity:$("#quantityId").numberbox("getValue")},	
		         		//alert(JSON.stringify(data));	
		            	$.ajax({  
							type: "post",  
							url: '${pageContext.request.contextPath}/purchaseItemInStock.htm/instoreMaterialNum',			
							data:data, 
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
								if(result[0].hasOwnProperty('error')){
									$.messager.show({
										title:'提示信息',
										msg:result[0].error,
										showType:'show',
										timeout:0,
										style:{
											right:'',
											top:document.body.scrollTop+document.documentElement.scrollTop,
											bottom:''
										}
									});
								}else{								
									$('#tt').datagrid('insertRow', {
										index: 0,
										row:result[0]
									});	
									$("#scanQ").textbox("setValue","");	
									$("#quantityId").numberbox("setValue","");									
								}							
							}, 
							error : function() {
						       	alert("异常！");
						  	}
						});
		       		}
		       	}
		    })
		});
	
	
		$('#num').combobox({
			prompt : '请输入要查询的部件号', //提示信息
			//required:true, 	//是否必填
			mode : 'remote', //动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
			url : '${pageContext.request.contextPath}/purchaseItemInStock.htm/autotimp', //请求数据路径				
			editable : true, //可编辑
			hasDownArrow : false, //下拉面板不关闭
			valueField : "id", //数组的键索引
			textField : "name", //数组的值索引
			queryParams : {
				purchaseNumID : window.parent.window.getPurchaseNumID()
			},
			icons : [ {
				//iconCls : 'icon-search'
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
			}/* ,
			onSelect : function(record) {
				if ($("#quantityId").numberbox("getValue") == "") {
					$.messager.show({
						title : '提示信息',
						msg : '报错，扫描数量为空',
						showType : 'show',
						timeout : 0,
						style : {
							right : '',
							top : document.body.scrollTop + document.documentElement.scrollTop,
							bottom : ''
						}
					});
					return;
				}
				$.messager.confirm('Confirm', '是否入库?', function(r) {
					if (r) {
						record["id"] = window.parent.window.getPurchaseNumID();
						record["quantity"] = $("#quantityId").numberbox("getValue");
						//alert(JSON.stringify(record));
						$.ajax({
							type : "post",
							url : '${pageContext.request.contextPath}/purchaseItemInStock.htm/instoreMaterialNum',
							data : record,
							traditional : true,
							dataType : "json",
							beforeSend : function() {
								load();
							},
							complete : function() {
								disLoad();
							},
							success : function(result) {
								//alert(JSON.stringify(result));	
								//$('#tt').datagrid('loadData', result);
								if (result[0].hasOwnProperty('error')) {
									$.messager.show({
										title : '提示信息',
										msg : result[0].error,
										showType : 'show',
										timeout : 0,
										style : {
											right : '',
											top : document.body.scrollTop + document.documentElement.scrollTop,
											bottom : ''
										}
									});
								} else {
									$('#tt').datagrid('insertRow', {
										index : 0,
										row : result[0]
									});
									$("#userId").textbox("setValue", "");
									$("#quantityId").numberbox("setValue", "");			
								}
							},
							error : function() {
								alert("异常！");
							}
						});
					}
				});	
			}
			inputEvents : $.extend({}, $.fn.combobox.defaults.inputEvents, {
				keyup : function(event) {
					if (event.keyCode == 13) {
						var value = $('#userId').combobox('getValue'); 
						//alert(value);
						inputEnter();
						$('#userId').combobox("hidePanel");
						$('#userId').combobox('setValue', '');
					}
				}
			}) */
		});

		function deleterow(target){
			return saveOrDelete(target,'${pageContext.request.contextPath}/purchaseItemInStock.htm/getEntity','${pageContext.request.contextPath}/purchaseItemInStock.htm/deleteRow','是否删除？');							
		} 
		
		function saveRow(target){			
			return saveOrDelete(target,'${pageContext.request.contextPath}/purchaseItemInStock.htm/getEntity','${pageContext.request.contextPath}/purchaseItemInStock.htm/updateRow','是否保存？');				
		}

		
	</script>
</body>
</html>


