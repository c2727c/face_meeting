package test.service;

import java.util.ArrayList;
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
	//测试登陆
	@Test//正确输入
	public void testLoginCorrect() {
		NoteResult<Person> nr =ps.checkLogin("cCode", "t15", "123456");
		nr.toString();
	}//ok 2/5
	//测试查找
	@Test
	public void testFind() {
		//按部分姓名
		NoteResult<List<Person>> nr =ps.findBypDeptpName(null,"8");
		nr.toString();
		//按部分姓名+部门指定
		nr =ps.findBypDeptpName("ggb","8");
		nr.toString();
		
	}//ok 2/5
	//测试访问关联的会议集合
	@Test
	public void testRelatedMeetings() {
		Person p=ps.findBypId("t15").getData();
		System.err.println(p.toString());
		List<Meeting> list = p.getMeetings();
		System.err.println("holdListSzie:"+list.size());
		for(Meeting m :list) {
			List<Event> el = m.getmEventList();
			System.err.println("EventListSzie:"+el.size());
		}
		
		List<Meeting> attendlist = p.getpAttendMeetingList();
		System.err.println("attendlistSzie:"+attendlist.size());
		for(Meeting m :attendlist) {
			List<Event> el = m.getmEventList();
			System.err.println("EventListSzie:"+el.size());
			for(Event e:el)System.out.println(e.toString());
		}
	}//ok 2/5
	
	//TODO 测试访问关联工作组
	
	
	//测试批量注册
	@Test
	public void testRejest() {
		List<Person> list = new ArrayList<Person>();
		for(int i=0;i<4;++i) {
			Person p = new Person();
			list.add(p);
		}
		NoteResult<Person> nr = ps.insert(list);
		nr.toString();
	}
	
	
	
	
}
