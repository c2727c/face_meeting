package cn.drrs.face_meeting.service.imple;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.drrs.face_meeting.dao.PGJoinDao;
import cn.drrs.face_meeting.entity.PGJoin;
import cn.drrs.face_meeting.service.PGJoinService;
import cn.drrs.face_meeting.util.NoteResult;
@Service("joinService")
public class PGJoinServiceImple implements PGJoinService{
	@Resource
	private PGJoinDao dao;
	
	//·加入分组
	public NoteResult<Object> insert(List<PGJoin> list){
		NoteResult<Object> nr= new  NoteResult<Object>();
		nr.setAll(0, "批量插入分组", dao.insert(list));
		return nr;
		
	}
	//·退出分组
	public NoteResult<Object> delete(List<PGJoin> list){
		NoteResult<Object> nr= new  NoteResult<Object>();
		nr.setAll(0, "批量删除分组", dao.delete(list));
		return nr;
		
	}
}
