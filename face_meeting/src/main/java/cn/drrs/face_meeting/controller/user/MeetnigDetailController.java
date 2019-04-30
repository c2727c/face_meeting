package cn.drrs.face_meeting.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drrs.face_meeting.entity.Event;
import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.entity.Report;
import cn.drrs.face_meeting.service.MeetingService;
import cn.drrs.face_meeting.util.NoteResult;
import net.sf.json.JSONObject;

//点击会议详情后，可以查询参会者名单、描述信息等


@Controller("meetingDetailController")
@RequestMapping("/meeting")
public class MeetnigDetailController {
	@Resource
	private MeetingService service;
	
	//查询会议详情
	@RequestMapping("/detail.do") 
	@ResponseBody
	public NoteResult<Meeting> detail(int mNo) {
		return service.findFullInfoBymNo(mNo);//返回的Meeting对象中的属性有其参会者名单/创建者Person对象的完整信息
	}
	@RequestMapping("/detailWithoutFace.do") 
	@ResponseBody
	public NoteResult<Meeting> detailWithoutFace(int mNo) {
		return service.findBymNo(mNo);//返回的Meeting对象中的属性有其参会者名单/创建者Person对象的完整信息
	}
	
	//查询出勤报表
	@RequestMapping("/report.do")
	@ResponseBody
	public NoteResult<Report> report(int mNo){
		return service.findReport(mNo);
	}
	

}
