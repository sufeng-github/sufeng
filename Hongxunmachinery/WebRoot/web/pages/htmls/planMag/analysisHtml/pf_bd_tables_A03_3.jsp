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
<!--     <link href="basic_info.css" rel="stylesheet"> -->
<!--     <link rel="stylesheet" type="text/css" href="css/my.css" /> -->
 	<script type="text/javascript" src="../../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../js/myjs/datagrid-export.js"></script>     
    <script type="text/javascript" src="../../../../custom/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../../../js/myjs/jquery.form.js"></script> 
</head>


<body> 
	<div class="easyui-layout" data-options="fit:true" style="padding:5px;background:#eee;">	
 		<div id="tb" style="padding:0 10px;height:auto">	
			<div id="ttdiv" style="width:100%; height:50px;">	
				<div style="margin-top:5px; margin-left:10px; float:left">			 	  
		        	<a href="javascript:;"  class="easyui-linkbutton" onclick="$('#tt').datagrid('toExcel','dg.xls')">导出Exel</a>
		        	<a href="javascript:;"  class="easyui-linkbutton" onclick="$('#tt').datagrid('print','DataGrid')">打印</a>				
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
	<div id="menu" class="easyui-menu" style="width: 50px; display: none;">
	    <!--放置一个隐藏的菜单Div-->
	    <div data-options="iconCls:'icon-save'" onclick="newWorkNum()">生成工单</div>
	    <div data-options="iconCls:'icon-save'" onclick="newWeiwaiNum()">生成外协单</div>
	</div>

	<script>																	
		$(function(){
			$('#tt').datagrid({
				width:'100%',
				height:'100%',
				toolbar:'#tb',
				url:'${pageContext.request.contextPath}/bomTree.htm/analysisItem ?name=list3_3',
				singleSelect:false,
				fitColumns:true,
				autoRowHeight:false,
				striped:true,
				method:'post',
				remoteSort:false,
				multiSort:true, 
				pagination:true,
				pageList: [10,100,400,800,1000],
				rownumbers:true,	
				columns:[[
					{field:'ck', checkbox:true},
 					{field:'idc',title:'ID',width:20,hidden:'true'},
					{field:'sn',title:'序号',width:40 ,align:'center'},	
					{field:'bomQuantity',title:'单梯',width:50,align:'center'}, 
					{field:'bomSpacification',title:'技术规格',width:150,editor:'text',align:'center'},	
					{field:'bomLength',title:'长度',width:50,align:'center'},  
 					{field:'bomDrawingNum',title:'图号',width:120,align:'center',
 						formatter:function(value,row,index){
 							if(value == null){
 								return '';
 							}else{
								return '<a href="#" onclick="openDrawingNum(\''+value+'\')" >'+value+'</a>&nbsp;';
							}
						}
					},	
					{field:'bomPosion',title:'位置',width:50,align:'center'},
    				{field:'bomMaterial',title:'材料',width:50,align:'center'},
    				{field:'bomMaterialNum',title:'部件号',width:150,sortable: true,editor:'text',align:'left'},
					{field:'attribute',title:'属性',align:'center',width:100,editor:'text'}, 
					{field:'bomItemQuantity',title:'项目数',width:50,align:'center'}, 
					{field:'bomMaterialCuting',title:'下料',align:'center',width:50,editor:'text'},																		                                       
					{field:'bomPlateCuting',title:'钣金',align:'center',width:50,editor:'text'},  
					{field:'bomMaterialWeld',title:'焊接',align:'center',width:50,editor:'text'},   
					{field:'bomWeiwai',title:'外协',align:'center',width:50,editor:'text'}, 
					{field:'bomPainting',title:'喷涂',align:'center',width:50,editor:'text'}, 
					{field:'bomAssemble',title:'装配',align:'center',width:50,editor:'text'}, 
					{field:'status',title:'标注',width:80,align:'center',editor:'text',
						formatter:function(value,row,index){ 
							if(value == undefined){
								return '';
							}else{	
			    	 			return  '<span style="color:orange">'+value+'</span>';
			    	 		}
			      		} 			
					},				
					{field:'remark',title:'备注',width:100,align:'center',editor:'text'},
					{field:'bomOrderNum',title:'定单号',align:'center',width:60,editor:'text'},	
					{field:'bomDeliveryDate',title:'交货日期',width:100,align:'center',editor:'text'},
					{field:'bomLine',title:'行号',width:100,align:'center'},
					
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
					}
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
			}).datagrid('clientPaging');	
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
					$('#tt').datagrid('deleteRow', rowIndex);							
				}else{
					alert('删除取消！');
				}
			});
		}
		function saverow(target){
			var rowIndex = getRowIndex(target);
			$('#tt').datagrid('endEdit', rowIndex);					
			$('#tt').datagrid('selectRow',rowIndex);
	       	var row = $('#tt').datagrid('getSelected');   
	       	$('#tt').datagrid('updateRow', {
		       	index: rowIndex,
		       	row:row}
	       	); 
	       	$('#tt').datagrid('refreshRow',rowIndex);   		    					
		}
		
		function cancelrow(target){
			$('#tt').datagrid('selectRow',getRowIndex(target));
			$('#tt').datagrid('cancelEdit', getRowIndex(target));
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
                    });
                },
                getAllRows: function(jq){
                    return jq.data('datagrid').allRows;
                }
            })
        })(jQuery);
	</script>
	
	<script>
/*  		$(function(){				
            $('#tt').datagrid().datagrid('clientPaging');   

        });  */
      
     
		function excelImportDlg(){
			$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格');	
		}
		
        function updateStatus(userList,mesg){
			
					//alert(JSON.stringify(result));	
					  
					var rows = $('#tt').datagrid('getSelections');						
					for(var i=0; i<rows.length; i++){
						//alert(result[0].status);
						var rowIndex = $('#tt').datagrid('getRowIndex', rows[i]);
						//alert(rowIndex);
						$('#tt').datagrid('updateRow',{
							index: rowIndex,
							row: {
								status: '已分配'
							}
						});
						$('#tt').datagrid('refreshRow',rowIndex);
					}
		}
		
		function newWorkNum(){
			var rows = $('#tt').datagrid('getSelections');  
			var userList = new Array(); 
			//alert(window.parent.window.getTimeStamp())
			
			userList.push({remark:'A03焊接焊接',status:window.parent.window.getTimeStamp()});
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
					beforeSend: function () {
						load();
					},
					complete: function () {
						disLoad();
					},
					success: function(result) {	
						//updateStatus(userList,'工单已生成!');			
						alert("分配成功");															
					}, 
					error : function() {
				       	alert("异常！");
				  	}
			});    		
		}
		
	function newWeiwaiNum(){
		var rows = $('#tt').datagrid('getSelections');  	
			var userList = new Array();      		
			//alert('attribute:' + attribute);
			userList.push({remark:'A01焊接焊接委外',status:window.parent.window.getTimeStamp()});
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
					beforeSend: function () {
						load();
					},
					complete: function () {
						disLoad();
					},
					success: function(result) {	
						//updateStatus(userList,'外协单已生成！');	
						alert("分配成功");																							
					}, 
					error : function() {
				       	alert("异常！");
				  	}
				});
		} 
	
 	</script>
</body>

</html>


