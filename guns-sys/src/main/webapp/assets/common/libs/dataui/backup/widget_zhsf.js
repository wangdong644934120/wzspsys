//智慧书房
//背景地图
Bswidget.prototype.BigMap = function(widgetBean) {
	Bswidget.call(this, widgetBean);
	var self = this;
	var myChart = null;
	this.onShow = function() {
		myChart = echarts.init(self.container.get(0));
		var option = option = {
			tooltip : {
				trigger : 'item'
			},
			bmap : {
				center : [ 122.038827, 37.249083 ],
				zoom : 11,
				roam : true,
				mapStyle : {
					style : "midnight"// 默认午夜样式
				}
			},
			series : [ {
				name : '自助图书馆',
				type : 'scatter',
				symbolSize : 8,
				coordinateSystem : 'bmap',
				data : [],
				label : {
					normal : {
						formatter : '自助图书馆',
						position : 'right',
						show : false
					},
					emphasis : {
						show : true
					}
				},
				itemStyle : {
					normal : {
						color : 'yellow'
					}
				}
			}, {
				name : '城市书房',
				type : 'scatter',
				symbolSize : 10,
				coordinateSystem : 'bmap',
				data : [],
				label : {
					normal : {
						formatter : '城市书房',
						position : 'right',
						show : false
					},
					emphasis : {
						show : true
					}
				},
				itemStyle : {
					normal : {
						color : '#9FC33D'
					}
				}
			}, {
				name : '项目',
				type : 'effectScatter',
				coordinateSystem : 'bmap',
				data : [],
				symbolSize : 20,
				showEffectOn : 'render',
				rippleEffect : {
					brushType : 'stroke'
				},
				hoverAnimation : true,
				label : {
					normal : {
						formatter : '会展中心项目',
						position : 'right',
						show : true
					}
				},
				itemStyle : {
					normal : {
						color : '#76EEC6',
						shadowBlur : 10,
						shadowColor : '#333'
					}
				},
				zlevel : 1
			} ]
		};
		myChart.setOption(option);
		myChart.on('click', function (params) {
		    console.log(params);
		});
	};
	this.onResize = function() {
		myChart.resize();
	};
	this.onSetData = function(data) {// 设置数据
		myChart.setOption({
			series : [ {
				data : data.zztsg,
			}, {
				data : data.cssf,
			}, {
				data : data.zxg,
			} ]
		});
	};
};
// 借阅概览
Bswidget.prototype.JYGL = function(widgetBean) {
	Bswidget.call(this, widgetBean);
	var self = this;
	var myChart = null;
	this.onShow = function() {
		myChart = echarts.init(self.container.get(0));
		var option = {
			grid : {
				left : '20%',
				right : '20%',
				bottom : '17%'
			},
			legend : {
				data : [ "1量", "2量", "3量" ],
				textStyle : {
					fontSize : "8px",
					color : "#fff"
				},
				x : 'center',
			},
			xAxis : [ {
				type : 'category',
				data : [],
				axisLine : {
					lineStyle : {
						type : 'solid',
						color : '#fff',
					},
				},
				axisPointer : {
					type : 'shadow'
				}
			} ],
			yAxis : [ {
				type : 'value',
				name : '册',
				axisLine : {
					lineStyle : {
						type : 'solid',
						color : '#fff',
					},
				},
				axisLabel : {
					formatter : '{value} ',
				// formatter: function (value) {
				// return (value / 10000) ;
				// }
				}
			}, {
				type : 'value',
				name : '人',
				axisLine : {
					lineStyle : {
						type : 'solid',
						color : '#fff',
					},
				},
				axisLabel : {
					show : true,
					formatter : '{value} ',
				// formatter: function (value) {
				// return (value / 10000) ;
				// }
				}
			} ],
			series : []
		}
		myChart.setOption(option);
	};
	this.onResize = function() {
		myChart.resize();
	};
	this.onSetData = function(data) {// 设置数据
		myChart.setOption({
			xAxis : [ {
				data : data.month
			} ],
			series : [
					{
						itemStyle : {
							normal : {
								barBorderRadius : 5,
								color : new echarts.graphic.LinearGradient(0,
										0, 0, 1, [ {
											offset : 0,
											color : '#f7734e'
										}, {
											offset : 1,
											color : '#e12945'
										} ]),
							},
						},
						name : '1量',
						type : 'bar',
						data : data.jsl
					},
					{
						itemStyle : {
							normal : {
								barBorderRadius : 5,
								color : new echarts.graphic.LinearGradient(0,
										0, 0, 1, [ {
											offset : 0,
											color : '#96d668'
										}, {
											offset : 1,
											color : '#01babc'
										} ]),
							},
						},
						name : '2量',
						type : 'bar',
						data : data.hsl
					}, {
						itemStyle : {
							normal : {
								color : '#9400D3'
							},
						},
						name : '3量',
						type : 'line',
						yAxisIndex : 1,
						data : data.rll
					} ]
		});
	};
};
// 最热图书
Bswidget.prototype.ZRTS = function(widgetBean) {
	Bswidget.call(this, widgetBean);
	var self = this;
	var myChart = null;
	this.onShow = function() {
		myChart = echarts.init(self.container.get(0));
		var option = {
			color : [ '#61A8FF' ], // 进度条颜色
			grid : {
				bottom : '0',
				left : "5%",
				right : "5%",
				top : '1%',
				containLabel : true
			},
			xAxis : [ {
				show : false
			},
			// 由于下边X轴已经是百分比刻度了,所以需要在顶部加一个X轴,刻度是金额,也隐藏掉
			{
				show : false
			} ],
			yAxis : {
				type : 'category',
				axisLabel : {
					show : false
				// 让Y轴数据不显示
				},
				itemStyle : {},
				axisTick : {
					show : false
				// 隐藏Y轴刻度
				},
				axisLine : {
					show : false
				// 隐藏Y轴线段
				},
				data : []
			},
			series : [
					// 背景色--------------------我是分割线君------------------------------//
					{
						show : true,
						type : 'bar',
						barGap : '-100%',
						barWidth : '25%', // 统计条宽度
						itemStyle : {
							normal : {
								barBorderRadius : 15,
								color : 'rgba(102, 102, 102,0.5)'
							}
						},
						z : 1,
						data : []
					},
					// 蓝条--------------------我是分割线君------------------------------//
					{
						show : true,
						type : 'bar',
						barGap : '-100%',
						barWidth : '25%', // 统计条宽度
						itemStyle : {
							normal : {
								barBorderRadius : 20, // 统计条弧度
								color : new echarts.graphic.LinearGradient(0,
										0, 0, 1, [ {
											offset : 0,
											color : '#61A8FF'
										}, {
											offset : 1,
											color : '#01babc'
										} ])
							}
						},
						max : 1,
						label : {
							normal : {
								show : true
							}
						},
						labelLine : {
							show : false
						},
						z : 2,
						data : []
					},
					// 数据条--------------------我是分割线君------------------------------//
					{
						show : true,
						type : 'bar',
						xAxisIndex : 1, // 代表使用第二个X轴刻度!!!!!!!!!!!!!!!!!!!!!!!!
						barGap : '-100%',
						barWidth : '25%', // 统计条宽度
						itemStyle : {
							normal : {
								barBorderRadius : 20,
								color : 'rgba(22,203,115,0.05)'
							}
						},
						label : {
							normal : {
								show : true,
								// label 的position位置可以是top bottom
								// left,right,也可以是固定值
								// 在这里需要上下统一对齐,所以用固定值
								position : [ 0, '-100%' ]
							}
						},
						data : []
					}

			]
		};
		myChart.setOption(option);
	};
	this.onResize = function() {
		myChart.resize();
	};
	this.onSetData = function(data) {// 设置数据
		var bookname = data.bookname;
		var greybar = data.greybar;
		var baifenbi = data.baifenbi;
		var paiming = data.paiming;
		myChart.setOption({
			yAxis : [ {
				data : bookname
			} ],
			series : [
					{
						data : greybar
					},
					{
						label : {
							normal : {
								show : true,
								// 百分比格式
								formatter : function(data) {
									return baifenbi[data.dataIndex] + '本';
								},
							}
						},
						data : baifenbi
					},
					{
						label : {
							normal : {
								show : true,
								// 百分比格式
								rich : {// 富文本
									white : {// 自定义颜色
										color : '#fff',
									}
								},
								formatter : function(data) {
									return '{white|NO.'
											+ paiming[data.dataIndex] + '  '
											+ bookname[data.dataIndex] + '}';
								},
							}
						},
						data : paiming
					}, ]
		});
	};
};
// 书房数据
Bswidget.prototype.SFSJ = function(widgetBean) {
	Bswidget.call(this, widgetBean);
	var self = this;
	var myChart = null;
	this.onShow = function() {
		myChart = echarts.init(self.container.get(0));
		var option = option = {
			legend : {
				data : [ '在线', '离线' ],
				textStyle : {
					fontSize : "8px",
					color : "#fff"
				},
				x : 'center',
			},
			grid : {
				left : '3%',
				right : '4%',
				top : '17%',
				bottom : '3%',
				containLabel : true
			},
			yAxis : {
				type : 'value',
				name : '册',
				boundaryGap : [ 0, 0.01 ],
				axisLine : {
					lineStyle : {
						type : 'solid',
						color : '#fff',
					},
				},
				axisLabel : {
					formatter : '{value}',
				}
			},
			xAxis : {
				type : 'category',
				axisLine : {
					lineStyle : {
						type : 'solid',
						color : '#fff',
					},
				},
				axisLabel : {
					interval : 0,
					formatter : function(value) {
						var ret = "";
						var maxLength = 3;
						var valLength = value.length;
						var rowN = Math.ceil(valLength / maxLength);
						if (rowN > 1) {
							for (var i = 0; i < rowN; i++) {
								var temp = "";
								var start = i * maxLength;
								var end = start + maxLength;
								temp = value.substring(start, end) + "\n";
								ret += temp;
							}
							return ret;
						} else {
							return value;
						}
					}
				},
				data : []
			},
			series : [ {
				name : '借书量',
				type : 'bar',
				data : []
			}, {
				name : '还书量',
				type : 'bar',
				data : []
			} ]
		};
		myChart.setOption(option);
	}
	this.onResize = function() {
		myChart.resize();
	};
	this.onSetData = function(data) {// 设置数据

		myChart.setOption({
			xAxis : [ {
				data : data.sf
			} ],
			series : [
					{
						itemStyle : {
							normal : {
								barBorderRadius : 5,
								color : new echarts.graphic.LinearGradient(0,
										0, 0, 1, [ {
											offset : 0,
											color : '#00FFE6'
										}, {
											offset : 1,
											color : '#007CC6'
										} ]),
							},
						},
						data : data.jsl
					},
					{
						itemStyle : {
							normal : {
								barBorderRadius : 5,
								color : new echarts.graphic.LinearGradient(0,
										0, 0, 1, [ {
											offset : 0,
											color : '#3023AE'
										}, {
											offset : 1,
											color : '#C96DD8'
										} ]),
							},
						},
						data : data.hsl
					} ]
		});
	};
};

// 书房数据2
Bswidget.prototype.SFSJ2 = function(widgetBean) {
	Bswidget.call(this, widgetBean);
	var self = this;
	var myChart = null;
	this.onShow = function() {
		myChart = echarts.init(self.container.get(0));
		var option = option = {
			legend : {
				data : [ '借书量', '还书量' ],
				textStyle : {
					fontSize : "8px",
					color : "#fff"
				},
				x : 'center',
			},
			grid : {
				left : '3%',
				right : '4%',
				top : '17%',
				bottom : '3%',
				containLabel : true
			},
			yAxis : {
				type : 'value',
				name : '册',
				boundaryGap : [ 0, 0.01 ],
				axisLine : {
					lineStyle : {
						type : 'solid',
						color : '#fff',
					},
				},
				axisLabel : {
					formatter : '{value}',
				}
			},
			xAxis : {
				type : 'category',
				axisLine : {
					lineStyle : {
						type : 'solid',
						color : '#fff',
					},
				},
				axisLabel : {
					interval : 0,
					formatter : function(value) {
						var ret = "";
						var maxLength = 3;
						var valLength = value.length;
						var rowN = Math.ceil(valLength / maxLength);
						if (rowN > 1) {
							for (var i = 0; i < rowN; i++) {
								var temp = "";
								var start = i * maxLength;
								var end = start + maxLength;
								temp = value.substring(start, end) + "\n";
								ret += temp;
							}
							return ret;
						} else {
							return value;
						}
					}
				},
				data : []
			},
			series : [ {
				name : '借书量',
				type : 'bar',
				data : []
			}, {
				name : '还书量',
				type : 'bar',
				data : []
			} ]
		};
		myChart.setOption(option);
	}
	this.onResize = function() {
		myChart.resize();
	};
	this.onSetData = function(data) {// 设置数据

		myChart.setOption({
			xAxis : [ {
				data : data.sf
			} ],
			series : [
					{
						itemStyle : {
							normal : {
								barBorderRadius : 5,
								color : new echarts.graphic.LinearGradient(0,
										0, 0, 1, [ {
											offset : 0,
											color : '#00FFE6'
										}, {
											offset : 1,
											color : '#007CC6'
										} ]),
							},
						},
						data : data.jsl
					},
					{
						itemStyle : {
							normal : {
								barBorderRadius : 5,
								color : new echarts.graphic.LinearGradient(0,
										0, 0, 1, [ {
											offset : 0,
											color : '#3023AE'
										}, {
											offset : 1,
											color : '#C96DD8'
										} ]),
							},
						},
						data : data.hsl
					} ]
		});
	};
};
// 馆藏图书分析
Bswidget.prototype.GCTSFX = function(widgetBean) {
	Bswidget.call(this, widgetBean);
	var self = this;
	var myChart = null;
	this.onShow = function() {
		myChart = echarts.init(self.container.get(0));
		var option = {
			color : [ '#1E90FF', '#87CEFA', '#00FFFF', '#FFA500', '#EEC900',
					'#E9967A', '#FF00FF', '#7D26CD' ],
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b}: {c} ({d}%)"
			},
			grid : {
				left : '50',
				right : '50',
				bottom : '20',
				top : "20",
				containLabel : true
			},
			series : [ {
				name : '藏书量',
				type : 'pie',
				roseType : 'area',
				radius : [ '20%', '50%' ],
				label : {
					normal : {
						formatter : ' {b|{b}：}{per|{d}%}  ',
						borderColor : '#00FFFF',
						borderWidth : 1,
						borderRadius : 4,
						shadowBlur : 3,
						shadowOffsetX : 2,
						shadowOffsetY : 2,
						shadowColor : '#00FFFF',
						padding : [ 0, 2 ],
						rich : {
							hr : {
								borderColor : '#00FFFF',
								width : '100%',
								borderWidth : 0.5,
								height : 0
							},
							b : {
								fontSize : 8,
								lineHeight : 26
							},
							per : {
								color : '#eee',
								backgroundColor : '#334455',
								padding : [ 2, 4 ],
								borderRadius : 2
							}
						}
					}
				},
				itemStyle : {
					normal : {
						shadowBlur : 10
					}
				},
				data : []
			} ]
		};
		myChart.setOption(option);
	}
	this.onResize = function() {
		myChart.resize();
	};
	this.onSetData = function(data) {// 设置数据

		myChart.setOption({
			series : [ {
				data : data.sl
			}, ]
		});
	};
};
// 读者借阅分析
Bswidget.prototype.DZJYFX = function(widgetBean) {
	Bswidget.call(this, widgetBean);
	var self = this;
	var myChart = null;
	this.onShow = function() {
		myChart = echarts.init(self.container.get(0));
		var option = {
			color : [ '#3398DB' ],
			legend : {
				data : [ "借阅数" ],
				textStyle : {
					fontSize : "8px",
					color : "#fff"
				},
				x : 'center',
			},
			tooltip : {
				trigger : 'axis',
				axisPointer : {// 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			grid : {
				left : '3%',
				right : '4%',
				top : '15%',
				bottom : '8%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				data : [],
				axisTick : {
					alignWithLabel : true
				},
				axisLine : {
					lineStyle : {
						type : 'solid',
						color : '#fff',
					}
				},
				axisLabel : {
					interval : 0,
					formatter : function(value) {
						var ret = "";
						var maxLength = 1;
						var valLength = value.length;
						var rowN = Math.ceil(valLength / maxLength);
						if (rowN > 1) {
							for (var i = 0; i < rowN; i++) {
								var temp = "";
								var start = i * maxLength;
								var end = start + maxLength;
								temp = value.substring(start, end) + "\n";
								ret += temp;
							}
							return ret;
						} else {
							return value;
						}
					}
				}
			} ],
			yAxis : [ {
				type : 'value',
				name : '册',
				axisLine : {
					lineStyle : {
						type : 'solid',
						color : '#fff',
					},
				},
				axisLabel : {
					formatter : '{value}',
				}
			} ],
			series : [ {
				name : '借阅数',
				type : 'bar',
				barWidth : '60%',
				itemStyle : {
					normal : {
						barBorderRadius : 5,
						color : new echarts.graphic.LinearGradient(0, 0, 0, 1,
								[ {
									offset : 0,
									color : '#1a98f8'
								}, {
									offset : 1,
									color : '#7049f0'
								} ]),
					},
				},
				data : []
			} ]
		};
		myChart.setOption(option);
	}
	this.onResize = function() {
		myChart.resize();
	};
	this.onSetData = function(data) {// 设置数据

		myChart.setOption({
			xAxis : [ {
				data : data.lb
			} ],
			series : [ {
				data : data.sl
			} ]
		});
	};
};

// 读者年龄分析
Bswidget.prototype.DZXBFX = function(widgetBean) {
	Bswidget.call(this, widgetBean);
	var self = this;
	var myChart = null;
	var selectNum = 0;
	this.onShow = function() {
		myChart = echarts.init(self.container.get(0));
		var option = {
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b}: {c} ({d}%)"
			},
			series : [ {
				name : '',
				type : 'pie',
				radius : [ '50%', '70%' ],
				avoidLabelOverlap : false,
				label : {
					normal : {
						show : false,
						position : 'center'
					},
					emphasis : {
						show : true,
						textStyle : {
							fontSize : 18,
							fontWeight : 'bold',
						},
					},
				},
				data : []
			} ]
		};
		myChart.setOption(option);
	};
	this.onResize = function() {
		myChart.resize();
	};
	this.onSetData = function(data) {// 设置数据
		myChart.setOption({
			color : [ '#b6a2de', '#5ab1ef', '#ffb980', '#d87a80', '#2ec7c9' ],
			series : [ {
				name : data.name,
				data : data.data
			} ]
		});
		window
				.setInterval(
						function() {
							for (var i = 0; i < myChart.getOption().series[0].data.length; i++) {
								if (i == selectNum) {
									myChart.dispatchAction({
										type : 'highlight',
										dataIndex : i
									});
								} else {
									myChart.dispatchAction({
										type : 'downplay',
										dataIndex : i
									});
								}
							}
							selectNum++;
							if (selectNum === myChart.getOption().series[0].data.length) {
								selectNum = 0;
							}
						}, 2000);
	};
};

// 读者性别比例
Bswidget.prototype.DZXBBL = function(widgetBean) {
	Bswidget.call(this, widgetBean);
	var self = this;
	var myChart = null;
	this.onShow = function() {
		myChart = echarts.init(self.container.get(0));
		var option = {
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b}: {c} ({d}%)"
			},
			series : [ {
				type : 'pie',
				name : [],
				radius : [ 0, '70%' ],
				label : {
					normal : {
						// show: false,
						position : 'inside',
						fontSize : 12,
						formatter : '{b}\n\
{d}%'
					}
				},
				color : [ '#67E0E3', 'transparent' ],
				itemStyle : {
					borderColor : 'rgba(250, 250, 250,1)'
				},
				data : []
			} ]
		};
		myChart.setOption(option);
	};
	this.onResize = function() {
		myChart.resize();
	};
	this.onSetData = function(data) {// 设置数据
		myChart.setOption({
			series : [ {
				name : data.name,
				data : data.data
			} ]
		});
	};
};

// 读者分析
Bswidget.prototype.DZFX = function(widgetBean) {
	Bswidget.call(this, widgetBean);
	var self = this;
	var myChart1 = null;
	var myChart2 = null;
	var selectNum = 0;
	this.onShow = function() {
		// var height = (self.container.get(0).offsetHeight - 50) / 3; //42按钮高度
		// 16表格实际增加高度
		var fontSize = getSize(self.container.get(0), 10);
		var content1 = $("<div class=\"l_widget_content_show\"></div>");
		content1.get(0).style.height = "100%";
		content1.get(0).style.display = "flex";
		$(self.container).append(content1);
		var ddd = $("<div class=\"l_widget_content_show\"></div>");
		ddd.get(0).style.width = "50%";
		$(content1).append(ddd);
		myChart1 = echarts.init(ddd.get(0));
		var option = {
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b}: {c} ({d}%)"
			},
			series : [ {
				type : 'pie',
				name : [],
				radius : [ 0, '70%' ],
				label : {
					normal : {
						// show: false,
						position : 'inside',
						fontSize : 12,
						formatter : '{b}\n\
{d}%'
					}
				},
				color : [ '#67E0E3', 'transparent' ],
				itemStyle : {
					borderColor : 'rgba(250, 250, 250,1)'
				},
				data : []
			} ]
		};
		myChart1.setOption(option);

		var ddd2 = $("<div class=\"l_widget_content_show\"></div>");
		ddd2.get(0).style.width = "50%";
		$(content1).append(ddd2);
		myChart2 = echarts.init(ddd2.get(0));
		var option2 = {
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b}: {c} ({d}%)"
			},
			series : [ {
				name : '',
				type : 'pie',
				radius : [ '50%', '70%' ],
				avoidLabelOverlap : false,
				label : {
					normal : {
						show : false,
						position : 'center',
					},
					emphasis : {
						show : true,
						textStyle : {
							fontSize : 18,
							fontWeight : 'bold',
						},
					},
				},
				data : []
			} ]
		};
		myChart2.setOption(option2);

	};
	this.onResize = function() {
		myChart1.resize();
		myChart2.resize();
	};
	this.onSetData = function(data) {// 设置数据
		myChart1.setOption({
			series : [ {
				name : data.name0,
				data : data.data0
			} ]
		});
		myChart2.setOption({
			color : [ '#b6a2de', '#5ab1ef', '#ffb980', '#d87a80', '#2ec7c9' ],
			series : [ {
				name : data.name1,
				data : data.data1
			} ]
		});
		window
				.setInterval(
						function() {
							for (var i = 0; i < myChart2.getOption().series[0].data.length; i++) {
								if (i == selectNum) {
									myChart2.dispatchAction({
										type : 'highlight',
										dataIndex : i
									});
								} else {
									myChart2.dispatchAction({
										type : 'downplay',
										dataIndex : i
									});
								}
							}
							selectNum++;
							if (selectNum === myChart2.getOption().series[0].data.length) {
								selectNum = 0;
							}
						}, 5000);
	};
};

// 标题
Bswidget.prototype.Title = function(widgetBean) {
	Bswidget.call(this, widgetBean);
	if (widgetBean.text.text == "") {
		widgetBean.text.text = "文本框";
	}
	widgetBean.backgroud.show = 2;
	Bswidget.call(this, widgetBean);
	this.onSetData = function(data) {// 设置数据
		if (data != null) {
			this.container.html(data);
		}
	};
};

// 标题
Bswidget.prototype.Number = function(widgetBean) {
	Bswidget.call(this, widgetBean);
	if (widgetBean.text.text == "") {
		widgetBean.text.text = "文本框";
	}
	widgetBean.backgroud.show = 3;
	Bswidget.call(this, widgetBean);
	this.onSetData = function(data) {// 设置数据
		if (data != null) {
			this.container.html(data);
		}
	};
};
