package cn.drrs.face_meeting.dao;

import java.util.List;

import cn.drrs.face_meeting.entity.PGJoin;


public interface PGJoinDao {

	int insert(List<PGJoin> list);

	int delete(List<PGJoin> list);

}
