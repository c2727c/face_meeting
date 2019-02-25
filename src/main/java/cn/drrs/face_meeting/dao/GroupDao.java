package cn.drrs.face_meeting.dao;

import cn.drrs.face_meeting.entity.Group;

public interface GroupDao {

	int insert(Group g);

	int delete(String gId);

	Group findBygId(String gId);

	int update(Group g);
}
