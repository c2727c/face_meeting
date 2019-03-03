package cn.drrs.face_meeting.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.entity.ResponseData;
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
	
	@RequestMapping("/updateRoom.do") 
	@ResponseBody
	public NoteResult<Object> updateRoom(@RequestBody(required = false) Room r) {
		return roomService.update(r);
	}
	
	@RequestMapping("/deleteRoom.do") 
	@ResponseBody
	public NoteResult<Object> deleteRoom(String rId) {
		return roomService.deleteByrId(rId);
	}
	@RequestMapping(value="/getPageRoom.do",method=RequestMethod.GET)
	@ResponseBody
	public ResponseData execute(int page, int limit) {
		//调用MeetingService处理添加会议请求
		System.out.println("/admin/room/getPageRoom.do REQUEST");
        ResponseData result = new ResponseData();
		result=roomService.findPageRoom(page,limit);
		return result;
	}
	
}
