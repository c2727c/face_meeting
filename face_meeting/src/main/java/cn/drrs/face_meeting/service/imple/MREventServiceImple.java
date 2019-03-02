package cn.drrs.face_meeting.service.imple;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.drrs.face_meeting.dao.MREventDao;
import cn.drrs.face_meeting.entity.Event;
import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.service.MREventService;
import cn.drrs.face_meeting.util.NoteResult;
@Service("mREventService")
public class MREventServiceImple implements MREventService{
	@Resource
	private MREventDao mREventDao; 

	public NoteResult<Object> insert(Event e) {
		NoteResult<Object> nr = new NoteResult<Object>();
		int n;
		try {
			n = mREventDao.insert(e);
			nr.setStatus(0);
			nr.setMsg("成功安排一个会程，影响行数："+n);
		}catch(Exception ecp) {
			ecp.printStackTrace();
			nr.setStatus(1);
			nr.setMsg("安排会程出错，可能是外键限制");
		}
		return nr;
	}

	public NoteResult<Object> delete(int mNo, String rId) {
		NoteResult<Object> nr = new NoteResult<Object>();
		int n;
		try {
			n = mREventDao.delete(mNo,rId);
			nr.setStatus(0);
			nr.setMsg("成功删除一个会程，影响行数："+n);
		}catch(Exception ecp) {
			ecp.printStackTrace();
			nr.setStatus(1);
			nr.setMsg("删除会程出错，可能是外键限制");
		}
		return nr;
	}
	
	public NoteResult<Object> update(Event e) {
		NoteResult<Object> nr = new NoteResult<Object>();
		try {
			delete(e.getmNo(),null);
			insert(e);
			nr.setAll(0, "重新安排会程成功", null);
			return nr;
		} catch (Exception e1) {
			e1.printStackTrace();
			nr.setAll(1, "重新安排会程失败", null);
			return nr;
		}
	}

	public NoteResult<List<Event>> findBymNo(int mNo) {
		return findByFields(mNo,null,null);
	}
	public NoteResult<List<Event>> findByrId(String rId) {
		return findByFields(0,rId,null);
	}
	public NoteResult<List<Event>> findByrIdDate(String rId,Date date) {
		return findByFields(0,rId,date);
	}
	private NoteResult<List<Event>> findByFields(int mNo,String rId,Date startDate) {
		NoteResult<List<Event>> nr = new NoteResult<List<Event>>();
		List<Event> list = mREventDao.findByFields(mNo,rId,startDate);
		nr.setStatus(0);
		nr.setMsg("查询会程成功，结果条数："+list.size());
		nr.setData(list);
		return nr;
	}
	
	public NoteResult<List<Event>> getMyEvents(String pId,LocalDate ld,int option) {
		NoteResult<List<Event>> nr = new NoteResult<List<Event>>();
		List<Event> list = new ArrayList<Event>();
		try {
			switch(option) {
			case 1: list =  mREventDao.findByCreator(pId,ld);break;
			case 2: list =  mREventDao.findByAttendDate(pId,ld);break;
			case 3: list =  mREventDao.findByInformDate(pId,ld);break;
			}
			nr.setAll(0, "查询用户："+pId+" "+ld+"后七天的相关会议成功", list);
			return nr;
		} catch (Exception e) {
			e.printStackTrace();
			nr.setAll(1, "查询用户："+pId+"的相关会议失败", null);
			return nr;
		}
	}

	

	/*public NoteResult<List<Event>> findByFQpId(String pId) {
		NoteResult<List<Event>> nr = new NoteResult<List<Event>>();
		List<Event> list = mREventDao.findByFQpId(pId);
		nr.setStatus(0);
		nr.setMsg("查询会程成功，结果条数："+list.size());
		nr.setData(list);
		return nr;
	}
	
	public NoteResult<List<Event>> findByAttendant(String pId) {
		//不需要了
		return null;
	}
	*/

	

}
