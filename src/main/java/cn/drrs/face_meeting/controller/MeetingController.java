package cn.drrs.face_meeting.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.drrs.face_meeting.entity.ResponseData;
import cn.drrs.face_meeting.service.MeetingService;

@Controller	//加入到扫描当中
@RequestMapping("/meeting")	//匹配请求路径
public class MeetingController {
	@Resource
	private MeetingService meetingService;
	
	@RequestMapping(value="/getRoomList.do",method=RequestMethod.GET)	//GET请求
	public ResponseData execute(int page, int limit) {
		//调用MeetingService处理添加会议请求
		System.out.println("/meeting/getRoomList.do REQUEST");
        ResponseData result = new ResponseData();
		result=meetingService.findAllRoomByPage(page,limit);
		return result;
	}

}








