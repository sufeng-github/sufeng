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
			url : '${pageContext.request.contextPath}/cabin.htm/rightPartFour',
			traditional : true,
			dataType : "json",
			beforeSend : function() {
				load();
			},
			complete : function() {
				disLoad();
			},
			success : function(result) {
   				var orderNum = result[0].orderNum;
   				var stockInQuantity = result[0].stockInQuantity;
   				var stockInQuantity1 = result[0].stockInQuantity1;
   				var stockOutQuantity = result[0].stockOutQuantity;
   				var stockOutQuantity1 = result[0].stockOutQuantity1;
   				var analysis = result[0].analysis;
   				var analysis1 = result[0].analysis1;
   				
				var myChart = echarts.init(document.getElementById('chart'));
				var placeHoledStyle = {
				    normal:{
				        barBorderColor:'rgba(0,0,0,0)',
				        color:'rgba(0,0,0,0)'
				    },
				    emphasis:{
				        barBorderColor:'rgba(0,0,0,0)',
				        color:'rgba(0,0,0,0)'
				    }
				};
				var dataStyle = { 
				    normal: {
				        label : {
				            show: true,
				            position: 'insideLeft',
				            formatter: '{c}%'
				        }
				    }
				};
				option = {
					
					backgroundColor : "#344b58",
				    title: {
				        text: '定单状态',
				        subtext: '',
				        textStyle: {
						             color: "#ccc"
						         }
				        //sublink: 'http://e.weibo.com/1341556070/AiEscco0H'
				    },
				    tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        },
				        formatter : '{b}<br/>{a0}:{c0}%<br/>{a2}:{c2}%<br/>{a4}:{c4}%<br/>'
				    },
				    legend: {
				        x: 'center',
				        //itemGap : document.getElementById('main').offsetWidth / 8,
				        textStyle : {
							color : '#90979c',
						},
				        data:['分解', '入库','出库']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    grid: {
				        y: 80,
				        y2: 30
				    },
				    xAxis : [
				        {
				            type : 'value',
				            position: 'top',
				            splitLine: {show: false},
				            axisLabel: {show: false},
				            axisLine:{	            		
			            		lineStyle: {
							                 color: 'white'
							             }
			            	},
				        }
				    ],
				    yAxis : [
				        {
				            type : 'category',
				            splitLine: {show: false},
				            axisLine:{	            		
			            		lineStyle: {
							                 color: 'white'
							             }
			            	},
				            data : orderNum
				        }
				    ],
				    series : [
				        {
				            name:'分解',
				            type:'bar',
				            stack: '总量',
				            itemStyle : dataStyle,
				            data:analysis
				        },
				        {
				            name:'分解',
				            type:'bar',
				            stack: '总量',
				            itemStyle: placeHoledStyle,
				            data:analysis1
				        },
				        {
				            name:'入库',
				            type:'bar',
				            stack: '总量',
				            itemStyle : dataStyle,
				            data:stockInQuantity
				        },
				        {
				            name:'入库',
				            type:'bar',
				            stack: '总量',
				            itemStyle: placeHoledStyle,
				            data:stockInQuantity1
				        },
				        {
				            name:'出库',
				            type:'bar',
				            stack: '总量',
				            itemStyle : dataStyle,
				            data:stockOutQuantity
				        },
				        {
				            name:'出库',
				            type:'bar',
				            stack: '总量',
				            itemStyle: placeHoledStyle,
				            data:stockOutQuantity1
				        }/*,
				        {
				            name:'ZTW',
				            type:'bar',
				            stack: '总量',
				            itemStyle : dataStyle,
				            data:[71, 50, 31, 39]
				        },
				        {
				            name:'ZTW',
				            type:'bar',
				            stack: '总量',
				            itemStyle: placeHoledStyle,
				            data:[29, 50, 69, 61]
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