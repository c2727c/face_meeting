package cn.drrs.face_meeting.dao;

import java.util.List;

import cn.drrs.face_meeting.entity.Meeting;

public interface MeetingDao {
	public boolean save(Meeting m);
	public boolean deleteBymNo(int mNo);
	public boolean update(Meeting m);
	//public List<Meeting> findByCreator(String pId_FQ);//通过集合映射实现
	//public List<Meeting> findByPM_inform(String pId_FQ);//通过集合映射实现
	//public List<Meeting> findByPM_attend(String pId_FQ);//通过集合映射实现
	public Meeting findBymNo(int mNo);
	public List<Meeting> findByFields(Meeting m);

	
	//T/ODO mapping

}
