package test.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.drrs.face_meeting.entity.Event;
import cn.drrs.face_meeting.service.MREventService;

public class TestMREventService {
	String[] conf= {"conf/spring-mybatis.xml",
	"conf/spring-mvc.xml"};
	ApplicationContext ctx = 
			new ClassPathXmlApplicationContext(conf);
	MREventService mres=ctx.getBean("mREventService", MREventService.class);
	@Test
	public void testInsert() {
		System.out.println(mres.insert(new Event(1,"CR301",LocalDate.parse("2019-01-20"),LocalTime.parse("9:00:00"),LocalTime.parse("9:45:00"))));
	}//ok
	
	@Test
	public void testDelete() {
		System.out.println(mres.delete(1, null));
	} 
	@Test
	// 按字段查找：mNo/rId/日期/开始时间/结束时间
	public void testFind() {
		System.out.println(mres.findBymNo(1));
		for(Event e :mres.findBymNo(1).getData() ) {
			System.out.println(e.getStartDate()+","+e.getStartTime()+","+e.getMeeting().toString());
		}

	} 
	

}
