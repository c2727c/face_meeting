package cn.drrs.face_meeting.dao;

import java.util.List;
import java.util.Map;

import cn.drrs.face_meeting.entity.Person;

public interface PersonDao {
	
	public Person findById(String pId);
	public Person findFullInfoById(String pId);
	public List<Person> findAll();
	
	
	//DONE 按字段查找用户
	public List<Person> findByFields(Person p);
	public int update(Person p);
	
	//批量注册
	public int insert(List<Person> list);
	public int queryUserCount();
	public List<Person> getPageofUser(Map<String, Integer> map);
	public List<String> findAllDeptName();
	public boolean delete(List<String> list);

}
