            //产品销售额饼图(前十种)
        var myChart3 = echarts.init(document.getElementById('spxiaoshoue'));

           myChart3.showLoading();    //数据加载完之前先显示一段简单的loading动画
           var names3 = [];    //类别数组（用于存放饼图的类别）
           var echartData = [];
    $.ajax({
        type: 'post',
//        data: postData,
        url: '/dpzs/changXiaoMoneyList',//请求数据的地址
        dataType: "json",        //返回数据形式为json
        success: function (data) {
//                         请求成功时执行该函数内容，result即为服务器返回的json对象
                         $.each(data, function (index, item) {
                             names3.push(item.spmc);    //挨个取出类别并填入类别数组
                             echartData.push({
                                 value: item.ddspsl,
                                 name: item.spmc
                             });
                         });
                         console.log(echartData);
            myChart3.hideLoading();    //隐藏加载动画
                    var scale = 1;
                    var rich = {
                        yellow: {
                            color: "#ffc72b",
                            fontSize: 12 * scale,
                            padding: [5, 4],
                            align: 'center'
                        },
                        total: {
                            color: "#ffc72b",
                            fontSize: 27 * scale,
                            align: 'center'
                        },
                        white: {
                            color: "#fff",
                            align: 'center',
                            fontSize: 12 * scale,
                            padding: [8, 0]
                        },
                        blue: {
                            color: '#49dff0',
                            fontSize: 12 * scale,
                            align: 'center'
                        },
                        hr: {
                            borderColor: '#0b5263',
                            width: '100%',
                            borderWidth: 1,
                            height: 0,
                        }
                    }
            myChart3.setOption({        //加载数据图表
            backgroundColor:'#11183c',
             grid: {
                        left: '5%',
                        right: '5%',
                        top:'5%',
                        bottom: '1%',
                        containLabel: true
                    },
             title: {
                        text: '产品销售额前十',
                        textStyle:{
                        color:'#FFCC00'
                                  }
                     },
            legend: {
                selectedMode:false,
                formatter: function(name) {
                    var total = 0; //各科正确率总和
                    var averagePercent; //综合正确率
                    echartData.forEach(function(value, index, array) {
                        total += value.value;
                    });
                    return '{total|' + total + '}{white|元}';
                },
                data: [echartData[0].name],
                left: 'center',
                top: 'center',
                icon: 'none',
                align:'center',
                textStyle: {
                    color: "#fff",
                    fontSize: 18 * scale,
                    rich: rich
                },
            },
            series: [{
                name: '总考生数量',
                type: 'pie',
                radius: ['28%', '35%'],
                hoverAnimation: false,
                color: ['#c487ee', '#deb140', '#49dff0', '#034079', '#6f81da', '#00ffb4'],
                label: {
                    normal: {
                        formatter: function(params, ticket, callback) {
                            var total = 0; //考生总数量
                            var percent = 0; //考生占比
                            echartData.forEach(function(value, index, array) {
                                total += value.value;
                            });
                            percent = ((params.value / total) * 100).toFixed(1);
                            return '{white|' + params.name + '}\n{hr|}\n{yellow|' + params.value + '元/}{blue|' + percent + '%}';
                        },
                        rich: rich
                    },
                },
                labelLine: {
                    normal: {
                        length:40 * scale,
                        length2: 0,
                        lineStyle: {
                            color:'#a2c7f3'
                        }
                    }
                },
                data: echartData
            }]
            });
//            }
          },
        error: function (errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myChart3.hideLoading();
        }
    })