package cn.drrs.face_meeting.service;

import java.util.List;

import cn.drrs.face_meeting.entity.Attend;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.util.NoteResult;

public interface PMInformService {
	//添加通知关系
	//public NoteResult<Object> insert(Inform a);
	//删除通知关系
	public NoteResult<Object> delete(int mNo,String pId);
	//按会议查询通知名单
	public NoteResult<List<Person>> findAtdBymNo(int mNo);
	//查询历史通知用户（或按频数，最新排序后返回）
	public NoteResult<List<Person>> findHistoryAtdBypId(String pId);

}
