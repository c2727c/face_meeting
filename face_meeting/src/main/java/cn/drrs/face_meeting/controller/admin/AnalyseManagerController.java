package cn.drrs.face_meeting.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drrs.face_meeting.entity.Room;
import cn.drrs.face_meeting.entity.dto.EChart;
import cn.drrs.face_meeting.service.RoomService;
import cn.drrs.face_meeting.util.NoteResult;

@Controller("analyseManageController")
@RequestMapping("/admin/meeting")
public class AnalyseManagerController {//统计
	@Resource
	private RoomService roomService;
	
	
	@RequestMapping("/usageRoport.do") 
	@ResponseBody
	public NoteResult<EChart> usageRoport(String date) {//给出年月,返回近一年的数据
		
		return null;
	}

}
