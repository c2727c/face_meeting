package test.controller;

import java.util.Base64;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.drrs.face_meeting.controller.user.GroupJoinController;
import cn.drrs.face_meeting.controller.user.MeetingListController;
import cn.drrs.face_meeting.controller.user.MeetnigDetailController;
import cn.drrs.face_meeting.entity.Department;
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
	}
	
	@Test
	public void test2() {
		NoteResult<Object> nr2 = new NoteResult<Object>();
		nr2=gc.join("项目A组", "t37");nr2.printJSON();
		nr2=gc.quit("项目A组", "t38");nr2.printJSON();
	}
	
	@Test
	public void findByDeptTest() {
		NoteResult<List<Group>> nr = new NoteResult<List<Group>>();
		nr=gc.findAllGroup();nr.printJSON();
		nr=gc.findMyGroup("t15");
		NoteResult<List<JSONObject>> nr2 = new NoteResult<List<JSONObject>>();
		NoteResult<List<Department>> nr3 = new NoteResult<List<Department>>();
		nr2=gc.findAllDept();
		nr3=gc.findAllDept2();
		nr.toString();
		
		nr3.toString();
		nr2.toString();
		nr.printJSON();
		nr3.printJSON();
		nr2.printJSON();
		
		

	}
	
	@Test
	public void encodeTest() {
		String aa = "啊啊啊啊";
		Base64.Encoder e = Base64.getEncoder();
		String base64S = e.encodeToString(aa.getBytes());
		System.out.println(base64S);
	}
	
	

}
