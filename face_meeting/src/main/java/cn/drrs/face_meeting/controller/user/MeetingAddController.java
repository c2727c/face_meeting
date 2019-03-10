package cn.drrs.face_meeting.controller.user;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drrs.face_meeting.entity.Attend;
import cn.drrs.face_meeting.entity.Event;
import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.entity.Room;
import cn.drrs.face_meeting.entity.RoomRestrict;
import cn.drrs.face_meeting.service.AnalyseService;
import cn.drrs.face_meeting.service.MREventService;
import cn.drrs.face_meeting.service.MeetingService;
import cn.drrs.face_meeting.service.PMAttendService;
import cn.drrs.face_meeting.service.RoomService;
import cn.drrs.face_meeting.util.NoteResult;
import cn.drrs.face_meeting.util.UUIDUtil;

@Controller("meetingAddController")
@RequestMapping("/meeting")
//新建会议所需的请求
public class MeetingAddController {
	@Resource
	private MeetingService service;
	@Resource
	private RoomService roomService;
	@Resource
	private MREventService eventService;
	@Resource
	private PMAttendService attendService;
	@Resource
	private AnalyseService analyseService;

	// 根据选择的日期，显示当日剩余可用会议室时间轴分布
	public NoteResult<Map<Time, Integer>> dailyAvilable(String dateInString) {
		NoteResult<Map<Time, Integer>> result;
		Date date = Date.valueOf(dateInString);
		result = analyseService.dailyAvilable(date);
		return result;
	}

	// 根据前端填写的表单，传上来房间限制RoomStrict，返回推荐可用的房间列表
	// onblur就提交一次，更新推荐的房间列表
	@RequestMapping("/recommendRoom.do")
	@ResponseBody
	public NoteResult<List<Room>> recommendRoom(int mSize, String startDate, String startTime, String endTime) {
		RoomRestrict rr = new RoomRestrict(15, null, LocalDate.parse(startDate), LocalTime.parse(startTime),
				LocalTime.parse(endTime));
		return roomService.findByRoomRestrict(rr);
	}

	// 一步提交会议和会程并安排已经选择的参会人员
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult<Object> add(String mTitle, String mInfo, int mSize, int mSpan, String pId_FQ, String rId,
			String startDate, String startTime, String endTime, String attendList) {
		NoteResult<Object> nr = new NoteResult<Object>();
			System.err.println("测试点mInfo");
			System.err.println(mInfo);
		Meeting m;
		try {
			m = new Meeting(mTitle, mInfo, mSize, mSpan, pId_FQ);
			service.add(m);
			Event e = new Event(m.getmNo(), rId, LocalDate.parse(startDate), LocalTime.parse(startTime),
					LocalTime.parse(endTime));
			eventService.insert(e);
			System.err.println("测试点a");
			String[] attends = attendList.split(",");
			List<Attend> alist = new ArrayList<Attend>();
			System.err.println("测试点b");
			for (String pId : attends) {
				System.err.println("测试点c：pId="+pId);
				alist.add(new Attend(m.getmNo(), pId, "noshow"));
			}
			attendService.insert(alist);
			nr.setAll(0, "插入会议并安排会程成功", null);
		} catch (Exception e) {
			nr.setAll(0, "插入会议并安排会程失败", null);
			return nr;
		}
		return nr;
	}

	// 点击提交之后第一步是增加会议，返回新增会议编号
	@RequestMapping("/addMeeting.do")
	@ResponseBody
	public NoteResult<Meeting> addMeeting(@RequestBody(required = false) Meeting m) {
		return service.add(m);
	}

	// 第二步是增加会议成功后取返回的会议编号，加上时间信息和选定的房间信息组成event对象，为其安排时间地点
	@RequestMapping("/arrangeMeeting.do")
	@ResponseBody
	public NoteResult<Object> addEvent(@RequestBody(required = false) Event e) {
		return eventService.insert(e);
	}

}
