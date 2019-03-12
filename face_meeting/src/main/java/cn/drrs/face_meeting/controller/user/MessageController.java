package cn.drrs.face_meeting.controller.user;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drrs.face_meeting.entity.Message;
import cn.drrs.face_meeting.service.MeetingService;
import cn.drrs.face_meeting.service.MessageService;
import cn.drrs.face_meeting.util.NoteResult;

@Controller("messageController")
@RequestMapping("/message")
public class MessageController {
	@Resource
	private MessageService service;

	// 发件箱查询，按发送时间逆序排序，取前15条
	public NoteResult<List<Message>> findByOwnerSend(String owner, String date) {
		NoteResult<List<Message>> nr = new NoteResult<List<Message>>();
		try {
			nr.setAll(0, "发件箱查询成功", service.findByOwnerSend(owner, LocalDate.parse(date)));
		} catch (Exception e) {
			nr.setAll(1, "发件箱查询失败", null);
			e.printStackTrace();
		}
		return nr;
	}

	@RequestMapping("/messageReceive.do")
	@ResponseBody
	// 收件箱查询，按发送时间逆序排序，取前15条
	public NoteResult<List<Message>> findByOwnerReceive(String owner, LocalDate date) {
		NoteResult<List<Message>> nr = new NoteResult<List<Message>>();
		try {
			nr.setAll(0, "收件箱查询成功", service.findByOwnerReceive(owner, date));
		} catch (Exception e) {
			nr.setAll(1, "收件箱查询失败", null);
			e.printStackTrace();
		}
		return nr;
	}

	// 新建消息/请假/公告/系统通知（内部调用）
	public NoteResult<Object> add(Message m) {
		NoteResult<Object> nr = new NoteResult<Object>();
		try {
			nr.setAll(0, "发送成功", service.add(m));
		} catch (Exception e) {
			nr.setAll(1, "发送失败", null);
			e.printStackTrace();
		}

		return nr;
	}

	// 批假update Message//同时调用内部的update pm_attend
	public NoteResult<Object> update(Message m) {
		NoteResult<Object> nr = new NoteResult<Object>();
		try {
			nr.setAll(0, "批假操作成功", service.update(m));
		} catch (Exception e) {
			nr.setAll(1, "批假操作失败", null);
			e.printStackTrace();
		}
		return nr;
	}

	// 删除消息，只能删除自己的消息
	public NoteResult<Object> delete(String owner, String messageId) {
		NoteResult<Object> nr = new NoteResult<Object>();
		try {
			nr.setAll(0, "删除消息成功", service.delete(owner, messageId));
		} catch (Exception e) {
			nr.setAll(1, "删除消息失败", null);
			e.printStackTrace();
		}
		return nr;
	}

}
