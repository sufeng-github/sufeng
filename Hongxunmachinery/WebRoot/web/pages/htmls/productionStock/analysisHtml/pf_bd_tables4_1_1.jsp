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
    <script type="text/javascript" src="../../../js/myjs/datagrid-export.js"></script>
    <script type="text/javascript" src="../../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../../custom/easyui-lang-zh_CN.js"></script>
<!--     <link rel="stylesheet" type="text/css" href="../css/my.css" /> -->
</head>


<body>

	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
 		<div id="tb" style="padding:0 10px;height:auto">	
			<div id="ttdiv" style="width:100%;  height:50px;">
				<div style="margin-top:5px; margin-left:10px; float:left">				
					<input type="text" id="quantityId" class="easyui-numberbox" value="" data-options="min:1,prompt:'盘典数量'" style="width:90px;height:35px;">	 	
<!-- 					<input class="easyui-combobox" id="userId" style="width:200px;height:35px;"></input>	 -->
					<input class="easyui-textbox" id="scanQ" data-options="iconCls:'icon-search'" style="width:200px;height:35px;"> 	  
		        	<a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('toExcel','dg.xls')">导出</a>
		        	<a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('print','DataGrid')">打印</a>
		        	<a href="javascript:;"  class="easyui-linkbutton"   onclick="synchronization()">同步</a>			
				</div>
			</div>	
		</div> 
		<table id="tt" class="easyui-datagrid">
			<thead>
			 		
				<tr>
					<th data-options="field:'ck',checkbox:true,rowspan:2"></th>
					<th rowspan="2" field="materialNum" align="center" width="120">物料编码</th>
					<th rowspan="2" field="specification" align="center" width="280">型号/规格</th>
					<th colspan="6">一月</th>
					<th colspan="6">二月</th>
					<th colspan="6">三月</th>
					<th colspan="6">四月</th>
					<th colspan="6">五月</th>
					<th colspan="6">六月</th>
					<th colspan="6">七月</th>
					<th colspan="6">八月</th>
					<th colspan="6">九月</th>
					<th colspan="6">十月</th>
					<th colspan="6">十一月</th>
					<th colspan="6">十二月</th>
			
				</tr>
				<tr>
					<th field="quantity1" width="60" align="center">库存量</th>
					<th field="inQuantity1" width="60" align="center">入库量</th>
					<th field="outQuantity1" width="60" align="center">出库量</th>
					<th field="checkQuantity1" width="60" align="center">盘典量</th>
					<th field="diffQuantity1" width="60" align="center">差异量</th>
					<th field="remark1" width="60" align="center">备注</th>
					<th field="quantity2" width="60" align="center">库存量</th>
					<th field="inQuantity2" width="60" align="center">入库量</th>
					<th field="outQuantity2" width="60" align="center">出库量</th>
					<th field="checkQuantity2" width="60" align="center">盘典量</th>
					<th field="diffQuantity2" width="60" align="center">差异量</th>
					<th field="remark2" width="60" align="center">备注</th>
					<th field="quantity3" width="60" align="center">库存量</th>
					<th field="inQuantity3" width="60" align="center">入库量</th>
					<th field="outQuantity3" width="60" align="center">出库量</th>
					<th field="checkQuantity3" width="60" align="center">盘典量</th>
					<th field="diffQuantity3" width="60" align="center">差异量</th>
					<th field="remark3" width="60" align="center">备注</th>
					<th field="quantity4" width="60" align="center">库存量</th>
					<th field="inQuantity4"  align="center" width="60">入库量</th>
					<th field="outQuantity4"  align="center" width="60">出库量</th>
					<th field="checkQuantity4"  align="center" width="60">盘典量</th>
					<th field="diffQuantity4"  align="center" width="60">差异量</th>
					<th field="remark4"  align="center" width="60">备注</th>
					<th field="quantity5"  align="center" width="60">库存量</th>
					<th field="inQuantity5"  align="center" width="60">入库量</th>
					<th field="outQuantity5"  align="center" width="60">出库量</th>
					<th field="checkQuantity5"  align="center" width="60">盘典量</th>
					<th field="diffQuantity5"  align="center" width="60">差异量</th>
					<th field="remark5"  align="center" width="60">备注</th>
					<th field="quantity6"  align="center" width="60">库存量</th>
					<th field="inQuantity6"  align="center" width="60">入库量</th>
					<th field="outQuantity6"  align="center" width="60">出库量</th>
					<th field="checkQuantity6"  align="center" width="60">盘典量</th>
					<th field="diffQuantity6"  align="center" width="60">差异量</th>
					<th field="remark6"  align="center" width="60">备注</th>
					<th field="quantity7"  align="center" width="60">库存量</th>
					<th field="inQuantity7"  align="center" width="60">入库量</th>
					<th field="outQuantity7"  align="center" width="60">出库量</th>
					<th field="checkQuantity7"  align="center" width="60">盘典量</th>
					<th field="diffQuantity7"  align="center" width="60">差异量</th>
					<th field="remark7"  align="center" width="60">备注</th>
					<th field="quantity8"  align="center" width="60">库存量</th>
					<th field="inQuantity8"  align="center" width="60">入库量</th>
					<th field="outQuantity8"  align="center" width="60">出库量</th>
					<th field="checkQuantity8"  align="center" width="60">盘典量</th>
					<th field="diffQuantity8"  align="center" width="60">差异量</th>
					<th field="remark8"  align="center" width="60">备注</th>
					<th field="quantity9"  align="center" width="60">库存量</th>
					<th field="inQuantity9"  align="center" width="60">入库量</th>
					<th field="outQuantity9"  align="center" width="60">出库量</th>
					<th field="checkQuantity9"  align="center" width="60">盘典量</th>
					<th field="diffQuantity9"  align="center" width="60">差异量</th>
					<th field="remark9"  align="center" width="60">备注</th>
					<th field="quantity10"  align="center" width="60">库存量</th>
					<th field="inQuantity10"  align="center" width="60">入库量</th>
					<th field="outQuantity10"  align="center" width="60">出库量</th>
					<th field="checkQuantity10"  align="center" width="60">盘典量</th>
					<th field="diffQuantity10"  align="center" width="60">差异量</th>
					<th field="remark10"  align="center" width="60">备注</th>
					<th field="quantity11"  align="center" width="60">库存量</th>
					<th field="inQuantity11"  align="center" width="60">入库量</th>
					<th field="outQuantity11"  align="center" width="60">出库量</th>
					<th field="checkQuantity11"  align="center" width="60">盘典量</th>
					<th field="diffQuantity11"  align="center" width="60">差异量</th>
					<th field="remark11"  align="center" width="60">备注</th>
					<th field="quantity12"  align="center" width="60">库存量</th>
					<th field="inQuantity12"  align="center" width="60">入库量</th>
					<th field="outQuantity12"  align="center" width="60">出库量</th>
					<th field="checkQuantity12"  align="center" width="60">盘典量</th>
					<th field="diffQuantity12"  align="center" width="60">差异量</th>
					<th field="remark12"  align="center" width="60">备注</th>
				</tr>
			</thead>
		</table>
	</div>		

	<script>
		$(function(){
			 $('#tt').datagrid({
				width:'100%',
				height:'100%',
				toolbar:'#tb',
				singleSelect:true,
				//fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				pagination:true,
				pageList: [10,50,100,500,1000],
				rownumbers:true,				
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
        $(function(){				

           // $('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/materialStock.htm/loadData')}).datagrid('clientPaging');                 
           	$.ajax({ 
				type: "post",  				
				url:'${pageContext.request.contextPath}/assembleCompilation.htm/loadData',
				traditional: true,
				dataType:"json",  
				beforeSend: function () {
					load();
				},
				complete: function () {
					disLoad();
				},
				success: function(result) {
					//$('#tt').datagrid('loadData',result);
					//alert(JSON.stringify(result));
					$('#tt').datagrid({data:result}).datagrid('clientPaging');   
					//$('#tt').datagrid('loadData',result);              
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	
           	
 			if(isOnlyRead()==false){
				$('#scanQ').textbox('disable');
			}             
        });  
         
		function isOnlyRead(){
			var str = localStorage.getItem('authority2'); 			
	        str = str.substr(0, 1)
			if(str.charAt(0)==1){	
	        	return true;					        			
	        }else{
	        	return false;
	        }    		        	    	         		
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
		         				}
		         				//alert(qvalue)
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
		         		//data["id"]=window.parent.window.getOutStoreNumID();
		         		data["quantity"]=$("#quantityId").numberbox("getValue");
		         		//var data = {name:$('#userId').combobox("getValue"),id:window.parent.window.getPurchaseNumID(),quantity:$("#quantityId").numberbox("getValue")},
		         		//alert(JSON.stringify(data));	
		            	$.ajax({  
							type: "post",  
							url: '${pageContext.request.contextPath}/assembleCompilation.htm/scanAssembleCompilation',			
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
									//alert(result[0].error);
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
						 		}else if(result[0].hasOwnProperty('update')){
					/* 				$('#tt').datagrid('updateRow',{
										index: 0,
										row: {
											quantity: result[0].updateQuantity,
										}
									}); */	
									$("#scanQ").textbox("setValue","");
									$("#quantityId").numberbox("setValue","");
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


		function synchronization(){
		

					$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/assembleCompilation.htm/synchronization',
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
						success: function(result) {		
							/*$('#tt').datagrid('updateRow',{
							
							});	*/	
							alert("同步完成")									
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
                            },
                            buttons: [{								
									iconCls:'icon-arrow-refresh',
									handler:function(){  
										$.ajax({ 
											type: "post",  				
											url:'${pageContext.request.contextPath}/assembleCompilation.htm/loadData',
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
												//alert(JSON.stringify(result));
												//$('#tt').datagrid({data:result}).datagrid('clientPaging'); 
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
		
		function getData(tt, url, para){
           	$.ajax({ 
				type: "post",  				
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
					//alert(JSON.stringify(result));
		
					$(tt).datagrid('loadData', result);
					
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	
        }
	    
 	</script>
 
</body>
</html>


