			function openScrewRejectedHtml(){
				/*var tabPanel = $('#wu-tabs');
				if(!(tabPanel.tabs('exists','退货记录'))){
					addTab('退货记录','html/screwRejected.html','icon-database-error',0);
				}*/
				$('#wu-tabs').panel('refresh', 'html/screwRejected.html');
				$('#wu-tabs').panel({title: "全部退货记录"});
				$.ajax({ 
					type: "post",  
					url:'screwrejected.htm/load',
					traditional: true,
					dataType:"json",  
					success: function(result) {
						$('#dg_screwRejected').datagrid('loadData', result);	
					},
					error : function() {
						alert("异常！");
					}
				});  
			}
			
			function rejectedToData(){
				var rows = $('#dg_screwRejected').datagrid('getSelections');
				if(rows.length>0){
					if (confirm("您确定返回库存？")){
						var currentIndex=0;	
						var start=0;
						for(var i=0; i<rows.length; i++){				
							var rowIndex = $('#dg_screwRejected').datagrid('getRowIndex', rows[i]);	
							if(rows[i].quantity == 0){
								currentIndex++;
								alert(rows[i].name + ' :数量为0无需返回');
							}else{
								$.ajax({  
									type: "post",  
									async: true,//同步请求数据
									url: 'screwrejected.htm/return',
									data:{"ID":rows[i].ID, "index":rowIndex}, 
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
										if(tabPanel.tabs('exists','库存')){
											var data = $('#dg_screwData').datagrid('getData'); 
									    	//var options = $('#dg_screwData').datagrid('getPager').data("pagination").options;  
							    			var totalRowNum = data.originalRows.length;
											for (var i=0; i < totalRowNum; i++) { 
												if(data.originalRows[i].ID == obj[0].ID){
													var index = $('#dg_screwData').datagrid('getRowIndex', data.originalRows[i]);
													data.originalRows[i].realquantity = obj[0].svalue;
													data.originalRows.push(data.originalRows[i]);
													data.originalRows.splice(index, 1);
													$('#dg_screwData').datagrid('loadData', data.originalRows);			
													
													break;
											
												}
											}
										}*/
										var remark = obj[0].remark;
										$('#dg_screwRejected').datagrid('updateRow',{index:obj[0].index, row:{quantity:"0",date:obj[0].date,remark:remark }});	
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
			
