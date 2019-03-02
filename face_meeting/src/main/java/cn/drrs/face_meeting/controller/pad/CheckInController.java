package cn.drrs.face_meeting.controller.pad;

import java.util.Base64;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drrs.face_meeting.entity.Attend;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.service.FaceService;
import cn.drrs.face_meeting.service.PMAttendService;
import cn.drrs.face_meeting.service.imple.FaceServiceImple;
import cn.drrs.face_meeting.util.NoteResult;

@Controller("checkInController")
@RequestMapping("/checkin")
//管理人脸识别、硬件开启
public class CheckInController {
	@Resource
	private PMAttendService attendService;
	private FaceService faceService = new FaceServiceImple();
	
	//1.平板端给服务器提取出的人脸特征码，服务器返回给前端该用户的信息
	@RequestMapping("/recognize.do") 
	@ResponseBody
	public NoteResult<Person> recognize(String face) {
		final Base64.Decoder decoder = Base64.getDecoder();
		byte[] faceBytes = decoder.decode(face);
		return faceService.faceRecognition(faceBytes);
	}
	
	//2.平板端提出签到并开门的请求（mNo，pId），服务器验证该用户是否属于该会议，若属于则更新参会状态，查找到对应房间的rId然后开启硬件
	@RequestMapping("/checkin.do") 
	@ResponseBody
	public NoteResult<Object> checkin(@RequestBody(required = false) Attend attend) {
		return attendService.checkin(attend);
	}

}
