			function load() {  
				$("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");  
				$("<div class=\"datagrid-mask-msg\"></div>").html("数据处理中，请稍候。。。").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });  
			}
				//取消加载层  
			function disLoad() {  
				$(".datagrid-mask").remove();  
				$(".datagrid-mask-msg").remove();  
			}
			function addNode(){
				var node = $('#tt').tree('getSelected');
				var temNode = node;	
				for(var i=0; i<4; i++){
					 id = temNode.id;	
				 	 temNode = $('#tt').tree('getParent', temNode.target); 
				 	 if(temNode == null){	 	
				 	 	$.ajax({  	
							url:'tree.htm/create', 
							traditional: true,
							data:{"index":i, "id":node.id},
							dataType:"json",  	
							success: function(result){ 
								$('#tt').tree('append', {parent:node.target, data:result});							
								//alert('更新OK!' + JSON.stringify(result));
							},
							error : function(result){								
				                alert('报错');
				            }
						});		
				 	 	break;
				 	 }
				}
			}
			function removeNode(){
				var node = $('#tt').tree('getSelected');
				var temNode = node;	
				for(var i=0; i<5; i++){
					 id = temNode.id;	
				 	 temNode = $('#tt').tree('getParent', temNode.target); 
				 	 if(temNode == null){	 	
				 	 	$.ajax({  	
							url:'tree.htm/delete', 
							traditional: true,
							data:{"index":i, "id":node.id},
							dataType:"json",  	
							success: function(result){ 
								$('#tt').tree('remove', node.target);					
								//alert('更新OK!' + JSON.stringify(result));
							},
							error : function(result){								
				                alert('报错');
				            }
						});		
				 	 	break;
				 	 }
				}		
			}
			function updateNode(){		
				var node = $('#tt').tree('getSelected');
				$('#tt').tree('beginEdit',node.target);				
			}
			
			function onafterEdit(node){
				var temNode = node;
				for(var i=0; i<5; i++){
				 	 temNode = $('#tt').tree('getParent', temNode.target); 
				 	 if(temNode == null){	
				 	 	$.ajax({  	
							url:'tree.htm/update', 
							traditional: true,
							data:{"index":i, "id":node.id, "text":node.text},
							dataType:"json",  	
							success: function(result){ 
								//alert('更新OK!');
							},
							error : function(result){								
								$('#tt').tree('update', {
									target: node.target,
									text: 'newNode'
								});
				                alert('报错');
				            }
						});		
				 	 	break;
				 	 }
				}
			}
			
			function getSelected(node){
			//	var node = $('#tt').tree('getSelected');
				if (node){
					var s = node.text;			
				}
			}
			
			function leafDblClick(node){
			//	var leaf = $('#tt').tree('isLeaf', node.target);  
			//	alert('1230');
		        var parent = $('#tt').tree('getParent', node.target);  
		        var parentTow = $('#tt').tree('getParent', parent.target);  
		        var root;
		        if(parentTow != null){	
		        	var parentThree = $('#tt').tree('getParent', parentTow.target);  
		        	if(parentThree != null){
		        		var parentFour = $('#tt').tree('getParent', parentThree.target);  
		        		if(parentFour != null){
		        			root = parentFour;
		        		}else{
		        			root = parentThree;
		        		}
		        		
		        	}else{
		        		root = parentTow;
		        	}
		        }else{
		        	root = parent;
		        }
		       // alert(root.text);
		        if( parent != null){
					var temNode = node;	
					var param = [];
					for(var i=0; i<5; i++){			
					 	 temNode = $('#tt').tree('getParent', temNode.target); 		 	
					 	 if(temNode == null){
					 		if(root.text == '库存目录'){
						 		$('#wu-tabs').panel('refresh', 'html/screwData.html');
								$('#wu-tabs').panel({title: '库存翻阅'});
								$('#wu-tabs').panel({iconCls: 'icon-cmy'});
					 		}else if(root.text == '出库目录'){
								if(parent.text == root.text){
					 				$('#wu-tabs').panel('refresh', 'html/screwOutNumList.html');
					 				$('#wu-tabs').panel({title:  node.text+'出库单详细'});
					 			}else{
						 			$('#wu-tabs').panel('refresh', 'html/screwOut.html');
									$('#wu-tabs').panel({title: '出库翻阅'});
					 			}
								$('#wu-tabs').panel({iconCls: 'icon-color'});
					 		}else if(root.text == '销售目录'){
					 			if(parent.text == root.text){
					 				$('#wu-tabs').panel('refresh', 'html/screwSaleNumList.html');
					 				$('#wu-tabs').panel({title:  node.text+'开票号详细'});
					 			}else{
					 				$('#wu-tabs').panel('refresh', 'html/screwSale.html');
					 				$('#wu-tabs').panel({title: '销售翻阅'});
					 			}
								$('#wu-tabs').panel({iconCls: 'icon-sport-tennis'});	
					 		}else if(root.text == '器械盒'){
						 		$('#wu-tabs').panel('refresh', 'html/screwData.html');
								$('#wu-tabs').panel({title: '器械盒翻阅'});
								$('#wu-tabs').panel({iconCls: 'icon-cmy'});
					 		}else if(root.text == '送货员'){			 			
					 			$('#wu-tabs').panel('refresh', 'html/screwOutNumList.html');
					 			$('#wu-tabs').panel({title:  node.text+'出库单详细'});
								$('#wu-tabs').panel({iconCls: 'icon-color'});
					 		}	
					 		param[0] = node.text;
					 	 	$.ajax({  	
								url:'tree.htm/treefind', 
								traditional: true,
								data:{"array":param},
								dataType:"json",  
								beforeSend: function () {
									load();
								},
								complete: function () {
									disLoad();
								},
								success: function(result){ 
									if(root.text == '库存目录'){
										$('#dg_screwData').datagrid('loadData', result);
									}else if(root.text == '出库目录'){
										if(parent.text == root.text){
											$('#dg_screwOutNumList').datagrid('loadData', result);		
										}else{
											$('#dg_screwOut').datagrid('loadData', result);
										}
									}else if(root.text == '销售目录'){		
										if(parent.text == root.text){
											$('#dg_screwSaleNumList').datagrid('loadData', result);		
										}else{
											$('#dg_screwSale').datagrid('loadData', result);
										}
									}else if(root.text == '器械盒'){
										$('#dg_screwData').datagrid('loadData', result);
									}else if(root.text == '送货员'){								
										$('#dg_screwOutNumList').datagrid('loadData', result);												
									}	
									jsondata = result;
								},
								error : function(result){								
					                alert('报错');
					            }
							});		
					 	 	break;
					 	 }else{
					 		param[i+1] = temNode.text;
					 	 }
					}
		        }else{
				    $('#tt').tree(node.state === 'closed' ? 'expand' : 'collapse', node.target);  
				    node.state = node.state === 'closed' ? 'open' : 'closed';  
		        }
			}

		
			
			     	 function contextMenu(e, node){ //给结点添加右键菜单   
			         	e.preventDefault();  //阻止右键默认事件                
			         	$('#tt').tree('select', node.target)     
			            var root = $('#tt').tree('getParent', node.target);//判断该结点有没有父结点  
			            if(root == null){//若成立则为根结点，添加一个右键样式，可以添加子节点  
				            $('#parentNode').menu('show', {    
				              	left: e.pageX,    
				               	top: e.pageY  		                                      
				           	});                     
			            }  
			            var leaf = $('#tt').tree('isLeaf', node.target);  
			            var children = $('#tt').tree('getChildren', node.target);
			            var parentOne = $('#tt').tree('getParent', node.target);  			           
			            var parentTwo = $('#tt').tree('getParent', parentOne.target); 					
						var parentFour = null;
						if(parentTwo != null){
							var parentThree = null;
							parentThree = $('#tt').tree('getParent', parentTwo.target); 
							if(parentThree != null){
								parentFour = $('#tt').tree('getParent', parentThree.target); 
							}
						}
						
						if(children.length > 0){
							$('#parentNode').menu('show', {    
				              	left: e.pageX,    
				               	top: e.pageY  		                                      
				           	}); 
			            }else if(leaf && parentFour != null){//本项目要求tree只有4层,若此时条件成立，说明已经是最底层（第三层），右键菜单去除添加节点  
			            	//alert('1');
			              	$('#leaf').menu('show', {    
			                 	left: e.pageX,    
			                   	top: e.pageY    
			              	});    
			          	}else{//此时说明还可以添加子节点  
			          	 	//alert('2');
			           		$('#middleNode').menu('show', {    
			                  	left: e.pageX,    
			                   	top: e.pageY    
			               	});  
			          	}
			         	//$('#tt').tree('select', node.target);//设置选中该节点                  
			       	}    