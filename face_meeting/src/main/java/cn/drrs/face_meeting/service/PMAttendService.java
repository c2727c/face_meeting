package cn.drrs.face_meeting.service;

import java.util.List;

import cn.drrs.face_meeting.entity.Attend;
import cn.drrs.face_meeting.entity.Event;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.util.NoteResult;

public interface PMAttendService {
	//添加参会关系
	public NoteResult<Object> insert(Attend a);
	//删除参会关系
	public NoteResult<Object> delete(int mNo,String pId);
	//修改参会状态
	public NoteResult<Object> update(Attend a);
	//按会议查询参会名单 Meeting集合映射
	//public NoteResult<List<Person>> findAtdBymNo(int mNo);
	

}
