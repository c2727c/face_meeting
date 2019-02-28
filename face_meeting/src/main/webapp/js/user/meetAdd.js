layui.use(["element", "layer", "jquery", "form", "laydate"], function () {
	var element = layui.element;
	var layer = layui.layer;
	var $ = layui.jquery;
	var form = layui.form;
	var laydate = layui.laydate;

	//日期选择器
	laydate.render({
		elem: "#chosedate",
		type: "date",
		isInitValue: true,
		value: new Date()
	});
	laydate.render({
		elem: "#chosetime",
		type: "time",
		isInitValue: true,
		format: "HH点mm分",
		value: new Date()
	});




})