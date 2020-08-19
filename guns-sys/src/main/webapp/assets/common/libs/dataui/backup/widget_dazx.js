
//状态表
Bswidget.prototype.StateList = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var childDiv = null;
    this.onShow = function () {
        var height = (self.container.get(0).offsetHeight - 50) / 3; //42按钮高度  16表格实际增加高度
        var fontSize = height / 2;
        if (fontSize < 5) {
            fontSize = 5;
        }
        childDiv = $("<div class=\"l_widget_state\"></div>");
        $(self.container).append(childDiv);
    };
    this.onResize = function () {
        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
        if (data != null) {
            childDiv.empty();
            for (var i = 0; i < data.length; i++) {
                var state = "<div  class=\"l_widget_statelist\">" +
                        "<embed src=\"lib/bigscreen/images/circle_" + data[i].type + ".svg\"  type=\"image/svg+xml\"/>" +
                        "<span style=\"margin-left: 20px\">" + data[i].value + "</span>" +
                        "</div>";
                childDiv.append(state);
            }
        }
    };
};

//轮巡的列表
Bswidget.prototype.LunxunList = function (widgetBean) {
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
                var ul_div = $("<div id=\"" + data.data[i].id + "\" class=\"baseItem\" whichMap=\"" + data.data[i].whichMap + "\" title='点击查看报警详情'></div>");
                childDiv.append(ul_div);
                var des = "<div>" + data.data[i].des + "</div>";
                if (data.data[i].remark != null && data.data[i].remark != "") {
                    des = "<div>" + data.data[i].des + "<span class=\"weui-badge\" style=\"margin-left: 5px;\">" + data.data[i].remark + "</span>" + "</div>";
                }
                ul_div.append(des + "<div>" + data.data[i].deviceName + "</div><div >" + data.data[i].time + "</div>");
                $(ul_div).bind("click", function (event) {
                    var item = $(event.target).closest(".baseItem");
                    var whichMap = item.attr("whichMap");
                    $("#ecmapDiv").data("ecmap").showEcmap(whichMap, false);
                    if (window.frames["ecmapIframe"] != null && window.frames["ecmapIframe"].ecmap._systemsDictionary != null) {
                        window.frames["ecmapIframe"].ecmap.system = new Array();
                        for (var key in window.frames["ecmapIframe"].ecmap._systemsDictionary) {
                            window.frames["ecmapIframe"].ecmap.system.push(key);
                        }
                    }
                    $("#" + item[0].id + " span").remove();
                });
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

//滚动动画
function scrollList(obj) {
    //获得当前<li>的高度
    var scrollHeight = $(obj).children(".baseItem").height();
    //滚动出一个<li>的高度
    $(obj).stop().animate({marginTop: -scrollHeight}, 600, function () {
        //动画结束后，将当前<ul>marginTop置为初始值0状态，再将第一个<li>拼接到末尾。
        $(this).css({marginTop: 0}).find(".baseItem:first").appendTo(this);
    });
}

function getJqEcmap() {
    return $("#ecmapDiv").data("ecmap");
}

//饼图，中间有图标
Bswidget.prototype.DevicePie = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    this.onShow = function () {
        myChart = echarts.init(self.container.get(0));
        var option = getDevicePieOption(getSize(self.container.get(0), 5));
        myChart.setOption(option);
    };
    this.onResize = function () {
        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
        myChart.setOption({
            series: [{
                    label: {
                        normal: {
                            rich: {
                                Showers: {
                                    backgroundColor: {
                                        image: data.image
                                    }
                                }
                            }
                        }
                    }
                }, {
                    data: data.data
                }
            ]
        });
    };
};


//透明饼图
Bswidget.prototype.TransPie = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    this.onShow = function () {
        myChart = echarts.init(self.container.get(0));
        var option = getTransPieOption(getSize(self.container.get(0), 3));
        myChart.setOption(option);
    };
    this.onResize = function () {
        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
        myChart.setOption({
            series: [{
                    name: data.name,
                    data: data.data
                }
            ]
        });
    };
};

//双饼图
Bswidget.prototype.DoublePie = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    this.onShow = function () {
        var height = (self.container.get(0).offsetHeight - 50) / 3; //42按钮高度  16表格实际增加高度
        var fontSize = height / 5;
        if (fontSize < 5) {
            fontSize = 5;
        }
        myChart = echarts.init(self.container.get(0));
        var option = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            series: [
                {
                    name: '在线状态',
                    type: 'pie',
                    radius: ["0%", '40%'],
                    tooltip: {
//                        show: false
                    },
                    label: {
                        normal: {
                            show: true,
                            position: 'inner',
                            textStyle: {
                                fontSize: fontSize,
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    color: ["#1B9600", "#FB6C6C"],
                    data: []
                },
                {
                    name: '在线状态',
                    type: 'pie',
                    radius: ['55%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            formatter: '{b}:{c}',
                            textStyle: {
                                fontSize: fontSize,
                                color: 'rgba(250, 250, 250,1)'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false,
                            length: 3,
                            length2: 3
                        }
                    },
                    data: []
                }
            ]
        };
        myChart.setOption(option);
    };
    this.onResize = function () {
        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
        myChart.setOption({
            series: [{
                    name: data.name0,
                    data: data.pie0
                }, {
                    name: data.name1,
                    data: data.pie1
                }
            ]
        });
    };
};

//轮巡饼图
Bswidget.prototype.PollingPie = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    var selectNum = 0;
    this.onShow = function () {
        myChart = echarts.init(self.container.get(0));
        var option = getPollingPieOption(getSize(self.container.get(0), 2));
        myChart.setOption(option);
    };
    this.onResize = function () {
        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
        myChart.setOption({
            series: [{
                    name: data.name,
                    data: data.data
                }
            ]
        });
        window.setInterval(function () {
            for (var i = 0; i < myChart.getOption().series[0].data.length; i++) {
                if (i == selectNum) {
                    myChart.dispatchAction({
                        type: 'highlight',
                        dataIndex: i
                    });
                } else {
                    myChart.dispatchAction({
                        type: 'downplay',
                        dataIndex: i
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

//数字
Bswidget.prototype.CountUp = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    this.onShow = function () {
        var width = self.container.get(0).offsetWidth; //42按钮高度  16表格实际增加高度
        var fontSize = width / 110;
        self.container.get(0).style.lineHeight = self.container.get(0).offsetHeight + "px";
        self.container.get(0).style.overflow = "hidden";
        self.container.get(0).style.fontSize = fontSize + "em";
        self.container.get(0).style.textAlign = "center";
    };
    this.onResize = function () {
        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
        var demo = new CountUp(self.container.get(0), data.startVal, data.endVal, data.decimals, data.duration);
        demo.start();
    };
};

//人流量
Bswidget.prototype.PersonFlow = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    this.onShow = function () {
        var height = (self.container.get(0).offsetHeight - 50) / 3; //42按钮高度  16表格实际增加高度
        var fontSize = height / 2;
        if (fontSize < 5) {
            fontSize = 5;
        }
        myChart = echarts.init(self.container.get(0));

        var option = {
            tooltip: {
                trigger: 'axis'
            },
            grid: {
                left: '2%',
                right: '2%',
                top: '10%',
                bottom: '1%',
                containLabel: true
            },
            xAxis: [{
//                    axisLabel: {
//                        interval: 1
//                    },
                    axisLine: {
                        lineStyle: {
                            color: '#FFF'
                        }
                    },
                    data: []
                }],
            yAxis: [{
                    name: '', //人流量(人次)',
                    axisLine: {
                        lineStyle: {
                            color: '#FFF'
                        }
                    },
                    splitLine: {show: false}
                }],
            series: [{
//                radius: ['95%'],
                    symbol: 'none',
                    type: 'line',
                    showSymbol: false,
                    data: [],
                    areaStyle: {
                        normal: {
                            color: {
                                type: 'linear',
                                x: 0,
                                y: 0,
                                x2: 0,
                                y2: 1,
                                colorStops: [{
                                        offset: 0, color: '#4ED5DB' // 0% 处的颜色
                                    }, {
                                        offset: 1, color: '#99FFFF' // 100% 处的颜色
                                    }],
                                globalCoord: false // 缺省为 false
                            }
                        }
                    },
                    color: ["#4ED5DB"]
                }]
        };
        myChart.setOption(option);
    };
    this.onResize = function () {
        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
        myChart.setOption({
            xAxis: [{
                    data: data.dateList
                }],
            series: [{
                    data: data.valueList
                }
            ]
        });
    };
};

//水波图
Bswidget.prototype.Liquidfill = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    this.onShow = function () {
        myChart = echarts.init(self.container.get(0));
        var option = getLiquidfillOption(getSize(self.container.get(0), 2));
        myChart.setOption(option);
    };
    this.onResize = function () {
//        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
        myChart.setOption({//水波纹设置数据需要先置空，否则会出错
            series: [{
                    "data": []
                }
            ]
        });
        myChart.setOption({
            series: [{
                    data: data
                }
            ]
        });
    };
};

//报警类型雷达图
Bswidget.prototype.AlarmType = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    this.onShow = function () {
        var height = (self.container.get(0).offsetHeight - 50) / 3; //42按钮高度  16表格实际增加高度
        var fontSize = height / 2;
        if (fontSize < 5) {
            fontSize = 5;
        }
        myChart = echarts.init(self.container.get(0));
        var option = {
            tooltip: {},
            backgroundColor: "transparent",
            radar: [
                {
                    indicator: [{}
                    ],
                    center: ['50%', '50%'],
                    startAngle: 90,
                    splitNumber: 4,
                    shape: 'circle',
                    name: {
                        formatter: '【{value}】',
                        textStyle: {
                            color: '#72ACD1'
                        }
                    },
                    splitArea: {
                        areaStyle: {
                            color: ['rgba(114, 172, 209, 0.2)',
                                'rgba(114, 172, 209, 0.4)', 'rgba(114, 172, 209, 0.6)',
                                'rgba(114, 172, 209, 0.8)', 'rgba(114, 172, 209, 1)'],
                            shadowColor: 'rgba(0, 0, 0, 0.3)',
                            shadowBlur: 10
//                            shadowColor: "transparent"
                        }
                    },
                    axisLine: {
                        lineStyle: {
                            color: 'rgba(255, 255, 255, 0.5)'
                        }
                    },
                    splitLine: {
                        lineStyle: {
                            color: 'rgba(255, 255, 255, 0.5)'
                        }
                    }
                }
            ],
            series: [
                {
                    name: '雷达图',
                    type: 'radar',
                    itemStyle: {
                        emphasis: {
                            // color: 各异,
                            lineStyle: {
                                width: 4
                            }
                        }
                    },
                    data: [
                        {
//                            value: [75, 70, 70, 65, 65],
                            name: '已处理报警',
                            areaStyle: {
                                normal: {
                                    color: 'rgba(255, 255, 255, 0.5)'
                                }
                            }
                        },
                        {
//                            value: [95, 80, 95, 90, 93],
                            name: '报警'
                        }
                    ]
                }]
        };
        myChart.setOption(option);
    };
    this.onResize = function () {
        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
        myChart.setOption({
            radar: [{
                    indicator: data.indicator
                }],
            series: [{
                    data: [{
                            value: data.dealAlarm,
                            name: '已处理报警',
                            areaStyle: {
                                normal: {
                                    color: 'rgba(255, 255, 255, 0.5)'
                                }
                            }
                        }, {
                            value: data.alarm,
                            name: '报警'
                        }
                    ]
                }
            ]
        });
    };
};


//库房设备
Bswidget.prototype.KFHK = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    var myChart1 = null;
    this.onShow = function () {
        self.container.get(0).style.height = "50%";

        var height = (self.container.get(0).offsetHeight - 50) / 3; //42按钮高度  16表格实际增加高度
        var fontSize = height / 2;
        if (fontSize < 5) {
            fontSize = 5;
        }
        myChart = echarts.init(self.container.get(0));
        var option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {// 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                top: "8%",
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    axisLine: {
                        lineStyle: {
                            color: "#FFF"
                        }
                    },
                    data: []
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLine: {
                        lineStyle: {
                            color: '#FFF'
                        }
                    }
                }
            ],
            series: [{
                    name: '开启',
                    type: 'bar',
                    stack: '总数',
                    itemStyle: {
                        normal: {
                            label: {
                                show: false,
                                position: 'insideRight'
                            },
                            color: '#8FDE8E'
                        }
                    },
                    data: []
                }, {
                    name: '关闭',
                    type: 'bar',
                    stack: '总数',
                    itemStyle: {
                        normal: {
                            label: {
                                show: false,
                                position: 'insideRight'
                            },
                            color: '#C1C1C1'
                        }
                    },
                    data: []
                }, {
                    name: '设备异常',
                    type: 'bar',
                    stack: '总数',
                    itemStyle: {
                        normal: {
                            label: {
                                show: false,
                                position: 'insideRight'
                            },
                            color: '#E4C791'
                        }
                    },
                    data: []
                }
            ]
        };
        myChart.setOption(option);

        var container2 = $("<div class=\"l_widget_content\"></div>");
//        container2.get(0).style.height = "50%";
        $(self.container).append(container2);
        myChart1 = echarts.init(container2.get(0));
        var option1 = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {// 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                top: "25%",
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                data: ['空调', '加湿机', '风机', '消毒机', '一体机', '空气净化机', '灯光', '电源'],
                silent: false,
                splitLine: {
                    show: false
                },
                axisLine: {
                    lineStyle: {
                        color: '#FFF'
                    }
                }
            },
            yAxis: [{
                    type: 'value',
                    scale: true,
                    name: '温度℃',
                    max: 50,
                    min: 0,
                    nameLocation: 'end',
                    boundaryGap: [0.2, 0.2],
                    axisLine: {
                        lineStyle: {
                            color: '#FFF'
                        }
                    }
                },
                {
                    type: 'value',
                    scale: true,
                    name: '湿度%',
                    max: 100,
                    min: 0,
                    nameLocation: 'end',
                    boundaryGap: [0.2, 0.2],
                    axisLine: {
                        lineStyle: {
                            color: '#FFF'
                        }
                    }
                }],
            series: [{
                    name: '温度',
                    type: 'bar',
                    data: [],
                    animationDelay: function (idx) {
                        return idx * 10;
                        }
                }, {
                    name: '湿度',
                    type: 'bar',
                    data: [],
                    yAxisIndex: 1,
                    animationDelay: function (idx) {
                        return idx * 10 + 10;
                        }
                }],
            animationEasing: 'elasticOut',
            animationDelayUpdate: function (idx) {
                return idx * 5;
            }
        };
        myChart1.setOption(option1);
    };
    this.onResize = function () {
        myChart.resize();
        myChart1.resize();
    };
    this.onSetData = function (data) {//设置数据
        myChart.setOption({
            xAxis: [
                {
                    data: data.device.name
                }
            ],
            series: [{
                    data: data.device.open
                }, {
                    data: data.device.close
                }, {
                    data: data.device.error
                }
            ]
        });
        myChart1.setOption({
            xAxis: {
                data: data.humiture.type
            },
            series: [{
                    data: data.humiture.temperature
                }, {
                    data: data.humiture.humidity
                    }
            ]
        });
    };
};


//档案信息
Bswidget.prototype.DangAnInfo = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart1 = null;
    var myChart2 = null;
    var myChart3 = null;
    var myChart4 = null;
    var timer3 = null;
    var timer4 = null;
    var selectNum3 = 0;
    var selectNum4 = 0;
    this.onShow = function () {
//        var height = (self.container.get(0).offsetHeight - 50) / 3; //42按钮高度  16表格实际增加高度
        var fontSize = getSize(self.container.get(0), 10);
        var content1 = $("<div class=\"l_widget_content_show\"></div>");
        content1.get(0).style.height = "50%";
        content1.get(0).style.display = "flex";
        $(self.container).append(content1);
        var content2 = $("<div class=\"l_widget_content_show\"></div>");
        content2.get(0).style.height = "50%";
        content2.get(0).style.display = "flex";
        $(self.container).append(content2);
        var ddd = $("<div class=\"l_widget_content_show\"></div>");
        ddd.get(0).style.width = "50%";
        $(content1).append(ddd);
        myChart1 = echarts.init(ddd.get(0));
        var option = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            series: [{
                    type: 'pie',
                    name: '',
                    radius: [0, '70%'],
                    label: {
                        normal: {
//                            show: false,
                            position: 'inside',
                            fontSize: fontSize
                        }
                    },
                    color: ['#4DD3E2', 'transparent'],
                    itemStyle: {
                        borderColor: 'rgba(250, 250, 250,1)'
                    },
                    data: [
                    ]
                }]
        };
        myChart1.setOption(option);

        var ddd2 = $("<div class=\"l_widget_content_show\"></div>");
        ddd2.get(0).style.width = "50%";
        $(content1).append(ddd2);
        myChart2 = echarts.init(ddd2.get(0));
        var option2 = getDevicePieOption(fontSize);
        myChart2.setOption(option2);

        var ddd3 = $("<div class=\"l_widget_content_show\"></div>");
        ddd3.get(0).style.width = "50%";
        $(content2).append(ddd3);
        myChart3 = echarts.init(ddd3.get(0));
        var option3 = getPollingPieOption(fontSize * 2);
        myChart3.setOption(option3);

        var ddd4 = $("<div class=\"l_widget_content_show\"></div>");
        ddd4.get(0).style.width = "50%";
        $(content2).append(ddd4);
        myChart4 = echarts.init(ddd4.get(0));
        var option4 = getPollingPieOption(fontSize * 2);
        myChart4.setOption(option4);
    };
    this.onResize = function () {
        myChart1.resize();
        myChart2.resize();
        myChart3.resize();
        myChart4.resize();
    };
    this.onSetData = function (data) {//设置数据
        myChart1.setOption({
            series: [{
                    name: "", //data.data1.name,
                    data: data.data1.data
                }
            ]
        });
        myChart2.setOption({
            series: [{
                    label: {
                        normal: {
                            rich: {
                                Showers: {
                                    backgroundColor: {
                                        image: "lib/bigscreen/images/dangan.png", //data.data2.image
                                    }
                                }
                            }
                        }
                    }
                }, {
                    name: "档案数量", //data.data2.name,
                    data: data.data2.data
                }
            ]
        });
        myChart3.setOption({
            series: [{
                    name: "档案门类", //data.data3.name,
                    data: data.data3.data
                }
            ]
        });
        myChart4.setOption({
            series: [{
                    name: "档案状态", //data.data4.name,
                    data: data.data4.data
                }
            ]
        });
        clearInterval(timer3);
        clearInterval(timer4);
        timer3 = window.setInterval(function () {
            for (var i = 0; i < myChart3.getOption().series[0].data.length; i++) {
                if (i == selectNum3) {
                    myChart3.dispatchAction({
                        type: 'highlight',
                        dataIndex: i
                    });
                } else {
                    myChart3.dispatchAction({
                        type: 'downplay',
                        dataIndex: i
                    });
                }
            }
            selectNum3++;
            if (selectNum3 === myChart3.getOption().series[0].data.length) {
                selectNum3 = 0;
            }
        }, 2000);

        timer4 = window.setInterval(function () {
            for (var i = 0; i < myChart4.getOption().series[0].data.length; i++) {
                if (i == selectNum4) {
                    myChart4.dispatchAction({
                        type: 'highlight',
                        dataIndex: i
                    });
                } else {
                    myChart4.dispatchAction({
                        type: 'downplay',
                        dataIndex: i
                    });
                }
            }
            selectNum4++;
            if (selectNum4 === myChart4.getOption().series[0].data.length) {
                selectNum4 = 0;
            }
        }, 2000);

    };
};


//楼宇自控设备
Bswidget.prototype.LYDevice = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    var childDiv = null;
    this.onShow = function () {
        var height = (self.container.get(0).offsetHeight - 50) / 3; //42按钮高度  16表格实际增加高度
        var fontSize = height / 2;
        if (fontSize < 5) {
            fontSize = 5;
        }
        self.container.get(0).style.display = "flex";
//        self.container.get(0).style.float = "left";
        var ddd = $("<div class=\"l_widget_content_show\"></div>");
        ddd.get(0).style.width = "50%";
        $(self.container).append(ddd);
        myChart = echarts.init(ddd.get(0));
        var option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {// 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                top: "8%",
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    axisLine: {
                        lineStyle: {
                            color: "#FFF"
                        }
                    },
                    data: []
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLine: {
                        lineStyle: {
                            color: '#FFF'
                        }
                    }
                }
            ],
            series: [
                {
                    name: '开启',
                    type: 'bar',
                    stack: '总数',
                    itemStyle: {
                        normal: {
                            label: {
                                show: false,
                                position: 'insideRight'
                            },
                            color: '#8FDE8E'
                        }
                    },
                    data: [1, 2]
                },
                {
                    name: '关闭',
                    type: 'bar',
                    stack: '总数',
                    itemStyle: {
                        normal: {
                            label: {
                                show: false,
                                position: 'insideRight'
                            },
                            color: '#C1C1C1'
                        }
                    },
                    data: []
                },
                {
                    name: '设备异常',
                    type: 'bar',
                    stack: '总数',
                    itemStyle: {
                        normal: {
                            label: {
                                show: false,
                                position: 'insideRight'
                            },
                            color: '#E4C791'
                        }
                    },
                    data: []
                }
            ]
        };
        myChart.setOption(option);

        childDiv = $("<div class=\"l_widget_state_JF\"></div>");
        childDiv.get(0).style.width = "50%";
        $(self.container).append(childDiv);
    };
    this.onResize = function () {
        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
        if (data != null) {
            myChart.setOption({
                xAxis: [
                    {
                        data: ['暖通空调', '给排水']//data.device.type
                    }
                ],
                series: [{
                        data: [1, 2]
                    }, {
                        data: [5, 4]
                    }, {
                        data: [0, 0]
                    }
                ]
                        // series: [{
                        // data: data.device.open
                        // }, {
                        // data: data.device.close
                        // }, {
                        // data: data.device.error
                        // }
                        // ]
            });
            childDiv.empty();
            for (var i = 0; i < data.state.length; i++) {
                var state = "<div  class=\"l_widget_statelist\">" +
                        "<embed src=\"lib/bigscreen/images/circle_" + data.state[i].type + ".svg\"  type=\"image/svg+xml\"/>" +
                        "<span class=\"l_widget_statelist_item\">" + data.state[i].value + "</span>" +
                        "</div>";
                childDiv.append(state);
            }
        }
    };
};


//机房环控
Bswidget.prototype.JFHK = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    var childDiv = null;
    this.onShow = function () {
        self.container.get(0).style.display = "flex";

        var ddd = $("<div class=\"l_widget_content_show\"></div>");
        ddd.get(0).style.width = "50%";
        $(self.container).append(ddd);
        myChart = echarts.init(ddd.get(0));
        var option = getLiquidfillOption(getSize(self.container.get(0), 2));
        option.series[0].shape = 'roundRect';
        myChart.setOption(option);
        childDiv = $("<div class=\"l_widget_state_JF\"></div>");
        childDiv.get(0).style.width = "50%";
        childDiv.get(0).style.padding = 0;
        childDiv.get(0).style.paddingTop = '10%';
        $(self.container).append(childDiv);
    };
    this.onResize = function () {
//        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
        myChart.setOption({//水波纹设置数据需要先置空，否则会出错
            series: [{
                    "data": []
                }
            ]
        });
        myChart.setOption({
            series: [{
                    "data": data.ups
                }
            ]
        });
        childDiv.empty();
        for (var i = 0; i < data.state.length; i++) {
            var state = "<div  class=\"l_widget_statelist\">" +
                    "<embed src=\"lib/bigscreen/images/circle_" + data.state[i].type + ".svg\"  type=\"image/svg+xml\"/>" +
                    "<span class=\"l_widget_statelist_item\">" + data.state[i].value + "</span>" +
                    "</div>";
            childDiv.append(state);
        }
    };
};



//*************** 通用  *****************

function getSize(contentDiv, num) {
    var offsetHeight = contentDiv.offsetHeight > contentDiv.width ? contentDiv.width : contentDiv.offsetHeight;
    var height = (offsetHeight - 50) / 3;
    var fontSize = height / num;
    if (fontSize < 5) {
        fontSize = 5;
    }
    return fontSize;
}

//环形饼图，内有图片
function  getDevicePieOption(fontSize) {
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        series: [
            {
                name: '',
                type: 'pie',
                selectedMode: 'single',
                radius: ["50%", '50%'],
                tooltip: {
                    show: false
                },
                label: {
                    normal: {
                        show: true,
                        position: 'center',
                        formatter: function () {
                            return '{Showers| }';
                        },
                        rich: {
                            Showers: {
                                height: fontSize * 5,
                                align: 'center',
                                backgroundColor: {
                                    image: ''
                                }
                            }
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: [
                    {name: ''}]
            },
            {
                name: '',
                type: 'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false
//                        formatter: '{b}:{c}',
//                        textStyle: {
//                            fontSize: fontSize,
//                            color: 'rgba(250, 250, 250,1)'
//                        }
                    }
                },
                color: ["#1B9600", "#1B9600", "#FB6C6C"],
//                labelLine: {
//                    normal: {
//                        show: false,
//                        length: 2,
//                        length2: 2
//                    }
//                },
                data: [
                ]
            }
        ]
    };
    return option;
}

//文字内嵌饼图
function  getPollingPieOption(fontSize) {
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        series: [
            {
                name: '',
                type: 'pie',
                radius: ['60%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: fontSize,
                            fontWeight: 'bold'
                        }
                    }
                },
                data: []
            }
        ]
    };
    return option;
}

//水波图
function getLiquidfillOption(fontSize) {
    var option = {
        textStyle: {
            "fontSize": fontSize
        },
        series: [{
                type: 'liquidFill',
                radius: '80%',
                data: []
            }]
    };
    return option;
}

//有透明颜色饼图
function getTransPieOption(fontSize) {
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        series: [{
                type: 'pie',
                name: '',
                radius: [0, '70%'],
                label: {
                    normal: {
//                            show: false,
                        position: 'inside',
                        fontSize: fontSize
                    }
                },
                color: ['#4DD3E2', 'transparent'],
                itemStyle: {
                    borderColor: 'rgba(250, 250, 250,1)'
                },
                data: [
                ]
            }]
    };
    return option;
}