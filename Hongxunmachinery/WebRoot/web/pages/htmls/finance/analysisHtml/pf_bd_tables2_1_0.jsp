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
    <link href="../../../css/base.css" rel="stylesheet">
    <link href="../../../css/providers1.css" rel="stylesheet">
    <link rel="stylesheet" href="../../../../custom/uimaker/easyui.css"> 
 	<link rel="stylesheet" type="text/css" href="../../../../custom/uimaker/icon.css">
<!--     <link href="../basic_info.css" rel="stylesheet"> -->
 	<script type="text/javascript" src="../../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../../js/myjs/xlsx.js"></script>
    <script type="text/javascript" src="../../../js/myjs/datagrid-export.js"></script> 
    <script type="text/javascript" src="../../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../../custom/easyui-lang-zh_CN.js"></script>
</head>

<body>  
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
 		<div id="tb" style="padding:0 10px;height:auto">	
			<div id="ttdiv" style="width:100%; height:50px;">	
				<div style="margin-top:10px; margin-left:10px; float:left">			 	  
					<input id="textbox5" class="easyui-datebox" validtype="date" style="height:30px;width:150px;line-height:30px;">			
				</div>
				<div style="margin-top:10px; margin-left:0px; float:left">			 	  
					<img style="height:30px;line-height:30px;" class="img " src="../../../images/main/arrow-right.png">				
				</div>
				<div style="margin-top:10px; margin-left:10px; float:left">		
					<input id="textbox6" class="easyui-datebox" validtype="date" style="height:30px;width:150px;line-height:30px;">   		
				</div>
				<div style="margin-top:10px; margin-left:20px; float:left">		
					<input class="easyui-combobox" id="userId" style="width:200px; height:30px;"></input>
				</div>	
			</div>			
		</div>
		 
		<table id="tt"></table>		
		<!-- <a href="javascript:void(0);" onclick="CreateFormPage('打印测试', $('#tt'));">打印</a>	 -->
	</div>		
	<div id="dialog"  class="easyui-dialog" style="width:90%;height:90%;padding:0px" data-options="
			closed:true,
			resizable:true,	
			modal:true,		
			onResize:function(){
				$(this).dialog('center');
			}">
<!-- 		<div class="easyui-layout"  data-options="fit:true">	
		    <div data-options="region:'center'" style="padding:5px;background:#eee;">
				<div id="tt1tb" style="padding:0 10px;height:auto">		 
			        <div id="tt1div" style="width:100%;  height:40px;">
			    		<div style="margin-top:5px; margin-left:10px; float:left">	
							<input class="easyui-combobox" id="purItemID" style="width:200px; height:30px;"></input>
							<a href="javascript:;" class="easyui-linkbutton"  onclick="excelImportDlg()">导入</a> 						 	  
						    <a href="javascript:;"  class="easyui-linkbutton" onclick="$('#tt1').datagrid('toExcel','采购单.xls')">导出</a>			        						 	  
						  	<a href="javascript:;"  class="easyui-linkbutton" onclick="$('#tt1').datagrid('print','采购单')">打印</a>			
						</div>
			        </div>		   
				</div>	
				<table id="tt1"></table>			
			</div>
		</div>	 -->
	</div> 
<!-- 	<div id="importExcelDlg" class="easyui-dialog" style="width:280px;height:150px;padding:10px 0px" data-options="top:10,closed:true">
		<form action='' enctype="multipart/form-data" method="post" name="fileForm"> 
		  	<label >
				<input type="file" name="xlfile" id="xlf" />		  
			</label> 			
		</form> 	
	</div>  -->   	
	<script>
	
		$(function(){
			$('#tt').datagrid({
				width:'100%',
				height:'auto',
				toolbar:'#tb',
				singleSelect:true,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				pagination:true,
				rownumbers:true,
				showFooter: true,
				columns:[[
 					//{field:'ck', checkbox:true},	
 					{field:'action1',title:'OP',width:40,align:'center',
						formatter:function(value,row,index){
							if (row.editing){	
								return '';
							}else{
								var a = '<a href="#" onclick="spreadrow(this)" style = "color: #1da02b;text-decoration:none"><i class="iconfont">&#xe60a;</i></a> ';	
								/* var b = '<a href="#" onclick="insert(this)" style = "color: #1da02b;text-decoration:none"><i class="iconfont">&#xe663;</i></a>';	 */
								return a ;
							}	
						}
					},				
  					{field:'purchaseNum',title:'采购单号',width:100,align:'center'},
					{field:'productionDes',title:'采购项目',width:150,align:'center',editor:'text'},
				//	{field:'purchaseQuantity',title:'采购数目',width:100,align:'center',editor:'text'},
				//	{field:'purchaseProcedure',title:'属性',width:100,align:'center'},
					{field:'purchaseDate',title:'采购日期',width:130,align:'center',sortable: true},
				//	{field:'purchaseDeliveryDate',title:'交货预期',width:100,align:'center',sortable: true,editor: {type:'datebox',options:{validType:'validDate[\'yyyy-MM-dd\']' }} },
					{field:'purchasingAgent',title:'采购员',width:100,align:'center',editor:'text'},
					{field:'purchaseOrderAmount',title:'采购金额',width:80,align:'center',
						formatter:function(value,row,index){ 
							if(value == undefined){
								return '';
							}else{	
			    	 			return  '<span style="color:red">'+value+'</span>';
			    	 		}
			      		}  
					}, 
					{field:'purchaseSupplier',title:'供应商',width:80,align:'center',editor:'text',hidden:'true'}, 
					//{field:'purchaseStatus',title:'采购状态',width:100,align:'center'},
 					{field:'purchaseStatus',title:'采购状态',width:50,align:'center',
						formatter:function(value,row,index){ 	
			    	 		if(value == '入库完成'){
								return  '<span style="color:green">'+value+'</span>';
							}else if(value == '部分入库'){
								return  '<span style="color:red">'+value+'</span>';								
							}else{	
			    	 			return  '<span style="color:orange">'+value+'</span>';
			    	 		}
			      		}  
					}, 
					{field:'purchaseRemark',title:'备注',width:100,align:'center',editor:'text'},
					/* {field:'action',title:'Action',width:60,align:'center',
						formatter:function(value,row,index){
							if(isOnlyRead()==false){
								if (row.editing){							
									var s = '<a href="#" onclick="saverow(this)">保存</a> ';
									var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
									return s+c;
								} else {					
									var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
									//var d = '<a href="#" onclick="deleterow(this)">删除</a>';
									return e;
								}
							}else{
								return '';
							}
						}
					},		 */
					{field:'idc',title:'ID', width:10,hidden:'true'}
				]],
				rowStyler: function (index, row) {
					if(index > 0){
		                //return 'background-color:LightCyan;';
		            }
		        },
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
			/* $('#tt1').datagrid({
				width:'100%',
				height:'auto',
				toolbar:'#tt1tb',
				singleSelect:true,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				pagination:true,
				rownumbers:true,
				columns:[[
 					{field:'ck', checkbox:true},			
					{field:'materialNum',title:'物料编码',align:'center',width:40,editor:'text'},
					//{field:'materialName',title:'物料名称',align:'center',width:40,editor:'text', hidden:'true'},									
					{field:'specification',title:'型号/规格',align:'center',width:100,editor:'text'},		
					{field:'itemQuantity',title:'项目数',align:'center',width:20},
					{field:'storeQuantity',title:'库存量',align:'center',width:30},
					{field:'inRoadQuantity',title:'在途量',align:'center',width:20},
					//{field:'inroadQuantity',title:'在途数量',align:'center',width:20},
					{field:'materialPurchaseQuantity',title:'采购量',align:'center',width:30,editor:{type:'numberbox',options:{required:true}},
						formatter:function(value,row,index){ 
							if(value != undefined){			    	 	
								return  '<span style="color:orange">'+value+'</span>';	
							}								
			      		} 
					},
					{field:'materialLotNum',title:'批号',align:'center',width:30},  					
					{field:'materialRealQuantity',title:'到货数量',align:'center',width:20,
						formatter:function(value,row,index){ 
							if(value != undefined){		
				    	 		if(row.materialPurchaseQuantity <= row.materialRealQuantity){
									return  '<span style="color:green">'+value+'</span>';			
								}else{									
				    	 			return  '<span style="color:red">'+value+'</span>';	
				    	 		}
				    	 	}	
			      		}  
					},	
					//{field:'materialModel',title:'库位代码',align:'center',width:20},  
					{field:'unit',title:'单位',align:'center',width:20,editor:'text'},  
					{field:'price',title:'单价',align:'center',width:20,editor:{type:'numberbox',options:{precision:2,required:false}}},  
					{field:'materialMoney',title:'金额',align:'center',width:20},  
					//{field:'supplier',title:'供应商',align:'center',width:60,editor:'text'},	
					{field:'materialRemark',title:'备注',align:'center',width:20,editor:'text'},
					{field:'action',title:'Action',width:30,align:'center',
						formatter:function(value,row,index){
							if (row.editing){							
								var s = '<a href="#" onclick="saveRow1(this)">保存</a> ';
								var c = '<a href="#" onclick="cancelrow1(this)">取消</a>';
								return s+c;
							} else {					
								var e = '<a href="#" onclick="editrow1(this)">编辑</a> ';								
								var d = '<a href="#" onclick="deleterow1(this)">删除</a>';
								if(getDlgPurchaseProcedure('#dialog') == undefined){
									return e+d;
								}else{
									return e;
								}
							}
						}
					},
					{field:'idc',title:'ID',width:20,hidden:'true'},
					{field:'bortherID',title:'bortherID',width:20,hidden:'true'},
					{field:'purchaseNumID',title:'purchaseNumID',width:20,hidden:'true'}
					
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
			
			$('#tt2').datagrid({
				width:'100%',
				height:'auto',	
				toolbar:'#dlgtb2',
				singleSelect:true,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				pagination:false,
				rownumbers:true,
				collapsible:true,	
				columns:[[
 					{field:'ck', checkbox:true},						
					{field:'purchaseBackName',title:'退货名称',align:'center',width:60,editor:'text'},		
					{field:'purchaseBackDate',title:'退货日期',width:130,align:'center',sortable: true,editor:{type:'datetimebox',options:{required:true}}},		
					{field:'purchaseBackModel',title:'型号',align:'center',width:60,editor:'text',hidden:'true'},  
					{field:'purchaseBackSpecification',title:'型号/规格',align:'center',width:60,editor:'text'},		
					{field:'purchaseBackLotNum',title:'批号',align:'center',width:60,editor:'text'},  
					{field:'purchaseBackQuantity',title:'数量',align:'center',width:60,editor:'numberbox'},		
					{field:'purchaseBackUnit',title:'单位',align:'center',width:60,editor:'text'},  
					{field:'purchaseBacksupplier',title:'供应商',align:'center',width:60,editor:'text'},	
					{field:'purchaseBackRemark',title:'退货说明',align:'center',width:80,editor:'text'},
					{field:'purchaseNum',title:'采购单号',width:20, hidden:'true'},	
					{field:'ID',title:'ID',width:20,hidden:'true'}
				]] 
			});	 */
			
			$("#dialog").dialog({
		        onClose: function () {
		            //alert(result);	
		            var userList = new Array(); 
		            var purchaseNumID = getPurchaseNumID('#dialog'); 
        			userList.push({purchaseNumID:purchaseNumID});			     	
		        	$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/purchaseItem.htm/saveMoney',
						data: JSON.stringify(userList), 
						//traditional: true,
						dataType:"json", 
						contentType : 'application/json;charset=utf-8', //设置请求头信息  
						beforeSend: function () {
							load();
						},
						complete: function () {
							disLoad();
						},
						success: function(result) {	
							//alert(JSON.stringify(result));	
							
								$('#tt').datagrid('updateRow',{
									index: getDlgParaRowIndex('#dialog'),
									row: {
										purchaseOrderAmount: result[0].purchaseOrderAmount,
										purchaseStatus:	result[0].state
									}
								});		
											
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});	     	
		        }
		    });			
/* 		    $("#dialog").dialog({
			    modal:true,
			    content:"<iframe name='iframe0' scrolling='auto' frameborder='0' src='pf_bd_tables2_3.html' style='width:100%; height:100%; display:block;'></iframe>"
			});
    
			$("#dialog1").dialog({
			     modal:true,
			     content:"<iframe scrolling='auto' frameborder='0' src='pf_bd_tables2_4.html' style='width:100%; height:100%; display:block;'></iframe>"
			}); */
				
		});
		
	</script>	
	<script>	
		function isOnlyRead(){
			var str = localStorage.getItem('authority1'); 			
	        str = str.substr(12, 1)
			if(str.charAt(0)==1){	
	        	return true;					        			
	        }else{
	        	return false;
	        }    		        	    	         		
		}
		function getRowIndex(target){
			var tr = $(target).closest('tr.datagrid-row');
			return parseInt(tr.attr('datagrid-row-index'));
		}
		function editrow(target){
			$('#tt').datagrid('selectRow',getRowIndex(target));
			$('#tt').datagrid('beginEdit', getRowIndex(target));
		}
		function deleterow(target){
			$.messager.confirm('Confirm','是否删除?',function(r){
				if (r){
				//alert(JSON.stringify($('#tt').datagrid('getData')));
					var rowIndex = getRowIndex(target);	
					$('#tt').datagrid('selectRow',rowIndex);
	       			var row = $('#tt').datagrid('getSelected');
	       			if(row.idc!=0){
	       				var userList = new Array();  
	       				userList.push({idc:row.idc});						
						$.ajax({  
							type: "post",  
							url: '${pageContext.request.contextPath}/purchaseNum.htm/deleteRow',
							data:JSON.stringify(userList), 
							//traditional: true,
							dataType:"json", 
							contentType : 'application/json;charset=utf-8', //设置请求头信息  
							beforeSend: function () {
								load();
							},
							complete: function () {
								disLoad();
							},
							success: function(result) {		
								if(result){
									$('#tt').datagrid('deleteRow', rowIndex);	
								}else{
									alert('存在子项，请先删除子项。');
								}							
							}, 
							error : function() {
						       	alert("异常！");
						  	}
						});						
					}		
					//$('#tt').datagrid('deleteRow', rowIndex);	
								
				}else{
					alert('删除失败，请重新操作。');
				}
			});
		}
		
		function saverow(target){

			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/purchaseNum.htm/getEntity',
				traditional: true,
				dataType:"json", 
				//contentType : 'application/json;charset=utf-8', //设置请求头信息  
				success: function(result) {	
				//alert(JSON.stringify(result));		
					var rowIndex = getRowIndex(target);
					$('#tt').datagrid('endEdit', rowIndex);	
					$('#tt').datagrid('selectRow',rowIndex);
					var row = $("#tt").datagrid('getSelected');  		
					var userList = new Array();   		
					for(var key in row){
						if(!result[0].hasOwnProperty(key)){	
							delete row[key];
						}else{
							if(row[key] == undefined){
								delete row[key];
							}
						}				
					}
					userList.push(row);
					//alert(JSON.stringify(userList));
					$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/purchaseNum.htm/saveRow',
						data:JSON.stringify(userList), 
						//traditional: true,
						dataType:"json", 
						contentType : 'application/json;charset=utf-8', //设置请求头信息  
						beforeSend: function () {
							load();
						},
						complete: function () {
							disLoad();
						},
						success: function(result) {		
																	
						}, 
						error : function() {
							alert("异常！");
						}
					});							
				}
			});						
		}
		
		function cancelrow(target){
			$('#tt').datagrid('selectRow',getRowIndex(target));
			$('#tt').datagrid('cancelEdit', getRowIndex(target));
		}			
		
		function insert(target){
			//$('#tt').datagrid('loadData',$('#tt').datagrid('getData')); 
			//$('#tt').datagrid('fixDetailRowHeight',index);
			var index;
			if(target !=null){
				index = getRowIndex(target);
			}else{
	 			var row = $('#tt').datagrid('getSelected');
				if (row){
					index = $('#tt').datagrid('getRowIndex', row);
				} else {
					index = 0;
				}
			}

			$('#tt').datagrid('insertRow', {
				index: index,
				row:{
					//idc:0
					//purchaseStatus:'已下单'				
				}
			});
			$('#tt').datagrid('selectRow',index);	
			$('#tt').datagrid('beginEdit',index); 	
		}
 
        $(function(){				
           // $('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/purchaseNum.htm/loadData')}).datagrid('clientPaging');       
            $.ajax({ 
				type: "post",  			
				url:'${pageContext.request.contextPath}/purchaseNum.htm/loadData',
				traditional: true,
				dataType:"json",  
				beforeSend: function () {
					load();
				},
				complete: function () {
					disLoad();
				},
				success: function(result) {
 					$('#tt').datagrid({data:result}).datagrid('clientPaging');       
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	 
			//$('#tt1').datagrid('clientPaging');             
        });  
            
        function spreadrow(target){
        	var rowIndex = getRowIndex(target);
        	$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');
	        $("#dialog").dialog({
			     title: '采购单号 :' + row.purchaseNum,
			     queryParams: {purchaseNumID: row.idc,index:rowIndex,purchaseProcedure:row.purchaseProcedure},		
			     content:"<iframe scrolling='auto' frameborder='0' src='./pf_bd_tables2_1_0_0.jsp' style='width:100%; height:100%; display:block;'></iframe>"
			});
		
			$("#dialog").dialog("open"); // 打开dialog
			
/*         	var rowIndex = getRowIndex(target);
        	$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');
	       	$('#dialog').dialog({title: '采购单号 :' + row.purchaseOrderNum, queryParams: { purchaseNumID: row.idc,index:rowIndex,purchaseProcedure:row.purchaseProcedure} }); 	
			$("#dialog").dialog("open");
        	$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/purchaseItem.htm/getPurchaseItems',
				data: {purchaseNumID:row.idc},
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
					//if(result.length != 0){	
					$('#tt1').datagrid('loadData', result);								
				}, 
				error : function() {
			       	alert("异常！");
			  	}
			});	   */   	
        }
     	
       	$('#userId').combobox({
		        prompt:'请输入要查询的采购单', 	//提示信息
				//required:true, 	//是否必填
				mode:'remote', 		//动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
				url:'${pageContext.request.contextPath}/purchaseNum.htm/autotimp', 	//请求数据路径
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
		        },
		        onSelect: function(record){
		        	//alert(JSON.stringify(record));
		        	$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/purchaseNum.htm/searchPurchaseNum',
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
         
        
        $('#purItemID').combobox({
		        prompt:'请输入要新增的部件号', 	//提示信息
				//required:true, 	//是否必填
				mode:'remote', 		//动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
				url:'${pageContext.request.contextPath}/bomTree.htm/autotimp', 	//请求数据路径
				editable:true, 		//可编辑
				hasDownArrow:false, 	//下拉面板不关闭
				valueField: "id", 	//数组的键索引
				textField: "name", 	//数组的值索引
				icons:[{
					iconCls:'icon-add',
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
						url: '${pageContext.request.contextPath}/bomTree.htm/onselect',
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
							alert(JSON.stringify(result));	
							//$('#tt').datagrid('loadData', result);
							result[0]['purchaseNumID']=getPurchaseNumID('#dialog');	
							$('#tt1').datagrid('insertRow', {
								index: 0,
								row:result[0],
								//purchaseNumID:getPurchaseNumID('#dialog')
							});	
							$('#tt1').datagrid('beginEdit',0);								
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});
		        } 
		});	
         
       	$('#textbox6').datebox({
			onSelect: function(date){
				//alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
				$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/purchaseNum.htm/betweenDateFind',
					data:{
						beginDate:$("#textbox5").textbox('getValue'),
						endDate:$("#textbox6").textbox('getValue')
					}, 
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


	</script>
	<script>
/*	  	function excelImportDlg(){
			$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格');	
		}
 		function getRowIndex1(target){
			var tr = $(target).closest('tr.datagrid-row');
			return parseInt(tr.attr('datagrid-row-index'));
		}
		function editrow1(target){
			$('#tt1').datagrid('selectRow',getRowIndex(target));
			$('#tt1').datagrid('beginEdit', getRowIndex(target));
		}
		function deleterow1(target){
			$.messager.confirm('Confirm','是否删除?',function(r){
				if (r){
					var rowIndex = getRowIndex(target);
					$('#tt1').datagrid('selectRow',rowIndex);
	       			var row = $('#tt1').datagrid('getSelected');
	       			if(row.idc!=0){
	       				var userList = new Array();  
	       				userList.push(row);
						$.ajax({  
							type: "post",  
							url: '${pageContext.request.contextPath}/purchaseItem.htm/deleteRow',
							data:JSON.stringify(userList), 
							//traditional: true,
							dataType:"json", 
							contentType : 'application/json;charset=utf-8', //设置请求头信息  
							beforeSend: function () {
								load();
							},
							complete: function () {
								disLoad();
							},
							success: function(result) {		
								$('#tt1').datagrid('deleteRow', rowIndex);	  							
							}, 
							error : function() {
						       	alert("异常！");
						  	}
						});
					}				
					
				}else{
					$('#tt1').datagrid('deleteRow', rowIndex);	  
				}
			});
		}

		function saveRow1(target){
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/purchaseItem.htm/getEntity',
				traditional: true,
				dataType:"json", 
				//contentType : 'application/json;charset=utf-8', //设置请求头信息  
				success: function(result) {		
					var rowIndex = getRowIndex(target);
					$('#tt1').datagrid('endEdit', rowIndex);	
					$('#tt1').datagrid('selectRow',rowIndex);
			       	var row = $("#tt1").datagrid('getSelected');   
					var userList = new Array();   
					//delete row["editing"];		
					for(var key in row){
						if(!result[0].hasOwnProperty(key)){	
							delete row[key];
						}else{
							if(row[key] == undefined){
								delete row[key];
							}
						}				
					}
		        	userList.push(row);
					//alert(JSON.stringify(userList));
					$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/purchaseItem.htm/saveRow',
						data: JSON.stringify(userList), 
						//traditional: true,
						dataType:"json", 
						contentType : 'application/json;charset=utf-8', //设置请求头信息  
						beforeSend: function () {
							load();
						},
						complete: function () {
							disLoad();
						},
						success: function(result) {		
							//if(result.length != 0){	
										
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});	     	     	
				}, 
				error : function() {
			       	alert("异常！");
			  	}
			});	     	     
		
	
		}
		
		function cancelrow1(target){
			$('#tt1').datagrid('selectRow',getRowIndex(target));
			$('#tt1').datagrid('cancelEdit', getRowIndex(target));
		}
		
		function dlginsert(){
			var row = $('#tt1').datagrid('getSelected');
			if (row){
				var index = $('#tt1').datagrid('getRowIndex', row);
			} else {
				index = 0;
			}
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/bomTree.htm/getDataMsg',
				data:{materialNum:$("#textbox3").textbox('getValue')}, 
				traditional: true,
				dataType:"json", 
				beforeSend: function () {
					load();
				},
				complete: function () {
					disLoad();
				},
				success: function(result) {	
					$('#tt1').datagrid('insertRow', {
						index: index,
						row:{
							materialName:result[0].materialName,				
							materialSpecification:result[0].materialSpecification,
							//materialModel:result[0].materialModel,
							materialUnit:result[0].materialUnit,
							materialPrice:result[0].materialPrice,
							materialsupplier:result[0].materialsupplier,					
							materialNum:$("#textbox3").textbox('getValue'),
							materialPlanQuantity:$("#textbox4").textbox('getValue'),
							//materialRealQuantity:'0',
							idc:0,
							purchaseNumID:getPurchaseNumID('#dialog')
						}
					});
					$('#tt1').datagrid('selectRow',index);
					$('#tt1').datagrid('beginEdit',index);
				}, 
				error : function() {
			       	alert("异常！");
			  	}
			});		
		} */
		function getPurchaseNumID(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["purchaseNumID"];
		}
		function getDlgParaRowIndex(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["index"];
		}
		function getDlgPurchaseProcedure(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["purchaseProcedure"];
		}
/*
		$(".more").click(function(){
            $(this).closest(".conditions").siblings().toggleClass("hide");
        }); */
	</script>

	<script>
	
	     (function($){
            function pagerFilter(data){
                if ($.isArray(data)){   // is array
                    data = {
                        total: data.length,
                        rows: data
                    }
                }
                var target = this;
                var dg = $(target);
                var state = dg.data('datagrid');
                var opts = dg.datagrid('options');
                if (!state.allRows){
                    state.allRows = (data.rows);
                }
                if (!opts.remoteSort && opts.sortName){
                    var names = opts.sortName.split(',');
                    var orders = opts.sortOrder.split(',');
                    state.allRows.sort(function(r1,r2){
                        var r = 0;
                        for(var i=0; i<names.length; i++){
                            var sn = names[i];
                            var so = orders[i];
                            var col = $(target).datagrid('getColumnOption', sn);
                            var sortFunc = col.sorter || function(a,b){
                                return a==b ? 0 : (a>b?1:-1);
                            };
                            r = sortFunc(r1[sn], r2[sn]) * (so=='asc'?1:-1);
                            if (r != 0){
                                return r;
                            }
                        }
                        return r;
                    });
                }
                var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
                var end = start + parseInt(opts.pageSize);
                data.rows = state.allRows.slice(start, end);
                return data;
            }

            var loadDataMethod = $.fn.datagrid.methods.loadData;
            var deleteRowMethod = $.fn.datagrid.methods.deleteRow;

            $.extend($.fn.datagrid.methods, {
                clientPaging: function(jq){
                    return jq.each(function(){
                        var dg = $(this);
                        var state = dg.data('datagrid');
                        var opts = state.options;
                        opts.loadFilter = pagerFilter;
                        var onBeforeLoad = opts.onBeforeLoad;
                        opts.onBeforeLoad = function(param){
                            state.allRows = null;
                            return onBeforeLoad.call(this, param);
                        }
                        var pager = dg.datagrid('getPager');
                        pager.pagination({
                            onSelectPage:function(pageNum, pageSize){
                                opts.pageNumber = pageNum;
                                opts.pageSize = pageSize;
                                pager.pagination('refresh',{
                                    pageNumber:pageNum,
                                    pageSize:pageSize
                                });
                                dg.datagrid('loadData',state.allRows);
                            },
                            buttons: [{		
									iconCls:'icon-arrow-inout',
									handler:function(){
										var oDiv = document.getElementById("ttdiv");						
										if (oDiv.style.display == "none"){
									      	oDiv.style.display = "block";
									    }else {
									       	oDiv.style.display = "none";
									    } 									    									     
									}
									},'-',{
									iconCls:'icon-arrow-refresh',
									handler:function(){  
										$.ajax({ 
											type: "post",  			
											url:'${pageContext.request.contextPath}/purchaseNum.htm/loadData',
											traditional: true,
											dataType:"json",  
											beforeSend: function () {
												load();
											},
											complete: function () {
												disLoad();
											},
											success: function(result) {
							 					$('#tt').datagrid('loadData',result);       
											},
											error : function() {
										       	alert("出错了！");
										   	}
										});	           
									}
								}]
                        }); 
   
                        
                        $(this).datagrid('loadData', state.data);
                        if (opts.url){
                            $(this).datagrid('reload');
                        }
                    });
                },
                loadData: function(jq, data){
                    jq.each(function(){
                        $(this).data('datagrid').allRows = null;
                    });
                    return loadDataMethod.call($.fn.datagrid.methods, jq, data);
                },
                deleteRow: function(jq, index){
                    return jq.each(function(){
                        var row = $(this).datagrid('getRows')[index];
                        deleteRowMethod.call($.fn.datagrid.methods, $(this), index);
                        /*var state = $(this).data('datagrid');
                        if (state.options.loadFilter == pagerFilter){
                            for(var i=0; i<state.allRows.length; i++){
                                if (state.allRows[i] == row){
                                    state.allRows.splice(i,1);
                                    break;
                                }
                            }
                            $(this).datagrid('loadData', state.allRows);
                        }*/
                    });
                },
                getAllRows: function(jq){
                    return jq.data('datagrid').allRows;
                }
            })
        })(jQuery);
	</script>
	
	<script>
	 $.extend($.fn.datagrid.defaults.editors, {
            datebox: {
                init: function (container, options) {
                    var input = $('<input type="text">').appendTo(container);
                    input.datebox(options);
                    return input;
                },
                destroy: function (target) {
                    $(target).datebox('destroy');
                },
                getValue: function (target) {
                    return $(target).datebox('getValue');//获得旧值
                },
                setValue: function (target, value) {
                    console.info(value);
                    $(target).datebox('setValue', value);//设置新值的日期格式
                },
                resize: function (target, width) {
                    $(target).datebox('resize', width);
                }
            }
        });
		
<%-- 		function getData(tt, url, para){
           	$.ajax({ 
				type: "post",  
				//url:'<%=path%>/screwdata.htm/load',  	
				url:url,
				traditional: true,
				dataType:"json",  
				beforeSend: function () {
					load();
				},
				complete: function () {
					disLoad();
				},
				success: function(result) {
					//$(tt).datagrid('loadData', result);
					if(result.length==0){
						$(tt).datagrid('loadData', [{}])	
						$(tt).datagrid('deleteRow', 0);	 
					}else{
						$(tt).datagrid('loadData', result);
					}
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	
        } --%>
		
	
	</script>
 	
</body>
</html>


