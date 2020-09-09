             //二级学院消费柱状图
             var myChart6 = echarts.init(document.getElementById('erjixueyuan'));
             myChart6.setOption({
				backgroundColor:'#11183c',
                title: {
                        text: '二级学院消费柱状图',
                     },
				grid: {
					top: '40',
					right: '20',
					left: '50',
					bottom: '28' //图表尺寸大小
				},
				xAxis: [{
					type: 'category',
					color: '#59588D',
					data: [],
					axisLabel: {
						margin:7,
						color: '#999',
						textStyle: {
							fontSize: 8
						},
					},
					axisLine: {
						lineStyle: {
							color: 'rgba(107,107,107,0.37)',
						}
					},
					axisTick: {
						show: false
					},
				}],
				yAxis: [{
					axisLabel: {
						formatter: '{value}',
						color: '#999',
						textStyle: {
							fontSize: 12
						},
					},
					axisLine: {
						lineStyle: {
							color: 'rgba(107,107,107,0.37)',
						}
					},
					axisTick: {
						show: false
					}

				}],
				series: [{
					type: 'bar',
					data: [],
					barWidth: '16px',
					itemStyle: {
						normal: {
							color: function(params) { //展示正值的柱子，负数设为透明
								let colorArr = params.value > 0 ? ['#55d1ff', '#2d82ff'] : ['rgba(0,0,0,0)', 'rgba(0,0,0,0)']
								return new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
									offset: 0,
									color: colorArr[0] // 0% 处的颜色
								}, {
									offset: 1,
									color: colorArr[1] // 100% 处的颜色
								}], false)
							},
							barBorderRadius: [30, 30, 0, 0] //圆角大小
						},
					},
					label: {
						normal: {
							show: true,
							fontSize: 13,
							fontWeight: 'bold',
							color: '#333',
							position: 'top',
						}
					}
				}]
			});

			         myChart6.showLoading();    //数据加载完之前先显示一段简单的loading动画

                     var depts=[];    //类别数组（实际用来盛放X轴坐标值）
                     var dXiaoshoue=[];    //销量数组（实际用来盛放Y坐标值）

                     $.ajax({
                     type : "post",
                     async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                     url : "/dpzs/deptXiaoShouECX",    //请求发送到TestServlet处
                     data : {},
                     dataType : "json",        //返回数据形式为json
                     success : function(result) {
                        var data = result.data;
                         //请求成功时执行该函数内容，result即为服务器返回的json对象
                         if (data) {
                                for(var i=0;i<data.length;i++){
                                   depts.push(data[i].fullName);    //挨个取出类别并填入类别数组
                                 }
                                for(var i=0;i<data.length;i++){
                                    dXiaoshoue.push(data[i].xsze);    //挨个取出销量并填入销量数组
                                  }
                                myChart6.hideLoading();    //隐藏加载动画
                                myChart6.setOption({        //加载数据图表
                                    xAxis: {
                                        data: depts
                                    },
                                    series: [{
                                        // 根据名字对应到相应的系列
                                        name: '销售额',
                                        data: dXiaoshoue
                                    }]
                                });

                         }

                    },
                     error : function(errorMsg) {
                         //请求失败时执行该函数
                     alert("图表请求数据失败!");
                     myChart6.hideLoading();
                     }
                })

