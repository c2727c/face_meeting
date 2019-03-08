package cn.drrs.face_meeting.service.imple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.drrs.face_meeting.dao.PersonDao;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.entity.ResponseData;
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
		try {
			personDao.update(p);
			Person per = personDao.findById(p.getpId());
			nr.setAll(0, "更新用户信息成功", per);
		} catch (Exception e) {
			Person per = personDao.findById(p.getpId());
			nr.setAll(1, "更新用户信息失败", per);
			e.printStackTrace();
			return nr;
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

	public ResponseData getPageofUser(int page, int limit) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				System.out.println("进入PersonService层的getPageofUser方法**************************************************************************************************");
		        ResponseData rd=new ResponseData();
		        List<Person> personList;

		        System.out.println("page的值："+page+"****************************************************************************************************************");
		        page=(page-1)*limit;
		        System.out.println("page变换之后的值："+page+"****************************************************************************************************************");
		        System.out.println("limit的值："+limit+"***************************************************************************************************************");
		        try {
		            rd.setCode("0");
		            int num = personDao.queryUserCount();
		            System.out.println("num的值为：" + num + "*****************************************************************************************************");
		            String snum=num+"";
		            rd.setCount(snum);//获取记录总数
		            Map<String,Integer> map = new HashMap<String, Integer>();
		            map.put("page",page);//从第几页开始
		            map.put("limit",limit);//每页显示多少条记录
		            personList = personDao.getPageofUser(map);
		            rd.setData(personList);
		            rd.setMsg("请求成功");
		        }catch(Exception ex){
		            ex.printStackTrace();
		        }
		        return rd;
	}

	public List<Person> findByFields(Person p) {
		return personDao.findByFields(p);
	}

	public List<String> findAllDeptName() {
		return personDao.findAllDeptName();
	}
	
	
	
}
