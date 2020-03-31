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
 		<div id="tb" style="padding:0 10px;height:auto">	
			<div id="ttdiv" style="width:100%;  height:50px;">
				<div style="margin-top:5px; margin-left:10px; float:left">					
					<input type="text" id="quantityId" class="easyui-numberbox" value="" data-options="min:1,prompt:'入库数量'" style="width:90px;height:35px;">	 		
					<input class="easyui-textbox" id="scanQ" data-options="iconCls:'icon-search'" style="width:200px;height:35px;">
					<input class="easyui-combobox" id="userId" style="width:400px;height:35px;"></input>		        	
		        	<a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('toExcel','dg.xls')">导出</a>
		        	<a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('print','DataGrid')">打印</a>			
					<input id="sbutton" class="easyui-switchbutton" checked onText="手动" offText="扫描" style="width:100px;height:35px">
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
				pageList: [10,100,400,800,1000],
				rownumbers:true,
				columns:[[
 					{field:'ck', checkbox:true},	
					{field:'materialNum',title:'物料编码',align:'center',width:40},								
					{field:'specification',title:'型号/规格',align:'center',width:50},		
					{field:'quantity',title:'入库数量',align:'center',width:40, editor:'numberbox'}, 
					{field:'materialUnit',title:'单位',align:'center',width:20},  
 					{field:'location',title:'库位代码',align:'center',width:40},  	
					{field:'date',title:'时间日期',width:40,align:'center'},   					
					{field:'lotNum',title:'批号',align:'center',width:30},		
					{field:'remark',title:'备注',align:'center',width:20},
					{field:'action',title:'Action',width:40,align:'center',
						formatter:function(value,row,index){
							if(isOnlyRead()==false){
								if (row.editing){
									var s = '<a href="#" onclick="saveRow(this)">保存</a> ';
									var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
									return s+c;
								} else {
									var	e;
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
			$('#scanQ').next().hide();		
		});
		
		$(function(){ 
			$('#sbutton').switchbutton({
				//checked: false,
				onChange: function(checked){
					if (checked == true){					
						$('#scanQ').next().hide();
						$('#userId').next().show();						
						return;
					}
					if (checked == false){
						$('#userId').next().hide();
						$('#scanQ').next().show();						
					}
				}
			});
		});
		
	</script>
	<script>
        $(function(){				    
       		$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/weldNoLimitItemInStock.htm/loadData',
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
	  
			if(isOnlyRead()==true){
				$('#scanQ').textbox('disable');
				$('#userId').combobox('disable');
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
	
		function inputEnter() {
			/*var qvalue = $("#userId").textbox("getValue");
			if (qvalue.indexOf("shuliang=") > -1) {
				qvalue = qvalue.split("=")[1];
				if ($("#quantityId").numberbox("getValue") != "") {
					//alert(123);
					if (qvalue == 0) {
						qvalue = "";
					} else {
						qvalue = parseInt(qvalue, 10) + parseInt($("#quantityId").numberbox("getValue"), 10);
					} //alert(qvalue)
				}
				$("#quantityId").numberbox("setValue", qvalue);
				$("#userId").textbox("setValue", "");
				return;
			}*/
			if ($("#quantityId").numberbox("getValue") == "") {
				$.messager.show({
					title : '提示信息',
					msg : '报错，扫描数量为空',
					showType : 'show',
					timeout : 0,
					style : {
						right : '',
						top : document.body.scrollTop + document.documentElement.scrollTop,
						bottom : ''
					}
				});
				return;
			}
			var data = {}
	
			data["name"] = $("#userId").textbox("getValue");
			//data["size"] = $("#sizeId").textbox("getValue");
			data["quantity"] = $("#quantityId").numberbox("getValue");
			//var data = {name:$('#userId').combobox("getValue"),id:window.parent.window.getPurchaseNumID(),quantity:$("#quantityId").numberbox("getValue")},
			//alert(JSON.stringify(data));	
			$.ajax({
				type : "post",
				url : '${pageContext.request.contextPath}/weldNoLimitItemInStock.htm/instoreProduction',
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
					if (result[0].hasOwnProperty('error')) {
						alert(result[0].error);
					} else {
						$('#tt').datagrid('insertRow', {
							index : 0,
							row : result[0]
						});
						$("#userId").textbox("setValue", "");
						//$("#sizeId").textbox("setValue", "");
						$("#quantityId").numberbox("setValue", "");
					}
				},
				error : function() {
					alert("异常！");
				}
			});
		}
	
		$("#scanQ").textbox({
		  	inputEvents: $.extend({}, $.fn.textbox.defaults.inputEvents,{
		    	keyup:function(event){
		         	if(event.keyCode == 13){
		         		//alert($("#userId").combobox("getValue"));
		         		var data={};
		     			var qvalue = $("#scanQ").textbox("getValue");
		         		if(qvalue.indexOf("#")>-1){		         	
		         			//data["name"] = qvalue.split("#")[0];					
							data["quantity"] = qvalue.split("#")[1];
							if(qvalue.split("#").length>3){
								data["name"] = qvalue.split("#")[0] +'->'+ qvalue.split("#")[3];
							}else{
								data["name"] = qvalue.split("#")[0];
							}
		         		}else{
			         		//if($("#quantityId").numberbox("getValue")==""){
			         			$.messager.show({
									title:'提示信息',
									msg:'报错，自动扫描模式二维码信息错误',
									showType:'show',
									timeout:0,
									style:{
										right:'',
										top:document.body.scrollTop+document.documentElement.scrollTop,
										bottom:''
									}
								});
								return;
			         		//}	         		
		         			/*data["name"]=$("#scanQ").textbox("getValue");
		         			data["quantity"]=$("#quantityId").numberbox("getValue");*/		
		         		       		
						}
		         		//alert(JSON.stringify(data));	
		            	$.ajax({  
							type: "post",  
							url: '${pageContext.request.contextPath}/weldNoLimitItemInStock.htm/instoreProduction',			
							data:data, 
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
									alert(result[0].error);
								}else{								
									$('#tt').datagrid('insertRow', {
										index: 0,
										row:result[0]
									});	
									$("#scanQ").textbox("setValue","");	
									$("#quantityId").numberbox("setValue","");
								}							
							}, 
							error : function() {
						       	alert("异常！");
						  	}
						});
		       		}
		       	}
		    })
		});
	
		$('#userId').combobox({
			prompt : '请输入部件号', //提示信息
			//required:true, 	//是否必填
			mode : 'remote', //动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
			url : '${pageContext.request.contextPath}/weldNoLimitItemInStock.htm/autotimp', //请求数据路径				
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
				if ($("#quantityId").numberbox("getValue") == "") {
					$.messager.show({
						title : '提示信息',
						msg : '报错，扫描数量为空',
						showType : 'show',
						timeout : 0,
						style : {
							right : '',
							top : document.body.scrollTop + document.documentElement.scrollTop,
							bottom : ''
						}
					});
					return;
				}
				$.messager.confirm('Confirm', '是否入库?', function(r) {
					if (r) {
						//record["id"] = window.parent.window.getOrderNumID();
						record["quantity"] = $("#quantityId").numberbox("getValue");
						//alert(JSON.stringify(record));
						$.ajax({
							type : "post",
							url : '${pageContext.request.contextPath}/weldNoLimitItemInStock.htm/instoreProduction',
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
									//$('#tt').datagrid('loadData', result);	
									$('#tt').datagrid('insertRow', {
										index : 0,
										row : result[0]
									});
									$("#userId").textbox("setValue","");	
									$("#quantityId").numberbox("setValue", "");
									$("#quantityId").textbox().next('span').find('input').focus();
								}
							},
							error : function() {
								alert("异常！");
							}
						});
					}
				});
			},
	
			inputEvents : $.extend({}, $.fn.combobox.defaults.inputEvents, {
				keyup : function(event) {
					if (event.keyCode == 13) {
						//var value = $('#userId').combobox('getValue'); 
						//alert(value);
						$.messager.confirm('Confirm', '是否入库?', function(r) {
							if (r) {
								inputEnter();
								$('#userId').combobox("hidePanel");
								$('#userId').combobox('setValue', '');
								$("#quantityId").textbox().next('span').find('input').focus();
							}
						});	
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
									iconCls:'icon-arrow-refresh',
									handler:function(){	   
										$.ajax({  
											type: "post",  
											url: '${pageContext.request.contextPath}/weldNoLimitItemInStock.htm/loadData',
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
		
		
		function saveRow(target){
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/weldNoLimitItemInStock.htm/getEntity',
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
							}else if(key == 'date'){
								delete row[key];
							}
						}				
					}
		        	userList.push(row);
					//alert(JSON.stringify(userList));
					$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/weldNoLimitItemInStock.htm/updateRow',
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
							if(result[0].hasOwnProperty('error')){
								alert(result[0].error);   
							}												
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
  
</body>
</html>


