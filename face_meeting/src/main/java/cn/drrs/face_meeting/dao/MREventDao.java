package cn.drrs.face_meeting.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.drrs.face_meeting.entity.Event;
import cn.drrs.face_meeting.util.NoteResult;

public interface MREventDao {

	public int insert(Event e);
	public int delete(@Param("mNo")int mNo, @Param("rId")String rId);
	public List<Event> findByFields(@Param("mNo")int mNo,@Param("rId")String rId,@Param("startDate")Date startDate);
	

}
