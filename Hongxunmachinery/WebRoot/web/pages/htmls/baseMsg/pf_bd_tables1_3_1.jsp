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
    <link href="../../css/base.css" rel="stylesheet">
    <link href="../../css/providers1.css" rel="stylesheet">
    <link rel="stylesheet" href="../../../custom/uimaker/easyui.css"> 
 	<link rel="stylesheet" type="text/css" href="../../../custom/uimaker/icon.css">
<!--     <link href="basic_info.css" rel="stylesheet"> -->
<!--     <link rel="stylesheet" type="text/css" href="css/my.css" /> -->
 	<script type="text/javascript" src="../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../custom/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../../js/myjs/jquery.form.js"></script> 

</head>


<body> 
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
 		<div id="tb" style="padding:0 10px;height:auto">	
			<div id="ttdiv" style="width:100%; height:50px;">	
		 		<div style="margin-top:10px; margin-left:10px; float:left">		 	  
					<input id="ss" style="height:30px; "></input>
				</div>	
				<div style="margin-top:10px; margin-left:10px; float:left">		 	  
					<input id="ssAttribute" style="height:30px; "></input>
				</div>	
				<div style="margin-top:10px; margin-left:10px; float:left">		 	  
					<a href="#" class="easyui-linkbutton" style="height:30px;" data-options="iconCls:'icon-table-add', plain:true" onclick="excelImportDlg()">导入表格</a>
				</div>	
				<div style="margin-top:10px; margin-left:10px; float:left">		 	  
					<a href="#" class="easyui-linkbutton" style="height:30px;" data-options="iconCls:'icon-table-go', plain:true" onclick="exportExcel()">导出表格</a>
				</div>						
			</div>						 
		</div> 
		<table id="tt"></table> 
	</div>	
		<div id="importExcelDlg" class="easyui-dialog" style="width:280px;height:150px;padding:10px 0px" data-options="top:10,closed:true">
			<form action='' enctype="multipart/form-data" method="post" name="fileForm"> 
		        <label >
				  	<!-- <input type="file" id="upload" class="file" name="filename" style='opacity:0;filter:alpha(opacity=0);'>  -->
				  	<input type="file" class="file" name="filename" id="fileId" onchange="upload('${pageContext.request.contextPath}/bomPlane.htm/exlImport');" accept=".xls"/>
				</label> 			
			</form> 		
		</div> 
	

	<script>																	
		$(function(){
			$('#tt').datagrid({
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
				pagination:true,
				rownumbers:true,	
				columns:[[
 					{field:'idc',title:'ID',width:20,hidden:'true'},
					{field:'sn',title:'序号',width:40 ,align:'center'},	
					{field:'BomQuantity',title:'数量',width:50,align:'center'},
					{field:'BomSpacification',title:'技术规格',width:150,editor:'text',align:'center'},	
					{field:'BomLength',title:'长度',width:50,align:'center'},  
 					{field:'BomDrawingNum',title:'图号',width:50,align:'center',
 						formatter:function(value,row,index){
 							if(value == null){
 								return '';
 							}else{
								return '<a href="#" onclick="openDrawingNum(\''+value+'\')" >'+value+'</a>&nbsp;';
							}
						}
					},	
					{field:'BomPosion',title:'位置',width:50,align:'center'},
    				{field:'BomMaterial',title:'材料',width:50,align:'center'},
    				{field:'BomMaterialNum',title:'部件号',width:150,editor:'text',align:'left'},
					{field:'attribute',title:'属性',align:'center',width:100,editor:'text'},  
					{field:'BomMaterialCuting',title:'下料',align:'center',width:50,editor:'text'},																		                                       
					{field:'BomPlateCuting',title:'钣金',align:'center',width:50,editor:'text'},  
					{field:'BomMaterialWeld',title:'焊接',align:'center',width:50,editor:'text'},   
					{field:'BomWeiwai',title:'外协',align:'center',width:50,editor:'text'}, 
					{field:'BomPainting',title:'喷涂',align:'center',width:50,editor:'text'}, 
					{field:'BomAssemble',title:'装配',align:'center',width:50,editor:'text'}, 
					{field:'BomLocation',title:'库位编码',width:100,align:'center',editor:'text'},
					{field:'remark',title:'备注',width:100,align:'center',editor:'text'},
					{field:'BomGroup',title:'组',width:100,align:'center'},
					
					{field:'action',title:'Action',width:100,align:'center',
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
				
				]],
				onRowContextMenu: function(e, rowIndex, rowData) { //右键时触发事件
	                //三个参数：e里面的内容很多，真心不明白，rowIndex就是当前点击时所在行的索引，rowData当前行的数据
	                e.preventDefault(); //阻止浏览器捕获右键事件
	                //$(this).datagrid("clearSelections"); //取消所有选中项
	                $(this).datagrid("selectRow", rowIndex); //根据索引选中该行
	                $('#menu').menu('show', {
						//显示右键菜单
	                    left: e.pageX,//在鼠标点击处显示菜单
	                    top: e.pageY
	                });
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
				
		});
		
	</script>	
	
	<script>	

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
	       			var userList = new Array();  
					userList.push(row);
	       			if(row.Idc!=0){
	       			//alert(JSON.stringify(userList));
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
								alert('删除成功！');							
								$('#tt').datagrid('deleteRow', rowIndex);		 						
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
					$('#tt').datagrid('updateRow',{
						index: rowIndex,
						row: result[0]
					});		 						
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
        }
        
        $(function(){				
            $('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/bomPlane.htm/loadData')}).datagrid('clientPaging');                 
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
										$('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/poNum.htm/loadData')}).datagrid('clientPaging');              
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
		        prompt:'请输入要查询的Po#', 	//提示信息
				//required:true, 	//是否必填
				mode:'remote', 		//动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
				url:'${pageContext.request.contextPath}/poNum.htm/autotimp', 	//请求数据路径
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
						url: '${pageContext.request.contextPath}/poNum.htm/searchPoNum',
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
		}
		
		function excelImportDlg(){
			$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格');	
		}
		function upload(url) { 
    			var fileName = $('#fileId').val().split('\\'); //得到文件名数组
    			var fileSize =  document.getElementById('fileId').files[0]; //获得文件大小；
    			fileName2 = fileName[fileName.length-1]; // 获得文件名
    			filePath = $('#fileId').val().toLowerCase().split(".");
    			fileType =  filePath[filePath.length - 1]; //获得文件结尾的类型如 zip rar 这种写法确保是最后的
    			if(!(fileType == "xls")){
    			 	alert('文件格式不符合要求！');
    			}else if(fileSize.size>10485760){
    			  	alert('文件超过10485760')		       
    			}else{
    			   	//alert('asdfsa');
    				var form = $("form[name=fileForm]"); 
    				var options = {  
    					url:url, //'${pageContext.request.contextPath}/numTen.htm/exlImport', //上传文件的路径  
    					type:'post',
    					clearForm:true,
    					success:function(data){
    						$('#importExcelDlg').dialog('close');	
    						//$('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/poNum.htm/loadData')}).datagrid('clientPaging');
    					},
    					beforeSubmit: function () {						
    						load();			        
    					},
    					complete: function () {
    						disLoad();	
    					}
    				};  
    				form.ajaxSubmit(options); 
    				//alert('123');
    			}
        } 
        
		function newWorkNum(){
			var rows = $('#tt').datagrid('getSelections');  
			var attribute = null;
			var flag = true;
			var userList = new Array();      		
			for(var i=0; i<rows.length; i++){
				if(rows[i].attribute != undefined){
					if(attribute == null){
						attribute = rows[i].attribute;
					}else if(rows[i].attribute.length < attribute.length){
						attribute = rows[i].attribute;
					}
				}else{
					alert('错误，定单属性为空。');
					return;
				}
			}
			
			//alert('attribute:' + attribute);
			if(attribute.indexOf(",") > -1){
				$.messager.prompt('My Title', 'Please type something', function(r){
					if (r){
						createWorkNum(rows,flag,r,userList)
					}
				});
			}else{
				createWorkNum(rows,flag,attribute,userList)
			}
			

		}
		
		function createWorkNum(rows,flag,attribute,userList){	
			for(var i=0; i<rows.length; i++){
				if(rows[i].attribute.indexOf(attribute) < 0){
					flag = false;
					alert('属性设置或输入信息有错误');
					break;
				}else{
					//delete rows[i]["poDeliveryDate"]; 	
					//delete rows[i]["poCreateDate"]; 
					if(rows[i].status != null){
						rows[i].status = rows[i].status + ',' + attribute;
					}else{
						rows[i].status = attribute;
					}	
					userList.push(rows[i]);
				}	
			}		
			
			if(flag == true){
				//alert(JSON.stringify(userList));
				//funOne('#tt', '${pageContext.request.contextPath}/numThree.htm/newWorkNum','',JSON.stringify(userList), 'saverow');
				$.ajax({  
					type: "post",  
					url: '${pageContext.request.contextPath}/workNum.htm/newWorkNum',
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
						updateStatus(userList,'工单已生成!');																
					}, 
					error : function() {
				       	alert("异常！");
				  	}
				});
			}		
		}
		
	
 	</script>
</body>

</html>


