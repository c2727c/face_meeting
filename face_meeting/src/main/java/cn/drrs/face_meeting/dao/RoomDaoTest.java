package cn.drrs.face_meeting.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.drrs.face_meeting.entity.Room;

@Component("RoomDaoTest")
public interface RoomDaoTest {
	public int queryRoomCount();
	//T/ODO mapping
	public List<Room> findRoomByPage(Map<String, Integer> map);
}
