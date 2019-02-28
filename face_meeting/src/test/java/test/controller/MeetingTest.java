package test.controller;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.drrs.face_meeting.controller.meeting.MeetingListController;
import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.service.MeetingService;
import cn.drrs.face_meeting.service.PersonService;
import cn.drrs.face_meeting.util.NoteResult;

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
		Person p = ps.findBypId("t15").getData();
		nr=mlc.getMyAttends(p);
		nr.toString();
		
	}
	

}
