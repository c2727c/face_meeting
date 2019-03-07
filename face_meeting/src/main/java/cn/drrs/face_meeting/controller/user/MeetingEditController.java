package cn.drrs.face_meeting.controller.user;

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
//变更或完善会议所需的请求
public class MeetingEditController {
	@Resource
	private MeetingService meetingService;
	@Resource
	private MREventService eventService;
	
	
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
		return meetingService.update(m);
	}	
	
	//删除会议
	@RequestMapping("/delete.do") 
	@ResponseBody
	public NoteResult<Object> delete(int mNo) {
		return meetingService.delete(mNo);
	}


}
