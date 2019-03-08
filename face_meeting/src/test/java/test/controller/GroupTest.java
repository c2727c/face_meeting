package test.controller;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.drrs.face_meeting.controller.user.GroupJoinController;
import cn.drrs.face_meeting.controller.user.MeetingListController;
import cn.drrs.face_meeting.controller.user.MeetnigDetailController;
import cn.drrs.face_meeting.entity.Event;
import cn.drrs.face_meeting.entity.Group;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.service.PersonService;
import cn.drrs.face_meeting.util.NoteResult;
import net.sf.json.JSONObject;

public class GroupTest {
	GroupJoinController gc;
	
	@Before
	public void init() {
		String[] conf= {"conf/spring-mybatis.xml",
						"conf/spring-mvc.xml"};
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		gc=ctx.getBean("groupjoinController", GroupJoinController.class);
	}
	
	@Test
	public void test() {
		NoteResult<List<Group>> nr = new NoteResult<List<Group>>();
		nr=gc.findAllGroup();nr.printJSON();
		nr=gc.findMyGroup("t15");nr.printJSON();
		NoteResult<Object> nr2 = new NoteResult<Object>();
	}
	
	@Test
	public void test2() {
		NoteResult<Object> nr2 = new NoteResult<Object>();
		nr2=gc.join("项目A组", "t37");nr2.printJSON();
		nr2=gc.quit("项目A组", "t38");nr2.printJSON();
	}
	
	@Test
	public void findByDeptTest() {
		NoteResult<List<JSONObject>> nr2 = new NoteResult<List<JSONObject>>();
		nr2=gc.findAllDept();
		nr2.printJSON();
		nr2.printJSON();
		nr2=gc.findAllDept();
		nr2.printJSON();

	}
	
	

}
