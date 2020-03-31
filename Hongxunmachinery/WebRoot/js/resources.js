			function load() {  
				$("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");  
				$("<div class=\"datagrid-mask-msg\"></div>").html("鏁版嵁澶勭悊涓紝璇风◢鍊欍�傘�傘��").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });  
			}
				//鍙栨秷鍔犺浇灞�  
			function disLoad() {  
				$(".datagrid-mask").remove();  
				$(".datagrid-mask-msg").remove();  
			}
			
			var jsondata;
			function pagerFilter(data){
				if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
					data = {
						total: data.length,
						rows: data
					}
				}
				var dg = $(this);
				var opts = dg.datagrid('options');
				var pager = dg.datagrid('getPager');
				pager.pagination({
					onSelectPage:function(pageNum, pageSize){
						opts.pageNumber = pageNum;
						opts.pageSize = pageSize;
						pager.pagination('refresh',{
							pageNumber:pageNum,
							pageSize:pageSize
						});
						dg.datagrid('loadData',data);
					}
				});
				if (!data.originalRows){
					data.originalRows = (data.rows);
				}
				var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
				var end = start + parseInt(opts.pageSize);
				data.rows = (data.originalRows.slice(start, end));
				return data;
			}
			
			 function getNowFormatDate() {
			        var date = new Date();
			        var seperator1 = "-";
			        var year = date.getFullYear();
			        var month = date.getMonth() + 1;
			        var strDate = date.getDate();
			        if (month >= 1 && month <= 9) {
			            month = "0" + month;
			        }
			        if (strDate >= 0 && strDate <= 9) {
			            strDate = "0" + strDate;
			        }
			        var currentdate = year + seperator1 + month + seperator1 + strDate;
			        return currentdate;
			    }
			 
				function total(dg){
					var p = $(dg).datagrid('getPager');
					var data = $(dg).datagrid('getData'); 
			    	var options = $(dg).datagrid('getPager').data("pagination").options;  
	    			return options.total;
				}
				
			 $.fn.serializeObject = function()    
			 {    
			    var o = {};    
			    var a = this.serializeArray();    
			    $.each(a, function() {    
			        if (o[this.name]) {    
			            if (!o[this.name].push) {    
			                o[this.name] = [o[this.name]];    
			            }    
			            o[this.name].push(this.value || '');    
			        } else {    
			            o[this.name] = this.value || '';    
			        }    
			    });    
			    return o;    
			 };
			 
			function loadDlgHelpData(dlg,url,title){
				$(dlg).dialog('open')
				$(dlg).panel('refresh', url);
				$(dlg).panel({title: title});
			}
			 

			
				function loadData(url,title,aurl,dataGrid,icon){
		
					$('#wu-tabs').panel('refresh', url);
					$('#wu-tabs').panel({title: title});
					$('#wu-tabs').panel({iconCls: icon});
					
					ajaxFunction(aurl,dataGrid);				
				}
				function ajaxFunction(aurl,dataGrid){

					$.ajax({ 
						type: "post",  
						url:aurl,  	
						traditional: true,
						dataType:"json",  
						beforeSend: function () {
							load();
						},
						complete: function () {
							disLoad();
						},
						success: function(result) {
							var dg = null;
							dg = document.getElementById(dataGrid.replace('#',''));
							if(dg == null){
								setTimeout(function(){
									$(dataGrid).datagrid('loadData', result);	
									jsondata = result;
									//alert('1');
								},1000);
							}else{
								$(dataGrid).datagrid('loadData', result);	
								jsondata = result;
								//alert('2');
							}	
						},
						error : function() {
			                 alert("寮傚父锛�");
			            }
					});					
				}
			/* 
			function getLayoutTitle(){
            	if($('#layoutID').layout('options').title=='guangzhou'){
            		return 'guangzhou'
            	}else if($('#layoutID').layout('options').title=='zhongshan'){
            		return 'zhongshan'
            	}else{
            		return '';
            	}
			}*/
				
				function exportExcelExample(dg){
					var data = $(dg).datagrid('getData');
					var json = data.originalRows;
					var type;
					for(var i=0; i<json.length; i++){
						var id = json[i].ID; 
						delete json[i].ID;
						json[i].id = id;
					}		
		            var tmpdata = json[0];
		           
		            json.unshift({});
		            var keyMap = []; //鑾峰彇keys
		            //keyMap =Object.keys(json[0]);
		            for (var k in tmpdata) {
		            	keyMap.push(k);
			           	json[0][k] = k;
		            }        
		            
	          		var tmpdata = [];//鐢ㄦ潵淇濆瓨杞崲濂界殑json 
	                json.map((v, i) => keyMap.map((k, j) => Object.assign({}, {
	                    v: v[k],
	                    position: (j > 25 ? getCharCol(j) : String.fromCharCode(65 + j)) + (i + 1)
	                }))).reduce((prev, next) => prev.concat(next)).forEach((v, i) => tmpdata[v.position] = {
	                    v: v.v
	                });
	                var outputPos = Object.keys(tmpdata); //璁剧疆鍖哄煙,姣斿琛ㄦ牸浠嶢1鍒癉10         
	                var tmpWB = {
	                    SheetNames: ['mySheet'], //淇濆瓨鐨勮〃鏍囬
	                    Sheets: {
	                        'mySheet': Object.assign({},
	                            tmpdata, //鍐呭
	                            {
	                                '!ref': outputPos[0] + ':' + outputPos[outputPos.length - 1] //璁剧疆濉厖鍖哄煙
	                            })
	                    }
	                };
	         
	              	var  tmpDown = new Blob([s2ab(XLSX.write(tmpWB, 
	                    {bookType: (type == undefined ? 'xlsx':type),bookSST: false, type: 'binary'}//杩欓噷鐨勬暟鎹槸鐢ㄦ潵瀹氫箟瀵煎嚭鐨勬牸寮忕被鍨�
	                    ))], {
	                    type: ""
	                }); //鍒涘缓浜岃繘鍒跺璞″啓鍏ヨ浆鎹㈠ソ鐨勫瓧鑺傛祦
	             
		            var href = URL.createObjectURL(tmpDown); //鍒涘缓瀵硅薄瓒呴摼鎺�
		            document.getElementById("hf").href = href; //缁戝畾a鏍囩
		            document.getElementById("hf").click(); //妯℃嫙鐐瑰嚮瀹炵幇涓嬭浇
		            setTimeout(function() { //寤舵椂閲婃斁
		                URL.revokeObjectURL(tmpDown); //鐢║RL.revokeObjectURL()鏉ラ噴鏀捐繖涓猳bject URL
		            }, 100);
		        }

		        function s2ab(s) { //瀛楃涓茶浆瀛楃娴�
		            var buf = new ArrayBuffer(s.length);
		            var view = new Uint8Array(buf);
		            for (var i = 0; i != s.length; ++i) view[i] = s.charCodeAt(i) & 0xFF;
		            return buf;
		        }
		         // 灏嗘寚瀹氱殑鑷劧鏁拌浆鎹负26杩涘埗琛ㄧず銆傛槧灏勫叧绯伙細[0-25] -> [A-Z]銆�
		        function getCharCol(n) {
		            lettemCol = '',
		            s = '',
		            m = 0
		            while (n > 0) {
		                m = n % 26 + 1
		                s = String.fromCharCode(m + 64) + s
		                n = (n - m) / 26
		            }
		            return s
		        }	
		        
	            /*
	           // FileReader鍏辨湁4绉嶈鍙栨柟娉曪細
	           // 1.readAsArrayBuffer(file)锛氬皢鏂囦欢璇诲彇涓篈rrayBuffer銆�
	           // 2.readAsBinaryString(file)锛氬皢鏂囦欢璇诲彇涓轰簩杩涘埗瀛楃涓�
	           // 3.readAsDataURL(file)锛氬皢鏂囦欢璇诲彇涓篋ata URL
	           // 4.readAsText(file, [encoding])锛氬皢鏂囦欢璇诲彇涓烘枃鏈紝encoding缂虹渷鍊间负'UTF-8'
	            
	           
	            function importf(obj) {//瀵煎叆		
	            	var wb;//璇诲彇瀹屾垚鐨勬暟鎹�
	             	var rABS = true; //鏄惁灏嗘枃浠惰鍙栦负浜岃繘鍒跺瓧绗︿覆
	            	var url;
	            	var dg;
					if(document.getElementById("dg_screwData")!=null){
						url = 'screwdata.htm/importexcel';
						dg	= '#dg_screwData';		
					}else if(document.getElementById("dg_screwOut")!=null){
						url = 'screwdata.htm/importexcel';
						dg	= '#dg_screwOut';		
					}else if(document.getElementById("dg_screwOrder")!=null){
						url = 'screwdata.htm/importexcel';
						dg	= '#dg_screwOrder';				
					}else if(document.getElementById("dg_screwAcceptance")!=null){
						url = 'screwacceptance.htm/importexcel';
						dg	= '#dg_screwAcceptance';								
					}else{
						alert('姝ら〉闈㈠皻涓嶆敮鎸佸鍏ュ姛鑳�!');
						return;
					}
					
	                if(!obj.files) {
	                    return;
	                }
	                var f = obj.files[0];
	                var reader = new FileReader();
	                reader.onload = function(e) {
	                    var data = e.target.result;
	                    if(rABS) {
	                        wb = XLSX.read(btoa(fixdata(data)), {//鎵嬪姩杞寲
	                            type: 'base64'
	                        });
	                    } else {
	                        wb = XLSX.read(data, {
	                            type: 'binary'
	                        });
	                    }
	                    //wb.SheetNames[0]鏄幏鍙朣heets涓涓�涓猄heet鐨勫悕瀛�
	                    //wb.Sheets[Sheet鍚峕鑾峰彇绗竴涓猄heet鐨勬暟鎹�
	                   	//document.getElementById("demo").innerHTML= JSON.stringify( XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]));
	                    //alert(JSON.stringify(XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]])));
	                    var data = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]);
		               	//var rows = $('#dg_screwData').datagrid('getSelections');	
		              // 	for(var i=0; i<data.length; i++){
			           //    	var id = data[i].ID; 
						//	delete data[i].ID;
						//	data[i].id = id;
		               //	}
		               	//alert(JSON.stringify(data));               		
						//var saveDataAry=[];
						//var id = rows[0].ID; 
						//delete rows[0].ID;
						//rows[0].id = id;
						//saveDataAry.push(rows[0]);
						//alert(JSON.stringify(saveDataAry));
						$.ajax({
							type:"post",
							url:url,
							dataType:"json",
							contentType:"application/json",
							data:JSON.stringify(data),
							beforeSend: function () {
								load();
							},
							complete: function () {
								disLoad();
							},
							success:function(){
								$(dg).datagrid('loadData', data);
							}
						});               
	                };               
	                if(rABS) {
	                    reader.readAsArrayBuffer(f);
	                } else {
	                    reader.readAsBinaryString(f);
	                }               
	            }

	            function fixdata(data) { //鏂囦欢娴佽浆BinaryString
	                var o = "",
	                    l = 0,
	                    w = 10240;
	                for(; l < data.byteLength / w; ++l) o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w, l * w + w)));
	                o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w)));
	                return o;
	            }
	            */
				