package cn.drrs.face_meeting.service;

import java.util.List;

import cn.drrs.face_meeting.entity.Group;
import cn.drrs.face_meeting.entity.ResponseData;
import cn.drrs.face_meeting.util.NoteResult;

public interface GroupService {
	//·建立分组
	public NoteResult<Object> insert(Group g);
	//·删除分组
	public NoteResult<Object> delete(String gName);
	//·按组号查询分组
	public NoteResult<Group> findBygId(String gName);
	//·查询某人所在分组用映射
	public List<Group> findBypId(String pId);
	public List<Group> findAll();
	//·修改分组信息
	public NoteResult<Object> update(Group g);
	public ResponseData getPageofGroup(int page, int limit);
}
