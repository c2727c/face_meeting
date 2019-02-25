package test.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.drrs.face_meeting.entity.Group;
import cn.drrs.face_meeting.entity.PGJoin;
import cn.drrs.face_meeting.entity.Room;
import cn.drrs.face_meeting.service.GroupService;
import cn.drrs.face_meeting.service.MeetingService;
import cn.drrs.face_meeting.service.PGJoinService;
import cn.drrs.face_meeting.service.PersonService;
import cn.drrs.face_meeting.service.RoomService;
import cn.drrs.face_meeting.util.NoteResult;

public class TestGroupAndJoinService {//complete2-5
	
	GroupService gs;
	PGJoinService js;
	@Before
	public void init() {
		String[] conf= {"conf/spring-mybatis.xml",
						"conf/spring-mvc.xml"};
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		 gs = ctx.getBean("groupService", GroupService.class);
		 js = ctx.getBean("joinService", PGJoinService.class);
	}
	
	@Test
	public void testInsert() {
		gs.insert(new Group("项目A组","特别重要的项目"));
	}
	
	@Test
	public void testDelete() {
		gs.delete( "项目A组");
	}
	
	@Test
	public void testUpdate() {
		gs.update(new Group("项目A组","特别特别特比重要的项目"));
	}
	
	@Test
	public void testJoinGroup() {//5-3OK
		List<PGJoin> list = new ArrayList<PGJoin>();
		list.add(new PGJoin("B","CC001"));
		list.add(new PGJoin("B","CC002"));
		js.insert(list);
		
	}
	
	@Test
	public void testQuitGroup() {//5-3OK
		List<PGJoin> list = new ArrayList<PGJoin>();
		list.add(new PGJoin("B","CC001"));
		js.delete(list);
		
	}
	
	
	

	
	
}
