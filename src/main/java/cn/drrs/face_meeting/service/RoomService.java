package cn.drrs.face_meeting.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.drrs.face_meeting.entity.Event;
import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.entity.Room;
import cn.drrs.face_meeting.util.NoteResult;


public interface RoomService {
	//【0】对房间的增删改查
	//增加房间
	public NoteResult<Object> insert(String rId,String rName,String rInfo,int rSize,String tName);
	//删除房间
	public NoteResult<Object> deleteByrId(String rId);
	//更新房间信息
	public NoteResult<Object> update(String rId,String rName,String rInfo,int rSize,String tName);
	//查找所有房间
	//public NoteResult<List<Room>>  findAll();
	//查找符合该会议要求的房间
	//public NoteResult<List<Room>>  findByMeeting(int mNo);
	//查找在某日期符合该会议要求的房间
	//public NoteResult<List<Room>>  findByMeetingDate(int mNo,Date startDate);
	//查找某日期某开始时间符合该会议要求的房间（在内部或可对其排序）
	public NoteResult<List<Room>>  findByMeetingDateTime(int mNo,LocalDate startDate,LocalTime startTime);
	/*1.指定时间段有空（startTime和endTime由Meeting m中的span和Calendar c计算出来）
	 *2.该会议的发起者有权使用这个Room
	 *3.该会议室的设备满足会议的要求*/
	
	//【便捷转场业务】，给出一个Event，为它安排合适的临近Room，还需要给出“所需时长”的参数
	public NoteResult<List<Room>> transfer(Event e,int span);

}
