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
  <!--   <link href="../basic_info.css" rel="stylesheet"> -->
    <link rel="stylesheet" href="../../../font-awesome-4.7.0/css/font-awesome.min.css" media="screen" type="text/css"/>
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

		<table id="tt"></table>		
		<!-- <a href="javascript:void(0);" onclick="CreateFormPage('打印测试', $('#tt'));">打印</a>	 -->
	</div>		

	<div id="menu" class="easyui-menu" style="width: 100px; display: none;">
	    <!--放置一个隐藏的菜单Div-->
		<div  onclick="moveToNewPurchaseNum()"><i class="fa fa-file-powerpoint-o "></i>&nbsp; 提交采购单</div>
		<!-- <div data-options="iconCls:'icon-edit'" onclick="moveToOldPurchaseNum()">移动到已有采购单</div> -->
	    <!-- <div data-options="iconCls:'icon-remove'" onclick="deleteSelected()">删除选中行</div>  -->
	</div>
	<script>
	
		//$(function(){
			
			$('#tt').datagrid({
				width:'100%',
				height:'100%',
				//toolbar:'#tb',
				url:'${pageContext.request.contextPath}/materialStock.htm/getMaterialAlarms',
				singleSelect:false,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				pagination:true,
				pageList: [10,50,100,500,1000],
				rownumbers:true,
				columns:[[
 					{field:'ck', checkbox:true},	
					{field:'materialNum',title:'物料编码',align:'center',width:30},								
					{field:'specification',title:'型号/规格',align:'center',width:80},		
					{field:'quantity',title:'库存量',align:'center',width:20},
					{field:'safeQuantity',title:'安全库存',align:'center',width:20},
					{field:'itemQuantity',title:'项目数',align:'center',width:20},
					{field:'inRoadQuantity',title:'在途数',align:'center',width:20},
					{field:'purQuantity',title:'提单量',align:'center',width:20,editor:'numberbox'},		
					{field:'deliveryDate',title:'交货日期',align:'center',width:20}, 
					{field:'alarm',title:'状态',align:'center',width:20,
						formatter:function(value,row,index){ 				    	 	
							return  '<span style="color:red">'+value+'</span>';						
			      		}  
					},
					{field:'unit',title:'单位',align:'center',width:20},  
 					{field:'localCode',title:'库位代码',align:'center',width:20},  	
					{field:'remark',title:'备注',align:'center',width:20,editor:'text'},	
					{field:'action',title:'Action',width:20,align:'center',
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
							}
						}
					},			
					{field:'purchaseDeId',title:'purchaseDeId',width:10,hidden:'true'},	
					{field:'purchaseId',title:'purchaseId',width:10,hidden:'true'},	
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
				},
				onRowContextMenu: function(e, rowIndex, rowData) { //右键时触发事件
					if(isOnlyRead()==false){
		                //三个参数：e里面的内容很多，真心不明白，rowIndex就是当前点击时所在行的索引，rowData当前行的数据
		                e.preventDefault(); //阻止浏览器捕获右键事件
		                //$(this).datagrid("clearSelections"); //取消所有选中项
		                $(this).datagrid("selectRow", rowIndex); //根据索引选中该行
		                $('#menu').menu('show', {
							//显示右键菜单
		                    left: e.pageX,//在鼠标点击处显示菜单
		                    top: e.pageY
		                });
	                }
	            },
			});
			
		//});
		
	</script>	
	<script>	

        $(function(){				  
/*            $.ajax({ 
				type: "post",  				
				url:'${pageContext.request.contextPath}/materialStock.htm/getMaterialAlarms',
				traditional: true,
				data:{alarm:"报警"},
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
			});	 */
                         
        });  
            
	</script>
	<script>
		function isOnlyRead(){
			var str = localStorage.getItem('authority1'); 			
	        str = str.substr(7, 1)
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
	
		function getDlgParameter(dialog){
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
		}
/*
		$(".more").click(function(){
            $(this).closest(".conditions").siblings().toggleClass("hide");
        }); */
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
							url: '${pageContext.request.contextPath}/purchaseNum.htm/deleteRow',
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
				url: '${pageContext.request.contextPath}/materialStock.htm/getEntity',
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
						url: '${pageContext.request.contextPath}/materialStock.htm/saveRow',
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
											url:'${pageContext.request.contextPath}/materialStock.htm/getMaterialAlarms',
											traditional: true,
											data:{alarm:"报警"},
											dataType:"json",  
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
		
		function moveToNewPurchaseNum(){
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/materialStock.htm/getEntity',
				traditional: true,
				dataType:"json", 
				//contentType : 'application/json;charset=utf-8', //设置请求头信息  
				success: function(result) {						
					var rows = $('#tt').datagrid('getSelections');
					var userList = new Array();   				
					for(var i=0; i<rows.length; i++){
						for(var key in rows[i]){
							if(!result[0].hasOwnProperty(key)){	
								delete rows[i][key];
							}else{
								if(rows[i][key] == undefined){
									delete rows[i][key];
								}
							}				
						}
						userList.push(rows[i]);								
					}	
					$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/purchaseDeNum.htm/newPurchaseDeNum',
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
							for(var i=0; i<rows.length; i++){	
								var rowIndex = $('#tt').datagrid('getRowIndex', rows[i]);	
								//$('#tt').datagrid('deleteRow', rowIndex);							
							}
							//alert('删除成功！');			 						
						}, 
						error : function() {
							alert("异常！");
						}
					});	
				}
			});					
		}
		
	</script>
 	
</body>
</html>


