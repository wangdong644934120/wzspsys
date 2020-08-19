//雷达图
Bswidget.prototype.DataDistribute = function (widgetBean) {
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
                            lineStyle: {
                                width: 4
                            }
                        }
                    },
                    data: [
                        {
                            name: '分布',
                            areaStyle: {
                                normal: {
                                    color: 'rgba(255, 255, 255, 0.5)'
                                }
                            }
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
                            value: data.value,
                            name: data.name,
                            areaStyle: {
                                normal: {
                                    color: 'rgba(255, 255, 255, 0.5)'
                                }
                            }
                        }
                    ]
                }
            ]
        });
    };
};

//容量统计
Bswidget.prototype.RLTJ = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    var childDiv = null;
    var totalYinHuan = null;
    var dealYinHuan = null;
    this.onShow = function () {
        var ddd = $("<div class=\"l_widget_content_show\"></div>");
        ddd.get(0).style.height = "70%";
        ddd.get(0).style.width = "100%";
        $(self.container).append(ddd);
        myChart = echarts.init(ddd.get(0));
        var option = getLiquidfillOption(getSize(self.container.get(0), 2));
        option.series[0].shape = 'roundRect';
        myChart.setOption(option);
        childDiv = $("<div class=\"l_widget_state_JF\"></div>");
        childDiv.get(0).style.height = "30%";
        childDiv.get(0).style.display = "flex";
        childDiv.get(0).style.padding = 0;
        $(self.container).append(childDiv);
        var fontSize = getSize(childDiv.get(0), 1);
        totalYinHuan = $("<div class=\"l_widget_content_item\"style='padding-top: 10px;'><div id='RLTJ_ALL_TEXT'>总容量</div><div id='RLTJ_ALL_NUM'>10</div></div>");
        dealYinHuan = $("<div class=\"l_widget_content_item\" style='padding-top: 10px;'><div id='RLTJ_USE_TEXT'>已用容量</div><div id='RLTJ_USE_NUM'>6</div></div>");
        $(totalYinHuan).find("div")[0].style.fontSize = fontSize * 1.2 + 'px';
        $(totalYinHuan).find("div")[1].style.fontSize = fontSize * 1.5 + 'px';
        $(dealYinHuan).find("div")[0].style.fontSize = fontSize * 1.2 + 'px';
        $(dealYinHuan).find("div")[1].style.fontSize = fontSize * 1.5 + 'px';
        $(childDiv).append(totalYinHuan);
        $(childDiv).append(dealYinHuan);
    };
    this.onResize = function () {
//        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
        if (data.allName != undefined && data.allName != '') {
            $("#RLTJ_ALL_TEXT").html(data.allName);
        }
        if (data.useName != undefined && data.useName != '') {
            $("#RLTJ_USE_TEXT").html(data.useName);
        }
        $("#RLTJ_ALL_NUM").html(data.all);
        $("#RLTJ_USE_NUM").html(data.use);
        var value = data.use / data.all;
        var num = value.toFixed(2);
        var uList = [];
        for (var i = 0; i < 4; i++) {
            if (num >= 0) {
                uList.push(num);
                num -= 0.1;
            } else {
                break;
            }
        }
        myChart.setOption({//水波纹设置数据需要先置空，否则会出错
            series: [{
                    "data": []
                }
            ]
        });
        myChart.setOption({
            series: [{
                    "data": uList
                }
            ]
        });

    };
};


//库房环控统计曲线图
Bswidget.prototype.TJLine = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    this.onShow = function () {
        myChart = echarts.init(self.container.get(0));
        var option = {
            tooltip: {
                trigger: 'axis'
            },
            calculable: true,
            legend: {
                data: [
                    {
                        name: '温度',
                        textStyle: {
                            color: "#408829"
                        }
                    },
                    {
                        name: '湿度',
                        textStyle: {
                            color: "#159CF6"
                        }
                    }
                ]
            },
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
                {
                    name: '温度',
                    type: 'line',
                    data: [],
                    color: "#408829",
                    markPoint: {
                        data: [
                        ], itemStyle: {
                            color: 'red'
                        }
                    },
                    markLine: {
                        data: [
                        ], lineStyle: {
                            width: 2,
                            type: 'dashed'
                        },
                        label: {
                            position: 'start'
                        }
                    }
                },
                {
                    name: '湿度',
                    type: 'line',
                    data: [],
                    color: "#159CF6",
                    markPoint: {
                        data: [
                        ], itemStyle: {
                            color: 'red'
                        }
                    },
                    markLine: {
                        data: [
                        ],
                        lineStyle: {
                            width: 2,
                            type: 'solid'
                        }
                    }
                    , yAxisIndex: 1
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
            series: [
                {
                    data: data.wd,
                    markPoint: {
                        data: data.temperatureAbnormal,
                    },
                    markLine: {
                        data: data.temperatureYJ,
                    }
                },
                {
                    data: data.sd,
                    markPoint: {
                        data: data.humidityAbnormal,
                    },
                    markLine: {
                        data: data.humidityYJ,
                    }

                }
            ]
        });
    };
};
//库房环控统计曲线图
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
//柱状图统计
Bswidget.prototype.ZZTTJ = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    this.onShow = function () {
        myChart = echarts.init(self.container.get(0));
        var option = {
            color: ['#3398DB'],
            legend: {
                show: false
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {// 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                top: '15%',
                bottom: '8%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: [],
                    axisTick: {
                        alignWithLabel: true
                    },
                    axisLine: {
                        lineStyle: {
                            type: 'solid',
                            color: '#fff',
                        }
                    },
                    axisLabel: {
                        interval: 0,
                        formatter: function (value) {
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
                            }
                            else {
                                return value;
                            }
                        }
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '册',
                    axisLine: {
                        lineStyle: {
                            type: 'solid',
                            color: '#fff',
                        },
                    },
                    axisLabel: {
                        formatter: '{value}',
                    }
                }
            ],
            series: [
                {
                    name: '数量',
                    type: 'bar',
                    barWidth: '60%',
                    itemStyle: {
                        normal: {
                            barBorderRadius: 5,
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#1a98f8'
                                }, {
                                    offset: 1,
                                    color: '#7049f0'
                                }])
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
            xAxis: [{
                    data: data.lb
                }],
            yAxis: [{
                    name: data.ydw
                }],
            series: [
                {
                    data: data.sl
                }
            ]
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
/**
 * 横向柱状图
 * @param {type} widgetBean
 * @returns {undefined}
 */

Bswidget.prototype.HXZZT = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    this.onShow = function () {
        myChart = echarts.init(self.container.get(0));
        var option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {
                data: ['待上架', '在库', '借阅'],
                y: 8,
                textStyle: {
                    color: "#fff"
                }
            },
            grid: {
                x: 70,
                y: 35,
                x2: 20,
                y2: 30
            },
            toolbox: {
                show: false
            },
            calculable: false,
            xAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        interval: 'auto'
                    },
                    axisLine: {
                        lineStyle: {
                            type: 'solid',
                            color: '#fff'
                        }
                    }
                }
            ],
            yAxis: [
                {
                    type: 'category',
                    axisTick: {
                        alignWithLabel: true
                    },
                    axisLine: {
                        lineStyle: {
                            type: 'solid',
                            color: '#fff',
                        }
                    },
                }
            ],
            series: [
                {
                    name: '待上架',
                    type: 'bar',
                    stack: '总数',
                    itemStyle: {
                        normal: {
                            label: {
                                show: false,
                                position: 'insideRight'
                            },
                            color: '#93AEE5'
                        }
                    },
                    data: [0, 0, 0]
                },
                {
                    name: '在库',
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
                    data: [0, 0, 0]
                },
                {
                    name: '借阅',
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
                    data: [0, 0, 0]
                }
            ]
        };
        myChart.setOption(option);
    };
    this.onResize = function () {
    };
    this.onSetData = function (data) {//设置数据
        var ztList = data.zt;
        var dataList = new Array();
        for (var i = 0; i < ztList.length; i++) {
            dataList.push(
                    {
                        name: ztList[i],
                        data: data.info[i],
                        type: 'bar',
                        stack: '总数',
                        itemStyle: {
                            normal: {
                                label: {
                                    show: false,
                                    position: 'insideRight'
                                },
                                color: data.color[i]
                            }
                        }
                    }
            );
        }
        myChart.setOption({
            legend: {
                data: data.zt
            },
            yAxis: {
                data: data.lb
            },
            series: dataList
        });
    };
};
//库房设备
Bswidget.prototype.WdCount = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;

    var myChart1 = null;
    this.onShow = function () {
        self.container.get(0).style.height = "100%";

        var height = (self.container.get(0).offsetHeight - 50) / 3; //42按钮高度  16表格实际增加高度
        var fontSize = height / 2;
        if (fontSize < 5) {
            fontSize = 5;
        }
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
            }, legend: {
                data: ['温度', '湿度']
                ,
                textStyle: {
                    color: '#FFF'
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
                data: ['库房1', '库房2', '库房3', '库房4', '库房5', '库房6'],
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
            yAxis: [
                {
                    splitLine: {show: false}, //去除网格线
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
                    splitLine: {show: false}, //去除网格线
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
                    color: 'rgb(64, 136, 41)',
                    animationDelay: function (idx) {
                        return idx * 10;
                    },
                    markPoint: {
                        data: [
                        ], itemStyle: {
                            color: 'red'
                        }
                    },
                    markLine: {
                        data: [
                        ], lineStyle: {
                            width: 2,
                            type: 'dashed'
                        },
                        label: {
                            position: 'start',
                        }
                    }
                }, {
                    name: '湿度',
                    type: 'bar',
                    data: [],
                    color: 'rgb(30, 144, 255)',
                    animationDelay: function (idx) {
                        return idx * 10 + 10;
                    },
                    markPoint: {
                        data: [
                        ], itemStyle: {
                            color: 'red'
                        }
                    },
                    markLine: {
                        data: [
                        ],
                        lineStyle: {
                            width: 2,
                            type: 'solid'
                        }
                    }
                    , yAxisIndex: 1
                }],
            animationEasing: 'elasticOut',
            animationDelayUpdate: function (idx) {
                return idx * 5;
            }
        };
        myChart1.setOption(option1);
    };
    this.onResize = function () {
        myChart1.resize();
    };
    this.onSetData = function (data) {//设置数据
        myChart1.setOption({
            xAxis: {
                data: data.humiture.type
            },
            series: [{
                    data: data.humiture.temperature,
                    markPoint: {
                        data: data.humiture.temperatureAbnormal,
                    },
                    markLine: {
                        data: data.humiture.temperatureYJ,
                    }
                }, {
                    data: data.humiture.humidity,
                    markPoint: {
                        data: data.humiture.humidityAbnormal,
                    },
                    markLine: {
                        data: data.humiture.humidityYJ,
                    }
                }
            ]
        });
    };
};
//库房设备
//隐患排查情况
Bswidget.prototype.WSDEvery = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;

    this.onShow = function () {
        var ddd = $("<div class=\"l_widget_content_show\"></div>");
        ddd.get(0).style.height = "85%";
        ddd.get(0).style.width = "100%";
        $(self.container).append(ddd);
        myChart = echarts.init(ddd.get(0));
        var fontSize = getSize(self.container.get(0), 2);
        var fontSize1 = getSize(self.container.get(0),1.2);
        var option = {
            tooltip: {
                formatter: "{b} <br/>{c}% "
            },
            series: [
                {
                    name: '温度',
                    type: 'gauge',
                    min: 0,
                    max: 50,
                    startAngle: 0,
                    endAngle: 0,
                    splitNumber: 5,
                    radius: '98%',
                    center: ['25%', '60%'],
                    axisLine: {// 坐标轴线
                        lineStyle: {// 属性lineStyle控制线条样式
                            color: [[0.6, '#fff'], [0.8, '#1e90ff'], [1, 'red']],
                            width: 5,
                            shadowColor: '#fff', //默认透明
                            shadowBlur: 5
                        }
                    },
                    axisLabel: {// 坐标轴小标记
                        textStyle: {// 属性lineStyle控制线条样式
                            fontWeight: 'bolder',
                            color: '#fff',
                            shadowColor: '#fff', //默认透明
                            shadowBlur: 5
                        }
                    },
                    axisTick: {// 坐标轴小标记
                        length: 8, // 属性length控制线长
                        lineStyle: {// 属性lineStyle控制线条样式
                            color: 'auto',
                            shadowColor: '#fff', //默认透明
                            shadowBlur: 5
                        }
                    },
                    splitLine: {// 分隔线
                        length: 10, // 属性length控制线长
                        lineStyle: {// 属性lineStyle（详见lineStyle）控制线条样式
                            width: 3,
                            color: '#fff',
                            shadowColor: '#fff', //默认透明
                            shadowBlur: 5
                        }
                    },
                    pointer: {// 分隔线
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 5,
                        width:5
                    },
                    title: {
                        textStyle: {// 其余属性默认使用全局文本样式，详见TEXTSTYLE
                            fontWeight: 'bolder',
                            fontSize: fontSize,
                            fontStyle: 'italic',
                            color: '#fff',
                            shadowColor: '#fff', //默认透明
                            shadowBlur: 5
                        }
                    },
                    detail: {
                        borderColor: '#fff',
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 5,
                        width: '68%',
                        
                        offsetCenter: [0, "27%"], // x, y，单位px
                        textStyle: {// 其余属性默认使用全局文本样式，详见TEXTSTYLE
                            fontWeight: 'bolder',
                            fontSize: fontSize1,
//                            color: '#fff'
                        },
                        formatter: '{value}℃'
                    },
                    data: [{value: 0, name: "温度"}]
                },
                {
                    name: '湿度',
                    type: 'gauge',
                    min: 0,
                    max: 100,
                    startAngle: 180,
                    endAngle: 0,
                    splitNumber: 5,
                    radius: '98%',
                    center: ['75%', '60%'],
                    axisLine: {// 坐标轴线
                        lineStyle: {// 属性lineStyle控制线条样式
                            color: [[0.6, '#fff'], [0.8, '#1e90ff'], [1, 'red']],
                            width: 5,
                            shadowColor: '#fff', //默认透明
                            shadowBlur: 5
                        }
                    },
                    axisLabel: {// 坐标轴小标记
                        textStyle: {// 属性lineStyle控制线条样式
                            fontWeight: 'bolder',
                            color: '#fff',
                            shadowColor: '#fff', //默认透明
                            shadowBlur: 5
                        }
                    },
                    axisTick: {// 坐标轴小标记
                        length: 8, // 属性length控制线长
                        lineStyle: {// 属性lineStyle控制线条样式
                            color: 'auto',
                            shadowColor: '#fff', //默认透明
                            shadowBlur: 5
                        }
                    },
                    splitLine: {// 分隔线
                        length: 10, // 属性length控制线长
                        lineStyle: {// 属性lineStyle（详见lineStyle）控制线条样式
                            width: 3,
                            color: '#fff',
                            shadowColor: '#fff', //默认透明
                            shadowBlur: 5
                        }
                    },
                    pointer: {// 分隔线
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 5,
                        width:5
                        
                    },
                    title: {
                        textStyle: {// 其余属性默认使用全局文本样式，详见TEXTSTYLE
                            fontWeight: 'bolder',
                            fontSize: fontSize,
                            fontStyle: 'italic',
                            color: '#fff',
                            shadowColor: '#fff', //默认透明
                            shadowBlur: 5
                        }
                    },
                    detail: {
                        borderColor: '#fff',
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 5,
                        width: '68%',
                        offsetCenter: [0, "27%"], // x, y，单位px
                        textStyle: {// 其余属性默认使用全局文本样式，详见TEXTSTYLE
                            fontWeight: 'bolder',
                            fontSize: fontSize1,
//                            color: '#fff'
                        },
                        formatter: '{value}%RH'
                    },
                    data: [{value: 0, name: "湿度"}]
                }
            ]
        };
        myChart.setOption(option);
    };
    this.onResize = function () {
        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
//        var value = parseInt((data.sjrw * 100) / data.yjrw);
        myChart.setOption({
            series: [{
                    axisLine: {// 坐标轴线
                        lineStyle: {// 属性lineStyle控制线条样式
                            color: [[data.wdmin, '#fff'], [data.wdmax, '#1e90ff'], [1, 'red']],
                            width: 5,
                            shadowColor: '#fff', //默认透明
                            shadowBlur: 5
                        }
                    },
                    "data":
                            [ {"value": data.wd,"name": " 温度"}],
         
                }, {axisLine: {// 坐标轴线
                        lineStyle: {// 属性lineStyle控制线条样式
                            color: [[data.sdmin, '#fff'], [data.sdmax, '#1e90ff'], [1, 'red']],
                            width: 5,
                            shadowColor: '#fff', //默认透明
                            shadowBlur: 5
                        }
                    },
                    "data":
                            [ {"value": data.sd,"name": " 湿度"}],
                }
            ]
        });
    };
};