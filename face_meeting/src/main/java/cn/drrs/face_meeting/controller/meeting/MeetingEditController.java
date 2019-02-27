package cn.drrs.face_meeting.controller.meeting;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drrs.face_meeting.entity.Event;
import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.entity.Room;
import cn.drrs.face_meeting.entity.RoomRestrict;
import cn.drrs.face_meeting.service.MREventService;
import cn.drrs.face_meeting.service.MeetingService;
import cn.drrs.face_meeting.service.RoomService;
import cn.drrs.face_meeting.util.NoteResult;

@Controller("meetingEditController")
@RequestMapping("/meeting")
public class MeetingEditController {
	@Resource
	private MeetingService service;
	@Resource
	private RoomService roomService;
	@Resource
	private MREventService eventService;
	
	//根据前端填写的表单，传上来房间限制RoomStrict，返回推荐可用的房间列表
	//onblur就提交一次，更新推荐的房间列表
	@RequestMapping("/recommendRoom.do") 
	@ResponseBody
	public NoteResult<List<Room>> recommendRoom(@RequestBody(required = false) RoomRestrict rr) {
		return roomService.findByRoomRestrict(rr);
	}
	//点击提交之后第一步是增加会议，返回新增会议编号	
	@RequestMapping("/addMeeting.do") 
	@ResponseBody
	public NoteResult<Meeting> addMeeting(@RequestBody(required = false) Meeting m) {
		return service.add(m);
	}
	//第二步是增加会议成功后取返回的会议编号，加上时间信息和选定的房间信息组成event对象，为其安排时间地点
	@RequestMapping("/arrangeMeeting.do") 
	@ResponseBody
	public NoteResult<Object> addEvent(@RequestBody(required = false) Event e) {
		return eventService.insert(e);
	}
	
	//重新安排时间地点
	@RequestMapping("/reArrangeMeeting.do") 
	@ResponseBody
	public NoteResult<Object> reArrangeMeeting(@RequestBody(required = false) Event e) {
		return eventService.update(e);
	}
	
	//取消时间地点安排
	@RequestMapping("/cancelMeeting.do") 
	@ResponseBody
	public NoteResult<Object> cancelMeeting(@RequestBody(required = false) Meeting m) {
		return eventService.delete(m.getmNo(),null);
	}
	
	//更新会议描述
	@RequestMapping("/update.do") 
	@ResponseBody
	public NoteResult<Object> update(@RequestBody(required = false) Meeting m) {
		return service.update(m);
	}	
	
	//删除会议
	@RequestMapping("/delete.do") 
	@ResponseBody
	public NoteResult<Object> delete(int mNo) {
		return service.delete(mNo);
	}


}
