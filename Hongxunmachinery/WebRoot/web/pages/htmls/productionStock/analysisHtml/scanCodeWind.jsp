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
		<div id="tb" style="padding:0 10px;height:150px">	
			<div style="margin-top:5px; margin-left:10px; float:left">	
						<table>	
							<tr>
								<td>扫描框:</td>
								<td><input class="easyui-textbox" id="scanQ" style="width:200px;height:25px;borad:none"></input></td>						
							</tr>
							<tr></tr>
							<tr></tr>
							<tr></tr>
							<tr></tr>
						</table>		
						<table>	
							<tr>
								<td>部件号:</td>
								<td><input class="easyui-textbox" id="num" readonly="true" style="width:200px;height:25px;"></input></td>
								<td>型号/规格:</td>
								<td><input class="easyui-textbox" id="spec" style="width:200px;height:25px;"></input></td>		
								<td>数量:</td>	
								<td><input class="easyui-textbox" id="quantity" readonly="true" style="width:50px;height:25px;"></input></td>				
							</tr>
							<tr></tr>
							<tr>
								<td>工单号:</td>
								<td><input class="easyui-textbox" id="workNum" readonly="true" style="width:200px;height:25px;"></input></td>
								<td>订单号:</td>
								<td><input class="easyui-textbox" id="orderNum" readonly="true" style="width:200px;height:25px;"></input></td>	
							</tr>			
							<tr>																	
								<td style="display:none;"><input class="easyui-numberbox"  id="idc" readonly="true" style="width:100px;height:25px;" ></input></td>		
								<td style="display:none;"><input class="easyui-textbox"  id="name" readonly="true" style="width:100px;height:25px;" ></input></td>									
							</tr>								
							<tr>	
								<td><a href="javascript:;" class="easyui-linkbutton"  onclick="instock()" >确定</a></td>															
							</tr>
						</table>						
			 </div> 			
		</div>
		<table id="tt" class="easyui-datagrid" style="width:100%; height:100%;" data-options="collapsible:true,				
				url:'${pageContext.request.contextPath}/productionStock.htm/getData',
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
					<th data-options="field:'materialNum',align:'center',width:40,editor:'text'">物料编码</th>
					<th data-options="field:'specification',align:'center',width:70,editor:'text'">型号/规格</th>
					<th data-options="field:'quantity',align:'center',width:20">库存量</th>
					<th data-options="field:'unit',align:'center',width:20">单位</th>
					<th data-options="field:'idc',width:10,hidden:'true'">ID</th>

				</tr>
			</thead>
	 	</table> 		
	</div>	
	

	<script>
			
		$('#tt').datagrid({
			loadFilter: function(data){
				if(data.total>50){
					data.rows=[];
					data.total = 0;
					data.pageSize=0;
				}
				return data;
			}, 
			onLoadSuccess:function(data){	
				if(data.total>0){
					$('#spec').textbox('setText',data.rows[0].specification);
					$('#idc').textbox('setText',data.rows[0].idc);					
				}										
			},
			onBeforeLoad:function(param){	

			},
			onDblClickRow:function(index, row){
				$('#spec').textbox('setText',row.specification);
				$('#idc').textbox('setText',row.idc);
				$('#idc').textbox('setText',row.name);		
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
		
		$("#scanQ").textbox({
			inputEvents : $.extend({}, $.fn.textbox.defaults.inputEvents, {
				keyup : function(event) {
					if (event.keyCode == 13) {						
						var qvalue = $("#scanQ").textbox("getValue");
						if (qvalue.indexOf("#") > -1) {	
							var queryParams = $('#tt').datagrid('options').queryParams;
							queryParams.materialNum = qvalue.split("#")[0];	
							$('#tt').datagrid('reload');
							$('#num').textbox('setText',qvalue.split("#")[0]);	
							$('#quantity').textbox('setText',qvalue.split("#")[1]);	
							$('#workNum').textbox('setText',qvalue.split("#")[2]);
							$('#orderNum').textbox('setText',qvalue.split("#")[2]);																									
						}else{						
								$.messager.show({
									title : '提示信息',
									msg : '报错，自动扫描模式二维码信息错误',
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

					}
				}
			})
		});
		
		function instock(){					
			if($('#num').textbox('getText')==""){
				alert("部件号不能为空！")
				return;
			}
			if($('#spec').textbox('getText')==""){
				alert("规格不能为空！")
				return;
			}
			if($('#quantity').textbox('getText')==""){
				alert("数量不能为空！")
				return;
			}
			var list = new Array();
			var obj = {};
			obj.materialNum = $('#num').textbox('getText');
			obj.specification = $('#spec').textbox('getText');
			obj.thisInQuantity = $('#quantity').textbox('getText');
			obj.idc = $('#idc').textbox('getText');
			if($('#name').textbox('getText')==""){
				if(parent.parent.getFatherParameter()=='productionStock.htm'){
					obj.name = 'pro';
				}else{
					obj.name = 'weld'; 
				}
			}else{
				obj.name = $('#name').textbox('getText');
			}
			list.push(obj);
			//alert(JSON.stringify(list));
			ajax("${pageContext.request.contextPath}/productionStock.htm/inStock",list,"装配件入库")
		} 
	</script>

</body>
</html>


