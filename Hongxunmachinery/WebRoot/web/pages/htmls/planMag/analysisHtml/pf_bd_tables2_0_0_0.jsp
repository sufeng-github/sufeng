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
    <link href="../../../css/base.css" rel="stylesheet">
	<link href="../../../css/providers1.css" rel="stylesheet">
	<link rel="stylesheet" href="../../../../custom/uimaker/easyui.css">
	<link rel="stylesheet" type="text/css" href="../../../../custom/uimaker/icon.css">
	<link href="../basic_info.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="../css/my.css" />
	<link href="../../platform.css" rel="stylesheet">
	<link rel="stylesheet" href="../../../css/providers1.css">
	<link rel="stylesheet" href="../../../css/style.css" media="screen" type="text/css" />
	<link rel="stylesheet" href="../../../font-awesome-4.7.0/css/font-awesome.min.css" media="screen" type="text/css"/>
	<script type="text/javascript" src="../../../../custom/jquery.min.js"></script>
	<script type="text/javascript" src="../../../../custom/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../../../js/myjs/myjs.js"></script>
	<script type="text/javascript" src="../../../js/myjs/gridHeader.js"></script>
	<script type="text/javascript" src="../../../../custom/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="../../../js/myjs/jquery.form.js"></script>
	<script type="text/javascript" src="../../../js/main.js"></script>
</head>


<body> 	
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
 		<div id="tb" style="height:auto">	
			<div id="ttdiv" style="width:100%; height:50px;">	
				<div style="margin-top:5px; margin-left:20px; float:left">	
					<input class="easyui-combobox" id="userId" style="width:200px; height:30px;"></input>
					<a href="javascript:void(0)" id="decrease" class="easyui-linkbutton" data-options=" plain:true" style="height:30px;line-height:30px;" onclick="decrease()"><i class="fa fa-weibo fa-fw"></i>扣库存</a> 
					<a href="javascript:void(0)" id="analysis" class="easyui-linkbutton" data-options=" plain:true" style="height:30px;line-height:30px;" onclick="analysis()"><i class="fa fa-chain-broken fa-fw"></i>订单分解</a> 
<!-- 					<a href="javascript:void(0)" id="oneKeyDistribution" class="easyui-linkbutton" data-options="iconCls:'', plain:true" style="height:30px;line-height:30px;" onclick="oneKeyDistribution()">一键分配</a> 
					<a href="javascript:void(0)" id="oneKeyExportExcel" class="easyui-linkbutton" data-options="iconCls:'', plain:true" style="height:30px;line-height:30px;" onclick="oneKeyExportExcel()">一键导出</a>  -->
					<!-- <form id="oneKeyExportID" action="/MySpringMVC_TEST/bomTree/makeexcel" 	method="post"></form>					 -->
				</div>	
			</div>						 
		</div> 
		<table id="tt"></table> 
	</div>	
 
	<script>																	
		$(function(){		
		var tt=	$('#tt').datagrid({
				width:'100%',
				height:'100%',
				toolbar:'#tb',
				singleSelect:false,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				remoteSort:false,
				multiSort:true, 
				//pagination:true,
				//pageList: [10,100,400,800,1000],
				rownumbers:true,				
				columns:[[
 					{field:'ck', checkbox:true},											
  					{field:'supplier',title:'供应商',width:80,align:'center'},
					{field:'pon',title:'PO号',width:100,align:'center'},
					{field:'line',title:'行号',width:50,align:'center'},
					{field:'groupd',title:'采购组',width:50,align:'center'},
					{field:'materialNo',title:'物料号',width:130,align:'center'},				
					{field:'materialDesc',title:'物料描述',width:180,align:'center'}, 
					{field:'exemption',title:'免检',width:30,align:'center'}, 
					{field:'unit',title:'单位',width:40,align:'center',hidden:'true'},
					{field:'stockQuantity',title:'库存量',width:50,align:'center'},				
					{field:'quantity',title:'订单量',width:50,align:'center'}, 
					{field:'workQuantity',title:'生产量',width:50,align:'center',editor:'numberbox',
						formatter:function(value,row,index){ 						
			    	 		return  '<span style="color:orange">'+value+'</span>';			    	 		
			      		} 	
					},
					//{field:'price',title:'单价',width:60,align:'center',hidden:'true'}, 
					//{field:'totalAmount',title:'价格',width:60,align:'center'}, 
					//{field:'deliveredQuantity',title:'已交数量',width:30,align:'center'}, 
					//{field:'returnedQuantity',title:'退货数量',width:30,align:'center'}, 
					//{field:'nonDelivery',title:'未交数量',width:30,align:'center'}, 
					//{field:'readyQuantity',title:'备货数量',width:30,align:'center'}, 
					//{field:'inroadQuantity',title:'在途数量',width:30,align:'center'}, 
					{field:'poDeliveryDate',title:'PO交期',width:100,align:'center'/* ,editor:{type:'datebox',options:{required:true}} */}, 
					{field:'poCreateDate',title:'PO创建日期',width:100,align:'center',hidden:'true'},
					//{field:'workDeliveryDate',title:'生产交期',width:100,align:'center',editor:{type:'datebox',options:{required:true}}},  
					{field:'others',title:'其他',width:80,align:'center'}, 
/* 					{field:'attribute',title:'属性',width:100,align:'center', editor:{type:'textbox',options:{required:true}},
						formatter:function(value,row,index){ 
							if(value == undefined){
								return '';
							}else{	
			    	 			return  '<span style="color:red">'+value+'</span>';
			    	 		}
			      		} 		
					},  */
					
 					/*{field:'status',title:'标注',width:80,align:'center',editor:'text',
						formatter:function(value,row,index){ 
							if(value == undefined){
								return '';
							}else{	
			    	 			return  '<span style="color:orange">'+value+'</span>';
			    	 		}
			      		} 			
					}, */
					{field:'stockInQuantity',title:'入库量',width:50,align:'center'},
					{field:'stockOutQuantity',title:'出库量',width:50,align:'center'},
					
					//{field:'stockSafeQuantity',title:'安全库存',width:50,align:'center',editor:'text'},
					//{field:'workQuantity',title:'生产量',width:50,align:'center',editor:'text'},
					{field:'action',title:'Action',width:100,align:'center',					
						formatter:function(value,row,index){
							if(isOnlyRead()==false){
								if (row.editing){							
									var s = '<a href="#" onclick="saverow(this)">保存</a> ';
									var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
									return s+c;
								} else {					
									var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
									//var d = '<a href="#" onclick="deleterow(this)">删除</a>';
									return e;
								}
							}else{
								return '';
							}
						}
					},		
					{field:'idc',title:'ID', width:10,hidden:'true'},
					{field:'orderNumID',title:'orderNumID', width:10,hidden:'true'}
				]],
/* 				onRowContextMenu: function(e, rowIndex, rowData) { //右键时触发事件
	                //三个参数：e里面的内容很多，真心不明白，rowIndex就是当前点击时所在行的索引，rowData当前行的数据
	                e.preventDefault(); //阻止浏览器捕获右键事件
	                //$(this).datagrid("clearSelections"); //取消所有选中项
	                $(this).datagrid("selectRow", rowIndex); //根据索引选中该行
	                $('#menu').menu('show', {
						//显示右键菜单
	                    left: e.pageX,//在鼠标点击处显示菜单
	                    top: e.pageY
	                });
	            }, */
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
		function isOnlyRead(){
			var str = localStorage.getItem('authority1'); 			
	        str = str.substr(13, 1)
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
		function editrow(target){
			$('#tt').datagrid('unselectAll');
			$('#tt').datagrid('selectRow',getRowIndex(target));
			$('#tt').datagrid('beginEdit', getRowIndex(target));
		}
		function deleterow(target){
			$('#tt').datagrid('unselectAll');
			$.messager.confirm('Confirm','是否删除?',function(r){
				if (r){
					var rowIndex = getRowIndex(target);	
					$('#tt').datagrid('selectRow',rowIndex);
	       			var row = $('#tt').datagrid('getSelected');
	       			//var userList = new Array();  
					//userList.push(row);
	       			if(row.idc!=0){
	       			//alert(JSON.stringify(userList));
						$.ajax({  
							type: "post",  
							url: '${pageContext.request.contextPath}/poNum.htm/deleteRow',
							//data:JSON.stringify(userList), 
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
								//alert('删除成功！');		 						
							}, 
							error : function() {
						       	alert("异常！");
						  	}
						});
					}		
					
								
				}else{
					//alert('删除失败，请重新操作。');
				}
			});
		}
		function saverow(target){
			var rowIndex = getRowIndex(target);
			$('#tt').datagrid('endEdit', rowIndex);					
			$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');    
        	var userList = new Array();  
	 		delete row["editing"];
	 		for(var key in row){	
				if(row[key] == ''){
					delete row[key];
				}				
			} 
			userList.push(row);
	       	//alert(JSON.stringify(userList));
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/poNum.htm/saveRow',
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
					/*$('#tt').datagrid('updateRow',{
						index: rowIndex,
						row: result[0]
					});	*/	 						
				}, 
				error : function() {
			       	alert("异常！");
			  	}
			});
						
		}
		function cancelrow(target){
			$('#tt').datagrid('selectRow',getRowIndex(target));
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
					idc:0				
				}
			});
			$('#tt').datagrid('selectRow',index);	
			$('#tt').datagrid('beginEdit',index); 	
		}

		
/* 		function getData(tt, url, para){
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
        } */
        
        $(function(){				
           // $('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/poNum.htm/loadData')}).datagrid('clientPaging');   
            //$('#tt1').datagrid('clientPaging'); 
         	$('#oneKeyDistribution').linkbutton('disable');
         	$('#oneKeyExportExcel').linkbutton('disable');
         	//if(window.parent.window.getOrderNumStatus()!="新定单"){
         	$('#analysis').linkbutton('disable');
         	$('#decrease').linkbutton('disable');
         	//}
			//alert( window.parent.window.getOrderNumID());
			$.ajax({ 
				type: "post",  
				url:'${pageContext.request.contextPath}/poNum.htm/orderNumGetList', 
				data:{orderNumID:window.parent.window.getOrderNumID()},				
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
					//$('#tt').datagrid({data: result}).datagrid('clientPaging');
					$('#tt').datagrid({data: result});
					if(window.parent.window.getOrderNumStatus()=="新工单"){
						if(isOnlyRead()==false){
				         	$('#analysis').linkbutton('enable');
				         	for (var i=0;i<result.length;i++){					
								if(result[i]["workQuantity"] != result[i]["quantity"]){							
									break;
								}else {
									if(i==result.length-1){										
										$('#decrease').linkbutton('enable');
									}
								}						
							} 
				        } 
				        
				    } 
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	
           /*	$('#ss').searchbox({
            	//height:'40px',
			    searcher:function(value,name){
			   // alert(value);
			   		var userList = new Array();  
			    	var data = $('#tt').datagrid('getData');
			 			   
			    	for(var i=0; i<data.rows.length; i++){
			    		//var rowIndex = $('#tt').datagrid('getRowIndex', data.rows[i]);
			    		// 	alert('rowIndex:' + rowIndex);
			    		if(data.rows[i].attribute.indexOf(value) > -1){ 
			    			//$('#tt').datagrid('deleteRow', rowIndex);
			    			//alert(JSON.stringify(data.rows[i]));
			    			userList.push(data.rows[i]);
			    		}
			    	}
			    	//alert(JSON.stringify(userList));
			    	$('#tt').datagrid('loadData', userList);
			    },
			    //menu:'#mm',
			    prompt:'请输入要查询的属性'
			});*/ 	             
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
         
                    });
                }
                /*loadData: function(jq, data){
                    jq.each(function(){
                        $(this).data('datagrid').allRows = null;
                    });
                    return loadDataMethod.call($.fn.datagrid.methods, jq, data);
                },
                deleteRow: function(jq, index){
                    return jq.each(function(){
                        var row = $(this).datagrid('getRows')[index];
                        deleteRowMethod.call($.fn.datagrid.methods, $(this), index);
                    });
                },
                getAllRows: function(jq){
                    return jq.data('datagrid').allRows;
                }*/
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
		/*
		function funOne(tt,url,rowIndex,para){	 
	       	$(tt).datagrid('selectRow',rowIndex);
	       	var row = $(tt).datagrid('getSelected');
        	row["para"] = para;
	       //	alert(JSON.stringify(row));
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
		*/
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
		
		$('#userId').combobox({
		        prompt:'请输入要查询的部件号', 	//提示信息
				//required:true, 	//是否必填
				mode:'remote', 		//动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
				url:'${pageContext.request.contextPath}/poNum.htm/autotimp?orderNumID=' + window.parent.window.getOrderNumID(), 	//请求数据路径
				//data:{orderNum:window.parent.window.getOrderNumID()},
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
		        	record["orderNumID"] = window.parent.window.getOrderNumID();
		        	$.ajax({  
						type: "post",  
						url: '${pageContext.request.contextPath}/poNum.htm/searchMaterialNum',
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
		        },
				inputEvents : $.extend({}, $.fn.combobox.defaults.inputEvents, {
					keyup : function(event) {
						if (event.keyCode == 13) {
							var record = {};
							record["orderNumID"] = window.parent.window.getOrderNumID();
							record["name"] = $("#userId").textbox("getValue");
							$.ajax({
								type : "post",
								url : '${pageContext.request.contextPath}/poNum.htm/searchMaterialNum',
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
									$('#tt').datagrid('loadData', result);
								},
								error : function() {
									alert("异常！");
								}
							});
							$('#userId').combobox("hidePanel");
							$('#userId').combobox('setValue', '');
						}
					}
				}) 
		    });	
		    
		/*
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
		}*/
		
		function excelImportDlg(){
			$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格');	
		}
		Date.prototype.format = function (format) {
           var args = {
               "M+": this.getMonth() + 1,
               "d+": this.getDate(),
               "h+": this.getHours(),
               "m+": this.getMinutes(),
               "s+": this.getSeconds(),
               "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
               "S": this.getMilliseconds()
           };
           if (/(y+)/.test(format))
               format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
           for (var i in args) {
               var n = args[i];
               if (new RegExp("(" + i + ")").test(format))
                   format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));
           }
           return format;
       };
       
		function analysis(){
			//var tab = $('#tt').tabs('getTab', '元数据');
			//var iframeWindow=tab.find('iframe')[0].contentWindow;	    
			//var data = iframeWindow.$('#tt').datagrid("getData").rows;	
			//window.top.window.getDlgOrderNumID();
			var data = $('#tt').datagrid("getData").rows;		
			//alert(JSON.stringify(data));
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/bomTree.htm/analysis',
				data:JSON.stringify(data), 
				//traditional: true,
				dataType:"json", 
				contentType : 'application/json;charset=utf-8', //设置请求头信息  
				beforeSend: function () {
					load();
				},
				complete: function () {					
					disLoad();
					//$('#oneKeyDistribution').linkbutton('enable');
					//$('#oneKeyExportExcel').linkbutton('enable');					
					$('#analysis').linkbutton('disable');
					$('#decrease').linkbutton('disable');
				},	
				success: function(result) {	

					//alert(JSON.stringify(result));
/* 					var t = new Date().format("yyyy-MM-dd hh:mm:ss");				
  					t=t.replace(/[-]/g,"");  
  					t=t.replace(/[:]/g,"");  
					t=t.replace(/[ ]/g,"");  
					window.parent.window.setTimeStamp(t) */
					if(result[0].hasOwnProperty('error')){
						alert(result[0].error);
					}else{	
						oneKeyExportExcel();
						oneKeyDistribution(result);		
						var list1 = result[0].list1;
						var list2 = result[0].list2;
						var list3 = result[0].list3;
						var list4 = result[0].list4;
						var list5 = result[0].list5;
						var list6 = result[0].list6;
						var listErrs = result[0].listErrs;
						var listError = result[0].listError;
						if(listErrs != undefined){
							window.parent.window.addTab('BOM不包含信息', './pf_bd_tables_error.jsp');							
						}else{
							
							if(list1 != undefined){
								window.parent.window.addTab('A01焊接下料', './pf_bd_tables_A01.jsp');
							} 
							if(list2 != undefined){
								window.parent.window.addTab('A02焊接钣金', './pf_bd_tables_A02.jsp');	
							}
							if(list3 != undefined){
								var list3_0 = list3[0].list3_0;
								if(list3_0 != undefined){
									window.parent.window.addTab('A03焊接', './pf_bd_tables_A03_0.jsp');
								}
								var list3_1 = list3[0].list3_1;
								if(list3_1 != undefined){
									window.parent.window.addTab('A03焊接下料', './pf_bd_tables_A03_1.jsp');
								}
								var list3_2 = list3[0].list3_2;
								if(list3_2 != undefined){
									window.parent.window.addTab('A03焊接钣金', './pf_bd_tables_A03_2.jsp');
								}
								var list3_3 = list3[0].list3_3;
								if(list3_3 != undefined){
									window.parent.window.addTab('A03焊接件', './pf_bd_tables_A03_3.jsp');
								}
								var list3_4 = list3[0].list3_4;
								if(list3_4 != undefined){
									window.parent.window.addTab('A03外购', './pf_bd_tables_A03_4.jsp');
								}
							}
							//alert(JSON.stringify(list4));
							if(list4 != undefined){
								var list4_4 = list4[0].list4_4;
								if(list4_4 != undefined){
									window.parent.window.addTab('装配', './pf_bd_tables_A04_4.jsp');
								}
								var list4_1 = list4[0].list4_1;
								if(list4_1 != undefined){
									window.parent.window.addTab('装配下料', './pf_bd_tables_A04_1.jsp');
								}
								var list4_2 = list4[0].list4_2;
								if(list4_2 != undefined){
									window.parent.window.addTab('装配钣金', './pf_bd_tables_A04_2.jsp');
								}
								var list4_3 = list4[0].list4_3;
								if(list4_3 != undefined){
									window.parent.window.addTab('装配焊接', './pf_bd_tables_A04_3.jsp');
								}
		
								var list4_5 = list4[0].list4_5;
								if(list4_5 != undefined){
									window.parent.window.addTab('装配外购', './pf_bd_tables_A04_5.jsp');
								}
								var list4_6 = list4[0].list4_6;
								if(list4_6 != undefined){
									window.parent.window.addTab('装配外协', './pf_bd_tables_A04_6.jsp');
								}
								var list4_7 = list4[0].list4_7;
								if(list4_7 != undefined){
									window.parent.window.addTab('装配喷涂', './pf_bd_tables_A04_7.jsp');
								}
							}
							
							if(list5 != undefined){
								var list5_4 = list5[0].list5_4;
								if(list5_4 != undefined){
									window.parent.window.addTab('732', './pf_bd_tables_A05_4.jsp');
								}
								var list5_1 = list5[0].list5_1;
								if(list5_1 != undefined){
									window.parent.window.addTab('732下料', './pf_bd_tables_A05_1.jsp');
								}
								var list5_2 = list5[0].list5_2;
								if(list5_2 != undefined){
									window.parent.window.addTab('732钣金', './pf_bd_tables_A05_2.jsp');
								}
								var list5_3 = list5[0].list5_3;
								if(list5_3 != undefined){
									window.parent.window.addTab('732焊接', './pf_bd_tables_A05_3.jsp');
								}
		
								var list5_5 = list5[0].list5_5;
								if(list5_5 != undefined){
									window.parent.window.addTab('732外购', './pf_bd_tables_A05_5.jsp');
								}
								var list5_6 = list5[0].list5_6;
								if(list5_6 != undefined){
									window.parent.window.addTab('732外协', './pf_bd_tables_A05_6.jsp');
								}
								var list5_7 = list5[0].list5_7;
								if(list5_7 != undefined){
									window.parent.window.addTab('732喷涂', './pf_bd_tables_A05_7.jsp');
								} 
							}
							if(list6 != undefined){
								window.parent.window.addTab('外购件', './pf_bd_tables_A06.jsp');
							}
							if(listError != undefined){
								window.parent.window.addTab('BOM属性为空的信息', './pf_bd_tables_attribute.jsp');
							}
							
						}
					}
				}, 
				error : function() {
					alert("异常！");
				}
			});
			
		}
		
		function oneKeyDistribution(result){
			//window.parent.window.distribution();
			//alert('asdfasdfe123');
/* 			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/bomTree.htm/analysisItem ?name=all',
				//data:JSON.stringify(userList), 
				traditional: true,
				dataType:"json", 
				//contentType : 'application/json;charset=utf-8', //设置请求头信息  
				beforeSend: function () {
					load();
				},
				complete: function () {
					disLoad();
				},
				success: function(result) {	 */
												
					var list1 = result[0].list1;
					var list2 = result[0].list2;
					var list3 = result[0].list3;
					var list4 = result[0].list4;
					var list5 = result[0].list5;
					var list6 = result[0].list6;
					/*if(list1 != undefined){
						newWorkNum(list1, 'A01焊接下料');										
					} 
					if(list2 != undefined){
						newWorkNum(list2, 'A02焊接钣金');
					}*/
					if(list3 != undefined){
						/*var list3_1 = list3[0].list3_1;
						if(list3_1 != undefined){
							newWorkNum(list3_1, 'A03焊接下料');
						}
						var list3_2 = list3[0].list3_2;
						if(list3_2 != undefined){
							newWorkNum(list3_2, 'A03焊接钣金');
						}
						var list3_3 = list3[0].list3_3;
						if(list3_3 != undefined){
							newWorkNum(list3_3, 'A03焊接');
						}*/
						var list3_4 = list3[0].list3_4;
						if(list3_4 != undefined){
							newMaterialOutStoreNum(list3_4, 'A03外购');
						}
					}
					//alert(JSON.stringify(list4));
					if(list4 != undefined){
/* 						var list4_1 = list4[0].list4_1;
						if(list4_1 != undefined){
							newWorkNum(list4_1, '装配下料');
						}
						var list4_2 = list4[0].list4_2;
						if(list4_2 != undefined){
							newWorkNum(list4_2, '装配钣金');
						}
						var list4_3 = list4[0].list4_3;
						if(list4_3 != undefined){							
							newWorkNum(list4_3, '装配焊接');
						}
						var list4_4 = list4[0].list4_4;
						if(list4_4 != undefined){
							newWorkNum(list4_4, '装配装配');
						} */
						var list4_5 = list4[0].list4_5;
						if(list4_5 != undefined){
							newMaterialOutStoreNum(list4_5, '装配外购');
						}
						var list4_6 = list4[0].list4_6;
						if(list4_6 != undefined){
							newWeiwaiNum(list4_6, '装配外协');
						}
/* 						var list4_7 = list4[0].list4_7;
						if(list4_7 != undefined){
							newWorkNum(list4_7, '装配喷涂');
						} */
					}
					
					if(list5 != undefined){
/* 						var list5_1 = list5[0].list5_1;
						if(list5_1 != undefined){
							newWorkNum(list5_1, '732下料');
						}
						var list5_2 = list5[0].list5_2;
						if(list5_2 != undefined){
							newWorkNum(list5_2, '732钣金');
						}
						var list5_3 = list5[0].list5_3;
						if(list5_3 != undefined){
							newWorkNum(list5_3, '732焊接');
						}
						var list5_4 = list5[0].list5_4;
						if(list5_4 != undefined){
							newWorkNum(list5_4, '732装配');
						} */
						var list5_5 = list5[0].list5_5;
						if(list5_5 != undefined){
							newMaterialOutStoreNum(list5_5, '732外购');
						}
						var list5_6 = list5[0].list5_6;
						if(list5_6 != undefined){
							newWeiwaiNum(list5_6, '732外协');
						}
	/* 					var list5_7 = list5[0].list5_7;
						if(list5_7 != undefined){
							newWorkNum(list5_7, '732喷涂');
						}  */
					}
					if(list6 != undefined){
						//alert('list6');
						newMaterialOutStoreNum(list6, '外购件');
					}
					//$('#oneKeyDistribution').linkbutton('disable');
					$.ajax({ 
						type: "post",  
						url:'${pageContext.request.contextPath}/poNum.htm/updateState', 
						data:{orderNumID:window.parent.window.getOrderNumID(),state:"订单已分配"},				
						traditional: true,
						dataType:"json",  
						error : function() {
					       	alert("出错了！");
					   	}
					});				 						
				/* }, 
				error : function() {
					alert("异常！");
				}
			});		 */
			
		}
		
		function oneKeyExportExcel(){
	        var fileName ="工单信息";
	            //编码防止中文字符乱码
	        window.location.href=encodeURI("${pageContext.request.contextPath}/bomTree.htm/oneKeyExportExcel?fileName="+encodeURIComponent(fileName) + "&orderNum="+ window.parent.window.getOrderNum());
		}
	
		function decrease(){
			$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/poNum.htm/decrease',
					data:{orderNumID:window.parent.window.getOrderNumID()}, 
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
						window.location.reload();
						//alert(JSON.stringify(result));
						//$('#tt').datagrid('loadData', result);  
						//$('#tt').datagrid({data: result}).datagrid('clientPaging');											
					}, 
					error : function() {
				       	alert("异常！");
				  	}
			});    		
		}
		
       function newWorkNum(rows, title){
			//var rows = $('#tt').datagrid('getSelections');  
			var userList = new Array(); 
			//alert(window.parent.window.getTimeStamp())
			userList.push({remark:title,status:window.parent.window.getOrderNum()});
		    for(var i=0; i<rows.length; i++){  
        		userList.push(rows[i]);
			}
			//var userList = $('#tt').datagrid('getData').rows;
			//alert(JSON.stringify(userList));
			$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/workNum.htm/newWorkNum',
					data:JSON.stringify(userList), 
					//traditional: true,
					dataType:"json", 
					contentType : 'application/json;charset=utf-8', //设置请求头信息  
					success: function(result) {	
						//updateStatus(userList,'工单已生成!');		
						//alert("分配成功");														
					}, 
					error : function() {
				       	alert("异常！");
				  	}
			});    		
		}
        
        function newWeiwaiNum(rows, title){
				
			var userList = new Array();      		
			//alert('attribute:' + attribute);
			userList.push({remark:title,status:window.parent.window.getOrderNum()});
			for(var i=0; i<rows.length; i++){
				userList.push(rows[i]);	
			}
				//alert(JSON.stringify(userList));
				$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/weiwaiNum.htm/newWeiwaiNum',
					data:JSON.stringify(userList), 
					//traditional: true,
					dataType:"json", 
					contentType : 'application/json;charset=utf-8', //设置请求头信息  

					success: function(result) {	
						//updateStatus(userList,'外协单已生成！');
						//alert("分配成功");																								
					}, 
					error : function() {
				       	alert("异常！");
				  	}
				});
		} 
		function newMaterialOutStoreNum(rows, title){
			//var rows = $('#tt').datagrid('getSelections');  	
			var flag = true;
			var userList = new Array();      		
			userList.push({remark:title,status:window.parent.window.getOrderNum()});
			//alert('attribute:' + attribute);
			for(var i=0; i<rows.length; i++){ //从1开始是因为每个LIST都加一个名子，在导出时会用到。
				//userList.push(rows[0]);	
				userList.push(rows[i]);		
			}
					//alert(JSON.stringify(userList));
			$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/purchaseNum.htm/newMaterialOutStoreNum',
					data:JSON.stringify(userList), 
					//traditional: true,
					dataType:"json", 
					contentType : 'application/json;charset=utf-8', //设置请求头信息  
					success: function(result) {
						//updateStatus(userList,'外购单已生成！');
						//alert("分配完成")																																	
					}, 
					error : function() {
				       	alert("异常！");
				  	}
			});		
		}
		
		
/* 		function deleteSelected(){
			var userList = new Array();
			var rows = $('#tt').datagrid('getSelections');
			for(var i=0; i<rows.length; i++){
				userList.push(rows[i]);		
				//var rowIndex = $('#tt').datagrid('getRowIndex', rows[i]);	
				//$('#tt').datagrid('deleteRow', rowIndex);							
			}
			//alert (JSON.stringify(userList));
				       			  
			$.ajax({  
				type: "post",  
				url: '${pageContext.request.contextPath}/poNum.htm/deleteRow',
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
					for(var i=0; i<rows.length; i++){	
						var rowIndex = $('#tt').datagrid('getRowIndex', rows[i]);	
						$('#tt').datagrid('deleteRow', rowIndex);							
					}
					alert('删除成功！');			 						
				}, 
				error : function() {
					alert("异常！");
				}
			});				
		} */



 	</script>

</body>
</html>