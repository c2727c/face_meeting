package test.controller;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.drrs.face_meeting.controller.user.MeetingListController;
import cn.drrs.face_meeting.entity.Attend;
import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.service.MeetingService;
import cn.drrs.face_meeting.service.PersonService;
import cn.drrs.face_meeting.util.NoteResult;
import net.sf.json.JSONObject;

public class MeetingTest {
	MeetingListController mlc;
	PersonService ps;
	
	@Before
	public void init() {
		String[] conf= {"conf/spring-mybatis.xml",
						"conf/spring-mvc.xml"};
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		mlc=ctx.getBean("meetingListController", MeetingListController.class);
		ps = ctx.getBean("personService", PersonService.class);
	}
	
	@Test
	public void test() {
		NoteResult<List<Meeting>> nr = new NoteResult<List<Meeting>>();
		nr=mlc.getMyAttends("t15","2019-01-20");
		System.err.println(JSONObject.fromObject(nr).toString());
		nr=mlc.getMyAttends("t15","2019-01-21");
		System.err.println(JSONObject.fromObject(nr).toString());
		
	}
	
	@Test
	public void jsonTest() {
		Attend a = new Attend(1,"t15","noshow");
		Meeting m = new Meeting(1, null, null, 0, 0, null);
		System.out.println(JSONObject.fromObject(a).toString());
		System.out.println(JSONObject.fromObject(m).toString());
		
	}
	

}
