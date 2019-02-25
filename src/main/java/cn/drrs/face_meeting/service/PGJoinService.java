package cn.drrs.face_meeting.service;

import java.util.List;

import cn.drrs.face_meeting.entity.Group;
import cn.drrs.face_meeting.entity.PGJoin;
import cn.drrs.face_meeting.util.NoteResult;

public interface PGJoinService {
	//·批量加入分组
	public NoteResult<Object> insert(List<PGJoin> list);
	//·批量退出分组
	public NoteResult<Object> delete(List<PGJoin> list);
}
