package cn.drrs.face_meeting.controller.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drrs.face_meeting.entity.Event;
import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.service.MREventService;
import cn.drrs.face_meeting.service.MeetingService;
import cn.drrs.face_meeting.util.NoteResult;


@Controller("meetingListController")
@RequestMapping("/meeting")
public class MeetingListController {
	@Resource
	private MeetingService service;
	@Resource
	private MREventService eventService;
	
	//获取用户创建的会议列表
	@RequestMapping("/getMyCreates.do") 
	@ResponseBody
	public NoteResult<List<Event>> getMyCreates(String pId,String date) {
		return eventService.getMyEvents(pId,LocalDate.parse(date),1);
	}
	public NoteResult<List<Meeting>> getMyCreatess(String pId,String date) {
		return service.getMyMeetings(pId,LocalDate.parse(date),1);
	}
	
	//获取用户参加的会议列表
	@RequestMapping("/getMyAttends.do") 
	@ResponseBody
	public NoteResult<List<Event>> getMyAttends(String pId,String date) {
		return eventService.getMyEvents(pId,LocalDate.parse(date),2);
	}
	public NoteResult<List<Meeting>> getMyAttendss(String pId,String date) {
		return service.getMyMeetings(pId,LocalDate.parse(date),2);
	}

	//获取用户被报送的会议列表
	@RequestMapping("/getMyInforms.do") 
	@ResponseBody
	public NoteResult<List<Event>> getMyInforms(String pId,String date) {
		return eventService.getMyEvents(pId,LocalDate.parse(date),3);
	}
	public NoteResult<List<Meeting>> getMyInformss(String pId,String date) {
		return service.getMyMeetings(pId,LocalDate.parse(date),3);
	}

}
