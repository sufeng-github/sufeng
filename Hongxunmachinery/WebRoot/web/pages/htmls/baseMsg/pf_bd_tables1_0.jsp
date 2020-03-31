<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>TreeGrid - jQuery EasyUI Demo</title>
 	<link rel="stylesheet" href="../../../custom/uimaker/easyui.css"> 
	<link rel="stylesheet" type="text/css" href="../../../custom/uimaker/icon.css">    
    <link href="../../css/basic_info.css" rel="stylesheet">
    <link rel="stylesheet" href="../../font-awesome-4.7.0/css/font-awesome.min.css" media="screen" type="text/css"/>
 	<script type="text/javascript" src="../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../../custom/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="../../js/myjs/jquery.form.js"></script> 
	
</head>
<body>
<div class="easyui-layout"  data-options="fit:true">	
	<div data-options="region:'center'" style="padding:5px;background:#eee;">
	<!--树形表格  -->
	 	<div id="tb" style="height:50px;">	
			<div style="margin-top:10px; margin-left:20px; float:left">		
				<input class="easyui-combobox" id="userId" style="width:200px; height:35px;"></input>
			</div>	
			
 			<div style="margin-top:10px; margin-left:10px; float:left">		 	  
				<!-- <input id="ssAttribute" style="height:30px; "></input> -->
				<input id="sbutton" class="easyui-switchbutton" checked onText="启动" offText="禁止" style="width:100px;height:35px">
			</div>	 
<!-- 			<div style="margin-top:10px; margin-left:10px; float:left">		 	  
				<a href="#" class="easyui-linkbutton" style="height:30px;" data-options="iconCls:'icon-table-add', plain:true" onclick="excelImportDlg()">导入表格</a>
			</div>	
			<div style="margin-top:10px; margin-left:10px; float:left">		 	  
				<a href="#" class="easyui-linkbutton" style="height:30px;" data-options="iconCls:'icon-table-go', plain:true" onclick="exportExcel()">导出表格</a>
			</div>	 -->					
		</div>
		<div id="importExcelDlg" class="easyui-dialog" style="width:280px;height:150px;padding:10px 0px" data-options="top:10,closed:true">
			<form action='' enctype="multipart/form-data" method="post" name="fileForm"> 
		        <label >
		        	<input type="text" id="nodeID" name="nodeID" style="display:none" />
				  	<!-- <input type="file" id="upload" class="file" name="filename" style='opacity:0;filter:alpha(opacity=0);'>  -->
				  	<input type="file" class="file" name="filename" id="fileId" onchange="upload('${pageContext.request.contextPath}/bomTree.htm/exlImport');" accept=".xls"/>
				</label> 			
			</form> 		
		</div> 
		<table id="tg" ></table>
	<!--添加/编辑弹出框  子-->

		<div id="dd_tg">
<!--  	<form id="fm_tg">	   -->
 		  <mvc:form id="fm_tg" modelAttribute="bomtree" method="post">
			<!-- <input type="hidden" name="ID" id="ID">
 		     <input type="hidden" name="parentID" id="parentID">
 			 <p> 部件号:<input name="flx" id="flx" readonly="readonly"><p/> 
			 <p> 部件号:<input name="bomMaterialNum" id="bomMaterialNum"><p/>
			 <p> 数量:&nbsp;&nbsp;&nbsp;<input name="bomQuantity" id="bomQuantity"><p/>
			 <p> 技术规格:&nbsp;&nbsp;&nbsp;<input name="bomSpacification" id="bomSpacification"><p/>
			 <p> 长度:&nbsp;&nbsp;&nbsp;<input name="bomLength" id="bomLength"><p/>
			 <p> 图号:&nbsp;&nbsp;&nbsp;<input name="bomDrawingNum" id="bomDrawingNum"><p/>
			 <p> 位置:&nbsp;&nbsp;&nbsp;<input name="bomPosion" id="bomPosion"><p/>
			 <p> 材料&nbsp;&nbsp;&nbsp;<input name="bomMaterial" id="bomMaterial"><p/> -->
			   	<input type="hidden" name="idc" id="idc">
 		     	<input type="hidden" name="parentID" id="parentID">
 		     	<input type="hidden" name="nodeID">
				<div>
						<table > 
		                    <tr> 
		                        <td style="width:80px;text-align:right;"> 部件号 </td> 
		                        <td> <input  name="bomMaterialNum" id="bomMaterialNum" class="easyui-textbox" style="width:120px;" data-options="validType:'text'"> </td>                     
		                        <td style="width:70px;text-align:right;"> 数量 </td> 
		                        <td> <input  name="bomQuantity" id="bomQuantity" class="easyui-textbox" style="width:105px;"> </td>    	                    
		                   		<td style="width:70px;text-align:right;"> 材料 </td> 
		                        <td> <input name="bomMaterial" id="bomMaterial" class="easyui-textbox" style="width:105px;" data-options="validType:'text'"> </td> 							
		                    </tr> 
		                </table>     
				</div>
				<div>
						<table > 
							<tr> 
		                        <td style="width:80px;text-align:right;"> 技术规格 </td> 
		                        <td> <input name="bomSpacification" id="bomSpacification" class="easyui-textbox" style="width:120px;" data-options="validType:'text'"> </td> 
		                       	<td style="width:70px;text-align:right;"> 长度 </td> 
		                       	<td> <input  name="bomLength" id="bomLength" class="easyui-textbox" style="width:105px;"> </td>                         
								<td style="width:70px;text-align:right;"> 图号 </td> 
		                        <td> <input name="bomDrawingNum" id="bomDrawingNum" class="easyui-textbox" style="width:105px;" data-options="validType:'text'"> </td> 		                   
		                    </tr> 
		                </table> 
				</div>
				<div>

						<table > 
		                    <tr> 
	   							<td style="width:80px;text-align:right;"> 位置 </td> 
		                       	<td> <input name="bomPosion" id="bomPosion" class="easyui-textbox" style="width:120px;"> </td>
		                      	<td style="width:70px;text-align:right;"> 序号 </td> 
		                       	<td> <input  name="sn" id="sn" class="easyui-textbox" style="width:105px;"> </td>                         
								<td style="width:70px;text-align:right;"> 属性 </td> 
		                        <td> <input name="attribute" id="attribute" class="easyui-textbox" style="width:105px;" data-options="validType:'text'"> </td> 		                   
		                    </tr> 
		                </table> 
		                <table > 
		                    <tr> 
	   							<td style="width:80px;text-align:right;"> 下料 </td> 
		                       	<td> <input name="bomMaterialCuting" id="bomMaterialCuting" class="easyui-textbox" style="width:120px;"> </td>
		                      	<td style="width:70px;text-align:right;"> 库位代码 </td> 
		                       	<td> <input  name="bomLocation" id="bomLocation" class="easyui-textbox" style="width:105px;"> </td>                         
								<td style="width:70px;text-align:right;"> 钣金</td> 
		                        <td> <input name="bomPlateCuting" id="bomPlateCuting" class="easyui-textbox" style="width:105px;" data-options="validType:'text'"> </td> 		                   
		                    </tr> 
		                </table>
		                <table > 
		                    <tr> 
	   							<td style="width:80px;text-align:right;"> 焊接 </td> 
		                       	<td> <input name="bomMaterialWeld" id="bomMaterialWeld" class="easyui-textbox" style="width:120px;"> </td>            
		                   		<td style="width:70px;text-align:right;"> 外协 </td> 
		                       	<td> <input  name="bomWeiwai" id="bomWeiwai" class="easyui-textbox" style="width:105px;"> </td>                         
								<td style="width:70px;text-align:right;"> 喷涂</td> 
		                        <td> <input name="bomPainting" id="bomPainting" class="easyui-textbox" style="width:105px;" data-options="validType:'text'"> </td> 		                   
		                    </tr> 
		                </table> 	 
		                <table > 
		                    <tr> 
	   							<td style="width:80px;text-align:right;"> 装配 </td> 
		                       	<td> <input name="bomAssemble" id="bomAssemble" class="easyui-textbox" style="width:120px;"> </td>            
		                   		<td style="width:70px;text-align:right;"> 备注 </td> 
		                       	<td> <input  name="remark" id="remark" class="easyui-textbox" style="width:105px;"> </td>                         
								<td style="width:70px;text-align:right;"> 组</td> 
		                        <td> <input name="bomGroup" id="bomGroup" class="easyui-textbox" style="width:105px;" data-options="validType:'text'"> </td> 		                   
		                    </tr> 
		                </table>        
				</div>
<!-- 	      </form> -->
	      </mvc:form>
		</div>
		<div id="mm" class="easyui-menu" style="width: 120px;">
			<!-- <div data-options="iconCls:'icon-add'" onclick="add_tg()">新增</div> -->
			<div onclick="edit_tg()"><i class="fa fa-pencil fa-fw"></i> 更改</div>
			<div onclick="dele_tg()"><i class="fa fa-trash-o fa-fw"></i> 删除</div>
 		 	<div onclick="importItemExcel()"><i class="fa fa-sign-in fa-fw"></i> 导入</div>
 			<div onclick="findParent()"><i class="fa fa-arrow-circle-up fa-fw"></i>查看上层</div>
 			
		<!--	<div data-options="iconCls:'icon-table-go'" onclick="exportExcel()">导出</div> -->
		</div>
	</div>
	
	<div id="dialog"  class="easyui-dialog" style="width:80%;height:80%;padding:0px" data-options="
			closed:true,
			resizable:true,		
			onResize:function(){
				$(this).dialog('center');
			}">


	</div>	
</div>	
		
	<script>
	//***************************************************************************************树形表格(数据库用一个表,用parentid关联)
		$(function(){
			$('#dd_tg').hide();

			$('#tg').treegrid({
				//title:'树形表格',
				//iconCls:'icon-save',			
				width:'100%',                                         
				height:'100%',                                           
				nowrap: true,
				animate:true,	
				rownumbers:true,
				singleSelect:true,
				fitColumns:true,
				toolbar:'#tb',
				striped:true,
				autoRowHeight:false,
				method:'post',
				collapsible:true,                    
				//url:'${pageContext.request.contextPath}/bomTree.htm/loadData',
				idField:'idc',                                     
				treeField:'bomMaterialNum',
				//pagination: true,
				//pageSize: 10,
				//pageList: [10,20,30,40,50],
				columns:[[ 
					{field:'ck', checkbox:true},
 					{field:'idc',title:'ID',width:20,hidden:'true'},
 					{field:'bomMaterialNum',title:'部件号',width:150,editor:'text',align:'left'},
					{field:'sn',title:'序号',width:40 ,align:'center'},	
					{field:'bomQuantity',title:'数量',width:40,align:'center'},
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
					{field:'attribute',title:'属性',align:'center',width:100,editor:'text'},  
					{field:'bomMaterialCuting',title:'下料',align:'center',width:50,editor:'text'},																		                                       
					{field:'bomPlateCuting',title:'钣金',align:'center',width:50,editor:'text'},  
					{field:'bomMaterialWeld',title:'焊接',align:'center',width:50,editor:'text'},   
					{field:'bomWeiwai',title:'外协',align:'center',width:50,editor:'text'}, 
					{field:'bomPainting',title:'喷涂',align:'center',width:50,editor:'text'}, 
					{field:'bomAssemble',title:'装配',align:'center',width:50,editor:'text'}, 
					{field:'bomLocation',title:'库位编码',width:100,align:'center',editor:'text'},
					{field:'remark',title:'备注',width:100,align:'center',editor:'text'},
					{field:'bomGroup',title:'组',width:100,align:'center'},
					{field:'parentID',title:'parentID',width:10,align:'center',hidden:'true'},
					{field:'nodeID',title:'nodeID',width:10,align:'center'}
					/*{field:'action',title:'Action',width:100,align:'center',
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
				]],
  				onLoadSuccess:function(data){
                	//delete $(this).treegrid('options').queryParams['id'];  
                	//alert(1);
            	},           
				onContextMenu: function(e, row){
					if(isOnlyRead() == false){
						e.preventDefault();
						$(this).treegrid('unselectAll');
						$(this).treegrid('select',row.idc);
						$('#mm').menu('show', {
							left:e.pageX,
							top:e.pageY				
						});
					}
				}
			});
		});
		  
        $(function(){				
            $('#tg').treegrid({data:getData('#tg','${pageContext.request.contextPath}/bomTree.htm/loadData')});   
            $('#tg').treegrid('options').url = '${pageContext.request.contextPath}/bomTree.htm/loadData';                  
        }); 

		function isOnlyRead(){
			var str = localStorage.getItem('authority1'); 		
			//alert(str);	
	        str = str.substr(17, 1)
			if(str.charAt(0)==1){	
	        	return true;					        			
	        }else{
	        	return false;
	        }    		        	    	         		
		}

		function getData(tg, url){
           	$.ajax({ 
				type: "post",  
				url:url,
				traditional: true,
				dataType:"json",  
				beforeSend: function () {
					load();
					/*$.messager.progress({ 
				       　　title: '提示', 
				       　　msg: '文件上传中，请稍候……', 
				       　　text: '' 
				    　　});*/
				},
				complete: function () {
					disLoad();
					//$.messager.progress('close');
				},
				success: function(result) {
					//$(tt).datagrid('loadData', result);
					$(tg).treegrid('loadData', result);					
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	
        }


		(function($){
			function pagerFilter(data){
		        if ($.isArray(data)){    // is array  
		            data = {  
		                total: data.length,  
		                rows: data  
		            }  
		        }
		        var dg = $(this);  
				var state = dg.data('treegrid');
		        var opts = dg.treegrid('options');  
		        var pager = dg.treegrid('getPager');  
		        pager.pagination({  
		            onSelectPage:function(pageNum, pageSize){  
		                opts.pageNumber = pageNum;  
		                opts.pageSize = pageSize;  
		                pager.pagination('refresh',{  
		                    pageNumber:pageNum,  
		                    pageSize:pageSize  
		                });  
		                dg.treegrid('loadData',state.originalRows);  
		            }  
		        });  
		        if (!state.originalRows){
		        	state.originalRows = data.rows;
		        }
		        var topRows = [];
		        var childRows = [];
		        $.map(state.originalRows, function(row){
		        	row._parentId ? childRows.push(row) : topRows.push(row);
		        });
		        data.total = topRows.length;
		        var start = (opts.pageNumber-1)*parseInt(opts.pageSize);  
		        var end = start + parseInt(opts.pageSize);  
				data.rows = $.extend(true,[],topRows.slice(start, end).concat(childRows));
				return data;
			}
 
			var appendMethod = $.fn.treegrid.methods.append;
			var loadDataMethod = $.fn.treegrid.methods.loadData;
			$.extend($.fn.treegrid.methods, {
				clientPaging: function(jq){
					return jq.each(function(){
						var state = $(this).data('treegrid');
						var opts = state.options;
						opts.loadFilter = pagerFilter;
						var onBeforeLoad = opts.onBeforeLoad;
						opts.onBeforeLoad = function(row,param){
							state.originalRows = null;
							onBeforeLoad.call(this, row, param);
						}
						$(this).treegrid('loadData', state.data);
						$(this).treegrid('reload');
					});
				},
				loadData: function(jq, data){
					jq.each(function(){
						$(this).data('treegrid').originalRows = null;
					});
					return loadDataMethod.call($.fn.treegrid.methods, jq, data);
				},
				append: function(jq, param){
					return jq.each(function(){
						var state = $(this).data('treegrid');
						if (state.options.loadFilter == pagerFilter){
							$.map(param.data, function(row){
								row._parentId = row._parentId || param.parent;
								state.originalRows.push(row);
							});
							$(this).treegrid('loadData', state.originalRows);
						} else {
							appendMethod.call($.fn.treegrid.methods, jq, param);
						}
					})
				}
			});
 
		})(jQuery);


 //******************************************************************************添加
                      
		function add_tg(){
			$("#fm_tg").form("clear");
			var s='';
			var node = $('#tg').treegrid('getSelected');
			if (node){
				$('#dd_tg').show();
				s='#dd_tg';
				$(''+s).dialog({   
				    title: '添加/编辑',   
				    width: 640,   
				    height: 480,   
				    closed: false,   
				    cache: false,   
				    modal: true,
				    buttons:[{
						text:'保存',
						iconCls:'icon-save',
						handler:function(){
 							var data = [{						
									idc:0,
									bomMaterialNum: $("#fm_tg").find('input[name=bomMaterialNum]').val(),
									bomQuantity: $("#fm_tg").find('input[name=bomQuantity]').val(),
									bomSpacification: $("#fm_tg").find('input[name=bomSpacification]').val(),
									bomLength: $("#fm_tg").find('input[name=bomLength]').val(),
									bomDrawingNum: $("#fm_tg").find('input[name=bomDrawingNum]').val(),
									bomPosion: $("#fm_tg").find('input[name=bomPosion]').val(),
									bomMaterial: $("#fm_tg").find('input[name=bomMaterial]').val(),
									parentID: node.idc,
									sn: $("#fm_tg").find('input[name=sn]').val(),
									attribute: $("#fm_tg").find('input[name=attribute]').val(),
									bomMaterialCuting: $("#fm_tg").find('input[name=bomMaterialCuting]').val(),
									bomLocation: $("#fm_tg").find('input[name=bomLocation]').val(),
									bomPlateCuting: $("#fm_tg").find('input[name=bomPlateCuting]').val(),					
									bomMaterialWeld: $("#fm_tg").find('input[name=bomMaterialWeld]').val(),
									bomMaterialWeld: $("#fm_tg").find('input[name=bomMaterialWeld]').val(),	
									bomWeiwai: $("#fm_tg").find('input[name=bomWeiwai]').val(),	
									bomPainting: $("#fm_tg").find('input[name=bomPainting]').val(),	
	   								bomAssemble: $("#fm_tg").find('input[name=bomAssemble]').val(),	
	   								remark: $("#fm_tg").find('input[name=remark]').val(),	
	   								bomGroup: $("#fm_tg").find('input[name=bomGroup]').val(),	
							}] ; 
								
							$.ajax({ 
									type: "post",  
									url:'${pageContext.request.contextPath}/bomTree.htm/saveRow',
									data:data[0],
									//data:$('#fm_tg').serialize(),
									traditional: true,
									dataType:"json",  
									beforeSend: function () {
										load();
									},
									complete: function () {
										disLoad();
									},
									success: function(result) {
										/*$.messager.show({
											title:'提示信息',
											msg:'操作成功！'
										});*/
										//alert(JSON.stringify(result));
										$('#tg').treegrid('append',{
											parent: node.idc,  
			 								data: result
										});
										$(''+s).window('close');
									},
									error : function() {
								       	alert("出错了！");
								   	}
							});	
					         
						}
					},{
						text:'关闭',
						iconCls:'icon-cancel',
						handler:function(){
							$(''+s).window('close');
						}
					}]
				}); 
			}	
		}
//*********************************************************************编辑
		function edit_tg(){
			$("#fm_tg").form("clear");
			var s='';
			var node = $('#tg').treegrid('getSelected');
			if (node){
/* 					var node = $('#tg').treegrid('getSelected');
					var nodep = $('#tg').treegrid('getParent',node.ID);
					var nodec = $('#tg').treegrid('getChildren',node.ID);
					var nodes = $('#tg').treegrid('getChildren');      //*******************treegrid('getChildren')获取的是该节点本身及(其所有子节点)或(兄弟节点和父节点)
					var f_id;
					var f_lx;
					if(!(nodep!=null && node.ID!=nodep.ID && (nodec[0]==null))){                                 //**************************判断点击的节点是否是父节点
								f_id=node.id;
								f_lx=node.type;
								$('#ffid').val(f_id);
								$('#fflx').val(f_lx);
								$('#ddf_tg').show();
								 s='#ddf_tg';
					}else{} */
					
								//f_id=nodep.ID;
								//f_lx=nodep.type;
					$('#dd_tg').show();
					s='#dd_tg';																						
					$('#fm_tg').form('load',node);
						
					
		 		$(''+s).dialog({   
				    title: '编辑',   
				    width: 640,   
				    height: 480,   
				    closed: false,   
				    cache: false,   
				    modal: true,
				    buttons:[{
						text:'保存',
						iconCls:'icon-save',
						handler:function(){
/* 							$.post("#", $("#fm_tg").serialize(),function(data){
									$('#tg').treegrid('reload');
									$(''+s).window('close');
									}
							); */

							$('#tg').treegrid('update',{
								id: node.idc,  // the node has a 'id' value that defined through 'idField' property
 								row: {
									bomMaterialNum: $("#fm_tg").find('input[name=bomMaterialNum]').val(),
									bomQuantity: $("#fm_tg").find('input[name=bomQuantity]').val(),
									bomSpacification: $("#fm_tg").find('input[name=bomSpacification]').val(),
									bomLength: $("#fm_tg").find('input[name=bomLength]').val(),
									bomDrawingNum: $("#fm_tg").find('input[name=bomDrawingNum]').val(),
									bomPosion: $("#fm_tg").find('input[name=bomPosion]').val(),
									bomMaterial: $("#fm_tg").find('input[name=bomMaterial]').val(),
									parentID: $("#fm_tg").find('input[name=parentID]').val(),
									nodeID: $("#fm_tg").find('input[name=nodeID]').val(),
									sn: $("#fm_tg").find('input[name=sn]').val(),
									attribute: $("#fm_tg").find('input[name=attribute]').val(),
									bomMaterialCuting: $("#fm_tg").find('input[name=bomMaterialCuting]').val(),
									bomLocation: $("#fm_tg").find('input[name=bomLocation]').val(),
									bomPlateCuting: $("#fm_tg").find('input[name=bomPlateCuting]').val(),					
									bomMaterialWeld: $("#fm_tg").find('input[name=bomMaterialWeld]').val(),
									bomMaterialWeld: $("#fm_tg").find('input[name=bomMaterialWeld]').val(),	
									bomWeiwai: $("#fm_tg").find('input[name=bomWeiwai]').val(),	
									bomPainting: $("#fm_tg").find('input[name=bomPainting]').val(),	
	   								bomAssemble: $("#fm_tg").find('input[name=bomAssemble]').val(),	
	   								remark: $("#fm_tg").find('input[name=remark]').val(),	
	   								bomGroup: $("#fm_tg").find('input[name=bomGroup]').val(),	
								}
								//data:$("#fm_tg").serialize()
							});
							
							$.ajax({ 
									type: "post",  
									url:'${pageContext.request.contextPath}/bomTree.htm/saveRow',
									data:$('#fm_tg').serialize(),
									traditional: true,
									dataType:"json",  
									beforeSend: function () {
										load();
									},
									complete: function () {
										disLoad();
									},
									success: function(result) {
										$(''+s).window('close');
									},
									error : function() {
								       	alert("出错了！");
								   	}
							});	
							//$(''+s).window('close');
						}
					},{
						text:'关闭',
						iconCls:'icon-cancel',
						handler:function(){
							$(''+s).window('close');
						}
					}]
				}); 
			}
		}
//*********************************************************************************删除
		function dele_tg(){
			$.messager.confirm("提示信息","确认信息",function(r){
				 var node = $('#tg').treegrid('getSelected');
				 if (node){
					$('#fm_tg').form('load',node);
					$.ajax({ 
						type: "post",  
						url:'${pageContext.request.contextPath}/bomTree.htm/deleteRow',
						data:$('#fm_tg').serialize(),
						traditional: true,
						dataType:"json",  
						beforeSend: function () {
							load();
						},
						complete: function () {
							disLoad();
						},
						success: function(result) {
							$('#tg').treegrid('remove', node.idc);
							$.messager.show({
								title:'提示信息',
								msg:'操作成功！'
							});
						},
						error : function() {
					       	alert("出错了！");
					   	}
					});	
				}
			});
		}
		/******************************************************************************************************/
		function findParent() {
			var node = $('#tg').treegrid('getSelected');
			if (node) {
				//alert(node.bomMaterialNum);
				getData('#tg', '${pageContext.request.contextPath}/bomTree.htm/findParent' + '?parentID=' + node.parentID);
			}
		}
	
		function importItemExcel() {
			var node = $('#tg').treegrid('getSelected');
			if (node.parentID == "99999") {
				$('#importExcelDlg').dialog('open').dialog('setTitle', '导入表格');
			} else {
				alert('请选中BOM节点！')
			}
		}
	
		function inputEnter() {
			var qvalue = $("#userId").textbox("getValue");
			//alert(qvalue);
			var data = {}
			data["name"] = $("#userId").textbox("getValue");
			$.ajax({
					type : "post",
					url : '${pageContext.request.contextPath}/bomTree.htm/onselect',
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
						$('#tg').treegrid('loadData', result);
	
					},
					error : function() {
						alert("异常！");
					}
			});
		}
		$('#userId').combobox({
			prompt : '请输入要查询的部件号', //提示信息
			//required:true, 	//是否必填
			mode : 'remote', //动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
			url : '${pageContext.request.contextPath}/bomTree.htm/autotimp', //请求数据路径
			editable : true, //可编辑
			hasDownArrow : false, //下拉面板不关闭
			valueField : "id", //数组的键索引
			textField : "name", //数组的值索引
			icons : [ {
				iconCls : 'icon-search'
			} ],
			onBeforeLoad : function(param) { //onBeforeLoad：在请求加载数据之前触发，返回 false 则取消加载动作，为true的话则重新加载数据。
				console.log("------ " + param.q + " ------"); //param.q ：combobox框输入的参数，请求方式POST
				if (param == null || param.q == null || param.q.replace(/ /g, '') == '') {
					var value = $(this).combobox('getValue');
					if (value) { //不为空的时候才传关键字到后台，模糊查询数据到前台
						param.q = value;
						return true;
					}
					return false;
				}
			},
			onSelect : function(record) {
				//alert(JSON.stringify(record));
				$.ajax({
					type : "post",
					url : '${pageContext.request.contextPath}/bomTree.htm/onselect',
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
						$('#tg').treegrid('loadData', result);
	
					},
					error : function() {
						alert("异常！");
					}
				});
			},
			inputEvents : $.extend({}, $.fn.combobox.defaults.inputEvents, {
				keyup : function(event) {
					if (event.keyCode == 13) {
						var value = $('#userId').combobox('getValue');
						//alert(value);
						inputEnter();
						$('#userId').combobox("hidePanel");
						$('#userId').combobox('setValue', '');
					}
				}
			})
		});
		/* 			
		  	    $(function(){				
		           	$('#ss').searchbox({
		            	//height:'40px',
					    searcher:function(value,name){
					    	getData('#tg','${pageContext.request.contextPath}/bomTree.htm/searchNum' + '?num=' + value);
					    },
					    //menu:'#mm',
					    prompt:'部件号'
					});  
					
					$('#ssAttribute').searchbox({
		            	//height:'40px',
					    searcher:function(value,name){
					    	getData('#tg','${pageContext.request.contextPath}/bomTree.htm/searchAttribute' + '?attribute=' + value);
					    },
					    //menu:'#mm',
					    prompt:'属性'
					}); 
					            
		        });  */
	
	
		function excelImportDlg(){
			$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格');	
		}
		
		function upload(url) { 
				$('#importExcelDlg').dialog('close');	
    			var fileName = $('#fileId').val().split('\\'); //得到文件名数组
    			var fileSize =  document.getElementById('fileId').files[0]; //获得文件大小；
    			fileName2 = fileName[fileName.length-1]; // 获得文件名
    			filePath = $('#fileId').val().toLowerCase().split(".");
    			fileType =  filePath[filePath.length - 1]; //获得文件结尾的类型如 zip rar 这种写法确保是最后的
    			if(!(fileType == "xls")){
    			 	alert('文件格式不符合要求！');
    			}else if(fileSize.size>20971520){  			
    			  	alert('文件超过20M')		       
    			}else{
    				var form = $("form[name=fileForm]"); 
    				var node = $('#tg').treegrid('getSelected');
    				//alert(node.idc);
    				if(node){
    					//alert('123');
    				
    					document.getElementById("nodeID").value=node.idc;	
	    				var options = {  
	    					url:url, //'${pageContext.request.contextPath}/numTen.htm/exlImport', //上传文件的路径  
	    					type:'post', 
	    					dataType:"json",  	
	    					clearForm:true,
	    					success:function(result){
	    						//alert(JSON.stringify(result)); 
	    						if(result[0].hasOwnProperty('error')){
									alert(result[0].error);   
								}else if(result[0].hasOwnProperty('success')){
									alert(result[0].success);  
								}						
	    						$('#tg').treegrid({data:getData('#tg','${pageContext.request.contextPath}/bomTree.htm/loadData')}); 
	    					},
	    					beforeSubmit: function () {			
	    						load();			        
	    					},
	    					complete: function () {
	    						disLoad();	
	    					}
	    				};  
	    				form.ajaxSubmit(options); 
    				}else{
    					alert('请先选中一个节点！');
    				}
    			}
        	} 
		
		function openDrawingNum(drawingNum){
			$.ajax({ 
				type: "post",  
				url:'${pageContext.request.contextPath}/fileop.htm/fileName',  	
				data:{name:drawingNum +'.pdf'},
				traditional: true,
				dataType:"json",  
				success: function(result) {
					if(result){		                   
 		               	var content = '<iframe src="<c:url value="../../pdf/web/viewer.html"/>?file=${pageContext.request.contextPath}/fileop.htm/readFile" style="width:100%;height:100%;"></iframe>';
			            $('#dialog').dialog({
			                content: content,
			                //noheader: true,
			                //border: true,
			                //resizable: false,//定义对话框是否可调整尺寸。
			                //maximized: true,//默认最大化
			                //modal: false,
						}).dialog('open').dialog('setTitle',drawingNum); 
		               //	window.parent.window.addTabIframe(drawingNum +'.pdf');			
					}else{
						alert("出错了！");
					}
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});				
		}
		

/* 		function onchange(){ 
			$('#importExcelDlg').dialog('close');
			upload('${pageContext.request.contextPath}/bomTree.htm/exlImport');
		}	 */
		$(function(){ 
			
			$('#sbutton').switchbutton({
				//checked: false,

				onChange: function(checked){
					var data = {};
					if (checked == true){
						data.bomEnable = 1;		
					}
					if (checked == false){						
						data.bomEnable = 0;			
					}
					$.ajax({
						type : "post",
						url : '${pageContext.request.contextPath}/bomTree.htm/bomEnable',
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
		
						},
						error : function() {
							alert("异常！");
						}
					});			
				}
			});
            if(isOnlyRead() == true){
            	$('#sbutton').switchbutton('disable');
            } 
			$.ajax({
					type : "post",
					url : '${pageContext.request.contextPath}/bomTree.htm/getBomEnable',						
					traditional : true,
					dataType : "json",
					beforeSend : function() {
						load();
					},
					complete : function() {
						disLoad();
					},
					success : function(result) {
						//alert();
						if(result[0].bomEnable==1){
							$('#sbutton').switchbutton('check');							
						}else{
							$('#sbutton').switchbutton('uncheck');
						}
					},
					error : function() {
						alert("异常！");
					}
			});			

		});
	</script>

</body>
</html>