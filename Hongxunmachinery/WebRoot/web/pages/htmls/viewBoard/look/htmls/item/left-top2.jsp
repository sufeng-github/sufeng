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
 	$.ajax({ 
			type: "post",  	
			url:'${pageContext.request.contextPath}/cabin.htm/leftTopTwo',
			traditional: true,
			dataType:"json",  
			beforeSend: function () {
				load();
			},
			complete: function () {
				disLoad();
			},
			success: function(result) {
			var materialNum = result[0].materialNum;
   			var stockQuantity = result[0].stockQuantity;
   		 	var stockItemQuantity = result[0].stockItemQuantity;
   			var stockInRoadQuantity = result[0].stockInRoadQuantity;

			var myChart = echarts.init(document.getElementById('chart'));
			option = {
				//backgroundColor: '#323a5e',
				backgroundColor:"#071435",
				title: {
					text: '原材料仓库',
					textStyle: {
						color: "#ccc"
					}
				},
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    legend: {
			        data:['库存', '项目数', '在途数']	,
			        textStyle: {
						color: "#ccc"
					}		 
			    },
			    grid: {
			    	top:'16%',
			        left: '2%',
			        right: '4%',
			        bottom: '10%',
			        containLabel: true
			    },
			    xAxis : [
			        {
			           	type : 'category',
			            axisTick : {show: false},
			            data : materialNum ,
			            axisLine: {
						   	lineStyle: {
						       	color: 'white'
						    }
						}
			        }
			        
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            axisLine: {
						   	lineStyle: {
						       	color: 'white'
						    }
						}
			        }
			    ],
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
			    series : [
			        {
			            name:'库存',
			            type:'bar',
			            label: {
			                normal: {
			                    show: true,
			                    position: 'inside'
			                }
			            },
			            data:stockQuantity
			        },
			        {
			            name:'在途数',
			            type:'bar',
			            stack: '总量',
			            label: {
			                normal: {
			                    show: true
			                }
			            },
			            data:stockInRoadQuantity
			        },
			        {
			            name:'项目数',
			            type:'bar',
			            stack: '总量',
			            label: {
			                normal: {
			                    show: true,
			                    position: 'left'
			                }
			            },
			            data:stockItemQuantity
			        }
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