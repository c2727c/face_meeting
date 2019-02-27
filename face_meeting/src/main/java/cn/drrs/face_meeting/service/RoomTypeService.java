package cn.drrs.face_meeting.service;

import cn.drrs.face_meeting.entity.RoomType;
import cn.drrs.face_meeting.util.NoteResult;

public interface RoomTypeService {
	//增加会议室类型
	public NoteResult<Object> insert(RoomType rt);
	//删除会议室类型
	public NoteResult<Object> delete(String tName);
	//修改会议室类型
	public NoteResult<Object> update(RoomType rt);

}
