package test.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.drrs.face_meeting.controller.user.UserControllerxdw;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.service.PersonService;
import cn.drrs.face_meeting.util.NoteResult;

public class UserTest {
	UserControllerxdw ucl;
	PersonService ps;
	
	@Before
	public void init() {
		String[] conf= {"conf/spring-mybatis.xml",
						"conf/spring-mvc.xml"};
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		ucl=ctx.getBean("userControllerxdw", UserControllerxdw.class);
		ps = ctx.getBean("personService", PersonService.class);
	}

	@Test
	public void test() {
		NoteResult<Person> nr = new NoteResult<Person>();
		Person p = ps.findBypId("t15").getData();
		nr=ucl.userLogin(p);
		nr.toString();
	}
}
