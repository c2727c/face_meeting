package cn.drrs.face_meeting.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.drrs.face_meeting.entity.Attend;
import cn.drrs.face_meeting.entity.Group;
import cn.drrs.face_meeting.entity.Meeting;
import cn.drrs.face_meeting.entity.Person;
import cn.drrs.face_meeting.entity.Room;
import cn.drrs.face_meeting.entity.RoomRestrict;
import cn.drrs.face_meeting.util.NoteResult;

public interface GroupDao {

	int insert(Group g);

	int delete(String gId);

	Group findBygId(String gId);

	int update(Group g);

	int queryGroupCount();

	List<Group> getPageofGroup(Map<String, Integer> map);

	List<Group> findBypId(String pId);
	List<Group> findAll();
}
