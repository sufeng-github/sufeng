		function getName(param){
			var name="";
			if(param=='son'){
				if(parent.getFatherParameter()=='productionStock.htm'){
					name='pro'
				}else{
					name='weld'
				}
			}else if(param=='sonson'){
				if(parent.parent.getFatherParameter()=='productionStock.htm'){
					name='pro'
				}else{
					name='weld'
				}
			}
			return name;
		}
        function isOnlyRead(){
			var str = localStorage.getItem('authority1'); 			
	        str = str.substr(6, 1);
			if(str.charAt(0)==1){	
	        	return true;					        			
	        }else{
	        	return false;
	        }    		        	    	         		
		} 
		