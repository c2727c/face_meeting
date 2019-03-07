package cn.drrs.face_meeting.controller.user;

import java.util.Base64;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
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
public class UserControllerxdw {
	@Resource
	private PersonService userservice;

	// 用户登录
	@RequestMapping("/user-login.do")
	@ResponseBody
	public NoteResult<Person> userLogin(@RequestBody(required = false) Person user) {
		NoteResult<Person> result = userservice.checkLogin("cCode", user.getpId(), user.getpPass());
		return result;
	}
	

	// 根据pId获取用户信息
	@RequestMapping("/getPerson.do")
	@ResponseBody
	public NoteResult<Person> getPerson(@RequestBody(required = false) Person user) {
		System.out.println("开始 /getPerson.do ");
		System.out.println(user.getpId());
		NoteResult<Person> result = userservice.findBypId(user.getpId());
		System.out.println("结果result: " + result);
		return result;
	}

	// 上传图片
	@RequestMapping("/updatenew.do")
	@ResponseBody
	public NoteResult<Person> updatenew(@RequestParam("file") MultipartFile file, @RequestParam("pId") String pId) {
		NoteResult<Person> nr = new NoteResult<Person>();
		System.out.println(file);
		System.out.println(pId);
		try {
			Person user = new Person();
			user.setpId(pId);
			if (!file.isEmpty()) {
				user.setpIcon(file.getBytes());
				return userservice.update(user);
			} else {
				nr.setAll(1, "头像不能为空", null);
			}
			return nr;
		} catch (Exception e) {
			nr.setAll(2, "出错", null);
			e.printStackTrace();
			return nr;
		}
	}

	// 修改密码
	@RequestMapping("/changePwd.do")
	@ResponseBody
	public NoteResult<Person> changePwd(String pId, String pOldpass, String pPass) {
		Person user = new Person();
		user.setpId(pId);
		user.setpPass(pOldpass);
		//检查是否原密码正确
		NoteResult<Person> result = userservice.checkLogin("cCode", user.getpId(), user.getpPass());
		
		return result;
	}

	// 修改个人信息
	@RequestMapping("/userInfoUpdate.do")
	@ResponseBody
	public NoteResult<Person> execute(@RequestBody(required = false) Person user) {
//		System.out.println();
//		System.out.println(user);
		NoteResult<Person> result = userservice.update(user);
		return result;
	}
	
	@RequestMapping("/updateFace.do")
	@ResponseBody
	public NoteResult<Person> updateFace(String pId,String pFace) {
		Person user = new Person();
		user.setpId(pId);
		final Base64.Decoder decoder = Base64.getDecoder();
		byte[] faceBytes = decoder.decode(pFace);
		user.setpFace(faceBytes);
		NoteResult<Person> result = userservice.update(user);
		return result;
	}
	 

}
