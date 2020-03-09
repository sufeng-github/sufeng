<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en"> 
<head> 
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>工作台</title> 

<link href="../../css/base.css" rel="stylesheet">

<link rel="stylesheet" href="../../css/workbench.css">
 	<link rel="stylesheet" href="../../../custom/uimaker/easyui.css"> 
 	<script src="../../../../incubator-echarts-4.2.1/dist/echarts.min.js"></script>
</head> 
<body>
    <div class="container">
        <div id="hd">
            
        </div>
        <div id="bd">
            <div class="bd-content">
                <div class="right-zone">
                    <div class="inform item-box"  id="chartHoop">
                        <div class="inform-hd">
                            <label>生产投入统计</label>
                            <a href="javascript:;">更多<span>&gt;</span></a>
                        </div>
                        
                    </div>
                    <div class="price item-box" >
                        <div class="inform-hd">
                            <label>排产计划</label>
                            <a href="javascript:;">更多<span>&gt;</span></a>
                        </div>  
                    
                        <div class="chart0-inner">
                          	<div class="item-content">                                 
                            	<div class="chart0-chart" id="main"></div>
                           	</div>
                       	</div>
                        

                    </div>
<!--                     <div class="attached item-box">
                        <div class="inform-hd">
                            <label>利润统计表</label>
                            <a href="javascript:;">更多<span>&gt;</span></a>
                        </div>
                        <div class="attached-tab">
                            <a href="javascript:;" class="current item-left" attached="public-attached">月利润统计</a>
                            <a href="javascript:;" class="item-right" attached="inner-attached">月利润统计分析</a>
                        </div>
                    
                    </div> -->
                </div>
                <div class="center-part">
                    <div class="center-items todo">
                        <div class="calendar-part">
                            <!--  <div class="easyui-calendar" style="width:205px;height:231px;"></div> -->
                            
                        </div> 
                        <ul class="work-items clearfix">
                            <li>
                                <div class="work-inner">
                                    <div class="work-item purple">
                                        <i class="iconfont">&#xe61f;</i>
                                        <span class="num" id="orderAlarm">14&nbsp;<span class="unit">个</span></span>
                                        <label>定单报警</label>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="work-inner">
                                    <div class="work-item red">
                                        <i class="iconfont">&#xe622;</i>
                                        <span class="num" id="purchaseAlarm">6&nbsp;<span class="unit">条</span></span>
                                        <label>采购报警</label>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="work-inner">
                                    <div class="work-item yellow">
                                         <i class="iconfont">&#xe61d;</i>
                                        <span class="num">9&nbsp;<span class="unit">封</span></span>
                                        <label>发货未处理信息</label>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="work-inner">
                                    <div class="work-item blue">
                                         <i class="iconfont">&#xe621;</i>
                                        <span title="2000,00万" class="num">2000,00&nbsp;<span class="unit">万</span></span>
                                        <label>财务未处理信息</label>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="work-inner">
                                    <div class="work-item purple">
                                         <i class="iconfont">&#xe61e;</i>
                                        <span title="2000,00万" class="num">100,00&nbsp;<span class="unit">万</span></span>
                                        <label>委外未处理信息</label>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
                                         <i class="iconfont">&#xe620;</i>
                                        <span class="num">10&nbsp;<span class="unit">个</span></span>
                                        <label>仓库异常信息</label>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="center-items chart0 clearfix">
                        <div class="chart0-item">
                            <div class="item-inner">
                                <div class="item-content">
                                    <div class="content-hd">采购</div>
                                    <div class="chart-chart" id="chart0"></div>
                                </div>
                            </div>
                        </div>
                        <div class="chart0-item">
                            <div class="item-inner">
                                <div class="item-content">
                                    <div class="content-hd">委外</div>
                                    <div class="chart-chart" id="chart1"></div>
                                </div>
                            </div>
                        </div>
                        <div class="chart0-item">
                            <div class="item-inner">
                                <div class="item-content">
                                    <div class="content-hd">销售</div>
                                    <div class="chart-chart" id="chart2"></div>
                                </div>
                            </div>
                        </div>
                        <div class="chart0-item">
                        </div>
                    </div>
                    <div class="center-items chart1">
                        <div class="chart1-inner">
                             <div class="item-hd">采购-委外-销售 ->对比图</div>
                             <div class="chart1-chart">
                             	<iframe scrolling="auto" frameborder="0"  src="../../../../incubator-echarts-4.2.1/test/bar-label-rotation.html" style="width:100%;height:100%;"></iframe>
                             </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="ft"></div>
    </div>
    <div class="todo-panel">
         <div class="todo-title">
            <i class="iconfont">&#xe61f;</i>
            <span class="num">14&nbsp;<span class="unit">个</span></span>
            <label>采购未处理</label>
        </div>
        <div class="todo-items">
            <ul>
                <li>
                    <span></span>
                    <a href="javascript:;" class="ellipsis">您有<span>2条</span>采购未处理信息<i></i></a>
                    <label>04-13</label>
                </li>
                <li>
                    <span></span>
                    <a href="javascript:;" class="ellipsis">您有<span>1条</span>生产异常未处理</a></a>
                    <label>04-13</label>
                </li>
                <li>
                    <span></span>
                    <a href="javascript:;" class="ellipsis">您有<span>4条</span>财务未处理信息</a></a>
                    <label>04-13</label>
                </li>            
            </ul>
        </div>
        
    </div>
    <script type="text/javascript" src="../../../custom/jquery.min.js"></script>
    <script type="text/javascript" src="../../../custom/jquery.easyui.min.js"></script>
    <!-- <script type="text/javascript" src="js/menu.js"></script> -->
    <!-- <script type="text/javascript" src="../../js/echarts-all.js"></script> -->
    <script type="text/javascript">
    	document.getElementById("orderAlarm").innerText="Hello,World!";
	</script>
    
    <script type="text/javascript">
    //chart0

    $(document).ready(function(){
       	var optionHoop = {

    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data: ['空闲', '停机','维修','工作','报警']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis:  {
        type: 'value'
    },
    yAxis: {
        type: 'category',
        data: ['设备一','设备二','设备三','设备四','设备五','设备六','设备七']
    },
    series: [
        {
            name: '空闲',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
            data: [320, 302, 301, 334, 390, 330, 320]
        },
        {
            name: '停机',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
            data: [120, 132, 101, 134, 90, 230, 210]
        },
        {
            name: '维修',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
            data: [220, 182, 191, 234, 290, 330, 310]
        },
        {
            name: '工作',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
            data: [150, 212, 201, 154, 190, 330, 410]
        },
        {
            name: '报警',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
            data: [820, 832, 901, 934, 1290, 1330, 1320]
        }
    ]
};

      var myChartHoop = echarts.init(document.getElementById('chartHoop'));
      myChartHoop.setOption(optionHoop);
    
    
    
        var option0 = {
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['采购'],
                show:false
            },
            toolbox: {
                show : false,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : ['周一','周二','周三','周四','周五','周六','周日']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'采购金额',
                    type:'bar',
                    data:[ 37, 48, 18,12,16,19,32],
                   	itemStyle:{
                      	normal:{
                           	color:'#4ad2ff'
                       	}
                  	},
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                }
            ]
        };

        var myChart0 = echarts.init(document.getElementById('chart0'));
        myChart0.setOption(option0);  
        
       	var option1 = {
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['委外'],
                show:false
            },
            toolbox: {
                show : false,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : ['周一','周二','周三','周四','周五','周六','周日']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'委外金额',
                    type:'bar',
                    data:[60, 45, 73, 16,19,20,32],
                   	itemStyle:{
                      	normal:{
                           	color:'#006699'
                       	}
                  	},
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                }
            ]
        };

        var myChart1 = echarts.init(document.getElementById('chart1'));
        myChart1.setOption(option1);  

        var option2 = {
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['出货'],
                show:false
            },
            toolbox: {
                show : false,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : ['周一','周二','周三','周四','周五','周六','周日']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'销售额',
                    type:'bar',
                    data:[60, 45, 73, 23, 37, 48, 18],
                   	itemStyle:{
                      	normal:{
                           	color:'#e5323e'
                       	}
                  	},
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                }
            ]
        };

        var myChart2 = echarts.init(document.getElementById('chart2'));
        myChart2.setOption(option2);         
          
        //我的待办点击事件
        $(document).on('click','.work-item.green',function(event){
            var width = (2 * $(this).width()) + 10;
            $(".todo-panel").width(width -2).css({top:$(this).offset().top,left:$(this).offset().left}).show();
            event.stopPropagation();
        });  
        $(document).on('click','.work-item.red',function(event){
            var width = (2 * $(this).width()) + 10;
            $(".todo-panel").width(width -2).css({top:$(this).offset().top,left:$(this).offset().left}).show();
            event.stopPropagation();
        });  
        $(document).on('click','.work-item.yellow',function(event){
            var width = (2 * $(this).width()) + 10;
            $(".todo-panel").width(width -2).css({top:$(this).offset().top,left:$(this).offset().left}).show();
            event.stopPropagation();
        });
        $(".todo-panel").click(function(){
             event.stopPropagation();
        });    
        $(document).click(function(){
            $(".todo-panel").hide();
        });      

    });
        
    //公开附件tab事件处理
    $(".attached-tab").on("click","a",function(){
        $(this).closest(".attached-tab").find("a").removeClass("current");
        $(this).addClass("current");
        $(this).closest(".attached").find("ul").addClass("hide");   
        $(this).closest(".attached").find("ul." + $(this).attr("attached")).removeClass("hide");    
    })
                    
    </script>
    
        <script>
        // 基于准备好的dmo，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
		var time_min = 1554799935;

		// 数据【数据说明：朝代、开始纪年、结束纪年、主要人物、首都】
		var data_chinese = [
			
			[ "1234656", 1554799935, 1554814335, "下弯副轮导轨右", "磨砂" ],
			[ "12569d", 1554814335, 1554825135, "下弯副轮导轨左", "折弯" ],
			[ "4852168", 1554850335, 1554861135, "下弯主轮导轨左", "切割" ],
			[ "7813213", 1554861135, 1554886335, "下弯主轮导轨右", "折弯" ],
			[ "78561031", 1554886335, 1554904335, "下弯副轮导轨左", "组装" ],
			[ "6549812", 1554904335, 1554911535, "下弯副轮导轨右", "磨砂" ],
			[ "84361321", 1554936735, 1554958335, "下弯副轮导轨左", "组装" ],
			[ "97984631", 1554958335, 1554997935, "下弯副轮导轨左", "磨砂" ]

	
		];
		var time_max = 1554997935;

		// 提取Y轴的数组
		var data_categories = [];
		for(var i=0; i<data_chinese.length; i++){
			data_categories[i] = data_chinese[i][0];
		}

		function renderItem(params, api) {
			var categoryIndex = api.value(0);
			var start = api.coord([api.value(1), categoryIndex]);
			var end = api.coord([api.value(2), categoryIndex]);
			var height = api.size([0, 1])[1] * 0.6;

			var rectShape = echarts.graphic.clipRectByRect({
				x: start[0],
				y: start[1] - height / 2,
				width: end[0] - start[0],
				height: height
			}, {
				x: params.coordSys.x,
				y: params.coordSys.y,
				width: params.coordSys.width,
				height: params.coordSys.height
			});

			return rectShape && {
				type: 'rect',
				shape: rectShape,
				style: api.style()
			};
		}

		option = {

			// 设置图表的标题
			title: {
				text: '',
				left: 'left'
			},

			// 设置浮动提示框的内容
			tooltip: {
				axisPointer: {
					type: 'cross'
				},
				formatter: function (params) {
					var params_return = "";
					params_return += params.marker;
					params_return += params.name;
					params_return += '<br/>产品: '+ params.value[3];
					params_return += '<br/>工序: '+ params.value[4];
					params_return += '<br/>开始时间: ';
					params_return += new Date(parseInt(params.value[1]) * 1000).toLocaleString().replace(/:\d{1,2}$/,' '); 
					params_return += '<br/>结束时间: ';
					params_return += new Date(parseInt(params.value[2]) * 1000).toLocaleString().replace(/:\d{1,2}$/,' '); 
					return params_return; 
				}
			},

			// 设置X轴与Y轴的放大器
			dataZoom: [{
				yAxisIndex: [0],
				type: 'slider',
				show: true,
				realtime: true,
				start: 0,
				end: 100,
				left: '1%',
			},{
				xAxisIndex: [0],
				type: 'slider',
				show: true,
				realtime: true,
				start: 0,
				end: 100,
				bottom: '5%',
			}],

			// 设置图表外边框距
			grid: {
				top: '5%',
				bottom: '20%',
				left: '15%',
				right: '3%',
			},

			// 设置X轴的参数
			xAxis: {
				name: '年',
				splitNumber: 10,
				min: time_min,
				max: time_max,
				splitLine: {
					show: true
				},
				axisLabel: {
					formatter: function (val) {
						//if(val < 0){ return 'BC'+ Math.abs(val) }else{ return Math.abs(val) }
						return new Date(parseInt(val) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');     
					},
					interval:0,
                    rotate:-15
				},
			},

			// 设置Y轴的参数
			yAxis: {
				data: data_categories,
				scale: true,
				splitLine: {
					show: true
				}
			},

			// 设置图表内容
			series: [{
				type: 'custom',
				renderItem: renderItem,
				itemStyle: {
					normal: {
						opacity: 0.8
					}
				},
				encode: {
					x: [1, 2],
					y: 0
				},
				data: data_chinese,
			}],
		};
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
</body> 
</html>
