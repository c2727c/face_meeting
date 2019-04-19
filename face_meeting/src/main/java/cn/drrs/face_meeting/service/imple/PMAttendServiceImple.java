package cn.drrs.face_meeting.service.imple;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.drrs.face_meeting.dao.MeetingDao;
import cn.drrs.face_meeting.dao.PMAttendDao;
import cn.drrs.face_meeting.entity.Attend;
import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.entity.PersonLight;
import cn.drrs.face_meeting.service.PMAttendService;
//import cn.drrs.face_meeting.service.imple.UsbRelayServiceImple;
import cn.drrs.face_meeting.util.NoteResult;


@Service("pmattendService")
public class PMAttendServiceImple implements PMAttendService{
	@Resource
	private PMAttendDao dao;
	@Resource
	private MeetingDao meetingDao;
//	private UsbRelayServiceImple usbRelayService = new UsbRelayServiceImple();
	//添加参会关系
	public NoteResult<Object> insert(List<Attend> alist){
		NoteResult<Object> nr=new NoteResult<Object>();
		try {
			for(Attend attend : alist ) {
				dao.insert(attend);
			}
			nr.setAll(0, "插入参会关系列表成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			nr.setAll(1, "插入参会关系列表失败", null);
			return nr;
		}
		return nr;
	}
	//删除参会关系
	public NoteResult<Object> delete(int mNo,String pId){
		NoteResult<Object> nr=new NoteResult<Object>();
		nr.setAll(0, "删除参会关系成功", dao.delete(mNo,pId));
		return nr;
	}
	public NoteResult<Object> deleteAll(int mNo){
		NoteResult<Object> nr=new NoteResult<Object>();
		nr.setAll(0, "删除mNo参会关系成功", dao.deleteAll(mNo));
		return nr;
	}
	public NoteResult<Object> delete(List<Attend> alist){
		NoteResult<Object> nr=new NoteResult<Object>();
		try {
			for(Attend attend : alist ) {
				dao.delete(attend.getmNo(),attend.getpId());
			}
			nr.setAll(0, "删除参会关系成功", null );
		} catch (Exception e) {
			e.printStackTrace();
			nr.setAll(1, "删除参会关系出错", null);
			return nr;
		}
		return nr;
	}
	//修改参会状态
	public NoteResult<Object> update(Attend a){
		NoteResult<Object> nr=new NoteResult<Object>();
		nr.setAll(0, "更新参会状态为"+a.getState(), dao.update(a));
		return nr;
	}
	//按会议查询参会名单 Meeting集合映射
	//public NoteResult<List<Person>> findAtdBymNo(int mNo);
	//查询历史邀请用户（或按频数，最新排序后返回）
	public NoteResult<Object> checkin(Attend attend) {
		NoteResult<Object> nr=new NoteResult<Object>();
		Meeting m = meetingDao.findFullInfoBymNo(attend.getmNo());
		for(PersonLight p:m.getmAttendList()) {
			if(p.getpId().equals(attend.getpId())) {
				//验证成功
				try {
					attend.setState("到场");
					update(attend);
					String rId = m.getmEventList().get(0).getrId();
//					usbRelayService.openDoor(rId);
					nr.setAll(0, "签到并开启门禁成功", null);
					return nr;
				} catch (Exception e) {
					nr.setAll(0, "签到并开启门禁出错", null);
					e.printStackTrace();
					return nr;
				}
			}
		}
		nr.setAll(2, "不在该会议参会名单", null);
		return nr;
	}
	@Override
	public List<String> conflictTest(LocalDate startDate, LocalTime startTime, LocalTime endTime) {
		List<String> confli =  dao.conflictTest(startDate,startTime,endTime);
		return confli;
	}

}
