package cn.drrs.face_meeting.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.entity.Room;
import cn.drrs.face_meeting.entity.RoomRestrict;
import cn.drrs.face_meeting.util.NoteResult;

public interface RoomDao {
	//增加房间
	public boolean insert(Room r);
	//删除房间
	public boolean deleteByrId(String rId);
	//更新房间信息
	public boolean update(Room r);
	
	/*/查找所有房间
	public List<Room>  findAll();
	//查找符合该会议要求的房间
	public List<Room>  findByMeeting(RoomRestrict rr);
	//查找在某日期符合该会议要求的房间
	public List<Room>  findByMeetingDate(RoomRestrict rr);*/
	
	//DONE 查找某日期某时间符合该会议要求的房间
	public List<Room>  findByMeetingDateTime(RoomRestrict rr);
	public int queryRoomCount();
	public List<Room> findRoomByPage(Map<String, Integer> map);
	public boolean batchRoomDelete(List<String> orderNoList);

}
