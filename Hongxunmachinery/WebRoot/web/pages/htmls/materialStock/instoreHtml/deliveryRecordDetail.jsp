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
								<td><a href="javascript:;" id="importBtn" class="easyui-linkbutton"  onclick="$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格')">导入</a> </td>
								<td><a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('toExcel','采购单.xls')">导出</a></td>
								<td><a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('print','DataGrid')">打印</a></td>
							</tr>
						</table>						
			 </div> 			
		</div>
		<table id="tt" class="easyui-datagrid" style="width: 100%; height: '100%'" 
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
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'materialNum',title:'物料编码',align:'center',width:30"></th>									
					<th data-options="field:'specification',title:'型号/规格',align:'center',width:80"></th>	    		
					<th data-options="field:'quantity',title:'库存量',align:'center',width:20"></th>	
					<th data-options="field:'safeQuantity',title:'安全库存',align:'center',width:20"></th>	
					<th data-options="field:'itemQuantity',title:'项目数',align:'center',width:20"></th>	
					<th data-options="field:'inRoadQuantity',title:'在途数',align:'center',width:20,formatter:fmaterColor"></th>	
					<th data-options="field:'quantity',title:'提单量',align:'center',width:20,formatter:fmaterColor"></th>		
					<!-- <th data-options="field:'date',title:'下单日期',align:'center',width:25"></th>	 --> 
					<th data-options="field:'deliveryDate',title:'交货日期',align:'center',width:25,formatter:fmaterDate"></th>	 
 					<th data-options="field:'status',title:'状态',align:'center',width:20,formatter:fmaterLcaStatus"></th>
					<th data-options="field:'unit',title:'单位',align:'center',width:20"></th>  					
					<th data-options="field:'supplier',title:'供应商',align:'center',width:20,editor:'text'"></th>
					<th data-options="field:'action',title:'Action',width:20,align:'center',formatter:fmaterAction"></th>	 				
					<th data-options="field:'idc',title:'ID',width:10,hidden:'true'">ID</th>	
				</tr>
			</thead>
	 	</table> 	
		
	</div>		

	<div id="menu" class="easyui-menu" style="width: 100px; display: none;">
	    <!--放置一个隐藏的菜单Div-->
		
		<div data-options="iconCls:''" onclick="moveToNewPurchaseNum()">生成采购单</div>
		<!-- <div data-options="iconCls:'icon-edit'" onclick="moveToOldPurchaseNum()">移动到已有采购单</div> -->
	    <!-- <div data-options="iconCls:'icon-remove'" onclick="deleteSelected()">删除选中行</div>  -->
	</div>
	<script>
		function fmaterLcaStatus(value,row,index){ 
			if(value=='已提单'){				
				return '<span style="color:red">'+'待采购'+'</span>';		
			}else{
				return value;
			}		
		}
		function find(){	
 			var queryParams = $('#tt').datagrid('options').queryParams;
			queryParams.materialNum = $('#num').combobox('getText');
			queryParams.specification = $('#spec').textbox('getText');
			queryParams.supplier = $('#supplier').textbox('getText');			
			$('#tt').datagrid('reload');				
		}
		
		function getNumID(){
			if(parent.getFatherDeItemStr()=="material"){
				return window.parent.window.getPurchaseDeNumID('#dialog')			 
			}else{
				return 0;
			}
		}
		
		$('#num').combobox({
			        prompt:'请输入要查询的部件号', 	//提示信息
					//required:true, 	//是否必填
					mode:'remote', 		//动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
					url:'${pageContext.request.contextPath}/purchaseDeItem.htm/autotimp', 	//请求数据路径
 					queryParams : {
						numId : getNumID()						
					}, 
					editable:true, 		//可编辑
					hasDownArrow:false, 	//下拉面板不关闭
					valueField: "id", 	//数组的键索引
					textField: "name", 	//数组的值索引
					icons:[{
					//iconCls:'icon-search'
					}],
					onBeforeLoad: function(param){ 	//onBeforeLoad：在请求加载数据之前触发，返回 false 则取消加载动作，为true的话则重新加载数据。
					   // console.log("------ "+param.q+" ------"); 	//param.q ：combobox框输入的参数，请求方式POST
		
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
				onBeforeLoad:function(param){
					if(parent.getFatherDeItemStr()=="material"){
					 	param.purchaseDeNumId=window.parent.window.getPurchaseDeNumID('#dialog')			 
					}					
				},
				loadFilter: function(data){					
 					for(var i=0; i<data.rows.length;i++){
						if(data.rows[i].hongXunMaterialStock != null){
							data.rows[i].materialNum 	= data.rows[i].hongXunMaterialStock.materialNum;
							data.rows[i].specification 	= data.rows[i].hongXunMaterialStock.specification;
							data.rows[i].quantity 		= data.rows[i].hongXunMaterialStock.quantity	
							data.rows[i].safeQuantity 	= data.rows[i].hongXunMaterialStock.safeQuantity		
							data.rows[i].itemQuantity 	= data.rows[i].hongXunMaterialStock.itemQuantity	
							data.rows[i].inRoadQuantity = data.rows[i].hongXunMaterialStock.inRoadQuantity	
							//data.rows[i].deliveryDate 	= data.rows[i].hongXunMaterialStock.deliveryDate   
							data.rows[i].unit  			= data.rows[i].hongXunMaterialStock.unit  
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
				},
				onRowContextMenu: function(e, rowIndex, rowData) { //右键时触发事件
					//if(materialOnlyRead()==false){
		                //三个参数：e里面的内容很多，真心不明白，rowIndex就是当前点击时所在行的索引，rowData当前行的数据
		                e.preventDefault(); //阻止浏览器捕获右键事件
		                //$(this).datagrid("clearSelections"); //取消所有选中项
		                $(this).datagrid("selectRow", rowIndex); //根据索引选中该行
		                $('#menu').menu('show', {
							//显示右键菜单
		                    left: e.pageX,//在鼠标点击处显示菜单
		                    top: e.pageY
		                });
	                //}
	            },
			});
			
	
		function deleterow(target){
			return saveOrDelete(target,'${pageContext.request.contextPath}/purchaseDeItem.htm/getEntity','${pageContext.request.contextPath}/purchaseDeItem.htm/deleteRow','是否删除？');							
		} 
		
		function saveRow(target){			
			return saveOrDelete(target,'${pageContext.request.contextPath}/purchaseDeItem.htm/getEntity','${pageContext.request.contextPath}/purchaseDeItem.htm/updateRow','是否保存？');				
		}
	
		function moveToNewPurchaseNum(){					
			return action('${pageContext.request.contextPath}/purchaseDeItem.htm/getEntity','${pageContext.request.contextPath}/purchaseNum.htm/newPurchaseNum','是否生成采购单？');				
		}
	    function isOnlyRead(){
			var str = localStorage.getItem('authority1'); 			
	        str = str.substr(12, 1)
			if(str.charAt(0)==1){	
	        	return true;					        			
	        }else{
	        	return false;
	        }    		        	    	         		
		}
		
	
/* 		function getDlgParameter(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["mainID"];
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
		} */
	
	
/* 		function moveToNewPurchaseNum(){
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/purchaseDeItem.htm/getEntity',
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
					//alert(JSON.stringify(userList));
					$.ajax({  
						type: "post",
						url : '${pageContext.request.contextPath}/purchaseNum.htm/newPurchaseNum',
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
		}	 */
	</script>
 	
</body>
</html>



  
		
		


			    