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
	<script type="text/javascript" src="./js/left-top1.js"></script>
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
			url:'${pageContext.request.contextPath}/cabin.htm/leftTopThree',
			traditional: true,
			dataType:"json",  
			beforeSend: function () {
				load();
			},
			complete: function () {
				disLoad();
			},
			success : function(result) {

				var myChart = echarts.init(document.getElementById('chart'));
				var xData = result[0].materialNum;
				var data1 = result[0].stockQuantity;
				var data2 = result[0].stockOutQuantity;
				var data3 = result[0].stockInQuantity;
				option = {					
					//backgroundColor: '#323a5e',
					backgroundColor:"#071435",
					"title" : {
						"text" : "装配件成品仓",
						"subtext" : "",
						textStyle: {
							color: "#ccc"
						}
					/* "x": "center" */
					},
					"tooltip" : {
						"trigger" : "axis",
						"axisPointer" : {
							"type" : "shadow"
						},
					},
					"grid" : {
						"borderWidth" : 0,
						top : '16%',
						left : '5%',
						right : '2%',
						bottom : '16%'
					},
					"legend" : {
						"x" : "right",
						"data" : []
					},
					"toolbox" : {
						"show" : true,
						"feature" : {
							"restore" : { },
							"saveAsImage" : { }
						}
					},
					"calculable" : true,
					"xAxis" : [
						{
							"type" : "category",
							"splitLine" : {
								"show" : false
							},
							"axisTick" : {
								"show" : false
							},
							"splitArea" : {
								"show" : false
							},
							/*"axisLabel": {
							    "interval": 0, 
							    "rotate": 45, 
							    "show": true, 
							    "splitNumber": 15, 
							    "textStyle": {
							        "fontFamily": "微软雅黑", 
							        "fontSize": 12
							    }
							}, */
							"data" : xData,
							axisLine: {
							   	lineStyle: {
							       	color: 'white'
							    }
							}
						}
					],
					"yAxis" : [
						{
							"type" : "value",
							"splitLine" : {
								"show" : false
							},
							"axisLine" : {
								"show" : true
							},
							"axisTick" : {
								"show" : false
							},
							"splitArea" : {
								"show" : false
							},
							axisLine: {
							   	lineStyle: {
							       	color: 'white'
							    }
							}
						}
					],
					"dataZoom" : [
						{
							"show" : true,
							"height" : 12,
							"xAxisIndex" : [
								0
							],
							bottom : 10,
							"start" : 0,
							"end" : 80
						},
						{
							"type" : "inside",
							"show" : true,
							"height" : 15,
							"xAxisIndex" : [
								0
							],
							"start" : 1,
							"end" : 35
						}
					],
					"series" : [
						{
							"name" : "库存",
							"type" : "bar",
							"stack" : "总量",
							"barMaxWidth" : 50,
							"barGap" : "10%",
							"itemStyle" : {
								"normal" : {
									"barBorderRadius" : 0,
									"color" : "rgba(60,169,196,0.5)",
									"label" : {
										"show" : true,
										"textStyle" : {
											"color" : "rgba(0,0,0,1)"
										},
										"position" : "insideTop",
										formatter : function(p) {
											return p.value > 0 ? (p.value) : '';
										}
									}
								}
							},
							"data" : data1
						},
						{
							"name" : "出库",
							"type" : "bar",
							"stack" : "总量",
							"itemStyle" : {
								"normal" : {
									"color" : "rgba(51,204,112,1)",
									"barBorderRadius" : 0,
									"label" : {
										"show" : true,
										"position" : "top",
										formatter : function(p) {
											return p.value > 0 ? ('▼'
											+ p.value + '')
												: '';
										}
									}
								}
							},
							"data" : data2
						},
						{
							"name" : "入库",
							"type" : "bar",
							"stack" : "总量",
							"itemStyle" : {
								"normal" : {
									"color" : "rgba(193,35,43,1)",
									"barBorderRadius" : 0,
									"label" : {
										"show" : true,
										"position" : "top",
										formatter : function(p) {
											return p.value > 0 ? ('▲'
											+ p.value + '')
												: '';
										}
									}
								}
							},
							"data" : data3
						}
					]
				}
				myChart.setOption(option);
			},
			error : function() {
			   alert("出错了！");
			}
		});	                     
    });  			
	
 
	
</script>
</html>