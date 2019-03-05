layui.use(["element", "layer", "jquery", "form", "laydate", "slider"], function () {
	var element = layui.element;
	var layer = layui.layer;
	var $ = layui.jquery;
	var form = layui.form;
	var laydate = layui.laydate;
	var slider = layui.slider;

	//日期选择器
	laydate.render({
		elem: "#chosedate",
		type: "date",
		isInitValue: true,
		value: new Date()
	});
	// laydate.render({
	// 	elem: "#chosetime",
	// 	type: "time",
	// 	isInitValue: true,
	// 	format: "HH点mm分",
	// 	value: new Date()
	// });

	//跳出人员选择
	$(".btnAddPeople").on("click", function () {
		layer.open({
			type: 2,
			title: ["选择人员"],
			content: "meetAdd-people.html",
			area: ["411px", "98%"]
		});
	})


	//滑动滑块提示时间
	var ins1 = slider.render({
		elem: "#slideTest1", //绑定元素
		max: 288, //288*5 min = 一天, 12*5 min= 1h
		step: 1,
		range: true,
		value: [72, 84],
		//theme: '#1E9FFF',
		setTips: function (value) {
			return getTimeValue(value);
		},
		change: function (value) {
			// console.log(value); //动态获取滑块数值
			//do something
			value[0]=getTimeValue(value[0])
			value[1]=getTimeValue(value[1])
			// console.log("v0=" + value[0])
			// console.log("v1=" + value[1])
			$("#sTime").html(value[0])
			$("#eTime").html(value[1])
		}
	});

	function getTimeValue(value) {
		//自定义提示文本
		//以5分钟为间隔
		var h = parseInt(value / 12);
		var m = (value - 12 * h) * 5;
		var result,
			hour,
			min = "";
		if (h < 10) hour = "0" + h;
		else hour = h.toString();
		if (m < 10) min = "0" + m;
		else min = m.toString();
		result = hour + ":" + min;
		return result;
	}
	// ins1.setValue(20)

})