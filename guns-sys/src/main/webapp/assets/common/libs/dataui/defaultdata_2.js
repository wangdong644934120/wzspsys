/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
DefaultData = {
    Label: {
        style: {
            title: {//标题
                show: 0
            },
            text: {
                size: "30px"
            }
        }
    },
    Demo: {
        loadShow: true, //展现时需不需要加载默认数据
        data: [
            {value: 15, name: '在线'}, //, selected: true
            {value: 5, name: '离线'}
        ]
    },
    CountUp: {
        data: {
            startVal: 0,
            endVal: 36874,
            decimals: 0, //小数
            duration: 2//显示完成时间
        }
    },
    StateList: {
        data: [
            {type: "normal", value: "机房温度"},
            {type: "error", value: "机房温度"},
            {type: "warning", value: "机房温度"}
        ]
    },
    LunxunList: {
        data: {
            data: [
                {des: "红外报警", deviceName: "实-1F-南西2室", time: "2018-01-11 16:45:19", remark: "new"},
                {des: "红外报警", deviceName: "实-1F-南西2室", time: "2018-01-11 16:45:19", remark: ""},
                {des: "红外报警", deviceName: "实-1F-南西2室", time: "2018-01-11 16:45:19", remark: ""},
                {des: "红外报警", deviceName: "实-1F-南西2室", time: "2018-01-11 16:45:19", remark: ""},
                {des: "红外报警", deviceName: "实-1F-南西2室", time: "2018-01-11 16:45:19", remark: ""},
                {des: "红外报警", deviceName: "实-1F-南西2室", time: "2018-01-11 16:45:19", remark: ""},
                {des: "红外报警", deviceName: "实-1F-南西2室", time: "2018-01-11 16:45:19", remark: ""}
            ],
            waitTime: 3000
        }
    },
    LunBoLabel: {
        data: {
            data: [
                {des: "红外报警", sourse: "实-1F-南西2室", time: "2018-01-11 16:45:19", remark: "new"},
                {des: "红外报警", sourse: "实-1F-南西2室", time: "2018-01-11 16:45:19", remark: ""},
                {des: "红外报警", sourse: "实-1F-南西2室", time: "2018-01-11 16:45:19", remark: ""},
                {des: "红外报警", sourse: "实-1F-南西2室", time: "2018-01-11 16:45:19", remark: ""},
                {des: "红外报警", sourse: "实-1F-南西2室", time: "2018-01-11 16:45:19", remark: ""},
                {des: "红外报警", sourse: "实-1F-南西2室", time: "2018-01-11 16:45:19", remark: ""},
                {des: "红外报警", sourse: "实-1F-南西2室", time: "2018-01-11 16:45:19", remark: ""}
            ],
            waitTime: 3000
        }
    },
    HXZZT: {
        data: {
            zt: ["在库", "借阅", "待借阅"],
            lb: ["1号库房", "2号库房"],
            color: ["LimeGreen", "#0000FF", "DarkTurquoise"],
            info: [[2, 0], [3, 5], [5, 8]]//不同zt下各个lb的值,即：待借阅状态下1号2号库房的数据分别是2,0
        }
    },
    TransPie: {
        data: {
            name: "在线状态",
            data: [
                {value: 335, name: '在线'},
                {value: 310, name: '离线'}
            ]
        }
    },
    DevicePie: {
        data: {
            name: "在线状态",
            image: "lib/bigscreen/images/dangan.png",
            data: [
                {value: 335, name: '在线'},
                {value: 310, name: '离线'}
            ]
        }
    },
    DoublePie: {
        data: {
            name0: '一键报警',
            pie0: [
                {value: 335, name: '在线'},
                {value: 310, name: '离线'}
            ],
            name1: '红外报警',
            pie1: [
                {value: 200, name: '在线'},
                {value: 310, name: '离线'}
            ]
        }
    },
    PollingPie: {
        data: {
            name: "档案状态",
            data: [//在库、待上架、待借阅、待销毁、待拆盒、借阅、销毁、拆盒和异常下架
                {value: 335, name: '待上架'},
                {value: 310, name: '在库'},
                {value: 234, name: '借阅'},
                {value: 234, name: '销毁'},
                {value: 310, name: '错序'}
            ]
        }
    },
    PersonFlow: {
        style: {
            title: {//标题
                text: '人流量'
            }
        },
        data: {
            dateList: [2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018],
            valueList: [2236, 2016, 2145, 4456, 3421, 3387, 6614, 2558]
        }
    },
    Liquidfill: {
        data: [0.6, 0.5, 0.4, 0.3]
    },
    AlarmType: {
        loadShow: true, //展现时需不需要加载默认数据
        style: {
            title: {//标题
                text: '报警类型分布'
            }
        },
        data: {
            indicator: [
                {text: '门禁'},
                {text: '入侵'},
                {text: '一键'},
                {text: '视频'},
                {text: '楼宇'}
            ],
            alarm: [95, 80, 95, 90, 93], //全部报警
            dealAlarm: [75, 70, 70, 65, 65]//已处理报警
        }
    },
    DataDistribute: {
        loadShow: false, //展现时需不需要加载默认数据
        style: {
            title: {//标题
                text: '分布图'
            }
        },
        data: {
            name: "门类分布图",
            indicator: [
                {text: '传统文书'},
                {text: '新文书'},
                {text: '图片'},
                {text: '视频'},
                {text: '录音'}
            ],
            value: [95, 80, 95, 90, 93]
        }
    },
    RLTJ: {
        style: {
            title: {//标题
                text: '容量统计'
            }
        },
        data: {
            all: 50000,
            allName: "总盒数",
            use: 36874,
            useName: "已用盒数"
        }
    },
    KFHK: {
        style: {
            title: {//标题
                text: '库房环控'
            }
        },
        data: {
            device: {
//                type: ['空调', '加湿机', '风机', '消毒机', '一体机', '空气净化机', '灯光', '电源'],
                open: [1, 2, 2, 1, 4, 3, 1, 2],
                close: [5, 4, 4, 5, 1, 3, 5, 4],
                error: [0, 0, 0, 0, 1, 0, 0, 0]
            },
            humiture: {
//                type: ['库房1', '库房2', '库房3', '库房4', '库房5', '库房6', '库房7', '库房8', '库房9', '库房10', '库房11', '库房12', '库房13', '库房14', '库房15', '库房16'],
//                temperature: [8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23],
//                humidity: [8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23],
                type: ['库房1', '库房2', '库房3', '库房4', '库房5', '库房6', '库房7', '库房8', '库房9', '库房10', '库房11', '库房12', '库房13', '库房14', '库房15', '库房16'],
                temperature: [8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 50],
                temperatureYJ: [{yAxis: 14}, {yAxis: 24}],
                temperatureAbnormal: [{value: '50%', xAxis: 15, yAxis: 50}],
                humidity: [8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 60],
                humidityYJ: [{yAxis: 45}, {yAxis: 60}],
                humidityAbnormal: [{value: 60, xAxis: 15, yAxis: 60}]

            }
        }
    },
    DangAnInfo: {
        style: {
            title: {//标题
                text: '档案信息'
            }
        },
        data: {
            data1: {
                name: "",
                data: [{
                        value: 335,
                        name: '在馆档案',
//                        itemStyle: {
//                            normal: {color: '#4DD3E2'}
//                        }
                    }, {
                        value: 310,
                        name: '借出档案',
//                        itemStyle: {
//                            normal: {color: 'transparent'}
//                        }
                    }
                ]
            },
            data2: {
                name: "档案数量",
                image: "lib/bigscreen/images/dangan.png",
                data: [
                    {value: 335, name: '已存档案'},
                    {value: 310, name: '剩余档案位'}
                ]
            },
            data3: {
                name: "档案门类",
                data: [//传统文书、新文书、录音、录像、全宗卷、照片
                    {value: 335, name: '传统文书'},
                    {value: 310, name: '新文书'},
                    {value: 234, name: '录音'},
                    {value: 234, name: '录像'},
                    {value: 310, name: '全宗卷'},
                    {value: 310, name: '照片'}
                ]
            },
            data4: {
                name: "档案状态",
                data: [//在库、待上架、待借阅、待销毁、待拆盒、借阅、销毁、拆盒和异常下架
                    {value: 335, name: '待上架'},
                    {value: 310, name: '在库'},
                    {value: 234, name: '借阅'},
                    {value: 234, name: '销毁'},
                    {value: 310, name: '错序'}
                ]
            }
        }
    },
    LYDevice: {
        style: {
            title: {//标题
                text: '楼宇自控'
            }
        },
        data: {
            device: {
//                type: ['暖通空调', '给排水'],
                open: [1, 2],
                close: [5, 4],
                error: [0, 0]
            },
            state: [
                {type: "normal", value: "楼宇温度：27℃"},
                {type: "error", value: "楼宇湿度：55%"},
                {type: "warning", value: "水箱液位：正常"}
            ]
        }
    },
    JFHK: {
        style: {
            title: {//标题
                text: '机房环控'
            }
        },
        data: {
            ups: [0.6, 0.5, 0.4, 0.3],
            state: [
                {type: "normal", value: "机房温度：27℃"},
                {type: "error", value: "机房温度：55%"},
                {type: "warning", value: "机房电压：250V"},
                {type: "warning", value: "机房电流：10A"}
            ]
        }
    },
    BigMap: {
        loadShow: true, //展现时需不需要加载默认数据
        data: {
            zztsg: [
                {
                    name: '威海自助图书柜1',
                    value: [122.055799, 37.503934]
                },
                {
                    name: '威海自助图书柜2',
                    value: [122.077358, 37.51424]
                },
                {
                    name: '威海自助图书柜3',
                    value: [122.060398, 37.52191]
                },
                {
                    name: '威海自助图书柜4',
                    value: [122.161224, 37.53124]
                },
                {
                    name: '威海自助图书柜5',
                    value: [122.142036, 37.496834]
                },
                {
                    name: '威海自助图书柜6',
                    value: [122.18767, 37.385206]
                },
                {
                    name: '威海自助图书柜7',
                    value: [122.112285, 37.409173]
                },
                {
                    name: '威海自助图书柜8',
                    value: [122.063848, 37.198652]
                },
                {
                    name: '威海自助图书柜9',
                    value: [121.997302, 37.185313]
                },
                {
                    name: '威海自助图书柜10',
                    value: [122.075203, 37.238655]
                },
                {
                    name: '威海自助图书柜11',
                    value: [122.127951, 37.20969]
                },
                {
                    name: '威海自助图书柜12',
                    value: [122.107973, 37.267954]
                },
                {
                    name: '威海自助图书柜13',
                    value: [122.197157, 37.084043]
                },
                {
                    name: '威海自助图书柜14',
                    value: [122.270746, 37.195433]
                },
                {
                    name: '威海自助图书柜15',
                    value: [122.572145, 37.168521]
                },
                {
                    name: '威海自助图书柜16',
                    value: [122.573726, 37.191983]
                },
                {
                    name: '威海自助图书柜17',
                    value: [122.494532, 37.170131]
                },
                {
                    name: '威海自助图书柜18',
                    value: [122.480159, 37.171972]
                },
                {
                    name: '威海自助图书柜19',
                    value: [122.466648, 37.178729]
                },
                {
                    name: '威海自助图书柜20',
                    value: [122.42238, 37.129373]
                },
                {
                    name: '威海自助图书柜21',
                    value: [121.533345, 36.920025]
                },
                {
                    name: '威海自助图书柜22',
                    value: [121.457887, 36.890359]
                },
                {
                    name: '威海自助图书柜23',
                    value: [121.553467, 36.878697]
                },
                {
                    name: '威海自助图书柜24',
                    value: [121.512791, 37.002266]
                },
                {
                    name: '威海自助图书柜25',
                    value: [121.533991, 36.847745]
                },
            ],
            cssf: [
                {
                    name: '高区城市书房',
                    value: [122.080753, 37.539297]
                },
                {
                    name: '高新花园城市书房',
                    value: [122.100131, 37.518273]
                },
                {
                    name: '环翠楼城市书房',
                    value: [122.125867, 37.516967]
                },
                {
                    name: '鲸园城市书房',
                    value: [122.128784, 37.508616]
                },
                {
                    name: '竹岛城市书房',
                    value: [122.140426, 37.493372]
                },
                {
                    name: '皇冠城市书房',
                    value: [122.165767, 37.44808]
                },
                {
                    name: '金沙滩城市书房',
                    value: [122.174808, 37.43251]
                },
            ],
            zxg: [
                {
                    name: '中心馆',
                    value: [122.149178, 37.470652]
                }
            ]
        }
    },
    JYGL: {
        loadShow: false, //展现时需不需要加载默认数据
        data: {
            month: ["一月", "二月", "三月", "四月", "五月"],
            jsl: [290, 660, 770, 460, 860],
            hsl: [280, 670, 350, 940, 580],
            rll: [12, 20, 50, 35, 40]
        }
    },
    ZRTS: {
        loadShow: false, //展现时需不需要加载默认数据
        data: {
            bookname: ["十万个为什么", "三国演义", "西游记", "水浒传", "红楼梦"],
            baifenbi: [500, 600, 700, 800, 900],
            greybar: [1000, 1000, 1000, 1000, 1000],
            paiming: [5, 4, 3, 2, 1]
        }
    },
    SFSJ: {
        loadShow: false, //展现时需不需要加载默认数据
        data: {
            sf: ["竹岛", "环翠楼", "高区", "豪业圣迪"],
            jsl: [90, 60, 70, 60],
            hsl: [80, 70, 50, 40],
        }
    },
    SFSJ2: {
        loadShow: false, //展现时需不需要加载默认数据
        data: {
            sf: ["金沙滩", "乳山", "荣成", "威高"],
            jsl: [50, 80, 10, 20],
            hsl: [30, 70, 60, 80],
        }
    },
    GCTSFX: {
        loadShow: false, //展现时需不需要加载默认数据
        data: {
            sl: [
                {value: 335, name: '宗教'},
                {value: 310, name: '政治'},
                {value: 234, name: '经济'},
                {value: 135, name: '军事'},
                {value: 1048, name: '历史'},
                {value: 251, name: '数学'},
                {value: 147, name: '少儿'},
                {value: 102, name: '医药'}
            ]
        }
    },
    DZJYFX: {
        loadShow: false, //展现时需不需要加载默认数据
        data: {
            lb: ["宗教", "政治", "经济", "军事", "历史", "数学", "少儿", "医药", ],
            sl: [
                1335,
                1310,
                1234,
                1135,
                1048,
                1251,
                1147,
                1102,
            ]
        }
    },
    ZZTTJ: {
        loadShow: false, //展现时需不需要加载默认数据
        data: {
            ydw: "本", //y轴单位
            lb: ["宗教", "政治", "经济", "军事", "历史", "数学", "少儿", "医药"],
            sl: [
                1335,
                1310,
                1234,
                1135,
                1048,
                1251,
                1147,
                1102,
            ]
        }
    },
    DZXBFX: {
        loadShow: false, //展现时需不需要加载默认数据
        data: {
            name: "读者年龄分析",
            data: [
                {value: 100, name: '6-12岁'},
                {value: 300, name: '12-18岁'},
                {value: 534, name: '18-30岁'},
                {value: 234, name: '30-60岁'},
                {value: 210, name: '60岁以上'}
            ]
        }
    },
    DZXBBL: {
        loadShow: false, //展现时需不需要加载默认数据
        data: {
            name: "读者性别比例",
            data: [
                {value: 655, name: '男'},
                {value: 310, name: '女'}
            ],
        }
    },
    DZFX: {
        loadShow: false, //展现时需不需要加载默认数据
        data: {
            name0: "读者性别比例",
            data0: [
                {value: 655, name: '男'},
                {value: 310, name: '女'}
            ],
            name1: "读者年龄分析",
            data1: [//在库、待上架、待借阅、待销毁、待拆盒、借阅、销毁、拆盒和异常下架
                {value: 100, name: '6-12岁'},
                {value: 300, name: '12-18岁'},
                {value: 534, name: '18-30岁'},
                {value: 234, name: '30-60岁'},
                {value: 210, name: '60岁以上'}
            ]
        }
    },
    Title: {
        style: {
            title: {//标题
                show: 0
            },
            text: {
                size: "30px"
            }
        }
    },
    Number: {
        style: {
            title: {//标题
                show: 0
            },
            text: {
                size: "30px"
            }
        }
    }, WdCount: {
        style: {
            title: {//标题
                text: '密集架温湿度'
            }
        },
        data: {
            humiture: {
                type: ['库房1', '库房2', '库房3', '库房4', '库房5', '库房6', '库房7', '库房8', '库房9', '库房10', '库房11', '库房12', '库房13', '库房14', '库房15', '库房16'],
                temperature: [8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 50],
                temperatureYJ: [{yAxis: 14}, {yAxis: 24}],
                temperatureAbnormal: [{value: '50%', xAxis: 15, yAxis: 50}],
                humidity: [8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 60],
                humidityYJ: [{yAxis: 45}, {yAxis: 60}],
                humidityAbnormal: [{value: 60, xAxis: 15, yAxis: 60}]
            }
        }
    },
    TJLine: {
        loadShow: false, //展现时需不需要加载默认数据
        style: {
            title: {//标题
                text: '库房温湿度'
            }
        }, data: {
//            
            time: ['0点', '1点', '2点', '3点', '4点', '5点', '6点', '7点', '8点', '9点', '10点', '11点', '12点', '13点', '14点', '15点'],
            wd: [23, 14, 18, 22, 24, 23, 21, 15, 28, 22, 24, 23, 20, 21, 22, 0],
            temperatureYJ: [{yAxis: 14}, {yAxis: 24}],
            temperatureAbnormal: [{value: 28, xAxis: 8, yAxis: 28}],
//            wd2: [45, 46, 48, 44, 50, 57, 53, 55, 52, 60, 64, 58, 57, 53, 54, 50],
//            temperatureYJ2: [{yAxis: 14}, {yAxis: 24}],
//            temperatureAbnormal2: [{value: 50, xAxis: 15, yAxis: 50}],
            sd: [45, 46, 48, 44, 50, 57, 53, 55, 52, 60, 64, 58, 57, 53, 54, 0],
            humidityYJ: [{yAxis: 45}, {yAxis: 60}],
            humidityAbnormal: [{value: 64, xAxis: 10, yAxis: 64}]

        },
        series: [
            {
                name: '温度',
                type: 'line',
                data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
            },
            {
                name: '湿度',
                type: 'line',
                data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
                yAxisIndex: 1
            }
        ]
    },
    AllWSD: {
        loadShow: false, //展现时需不需要加载默认数据
        style: {
            title: {//标题
                text: '全部库房温湿度'
            }
        }, data: {
//            
            type: "湿度",
            axisLabel: '{value} %',
            time: ['0点', '1点', '2点', '3点', '4点', '5点', '6点', '7点', '8点', '9点', '10点', '11点', '12点', '13点', '14点', '15点'],
            max: 50,
//            sdMax: 100,
            series: [
                {
                    type: 'line',
                    markLine: {
                        data: [{yAxis: 14}, {yAxis: 24}
                        ],
                        lineStyle: {
                            color: "#408829",
                            type: 'dashed'
                        },
                        label: {
                            position: 'start'
                        }
                    }
                },
                {
                    type: 'line',
                    markLine: {
                        data: [{yAxis: 45}, {yAxis: 60}
                        ],
                        lineStyle: {
                            type: 'solid',
                            color: "#159CF6"
                        }
                    }
                },
                {
                    name: '湿度',
                    data: [8, 9, 50, 11, 12, 13, 50, 15, 16, 17, 18, 19, 84, 21, 22, 60],
                    type: 'line',
                    markPoint: {
                        data: [{value: 50, xAxis: 15, yAxis: 50}
                        ], itemStyle: {
                            color: 'red'
                        }
                    }
                }
                , {
                    name: '高度',
                    data: [8, 9, 10, 11, 12, 13, 45, 15, 16, 17, 18, 46, 20, 21, 22, 60],
                    type: 'line'
                }, {
                    name: 'aa',
                    data: [8, 9, 10, 11, 12, 13, 14, 96, 16, 17, 18, 19, 20, 21, 22, 60],
                    type: 'line'
                }],
            legend: [{
                    name: '温度'

                },
                {
                    name: '湿度'

                },
                {
                    name: '高度'

                },
                {
                    name: 'aa'

                }]
        }
    },WSDEvery  : {
        loadShow: false, //展现时需不需要加载默认数据
        style: {
            title: {//标题
                text: '1号库房温湿度'
            }
        }, data: {
          wd:32.4,
          sd:77.6,
          wdmin:0.3,
          sdmin:0.3,
          wdmax:0.8,
          sdmax:0.7,
          deviceID:0001
        }
    }
};

