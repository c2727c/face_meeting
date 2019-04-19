package cn.drrs.face_meeting.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import cn.drrs.face_meeting.entity.Attend;
import cn.drrs.face_meeting.entity.Event;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.util.NoteResult;

public interface PMAttendService {
	//添加参会关系
	public NoteResult<Object> insert(List<Attend> alist);
	//删除参会关系
	public NoteResult<Object> delete(int mNo,String pId);
	public NoteResult<Object> deleteAll(int mNo);
	public NoteResult<Object> delete(List<Attend> alist);
	//修改参会状态
	public NoteResult<Object> update(Attend a);
	public NoteResult<Object> checkin(Attend attend);
	public List<String> conflictTest(LocalDate startDate,LocalTime startTime,LocalTime endTime);
	//按会议查询参会名单 Meeting集合映射
	//public NoteResult<List<Person>> findAtdBymNo(int mNo);
	
	

}
