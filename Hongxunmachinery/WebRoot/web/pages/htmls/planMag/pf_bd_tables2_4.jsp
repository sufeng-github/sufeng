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
    <link href="../../css/base.css" rel="stylesheet">
    <link href="../../css/providers1.css" rel="stylesheet">
    <link rel="stylesheet" href="../../../custom/uimaker/easyui.css"> 
 	<link rel="stylesheet" type="text/css" href="../../../custom/uimaker/icon.css">
    <link href="basic_info.css" rel="stylesheet">
 	<script type="text/javascript" src="../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../js/myjs/datagrid-export.js"></script>   
    <script type="text/javascript" src="../../../custom/easyui-lang-zh_CN.js"></script>
    
<!--     
	<script type="text/javascript" src="../topmenu/scripts/jquery.tmailsilder.v2.js"></script>	
    <link rel="stylesheet" type="text/css" href="../topmenu/css/jquery.tmailsilder.v2.css" /> 
-->

</head>


<body> 
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
 		<div id="tb" style="padding:0 10px;height:auto">	
<!-- 			<div id="ttdiv" style="width:100%; display:none">			 	  
				<span class="con-span">从: </span><input id="textbox1" class="easyui-datebox" validtype="date" style="width:130px;height:25px;line-height:25px;">			
				<span class="con-span">到: </span><input id="textbox2" class="easyui-datebox" validtype="date" style="width:130px;height:25px;line-height:25px;">   		
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:false,plain:true" style="height:25px;" onclick="betweendatefind()">查询</a>
				<input id="ss"></input>	
			</div> -->
			<div id="ttdiv" style="width:100%; height:50px;">	
				<div style="margin-top:10px; margin-left:10px; float:left">			 	  
					<input id="textbox1" class="easyui-datebox" validtype="date" style="height:30px;line-height:30px;">			
				</div>
				<div style="margin-top:10px; margin-left:0px; float:left">			 	  
					<img style="height:30px;line-height:30px;" class="img " src="../../images/main/arrow-right.png">				
				</div>
				<div style="margin-top:10px; margin-left:10px; float:left">		
					<input id="textbox2" class="easyui-datebox" validtype="date" style="height:30px;line-height:30px;">   		
				</div>
				<div style="margin-top:10px; margin-left:20px; float:left">		
					<input class="easyui-combobox" id="userId" style="width:200px; height:30px;"></input>
				</div>	
			</div>
		</div> 
		<table id="tt"></table> 
	</div>		
	<div id="dlg1"  class="easyui-dialog" style="width:90%;height:90%;padding:0px" data-options="
			closed:true,
			resizable:true,		
			onResize:function(){
				$(this).dialog('center');
			}">
		<div class="easyui-layout"  data-options="fit:true">	
		    <div data-options="region:'center'" style="padding:5px;background:#eee;">
				<div id="tt1tb" style="padding:0 10px;height:auto">		 
			        <div id="tt1div" style="width:100%; height:40px;">
			        	<div style="margin-top:5px; margin-left:20px; float:left">		
							<input class="easyui-combobox" id="weiwaiItemID" style="width:200px; height:30px;"></input>
						</div>
						<div style="margin-top:5px; margin-left:10px; float:left">			 	  
						    <a href="javascript:;"  class="easyui-linkbutton" style="height:30px;" onclick="$('#tt1').datagrid('toExcel','dg.xls')">导出</a>			        	
						</div>
						<div style="margin-top:5px; margin-left:10px; float:left">			 	  
						  	<a href="javascript:;"  class="easyui-linkbutton" style="height:30px;" onclick="$('#tt1').datagrid('print','DataGrid')">打印</a>			
						</div>
						<div style="margin-top:5px; margin-left:10px; float:left">			 	  
				        	<a href="javascript:;"  class="easyui-linkbutton" style="height:30px;" onclick="spreadMerterialSheet()">查看领料单</a>			
						</div>
					</div>
				</div>	
				
				<table id="tt1"></table>				
			</div>
		</div>	
	</div> 
	<div id="dlg2" class="easyui-dialog" style="width:80%;height:80%;padding:0px" data-options="
			closed:true,
			resizable:true,
			onResize:function(){
				$(this).dialog('center');
			}">
		<div class="easyui-layout"  data-options="fit:true">	    
			<div id="center" data-options="region:'center', border:true, split:true" style="padding:5px;background:#eee;">	
<!-- 				<div id="tt1tb" style="padding:0 10px;height:auto"> 
			        <div id="tt1div" style="width:100%; height:40px;">
					</div>
				</div> -->
				<table id="tt2"></table>
			</div>
		</div>
	</div> 
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
 					{field:'ck', checkbox:true},
 					{field:'action1',title:'OP',width:80,align:'center',
						formatter:function(value,row,index){	
							if (row.editing){	
								return '';
							}else{
								var a = '<a href="#" onclick="spreadrow(this)" style = "color: #1da02b;text-decoration:none"><i class="iconfont">&#xe60a;</i></a> ';	
								var b = '<a href="#" onclick="insert(this)" style = "color: #1da02b;text-decoration:none"><i class="iconfont">&#xe663;</i></a>';	
								return a + b;
							}
						}
					},					
					{field:'weiwaiNum',title:'外协单号',width:100,align:'left'},
				 	{field:'workNum',title:'工单号',width:60,align:'left',sortable: true,editor:'textbox',hidden:'true'},	
				 	{field:'weiwaiProcedure',title:'委外工序',width:100,align:'left'},
					{field:'weiwaiDate',title:'委外日期',width:80,align:'left',sortable: true},
					{field:'weiwaiQuantity',title:'委外产品数',width:60,align:'left',sortable: true,editor:'numberbox'},	
					{field:'supplier',title:'委外供应商',width:100,align:'left',editor:'text'},	
					{field:'weiwaiStaff',title:'下单员',width:100,align:'left',editor:'text'},
					{field:'weiwaiDeliveryDate',title:'交付日期',width:80,align:'left',sortable: true, editor:{type:'datebox',options:{required:true}}},
					{field:'weiwaiMoney',title:'委外总金额',width:80,align:'left'}, 								
					{field:'state',title:'委外状态',width:80,align:'center',editor:'text',
						formatter:function(value,row,index){ 
							if(value == undefined){
								return '';
							}else{	
			    	 			return  '<span style="color:orange">'+value+'</span>';
			    	 		}
			      		} 			
					}, 
					{field:'remark',title:'备注',width:80,align:'left',editor:'text'}, 
					{field:'action',title:'操作',width:80,align:'left',
						formatter:function(value,row,index){
							if (row.editing){							
								var s = '<a href="#" onclick="saverow(this)">保存</a> ';
								var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
								return s+c;
							} else {					
								var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
								var d = '<a href="#" onclick="deleterow(this)">删除</a>';
								return e+d;
							}
						}
					},
					{field:'idc',title:'ID',width:10,hidden:'true'}
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
			$('#tt1').datagrid({
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
					{field:'materialName',title:'物料名称',align:'center',width:40,editor:'text', hidden:true},									
					{field:'specification',title:'型号/规格',align:'center',width:120,editor:'text'},		
					{field:'planQuantity',title:'委外数量',align:'center',width:30,editor:'numberbox'},				
					{field:'realQuantity',title:'到货数量',align:'center',width:30,editor:'numberbox'},	
					{field:'realDeliveryDate',title:'到货日期',width:40,align:'center',sortable: true,editor:{type:'datebox'}},
					//{field:'materialModel',title:'库位代码',align:'center',width:20},  
					{field:'unit',title:'单位',align:'center',width:20,editor:'text'},  
					{field:'price',title:'单价',align:'center',width:20,editor:{type:'numberbox',options:{precision:2,required:false}}},  
					{field:'supplier',title:'委外方',align:'center',width:60,editor:'text'},  
					{field:'weiwaiMoney',title:'金额',align:'center',width:20},  
					{field:'remark',title:'备注',align:'center',width:20,editor:'text'},
					{field:'action',title:'Action',width:30,align:'center',
						formatter:function(value,row,index){
							if (row.editing){							
								var s = '<a href="#" onclick="saveRow1(this)">保存</a> ';
								var c = '<a href="#" onclick="cancelrow1(this)">取消</a>';
								return s+c;
							} else {					
								var e = '<a href="#" onclick="editrow1(this)">编辑</a> ';
								var d = '<a href="#" onclick="deleterow1(this)">删除</a>';
								return e+d;
							}
						}
					},
					{field:'idc',title:'ID',width:20,hidden:'true'},
					//{field:'mainID',title:'bortherID',width:20,hidden:'true'}
					{field:'weiwaiNumID',title:'weiwaiNumID',width:20,hidden:'true'}	
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
				iconCls:'icon-edit',
				width:'100%',
				height:'auto',
				toolbar:'#tt2tb',
				singleSelect:true,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				pagination:true,
				rownumbers:true,				
				columns:[[
 					{field:'ck', checkbox:true},
 					//{field:'stockoutNum',title:'领料单号',align:'center',width:60},			
					{field:'materialNum',title:'物料编码',align:'center',width:60},
					{field:'materialName',title:'物料名称',align:'center',width:60},	
					{field:'materialSpecification',title:'型号/规格',align:'center',width:60},		
					{field:'outStoreDate',title:'出库日期',width:100,align:'center',sortable: true},								
					{field:'outStoreQuantity',title:'数量',align:'center',width:60},									
/* 					{field:'length',title:'长',width:60,align:'center',sortable: false,editor:{type:'numberbox',options:{precision:2,required:false}},hidden:'true'},
					{field:'width',title:'宽',width:60,align:'center',sortable: false,editor:{type:'numberbox',options:{precision:2,required:false}},hidden:'true'},	 */
					{field:'materialUnit',title:'单位',align:'center',width:60},  
					//{field:'materialPrice',title:'单价',align:'center',width:60,editor:{type:'numberbox',options:{precision:3,required:false}}},  
					//{field:'materialMoney',title:'金额',align:'center',width:60,editor:{type:'numberbox',options:{precision:3,required:false}}},  
					{field:'materialsupplier',title:'供应商',align:'center',width:60},	
					{field:'remark',title:'备注',align:'center',width:80,editor:'text'},
					{field:'action',title:'Action',width:80,align:'center',
						formatter:function(value,row,index){
							if (row.editing){							
								var s = '<a href="#" onclick="saveRow2(this)">保存</a> ';
								var c = '<a href="#" onclick="cancelRow2(this)">取消</a>';
								return s+c;
							} else {					
								var e = '<a href="#" onclick="editRow2(this)">编辑</a> ';
								var d = '<a href="#" onclick="deleteRow2(this)">返仓</a>';
								return e+d;
							}
						}
					},
					
					/*{field:'action1',title:'Action',width:80,align:'center',
						formatter:function(value,row,index){
							if (row.editing){							
								var c = '';
								return c;
							} else {		
								var a = '<a href="#" onclick="spreadrow1(this)"></a> ';				
								return a;
							}
						}
					},*/			
					{field:'idc',title:'ID',width:20},
					{field:'fatherID',title:'fatherID',width:20}
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
			
			$("#dlg1").dialog({
		        onClose: function () {
		            //$("#projectQuery").css("right", "-1000px"); 
		            var rows = $('#tt1').datagrid('getRows');
		            var result=0;
		            for(var i=0; i<rows.length; i++){
		            	if(isNumber(rows[i].weiwaiMoney)){
		            		result = result + rows[i].weiwaiMoney;
		            	}
		            }
		            //alert(result);	
		            var userList = new Array(); 
		            var weiwaiNumID = getDlgParameter('#dlg1'); 
        			userList.push({weiwaiNumID:weiwaiNumID, weiwaiMoney:result});			     	
	       			//funOne('#tt', '${pageContext.request.contextPath}/weiwaiNum.htm/saveMoney',0,JSON.stringify(userList), 'saveMoney');	            
		        	//$('#tt').datagrid('reload');
		        	$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/weiwaiItem.htm/saveMoney',
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
							//alert(JSON.stringify(result[0]))	
							$('#tt').datagrid('updateRow',{
								index: getDlgParaRowIndex('#dlg1'),
								row: {	
									weiwaiMoney: result[0].weiwaiMoney		
								}
							});									
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});
		        }
		    });			
/* 		    $("#dlg1").dialog({
			    modal:true,
			    content:"<iframe name='iframe0' scrolling='auto' frameborder='0' src='pf_bd_tables2_3.html' style='width:100%; height:100%; display:block;'></iframe>"
			});
    
			$("#dlg11").dialog({
			     modal:true,
			     content:"<iframe scrolling='auto' frameborder='0' src='pf_bd_tables2_4.html' style='width:100%; height:100%; display:block;'></iframe>"
			}); */
			
			$('#weiwaiItemID').combobox({
		        prompt:'请输入要委外的部件号', 	//提示信息
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
							alert(JSON.stringify(result[0]));	
							//$('#tt').datagrid('loadData', result);
							result[0]['weiwaiNumID']=getDlgParameter('#dlg1');	
							$('#tt1').datagrid('insertRow', {
								index: 0,
								row:result[0],
								//weiwaiNumID:getDlgParameter('#dlg1')
							});	
							$('#tt1').datagrid('beginEdit',0);								
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});
		        } 
			});
			
			$('#weiwaiItemLLID').combobox({
		        prompt:'请输入委个领料的部件号', 	//提示信息
				//required:true, 	//是否必填
				mode:'remote', 		//动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
				url:'${pageContext.request.contextPath}/weiwaiItem.htm/autotimp', 	//请求数据路径
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
						url: '${pageContext.request.contextPath}/weiwaiItem.htm/searchWeiwaiItem',
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
							alert(JSON.stringify(result[0]));	
							//$('#tt').datagrid('loadData', result);
							result[0]['weiwaiNumID']=getDlgParameter('#dlg1');	
							$('#tt2').datagrid('insertRow', {
								index: 0,
								row:result[0],
								//weiwaiNumID:getDlgParameter('#dlg1')
							});	
							$('#tt2').datagrid('beginEdit',0);								
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});
		        } 
			});							
		});
		
	</script>	
	<script>	

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
							url: '${pageContext.request.contextPath}/weiwaiNum.htm/deleteRow',
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
								$('#tt').datagrid('deleteRow', rowIndex);		
							}, 
							error : function() {
						       	alert("异常！");
						  	}
						});
						
					}else{
						$('#tt').datagrid('deleteRow', rowIndex);		
					}		
					
					//funOne('#tt','${pageContext.request.contextPath}/weiwaiNum.htm/deleteRow',rowIndex,JSON.stringify(userList),'deleterow');			
				}
			});
		}
		
		function saverow(target){
			var rowIndex = getRowIndex(target);
			$('#tt').datagrid('endEdit', rowIndex);				
			$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');	      
				if(row.weiwaiDeliveryDate =="" || (IsDate(row.weiwaiDeliveryDate))){					
					var userList = new Array();  
					for(var key in row){	
				　　　　	if(row[key] == ''){
							delete row[key];
						}				
				　　	}
					userList.push(row);			
					//alert(JSON.stringify(userList));
					//funOne('#tt','${pageContext.request.contextPath}/weiwaiNum.htm/saveRow', rowIndex, JSON.stringify(userList), 'saverow');	
					$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/weiwaiNum.htm/saveRow',
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
							if(result.length>0){					
								$('#tt').datagrid('updateRow',{
									index: rowIndex,
									row: {
										idc: result[0].idc,
									}
								});		
							}					
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});
				}else{
					alert("请输入正确的交货日期！");
					$('#tt').datagrid('beginEdit', rowIndex);	
				}    				
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
				}
			});
			$('#tt').datagrid('selectRow',index);	
			$('#tt').datagrid('beginEdit',index); 	
		}

        $(function(){				
            $('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/weiwaiNum.htm/loadData')}).datagrid('clientPaging');       
            $('#tt1').datagrid('clientPaging'); 
            $('#tt2').datagrid('clientPaging'); 
           	$('#ss').searchbox({
            	//height:'40px',
			    searcher:function(value,name){
			    	getData('#tt','${pageContext.request.contextPath}/numOne.htm/searchNum' + '?num=' + value);
			    },
			    //menu:'#mm',
			    prompt:'采购单号'
			});              
        });  
            
        function spreadrow(target){
        	var rowIndex = getRowIndex(target);
        	$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');
	       	$('#dlg1').dialog({title: '委外单号 :' + row.weiwaiNum, queryParams: { mainID: row.idc,index:rowIndex, weiwaiNum:row.weiwaiNum} }); 
			//$("#dlg1").dialog("open").dialog('setTitle','采购单号 :' + row.purchaseOrderNum);
			$("#dlg1").dialog("open");
			//getData('#tt1', '${pageContext.request.contextPath}/numTwo.htm/loadData' + '?para=' + row.purchaseOrderNum);
			var userList = new Array();  
			//var id = row.idc;
			userList.push({idc:row.idc});
			//alert(JSON.stringify(userList));
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/weiwaiItem.htm/getWeiwaiChildrens',
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
					$('#tt1').datagrid('loadData', result);							
				}, 
				error : function() {
			       	alert("异常！");
			  	}
			});
        }
     	
     	function spreadMerterialSheet(){
	       	$('#dlg2').dialog({title: '委外单号 :' +  getDlgWeiwaiNum('#dlg1') + ' 领料单'}); 
			$("#dlg2").dialog("open");
			//var userList = new Array();   
			//userList.push({idc:getDlgParameter('#dlg1')});
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/purchaseItemOutstock.htm/getWeiwaiItemOutStocks',
				//data:JSON.stringify(userList), 
				data:{weiwaiNum:getDlgWeiwaiNum('#dlg1')},
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
					//alert(JSON.stringify(result));					
					$('#tt2').datagrid('loadData', result);						
				}, 
				error : function() {
			       	alert("异常！");
			  	}
			});	
        }   
     	
     	$('#userId').combobox({
		        prompt:'请输入要查询的工单号', 	//提示信息
				//required:true, 	//是否必填
				mode:'remote', 		//动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
				url:'${pageContext.request.contextPath}/weiwaiNum.htm/autotimp', 	//请求数据路径
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
		        	alert(JSON.stringify(record));
		        	$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/weiwaiNum.htm/searchWorkNum',
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
     	
     	$('#textbox2').datebox({
			onSelect: function(date){
				alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
				$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/weiwaiNum.htm/betweenDateFind',
					data:{
						beginDate:$("#textbox1").textbox('getValue'),
						endDate:$("#textbox2").textbox('getValue')
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
     	
		/*        	
		function popFindDlg(){
       		$('#dlgFind').dialog('open').dialog('setTitle','查找');
       	} */
       	
/* 		function getIndex(){
			var row = $('#tt').datagrid('getSelected');		
			var index = $('#tt').datagrid('getRowIndex', row);	
			//alert("index:" + index);	
		} */

	</script>
	<script>
		function editrow1(target){
			$('#tt1').datagrid('selectRow',getRowIndex(target));
			$('#tt1').datagrid('beginEdit', getRowIndex(target));
		}
		function deleterow1(target){
			$.messager.confirm('Confirm','Are you sure?',function(r){
				if (r){
					var rowIndex = getRowIndex(target);
					$('#tt1').datagrid('selectRow',rowIndex);
	       			var row = $('#tt1').datagrid('getSelected');
	       			if(row.idc!=0){
	       				var userList = new Array();  
	       				userList.push({idc:row.idc});
						//funOne('#tt1','${pageContext.request.contextPath}/weiwaiItem.htm/deleteRow',rowIndex,JSON.stringify(userList), 'delete1');	
						$.ajax({  
							type: "post",  
							url: '${pageContext.request.contextPath}/weiwaiItem.htm/deleteRow',
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
					}else{			
						$('#tt1').datagrid('deleteRow', rowIndex);	
					}  
				}else{
					alert("删除取消");
				}
			});
		}
		function saveRow1(target){
			var rowIndex = getRowIndex(target);
			$('#tt1').datagrid('endEdit', rowIndex);	
			$('#tt1').datagrid('selectRow',rowIndex);
	       	var row = $("#tt1").datagrid('getSelected');   
			var userList = new Array();   
			for(var key in row){	
				if(row[key] == ''){
					delete row[key];
				}				
			}
        	userList.push(row);

			//alert(JSON.stringify(userList));	     	
	       	//funOne('#tt1', '${pageContext.request.contextPath}/weiwaiItem.htm/saveRow',rowIndex,JSON.stringify(userList), 'saverow1');
	       	$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/weiwaiItem.htm/saveRow',
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
					if(result.length>0){
						$('#tt1').datagrid('updateRow',{
							index: rowIndex,
							row: {
								weiwaiMoney: result[0].weiwaiMoney,
							}
						});	
					}									
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
							weiwaiPrice:result[0].materialPrice,
										
							materialNum:$("#textbox3").textbox('getValue'),
							planQuantity:$("#textbox4").textbox('getValue'),
							//materialRealQuantity:'0',
							idc:0,
							weiwaiNumID:getDlgParameter('#dlg1')
						}
					});
					$('#tt1').datagrid('selectRow',index);
					$('#tt1').datagrid('beginEdit',index);
				}, 
				error : function() {
			       	alert("异常！");
			  	}
			});		
		}
		function getDlgParameter(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["mainID"];
		}
		function getDlgParaRowIndex(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["index"];
		}
		function getDlgWeiwaiNum(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["weiwaiNum"];
		}
	</script>
	
	<script>
		function editRow2(target){
			$('#tt2').datagrid('selectRow',getRowIndex(target));
			$('#tt2').datagrid('beginEdit', getRowIndex(target));
		}
		function deleteRow2(target){
			$.messager.confirm('Confirm','Are you sure?',function(r){
				if (r){
					var rowIndex = getRowIndex(target);
					$('#tt2').datagrid('selectRow',rowIndex);
	       			var row = $('#tt2').datagrid('getSelected');
	       			if(row.idc!=0){
	       				var userList = new Array();         				
	       				userList.push({idc:row.idc,outStoreQuantity:row.outStoreQuantity, fatherID:row.fatherID});
	       				alert(JSON.stringify(userList));
						funOne('#tt2','${pageContext.request.contextPath}/numTwoChildren2.htm/backStoreRow',rowIndex,JSON.stringify(userList), '');	
					}				
					$('#tt2').datagrid('deleteRow', rowIndex);	  
				}else{
					alert("删除失败，请重新操作.");
				}
			});
		}
		function saveRow2(target){
			var rowIndex = getRowIndex(target);
			$('#tt2').datagrid('endEdit', rowIndex);	
			$('#tt2').datagrid('selectRow',rowIndex);
	       	var row = $("#tt2").datagrid('getSelected');   
	       	row = {outStoreQuantity:row.outStoreQuantity, remark:row.remark, fatherID:row.fatherID, idc:row.idc, childUncleID : getDlgParameter('#dlg1')};
	       	for(var key in row){	
				if(row[key] == ''){
					delete row[key];
				}				
			}
			var userList = new Array();  
        	userList.push(row);
			alert(JSON.stringify(userList));	     	
	       	funOne('#tt2', '${pageContext.request.contextPath}/numTwoChildren2.htm/outstore',rowIndex,JSON.stringify(userList), 'outstore');  					   
		}
		function cancelRow2(target){
			$('#tt2').datagrid('selectRow',getRowIndex(target));
			$('#tt2').datagrid('cancelEdit', getRowIndex(target));
		}
		function dlginsert2(){
			var row = $('#tt2').datagrid('getSelected');
			if (row){
				var index = $('#tt2').datagrid('getRowIndex', row);
			} else {
				index = 0;
			}			
			var userList = new Array();   
			userList.push({materialNum:$("#textbox5").textbox('getValue'),materialPlanQuantity:$("#numberbox6").numberbox('getValue'),idc:getDlgParameter('#dlg1')});			
			funOne('#tt2', '${pageContext.request.contextPath}/numTwoFather.htm/preOutstore',index,JSON.stringify(userList), 'dlgInsert');  					   

		} 
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
                            }
                        }); 
                        var columns = dg.datagrid('getColumnFields');								
						if(columns.indexOf("weiwaiNum")>-1){
							pager.pagination({
	                            buttons: [{
									iconCls:'icon-add',
									handler:function(){insert();}
									},'-',{
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
										$('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/weiwaiNum.htm/loadData')}).datagrid('clientPaging');              
									}
								}]
	                        }); 
						}else if(columns.indexOf("price")>-1){
							pager.pagination({
	                            buttons: [{								
									iconCls:'icon-arrow-inout',
									handler:function(){
										var oDiv = document.getElementById("tt1div");									
										if (oDiv.style.display == "none"){
									      	oDiv.style.display = "block";
									    }else {
									       	oDiv.style.display = "none";
									    }
									}
									},'-',{
									iconCls:'icon-arrow-refresh',
									handler:function(){	   
										var userList = new Array();  
										var idc = getDlgParameter('#dlg1');
										userList.push({idc:idc});
										funOne('#tt1', '${pageContext.request.contextPath}/weiwaiItem.htm/getWeiwaiChildrens',0,JSON.stringify(userList),'getChildrens');																				
										//getData('#tt2', '${pageContext.request.contextPath}/numEight.htm/loadData' + '?para=' + para);	
									}
								}]
	                        }); 
						}else{
							pager.pagination({
	                            buttons: [{
									iconCls:'icon-arrow-inout',
									handler:function(){		
										var	oDiv = document.getElementById("tt2div");																	
										if (oDiv.style.display == "none"){
									      	oDiv.style.display = "block";
									    }else {
									       	oDiv.style.display = "none";
									    }
									}
									},'-',{
									iconCls:'icon-arrow-refresh',
									handler:function(){	
										
										var para = $('#dlg1').panel('options').title;  
										para = para.split(':')[1];
									   
										getData('#tt2', '${pageContext.request.contextPath}/numThreeBranchOne.htm/loadData' + '?para=' + para);
										
									}
								}]
	                        });
						}
                        
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
		
		function getData(tt, url, para){
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
        }
		
		function funOne(tt,url,rowIndex,data,para){	 
			$.ajax({  
				type: "post",  
				url: url,
				data:data, 
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
					
						if(para=="saverow" && result.length != 0){
							$(tt).datagrid('updateRow',{
								index: rowIndex,
								row: {
									idc: result[0].idc,
									purchaseStatus: result[0].purchaseStatus, 
									purchaseDate: result[0].purchaseDate
								}
							});	
							//$(tt).datagrid('refreshRow', rowIndex);
								
						}else if(para=="saverow1" && result.length != 0){
							$(tt).datagrid('updateRow',{
								index: rowIndex,
								row: {
									idc: result[0].idc,
									weiwaiMoney:result[0].weiwaiMoney
								}
							});	
						} else if(para=="refresh"){
							var rows = $('#tt').datagrid('getRows');
							for(var i=0; i<rows.length; i++){
								rowIndex = $('#tt').datagrid('getRowIndex',rows[i]);
								$(tt).datagrid('updateRow',{
									index: rowIndex,
									row: result[i]								
								});	
							}
						} else if(para=='getChildrens' || para=='datefind'){
							//$(tt).datagrid({data:result}).datagrid('clientPaging');; 	
											
							$(tt).datagrid('loadData', result);
						}else if(para=="saveMoney"){
							//alert()
							alert(JSON.stringify(result[0]))	
							$('#tt').datagrid('updateRow',{
								index: getDlgParaRowIndex('#dlg1'),
								row: {
									
									weiwaiMoney: result[0].weiwaiMoney
									
								}
							});		
						}else if(para=='dlgInsert'){
							alert('123');
							alert(JSON.stringify(result));
							for(var i=0; i<result.length; i++){
								var index = i;
								$(tt).datagrid('insertRow', {
									index: index,
									row:result[i]
								});
								$(tt).datagrid('selectRow',index);	
								$(tt).datagrid('beginEdit',index); 	
							}			
						} else if(para=='outstore'){
							$(tt).datagrid('updateRow',{
								index: rowIndex,
								row: {
									idc:result[0].idc,
									outStoreDate:result[0].outStoreDate
								}
							});		
						}	 
												
				}, 
				error : function() {
			       	alert("异常！");
			  	}
			});
		}
		
		function IsDateTime(str){
			var reg = /^(\d+)-(\d{1,2})-(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
			var r = str.match(reg);
			if(r==null)return false;
			r[2]=r[2]-1;
			var d= new Date(r[1], r[2],r[3], r[4],r[5], r[6]);
			if(d.getFullYear()!=r[1])return false;
			if(d.getMonth()!=r[2])return false;
			if(d.getDate()!=r[3])return false;
			if(d.getHours()!=r[4])return false;
			if(d.getMinutes()!=r[5])return false;
			if(d.getSeconds()!=r[6])return false;
			return true;
		}
		
		function IsDate(mystring) {
			var bagin_r = mystring.match(/^(\d{4})(-)(\d{2})(-)(\d{2})$/);
			 
			if(bagin_r==null){
			 	alert("请输入正确的开始时间格式,如:2017-01-01");
			 	return false;
			}else{
				return true;
			}
		}
		
		function isNumber(val) {
		    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
		    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
		    if(regPos.test(val) || regNeg.test(val)) {
		        return true;
		    } else {
		        return false;
		    }
		}
				    
 	</script>   
</body>
</html>