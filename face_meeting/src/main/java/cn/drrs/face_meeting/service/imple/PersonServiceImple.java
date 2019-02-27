package cn.drrs.face_meeting.service.imple;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.drrs.face_meeting.dao.PersonDao;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.service.PersonService;
import cn.drrs.face_meeting.util.NoteResult;

@Service("personService")	//扫描Spring容器
public class PersonServiceImple implements PersonService{
	@Resource
	private PersonDao personDao;

	public NoteResult<Person> checkLogin(String cCode, String pId, String password) {
		// DONE Service登录
		NoteResult<Person> nr = new NoteResult<Person>();
		Person p = personDao.findById(pId);
		if(p!=null&&p.getpPass().equals(password)) {
			nr.setStatus(0);nr.setMsg("登录：匹配成功");
		}else if(p!=null) {
			nr.setStatus(1);nr.setMsg("登录：密码或用户id不正确");
		}else {
			nr.setStatus(2);nr.setMsg("登录：用户id不存在");
		}nr.setData(p);
		return nr;
	}

	public NoteResult<Person> update(Person p) {
		// TODO 更改账户信息
		NoteResult<Person> nr = new NoteResult<Person>();
		int res = personDao.update(p);
		if(res>0) {
			nr.setAll(0, "成功更新用户信息", p);
		}else {
			nr.setAll(1, "更新用户信息失败", null);
		}
		return nr;
	}

	public NoteResult<Person> findBypId(String pId) {
		NoteResult<Person> nr = new NoteResult<Person>();
		nr.setStatus(0);
		nr.setMsg("按pId查询用户成功");
		nr.setData(personDao.findById(pId));
		return nr;
	}

	public NoteResult<List<Person>> findBypDeptpName(String pDept,String pName) {
		NoteResult<List<Person>> nr = new NoteResult<List<Person>>();
		Person p = new Person();
		p.setpDept(pDept);
		p.setpName("%"+pName+"%");
		List<Person>  list = personDao.findByFields(p);
		nr.setStatus(0);
		nr.setMsg("按名字查询用户成功，个数："+list.size());
		nr.setData(list);
		return nr;
	}
	

	public NoteResult<Person> insert(List<Person> list) {
		
		NoteResult<Person> nr = new NoteResult<Person>();
		if(list.size()<=0)return nr;
		try {
			int i = personDao.insert(list);
			nr.setAll(0, "批量注册成功，新增用户个数："+i, list.get(0));
		}catch(Exception e) {
			nr.setAll(1, "批量注册失败，可能存在重复id", null);
		}
		return nr;
	}

	public NoteResult<Object> delete(List<String> list) {
		// TODO 批量注销
		return null;
	}
	
	
	
}
