package test.service;


import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.drrs.face_meeting.entity.Event;
import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.service.MeetingService;
import cn.drrs.face_meeting.service.PersonService;
import cn.drrs.face_meeting.util.NoteResult;
import net.sf.json.JSONObject;

public class TestMeetingService {
	MeetingService ms;
	PersonService ps;
	NoteResult<Object> nr;
	@Before
	public void init() {
		String[] conf= {"conf/spring-mybatis.xml",
						"conf/spring-mvc.xml"};
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		ms=ctx.getBean("meetingService", MeetingService.class);
		ps = ctx.getBean("personService", PersonService.class);
	}
	
	
	

	@Test//ok 2/5
	public void testAdd() {
		Meeting m = new Meeting("3333333333", "aa", 30, 60, "t15");
		ms.add(m);
		JSONObject js = JSONObject.fromObject(m);
		System.err.println(js.toString());
	}
	@Test//ok 2/5
	public void deleteTest() {
		ms.delete(1);
	}
	
	//测试访问查找并参会名单和报送名单
	@Test
	public void testFindBymNo() {
		NoteResult<List<Meeting>> nr;
		Meeting m = ms.findBymNo(2).getData();

		
	}
	
	@Test
	public void testFindByFields() {
		NoteResult<List<Meeting>> nr;
		Meeting m = new Meeting();
		m.setmNo(2);
		nr=ms.findByFields(m);
		nr.toString();
		m = nr.getData().get(0);

		
	}
	
	
	/*
	@Test
	public void attend() {
		NoteResult<List<Meeting>> nr=ms.findByPM_attend("CC001");
		System.out.println(nr.toString());
	}
	*/
	/*
	@Test
	public void inform() {
		testPrint(ms.findByPM_inform("CC001"));
	}
	*/
	/*
	public void update() {
		testPrint(ms.update(1, "update11111111","new info1", 80, Time.valueOf("1:30:00"),"A"));

	}
	
	private void testPrint(Object nr) {
		System.out.println(nr.toString());
	}
	*/
	
	

}
