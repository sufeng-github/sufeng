<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>TreeGrid - jQuery EasyUI Demo</title>
<!-- 		<link rel="stylesheet" type="text/css" href="easyui/css/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/css/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
		<link rel="stylesheet" type="text/css" href="easyui/css/dlg.css">
		<script type="text/javascript" src="easyui/js/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="easyui/js/jquery.easyui.min.js"></script> -->
 	<link rel="stylesheet" href="../../../custom/uimaker/easyui.css"> 
	<link rel="stylesheet" type="text/css" href="../../../custom/uimaker/icon.css">    
    <link href="../../css/basic_info.css" rel="stylesheet">
 	<script type="text/javascript" src="../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../../custom/easyui-lang-zh_CN.js"></script>
		
<!-- 
插记录时,将其父节点的数据库字段state改为closed,本身节点的数据库字段state插位入open,字段parentid插入父节点id
数据录字段必有parentid,state(是否能展开) 
-->
	<script>
	//***************************************************************************************树形表格(数据库用一个表,用parentid关联)
		$(function(){
			$('#dd_tg').hide();
			$('#ddf_tg').hide();
			$('#tg').treegrid({
				//title:'树形表格',
				//iconCls:'icon-save',
				width:'100%',                                          //*********************设置treegrid宽度
				height:600,                                            //*********************设置treegrid高度 -->
				nowrap: false,
				animate:true,
				collapsible:true,
				url:'${pageContext.request.contextPath}/bomTree.htm/loadData',
				//url:'treegrid_data.json',                               //**************获取数据地址返回的数据是集合(对象类型)
				idField:'ID',                                          //********************每行记录对应一个唯一标识,id值
				treeField:'BomMaterialNum',
				columns:[[ 
					{field:'ID',title:'ID',width:20},
					{field:'BomMaterialNum',title:'部件号',width:198},
					{field:'BomQuantity',title:'数量',width:198},
					{field:'BomSpacification',title:'技术规格',width:198},
					{field:'BomLength',title:'长度',width:198},                                            //********************* 设置字段名
				    {field:'BomDrawingNum',title:'图号',width:198},
				    {field:'BomPosion',title:'位置',width:198},
				    {field:'BomMaterial',title:'材料',width:198},
					{field:'parentID',title:'parentID',width:20}
					//{field:'addr',title:'地址',width:198},
					//{field:'work',title:'内容',width:198}
				]],

   //***************************************************************************************************************************************** -->
				//onClickRow:function(row){                               //*********************为所有子节点(不能展开)添加点击事件 按需添加-->
				//	 var node = $('#tg').treegrid('getSelected');
				//	 var nodes = $('#tg').treegrid('getChildren');
				//	  if(node.id!=nodes[0].id){
				//		alert(node.id);
				//	  }
			//	},
  //*****************************************************************************工具栏
				toolbar:[{
						id:'btnadd',
						text:'添加',
						iconCls:'icon-add',
						handler:function(){
							//$('#btnsave').linkbutton('enable');
							add_tg();
						}
					},{
						id:'btnedit',
						text:'编辑',
						iconCls:'icon-edit',
						handler:function(){
							//$('#btnsave').linkbutton('enable');
							edit_tg();
						}
					},{
						id:'btncut',
						text:'删除',
						iconCls:'icon-no',
						handler:function(){
							//$('#btnsave').linkbutton('enable');
							dele_tg();
						}
					}]
				});
			});
 //******************************************************************************添加
		function add_tg(){
			$("#fm_tg").form("clear");
			var s='';
			var node = $('#tg').treegrid('getSelected');
			//alert(node.ID);
			//alert(node.BomMaterialNum);
			if (node){
				//$('#fid').val(node.id);
				//$('#flx').val(node.type);
				 $('#dd_tg').show();
				 s='#dd_tg';
			}else{
				$('#ddf_tg').show();
				 s='#ddf_tg';
			}

			
			  $(''+s).dialog({   
				    title: '添加/编辑',   
				    width: 400,   
				    height: 250,   
				    closed: false,   
				    cache: false,   
				    modal: true,
				    buttons:[{
						text:'保存',
						iconCls:'icon-save',
						handler:function(){
							/*$.post("#", $("#fm_tg").serialize(),function(data){
									$('#tg').treegrid('reload');
									$(''+s).window('close');
									}
							);*/
							var data = [{
 									ID:0,
									BomMaterialNum: $("#fm_tg").find('input[name=BomMaterialNum]').val(),
									BomQuantity: $("#fm_tg").find('input[name=BomQuantity]').val(),
									BomSpacification: $("#fm_tg").find('input[name=BomSpacification]').val(),
									BomLength: $("#fm_tg").find('input[name=BomLength]').val(),
									BomDrawingNum: $("#fm_tg").find('input[name=BomDrawingNum]').val(),
									BomPosion: $("#fm_tg").find('input[name=BomPosion]').val(),
									BomMaterial: $("#fm_tg").find('input[name=BomMaterial]').val(),
									parentID: node.ID
							}] ;
							$('#tg').treegrid('append',{
								parent: node.ID,  // the node has a 'id' value that defined through 'idField' property
 								data: data
								//data:$("#fm_tg").serialize()
							});
							
							//$('#parentID').val(node.ID);
							//var data = $.param({'parentid':node.ID})  + '&' + $('#fm_tg').serialize();
							alert(JSON.stringify(data));
							//var data = $.param($('#fm_tg').serialize());
							getData('#tg','${pageContext.request.contextPath}/bomTree.htm/saveRow', data[0]);
						
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
//*********************************************************************编辑
		function edit_tg(){
			var s='';
			 var node = $('#tg').treegrid('getSelected');
			if (node){
					var node = $('#tg').treegrid('getSelected');
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
					}else{
					
								f_id=nodep.ID;
								//f_lx=nodep.type;
								$('#BomMaterialNum').val(node.BomMaterialNum);
								$('#BomQuantity').val(node.BomQuantity);
								$('#BomSpacification').val(node.BomSpacification);
								$('#BomLength').val(node.BomLength);
								$('#BomDrawingNum').val(node.BomDrawingNum);
								$('#BomPosion').val(node.BomPosion);
								$('#BomMaterial').val(node.BomMaterial);
								$('#dd_tg').show();
								s='#dd_tg';
					}
		 $(''+s).dialog({   
				    title: '添加/编辑',   
				    width: 400,   
				    height: 250,   
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
								id: node.ID,  // the node has a 'id' value that defined through 'idField' property
 								row: {
									BomMaterialNum: $("#fm_tg").find('input[name=BomMaterialNum]').val(),
									BomQuantity: $("#fm_tg").find('input[name=BomQuantity]').val(),
									BomSpacification: $("#fm_tg").find('input[name=BomSpacification]').val(),
									BomLength: $("#fm_tg").find('input[name=BomLength]').val(),
									BomDrawingNum: $("#fm_tg").find('input[name=BomDrawingNum]').val(),
									BomPosion: $("#fm_tg").find('input[name=BomPosion]').val(),
									BomMaterial: $("#fm_tg").find('input[name=BomMaterial]').val(),
								}
								//data:$("#fm_tg").serialize()
							});
							$(''+s).window('close');
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
			 var node = $('#tg').treegrid('getSelected');
			 if (node){
				  
/* 				$.post("*", { id: ''+node.id},function(data){
					$('#tg').treegrid('reload');
				} ); */
//				alert(node.ID);
				$('#tg').treegrid('remove', node.ID);
			}
		}
		function getData(tt, url, row){
			$.ajax({ 
				type: "post",  
				url:url,
				data:{"ID":row.ID},
				traditional: true,
				dataType:"json",  
				beforeSend: function () {
					load();
				},
				complete: function () {
					disLoad();
				},
				success: function(result) {
					alert(JSON.stringify(result));
				
 						$(tt).treegrid('append',{
							parent: row.ID, 
	 						data: result								
						}); 

					//$(tt).datagrid('loadData', result);	
					
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	
        }
		function getData1(tt, url){
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
					$(tt).treegrid('loadData', result);
					//alert(JSON.stringify(result));
					
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	
        }		
	</script>
</head>
<body>
<div >
<!--树形表格  -->
	<table id="tg" ></table>
<!--添加/编辑弹出框 父 -->
	<div id="ddf_tg">
		  <form id="ffm_tg">
		   <input type="hidden" name="fid" id="ffid">
			 <p> 类型:&nbsp;&nbsp;&nbsp;<input name="flx" id="fflx"><p/>
	      </form>
	</div>  
<!--添加/编辑弹出框  子-->

	<div id="dd_tg">
 		  <form id="fm_tg">
 		  
 		     <input type="hidden" name="ID" id="ID">
 		     <input type="hidden" name="parentID" id="parentID"> -->
<!-- 			 <p> 部件号:<input name="flx" id="flx" readonly="readonly"><p/> -->
			 <p> 部件号:<input name="BomMaterialNum" id="BomMaterialNum"><p/>
			 <p> 数量:&nbsp;&nbsp;&nbsp;<input name="BomQuantity" id="BomQuantity"><p/>
			 <p> 技术规格:&nbsp;&nbsp;&nbsp;<input name="BomSpacification" id="BomSpacification"><p/>
			 <p> 长度:&nbsp;&nbsp;&nbsp;<input name="BomLength" id="BomLength"><p/>
			 <p> 图号:&nbsp;&nbsp;&nbsp;<input name="BomDrawingNum" id="BomDrawingNum"><p/>
			 <p> 位置:&nbsp;&nbsp;&nbsp;<input name="BomPosion" id="BomPosion"><p/>
			 <p> 材料&nbsp;&nbsp;&nbsp;<input name="BomMaterial" id="BomMaterial"><p/>
	
	      </form>
	</div>
</div>
</body>
</html>