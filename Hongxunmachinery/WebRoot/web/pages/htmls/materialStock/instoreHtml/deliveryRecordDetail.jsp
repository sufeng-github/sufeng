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
    <script type="text/javascript" src="../../../js/myjs/materialReadOnly.js"></script> 
    <script type="text/javascript" src="../../../js/myjs/myjs.js"></script>   
    <script type="text/javascript" src="../../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../../custom/easyui-lang-zh_CN.js"></script>
</head>

<body>  
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	

		<table id="tt" class="easyui-datagrid" style="width: 100%; height: 'atuo'" 
			data-options="collapsible:true,				
				url:'${pageContext.request.contextPath}/purchaseDeItem.htm/getData',
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
					<th data-options="field:'materialNum',title:'物料编码',align:'center',width:30,
									formatter:function(value,data){
										if(data.hongXunMaterialStock != null){
											return data.hongXunMaterialStock.materialNum
										}                    
							        } ">
			        </th>									
					<th data-options="field:'specification',title:'型号/规格',align:'center',width:80,
									formatter:function(value,data){
										if(data.hongXunMaterialStock != null){
											return data.hongXunMaterialStock.specification
										}                    
							        } "></th>			
					<th data-options="field:'quantity',title:'库存量',align:'center',width:20,
									formatter:function(value,data){
										if(data.hongXunMaterialStock != null){
											return data.hongXunMaterialStock.quantity
										}                    
							        } "></th>	
					<th data-options="field:'safeQuantity',title:'安全库存',align:'center',width:20,
									formatter:function(value,data){
										if(data.hongXunMaterialStock != null){
											return data.hongXunMaterialStock.safeQuantity
										}                    
							        } "></th>	
					<th data-options="field:'itemQuantity',title:'项目数',align:'center',width:20,
									formatter:function(value,data){
										if(data.hongXunMaterialStock != null){
											return data.hongXunMaterialStock.itemQuantity
										}                    
							        } "></th>	
					<th data-options="field:'inRoadQuantity',title:'在途数',align:'center',width:20,
									formatter:function(value,data){
										if(data.hongXunMaterialStock != null){
											return data.hongXunMaterialStock.inRoadQuantity
										}                    
							        } "></th>	
					<th data-options="field:'quantity',title:'提单量',align:'center',width:20,editor:'numberbox'"></th>			
					<th data-options="field:'date',title:'下单日期',align:'center',width:25"></th>	 
					<th data-options="field:'deliveryDate',title:'交货日期',align:'center',width:25,
									formatter:function(value,data){
										if(data.hongXunMaterialStock != null){
											return data.hongXunMaterialStock.deliveryDate											
										}                    
							        } "></th>	 
					<th data-options="field:'status',title:'状态',align:'center',width:20,formatter:fmaterLcaStatus"></th>
					<th data-options="field:'unit',title:'单位',align:'center',width:20,
									formatter:function(value,data){
										if(data.hongXunMaterialStock != null){
											return data.hongXunMaterialStock.unit
										}                    
							        } "></th>  					
					<!-- <th data-options="field:'remark',title:'备注',align:'center',width:20,editor:'text'"></th>	 --> 
					<th data-options="field:'action',title:'Action',width:20,align:'center',formatter:fmaterAction"></th>	 				
					<th data-options="field:'idc',title:'ID',width:10">ID</th>	
				</tr>
			</thead>
	 	</table> 	
		
	</div>		

	<div id="menu" class="easyui-menu" style="width: 100px; display: none;">
	    <!--放置一个隐藏的菜单Div-->
		<div  onclick="moveToNewPurchaseNum()"><i class="fa fa-file-powerpoint-o "></i>&nbsp; 提交采购单</div>
		<!-- <div data-options="iconCls:'icon-edit'" onclick="moveToOldPurchaseNum()">移动到已有采购单</div> -->
	    <!-- <div data-options="iconCls:'icon-remove'" onclick="deleteSelected()">删除选中行</div>  -->
	</div>
	<script>
		function fmaterLcaStatus(value,row,index){ 
			if(value == '已提单'){
				return  '<span style="color:red">'+value+'</span>';
			}else if(value=='已下单'){	
				return  '<span style="color:green">'+value+'</span>';
			}
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
			onLoadSuccess:function(data){
				alert(data.hongXunMaterialStock.materialNum);
			},
				queryParams: {
					purchaseDeNumId:window.parent.window.getPurchaseDeNumID('#dialog')
			    },		
				onBeforeEdit:function(index,row){
					$(this).datagrid('updateRow', {index:index,row:{editing:true}})
				},
				onAfterEdit:function(index,row){
					$(this).datagrid('updateRow', {index:index,row:{editing:false}})
				},
				onCancelEdit:function(index,row){
					$(this).datagrid('updateRow', {index:index,row:{editing:false}})
				},
				onRowContextMenu: function(e, rowIndex, rowData) { //右键时触发事件
					if(materialOnlyRead()==false){
		                //三个参数：e里面的内容很多，真心不明白，rowIndex就是当前点击时所在行的索引，rowData当前行的数据
		                e.preventDefault(); //阻止浏览器捕获右键事件
		                //$(this).datagrid("clearSelections"); //取消所有选中项
		                $(this).datagrid("selectRow", rowIndex); //根据索引选中该行
		                $('#menu').menu('show', {
							//显示右键菜单
		                    left: e.pageX,//在鼠标点击处显示菜单
		                    top: e.pageY
		                });
	                }
	            },
			});
			
	
		function deleterow(target){
			return saveOrDelete(target,'${pageContext.request.contextPath}/purchaseDeItem.htm/getEntity','${pageContext.request.contextPath}/purchaseDeItem.htm/deleteRow','是否删除？');							
		} 
		
		function saveRow(target){			
			return saveOrDelete(target,'${pageContext.request.contextPath}/purchaseDeItem.htm/getEntity','${pageContext.request.contextPath}/purchaseDeItem.htm/saveRow','是否保存？');				
		}
	</script>
	<!--

		function deleterow(target){
			$.messager.confirm('Confirm','是否删除?',function(r){
				if (r){		
					var rowIndex = getRowIndex(target);	
					$('#tt').datagrid('selectRow',rowIndex);
	       			var row = $('#tt').datagrid('getSelected');
	       			if(row.idc!=0){
	       				var userList = new Array();  
	       				userList.push({idc:row.idc});						
						$.ajax({  
							type: "post",  
							url: '${pageContext.request.contextPath}/purchaseDeItem.htm/deleteRow',
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
					}		
					//$('#tt').datagrid('deleteRow', rowIndex);				
				}
			});
		}
		
		function saveRow(target){
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/materialStock.htm/getEntity',
				traditional: true,
				dataType:"json", 
				//contentType : 'application/json;charset=utf-8', //设置请求头信息  
				success: function(result) {		
					var rowIndex = getRowIndex(target);
					$('#tt').datagrid('endEdit', rowIndex);	
					$('#tt').datagrid('selectRow',rowIndex);
			       	var row = $("#tt").datagrid('getSelected');   
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
						url: '${pageContext.request.contextPath}/materialStock.htm/saveRow',
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
							$('#tt').datagrid('updateRow',{
							
							});											
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});
				}	
			});				
		} -->
	
		 
	 
	
	
	<script>
	
		
		function moveToNewPurchaseNum(){
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/materialStock.htm/getEntity',
				traditional: true,
				dataType:"json", 
				//contentType : 'application/json;charset=utf-8', //设置请求头信息  
				success: function(result) {						
					var rows = $('#tt').datagrid('getSelections');
					var userList = new Array();   				
					for(var i=0; i<rows.length; i++){
						for(var key in rows[i]){
							if(!result[0].hasOwnProperty(key)){	
								delete rows[i][key];
							}else{
								if(rows[i][key] == undefined){
									delete rows[i][key];
								}
							}				
						}
						userList.push(rows[i]);								
					}	
					$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/purchaseDeNum.htm/newPurchaseDeNum',
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
							for(var i=0; i<rows.length; i++){	
								var rowIndex = $('#tt').datagrid('getRowIndex', rows[i]);	
								$('#tt').datagrid('deleteRow', rowIndex);							
							}
							//alert('删除成功！');			 						
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


