package test.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.drrs.face_meeting.entity.Room;
import cn.drrs.face_meeting.service.MeetingService;
import cn.drrs.face_meeting.service.PersonService;
import cn.drrs.face_meeting.service.RoomService;
import cn.drrs.face_meeting.util.NoteResult;

public class TestRoomService {//complete2-5
	String[] conf= {"conf/spring-mybatis.xml",
	"conf/spring-mvc.xml"};
	ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
	RoomService rs = ctx.getBean("roomService", RoomService.class);
	MeetingService ms=ctx.getBean("meetingService", MeetingService.class);
	@Test
	public void findByMeetingDateTime() {
		rs.findByMeetingDateTime(1,LocalDate.of(2019,1,20),LocalTime.of(11,30,0,0));
		
	}
	@Test
	public void insert() {
		NoteResult nr = rs.insert("A001", "", "", 30, "default");
		nr.toString();
	}//ok2-5
	@Test
	public void delete() {
		NoteResult nr = rs.deleteByrId("A001");
		System.out.println(nr.toString());
	}//ok2-5
	@Test
	public void update() {
		NoteResult nr = rs.update("A001", "UPDATE", "TEST", 30, "default");
		System.out.println(nr.toString());
	}//ok2-5
	
	@Test//测试按条件查找会议室并获取房间的eventList
	public void getEventList() {
		NoteResult<List<Room>> nr= rs.findByMeetingDateTime(1,LocalDate.parse("2019-01-20"),LocalTime.parse("09:00:00"));
		System.out.println(nr.toString());
		for(Room r:nr.getData()) {
			System.out.println(r.getrEventList().toString());
		}
	}//ok2-5


	
	
}
