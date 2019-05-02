package cn.drrs.face_meeting.controller.admin;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.drrs.face_meeting.entity.ResponseData;
import cn.drrs.face_meeting.service.MeetingService;

@Controller("meetingManageController")
@RequestMapping("/admin/meeting")
public class MeetingManageController {
	@Resource
	private MeetingService meetingService;
	
	//得到所有会议信息（按页查询）
	public ResponseData getPageofMeetingByDate(int page,int limit,String date) {
		ResponseData rd = new ResponseData();
		
		rd = meetingService.getPageofMeetingByDate(page,limit,LocalDate.parse(date));
		return rd;
	}
}
