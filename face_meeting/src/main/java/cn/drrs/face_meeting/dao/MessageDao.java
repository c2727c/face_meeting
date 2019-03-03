package cn.drrs.face_meeting.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;

import cn.drrs.face_meeting.entity.Message;

public interface MessageDao {
	
	public int add (Message m)throws DuplicateKeyException;
	public int update(Message m);
	public int delete(@Param("owner")String owner,@Param("messageId")String messageId);
	
	public List<Message> findByOwnerSend(@Param("owner")String owner,@Param("date")LocalDate date);
	public List<Message> findByOwnerReceive(@Param("owner")String owner,@Param("date")LocalDate date);
	
	public List<String> getreceivers(int mNo);
	
	
	

}
