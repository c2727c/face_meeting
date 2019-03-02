package cn.drrs.face_meeting.service.imple;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ser.PropertyFilter;

import cn.drrs.face_meeting.dao.MeetingDao;
import cn.drrs.face_meeting.dao.PersonDao;
import cn.drrs.face_meeting.dao.RoomDaoTest;
import cn.drrs.face_meeting.entity.*;
import cn.drrs.face_meeting.service.MeetingService;
import cn.drrs.face_meeting.util.NoteResult;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


@Service("meetingService")
public class MeetingServiceImple implements MeetingService{
	@Resource
	private MeetingDao meetingDao;
	@Resource
	private PersonDao personDao;
	@Resource
	private RoomDaoTest roomDaoTest;
	
	public NoteResult<Object> add(String mTitle, int mSize, int mSpan,String pId_FQ) {
		NoteResult<Object> result = new NoteResult<Object>();
		//新建会议
		System.out.println(mTitle+mSize+mSpan+pId_FQ);
		Person fqp = personDao.findById(pId_FQ);
		System.out.println(fqp.toString());
		Meeting m = new Meeting();
		//赋值
		m.setmTitle(mTitle);
		m.setmSize(mSize);
		m.setmSpan(mSpan);
		m.setpId_FQ(pId_FQ);
		//state由用户特权级决定
		if(fqp.getpPrivilege()>0) {
			m.setState("pass");
		}
		//调用dao保存
		if(meetingDao.save(m)) {
			result.setStatus(0);
			result.setMsg("添加会议成功!");
		}else{
			result.setStatus(1);
			result.setMsg("添加会议失败!");
		}
		return result;
	}
	
	public NoteResult<Meeting> add(Meeting m) {
		NoteResult<Meeting> result = new NoteResult<Meeting>();
		try {
			meetingDao.save(m);
			result.setAll(0, "添加会议成功!", m);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setAll(1, "添加会议失败!", null);
			return result;
		}
	}
	

	public NoteResult<Object> delete(int mNo) {
		NoteResult<Object> result = new NoteResult<Object>();
		//DONE 删除会议
		if(meetingDao.deleteBymNo(mNo)) {
			result.setStatus(0);
			result.setMsg("删除会议成功!");
		}else {
			result.setStatus(1);
			result.setMsg("删除会议失败!");
		}
		return result;
	}
	/*
	public NoteResult<List<Meeting>> findByCreator(String pId_FQ) {
		//DONE 查找我创建的会议(按发起者Id查询)
		//调dao层获得查询结果
		List<Meeting> list = meetingDao.findByCreator(pId_FQ);
		//构建result
		NoteResult<List<Meeting>> result = new NoteResult<List<Meeting>>();
		if(list!=null) {
			System.out.println(list.toString());
			result.setStatus(0);
			result.setMsg("查询我创建的会议成功,个数："+list.size());
			result.setData(list);
		}else {
		}
		return result;
	}

	public NoteResult<List<Meeting>> findByPM_inform(String pId) {
		List<Meeting> list = meetingDao.findByPM_inform(pId);
		//DONE查找通知我的会议
		NoteResult<List<Meeting>> result = new NoteResult<List<Meeting>>();
		if(list!=null) {
			System.out.println(list.toString());
			result.setStatus(0);
			result.setMsg("查询通知我的会议成功,个数："+list.size());
			result.setData(list);
		}else {
			//TODO查询失败result怎么设置，怎么算查询失败
		}
		return result;
	}

	public NoteResult<List<Meeting>> findByPM_attend(String pId) {
		List<Meeting> list = meetingDao.findByPM_inform(pId);
		//DONE 查找我要参加的会议
		NoteResult<List<Meeting>> result = new NoteResult<List<Meeting>>();
		if(list!=null) {
			System.out.println(list.toString()+","+list.size());
			result.setStatus(0);
			result.setMsg("查询我参加的会议成功,个数："+list.size());
			result.setData(list);
		}else {
			//TODO查询失败result怎么设置，怎么算查询失败
		}
		return result;
	}

	
	*/
	public NoteResult<Object> update(Meeting m) {
		NoteResult<Object> result = new NoteResult<Object>();
		if(meetingDao.update(m)) {
			result.setStatus(0);
			result.setMsg("更新会议成功!");
		}else {
			result.setStatus(1);
			result.setMsg("更新会议失败!");
		}
		return result;
	}

	public NoteResult<List<Meeting>> findByFields(Meeting m) {
		NoteResult<List<Meeting>> result = new NoteResult<List<Meeting>>();
		List<Meeting> list =meetingDao.findByFields(m);
		result.setData(list);
		result.setStatus(0);
		result.setMsg("按条件查找会议，个数："+list.size());
		return result;
	}

	public NoteResult<Meeting> findBymNo(int mNo) {
		NoteResult<Meeting> result = new NoteResult<Meeting>();
		Meeting m =meetingDao.findBymNo(mNo);
		result.setData(m);
		result.setStatus(0);
		result.setMsg("按编号取唯一会议");
		return result;
	}


	public NoteResult<List<Meeting>> findAllRoom(int before, int after) {
		// TODO Auto-generated method stub
		NoteResult<List<Meeting>> result = new NoteResult<List<Meeting>>();
		return result;
	}
	@Transactional(readOnly = true)//page默认是从1开始的
	public ResponseData findAllRoomByPage(int page, int limit) {
		// TODO Auto-generated method stub
		System.out.println("进入MeetingService层的findAllRoomByPage方法**************************************************************************************************");
        ResponseData rd=new ResponseData();
        List<Room> roomList;

        System.out.println("page的值："+page+"****************************************************************************************************************");
        page=(page-1)*limit;
        System.out.println("page变换之后的值："+page+"****************************************************************************************************************");
        System.out.println("limit的值："+limit+"***************************************************************************************************************");
        try {
            rd.setCode("0");
            int num = roomDaoTest.queryRoomCount();
            System.out.println("num的值为：" + num + "*****************************************************************************************************");
            String snum=num+"";
            rd.setCount(snum);//获取记录总数
            Map<String,Integer> map = new HashMap<String, Integer>();
            map.put("page",page);//从第几页开始
            map.put("limit",limit);//每页显示多少条记录
            roomList = roomDaoTest.findRoomByPage(map);
            rd.setData(roomList);
            rd.setMsg("请求成功");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return rd;
	}
	public NoteResult<List<Meeting>> getMyMeetings(String pId,int option) {
		NoteResult<List<Meeting>> nr = new NoteResult<List<Meeting>>();
		List<Meeting> list = new ArrayList<Meeting>();
		try {
			Person p = personDao.findById(pId);
			switch(option) {
			case 1: list =  p.getMeetings();break;
			case 2: list =  p.getpAttendMeetingList();break;
			case 3: list =  p.getpInformMeetingList();break;
			}
			nr.setAll(0, "查询用户："+pId+"全部相关会议成功", list);
			return nr;
		} catch (Exception e) {
			e.printStackTrace();
			nr.setAll(1, "查询用户："+pId+"的全部相关会议失败", null);
			return nr;
		}
	}
	
	public NoteResult<List<Meeting>> getMyMeetings(String pId,LocalDate ld,int option) {
		NoteResult<List<Meeting>> nr = new NoteResult<List<Meeting>>();
		List<Meeting> list = new ArrayList<Meeting>();
		try {
			Person p = personDao.findById(pId);
			switch(option) {
			case 1: list =  p.getMeetings();break;
			case 2: list =  p.getpAttendMeetingList();break;
			case 3: list =  p.getpInformMeetingList();break;
			}
			for(Meeting m:list) {
				LocalDate mDate = m.getmEventList().get(0).getStartDate();
				if(mDate.isBefore(ld)||mDate.isAfter(ld.plusDays(7))) {
					list.remove(m);
				}
			}
			nr.setAll(0, "查询用户："+pId+" "+ld+"后七天的相关会议成功", list);
			return nr;
		} catch (Exception e) {
			e.printStackTrace();
			nr.setAll(1, "查询用户："+pId+"的相关会议失败", null);
			return nr;
		}
	}

	public NoteResult<List<JSONObject>> getMyMeetingss(String pId,int option) {
		NoteResult<List<JSONObject>> nr = new NoteResult<List<JSONObject>>();
		List<Meeting> list = new ArrayList<Meeting>();
		List<JSONObject> entities = new ArrayList<JSONObject>();
		try {
			Person p = personDao.findById(pId);
			switch(option) {
			case 1:
				list = p.getMeetings();
				
				break;
			case 2:
				list = p.getpAttendMeetingList();
				break;
			case 3:
				list = p.getpInformMeetingList();
				break;
			}
			for(Meeting m :list) {
				 JsonConfig config = JsonConfig.getInstance();
				 config.setExcludes(new String[]{"emps"});
				 entities.add(JSONObject.fromObject(m));
			}
			nr.setAll(0, "查询用户："+pId+"的相关会议成功", entities);
			return nr;
		} catch (Exception e) {
			e.printStackTrace();
			nr.setAll(1, "查询用户："+pId+"的相关会议失败", null);
			return nr;
		}
	}
	

}