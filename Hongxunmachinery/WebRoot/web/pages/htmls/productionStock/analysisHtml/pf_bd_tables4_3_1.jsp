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
    <script type="text/javascript" src="../../../js/myjs/xlsx.js"></script>
    <script type="text/javascript" src="../../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../../custom/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../../../js/myjs/jquery.form.js"></script> 
<!--     <link rel="stylesheet" type="text/css" href="../css/my.css" /> -->
</head>


<body>

	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
 		<div id="tb" style="padding:0 10px;height:auto">	
			<div id="ttdiv" style="width:100%;  height:50px;">
				<div style="margin-top:5px; margin-left:10px; float:left">				
					<input type="text" id="quantityId" class="easyui-numberbox" value="" data-options="min:1,prompt:'盘点数量'" style="width:90px;height:35px;">	 	
 					<input class="easyui-combobox" id="userId" style="width:400px;height:35px;"></input>
					<!-- <input class="easyui-textbox" id="scanQ" data-options="iconCls:'icon-search'" style="width:200px;height:35px;"> 	   -->
		        	<a href="javascript:;" id="importBtn" class="easyui-linkbutton"  onclick="$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格')">导入</a>	  
		        	<a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('toExcel','dg.xls')">导出</a>
		        	<a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('print','DataGrid')">打印</a>
		        	<a href="javascript:;" id="sync" class="easyui-linkbutton"   onclick="synchronization()">同步</a>			
				</div>
			</div>	
		</div> 
		<div id="importExcelDlg" class="easyui-dialog" style="width:280px;height:150px;padding:10px 0px" data-options="top:10,closed:true">
			<form action='' enctype="multipart/form-data" method="post" name="fileForm"> 
		        <label >
				  	<input type="file" name="xlfile" id="xlf" />
 			   		<%-- <input type="file" class="file" name="filename" id="fileId" onchange="upload('${pageContext.request.contextPath}/weldCompilation.htm/exlImport')" accept=".xls"/>			 --%>
				</label> 			
			</form> 	
		</div> 
		<table id="tt" class="easyui-datagrid">
			<thead frozen="true">
				<tr>					
					<th data-options="field:'ck',checkbox:true"></th>
					<th field="idc" align="center" width="20" hidden="true">ID</th>
					<th field="materialNum" align="center" width="120">物料编码</th>
					<th field="specification" align="center" width="280">型号/规格</th>
				</tr>
			</thead>
			<thead>
			 		
				<tr>
	<!-- 			<th data-options="field:'ck',checkbox:true,rowspan:2"></th>
					<th rowspan="2" field="materialNum" align="center" width="120">物料编码</th>
					<th rowspan="2" field="specification" align="center" width="280">型号/规格</th> -->
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
					<th field="checkQuantity1" width="60" align="center">盘点量</th>
					<th field="diffQuantity1" width="60" align="center">差异量</th>
					<th field="remark1" width="60" align="center">备注</th>
					<th field="quantity2" width="60" align="center">库存量</th>
					<th field="inQuantity2" width="60" align="center">入库量</th>
					<th field="outQuantity2" width="60" align="center">出库量</th>
					<th field="checkQuantity2" width="60" align="center">盘点量</th>
					<th field="diffQuantity2" width="60" align="center">差异量</th>
					<th field="remark2" width="60" align="center">备注</th>
					<th field="quantity3" width="60" align="center">库存量</th>
					<th field="inQuantity3" width="60" align="center">入库量</th>
					<th field="outQuantity3" width="60" align="center">出库量</th>
					<th field="checkQuantity3" width="60" align="center">盘点量</th>
					<th field="diffQuantity3" width="60" align="center">差异量</th>
					<th field="remark3" width="60" align="center">备注</th>
					<th field="quantity4" width="60" align="center">库存量</th>
					<th field="inQuantity4"  align="center" width="60">入库量</th>
					<th field="outQuantity4"  align="center" width="60">出库量</th>
					<th field="checkQuantity4"  align="center" width="60">盘点量</th>
					<th field="diffQuantity4"  align="center" width="60">差异量</th>
					<th field="remark4"  align="center" width="60">备注</th>
					<th field="quantity5"  align="center" width="60">库存量</th>
					<th field="inQuantity5"  align="center" width="60">入库量</th>
					<th field="outQuantity5"  align="center" width="60">出库量</th>
					<th field="checkQuantity5"  align="center" width="60">盘点量</th>
					<th field="diffQuantity5"  align="center" width="60">差异量</th>
					<th field="remark5"  align="center" width="60">备注</th>
					<th field="quantity6"  align="center" width="60">库存量</th>
					<th field="inQuantity6"  align="center" width="60">入库量</th>
					<th field="outQuantity6"  align="center" width="60">出库量</th>
					<th field="checkQuantity6"  align="center" width="60">盘点量</th>
					<th field="diffQuantity6"  align="center" width="60">差异量</th>
					<th field="remark6"  align="center" width="60">备注</th>
					<th field="quantity7"  align="center" width="60">库存量</th>
					<th field="inQuantity7"  align="center" width="60">入库量</th>
					<th field="outQuantity7"  align="center" width="60">出库量</th>
					<th field="checkQuantity7"  align="center" width="60">盘点量</th>
					<th field="diffQuantity7"  align="center" width="60">差异量</th>
					<th field="remark7"  align="center" width="60">备注</th>
					<th field="quantity8"  align="center" width="60">库存量</th>
					<th field="inQuantity8"  align="center" width="60">入库量</th>
					<th field="outQuantity8"  align="center" width="60">出库量</th>
					<th field="checkQuantity8"  align="center" width="60">盘点量</th>
					<th field="diffQuantity8"  align="center" width="60">差异量</th>
					<th field="remark8"  align="center" width="60">备注</th>
					<th field="quantity9"  align="center" width="60">库存量</th>
					<th field="inQuantity9"  align="center" width="60">入库量</th>
					<th field="outQuantity9"  align="center" width="60">出库量</th>
					<th field="checkQuantity9"  align="center" width="60">盘点量</th>
					<th field="diffQuantity9"  align="center" width="60">差异量</th>
					<th field="remark9"  align="center" width="60">备注</th>
					<th field="quantity10"  align="center" width="60">库存量</th>
					<th field="inQuantity10"  align="center" width="60">入库量</th>
					<th field="outQuantity10"  align="center" width="60">出库量</th>
					<th field="checkQuantity10"  align="center" width="60">盘点量</th>
					<th field="diffQuantity10"  align="center" width="60">差异量</th>
					<th field="remark10"  align="center" width="60">备注</th>
					<th field="quantity11"  align="center" width="60">库存量</th>
					<th field="inQuantity11"  align="center" width="60">入库量</th>
					<th field="outQuantity11"  align="center" width="60">出库量</th>
					<th field="checkQuantity11"  align="center" width="60">盘点量</th>
					<th field="diffQuantity11"  align="center" width="60">差异量</th>
					<th field="remark11"  align="center" width="60">备注</th>
					<th field="quantity12"  align="center" width="60">库存量</th>
					<th field="inQuantity12"  align="center" width="60">入库量</th>
					<th field="outQuantity12"  align="center" width="60">出库量</th>
					<th field="checkQuantity12"  align="center" width="60">盘点量</th>
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
				url:'${pageContext.request.contextPath}/weldCompilation.htm/loadData',
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
				$('#userId').textbox('disable');
				$('#importBtn').linkbutton('disable');
				$('#sync').linkbutton('disable');
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
		
		$('#userId').combobox({
			prompt : '请输入部件号', //提示信息
			//required:true, 	//是否必填
			mode : 'remote', //动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
			url : '${pageContext.request.contextPath}/weldNoLimitItemOutStock.htm/autotimp', //请求数据路径				
			editable : true, //可编辑
			hasDownArrow : false, //下拉面板不关闭
			valueField : "id", //数组的键索引
			textField : "name", //数组的值索引
			queryParams : {
				//orderNumID : window.parent.window.getOrderNumID()
			},
			icons : [ {
				iconCls : 'icon-search'
			} ],
			onBeforeLoad : function(param) { //onBeforeLoad：在请求加载数据之前触发，返回 false 则取消加载动作，为true的话则重新加载数据。
				//console.log("------ "+param.q+" ------"); 	//param.q ：combobox框输入的参数，请求方式POST
				if (param == null || param.q == null || param.q.replace(/ /g, '') == '') {
					var value = $(this).combobox('getValue');
					if (value) { //不为空的时候才传关键字到后台，模糊查询数据到前台
						param.q = value;
						//param["outStoreNumID"] = window.parent.window.getOutStoreNumID();
						return true;
					}
					return false;
				}
			},
			onSelect : function(record) {
				if ($("#quantityId").numberbox("getValue") == "") {
					$.messager.show({
						title : '提示信息',
						msg : '报错，扫描数量为空',
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
				$.messager.confirm('Confirm', '是否盘点?', function(r) {
					if (r) {
						var data={}
		         		//data["name"]=$("#userId").textbox("getValue");		         		
		         		record["quantity"]=$("#quantityId").numberbox("getValue");
						//alert(JSON.stringify(record));	
		            	$.ajax({  
							type: "post",  
							url: '${pageContext.request.contextPath}/weldCompilation.htm/weldCompilation',			
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
								if(result.length>0){
									if(result[0].hasOwnProperty('error')){						
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
							 		}
								}else{	
									$("#userId").textbox("setValue","");	
									$("#quantityId").numberbox("setValue","");						
									alert("盘点写入成功");
								}							
							}, 
							error : function() {
						       	alert("异常！");
						  	}
						});
					}
				});
			}
		});


		function synchronization(){
			$.messager.confirm('Confirm', '是否同步库存?', function(r) {
				if (r) {

					$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/weldCompilation.htm/synchronization',
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
											url:'${pageContext.request.contextPath}/weldCompilation.htm/loadData',
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
        function getNowFormatDate() {
	      	var date = new Date();
	      	var seperator1 = "-";
	      	var year = date.getFullYear();
	      	var month = date.getMonth() + 1;
	      	var strDate = date.getDate();
	      	if (month >= 1 && month <= 9) {
	          	month = "0" + month;
	      	}
	      	if (strDate >= 0 && strDate <= 9) {
	          	strDate = "0" + strDate;
	      	}
	      	var currentdate = year + seperator1 + month + seperator1 + strDate;
	      	return currentdate;
	  	}  
 	</script>
	<script>
	
		(function() {
			var xlf = document.getElementById('xlf');
			if(!xlf.addEventListener) return;
			function handleFile(e) {
				//$('#tt').datagrid('importexcl','DataGrid');	
				do_file(e.target.files);
			}
			xlf.addEventListener('change', handleFile, false);
		})();
		
		var process_wb = (function() {
			var to_json = function to_json(workbook) {
				var result = {};
				workbook.SheetNames.forEach(function(sheetName) {
					var roa = XLSX.utils.sheet_to_json(workbook.Sheets[sheetName], {header:1});
					if(roa.length) result[sheetName] = roa;
				});
				return (result[Object.keys(result)[0]]);
			};
		
			return function process_wb(wb) {	
				return to_json(wb)		
			};
		})();
	
		var do_file = (function() {
			return function do_file(files) {
				var f = files[0];
				var reader = new FileReader();
				reader.onload = function(e) {
					var data = e.target.result;
					var result =  process_wb(XLSX.read(data, {type: 'binary'}));
					var fields = $('#tt').datagrid('getColumnFields',true).concat($('#tt').datagrid('getColumnFields',false));		
					var jsonHead = {};
					for(var i=0; i<fields.length; i++){
					 	var option = $("#tt").datagrid('getColumnOption', fields[i]);
						if (option.field != "checkItem") { //过滤勾选框和隐藏列
		                   jsonHead[fields[i]]= option.title;
		                }
					}
					//alert(JSON.stringify(jsonHead));
					if(result[0].indexOf('物料编码')>0 && result[0].indexOf('盘点量')>0){
						var data = new Array(); 					
						for(var i=1; i< result.length; i++){
							var jsonRow = {};
							for(var j=0; j< result[i].length; j++){	
								for(var p in jsonHead){  
									if(result[0][j]=== jsonHead[p]){
								  　　　　　	jsonRow[p]= result[i][j];
								　　　　　	break;  
								　　　}  						
								}
							}
						if(jsonRow.hasOwnProperty('materialNum') && 
								jsonRow.hasOwnProperty('checkQuantity1') && 
								jsonRow.hasOwnProperty('idc')){								
								data.push(jsonRow);
							}else{
								break;
							}
						}
						//alert(result.length + ':' + data.length);
						if((result.length-1) != data.length){
							alert('请检查导入表格的物料编码，盘点量，ID列数据是否有空值');
						}else{		
							var date = (getNowFormatDate());					
							$.ajax({
								type : "post",
								url : '${pageContext.request.contextPath}/weldCompilation.htm/xlf?date=' + date,
								data : JSON.stringify(data),
								//traditional: true,
								dataType : "json",
								contentType : 'application/json;charset=utf-8', //设置请求头信息  
								beforeSend : function() {
									load();
								},
								complete : function() {
									disLoad();
								},
								success : function(result) {
									if(result[0].hasOwnProperty('error')){
										alert(result[0].error);	
									}else{
										$('#tt').datagrid('loadData', result);
									}
									$('#importExcelDlg').dialog('close');
								},
								error : function() {
									alert("异常！");
								}
							});
						}	
					}else{
						alert('导入表格格式出错');
					}
				};
				reader.readAsBinaryString(f);
			};
			
		})();
	</script> 
</body>
</html>


