package test.service;
import java.sql.Date;
import java.sql.Time;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.drrs.face_meeting.service.AnalyseService;
import cn.drrs.face_meeting.service.MeetingService;
import cn.drrs.face_meeting.util.NoteResult;
public class TestAnalyseService {
	String[] conf= {"conf/spring-mybatis.xml",
	"conf/spring-mvc.xml"};
	ApplicationContext ctx = 
			new ClassPathXmlApplicationContext(conf);
	AnalyseService as=ctx.getBean("analyseService", AnalyseService.class);
	
	@Test
	public void dailyAvailableTest() {
		NoteResult<Map<Time, Integer>> nr;
		nr=as.dailyAvilable(Date.valueOf("2019-01-24"));
		System.out.println(nr.toString());
	}
	

}
