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
    <script type="text/javascript" src="../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../custom/jquery.easyui.min.js"></script>
    <link href="../../css/base.css" rel="stylesheet">
    <link href="../../css/providers1.css" rel="stylesheet">
    <link rel="stylesheet" href="../../../custom/uimaker/easyui.css"> 
 	<link rel="stylesheet" type="text/css" href="../../../custom/uimaker/icon.css">

    <script type="text/javascript" src="../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../js/myjs/ajaxfileupload.js"></script>
    <script type="text/javascript" src="../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../custom/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
 		<div id="tb" style="padding:0 10px;height:auto">	
			<div id="ttdiv" style="width:100%; display:none; height:50px;">	
				<div style="margin-top:10px; margin-left:10px; float:left">			 	  
					<input id="textbox5" class="easyui-datebox" validtype="date" style="height:30px;line-height:30px;">			
				</div>
				<div style="margin-top:10px; margin-left:0px; float:left">			 	  
					<img style="height:30px;line-height:30px;" class="img " src="../../images/main/arrow-right.png">				
				</div>
				<div style="margin-top:10px; margin-left:10px; float:left">		
					<input id="textbox6" class="easyui-datebox" validtype="date" style="height:30px;line-height:30px;">   		
				</div>
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
			onResize:function(){
				$(this).dialog('center');
			}">
<!-- 		<div class="easyui-layout"  data-options="fit:true">	
		    <div data-options="region:'center'" style="padding:5px;background:#eee;">
				<table id="tt1"></table>				
			</div>
		</div> -->	
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
  					{field:'purchaseNum',title:'采购单号',width:100,align:'center',editor:'text'},
  					{field:'productionDes',title:'采购项目',width:150,align:'center',editor:'text'},
					/* {field:'productionOrderNum',title:'采购项目',width:150,align:'center',editor:'text'}, */
					{field:'purchaseDate',title:'采购日期',width:130,align:'center',sortable: true,editor:{type:'datetimebox',options:{required:true}}},
					{field:'purchasingAgent',title:'采购员',width:100,align:'center',editor:'text'},
					{field:'purchaseSupplier',title:'供应商',width:80,align:'center',editor:'text'}, 
 					{field:'purchaseStatus',title:'采购状态',width:50,align:'center',
 						formatter:function(value,row,index){ 	
			    	 		if(value == '100%已下单'){
								return  '<span style="color:green">'+value+'</span>';
							}else{
								return  '<span style="color:red">'+value+'</span>';								
							}
			      		}  
						
					}, 
					{field:'purchaseRemark',title:'备注',width:100,align:'center',editor:'text'},		
					{field:'idc',title:'ID', width:10,hidden:'true'}
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
            //$('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/purchaseNum.htm/loadData')}).datagrid('clientPaging');       
            $.ajax({ 
				type: "post",  	
				url:'${pageContext.request.contextPath}/purchaseNum.htm/loadData',
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
            
           // $('#tt1').datagrid('clientPaging');                     
        });  
        
        function spreadrow(target){
        	var rowIndex = getRowIndex(target);
        	$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');
	        $("#dialog").dialog({
			     title:  '采购单号：' + row.purchaseNum,
			     queryParams: {purchaseNumID: row.idc,index:rowIndex},		
			     content:"<iframe scrolling='auto' frameborder='0' src='./instoreHtml/pf_bd_tables3_1_0.html' style='width:100%; height:100%; display:block;'></iframe>"
			});	
			$("#dialog").dialog("open"); // 打开dialog   	
        }
        
		$("#dialog").dialog({
		   	onClose: function () {
		   		if(isOnlyRead()==false){
		            //alert(result);	
		            //var userList = new Array(); 
		            var purchaseNumID = getPurchaseNumID('#dialog'); 
        			//userList.push({idc:purchaseNumID});			     	
		        	$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/purchaseNum.htm/updateSotockState',
						data: {purchaseNumID : getPurchaseNumID('#dialog')},//JSON.stringify(userList), 
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
										purchaseStatus:	result[0].purchaseStatus
									}
								});			
							}							
						}, 
						error : function() {
					       	alert("异常！");
					  	}
					});	     	
		        }
		    }   
		});		            
     	
     	$('#userId').combobox({
		        prompt:'请输入要查询的采购单', 	//提示信息
				//required:true, 	//是否必填
				mode:'remote', 		//动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
				url:'${pageContext.request.contextPath}/purchaseNum.htm/autotimp', 	//请求数据路径
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
						url: '${pageContext.request.contextPath}/purchaseNum.htm/searchPurchaseNum',
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
       	
       	$('#textbox6').datebox({
			onSelect: function(date){
				alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
				$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/purchaseNum.htm/betweenDateFind',
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
		});		

	</script>
	<script>
	  	function isOnlyRead(){
			var str = localStorage.getItem('authority1'); 			
	        str = str.substr(9, 1)
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
		function getPurchaseNumID(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["purchaseNumID"];
		}
 		function getDlgParaRowIndex(dialog){
			var obj=$(dialog).dialog('options');
			var queryParams = obj["queryParams"];            
			return queryParams["index"];
		} 
		/*$(".more").click(function(){
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
										//$('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/purchaseNum.htm/loadData')}).datagrid('clientPaging');              
										$.ajax({ 
											type: "post",  	
											url:'${pageContext.request.contextPath}/purchaseNum.htm/loadData',
											traditional: true,
											dataType:"json",  
											beforeSend: function () {
												load();
											},
											complete: function () {
												disLoad();
											},
											success: function(result) {							
												$('#tt').datagrid('loadData', result);  
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
		
<%-- 		function getData(tt, url, para){
           	$.ajax({ 
				type: "post",  
				//url:'<%=path%>/screwdata.htm/load',  	
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
        } --%>
		
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


