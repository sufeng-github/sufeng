/*			function load() {  
				$("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");  
				$("<div class=\"datagrid-mask-msg\"></div>").html("数据处理中，请稍候。。。").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });  
			}
				//取消加载层  
			function disLoad() {  
				$(".datagrid-mask").remove();  
				$(".datagrid-mask-msg").remove();  
			}
*/
			function capImport(){
				//alert('123');
				$.ajax({ 
					type: "post",  
					url:'capitalmsg.htm/import',
					traditional: true,
					dataType:"json",  
					success: function(result) {
						alert('success')
					},
					error : function() {
						alert("异常！");
					}
				});  
			}	
			function capProductionAdd(){
				
			}
	      	function capProductionDelete(){
	      		
	      	} 
	      	function capProductionModify(){
	      		
	      	}
			