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
                    bottom: '10%',
                    left: '5%',
                    right: '5%',
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross'
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: dataAxis
                },
                yAxis: {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value} '
                    },
                    axisPointer: {
                        snap: true
                    }
                },
                series: [{
                    name: '可用房间数',
                    type: 'line',
                    smooth: true,
                    data: data,
                    lineStyle: {
                        color: {
                            type: 'linear',
                            x: 0,
                            y: 0,
                            x2: 0,
                            y2: 1,
                            colorStops: [{
                                offset: 0,
                                color: '#5FB878' // 0% 处的颜色
                            }, {
                                offset: 1,
                                color: '#1E9FFF' // 100% 处的颜色
                            }],
                            global: false // 缺省为 false
                        },
                        width: 2,
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