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
	
	<div id="dlg1" class="easyui-dialog" style="width:90%;height:90%;padding:0px" data-options="
			closed:true,
			resizable:true,
			onResize:function(){
				$(this).dialog('center');
			}">
		<div class="easyui-layout"  data-options="fit:true">	    
			<div id="center" data-options="region:'center', border:true, split:true" style="padding:5px;background:#eee;">	
				<div id="tt1tb" style="padding:0 10px;height:auto"> 
			        <div id="tt1div" style="width:100%; display:none; height:40px;">
			        	<div style="margin-top:5px; margin-left:20px; float:left">		
							<input class="easyui-combobox" id="workItemID" style="width:200px; height:30px;"></input>
						</div>
					</div>
				</div>
				<table id="tt1"></table>
			</div>
<!-- 			<div data-options="region:'south',split:true" style="height:100px;padding:5px;background:#eee;">
				<div id="tt2tb" style="padding:0 10px;height:auto">
					<div id="tt2div" style="width:100%; display:none; height:40px;">   	
						<div style="margin-top:5px; margin-left:20px; float:left">		
							<input class="easyui-combobox" id="workItemLLID" style="width:200px; height:30px;"></input>
						</div>
					</div>
				</div>
				<table id="tt2"></table> 	
			</div> -->	
		</div>			
	</div>
	
	<div id="dlg2" class="easyui-dialog" style="width:40%;height:40%;padding:0px" data-options="
			closed:true,
			iconCls:'',
			resizable:true,
			onResize:function(){
				$(this).dialog('center');
			}">
		<table id="tt2"></table>
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
					{field:'quantity',title:'领料数量',width:100,align:'center'},	
					{field:'state',title:'状态',width:80,align:'center',
						formatter:function(value,row,index){ 
							if(value == undefined){
								return '';
							}else{	
			    	 			return  '<span style="color:orange">'+value+'</span>';
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
					{field:'action',title:'操作',width:30,align:'center',
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
					},
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

			$('#tt1').datagrid({
				width:'100%',
				height:'100%',
				toolbar:'#tt2tb',
				singleSelect:true,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				pagination:true,
				rownumbers:true,
				columns:[[
 					{field:'ck', checkbox:true},			
					{field:'materialNum',title:'物料编码',align:'center',width:40},
					//{field:'materialName',title:'物料名称',align:'center',width:40,editor:'text', hidden:'true'},									
					{field:'specification',title:'型号/规格',align:'center',width:50},		
					{field:'materialPlanQuantity',title:'需求数量',align:'center',width:20},
					{field:'storeQuantity',title:'库存数量',align:'center',width:20},
					{field:'readyQuantity',title:'库存水位',align:'center',width:20},
					{field:'outStoreDate',title:'领料日期',align:'center',width:20},	
					{field:'quantity',title:'领料数量',align:'center',width:20},	
					{field:'staff',title:'领料员',align:'center',width:20,editor:'text'},
					{field:'lotNum',title:'批号',align:'center',width:30,editor:'text'},  					
					{field:'unit',title:'单位',align:'center',width:20,editor:'text'},  
					{field:'remark',title:'备注',align:'center',width:20,editor:'text'},			
					{field:'action',title:'操作',width:30,align:'center',
						formatter:function(value,row,index){
													
								if (row.editing){							
									var s = '<a href="#" onclick="saverow1(this)">领料</a> ';
									var c = '<a href="#" onclick="cancelrow1(this)">取消</a>';
									return s+c;
								} else {
									var	e;
									if(row.materialPlanQuantity <= row.quantity){
										e= '已领料'
									}else{
										e = '<a href="#" onclick="editrow1(this)">编辑</a> ';
									}					
									/*var e = '<a href="#" onclick="editrow1(this)">编辑</a> ';
									 var d = '<a href="#" onclick="deleterow1(this)">删除</a>'; */
									return e;
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
			       	alert("出错了！");
			   	}
			});	
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
	       	//alert(row.idc + row.workNum);

			var userList = new Array();   
			userList.push({idc:row.idc});
			$.ajax({  
				type: "post",  
				//url: '${pageContext.request.contextPath}/purchaseItemOutstock.htm/getWorkItemOutStocks',
				url: '${pageContext.request.contextPath}/outStoreItem.htm/getOutStoreItems',
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
					//alert(JSON.stringify(result));	
					if(result.length>0){	
						$('#dlg1').dialog({title: '工单号 :' + row.outStoreNum + '领料', queryParams: {dlgPara: row.idc} }); 
						$("#dlg1").dialog("open");					
						$('#tt1').datagrid('loadData', result);	
					}else{
						alert('无领料单')
					}	 						
				}, 
				error : function() {
			       	alert("异常！");
			  	}
			});
        }
       	
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


	</script>
	
	<script>
		function getDlgParameter(dlg1){
			var obj=$(dlg1).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["dlgPara"];
		}
	</script>
	<script>
		function getRowIndex(target){
			var tr = $(target).closest('tr.datagrid-row');
			return parseInt(tr.attr('datagrid-row-index'));
		}
		function editrow1(target){
			$('#tt1').datagrid('selectRow', getRowIndex(target));	
			$('#tt1').datagrid('beginEdit', getRowIndex(target));
		}
	
		function saverow1(target){		
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/purchaseItemOutStock.htm/getEntity',
				traditional: true,
				dataType:"json", 
				//contentType : 'application/json;charset=utf-8', //设置请求头信息  
				success: function(result) {	
					var rowIndex = getRowIndex(target);
					$('#tt1').datagrid('endEdit', rowIndex);	
					$('#tt1').datagrid('selectRow',rowIndex);
			       	var row = $("#tt1").datagrid('getSelected');   
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
					alert(JSON.stringify(userList));
					//userList.push({idc:row.idc,staff:row.staff,lotNum:row.lotNum,unit:row.unit,remark:row.remark}); //
					$.ajax({  
						type: "post",  
						//url: '${pageContext.request.contextPath}/purchaseItemOutstock.htm/getWorkItemOutStocks',
						url: '${pageContext.request.contextPath}/purchaseItemOutStock.htm/saveRow',
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
							alert(JSON.stringify(result));
							if(result.length>0){	
								$('#tt1').datagrid('updateRow',{
									index: rowIndex,
									row: {
										outStoreDate: result[0].outStoreDate,
										quantity: result[0].quantity,
										storeQuantity:result[0].storeQuantity
									}
								});
							}else{
								alert("库存不足");
							}	
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					}); 			
				}
			});	
		}
		
		function cancelrow1(target){
			$('#tt1').datagrid('selectRow', getRowIndex(target));	
			$('#tt1').datagrid('cancelEdit', getRowIndex(target));
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
		function funOne(tt,url,rowIndex,data,para){	 
			$.ajax({  
				type: "post",  
				url: url,
				data:data, 
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
					alert(JSON.stringify(result));	
					//if(result.length != 0){	
						if(para=="saverow" && result.length != 0){
							$(tt).datagrid('updateRow',{
								index: rowIndex,
								row: {
									idc: result[0].idc,
								}
							});	
							//$(tt).datagrid('refreshRow', rowIndex);
								
						}else if(para=="saverow1" && result.length != 0){
							$(tt).datagrid('updateRow',{
								index: rowIndex,
								row: {
									idc: result[0].idc,
									//materialMoney:result[0].materialMoney,
									//instoreDate:result[0].instoreDate
								}
							});	
						} else if(para=="refresh"){
							var rows = $('#tt').datagrid('getRows');
							for(var i=0; i<rows.length; i++){
								rowIndex = $('#tt').datagrid('getRowIndex',rows[i]);
								$(tt).datagrid('updateRow',{
									index: rowIndex,
									row: result[i]								
								});	
							}
						} else if(para=='dlgInsert'){
							for(var i=0; i<result.length; i++){
								var index = i;
								$(tt).datagrid('insertRow', {
									index: index,
									row:result[i]
								});
								$(tt).datagrid('selectRow',index);	
								$(tt).datagrid('beginEdit',index); 	
							}			
						} else if (para=='getChildrens' || para=='datefind'){
							$(tt).datagrid('loadData', result);
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
			alert(mystring + ":" + RegExp.$2 + ":" + RegExp.$3);
			if (!reg.test(str)&&RegExp.$2<=12&&RegExp.$3<=31){			
				return true;
			}
			return false;
		}
	</script>

	
</body>
</html>


