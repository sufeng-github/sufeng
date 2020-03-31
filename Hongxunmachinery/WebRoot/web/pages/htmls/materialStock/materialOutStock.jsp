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
    <link rel="stylesheet" href="../../../custom/uimaker/easyui.css">
	<link rel="stylesheet" type="text/css" href="../../../custom/uimaker/icon.css">    
    <link href="../../css/basic_info.css" rel="stylesheet">
 	<script type="text/javascript" src="../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../../custom/easyui-lang-zh_CN.js"></script>
</head>
<body>

	<div class="easyui-layout"  data-options="fit:true">	    
		<div id="center" data-options="region:'center', border:true, split:true" style="padding:5px;background:#eee;">
			<div id="tb" style="padding:0 10px; height:auto">
				<div id="ttdiv" style="width:100%; display:none">   	
<!-- 					<span class="con-span">领料单号: </span><input id="textbox1" class="easyui-textbox" type="text" name="code" style="width:130px;height:25px;line-height:25px;"></input> 		       
					<span class="con-span">工单编号: </span><input id="textbox2" class="easyui-textbox" type="text" name="code" style="width:130px;height:25px;line-height:25px;"></input>	        
					<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" style="height:25px" onclick="insert()">新增</a> -->							   
					<input id="textbox5" class="easyui-datebox" validtype="date" style="width:130px;height:25px;line-height:25px;">
					<span class="con-span">到 </span><input id="textbox6" class="easyui-datebox" validtype="date" style="width:130px;height:25px;line-height:25px;">   		
<!-- 					<a href="#" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:false, plain:true" style="height:25px" onclick="betweendatefind()">查询</a> -->
					<input id="ss"></input>
				</div>
			 </div>
			<table id="tt"></table>
		</div>
	</div>	
	
	<div id="dialog"  class="easyui-dialog" style="width:90%;height:90%;padding:0px" data-options="
			closed:true,
			resizable:true,		
			onResize:function(){
				$(this).dialog('center');
			}">
	</div>    
	
	<script>
			
		$(function(){
			$('#tt').datagrid({
				width:'100%',
				height:'auto',
				toolbar:'#tb',
				singleSelect:true,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				remoteSort:false,
				multiSort:true,
				pagination:true,
				rownumbers:true,	
				idField:'name',
				columns:[[
 					{field:'ck', checkbox:true},	
 					{field:'action1',title:'OP',width:30,align:'center',
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
					{field:'outStoreNum',title:'领料单号',width:100,align:'center'},			
					{field:'workProcedure',title:'加工工序',width:100,align:'center',editor:{type:'textbox',options:{required:true}}},		
					//{field:'quantity',title:'领料数量',width:100,align:'center'},	
					{field:'state',title:'状态',width:80,align:'center',
						formatter:function(value,row,index){ 
							if(value == '领料100%'){
								return  '<span style="color:green">'+value+'</span>';
							}else{
								return  '<span style="color:red">'+value+'</span>';
							}
			      		} 			
					}, 
					{field:'remark',title:'备注',width:80,align:'center',editor:'text',
						formatter:function(value,row,index){ 
							if(value != undefined && value.indexOf('异常')>-1){	
			    	 			return  '<span style="background-color:red;">'+value+'</span>';
			    	 		}
			      		} 					
					},
/* 					{field:'action',title:'操作',width:30,align:'center',
						formatter:function(value,row,index){												
								if (row.editing){							
									var s = '<a href="#" onclick="saverow1(this)">领料</a> ';
									var c = '<a href="#" onclick="cancelrow1(this)">取消</a>';
									return s+c;
								} else {
				
									var e = '<a href="#" onclick="editrow1(this)">编辑</a> ';
									 var d = '<a href="#" onclick="deleterow1(this)">删除</a>'; 
									return e + d;
								}							
						}
					}, */
					{field:'idc',title:'ID',width:10,hidden:'true'}
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
		
		
        $(function(){				
            //$('#tt').datagrid('clientPaging'); 
            //$('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/workNum.htm/loadData')}).datagrid('clientPaging');   
            $.ajax({ 
				type: "post",  
				url:'${pageContext.request.contextPath}/storeOutNum.htm/loadData',  	
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
 			$('#tt1').datagrid('clientPaging'); 
        });  
        
     
        function spreadrow(target){
		    var rowIndex = getRowIndex(target);
        	$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');
	        $("#dialog").dialog({
			     title: row.workProcedure + '->领料单号： ' + row.outStoreNum,
			     queryParams: {outStoreNumID:row.idc,index:rowIndex},		
			     content:"<iframe scrolling='auto' frameborder='0' src='./instoreHtml/pf_bd_tables3_0_6.html' style='width:100%; height:100%; display:block;'></iframe>"
			});
		
			$("#dialog").dialog("open"); // 打开dialog	
        }
       	
       	$("#dialog").dialog({
		   	onClose: function () {		          		     	
		        $.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/storeOutNum.htm/updateStatus',
						data: {outStoreNumID:getOutStoreNumID('#dialog')}, 
						//traditional: true,
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
										state: result[0].state	
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
       	
		function betweendatefind(){
			 var date1 = $("#textbox5").textbox('getValue');
			 var date2 = $("#textbox6").textbox('getValue');
				$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/workNum.htm/betweenDateFind',
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

		function getOutStoreNumID(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["outStoreNumID"];
		}
		
		function getDlgParaRowIndex(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["index"];
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


