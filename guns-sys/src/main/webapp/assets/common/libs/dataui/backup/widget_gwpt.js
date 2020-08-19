/* 
 * 管网平台大屏显示
 */
Bswidget.prototype.bgMap = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    this.onShow = function () {
        $("#bgMap").css("opacity", 0.7);
        $("#bgMap").css("z-index", 0);
        myChart = echarts.init(self.container.get(0));
        var option = option = {
            tooltip: {
                trigger: 'item',
                formatter: function (params) {
                    return params.data.name + "(" + params.data.value[0] + "," + params.data.value[1] + ")";
                }
            },
            bmap: {
                center: [121.938827, 37.249083],
                zoom: 10,
                roam: true,
                mapStyle: {
                    styleJson: [{
                            "featureType": "water",
                            "elementType": "all",
                            "stylers": {
                                "color": "#436486"
                            }
                        }, {
                            "featureType": "land",
                            "elementType": "all",
                            "stylers": {
                                "color": "#0E2338"
                            }
                        }, {
                            "featureType": "boundary",
                            "elementType": "geometry",
                            "stylers": {
                                "color": "#064f85"
                            }
                        }, {
                            "featureType": "railway",
                            "elementType": "all",
                            "stylers": {
                                "visibility": "off"
                            }
                        }, {
                            "featureType": "highway",
                            "elementType": "geometry",
                            "stylers": {
                                "color": "#004981",
                                "visibility": "off"
                            }
                        }, {
                            "featureType": "highway",
                            "elementType": "geometry.fill",
                            "stylers": {
                                "color": "#005b96",
                                "lightness": 1,
                                "visibility": "off"
                            }
                        }, {
                            "featureType": "highway",
                            "elementType": "labels",
                            "stylers": {
                                "visibility": "off"
                            }
                        }, {
                            "featureType": "arterial",
                            "elementType": "geometry",
                            "stylers": {
                                "color": "#004981",
                                "visibility": "off"
                            }
                        }, {
                            "featureType": "arterial",
                            "elementType": "geometry.fill",
                            "stylers": {
                                "color": "#00508b",
                                "visibility": "off"
                            }
                        }, {
                            "featureType": "poi",
                            "elementType": "all",
                            "stylers": {
                                "visibility": "off"
                            }
                        }, {
                            "featureType": "green",
                            "elementType": "all",
                            "stylers": {
                                "color": "#056197",
                                "visibility": "off"
                            }
                        }, {
                            "featureType": "subway",
                            "elementType": "all",
                            "stylers": {
                                "visibility": "off"
                            }
                        }, {
                            "featureType": "manmade",
                            "elementType": "all",
                            "stylers": {
                                "visibility": "off"
                            }
                        }, {
                            "featureType": "local",
                            "elementType": "all",
                            "stylers": {
                                "visibility": "off"
                            }
                        }, {
                            "featureType": "arterial",
                            "elementType": "labels",
                            "stylers": {
                                "visibility": "off"
                            }
                        }, {
                            "featureType": "boundary",
                            "elementType": "geometry.fill",
                            "stylers": {
                                "color": "#029fd4"
                            }
                        }, {
                            "featureType": "building",
                            "elementType": "all",
                            "stylers": {
                                "color": "#1a5787"
                            }
                        }, {
                            "featureType": "label",
                            "elementType": "all",
                            "stylers": {
                                "visibility": "off"
                            }
                        }]
                }
            },
            series: [
                {//区域开始坐标位置
                    type: 'scatter',
                    symbolSize: 16,
                    coordinateSystem: 'bmap',
                    data: [],
                    itemStyle: {
                        normal: {
                            color: '#7FFF00'
                        }
                    }
                },
                {//预警
                    type: 'effectScatter',
                    coordinateSystem: 'bmap',
                    data: [],
                    symbolSize: 20,
                    showEffectOn: 'render',
                    rippleEffect: {
                        brushType: 'stroke'
                    },
                    hoverAnimation: true,
                    itemStyle: {
                        normal: {
                            color: 'yellow',
                            shadowBlur: 10,
                            shadowColor: '#333'
                        }
                    },
                    zlevel: 1
                },
                {//报警
                    type: 'effectScatter',
                    coordinateSystem: 'bmap',
                    data: [],
                    symbolSize: 20,
                    showEffectOn: 'render',
                    rippleEffect: {
                        brushType: 'stroke'
                    },
                    hoverAnimation: true,
                    itemStyle: {
                        normal: {
                            color: 'red',
                            shadowBlur: 10,
                            shadowColor: '#333'
                        }
                    },
                    zlevel: 1
                },
                {//系统报警
                    type: 'effectScatter',
                    coordinateSystem: 'bmap',
                    data: [],
                    symbolSize: 20,
                    showEffectOn: 'render',
                    rippleEffect: {
                        brushType: 'stroke'
                    },
                    hoverAnimation: true,
                    itemStyle: {
                        normal: {
                            color: 'pink',
                            shadowBlur: 10,
                            shadowColor: '#333'
                        }
                    },
                    zlevel: 1
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
                    data: data.zone
                },
                {
                    data: data.warn
                },
                {
                    data: data.alarm
                },
                {
                    data: data.sysAlarm
                }
            ]
        });
    };
};

//分区报警比例
Bswidget.prototype.FQBJBL = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    var selectNum = 0;
    var flag = true;
    var sbTimer = null;
    this.onShow = function () {
        myChart = echarts.init(self.container.get(0));
        var option = getPollingPieOption(getSize(self.container.get(0)));
        myChart.setOption(option);
        myChart.on('mouseover', function (params) {
            flag = false;
            myChart.setOption({
                series: [{
                        label: {
                            normal: {
                                show: true,
                                textStyle: {
                                    fontSize: '100%'
                                },
                                formatter: function () {
                                    return  params.name.split("：")[0] + '\n' + params.value + '\n' + params.percent + "%";
                                }
                            }
                        }
                    }
                ]
            });
            myChart.dispatchAction({
                type: 'highlight',
                dataIndex: params.dataIndex
            });
        });
        myChart.on('mouseout', function (params) {
            flag = true;
            myChart.setOption({
                series: [{
                        label: {
                            normal: {
                                show: false
                            }
                        }
                    }
                ]
            });
        });
    }
    this.onResize = function () {
        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
        myChart.setOption({
            series: [{
                    data: data.data
                }
            ]
        });
        if (sbTimer == null) {
            sbTimer = window.setInterval(function () {
                if (flag) {
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
                }
            }, 2000);
        }
    };
};

//设备报警比例
Bswidget.prototype.SBBJBL = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    var selectNum = 0;
    var flag = true;
    var sbTimer = null;
    this.onShow = function () {
        myChart = echarts.init(self.container.get(0));
        var option = getPollingPieOption2(getSize(self.container.get(0)));
        myChart.setOption(option);
        myChart.on('mouseover', function (params) {
            flag = false;
            myChart.setOption({
                series: [{
                        label: {
                            normal: {
                                show: true,
                                textStyle: {
                                    fontSize: '100%'
                                },
                                formatter: function () {
                                    return  params.name.split("：")[0] + '\n' + params.value + '\n' + params.percent + "%";
                                }
                            }
                        }
                    }
                ]
            });
            myChart.dispatchAction({
                type: 'highlight',
                dataIndex: params.dataIndex
            });
        });
        myChart.on('mouseout', function (params) {
            flag = true;
            myChart.setOption({
                series: [{
                        label: {
                            normal: {
                                show: false
                            }
                        }
                    }
                ]
            });
        });
    }
    this.onResize = function () {
        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据
        myChart.setOption({
            series: [{
                    data: data.data
                }
            ]
        });
        if (sbTimer == null) {
            sbTimer = window.setInterval(function () {
                if (flag) {
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
                }
            }, 2000);
        }
    };
};
//设备状态
Bswidget.prototype.SBZT = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    this.onShow = function () {
        myChart = echarts.init(self.container.get(0));
        var option = option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {// 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                show: true,
                y: 'bottom',
                bottom: '2%',
                textStyle: {
                    color: '#fff'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '18%',
                top: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                splitLine: {show: false}, //去除网格线
                axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#fff'
                    }
                }
            },
            yAxis: {
                type: 'category',
                axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#fff'
                    }
                },
                axisLabel: {
//                    rotate: 45, //倾斜度 -90 至 90 默认为0  
                    fontSize: '12'    // y轴字体大小
                }
            },
            series: [
                {
                    name: '在线',
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: {
                            color: '#2EC7C9'
                        }
                    },
                    label: {
                        normal: {
                            show: false,
                            position: 'insideRight'
                        }
                    }
                },
                {
                    name: '预警',
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: {
                            color: '#B6A2DE'
                        }
                    },
                    label: {
                        normal: {
                            show: false,
                            position: 'insideRight'
                        }
                    }
                },
                {
                    name: '报警',
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: {
                            color: '#5AB1EF'
                        }
                    },
                    label: {
                        normal: {
                            show: false,
                            position: 'insideRight'
                        }
                    }
                },
                {
                    name: '系统报警',
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: {
                            color: '#FFB980'
                        }
                    },
                    label: {
                        normal: {
                            show: false,
                            position: 'insideRight'
                        }
                    }
                }
            ]
        };
        myChart.setOption(option);
    }
    this.onResize = function () {
        myChart.resize();
    };
    this.onSetData = function (data) {//设置数据

        myChart.setOption({
            yAxis: [{
                    data: data.typeData
                }],
            series: [
                {
                    data: data.normal
                },
                {
                    data: data.preAlarm
                },
                {
                    data: data.alarm
                },
                {
                    data: data.sysAlarm
                }
            ]
        });
    };
};
//轮巡的列表
Bswidget.prototype.InfoList = function (widgetBean) {
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
        if (data != null && data.data.length > 0) {
            for (var i = 0; i < data.data.length; i++) {
                var ul_div = $("<div class=\"alarmItem\"></div>");
                childDiv.append(ul_div);
                var des = "<div class='info'>" + data.data[i].zoneStr + "</div>";
                ul_div.append(des + "<div class='info'>" + data.data[i].deviceName + "</div><div class='info'>" + data.data[i].alarmTypeDes + "</div><div class='time'>" + new Date(data.data[i].freshtime).format("yyyy-MM-dd hh:mm:ss") + "</div>");
            }
        } else {
            var ul_div = $("<div class=\"alarmItem\"></div>");
            childDiv.append(ul_div);
            ul_div.append("<span>暂无报警信息</span>");
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
        $(".l_widget_lunxun").bind("click", function () {
            window.open("../BSPServer/base/mainPage.jsp?id=JKZX_JKYBJ_BJLS");
        });
    };
};
Bswidget.prototype.CountLabel = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    this.onSetData = function (data) {//设置数据
        if (data != null) {
            this.container.html(data);
        }
    };
};
//frame
Bswidget.prototype.Frame = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var content = '<iframe style="height: 100%; width:100%" class="emapframe" width="100%" frameborder="0" border="0" marginwidth="0" marginheight="0" allowtransparency="yes" src="../MAServer/page/bgMapShow.jsp?themeCSS=blueopal"></iframe>';
    $(self.container).append(content);
    this.onResize = function (e) {
    };
};
Bswidget.prototype.TimeLabel = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    this.onSetData = function (data) {//设置数据
        if (data != null) {
            this.container.html(new Date(data).format("yyyy-MM-dd hh:mm:ss"));
        }
    };
};


//文字内嵌饼图
function  getPollingPieOption2() {
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} ({d}%)"
        },
        color: ['#85BED8', '#8292D5', '#DAB4E3', '#DBF7A2', '#86F9D9', '#E9D0E9', '#F2D5B9', '#FAFEE2', '#DDF6CD', '#C9B3E2'],
        series: [
            {
                name: '本月设备类型报警比例',
                type: 'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center',
                        color: '#fff'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '100%',
                            fontWeight: 'bold'
                        },
                        formatter: function (params) {
                            return  params.name.split("：")[0] + '\n' + params.value + '\n' + params.percent + "%";
                        }
                    }
                },
                data: []
            }
        ]
    };
    return option;
}
//文字内嵌饼图
function  getPollingPieOption() {
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} ({d}%)"
        },
        color: ['#85BED8', '#8292D5', '#DAB4E3', '#DBF7A2', '#86F9D9', '#E9D0E9', '#F2D5B9', '#FAFEE2', '#DDF6CD', '#C9B3E2'],
        series: [
            {
                name: '本月分区报警比例',
                type: 'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center',
                        color: '#fff'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '100%',
                            fontWeight: 'bold'
                        },
                        formatter: function (params) {
                            return  params.name.split("：")[0] + '\n' + params.value + '\n' + params.percent + "%";
                        }
                    }
                },
                data: [
                    {value: 10, name: 'rose1'},
                    {value: 5, name: 'rose2'},
                    {value: 15, name: 'rose3'},
                    {value: 25, name: 'rose4'},
                    {value: 20, name: 'rose5'},
                    {value: 35, name: 'rose6'},
                    {value: 30, name: 'rose7'},
                    {value: 40, name: 'rose8'}
                ]
            }
        ]
    };
    return option;
}
//滚动动画
function scrollList(obj) {
    //获得当前<li>的高度
    var scrollHeight = $(obj).children(".alarmItem").height();
    //滚动出一个<li>的高度
    $(obj).stop().animate({marginTop: -scrollHeight}, 600, function () {
        //动画结束后，将当前<ul>marginTop置为初始值0状态，再将第一个<li>拼接到末尾。
        $(this).css({marginTop: 0}).find(".alarmItem:first").appendTo(this);
    });
}

/*
 * 日期格式转化
 */
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    }
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};