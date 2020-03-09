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
    <script type="text/javascript" src="../../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../custom/jquery.easyui.min.js"></script>
    <link href="../../../css/base.css" rel="stylesheet">
    <link href="../../../css/providers1.css" rel="stylesheet">
    <link rel="stylesheet" href="../../../../custom/uimaker/easyui.css"> 
 	<link rel="stylesheet" type="text/css" href="../../../../custom/uimaker/icon.css">

    <script type="text/javascript" src="../../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../../js/myjs/ajaxfileupload.js"></script>
    <script type="text/javascript" src="../../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../../custom/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
		<div id="tb" style="padding:0 10px;height:auto">	
			<div id="ttdiv" style="width:100%;  height:50px;">
				<div style="margin-top:5px; margin-left:10px; float:left">	
					<!-- <input type="text" id="quantityId" class="easyui-numberbox" value="" data-options="min:1,prompt:'出库数量'" style="width:90px;height:35px;">
					<input class="easyui-combobox" id="userId" style="width:200px;height:35px;"></input>  -->	  
		        	<a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('toExcel','领料单.xls')">导出</a>
		        	<a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('print','DataGrid')">打印</a>			
				</div>
			</div>		   
		</div> 
		<table id="tt"></table> 
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
				pageList: [10,50,100,500,1000],
				rownumbers:true,
				columns:[[
 					{field:'ck', checkbox:true},	
					{field:'materialNum',title:'物料编码',align:'center',width:40},
					//{field:'materialName',title:'物料名称',align:'center',width:40},									
					{field:'specification',title:'型号/规格',align:'center',width:50},		
					{field:'materialPurchaseQuantity',title:'采购数量',align:'center',width:20},
					{field:'materialRealQuantity',title:'已入库量',align:'center',width:30,
						formatter:function(value,row,index){
							if(value != undefined){
								if(row.materialPurchaseQuantity == value){
									return  '<span style="color:green">'+value+'</span>';
								}else{
									return  '<span style="color:red">'+value+'</span>';
								}
							}
						}
					},	 
					{field:'unit',title:'单位',align:'center',width:20}, 
					{field:'instoreDate',title:'入库日期',width:40,align:'center',sortable: true },   	 				
					//{field:'instoreQuantity',title:'本次入库量',align:'center',width:40,editor:{type:'numberbox',options:{required:true}}},	
					{field:'lotNum',title:'批号',align:'center',width:30,editor:'text'},		
					{field:'remark',title:'备注',align:'center',width:20,editor:'text'},
					/*{field:'action',title:'Action',width:30,align:'center',
						formatter:function(value,row,index){
							if(isOnlyRead()==false){														
								var s = '<a href="#" onclick="instore(this)">入库</a> ';									
								return s;
								
							}else{
					    	 	return '';
					    	}
				    	}
					},*/
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
				url: '${pageContext.request.contextPath}/purchaseItem.htm/getPurchaseItems',
				data:{purchaseNumID:window.parent.window.getPurchaseNumID()},
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
                         
        });  

			
		function getRowIndex(target){
			var tr = $(target).closest('tr.datagrid-row');
			return parseInt(tr.attr('datagrid-row-index'));
		}
			        
        function instore(target){
        	$.messager.confirm('Confirm','是否采购入库?',function(r){
				if (r){
		 			var rowIndex = getRowIndex(target);
					$('#tt').datagrid('endEdit', rowIndex);					
					$('#tt').datagrid('selectRow',rowIndex);
			       	var row = $('#tt').datagrid('getSelected'); 		
				    $.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/purchaseItem.htm/materialInStore',
						data:{id:row.idc, quantity: $("#quantityId").numberbox("getValue")}, 
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
							}else{	
								$('#tt').datagrid('loadData', result);	
							}									
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
											url: '${pageContext.request.contextPath}/purchaseItem.htm/getPurchaseItems',
											data:{purchaseNumID:window.parent.window.getPurchaseNumID()},
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
												//$('#tt').datagrid({data:result}).datagrid('clientPaging'); 	 
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
</body>
</html>

