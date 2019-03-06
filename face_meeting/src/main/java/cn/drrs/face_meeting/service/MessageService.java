package cn.drrs.face_meeting.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.drrs.face_meeting.entity.Message;

public interface MessageService {
	//发件箱查询，按发送时间逆序排序，取前15条
	public List<Message> findByOwnerSend(String owner,LocalDate date);
	//收件箱查询，按发送时间逆序排序，取前15条
	public List<Message> findByOwnerReceive(String owner,LocalDate date);
	//新建消息/请假/公告/系统通知（内部调用）
	public int add(Message m);
	//批假update Message//同时调用内部的update pm_attend
	public int update(Message m);
	//删除消息，只能删除自己的消息
	public int delete(@Param("owner")String owner,@Param("messageId")String messageId);
	
	
	
	
	

}
