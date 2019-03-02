package cn.drrs.face_meeting.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drrs.face_meeting.entity.ResponseData;
import cn.drrs.face_meeting.service.PersonService;

@Controller("userManageController")
@RequestMapping("/admin/user")
public class UserManageController {
	@Resource
	private PersonService personService;
	
	@RequestMapping(value="/getPageofUser.do",method=RequestMethod.GET)
	@ResponseBody
	public ResponseData execute(int page, int limit) {
		//调用MeetingService处理添加会议请求
		System.out.println("/admin/room/getPageRoom.do REQUEST");
        ResponseData result = new ResponseData();
		result=personService.getPageofUser(page,limit);
		return result;
	}
}
