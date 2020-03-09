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
	<link rel="stylesheet" type="text/css" href="../../../../jquery-easyui-1.7.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../../../../jquery-easyui-1.7.0/themes/icon.css">
<!-- 	<link rel="stylesheet" type="text/css" href="../../../../jquery-easyui-1.7.0/demo.css"> -->
	<script type="text/javascript" src="../../../../jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/myjs/xlsx.js"></script>
    <script type="text/javascript" src="../../js/myjs/datagrid-export.js"></script> 
    <script type="text/javascript" src="../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../custom/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../../js/myjs/jquery.form.js"></script> 
</head>
<!-- type="hidden" -->
<body>  
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
 		<div id="tb" style="padding:0 10px;height:auto">	
			<div id="ttdiv" style="width:100%; height:40px;">	

			</div>			
		</div> 
		<table id="tt"></table>		
		<!-- <a href="javascript:void(0);" onclick="CreateFormPage('打印测试', $('#tt'));">打印</a>	 -->
	</div>		
	<div id="dialog"  class="easyui-dialog" style="width:90%;height:80%;padding:0px" data-options="
			closed:true,
			resizable:true,	
			modal:true,	
			onResize:function(){
				$(this).dialog('center');
			},
			buttons: [{
					text:'保存',
					iconCls:'icon-ok',
					handler:function(){
						saverow();
					}
				},{
					text:'关闭',
					handler:function(){
						close();
					}
				}]
			">
		<div style="width:100%; margin-top:20px;"> 
			<div>
				<table > 
				    <tr> 
				      	<td style="width:80px;text-align:right;"> 用户名 </td> 
				      	<td> <input  id="userName" class="easyui-textbox" style="width:150px;" data-options="validType:'text'"> </td>                     
				       	<td style="width:70px;text-align:right;"> 密码 </td> 
				        <td> <input  id="userCode" class="easyui-passwordbox" style="width:150px;"  data-options="showEye: false"> </td>    	                    
				      	<td style="width:70px;text-align:right;"> 职位 </td> 
				      	<td> <input  id="position" class="easyui-textbox" style="width:150px;" data-options="validType:'text'"> </td> 	
				      	<td> <input  id="idc" class="easyui-numberbox" style="width:30px;board:0" type="hidden" > </td> 															
				  	</tr> 
				</table>     
			</div> 
		</div>	
		<div style="width:100%; height:300px; margin-top:20px;">
			
			<div style="width:25%; height:300px; text-align:center; float:left">
				<h2>基础信息</h2>
				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-checkbox dynamic" id="dynamic1" name="fruit" value="1" label="BOM读写:"></span>
						<span><input class="easyui-checkbox read" 	 id="read1"    name="fruit" value="1" label="BOM只读:"></span>					
					</form>
				</div>
				<div style="margin-bottom:20px">					
					<form>
						<span><input class="easyui-checkbox dynamic" id="dynamic2" name="fruit" value="2" label="图纸读写:"></span>
						<span><input class="easyui-checkbox read" 	 id="read2" name="fruit"    value="2" label="图纸只读:"></span>					
					</form>
				</div>
				<div style="margin-bottom:20px">				
					<form>
						<span><input class="easyui-checkbox dynamic" id="dynamic4" name="fruit" value="4" label="二维码:"></span>
						<span><input class="easyui-checkbox read" id="read4" name="fruit" value="4" label="二维码读:"></span>
					</form>
				</div>
 				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-checkbox dynamic" id="dynamic8" name="fruit" value="8" label="不合并读写:"></span>
						<span><input class="easyui-checkbox read" id="read8" name="fruit" value="8" label="不合并只读:"></span>
					</form>
				</div>
			</div>
			<div style="width:25%; height:300px; text-align:center; float:left">
				<h2>计划管理</h2>
				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-checkbox dynamic" name="fruit" id="dynamic16" value="16" label="订单读写:"></span>
						<span><input class="easyui-checkbox read" name="fruit" 		id="read16" value="16" label="订单只读:"></span>
					</form>
				</div>
				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-checkbox dynamic" name="fruit" id="dynamic32" value="32" label="采购读写:"></span>
						<span><input class="easyui-checkbox read" name="fruit" 		id="read32" value="32" label="采购只读:"></span>
					</form>
				</div>
				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-checkbox dynamic" name="fruit" id="dynamic64" value="64" label="排产读写:"></span>
						<span><input class="easyui-checkbox read" name="fruit" 		id="read64" value="64" label="排产只读:"></span>
					</form>
				</div>
				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-checkbox dynamic" name="fruit" id="dynamic128" value="128" label="委外读写:"></span>
						<span><input class="easyui-checkbox read" name="fruit" 		id="read128" value="128" label="委外只读:"></span>
					</form>
				</div>
			</div>
			<div style="width:25%; height:300px; text-align:center; float:left">
				<h2>原料仓库</h2>
				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-checkbox dynamic" name="fruit" id="dynamic256" value="256" label="入库读写:"></span>
						<span><input class="easyui-checkbox read" name="fruit" 		id="read256" value="256" label="入库只读:"></span>
					</form>
				</div>
				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-checkbox dynamic" name="fruit" id="dynamic512" value="512" label="领料读写:"></span>
						<span><input class="easyui-checkbox read" name="fruit" 		id="read512" value="512" label="领料只读:"></span>
					</form>
				</div>
				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-checkbox dynamic" name="fruit" id="dynamic1024" value="1024" label="库存读写:"></span>
						<span><input class="easyui-checkbox read" name="fruit" 		id="read1024" value="1024" label="库存只读:"></span>
					</form>
				</div>
				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-radiobutton scanen" name="fruit" id="scanen2" value="2" label="盘点允许:"></span>
						<span><input class="easyui-radiobutton scandis" name="fruit" id="scandis2" value="2" label="盘点禁止:"></span>
					</form>
				</div>
			</div>
			<div style="width:25%; height:300px; text-align:center; float:left">
				<h2>装配仓库</h2>
				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-checkbox dynamic" name="fruit" id="dynamic2048" value="2048" label="装配读写:"></span>	
						<span><input class="easyui-checkbox read" 		name="fruit" id="read2048" value="2048" label="装配只读:"></span>
					</form>				
				</div>
				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-radiobutton scanen" name="fruit" id="scanen8" value="8" label="装配盘点允:"></span>
						<span><input class="easyui-radiobutton scandis" name="fruit" id="scandis8" value="8" label="装配盘点禁:"></span>
					</form>
				</div>
			</div>
<!-- 		</div>
		<div style="width:100%; height:auto; margin-top:20px;"> -->
			<div style="width:25%; height:300px; text-align:center; float:left">
				<h2>焊接仓库</h2>	
				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-checkbox dynamic" name="fruit" id="dynamic4096" value="4096" label="焊接读写:"></span>	
						<span><input class="easyui-checkbox read" 	name="fruit" id="read4096" value="4096" label="焊接只读:"></span>
					</form>				
				</div>	
				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-radiobutton scanen" name="fruit" id="scanen32" value="32" label="焊接盘点允:"></span>
						<span><input class="easyui-radiobutton scandis" name="fruit" id="scandis32" value="32" label="焊接盘点禁:"></span>
					</form>
				</div>
			</div>	
			<div style="width:25%; height:300px; text-align:center; float:left">
				<h2>扣库存</h2>		
				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-checkbox dynamic" name="fruit" id="dynamic8192" value="8192" label="扣库存读写:"></span>	
						<span><input class="easyui-checkbox read" 	name="fruit" id="read8192" value="8192" label="扣库存只读:"></span>
					</form>				
				</div>		
			</div>
			<div style="width:25%; height:300px; text-align:center; float:left">
				<h2>看板模块</h2>
				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-checkbox dynamic" name="fruit" id="dynamic16384" value="16384" label="看板读写:"></span>
					 	<span><input class="easyui-checkbox read" name="fruit" id="read16384" value="16384" label="看板只读:"></span>
					</form>
				</div>
	
			</div>
			<div style="width:25%; height:300px; text-align:center; float:left">
				<h2>财务管理</h2>
				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-checkbox dynamic" name="fruit" id="dynamic32768" value="32768" label="来料读写:"></span>
						<span><input class="easyui-checkbox read" 	name="fruit" 	id="read32768" value="32768" label="来料只读:"></span>
					</form>
				</div>
				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-checkbox dynamic" name="fruit" id="dynamic65536" value="65536" label="成品读写:"></span>	
						<span><input class="easyui-checkbox read" name="fruit" 		id="read65536" value="65536" label="成品只读:"></span>
					</form>				
				</div>	
				<div style="margin-bottom:20px">
					<form>
						<span><input class="easyui-checkbox dynamic" name="fruit" id="dynamic131072" value="131072" label="委外读写:"></span>	
						<span><input class="easyui-checkbox read" name="fruit" 		id="read131072" value="131072" label="委外只读:"></span>
					</form>				
				</div>		
			</div>
	 	</div>	
	</div> 
  	
	<script>
	
		$(function(){
			$('#tt').datagrid({
				width:'100%',
				height:'100%',
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
  					{field:'userName',title:'用户名',width:100,align:'center'},
					{field:'position',title:'职位',width:100,align:'center'},	
					{field:'authorityDsc',title:'权限描述',width:500,align:'center'},		
					{field:'action',title:'Action',width:60,align:'center',
						formatter:function(value,row,index){							
							var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
							var d = '<a href="#" onclick="deleterow(this)">删除</a>';
							return e+d;
							
						}
					},		
					{field:'idc',title:'ID', width:10,hidden:'true'}
				]]			
			});
		       			 
        	$.ajax({ 
				type: "post",  				
				url:'${pageContext.request.contextPath}/authority.htm/loadData',
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
                              
		});
		
	</script>	
	<script>	

		function getRowIndex(target){
			var tr = $(target).closest('tr.datagrid-row');
			return parseInt(tr.attr('datagrid-row-index'));
		}

		function deleterow(target){
			$.messager.confirm('Confirm','是否删除?',function(r){
				if (r){
					var rowIndex = getRowIndex(target);
					$('#tt').datagrid('selectRow',rowIndex);
					var row = $("#tt").datagrid('getSelected');  					
					$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/authority.htm/deleteRow',
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
							$('#tt').datagrid('deleteRow', rowIndex);														
						}, 
						error : function() {
							alert("异常！");
						}
					});																
				}
			});
		}
		
		function saverow(){
 			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/authority.htm/saveRow',
				data:{userName:$("#userName").textbox("getText"),
					  userCode:$("#userCode").passwordbox("getValue"),
					  position:$("#position").textbox("getText"),
					  stateValue:stateValue,
					  stateValue1:stateValue1,
					  stateValue2:stateValue2,
					  idc:$("#idc").numberbox("getText"),
				}, 
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
					$('#dialog').dialog('close');	
					alert('保存成功')
/* 					$.messager.show({
								title:'提示信息',
								msg:'保存成功！'
					});	 */										
				}, 
				error : function() {
					alert("异常！");
				}
			}); 			
		}					 
        function close(){
        	$('#dialog').dialog('close');	
        }    
        function editrow(target){
        	var rowIndex = getRowIndex(target);
        	$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');
	       	var userid;
	       	if(target != undefined){	       		
	       		userid = row.idc;
	       	}else{
	       		userid = 0;
	       		$("#idc").numberbox("setValue", userid);
	       	}
	       	stateValue = 0;
	       	stateValue1 = 0
	       	stateValue2 = 0
	        $("#dialog").dialog({
			     title: '用户信息',
			     queryParams: {index:rowIndex,userid:userid},		
			    /*  content:"<iframe scrolling='auto' frameborder='0' src='./html/pf_bd_tables7_0_0.jsp' style='width:100%; height:100%; display:block;'></iframe>" */
			});		
			$("#dialog").dialog("open"); // 打开dialog     
			if (userid != 0) {
				$.ajax({
					type : "post",
					url : '${pageContext.request.contextPath}/authority.htm/getMessage',
					data: {idc:userid},
					traditional : true,
					dataType : "json",
					beforeSend : function() {
						load();
					},
					complete : function() {
						disLoad();
					},
					success : function(result) {
						$("#userName").textbox("setValue", result[0].userName);
					  	$("#userCode").passwordbox("setValue", result[0].userCode);
					  	$("#position").textbox("setValue", result[0].position);
						$("#idc").numberbox("setValue", userid);
						//var value = result[0].stateValue;
						stateValue = result[0].stateValue;					
						var str = stateValue.toString(2);		
						for(var i=0; i<str.length; i++){
							if(str.charAt(i)==1){
								var str1 = "1";
								for(j=0;j<str.length-i-1;j++){
									str1 = str1 + "0";
								}								
								
 								$("#dynamic" + parseInt(str1, 2)).checkbox({
	                                checked: true
	                            }); 
								
							}
							//alert(str.charAt(i));
						}
						stateValue1 = result[0].stateValue1;					
						var str = stateValue1.toString(2);		
						for(var i=0; i<str.length; i++){
							if(str.charAt(i)==1){
								var str1 = "1";
								for(j=0;j<str.length-i-1;j++){
									str1 = str1 + "0";
								}								
								
 								$("#read" + parseInt(str1, 2)).checkbox({
	                                checked: true
	                            }); 
								
							}
							//alert(str.charAt(i));
						}
						stateValue2 = result[0].stateValue2;					
						var str = stateValue2.toString(2);	
												
			        	cnt = 6-str.length;
			        	for(var i=0; i<cnt; i++){
			        		str = "0" + str;
			        	} 
						for(var i=0; i<str.length; i++){
							if(str.charAt(i)==1){
						 		var str1 = "1";
								for(j=0;j<str.length-i-1;j++){
									str1 = str1 + "0";
								} 																
 								$("#scanen" + parseInt(str1, 2)).radiobutton({
	                                checked: true
	                            }); 
								
							}else{
								var str1 = "1";
								for(j=0;j<str.length-i-1;j++){
									str1 = str1 + "0";
								}								
								//alert(str1);
 								$("#scandis" + parseInt(str1, 2)).radiobutton({
	                                checked: true
	                            }); 
							}
							//alert(str.charAt(i));
						}				
										
					},
					error : function() {
						alert("出错了！");
					}
				});
			}
		}
	
		$("#dialog").dialog({
		    onClose: function () {
		         location.reload();
		   	}
		});			
	
	</script>
	
	<script>
/*  		var stateValue = 0;
	 	$('.dynamic').checkbox({
	        onChange:function(){
	        	var ch = $(this).checkbox('options')['checked'];
				var value = parseInt($(this).checkbox('options').value, 10); //returns 2								
	           	if(ch == false){
					stateValue = stateValue - value;
	           	}else {
					stateValue = value + stateValue;
	          	}  
				//alert(stateValue);     
	        }
	    }); */
	
	 	var stateValue = 0;	
	 	$('.dynamic').checkbox({
	        onChange:function(){
	        	var ch = $(this).checkbox('options')['checked'];
				var value = parseInt($(this).checkbox('options').value, 10); //returns 2	
				if(ch == false){
					stateValue = stateValue - value;
	           	}else {
					stateValue = value + stateValue;
	          	} 
				//alert(stateValue); 							           
	        }
	    });
		var stateValue1 = 0;
		$('.read').checkbox({
	        onChange:function(){
	        	var ch = $(this).checkbox('options')['checked'];
				var value = parseInt($(this).checkbox('options').value, 10); //returns 2	
				if(ch == false){
					stateValue1 = stateValue1 - value;
	           	}else {
					stateValue1 = value + stateValue1;
	          	} 
				//alert(stateValue1); 			 						           
	        }
	    });
	
		var stateValue2 = 0;
		$('.scanen').radiobutton({
	        onChange:function(){
	        	var ch = $(this).radiobutton('options')['checked'];
				var value = parseInt($(this).radiobutton('options').value, 10); //returns 2	
				if(ch == false){
					stateValue2 = stateValue2 - value;
	           	}else {
					stateValue2 = value + stateValue2;
	          	} 
				//alert(stateValue2); 						           
	        }
	    });
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
                            	iconCls:'icon-add',
									handler:function(){editrow();}
								},'-',{
								iconCls:'icon-arrow-refresh',
								handler:function(){  
									$.ajax({ 
										type: "post",  				
										url:'${pageContext.request.contextPath}/authority.htm/loadData',
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
		
	</script>
 	
</body>
</html>


