var mTitle = '吃饭'
var mInfo = '无'
var mSize = '20'
var mSpan = ''
var pId_FQ = 'user01'
var rId = 'null'
var startDate = '2019-03-10'
var startTime = '18:00'
var endTime = '19:00'
var attendList = ''


layui.use(["element", "layer", "jquery", "form", "laydate", "slider"], function () {
	var element = layui.element;
	var layer = layui.layer;
	var $ = layui.jquery;
	var form = layui.form;
	var laydate = layui.laydate;
	var slider = layui.slider;

	//跳出人员选择
	$(".btnAddPeople").on("click", function () {
		layer.open({
			type: 2,
			title: ["选择人员"],
			content: "meetAdd-people.html",
			area: ["411px", "98%"],
			skin: 'layui-layer-molv',
			cancel: function (index, layero) {
				layer.confirm('选择完成？', {
					btn: ['保存离开', '继续选择'], //按钮
					title: '提示'
				}, function (index1) {
					var body = layer.getChildFrame('body', index);
					var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
					attendList = iframeWin.getAttend();
					layer.close(index)
					layer.close(index1)
					getPersonList(attendList)
				}, function (index1) {
					layer.close(index1)
				});
				return false;
			},
		});
	})

	//日期选择器
	laydate.render({
		elem: "#chosedate",
		type: "date",
		isInitValue: true,
		value: new Date(),
		done: function (value, date) {
			// console.log("done:")
			// console.log(value); //得到日期生成的值，如：2017-08-18
			// console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
			startDate = value;
			getEchart(startDate);
			getRoomList();
		}
	});
	//第一次加载图表
	startDate = $(".startDate").val()
	getEchart(startDate)

	//初始化时间（滑块的两个指针的数据）
	function initMytime() {
		var myDate = new Date();
		var myHour = myDate.getHours(); //获取当前小时数(0-23)
		var myMin = myDate.getMinutes(); //获取当前分钟数(0-59)
		var value = myHour * 12 + Math.ceil(myMin / 5)
		return value;
	}
	//得到初始时间，当前时间10分钟之后为开始时间
	var nowTime = initMytime();
	var nowTimeS = nowTime + 2;
	var nowTimeE = nowTime + 14;
	if (nowTimeS < 288 && nowTimeE >= 288) {
		nowTimeE = 287;
	} else if (nowTimeS >= 288 && nowTimeE >= 288) {
		nowTimeS = 287;
		nowTimeE = 287;
	}
	$("#sTime").html(getTimeValue(nowTimeS))
	$("#eTime").html(getTimeValue(nowTimeE))
	getRoomList();

	//滑动滑块提示时间
	var ins1 = slider.render({
		elem: "#slideTest1", //绑定元素
		max: 287, //288*5 min = 一天, 12*5 min= 1h
		step: 1,
		range: true,
		value: [nowTimeS, nowTimeE],
		//theme: '#1E9FFF',
		setTips: function (value) {
			return getTimeValue(value);
		},
		change: function (value) {
			// console.log(value); //动态获取滑块数值
			//do something
			value[0] = getTimeValue(value[0])
			value[1] = getTimeValue(value[1])
			// console.log("v0=" + value[0])
			// console.log("v1=" + value[1])
			$("#sTime").html(value[0])
			$("#eTime").html(value[1])
			startTime = value[0];
			endTime = value[1];
			getRoomList();
		}
	});

	//点击按钮创建会议
	form.on('submit(meetAdd)', function (data) {
		// console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
		// console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
		// console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
		mTitle = data.field.mTitle;
		mInfo = data.field.mInfo;
		rId = data.field.rId;
		mSize = $(".mSize").val()
		startDate = $(".startDate").val()
		startTime = $(".startTime").text()
		endTime = $(".endTime").text()
		pId_FQ = userId
		mSpan = (timeTonum(endTime) - timeTonum(startTime)) * 5
		if (attendList == '') {
			layer.msg('请选择参与人员', {
				icon: 5,
				time: 1500
			});
		} else if (rId == null) {
			layer.msg('请选择会议室', {
				icon: 5,
				time: 1500
			});
		} else {
			console.log(mTitle)
			console.log(mInfo)
			console.log(mSize)
			console.log(mSpan)
			console.log(pId_FQ)
			console.log(rId)
			console.log(startDate)
			console.log(startTime)
			console.log(endTime)
			console.log(attendList)
			var url = path + "/meeting/add.do";
			// console.log("请求controller的url是:" + url)
			$.ajax({
				url: url,
				type: "post",
				data: {
					'mTitle': mTitle,
					'mInfo': mInfo,
					'mSize': mSize,
					'mSpan': mSpan,
					'pId_FQ': pId_FQ,
					'rId': rId,
					'startDate': startDate,
					'startTime': startTime,
					'endTime': endTime,
					'attendList': attendList,
				},
				dataType: "json",
				success: function (data) {
					// console.log("传过来的是：")
					// console.log(data)
					layer.msg('会议创建成功！', {
						icon: 1,
						time: 1500
					}, function () {
						window.location.href = "meetMy.html"
					});

				},
				error: function () {
					console.log("ajax请求失败");
				}
			});

		}
		return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});

	// console.log(timeTonum('08:11'))
	//传入'08:00'，转化为 8*12的数字
	function timeTonum(strTime) {
		var number =
			parseInt(strTime.charAt(0)) * 12 * 10 +
			parseInt(strTime.charAt(1)) * 12 +
			parseInt(strTime.charAt(3)) * 2 +
			Math.ceil(parseInt(strTime.charAt(4)) / 5) * 1;
		return number;
	}

	//传入数字，得到格式化的时间。如‘08:00'
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

	//调用ajax获得可用房间列表
	function getRoomList() {
		mSize = $(".mSize").val()
		startDate = $(".startDate").val()
		startTime = $(".startTime").text()
		endTime = $(".endTime").text()
		// console.log("各个数据：")
		// console.log(mSize)
		// console.log(startDate)
		// console.log(startTime)
		// console.log(endTime)

		var url = path + "/meeting/recommendRoom.do";
//		 console.log("请求controller的url是:" + url)
		$.ajax({
			url: url,
			type: "post",
			data: {
				'mSize': mSize,
				'startDate': startDate,
				'startTime': startTime,
				'endTime': endTime,
			},
			dataType: "json",
			success: function (data) {
				// console.log("传过来的是房间信息：")
				//  console.log(data)
				// console.log("data.data是：" + JSON.stringify(data.data))
				var str = data.data;
				if (str != '') {
					var html = template('roomList', data);
					document.getElementById('content').innerHTML = html;
					//更新渲染
					form.render("radio");
				} else {
					// console.log('当前无会议室')
					var html = template('noroom', data);
					document.getElementById('content').innerHTML = html;
					//更新渲染
					form.render("radio");
				}

			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				console.log("ajax请求失败");
			}
		});
	}

	//调用ajax获得已选人员列表
	function getPersonList(attendList) {
		console.log('attendList:'+attendList)		
		var url = path + "/user/findUsers.do";
		// console.log("请求controller的url是:" + url)
		$.ajax({
			url: url,
			type: "post",
			data: {
				'userList': attendList,
			},
			dataType: "json",
			success: function (data) {
				// console.log("传过来的是：")
				// console.log(data)
				var str = data.data;
				if (str != '') {
					var html = template('personList', data);
					document.getElementById('content2').innerHTML = html;
				}
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				console.log("ajax请求失败");
			}
		});
	}

})