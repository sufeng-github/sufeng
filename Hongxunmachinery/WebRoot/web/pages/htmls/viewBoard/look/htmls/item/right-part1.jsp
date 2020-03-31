<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	
	<head> 
	    <meta charset="utf-8"> 
	    
	    <title></title> 
		<link rel="stylesheet" href="./css/right-part1.css">
		<script type="text/javascript" src="../../incubator-echarts-4.2.1/dist/echarts.min.js"></script>
	    <script type="text/javascript" src="../../../../../../custom/jquery.min.js"></script>
	    <script type="text/javascript" src="../../../../../../custom/jquery.easyui.min.js"></script>
	    <script type="text/javascript" src="../../../../../js/myjs/myjs.js"></script> 
	
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
			url : '${pageContext.request.contextPath}/cabin.htm/rightPartOne',
			traditional : true,
			dataType : "json",
			beforeSend : function() {
				load();
			},
			complete : function() {
				disLoad();
			},
			success : function(result) {
				var date=new Date;
				var y = date.getFullYear();
				var myChart = echarts.init(document.getElementById('chart'));
				var data = [ {
					value : result[0].orderMoney,
					name : '销售额'
				}, {
					value : result[0].weiwaiMoney,
					name : '委外额'
				}, {
					value : result[0].purchaseMoney,
					name : '采购额'
				} /* , {
			    value: 17623,
			    name: '100w-500w'
			}, {
			    value: 3299,
			    name: '>500w'
			} */ ];
				option = {
					backgroundColor : "#344b58",
					title : {
						text : '利润分析',
						subtext : y + '年',
						x : 'center',
						y : 'center',
						textStyle : {
							fontWeight : 'normal',
							fontSize : 16,
							color : '#fff'							
						}
					},
					tooltip : {
						show : true,
						trigger : 'item',
						formatter : "{b}: {c} ({d}%)"
					},
					legend : {
						orient : 'horizontal',
						bottom : '0%'
					//data: ['<10w', '10w-50w', '50w-100w', '100w-500w', '>500w']
					},
					series : [ {
						type : 'pie',
						selectedMode : 'single',
						radius : [ '25%', '58%' ],
						color : [ '#86D560', '#AF89D6', '#59ADF3' ],
	
						label : {
							normal : {
								position : 'inner',
								formatter : '{d}%',
	
								textStyle : {
									color : '#fff',
									fontWeight : 'bold',
									fontSize : 14
								}
							}
						},
						labelLine : {
							normal : {
								show : false
							}
						},
						data : data
					}, {
						type : 'pie',
						radius : [ '58%', '83%' ],
						itemStyle : {
							normal : {
								color : '#F2F2F2'
							},
							emphasis : {
								color : '#ADADAD'
							}
						},
						label : {
							normal : {
								position : 'inner',
								formatter : '{c}元',
								textStyle : {
									color : '#777777',
									fontWeight : 'bold',
									fontSize : 14
								}
							}
						},
						data : data
					} ]
				};
				myChart.setOption(option);
			},
			error : function() {
				alert("出错了！");
			}
		});
	</script>
</html>