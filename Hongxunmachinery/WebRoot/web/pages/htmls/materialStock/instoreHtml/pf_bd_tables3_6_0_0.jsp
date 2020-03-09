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
    <script type="text/javascript" src="../../../js/myjs/ajaxfileupload.js"></script>
    <script type="text/javascript" src="../../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../../custom/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
 		<div id="tb" style="padding:0 10px;height:auto">	
			<div id="ttdiv" style="width:100%;  height:50px;">
				<div style="margin-top:5px; margin-left:10px; float:left">	
<!-- 					<input type="text" id="quantityId" class="easyui-numberbox" value="" data-options="min:1,prompt:'出库数量'" style="width:90px;height:35px;">
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
					{field:'materialNum',title:'物料编码',align:'center',width:20},
					//{field:'materialName',title:'物料名称',align:'center',width:40,editor:'text', hidden:'true'},									
					{field:'specification',title:'型号/规格',align:'center',width:100},		
					{field:'storeOutPlanQuantity',title:'项目数',align:'center',width:20},
					{field:'storeQuantity',title:'库存数量',align:'center',width:20},
					{field:'safeQuantity',title:'安全库存',align:'center',width:20},
					{field:'storeOutRealQuantity',title:'领料数量',align:'center',width:20},	
/* 					{field:'outStoreDate',title:'领料日期',align:'center',width:20},						
					{field:'staff',title:'领料员',align:'center',width:20,editor:'text'}, */
					{field:'lotNum',title:'批号',align:'center',width:30,editor:'text'},  					
					{field:'unit',title:'单位',align:'center',width:20,editor:'text'},  
					{field:'remark',title:'备注',align:'center',width:20,editor:'text'},	
					/*{field:'action',title:'Action',width:30,align:'center',
						formatter:function(value,row,index){
							if(isOnlyRead()==false){										
								var d = '<a href="#" onclick="outstore(this)">出库</a>';							
								return d;															
							}else{
					    	 	return '';
					    	}
				    	}
					},	*/	
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
				url: '${pageContext.request.contextPath}/outStoreItem.htm/getOutStoreItems',
				data:{outStoreNumID:window.parent.window.getOutStoreNumID()},
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
			        
        function outstore(target){
        	$.messager.confirm('Confirm','是否领料出库?',function(r){
				if (r){
		 			var rowIndex = getRowIndex(target);
					$('#tt').datagrid('endEdit', rowIndex);					
					$('#tt').datagrid('selectRow',rowIndex);
			       	var row = $('#tt').datagrid('getSelected'); 		
				    $.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/outStoreItem.htm/materialOutStore',
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
											url: '${pageContext.request.contextPath}/outStoreItem.htm/getOutStoreItems',
											data:{outStoreNumID:window.parent.window.getOutStoreNumID()},
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
												//$('#tt').datagrid({data:result}).datagrid('clientPaging'); 	 						
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
		
/* 		
		function IsDateTime(str){
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
			var bagin_r = mystring.match(/^(\d{4})(-)(\d{2})(-)(\d{2})$/);
			 
			if(bagin_r==null){
			 	alert("请输入正确的开始时间格式,如:2017-01-01");
			 	return false;
			}else{
				return true;
			}
		}
		
		function isNumber(val) {
		    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
		    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
		    if(regPos.test(val) || regNeg.test(val)) {
		        return true;
		    } else {
		        return false;
		    }
		} */
		/*
		function fileImport(){
			//alert(123);
			$("#fileUploadDlg").dialog("open");;
		}
		
		function exportExcel(){
			$.ajax({ 
				type: "post",  
				url:'${pageContext.request.contextPath}/fileop.htm/readFile',  	
				
				traditional: true,
				dataType:"json",  
	
				success: function(result) {

				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	
		}	*/    
 	</script>
</body>
</html>


