package cn.drrs.face_meeting.controller.admin;

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
}
