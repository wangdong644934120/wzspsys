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
				center : [ 122.088827, 37.509083 ],
				zoom : 13,
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
				name : '院落',
				type : 'effectScatter',
				coordinateSystem : 'bmap',
				data : [],
				symbolSize : 15,
				showEffectOn : 'render',
				rippleEffect : {
					brushType : 'stroke'
				},
				hoverAnimation : true,
				label : {
					normal : {
						formatter : '{b}',
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
				zlevel : 10
			} ]
		};
		myChart.setOption(option);
		myChart.on('click', function (params) {
		    console.log(params);
			onMapPointClick(params);
		});
	};
	this.onResize = function() {
		myChart.resize();
	};
	this.onSetData = function(data) {// 设置数据
		myChart.setOption({
			series : [{
				data : data.zztsg,
			}, {
				data : data.cssf,
			}, {
				data : data.zxg,
			}]
		});
	};
};

Bswidget.prototype.AllWSD = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    this.onShow = function () {
        myChart = echarts.init(self.container.get(0));
        var option = {
            tooltip: {
                trigger: 'item'
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    data: ['0点', '1点', '2点', '3点', '4点', '5点', '6点', '7点', '8点', '9点', '10点', '11点'],
                    axisLine: {
                        lineStyle: {
                            color: '#FFF'
                        }
                    }
                }
            ],
            yAxis: [
                {
                    splitLine: {show: false}, //去除网格线
                    type: 'value',
                    name: '温度',
                    axisLabel: {
                        formatter: '{value} °C'
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#FFF'
                        }
                    }
                },
                {
                    type: 'value',
                    name: '湿度',
                    splitLine: {
                        show: false
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#FFF'
                        }
                    },
                    axisLabel: {
                        formatter: '{value} %',
                        lineStyle: {
                            color: '#FFF'
                        }
                    }
                }
            ],
            series: [
            ]
        };
        myChart.setOption(option);
    };
    this.onResize = function () {
        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
        myChart.setOption({
//            legend:{data:data.legend},
            title: {//标题
                text: data.mc,
                textStyle: {
                    color: "#F8C15C"
                }
            },
            xAxis: [
                {
                    data: data.time
                }
            ],
            yAxis: [
                {
                    max: data.wdMax + 1,
                    min: data.wdMin - 1
                },
                {
                    max: data.sdMax + 1,
                    min: data.sdMin - 1
                }
            ],
            series:
                    data.series
        });
    };
};

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
//书房数据
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

//馆藏图书分析
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
				name : '房屋面积',
				type : 'pie',
				roseType : 'area',
				radius : [ '20%', '50%' ],
				label : {
					normal : {
						formatter : ' {b|{b}：}{per|{c}㎡}  ',
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
								fontSize : 14,
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


Bswidget.prototype.LunBoLabel = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var childDiv = null;
    var lunxunTimer = null;
    var waitTime = 3000;
    this.onShow = function () {
        self.container.get(0).style.overflow = "hidden";
        self.container.get(0).style.paddingTop = 0;
        self.container.get(0).style.marginTop = "15px";
        childDiv = $("<div class=\"l_widget_lunxun\"></div>");
        $(self.container).append(childDiv);
    };
    this.onResize = function () {
        if (self.container.get(0).offsetHeight < childDiv.get(0).offsetHeight) {
            //触摸清空定时器
            childDiv.hover(function () {
                clearInterval(lunxunTimer);
            }, function () {//离开启动定时器
                lunxunTimer = setInterval(function () {
                    scrollList(childDiv);
                }, waitTime);
            }).trigger("mouseleave"); //自动触发触摸事件
        } else {
            clearInterval(lunxunTimer);
        }
    };
    this.onSetData = function (data) {//设置数据
        childDiv.empty();
        if (data != null && data.data != null && data.data.length > 0) {
            for (var i = 0; i < data.data.length; i++) {
                var ul_div = $("<div id=\"" + data.data[i].id + "\" class=\"baseItem\" title=\"" + data.data[i].title + "\"></div>");
                childDiv.append(ul_div);
                var des = "<div style='width:200px;height:30px;'>" + data.data[i].des + "</div>";
                if (data.data[i].remark != null && data.data[i].remark != "") {
                    des = "<div style='width:200px;height:30px;'>" + data.data[i].des + "<span class=\"weui-badge\" style=\"margin-left: 5px;\">" + data.data[i].remark + "</span>" + "</div>";
                }
                ul_div.append(des + "<div style='width:200px;height:30px;'>" + data.data[i].sourse + "</div><div style='width:200px;'>" + data.data[i].time + "</div>");
            }
            if (data.waitTime != null && data.waitTime > 0) {
                waitTime = data.waitTime;
            }
        } else {
            var ul_div = $("<div class=\"baseItem\"></div>");
            childDiv.append(ul_div);
            ul_div.append("<span>暂无数据</span>");
        }
        if (self.container.get(0).offsetHeight < childDiv.get(0).offsetHeight) {
            //触摸清空定时器
            childDiv.hover(function () {
                clearInterval(lunxunTimer);
            }, function () {//离开启动定时器
                lunxunTimer = setInterval(function () {
                    scrollList(childDiv);
                }, waitTime);
            }).trigger("mouseleave"); //自动触发触摸事件
        }
    };
};

//标题
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
	//widgetBean.backgroud.show = 3;
	Bswidget.call(this, widgetBean);
	this.onSetData = function(data) {// 设置数据
		if (data != null) {
			this.container.html(data);
		}
	};
};

Bswidget.prototype.McPie = function(widgetBean) {
	Bswidget.call(this, widgetBean);
	var self = this;
	var myChart = null;
	this.onShow = function() {
		myChart = echarts.init(self.container.get(0));
		var dataStyle = {
			normal: {
				label: {show:false},
				labelLine: {show:false}
			}
		};
		var placeHolderStyle = {
			normal : {
				color: 'rgba(0,0,0,0)',
				label: {show:false},
				labelLine: {show:false}
			},
			emphasis : {
				color: 'rgba(0,0,0,0)'
			}
		};
		var option = {
			title: {
				text: '房屋用途',
				// subtext: 'From ExcelHome',
				// sublink: 'http://e.weibo.com/1341556070/AhQXtjbqh',
				x: 'center',
				y: 'center',
				itemGap: 20,
				textStyle : {
					color : 'rgba(30,144,255,0.8)',
					fontFamily : '微软雅黑',
					fontSize : 20,
					fontWeight : 'bolder'
				}
			},
			tooltip : {
				show: true,
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			legend: {
				orient : 'vertical',
				x : self.container.get(0).offsetWidth / 1.5,
				y : 30,
				itemGap:12,
				data:['68%的人表示过的不错','29%的人表示生活压力很大','3%的人表示“我姓曾”']
			},
			// toolbox: {
			// 	show : true,
			// 	feature : {
			// 		mark : {show: true},
			// 		dataView : {show: true, readOnly: false},
			// 		restore : {show: true},
			// 		saveAsImage : {show: true}
			// 	}
			// },
			series : [
				{
					name:'1',
					type:'pie',
					clockWise:false,
					radius : [80, 100],
					itemStyle : dataStyle,
					data:[
						{
							value:68,
							name:'68%的人表示过的不错'
						},
						{
							value:32,
							name:'invisible',
							itemStyle : placeHolderStyle
						}
					]
				},
				{
					name:'2',
					type:'pie',
					clockWise:false,
					radius : [60, 80],
					itemStyle : dataStyle,
					data:[
						{
							value:29,
							name:'29%的人表示生活压力很大'
						},
						{
							value:71,
							name:'invisible',
							itemStyle : placeHolderStyle
						}
					]
				},
				{
					name:'3',
					type:'pie',
					clockWise:false,
					radius : [45, 60],
					itemStyle : dataStyle,
					data:[
						{
							value:3,
							name:'3%的人表示“我姓曾”'
						},
						{
							value:97,
							name:'invisible',
							itemStyle : placeHolderStyle
						}
					]
				}
			]
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
		// myChart.setOption({
		// 	series : [ {
		// 		data : data.zztsg,
		// 	}, {
		// 		data : data.cssf,
		// 	}, {
		// 		data : data.zxg,
		// 	} ]
		// });
	};
};


