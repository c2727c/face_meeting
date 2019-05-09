function getEchart(dateInString) {
    var url = path + "/meeting/dailyAvilable.do";
    // console.log("请求controller的url是:" + url)
    // console.log(dateInString)
    $.ajax({
        url: url,
        type: "post",
        data: {
            'dateInString': dateInString,
        },
        dataType: "json",
        success: function (data) {
            var dataAxis = data.data.dataAxis
            var data = data.data.data
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            // 指定图表的配置项和数据
            var option = {
                legend: {
                    show: false,
                },
                grid: {
                    top: '5%',
                    bottom: '15%',
                    left: '5%',
                    right: '5%',
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        lable: {
                        	precision: '0',
                        }
                    },
                    formatter: function (params) {
                        var result = ''
                        var dotHtml = '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:#009688"></span>'
                        params.forEach(function (item) {
                            result += item.axisValue + "</br>" + dotHtml + '可用房间数：' + item.data
                        })
                        return result
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: dataAxis
                },
                yAxis: {
                    type: 'value',
                    minInterval: 1,
                    axisPointer: {
                        snap: true
                    },
                    axisLine: { //axisLine决定是否显示坐标刻度
                        show: false,
                    },
                    axisLabel: { //决定是否显示数据
                        show: false,
                        formatter: function (v) {
                            return parseInt(v) //表示整数。其他数值类型以此类推
                        }
                    },
                    axisTick: {
                        show: false,
                    },
                    splitLine: {
                        show: false,
                    }

                },
                series: [{
                    name: '可用房间数',
                    type: 'line',
                    smooth: true,
                    data: data,
                    symbol: 'none',
                    lineStyle: {
                        color: {
                            type: 'linear',
                            x: 0,
                            y: 0,
                            x2: 0,
                            y2: 1,
                            colorStops: [{
                                offset: 0,
                                color: '#FF5722' // 0% 处的颜色
                            }, {
                                offset: 1,
                                color: '#009688' // 100% 处的颜色
                            }],
                            global: false // 缺省为 false
                        },
                        width: 0,
                    },
                    areaStyle: {
                        color: {
                            type: 'linear',
                            x: 0,
                            y: 0,
                            x2: 0,
                            y2: 1,
                            colorStops: [{
                                offset: 0,
                                color: '#48D1CC' // 0% 处的颜色
                            }, {
                                offset: 1,
                                color: '#F0FFFF' // 100% 处的颜色
                            }],
                            global: false // 缺省为 false
                        }
                    },
                }]

            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            //根据窗口的大小变动图表 --- 重点
            window.onresize = function () {
                myChart.resize();
                //myChart1.resize();    //若有多个图表变动，可多写
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("ajax请求失败");
        }
    });

}


$(".echartButton").click(function () {
    $(".echart").slideToggle();
})