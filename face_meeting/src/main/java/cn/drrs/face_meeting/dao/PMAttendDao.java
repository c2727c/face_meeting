package cn.drrs.face_meeting.dao;


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

}
