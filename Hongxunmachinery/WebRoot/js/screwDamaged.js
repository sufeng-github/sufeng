			function openScrewDamagedHtml(){
				/*
				var tabPanel = $('#wu-tabs');
				if(!(tabPanel.tabs('exists','报损记录'))){
					addTab('报损记录','html/screwDamaged.html','icon-database-lighting',0);
				}*/
				$('#wu-tabs').panel('refresh', 'html/screwDamaged.html');
				$('#wu-tabs').panel({title: "全部报损记录"});
				$.ajax({ 
					type: "post",  
					url:'screwdamaged.htm/load',
					traditional: true,
					dataType:"json",  
					success: function(result) {
						$('#dg_screwDamaged').datagrid('loadData', result);	
					},
					error : function() {
						alert("异常！");
					}
				});  
			}			

			function damagedToData(){				
				var rows = $('#dg_screwDamaged').datagrid('getSelections');
				if(rows.length>0){
					if (confirm("您确定返回库存？")){
						var currentIndex=0;	
						var start=0;
						for(var i=0; i<rows.length; i++){			
							var rowIndex = $('#dg_screwDamaged').datagrid('getRowIndex', rows[i]);	
							if(rows[i].quantity == 0){
								currentIndex++;
								alert(rows[i].name + ' :数量为0无需返回');
							}else{
								$.ajax({  
									type: "post",  
									url: 'screwdamaged.htm/return',
									data:{"ID":rows[i].ID, "index":rowIndex}, 
									traditional: true,
									async: true,//同步请求数据
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
										/*
										var tabPanel = $('#wu-tabs');
										if(tabPanel.tabs('exists','库存记录')){
											var data = $('#dg_screwData').datagrid('getData'); 
									    	//var options = $('#dg_screwData').datagrid('getPager').data("pagination").options;  
											var totalRowNum = data.originalRows.length;
											for (var i=0; i < totalRowNum; i++) { 
												if(data.originalRows[i].ID == obj[0].ID){
													alert("0");
													var index = $('#dg_screwData').datagrid('getRowIndex', data.originalRows[i]);
													data.originalRows[i].realquantity = obj[0].svalue;
													alert("1");
													data.originalRows.push(data.originalRows[i]);
													data.originalRows.splice(index, 1);
													alert("2");
													$('#dg_screwData').datagrid('loadData', data.originalRows);																
													break;
												}
											}
										}*/	
											var remark = obj[0].remark;
											$('#dg_screwDamaged').datagrid('updateRow',{index:obj[0].index, row:{quantity:"0",date:obj[0].date,remark:remark}});	
																			
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
					alert("至少选中一行哦...");
				}
				
			}
			
			$(function(){
				$('#dg_screwDamaged').datagrid({loadFilter:pagerFilter});
			});
			
			$(function(){
				var pager = $('#dg_screwDamaged').datagrid().datagrid('getPager');	// get the pager of datagrid
				pager.pagination({
					buttons:[{
						iconCls:'icon-add',
						handler:function(){	
							//$('#dlgScrewOut').dialog('open').dialog('setTitle','out');
							//$('#fmScrewOut').form('clear');	
						}
					},
					{
						iconCls:'icon-edit',
						handler:function(){
							$('#dlgScrewSaleEdit').dialog('open').dialog('setTitle','Edit User');						

						}
					},{	
						iconCls:'icon-search',
					}]
				});			
			});
/*
			function screwSaleEditSave(){
				var rows = $('#dg_screwSale').datagrid('getSelections');
				if(rows.length>0){
					for(var i=0; i<rows.length; i++){
						var rowIndex = $('#dg_screwSale').datagrid('getRowIndex', rows[i]);	
						$('#fmScrewSale').form('load',rows[i]);
						var data = $.param({  	
							'index':rowIndex})  + '&' +
							$('#fmScrewSale').serialize();
						$.ajax({  
							type: "post",  
							url: 'screwsale.htm/lotedit',
							data:data, 
							traditional: true,
							dataType:"json",  
							success: function(result) {	
								var obj = eval(result);  																																   
								$('#dg_screwSale').datagrid('updateRow',{index:obj[0].index, row:{patient:obj[0].patient,
																								  sickbed:obj[0].sickbed,
																								  invoice:obj[0].invoice,
																								  companysale:obj[0].companysale}});
							}, 
							error : function() {
				                alert("异常！");
				            }
						});
					}
				}else{
					alert("至少选中一行删除哦...");
				}	
			}
			*/
			
