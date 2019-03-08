package cn.drrs.face_meeting.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drrs.face_meeting.entity.Group;
import cn.drrs.face_meeting.entity.ResponseData;
import cn.drrs.face_meeting.service.GroupService;
import cn.drrs.face_meeting.service.PGJoinService;
import cn.drrs.face_meeting.util.NoteResult;

@Controller("groupManageController")
@RequestMapping("/admin/group")
public class GroupManageController {
	@Resource
	private GroupService groupService;
	@Resource 
	private PGJoinService pGJoinService;
	@RequestMapping(value="/getPageofGroup.do",method=RequestMethod.GET)
	@ResponseBody
	public ResponseData execute(int page,int limit) {
		System.out.println("admin/group/getpageofGroup.do REQUEST");
		ResponseData result = new ResponseData();
		result=groupService.getPageofGroup(page,limit);
		return result;
	}
	@RequestMapping("/getGroupMember.do")
	@ResponseBody
	public ResponseData getGroupMembers(int page,int limit,String gId) {
		System.out.println("admin/group/getGroupMember.do REQUEST");
		ResponseData result = new ResponseData();
		result=pGJoinService.findGroupMembersBygId(gId);
		return result;
	}
	@RequestMapping("/batchGroupDelete.do")
	@ResponseBody
	public NoteResult<String> batchGroupDelete(@RequestBody List<String> orderNoList){
		NoteResult<String> rs = new NoteResult<String>();
		System.out.println(orderNoList);
		System.out.println("one /admin/group/batchGroupDelete.do");
		rs = groupService.batchGroupDelete(orderNoList);
		return rs;
	}
	@RequestMapping("/updateGroupInfo.do")
	@ResponseBody
	public NoteResult<String> updateGroupInfo(@RequestBody(required = false) Group group){
		NoteResult<String> rs = new NoteResult<String>();
		System.out.println(group);
		System.out.println("one /admin/group/updateGroupInfo.do");
		rs = groupService.update(group);
		return rs;
	}
	@RequestMapping("/addGroup.do")
	@ResponseBody
	public NoteResult<String> addGroup(@RequestBody(required = false) Group group){
		NoteResult<String> rs = new NoteResult<String>();
		System.out.println(group);
		System.out.println("one /admin/group/addGroup.do");
		rs = groupService.insert(group);
		return rs;
	}
}
