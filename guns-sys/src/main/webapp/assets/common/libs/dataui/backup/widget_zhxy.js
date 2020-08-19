//智慧校园

//班级打卡率
Bswidget.prototype.BJDKL = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    this.onShow = function () {
        myChart = echarts.init(self.container.get(0));
        var option = {
            color: ['#3398DB'],
            legend: {
                data: ["打卡率"],
                textStyle: {
                    fontSize: "8px",
                    color: "#fff"
                },
                x: 'center'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {// 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                },
                formatter: "{a} <br/>{b}: {c}% "
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
                            color: '#fff'
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
                    name: '',
                    axisLine: {
                        lineStyle: {
                            type: 'solid',
                            color: '#fff'
                        }
                    },
                    axisLabel: {
                        formatter: '{value}'
                    }
                }
            ],
            series: [
                {
                    name: '打卡率',
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
            series: [
                {
                    data: data.sl
                }
            ]
        });
    };
};


//考勤状态
Bswidget.prototype.KaoQinState = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    this.onShow = function () {
        myChart = echarts.init(self.container.get(0));
        var fontSize = getSize(self.container.get(0), 4);
        var option = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            series: [{
                    type: 'pie',
                    name: '',
                    roseType: 'area',
                    radius: ['30%', '70%'],
                    label: {
                        normal: {
                            fontSize: fontSize
                        }
                    },
//                    color: ["#058CB0", "#FAAB42", "#00C272", "#F93271", "#8030FF", "#EBC806"],
                    color: ['#1E90FF', '#87CEFA', '#00FFFF', '#FFA500', '#EEC900', '#E9967A', '#FF00FF', '#7D26CD'],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    },
                    data: [
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
            series: [{
                    name: data.name,
                    data: data.data
                }
            ]
        });
    };
};


//隐患巡检情况
Bswidget.prototype.YHXJQK = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    this.onShow = function () {
        myChart = echarts.init(self.container.get(0));
        var option = {
            grid: {
                left: '5%',
                right: '5%',
                bottom: '10%'
            },
            legend: {
                data: ["巡检完成率", "巡检及时率", "隐患解决率", "隐患数量"],
                textStyle: {
                    fontSize: "8px",
                    color: "#fff"
                },
                x: 'center'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            xAxis: [
                {
                    type: 'category',
                    data: [],
                    axisLine: {
                        lineStyle: {
                            type: 'solid',
                            color: '#fff'
                        }
                    },
                    axisPointer: {
                        type: 'shadow'
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '%',
                    max: 100,
                    min: 0,
                    axisLine: {
                        lineStyle: {
                            type: 'solid',
                            color: '#fff'
                        }
                    },
                    axisLabel: {
                        formatter: '{value} '
                    }
                },
                {
                    type: 'value',
                    name: '个',
                    axisLine: {
                        lineStyle: {
                            type: 'solid',
                            color: '#fff'
                        }
                    },
                    axisLabel: {
                        show: true,
                        formatter: '{value} '
                    }
                }
            ],
            series: []
        };
        myChart.setOption(option);
    };
    this.onResize = function () {
        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
        myChart.setOption({
            xAxis: [{
                    data: data.month
                }],
            series: [{
                    itemStyle: {
                        normal: {
                            barBorderRadius: 5,
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#f7734e'
                                }, {
                                    offset: 1,
                                    color: '#e12945'
                                }])
                        }
                    },
                    name: '巡检完成率',
                    type: 'bar',
                    data: data.xjwcl
                },
                {
                    itemStyle: {
                        normal: {
                            barBorderRadius: 5,
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#96d668'
                                }, {
                                    offset: 1,
                                    color: '#01babc'
                                }])
                        }
                    },
                    name: '巡检及时率',
                    type: 'bar',
                    data: data.xjjsl
                },
                {
                    itemStyle: {
                        normal: {
                            barBorderRadius: 5,
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#00FFE6'
                                }, {
                                    offset: 1,
                                    color: '#007CC6'
                                }])
                        }
                    },
                    name: '隐患解决率',
                    type: 'bar',
                    data: data.yhjjl
                },
                {
                    itemStyle: {
                        normal: {
                            color: '#9400D3'
                        }
                    },
                    name: '隐患数量',
                    type: 'line',
                    yAxisIndex: 1,
                    data: data.yhsl
                }
            ]
        });
    };
};

//隐患处理情况
Bswidget.prototype.YHCLQK = function (widgetBean) {
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
        totalYinHuan = $("<div class=\"l_widget_content_item\"><div>汇报隐患</div><div>15个</div></div>");
        dealYinHuan = $("<div class=\"l_widget_content_item\"><div>处理隐患</div><div>9个</div></div>");
        $(totalYinHuan).find("div")[0].style.fontSize = fontSize * 2 + 'px';
        $(totalYinHuan).find("div")[1].style.fontSize = fontSize * 4 + 'px';
        $(dealYinHuan).find("div")[0].style.fontSize = fontSize * 2 + 'px';
        $(dealYinHuan).find("div")[1].style.fontSize = fontSize * 4 + 'px';
        $(childDiv).append(totalYinHuan);
        $(childDiv).append(dealYinHuan);
    };
    this.onResize = function () {
//        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
        var value = data.clyh / data.hbyh;
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

//隐患排查情况
Bswidget.prototype.YHPCQK = function (widgetBean) {
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
        var fontSize = getSize(self.container.get(0), 5);
        var option = {
            tooltip: {
                formatter: "{b} <br/>{c}% "
            },
            series: [
                {
                    name: '完成率',
                    type: 'gauge',
                    min: 0,
                    max: 100,
                    startAngle: 180,
                    endAngle: 0,
                    splitNumber: 5,
                    radius: '150%',
                    center: ['50%', '80%'],
                    axisLine: {// 坐标轴线
                        lineStyle: {// 属性lineStyle控制线条样式
                            color: [[0.6, '#6BE7C6'], [0.8, '#1e90ff'], [1, 'lime']],
                            width: 10,
                            shadowColor: '#fff', //默认透明
                            shadowBlur: 10
                        }
                    },
                    axisLabel: {// 坐标轴小标记
                        textStyle: {// 属性lineStyle控制线条样式
                            fontWeight: 'bolder',
                            color: '#fff',
                            shadowColor: '#fff', //默认透明
                            shadowBlur: 10
                        }
                    },
                    axisTick: {// 坐标轴小标记
                        length: 15, // 属性length控制线长
                        lineStyle: {// 属性lineStyle控制线条样式
                            color: 'auto',
                            shadowColor: '#fff', //默认透明
                            shadowBlur: 10
                        }
                    },
                    splitLine: {// 分隔线
                        length: 25, // 属性length控制线长
                        lineStyle: {// 属性lineStyle（详见lineStyle）控制线条样式
                            width: 3,
                            color: '#fff',
                            shadowColor: '#fff', //默认透明
                            shadowBlur: 10
                        }
                    },
                    pointer: {// 分隔线
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 10
                    },
                    title: {
                        textStyle: {// 其余属性默认使用全局文本样式，详见TEXTSTYLE
                            fontWeight: 'bolder',
                            fontSize: fontSize,
                            fontStyle: 'italic',
                            color: '#fff',
                            shadowColor: '#fff', //默认透明
                            shadowBlur: 10
                        }
                    },
                    detail: {
//                        backgroundColor: 'rgba(30,144,255,0.8)',
//                        borderWidth: 1,
                        borderColor: '#fff',
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 5,
                        width: '68%',
                        offsetCenter: [0, "-20%"], // x, y，单位px
                        textStyle: {// 其余属性默认使用全局文本样式，详见TEXTSTYLE
                            fontWeight: 'bolder',
                            fontSize: fontSize,
                            color: '#fff'
                        },
                        formatter: '{value}%'
                    },
                    data: [{value: 0, name: "完成率"}]
                }
            ]
        };
        myChart.setOption(option);
        childDiv = $("<div class=\"l_widget_state_JF\"></div>");
        childDiv.get(0).style.height = "30%";
        childDiv.get(0).style.display = "flex";
        childDiv.get(0).style.padding = 0;
        $(self.container).append(childDiv);
        var fontSize = getSize(childDiv.get(0), 1);
        totalYinHuan = $("<div class=\"l_widget_content_item\"><div>应检任务</div><div>15个</div></div>");
        dealYinHuan = $("<div class=\"l_widget_content_item\"><div>实检任务</div><div>9个</div></div>");
        $(totalYinHuan).find("div")[0].style.fontSize = fontSize * 2 + 'px';
        $(totalYinHuan).find("div")[1].style.fontSize = fontSize * 4 + 'px';
        $(dealYinHuan).find("div")[0].style.fontSize = fontSize * 2 + 'px';
        $(dealYinHuan).find("div")[1].style.fontSize = fontSize * 4 + 'px';
        $(childDiv).append(totalYinHuan);
        $(childDiv).append(dealYinHuan);
    };
    this.onResize = function () {
        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
        var value = parseInt((data.sjrw * 100) / data.yjrw);
        myChart.setOption({
            series: [{
                    "data": [
                        {
                            value: value,
                            name: data.name
                        }
                    ]
                }
            ]
        });
    };
};

Bswidget.prototype.AlarmCount = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    this.onShow = function () {
        myChart = echarts.init(self.container.get(0));
        var option = option = {
            legend: {
                data: ['今日报警', '未处理报警'],
                textStyle: {
                    fontSize: "8px",
                    color: "#fff"
                },
                x: 'center'
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
                top: '17%',
                bottom: '3%',
                containLabel: true
            },
            yAxis: {
                type: 'value',
                name: '数量',
                boundaryGap: [0, 0.01],
                axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#fff'
                    }
                },
                axisLabel: {
                    formatter: '{value}'
                }
            },
            xAxis: {
                type: 'category',
                axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#fff'
                    }
                },
                axisLabel: {
                    interval: 0,
                    formatter: function (value) {
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
                        }
                        else {
                            return value;
                        }
                    }
                },
                data: []
            },
            series: [
                {
                    name: '今日报警',
                    type: 'bar',
                    barMaxWidth: "40",
                    data: []
                },
                {
                    name: '未处理报警',
                    type: 'bar',
                    barMaxWidth: "40",
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
                    data: data.humiture.type
                }],
            series: [
                {
                    itemStyle: {
                        normal: {
                            barBorderRadius: 5,
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#00FFE6'
                                }, {
                                    offset: 1,
                                    color: '#007CC6'
                                }])
                        }
                    },
                    data: data.humiture.temperature
                },
                {
                    itemStyle: {
                        normal: {
                            barBorderRadius: 5,
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#3023AE'
                                }, {
                                    offset: 1,
                                    color: '#C96DD8'
                                }])
                        }
                    },
                    data: data.humiture.humidity
                }
            ]
        });
    };
};


//设备信息
Bswidget.prototype.DeviceInfo = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart1 = null;
    var myChart2 = null;
    var myChart3 = null;
    var myChart4 = null;
    var div1 = null;
    var div2 = null;
    var div3 = null;
    var div4 = null;
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
        //058CB0  FAAB42  00C272  F93271  8030FF   EBC806
        //39B2E7  6BE7C6  636D94  A5A6E7 C6EBAD 94DFEF
        div1 = $("<div class=\"l_widget_content_show\"></div>");
        div1.get(0).style.width = "50%";
        $(content1).append(div1);
        myChart1 = echarts.init(div1.get(0));
        var option = getDeviceStateOption(fontSize, "#39B2E7");
        myChart1.setOption(option);

        div2 = $("<div class=\"l_widget_content_show\"></div>");
        div2.get(0).style.width = "50%";
        $(content1).append(div2);
        myChart2 = echarts.init(div2.get(0));
        var option2 = getDeviceStateOption(fontSize, "#6BE7C6");
        myChart2.setOption(option2);

        div3 = $("<div class=\"l_widget_content_show\"></div>");
        div3.get(0).style.width = "50%";
        $(content2).append(div3);
        myChart3 = echarts.init(div3.get(0));
        var option3 = getDeviceStateOption(fontSize, "#C6EBAD");
        myChart3.setOption(option3);

        div4 = $("<div class=\"l_widget_content_show\"></div>");
        div4.get(0).style.width = "50%";
        $(content2).append(div4);
        myChart4 = echarts.init(div4.get(0));
        var option4 = getDeviceStateOption(fontSize, "#A5A6E7");
        myChart4.setOption(option4);
    };
    this.onResize = function () {
        myChart1.resize();
        myChart2.resize();
        myChart3.resize();
        myChart4.resize();
    };
    this.onSetData = function (data) {//设置数据
        $(div1).unbind("click");
        $(div2).unbind("click");
        $(div3).unbind("click");
        $(div4).unbind("click");
        if (data.data1.type != null) {
            $(div1).click(function () {
                $("#ecmapDiv").data("ecmap").showEcmap("", false);
                window.frames["ecmapIframe"].ecmap.system = new Array();
                window.frames["ecmapIframe"].ecmap.system.push(data.data1.type);
            });
        } else {
            $(div1).click(function () {
                $("#ecmapDiv").data("ecmap").showEcmap("", false);
            });
        }
        if (data.data2.type != null) {
            $(div2).click(function () {
                $("#ecmapDiv").data("ecmap").showEcmap("", false);
                window.frames["ecmapIframe"].ecmap.system = new Array();
                window.frames["ecmapIframe"].ecmap.system.push(data.data2.type);
            });
        } else {
            $(div2).click(function () {
                $("#ecmapDiv").data("ecmap").showEcmap("", false);
            });
        }
        if (data.data3.type != null) {
            $(div3).click(function () {
                $("#ecmapDiv").data("ecmap").showEcmap("", false);
                window.frames["ecmapIframe"].ecmap.system = new Array();
                window.frames["ecmapIframe"].ecmap.system.push(data.data3.type);
            });
        } else {
            $(div3).click(function () {
                $("#ecmapDiv").data("ecmap").showEcmap("", false);
            });
        }
        if (data.data4.type != null) {
            $(div4).click(function () {
                $("#ecmapDiv").data("ecmap").showEcmap("", false);
                window.frames["ecmapIframe"].ecmap.system = new Array();
                window.frames["ecmapIframe"].ecmap.system.push(data.data4.type);
            });
        } else {
            $(div4).click(function () {
                $("#ecmapDiv").data("ecmap").showEcmap("", false);
            });
        }
        myChart1.setOption({
            title: {
                text: data.data1.name
            },
            series: [
                {
                    data: [{name: data.data1.lable + "%"}]
                },
                {
                    name: data.data1.name,
                    data: data.data1.data
                }
            ]
        });
        myChart2.setOption({
            title: {
                text: data.data2.name
            },
            series: [
                {
                    data: [{name: data.data2.lable + "%"}]
                },
                {
                    name: data.data2.name,
                    data: data.data2.data
                }
            ]
        });
        myChart3.setOption({
            title: {
                text: data.data3.name
            },
            series: [
                {
                    data: [{name: data.data3.lable + "%"}]
                },
                {
                    name: data.data3.name,
                    data: data.data3.data
                }
            ]
        });
        myChart4.setOption({
            title: {
                text: data.data4.name
            },
            series: [
                {
                    data: [{name: data.data4.lable + "%"}]
                },
                {
                    name: data.data4.name,
                    data: data.data4.data
                }
            ]
        });

    };
};

//访客信息
Bswidget.prototype.VisitorInfo = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    var myChart1 = null;
    this.onShow = function () {
        var fontSize = getSize(self.container.get(0), 10);
        self.container.get(0).style.display = "flex";
        var ddd = $("<div class=\"l_widget_content_show\"></div>");
        ddd.get(0).style.width = "50%";
        $(self.container).append(ddd);
        myChart = echarts.init(ddd.get(0));
        var option = {
            title: {
                text: '访客总数',
                x: 'center',
                top: "5%",
                textStyle: {
                    fontSize: fontSize * 3,
                    fontWeight: 'bold',
                    color: "#FFF"
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            series: [
                {
                    name: '',
                    type: 'pie',
                    selectedMode: 'single',
                    center: ['50%', '60%'],
                    radius: ['60%', '80%'],
                    itemStyle: {
                        color: {
                            type: 'linear',
                            x: 0,
                            y: 0,
                            x2: 0,
                            y2: 1,
                            colorStops: [{
                                    offset: 0, color: '#eea2a2' // 0% 处的颜色
                                }, {
                                    offset: 0.19, color: '#bbc1bf' // 0% 处的颜色
                                }, {
                                    offset: 0.42, color: '#57c6e1' // 0% 处的颜色
                                }, {
                                    offset: 0.79, color: '#b49fda' // 0% 处的颜色
                                }, {
                                    offset: 1, color: '#7ac5d8' // 100% 处的颜色
                                }]
                        }
                    },
                    tooltip: {
                        show: false
                    },
                    label: {
                        normal: {
                            show: true,
                            position: 'center',
                            formatter: "{c}人",
                            textStyle: {
                                fontSize: fontSize * 4,
                                fontWeight: 'bold',
                                color: "#57c6e1"
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: [
                        {name: '今日访客', value: "200"}
                    ]
                }]
        };
        myChart.setOption(option);
        var ddd2 = $("<div class=\"l_widget_content_show\"></div>");
        ddd2.get(0).style.width = "50%";
        $(self.container).append(ddd2);
        myChart1 = echarts.init(ddd2.get(0));
        var option2 = {
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
                    axisLabel: {
                        interval: 0,
                        formatter: function (value) {
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
                            }
                            else {
                                return value;
                            }
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
                    },
                    minInterval: 1
                }
            ],
            series: [{
                    name: '',
                    type: 'bar',
//                    color: ["#FAAB42", "#00C272", "#F93271"],
                    itemStyle: {
                        normal: {
                            label: {
                                show: false,
                                position: 'insideRight'
                            },
                            barBorderRadius: 5,
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#96d668'
                                }, {
                                    offset: 1,
                                    color: '#01babc'
                                }])
                        }
                    },
                    data: []
                }
            ]
        };
        myChart1.setOption(option2);
    };
    this.onResize = function () {
        myChart.resize();
        myChart1.resize();
    };
    this.onSetData = function (data) {//设置数据
        myChart.setOption({
            series: [{
                    data: data.total
                }
            ]
        });
        myChart1.setOption({
            xAxis: [
                {
                    data: data.type
                }
            ],
            series: [{
                    data: data.count
                }
            ]
        });
    };
};

//环形饼图，内有文字，显示在线状态
function  getDeviceStateOption(fontSize, color) {
    var option = {
        title: {
            text: '',
            x: 'left',
            textStyle: {
                fontSize: fontSize * 2,
                fontWeight: 'bold',
                color: "#FFF"
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        series: [
            {
                name: '',
                type: 'pie',
                selectedMode: 'single',
                center: ['50%', '60%'],
                radius: ["50%", '50%'],
                tooltip: {
                    show: false
                },
                label: {
                    normal: {
                        show: true,
                        position: 'center',
                        textStyle: {
                            fontSize: fontSize * 2.5,
                            fontWeight: 'bold',
                            color: color
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: [
                    {name: ''}
                ]
            },
            {
                name: '',
                type: 'pie',
                radius: ['50%', '70%'],
                center: ['50%', '60%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false

                    }
                },
//                color: [color, color, 'rgba(204, 204, 204, 0.5)'],
                data: [
                ]
            }
        ]
    };
    return option;
}

//058CB0  FAAB42  00C272  F93271  8030FF   EBC806


//39B2E7  6BE7C6  636D94  A5A6E7 C6EBAD 94DFEF