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
    <title>提单记录</title> 
<!--         <link href="../../../css/base.css" rel="stylesheet"> -->
<!--     <link href="../../../css/providers1.css" rel="stylesheet"> -->
    <!-- <link rel="stylesheet" href="../../../../custom/uimaker/easyui.css">  -->
    
    
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
 		<div id="tb" style="padding:0 10px;height:auto">	
			<!-- <div id="ttdiv" style="width:100%; height:50px;">	 -->
				<table>
					<tr>
						<td>提单号:</td>
						<td><input class="easyui-combobox" id="num" style="width:200px; height:25px;"></input></td>
						<td>状态:</td>
						<td><input class="easyui-textbox" id="status"  style="width:100px;height:25px;"></input></td>								
						<td>开始时间:</td>
						<td><input class="easyui-datebox" id="startTime"  style="height:25px;width:150px;line-height:25px;"></input></td>						
						<td>结束时间:</td>
						<td><input class="easyui-datebox" id="endTime"  style="height:25px;width:150px;line-height:25px;"></input></td>						

						<td><a href="javascript:;" class="easyui-linkbutton"  onclick="find()" >查找</a></td>
						<!-- <td><a href="javascript:;" id="importBtn" class="easyui-linkbutton"  onclick="$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格')">导入</a> </td> -->
						<td><a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('toExcel','实时库存.xls')">导出</a></td>
						<td><a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('print','DataGrid')">打印</a>			</td>
					</tr>
				</table>
			<!-- </div> -->			
		</div>
		 
		<table id="tt" class="easyui-datagrid" style="width: 100%; height: 'atuo'" 
			data-options="collapsible:true,				
				url:'${pageContext.request.contextPath}/purchaseDeNum.htm/getData',
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
				<!-- 	<th data-options="field:'spread',width:40,formatter:fmaterSpread"></th>	 -->							
					<th data-options="field:'purchaseDeNum',title:'',width:100,editor:'text'">提单号</th>		
					<th data-options="field:'purchaseDeDate',title:'',width:100,align:'center',formatter:fmaterDate">日期</th>
					<th data-options="field:'purchaseDeStatus',title:'',width:100,align:'center',formatter:fmaterState">状态</th>
					<th data-options="field:'purchaseDeRemark',title:'',width:100">备注</th>
					<th data-options="field:'action',title:'',width:60,formatter:fmaterDetail"></th>
					<th data-options="field:'idc',title:'ID',width:10">ID</th>	
				</tr>
			</thead>
	 	</table> 		
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
		
		
		
	</script>	
	<script>	
/* 		function isOnlyRead(){
			var str = localStorage.getItem('authority1'); 			
	        str = str.substr(7, 1)
			if(str.charAt(0)==1){	
	        	return true;					        			
	        }else{
	        	return false;
	        }    		        	    	         		
		} */

		function find(){		
			var queryParams = $('#tt').datagrid('options').queryParams;
			queryParams.purchaseDeNum = $('#num').combobox('getText');
			queryParams.startTime = $('#startTime').datebox('getText');
			queryParams.endTime = $('#endTime').textbox('getText');
			queryParams.purchaseDeStatus = $('#status').textbox('getText');
			$('#tt').datagrid('reload');
		}
		
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
		
		/* function insert(target){
			if(isOnlyRead()==false){
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
		} */
 
/*         $(function(){				
           // $('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/purchaseNum.htm/loadData')}).datagrid('clientPaging');       
            $.ajax({ 
				type: "post",  			
				url:'${pageContext.request.contextPath}/purchaseDeNum.htm/loadData',
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
        });  */ 
            
        function spreadrow(target){
        	$('#tt').datagrid('unselectAll');
        	var rowIndex = getRowIndex(target);
        	$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');
	        $("#dialog").dialog({
			     title: '提单号 :' + row.purchaseDeNum,
			     queryParams: {purchaseDeNumID: row.idc},		
			     content:"<iframe scrolling='auto' frameborder='0' src='./deliveryRecordDetail.jsp' style='width:100%; height:100%; display:block;'></iframe>"
			});		
			$("#dialog").dialog("open"); // 打开dialog	
					 	
        }    			
     	
     	
/*      	function inputEnter() {
			var qvalue = $("#userId").textbox("getValue");
			//alert(qvalue);
			var data = {}	
			data["name"] = $("#userId").textbox("getValue");
			$.ajax({
				type : "post",
				url : '${pageContext.request.contextPath}/purchaseDeNum.htm/searchPurchaseDeNum',
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
					$('#tt').datagrid('loadData', result);
				},
				error : function() {
					alert("异常！");
				}
			});
		} */
		
       	$('#num').combobox({
		        prompt:'请输入要查询的采购单', 	//提示信息
				//required:true, 	//是否必填
				mode:'remote', 		//动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
				url:'${pageContext.request.contextPath}/purchaseDeNum.htm/autotimp', 	//请求数据路径
				editable:true, 		//可编辑
				hasDownArrow:false, 	//下拉面板不关闭
				valueField: "id", 	//数组的键索引
				textField: "name", 	//数组的值索引
				icons:[{
						//iconCls:'icon-search'
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
		        }

		});		             
         
        
   /*      $('#purItemID').combobox({
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
							//alert(JSON.stringify(result));	
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
		});	 */
         
       	/* $('#textbox6').datebox({
			onSelect: function(date){
				alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
				$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/purchaseDeNum.htm/betweenDateFind',
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
		});		 */



		
		function getPurchaseDeNumID(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["purchaseDeNumID"];
		}
		
		function deleterow(target){
			return saveOrDelete(target,'${pageContext.request.contextPath}/purchaseDeNum.htm/getEntity','${pageContext.request.contextPath}/purchaseDeNum.htm/deleteRow','是否删除？');							
		} 
		
		function saveRow(target){			
			return saveOrDelete(target,'${pageContext.request.contextPath}/purchaseDeNum.htm/getEntity','${pageContext.request.contextPath}/purchaseDeNum.htm/saveRow','是否保存？');				
		}
	</script>

</body>
</html>


