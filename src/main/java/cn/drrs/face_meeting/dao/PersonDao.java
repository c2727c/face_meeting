package cn.drrs.face_meeting.dao;

import java.util.List;

import cn.drrs.face_meeting.entity.Person;

public interface PersonDao {
	
	public Person findById(String pId);
	//DONE 按字段查找用户
	public List<Person> findByFields(Person p);

	//更新用户信息
	//函数名与mapper中的id相对应
	public boolean userInfoUpdate(Person p);
	public boolean changePass(Person user);
	public boolean add(Person person);
}
