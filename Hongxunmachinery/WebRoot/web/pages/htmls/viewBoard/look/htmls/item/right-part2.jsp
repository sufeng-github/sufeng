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
			url : '${pageContext.request.contextPath}/cabin.htm/rightPartTwo',
			traditional : true,
			dataType : "json",
			beforeSend : function() {
				load();
			},
			complete : function() {
				disLoad();
			},
			success : function(result) {
				var xData = result[0].purchaseNum;
				var data1 = result[0].stockInQuantity;
				var data2 = result[0].stockInRoadQuantity;
				var data3 = result[0].stockQuantity;		
				var myChart = echarts.init(document.getElementById('chart'));
				option = {
					backgroundColor : "#344b58",
					"title" : {
						"text" : "采购(在途/入库)展示",
						"subtext" : "",
						x : "4%",
	
						textStyle : {
							color : '#fff'
						},
						subtextStyle : {
							color : '#90979c',
							fontSize : '16',
						},
					},
					"tooltip" : {
						"trigger" : "axis",
						"axisPointer" : {
							"type" : "shadow",
							textStyle : {
								color : "#fff"
							}
						},
					},
					"grid" : {
						"borderWidth" : 0,
						"top" : 50,
						"bottom" : '15%',
						textStyle : {
							color : "#fff"
						}
					},
					"legend" : {
						x : 'center',
						top : '11%',
						textStyle : {
							color : '#90979c',
						},
						"data" : [ '入库', '在途' ]
					},
	
	
					"calculable" : true,
					"xAxis" : [ {
						"type" : "category",
						"axisLine" : {
							lineStyle : {
								color : '#90979c'
							}
						},
						"splitLine" : {
							"show" : false
						},
						"axisTick" : {
							"show" : false
						},
						"splitArea" : {
							"show" : false
						},
						"axisLabel" : {
							"interval" : 0,
						},
						"data" : xData,
					} ],
					"yAxis" : [ {
						"type" : "value",
						"splitLine" : {
							"show" : false
						},
						"axisLine" : {
							lineStyle : {
								color : '#90979c'
							}
						},
						"axisTick" : {
							"show" : false
						},
						"axisLabel" : {
							"interval" : 0,
						},
						"splitArea" : {
							"show" : false
						},
					} ],
					"dataZoom" : [ {
						"show" : true,
						"height" : '12',
						"xAxisIndex" : [
							0
						],
						bottom : '2%',
						"start" : 10,
						"end" : 80,
						handleIcon : 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
						handleSize : '110%',
						handleStyle : {
							color : "#d3dee5",
						},
						textStyle : {
							color : "#fff"
						},
						borderColor : "#90979c"
					}, {
						"type" : "inside",
						"show" : true,
						"height" : 15,
						"start" : 1,
						"end" : 35
					} ],
					"series" : [ {
						"name" : "入库",
						"type" : "bar",
						"stack" : "总量",
						"barMaxWidth" : 35,
						"barGap" : "10%",
						"itemStyle" : {
							"normal" : {
								"color" : "rgba(255,144,128,1)",
								"label" : {
									"show" : true,
									"textStyle" : {
										"color" : "#fff"
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
							"name" : "在途",
							"type" : "bar",
							"stack" : "总量",
							"itemStyle" : {
								"normal" : {
									"color" : "rgba(0,191,183,1)",
									"barBorderRadius" : 0,
									"label" : {
										"show" : true,
										"position" : "top",
										formatter : function(p) {
											return p.value > 0 ? (p.value) : '';
										}
									}
								}
							},
							"data" : data2
						}, {
							"name" : "总数",
							"type" : "line",
							"stack" : "总量",
							symbolSize : 10,
							symbol : 'circle',
							"itemStyle" : {
								"normal" : {
									"color" : "rgba(252,230,48,1)",
									"barBorderRadius" : 0,
									"label" : {
										"show" : true,
										"position" : "top",
										formatter : function(p) {
											return p.value > 0 ? (p.value) : '';
										}
									}
								}
							},
							"data" : data3
						},
					]
				}
				myChart.setOption(option);
			},
			error : function() {
				alert("出错了！");
			}
		});
	</script>
</html>