package cn.drrs.face_meeting.service.imple;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import cn.drrs.face_meeting.dao.MeetingDao;
import cn.drrs.face_meeting.dao.MessageDao;
import cn.drrs.face_meeting.entity.Message;
import cn.drrs.face_meeting.service.MessageService;
import cn.drrs.face_meeting.util.UUIDUtil;


@Service("messageService")
public class MessageServiceImple implements MessageService {
	
	@Resource
	private MessageDao messageDao;
	//发件箱查询，按发送时间逆序排序，取前15条

	public List<Message> findByOwnerSend(String owner, LocalDate date) {		
		return messageDao.findByOwnerSend(owner, date.plusDays(1));
	}

	public List<Message> findByOwnerReceive(String owner, LocalDate date) {
		return messageDao.findByOwnerReceive(owner, date.plusDays(1));
	}

	public int add(Message m) {
		String type = m.getType();
		m.setMessageId(UUIDUtil.getUID());
		if(type.equals("meeting")) {//会议公告
			messageDao.add(m);
			List<String> receivers = messageDao.getreceivers(m.getmNo());
			for(String pId : receivers) {
				m.setOwner(pId);
				m.setReceiver(pId);
				try {
					messageDao.add(m);
				} catch (DuplicateKeyException e) {
					System.err.println("跳过插入主键重复信息");
				}
			}
		}else if(type.equals("system")) {
			messageDao.add(m);
		}else {//"request""private"
			messageDao.add(m);
			m.setOwner(m.getReceiver());
			try {
				messageDao.add(m);
			} catch (DuplicateKeyException e) {
				System.err.println("跳过插入主键重复信息");
			}
		}
		return 0;
	}

	public int update(Message m) {
		return messageDao.update(m);
	}

	public int delete(String owner, String messageId) {
		return messageDao.delete(owner, messageId);
	}
	

}
