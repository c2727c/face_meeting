package cn.drrs.face_meeting.service;

import cn.drrs.face_meeting.entity.Attend;
import cn.drrs.face_meeting.entity.Group;
import cn.drrs.face_meeting.util.NoteResult;

public interface GroupService {
	//·建立分组
	public NoteResult<Object> insert(Group g);
	//·删除分组
	public NoteResult<Object> delete(String gName);
	//·按组号查询分组
	public NoteResult<Group> findBygId(String gName);
	//·查询某人所在分组用映射
	//public NoteResult<Group> findBypId(String pId);
	//·修改分组信息
	public NoteResult<Object> update(Group g);


}
