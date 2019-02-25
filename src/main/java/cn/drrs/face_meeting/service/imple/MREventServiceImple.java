package cn.drrs.face_meeting.service.imple;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.drrs.face_meeting.dao.MREventDao;
import cn.drrs.face_meeting.entity.Event;
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
			nr.setStatus(-1);
			nr.setMsg("安排会程出错");
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
			nr.setStatus(-1);
			nr.setMsg("删除会程出错");
		}
		return nr;
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
