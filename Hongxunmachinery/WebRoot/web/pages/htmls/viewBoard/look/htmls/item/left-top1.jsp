<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>echarts</title>
	<link rel="stylesheet" href="./css/left-top1.css">
	<script type="text/javascript" src="../../incubator-echarts-4.2.1/dist/echarts.min.js"></script>
    <script type="text/javascript" src="../../../../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../../../custom/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../../../../js/myjs/myjs.js"></script> 
<!-- 	<script type="text/javascript" src="./js/left-top1.js"></script> -->
</head>
<body>
	<div id="container">
		<div class="bd">
			<div class="zone">		
				<div class="item chart" id="chart"></div>
			</div>
		</div>
	</div>			
</body>
<script type="text/javascript">
	$(function(){				
            //$('#tt').datagrid({data:getData('#tt','${pageContext.request.contextPath}/purchaseNum.htm/loadData')}).datagrid('clientPaging');       
           
            $.ajax({ 
				type: "post",  	
				url:'${pageContext.request.contextPath}/cabin.htm/leftTopOne',
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
					var date=new Date;
					var y = date.getFullYear();
					//alert (y);
					var myChart = echarts.init(document.getElementById('chart'));
					var xcoord =[y+'-01', y+'-02', y+'-03', y+'-04', y+'-05', y+'-06', y+'-07', y+'-08', y+'-09', y+'-10', y+'-11', y+'-12']
					var data1 = result[0].purchaseMoney;//[400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500];
					var data2 = result[0].weiwaiMoney;//[200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300];
					var data3 = result[0].orderMoney;//[10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120];
						var option = {
						     //backgroundColor: '#323a5e',
						     backgroundColor:"#071435",
						     title: {
						         text: '采购/委外/销售',
						         textStyle: {
						             color: "#ccc"
						         }
						     },
						     tooltip: {
						         trigger: 'axis',
						         axisPointer: { // 坐标轴指示器，坐标轴触发有效
						             type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
						         }
						     },
						     grid: {
						         left: '2%',
						         right: '4%',
						         bottom: '10%',
						         top: '16%',
						         containLabel: true
						     },
						     toolbox: {
						         show: true,
						         feature: {
						             mark: {
						                 show: true
						             },
						             dataView: {
						                 show: true,
						                 readOnly: false
						             },
						             magicType: {
						                 show: true,
						                 type: ['line', 'bar']
						             },
						             restore: {
						                 show: true
						             },
						             saveAsImage: {
						                 show: true
						             }
						         }
						     },
						     calculable: true,
						     legend: {
						         data: ['原料采购', '委外加工', '成品销售'],
						         top: 12,
						         textStyle: {
						             color: "#fff"
						         },
						         itemWidth: 12,
						         itemHeight: 10,
						         // itemGap: 35
						     },
						     xAxis: {
						         type: 'category',
						         data: xcoord,
						         axisLine: {
						             lineStyle: {
						                 color: 'white'
						
						             }
						         },
						         axisLabel: {
						             // interval: 0,
						             // rotate: 40,
						             textStyle: {
						                 fontFamily: 'Microsoft YaHei'
						             }
						         },
						     },
						
						     yAxis: {
						         type: 'value',
						         //max: '1200',
						         axisLine: {
						             show: true,
						             lineStyle: {
						                 color: 'white'
						             }
						         },
						         splitLine: {
						             show: true,
						             lineStyle: {
						                 color: 'rgba(255,255,255,0.3)'
						             }
						         },
						         axisLabel: {}
						     },
						     dataZoom: [{
						         show: true,
						         height: 12,
						         xAxisIndex: [
						             0
						         ],
						         bottom: '4%',
						         start: 10,
						         end: 90,
						         handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
						         handleSize: '110%',
						         handleStyle: {
						             color: "#d3dee5",
						
						         },
						         textStyle: {
						             color: '#fff'
						         },
						         borderColor: '#90979c'
						     }, {
						         type: 'inside',
						         show: true,
						         height: 15,
						         start: 1,
						         end: 35
						     }],
						     series: [{
						             name: '采购金额',
						             type: 'bar',
						             barWidth: '15%',
						             label: {
						                 normal: {
						                     show: true,
						                     lineHeight: 30,
						                     width: 80,
						                     height: 30,
						                     backgroundColor: '#252453',
						                     borderRadius: 200,
						                     position: ['-8', '-60'],
						                     distance: 1,
						                     formatter: [
						                         '    {d|●}',
						                         ' {a|{c}}     \n',
						                         '    {b|}'
						                     ].join(','),
						                     rich: {
						                         d: {
						                             color: '#3CDDCF',
						                         },
						                         a: {
						                             color: '#fff',
						                             align: 'center',
						                         },
						                         b: {
						                             width: 1,
						                             height: 30,
						                             borderWidth: 1,
						                             borderColor: '#234e6c',
						                             align: 'left'
						                         },
						                     }
						                 }
						             },
						             itemStyle: {
						                 normal: {
						                     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
						                         offset: 0,
						                         color: '#248ff7'
						                     }, {
						                         offset: 1,
						                         color: '#6851f1'
						                     }]),
						                     barBorderRadius: 11,
						                 }
						             },
						             data: data1
						         },
						         {
						             name: '委外金额',
						             type: 'bar',
						             barWidth: '15%',
						             label: {
						                 normal: {
						                     show: true,
						                     lineHeight: 30,
						                     width: 80,
						                     height: 30,
						                     backgroundColor: '#252453',
						                     borderRadius: 200,
						                     position: ['-8', '-60'],
						                     distance: 1,
						                     formatter: [
						                         '    {d|●}',
						                         ' {a|{c}}     \n',
						                         '    {b|}'
						                     ].join(','),
						                     rich: {
						                         d: {
						                             color: '#3CDDCF',
						                         },
						                         a: {
						                             color: '#fff',
						                             align: 'center',
						                         },
						                         b: {
						                             width: 1,
						                             height: 30,
						                             borderWidth: 1,
						                             borderColor: '#234e6c',
						                             align: 'left'
						                         },
						                     }
						                 }
						             },
						             itemStyle: {
						                 normal: {
						                     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
						                         offset: 0,
						                         color: '#8bd46e'
						                     }, {
						                         offset: 1,
						                         color: '#09bcb7'
						                     }]),
						                     barBorderRadius: 11,
						                 }
						
						             },
						             data: data2
						         },
						         {
						             name: '销售金额',
						             type: 'bar',
						             barWidth: '15%',
						             itemStyle: {
						                 normal: {
						                     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
						                         offset: 0,
						                         color: '#fccb05'
						                     }, {
						                         offset: 1,
						                         color: '#f5804d'
						                     }]),
						                     barBorderRadius: 12,
						                 },
						             },
						             label: {
						                 normal: {
						                     show: true,
						                     lineHeight: 30,
						                     width: 80,
						                     height: 30,
						                     backgroundColor: '#252453',
						                     borderRadius: 200,
						                     position: ['-8', '-60'],
						                     distance: 1,
						                     formatter: [
						                         '    {d|●}',
						                         ' {a|{c}}     \n',
						                         '    {b|}'
						                     ].join(','),
						                     rich: {
						                         d: {
						                             color: '#3CDDCF',
						                         },
						                         a: {
						                             color: '#fff',
						                             align: 'center',
						                         },
						                         b: {
						                             width: 1,
						                             height: 30,
						                             borderWidth: 1,
						                             borderColor: '#234e6c',
						                             align: 'left'
						                         },
						                     }
						                 }
						             },
						             data: data3
						         }
						     ]
						 };
						myChart.setOption(option);	
				},
				error : function() {
			       	alert("出错了！");
			   	}
			});	
            
                          
    });  	
	
 /*  	$(function(){				
		$.ajax({ 
				type: "post",  			
				url:'${pageContext.request.contextPath}/purchaseNum.htm/loadData',
				traditional: true,
				dataType:"json",  
				beforeSend: function () {
					//load();
				},
				complete: function () {
					//disLoad();
				},
				success: function(result) {
					var dateArr = new Array();
					var moneyArr =new Array();
					for(var i =0;i<result.length;i++){
						var purchaseDate = ''; var purchaseOrderAmount = 0;
					    for(var key in result[i]){
					        //console.log(key+":"+result[i][key])
					        if('purchaseDate' == key){
					        	console.log(key+":"+result[i][key].substr(0,7));
					        	purchaseDate = result[i][key].substr(0,7);
					        }else if('purchaseOrderAmount'==key){
					        	purchaseOrderAmount = result[i][key]
					        }					    
					    }
					    
					    var index = dateArr.indexOf(purchaseDate);
					    if(index==-1){
							dateArr.push(purchaseDate);
							moneyArr.push(purchaseOrderAmount);		
						}else{
							moneyArr[index] = moneyArr[index] + purchaseOrderAmount
						}	
								   
					}
					
					var valueArr = new Array();
					for(var i=0;i<dateArr.length;i++){
						var value = new Array();
						value.push(dateArr[i]);
						value.push(moneyArr[i]);
						value.push(0);
						value.push(0);
						valueArr.push(value);
					}
				
					var value = [
						    ['2016-09', 2242.6, 0, 0],
						    ['2016-10', 3312.5, 7.4, 7.2],
						    ['2016-11', 2127.5, 6.4, 8.2],
						    ['2016-12', 3527, 8.1, 6.2],
						    ['2017-01', 3547.3, 7.1, 6.2],
						    ['2017-02', 2927.5, 17.4, 9.2],
						    ['2017-03', 3126.5, 7.4, 4.2],
						    ['2017-04', 2677, 7.1, 9.2],
						    ['2017-05', 3546.5, 27.2, 11.2],
						    ['2017-06', 3327.1, 7.2, 11.2],
						    ['2017-07', 2226.1, 7.1, 7.2],
						    ['2017-08', 3116.5, 7.4, 21.2],
						    ['2017-09', 3336.6, 7.4, 19.2],
						    ['2017-10', 3222, 17.1, 16.2],
						    ['2017-11', 3337.5, 7.2, 12.2],
						    ['2017-12', 2447.1, 7.2, 11.2],
						    ['2018-01', 3117, 7.1, 12.2],
						    ['2018-02', 417.5, 1.4, 11.2],
						    ['2018-03', 2227.5, 7, 15.2],
						    ['2018-04', 3117, 7.1, 5.2],
						    ['2018-05', 3117.3, 8.1, 7.2],
						    ['2018-06', 3227.6, 7.1, 12.2],
						    ['2018-07', 2337.3, 7.1, 1.2],
						    ['2018-08', 2337.3, 7.1, 1.2],
						    ['2018-09', 2337.3, 7.1, 1.2],
						    ['2018-10', 2337.3, 7.1, 1.2],
						    ['2018-11', 2337.3, 7.1, 1.2],
						    ['2018-12', 2337.3, 7.1, 1.2],    
						    ['2019-03', 2337.3, 7.1, 1.2],
						];    
					chart(valueArr);				
				},
				error : function() {
			       	alert("异常");
			   	}
			});	 
           
    });     */
	
	
</script>
</html>