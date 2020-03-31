<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
<head> 
    <meta charset="utf-8"> 
    
    <title></title> 
	<link rel="stylesheet" href="./css/right-part1.css">
	<script type="text/javascript" src="../../incubator-echarts-4.2.1/dist/echarts.min.js"></script>
	<script type="text/javascript" src="../../../../../../custom/jquery.min.js"></script>
	<script type="text/javascript" src="../../../../../../custom/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../../../../../js/myjs/myjs.js"></script> 

</head>
	</head>
	<body>
		<div id="container">
			<div class="bd">
				<div class="zone">
					<div class="item" id="chart">

					</div>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		$.ajax({
			type : "post",
			url : '${pageContext.request.contextPath}/cabin.htm/rightPartFive',
			traditional : true,
			dataType : "json",
			beforeSend : function() {
				load();
			},
			complete : function() {
				disLoad();
			},
			success : function(result) {
			   	var materialNum =  result[0].materialNum;
   				var deliveryDate = result[0].deliveryDate;
   				var delayDate = result[0].delayDate;
				var myChart = echarts.init(document.getElementById('chart'));
				
				option = {
					backgroundColor : "#344b58",
		            title: {
		                text: '定单逾期报警',
		                left:'left',
		                textStyle: {
						             color: "#ccc"
						         }
		            },
		            tooltip: {
		                show:true,
		                trigger:'axis',
		                showDelay:0,
		                formatter:'{a0}: {c0}<br />{a1}: {c1}',
		                extraCssText: 'box-shadow: 0 0 3px rgba(0, 0, 0, 0.3);',
		                axisPointer:{
		                    type:'shadow'
		                }
		            },
		            legend: {
		                data:['实际天数','入库限期','出库限期','Good', 'Excellent '],
		                textStyle : {
							color : '#90979c',
						},
		                icon:'roundRect',
		                left:'right'
		                
		            },
		            xAxis: {
		            	boundaryGap: true,
		            	axisLine:{
		            		show:true,
		            		lineStyle: {
						                 color: 'white'
						
						             }
		            	},
		            	
		                data: materialNum/*  [
		                'KPI 1', 'KPI 2', 'KPI 3', 'KPI 4', 'KPI 5', 'KPI 3', 'KPI 4', 'KPI 5'
		                ] */
		            },
		            yAxis: {
		                type:'value',
		               // max:120,
		                splitLine:{
		                	show:false
		                	
		                },
		                axisLine:{
		            		
		            		lineStyle: {
						                 color: 'white'
						
						             }
		            	},
		                axisLabel:{
		                	formatter: '{value} 天'
		                	
		                }
		            },
		            dataZoom: [{
						         show: true,
						         height: 12,
						         xAxisIndex: [
						             0
						         ],
						         bottom: '4%',
						         start: 30,
						         end: 60,
						         handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
						         handleSize: '110%',
						         handleStyle: {
						             color: "#d3dee5",
						
						         },
						         textStyle: {
						             color: '#fff'
						         },
						         borderColor: '#90979c'
						     }, 
						     {
						         type: 'inside',
						         show: true,
						         height: 15,
						         start: 1,
						         end: 35
						     }
						 ],
		            series: [
		            {
		                name: '实际天数',
		                type: 'bar',
		                barGap:'-225%',
		                barWidth:'20%',
		                z:10,
		                itemStyle:{
		                	normal:{
		                		color:'#29252C'
		                	}
		                },
		                data: delayDate/* [ 110, 94, 97, 78, 68, 97, 78, 68] */
		            },
		            {
		            	name:'入库限期',
		            	type:'bar',
		            	//barGap:'-130%',
		            	barWidth:'70%',
		            	z:9,
		                itemStyle:{
		                	normal:{
		                		color:'#AACFD0'
		                	}
		                },
		            	stack:'total',
		            	data: deliveryDate /* [70, 70, 70, 70, 70, 97, 78, 68] */
		            }/*,
		             {
		            	name:'出库限期',
		            	type:'bar',
		            	//barGap:'-15%',
		            	barWidth:'70%',
		                itemStyle:{
		                	normal:{
		                		color:'#6b8ba3'
		                	}
		                },
		            	stack:'total',
		            	data: deliveryDate 
		            }*/
		           ]
		        }; 
				myChart.setOption(option);  
			},
			error : function() {
				alert("出错了！");
			}
		});	 
	</script>
</html>