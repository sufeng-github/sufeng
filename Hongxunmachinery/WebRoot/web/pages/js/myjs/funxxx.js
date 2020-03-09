		function funOne(tt,url,rowIndex,data,para){	 
			$.ajax({  
				type: "post",  
				url: url,
				data:data, 
				//traditional: true,
				dataType:"json", 
				contentType : 'application/json;charset=utf-8', //设置请求头信息  
				beforeSend: function () {
					load();
				},
				complete: function () {
					disLoad();
				},
				success: function(result) {	
					alert("123456");
					//if(result.length != 0){	
						if(para=="saverow" && result.length != 0){
							$(tt).datagrid('updateRow',{
								index: rowIndex,
								row: {
									idc: result[0].idc,
								}
							});	
							//$(tt).datagrid('refreshRow', rowIndex);
								
						}else if(para=="saverow1" && result.length != 0){
							$(tt).datagrid('updateRow',{
								index: rowIndex,
								row: {
									idc: result[0].idc,
									//materialMoney:result[0].materialMoney,
									//instoreDate:result[0].instoreDate
								}
							});	
						} else if(para=="refresh"){
							var rows = $('#tt').datagrid('getRows');
							for(var i=0; i<rows.length; i++){
								rowIndex = $('#tt').datagrid('getRowIndex',rows[i]);
								$(tt).datagrid('updateRow',{
									index: rowIndex,
									row: result[i]								
								});	
							}
						} else if(para=='getChildrens' || para=='datefind'){
							//$(tt).datagrid({data:result}).datagrid('clientPaging');; 						
							$(tt).datagrid('loadData', result);
						} 						
				}, 
				error : function() {
			       	alert("异常！");
			  	}
			});
		}
		
		function funTwo(tt, url, para){
           	$.ajax({ 
				type: "post",  
				//url:'${pageContext.request.contextPath}/screwdata.htm/load',  	
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
					alert(JSON.stringify(result));
					if(result.length==0){
						$(tt).datagrid('loadData', [{}])	
						$(tt).datagrid('deleteRow', 0);	 
					}else{
						$(tt).datagrid('loadData', result);
					}	
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	
        }
		
		function funThree(tt, url,data, para){
           	$.ajax({ 
				type: "post",  					
				url:url,
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
					//alert(JSON.stringify(result));
					if(result.length==0){
						$(tt).datagrid('loadData', [{}])	
						$(tt).datagrid('deleteRow', 0);	 
					}else{
						$(tt).datagrid('loadData', result);
					}	
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	
        }