package cn.drrs.face_meeting.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drrs.face_meeting.entity.Department;
import cn.drrs.face_meeting.entity.Group;
import cn.drrs.face_meeting.entity.PGJoin;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.entity.PersonLight;
import cn.drrs.face_meeting.entity.ResponseData;
import cn.drrs.face_meeting.service.GroupService;
import cn.drrs.face_meeting.service.PGJoinService;
import cn.drrs.face_meeting.service.PersonService;
import cn.drrs.face_meeting.util.NoteResult;
import net.sf.json.JSONObject;

@Controller("groupjoinController")
@RequestMapping("/user/group")
public class GroupJoinController {
	@Resource
	private GroupService groupService;
	@Resource 
	private PGJoinService pGJoinService;
	@Resource
	private PersonService userService;
	
	//加入某组
	@RequestMapping("/join.do")
	@ResponseBody
	public NoteResult<Object> join(String gId,String pId) {
		try {
			List<PGJoin> list = new ArrayList<PGJoin>();
			list.add(new PGJoin(gId,pId));
			return pGJoinService.insert(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new NoteResult<Object>(1,"加入组失败",null);
		}
	}
	//退出某组
	@RequestMapping("/quit.do")
	@ResponseBody
	public NoteResult<Object> quit(String gId,String pId) {
		try {
			List<PGJoin> list = new ArrayList<PGJoin>();
			list.add(new PGJoin(gId,pId));
			return pGJoinService.delete(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new NoteResult<Object>(1,"退出组失败",null);
		}
	}
	
	//查询所有组及其成员
	@RequestMapping("/findAllGroup.do")
	@ResponseBody
	public NoteResult<List<Group>> findAllGroup() {
		NoteResult<List<Group>> nr = new NoteResult<List<Group>>();
		try {
			List<Group> list = groupService.findAll();
			nr.setAll(0, "查询所有组及其成员成功", list);
		} catch (Exception e) {
			nr.setAll(1, "查询所有组及其成员失败", null);
			e.printStackTrace();
			return nr;
		}
		return nr;
	}
	
	//查询加入的组及其成员
	@RequestMapping("/findMyGroup.do")
	@ResponseBody
	public NoteResult<List<Group>> findMyGroup(String pId) {
		NoteResult<List<Group>> nr = new NoteResult<List<Group>>();
		try {
			List<Group> list = groupService.findBypId(pId);
			nr.setAll(0, "查询加入的组及其成员成功", list);
		} catch (Exception e) {
			nr.setAll(1, "查询加入的组及其成员失败", null);
			e.printStackTrace();
			return nr;
		}
		return nr;
	}
	//查找每个部门下成员
	@RequestMapping("/findAllDept.do")
	@ResponseBody
	public NoteResult<List<JSONObject>> findAllDept() {
		NoteResult<List<JSONObject>> nr = new NoteResult<List<JSONObject>>();
		try {
			List<String> list = userService.findAllDeptName();
			List<JSONObject> data = new ArrayList<JSONObject>(); 
			Person p = new Person();
			for(String dept:list) {
				JSONObject json = new JSONObject();
				p.setpDept(dept);
				json.put("deptName", dept);
				json.put("memberList", userService.findByFields(p));
				data.add(json);
			}
			nr.setAll(0, "查找每个部门下成员成功", data);
		} catch (Exception e) {
			nr.setAll(0, "查找每个部门下成员失败", null);
		}
		return nr;
	}
	//查找每个部门下成员
		@RequestMapping("/findAllDept2.do")
		@ResponseBody
		public NoteResult<List<Department>> findAllDept2() {
			NoteResult<List<Department>> nr = new NoteResult<List<Department>>();
			try {
				List<String> list = userService.findAllDeptName();
				List<Department> data = new ArrayList<Department>(); 
				Person p = new Person();
				for(String dept:list) {
					p.setpDept(dept);
					Department department = new Department(dept,userService.findByFields(p));
					data.add(department);
				}
				nr.setAll(0, "查找每个部门下成员成功", data);
			} catch (Exception e) {
				nr.setAll(0, "查找每个部门下成员失败", null);
			}
			return nr;
		}
	
	
	
//	
//	
//	
//	//查找有哪些部门
//	@RequestMapping("/findAllDeptName.do")
//	@ResponseBody
//	public NoteResult<List<String>> findAllDeptName() {
//		NoteResult<List<String>> nr = new NoteResult<List<String>>();
//		try {
//			List<String> list = userService.findAllDeptName();
//			nr.setAll(0, "查找全部部门名成功", list);
//		} catch (Exception e) {
//			nr.setAll(0, "查找全部部门名失败", null);
//		}
//		return nr;
//	}
//	
//	//按部门查找用户
//	@RequestMapping("/findUserByDept.do")
//	@ResponseBody
//	public NoteResult<List<PersonLight>> findUserByDept(String dept) {
//		NoteResult<List<PersonLight>> nr = new NoteResult<List<PersonLight>>();
//		try {
//			Person p = new Person();
//			p.setpName(dept);
//			List<PersonLight> list = userService.findByFields(p);
//			nr.setAll(0, "按部门查找用户成功", list);
//		} catch (Exception e) {
//			nr.setAll(1, "按部门查找用户失败", null);
//			e.printStackTrace();
//			return nr;
//		}
//		return nr;
//	}
//	
//	//按姓名查找用户//只要名字包含输入的字就行
//	@RequestMapping("/findUserByName.do")
//	@ResponseBody
//	public NoteResult<List<PersonLight>> findUserByName(String pName) {
//		NoteResult<List<PersonLight>> nr = new NoteResult<List<PersonLight>>();
//		try {
//			Person p = new Person();
//			p.setpName(pName);
//			List<PersonLight> list = userService.findByFields(p);
//			nr.setAll(0, "按姓名查找用户成功", list);
//		} catch (Exception e) {
//			nr.setAll(1, "按姓名查找用户失败", null);
//			e.printStackTrace();
//			return nr;
//		}
//		return nr;
//	}
//	//按多条件查找用户
//	@RequestMapping("/findUserByFields.do")
//	@ResponseBody
//	public NoteResult<List<PersonLight>> findUserByFields(Person p) {
//		NoteResult<List<PersonLight>> nr = new NoteResult<List<PersonLight>>();
//		try {
//			List<PersonLight> list = userService.findByFields(p);
//			nr.setAll(0, "按姓名查找用户成功", list);
//		} catch (Exception e) {
//			nr.setAll(1, "按姓名查找用户失败", null);
//			e.printStackTrace();
//			return nr;
//		}
//		return nr;
//	}

}
