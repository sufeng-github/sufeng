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
    <link href="../../css/base.css" rel="stylesheet">
    <link href="../../css/providers1.css" rel="stylesheet">
    <link rel="stylesheet" href="../../../custom/uimaker/easyui.css"> 
 	<link rel="stylesheet" type="text/css" href="../../../custom/uimaker/icon.css">
    <link href="basic_info.css" rel="stylesheet">
 	<script type="text/javascript" src="../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../custom/easyui-lang-zh_CN.js"></script>
<!--     
	<script type="text/javascript" src="../topmenu/scripts/jquery.tmailsilder.v2.js"></script>	
    <link rel="stylesheet" type="text/css" href="../topmenu/css/jquery.tmailsilder.v2.css" /> 
-->
    <link rel="stylesheet" type="text/css" href="css/my.css" />
     
	<script>
		var products = [
		    {productid:'未下单',name:'未下单'},
		    {productid:'已下单',name:'已下单'},    		    
		    {productid:'已完成',name:'已完成'}
		];
		$(function(){
			$('#tt').datagrid({
				width:'100%',
				height:'auto',
				toolbar:'#tb',
				singleSelect:false,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				pagination:true,
				rownumbers:true,
				columns:[[
 					{field:'ck', checkbox:true},	
/*  				{field:'action1',title:'OP',width:40,align:'center',
						formatter:function(value,row,index){
							if (row.editing){	
								return '';
							}else{
								var a = '<a href="#" onclick="spreadrow(this)" style = "color: #1da02b;text-decoration:none"><i class="iconfont">&#xe60a;</i></a> ';	
								var b = '<a href="#" onclick="insert(this)" style = "color: #1da02b;text-decoration:none"><i class="iconfont">&#xe663;</i></a>';	
								return a + b;
							}	
						}
					}, */				
  					{field:'workNum',title:'加工单号',width:100,align:'center',editor:'text'},
  					{field:'orderNum',title:'订单号',width:100,align:'center',editor:'text'},
					{field:'componentNum',title:'部件编码',width:150,align:'center',editor:'text'},
					{field:'componentName',title:'部件名称',width:100,align:'center',editor:'text'},
					{field:'workQuantity',title:'加工数量',width:80,align:'center',editor:{type:'numberbox',options:{precision:0,required:false}}}, 
					{field:'workStartDate',title:'开始日期',width:130,align:'center',sortable: true,editor:{type:'datetimebox',options:{required:true}}},
					{field:'workEndDate',title:'结束日期',width:130,align:'center',sortable: true,editor: {type:'datetimebox',options:{required:true}} },								
 					{field:'status',title:'工单状态',width:50,align:'center',
						formatter:function(value){
							for(var i=0; i<products.length; i++){
								if (products[i].productid == value) return products[i].name;
							}
							return value;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'productid',
								textField:'name',
								data:products,
								required:true
							}
						}
					}, 
					{field:'remark',title:'备注',width:100,align:'center',editor:'text'},
					{field:'action',title:'Action',width:60,align:'center',
						formatter:function(value,row,index){
							if (row.editing){							
								var s = '<a href="#" onclick="saverow(this)">保存</a> ';
								var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
								return s+c;
							} else {					
								var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
								var d = '<a href="#" onclick="deleterow(this)">删除</a>';
								return e+d;
							}
						}
					},		
					{field:'ID',title:'ID', width:10,hidden:'true'}
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
			$('#tt1').datagrid({
				width:'100%',
				height:'auto',
				toolbar:'#tt1tb',
				singleSelect:true,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				pagination:true,
				rownumbers:true,
				columns:[[
 					{field:'ck', checkbox:true},			
					{field:'materialNum',title:'物料编码',align:'center',width:40,editor:'text'},
					{field:'materialName',title:'物料名称',align:'center',width:40,editor:'text'},									
					{field:'materialSpecification',title:'型号/规格',align:'center',width:50,editor:'text'},		
					{field:'materialPlanQuantity',title:'采购数量',align:'center',width:20,editor:'numberbox'},
					{field:'materialLotNum',title:'批号',align:'center',width:30,editor:'text'},  					
					{field:'materialRealQuantity',title:'到货数量',align:'center',width:20},	
					{field:'materialModel',title:'库位代码',align:'center',width:20,editor:'text'},  
					{field:'materialUnit',title:'单位',align:'center',width:20,editor:'text'},  
					{field:'materialPrice',title:'单价',align:'center',width:20,editor:{type:'numberbox',options:{precision:2,required:false}}},  
					{field:'materialMoney',title:'金额',align:'center',width:20,editor:{type:'numberbox',options:{precision:2,required:false}}},  
					{field:'materialsupplier',title:'供应商',align:'center',width:60,editor:'text'},	
					{field:'materialRemark',title:'备注',align:'center',width:20,editor:'text'},
					{field:'action',title:'Action',width:30,align:'center',
						formatter:function(value,row,index){
							if (row.editing){							
								var s = '<a href="#" onclick="saverow1(this)">保存</a> ';
								var c = '<a href="#" onclick="cancelrow1(this)">取消</a>';
								return s+c;
							} else {					
								var e = '<a href="#" onclick="editrow1(this)">编辑</a> ';
								var d = '<a href="#" onclick="deleterow1(this)">删除</a>';
								return e+d;
							}
						}
					},
					{field:'ID',title:'ID',width:20,hidden:'true'},
					{field:'parentID',title:'parentID',width:20,hidden:'true'}
					
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
			
			$('#tt2').datagrid({
				width:'100%',
				height:'auto',	
				toolbar:'#dlgtb2',
				singleSelect:true,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				pagination:false,
				rownumbers:true,
				collapsible:true,	
				//url:'${pageContext.request.contextPath}/numElevenBranchOne.htm/loadData',
			
				columns:[[
 					{field:'ck', checkbox:true},						
					{field:'purchaseBackName',title:'退货名称',align:'center',width:60,editor:'text'},		
					{field:'purchaseBackDate',title:'退货日期',width:130,align:'center',sortable: true,editor:{type:'datetimebox',options:{required:true}}},		
					{field:'purchaseBackModel',title:'型号',align:'center',width:60,editor:'text',hidden:'true'},  
					{field:'purchaseBackSpecification',title:'型号/规格',align:'center',width:60,editor:'text'},		
					{field:'purchaseBackLotNum',title:'批号',align:'center',width:60,editor:'text'},  
					{field:'purchaseBackQuantity',title:'数量',align:'center',width:60,editor:'numberbox'},		
					{field:'purchaseBackUnit',title:'单位',align:'center',width:60,editor:'text'},  
					{field:'purchaseBacksupplier',title:'供应商',align:'center',width:60,editor:'text'},	
					{field:'purchaseBackRemark',title:'退货说明',align:'center',width:80,editor:'text'},
					{field:'purchaseNum',title:'采购单号',width:20, hidden:'true'},	
					{field:'ID',title:'ID',width:20,hidden:'true'}

				]] 
			});				
/* 		    $("#dialog").dialog({
			    modal:true,
			    content:"<iframe name='iframe0' scrolling='auto' frameborder='0' src='pf_bd_tables2_3.html' style='width:100%; height:100%; display:block;'></iframe>"
			});
    
			$("#dialog1").dialog({
			     modal:true,
			     content:"<iframe scrolling='auto' frameborder='0' src='pf_bd_tables2_4.html' style='width:100%; height:100%; display:block;'></iframe>"
			}); */
				
		});
		
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
			$.messager.confirm('Confirm','Are you sure?',function(r){
				if (r){
				//alert(JSON.stringify($('#tt').datagrid('getData')));
					var rowIndex = getRowIndex(target);	
					$('#tt').datagrid('selectRow',rowIndex);
	       			var row = $('#tt').datagrid('getSelected');
	       			if(row.ID!=0){
						funOne('#tt','${pageContext.request.contextPath}/numEleven.htm/deleteRow',rowIndex);
					}		
					$('#tt').datagrid('deleteRow', rowIndex);	
								
				}else{
					alert('删除失败，请重新操作。');
				}
			});
		}
		function saverow(target){
			var rowIndex = getRowIndex(target);
			$('#tt').datagrid('endEdit', rowIndex);				
			 	//$(tt).datagrid('selectRow',rowIndex);
	       		//var row = $(tt).datagrid('getSelected');
			//$('#tt').datagrid('updateRow', rowIndex, row);	  			     		       	
	       	funOne('#tt','${pageContext.request.contextPath}/numEleven.htm/saveRow',rowIndex);	       	
			//$('#tt').datagrid('selectRow', rowIndex);	
					
		}
		function cancelrow(target){
			$('#tt').datagrid('selectRow',getRowIndex(target));
			$('#tt').datagrid('cancelEdit', getRowIndex(target));
		}			
		
		function insert(target){
			//$('#tt').datagrid('loadData',$('#tt').datagrid('getData')); 
			//$('#tt').datagrid('fixDetailRowHeight',index);
			
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
			//var expanderRows = $('#tt').datagrid('getExpander');		
			
			$('#tt').datagrid('insertRow', {
				index: index,
				row:{
					//purchaseOrderNum: $("#combobox1").textbox('getValue'),
					//productionOrderNum:	$("#textbox2").textbox('getValue'),
					//purchaseStatus:'采购中',
					ID:0				
				}
			});
			$('#tt').datagrid('selectRow',index);	
			$('#tt').datagrid('beginEdit',index); 	
			
			//('#tt').datagrid('updateRow',{index: index,row:{}});			
/*			
			var parentIndex = getRowIndex(target);
		    //保存父行数据，用于新增数据。
		    $j('#tt').datagrid('endEdit', parentIndex);
		    $j('#tt').datagrid('updateRow',{index: parentIndex,row:{}});
		    //获取父行数据，进行新增操作。
		    var newIndex = parentIndex+1;
		    $j('#tt').datagrid('selectRow',parentIndex);
		    var rowParent = $j('#tt').datagrid('getSelected');
		    var newRow = jQuery.extend(true, {}, rowParent);
		    $j('#tt').datagrid('insertRow',{
		        index:newIndex,
			  	row:newRow
		    });
*/
		}

		
		function getData(tt, url, para){
           	$.ajax({ 
				type: "post",  
				//url:'${pageContext.request.contextPath}/screwdata.htm/load',  	
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
					if(result.length==0){
						$(tt).datagrid('loadData', [{}])	
						$(tt).datagrid('deleteRow', 0);	 
					}else{
						$(tt).datagrid('loadData', result);
					}
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	
        }
        $(function(){				
            $('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/numEleven.htm/loadData')}).datagrid('clientPaging');       
            $('#tt1').datagrid('clientPaging'); 
           	$('#ss').searchbox({
            	//height:'40px',
			    searcher:function(value,name){
			    	getData('#tt','${pageContext.request.contextPath}/numEleven.htm/searchNum' + '?num=' + value);
			    },
			    //menu:'#mm',
			    prompt:'采购单号'
			});              
        });  
            
        function spreadrow(target){
        	$('#tt').datagrid('selectRow',getRowIndex(target));
	       	var row = $('#tt').datagrid('getSelected');
			$("#dialog").dialog("open").dialog('setTitle','采购单号 :' + row.purchaseOrderNum);
			getData('#tt1', '${pageContext.request.contextPath}/numElevenBranchOne.htm/loadData' + '?para=' + row.purchaseOrderNum);
			getData('#tt2', '${pageContext.request.contextPath}/numEight.htm/loadData' + '?para=' + row.purchaseOrderNum);
        }
     	
/*        	function popFindDlg(){
       		$('#dlgFind').dialog('open').dialog('setTitle','查找');
       	} */
       	
		function betweendatefind(){
			 var date1 = $("#textbox5").textbox('getValue');
			 var date2 = $("#textbox6").textbox('getValue');

				$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/numEleven.htm/betweenDateFind',
					data:{
						beginDate:$("#textbox5").textbox('getValue'),
						endDate:$("#textbox6").textbox('getValue')
					}, 
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
/* 		function getIndex(){
			var row = $('#tt').datagrid('getSelected');		
			var index = $('#tt').datagrid('getRowIndex', row);	
			//alert("index:" + index);	
		} */

	</script>
	<script>

		function getRowIndex1(target){
			var tr = $(target).closest('tr.datagrid-row');
			return parseInt(tr.attr('datagrid-row-index'));
		}
		function editrow1(target){
			$('#tt1').datagrid('selectRow',getRowIndex(target));
			$('#tt1').datagrid('beginEdit', getRowIndex(target));
		}
		function deleterow1(target){
			$.messager.confirm('Confirm','Are you sure?',function(r){
				if (r){
					var rowIndex = getRowIndex(target);
					$('#tt1').datagrid('selectRow',rowIndex);
	       			var row = $('#tt1').datagrid('getSelected');
	       			if(row.ID!=0){
						funOne('#tt1','${pageContext.request.contextPath}/numElevenBranchOne.htm/deleteRow',rowIndex);	
					}				
					$('#tt1').datagrid('deleteRow', rowIndex);	  
				}else{
					alert("删除失败，请重新操作.");
				}
			});
		}
		function saverow1(target){
			var rowIndex = getRowIndex(target);
			$('#tt1').datagrid('endEdit', rowIndex);	   
			var para = $('#dialog').panel('options').title;  
			para = para.split(':')[1];		     	
	       	funOne('#tt1', '${pageContext.request.contextPath}/numElevenBranchOne.htm/saveRow', rowIndex, para);
	       
		}
		function cancelrow1(target){
			$('#tt1').datagrid('selectRow',getRowIndex(target));
			$('#tt1').datagrid('cancelEdit', getRowIndex(target));
		}
		function dlginsert(){
			var row = $('#tt1').datagrid('getSelected');
			if (row){
				var index = $('#tt1').datagrid('getRowIndex', row);
			} else {
				index = 0;
			}
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/bomTree.htm/getDataMsg',
				data:{materialNum:$("#textbox3").textbox('getValue')}, 
				traditional: true,
				dataType:"json", 
				beforeSend: function () {
					load();
				},
				complete: function () {
					disLoad();
				},
				success: function(result) {	
					$('#tt1').datagrid('insertRow', {
						index: index,
						row:{
							materialNum:$("#textbox3").textbox('getValue'),
							materialPlanQuantity:$("#textbox4").textbox('getValue'),
							materialRealQuantity:'0',
							ID:0,
							parentID:0
						}
					});
					$('#tt1').datagrid('selectRow',index);
					$('#tt1').datagrid('beginEdit',index);
				}, 
				error : function() {
			       	alert("异常！");
			  	}
			});		
		}
		$(".more").click(function(){
            $(this).closest(".conditions").siblings().toggleClass("hide");
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
                            }
                        }); 
                        var columns = dg.datagrid('getColumnFields');								
						if(columns.indexOf("workNum")>-1){
							pager.pagination({
	                            buttons: [{
									iconCls:'icon-add',
									handler:function(){insert();}
									},'-',{
									iconCls:'icon-arrow-inout',
									handler:function(){
										var oDiv = document.getElementById("ttdiv");						
										if (oDiv.style.display == "none"){
									      	oDiv.style.display = "block";
									    }else {
									       	oDiv.style.display = "none";
									    }
									}
									},'-',{
									iconCls:'icon-arrow-refresh',
									handler:function(){
										$('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/numEleven.htm/loadData')}).datagrid('clientPaging');              
									}
								}]
	                        }); 
						}else{
							pager.pagination({
	                            buttons: [{								
									iconCls:'icon-arrow-inout',
									handler:function(){
										var oDiv = document.getElementById("tt1div");									
										if (oDiv.style.display == "none"){
									      	oDiv.style.display = "block";
									    }else {
									       	oDiv.style.display = "none";
									    }
									}
									},'-',{
									iconCls:'icon-arrow-refresh',
									handler:function(){	   
										var para = $('#dialog').panel('options').title;  
										para = para.split(':')[1];	
										getData('#tt1', '${pageContext.request.contextPath}/numElevenBranchOne.htm/loadData' + '?para=' + para);
										getData('#tt2', '${pageContext.request.contextPath}/numEight.htm/loadData' + '?para=' + para);	
									}
								}]
	                        }); 
						}
                        
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
  	/*	$.fn.datetimebox.defaults.formatter = function(value){
 			var date = new Date(value);
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			var HH = date.getHours();
			var mm = date.getMinutes();
			var ss = date.getSeconds();	
			return y +'-'+ m +'-'+ d + ' ' + HH + ':' + mm + ':' + ss;
		} 
	 	$.fn.datebox.defaults.formatter = function(value){
	 		var date = new Date(value);
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+m+'-'+d;
		}*/
		function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		}
		function myparser(s){
			if (!s) return new Date();
			var ss = (s.split('-'));
			var y = parseInt(ss[0],10);
			var m = parseInt(ss[1],10);
			var d = parseInt(ss[2],10);
			if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
				return new Date(y,m-1,d);
			} else {
				return new Date();
			}
		}
		function funOne(tt,url,rowIndex,para){	 
	       	$(tt).datagrid('selectRow',rowIndex);
	       	var row = $(tt).datagrid('getSelected');
	      	//	$('#dlg1').dialog.option.title;
	       	//	row.ordernum="para";
        	//也可以如下添加新的属性并赋值
        	row["para"] = para;
	       	//alert(JSON.stringify(row));
			$.ajax({  
				type: "post",  
				url: url,
				data:row, 
				traditional: true,
				dataType:"json", 
				beforeSend: function () {
					load();
				},
				complete: function () {
					disLoad();
				},
				success: function(result) {	
				//alert('adf123');
				//alert(result.length);		
					if(result.length != 0){	
						$(tt).datagrid('updateRow',{
							index: rowIndex,
							row: {
								ID: result[0].ID,
								parentID:result[0].parentID
							}
						});	
					}							
				}, 
				error : function() {
			       	alert("异常！");
			  	}
			});
		}
		
		function CheckDateTime(str){
			var reg = /^(\d+)-(\d{1,2})-(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
			var r = str.match(reg);
			if(r==null)return false;
			r[2]=r[2]-1;
			var d= new Date(r[1], r[2],r[3], r[4],r[5], r[6]);
			if(d.getFullYear()!=r[1])return false;
			if(d.getMonth()!=r[2])return false;
			if(d.getDate()!=r[3])return false;
			if(d.getHours()!=r[4])return false;
			if(d.getMinutes()!=r[5])return false;
			if(d.getSeconds()!=r[6])return false;
			return true;
		}
		
		function IsDate(mystring) {
			var reg = /^(\d{4})-(\d{2})-(\d{2})$/;
			var str = mystring;
			var arr = reg.exec(str);
			if (str=="") return false;
			//alert(mystring + ":" + RegExp.$2 + ":" + RegExp.$3);
			if (!reg.test(str)&&RegExp.$2<=12&&RegExp.$3<=31){			
				return true;
			}
			return false;
		}
 	</script>
</head>


<body>

	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
 		<div id="tb" style="padding:0 10px;height:auto">	
			<div id="ttdiv" style="width:100%; display:none">			 	  
				<span class="con-span">从: </span><input id="textbox5" class="easyui-datebox" validtype="date" style="width:130px;height:25px;line-height:25px;">			
				<span class="con-span">到: </span><input id="textbox6" class="easyui-datebox" validtype="date" style="width:130px;height:25px;line-height:25px;">   		
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:false,plain:true" style="height:25px;" onclick="betweendatefind()">查询</a>
				<input id="ss"></input>	
<!-- 			<input class="easyui-searchbox" data-options="prompt:'输入采购单号查找'" style="width:150px"></input></td> -->
<!-- 			<a href="#" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:false" onclick="getIndex()">查询</a> -->
			</div>
		</div> 
		<table id="tt"></table> 
	</div>		
	<div id="dialog"  class="easyui-dialog" style="width:80%;height:80%;padding:0px" data-options="
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
					<!--<span class="con-span"><input id="textbox3"  class="easyui-textbox" type="text" name="code" style="width:120px;height:25px;line-height:25px;"></input></span> -->
						<span class="con-span"><select class="easyui-combobox" id="textbox3" style="height:25px;width:120px;"></select></span>					
						<span class="con-span">数量:</span>
						<span class="con-span"><input id="textbox4"  class="easyui-numberbox" type="text" name="code" style="width:120px;height:25px;line-height:25px;"></input></span>
						<span class="con-span"><a href="#" class="easyui-linkbutton" style="height:25px;" data-options="iconCls:'icon-add', plain:true" onclick="dlginsert()">新增</a></span>
						<span class="con-span"><a href="#" class="easyui-linkbutton" style="height:25px;" data-options="iconCls:'icon-table-add', plain:true" onclick="orderImport()">导入表格</a></span>
						<span class="con-span"><a href="#" class="easyui-linkbutton" style="height:25px;" data-options="iconCls:'icon-table-go', plain:true" onclick="exportExcel()">导出表格</a></span>
			        </div>
				</div>	
				<table id="tt1"></table>				
			</div>
			<div data-options="region:'south',title:'South Title',split:true" style="height:100px;padding:5px;background:#eee;">
				<table id="tt2"></table> 	
			</div>	
		</div>	
	</div>
</body>
</html>


