package cn.drrs.face_meeting.dao;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.drrs.face_meeting.entity.Attend;

public interface PMAttendDao {
	//添加参会关系
	public int insert(Attend a);
	//删除参会关系
	public int delete(@Param("mNo")int mNo,@Param("pId")String pId);
	public int deleteAll(@Param("mNo")int mNo);
	//修改参会状态
	public int update(Attend a);
	public List<String> conflictTest(@Param("startDate")LocalDate startDate, 
							@Param("startTime")LocalTime startTime, @Param("endTime")LocalTime endTime);

}
