        //销售额折线图(1-12个月)
        var myChart2 = echarts.init(document.getElementById('xiaoshoue_12'));
        var fontColor = '#30eee9';
            myChart2.setOption({
                    backgroundColor:'#11183c',
                    grid: {
                        left: '3%',
                        right: '7%',
                        top:'20%',
                        bottom: '12%',
                        containLabel: true
                    },
                    title: {
                        text: '销售额折线图(1-12个月)',
                        textStyle:{
                            color:'#FFCC00'
                            }
                     },
                    tooltip : {
                        show: true,
                        trigger: 'item'
                    },
                    xAxis : [
                        {
                            type : 'category',
                            boundaryGap : false,
                            axisLabel:{
                                color: fontColor
                            },
                    axisLabel: {
                        textStyle: {
                            color: '#fff'
                        },
                        interval: 0,
                        rotate: -33,
                        margin: 15
                    },
                            axisLine:{
                                show:true,
                                lineStyle:{
                                    color:'#397cbc'
                                }
                            },
                            axisTick:{
                                show:false,
                            },
                            splitLine:{
                                show:false,
                                lineStyle:{
                                    color:'#195384'
                                }
                            },
                            data : []
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value',
                            name : '',
                            min:0,
                            max:15000,
                            axisLabel : {
                                formatter: '{value}',
                                textStyle:{
                                    color:'#2ad1d2'
                                }
                            },
                            axisLine:{
                                lineStyle:{
                                    color:'#27b4c2'
                                }
                            },
                            axisTick:{
                                show:false,
                            },
                            splitLine:{
                                show:true,
                                lineStyle:{
                                    color:'#11366e'
                                }
                            }
                        },

                    ],
                    series : [
                        {
                            name:'销售额',
                            areaStyle: {},
                            color: '#ffffff',
                            type:'line',
                            stack: '总量',
                            symbol:'circle',
                            symbolSize: 8,
                             lineStyle: {
                                 normal: {
                                     color: '#ea6f21',
                                     width: 3,
                                 },
                             },
                             areaStyle: {
                                 normal: {
                                     color: new echarts.graphic.LinearGradient(
                                         0,
                                         0,
                                         0,
                                         1,
                                         [{
                                                 offset: 0,
                                                 color: 'rgba(236, 169, 44, 1)',
                                             },
                                             {
                                                 offset: 1,
                                                 color: 'rgba(236, 169, 44,0)',
                                             },
                                         ],
                                         false
                                     ),
                                 },
                             },
                            markPoint:{
                                itemStyle:{
                                    normal:{
                                        color:'red'
                                    }
                                }
                            },
                            data:[]
                        }
                    ]
                });

         myChart2.showLoading();    //数据加载完之前先显示一段简单的loading动画

         var times=[];    //类别数组（实际用来盛放X轴坐标值）
         var mXiaoshoue=[];    //销量数组（实际用来盛放Y坐标值）

         $.ajax({
         type : "post",
         async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
         url : "/dpzs/monthXiaoShouECX",    //请求发送到TestServlet处
         data : {},
         dataType : "json",        //返回数据形式为json
         success : function(result) {
            var data = result.data;
             //请求成功时执行该函数内容，result即为服务器返回的json对象
             if (data) {
                    for(var i=0;i<data.length;i++){
                       times.push(data[i].times);    //挨个取出类别并填入类别数组
                     }
                    for(var i=0;i<data.length;i++){
                        mXiaoshoue.push(data[i].xsze);    //挨个取出销量并填入销量数组
                      }
                    myChart2.hideLoading();    //隐藏加载动画
                    myChart2.setOption({        //加载数据图表
                        xAxis: {
                            data: times
                        },
                        series: [{
                            // 根据名字对应到相应的系列
                            name: '销售额',
                            data: mXiaoshoue
                        }]
                    });
             }
        },
         error : function(errorMsg) {
             //请求失败时执行该函数
         alert("图表请求数据失败!");
         myChart2.hideLoading();
         }
    })

