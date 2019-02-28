package cn.drrs.face_meeting.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.entity.Room;
import cn.drrs.face_meeting.service.RoomService;
import cn.drrs.face_meeting.util.NoteResult;

@Controller("roomManageController")
@RequestMapping("/admin/room")

//管理端管理会议室
public class RoomManageController {
	@Resource
	private RoomService roomService;
	
	@RequestMapping("/addRoom.do") 
	@ResponseBody
	public NoteResult<Object> addRoom(@RequestBody(required = false) Room r) {
		return roomService.insert(r);
	}

}
