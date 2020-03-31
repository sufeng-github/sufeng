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
 	<script type="text/javascript" src="../../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../../js/myjs/xlsx.js"></script>
    <script type="text/javascript" src="../../../js/myjs/datagrid-export.js"></script>
    <script type="text/javascript" src="../../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../../custom/easyui-lang-zh_CN.js"></script>
</head>

<body>
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
 		<div id="tb" style="padding:0 10px;height:auto">	
			<div id="ttdiv" style="width:100%;  height:50px;">
				<div style="margin-top:5px; margin-left:10px; float:left">	
					<input class="easyui-combobox" id="userId" style="width:400px;height:35px;"></input>		        		        	
					<a href="javascript:;" id="importBtn" class="easyui-linkbutton"  onclick="$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格')">导入</a> 
					<a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('toExcel','焊接送货单.xls')">导出</a>
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
				height:'100%',
				toolbar:'#tb',
				singleSelect:true,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				pagination:true,
				rownumbers:true,
				columns:[[
 					{field:'ck', checkbox:true},						
					{field:'pon',title:'PO号',align:'center',width:40},								
					{field:'line',title:'行号',align:'center',width:30},								
					{field:'materialNo',title:'物料号',width:40,align:'center'},   					
					{field:'materialDesc',title:'物料描述',align:'center',width:150},		
					{field:'exemption',title:'免检',align:'center',width:20},	
					{field:'code',title:'仓储代码',align:'center',width:20},
					{field:'unit',title:'单位',align:'center',width:20},
					{field:'projectNum',title:'项目号',align:'center',width:20},
					{field:'sendQuantity',title:'送货数量',align:'center',width:20},
					{field:'status',title:'状态',align:'center',width:20},
					{field:'remark',title:'备注',align:'center',width:20},				
					{field:'idc',title:'ID',width:20,hidden:'true'},
					{field:'deliveryNumID',title:'deliveryNumID',width:20,hidden:'true'}
												
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
				url: '${pageContext.request.contextPath}/deliveryWeldItem.htm/loadData',
				data:{deliveryNumID:window.parent.window.getDeliveryNumID('#dialog')},
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
				}, 
				error : function() {
			       	alert("异常！");
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

/* 		$('#userId').combobox({
			prompt : '请输入部件号', //提示信息
			//required:true, 	//是否必填
			mode : 'remote', //动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
			url : '${pageContext.request.contextPath}/deliveryWeldItem.htm/autotimp', //请求数据路径				
			editable : true, //可编辑
			hasDownArrow : false, //下拉面板不关闭
			valueField : "id", //数组的键索引
			textField : "name", //数组的值索引
			queryParams : {
				//orderNumID : window.parent.window.getOrderNumID()
				deliveryNumID : window.parent.window.getDeliveryNumID('#dialog')
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
				record["deliveryNumID"] = window.parent.window.getDeliveryNumID('#dialog');
				$.ajax({
						type : "post",
						url : '${pageContext.request.contextPath}/deliveryWeldItem.htm/searchMaterialNum',
						data : record,
						traditional : true,
						dataType : "json",
						beforeSend : function() {
							load();
						},
						complete : function() {
							disLoad();
						},
						success : function(result) {						
							$('#tt').datagrid('loadData',result);	 	
							$("#userId").textbox("setValue", "");
						},
						error : function() {
							alert("异常！");
						}
				});
	
			}
	
			
		}); */

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
				$.messager.confirm('Confirm', '是否出库?', function(r) {
					if (r) {
					
	       				var row = $('#tt').datagrid('getSelected');
						if(row!=null){
							record["deliveryItemID"] = row.idc;
						}else{
							alert("请选中要出库的项");
							return;
						}
				
						$.ajax({
							type : "post",
							url : '${pageContext.request.contextPath}/deliveryWeldItem.htm/outstock',
							data : record,
							traditional : true,
							dataType : "json",
							beforeSend : function() {
								load();
							},
							complete : function() {
								disLoad();
							},
							success : function(result) {
								if (result[0].hasOwnProperty('error')) {
									alert(result[0].error);
								} else {														
									$('#tt').datagrid('updateRow', {
										index : $('#tt').datagrid('getRowIndex',row),
										row : {
											remark:""
										}
									});
								}
								$("#userId").textbox("setValue", "");
							},
							error : function() {
								alert("异常！");
							}
						});						
					}
				});
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
									iconCls:'icon-arrow-refresh',
									handler:function(){	   
										$.ajax({  
											type: "post",  
											url: '${pageContext.request.contextPath}/deliveryWeldItem.htm/loadData',
											data:{deliveryNumID:window.parent.window.getDeliveryNumID('#dialog')},
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
												$('#tt').datagrid('loadData',result);	 						
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
						jsonRow.deliveryNumID=window.parent.window.getDeliveryNumID('#dialog');
						data.push(jsonRow);
  					}
  					//alert(JSON.stringify(data));
	  				$.ajax({
	  					type : "post",
	  					url : '${pageContext.request.contextPath}/deliveryWeldItem.htm/importData',
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


