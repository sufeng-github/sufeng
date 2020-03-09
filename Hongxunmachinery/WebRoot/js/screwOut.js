	/*		function openScrewOutHtml(){
				$('#wu-tabs').panel('refresh', 'html/screwOut.html');
				$('#wu-tabs').panel({title: "全部出库记录"});
				$.ajax({ 
					type: "post",  
					url:'screwout.htm/load',
					traditional: true,
					dataType:"json",  
					success: function(result) {
						$('#dg_screwOut').datagrid('loadData', result);	
					},
					error : function() {
						alert("异常！");
					}
				});  
			}			
*/	
			function openscrewoutloteditdlg(){
				$('#screwoutloteditdlg').dialog('open').dialog('setTitle','批量编辑');
			}
			
			function screwoutlotedit(col, context){
				$('#screwoutloteditdlg').dialog('close');	
				//alert(col);
				var currentIndex=0;	
				var start=0;
				var rows = $('#dg_screwOut').datagrid('getSelections');
				for(var i=0; i<rows.length; i++){	
					var index = $('#dg_screwOut').datagrid('getRowIndex', rows[i]);
					rows[i][col]=context;				
					$('#fmScrewOut').form('load',rows[i]);
					var data = $.param({  	
					'index':index})  + '&' +
					$('#fmScrewOut').serialize();
					$.ajax({  
						type: "post",  
						url: 'screwout.htm/edit',
						//data:$('#fmScrewOut').serialize(), 
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
							$('#dg_screwOut').datagrid('updateRow',{index:result[0].index, row:result[0]});	
							$('#dg_screwOut').datagrid('refreshRow', result[0].index);	
							
						}, 
						error : function() {
							currentIndex++;
			                 alert("异常！");
			            }
					});  	
				}	
			}
			
			function screwOutEditRun(){
				var rows = $('#dg_screwOut').datagrid('getSelections');
				if (rows.length == 1){			
					$('#dlgScrewOutEdit').dialog('open').dialog('setTitle','Edit User');							
					$('#fmScrewOut').form('load',rows[0]);
				}else{
					alert('只能选中一行哦...');
				}
			}
			
			function screwOutEdit(){
				var row = $('#dg_screwOut').datagrid('getSelected');	
				var index = $('#dg_screwOut').datagrid('getRowIndex', row);	
				var data = $.param({  	
					'index':index})  + '&' +
					$('#fmScrewOut').serialize();
				$.ajax({  
					type: "post",  
					url: 'screwout.htm/edit',
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
						$('#dg_screwOut').datagrid('updateRow',{index:result[0].index, row:result[0]});	                                 
						$('#fmScrewOut').form('clear');		
						$('#dlgScrewOutEdit').dialog('close');		
					}, 
					error : function() {
		                alert("异常！");
		            }
				});   	
			}
			
			function openmovetosaledialog(){		
				var rows = $('#dg_screwOut').datagrid('getSelections');
				var flag = true;
				if(rows.length>0){
					for(var i=0; i<rows.length; i++){
						if(rows[i].hospital=='广州公司'){
							flag=false;
							break;
						}
					}
					if(flag == true){
						$('#dlgScrewOutMoveScrewSale').dialog('open').dialog('setTitle','移动到销售');
					}else{
						alert("选中行中有发货到广州的产品,不能销售...");
					}
				}else{
					alert("至少选中一行哦...");
				}		
			}

			function screwOutMoveScrewSaleOK(){			
				var quantity = $('#quantityScrewOutMoveSale').numberbox('getValue');	
				//var invoice = $('#invoiceScrewOutMoveSale').numberbox('getValue');	
				var rows = $('#dg_screwOut').datagrid('getSelections');		
				if(quantity == ''){
					alert('数量不能为空...');
				}/*else if(invoice == ''){
					alert('开票号不能为空...');
				}*/else{
					$('#dlgScrewOutMoveScrewSale').dialog('close');
					var currentIndex=0;	
					var start=0;
					for(var i=0; i<rows.length; i++){
						if(rows[i].realquantity >= quantity){  
							//alert('123');
							var index = $('#dg_screwOut').datagrid('getRowIndex', rows[i]);	
							//$('#fmScrewOut').form('load',rows[i]);
							/*var data = $.param({  	
								'index':index,'quantity':quantity})  + '&' +
								$('#fmScrewOut').serialize();*/
							$.ajax({  
								type: "post",  
								url: 'screwout.htm/move',
								data:{"index":index, "id":rows[i].ID, "quantity":quantity},  
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
									/*var tabPanel = $('#wu-tabs');
									if(tabPanel.tabs('exists','销售')){
										var data = $('#dg_screwSale').datagrid('getData');
										data.originalRows.push(result[0]);
										$('#dg_screwSale').datagrid('loadData', data.originalRows);
									}
									*/
									$('#dg_screwOut').datagrid('updateRow',{index:obj[0].index, row:{realquantity:obj[0].dvalue}});										
								}, 
								error : function(){
									currentIndex++;
					                alert("异常！");
					            }
							});
						}else{
							currentIndex++;
							alert(rows[i].name + ': 数量不足');
						}
					}
				}
			}
			
			function sethospitalprepare(){
				var rows = $('#dg_screwOut').datagrid('getSelections');
				if(rows.length>0){
					if (confirm("您确备货产品？")){
						var currentIndex=0;	
						var start=0;
						for(var i=0; i<rows.length; i++){			
							var index = $('#dg_screwOut').datagrid('getRowIndex', rows[i]);	
							if(rows[i].realquantity == 0){
								currentIndex++;
								alert(rows[i].name + ' 数量为0不能备货');								
							}else{
								$.ajax({  
									type: "post", 
									async: true,//同步请求数据
									url: 'screwout.htm/prepare',
									data:{"index":index,
										"ID":rows[i].ID
										},  
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
										//alert(currentIndex);	
										currentIndex++;
										var obj = eval(result);						
										$('#dg_screwOut').datagrid('updateRow',{index:obj[0].index, row:{remark:'备货'}});						
									}, 
									error : function() {
										currentIndex++;
						                alert("异常！");
						            }
								});
							}
						}
					}
				}else{
					alert("至少选中一行删除哦...");
				}			
			}
			
			function returnscrewdata(){			
				var rows = $('#dg_screwOut').datagrid('getSelections');
				if(rows.length>0){
					if (confirm("您确定回流产品到库存？")){
						var currentIndex=0;	
						var start=0;
						for(var i=0; i<rows.length; i++){			
							var index = $('#dg_screwOut').datagrid('getRowIndex', rows[i]);	
							if(rows[i].realquantity == 0){
								currentIndex++;
								alert(rows[i].name + ' 数量为0无需回流');								
		
							}else{
								$.ajax({  
									type: "post", 
									async: true,//同步请求数据
									url: 'screwout.htm/return',
									data:{"index":index,
										"ID":rows[i].ID,
										"MainID":rows[i].MainID
										},  
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
										//alert(currentIndex);	
										currentIndex++;
										var obj = eval(result);		
									/*	var tabPanel = $('#wu-tabs');
										if(tabPanel.tabs('exists','库存')){
											var data = $('#dg_screwData').datagrid('getData'); 
							    			var totalRowNum = data.originalRows.length;
											for (var i=0; i < totalRowNum; i++) { 
												if(data.originalRows[i].ID == obj[0].ID){
													var index = $('#dg_screwData').datagrid('getRowIndex', data.originalRows[i]);
													data.originalRows[i].realquantity = obj[0].quantity;
													data.originalRows.push(data.originalRows[i]);
													data.originalRows.splice(index, 1);
													$('#dg_screwData').datagrid('loadData', data.originalRows);		
													break;
												}
											}
										}*/
										var remark = obj[0].remark;
										$('#dg_screwOut').datagrid('updateRow',{index:obj[0].index, row:{realquantity:0,date:obj[0].date,remark:remark}});						
									}, 
									error : function() {
										currentIndex++;
						                alert("异常！");
						            }
								});
							}
						}
					}
				}else{
					alert("至少选中一行删除哦...");
				}			
			}
			
			$(function(){
				$('#scanScrewOut').textbox('textbox').keydown(function (e) {			
	                if (e.keyCode == 13) {
	                	if($('#screwOutHospital').textbox('getValue')==''){
	                		alert('医院不能为空...');
	                	}else if($('#screwOutDeliveryman').textbox('getValue') == ''){
	                		alert('送货员不能为空...');
	                	}else if($('#quantityScrewOut').numberbox('getValue') == ''){
							alert('数量不能为空...');
	                	}else if($('#outstoreOutnum').textbox('getValue') == ''){
	    					alert('出库单号不能为空...');	
	    	            }else{
	    	            	//alert($('#ckoutnum').is(":checked"));
	    	            	if($('#ckoutnum').is(":checked") == false){
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
	    									if(confirm('出库单号:' + $('#outstoreOutnum').textbox('getValue') + '已存在是否继续执行操作？')){
	    										ajaxscanout();
	    									}
	    								}else{
	    									ajaxscanout();
	    								}
	    							},
	    							error : function() {
	    								alert("异常！");
	    					        }
	    						}); 
	    	            	}else{
	    	            		ajaxscanout();
	    	            	}    	            			                   	
						} 
	                }
	            });
            });
	
			function ajaxscanout(){
				$.ajax({  
					type: "post",  
					url:'screwout.htm/barcode',
					data:{  
						"barcode":$('#scanScrewOut').textbox('getValue'),  
						"hospital":$('#screwOutHospital').textbox('getValue'),  							
						"quantity":$('#quantityScrewOut').numberbox('getValue'), 
						"selected" :$('#ck1').is(":checked"),
						"deliveryman":$('#screwOutDeliveryman').textbox('getValue'),
						"outnum":$('#outstoreOutnum').textbox('getValue')
					},  
					dataType:"json",  
					beforeSend: function () {
						load();
					},
					complete: function () {
						disLoad();
					},
					success: function(result) { 
						var obj = eval(result);	
						if(obj[0].alert == undefined){
							if(result.length==1){											
								var dataout = $('#dg_screwOut').datagrid('getData');
								dataout.originalRows.push(result[0]);			
								$("#dg_screwOut").datagrid('loadData', dataout.originalRows);  
								$('#scanScrewOut').textbox('setValue', '');				
							}else{								
								$('#screwOutWin').window('open');
								$('#screwOutWinTable').datagrid('loadData',result);
							}									
						}else{
							alert(obj[0].alert);
						}								
					},
					error : function() {								
		                alert("异常！");
		            }
				}); 	
			}
			
			function dgmoveout(){
				var rows = $('#screwOutWinTable').datagrid('getSelections');
				$.ajax({  
					type: "post",  
					url:'screwout.htm/rebarcode',
					data:{  
						"barcode":$('#scanScrewOut').textbox('getValue'),  
						"hospital":$('#screwOutHospital').textbox('getValue'),  							
						"quantity":$('#quantityScrewOut').numberbox('getValue'),  
						"deliveryman":$('#screwOutDeliveryman').textbox('getValue'), 
						"selected" :$('#ck1').is(":checked"),
						"outnum":$('#outstoreOutnum').textbox('getValue'),
						"id":rows[0].ID
					},  
					dataType:"json",  
					beforeSend: function () {
						load();
					},
					complete: function () {
						disLoad();
					},
					success: function(result) { 
						var obj = eval(result);	
						if(obj[0].alert == undefined){
							if(result.length==1){																	
								var dataout = $('#dg_screwOut').datagrid('getData');
								dataout.originalRows.push(result[0]);
								/*var data = $('#dg_screwData').datagrid('getData'); 
				    			var totalRowNum = data.originalRows.length;
								for (var i=0; i < totalRowNum; i++) { 
									if(data.originalRows[i].ID == obj[0].screwdataID){
										var index = $('#dg_screwData').datagrid('getRowIndex', data.originalRows[i]);
										data.originalRows[i].realquantity = obj[0].dvalue;
										$('#dg_screwData').datagrid('loadData', data.originalRows);			
										$("#dg_screwOut").datagrid('loadData', dataout.originalRows);  
										$('#scanScrewOut').textbox('setValue', '');	
										$('#screwOutWin').window('close');
										break;
									}
								}*/
								$("#dg_screwOut").datagrid('loadData', dataout.originalRows);  
								$('#scanScrewOut').textbox('setValue', '');	
								$('#screwOutWin').window('close');
							}							
						}else{
							alert(obj[0].alert);
						}								
					},
					error : function() {								
		                alert("异常！");
		            }
				}); 
			}			

			function modifyHospitalName(){
				$.ajax({  
					type: "post",  
					url: 'screwout.htm/modifyHospitalName',
					traditional: true,
					dataType:"json",  
					beforeSend: function () {
						load();
					},
					complete: function () {
						disLoad();
					},
					success: function(result) {
						alert('完成');
					}, 
					error : function() {
		                alert("异常！");
		            }
				});   	
			}
		function exportExcelForCompany(){
			var rows = $('#dg_screwOut').datagrid('getSelections');	
			var ids = [];
			//if(rows.length > 0){
				for(var i=0; i<rows.length; i++){													
					ids.push(rows[i].ID);	
				}
				if(ids.length==0){
					ids.push(0);
				}
				$.ajax({  
					type: "post",  
					url:'screwout.htm/exportforcompany',
					dataType:"text", 
					data:{ids:ids},
					traditional: true,//这里设置为true
					beforeSend: function () {
						load();	
					},
					complete: function () {
						disLoad();
					},
					success: function(result) {
						$("#screwoutformdata").submit();
					}, 
					error : function() {
					   	alert("异常！"); 
					}
				});  		
		}