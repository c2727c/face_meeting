package cn.drrs.face_meeting.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.service.PersonService;
import cn.drrs.face_meeting.util.NoteResult;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private PersonService userservice;

	// 更改信息包括头像
//	@RequestMapping("/userInfoUpdate.do")
//	@ResponseBody
//	public NoteResult<Person> execute(@RequestBody(required = false) Person user) {
//		System.out.println(user);
//		System.out.println(" /user/userInfoUpdate.do REQUEST");
//		if (user.getpTel() != null)
//			user.setpId(user.getpTel().substring(user.getpTel().length() - 6, user.getpTel().length()));
//		NoteResult<Person> result = userservice.update(user);
//		return result;
//	}


//	@RequestMapping("/update.do")
//	@ResponseBody
//	public NoteResult<Object> update(HttpServletRequest request, @ModelAttribute Person user, Model model) {
//		NoteResult<Object> nr = new NoteResult<Object>();
//		try {
//			if (!user.getImage().isEmpty()) {
//				user.setpIcon(user.getImage().getBytes());
//				userservice.update(user);
//				nr.setAll(0, "更新用户成功", null);
//			} else {
//				nr.setAll(1, "头像不能为空", null);
//			}
//			return nr;
//		} catch (Exception e) {
//			nr.setAll(2, "出错", null);
//			e.printStackTrace();
//			return nr;
//		}
//	}

	@RequestMapping("/changePass.do")
	@ResponseBody
	public NoteResult<Person> changePass(@RequestBody(required = false) Person user) {
		System.out.println(user);
		System.out.println(" /user/changePass.do REQUEST");
		// Controller-->Service-->ServiceImpl-->Dao-->Mapper
		NoteResult<Person> result = userservice.update(user);
		return result;
	}
	@RequestMapping("/deleteUser.do")
	@ResponseBody
	public NoteResult<String> deleteUser(@RequestBody List<String> orderNoList) {
		System.out.println(orderNoList);
		System.out.println(" /user/deleteUser.do REQUEST");
		// Controller-->Service-->ServiceImpl-->Dao-->Mapper
		NoteResult<String> result = userservice.delete(orderNoList);
		return result;
	}
}
