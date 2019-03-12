package cn.drrs.face_meeting.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.drrs.face_meeting.entity.Room;
import cn.drrs.face_meeting.service.MessageService;
import cn.drrs.face_meeting.service.RoomService;
import cn.drrs.face_meeting.util.NoteResult;

@Controller("roomController")
@RequestMapping("/room")
public class RoomController {
	@Resource
	private RoomService service;
	
	//按ID查找会议室详情
	public NoteResult<Room> findByrId(String rId){
		NoteResult<Room> nr = new NoteResult<Room>();
		try {
			Room r = service.findByrId(rId);
			nr.setAll(0, "按ID查找会议室详情成功", r);
		} catch (Exception e) {
			nr.setAll(1, "按ID查找会议室详情失败", null);
			e.printStackTrace();
		}
		return nr;
	}
	

}
