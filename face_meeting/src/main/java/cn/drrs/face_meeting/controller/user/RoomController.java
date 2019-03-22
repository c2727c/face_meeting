package cn.drrs.face_meeting.controller.user;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drrs.face_meeting.entity.Event;
import cn.drrs.face_meeting.entity.Room;
import cn.drrs.face_meeting.service.MREventService;
import cn.drrs.face_meeting.service.RoomService;
import cn.drrs.face_meeting.util.NoteResult;

@Controller("roomController")
@RequestMapping("/room")
public class RoomController {
	@Resource
	private RoomService service;
	@Resource
	private MREventService eventService;
	
	//按ID查找会议室详情
	@RequestMapping("/roomInfo.do")
	@ResponseBody
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
