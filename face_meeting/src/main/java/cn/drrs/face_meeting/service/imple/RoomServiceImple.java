package cn.drrs.face_meeting.service.imple;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.drrs.face_meeting.dao.MeetingDao;
import cn.drrs.face_meeting.dao.RoomDao;
import cn.drrs.face_meeting.entity.*;
import cn.drrs.face_meeting.service.RoomService;
import cn.drrs.face_meeting.util.NoteResult;

@Service("roomService")
public class RoomServiceImple implements RoomService{
	@Resource
	private RoomDao roomDao;
	@Resource
	private MeetingDao meetingDao;

	public NoteResult<Object> insert(String rId, String rName, String rInfo, int rSize, String tName) {
		// DONE 添加房间
		NoteResult<Object> result = new NoteResult<Object>();
		Room r = new Room(rId,rName,rInfo,rSize,tName);
		if(roomDao.insert(r)) {
			result.setStatus(0);
			result.setMsg("添加房间成功!");
		}else {
			result.setStatus(1);
			result.setMsg("添加房间失败!");
		}
		return result;
	}

	public NoteResult<Object> deleteByrId(String rId) {
		// DONE 删除房间
		NoteResult<Object> result = new NoteResult<Object>();
		if(roomDao.deleteByrId(rId)) {
			result.setStatus(0);
			result.setMsg("删除房间成功!");
		}else {
			result.setStatus(1);
			result.setMsg("删除房间失败!");
		}
		return result;
	}

	public NoteResult<Object> update(String rId, String rName, String rInfo, int rSize, String tName) {
		// DONE 更新房间
		NoteResult<Object> result = new NoteResult<Object>();
		Room r = new Room(rId,rName,rInfo,rSize,tName);
		if(roomDao.update(r)) {
			result.setStatus(0);
			result.setMsg("更新房间成功!");
		}else {
			result.setStatus(1);
			result.setMsg("更新房间失败!");
		}
		return result;
	}

	public NoteResult<List<Room>> findAll() {
		// TODO 查找所有房间
		return null;
	}
	/*public NoteResult<List<Room>> findByMeeting(int mNo) {
		// TODO //查找符合该会议要求的房间
		return null;
	}

	public NoteResult<List<Room>> findByMeetingDate(int mNo, Date startDate) {
		// TODO //查找在某日期符合该会议要求的房间
		return null;
	}*/
	public NoteResult<List<Room>> findByMeetingDateTime(int mNo, LocalDate startDate, LocalTime startTime) {
		// TODO //查找某日期某开始时间符合该会议要求的房间
		NoteResult<List<Room>> result = new NoteResult<List<Room>>();
		Meeting m = meetingDao.findBymNo(mNo);
		RoomRestrict rr = new RoomRestrict(m,startDate,startTime);
		System.out.println(rr.toString());
		List<Room> list = roomDao.findByMeetingDateTime(rr);
		result.setStatus(0);
		result.setMsg("查询某日期某开始时间符合该会议要求的房间成功,个数："+list.size());
		result.setData(list);
		return result;
	}
	public NoteResult<List<Room>> findByRoomRestrict(RoomRestrict rr) {
		NoteResult<List<Room>> result = new NoteResult<List<Room>>();
		System.out.println(rr.toString());
		List<Room> list = roomDao.findByMeetingDateTime(rr);
		result.setStatus(0);
		result.setMsg("按会议室限制查询会议室成功,个数："+list.size());
		result.setData(list);
		return result;
	}
	public NoteResult<List<Room>> transfer(Event e, int span) {
		NoteResult<List<Room>> result = new NoteResult<List<Room>>();
		//提取系统时间，和e中的结束时间比较，取较晚者
		LocalTime time = LocalTime.now();
		if(e.getEndTime().isAfter(time))time=e.getEndTime();
		//查询从这个时间起到加上span ，avilable的room
		List<Room> list = roomDao.findByMeetingDateTime(
				new RoomRestrict(e.getMeeting(),e.getStartTime(),span));
		//如果超过5个，返回roomList
		//如果不足5个，基准时间+5min再查询，再检查是否够5个，循环直至够5个
		//排序，与后面会议室冲突可能性越小越好
		//返回roomList
		result.setStatus(0);
		result.setMsg("转场查询会议室成功,个数："+list.size());
		result.setData(list);
		return result;
	}
	
	

}
