		function isOnlyRead(){
				var str = localStorage.getItem('authority1'); 			
		        str = str.substr(7, 1)
				if(str.charAt(0)==1){	
		        	return true;					        			
		        }else{
		        	return false;
		        }    		        	    	         		
		} 