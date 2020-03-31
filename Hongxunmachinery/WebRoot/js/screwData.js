			/*function screwDataReloadBtn(){		
				$('#dg_screwData').datagrid('reload');
			}*/
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
			function screwDataExportExcel(){
				var data = $('#dg_screwData').datagrid('getData');
				var option={};
				option.fileName = 'excel'
				option.datas=[{
					sheetData:data.originalRows,
					sheetName:'库存导出',
					sheetFilter:['date','name','xinghao','guige','caizhi','unit','realquantity','totalquantity','changjia','registnum','lotnum','serialnum'],
					sheetHeader:['入库日期','产品名称','型号','规格','材质','单位','实时量','总量','生产厂家','注册证号','批号','系列号']}
				];
				//alert( JSON.stringify(data.originalRows));
				var toExcel=new ExportJsonExcel(option);
				toExcel.saveExcel();
			}
			*/
			function screwOutReloadBtn(){
				//alert('sdfasdf');
				$('#dg_screwOut').datagrid('reload');
			}	

			$(function(){
				$('#scan').textbox('textbox').keydown(function (e) {			
	                if (e.keyCode == 13) {
	                	$.ajax({  
							type: "post",  
							url:'screwdata.htm/barcode',
							data:{ 				
								"barcode":$('#scan').textbox('getValue')     
							},  
							dataType:"json",  
							beforeSend: function () {
								load();
							},
							complete: function () {
								disLoad();
							},
							success: function(result) { 
								result[0].realquantity=1;
								$('#fm').form('load',result[0]);
							},
							error : function() {
				                alert("基础库无此信息！");
				            }
						});  
	                }
	            });
            });

			function screwDataSave(){			
				$.ajax({  
					type: "post",  
					url: 'screwdata.htm/save',
					data:$('#fm').serialize(),  
					traditional: true,
					dataType:"json",
					beforeSend: function () {
						load();
					},
					complete: function () {
						disLoad();
					},
					success: function(result) {
						var data = $('#dg_screwData').datagrid('getData');
						data.originalRows.push(result[0]);
						$('#dg_screwData').datagrid('loadData', data.originalRows);	
						$('#fm').form('clear');	
						$('#scan').textbox('setValue', '');		
						$('#dlg').dialog('close');
						$('#dlg').dialog('open').dialog('setTitle','入库扫描');					
					}, 
					error : function() {
		                 alert("异常！");
		            }
				});  	
			}
			
			function screwDataEditRun(){
				//var rows = $('#dg_screwData').datagrid('getSelections');
				/*if (rows.length == 1){			
					$('#dlgEdit').dialog('open').dialog('setTitle','Edit User');							
					$('#fmEdit').form('load',rows[0]);
				}else{
					alert('只能选中一行哦...');
				}*/
				var row = $('#dg_screwData').datagrid('getSelected');
				//var index = $('#dg_screwData').datagrid('getRowIndex', row);	
				//var data = $.param({'index':index})  + '&' + $('#fmEdit').serialize();
				$.ajax({  
					type: "post",  
					url: 'screwdata.htm/edit',
					//data:$('#fmEdit').serialize(),  
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
						//$('#dg_screwData').datagrid('updateRow',{index:result[0].index, row:result[0]});
						//$('#dg_screwData').datagrid('refreshRow', result[0].index);	
						//$('#fmEdit').form('clear');								
					}, 
					error : function() {
		                 alert("异常！");
		            }
				}); 
			}
			
			function screwDataEdit(){
				$('#dlgEdit').dialog('close');	
				var row = $('#dg_screwData').datagrid('getSelected');
				var index = $('#dg_screwData').datagrid('getRowIndex', row);	
				var data = $.param({'index':index})  + '&' + $('#fmEdit').serialize();
				$.ajax({  
					type: "post",  
					url: 'screwdata.htm/edit',
					//data:$('#fmEdit').serialize(),  
					data:data,
					traditional: true,
					dataType:"json", 
					beforeSend: function () {
						load();
					},
					complete: function () {
						disLoad();
					},
					success: function(result) {
						$('#dg_screwData').datagrid('updateRow',{index:result[0].index, row:result[0]});
						$('#dg_screwData').datagrid('refreshRow', result[0].index);	
						//$('#fmEdit').form('clear');								
					}, 
					error : function() {
		                 alert("异常！");
		            }
				});  	
			}
			
			function openloteditdlg(){
				$('#screwdataloteditdlg').dialog('open').dialog('setTitle','批量编辑');
			}
			
			function screwdatalotedit(col, context){
				$('#screwdataloteditdlg').dialog('close');	
				//alert(col);
				var currentIndex=0;	
				var start=0;
				var rows = $('#dg_screwData').datagrid('getSelections');
				for(var i=0; i<rows.length; i++){	
					var index = $('#dg_screwData').datagrid('getRowIndex', rows[i]);
					rows[i][col]=context;				
					$('#fmEdit').form('load',rows[i]);
					var data = $.param({  	
					'index':index})  + '&' +
					$('#fmEdit').serialize();
					$.ajax({  
						type: "post",  
						url: 'screwdata.htm/edit',
						//data:$('#fmEdit').serialize(), 
						data:data,
						traditional: true,
						dataType:"json", 
						beforeSend: function () {
							if(start == 0){
								start++;
								load();
							}
						},
						complete: function () {
							if(currentIndex==rows.length){
								disLoad();
							}
						},
						success: function(result) {	
							currentIndex++;
							//alert("1" + result[0].index);
							$('#dg_screwData').datagrid('updateRow',{index:result[0].index, row:result[0]});	
							$('#dg_screwData').datagrid('refreshRow', result[0].index);	
							
						}, 
						error : function() {
							currentIndex++;
			                 alert("异常！");
			            }
					});  	
				}	
			}
			
			function screwDataDelete(){	

				var rows = $('#dg_screwData').datagrid('getSelections');	
				if(rows.length > 0){
					if (confirm("您确定要删除选中行？")){
						var currentIndex=0;	
						var start=0;
						for(var i=0; i<rows.length; i++){						
							//$('#fmEdit').form('load',rows[i]);
							//var index = $('#dg_screwData').datagrid('getRowIndex', rows[i]);	
							/*var data = $.param({  	
								'index':index})  + '&' +
								$('#fmEdit').serialize();*/
							$.ajax({  
								type: "post",  
								url:'screwdata.htm/delet',
								data:{"id":rows[i].ID}, 
								dataType:"json", 
								beforeSend: function () {
									if(start == 0){
										start++;	
										load();
									}
								},
								complete: function () {
									if(currentIndex==rows.length){
										disLoad();
									}
								},
								success: function(result) {
									currentIndex++;
									var obj = eval(result);
									var data = $('#dg_screwData').datagrid('getData');
									var getJson = data.rows.filter((p) => {
									    return p.ID == obj[0].ID;//parseInt(obj[0].ID, 10);
									});											
									var index = data.originalRows.indexOf(getJson[0]);
									if(index > -1){					
										data.originalRows.splice(index, 1);
										$('#dg_screwData').datagrid('loadData', data.originalRows);
									} 
								}, 
								error : function() {
									currentIndex++;
					                alert("异常！"); 
					            }
							});  			
						}	
					}
				}else{
					alert("至少选中一行哦...");
				}	
			}

			function moveout(){
				var rows = $('#dg_screwData').datagrid('getSelections');
				if(rows.length>0){
					$('#movedlg').dialog('open').dialog('setTitle','移动到出库');
				}else{
					alert("至少选中一行哦...");
				}			
			}
			
			function rejected(){
				var rows = $('#dg_screwData').datagrid('getSelections');
				if(rows.length>0){
					$('#rejecteddlg').dialog('open').dialog('setTitle','退货');
				}else{
					alert("至少选中一行哦...");
				}			
				
			}
			
			function damaged(){
				var rows = $('#dg_screwData').datagrid('getSelections');
				if(rows.length>0){
					$('#damageddlg').dialog('open').dialog('setTitle','报损');
				}else{
					alert("至少选中一行哦...");
				}			
				
			}
			function openboxdlg(){
				var rows = $('#dg_screwData').datagrid('getSelections');
				if(rows.length>0){
					$('#instrumentsboxdlg').dialog('open').dialog('setTitle','加入器械盒');
				}else{
					alert("至少选中一行哦...");
				}	
			}
			
			function movebox(instrumentsbox){
				$('#instrumentsboxdlg').dialog('close');
				var rows = $('#dg_screwData').datagrid('getSelections');
				var currentIndex=0;	
				var start=0;
				for(var i=0; i<rows.length; i++){

					var index = $('#dg_screwData').datagrid('getRowIndex', rows[i]);							
					var data = $.param({  	
						'index':index,
						'id':rows[i].ID,
						'instrumentsbox':instrumentsbox});
						
						$.ajax({  
							type: "post",  
							url:"screwdata.htm/instrumentsbox",  
							data:data,  
							traditional: true,
							dataType:"json", 
							beforeSend: function () {
								if(start == 0){
									start++;
									load();
								}
							},
							complete: function () {									
								if(currentIndex==rows.length){
									disLoad();
								}
							},
							success: function(result) {
								currentIndex++;
								var obj = eval(result);  					
								$('#dg_screwData').datagrid('updateRow',{index:obj[0].index, row:{instrumentsbox:obj[0].instrumentsbox}});								
							},
							error : function() {
								currentIndex++;
				                alert("异常！");
				            }
						});  
				}				
			}
			
				function getoutbox(){
					var rows = $('#dg_screwData').datagrid('getSelections');
					var currentIndex=0;	
					var start=0;
					for(var i=0; i<rows.length; i++){
						var index = $('#dg_screwData').datagrid('getRowIndex', rows[i]);							
						var data = $.param({  	
							'index':index,
							'id':rows[i].ID,
							'instrumentsbox':''});						
							$.ajax({  
								type: "post",  
								url:"screwdata.htm/instrumentsbox",  
								data:data,  
								traditional: true,
								dataType:"json", 
								beforeSend: function () {
									if(start == 0){
										start++;
										load();
									}
								},
								complete: function () {									
									if(currentIndex==rows.length){
										disLoad();
									}
								},
								success: function(result) {
									currentIndex++;
									var obj = eval(result);  					
									$('#dg_screwData').datagrid('updateRow',{index:obj[0].index, row:{instrumentsbox:''}});								
								},
								error : function() {
									currentIndex++;
					                alert("异常！");
					            }
							});  
					}				
				}
				
				function move2out(){	
					//alert("asfdasfsaf");
					if($('#screwDataHospital').textbox('getValue')==''){
		            	alert('医院不能为空...');
		           	}else if($('#screwDataDeliveryman').textbox('getValue') == ''){
		           		alert('送货员不能为空...');
		            }else if($('#screwDataMovequantity').numberbox('getValue') == ''){
						alert('数量不能为空...');
		            }else if($('#instoreOutnum').textbox('getValue') == ''){
						alert('出库单号不能为空...');	
		            }else{
		            //	ckoutnum
		            	if($('#ckinnum').is(":checked") == false){
			            	$.ajax({  
								type: "post",  
								url:"screwdata.htm/checkoutnum",  
								data:{'outnum':$('#instoreOutnum').textbox('getValue'),
									 'hospital':$('#screwDataHospital').textbox('getValue')},  
								traditional: true,
								dataType:"json", 
								beforeSend: function () {						
									load();								
								},
								complete: function () {																		
									disLoad();										
								},
								success: function(result) {	
									if(result[0].flag==1){
										if(confirm('出库单号:' + $('#instoreOutnum').textbox('getValue') + '已存在是否继续执行操作？')){
											ajaxmove();
										}
									}else{
										ajaxmove();
									}
								},
								error : function() {
									alert("异常！");
						        }
							}); 
		            	}else{
		            		ajaxmove();
		            	}
					}						
				}		
				
			function ajaxmove(){
				var rows = $('#dg_screwData').datagrid('getSelections');
				$('#movedlg').dialog('close');
        	//	alert($('#ck').is(":checked"));
				var currentIndex=0;	
				var start=0;
				for(var i=0; i<rows.length; i++){
					//if(parseInt(rows[i].realquantity, 10) >= $('#screwDataMovequantity').numberbox('getValue')){   	
						$('#fmEdit').form('load',rows[i]);
						var index = $('#dg_screwData').datagrid('getRowIndex', rows[i]);							
						var data = $.param({  	
							'index':index,
							'quantity':$('#screwDataMovequantity').numberbox('getValue'),
							'hospital':$('#screwDataHospital').combobox('getValue'),
							'selected' :$('#ck').is(":checked"),
							'outnum':$('#instoreOutnum').textbox('getValue'),	
							'deliveryman':$('#screwDataDeliveryman').combobox('getValue'),
							'id':rows[i].ID}) 
							//+ '&' +
							//$('#fmEdit').serialize()
							;																		
						
						$.ajax({  
							type: "post",  
							url:"screwdata.htm/move",  
							data:data,  
							traditional: true,
							dataType:"json", 
							beforeSend: function () {
								if(start == 0){
									start++;
									load();
								}
							},
							complete: function () {									
								if(currentIndex==rows.length){
									disLoad();
								}
							},
							success: function(result) {
								currentIndex++;
								var obj = eval(result);  
								if(obj[0].dvalue == 'empty'){
									alert("库存数量足,也可能是其它人正在操作此产品,请刷新查看一下。")
								}else{
									$('#dg_screwData').datagrid('updateRow',{index:obj[0].index, row:{realquantity:obj[0].dvalue}});
								}											
							},
							error : function() {
								currentIndex++;
				                alert("异常！");
				            }
						});  
					 	
					//}else{	
						//currentIndex++;
						//alert(rows[i].name + "->库存数量不足,不能出库.");					
					//}	
				}
			}
			
			function rejecteddlgSave(){	
				var rows = $('#dg_screwData').datagrid('getSelections');
				var quantity = $('#rejectedQuantity').numberbox('getValue');			
				if(quantity == ''){
					alert('数量不能为空...');
				}else{
					var currentIndex=0;	
					var start=0;
					$('#rejecteddlg').dialog('close');
					for(var i=0; i<rows.length; i++){	
					if((quantity > 0) && (rows[i].realquantity>= quantity)){   
						$('#fmEdit').form('load',rows[i]);
						var index = $('#dg_screwData').datagrid('getRowIndex', rows[i]);	
						var data = $.param({  	
							'index':index,
							'quantity':quantity})  + '&' +
							$('#fmEdit').serialize();
							$.ajax({  
								type: "post",  
								url:"screwdata.htm/rejected",  
								data:data,  
								traditional: true,
								dataType:"json", 
								beforeSend: function () {
									if(start == 0){
										load();
										start++;
									}
								},
								complete: function () {
									if(currentIndex==rows.length){
										disLoad();
									}
								},
								success: function(result) {
									currentIndex++;
									var obj = eval(result);  					
									$('#dg_screwData').datagrid('updateRow',{index:obj[0].index, row:{realquantity:obj[0].dvalue}});	
									/*var tabPanel = $('#wu-tabs');
									if(tabPanel.tabs('exists','退货')){
										var data = $('#dg_screwRejected').datagrid('getData');
										data.originalRows.push(result[0]);
										$('#dg_screwRejected').datagrid('loadData', data.originalRows);		
									}*/			
								},
								error : function() {
									currentIndex++;
									alert("异常！");
								}
							});  							 	
						}else{
							currentIndex++;
							alert(rows[i].name + "->库存数量不足,不能退货.");
							/*if (confirm("ID:" + rows[i].ID + " 退货数量大于库存数量是否继续?")){								
							}else{
								$('#rejecteddlg').dialog('close');
								break;
							}*/
						}	
					}	
				}						
			}
			
			function damageddlgSave(){
				
				var quantity = $('#damagedQuantity').numberbox('getValue');
				var rows = $('#dg_screwData').datagrid('getSelections');
					if(quantity == ''){
						alert('数量不能为空...');
					}else{
						var currentIndex=0;	
						var start=0;
						$('#damageddlg').dialog('close');
						for(var i=0; i<rows.length; i++){
							if((quantity > 0) && (parseInt(rows[i].realquantity, 10) >= quantity)){   
								$('#fmEdit').form('load',rows[i]);
								var index = $('#dg_screwData').datagrid('getRowIndex', rows[i]);	
								var data = $.param({  	
									'index':index,
									'quantity':quantity})  + '&' +
									$('#fmEdit').serialize();
								$.ajax({  
									type: "post",  
									url:"screwdata.htm/damaged",  
									data:data,  
									traditional: true,
									dataType:"json",  
									beforeSend: function () {
										if(start == 0){
											load();
											start++;
										}
									},
									complete: function () {
										if(currentIndex==rows.length){
											disLoad();
										}
									},
									success: function(result) {
										currentIndex++;
										var obj = eval(result);  												
										$('#dg_screwData').datagrid('updateRow',{index:obj[0].index, row:{realquantity:obj[0].dvalue}});	
										/*var tabPanel = $('#wu-tabs');
										if(tabPanel.tabs('exists','报损')){
											alert('报损');
											var data = $('#dg_screwDamaged').datagrid('getData');
											data.originalRows.push(result[0]);
											$('#dg_screwDamaged').datagrid('loadData', data.originalRows);	
										}*/
										
									},
									error : function() {
										currentIndex++;
						                 alert("异常！");
						            }
								});  
							 	
							}else{
								currentIndex++;
								alert(rows[i].name + "->库存数量不足,不能报损.");
								/*if (confirm("ID:" + rows[i].ID + " 报损数量大于库存数量是否继续?")){								
								}else{
									$('#damageddlg').dialog('close');
									break;
								}*/
							}	
						}
					}
								
			}  


			function screwDataAdd(){
				$('#dlg').dialog('open').dialog('setTitle','入库扫描');
				$('#fm').form('clear');			
			}
/*
			function openScrewDataHtml(){
					$('#wu-tabs').panel('refresh', 'html/screwData.html');
					$('#wu-tabs').panel({title: "全部入库记录"});
					$.ajax({ 
						type: "post",  
						url:'screwdata.htm/load',
						traditional: true,
						dataType:"json",  
						success: function(result) {
							$('#dg_screwData').datagrid('loadData', result);	
							//alert('finish');
						},
						error : function() {
			                 alert("异常！");
			            }
					});  
				
			}
			*/
			function realstore(){
				/*var tabPanel = $('#wu-tabs');
				if(!tabPanel.tabs('exists','库存记录')){
					addTab('库存记录','html/screwData.html','icon-database-add',0);
				}*/
				$('#wu-tabs').panel('refresh', 'html/screwData.html');
				$('#wu-tabs').panel({title: "实时库存"});
					$.ajax({ 
						type: "post",  
						url:"screwdata.htm/realstore",  	
						traditional: true,
						dataType:"json",  
						success: function(result) {
							$('#dg_screwData').datagrid('loadData', result);	
							//alert('finish');
						},
						error : function() {
			                 alert("异常！");
			            }
					});  	
			}
			
			function classify(){
				/*var tabPanel = $('#wu-tabs');
				if(!tabPanel.tabs('exists','库存记录')){
					addTab('库存记录','html/screwData.html','icon-database-add',0);
				}*/
				$('#wu-tabs').panel('refresh', 'html/screwData.html');
				$('#wu-tabs').panel({title: "库存统计"});
					$.ajax({ 
						type: "post",  
						url:"screwdata.htm/classify",  	
						traditional: true,
						dataType:"json",  
						success: function(result) {
							$('#dg_screwData').datagrid('loadData', result);	
							//alert('finish');
						},
						error : function() {
			                 alert("异常！");
			            }
					});
					
				
			}
			
			function todayinstore(){
				$('#wu-tabs').panel('refresh', 'html/screwData.html');
				$('#wu-tabs').panel({title: "当天入库记录"});
					$.ajax({ 
						type: "post",  
						url:"screwdata.htm/today",  	
						traditional: true,
						dataType:"json",  
						success: function(result) {
							$('#dg_screwData').datagrid('loadData', result);	
						},
						error : function() {
			                 alert("异常！");
			            }
					});
			}
			
			function screwDataImport(){
				$.ajax({  
					type: "post",  
					url:"screwdata.htm/import",  
			
					traditional: true,
					dataType:"text",  
					success: function(result) {
						alert('finish');
					},
					error : function() {
		                 alert("异常！");
		            }
				});  
			}			
		

			