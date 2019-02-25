package cn.drrs.face_meeting.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.service.PersonService;
import cn.drrs.face_meeting.util.NoteResult;
 
@Controller
@RequestMapping("/user")
public class UserLoginController {
	@Resource
	private PersonService userService;
	
	@RequestMapping("/login.do") 
	@ResponseBody
	public NoteResult<Person> execute(@RequestBody(required = false) Person user) {
		// System.out.println(name+","+password);
		System.out.println(user);
		System.out.println(" /user/login.do REQUEST");
		System.out.println("pId="+user.getpId()+" pPass="+user.getpPass());
		NoteResult<Person> result = userService.checkLogin("cCode",user.getpId(), user.getpPass());
		return result;
	}

}
