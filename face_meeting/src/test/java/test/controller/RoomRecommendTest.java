package test.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.drrs.face_meeting.controller.pad.StateController;
import cn.drrs.face_meeting.controller.user.*;
import cn.drrs.face_meeting.entity.Event;
import cn.drrs.face_meeting.entity.Room;
import cn.drrs.face_meeting.entity.RoomRestrict;
import cn.drrs.face_meeting.service.PersonService;
import cn.drrs.face_meeting.util.NoteResult;
import net.sf.json.JSONObject;

public class RoomRecommendTest {
	MeetingAddController mac;
	StateController rc;
	
	@Before
	public void init() {
		String[] conf= {"conf/spring-mybatis.xml",
						"conf/spring-mvc.xml"};
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		mac=ctx.getBean("meetingAddController", MeetingAddController.class);
		rc=ctx.getBean("stateController", StateController.class);
	}
	@Test
	public void test() {
		NoteResult<List<Room>> nr = new NoteResult<List<Room>>();
//		RoomRestrict rr = new RoomRestrict(15,null,LocalDate.of(2019, 3, 5),LocalTime.of(9, 0),LocalTime.of(10, 0));
		nr = mac.recommendRoom(15,"2019-03-05","09:00","10:00");
//		nr = mac.recommendRoom(rr);
//		System.out.println();
//		System.out.println(LocalDate.of(2019, 3, 5));
//		System.out.println(LocalTime.of(9, 0));
		System.err.println(JSONObject.fromObject(nr).toString());
	}
	
	@Test
	public void testFindEvents() {
		NoteResult<List<Event>> nr = new NoteResult<List<Event>>();
		nr = rc.findEventList("A001", "2019-03-21");
		nr.printJSON();
	}
	

}
