
			var authority;

			function load() {
			    $("<div class=\"datagrid-mask\"></div>").css({
			        display: "block",
			        width: "100%",
			        height: $(window).height()
			    }).appendTo("body");
			    $("<div class=\"datagrid-mask-msg\"></div>").html("加载中，请稍候。。。").appendTo("body").css({
			        display: "block",
			        left: ($(document.body).outerWidth(true) - 190) / 2,
			        top: ($(window).height() - 45) / 2
			    });
			}
			//取消加载层  
			function disLoad() {
			    $(".datagrid-mask").remove();
			    $(".datagrid-mask-msg").remove();
			}			

			function getRowIndex(target){
				var tr = $(target).closest('tr.datagrid-row');
				return parseInt(tr.attr('datagrid-row-index'));
			}
			function editrow(target){
				$('#tt').datagrid('beginEdit', getRowIndex(target));
			}

			function cancelrow(target){
				$('#tt').datagrid('cancelEdit', getRowIndex(target));
			}
			
			function fmaterSpread(value,row,index){
				if (row.editing){	
					return '';
				}else{
					var a = '<a href="#" onclick="spreadrow(this)" style = "color: #1da02b;text-decoration:none"><i class="iconfont">&#xe60a;</i></a> ';	
					return a ;
				}	
			}
			function fmaterState(value,row,index){ 
				
    	 		if(value!='null' /*&& value.indexOf('100%')>-1*/){
					return  '<span style="color:green">'+value+'</span>';
				}else{
					return  '<span style="color:red">'+value+'</span>';								
				}
      		} 
			
			function fmaterDate(value){				
				if (value != null) {
					var date = new Date(value);
					return date.getFullYear()
					        + "-"// "年"
					        + ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
					        + (date.getMonth() + 1)) + "-"// "月"
					        + (date.getDate() < 10 ? "0" + date.getDate() : date.getDate());
				}
            }
/*			function fmaterOperate(value,row,index){
				if(isOnlyRead()==false){
					if (row.editing){							
						var s = '<a href="#" onclick="saverow(this)">保存</a> ';
						var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
						return s+c;
					} else {					
						var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
						var d = '<a href="#" onclick="deleterow(this)">删除</a>';
						if(row.purchaseDeStatus.indexOf('0%')>-1){
							return e + d;
						}else{
							return e;	
						}
					}
				}else{
					return '';
				}
			}*/	
				function fmaterAction (value,row,index){
		 			if(isOnlyRead()==false){				
						if (row.editing){				
							var s = '<a href="#" onclick="saveRow(this)">保存</a> ';
							var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
							return s+c;
						}else {				
							var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
							var d = '<a href="#" onclick="deleterow(this)">删除</a>';
							return e+d;
						}
					}	 
				} 
				function fmaterDetail (value,row,index){
		 			if(isOnlyRead()==false){				
						if (row.editing){				
							var s = '<a href="#" onclick="saveRow(this)">保存</a> ';
							var c = '<a href="#" onclick="cancelrow(this)">取消</a>';						
							return s+c;
						}else {				
							var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
							var d = '<a href="#" onclick="deleterow(this)">删除</a>';
							var t = '<a href="#" onclick="spreadrow(this)"> 详细</a>';
							return e+d+t;
						}
					}	 
				} 
				
				//单行的保存与修改
				function saveOrDelete(target,entityUrl,saveUrl,msg){
					$.messager.confirm('Confirm',msg,function(r){
						if (r){
							$.ajax({  
								type: "post",  
								url: entityUrl,
								traditional: true,
								dataType:"json", 
								//contentType : 'application/json;charset=utf-8', //设置请求头信息  
								success: function(result) {
									$('#tt').datagrid('unselectAll');
									var rowIndex = getRowIndex(target);
									$('#tt').datagrid('endEdit', rowIndex);	
									$('#tt').datagrid('selectRow',rowIndex);
							       	var row = $("#tt").datagrid('getSelected'); 
							     // alert(JSON.stringify(row));
							       	if(row.idc==0 && msg.indexOf('删除')>-1){ 
							       		$('#tt').datagrid('deleteRow', rowIndex);
							       		return;
									}
									var list = new Array();
									//delete row["editing"];		
									for (var key in row) {
										if (!result[0].hasOwnProperty(key)) {
											delete row[key];
										} else {
											if (row[key] == undefined) {
												delete row[key];
											}
										}
									}
									list.push(row);
									//alert(JSON.stringify(list));
									ajax(saveUrl,list,msg,rowIndex);
				
								}	
							});	
						}
					});	
				}
				
				//多行操作
				function action(entityUrl,saveUrl,msg){
					$.messager.confirm('Confirm',msg,function(r){
						if (r){
							$.ajax({  
								type: "post",  
								url: entityUrl,
								traditional: true,
								dataType:"json", 
								//contentType : 'application/json;charset=utf-8', //设置请求头信息  
								success: function(result) {		
									var rows = $('#tt').datagrid('getSelections');
									var list = new Array();   				
									for(var i=0; i<rows.length; i++){
										for(var key in rows[i]){
											if(!result[0].hasOwnProperty(key)){	
												delete rows[i][key];
											}else{
												if(rows[i][key] == undefined){
													delete rows[i][key];
												}
											}				
										}
										list.push(rows[i]);								
									}	
									ajax(saveUrl,list,msg);
				
								}	
							});	
						}
					});	
				}
				//发出请求，后台实现
				function ajax(url,list,msg,rowIndex){
					$.ajax({
						type : "post",
						url : url,
						data : JSON.stringify(list),
						//traditional: true,
						dataType : "json",
						contentType : 'application/json;charset=utf-8', //设置请求头信息  
						beforeSend : function() {
							load();
						},
						complete : function() {
							disLoad();
						},
						success : function(result) {
							if (msg.indexOf('删除') > -1) {
								$('#tt').datagrid('deleteRow', rowIndex);
							}else if(msg.indexOf('提交') > -1) {
								$('#tt').datagrid('reload');
							}
						},
						error : function() {
							alert("异常！");
						}
					});
				}
				
				(function() {
					var xlf = document.getElementById('xlf');
					if(!xlf.addEventListener) return;
					function handleFile(e) {
						//$('#tt').datagrid('importexcl','DataGrid');	
						do_file(e.target.files);
					}
					xlf.addEventListener('change', handleFile, false);
				})();
				
				var process_wb = (function() {
					var to_json = function to_json(workbook) {
						var result = {};
						workbook.SheetNames.forEach(function(sheetName) {
							var roa = XLSX.utils.sheet_to_json(workbook.Sheets[sheetName], {header:1});
							if(roa.length) result[sheetName] = roa;
						});
						return (result[Object.keys(result)[0]]);
					};		
					return function process_wb(wb) {	
						return to_json(wb)		
					};
				})();
			
				var do_file = (function() {
					return function do_file(files) {
						var f = files[0];
						var reader = new FileReader();
						reader.onload = function(e) {
							var data = e.target.result;
							var result =  process_wb(XLSX.read(data, {type: 'binary'}));
							var fields = $('#tt').datagrid('getColumnFields',true).concat($('#tt').datagrid('getColumnFields',false));		
							var jsonHead = {};
							for(var i=0; i<fields.length; i++){
							 	var option = $("#tt").datagrid('getColumnOption', fields[i]);
								if (option.field != "checkItem") { //过滤勾选框和隐藏列
				                   jsonHead[fields[i]]= option.title;
				                }
							}
							var data = new Array(); 					
							for(var i=1; i< result.length; i++){
								var jsonRow = {};
								for(var j=0; j< result[i].length; j++){	
									for(var p in jsonHead){  
										if(result[0][j]=== jsonHead[p]){
									  　　　　　	jsonRow[p]= result[i][j];
									　　　　　	break;  
									　　　}  						
									}
								}
								data.push(jsonRow);
							}
							$.ajax({
								type : "post",
								url : '${pageContext.request.contextPath}/materialStock.htm/saveRow',
								data : JSON.stringify(data),
								//traditional: true,
								dataType : "json",
								contentType : 'application/json;charset=utf-8', //设置请求头信息  
								beforeSend : function() {
									load();
								},
								complete : function() {
									disLoad();
								},
								success : function(result) {
									$('#tt').datagrid('loadData', result);
									$('#importExcelDlg').dialog('close');
								},
								error : function() {
									alert("异常！");
								}
							});
						};
						reader.readAsBinaryString(f);
					};
				})();