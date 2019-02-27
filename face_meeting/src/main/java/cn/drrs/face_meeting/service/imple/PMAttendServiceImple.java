package cn.drrs.face_meeting.service.imple;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.drrs.face_meeting.dao.PMAttendDao;
import cn.drrs.face_meeting.entity.Attend;
import cn.drrs.face_meeting.service.PMAttendService;
import cn.drrs.face_meeting.util.NoteResult;


@Service("pmattendService")
public class PMAttendServiceImple implements PMAttendService{
	@Resource
	PMAttendDao dao;
	//添加参会关系
	public NoteResult<Object> insert(Attend a){
		NoteResult<Object> nr=new NoteResult<Object>();
		nr.setAll(0, "插入参会关系成功", dao.insert(a));
		return nr;
	}
	//删除参会关系
	public NoteResult<Object> delete(int mNo,String pId){
		NoteResult<Object> nr=new NoteResult<Object>();
		nr.setAll(0, "删除参会关系成功", dao.delete(mNo,pId));
		return nr;
	}
	//修改参会状态
	public NoteResult<Object> update(Attend a){
		NoteResult<Object> nr=new NoteResult<Object>();
		nr.setAll(0, "更新参会状态为"+a.getState(), dao.update(a));
		return nr;
	}
	//按会议查询参会名单 Meeting集合映射
	//public NoteResult<List<Person>> findAtdBymNo(int mNo);
	//查询历史邀请用户（或按频数，最新排序后返回）

}
