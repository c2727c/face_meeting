package cn.drrs.face_meeting.service;

import java.util.List;

import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.entity.PersonLight;
import cn.drrs.face_meeting.entity.ResponseData;
import cn.drrs.face_meeting.util.NoteResult;

public interface PersonService {
	//����¼
	public NoteResult<Person> checkLogin(String cCode,String name,String password);
	//��������Ϣ�޸�
	public NoteResult<Person> update(Person p);
	//��TODO ¼��������Ϣ 
	//��������/����/���ڷ�����������û�
	public NoteResult<Person> findBypId(String pId);
	public List<PersonLight> findList(String[] users);
	public NoteResult<List<PersonLight>> findBypDeptpName(String pDept,String pName);
	//Group����ӳ��public NoteResult<List<Person>> findBygName(String gName);
	//�� ����ע��
	public NoteResult<Person> insert(List<Person> list);
	//�� ����ע��
	public NoteResult<String> delete(List<String> list);
	public ResponseData getPageofUser(int page, int limit);
	public List<PersonLight> findByFields(Person p);
	public List<String> findAllDeptName();
}
