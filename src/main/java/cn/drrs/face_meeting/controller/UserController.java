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
public class UserController {
	@Resource
	private PersonService userservice;
	
	@RequestMapping("/userInfoUpdate.do")
	@ResponseBody
	public NoteResult<Person> execute(@RequestBody(required=false) Person user){
		System.out.println(user);
		System.out.println(" /user/userInfoUpdate.do REQUEST");
		NoteResult<Person> result=userservice.update(user); 
		return result;
	}
	@RequestMapping("/changePass.do")
	@ResponseBody
	public NoteResult<Person> changePass(@RequestBody(required=false)Person user){
		System.out.println(user);
		System.out.println(" /user/changePass.do REQUEST");
		//Controller-->Service-->ServiceImpl-->Dao-->Mapper
		NoteResult<Person> result=userservice.changePass(user); 
		return result;
	}
}
