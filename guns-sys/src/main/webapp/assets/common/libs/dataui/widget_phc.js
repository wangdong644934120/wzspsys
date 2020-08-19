
DefaultData.Liquidfill={
    // loadShow: true,
    data: [0.95, 0.7, 0.6, 0.5]
};

DefaultData.DataList={
    // loadShow: true,
    data: [
        {name:"市级正职",value:1},
        // {level:"市级副职",num:3},
        {name:"正局级",value:20},
        {name:"副局级",value:60},
        {name:"正科级",value:80},
        {name:"科员",value:2800}
    ]
};

DefaultData.RYDD = {
    // loadShow: true, //展现时需不需要加载默认数据
    data: {
        sf: ["市级正职", "市级副职", "正局级", "副局级", "正科级", "副科级", "科员"],
        jsl: [42, 30, 24, 18, 9, 9, 9],
        hsl: [40, 35, 24, 18, 9, 9, 10],
    }
};
DefaultData.CircleData={
    data:[]
};
DefaultData.GCTSFX = {
    // loadShow: true, //展现时需不需要加载默认数据
    data: {
        sl: [
            {value: 335, name: '办公'},
            {value: 310, name: '服务'},
            {value: 234, name: '设备'},
            {value: 135, name: '附属'},
            {value: 848, name: '业务'}
        ]
    }
};
DefaultData.BigMap = {
    // loadShow: true, //展现时需不需要加载默认数据
    data: {
        zztsg: [],
        cssf: [],
        zxg: [
            {
                id: "001",
                name: '高区市政大楼',
                value: [122.080753, 37.539297]
            }, {
                name: '会展中心',
                value: [122.149178, 37.470652]
            }, {
                name: '金沙滩社区办事处',
                value: [122.174808, 37.43251]
            }, {
                name: '皇冠社区办事处',
                value: [122.165767, 37.44808]
            }, {
                name: '高新花园办事处',
                value: [122.100131, 37.518273]
            },
            {
                name: '环翠楼办事处',
                value: [122.125867, 37.516967]
            },
            {
                name: '鲸园办事处',
                value: [122.128784, 37.508616]
            },
            {
                name: '竹岛办事处',
                value: [122.140426, 37.493372]
            },
        ]
    }
};

Bswidget.prototype.RYDD = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    this.onShow = function () {
        myChart = echarts.init(self.container.get(0));
        var legendArr = ['标准', '实际'];
        var option = {
            legend: {
                data: legendArr,
                textStyle: {
                    fontSize: "8px",
                    color: "#fff"
                },
                x: 'center',
            },
            grid: {
                left: '3%',
                right: '3%',
                top: '17%',
                bottom: '3%',
                containLabel: true
            },
            yAxis: {
                type: 'value',
                name: '平米',
                boundaryGap: [0, 0.01],
                axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#fff',
                    },
                },
                axisLabel: {
                    formatter: '{value}',
                }
            },
            xAxis: {
                type: 'category',
                axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#fff',
                    },
                },
                axisLabel: {
                    interval: 0
                },
                data: []
            },
            series: [{
                name: legendArr[0],
                type: 'bar',
                barCategoryGap: '40%',
                itemStyle: {
                    normal: {
                        barBorderRadius: 5,
                        color: new echarts.graphic.LinearGradient(0,
                            0, 0, 1, [{
                                offset: 0,
                                color: '#00FFE6'
                            }, {
                                offset: 1,
                                color: '#007CC6'
                            }]),
                    },
                },
                data: []
            }, {
                name: legendArr[1],
                type: 'bar',
                barCategoryGap: '40%',
                itemStyle: {
                    normal: {
                        barBorderRadius: 5,
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
                data: []
            }]
        };
        myChart.setOption(option);
    }
    this.onResize = function () {
        myChart.resize();
    };
    this.onSetData = function (data) {// 设置数据
        myChart.setOption({
            xAxis: [{
                data: data.sf
            }],
            series: [{
                data: data.jsl
            },{
                data: data.hsl
            }]
        });
    };
};

Bswidget.prototype.Liquidfill = function (widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    var myChart = null;
    this.onShow = function () {
        myChart = echarts.init(self.container.get(0));
        var option = {
            textStyle: {
                "fontSize": getSize(self.container.get(0), 2)
            },
            series: [{
                type: 'liquidFill',
                radius: '80%',
                data: []
            }]
        };
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
/**
 * 各级人员数量统计
 * @param widgetBean
 * @constructor
 */
Bswidget.prototype.DataList = function(widgetBean) {
    Bswidget.call(this, widgetBean);
    var self = this;
    this.onShow = function () {

    };
    this.onSetData = function(data) {// 设置数据
        this.container.empty();
        if (data != null) {
            for(var i=0;i<data.length;i++){
                var item=$("<div class='l-data-list'><a></a><span>"+data[i].name+"</span><span>"+data[i].value+"人</span></div>");
                this.container.append(item);
            }
        }
    };
};

// Bswidget.prototype.CircleData = function (widgetBean) {
//     Bswidget.call(this, widgetBean);
//     var self = this;
//     var myChart = null;
//     this.onShow = function () {
//         myChart = echarts.init(self.container.get(0), 'blue');
//         var dataStyle = {
//             normal: {
//                 label: {show:false},
//                 labelLine: {show:false}
//             }
//         };
//         var placeHolderStyle = {
//             normal : {
//                 color: 'rgba(0,0,0,0)',
//                 label: {show:false},
//                 labelLine: {show:false}
//             },
//             emphasis : {
//                 color: 'rgba(0,0,0,0)'
//             }
//         };
//         var option = {
//             title: {
//                 text: '各级人数统计',
//                 // subtext: 'From ExcelHome',
//                 // sublink: 'http://e.weibo.com/1341556070/AhQXtjbqh',
//                 x: 'center',
//                 y: 'center',
//                 itemGap: 20,
//                 textStyle : {
//                     color : 'rgba(30,144,255,0.8)',
//                     fontFamily : '微软雅黑',
//                     fontSize : 35,
//                     fontWeight : 'bolder'
//                 }
//             },
//             tooltip : {
//                 show: true,
//                 formatter: "{a} <br/>{b} : {c} ({d}%)"
//             },
//             legend: {
//                 orient : 'vertical',
//                 x : self.container.get(0).offsetWidth / 2,
//                 y : 45,
//                 itemGap:12,
//                 data:['正处级：20人','科员：3000人','市正职：1人']
//             },
//             series : [
//                 {
//                     name:'1',
//                     type:'pie',
//                     clockWise:false,
//                     radius : [125, 150],
//                     itemStyle : dataStyle,
//                     data:[
//                         {
//                             value:3000,
//                             name:'科员：3000人'
//                         },
//                         {
//                             value:100,
//                             name:'invisible',
//                             itemStyle : placeHolderStyle
//                         }
//
//                     ]
//                 },
//                 {
//                     name:'2',
//                     type:'pie',
//                     clockWise:false,
//                     radius : [100, 125],
//                     itemStyle : dataStyle,
//                     data:[
//                         {
//                             value:20,
//                             name:'正处级：20人'
//                         },
//                         {
//                             value:3080,
//                             name:'invisible',
//                             itemStyle : placeHolderStyle
//                         }
//                     ]
//                 },
//                 {
//                     name:'3',
//                     type:'pie',
//                     clockWise:false,
//                     radius : [75, 100],
//                     itemStyle : dataStyle,
//                     data:[
//                         {
//                             value:1,
//                             name:'市正职：1人'
//                         },
//                         {
//                             value:3099,
//                             name:'invisible',
//                             itemStyle : placeHolderStyle
//                         }
//                     ]
//                 }
//             ]
//         };
//         myChart.setOption(option);
//     };
//     this.onResize = function () {
//        myChart.resize();
//     };
//     this.onSetData = function (data) {//设置数据
//
//     };
// };

