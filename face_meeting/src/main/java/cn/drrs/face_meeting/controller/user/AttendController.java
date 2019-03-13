package cn.drrs.face_meeting.controller.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drrs.face_meeting.entity.Attend;
import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.service.PMAttendService;
import cn.drrs.face_meeting.util.NoteResult;

@Controller("attendController")
@RequestMapping("/attend")
//会议参与名单的管理
public class AttendController {
	@Resource
	private PMAttendService attendService;
	
	//为会议添加参会者
	@RequestMapping("/addAttendants.do") 
	@ResponseBody
	public NoteResult<Object> addAttendants(@RequestBody(required = false) List<Attend> alist ) {
		return attendService.insert(alist);
	}
	//为会议删除参会者
	@RequestMapping("/delAttendants.do") 
	@ResponseBody
	public NoteResult<Object> delAttendants(@RequestBody(required = false) List<Attend> alist ) {
		return attendService.delete(alist);
	}
	//请假
	@RequestMapping("/askForLeave.do") 
	@ResponseBody
	public NoteResult<Object> askForLeave(@RequestBody(required = false) Attend attend) {
		System.out.println(attend);
		attend.setState("请假");
		return attendService.update(attend);
	}
	
}
