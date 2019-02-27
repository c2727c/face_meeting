package cn.drrs.face_meeting.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import cn.drrs.face_meeting.entity.Event;
import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.util.NoteResult;

public interface MREventService {
	// 【1】对Event的增删改查，增加Event需要Meeting和Room做参数
	// 安排会程
	public NoteResult<Object> insert(Event e);
	// 删除会程
	public NoteResult<Object> delete(int mNo,String rId);
	// 更新会程
	//public NoteResult<Object> update(Event e);
	// 查找会程（按会议）
	public NoteResult<List<Event>> findBymNo(int mNo);
	// 查找会程（按多字段）
	//public NoteResult<List<Event>> findByFields(int mNo,String rId,Date startDate);
	
	/*
	//通过Meeting的集合映射实现
	// 查找会程（按会议的发起人）按会议实际安排开始时间排序
	public NoteResult<List<Event>> findByFQpId(String pId);
	// 查找会程（按会议的参与人）按会议实际安排开始时间排序
	public NoteResult<List<Event>> findByAttendant(String pId);
	
	//通过Room的集合映射实现
	// 查找会程（按房间）用于前端显示该会议室空闲情况
	public NoteResult<List<Event>> findByrId(String rId);
	public NoteResult<List<Event>> findByrIdDate(String rId,Date date);
	*/
	//私有方法【2】调度功能
	//对可能的Event集进行优劣排序（在内部可通过打分实现：给出一个可能的新Event，针对它对系统的影响进行打分//Event中包含会议，会议室，时间段的信息）
	//private NoteResult<List<Event>> sortByScore(List<Event> list);
	
}
