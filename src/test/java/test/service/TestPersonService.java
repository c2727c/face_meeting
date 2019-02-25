package test.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.drrs.face_meeting.entity.Event;
import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.service.PersonService;
import cn.drrs.face_meeting.util.NoteResult;

public class TestPersonService {
	PersonService ps;
	@Before
	public void init() {
		String[] conf= {"conf/spring-mybatis.xml",
						"conf/spring-mvc.xml"};
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		 ps = ctx.getBean("personService", PersonService.class);
	}
	//���Ե�½
	@Test//��ȷ����
	public void testLoginCorrect() {
		NoteResult<Person> nr =ps.checkLogin("cCode", "CC001", "123456");
		nr.toString();
	}//ok 2/5
	//���Բ���
	@Test
	public void testFind() {
		//����������
		NoteResult<List<Person>> nr =ps.findBypDeptpName(null,"a");
		nr.toString();
		//����������+����ָ��
		nr =ps.findBypDeptpName("ggb","a");
		nr.toString();
		
	}//ok 2/5
	//���Է��ʹ����Ļ��鼯��
	@Test
	public void testRelatedMeetings() {
		Person p=ps.findBypId("CC001").getData();
		System.out.println(p.toString());
		List<Meeting> list = p.getMeetings();
		System.out.println("holdListSzie:"+list.size());
		for(Meeting m :list) {
			List<Event> el = m.getmEventList();
			System.out.println("EventListSzie:"+el.size());
		}
		
		List<Meeting> attendlist = p.getpAttendMeetingList();
		System.out.println("attendlistSzie:"+attendlist.size());
		for(Meeting m :attendlist) {
			List<Event> el = m.getmEventList();
			System.out.println("EventListSzie:"+el.size());
			for(Event e:el)System.out.println(e.toString());
		}
	}//ok 2/5
	
	//TODO ���Է��ʹ���������
	
	
	
	
	
}
