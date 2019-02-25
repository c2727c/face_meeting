package cn.drrs.face_meeting.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.entity.Room;
@Component("MeetingDao")
public interface MeetingDao {
	public boolean save(Meeting m);
	public boolean deleteBymNo(int mNo);
	public boolean update(Meeting m);
	//public List<Meeting> findByCreator(String pId_FQ);//通过集合映射实现
	//public List<Meeting> findByPM_inform(String pId_FQ);//通过集合映射实现
	//public List<Meeting> findByPM_attend(String pId_FQ);//通过集合映射实现
	public Meeting findBymNo(int mNo);
	public List<Meeting> findByFields(Meeting m);
	public int queryRoomCount();
	//T/ODO mapping
	public List<Room> findRoomByPage(Map<String, Integer> map);
}
