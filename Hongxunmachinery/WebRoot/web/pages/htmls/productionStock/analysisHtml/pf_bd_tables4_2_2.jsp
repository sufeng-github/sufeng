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
    <title></title> 
    
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
    <script type="text/javascript" src="../../../js/myjs/jquery.form.js"></script> 
</head>

<body>  
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">
	  	<div id="tb" style="padding:0 10px;height:auto">	
			<div id="ttdiv" style="width:100%; height:50px;">	
<!-- 				<div style="margin-top:10px; margin-left:10px; float:left">			 	  
					<input id="textbox1" class="easyui-datebox" validtype="date" style="height:30px;line-height:30px;">			
				</div>
				<div style="margin-top:10px; margin-left:0px; float:left">			 	  
					<img style="height:30px;line-height:30px;" class="img " src="../../../images/main/arrow-right.png">				
				</div>
				<div style="margin-top:10px; margin-left:10px; float:left">		
					<input id="textbox2" class="easyui-datebox" validtype="date" style="height:30px;line-height:30px;">   		
				</div> -->
				
				<div style="margin-top:10px; margin-left:20px; float:left">		
					<input class="easyui-combobox" id="userId" style="width:200px; height:30px;"></input>
				</div>	
			</div>			
		</div>	
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
				toolbar:'#tb',
				singleSelect:true,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				pagination:true,
				rownumbers:true,
				showFooter: true,
				columns:[[
 					//{field:'ck', checkbox:true},	
 					{field:'action1',title:'OP',width:40,align:'center',
						formatter:function(value,row,index){
							if (row.editing){	
								return '';
							}else{
								var a = '<a href="#" onclick="spreadrow(this)" style = "color: #1da02b;text-decoration:none"><i class="iconfont">&#xe60a;</i></a> ';									
								return a;
							}	
						}
					},	
				
  					{field:'orderNum',title:'工单号',width:100,align:'center'},
					{field:'orderQuantity',title:'工单数量',width:100,align:'center'},
					{field:'poCreateDate',title:'工单创建日期',width:100,align:'center'},
					{field:'orderAmount',title:'金额',width:100,align:'center',
						formatter:function(value,row,index){ 
							if(value == undefined){
								return '';
							}else{	
			    	 			return  '<span style="color:red">'+value+'</span>';
			    	 		}
			      		}  
					},		
 					{field:'status',title:'工单状态',width:100,align:'center',
						formatter:function(value,row,index){ 	
			    	 		if(value == '工单结束'){
								return  '<span style="color:green">'+value+'</span>';
							}else if(value.indexOf('部分')>-1){
								return  '<span style="color:red">'+value+'</span>';
							}else{	
			    	 			return  '<span style="color:orange">'+value+'</span>';
			    	 		}
			      		}  
					}, 
					{field:'remark',title:'备注',width:100,align:'center',editor:'text'},
					/*{field:'action',title:'Action',width:60,align:'center',
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
					},*/		
					{field:'idc',title:'ID', width:10,hidden:'true'}
				]],
				rowStyler: function (index, row) {
					if(index > 0){
		                //return 'background-color:LightCyan;';
		            }
		        },
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
		
			
			$("#dialog").dialog({
		        onClose: function () {
		             //alert(result);	
		            var orderNumID = getDlgGetOrderNumID('#dialog');      				     	
		        	$.ajax({  
						type: "post",
						url : '${pageContext.request.contextPath}/orderNum.htm/changeStatus',
						data: {orderNumID:orderNumID}, 
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
							//alert(JSON.stringify(result));	
							if(result.length == 1){	
								$('#tt').datagrid('updateRow',{
									index: getDlgParaRowIndex('#dialog'),
									row: {
										status: result[0].status,
										orderAmount:result[0].orderAmount	
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

				
		});
		
	</script>	
	<script>	


	
        $(function(){				
  
        	$.ajax({ 
				type: "post",  				
				url:'${pageContext.request.contextPath}/orderNum.htm/loadData',
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
                         
        });  
        
        function getRowIndex(target){
			var tr = $(target).closest('tr.datagrid-row');
			return parseInt(tr.attr('datagrid-row-index'));
		}
		    
        function spreadrow(target){
        	var rowIndex = getRowIndex(target);
        	$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');
	        $("#dialog").dialog({
			     title: row.orderNum,
			     queryParams: {orderNumID: row.idc,index:rowIndex,orderNum:row.orderNum},		
			     content:"<iframe scrolling='auto' frameborder='0' src='./pf_bd_tables4_2_0.html' style='width:100%; height:100%; display:block;'></iframe>"
			});
		
			$("#dialog").dialog("open"); // 打开dialog
      	     	
        }
		$('#userId').combobox({
		        prompt:'请输入要查询的订单号', 	//提示信息
				//required:true, 	//是否必填
				mode:'remote', 		//动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
				url:'${pageContext.request.contextPath}/orderNum.htm/autotimp', 	//请求数据路径
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
		        	//alert(JSON.stringify(record));
		        	$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/orderNum.htm/searchOrderNum',
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
     	
     	/* $('#textbox2').datebox({
			onSelect: function(date){
				//alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
				$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/orderNum.htm/betweenDateFind',
					data:{
						beginDate:$("#textbox1").textbox('getValue'),
						endDate:$("#textbox2").textbox('getValue')
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
		}); */
     	
      

	</script>
	<script>
	  

		function getDlgGetOrderNum(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["orderNum"];
		}

		function getDlgGetOrderNumID(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["orderNumID"];
		}

		function getDlgParaRowIndex(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["index"];		
		}
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
									iconCls:'icon-arrow-refresh',
									handler:function(){  
										$.ajax({ 
											type: "post",  				
											url:'${pageContext.request.contextPath}/orderNum.htm/loadData',
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
												$('#tt').datagrid("reload",result); 
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
		


	</script>
 	
</body>
</html>



