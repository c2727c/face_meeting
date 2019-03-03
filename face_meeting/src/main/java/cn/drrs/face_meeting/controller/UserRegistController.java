package cn.drrs.face_meeting.controller;

import java.util.ArrayList;
import java.util.List;

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
public class UserRegistController {
	@Resource
	PersonService userService;
	@RequestMapping("/register.do")
	@ResponseBody
	public NoteResult<Person> execute(@RequestBody(required = false) Person user) {
		System.out.println("one /user/register.do REQUEST");
		System.out.println(user);
		user.setpId(user.getpTel().substring(user.getpTel().length()-6, user.getpTel().length()));
		List<Person> users=new ArrayList<Person>();
		users.add(user);
		NoteResult<Person> result=userService.insert(users);
		return result;
	}

}
