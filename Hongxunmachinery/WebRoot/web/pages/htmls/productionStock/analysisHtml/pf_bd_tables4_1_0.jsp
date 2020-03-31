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
    <script type="text/javascript" src="../../../js/myjs/xlsx.js"></script>
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
					<input class="easyui-combobox" id="userId" style="width:200px;height:35px;"></input>	
					<a href="javascript:;" id="importBtn" class="easyui-linkbutton"  onclick="$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格')">导入</a>  	  
		        	<a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('toExcel','装配件仓库.xls')">导出</a>
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
<!-- 	<div id="dialog"  class="easyui-dialog" style="width:80%;height:80%;padding:0px" data-options="
			closed:true,
			resizable:true,		
			onResize:function(){
				$(this).dialog('center');
			}">
		<div class="easyui-layout"  data-options="fit:true">	
		    <div data-options="region:'center'" style="padding:5px;background:#eee;">
				<div id="tt1tb" style="padding:0 10px;height:auto">		 
			        <div id="tt1div" style="width:100%; display:none">
			 			<span class="con-span">物料编码:</span>
					<span class="con-span"><input id="textbox3"  class="easyui-textbox" type="text" name="code" style="width:120px;height:25px;line-height:25px;"></input></span>
						<span class="con-span"><select class="easyui-combobox" id="textbox3" style="height:25px;width:120px;"></select></span>					
						<span class="con-span">数量:</span>
						<span class="con-span"><input id="textbox4"  class="easyui-numberbox" type="text" name="code" style="width:120px;height:25px;line-height:25px;"></input></span>
						<span class="con-span"><a href="#" class="easyui-linkbutton" style="height:25px;" data-options="iconCls:'icon-add', plain:true" onclick="dlginsert()">新增</a></span>
						<span class="con-span"><a href="#" class="easyui-linkbutton" style="height:25px;" data-options="iconCls:'icon-table-add', plain:true" onclick="orderImport()">导入表格</a></span>
						<span class="con-span"><a href="#" class="easyui-linkbutton" style="height:25px;" data-options="iconCls:'icon-table-go', plain:true" onclick="exportExcel()">导出表格</a></span>			        	
			        </div>
				</div>	
				<table id="tt"></table>				
			</div>
			<div data-options="region:'south',title:'South Title',split:true" style="height:100px;padding:5px;background:#eee;">
				<table id="tt2"></table> 	
			</div>	
		</div>	
	</div>       -->
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
				pageList: [10,100,400,800,1000],
				rownumbers:true,
				columns:[[
 					{field:'ck', checkbox:true},	
					{field:'materialNum',title:'物料编码',align:'center',width:40},								
					{field:'specification',title:'型号/规格',align:'center',width:50},		
					{field:'quantity',title:'库存量',align:'center',width:40},
					{field:'safeQuantity',title:'安全库存',align:'center',width:20,editor:'numberbox'},
					{field:'itemQuantity',title:'发货量',align:'center',width:20,editor:'numberbox'},
					{field:'inRoadQuantity',title:'在产量',align:'center',width:20,editor:'numberbox'},
					//{field:'calQuantity',title:'计算量',align:'center',width:20,editor:'numberbox'}, 
					{field:'unit',title:'单位',align:'center',width:20},  
 					{field:'localCode',title:'库位代码',align:'center',width:40,editor:'text'},  	
					{field:'remark',title:'备注',align:'center',width:20,editor:'text'},				
					{field:'action',title:'Action',width:40,align:'center',
						formatter:function(value,row,index){
							if(isOnlyRead()==false){
								if (row.editing){
									var s = '<a href="#" onclick="saveRow(this)">保存</a> ';
									var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
									return s+c;
								} else {
									var	e;
									/* 
									if(row.materialPlanQuantity <= row.materialRealQuantity){
										e= '入库完成'
									}else{
										e = '<a href="#" onclick="editrow1(this)">编辑</a> ';
									} */
									e = '<a href="#" onclick="editrow(this)">编辑</a> ';
									return e;	
								}
							}else{
								return "";
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
			

			
/* 			$("#dialog").dialog({
		        onClose: function () {
		            //$("#projectQuery").css("right", "-1000px"); 
		            var rows = $('#tt1').datagrid('getRows');
		            var result=0;
		            for(var i=0; i<rows.length; i++){
		            	if(isNumber(rows[i].materialMoney)){
		            		result = result + rows[i].materialMoney;
		            	}
		            }
		            //alert(result);	
		            var userList = new Array(); 
		            var mainID = getDlgParameter('#dialog'); 
        			userList.push({mainID:mainID, materialName:result});			     	
	       			funOne('#tt', '${pageContext.request.contextPath}/numOneBranchOne.htm/saveMoney',0,JSON.stringify(userList), 'saveMoney');	            
		        	//$('#tt').datagrid('reload');
		        }
		    });		 */			
		});
		
	</script>
	<script>
        $(function(){
        					
			$.ajax({ 
				type: "post",  				
				url:'${pageContext.request.contextPath}/productionStock.htm/loadData',
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
		
					$('#tt').datagrid({data:result}).datagrid('clientPaging');
					
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});     	
			if(isOnlyRead()==true){
				$('#importBtn').linkbutton('disable');
			}                        
        });
        
        function isOnlyRead(){
			var str = localStorage.getItem('authority1'); 			
	        str = str.substr(5, 1)
			if(str.charAt(0)==1){	
	        	return true;					        			
	        }else{
	        	return false;
	        }    		        	    	         		
		} 
		
        $('#userId').combobox({
		        prompt:'请输入要查询的部件号', 	//提示信息
				//required:true, 	//是否必填
				mode:'remote', 		//动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
				url:'${pageContext.request.contextPath}/productionStock.htm/autotimp', 	//请求数据路径
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
						url: '${pageContext.request.contextPath}/productionStock.htm/searchMaterialNum',
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

/*        	
		function betweendatefind(){
			var userList = new Array();  
			 
			userList.push({purchaseDate:$("#textbox5").textbox('getValue'),purchaseDeliveryDate:$("#textbox6").textbox('getValue')}),
			funOne('#tt','${pageContext.request.contextPath}/numOne.htm/betweenDateFind', 0, JSON.stringify(userList), 'datefind');			
		} */
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
											url:'${pageContext.request.contextPath}/productionStock.htm/loadData',
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
		function getRowIndex(target){
			var tr = $(target).closest('tr.datagrid-row');
			return parseInt(tr.attr('datagrid-row-index'));
		}
		function editrow(target){
			$('#tt').datagrid('selectRow',getRowIndex(target));
			$('#tt').datagrid('beginEdit', getRowIndex(target));
		}
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
							url: '${pageContext.request.contextPath}/productionStock.htm/deleteRow',
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
								if(result){
									$('#tt').datagrid('deleteRow', rowIndex);	
								}else{
									alert('存在子项，请先删除子项。');
								}							
							}, 
							error : function() {
						       	alert("异常！");
						  	}
						});						
					}		
					//$('#tt').datagrid('deleteRow', rowIndex);	
								
				}else{
					alert('删除失败，请重新操作。');
				}
			});
		}
		
		function saveRow(target){
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/productionStock.htm/getEntity',
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
						url: '${pageContext.request.contextPath}/productionStock.htm/saveRow',
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
		}
		function cancelrow(target){
			$('#tt').datagrid('selectRow',getRowIndex(target));
			$('#tt').datagrid('cancelEdit', getRowIndex(target));
		}	
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
						data.push(jsonRow);	
					}

					//alert(JSON.stringify(data));
					$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/productionStock.htm/delAllRows',
						//data:JSON.stringify(data), 
						traditional: true,
						dataType:"json", 
						//contentType : 'application/json;charset=utf-8', //设置请求头信息  
						beforeSend: function () {
							//load();
						},
						complete: function () {
							//disLoad();
						},
						success: function(result) {		
							$.ajax({  
								type: "post",  
								url: '${pageContext.request.contextPath}/productionStock.htm/saveRow',
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


