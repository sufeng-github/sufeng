function setquantitydlgOK(){
	$('#setquantitydlg').dialog('close');
	var rows = $('#dg_screwDataStatistics').datagrid('getSelections');
	var currentIndex=0;	
	var start=0;
	for(var i=0; i<rows.length; i++){

		var index = $('#dg_screwDataStatistics').datagrid('getRowIndex', rows[i]);	
		var quantity = $('#setquantity').numberbox('getValue'); 	
		var data = $.param({  	
			'index':index,
			'name':rows[i].name,
			'xinghao':rows[i].xinghao,
			'guige':rows[i].guige,
			'caizhi':rows[i].caizhi,	
			'changjia':rows[i].changjia,
			'statement':quantity});
			
			$.ajax({  
				type: "post",  
				url:"screwdata.htm/setquantity",  
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
					$('#dg_screwDataStatistics').datagrid('updateRow',{index:obj[0].index, row:{statement:obj[0].statement}});								
				},
				error : function() {
					currentIndex++;
	                alert("异常！");
	            }
			});  
	}			
}