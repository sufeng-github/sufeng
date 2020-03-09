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
 	<script type="text/javascript" src="../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/myjs/xlsx.js"></script>
    <script type="text/javascript" src="../../js/myjs/datagrid-export.js"></script> 
    <script type="text/javascript" src="../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../custom/easyui-lang-zh_CN.js"></script>
</head>

<body>  
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
		<div id="tb" style="padding:0 10px;height:auto">	
			<div id="ttdiv" style="width:100%; height:40px;">	
 				<div style="margin-top:1px; margin-left:5px; float:left">	
					<a href="javascript:;" class="easyui-linkbutton" style="height:35px;" onclick="$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格')">导入</a> 
		        </div>
		        <div style="margin-top:1px; margin-left:20px; float:left">	
		        	<a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('toExcel','不合并物料.xls')">导出</a>
		        </div>
		        <div style="margin-top:1px; margin-left:20px; float:left">	
		        	<a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('print','DataGrid')">打印</a>			
				</div>
			</div>			
		</div>
		<table id="tt"></table>		
	</div>		
 	<div id="importExcelDlg" class="easyui-dialog" style="width:280px;height:150px;padding:10px 0px" data-options="top:10,closed:true">
		<form action='' enctype="multipart/form-data" method="post" name="fileForm"> 
		  	<label >
				<input type="file" name="xlfile" id="xlf" />	  	
			</label> 			
		</form> 	
	</div>
	<script>
		$(function(){		
			$('#tt').datagrid({
				width:'100%',
				height:'90%',
				toolbar:'#tt1tb',
				singleSelect:true,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				pagination:true,
				//pagination:true,
				pageList: [10,100,400,800,1000],
				rownumbers:true,
				columns:[[
 					{field:'ck', checkbox:true},			
					{field:'materialNum',title:'物料编码',align:'center',width:40,editor:'text'},								
					{field:'specification',title:'型号/规格',align:'center',width:100,editor:'text'},		
					{field:'attribute',title:'属性',align:'center',width:20,editor:'text'},
					{field:'localtion',title:'层级',align:'center',width:30,editor:'text'},
					{field:'remark',title:'备注',align:'center',width:20,editor:'text'},
					{field:'action',title:'操作',width:20,align:'center',
						formatter:function(value,row,index){
							if(isOnlyRead()==false){
								if (row.editing){							
									var s = '<a href="#" onclick="saveRow(this)">保存</a> ';
									var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
									return s+c;
								} else {					
									var e = '<a href="#" onclick="editrow(this)">编辑</a> ';								
									var d = '<a href="#" onclick="moveRow(this)">删除</a>';							
									return e+d;							
								}
							}else{
					    	 	return '';
					    	}
				    	}
					},
					{field:'idc',title:'ID',width:20,hidden:'true'}
					
					
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
 
        $(function(){				         
           $.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/specialMaterialNum.htm/loadData',
				
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
					$('#tt').datagrid({data:result}).datagrid('clientPaging');      
					//$('#tt').datagrid('loadData', result);								
				}, 
				error : function() {
			       	alert("异常！");
			  	}
			});	 
			if(isOnlyRead()==true){
            	var oDiv = document.getElementById("ttdiv");														
				oDiv.style.display = "none";
			}          
        });  
            

	</script>
	<script>
	  	function isOnlyRead(){
			var str = localStorage.getItem('authority1'); 			
	        str = str.substr(14, 1)
			if(str.charAt(0)==1){	
	        	return true;					        			
	        }else{
	        	return false;
	        }    		        	    	         		
		}
		function getRowIndex(target){
			var tr = $(target).closest('tr.datagrid-row');
			return parseInt(tr.attr('datagrid-row-index'));
		}
		function editrow(target){
			$('#tt').datagrid('selectRow',getRowIndex(target));
			$('#tt').datagrid('beginEdit', getRowIndex(target));
		}
		function moveRow(target){
			$.messager.confirm('Confirm','是否删除?',function(r){
				if (r){
				
					var rowIndex = getRowIndex(target);
					$('#tt').datagrid('endEdit', rowIndex);	
					$('#tt').datagrid('selectRow',rowIndex);
					var row = $("#tt").datagrid('getSelected'); 
					//alert(row.idc); 
					if(row.idc==0){
						$('#tt').datagrid('deleteRow', rowIndex);
					}else{    
						$.ajax({  
							type: "post",  
							url: '${pageContext.request.contextPath}/specialMaterialNum.htm/deleteRow',
							//data:JSON.stringify(userList), 
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
								if(result){
									$('#tt').datagrid('deleteRow', rowIndex);	
								}						
							}, 
							error : function() {
								alert("异常！");
							}
						});	
					}									
				}
			});
		} 
		

		function saveRow(target){
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/specialMaterialNum.htm/getEntity',
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
						url: '${pageContext.request.contextPath}/specialMaterialNum.htm/saveRow',
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
							//if(result.length != 0){
							//alert(JSON.stringify(result[0]));	
							$('#tt').datagrid('updateRow',{
								index: rowIndex,
								row: {
									materialMoney: result[0].materialMoney
								}
							});								
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});	     	     	
				}, 
				error : function() {
			       	alert("异常！");
			  	}
			});	     	     
		
	
		}
		
		function cancelrow(target){
			$('#tt').datagrid('selectRow',getRowIndex(target));
			$('#tt').datagrid('cancelEdit', getRowIndex(target));
		}
		
		function insert(target){
			//$('#tt').datagrid('loadData',$('#tt').datagrid('getData')); 
			//$('#tt').datagrid('fixDetailRowHeight',index);
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
						idc:0
						//purchaseStatus:'已下单'				
					}
				});
				$('#tt').datagrid('selectRow',index);	
				$('#tt').datagrid('beginEdit',index); 	
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
/*
		$(".more").click(function(){
            $(this).closest(".conditions").siblings().toggleClass("hide");
        }); */
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
										handler:function(){insert();}
									},'-',{
									iconCls:'icon-arrow-refresh',
									handler:function(){  
										$.ajax({  
											type: "post",  
											url: '${pageContext.request.contextPath}/specialMaterialNum.htm/loadData',
											
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
												$('#tt').datagrid('loadData', result);							
											}, 
											error : function() {
										       	alert("异常！");
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
						//alert(window.parent.window.getPurchaseNumID("#dialog"));
						//jsonRow["purchaseNumID"] = window.parent.window.getPurchaseNumID("#dialog");
						data.push(jsonRow);	
					}
					//alert(JSON.stringify(data));
					$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/specialMaterialNum.htm/saveRow',
						data:JSON.stringify(data), 
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
							$('#tt').datagrid('loadData', result);
							$('#importExcelDlg').dialog('close');											
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});					
				};
				reader.readAsBinaryString(f);
			};
			
		})();
	</script>
</body>
</html>

