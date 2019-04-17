var mTitle = ''
var mInfo = ''
var mSize = ''
var mSpan = ''
var pId_FQ = ''
var rId = ''
var startDate = ''
var startTime = ''
var endTime = ''
var attendList = ''

var para = (window.location.href).split('?')[1]
var mNo = para.split('=')[1]


layui.use(["element", "layer", "jquery", "form", "laydate", "slider"], function () {
	var element = layui.element;
	var layer = layui.layer;
	var $ = layui.jquery;
	var form = layui.form;
	var laydate = layui.laydate;
	var slider = layui.slider;

	var url = path + "/meeting/detail.do";
	$.ajax({
		url: url,
		type: "post",
		data: {
			"mNo": mNo,
		},
		dataType: "json",
		success: function (data) {
			//填入会议名，简介
			console.log(data)
			var mEventList = data['data']['mEventList'][0];
			console.log(mEventList)
			rId = mEventList['rId']
			// console.log(rId)
			getRoom(rId)
			// html = template('roomAlways', {
			// 	'data': rId
			// });
			// document.getElementById('content3').innerHTML = html;
			// console.log('rId:', rId)


			mTitle = data['data']['mTitle']
			mInfo = data['data']['mInfo']
			mSize = data['data']['mSize']
			$("[name='mTitle']").val(mTitle)
			$("[name='mInfo']").val(mInfo)
			$("[name='mSize']").val(mSize)

			//初始化时间
			var now1 = mEventList['startTime']['hour'] * 12 + (mEventList['startTime']['minute'] / 5)
			var now2 = mEventList['endTime']['hour'] * 12 + (mEventList['endTime']['minute'] / 5)
			startTime = getTimeValue(now1)
			endTime = getTimeValue(now2);
			// console.log(startTime)
			// console.log(endTime)
			$("#sTime").html(startTime)
			$("#eTime").html(endTime)
			//滑动滑块提示时间
			var ins1 = slider.render({
				elem: "#slideTest1", //绑定元素
				max: 287, //288*5 min = 一天, 12*5 min= 1h
				step: 1,
				range: true,
				value: [now1, now2],
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

			var month, day;
			if (mEventList['startDate']['monthValue'] < 10) {
				month = '0' + mEventList['startDate']['monthValue'];
			} else {
				month = mEventList['startDate']['monthValue']
			}
			if (mEventList['startDate']['dayOfMonth'] < 10) {
				day = '0' + mEventList['startDate']['dayOfMonth'];
			} else {
				day = mEventList['startDate']['dayOfMonth']
			}
			startDate = mEventList['startDate']['year'] + '-' + month + '-' + day;

			// console.log('startDate:', startDate)
			//日期选择器
			laydate.render({
				elem: "#chosedate",
				type: "date",
				isInitValue: true,
				value: startDate,
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

			//人员
			var mAttendList = data['data']['mAttendList']
			for (var peo in mAttendList) {
				if (peo == 0) {
					attendList = mAttendList[peo]['pId'];
				} else {
					attendList = attendList + ',' + mAttendList[peo]['pId']
				}
			}
			// console.log(attendList)
			getPersonList(attendList)
			getRoomList()
		},
		error: function () {
			console.log("ajax请求失败");
		}
	})

	//跳出人员选择
	$(".btnAddPeople").on("click", function () {
		layer.open({
			type: 2,
			title: ["选择人员"],
			content: "meetAdd-people.html",
			area: ["411px", "98%"],
			skin: 'layui-layer-molv',
			success: function (layero, index) {
				// console.log(layero, index);
				if (attendList != '') {
					var body = layer.getChildFrame('body', index);
					var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
					iframeWin.tipDepart();
					console.log('tips')
				}
			},
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
		} else if (rId == '') {
			layer.msg('请选择会议室', {
				icon: 5,
				time: 1500
			});
		} else {
			mSpan = (timeTonum(endTime) - timeTonum(startTime)) * 5
			pId_FQ = userId
			var url = path + "/meeting/meetedit.do";
			console.log(url)

			$.ajax({
				url: url,
				type: "post",
				data: {
					'mNo': mNo,
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
					if (data.status != 0) {
						layer.msg(data.msg, {
							icon: 5
						}); //失败的表情
					} else if (data.status == 0) {
						layer.msg(data.msg, {
							icon: 1
						});
					}
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
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
		// console.log("请求controller的url是:" + url)
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
				// console.log("传过来的是：")
				// console.log("一次")
				// console.log(data)
				// console.log("data.data是：" + JSON.stringify(data.data))
				// $("#test1").html(JSON.stringify(data));
				var str = data.data;
				if (str != '') {
					var html = template('roomList', data);
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


	//获取会议室详情
	function getRoom(rId) {
		var url = path + "/room/roomInfo.do";
		console.log("请求controller的url是:" + url)
		$.ajax({
			url: url,
			type: "post",
			data: {
				'rId': rId,
			},
			dataType: "json",
			success: function (data) {
				// console.log(data)
				var rName = data['data']['rName']
				var rAddr = data['data']['rAddr']
				var rSize = data['data']['rSize']
				$(".rName").attr('title', rName + '(原本选择房间)')
				$(".rName").attr('value', rId)
				$(".rAddr").html(rAddr)
				$(".rSize").html(rSize)
				form.render('radio')
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				console.log("ajax请求失败");
			}
		});
	}

	//调用ajax获得已选人员列表
	function getPersonList(attendList) {
		console.log('url:' + attendList)
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