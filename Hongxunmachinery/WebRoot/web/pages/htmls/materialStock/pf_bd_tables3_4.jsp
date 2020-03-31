<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>    
   
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title></title> 
    <link href="../../css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="../../../custom/uimaker/easyui.css">
	<link rel="stylesheet" type="text/css" href="../../../custom/uimaker/icon.css">    
    <link href="../../css/basic_info.css" rel="stylesheet">
 	<script type="text/javascript" src="../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../../custom/easyui-lang-zh_CN.js"></script>
	<script>
		var products = [
		    {productid:'已下单',name:'已下单'},
		    {productid:'已发料',name:'已发料'},
		    {productid:'已交付',name:'已交付'}
		];
			
		$(function(){
			$('#tt').datagrid({
				//title:'Editable DataGrid',
				//iconCls:'icon-edit',
				width:'100%',
				height:'auto',	
				toolbar:'#tb',
				singleSelect:true,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				pagination:true,
				rownumbers:true,	
				//url:'${pageContext.request.contextPath}/numSeven.htm/loadData',
				columns:[[
 					{field:'ck', checkbox:true},
 					{field:'action1',title:'OP',width:80,align:'center',
						formatter:function(value,row,index){	
							if (row.editing){	
								return '';
							}else{
								var a = '<a href="#" onclick="spreadrow(this)" style = "color: #1da02b;text-decoration:none"><i class="iconfont">&#xe60a;</i></a> ';	
								//var b = '<a href="#" onclick="insert(this)" style = "color: #1da02b;text-decoration:none"><i class="iconfont">&#xe663;</i></a>';	
								return a;
							}
						}
					},					
  					
					//{field:'productMaterialNum',title:'物料号',width:100,align:'left',editor:'text'},
					//{field:'productName',title:'产品名称',width:100,align:'left',editor:'text'},
					//{field:'outsourcingCrafts',title:'加工工艺',width:100,align:'left',editor:'text'},				
					//{field:'outsourcingPlanQuantity',title:'委外数量',width:60,align:'left',sortable: true,editor:'numberbox'},													
					//{field:'outsourcingBackDate',title:'实到日期',width:80,align:'left',sortable: true},
					{field:'outsourcingNum',title:'外协单号',width:100,align:'left',editor:'text'},
					{field:'outsourcingItemsQuantity',title:'项目数',width:60,align:'left',sortable: true,editor:'numberbox'},	
					{field:'outsourcingDate',title:'委外日期',width:80,align:'left',sortable: true,editor:{type:'datetimebox',options:{required:true}}},
					
					{field:'supplier',title:'委外供应商',width:100,align:'left',editor:'text'},	
					{field:'outsourcingAgent',title:'下单员',width:100,align:'left',editor:'text'},
					{field:'outsourcingPayDate',title:'付款日期',width:80,align:'left',sortable: true,editor: {type:'datetimebox',options:{required:false}},hidden:true},
					{field:'outsourcingOrderAmount',title:'委外总金额',width:80,align:'left',editor:{type:'numberbox',options:{precision:3,required:false}}}, 								
					{field:'status',title:'委外状态',width:70,align:'left',
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
					{field:'remark',title:'备注',width:80,align:'left',editor:'text'},
					{field:'action',title:'操作',width:80,align:'left',
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
					{field:'ID',title:'ID',width:10,editor:'numberbox',hidden:'true'}
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
			
/*  if (val < 20){
        return '<span style="color:red;">('+val+')</span>';
    } else {
        return val;
    } */
    					/*
					{field:'materialNum',title:'物料编码',align:'left',width:60},
					{field:'materialName',title:'物料名称',align:'left',width:60},	
					{field:'materialModel',title:'型号',align:'left',width:60},  
					{field:'materialSpecification',title:'规格',align:'left',width:60},									
					{field:'quantity',title:'数量',align:'left',width:60},	
					{field:'materialsupplier',title:'供应商',align:'left',width:60},*/
    
			$('#tt1').datagrid({
				width:'100%',
				height:'auto',
				//toolbar:'#tt1tb',
				singleSelect:true,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				pagination:true,
				rownumbers:true,				
				//url:'${pageContext.request.contextPath}/numSevenBranchOne.htm/loadData',
				columns:[[
 					{field:'ck', checkbox:true},	
 					{field:'productNum',title:'产品编号',width:100,align:'left',editor:'text'},
					{field:'productName',title:'产品名称',width:100,align:'left',editor:'text'},
					{field:'outsourcingCrafts',title:'加工工艺',width:100,align:'left',editor:'text'},					
					{field:'outsourcingPlanQuantity',title:'委外数量',width:60,align:'left',sortable: true,editor:'numberbox'},								
					{field:'price',title:'单价',align:'left',width:60,editor:{type:'numberbox',options:{precision:3,required:false}}},  
					{field:'money',title:'金额',align:'left',width:60,editor:{type:'numberbox',options:{precision:3,required:false}}},  								
					{field:'outsourcingPlanDate',title:'交付日期',width:80,align:'left',sortable: true,editor: {type:'datebox',options:{required:false}}},
					{field:'outsourcingBackDate',title:'实到日期',width:80,align:'left',sortable: true},
 					{field:'outsourcingBackQuantity',title:'实到数量',width:60,align:'left',sortable: true},					
					{field:'productUnit',title:'单位',align:'left',width:60,editor:'text',hidden:'true'},  
					{field:'remark',title:'备注',align:'left',width:80,editor:'text'},
					{field:'action',title:'操作',width:80,align:'left',
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
					/*{field:'action1',title:'Action',width:80,align:'left',
						formatter:function(value,row,index){
							if (row.editing){							
								var c = '';
								return c;
							} else {		
								var a = '<a href="#" onclick="spreadrow1(this)"></a> ';				
								return a;
							}
						}
					},*/
					{field:'ID',title:'ID',width:20,hidden:'true'}
					//{field:'parentID',title:'parentID',width:20,hidden:'true'}
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
				iconCls:'icon-edit',
				width:'100%',
				height:'auto',
				toolbar:'#tt2tb',
				singleSelect:true,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				pagination:true,
				rownumbers:true,				
				//url:'${pageContext.request.contextPath}/numThreeBranchOne.htm/loadData',
				columns:[[
 					{field:'ck', checkbox:true},
 					//{field:'stockoutNum',title:'领料单号',align:'center',width:60},			
					{field:'materialNum',title:'物料编码',align:'center',width:60},
					{field:'materialName',title:'物料名称',align:'center',width:60},	
					{field:'materialModel',title:'型号',align:'center',width:60,hidden:'true'},  
					{field:'materialSpecification',title:'型号/规格',align:'center',width:60},									
					{field:'quantity',title:'数量',align:'center',width:60},			
					{field:'length',title:'长',width:60,align:'center',sortable: false,editor:{type:'numberbox',options:{precision:2,required:false}},hidden:'true'},
					{field:'width',title:'宽',width:60,align:'center',sortable: false,editor:{type:'numberbox',options:{precision:2,required:false}},hidden:'true'},	
					{field:'materialUnit',title:'单位',align:'center',width:60},  
					//{field:'materialPrice',title:'单价',align:'center',width:60,editor:{type:'numberbox',options:{precision:3,required:false}}},  
					//{field:'materialMoney',title:'金额',align:'center',width:60,editor:{type:'numberbox',options:{precision:3,required:false}}},  
					{field:'materialsupplier',title:'供应商',align:'center',width:60},	
					{field:'remark',title:'备注',align:'center',width:80,editor:'text'},
					{field:'action',title:'操作',width:80,align:'center',
						formatter:function(value,row,index){
							if (row.editing){							
								var s = '<a href="#" onclick="saverow2(this)">保存</a> ';
								var c = '<a href="#" onclick="cancelrow2(this)">取消</a>';
								return s+c;
							} else {					
								var e = '<a href="#" onclick="editrow2(this)">编辑</a> ';
								var d = '<a href="#" onclick="deleterow2(this)">返回</a>';
								return e+d;
							}
						}
					},
					/*{field:'action1',title:'Action',width:80,align:'center',
						formatter:function(value,row,index){
							if (row.editing){							
								var c = '';
								return c;
							} else {		
								var a = '<a href="#" onclick="spreadrow1(this)"></a> ';				
								return a;
							}
						}
					},*/
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
		});
		
	</script>
	
	<script>	

		function getRowIndex(target){
			var tr = $(target).closest('tr.datagrid-row');
			return parseInt(tr.attr('datagrid-row-index'));
		}
		function editrow(target){
			$('#tt').datagrid('selectRow', getRowIndex(target));	
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
						funOne('#tt','${pageContext.request.contextPath}/numSeven.htm/deleteRow',rowIndex);		
					}
					$('#tt').datagrid('deleteRow', rowIndex);	
								
				}else{
					alert('异常');
				}
			});
		}
		function saverow(target){
			var rowIndex = getRowIndex(target);
			$('#tt').datagrid('endEdit', rowIndex);				
			 	//$(tt).datagrid('selectRow',rowIndex);
	       		//var row = $(tt).datagrid('getSelected');
			//$('#tt').datagrid('updateRow', rowIndex, row);	  			     		       	
	       	funOne('#tt','${pageContext.request.contextPath}/numSeven.htm/saveRow',rowIndex);	       	
			//$('#tt').datagrid('selectRow', rowIndex);	
					
		}
		function cancelrow(target){
			$('#tt').datagrid('selectRow', getRowIndex(target));	
			$('#tt').datagrid('cancelEdit', getRowIndex(target));
		}			
		
		function insert(target){
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
					//outsourcingNum: $("#textbox1").textbox('getValue'),
					//productMaterialNum:	$("#textbox2").textbox('getValue'),
					ID:0		
				}
			});
			$('#tt1').datagrid('selectRow',index);
			$('#tt').datagrid('beginEdit',index); 		
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
					//alert(JSON.stringify(result));
					if(result.length==0){
						$(tt).datagrid('loadData', [{}])	
						$(tt).datagrid('deleteRow', 0);	 
					}else{
						$(tt).datagrid('loadData', result);
					}	
				},
				error : function() {
			       	alert("异常");
			   	}
			});	
        }
        $(function(){				
            //$('#tt').datagrid('clientPaging'); 
            $('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/numSeven.htm/loadData')}).datagrid('clientPaging');   
            $('#tt1').datagrid('clientPaging');   
            $('#tt2').datagrid('clientPaging');  
            $('#ss').searchbox({
            	//height:'40px',
			    searcher:function(value,name){
			    	getData('#tt','${pageContext.request.contextPath}/numSeven.htm/searchNum' + '?num=' + value);
			    },
			    prompt:'外协单号'
			});         
        });  
        
        function betweendatefind(){
			 var date1 = $("#textbox5").textbox('getValue');
			 var date2 = $("#textbox6").textbox('getValue');

				$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/numOne.htm/betweenDateFind',
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
     
        function spreadrow(target){
        	$('#tt').datagrid('selectRow',getRowIndex(target));
	       	var row = $('#tt').datagrid('getSelected');
	       	//alert(row.purchaseOrderNum);
			if($("#dlg1").parent().is(":hidden")){
				$('#dlg1').dialog('open').dialog('setTitle','委个单号 :' + row.outsourcingNum);		 	
			 	getData('#tt1', '${pageContext.request.contextPath}/numSevenBranchOne.htm/loadData' + '?para=' + row.outsourcingNum);			
			}else{
				alert('窗口已打开');
			}	
        }
       	
		function betweendatefind(){
			 var date1 = $("#textbox5").textbox('getValue');
			 var date2 = $("#textbox6").textbox('getValue');
				$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/numSeven.htm/betweenDateFind',
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
				       	alert("异常");
				  	}
				});
			
		}
		function getIndex(){
			var row = $('#tt').datagrid('getSelected');		
			var index = $('#tt').datagrid('getRowIndex', row);	
			alert("index:" + index);	
		}
	</script>
	
	<script>
/* 		function getRowIndex1(target){
			var tr = $(target).closest('tr.datagrid-row');
			return parseInt(tr.attr('datagrid-row-index'));
		} */
		function editrow2(target){
			$('#tt2').datagrid('selectRow', getRowIndex(target));	
			$('#tt2').datagrid('beginEdit', getRowIndex(target));
		}
		function deleterow2(target){
			$.messager.confirm('Confirm','Are you sure?',function(r){
				if (r){
					var rowIndex = getRowIndex(target);
					var para = $('#dlg1').panel('options').title;  
					para = para.split(':')[1];
					//alert(para) 
					$('#tt2').datagrid('selectRow',rowIndex);
	       			var row = $('#tt2').datagrid('getSelected');
					if(row.ID!=0){
						funOne('#tt2','${pageContext.request.contextPath}/numSevenBranchOne.htm/deleteRow',rowIndex,para);				
					}
					
					$('#tt2').datagrid('deleteRow', rowIndex);
					  
				}else{
					alert("异常！")
				}
			});
		}
		function saverow2(target){
			//$('#tt1').datagrid('endEdit', getRowIndex(target));
			var rowIndex = getRowIndex(target);
			$('#tt2').datagrid('endEdit', rowIndex);
			var para = $('#dlg1').panel('options').title;  
			para = para.split(':')[1];
			//alert(para)  		       	
	       	funOne('#tt2', '${pageContext.request.contextPath}/numSevenBranchOne.htm/saveRow', rowIndex, para);
	       //funOne('#tt1','${pageContext.request.contextPath}/numSevenBranchOne.htm/saveRow',rowIndex);
	       
		}
		function cancelrow2(target){
			$('#tt2').datagrid('selectRow', getRowIndex(target));	
			$('#tt2').datagrid('cancelEdit', getRowIndex(target));
		}
		function dlginsert(){
			var row = $('#tt2').datagrid('getSelected');
			if (row){
				var index = $('#tt2').datagrid('getRowIndex', row);
			} else {
				index = 0;
			}
			$('#tt2').datagrid('insertRow', {
							index: index,
							row:{
								//stockoutNum:stockoutNum,
								//materialNum:$("#textbox3").textbox('getValue'),
								//quantity:$("#numberbox4").textbox('getValue'),	 							 
								ID:0,
								parentID:0
							}
						});
			$('#tt2').datagrid('selectRow',index);
			$('#tt2').datagrid('beginEdit',index);
/* 			var para = $('#dlg1').panel('options').title;  
			var stockoutNum = para.split(':')[1];
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/numSevenBranchOne.htm/getDataTwoMsg',
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
				//alert(JSON.stringify(result));
					if(result.length>0){
						$('#tt1').datagrid('insertRow', {
							index: index,
							row:{
								//stockoutNum:stockoutNum,
								materialNum:$("#textbox3").textbox('getValue'),
								quantity:$("#numberbox4").textbox('getValue'),
	 							materialName:result[0].materialName,
								materialUnit:result[0].materialUnit,
								materialsupplier:result[0].materialsupplier,
								materialModel:result[0].materialModel,
								materialSpecification:result[0].materialSpecification, 
								ID:0,
								parentID:0
							}
						});
						$('#tt1').datagrid('selectRow',index);
						$('#tt1').datagrid('beginEdit',index);	
					}else{
						alert("库存无此料号信息或数量不够");
					}			
				}, 
				error : function() {
			       	alert("异常");
			  	}
			});	 */
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
                            }
                        });
                        var columns = dg.datagrid('getColumnFields');
                        if(columns.indexOf("outsourcingNum")>-1){
	                        pager.pagination({
	                            buttons: [{
										/*iconCls:'icon-add',
										handler:function(){	
											insert();
										}
									},'-',{*/
										iconCls:'icon-arrow-inout',
										handler:function(){
											var	oDiv = document.getElementById("ttdiv");
											if (oDiv.style.display == "none"){
										      	oDiv.style.display = "block";
										    }else {
										       	oDiv.style.display = "none";
										    }
										}
									},'-',{
										iconCls:'icon-arrow-refresh',
										handler:function(){
											$('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/numSeven.htm/loadData')}).datagrid('clientPaging');   
										}	
								}]
                        	});
                        }else if(columns.indexOf("productNum")>-1){
                        	pager.pagination({
	                         	buttons: [{
								/*		iconCls:'icon-add',
										handler:function(){	
											dlginsert();
										}
						
									},'-',{*/
									iconCls:'icon-arrow-refresh',
									handler:function(){
										var para = $('#dlg1').panel('options').title;  
										para = para.split(':')[1];
										getData('#tt1', '${pageContext.request.contextPath}/numSevenBranchOne.htm/loadData' + '?para=' + para);			
									}
								}]
                        	});
                        }else{
                        	pager.pagination({
	                         	buttons: [{
										iconCls:'icon-arrow-inout',
										handler:function(){
											var	oDiv = document.getElementById("tt2div");
											if (oDiv.style.display == "none"){
										      	oDiv.style.display = "block";
										    }else {
										       	oDiv.style.display = "none";
										    }
										}
						
									},'-',{
									iconCls:'icon-arrow-refresh',
									handler:function(){
										var para = $('#dlg1').panel('options').title;  
										para = para.split(':')[1];
										getData('#tt2', '${pageContext.request.contextPath}/numSevenBranchOne.htm/loadData' + '?para=' + para);			
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
                    return $(target).datebox('getValue');//è·å¾æ§å¼
                },
                setValue: function (target, value) {
                    console.info(value);
                    $(target).datebox('setValue', value);//è®¾ç½®æ°å¼çæ¥ææ ¼å¼
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
   			var columns = $(tt).datagrid('getColumnFields');
            if(columns.indexOf("productNum")>-1){
        		row["para"] = para;
        		if(row["outsourcingBackDate"] ==undefined){
        			row["outsourcingBackDate"]="";
        		} 
        		if(row["outsourcingBackQuantity"] ==undefined){
        			row["outsourcingBackQuantity"]=""
        		} 
        	}
        	
	       	//alert(row.purchaseDeliveryDate);
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
					if(result.length != 0){	
						alert(result[0].ID);
						$(tt).datagrid('updateRow',{
							index: rowIndex,
							row: {
								ID: result[0].ID,
								//parentID: result[0].parentID
							}
						});	
					}else{
					
					}							
				}, 
				error : function() {
			       	alert("异常");
			  	}
			});
		}
		
		function materialNumfind(){
			var materialNum = $("#textbox3").textbox('getValue');
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/numSevenBranchOne.htm/materialNumFind',
				data:{materialNum:materialNum}, 
				traditional: true,
				dataType:"json", 
				beforeSend: function () {
					load();
				},
				complete: function () {
					disLoad();
				},
				success: function(result) {			
					alert(JSON.stringify(result));	
					$('#dlg2').dialog('open').dialog('setTitle','物料编码:' + materialNum);	
	
				}, 
				error : function() {
			       	alert("异常");
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
			alert(mystring + ":" + RegExp.$2 + ":" + RegExp.$3);
			if (!reg.test(str)&&RegExp.$2<=12&&RegExp.$3<=31){			
				return true;
			}
			return false;
		}
	</script>
</head>
<body>

	<div class="easyui-layout"  data-options="fit:true">	    
		<div id="center" data-options="region:'center', border:true,  split:true" style="padding:5px;background:#eee;">	  	
			<div id="tb" style="padding:0 10px;height:auto">
				<div id="ttdiv" style="width:100%; display:none">
					<span class="con-span">从:</span>		
					<input id="textbox5" class="easyui-datebox" validtype="date" style="width:130px;height:25px;">
					<span class="con-span">到:</span>
					<input id="textbox6" class="easyui-datebox" validtype="date" style="width:130px;height:25px;">   													
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" style="height:25px;" plain="true" onclick="betweendatefind()">查询</a>							
					<input id="ss"></input>
			   	</div>
			</div>  	
			<table id="tt"></table>
		</div>
	</div>	
	
	
<!-- max-width:800px; -->
 	<div id="dlg1" class="easyui-dialog" style="width:80%;height:80%;padding:0px" data-options="
			closed:true,
			resizable:true,
			onResize:function(){
				$(this).dialog('center');
			}">
		<div class="easyui-layout"  data-options="fit:true">	    
			<div id="center" data-options="region:'center', border:true,  split:true" style="padding:5px;background:#eee;">	 			
 				<div id="tt2tb" style="padding:0 10px;height:auto">
					<div id="tt2div" style="width:100%; display:none">		
						<span class="con-span">物料编码: </span>
						<span class="con-span"><select class="easyui-combobox" id="textbox3" style="height:25px;width:130px;"></select></span>
						<!-- <span class="con-span"><input id="textbox3"  class="easyui-textbox" type="text" name="code" style="width:130px;height:25px;line-height:25px;"></input></span> -->
						<span class="con-span">数量: </span>
						<span class="con-span"><input id="numberbox4"  class="easyui-numberbox" type="text" name="code" style="width:130px;height:25px;line-height:25px;"></input></span>
						<span class="con-span"><a href="#" class="easyui-linkbutton" iconCls="icon-add" style="height:25px;" plain="true" onclick="dlginsert()">新增</a></span>
						<span class="con-span"><a href="#" class="easyui-linkbutton" iconCls="icon-search" style="height:25px;" plain="true" data-options="selected:false" onclick="materialNumfind()">查询</a>
					</div>
				</div>
				<table id="tt2"></table>
			</div>
			<div data-options="region:'south',title:'委外产品详细',split:true" style="height:100px;padding:5px;background:#eee;">
				<table id="tt1"></table> 	
			</div>
		</div>		
	</div>
	
<!-- 	<div id="dlg1" class="easyui-dialog" closed="true">
		<table id="tt1"></table>
	</div>
	<script>
		$("#dia1").dialog({
	     	width:800,
	     	height:500,
	     	modal:true,
	     	content:"<iframe scrolling='auto' frameborder='0' src='pf_bd_tables1_11.html.html' style='width:100%; height:100%; display:block;'></iframe>"
     	});
	</script> -->
	
</body>
</html>


