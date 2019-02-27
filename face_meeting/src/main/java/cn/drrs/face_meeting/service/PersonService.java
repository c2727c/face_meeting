package cn.drrs.face_meeting.service;

import java.util.List;

import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.util.NoteResult;

public interface PersonService {
	//·登录
	public NoteResult<Person> checkLogin(String cCode,String name,String password);
	//·个人信息修改
	public NoteResult<Person> update(Person p);
	//·TODO 录入人脸信息 
	//·按部门/姓名/所在分组查找其他用户
	public NoteResult<Person> findBypId(String pId);
	public NoteResult<List<Person>> findBypDeptpName(String pDept,String pName);
	//Group集合映射public NoteResult<List<Person>> findBygName(String gName);
	//· 批量注册
	public NoteResult<Person> insert(List<Person> list);
	//· 批量注销
	public NoteResult<Object> delete(List<String> list);
}
