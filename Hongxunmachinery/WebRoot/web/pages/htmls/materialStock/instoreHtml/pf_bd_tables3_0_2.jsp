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
<!--     <link href="../basic_info.css" rel="stylesheet"> -->
 	<script type="text/javascript" src="../../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../../js/myjs/datagrid-export.js"></script>
    <script type="text/javascript" src="../../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../../custom/easyui-lang-zh_CN.js"></script>
<!--     
	<script type="text/javascript" src="../topmenu/scripts/jquery.tmailsilder.v2.js"></script>	
    <link rel="stylesheet" type="text/css" href="../topmenu/css/jquery.tmailsilder.v2.css" /> 
-->
<!--     <link rel="stylesheet" type="text/css" href="../css/my.css" /> -->
</head>


<body>

	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
 		<div id="tb" style="padding:0 10px;height:auto">	
			<div id="ttdiv" style="width:100%;  height:50px;">
				<div style="margin-top:5px; margin-left:10px; float:left">		
					<input class="easyui-combobox" id="userId" style="width:200px;height:35px;"></input>	 	  
		        	<a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('toExcel','dg.xls')">导出</a>
		        	<a href="javascript:;"  class="easyui-linkbutton"   onclick="$('#tt').datagrid('print','DataGrid')">打印</a>			
				</div>
			</div>		   
		</div> 
		<table id="tt"></table> 
	</div>		
<!-- 	<div id="dialog"  class="easyui-dialog" style="width:80%;height:80%;padding:0px" data-options="
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
					<span class="con-span"><input id="textbox3"  class="easyui-textbox" type="text" name="code" style="width:120px;height:25px;line-height:25px;"></input></span>
						<span class="con-span"><select class="easyui-combobox" id="textbox3" style="height:25px;width:120px;"></select></span>					
						<span class="con-span">数量:</span>
						<span class="con-span"><input id="textbox4"  class="easyui-numberbox" type="text" name="code" style="width:120px;height:25px;line-height:25px;"></input></span>
						<span class="con-span"><a href="#" class="easyui-linkbutton" style="height:25px;" data-options="iconCls:'icon-add', plain:true" onclick="dlginsert()">新增</a></span>
						<span class="con-span"><a href="#" class="easyui-linkbutton" style="height:25px;" data-options="iconCls:'icon-table-add', plain:true" onclick="orderImport()">导入表格</a></span>
						<span class="con-span"><a href="#" class="easyui-linkbutton" style="height:25px;" data-options="iconCls:'icon-table-go', plain:true" onclick="exportExcel()">导出表格</a></span>			        	
			        </div>
				</div>	
				<table id="tt"></table>				
			</div>
			<div data-options="region:'south',title:'South Title',split:true" style="height:100px;padding:5px;background:#eee;">
				<table id="tt2"></table> 	
			</div>	
		</div>	
	</div>    -->   
	<script>
		$(function(){
			$('#tt').datagrid({
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
				columns:[[
 					{field:'ck', checkbox:true},			
					{field:'materialNum',title:'物料编码',align:'center',width:40},
					//{field:'materialName',title:'物料名称',align:'center',width:40,editor:'text', hidden:'true'},									
					{field:'specification',title:'型号/规格',align:'center',width:50},		
					{field:'outStoreDate',title:'领料日期',align:'center',width:40},	
					{field:'quantity',title:'领料数量',align:'center',width:20},	
					{field:'staff',title:'领料员',align:'center',width:20,editor:'text'},
					{field:'lotNum',title:'批号',align:'center',width:30,editor:'text'},  					
					{field:'unit',title:'单位',align:'center',width:20,editor:'text'},  
					{field:'remark',title:'备注',align:'center',width:20,editor:'text'},			
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
			
			
/* 			$("#dialog").dialog({
		        onClose: function () {
		            //$("#projectQuery").css("right", "-1000px"); 
		            var rows = $('#tt1').datagrid('getRows');
		            var result=0;
		            for(var i=0; i<rows.length; i++){
		            	if(isNumber(rows[i].materialMoney)){
		            		result = result + rows[i].materialMoney;
		            	}
		            }
		            //alert(result);	
		            var userList = new Array(); 
		            var mainID = getDlgParameter('#dialog'); 
        			userList.push({mainID:mainID, materialName:result});			     	
	       			funOne('#tt', '${pageContext.request.contextPath}/numOneBranchOne.htm/saveMoney',0,JSON.stringify(userList), 'saveMoney');	            
		        	//$('#tt').datagrid('reload');
		        }
		    });	 */				
		});
		
	</script>
	<script>
        $(function(){				

            $('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/purchaseItemOutStock.htm/loadData')}).datagrid('clientPaging');                 
       		$('#userId').combobox({
		        prompt:'请输入要查询的部件号', 	//提示信息
				//required:true, 	//是否必填
				mode:'remote', 		//动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
				url:'${pageContext.request.contextPath}/purchaseItemOutStock.htm/autotimp', 	//请求数据路径
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
		        	alert(JSON.stringify(record));
		        	$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/purchaseItemOutStock.htm/searchMaterialNum',
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
						if(columns.indexOf("purchaseOrderNum")>-1){
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
										$('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/numOne.htm/loadData')}).datagrid('clientPaging');              
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
										var userList = new Array();  
										var idc = getDlgParameter('#dialog');
										userList.push({idc:idc});
										funOne('#tt1', '${pageContext.request.contextPath}/numOneBranchOne.htm/getStockinChildrens',0,JSON.stringify(userList),'getChildrens');																				
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
		
		function getData(tt, url, para){
           	$.ajax({ 
				type: "post",  				
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
					//alert(JSON.stringify(result));
					$(tt).datagrid('loadData', result);			
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	
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
						} else if(para=='getChildrens' || para=='datefind'){
							//$(tt).datagrid({data:result}).datagrid('clientPaging');; 						
							$(tt).datagrid('loadData', result);
						} 						
				}, 
				error : function() {
			       	alert("异常！");
			  	}
			});
		}
		
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
		}
				    
 	</script>
   
</body>
</html>


