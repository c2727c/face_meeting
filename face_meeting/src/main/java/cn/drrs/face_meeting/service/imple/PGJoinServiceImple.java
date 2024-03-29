package cn.drrs.face_meeting.service.imple;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.drrs.face_meeting.dao.PGJoinDao;
import cn.drrs.face_meeting.dao.PersonDao;
import cn.drrs.face_meeting.entity.PGJoin;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.entity.ResponseData;
import cn.drrs.face_meeting.service.PGJoinService;
import cn.drrs.face_meeting.util.NoteResult;
@Service("joinService")
public class PGJoinServiceImple implements PGJoinService{
	@Resource
	private PGJoinDao dao;
	
	
	@Resource 
	private PersonDao personDao;
	//·加入分组
	public NoteResult<Object> insert(List<PGJoin> list){
		NoteResult<Object> nr= new  NoteResult<Object>();
		try {
			nr.setAll(0, "批量加入分组", dao.insert(list));
		} catch (Exception e) {
			nr.setAll(1, "加入分组失败，可能是外键限制", dao.insert(list));
			
			e.printStackTrace();
			return nr;
		}
		return nr;
		
	}
	//·退出分组
	public NoteResult<Object> delete(List<PGJoin> list){
		NoteResult<Object> nr= new  NoteResult<Object>();
		try {
			nr.setAll(0, "批量退出分组", dao.delete(list));
		} catch (Exception e) {
			nr.setAll(1, "退出分组失败", dao.insert(list));
			e.printStackTrace();
			return nr;
		}
		return nr;
		
	}
	public ResponseData findGroupMembersBygId(String gId) {
		// TODO Auto-generated method stub
		ResponseData rd = new ResponseData();
		List<PGJoin> list = new ArrayList<PGJoin>();
		List<Person> personList = new ArrayList<Person>();
		list=dao.getGroupMembersBygId(gId);
		for(int i=0;i<list.size();i++) {
			Person person = new Person();
			person = personDao.findById(list.get(i).getpId());
			personList.add(person);
		}
		String count  = String.valueOf(dao.queryMemberCountBygId(gId));
		rd.setCode("0");
		rd.setCount(count);
		rd.setData(personList);
		rd.setMsg("查询成功");
		return rd;
	}
}
