package cn.drrs.face_meeting.controller.pad;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drrs.face_meeting.entity.Event;
import cn.drrs.face_meeting.service.MREventService;
import cn.drrs.face_meeting.util.NoteResult;

//管理平板端的状态

@Controller("stateController")
@RequestMapping("/pad")
public class StateController {
	@Resource
	private MREventService eventService;
	//查询属于该会议室的event们，供前端判断状态
	@RequestMapping("/findEvents.do") 
	@ResponseBody
	public NoteResult<List<Event>> findEvents(String rId) {
		NoteResult<List<Event>> nr = new NoteResult<List<Event>>();
		try {
			nr = eventService.findByFields(-1,rId,Date.valueOf(LocalDate.now()));
		} catch (Exception e) {
			nr.setAll(1, "失败", null);
			e.printStackTrace();
		}
		return nr;
	}
	//按rId查找该会议室某日期以后的全部会议
	@RequestMapping("/findEventList.do")
	@ResponseBody
	public NoteResult<List<Event>> findEventList(String rId,String date){
		NoteResult<List<Event>> nr = new NoteResult<List<Event>>();
		try {
			nr = eventService.findByFields(-1,rId,Date.valueOf(date));
		} catch (Exception e) {
			nr.setAll(1, "失败", null);
			e.printStackTrace();
		}
		return nr;
	}

}
