<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html> 
<html lang="en"> 
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
	<!--     
	<script type="text/javascript" src="../../../../jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    -->
 	<script type="text/javascript" src="../../js/myjs/myjs.js"></script>  
    <script type="text/javascript" src="../../js/myjs/gridHeader.js"></script> 
    <script type="text/javascript" src="../../js/myjs/ajaxfileupload.js"></script> 
    <script type="text/javascript" src="../../js/myjs/pdfobject.min.js"></script> 
</head>

<body>

		<div class="easyui-layout"  data-options="fit:true">	
		    <div data-options="region:'center'" style="padding:0px;background:#eee;">	
 				<div id="tt" class="easyui-tabs1" style="width:100%;height:100%;">
					<!-- <div id="pdf" style="width:100%;height:100%"></div> -->
	          	</div>	 				
			</div>
			<div data-options="region:'west',title:'South Title',split:true" style="width:300px; height:100px;padding:5px;background:#eee;">
				<input class="easyui-combobox" id="userId" style="width:100%; height:30px;"></input>
				<ul id="tree" style="margin-top:20px"></ul>	
				<div id="mm" class="easyui-menu" style="width: 120px;">
					<div data-options="iconCls:'icon-add'" onclick="add_tg()">新建目录</div>
					<div data-options="iconCls:'icon-edit'" onclick="edit_tg()">更改</div>
					<div data-options="iconCls:'icon-remove'" onclick="dele_tg()">删除</div>
					<div data-options="iconCls:'icon-table-add'" onclick="importDlg()">导入</div>
				</div>
			</div>	
		</div>	
		<div id="fileUploadDlg" class="easyui-dialog" style="width:280px;height:150px;padding:10px 0px" data-options="top:10,closed:true">
			<form id="uploadfiles" enctype="multipart/form-data">			
		       	<input type="file" multiple="multiple" id="file_upload" name="file_upload" onchange="upload()" accept = ".pdf"/>    
				<input id="parentId"  class="easyui-textbox" type="hidden" name="parentId" ></input>       
		<!-- <input type="button" value="上传" onclick="upload()" />  -->
			</form>
		</div> 
	    <script type="text/javascript">
		function convert(rows){
			function exists(rows, parentId){
				for(var i=0; i<rows.length; i++){
					if (rows[i].id == parentId) return true;
				}
				return false;
			}	
			var nodes = [];
			// get the top level nodes
			for(var i=0; i<rows.length; i++){
				var row = rows[i];
				if (!exists(rows, row.parentId)){
					nodes.push({
						id:row.id,
						text:row.name
						
					});
				}
			}
		
			var toDo = [];
			for(var i=0; i<nodes.length; i++){
				toDo.push(nodes[i]);
			}
			while(toDo.length){
				var node = toDo.shift();	// the parent node
				// get the children nodes
				for(var i=0; i<rows.length; i++){
					var row = rows[i];
					if (row.parentId == node.id){
						var child = {id:row.id,text:row.name};
						if (node.children){
							node.children.push(child);
						} else {
							node.children = [child];
							if(toDo.length>1){						
								node.state = 'closed';
							}
						}
						toDo.push(child);
						
					}
				}
			}
			
			return nodes;
		}
		
		function add_tg(){
			var node = $('#tree').tree('getSelected');
			$.ajax({ 
				type: "post",  
				url:'${pageContext.request.contextPath}/fileop.htm/add',  	
				data:{parentId:node.id},
				traditional: true,
				dataType:"json",  
				success: function(result) {
				//alert(JSON.stringify(result));
					if(result.length==1){				
						$('#tree').tree('append', {
							parent: node.target,
							data: [{
								id: result[0].id,
								name: result[0].name,
								parentId:result[0].parentId
							}]
						});
					}	
				},
				error : function() {
					alert("出错了！");
				}
			});	
	
			
		}
		function edit_tg(){
			var node = $('#tree').tree('getSelected');
			$('#tree').tree('beginEdit', node.target);			
		}
		
		function dele_tg(){
			var node = $('#tree').tree('getSelected');
			$('#tree').tree('remove', node.target);	
			$.ajax({ 
				type: "post",  
				url:'${pageContext.request.contextPath}/fileop.htm/delete',  	
				data:{nodeId:node.id},
				traditional: true,
				dataType:"json",  
				success: function(result) {
				if(result){				
					}else{
						alert("出错了！");
					}
				},
				error : function() {
					alert("出错了！");
				}
			});	
		}

		
		$(function(){
			$('#tree').tree({
				animate:true,
				//url: 'data/tree6_data.json',
				url: "${pageContext.request.contextPath}/fileop.htm/loadData",
				formatter:function(node){
						var s = node.text;					
						if (node.children){
							s += ' <span style=\'color:blue\'>(' + node.children.length + ')</span>';
						}
						return s;
				},
				onContextMenu: function(e, node){
					if(isOnlyRead() == false){
						e.preventDefault();
						//$(this).tree('unselectAll');
						$(this).tree('select',node.target);
						$('#mm').menu('show', {
							left:e.pageX,
							top:e.pageY				
						});
					}
				},
				onAfterEdit:function(node){
					//var node = $(this).tree('getSelected');	
					if (node){
						$(this).tree('update', {
							target: node.target,
							text: node.text
						});
					
						$.ajax({ 
							type: "post",  
							url:'${pageContext.request.contextPath}/fileop.htm/update',  	
							data:{nodeId:node.id, text:node.text},
							traditional: true,
							dataType:"json",  
							success: function(result) {
								if(result){
									
								}else{
									alert("出错了！");
								}
							},
							error : function() {
						       	alert("出错了！");
						   	}
						});	
					}
				},				
				onClick: function(node){
					var fileName = node.text;
					if(fileName.indexOf(".pdf")>-1){
						addTab(node.text, node.id);
					}
				},

				loadFilter: function(rows){
					return convert(rows);
				}/*,
				 onLoadSuccess:function(node, data)  
		            { 
		            	alert(data.p) ;
		                $("#tree").tree("collapseAll");  
		            }  */
		
			});
		});
	</script>
	<script type="text/javascript">
		/*
		 * 上传文件
		 */
		function upload(){
			//var fileName = $('#file_upload').val().split('\\'); //得到文件名数组
			var files =  document.getElementById('file_upload').files; //获得文件个数；
			    //fileName2 = fileName[fileName.length-1]; // 获得文件名
			    filePath = $('#file_upload').val().toLowerCase().split(".");
			    //fileType =  filePath[filePath.length - 1]; //获得文件结尾的类型如 zip rar 这种写法确保是最后的
			//alert(fileSize.length);
			var size = 0;
			for(var i=0; i<files.length; i++){
				//alert(files[i].size);
				size = size + files[i].size;
				if(size>20971520){
					alert('文件超过20M');	
					return false;
				}else{
				/*
					var filePath = fileName[i+1].split(".");
					var fileType =  filePath[filePath.length - 1];
					alert(fileType);
					if(fileType != "pdf"){
						alert('只充许上传PDF文件');	
						return false;
					}*/
				}	
			}
			alert('size:' + size);
			var node = $('#tree').tree('getSelected');
			//alert(node.id);
		
			$('#parentId').textbox('setValue',node.id);      	       
		    var formData = new FormData($( "#uploadfiles" )[0]);
		    $.ajax({
		            type: "post",
		            url: "${pageContext.request.contextPath}/fileop.htm/writeFile",
		            dataType: "json",
		            data: formData,
		            /**   
		             *必须false才会自动加上正确的Content-Type   
		             */  
		            contentType : false, 
		            /**   
		             * 必须false才会避开jQuery对 formdata 的默认处理   
		             * XMLHttpRequest会对 formdata 进行正确的处理   
		             */ 
		            processData : false,
					beforeSend: function () {
						load();
					},
					complete: function () {
						disLoad();
						$("#fileUploadDlg").dialog("close");
					},
		            success: function(data){//从后端返回数据进行处理
						if(data.length>0){			
							//alert(JSON.stringify(data));
							for(var i=0; i<data.length; i++){
								//$(this).tree('reload',node.target);
								var selected = $('#tree').tree('getSelected');
	 							$('#tree').tree('append', {
									parent: node.target,
									data: [{
										id: data[i].id,
										name: data[i].name,
										parentId:data[i].parentId
									}]
								}); 	
							}        									
			                alert("上传成功！");
		              	}else{
		                  	alert("上传失败！");
		              	}
		            },
		            error: function(err) {//提交出错
		                $("#msg").html(JSON.stringify(err));//打出响应信息
		                alert("服务器无响应");
		              }
		          });
			}
			
		    $('#userId').combobox({
		        prompt:'请输入要查询的图号', 	//提示信息
				//required:true, 	//是否必填
				mode:'remote', 		//动态去服务器端拿数据；而mode:'local'是取本地数据的也就是javascirpt(内存)中的数据
				url:'${pageContext.request.contextPath}/fileop.htm/autotimp', 	//请求数据路径
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
		        	addTab(record.name, record.name);
		        } 
		    });		
	</script>  
		
	<script>
		//PDFObject.embed('../../pdf/abc.pdf', '#pdf');
		function importDlg(){
			$("#fileUploadDlg").dialog("open").dialog('setTitle','导入文件');
		}
		
		function isOnlyRead(){
			var str = localStorage.getItem('authority1'); 			
	        str = str.substr(16, 1)
			if(str.charAt(0)==1){	
	        	return true;					        			
	        }else{
	        	return false;
	        }    		        	    	         		
		}
	</script>
	<script type="text/javascript">
        
      	$('.easyui-tabs1').tabs({
          	tabHeight: 26,
           	onSelect:function(title,index){
   
          		var currentTab = $('.easyui-tabs1').tabs("getSelected");
         		if(currentTab.find("iframe") && currentTab.find("iframe").size()){
         			var start = title.indexOf('(')+1;
         			var stop = title.indexOf(')');
         			//alert(start + ':' + end);
         			var id =title.substring(start,stop)
	        		$.ajax({ 
						type: "post",  
						url:'${pageContext.request.contextPath}/fileop.htm/fileId',  	
						data:{id:id},
						traditional: true,
						dataType:"json",  
						beforeSend: function () {
							load();
						},
						complete: function () {
							disLoad();
						},
						success: function(result) {
							if(result){
								currentTab.find("iframe").attr("src",currentTab.find("iframe").attr("src"));				
							}else{
								alert("出错了！");
							}
						},
						error : function() {
						  	alert("出错了qwe！");
						}
					});	        
	           	}
       		}
 		});
       

/* 	 	$('.easyui-tabs1').tabs({
	      tabHeight: 26
	    }); */
	    $(window).resize(function(){
	    	$('.easyui-tabs1').tabs("resize");
	    }).resize(); 
	</script>
	<script type="text/javascript">
      	function addTab(title, data){    		
      		$.ajax({ 
				type: "post",  
				url:'${pageContext.request.contextPath}/fileop.htm/fileId',  	
				data:{id:data},
				traditional: true,
				dataType:"json",  
				success: function(result) {
					if(result){
						if ($('#tt').tabs('exists', title)){
		                    $('#tt').tabs('select', title);
		                } else {		                            
							var url = '<c:url value="../../pdf/web/viewer.html"/>?file=' + 
									  '${pageContext.request.contextPath}/fileop.htm/readFile' 
		                    var content = '<iframe src="'+url+'" style="width:100%;height:100%;"></iframe>';
			                $('#tt').tabs('add',{
			                   title:title+'(' + data + ')',
			                   content:content,
			                   closable:true
			                });
		               	}				
					}else{
						alert("出错了！");
					}
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	
       	}
            /*$(function(){
            	setTimeout(function(){
					addTab('Bom','./pf_bd_tables1_13.jsp')	
				},300);
	        });*/ 
   </script>
</body> 
</html>


