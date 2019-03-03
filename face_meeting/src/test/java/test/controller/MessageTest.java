package test.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.apache.tomcat.jni.Time;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.drrs.face_meeting.controller.user.MeetingListController;
import cn.drrs.face_meeting.controller.user.MessageController;
import cn.drrs.face_meeting.entity.Event;
import cn.drrs.face_meeting.entity.Message;
import cn.drrs.face_meeting.service.PersonService;
import cn.drrs.face_meeting.util.NoteResult;
import cn.drrs.face_meeting.util.UUIDUtil;
import net.sf.json.JSONObject;

public class MessageTest {
	MessageController mlc;
	PersonService ps;
	
	@Before
	public void init() {
		String[] conf= {"conf/spring-mybatis.xml",
						"conf/spring-mvc.xml"};
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(conf);
		mlc=ctx.getBean("messageController", MessageController.class);
		ps = ctx.getBean("personService", PersonService.class);
	}
	
	@Test
	public void test() {
		 String sender = "t15";
		 String receiver = null;//群发时在发送者处此项缺省
		 int mNo = 2;
		 String title = "会议公告";
		 String content = "xxxxxxxxxxx";
		 String type = "meeting";//系统通知system/会议公告meeting/请假request/私信private
		Message m = new Message(UUIDUtil.getUID(),sender, receiver, mNo, title, content,type);
		NoteResult<Object> nr=mlc.add(m);
		nr.printJSON();
		NoteResult<List<Message>> nr2 =mlc.findByOwnerReceive("t37", LocalDate.now());
		nr2.printJSON();
	}

}
