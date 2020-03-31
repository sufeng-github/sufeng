function chart(value){
	  	var myChart = echarts.init(document.getElementById('chart'));
		
	  
		option = {
		    dataset: {
		        source: value //æ°æ®æº
		    },
		    backgroundColor: "#344b58",
		    "title": {
		        "text": "测试",
		        //"subtext": "BY Wang Dingding",
		        x: "4%",
		
		        textStyle: {
		            color: '#fff',
		            fontSize: '22'
		        },
		        subtextStyle: {
		            color: '#90979c',
		            fontSize: '16',
		
		        },
		    },
		    "tooltip": {
		        "trigger": "axis",
		        "axisPointer": {
		            "type": "shadow",
		            textStyle: {
		                color: "#fff"
		            }
		
		        },
		    },
		    "grid": {
		        "borderWidth": 0,
		        "top": 50,
		        "bottom": 65,
		        textStyle: {
		            color: "#fff"
		        }
		    },
		    /*"legend": {
		        x: '4%',
		        top: '11%',
		        textStyle: {
		            color: '#90979c',
		        },
		       
		    },*/
		
		    "calculable": true,
		    "xAxis": [{
		        "type": "category",
		        "axisLine": {
		            lineStyle: {
		                color: '#90979c'
		            }
		        },
		        "splitLine": {
		            "show": false
		        },
		        "axisTick": {
		            "show": false
		        },
		        "splitArea": {
		            "show": false
		        },
		        "axisLabel": {
		            "interval": 0,
		
		        },
		      
		    }],
		    "yAxis": [{
		        "type": "value",
		        "splitLine": {
		            "show": false
		        },
		        "axisLine": {
		            lineStyle: {
		                color: '#90979c'
		            }
		        },
		        "axisTick": {
		            "show": false
		        },
		        "axisLabel": {
		            "interval": 0,
		
		        },
		        "splitArea": {
		            "show": false
		        },
		
		    }],
		    "dataZoom": [{
		        "show": true,
		        "height": 30,
		        "xAxisIndex": [
		            0
		        ],
		        bottom: 10,
		        "start": 50,
		        "end": 100,
		        handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
		        handleSize: '110%',
		        handleStyle:{
		            color:"#d3dee5",
		            
		        },
		           textStyle:{
		            color:"#fff"},
		           borderColor:"#90979c"
		        
		        
		    }, {
		        "type": "inside",
		        "show": true,
		        "height": 15,
		        "start": 1,
		        "end": 35
		    }],
		    "series": [{
		            "name": "name1",
		            "type": "bar",
		            "stack": "name-1",
		            "barMaxWidth": 35,
		            "barGap": "10%",
		            "itemStyle": {
		                "normal": {
		                    "color": "rgba(103,194,58,1)",
		                    "label": {
		                        "show": true,
		                        "textStyle": {
		                            "color": "#fff"
		                        },
		                        "position": "insideTop",
		                        formatter: function(p) {
		                            return p.value > 0 ? (p.value) : '';
		                        }
		                    }
		                }
		            },
		          
		        },
		
		      /*  {
		            "name": "å¤±è´¥",
		            "type": "bar",
		            "stack": "æ»é",
		            "itemStyle": {
		                "normal": {
		                    "color": "rgba(245,108,108,1)",
		                    "barBorderRadius": 0,
		                    "label": {
		                        "show": true,
		                        "position": "top",
		                        formatter: function(p) {
		                            return p.value > 0 ? (p.value) : '';
		                        }
		                    }
		                }
		            },
		
		        },*/ {
		            "name": "name2",
		            "type": "line",
		            "stack": "name-2",
		            symbolSize:10,
		            symbol:'circle',
		            "itemStyle": {
		                "normal": {
		                    "color": "rgba(230,162,60,1)",
		                    "barBorderRadius": 0,
		                    "label": {
		                        "show": true,
		                        "position": "top",
		                        formatter: function(p) {
		                            return p.value > 0 ? (p.value) : '';
		                        }
		                    }
		                }
		            },
		
		        },
		    ]
		}
	    
		myChart.setOption(option);

	}