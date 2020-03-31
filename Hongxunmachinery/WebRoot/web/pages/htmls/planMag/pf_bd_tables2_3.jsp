<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="../../../custom/uimaker/easyui.css">
	<link rel="stylesheet" type="text/css" href="../../../custom/uimaker/icon.css">    
    <link href="../../css/basic_info.css" rel="stylesheet">
 	<script type="text/javascript" src="../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../js/myjs/datagrid-export.js"></script>   
    <!-- <script type="text/javascript" src="../../js/myjs/merge.js"></script> --> <!-- 合并单元格  -->
    <script type="text/javascript" src="../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../js/myjs/checks.js"></script>  
    <script type="text/javascript" src="../../../custom/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<div class="easyui-layout"  data-options="fit:true">	    
		<div id="center" data-options="region:'center', border:true, split:true" style="padding:5px;background:#eee;">
			<div id="tb" style="padding:0 10px; height:auto">
<!-- 			<div id="ttdiv" style="width:100%; display:none">   	
					<input id="textbox5" class="easyui-datebox" validtype="date" style="width:130px;height:25px;line-height:25px;">
					<span class="con-span">到 </span><input id="textbox6" class="easyui-datebox" validtype="date" style="width:130px;height:25px;line-height:25px;">   		
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:false, plain:true" style="height:25px" onclick="betweendatefind()">查询</a>
					<input id="ss"></input>
				</div> -->
				<div id="ttdiv" style="width:100%; height:50px;">	
					<div style="margin-top:10px; margin-left:10px; float:left">			 	  
						<input id="textbox5" class="easyui-datebox" validtype="date" style="height:30px;line-height:30px;">			
					</div>
					<div style="margin-top:10px; margin-left:0px; float:left">			 	  
						<img style="height:30px;line-height:30px;" class="img " src="../../images/main/arrow-right.png">				
					</div>
					<div style="margin-top:10px; margin-left:10px; float:left">		
						<input id="textbox6" class="easyui-datebox" validtype="date" style="height:30px;line-height:30px;">   		
					</div>
					<div style="margin-top:10px; margin-left:20px; float:left">		
						<input class="easyui-combobox" id="userId" style="width:200px; height:30px;"></input>
					</div>	
				</div>	
			</div>
			<table id="tt"></table>
		</div>
	</div>	
	

<!-- max-width:800px; -->
 	<div id="dlg1" class="easyui-dialog" style="width:90%;height:90%;padding:0px" data-options="
			closed:true,
			resizable:true,
			onResize:function(){
				$(this).dialog('center');
			}">
		<div class="easyui-layout"  data-options="fit:true">	    
			<div id="center" data-options="region:'center', border:true, split:true" style="padding:5px;background:#eee;">	
				<div id="tt1tb" style="padding:0 10px;height:auto"> 
			        <div id="tt1div" style="width:100%; height:40px;">
			        	<div style="margin-top:5px; margin-left:20px; float:left">		
							<input class="easyui-combobox" id="workItemID" style="width:200px; height:30px;"></input>
						</div>
						<div style="margin-top:5px; margin-left:10px; float:left">			 	  
				        	<a href="javascript:;"  class="easyui-linkbutton" style="height:30px;" onclick="$('#tt1').datagrid('toExcel','dg.xls')">导出</a>			        	
						</div>
						<div style="margin-top:5px; margin-left:10px; float:left">			 	  
				        	<a href="javascript:;"  class="easyui-linkbutton" style="height:30px;" onclick="$('#tt1').datagrid('print','工单号: '+ getDlgWorkNum('#dlg1') + '  加工工序:' + getDlgWorkProcedure('#dlg1'))">打印</a>			
						</div>
<!-- 					<div style="margin-top:5px; margin-left:10px; float:left">			 	  
				        	<a href="javascript:;"  class="easyui-linkbutton" style="height:30px;" onclick="spreadMerterialSheet()">查看领料单</a>			
						</div> -->					
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
<!-- 	<div id="dlg2" class="easyui-dialog" style="width:40%;height:40%;padding:0px" data-options="
			closed:true,
			iconCls:'',
			resizable:true,
			onResize:function(){
				$(this).dialog('center');
			}">

	</div> -->
	<div id="menu" class="easyui-menu" style="width: 50px; display: none;">
	    <!--放置一个隐藏的菜单Div-->
	    <div data-options="iconCls:'icon-save'" onclick="newPurchaseItemLL()">生成领料单</div>
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
				remoteSort:false,
				multiSort:true,
				pagination:true,
				rownumbers:true,	
				idField:'name',
				columns:[[
 					{field:'ck', checkbox:true},	
 					{field:'action1',title:'OP',width:80,align:'center',
						formatter:function(value,row,index){
							if (row.editing){	
								return '';
							}else{
								var a = '<a href="#" onclick="spreadrow(this)" style = "color: #1da02b;text-decoration:none"><i class="iconfont">&#xe60a;</i></a> ';	
								var b = '<a href="#" onclick="insert(this)" style = "color: #1da02b;text-decoration:none"><i class="iconfont">&#xe663;</i></a>';	
								return a+b;
							}		
						}
					},
					{field:'workNum',title:'工单编号',width:100,align:'center',editor:'text'},	
					{field:'orderNum',title:'订单号',width:100,align:'center',editor:'text',hidden:'true'},
					{field:'componentNum',title:'部件编码',width:150,align:'center',editor:'text',hidden:'true'/*editor:{type:'textbox',options:{required:true}}*/},
					//{field:'componentName',title:'itemsID',width:100,align:'center',editor:'text'},				
					{field:'workProcedure',title:'加工工序',width:100,align:'center',editor:{type:'textbox',options:{required:true}}},		
					{field:'workQuantity',title:'加工数量',width:100,align:'center'},	
					{field:'workStartDate',title:'开始日期',width:130,align:'center',sortable: true,editor:{type:'datetimebox',options:{required:true}}},
					{field:'workEndDate',title:'结束日期',width:130,align:'center',sortable: true,editor: {type:'datetimebox',options:{required:true}}},												
					{field:'state',title:'状态',width:80,align:'center',
						formatter:function(value,row,index){ 
							if(value == undefined){
								return '';
							}else{	
			    	 			return  '<span style="color:orange">'+value+'</span>';
			    	 		}
			      		} 			
					}, 
					{field:'remark',title:'备注',width:80,align:'center',editor:'text',
						formatter:function(value,row,index){ 
							if(value != undefined && value.indexOf('异常')>-1){	
			    	 			return  '<span style="background-color:red;">'+value+'</span>';
			    	 		}
			      		} 					
					},
					/*{field:'swh',title:'开关',width:20,align:'center',
						formatter:function(value,row,index){
							var a;
							if (value==0){	
								a = '<a href="#" onclick="open(this)" style = "color: #1da02b;text-decoration:none"><i class="iconfont">&#xe62c;</i></a>';								
							}else{
								a = '<a href="#" onclick="close(this)" style = "color: #1da02b;text-decoration:none"><i class="iconfont">&#xe697;</i></a> ';		
								return a;
							}	
						}
					},*/
					{field:'action',title:'操作',width:80,align:'center',
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
			
/*  if (val < 20){
        return '<span style="color:red;">('+val+')</span>';
    } else {
        return val;
    } */
    
			$('#tt1').datagrid({
				iconCls:'icon-edit',
				width:'100%',
				height:'auto',
				toolbar:'#tt1tb',
				singleSelect:true,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				pagination:true,
				pageList: [10,20,50,100,200,400],
				rownumbers:true,				
				//url:'${pageContext.request.contextPath}/numThreeBranchOne.htm/loadData',			
				columns:[[
 					{field:'ck', checkbox:true},
 					{field:'orderNum',title:'定单号',align:'center',width:100,editor:'text'},			
					{field:'materialNum',title:'部件号',align:'center',width:100},
					{field:'lineNum',title:'行号',align:'center',width:60,editor:'text'},
					//{field:'groupNum',title:'组',align:'center',width:60,editor:'text'},
					{field:'drawNum',title:'图号',align:'center',width:100,editor:'text'},						
					{field:'specification',title:'型号/规格',align:'center',width:120,editor:'text'},	
					{field:'attribute',title:'属性',align:'center',width:50,editor:'text',
						formatter:function(value,row,index){ 
							if(value == undefined){
								return '';
							}else{	
			    	 			return  '<span style="color:red">'+value+'</span>';
			    	 		}
			      		}
					},													
					{field:'quantity',title:'数量',align:'center',width:60,editor:{type:'numberbox',options:{required:true}}},			
					//{field:'unit',title:'单位',align:'center',width:60,editor:'text'},
					
					{field:'workDeliveryDate',title:'生产交期',align:'center',width:80,editor:'text'},		
					{field:'bomMaterialCuting',title:'下料',align:'center',width:50,editor:'text'},																		                                       
					{field:'bomPlateCuting',title:'钣金',align:'center',width:50,editor:'text'},  
					{field:'bomMaterialWeld',title:'焊接',align:'center',width:50,editor:'text'},   
					{field:'bomWeiwai',title:'外协',align:'center',width:50,editor:'text'}, 
					{field:'bomPainting',title:'喷涂',align:'center',width:50,editor:'text'}, 
					{field:'bomAssemble',title:'装配',align:'center',width:50,editor:'text'},   
					//{field:'supplier',title:'供货商',align:'center',width:60,editor:'text'},  			
					{field:'workNumID',title:'workNumID',align:'center',width:60,hidden:'true'},	
					{field:'state',title:'状态',width:80,align:'center',
						formatter:function(value,row,index){ 
							if(value == undefined){
								return '';
							}else{	
			    	 			return  '<span style="color:orange">'+value+'</span>';
			    	 		}
			      		} 			
					}, 
					{field:'remark',title:'备注',align:'center',width:80,editor:'text',
						formatter:function(value,row,index){ 
							if(value != undefined && value.indexOf('删除')>-1){	
			    	 			return  '<span style="color:red">'+value+'</span>';
			    	 		}
			      		} 
					},
 					{field:'action',title:'操作',width:80,align:'center',
						formatter:function(value,row,index){						
								if (row.editing){							
									var s = '<a href="#" onclick="saverow1(this)">保存</a> ';
									var c = '<a href="#" onclick="cancelrow1(this)">取消</a>';
									return s+c;
								} else {					
									var e = '<a href="#" onclick="editrow1(this)">编辑</a> ';
									var d = '<a href="#" onclick="deleterow1(this)">删除</a>';
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
					{field:'Idc',title:'ID',width:20,hidden:'true'}
				]],
				onLoadSuccess: function () {
					//alert(2131);
					//var fields = ['orderNum',  'materialNum'];
         			//merge($("#tt1"), fields);
				},
				onRowContextMenu: function(e, rowIndex, rowData) {
	                e.preventDefault();
	                $(this).datagrid("selectRow", rowIndex);
	                $('#menu').menu('show', {
	                    left: e.pageX,
	                    top: e.pageY
	                });
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
			
			$('#tt2').datagrid({
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
					{field:'materialNum',title:'物料编码',align:'center',width:40,editor:'text'},
					//{field:'materialName',title:'物料名称',align:'center',width:40,editor:'text', hidden:'true'},									
					{field:'specification',title:'型号/规格',align:'center',width:50,editor:'text'},		
					{field:'materialPlanQuantity',title:'需求数量',align:'center',width:20},
					{field:'materialStoreQuantity',title:'库存数量',align:'center',width:20},
					{field:'materialReadyQuantity',title:'库存水位',align:'center',width:20},
					{field:'storeOutQuantity',title:'领料数量',align:'center',width:20,editor:{type:'numberbox',options:{required:true}}},
					{field:'storeOutDate',title:'领料日期',align:'center',width:80,editor:'text'},	
					{field:'storeOutWorker',title:'领料员',align:'center',width:80,editor:'text'},	
					//{field:'materialLotNum',title:'批号',align:'center',width:30},  					
					//{field:'materialRealQuantity',title:'到货数量',align:'center',width:20},	
					//{field:'materialModel',title:'库位代码',align:'center',width:20},  
					//{field:'unit',title:'单位',align:'center',width:20,editor:'text'},  
					//{field:'price',title:'单价',align:'center',width:20,editor:{type:'numberbox',options:{precision:2,required:false}}},  
					//{field:'materialMoney',title:'金额',align:'center',width:20},  
					//{field:'supplier',title:'供应商',align:'center',width:60,editor:'text'},	
					{field:'materialRemark',title:'备注',align:'center',width:20,editor:'text'},	
					{field:'idc',title:'ID',width:20,hidden:'true'},
					//{field:'bortherID',title:'bortherID',width:20,hidden:'true'},
					//{field:'purchaseNumID',title:'purchaseNumID',width:20,hidden:'true'}
					
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
		});
		
	</script>
	
	<script>	
		function updateActions(index){
			$('#tt').datagrid('updateRow',{
				index: index,
				row:{}
			});
		}
		function getRowIndex(target){
			var tr = $(target).closest('tr.datagrid-row');
			return parseInt(tr.attr('datagrid-row-index'));
		}
		function editrow(target){
			$('#tt').datagrid('selectRow', getRowIndex(target));	
			$('#tt').datagrid('beginEdit', getRowIndex(target));
		}
		function deleterow(target){
			$.messager.confirm('Confirm','是否删除?',function(r){
				if (r){
					var rowIndex = getRowIndex(target);	
					$('#tt').datagrid('selectRow',rowIndex);
	       			var row = $('#tt').datagrid('getSelected');
					if(row.idc!=0){
						var data = {idc:row.idc};
						$.ajax({ 
							type: "post",  	
							url:'${pageContext.request.contextPath}/workNum.htm/deleteRow',
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
								if(!result){					
									alert('删除失败');
								}
							},
							error : function() {
						       	alert("出错了！");
						   	}
						});	
					}
					$('#tt').datagrid('deleteRow', rowIndex);	
									
				}else{
					alert('删除失败，请重新操作。');
				}
			});
		}
		
		function saverow(target){
			var rowIndex = getRowIndex(target);
			$('#tt').datagrid('endEdit', rowIndex);				
			$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');	      
			if(IsDateTime(row.workStartDate)){
				if((IsDateTime(row.workEndDate))){					
					//alert(JSON.stringify(row))		
					//var rowClone = row; 
					var userList = new Array();  	
					var workStartDate = row.workStartDate;
					var workEndDate = row.workEndDate;
					//delete rowClone["editing"]; 
					delete row["workStartDate"]; 
	 				delete row["workEndDate"]; 
					for(var key in row){	
				　　　　	if(row[key] == ''){
							delete row[key];
						}				
				　　	}	 				 				
					userList.push(row);
					userList.push({workNum:workStartDate, orderNum:workEndDate});
					//alert(JSON.stringify(userList));
			
					$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/workNum.htm/saveRow',
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
						alert(JSON.stringify(result));
							$('#tt').datagrid('updateRow',{
								index: rowIndex,
								row:result[0]
								
							});						
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});	
				}else{
					alert("请输入正确的开始日期！");
					$('#tt').datagrid('beginEdit', rowIndex);	
				}
			}else{
				alert("请输入正确的结束日期！");
				$('#tt').datagrid('beginEdit', rowIndex);	
			}      				
		}
		
		function cancelrow(target){
			$('#tt').datagrid('selectRow', getRowIndex(target));	
			$('#tt').datagrid('cancelEdit', getRowIndex(target));
		}			
		
		function insert(){

 			var row = $('#tt').datagrid('getSelected');
			if (row){
				var index = $('#tt').datagrid('getRowIndex', row);
			} else {
				index = 0;
			}

			$('#tt').datagrid('insertRow', {
				index: index,
				row:{
					//stockoutNum: $("#textbox1").textbox('getValue'),
					//workNum:	$("#textbox2").textbox('getValue'),
					//idc:0	
					state:'新工单'	
				}
			});
			$('#tt').datagrid('selectRow',index);
			$('#tt').datagrid('beginEdit',index); 		

		}

        $(function(){				
            //$('#tt').datagrid('clientPaging'); 
            $('#tt').datagrid({data:funTwo('#tt','${pageContext.request.contextPath}/workNum.htm/loadData')}).datagrid('clientPaging');   
           /* $('#tt').datagrid({
				rowStyler:function(index,row){
					if (row.remark.indexOf('异常')>-1){
						return 'background-color:red;';
					}
				}
			});*/
            $('#tt1').datagrid('clientPaging'); 
           	$('#ss').searchbox({
            	//height:'40px',
			    searcher:function(value,name){
			    	funTwo('#tt','${pageContext.request.contextPath}/workNum.htm/searchNum' + '?num=' + value);
			    },
			    prompt:'出库单号'
			});      
        });  
        
     
        function spreadrow(target){
        	var rowIndex = getRowIndex(target);
        	$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');
	       	//alert(row.idc + row.workNum);
	       	$('#dlg1').dialog({title: '工单号 :' + row.workNum , queryParams: {dlgPara: row.idc,workNum:row.workNum,workProcedure:row.workProcedure}}); 
			$("#dlg1").dialog("open");
			var userList = new Array();   
			userList.push({idc:row.idc});
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/workItem.htm/getWorkNumItems',
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
					//alert(JSON.stringify(data));										
					$('#tt1').datagrid('loadData', result);					
				}, 
				error : function() {
			       	alert("异常！");
			  	}
			});
			
        }
        
/*         function spreadMerterialSheet(){
	       	$('#dlg2').dialog({title: '工单号 :' +  getDlgWorkNum('#dlg1') + ' 领料单'}); 
			$("#dlg2").dialog("open");
			var userList = new Array();   
			userList.push({purchaseOrderNum:getDlgWorkNum('#dlg1'),purchaseProcedure:getDlgWorkProcedure('#dlg1')});
			$.ajax({  
				type: "post",  
				//url: '${pageContext.request.contextPath}/purchaseItemOutstock.htm/getWorkItemOutStocks',
				url: '${pageContext.request.contextPath}/purchaseItem.htm/getPurchaseSheet',
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
					//alert(JSON.stringify(result));	
											
					$('#tt2').datagrid('loadData', result);	
									
				}, 
				error : function() {
			       	alert("异常！");
			  	}
			});
			
        }  */    	
       	
       	$('#userId').combobox({
		        prompt:'请输入要查询的工单号', 	//提示信息
				//required:true, 	//是否必填
				mode:'remote', 		//动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
				url:'${pageContext.request.contextPath}/workNum.htm/autotimp', 	//请求数据路径
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
						url: '${pageContext.request.contextPath}/workNum.htm/searchWorkNum',
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
       	
       	$('#textbox6').datebox({
			onSelect: function(date){
				alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
				$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/workNum.htm/betweenDateFind',
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

		$('#workItemID').combobox({
		        prompt:'请输入要加工的部件号', 	//提示信息
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
							
							//$('#tt').datagrid('loadData', result);
							result[0]['workNumID']=getDlgParameter('#dlg1');	
							//alert(JSON.stringify(result[0]));	
							$('#tt1').datagrid('insertRow', {
								index: 0,
								row:result[0],
								//workNumID:getDlgParameter('#dialog')
							});	
							$('#tt1').datagrid('beginEdit',0);								
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});
		        } 
		});
			
		$('#workItemLLID').combobox({
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
							result[0]['workNumID']=getDlgParameter('#dlg1');	
							$('#tt2').datagrid('insertRow', {
								index: 0,
								row:result[0],
								//workNumID:getDlgParameter('#dialog')
							});	
							$('#tt2').datagrid('beginEdit',0);								
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});
		        } 
		});							
		
		function getIndex(){
			var row = $('#tt').datagrid('getSelected');		
			var index = $('#tt').datagrid('getRowIndex', row);	
			//alert("index:" + index);	
		}
	</script>
	
	<script>
		function updateActions1(index){
			$('#tt1').datagrid('updateRow',{
				index: index,
				row:{}
			});
		}
		function getRowIndex1(target){
			var tr = $(target).closest('tr.datagrid-row');
			return parseInt(tr.attr('datagrid-row-index'));
		}
		function editrow1(target){
			$('#tt1').datagrid('selectRow', getRowIndex(target));	
			$('#tt1').datagrid('beginEdit', getRowIndex(target));
		}
		function deleterow1(target){
			$.messager.confirm('Confirm','是否删除?',function(r){
				if (r){
					var rowIndex = getRowIndex(target);
					//var para = $('#dlg1').panel('options').title;  
					//para = para.split(':')[1];
					//alert(para) 
					$('#tt1').datagrid('selectRow',rowIndex);
	       			var row = $('#tt1').datagrid('getSelected');
					if(row.Idc!=0){					
						var userList = new Array();   
						userList.push({idc:row.idc});
						$.ajax({  
							type: "post",  
							url: '${pageContext.request.contextPath}/workItem.htm/deleteRow',
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
								//alert("删除成功:" + result);										
							}, 
							error : function() {
						       	alert("异常！");
						  	}
						});				
					}				  
				}else{
					alert("删除失败，请重新操作。")
				}
			});
		}
		function saverow1(target){
			//$('#tt1').datagrid('endEdit', getRowIndex(target));
			var rowIndex = getRowIndex(target);
			$('#tt1').datagrid('endEdit', rowIndex);
			$('#tt1').datagrid('selectRow', rowIndex);
			var row = $('#tt1').datagrid('getSelected');	
			//var para = $('#dlg1').panel('options').title;  
			//para = para.split(':')[1];		       	
	       	var userList = new Array(); 
	       	delete row["editing"];
	       	for(var key in row){	
				if(row[key] == ''){
					delete row[key];
				}				
			}   
			userList.push(row);
			//alert(JSON.stringify(userList));
	       	$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/workItem.htm/saveRow',
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
					//alert(JSON.stringify(result));	
					$('#tt1').datagrid('updateRow',{
						index: rowIndex,
						row: {
							idc: result[0].idc,
						}
					});	
				}, 
				error : function() {
			       	alert("异常！");
			  	}
			});
	       
		}
		function cancelrow1(target){
			$('#tt1').datagrid('selectRow', getRowIndex(target));	
			$('#tt1').datagrid('cancelEdit', getRowIndex(target));
		}
		function dlginsert(){
			var row = $('#tt1').datagrid('getSelected');
			if (row){
				var index = $('#tt1').datagrid('getRowIndex', row);
			} else {
				index = 0;
			}
			var para = $('#dlg1').panel('options').title;  
			var stockoutNum = para.split(':')[1];
		
			//alert(row.materialNum)
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/numThreeBranchOne.htm/getDataTwoMsg',
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
				//alert(JSON.stringify(result));
					$('#tt1').datagrid('insertRow', {
						index: index,
						row:{
							//stockoutNum:stockoutNum,
							materialNum:$("#textbox3").textbox('getValue'),
							quantity:$("#numberbox4").textbox('getValue'),
							materialName:result[0].materialName,
							materialUnit:result[0].materialUnit,
							materialsupplier:result[0].materialsupplier,
							materialModel:result[0].materialModel,
							materialSpecification:result[0].materialSpecification,
							Idc:0,
							parentID:0
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
                        if(columns.indexOf("workNum")>-1){
	                        pager.pagination({
	                            buttons: [{
									iconCls:'icon-add',
									handler:function(){	
										insert();
									}
								},'-',{
									iconCls:'icon-arrow-inout',
									handler:function(){
										var	oDiv = document.getElementById("ttdiv");						
										if (oDiv.style.display == "none"){
									      	oDiv.style.display = "block";
									    }else {
									       	oDiv.style.display = "none";
									    }
									}
									},'-',{
									iconCls:'icon-arrow-refresh',
									handler:function(){	   
										$('#tt').datagrid({data:funTwo('#tt','${pageContext.request.contextPath}/workNum.htm/loadData')}).datagrid('clientPaging');                
									}	
								}]
	                        });
                        }else if(columns.indexOf("orderNum")>-1){
                        	pager.pagination({
	                            buttons: [{
 									iconCls:'icon-arrow-inout',
									handler:function(){		
										var	oDiv = document.getElementById("tt1div");																	
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
										userList.push({idc:getDlgParameter('#dlg1')});
										$.ajax({  
											type: "post",  
											url: '${pageContext.request.contextPath}/workItem.htm/getWorkNumItems',
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
												//alert(JSON.stringify(result));							
												$('#tt1').datagrid('loadData', result);		 						
											}, 
											error : function() {
										       	alert("异常！");
										  	}
										});
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
										var para = $('#dialog').panel('options').title;  
										para = para.split(':')[1];	   
										funTwo('#tt1', '${pageContext.request.contextPath}/numThreeBranchOne.htm/loadData' + '?para=' + para);
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

		function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		}
		function myparser(s){
			if (!s) return new Date();
			var ss = (s.split('-'));
			var y = parseInt(ss[0],10);
			var m = parseInt(ss[1],10);
			var d = parseInt(ss[2],10);
			if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
				return new Date(y,m-1,d);
			} else {
				return new Date();
			}
		}

		function funTwo(tt, url, para){
           	$.ajax({ 
				type: "post",  
				//url:'${pageContext.request.contextPath}/screwdata.htm/load',  	
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
					//alert(JSON.stringify(result));
					$(tt).datagrid('loadData', result);
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	
        }
		
		function newPurchaseItemLL(){
			var rows = $('#tt1').datagrid('getSelections');
			var userList = new Array();
			for(var i=0; i<rows.length; i++){
				userList.push({idc:rows[i].materialNum}); 
			}				  	
			$.ajax({ 
				type: "post",  
				url:'${pageContext.request.contextPath}/bomTree.htm/getBomItems',  	
				//traditional: true,
				data: JSON.stringify(userList),
				dataType:"json",  
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
			       	alert("出错了！");
			   	}
			});	
		}
		
		function CheckDateTime(str){
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
			var reg = /^(\d{4})-(\d{2})-(\d{2})$/;
			var str = mystring;
			var arr = reg.exec(str);
			if (str=="") return false;
			//alert(mystring + ":" + RegExp.$2 + ":" + RegExp.$3);
			if (!reg.test(str)&&RegExp.$2<=12&&RegExp.$3<=31){			
				return true;
			}
			return false;
		}
		
		function getDlgParameter(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["dlgPara"];
		}		
		function getDlgWorkNum(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["workNum"];
		}
		function getDlgWorkProcedure(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["workProcedure"];
		}
			
	</script>

</body>
</html>




