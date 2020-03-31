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
    <script type="text/javascript" src="../../../js/myjs/datagrid-export.js"></script>
    <script type="text/javascript" src="../../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../../custom/easyui-lang-zh_CN.js"></script>

</head>


<body>
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
<!--  		<div id="tb" style="padding:0 10px;height:auto">	
			<div id="ttdiv" style="width:100%;  height:50px;">
				<div style="margin-top:5px; margin-left:10px; float:left">	
					<input class="easyui-combobox" id="userId" style="width:200px;height:35px;"></input>		        	
				</div>
			</div>		   
		</div>  --> 
		<table id="tt"></table> 
	</div>
	<div id="dialog"  class="easyui-dialog" style="width:90%;height:90%;padding:0px" data-options="
			closed:true,
			resizable:true,	
			modal:true,	
			onResize:function(){
				$(this).dialog('center');
			}">
		
	</div> 
	<script>
		$(function(){
			$('#tt').datagrid({
				width:'100%',
				height:'100%',
				//toolbar:'#tb',
				singleSelect:true,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				pagination:true,
				rownumbers:true,
				columns:[[
 					{field:'ck', checkbox:true},
 					{field:'action1',title:'OP',width:10,align:'center',
						formatter:function(value,row,index){	
							if (row.editing){	
								return '';
							}else{
								var a = '<a href="#" onclick="spreadrow(this)" style = "color: #1da02b;text-decoration:none"><i class="iconfont">&#xe60a;</i></a> ';									
								return a;
							}
						}
					},							
					{field:'customer',title:'客户',align:'center',width:40,editor:'textbox'},								
					{field:'supplierCode',title:'供应商代码',align:'center',width:50,editor:'textbox'},								
					{field:'date',title:'时间日期',width:40,align:'center', editor:{type:'datebox',options:{required:true}}},   					
					{field:'deliveryNum',title:'送货单号',align:'center',width:30,editor:{type:'textbox',options:{required:true}}},		
/* 					{field:'status',title:'状态',align:'center',width:20,
						formatter:function(value,row,index){ 	
			    	 		if(value != '完成'){
								return  '<span style="color:red">'+value+'</span>';
							}
			      		}  
					},	 */
					{field:'remark',title:'备注',align:'center',width:20,editor:'textbox'},
					/* {field:'action',title:'操作',width:20,align:'center',
						formatter:function(value,row,index){
							if(isOnlyRead()==false){
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
						}
					},				 */	
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
				url: '${pageContext.request.contextPath}/deliveryWeldNum.htm/loadData',
				//data:{orderNumID:window.parent.window.getOrderNumID()},
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

         
		function isOnlyRead(){
			var str = localStorage.getItem('authority1'); 			
	        str = str.substr(5, 1)
			if(str.charAt(0)==1){	
	        	return true;					        			
	        }else{
	        	return false;
	        }
		}
	
		function inputEnter() {
			var data = {}	
			data["name"] = $("#userId").textbox("getValue");
			$.ajax({
				type : "post",
				url : '${pageContext.request.contextPath}/deliveryWeldNum.htm/searchDeliveryNum',
				data : data,
				traditional : true,
				dataType : "json",
				beforeSend : function() {
					load();
				},
				complete : function() {
					disLoad();
				},
				success : function(result) {
					//alert(JSON.stringify(result));
					$('#tt').datagrid('loadData',result);	
					$("#userId").textbox("setValue", "");
			
				},
				error : function() {
					alert("异常！");
				}
			});
		}

	
		$('#userId').combobox({
			prompt : '请输入送货单号', //提示信息
			//required:true, 	//是否必填
			mode : 'remote', //动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
			url : '${pageContext.request.contextPath}/deliveryWeldNum.htm/autotimp', //请求数据路径				
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
				//record["quantity"] = $("#quantityId").numberbox("getValue");
				//alert(JSON.stringify(record));
				$.ajax({
						type : "post",
						url : '${pageContext.request.contextPath}/deliveryWeldNum.htm/searchDeliveryNum',
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
							//alert(JSON.stringify(result));
							if (result[0].hasOwnProperty('error')) {
								//alert(result[0].error);
								$.messager.show({
									title : '提示信息',
									msg : result[0].error,
									showType : 'show',
									timeout : 0,
									style : {
										right : '',
										top : document.body.scrollTop + document.documentElement.scrollTop,
										bottom : ''
									}
								});
							} else {
								$('#tt').datagrid('loadData',result);	
								$("#userId").textbox("setValue", "");
							}
						},
						error : function() {
							alert("异常！");
						}
					});
	
			},
	
			inputEvents : $.extend({}, $.fn.combobox.defaults.inputEvents, {
				keyup : function(event) {
					if (event.keyCode == 13) {
						//var value = $('#userId').combobox('getValue'); 
						//alert(value);
						inputEnter();
						$('#userId').combobox("hidePanel");
						$('#userId').combobox('setValue', '');
						$("#quantityId").textbox().next('span').find('input').focus();
					}
				}
			})
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
										handler:function(){insert();}
									},'-',{		
									iconCls:'icon-search',
										handler:function(){search();}
									},'-',{										
									iconCls:'icon-arrow-refresh',
									handler:function(){	   
										$.ajax({  
											type: "post",  
											url: '${pageContext.request.contextPath}/deliveryWeldNum.htm/loadData',
											//data:{orderNumID:window.parent.window.getOrderNumID()},
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
				//alert(JSON.stringify($('#tt').datagrid('getData')));
					var rowIndex = getRowIndex(target);	
					$('#tt').datagrid('selectRow',rowIndex);
	       			var row = $('#tt').datagrid('getSelected');
	       			//alert(row.idc)
	       			if(row.idc!=0){
	       				//var userList = new Array();  
	       				//userList.push({idc:row.idc});
	       				$.ajax({  
							type: "post",  
							url: '${pageContext.request.contextPath}/deliveryWeldNum.htm/deleteRow',
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
						
					}else{
						$('#tt').datagrid('deleteRow', rowIndex);		
					}		
					
					//funOne('#tt','${pageContext.request.contextPath}/weiwaiNum.htm/deleteRow',rowIndex,JSON.stringify(userList),'deleterow');			
				}
			});
		}
		
		function saverow(target){
			var rowIndex = getRowIndex(target);
			$('#tt').datagrid('endEdit', rowIndex);				
			$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');	      
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/deliveryWeldNum.htm/getEntity',
				traditional: true,
				dataType:"json", 
				//contentType : 'application/json;charset=utf-8', //设置请求头信息  
				success: function(result) {						
					var userList = new Array();  
					userList.push(row);
					for(var key in row){
						if(!result[0].hasOwnProperty(key)){	
							delete row[key];
						}else{
							if(row[key] == undefined){
								delete row[key];
							}
						}				
					}			
					$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/deliveryWeldNum.htm/saveRow',
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
							if(result.length>0){					
								$('#tt').datagrid('updateRow',{
									index: rowIndex,
									row: {
										idc: result[0].idc,
									}
								});		
							}					
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});
		    	},	
		    	error : function() {
					alert("entity异常！");
				}
			});			
		}
		function cancelrow(target){
			$('#tt').datagrid('selectRow',getRowIndex(target));
			$('#tt').datagrid('cancelEdit', getRowIndex(target));
		}
		
		function insert(target){
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
						customer:'蒂森扶梯公司',								
						supplierCode:'100096',
						idc:0				
					}
				});
				$('#tt').datagrid('selectRow',index);	
				$('#tt').datagrid('beginEdit',index); 
			}	
		}
		
		function spreadrow(target){
        	var rowIndex = getRowIndex(target);
        	$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');
	        $("#dialog").dialog({
			     title: row.deliveryNum,
			     queryParams: {deliveryNumID: row.idc,index:rowIndex},		
			     content:"<iframe scrolling='auto' frameborder='0' src='./pf_bd_tables4_3_5_0.jsp' style='width:100%; height:100%; display:block;'></iframe>"
			});	
			$("#dialog").dialog("open"); // 打开dialog     	     	
        }
        
        $("#dialog").dialog({
			onClose : function() {
				//alert(result);		
				var deliveryNumID = getDeliveryNumID('#dialog');
				if(deliveryNumID == -1){
					return;
				}
				$.ajax({
					type : "post",
					url : '${pageContext.request.contextPath}/deliveryWeldNum.htm/changeStatus',
					data : {
						deliveryNumID : deliveryNumID
					},
					traditional : true,
					dataType : "json",
					//contentType : 'application/json;charset=utf-8', //设置请求头信息  
					beforeSend : function() {
						load();
					},
					complete : function() {
						disLoad();
					},
					success : function(result) {
						//alert(JSON.stringify(result));	
						if (result.length == 1) {
							$('#tt').datagrid('updateRow', {
								index : getDlgParaRowIndex('#dialog'),
								row : {
									status : result[0].status									
								}
							});
						}
					},
					error : function() {
						alert("异常！");
					}
				});
			}
		});
        
        function search(){
   
	        $("#dialog").dialog({
			     title: '焊接仓出库单查找',
			     queryParams: {deliveryNumID: -1},	
			     content:"<iframe scrolling='auto' frameborder='0' src='./pf_bd_tables4_3_5_1.jsp' style='width:100%; height:100%; display:block;'></iframe>"
			});	
			$("#dialog").dialog("open"); // 打开dialog  
        }
        
        function getDeliveryNumID(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["deliveryNumID"];
		}
		function getDlgParaRowIndex(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["index"];		
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
 
</body>
</html>


