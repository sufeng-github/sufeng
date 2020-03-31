/*			function load() {  
				$("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");  
				$("<div class=\"datagrid-mask-msg\"></div>").html("鏁版嵁澶勭悊涓紝璇风◢鍊欍�傘�傘��").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });  
			}
				//鍙栨秷鍔犺浇灞�  
			function disLoad() {  
				$(".datagrid-mask").remove();  
				$(".datagrid-mask-msg").remove();  
			}
*/
/*			function openScrewSaleHtml(){

				$('#wu-tabs').panel('refresh', 'html/screwSale.html');
				$.ajax({ 
					type: "post",  
					url:'screwsale.htm/load',
					traditional: true,
					dataType:"json",  
					success: function(result) {
						$('#dg_screwSale').datagrid('loadData', result);	
					},
					error : function() {
						alert("寮傚父锛�");
					}
				});  
			}*/	
	

			function screwSaleExportExcel(){
				var data = $('#dg_screwSale').datagrid('getData');
				var option={};
				option.fileName = 'excel'
				option.datas=[{
					sheetData:data.originalRows,
					sheetName:'閿�鍞鍑�',
					sheetFilter:['ID','date','name','xinghao','guige','caizhi','unit','quantity','changjia','registnum','lotnum','hospital','companysale','invoice','patient','sickbed','price','total'],
					sheetHeader:['ID','閿�鍞棩鏈�','浜у搧鍚嶇О','鍨嬪彿','瑙勬牸','鏉愯川','鍗曚綅','鏁伴噺','鐢熶骇鍘傚','娉ㄥ唽璇佸彿','鎵瑰彿','鍖婚櫌','鍞嚭鍏徃','寮�绁ㄥ彿','鐥呬汉','鐥呭簥鍙�','鍗曚环','鎬昏']}
				];
				//alert( JSON.stringify(data.originalRows));
				var toExcel=new ExportJsonExcel(option);
				toExcel.saveExcel();
			}

			function returnscrewout(){
				var rows = $('#dg_screwSale').datagrid('getSelections');
				if(rows.length>0){
					if (confirm("鎮ㄧ‘瀹氳繑鍥炲嚭搴擄紵")){
						var currentIndex=0;	
						var start=0;
						for(var i=0; i<rows.length; i++){		
							var rowIndex = $('#dg_screwSale').datagrid('getRowIndex', rows[i]);	
							if(rows[i].quantity == 0){
								currentIndex++;
								alert(rows[i].name + ' :鏁伴噺涓�0鏃犻渶鍥炴祦');
			
							}else{
								$.ajax({  
									type: "post",  
									url: 'screwsale.htm/return',
									data:{"ID":rows[i].ID, "index":rowIndex}, 
									traditional: true,
									async: true,//鍚屾璇锋眰鏁版嵁
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
										if(tabPanel.tabs('exists','鍑哄簱')){
											var data = $('#dg_screwOut').datagrid('getData'); 
							    			var totalRowNum = data.originalRows.length;
											for (var i=0; i < totalRowNum; i++){ 
												if(data.originalRows[i].ID == obj[0].ID){
													var index = $('#dg_screwOut').datagrid('getRowIndex', data.originalRows[i]);
													data.originalRows[i].realquantity = obj[0].dvalue;
													data.originalRows.push(data.originalRows[i]);
													data.originalRows.splice(index, 1);
													$('#dg_screwOut').datagrid('loadData', data.originalRows);														
													break;
												}
											}
										}*/
										var remark = obj[0].remark;
										$('#dg_screwSale').datagrid('updateRow',{index:obj[0].index, row:{quantity:"0",date:obj[0].date,remark:remark}});	
									}, 
									error : function() {
										currentIndex++;
						                alert("寮傚父锛�");
						            }
								});
							}
						}
					}
				}else{
					alert("鑷冲皯閫変腑涓�琛屽垹闄ゅ摝...");
				}
			}
			function screwSaleEditOpen(){
				var rows = $('#dg_screwSale').datagrid('getSelections');
				if (rows.length == 1){
					//for(var i=0; i<rows.length; i++){		
				/*	var temp=rows[0].patient
					if(parseInt("10", temp.length) > 20){
						alert("鐥呬汉淇℃伅瓒呴暱...");
					}else if(rows[0].sickbed.length > 20){
						alert("鐥呭簥鍙蜂俊鎭秴闀�...");
					}else if(rows[0].invoice.length > 80){
						alert("鍙戠エ鍙蜂俊鎭秴闀�...");
					}else if(rows[0].companysale.length > 20){
						alert("閿�鍞叕鍙镐俊鎭秴闀�...");
					}else if(rows[0].price.length > 20){
						alert("鍗曚环淇℃伅瓒呴暱...");
					}else if(rows[0].remark.length > 20){
						alert("澶囨敞淇℃伅瓒呴暱...");
					}else{*/
						$('#dlgScrewSaleEdit').dialog('open').dialog('setTitle','鍗曡缂栬緫');								
						$('#fmScrewSale').form('load',rows[0]);					
					//}
				}else{
					alert("鍙兘閫変腑涓�琛屽摝...");
				}		
			}
			
			function screwSaleEditSave(){
				var rows = $('#dg_screwSale').datagrid('getSelections');
				//if(rows.length>0){
				//	for(var i=0; i<rows.length; i++){	
						var rowIndex = $('#dg_screwSale').datagrid('getRowIndex', rows[0]);		
						
						var data = $.param({  	
							'index':rowIndex})  + '&' +
							$('#fmScrewSale').serialize();
							
						$.ajax({  
							type: "post",  
							async: true,//鍚屾璇锋眰鏁版嵁
							url: 'screwsale.htm/edit',
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
								//alert('asdfasdg...');
								var obj = eval(result);  	
								$('#dg_screwSale').datagrid('updateRow',{index:obj[0].index, row:{patient:obj[0].patient,
																								  sickbed:obj[0].sickbed,
																								  invoice:obj[0].invoice,
																								  companysale:obj[0].companysale,
																								  price:obj[0].price,
																								  total:obj[0].total
																								  }});
								// alert("asdfsaf");
								$('#fmScrewSale').form('clear');		
								$('#dlgScrewSaleEdit').dialog('close');	
							
								
							}, 
							error : function() {
				                alert("寮傚父锛�");
				            }
						});
					//}
		
			}
			/*
			function lotedit(){
				var rows = $('#dg_screwSale').datagrid('getSelections');
				if (rows.length > 0){	
					$('#dlgScrewSaleLotEdit').dialog('open').dialog('setTitle','鎵归噺缂栬緫');
				}else{
					alert("鑷冲皯閫変腑涓�琛屽垹闄ゅ摝...");
				}		
			}

			function screwSaleLotEditSave(){
				var rows = $('#dg_screwSale').datagrid('getSelections');
				if($('#sickbed').textbox('getValue').length>20){
					alert("鐥呭簥鍙蜂俊鎭秴闀�...");
				}else if($('#patient').textbox('getValue').length>20){
					alert("鐥呬汉淇℃伅瓒呴暱...");
				}else if($('#invoice').textbox('getValue').length>80){
					alert("鍙戠エ淇℃伅瓒呴暱...");
				}else if($('#licencenum').textbox('getValue').length>50){
					alert("璁稿彲璇佷俊鎭秴闀�...");
				}else{
					for(var i=0; i<rows.length; i++){
						var rowIndex = $('#dg_screwSale').datagrid('getRowIndex', rows[i]);	

						$.ajax({  
							type: "post",  
							url: 'screwsale.htm/lotedit',
							data:{index:rowIndex,
								  id:rows[i].ID,
								  patient:$('#patient').textbox('getValue'),
								  sickbed:$('#sickbed').textbox('getValue'),
								  companysale:$('#companysale').textbox('getValue'),
								  invoice:$('#invoice').textbox('getValue'),
								  licencenum:$('#licencenum').textbox('getValue')},
							
							traditional: true,
							dataType:"json", 
							beforeSend: function () {
								load();
							},
							complete: function () {
								disLoad();
							},
							success: function(result) {	
								var obj = eval(result);  	
								$('#dg_screwSale').datagrid('updateRow',{index:obj[0].index, row:{patient:$('#patient').textbox('getValue'),
																								  sickbed:$('#sickbed').textbox('getValue'),
																								  invoice:$('#invoice').textbox('getValue'),
																								  companysale:$('#companysale').textbox('getValue'),
																								  licencenum:$('#licencenum').textbox('getValue')}});
										
								$('#dlgScrewSaleLotEdit').dialog('close');			
							}, 
							error : function() {
				                alert("寮傚父锛�");
				            }
						});
					}
				}	
			}
			*/
			
			function openloteditdlg(){
				$('#screwsaleloteditdlg').dialog('open').dialog('setTitle','鎵归噺缂栬緫');
			}
			
			function screwsalelotedit(col, context){
				$('#screwsaleloteditdlg').dialog('close');	
				//alert(col);
				var currentIndex=0;	
				var start=0;
				var rows = $('#dg_screwSale').datagrid('getSelections');
				for(var i=0; i<rows.length; i++){	
					var index = $('#dg_screwSale').datagrid('getRowIndex', rows[i]);
					rows[i][col]=context;				
					$('#fmScrewSale').form('load',rows[i]);
					var data = $.param({  	
					'index':index})  + '&' +
					$('#fmScrewSale').serialize();
					alert(data);
					$.ajax({  
						type: "post",  
						url: 'screwsale.htm/edit',
						//data:$('#fmScrewSale').serialize(), 
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
							$('#dg_screwSale').datagrid('updateRow',{index:result[0].index, row:result[0]});	
							$('#dg_screwSale').datagrid('refreshRow', result[0].index);	
							
						}, 
						error : function() {
							currentIndex++;
			                 alert("寮傚父锛�");
			            }
					});  	
				}	
			}
			
			function screwsalereturnscrewdata(){
				var rows = $('#dg_screwSale').datagrid('getSelections');
				if(rows.length>0){
					if (confirm("鎮ㄧ‘瀹氳繑鍥炲簱瀛橈紵")){
						var currentIndex=0;	
						var start=0;
						for(var i=0; i<rows.length; i++){
							var rowIndex = $('#dg_screwSale').datagrid('getRowIndex', rows[i]);	
							if(rows[i].quantity == 0){
								currentIndex++;
								alert(rows[i].name + ' :鏁伴噺涓�0鏃犻渶杩斿洖');
							}else{
								$.ajax({  
									type: "post",  
									url: 'screwsale.htm/returnscrewdata',
									data:{"ID":rows[i].ID, "index":rowIndex}, 
									async: true,//鍚屾璇锋眰鏁版嵁
									traditional: false,
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
										if(tabPanel.tabs('exists','搴撳瓨')){
											var data = $('#dg_screwData').datagrid('getData'); 
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
										$('#dg_screwSale').datagrid('updateRow',{index:obj[0].index, row:{quantity:"0",date:obj[0].date,remark:remark}});	
									}, 
									error : function() {
										currentIndex++;
						                alert("寮傚父锛�");
						            }
								});
							}
						}
					}
				}else{
					alert("鑷冲皯閫変腑涓�琛屽垹闄ゅ摝...");
				}	
			}
			
			$(function(){
				$('#scanScrewSale').textbox('textbox').keydown(function (e) {			
	                if (e.keyCode == 13) {
	                	if($('#screwSaleHospital').textbox('getValue')==''){
	                		alert('鍖婚櫌涓嶈兘涓虹┖...');
	                	}else if($('#quantityScrewSale').numberbox('getValue') == ''){
							alert('鏁伴噺涓嶈兘涓虹┖...');
	                	}else{
		                   	$.ajax({  
								type: "post",  
								url:'screwsale.htm/barcode',
								data:{  
									"barcode":$('#scanScrewSale').textbox('getValue'),   	
									"hospital":$('#screwSaleHospital').textbox('getValue'),
									"quantity":$('#quantityScrewSale').numberbox('getValue') 		
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
											var datasale = $('#dg_screwSale').datagrid('getData');
											datasale.originalRows.push(result[0]);
											//var data = $('#dg_screwOut').datagrid('getData'); 
							    			//var totalRowNum = data.originalRows.length;
											//for (var i=0; i < totalRowNum; i++) { 
												//if(data.originalRows[i].ID == obj[0].screwoutID){
													//var index = $('#dg_screwOut').datagrid('getRowIndex', data.originalRows[i]);
													//data.originalRows[i].realquantity = obj[0].dvalue;
												//	$('#dg_screwOut').datagrid('loadData', data.originalRows);			
													$("#dg_screwSale").datagrid('loadData', datasale.originalRows);  
													$('#scanScrewSale').textbox('setValue', '');	
													//break;
												//}
											//}					
										}else{	
											//alert("2");
											$('#screwSaleWin').window('open');
											//for(var i=0; i<result.length; i++){	
												$('#screwSaleWinTable').datagrid('loadData',result);
											//}
										}									
									}else{
										alert(obj[0].alert);
									}								
								},
								error : function() {								
					                alert("寮傚父锛�");
					            }
							}); 
						} 
	                }
	            });
            });
			
			
			function dgmovesale(){			
				var rows = $('#screwSaleWinTable').datagrid('getSelections');
				$.ajax({  
					type:"post",  
					url :'screwsale.htm/rebarcode',
					data:{  
						//"barcode":$('#scanScrewSale').textbox('getValue'),  							
						"quantity":$('#quantityScrewSale').numberbox('getValue'),  
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
						//alert("asdfasf");
						var obj = eval(result);	
						if(obj[0].alert == undefined){
							if(result.length==1){
								var datasale = $('#dg_screwSale').datagrid('getData');
								datasale.originalRows.push(result[0]);
								$("#dg_screwSale").datagrid('loadData', datasale.originalRows);  
								$('#scanScrewSale').textbox('setValue', '');	
								$('#screwSaleWin').window('close');
								/*var data = $('#dg_screwOut').datagrid('getData'); 
				    			var totalRowNum = data.originalRows.length;
								for (var i=0; i < totalRowNum; i++) { 
									if(data.originalRows[i].ID == obj[0].screwoutID){
										var index = $('#dg_screwOut').datagrid('getRowIndex', data.originalRows[i]);
										data.originalRows[i].realquantity = obj[0].dvalue;
										$('#dg_screwOut').datagrid('loadData', data.originalRows);			
										$("#dg_screwSale").datagrid('loadData', datasale.originalRows);  
										$('#scanScrewSale').textbox('setValue', '');	
										$('#screwSaleWin').window('close');
										break;
									}
								}
								*/					
							}							
						}else{
							alert(obj[0].alert);
						}								
					},
					error : function() {								
		                alert("寮傚父锛�");
		            }
				}); 
			}			

		
			