     var myChart = echarts.init(document.getElementById('xiaoshoue_30'));
         // 显示标题，图例和空的坐标轴
         myChart.setOption({
              backgroundColor:'#11183c',
                    grid: {
                        left: '2%',
                        right: '6%',
                        top:'17%',
                        bottom: '15%',
                        containLabel: true
                    },
              title: {
                      text: '销售额折线图(30天)',
                        textStyle:{
                            color:'#FFCC00'
                            }
                      },
              xAxis: {
                      type: 'category',
                      boundaryGap: false,
        axisLabel: {
            textStyle: {
                color: '#fff'
            },
            interval: 0,
            rotate: -40,
            margin: 15
        },
                            axisLine:{
                                show:true,
                                lineStyle:{
                                    color:'#ffffff'
                                }
                              },
                      data: []
                      },
              yAxis: {
                      type: 'value',
                      color: '#ffffff',
                            axisLabel : {
                                formatter: '{value}',
                                textStyle:{
                                    color:'#ffffff'
                                }
                            },
                      },
              series: [{
                      data: [],
                      type: 'line',
                      areaStyle: {},
                      color: '#FFCC00'
                      }]
        });

//         myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画

         var names=[];    //类别数组（实际用来盛放X轴坐标值）
         var nums=[];    //销量数组（实际用来盛放Y坐标值）

         $.ajax({
         type : "post",
         async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
         url : "/dpzs/lastThirtyDays",    //请求发送到TestServlet处
         data : {},
         dataType : "json",        //返回数据形式为json
         success : function(result) {
            var data = result.data;
             //请求成功时执行该函数内容，result即为服务器返回的json对象
             if (data) {
                    for(var i=0;i<data.length;i++){
                       names.push(data[i].times);    //挨个取出类别并填入类别数组
                     }
                    for(var i=0;i<data.length;i++){
                        nums.push(data[i].xsze);    //挨个取出销量并填入销量数组
                      }
                    myChart.hideLoading();    //隐藏加载动画
                    myChart.setOption({        //加载数据图表
                        xAxis: {
                            data: names
                        },
                        series: [{
                            // 根据名字对应到相应的系列
                            name: '销售额',
                            data: nums
                        }]
                    });

             }

        },
         error : function(errorMsg) {
             //请求失败时执行该函数
         alert("图表请求数据失败!");
         myChart.hideLoading();
         }
    })

//            // 动态改变图表1数据
//            setInterval(function () {
//                for (var i = 0; i < mData.length; i++) {
//                    mData[i] += (Math.random() * 50 - 25);
//                    if (mData[i] < 0) {
//                        mData[i] = 0;
//                    }
//                }
//                myCharts.setOption({
//                    series: [{
//                        data: mData
//                    }]
//                });
//            }, 1000);

