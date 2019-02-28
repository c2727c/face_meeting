package cn.drrs.face_meeting.controller.meeting;

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
		attend.setState("请假");
		return attendService.update(attend);
	}
	//签到并开门：平板端提出签到并开门的请求（mNo，pId），服务器验证该用户是否属于该会议，若属于则更新参会状态，查找到对应房间的rId然后开启硬件
	//事先要平板端给服务器提取出的人脸特征码，服务器返回给前端该用户的信息
	@RequestMapping("/checkin.do") 
	@ResponseBody
	public NoteResult<Object> checkin(@RequestBody(required = false) Attend attend) {
		return attendService.checkin(attend);
	}
}
