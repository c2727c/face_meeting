package cn.drrs.face_meeting.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drrs.face_meeting.entity.Group;
import cn.drrs.face_meeting.entity.PGJoin;
import cn.drrs.face_meeting.entity.ResponseData;
import cn.drrs.face_meeting.service.GroupService;
import cn.drrs.face_meeting.service.PGJoinService;
import cn.drrs.face_meeting.util.NoteResult;
import net.sf.json.JSONObject;

@Controller("groupjoinController")
@RequestMapping("/user/group")
public class GroupJoinController {
	@Resource
	private GroupService groupService;
	@Resource 
	private PGJoinService pGJoinService;
	
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

}