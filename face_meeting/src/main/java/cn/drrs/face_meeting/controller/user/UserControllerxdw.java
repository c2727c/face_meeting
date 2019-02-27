package cn.drrs.face_meeting.controller.user;

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
public class UserControllerxdw {
	@Resource
	private PersonService userservice;
	
	@RequestMapping("/user-login.do") 
	@ResponseBody
	public NoteResult<Person> userLogin(@RequestBody(required = false) Person user) {
		NoteResult<Person> result = userservice.checkLogin("cCode",user.getpId(), user.getpPass());
		return result;
	}
	
	@RequestMapping("/getPerson.do") 
	@ResponseBody
	public NoteResult<Person> getPerson(@RequestBody(required = false) Person user) {
		System.out.println("开始 /getPerson.do ");
		System.out.println(user.getpId());
		NoteResult<Person> result = userservice.findBypId(user.getpId());
		System.out.println("结果result: "+result);
		return result;
	}
}
