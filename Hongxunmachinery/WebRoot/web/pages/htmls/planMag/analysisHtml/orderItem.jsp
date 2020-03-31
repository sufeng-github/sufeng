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
  	<link rel="stylesheet" type="text/css" href="../../../../../jquery-easyui-1.7.0/themes/icon.css">
 	<link rel="stylesheet" href="../../../../../jquery-easyui-1.7.0/themes/default/easyui.css"> 
    <script type="text/javascript" src="../../../../../jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../../jquery-easyui-1.7.0/jquery.easyui.min.js"></script> 
   	<script type="text/javascript" src="../../../js/myjs/xlsx.js"></script>
    <script type="text/javascript" src="../../../js/myjs/datagrid-export.js"></script>
    <script type="text/javascript" src="../../../js/myjs/orderReadOnly.js"></script> 
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
								<td>物料号:</td>
								<td><input class="easyui-combobox" id="num" style="width:200px;height:25px;"></input></td>						
								<td>物料描述:</td>
								<td><input class="easyui-textbox" id="spec"  style="width:200px;height:25px;"></input></td>										
								<!-- <td>供应商:</td>
								<td><input class="easyui-textbox" id="supplier"  style="width:100px;height:25px;"></input></td>					 -->
								<td>PO号:</td>
								<td><input class="easyui-textbox" id="pon"  style="width:100px;height:25px;"></input></td>					
								<td>行号:</td>
								<td><input class="easyui-textbox" id="line"  style="width:100px;height:25px;"></input></td>					
								<!-- <td>开始时间:</td>
								<td><input class="easyui-datebox" id="startTime"  style="height:25px;width:150px;line-height:25px;"></input></td>						
								<td>结束时间:</td>
								<td><input class="easyui-datebox" id="endTime"  style="height:25px;width:150px;line-height:25px;"></input></td>								 -->
								<td><a href="javascript:;" class="easyui-linkbutton" data-options=" plain:true" onclick="find()" >查找</a></td>
								<td><a href="javascript:;" id="decrease" class="easyui-linkbutton" data-options=" plain:true" onclick="decrease()"><i class="fa fa-weibo fa-fw"></i>扣库存</a></td>
								<td><a href="javascript:;" id="analysis" class="easyui-linkbutton" data-options=" plain:true" onclick="analysis()"><i class="fa fa-chain-broken fa-fw"></i>订单分解</a></td>
								<td><a href="javascript:;"  class="easyui-linkbutton" data-options=" plain:true"  onclick="$('#tt').datagrid('toExcel','采购单.xls')">导出</a></td>
								<td><a href="javascript:;"  class="easyui-linkbutton" data-options=" plain:true"  onclick="$('#tt').datagrid('print','DataGrid')">打印</a>			</td>
							</tr>
						</table>						
			 </div> 			
		</div> 
		<table id="tt" class="easyui-datagrid" style="width: 100%; height: '100%'" 
			data-options="collapsible:true,				
				url:'${pageContext.request.contextPath}/poNum.htm/getData',
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
					<th data-options="field:'supplier',title:'供应商',width:80,align:'center'"></th>	
					<th data-options="field:'pon',title:'PO号',width:100,align:'center'"></th>	
					<th data-options="field:'line',title:'行号',width:50,align:'center'"></th>	
					<th data-options="field:'groupd',title:'采购组',width:50,align:'center'"></th>	
					<th data-options="field:'materialNo',title:'物料号',width:130,align:'center'"></th>					
					<th data-options="field:'materialDesc',title:'物料描述',width:180,align:'center'"></th>	 
					<th data-options="field:'exemption',title:'免检',width:30,align:'center'"></th>	 
					<th data-options="field:'unit',title:'单位',width:40,align:'center',hidden:'true'"></th>	
					<th data-options="field:'stockQuantity',title:'库存量',width:50,align:'center'"></th>					
					<th data-options="field:'quantity',title:'订单量',width:50,align:'center'"></th>	 
					<th data-options="field:'workQuantity',title:'生产量',width:50,align:'center',editor:'numberbox',formatter:fmaterColor"></th>				
					<th data-options="field:'poDeliveryDate',title:'PO交期',width:100,align:'center'"></th>	 
					<th data-options="field:'poCreateDate',title:'PO创建日期',width:100,align:'center',hidden:'true'"></th>					
					<th data-options="field:'others',title:'其他',width:80,align:'center'"></th>	 
					<th data-options="field:'stockInQuantity',title:'入库量',width:50,align:'center'"></th>	
					<th data-options="field:'stockOutQuantity',title:'出库量',width:50,align:'center'"></th>	
					<th data-options="field:'action',title:'Action',width:100,align:'center',formatter:fmaterAction"></th> 		
					<th data-options="field:'idc',title:'ID',width:20,hidden:'true'"></th> 
					<th data-options="field:'orderNumID',title:'orderNumID',width:20,hidden:'true'"></th> 
				</tr>
			</thead>
	 	</table> 	
	</div>	
 

	<script>
		function find(){	
 			var queryParams = $('#tt').datagrid('options').queryParams;
			queryParams.materialNum = $('#num').combobox('getText');
			queryParams.specification = $('#spec').textbox('getText');
			queryParams.pon = $('#pon').textbox('getText');
			queryParams.line = $('#line').textbox('getText');			
			$('#tt').datagrid('reload');	
		}	
	 	$('#tt').datagrid({	
	 		queryParams: {
				orderNumId:window.parent.window.getOrderNumID('#dialog')
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


		function deleterow(target){
			return saveOrDelete(target,'${pageContext.request.contextPath}/poNum.htm/getEntity','${pageContext.request.contextPath}/poNum.htm/deleteRow','是否删除？');							
		} 
		
		function saveRow(target){			
			return saveOrDelete(target,'${pageContext.request.contextPath}/poNum.htm/getEntity','${pageContext.request.contextPath}/poNum.htm/saveRow','是否保存？');				
		}		
        
        $(function(){				
         	$('#oneKeyDistribution').linkbutton('disable');
         	$('#oneKeyExportExcel').linkbutton('disable');
         	//if(window.parent.window.getOrderNumStatus()!="新定单"){
         	$('#analysis').linkbutton('disable');
         	$('#decrease').linkbutton('disable');
         	//}

			$.ajax({ 
				type: "post",  
				url:'${pageContext.request.contextPath}/poNum.htm/orderNumGetList', 
				data:{orderNumID:window.parent.window.getOrderNumID()},				
				traditional: true,
				dataType:"json",  
				beforeSend: function () {
					load();
				},
				complete: function () {
					disLoad();
				},
				success: function(result) {
					$('#tt').datagrid({data: result});
					if(window.parent.window.getOrderNumStatus()=="新订单"){
						if(isOnlyRead()==false){
				         	$('#analysis').linkbutton('enable');
				         	for (var i=0;i<result.length;i++){					
								if(result[i]["workQuantity"] != result[i]["quantity"]){							
									break;
								}else {
									if(i==result.length-1){										
										$('#decrease').linkbutton('enable');
									}
								}						
							} 
				        } 
				        
				    } 
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	                
        }); 
	
	
		$('#num').combobox({
		        prompt:'请输入要查询的部件号', 	//提示信息
				//required:true, 	//是否必填
				mode:'remote', 		//动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
				url:'${pageContext.request.contextPath}/poNum.htm/autotimp?orderNumID=' + window.parent.window.getOrderNumID(), 	//请求数据路径
				//data:{orderNum:window.parent.window.getOrderNumID()},
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
		        }
		});	
		    
		
		function excelImportDlg(){
			$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格');	
		}
		
       
		function analysis(){
			//var tab = $('#tt').tabs('getTab', '元数据');
			//var iframeWindow=tab.find('iframe')[0].contentWindow;	    
			//var data = iframeWindow.$('#tt').datagrid("getData").rows;	
			//window.top.window.getDlgOrderNumID();
			//alert(window.parent.window.getOrderNumID());
			//var data = $('#tt').datagrid("getData").rows;		
			//alert(JSON.stringify(data));
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/bomTree.htm/analysis',
				data:{orderNumID:window.parent.window.getOrderNumID()}, 
				traditional: true,
				dataType:"json", 
				//contentType : 'application/json;charset=utf-8', //设置请求头信息  
				beforeSend: function () {
					load();
				},
				complete: function () {					
					disLoad();
					//$('#oneKeyDistribution').linkbutton('enable');
					//$('#oneKeyExportExcel').linkbutton('enable');					
					$('#analysis').linkbutton('disable');
					$('#decrease').linkbutton('disable');
				},	
				success: function(result) {	

					//alert(JSON.stringify(result));
/* 					var t = new Date().format("yyyy-MM-dd hh:mm:ss");				
  					t=t.replace(/[-]/g,"");  
  					t=t.replace(/[:]/g,"");  
					t=t.replace(/[ ]/g,"");  
					window.parent.window.setTimeStamp(t) */
					if(result[0].hasOwnProperty('error')){
						alert(result[0].error);
					}else{	
						oneKeyExportExcel();
						oneKeyDistribution(result);		
						var list1 = result[0].list1;
						var list2 = result[0].list2;
						var list3 = result[0].list3;
						var list4 = result[0].list4;
						var list5 = result[0].list5;
						var list6 = result[0].list6;
						var listErrs = result[0].listErrs;
						var listError = result[0].listError;
						if(listErrs != undefined){
							window.parent.window.addTab('BOM不包含信息', './pf_bd_tables_error.jsp');							
						}else{
							
							if(list1 != undefined){
								window.parent.window.addTab('A01焊接下料', './pf_bd_tables_A01.jsp');
							} 
							if(list2 != undefined){
								window.parent.window.addTab('A02焊接钣金', './pf_bd_tables_A02.jsp');	
							}
							if(list3 != undefined){
								var list3_0 = list3[0].list3_0;
								if(list3_0 != undefined){
									window.parent.window.addTab('A03焊接', './pf_bd_tables_A03_0.jsp');
								}
								var list3_1 = list3[0].list3_1;
								if(list3_1 != undefined){
									window.parent.window.addTab('A03焊接下料', './pf_bd_tables_A03_1.jsp');
								}
								var list3_2 = list3[0].list3_2;
								if(list3_2 != undefined){
									window.parent.window.addTab('A03焊接钣金', './pf_bd_tables_A03_2.jsp');
								}
								var list3_3 = list3[0].list3_3;
								if(list3_3 != undefined){
									window.parent.window.addTab('A03焊接件', './pf_bd_tables_A03_3.jsp');
								}
								var list3_4 = list3[0].list3_4;
								if(list3_4 != undefined){
									window.parent.window.addTab('A03外购', './pf_bd_tables_A03_4.jsp');
								}
							}
							//alert(JSON.stringify(list4));
							if(list4 != undefined){
								var list4_4 = list4[0].list4_4;
								if(list4_4 != undefined){
									window.parent.window.addTab('装配', './pf_bd_tables_A04_4.jsp');
								}
								var list4_1 = list4[0].list4_1;
								if(list4_1 != undefined){
									window.parent.window.addTab('装配下料', './pf_bd_tables_A04_1.jsp');
								}
								var list4_2 = list4[0].list4_2;
								if(list4_2 != undefined){
									window.parent.window.addTab('装配钣金', './pf_bd_tables_A04_2.jsp');
								}
								var list4_3 = list4[0].list4_3;
								if(list4_3 != undefined){
									window.parent.window.addTab('装配焊接', './pf_bd_tables_A04_3.jsp');
								}
		
								var list4_5 = list4[0].list4_5;
								if(list4_5 != undefined){
									window.parent.window.addTab('装配外购', './pf_bd_tables_A04_5.jsp');
								}
								var list4_6 = list4[0].list4_6;
								if(list4_6 != undefined){
									window.parent.window.addTab('装配外协', './pf_bd_tables_A04_6.jsp');
								}
								var list4_7 = list4[0].list4_7;
								if(list4_7 != undefined){
									window.parent.window.addTab('装配喷涂', './pf_bd_tables_A04_7.jsp');
								}
							}
							
							if(list5 != undefined){
								var list5_4 = list5[0].list5_4;
								if(list5_4 != undefined){
									window.parent.window.addTab('732', './pf_bd_tables_A05_4.jsp');
								}
								var list5_1 = list5[0].list5_1;
								if(list5_1 != undefined){
									window.parent.window.addTab('732下料', './pf_bd_tables_A05_1.jsp');
								}
								var list5_2 = list5[0].list5_2;
								if(list5_2 != undefined){
									window.parent.window.addTab('732钣金', './pf_bd_tables_A05_2.jsp');
								}
								var list5_3 = list5[0].list5_3;
								if(list5_3 != undefined){
									window.parent.window.addTab('732焊接', './pf_bd_tables_A05_3.jsp');
								}
		
								var list5_5 = list5[0].list5_5;
								if(list5_5 != undefined){
									window.parent.window.addTab('732外购', './pf_bd_tables_A05_5.jsp');
								}
								var list5_6 = list5[0].list5_6;
								if(list5_6 != undefined){
									window.parent.window.addTab('732外协', './pf_bd_tables_A05_6.jsp');
								}
								var list5_7 = list5[0].list5_7;
								if(list5_7 != undefined){
									window.parent.window.addTab('732喷涂', './pf_bd_tables_A05_7.jsp');
								} 
							}
							if(list6 != undefined){
								window.parent.window.addTab('外购件', './pf_bd_tables_A06.jsp');
							}
							if(listError != undefined){
								window.parent.window.addTab('BOM属性为空的信息', './pf_bd_tables_attribute.jsp');
							}							
						}
					}
				}, 
				error : function() {
					alert("异常！");
				}
			});
			
		}
		
		function oneKeyDistribution(result){
			//window.parent.window.distribution();
			//alert('asdfasdfe123');
/* 			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/bomTree.htm/analysisItem ?name=all',
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
				success: function(result) {	 */
												
					var list1 = result[0].list1;
					var list2 = result[0].list2;
					var list3 = result[0].list3;
					var list4 = result[0].list4;
					var list5 = result[0].list5;
					var list6 = result[0].list6;
					/*if(list1 != undefined){
						newWorkNum(list1, 'A01焊接下料');										
					} 
					if(list2 != undefined){
						newWorkNum(list2, 'A02焊接钣金');
					}*/
					if(list3 != undefined){
						/*var list3_1 = list3[0].list3_1;
						if(list3_1 != undefined){
							newWorkNum(list3_1, 'A03焊接下料');
						}
						var list3_2 = list3[0].list3_2;
						if(list3_2 != undefined){
							newWorkNum(list3_2, 'A03焊接钣金');
						}
						var list3_3 = list3[0].list3_3;
						if(list3_3 != undefined){
							newWorkNum(list3_3, 'A03焊接');
						}*/
						var list3_4 = list3[0].list3_4;
						if(list3_4 != undefined){
							newMaterialOutStoreNum(list3_4, 'A03外购');
						}
					}
					//alert(JSON.stringify(list4));
					if(list4 != undefined){

						var list4_5 = list4[0].list4_5;
						if(list4_5 != undefined){
							newMaterialOutStoreNum(list4_5, '装配外购');
						}
						var list4_6 = list4[0].list4_6;
						if(list4_6 != undefined){
							newWeiwaiNum(list4_6, '装配外协');
						}

					}
					
					if(list5 != undefined){

						var list5_5 = list5[0].list5_5;
						if(list5_5 != undefined){
							newMaterialOutStoreNum(list5_5, '732外购');
						}
						var list5_6 = list5[0].list5_6;
						if(list5_6 != undefined){
							newWeiwaiNum(list5_6, '732外协');
						}

					}
					if(list6 != undefined){
						//alert('list6');
						newMaterialOutStoreNum(list6, '外购件');
					}
					//$('#oneKeyDistribution').linkbutton('disable');
					$.ajax({ 
						type: "post",  
						url:'${pageContext.request.contextPath}/poNum.htm/updateState', 
						data:{orderNumID:window.parent.window.getOrderNumID(),state:"订单已分配"},				
						traditional: true,
						dataType:"json",  
						error : function() {
					       	alert("出错了！");
					   	}
					});				 						
			
		}
		
		function oneKeyExportExcel(){
	        var fileName ="工单信息";
	            //编码防止中文字符乱码
	        window.location.href=encodeURI("${pageContext.request.contextPath}/bomTree.htm/oneKeyExportExcel?fileName="+encodeURIComponent(fileName) + "&orderNum="+ window.parent.window.getOrderNum());
		}
	
		function decrease(){
			$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/poNum.htm/decrease',
					data:{orderNumID:window.parent.window.getOrderNumID()}, 
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
						window.location.reload();
						//alert(JSON.stringify(result));
						//$('#tt').datagrid('loadData', result);  
						//$('#tt').datagrid({data: result}).datagrid('clientPaging');											
					}, 
					error : function() {
				       	alert("异常！");
				  	}
			});    		
		}
		
       function newWorkNum(rows, title){
			//var rows = $('#tt').datagrid('getSelections');  
			var userList = new Array(); 
			//alert(window.parent.window.getTimeStamp())
			userList.push({remark:title,status:window.parent.window.getOrderNum()});
		    for(var i=0; i<rows.length; i++){  
        		userList.push(rows[i]);
			}
			//var userList = $('#tt').datagrid('getData').rows;
			//alert(JSON.stringify(userList));
			$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/workNum.htm/newWorkNum',
					data:JSON.stringify(userList), 
					//traditional: true,
					dataType:"json", 
					contentType : 'application/json;charset=utf-8', //设置请求头信息  
					success: function(result) {	
						//updateStatus(userList,'工单已生成!');		
						//alert("分配成功");														
					}, 
					error : function() {
				       	alert("异常！");
				  	}
			});    		
		}
		
        function newWeiwaiNum(rows, title){
				
			var userList = new Array();      		
			//alert('attribute:' + attribute);
			userList.push({remark:title,status:window.parent.window.getOrderNum()});
			for(var i=0; i<rows.length; i++){
				userList.push(rows[i]);	
			}
				//alert(JSON.stringify(userList));
				$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/weiwaiNum.htm/newWeiwaiNum',
					data:JSON.stringify(userList), 
					//traditional: true,
					dataType:"json", 
					contentType : 'application/json;charset=utf-8', //设置请求头信息  

					success: function(result) {	
						//updateStatus(userList,'外协单已生成！');
						//alert("分配成功");																								
					}, 
					error : function() {
				       	alert("异常！");
				  	}
				});
		} 
		function newMaterialOutStoreNum(rows, title){
			//var rows = $('#tt').datagrid('getSelections');  	
			var flag = true;
			var userList = new Array();      		
			userList.push({remark:title,status:window.parent.window.getOrderNum()});
			//alert('attribute:' + attribute);
			for(var i=0; i<rows.length; i++){ //从1开始是因为每个LIST都加一个名子，在导出时会用到。
				//userList.push(rows[0]);	
				userList.push(rows[i]);		
			}
					//alert(JSON.stringify(userList));
			$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/purchaseNum.htm/newMaterialOutStoreNum',
					data:JSON.stringify(userList), 
					//traditional: true,
					dataType:"json", 
					contentType : 'application/json;charset=utf-8', //设置请求头信息  
					success: function(result) {
						//updateStatus(userList,'外购单已生成！');
						//alert("分配完成")																																	
					}, 
					error : function() {
				       	alert("异常！");
				  	}
			});		
		}
 	</script>

</body>
</html>